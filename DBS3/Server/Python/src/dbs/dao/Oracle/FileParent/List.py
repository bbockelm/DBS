#!/usr/bin/env python
"""
This module provides FileParent.List data access object.
"""
__revision__ = "$Id: List.py,v 1.3 2010/02/19 17:36:25 yuyi Exp $"
__version__ = "$Revision: 1.3 $"


from WMCore.Database.DBFormatter import DBFormatter

class List(DBFormatter):
    """
    FileParent List DAO class.
    """
    def __init__(self, logger, dbi, owner=""):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
        self.sql = \
"""
SELECT PF.LOGICAL_FILE_NAME parent_logical_file_name, 
       PF.FILE_ID parent_file_id,
       F.LOGICAL_FILE_NAME
FROM %sFILES PF
JOIN %sFILE_PARENTS FP ON FP.PARENT_FILE_ID = PF.FILE_ID
JOIN %sFILES F ON  F.FILE_ID = FP.THIS_FILE_ID 
""" % ((self.owner,)*3)

    def execute(self, logical_file_name, conn=None):
        """
        Lists all primary datasets if pattern is not provided.
        """
        if not conn:
            conn = self.dbi.connection()
        sql = self.sql
        cursor = conn.connection.cursor()
        sql += "WHERE F.LOGICAL_FILE_NAME = :logical_file_name"
        binds = {"logical_file_name":logical_file_name}
        #cursor.execute(sql, binds)
	cursors = self.dbi.processData(sql, binds, conn, transaction=False, returnCursor=True)
        result = self.formatCursor(cursors[0])
        conn.close()
        return result
