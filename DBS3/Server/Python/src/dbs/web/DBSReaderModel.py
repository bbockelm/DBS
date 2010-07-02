#!/usr/bin/env python
"""
DBS Reader Rest Model module
"""

__revision__ = "$Id: DBSReaderModel.py,v 1.43 2010/06/23 21:21:27 afaq Exp $"
__version__ = "$Revision: 1.43 $"

from WMCore.WebTools.RESTModel import RESTModel

from dbs.business.DBSPrimaryDataset import DBSPrimaryDataset
from dbs.business.DBSDataset import DBSDataset
from dbs.business.DBSBlock import DBSBlock
from dbs.business.DBSSite import DBSSite
from dbs.business.DBSFile import DBSFile
from dbs.business.DBSAcquisitionEra import DBSAcquisitionEra
from dbs.business.DBSOutputConfig import DBSOutputConfig
from dbs.business.DBSProcessingEra import DBSProcessingEra
from dbs.business.DBSRun import DBSRun
from dbs.business.DBSDataType import DBSDataType
from dbs.business.DBSDataTier import DBSDataTier
from dbs.business.DBSStatus import DBSStatus

from dbs.business.DBSMigrate import DBSMigrate


from cherrypy import server

__server__version__ = "$Name:  $"

class DBSReaderModel(RESTModel):
    """
    DBS3 Server API Documentation 
    """
    def __init__(self, config):
        """
        All parameters are provided through DBSConfig module
        """
        RESTModel.__init__(self, config)
        self.version = self.getServerVersion()
      
	self.logger.warning("<<<<<<<<<<<<<DBS SERVER IS NO AVAILABLE ON>>>>>>>>> %s/%s" \
					% (server.base(), config._internal_name))

	self.logger.warning("<<<<<<<<<<<<<DBS SERVER IS CONNECTING TO ::::: %s" % config.database)
    
        self.methods = {'GET':{}, 'PUT':{}, 'POST':{}, 'DELETE':{}}
	self.addMethod('GET', 'serverinfo', self.getServerInfo)
        self.addMethod('GET', 'primarydatasets', self.listPrimaryDatasets)
        self.addMethod('GET', 'datasets', self.listDatasets)
        self.addMethod('GET', 'blocks', self.listBlocks)
        self.addMethod('GET', 'files', self.listFiles)
        self.addMethod('GET', 'datasetparents', self.listDatasetParents)
        self.addMethod('GET', 'datasetchildren', self.listDatasetChildren)
        self.addMethod('GET', 'outputconfigs', self.listOutputConfigs)
        self.addMethod('GET', 'fileparents', self.listFileParents)
        self.addMethod('GET', 'filechildren', self.listFileChildren)
        self.addMethod('GET', 'filelumis', self.listFileLumis)
        self.addMethod('GET', 'runs', self.listRuns)
        self.addMethod('GET', 'sites', self.listSites)
        self.addMethod('GET', 'datatypes', self.listDataTypes)
        self.addMethod('GET', 'datatiers', self.listDataTiers)
        self.addMethod('GET', 'blockparents', self.listBlockParents)
        self.addMethod('GET', 'blockchildren', self.listBlockChildren)
        self.addMethod('GET', 'blockdump', self.dumpBlock)
	
        
        self.dbsPrimaryDataset = DBSPrimaryDataset(self.logger, self.dbi, config.dbowner)
        self.dbsDataset = DBSDataset(self.logger, self.dbi, config.dbowner)
        self.dbsBlock = DBSBlock(self.logger, self.dbi, config.dbowner)
        self.dbsFile = DBSFile(self.logger, self.dbi, config.dbowner)
        self.dbsAcqEra = DBSAcquisitionEra(self.logger, self.dbi, config.dbowner)
        self.dbsOutputConfig = DBSOutputConfig(self.logger, self.dbi, config.dbowner)
        self.dbsProcEra = DBSProcessingEra(self.logger, self.dbi, config.dbowner)
        self.dbsSite = DBSSite(self.logger, self.dbi, config.dbowner)
	self.dbsRun = DBSRun(self.logger, self.dbi, config.dbowner)
	self.dbsDataType = DBSDataType(self.logger, self.dbi, config.dbowner)
	self.dbsDataTier = DBSDataTier(self.logger, self.dbi, config.dbowner)
	self.dbsStatus = DBSStatus(self.logger, self.dbi, config.dbowner)
    
	self.dbsMigrate = DBSMigrate(self.logger, self.dbi, config.dbowner)
    

    def addService_deprecated(self, verb, methodKey, func, args=[], validation=[], version=1):
        """
        method that adds services to the DBS rest model
        """
        self.methods[verb][methodKey] = {'args': args,
                                         'call': func,
                                         'validation': validation,
                                         'version': version,
					 'expires' : 10000 }

    def getServerVersion(self):
        """
        Reading from __version__ tag, determines the version of the DBS Server
        """
        version = __server__version__.replace("$Name: ", "")
        version = version.replace("$", "")
        version = version.strip()
        return version
    
    def getServerInfo(self):
        """
        Method that provides information about DBS Server to the clients
        The information includes
        * Server Version - CVS Tag
        * Schema Version - Version of Schema this DBS instance is working with
        * ETC - TBD
        """
        ret = {}
        ret["tagged_version"] = self.getServerVersion()
        ret["schema"] = self.dbsStatus.getSchemaStatus()
	ret["components"] = self.dbsStatus.getComponentStatus()
        return ret

    def listPrimaryDatasets(self, primary_ds_name="", primary_ds_type=""):
        """
        Example url's: <br />
        http://dbs3/primarydatasets <br />
        http://dbs3/primarydatasets/qcd_20_30 <br />
        http://dbs3/primarydatasets?primary_ds_name=qcd* <br />
	http://dbs3/primarydatasets?primary_ds_type=qcd* <br />
        """
        primary_ds_name = primary_ds_name.replace("*","%")
	primary_ds_type = primary_ds_type.replace("*","%")
        return self.dbsPrimaryDataset.listPrimaryDatasets(primary_ds_name, primary_ds_type)
        
    def listDatasets(self, dataset="", parent_dataset="", release_version="", pset_hash="", app_name="", output_module_label="", 
			processing_version="", acquisition_era="", run_num=0, physics_group_name="", logical_file_name="", primary_ds_name="",
			primary_ds_type="", data_tier_name="", dataset_access_type=""):
        """
        Example url's: <br />
        http://dbs3/datasets <br />
        http://dbs3/datasets/RelVal* <br />
        http://dbs3/datasets?dataset=/RelVal*/*/*RECO <br />
        http://dbs3/datasets?dataset=/RelVal*/*/*RECO&release_version=CMSSW_3_0_0<br />
        """
        dataset = dataset.replace("*", "%")
	parent_dataset = parent_dataset.replace("*", "%")
	release_version = release_version.replace("*", "%")
	pset_hash = pset_hash.replace("*", "%")
	app_name = app_name.replace("*", "%")
	output_module_label = output_module_label.replace("*", "%")
	logical_file_name = logical_file_name.replace("*", "%")
	physics_group_name = physics_group_name.replace("*", "%")
	primary_ds_name = primary_ds_name.replace("*", "%")
	primary_ds_type = primary_ds_type.replace("*", "%")
	data_tier_name = data_tier_name.replace("*", "%")
	dataset_access_type = dataset_access_type.replace("*", "%")
	if(run_num):
	    run_num = int(run_num)
	return self.dbsDataset.listDatasets(dataset, parent_dataset, release_version, pset_hash, app_name, output_module_label, processing_version, acquisition_era,
	    run_num, physics_group_name, logical_file_name, primary_ds_name, primary_ds_type, data_tier_name, dataset_access_type)

    def listDataTiers(self, data_tier_name=""):
	"""
	Example url's:
	    http://dbs3/datatiers
	    http://dbs3/datatiers?data_tier_name=...
	"""
	data_tier_name = data_tier_name.replace("*","%")
	return self.dbsDataTier.listDataTiers(data_tier_name)
	
    def listBlocks(self, dataset="", block_name="", origin_site_name="", logical_file_name="",run_num=-1):
        """
        Example url's:
        http://dbs3/blocks?dataset=myDataset ||?origin_site_name=mySite <br />
        http://dbs3/blocks?block_name=myBlock ||?origin_site_name=mySite <br />
	http://dbs3/blocks?logical_file_name=my_lfn ||?origin_site_name=mySite<br />
	http://dbs3/blocks?logical_file_name=my_lfn*?dataset=myDataset*?block_name=myBlock ||?origin_site_name=mySite<br />
        """
	#site_name is ORIGIN_SITE_NAME. We need to change the name and add REAL site_name
        dataset = dataset.replace("*","%")
        block_name = block_name.replace("*","%")
	logical_file_name = logical_file_name.replace("*","%")
	origin_site_name = origin_site_name.replace("*","%")
	run_num = int(run_num)
        return self.dbsBlock.listBlocks(dataset, block_name, origin_site_name, logical_file_name,run_num)

    def listBlockParents(self, block_name=""):
        """
        Example url's:
        http://dbs3/blockparents?block_name=/a/b/c%23*d <br />
        """
        block_name = block_name.replace("*","%")
        return self.dbsBlock.listBlockParents(block_name)
  
    def listBlockChildren(self, block_name=""):
        """
        Example url's:
        http://dbs3/blockchildren?block_name=/a/b/c%23*d <br />
        """
        block_name = block_name.replace("*","%")
        return self.dbsBlock.listBlockChildren(block_name)
 
    def listFiles(self, dataset = "", block_name = "", logical_file_name = "", release_version="", 
	pset_hash="", app_name="", output_module_label="", minrun=-1, maxrun=-1,
	origin_site_name="", lumi_list=[]):
        """
        Example url's: <br />
        http://dbs3/files?dataset=/a/b/c/ <br />
        http://dbs3/files?block_name=a/b/c#d <br />
        http://dbs3/files?dataset=/a/b/c&lfn=/store/* <br />
        http://dbs3/files?block_name=/a/b/c%23d&logical_file_name=/store/* <br />
        """
        logical_file_name = logical_file_name.replace("*", "%")
	release_version = release_version.replace("*", "%")
	pset_hash = pset_hash.replace("*", "%")
	app_name = app_name.replace("*", "%")
	block_name = block_name.replace("*", "%")
	origin_site_name = origin_site_name.replace("*", "%")
	dataset = dataset.replace("*", "%")
	if(maxrun):
	    maxrun = int(maxrun)
	if(minrun):
	    minrun = int(minrun)
	output_module_label = output_module_label.replace("*", "%")
	return self.dbsFile.listFiles(dataset, block_name, logical_file_name , release_version , pset_hash, app_name, 
					output_module_label, maxrun, minrun, origin_site_name, lumi_list)
    
    def listDatasetParents(self, dataset):
        """
        Example url's <br />
        http://dbs3/datasetparents?dataset=/a/b/c
        """
        return self.dbsDataset.listDatasetParents(dataset)

    def listDatasetChildren(self, dataset):
        """
        Example url's <br />
        http://dbs3/datasetchildren?dataset=/a/b/c
        """
        return self.dbsDataset.listDatasetChildren(dataset)
    
    def listOutputConfigs(self, dataset="", logical_file_name="", release_version="", pset_hash="", app_name="", output_module_label=""):
        """
        Example url's: <br />
        http://dbs3/outputconfigurations <br />
        http://dbs3/outputconfigurations?dataset=a/b/c <br />
        http://dbs3/outputconfigurations?logical_file_name=lfn <br />
        http://dbs3/outputconfigurations?release_version=version <br />
        http://dbs3/outputconfigurations?pset_hash=hash <br/>
        http://dbs3/outputconfigurations?app_name=app_name <br/>
        http://dbs3/outputconfigurations?output_module_label="output_module_label" <br/>
        """
       	release_version = release_version.replace("*", "%")
	pset_hash = pset_hash.replace("*", "%")
	app_name = app_name.replace("*", "%")
	output_module_label = output_module_label.replace("*", "%")
	return self.dbsOutputConfig.listOutputConfigs(dataset, logical_file_name, release_version, pset_hash, app_name, output_module_label)
    
    def listFileParents(self, logical_file_name):
        """
        Example url's <br />
        http://dbs3/fileparents?logical_file_name=lfn
        """
        return self.dbsFile.listFileParents(logical_file_name)

    def listFileChildren(self, logical_file_name):
        """
        Example url's <br />
        http://dbs3/filechildren?logical_file_name=lfn
        """
        return self.dbsFile.listFileChildren(logical_file_name)
       

    def listFileLumis(self, logical_file_name="", block_name=""):
        """
        Example url's <br />
        http://dbs3/filelumis?logical_file_name=lfn
        http://dbs3/filelumis?block_name=block_name
        """
        return self.dbsFile.listFileLumis(logical_file_name, block_name)
         
    def listRuns(self, minrun=-1, maxrun=-1):
        """
        http://dbs3/runs?runmin=1&runmax=10
        http://dbs3/runs
        """
        return self.dbsRun.listRuns(minrun, maxrun)
   
    def listSites(self, block_name="", site_name=""):
        """
        Example url's <br />
        http://dbs3/sites
	http://dbs3/sites?block_name=block_name
	http://dbs3/sites?site_name=T1_FNAL
        """
        return self.dbsSite.listSites(block_name, site_name)
  
    def listDataTypes(self, datatype="", dataset=""):
	"""
	lists datatypes known to dbs
	dataset : lists datatype of a dataset
	"""
	return  self.dbsDataType.listDataType(dataType=datatype, dataset=dataset)

    def dumpBlock(self, block_name):
	"""
	Returns all information related with the block_name
	"""
	return self.dbsMigrate.dumpBlock(block_name)


	    
