#!/usr/bin/env python
"""
This module provides Dataset.GetID data access object.
Light dao object to get the id for a give /primds/procds/tier
"""
__revision__ = "$Id: List.py,v 1.3 2009/10/27 17:24:48 akhukhun Exp $"
__version__ = "$Revision: 1.3 $"

from WMCore.Database.DBFormatter import DBFormatter
class GetID(DBFormatter):
    """
    Dataset GetID DAO class.
    """
    def __init__(self, logger, dbi):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % self.dbi.engine.url.username
        self.sql = \
"""
SELECT D.DATASET_ID, D.DATASET
FROM %sDATASETS D 
""" % ( self.owner )
        
	
    def execute(self, path, conn = None, transaction = False):
        """
        returns id for a given path = /primds/procds/tier
        """	
        sql = self.sql
        sql += "WHERE D.DATASET = :path"
        binds = {"path":path}
        result = self.dbi.processData(sql, binds, conn, transaction)
        plist = self.formatDict(result)
        assert len(plist) == 1, "Dataset %s does not exist" % path
        return plist[0]["dataset_id"]
