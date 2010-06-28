#!/usr/bin/env python
"""
This module provides MigrationRequests.ListOldest data access object.
"""
__revision__ = "$Id: List.py,v 1.4 2010/06/28 16:09:04 afaq Exp $"
__version__ = "$Revision: 1.4 $"


from WMCore.Database.DBFormatter import DBFormatter

class ListOldest(DBFormatter):
    """
    MigrationRequest ListOldest DAO class.
    """
    def __init__(self, logger, dbi, owner=""):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
        self.sql = """SELECT MIN(MIGRATION_REQUEST_ID) FROM %sMIGRATION_REQUESTS WHERE MIGRATION_STATUS='PENDING')""" % (self.owner)

    def execute(self, conn, migration_url="", migration_input="", create_by="", migration_request_id="", transaction=False):
        """
        Lists the oldest request queued 
        """

        binds = {}
	result = self.dbi.processData(sql, binds, conn, transaction)
        result = self.formatDict(result)
        return result
