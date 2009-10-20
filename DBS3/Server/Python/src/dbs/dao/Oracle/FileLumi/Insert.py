#!/usr/bin/env python
""" DAO Object for FileLumis table """ 

__revision__ = "$Revision: $"
__version__  = "$Id: $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi):
            DBFormatter.__init__(self, logger, dbi)
            self.owner = "%s." % self.dbi.engine.url.username

            self.sql = """INSERT INTO %sFILE_LUMIS ( FILE_LUMI_ID, RUN_NUM, LUMI_SECTION_NUM, FILE_ID) VALUES (:filelumiid, :runnum, :lumisectionnum, :fileid) % (self.owner) ;"""

    def getBinds_delme( self, file_lumisObj ):
            binds = {}
            if type(file_lumisObj) == type ('object'):
            	binds = {
			'filelumiid' : file_lumisObj['filelumiid'],
			'runnum' : file_lumisObj['runnum'],
			'lumisectionnum' : file_lumisObj['lumisectionnum'],
			'fileid' : file_lumisObj['fileid'],
                 }

            elif type(file_lumisObj) == type([]):
               binds = []
               for item in file_lumisObj:
                   binds.append({
 	                'filelumiid' : item['filelumiid'],
 	                'runnum' : item['runnum'],
 	                'lumisectionnum' : item['lumisectionnum'],
 	                'fileid' : item['fileid'],
 	                })
               return binds


    def execute( self, file_lumisObj, conn=None, transaction=False ):
            ##binds = self.getBinds( file_lumisObj )
            result = self.dbi.processData(self.sql, binds, conn, transaction)
            return


