!/usr/bin/env python
""" DAO Object for BranchHashes table """ 

__revision__ = "$Revision: 1.3 $"
__version__  = "$Id: Insert.py,v 1.3 2009/11/24 10:58:14 akhukhun Exp $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi):
            DBFormatter.__init__(self, logger, dbi)
            self.owner = "%s." % self.dbi.engine.url.username

            self.sql = """INSERT INTO %sBRANCH_HASHES ( BRANCH_HASH_ID, HASH, CONTENT) VALUES (:branch_hash_id, :branch_hash, :content)""" % (self.owner)

    def execute( self, binds, conn=None, transaction=False ):
            result = self.dbi.processData(self.sql, binds, conn, transaction)
            return


