# DAO Object for DatasetParent table
# $Revision: 1.1 $
# $Id: generate_dao.py,v 1.1 2009/10/07 20:14:33 afaq Exp $

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    sql = """INSERT INTO DATASET_PARENTS(DATASET_PARENT_ID, THIS_DATASET_ID, PARENT_DATASET_ID) VALUES (:datasetparentid, :thisdatasetid, :parentdatasetid);"""

    def getBinds( self, dataset_parentsObj ):
            binds = {}
            if type(dataset_parentsObj) == type ('object'):
            	binds = {
			'datasetparentid' : dataset_parentsObj['datasetparentid'],
			'thisdatasetid' : dataset_parentsObj['thisdatasetid'],
			'parentdatasetid' : dataset_parentsObj['parentdatasetid'],
                 }

            elif type(dataset_parentsObj) == type([]):
               binds = []
               for item in dataset_parentsObj:
                   binds.append({
 	                'datasetparentid' : item['datasetparentid'],
 	                'thisdatasetid' : item['thisdatasetid'],
 	                'parentdatasetid' : item['parentdatasetid'],
 	                })
               return binds


    def execute( self, dataset_parentsObj ):
            binds = self.getBinds(dataset_parentsObj )
            result = self.dbi.processData(self.sql, binds, conn = conn, transaction = transaction)
            return