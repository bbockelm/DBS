#!/usr/bin/env python
""" DAO Object for BlockSites table """ 

__revision__ = "$Revision: 1.5 $"
__version__  = "$Id: Insert.py,v 1.5 2010/03/05 15:38:02 yuyi Exp $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi):
            DBFormatter.__init__(self, logger, dbi)
	    self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
            self.sql = """INSERT INTO %sBLOCK_SITES ( BLOCK_SITE_ID, SITE_ID, BLOCK_ID) VALUES (:blocksiteid, (SELECT SITE_ID FROM SITES WHERE SITE_NAME=:sitename), :blockid)""" % (self.owner)

    def execute( self, conn, block_id, site_name, transaction=False ):
	if not conn:
	    raise Exception("dbs/dao/Oracle/BlockSite/Insert expects db connection from up layer.")
	binds={}
	binds['blocksiteid']=block_site_id
	binds['blockid']=block_id
	binds['sitename']=site_name
	result = self.dbi.processData(self.sql, binds, conn, transaction)
	return


