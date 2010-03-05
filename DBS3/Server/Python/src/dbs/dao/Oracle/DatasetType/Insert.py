#!/usr/bin/env python
""" DAO Object for DatasetTypes table """ 

__revision__ = "$Revision: 1.4 $"
__version__  = "$Id: Insert.py,v 1.4 2010/02/11 18:03:25 afaq Exp $ "

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    def __init__(self, logger, dbi):
            DBFormatter.__init__(self, logger, dbi)
	    self.owner = "%s." % owner if not owner in ("", "__MYSQL__") else ""

            self.sql = """INSERT INTO %sDATASET_TYPES ( DATASET_TYPE_ID, DATASET_TYPE) VALUES (:datasettypeid, :datasettype)""" % (self.owner)

    def getBinds_delme( self, dataset_typesObj ):
            binds = {}
            if type(dataset_typesObj) == type ('object'):
            	binds = {
			'datasettypeid' : dataset_typesObj['datasettypeid'],
			'datasettype' : dataset_typesObj['datasettype'],
                 }

            elif type(dataset_typesObj) == type([]):
               binds = []
               for item in dataset_typesObj:
                   binds.append({
 	                'datasettypeid' : item['datasettypeid'],
 	                'datasettype' : item['datasettype'],
 	                })
               return binds


    def execute( self, conn, dataset_typesObj, transaction=False ):
	if not conn:
	    raise Exception("dbs/dao/Oracle/DatasetType/Insert expects db connection from up layer.")
	result = self.dbi.processData(self.sql, binds, conn, transaction)
	return


