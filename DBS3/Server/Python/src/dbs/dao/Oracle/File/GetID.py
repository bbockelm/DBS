#!/usr/bin/env python
"""
This module provides File.GetID data access object.
"""
__revision__ = "$Id: GetID.py,v 1.5 2010/06/23 21:21:23 afaq Exp $"
__version__ = "$Revision: 1.5 $"

from WMCore.Database.DBFormatter import DBFormatter
class GetID(DBFormatter):
    """
    File GetID DAO class.
    """
    def __init__(self, logger, dbi, owner):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
        self.sql = \
"""
SELECT F.FILE_ID
FROM %sFILES F 
""" % ( self.owner )
        
    def execute(self, conn, name,  transaction = False):
        """
        returns id for a given lfn
        """	
        sql = self.sql
        sql += "WHERE F.LOGICAL_FILE_NAME = :lfn"
        binds = {"lfn":name}
        result = self.dbi.processData(sql, binds, conn, transaction)
        plist = self.formatDict(result)
	if len(plist) < 1: return -1
        return plist[0]["file_id"]
