#!/usr/bin/env python
"""
This module provides business object class to interact with Block. 
"""

__revision__ = "$Id: DBSBlock.py,v 1.23 2010/03/25 17:06:00 afaq Exp $"
__version__ = "$Revision: 1.23 $"

from WMCore.DAOFactory import DAOFactory
from dbs.utils.dbsUtils import dbsUtils

class DBSBlock:
    """
    Block business object class
    """
    def __init__(self, logger, dbi, owner):
        daofactory = DAOFactory(package='dbs.dao', logger=logger, dbinterface=dbi, owner=owner)
        self.logger = logger
        self.dbi = dbi

        self.blocklist = daofactory(classname="Block.List")
        self.sm = daofactory(classname = "SequenceManager")
        self.datasetid = daofactory(classname = "Dataset.GetID")
        self.siteid = daofactory(classname = "Site.GetID")
        self.blockin = daofactory(classname = "Block.Insert")
	self.updatestatus = daofactory(classname='Block.UpdateStatus')
	
    def updateStatus(self, block_name="", open_for_writing=0):
	"""
	Used to toggle the status of a block open_for_writing=1, open for writing, open_for_writing=0, closed
	"""

	open_for_writing=int(open_for_writing)
        conn = self.dbi.connection()
	trans = conn.begin()
	if not len(block_name) > 1:
	    raise Exception("Provide a valid block_name")
	if (open_for_writing < 0)  or (open_for_writing > 1) :
	    raise Exception("open_for_writing can only be 0 or 1 : passed %s" %open_for_writing)
	try :
	    self.updatestatus.execute(conn, block_name, open_for_writing, trans)
	    trans.commit()
	except Exception, ex:
	    trans.rollback()
	    raise ex
		
	finally:
	    trans.close()
	    conn.close()


    def listBlocks(self, dataset="", block_name="", site_name=""):
        """
        dataset, block_name or site_name must be passed.
        """
	if not dataset:
	    if not block_name:
		raise Exception("You must specify at least one parameter (dataset, block_name) with listBlocks api")
	    if block_name =='%':
		raise Exception("You cannot specify block_name ='*', cannot list all blocks of all datasets")
	if dataset=='%' and not block_name and not site_name:
	    raise Exception("You cannot specify dataset='*', cannot list all blocks of all datasets")
	if dataset=='%' and block_name=='%':
	    raise Exception("You cannot specify dataset='*', block_name='*' cannot list all blocks of all datasets")
	if dataset=='%' and block_name=='%' and site_name=='%':
	    raise Exception("You cannot specify dataset='*', block_name='*', site_name='*' cannot list all blocks of all datasets at all sites")
	try:
	    conn = self.dbi.connection()
	    result = self.blocklist.execute(conn, dataset, block_name, site_name)
	    conn.close()
	    return result
        except Exception, ex:
	    raise ex
        finally:
	    conn.close()
    
    
    def insertBlock(self, businput):
        """
        Input dictionary has to have the following keys:
        blockname
	
        It may have:
        open_for_writing, origin_site(name), block_size,
        file_count, creation_date, create_by, last_modification_date, last_modified_by
        
        it builds the correct dictionary for dao input and executes the dao

        NEED to validate there are no extra keys in the businput
        """
        conn = self.dbi.connection()
        tran = conn.begin()
        try:
	    blkinput = {
		"last_modification_date":businput["last_modification_date"],
		"last_modified_by":businput["last_modified_by"],
		"create_by":businput["create_by"],
		"creation_date":businput["creation_date"],
		"open_for_writing":businput["open_for_writing"],
		"block_size":businput["block_size"],
		"file_count":businput["file_count"],
		"block_name":businput["block_name"]
	    }
            blkinput["dataset_id"] = self.datasetid.execute(conn, (businput["block_name"]).split('#')[0], tran)
            blkinput["block_id"] =  self.sm.increment(conn, "SEQ_BK", tran)
            if(businput.has_key("origin_site")):
                blkinput["origin_site"] = self.siteid.execute(conn, businput["origin_site"], tran)
            self.blockin.execute(conn, blkinput, tran)
            tran.commit()
    
        except Exception, e:
	    if str(e).lower().find("unique constraint") != -1 or str(e).lower().find("duplicate") != -1:
		pass
	    else:
		tran.rollback()
		self.logger.exception(e)
		raise
		
        finally:
            conn.close()
