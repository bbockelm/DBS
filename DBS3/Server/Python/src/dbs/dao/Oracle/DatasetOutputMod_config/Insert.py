# DAO Object for DatasetOutputMod_config table
# $Revision: 1.1 $
# $Id: generate_dao.py,v 1.1 2009/10/07 20:14:33 afaq Exp $

from WMCore.Database.DBFormatter import DBFormatter

class Insert(DBFormatter):

    sql = """INSERT INTO DATASET_OUTPUT_MOD_CONFIGS(DS_OUTPUT_MOD_CONF_ID, DATASET_ID, OUTPUT_MOD_CONFIG_ID) VALUES (:dsoutputmodconfid, :datasetid, :outputmodconfigid);"""

    def getBinds( self, dataset_output_mod_configsObj ):
            binds = {}
            if type(dataset_output_mod_configsObj) == type ('object'):
            	binds = {
			'dsoutputmodconfid' : dataset_output_mod_configsObj['dsoutputmodconfid'],
			'datasetid' : dataset_output_mod_configsObj['datasetid'],
			'outputmodconfigid' : dataset_output_mod_configsObj['outputmodconfigid'],
                 }

            elif type(dataset_output_mod_configsObj) == type([]):
               binds = []
               for item in dataset_output_mod_configsObj:
                   binds.append({
 	                'dsoutputmodconfid' : item['dsoutputmodconfid'],
 	                'datasetid' : item['datasetid'],
 	                'outputmodconfigid' : item['outputmodconfigid'],
 	                })
               return binds


    def execute( self, dataset_output_mod_configsObj ):
            binds = self.getBinds(dataset_output_mod_configsObj )
            result = self.dbi.processData(self.sql, binds, conn = conn, transaction = transaction)
            return