#!/usr/bin/env python
""" DAO Object for BlockParents table """ 

__revision__ = "$Revision: 1.2 $"
__version__  = "$Id: Insert.py,v 1.2 2009/10/20 02:19:17 afaq Exp $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi, owner):
            DBFormatter.__init__(self, logger, dbi)
            self.owner = "%s." % owner
	    self.logger = logger

            self.sql = """INSERT INTO %sBLOCK_PARENTS ( BLOCK_PARENT_ID, THIS_BLOCK_ID, PARENT_BLOCK_ID) VALUES (:block_parent_id, :this_block_id, :parent_block_id)""" % (self.owner)

    def execute( self, binds, conn=None, transaction=False ):
            result = self.dbi.processData(self.sql, binds, conn, transaction)

