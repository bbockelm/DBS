#!/usr/bin/env python
""" DAO Object for PhysicsGroups table """ 

__revision__ = "$Revision: $"
__version__  = "$Id: $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi):
            DBFormatter.__init__(self, logger, dbi)
            self.owner = "%s." % self.dbi.engine.url.username

            self.sql = """INSERT INTO %sPHYSICS_GROUPS ( PHYSICS_GROUP_ID, PHYSICS_GROUP_NAME, PHYSICS_GROUP_CONVENER) VALUES (:physicsgroupid, :physicsgroupname, :physicsgroupconvener) % (self.owner) ;"""

    def getBinds_delme( self, physics_groupsObj ):
            binds = {}
            if type(physics_groupsObj) == type ('object'):
            	binds = {
			'physicsgroupid' : physics_groupsObj['physicsgroupid'],
			'physicsgroupname' : physics_groupsObj['physicsgroupname'],
			'physicsgroupconvener' : physics_groupsObj['physicsgroupconvener'],
                 }

            elif type(physics_groupsObj) == type([]):
               binds = []
               for item in physics_groupsObj:
                   binds.append({
 	                'physicsgroupid' : item['physicsgroupid'],
 	                'physicsgroupname' : item['physicsgroupname'],
 	                'physicsgroupconvener' : item['physicsgroupconvener'],
 	                })
               return binds


    def execute( self, physics_groupsObj, conn=None, transaction=False ):
            ##binds = self.getBinds( physics_groupsObj )
            result = self.dbi.processData(self.sql, binds, conn, transaction)
            return


