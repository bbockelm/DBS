#!/usr/bin/env python
"""
This module provides business object class to interact with Block. 
"""

__revision__ = "$Id: DBSBlock.py,v 1.13 2010/01/01 19:00:36 akhukhun Exp $"
__version__ = "$Revision: 1.13 $"

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


    def listBlocks(self, dataset="", block_name="", site_name=""):
        """
        dataset, block_name or site_name must be passed.
        """
        return self.blocklist.execute(dataset, block_name, site_name)
    
    
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
            blkinput["dataset_id"] = self.datasetid.execute((businput["block_name"]).split('#')[0], conn, True)
            blkinput["block_id"] =  self.sm.increment("SEQ_BK", conn, True)
            if(businput.has_key("origin_site")):
                blkinput["origin_site"] = self.siteid.execute(businput["origin_site"], conn, True)
            self.blockin.execute(blkinput, conn, True)
            tran.commit()
    
        except Exception, e:
	    if str(e).lower().find("unique constraint") != -1 :
		pass
	    else:
		tran.rollback()
		self.logger.exception(e)
		raise
		
        finally:
            conn.close()
