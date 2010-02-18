#!/usr/bin/env python
"""
This module provides PrimaryDataset.List data access object.
"""
__revision__ = "$Id: List.py,v 1.11 2010/02/11 22:52:00 afaq Exp $"
__version__ = "$Revision: 1.11 $"


from WMCore.Database.DBFormatter import DBFormatter

class List(DBFormatter):
    """
    PrimaryDataset List DAO class.
    """
    def __init__(self, logger, dbi, owner=""):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
        self.sql = \
"""
SELECT P.PRIMARY_DS_ID, P.PRIMARY_DS_NAME, 
       P.CREATION_DATE, P.CREATE_BY,
       PT.PRIMARY_DS_TYPE  
FROM %sPRIMARY_DATASETS P
JOIN %sPRIMARY_DS_TYPES PT ON PT.PRIMARY_DS_TYPE_ID = P.PRIMARY_DS_TYPE_ID
""" % (self.owner, self.owner)

    def execute(self, primary_ds_name="", conn=None):
        """
        Lists all primary datasets if pattern is not provided.
        """
        if not conn:
            conn = self.dbi.connection()
            
        sql = self.sql
        binds = {}
        
        if primary_ds_name:
            op = ("=", "like")["%" in primary_ds_name]
            sql += "WHERE P.PRIMARY_DS_NAME %s :primary_ds_name" % op
            binds.update(primary_ds_name=primary_ds_name)
        """    
        cursor = conn.connection.cursor()
        cursor.execute(sql, binds)
	"""
	cursors = self.dbi.processData(sql, binds, conn, transaction=False, returnCursor=True)
	assert len(cursors) == 1, "primary DS does not exist"
		
        result = self.formatCursor(cursors[0])
        conn.close()
        return result
