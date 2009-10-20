#!/usr/bin/env python
""" DAO Object for FileTypes table """ 

__revision__ = "$Revision: $"
__version__  = "$Id: $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi):
            DBFormatter.__init__(self, logger, dbi)
            self.owner = "%s." % self.dbi.engine.url.username

            self.sql = """INSERT INTO %sFILE_TYPES ( FILE_TYPE_ID, FILE_TYPE) VALUES (:filetypeid, :filetype) % (self.owner) ;"""

    def getBinds_delme( self, file_typesObj ):
            binds = {}
            if type(file_typesObj) == type ('object'):
            	binds = {
			'filetypeid' : file_typesObj['filetypeid'],
			'filetype' : file_typesObj['filetype'],
                 }

            elif type(file_typesObj) == type([]):
               binds = []
               for item in file_typesObj:
                   binds.append({
 	                'filetypeid' : item['filetypeid'],
 	                'filetype' : item['filetype'],
 	                })
               return binds


    def execute( self, file_typesObj, conn=None, transaction=False ):
            ##binds = self.getBinds( file_typesObj )
            result = self.dbi.processData(self.sql, binds, conn, transaction)
            return


