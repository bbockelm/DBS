#!/usr/bin/env python
"""
This module provides Block.List data access object.
"""
__revision__ = "$Id: List.py,v 1.17 2010/04/22 15:56:40 yuyi Exp $"
__version__ = "$Revision: 1.17 $"

from WMCore.Database.DBFormatter import DBFormatter
from WMCore.Database.MySQLCore import  MySQLInterface

class List(DBFormatter):
    """
    Block List DAO class.
    """
    def __init__(self, logger, dbi, owner=""):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
        self.sql = \
"""
SELECT B.BLOCK_ID, B.BLOCK_NAME, B.OPEN_FOR_WRITING, 
        B.BLOCK_SIZE, B.FILE_COUNT,
        B.DATASET_ID, DS.DATASET,
        B.ORIGIN_SITE_NAME
FROM %sBLOCKS B
JOIN %sDATASETS DS ON DS.DATASET_ID = B.DATASET_ID
""" % ((self.owner,)*2)

    def execute(self, conn, dataset="", block_name="", site_name="", transaction = False):
        """
        dataset: /a/b/c
        block: /a/b/c#d
        """	
	if not conn:
	    raise Exception("dbs/dao/Oarcle/Block/List expects db connection from up layer.")
        sql = self.sql
        binds = {}
        op = ("=", "like")["%" in block_name]
        if dataset:
            sql += "WHERE DS.DATASET = :dataset"
            binds.update(dataset=dataset)
        
            if block_name:
                sql += " AND B.BLOCK_NAME %s :block_name" % op
                binds.update({"block_name":block_name})
            if site_name:
                sql += " AND B.ORIGIN_SITE_NAME = :site_name"
                binds.update(site_name = site_name)
                
        elif block_name:
            sql += "WHERE B.BLOCK_NAME = :block_name"
            binds.update(block_name = block_name)
            
            if site_name:
                sql += " AND B.ORIGIN_SITE_NAME = :site_name"
                binds.update(site_name = site_name)
                
        elif site_name:
            sql += " WHERE B.ORIGIN_SITE_NAME = :site_name"
            binds.update( site_name = site_name)
            
        else: 
            raise Exception("dataset, block_name or origin_site_name must be provided")
	cursors = self.dbi.processData(sql, binds, conn, transaction, returnCursor=True)
	assert len(cursors) == 1, "block does not exist"
#if self.dbi.engine.dialect.name == 'mysql' :
        result = self.formatCursor(cursors[0])
        return result
