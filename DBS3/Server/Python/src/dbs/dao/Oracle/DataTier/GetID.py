#!/usr/bin/env python
"""
This module provides DataTier.GetID data access object.
"""
__revision__ = "$Id: GetID.py,v 1.1 2009/10/28 09:51:50 akhukhun Exp $"
__version__ = "$Revision: 1.1 $"

from WMCore.Database.DBFormatter import DBFormatter

class GetID(DBFormatter):
    """
    DataTier GetID DAO class.
    """
    def __init__(self, logger, dbi):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % self.dbi.engine.url.username
        self.sql = \
"""
SELECT DT.DATA_TIER_ID, DT.DATA_TIER_NAME
FROM %sDATA_TIERS DT 
""" % (self.owner)

    def execute(self, name, conn = None, transaction = False):
        """
        returns id for a given datatier name
        """
        sql = self.sql
        sql += "WHERE DT.DATA_TIER_NAME = :datatier" 
        binds = {"datatier":name}
        result = self.dbi.processData(sql, binds, conn, transaction)
        plist = self.formatDict(result)
        assert len(plist) == 1, \
            "DataTier %s does not exist" % name
        return plist[0]["data_tier_id"]
