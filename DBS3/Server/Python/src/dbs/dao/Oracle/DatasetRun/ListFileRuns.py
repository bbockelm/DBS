#!/usr/bin/env python
"""
This module provides DatasetRun.ListFileRuns data access object.
"""
__revision__ = "$Id: ListFileRuns.py,v 1.4 2010/03/18 16:28:56 afaq Exp $"
__version__ = "$Revision: 1.4 $"

from WMCore.Database.DBFormatter import DBFormatter

class ListFileRuns(DBFormatter):
    """
    DatasetRun ListFileRuns DAO class.
    """
    def __init__(self, logger, dbi, owner=""):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
        self.sql = \
	"""
	SELECT DISTINCT FL.RUN_NUM, F.LOGICAL_FILE_NAME
	FROM %sFILE_LUMIS FL
	JOIN %sFILES F ON F.FILE_ID = FL.FILE_ID
	WHERE F.LOGICAL_FILE_NAME= :lfn """% ((self.owner,) * 2)
	
    def execute(self, conn, logical_file_name, minRun=-1, maxRun=-1, trans=False):
        """
        Lists all primary datasets if pattern is not provided.
        """

	if not conn:
		raise Exception("dbs/dao/Oracle/DatasetRun/ListFileRuns expects db connection from upper layer.")	
        sql = self.sql
        binds = { "lfn" : logical_file_name }
	if minRun > 0: 
		sql += " AND FL.RUN_NUM >= :min_run"
		binds["min_run"] = minRun
	if maxRun > 0:
		if minRun > 0:
		    sql += " AND FL.RUN_NUM <= :max_run"
		else:
		    sql += " where FL.RUN_NUM <= :max_run"
		binds["max_run"] = maxRun

	cursors = self.dbi.processData(sql, binds, conn, transaction=trans, returnCursor=True)
	result = self.formatCursor(cursors[0])
        return result
