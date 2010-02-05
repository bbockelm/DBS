#!/usr/bin/env python
""" DAO Object for BlockParents table """ 

__revision__ = "$Revision: 1.3 $"
__version__  = "$Id: Insert.py,v 1.3 2010/01/05 00:24:58 afaq Exp $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi, owner):
            DBFormatter.__init__(self, logger, dbi)
            self.owner = "%s." % owner
	    self.logger = logger

            self.sql = """INSERT INTO %sBLOCK_PARENTS ( BLOCK_PARENT_ID, THIS_BLOCK_ID, PARENT_BLOCK_ID) VALUES (:block_parent_id, :this_block_id, :parent_block_id)""" % (self.owner)

    def execute( self, binds, conn=None, transaction=False ):
            result = self.dbi.processData(self.sql, binds, conn, transaction)

