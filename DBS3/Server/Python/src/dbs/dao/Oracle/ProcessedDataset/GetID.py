#!/usr/bin/env python
"""
This module provides ProcessedDataset.GetID data access object.
"""
__revision__ = "$Id: GetID.py,v 1.5 2010/03/05 19:51:41 yuyi Exp $"
__version__ = "$Revision: 1.5 $"

from WMCore.Database.DBFormatter import DBFormatter

class GetID(DBFormatter):
    """
    ProcessedDataset GetID DAO class.
    """
    def __init__(self, logger, dbi, owner):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
        self.sql = \
"""
SELECT PD.PROCESSED_DS_ID, PD.PROCESSED_DS_NAME
FROM %sPROCESSED_DATASETS PD 
""" % (self.owner)

    def execute(self, conn, name, transaction = False):
        """
        returns id for a given processed dataset name
        """
	if not conn:
	    raise Exception("dbs/dao/Oracle/ProcessedDataset/GetID expects db connection from upper layer.")
        sql = self.sql
        sql += "WHERE PD.PROCESSED_DS_NAME = :processeddsname" 
        binds = {"processeddsname":name}
        result = self.dbi.processData(sql, binds, conn, transaction)
        plist = self.formatDict(result)
        if len(plist) == 1:
            return plist[0]["processed_ds_id"]
        else:
            return -1
