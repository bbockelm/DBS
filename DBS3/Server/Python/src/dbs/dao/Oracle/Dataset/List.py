#!/usr/bin/env python
"""
This module provides Dataset.List data access object.
"""
__revision__ = "$Id: List.py,v 1.12 2009/11/30 09:53:44 akhukhun Exp $"
__version__ = "$Revision: 1.12 $"

from WMCore.Database.DBFormatter import DBFormatter

class List(DBFormatter):
    """
    Dataset List DAO class.
    """
    def __init__(self, logger, dbi, owner):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % owner
        self.sql = \
"""
SELECT D.DATASET_ID, D.DATASET, D.IS_DATASET_VALID, 
        D.XTCROSSSECTION, D.GLOBAL_TAG, 
        D.CREATION_DATE, D.CREATE_BY, 
        D.LAST_MODIFICATION_DATE,
        D.DATASET_TYPE_ID, D.PHYSICS_GROUP_ID,
        DP.DATASET_TYPE, PH.PHYSICS_GROUP_NAME
FROM %sDATASETS D 
LEFT OUTER JOIN %sPHYSICS_GROUPS PH ON PH.PHYSICS_GROUP_ID = D.PHYSICS_GROUP_ID
JOIN %sDATASET_TYPES DP on DP.DATASET_TYPE_ID = D.DATASET_TYPE_ID
""" % ((self.owner,)*3)

        self.formatkeys = {"PHYSICS_GROUP_DO":["PHYSICS_GROUP_ID", "PHYSICS_GROUP_NAME"], 
                          "DATASET_TYPE_DO":["DATASET_TYPE_ID","DATASET_TYPE"]}

    def formatDict(self, result):
        dictOut = []
        r = result[0]
        descriptions = [str(x) for x in r.keys]
        for i in r.fetchall():
            idict = dict(zip(descriptions, i)) 

            for k in self.formatkeys:
                idict[k] = {}
                for kk in self.formatkeys[k]:
                    idict[k][kk] = idict[kk]
                    del idict[kk]
                    
            dictOut.append(idict) 
	return dictOut
        #return {"result":dictOut} 

    def execute(self, dataset="", conn = None, transaction = False):
        """
        dataset key must be of /a/b/c pattern
        """	
        sql = self.sql
        if not dataset:
            result = self.dbi.processData(sql, conn=conn, transaction=transaction)
        else:
            sql += " WHERE D.DATASET %s :dataset" % ("=", "like")["%" in dataset]
            binds = {"dataset":dataset}
            result = self.dbi.processData(sql, binds, conn, transaction)
        return self.formatDict(result)
