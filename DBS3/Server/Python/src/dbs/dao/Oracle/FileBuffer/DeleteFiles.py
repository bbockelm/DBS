#!/usr/bin/env python
"""
This module provides FileBuffer.DeleteFiles data access object.
"""
__revision__ = "$Id: $"
__version__ = "$Revision: $"

from WMCore.Database.DBFormatter import DBFormatter


class DeleteFiles(DBFormatter):
    """
    File List DAO class.
    """
    def __init__(self, logger, dbi, owner=""):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
	self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else "" 
        self.sql = """DELETE FROM %sFILE_BUFFER WHERE LFN=:lfn""" % self.owner

    def execute(self, conn, lfn={}, transaction=False):

        """
	simple execute
        """	
        if not conn:
            raise Exception("dbs/dao/Oracle/FileBuffer/DeleteFiles expects db connection from up layer.")
        self.dbi.processData(self.sql, lfn, conn, transaction, returnCursor=True)

