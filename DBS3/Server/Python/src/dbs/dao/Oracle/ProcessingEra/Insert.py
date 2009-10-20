#!/usr/bin/env python
""" DAO Object for ProcessingEras table """ 

__revision__ = "$Revision: $"
__version__  = "$Id: $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi):
            DBFormatter.__init__(self, logger, dbi)
            self.owner = "%s." % self.dbi.engine.url.username

            self.sql = """INSERT INTO %sPROCESSING_ERAS ( PROCESSING_ERA_ID, PROCESSING_VERSION, CREATION_DATE, CREATE_BY, DESCRIPTION) VALUES (:processingeraid, :processingversion, :creationdate, :createby, :description) % (self.owner) ;"""

    def getBinds_delme( self, processing_erasObj ):
            binds = {}
            if type(processing_erasObj) == type ('object'):
            	binds = {
			'processingeraid' : processing_erasObj['processingeraid'],
			'processingversion' : processing_erasObj['processingversion'],
			'creationdate' : processing_erasObj['creationdate'],
			'createby' : processing_erasObj['createby'],
			'description' : processing_erasObj['description'],
                 }

            elif type(processing_erasObj) == type([]):
               binds = []
               for item in processing_erasObj:
                   binds.append({
 	                'processingeraid' : item['processingeraid'],
 	                'processingversion' : item['processingversion'],
 	                'creationdate' : item['creationdate'],
 	                'createby' : item['createby'],
 	                'description' : item['description'],
 	                })
               return binds


    def execute( self, processing_erasObj, conn=None, transaction=False ):
            ##binds = self.getBinds( processing_erasObj )
            result = self.dbi.processData(self.sql, binds, conn, transaction)
            return


