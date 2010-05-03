#!/usr/bin/env python
""" DAO Object for DataTiers table """ 

__revision__ = "$Revision: 1.5 $"
__version__  = "$Id: Insert.py,v 1.5 2010/03/05 17:00:53 yuyi Exp $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi, owner):
            DBFormatter.__init__(self, logger, dbi)
	    self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
	    
            self.sql = """INSERT INTO %sDATA_TIERS ( DATA_TIER_ID, DATA_TIER_NAME, CREATION_DATE, CREATE_BY) VALUES (:data_tier_id, :data_tier_name, :creation_date, :create_by)""" % (self.owner)

    def execute( self, conn, dtObj, transaction=False ):
	if not conn:
	    raise Exception("dbs/dao/Oracle/DataTier/Insert expects db connection from up layer.")
	result = self.dbi.processData(self.sql, dtObj, conn, transaction)
	return


