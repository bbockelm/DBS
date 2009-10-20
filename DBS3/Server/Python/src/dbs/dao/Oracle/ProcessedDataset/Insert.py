#!/usr/bin/env python
""" DAO Object for ProcessedDatasets table """ 

__revision__ = "$Revision: $"
__version__  = "$Id: $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi):
            DBFormatter.__init__(self, logger, dbi)
            self.owner = "%s." % self.dbi.engine.url.username

            self.sql = """INSERT INTO %sPROCESSED_DATASETS ( PROCESSED_DS_ID, PROCESSED_DS_NAME) VALUES (:processeddsid, :processeddsname) % (self.owner) ;"""

    def getBinds_delme( self, processed_datasetsObj ):
            binds = {}
            if type(processed_datasetsObj) == type ('object'):
            	binds = {
			'processeddsid' : processed_datasetsObj['processeddsid'],
			'processeddsname' : processed_datasetsObj['processeddsname'],
                 }

            elif type(processed_datasetsObj) == type([]):
               binds = []
               for item in processed_datasetsObj:
                   binds.append({
 	                'processeddsid' : item['processeddsid'],
 	                'processeddsname' : item['processeddsname'],
 	                })
               return binds


    def execute( self, processed_datasetsObj, conn=None, transaction=False ):
            ##binds = self.getBinds( processed_datasetsObj )
            result = self.dbi.processData(self.sql, binds, conn, transaction)
            return


