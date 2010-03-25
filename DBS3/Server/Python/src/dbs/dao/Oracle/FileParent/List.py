#!/usr/bin/env python
"""
This module provides FileParent.List data access object.
"""
__revision__ = "$Id: List.py,v 1.5 2010/03/05 18:56:04 yuyi Exp $"
__version__ = "$Revision: 1.5 $"


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

    def execute(self, conn, logical_file_name, transaction=False):
        """
        Lists all primary datasets if pattern is not provided.
        """
        if not conn:
            raise Exception("dbs/dao/Oracle/FileParent/List expects db connection from up layer.")
        sql = self.sql
        sql += "WHERE F.LOGICAL_FILE_NAME = :logical_file_name"
        binds = {"logical_file_name":logical_file_name}
	cursors = self.dbi.processData(sql, binds, conn, transaction=transaction, returnCursor=True)
        result = self.formatCursor(cursors[0])
        return result
