# DAO Object for FileType table
# $Revision: 1.1 $
# $Id: generate_dao.py,v 1.1 2009/10/07 20:14:33 afaq Exp $

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    sql = """INSERT INTO FILE_TYPES(FILE_TYPE_ID, FILE_TYPE) VALUES (:filetypeid, :filetype);"""

    def getBinds( self, file_typesObj ):
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


    def execute( self, file_typesObj ):
            binds = self.getBinds(file_typesObj )
            result = self.dbi.processData(self.sql, binds, conn = conn, transaction = transaction)
            return