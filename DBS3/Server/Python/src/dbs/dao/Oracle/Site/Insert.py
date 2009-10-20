#!/usr/bin/env python
""" DAO Object for Sites table """ 

__revision__ = "$Revision: $"
__version__  = "$Id: $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi):
            DBFormatter.__init__(self, logger, dbi)
            self.owner = "%s." % self.dbi.engine.url.username

            self.sql = """INSERT INTO %sSITES ( SITE_ID, SITE_NAME) VALUES (:siteid, :sitename) % (self.owner) ;"""

    def getBinds_delme( self, sitesObj ):
            binds = {}
            if type(sitesObj) == type ('object'):
            	binds = {
			'siteid' : sitesObj['siteid'],
			'sitename' : sitesObj['sitename'],
                 }

            elif type(sitesObj) == type([]):
               binds = []
               for item in sitesObj:
                   binds.append({
 	                'siteid' : item['siteid'],
 	                'sitename' : item['sitename'],
 	                })
               return binds


    def execute( self, sitesObj, conn=None, transaction=False ):
            ##binds = self.getBinds( sitesObj )
            result = self.dbi.processData(self.sql, binds, conn, transaction)
            return


