#!/usr/bin/env python
"""
DBS Insert Buffer Polling Module
"""
__revision__ = "$Id: DBSInsertBufferPoller.py,v 1.3 2010/05/27 19:36:11 afaq Exp $"
__version__ = "$Revision: 1.3 $"


"""
Polls FILE_BUFFER table and post entries to FILES (and related) tables
"""

import threading
import logging
import traceback
import os

from WMCore.WorkerThreads.BaseWorkerThread import BaseWorkerThread
from WMCore.DAOFactory import DAOFactory

class DBSInsertBufferPoller(BaseWorkerThread) :

    """
    Handles poll-based File Inserts
    """
    
    def __init__(self, config):
        """
        Initialise class members
        """

	# Used for creating connections/transactions
        myThread = threading.currentThread()
	self.dbi = myThread.dbi

	self.logger = myThread.logger
	
	BaseWorkerThread.__init__(self)
	# get the db owner
        self.config  = config
	dbconfig = config.section_("CoreDatabase")
	self.dbowner=dbconfig.dbowner

    # This is only called once by the frwk
    def setup(self, parameters):
        """
        Load DB objects required for queries
        """
	
	# Setup the DAO objects
	daofactory = DAOFactory(package='dbs.dao', logger=self.logger, dbinterface=self.dbi, owner=self.dbowner)
	self.sm = daofactory(classname = "SequenceManager")
	self.filein = daofactory(classname = "File.Insert")
	self.flumiin = daofactory(classname = "FileLumi.Insert")
	self.fparentin = daofactory(classname = "FileParent.Insert")
	self.fpbdlist = daofactory(classname = "FileParentBlock.List")
	self.blkparentin = daofactory(classname = "BlockParent.Insert")
	self.dsparentin = daofactory(classname = "DatasetParent.Insert")
	self.blkstats = daofactory(classname = "Block.ListStats")
	self.blkstatsin = daofactory(classname = "Block.UpdateStats")
	self.fconfigin = daofactory(classname='FileOutputMod_config.Insert')
	self.bufdeletefiles = daofactory(classname="FileBuffer.DeleteFiles")
	self.buflist = daofactory(classname="FileBuffer.List")
	self.buflistblks = daofactory(classname="FileBuffer.ListBlocks")
	self.buffinddups = daofactory(classname="FileBuffer.FindDuplicates")
	self.bufdeletedups = daofactory(classname="FileBuffer.DeleteDuplicates")
	self.compstatus = daofactory(classname="ComponentStatus.Insert")

    # called by frk at the termination time
    def terminate(self, params):
	"""
	Terminate
	"""
	logging.debug("Terminating DBSUploadPoller")

    # This is the actual poll method, called over and over by the frwk
    def algorithm(self, parameters):
	"""
	Performs the handleBuffer method, called by frwk over and over (until terminate is called)
	"""
	logging.debug("Running dbs insert buffer algorithm")
	self.handleBuffer()	
	
    def handleBuffer(self):
	"""
	The actual handle method for performing inserts from dbs buffer
	"""
	try :
	    # Remove the duplicate entries from the buffer
	    self.removeDuplicates()
	    blks = self.getBlocks()
	    for ablk_id in blks:
	        bufferedinput = self.getBufferedFiles(ablk_id["block_id"])
	        insertinput = [eval(afile['file_blob'])  for afile in bufferedinput ]
	        #for afile in insertinput:
		    #self.logger.debug("run_inserts : %s" % afile.keys() )
		    #self.logger.debug("run_inserts : %s" % afile['file_output_config_list'] )
		if len(insertinput) > 0:
		    self.insertBufferedFiles(businput=insertinput)
	except Exception, ex:
	    self.logger.exception("DBS Insert Buffer Poller Exception: %s" %ex)
	
    def reportStatus(self):
	"""
	This is a local function, basically component reports its status to database
	"""
	try:
	    conn = self.dbi.connection()
	    tran = conn.begin()
	    comp_status_id = self.sm.increment(conn, "SEQ_CS", transaction=tran)
	    statusObj={comp_status_id : comp_status_id, "component_name" : "WRITER BUFFER", "last_contact_time" : time.time()}
	    self.compstatus.execute(conn, statusObj, tran)
	    tran.commit()
	except Exception, ex:
	    tran.rollabck()
	    self.logger.exception("DBS Insert Buffer Poller Exception: %s" %ex)
    	finally:
	    conn.close()
    
    def getBlocks(self):
	"""
	Get the blocks that need to be migrated
	"""
	try:
	    conn = self.dbi.connection()
	    result = self.buflistblks.execute(conn)
            conn.close()
            return result
	except Exception, ex:
	    raise ex
	finally:
	    conn.close()
    
    def getBufferedFiles(self, block_id):
        """
        Get some files from the insert buffer
        """
	    
	try:
	    conn = self.dbi.connection()
	    result = self.buflist.execute(conn, block_id)
            conn.close()
            return result
	except Exception, ex:
	    raise ex
	finally:
	    conn.close()
   
    def removeDuplicates(self):
      """
      Check to see if there are duplicate entries for a block in the buffer, remove them
      """

      try:
	conn = self.dbi.connection()
	dups = self.buffinddups.execute(conn)
	if len(dups) > 0 :
	    # If there are duplicates, delete them
	    tran = conn.begin()
	    self.bufdeletedups.execute(conn, dups, tran)
	    tran.commit()
      except Exception, ex:
        tran.rollback()
        self.logger.exception(ex)
	raise ex
      finally:
        conn.close()
	
    def insertBufferedFiles(self, businput):
	"""

	insert the files from the buffer
	The files contain everything needed to insert them into various tables
	"""
	
	conn = self.dbi.connection()
	tran = conn.begin()
	block_id=""
	try:
	    files=[]
	    lumis=[]
	    parents=[]
	    configs=[]	    
	    fidl=[]
	    flfnl=[]
	    fileInserted=False
	    
	    for ablob in businput:
		block_id=ablob["file"]["block_id"]
		if ablob.has_key("file") : 
		    files.append(ablob["file"])
		    fidl.append(ablob["file"]["file_id"])
		    flfnl.append({"lfn" : ablob["file"]["logical_file_name"] })
	        if ablob.has_key("file_lumi_list") : lumis.extend(ablob["file_lumi_list"])
		if ablob.has_key("file_parent_list") : parents.extend(ablob["file_parent_list"])
		if ablob.has_key("file_output_config_list") : configs.extend(ablob["file_output_config_list"]) 

	    # insert files
	    if len(files) > 0:	
		self.filein.execute(conn, files, transaction=tran)
		fileInserted=True
	    # insert file - lumi   
	    if len(lumis) > 0:
		self.flumiin.execute(conn, lumis, transaction=tran)
	    # insert file parent mapping
	    if len(parents) > 0:
		self.fparentin.execute(conn, parents, transaction=tran)
	    # insert output module config mapping
	    if len(configs) > 0:
		self.fconfigin.execute(conn, configs, transaction=tran)  
	    # List the parent blocks and datasets of the file's parents (parent of the block and dataset)
	    # fpbdlist, returns a dict of {block_id, dataset_id} combination
	    if fileInserted:
		fpblks=[]
		fpds=[]
		fileParentBlocksDatasets = self.fpbdlist.execute(conn, fidl, transaction=tran)
		for adict in fileParentBlocksDatasets:
		    if adict["block_id"] not in fpblks:
			fpblks.append(adict["block_id"])
		    if adict["dataset_id"] not in fpds:
		    	fpds.append(adict["dataset_id"])
		# Update Block parentage
		if len(fpblks) > 0 :
		    # we need to bulk this, number of parents can get big in rare cases
		    bpdaolist=[]
		    iPblk = 0
		    fpblkInc = 10
		    bpID = self.sm.increment(conn, "SEQ_BP", transaction=tran, incCount=fpblkInc)
		    for ablk in fpblks:
			if iPblk == fpblkInc:
			    bpID = self.sm.increment(conn, "SEQ_BP", transaction=tran, incCount=fpblkInc)
			    iPblk = 0
			bpdao={ "this_block_id": block_id }
			bpdao["parent_block_id"] = ablk
			bpdao["block_parent_id"] = bpID
			bpdaolist.append(bpdao)
		    # insert them all
		    # Do this one by one, as its sure to have duplicate in dest table

		    for abp in bpdaolist:
			try:
			    self.blkparentin.execute(conn, abp, transaction=tran)
			except exceptions.IntegrityError, ex:
			    if str(ex).find("unique constraint") != -1 or str(ex).lower().find("duplicate") != -1:
				pass
			    else:
				raise
		# Update dataset parentage
		if len(fpds) > 0 :
		    dsdaolist=[]
		    iPds = 0
		    fpdsInc = 10
		    pdsID = self.sm.increment(conn, "SEQ_DP", transaction=tran, incCount=fpdsInc)
		    for ads in fpds:
			if iPds == fpdsInc:
			    pdsID = self.sm.increment(conn, "SEQ_DP", transaction=tran, incCount=fpdsInc)
			    iPds = 0
			dsdao={ "this_dataset_id": dataset_id }
			dsdao["parent_dataset_id"] = ads
			dsdao["dataset_parent_id"] = pdsID # PK of table 
			dsdaolist.append(dsdao)
		    # Do this one by one, as its sure to have duplicate in dest table
		    for adsp in dsdaolist:
			try:
			    self.dsparentin.execute(conn, adsp, transaction=tran)
			except exceptions.IntegrityError, ex:
			    if str(ex).find("unique constraint") != -1 or str(ex).lower().find("duplicate") != -1:
				pass
			    else:
				raise
		# Update block parameters, file_count, block_size
		blkParams=self.blkstats.execute(conn, block_id, transaction=tran)
		blkParams['block_size']=long(blkParams['block_size'])
		self.blkstatsin.execute(conn, blkParams, transaction=tran)
	    # Delete the just inserted files
	    self.bufdeletefiles.execute(conn, flfnl, transaction=tran)
	    # All good ?. 
            tran.commit()

	except Exception, e:
	    tran.rollback()
	    self.logger.exception(e)
	    raise

	finally:
	    conn.close()
	
