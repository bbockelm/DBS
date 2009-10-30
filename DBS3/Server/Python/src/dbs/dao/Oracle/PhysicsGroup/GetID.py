#!/usr/bin/env python
"""
This module provides PhysicsGroup.GetID data access object.
"""
__revision__ = "$Id: GetID.py,v 1.1 2009/10/28 09:51:50 akhukhun Exp $"
__version__ = "$Revision: 1.1 $"

from WMCore.Database.DBFormatter import DBFormatter

class GetID(DBFormatter):
    """
    PhysicsGroup GetID DAO class.
    """
    def __init__(self, logger, dbi):
        """
        Add schema owner and sql.
        """
        DBFormatter.__init__(self, logger, dbi)
        self.owner = "%s." % self.dbi.engine.url.username
        self.sql = \
"""
SELECT PG.PHYSICS_GROUP_ID, PG.PHYSICS_GROUP_NAME
FROM %sPHYSICS_GROUPS PG 
""" % (self.owner)

    def execute(self, name, conn = None, transaction = False):
        """
        returns id for a given physics group name
        """
        sql = self.sql
        sql += "WHERE PG.PHYSICS_GROUP_NAME = :physicsgroup" 
        binds = {"physicsgroup":name}
        result = self.dbi.processData(sql, binds, conn, transaction)
        plist = self.formatDict(result)
        assert len(plist) == 1, \
            "PhysicsGroup %s does not exist" % name
        return plist[0]["physics_group_id"]
