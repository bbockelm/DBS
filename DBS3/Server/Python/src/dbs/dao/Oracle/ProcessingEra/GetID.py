#!/usr/bin/env python
"""
This module provides ProcessingEra.GetID data access object.
"""
__revision__ = "$Id: GetID.py,v 1.7 2010/06/23 21:21:26 afaq Exp $"
__version__ = "$Revision: 1.7 $"

from WMCore.Database.DBFormatter import DBFormatter

class GetID(DBFormatter):
    """
    ProcessingEra GetID DAO class.
    """
    def __init__(self, logger, dbi, owner):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
	self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""
        self.sql = \
	"""
	SELECT PE.PROCESSING_ERA_ID, PE.PROCESSING_VERSION
	FROM %sPROCESSING_ERAS PE
	WHERE PE.PROCESSING_VERSION = :processing_version 
	""" % (self.owner)

    def execute(self, conn, name, transaction = False):
        """
        returns id for a given processing version name
        """
        binds = {"processing_version":name}
        result = self.dbi.processData(self.sql, binds, conn, transaction)
        plist = self.formatDict(result)
	if len(plist) < 1: return -1
        return plist[0]["processing_era_id"]
