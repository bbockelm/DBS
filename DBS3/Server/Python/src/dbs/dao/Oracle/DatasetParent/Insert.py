#!/usr/bin/env python
""" DAO Object for DatasetParents table """ 

__revision__ = "$Revision: 1.2 $"
__version__  = "$Id: Insert.py,v 1.2 2009/10/20 02:19:19 afaq Exp $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi, owner):
            DBFormatter.__init__(self, logger, dbi)
            self.owner = "%s." % owner
	    self.logger = logger

            self.sql = """INSERT INTO %sDATASET_PARENTS ( DATASET_PARENT_ID, THIS_DATASET_ID, PARENT_DATASET_ID) VALUES (:dataset_parent_id, :this_dataset_id, :parent_dataset_id)""" % (self.owner)
	    
    def execute( self, binds, conn=None, transaction=False ):
            result = self.dbi.processData(self.sql, binds, conn, transaction)


