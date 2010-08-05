#!/usr/bin/env python
"""
This module provides DataTier.List data access object.
"""
__vision__ = "$Id:$"
__revision__ = "$Revision: $"

from WMCore.Database.DBFormatter import DBFormatter

class List(DBFormatter):
    """
    DataTier List DAO class.
    """
    def __init__(self, logger, dbi, owner):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
        self.sql = \
"""
SELECT PE.PROCESSING_VERSION, PE.CREATE_DATE, PE.CREATE_BY, PE.DESCRIPTION   
FROM %sPROCESSING_ERAS PE 
""" % (self.owner)

    def execute(self, conn, processingV, transaction = False):
	if not conn:
	    raise Exception("dbs/dao/Oracle/ProcessingEra/List expects db connection from upper layer.")
        sql = self.sql
	binds={}
	if processingV:
	    sql += "WHERE PE.PROCESSING_VERSION = :processingV" 
	    binds = {"processingV":processingV}
        result = self.dbi.processData(sql, binds, conn, transaction)
        plist = self.formatDict(result)
        return plist
