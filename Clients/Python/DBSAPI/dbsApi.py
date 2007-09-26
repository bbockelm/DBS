#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
# DBS API class. Interfacing to Server using http/https or local
#

# system modules
import os, re, string, socket, xml.sax, xml.sax.handler
import base64
from xml.sax.saxutils import escape
from cStringIO import StringIO

# DBS specific modules
from dbsHttpService import DbsHttpService
from dbsExecService import DbsExecService

from dbsException import DbsException
from dbsApiException import *

#from dbsBaseObject import *
#from dbsRun import DbsRun 
#from dbsQueryableParameterSet import DbsQueryableParameterSet
#from dbsProcessedDataset import DbsProcessedDataset
#from dbsPrimaryDataset import DbsPrimaryDataset
#from dbsLumiSection import DbsLumiSection 
#from dbsFile import DbsFile
#from dbsFileBlock import DbsFileBlock
#from dbsDataTier import DbsDataTier 
#from dbsStorageElement import DbsStorageElement 
#from dbsFileBranch import DbsFileBranch 
#from dbsAlgorithm import DbsAlgorithm
#from dbsAnalysisDataset import DbsAnalysisDataset
#from dbsAnalysisDatasetDefinition import DbsAnalysisDatasetDefinition
#from dbsFileTriggerTag import DbsFileTriggerTag
#from dbsMigrateApi import DbsMigrateApi
#from DBSAPI.dbsDQFlag import DbsDQFlag
#from DBSAPI.dbsRunLumiDQ import DbsRunLumiDQ

from dbsParent import DbsParent
from dbsConfig import DbsConfig
import urlparse
import urllib2

import logging
import inspect

from dbsLogger import *

from dbsUtil import *

from dbsApiListPrimaryDataset import *



#DBS Api version
__version__ = "DBS_1_0_7"
#__version__ = "$Name:  $"
#__version__ = "$Name:  $"


# DBS Defined Log Levels
#DBSDEBUG=1
#DBSINFO=2
#DBSWARNING=3


def makeAPI(url):
		#args = {}
		#args['url'] = url
		args = {}
		if url.startswith('http'):
			args['url'] = url
			args['mode'] = 'POST'

		return DbsApi(args)

class DbsApi(DbsConfig):
  """
  DbsApi class, provides access to DBS Server, 
  all clients must use this interface 
  """ 

  def __init__(self, Args={}):
    """ 
    Constructor. 
    Initializes the DBS Api by reading configuration 
    parameters from dbs.config file OR from the parameters 
    passed through Args as a Python dictionary.
    Parameters passed through Args take precedence 
    over parameters in dbs.config
    
    For MODE=POST (Default Mode): Creates a http proxy, to be able to talk to DBS Server
    """

    DbsConfig.__init__(self,Args)

    if not self.configDict.has_key('version'):
       self.configDict['version'] = self.setApiVersion()
 

    if self.verbose():
       print "configuration dictionary:", self.configDict
       print "using version",self.version()
       print "using mode   ",self.mode()

    #Store infor about current user
    #
    if not self.configDict.has_key('userID'):
    	#Args['userID'] = os.getlogin()+'@'+socket.gethostname()
    	Args['userID'] = os.environ['USER']+'@'+socket.gethostname()
    #
    # Connect to the Server proxy
    #
    self._server = ""
    if not self.configDict.has_key('mode'):
	self.configDict['mode'] = "POST"  
    if self.mode() == "EXEC" :
	    self._server = DbsExecService(self.dbshome(), self.javahome(), self.version(), Args)
    else :
            self._server = DbsHttpService(self.url(), self.version(), Args)

    # Set up logging

    if not self.configDict.has_key('level'):
	self.configDict['level'] = "ERROR"
    if not self.configDict.has_key('log'):
        self.configDict['log'] = "STDOUT"

    DbsLogger(self.loglevel(), self.log()) 
    logging.log(DBSDEBUG, "DBS Api initialized")
    
  def getServerUrl(self):
    """
    Returns the server URL

    """
    return self.url()

  def setApiVersion(self):
    """
    Sets DBS Client Api Version
    Reading for __version__ tag

    Note: Config (dbs.config) and Constructor 
      arguments have higher presedence 
    """

    return __version__

  def getApiVersion(self):
    """
    Returns the API version of the API
    """
    return self.version()

  #------------------------------------------------------------

  def getServerInfo(self):

       #Calling the Implementation function
       from dbsApiGetServerInfo import dbsApiImplGetServerInfo
       return  dbsApiImplGetServerInfo(self)

  def listPrimaryDatasets(self, pattern="*"):

       #Calling the Implementation function
       from dbsApiListPrimaryDatasets import dbsApiImplListPrimaryDatasets
       return  dbsApiImplListPrimaryDatasets(self, pattern)

  def listProcessedDatasets(self, patternPrim="*", patternDT="*", patternProc="*",  patternVer="*", patternFam="*", patternExe="*", patternPS="*"):

       #Calling the Implementation function
       from dbsApiListProcessedDatasets import dbsApiImplListProcessedDatasets
       return  dbsApiImplListProcessedDatasets(self, patternPrim, patternDT, patternProc,  patternVer, patternFam, patternExe, patternPS)

  def listAlgorithms(self, patternVer="*", patternFam="*", patternExe="*", patternPS="*"):

       #Calling the Implementation function
       from dbsApiListAlgorithms import dbsApiImplListAlgorithms
       return  dbsApiImplListAlgorithms(self, patternVer, patternFam, patternExe, patternPS)

  def listRuns(self, dataset):

       #Calling the Implementation function
       from dbsApiListRuns import dbsApiImplListRuns
       return  dbsApiImplListRuns(self, dataset)

  def listTiers(self, dataset):

       #Calling the Implementation function
       from dbsApiListTiers import dbsApiImplListTiers
       return  dbsApiImplListTiers(self, dataset)

  def listBlocks(self, dataset=None, block_name="*", storage_element_name="*"):

       #Calling the Implementation function
       from dbsApiListBlocks import dbsApiImplListBlocks
       return  dbsApiImplListBlocks(self, dataset, block_name, storage_element_name)

  def listStorageElements(self, storage_element_name="*"):

       #Calling the Implementation function
       from dbsApiListStorageElements import dbsApiImplListStorageElements
       return  dbsApiImplListStorageElements(self, storage_element_name)

  def listLFNs(self, path="", queryableMetaData=""):

       #Calling the Implementation function
       from dbsApiListLFNs import dbsApiImplListLFNs
       return  dbsApiImplListLFNs(self, path, queryableMetaData)

  def listDatasetFiles(self, datasetPath):

       #Calling the Implementation function
       from dbsApiListDatasetFiles import dbsApiImplListDatasetFiles
       return  dbsApiImplListDatasetFiles(self, datasetPath)

  def listFiles(self, path="", primary="", proc="", tier_list=[], analysisDataset="",blockName="", patternLFN="*", runNumber="", details=None):

       #Calling the Implementation function
       from dbsApiListFiles import dbsApiImplListFiles
       return  dbsApiImplListFiles(self, path, primary, proc, tier_list, analysisDataset,blockName, patternLFN, runNumber, details)

  def listFileParents(self, lfn):

       #Calling the Implementation function
       from dbsApiListFileParents import dbsApiImplListFileParents
       return  dbsApiImplListFileParents(self, lfn)

  def listFileAlgorithms(self, lfn):

       #Calling the Implementation function
       from dbsApiListFileAlgorithms import dbsApiImplListFileAlgorithms
       return  dbsApiImplListFileAlgorithms(self, lfn)

  def listFileTiers(self, lfn):

       #Calling the Implementation function
       from dbsApiListFileTiers import dbsApiImplListFileTiers
       return  dbsApiImplListFileTiers(self, lfn)

  def listFileBranches(self, lfn):

       #Calling the Implementation function
       from dbsApiListFileBranches import dbsApiImplListFileBranches
       return  dbsApiImplListFileBranches(self, lfn)

  def listFileLumis(self, lfn):

       #Calling the Implementation function
       from dbsApiListFileLumis import dbsApiImplListFileLumis
       return  dbsApiImplListFileLumis(self, lfn)

  def listAnalysisDatasetDefinition(self, pattern="*"):

       #Calling the Implementation function
       from dbsApiListAnalysisDatasetDefinition import dbsApiImplListAnalysisDatasetDefinition
       return  dbsApiImplListAnalysisDatasetDefinition(self, pattern)

  def listAnalysisDataset(self, pattern="*", path="", version=None):

       #Calling the Implementation function
       from dbsApiListAnalysisDataset import dbsApiImplListAnalysisDataset
       return  dbsApiImplListAnalysisDataset(self, pattern, path, version)

  def listDatasetParents(self, dataset):

       #Calling the Implementation function
       from dbsApiListDatasetParents import dbsApiImplListDatasetParents
       return  dbsApiImplListDatasetParents(self, dataset)

  def listDatasetContents(self, path, block_name):

       #Calling the Implementation function
       from dbsApiListDatasetContents import dbsApiImplListDatasetContents
       return  dbsApiImplListDatasetContents(self, path, block_name)

  def insertDatasetContents(self, xmlinput, ignore_parent = False):

       #Calling the Implementation function
       from dbsApiInsertDatasetContents import dbsApiImplInsertDatasetContents
       return  dbsApiImplInsertDatasetContents(self, xmlinput, ignore_parent)

  def migrateDatasetContents(self, srcURL, dstURL, path, block_name="", noParentsReadOnly = False, pruneBranches = False):

       #Calling the Implementation function
       from dbsApiMigrateDatasetContents import dbsApiImplMigrateDatasetContents
       return  dbsApiImplMigrateDatasetContents(self, srcURL, dstURL, path, block_name, noParentsReadOnly , pruneBranches )

  def insertPrimaryDataset(self, dataset):

       #Calling the Implementation function
       from dbsApiInsertPrimaryDataset import dbsApiImplInsertPrimaryDataset
       return  dbsApiImplInsertPrimaryDataset(self, dataset)

  def insertAlgorithm(self, algorithm):

       #Calling the Implementation function
       from dbsApiInsertAlgorithm import dbsApiImplInsertAlgorithm
       return  dbsApiImplInsertAlgorithm(self, algorithm)

  def insertProcessedDataset(self, dataset):

       #Calling the Implementation function
       from dbsApiInsertProcessedDataset import dbsApiImplInsertProcessedDataset
       return  dbsApiImplInsertProcessedDataset(self, dataset)

  def insertRun(self, run):

       #Calling the Implementation function
       from dbsApiInsertRun import dbsApiImplInsertRun
       return  dbsApiImplInsertRun(self, run)

  def updateFileStatus(self, lfn, status):

       #Calling the Implementation function
       from dbsApiUpdateFileStatus import dbsApiImplUpdateFileStatus
       return  dbsApiImplUpdateFileStatus(self, lfn, status)

  def updateFileMetaData(self, lfn, metaData):

       #Calling the Implementation function
       from dbsApiUpdateFileMetaData import dbsApiImplUpdateFileMetaData
       return  dbsApiImplUpdateFileMetaData(self, lfn, metaData)

  def updateProcDSStatus(self, dataset, status):

       #Calling the Implementation function
       from dbsApiUpdateProcDSStatus import dbsApiImplUpdateProcDSStatus
       return  dbsApiImplUpdateProcDSStatus(self, dataset, status)

  def updateRun(self, run):

       #Calling the Implementation function
       from dbsApiUpdateRun import dbsApiImplUpdateRun
       return  dbsApiImplUpdateRun(self, run)

  def updateLumiSection(self, lumi):

       #Calling the Implementation function
       from dbsApiUpdateLumiSection import dbsApiImplUpdateLumiSection
       return  dbsApiImplUpdateLumiSection(self, lumi)

  def insertBranchInfo(self, branchInfo):

       #Calling the Implementation function
       from dbsApiInsertBranchInfo import dbsApiImplInsertBranchInfo
       return  dbsApiImplInsertBranchInfo(self, branchInfo)

  def insertFiles(self, dataset=None, files=[], block=None):

       #Calling the Implementation function
       from dbsApiInsertFiles import dbsApiImplInsertFiles
       return  dbsApiImplInsertFiles(self, dataset, files, block)

  def remapFiles_DEPRECATED(self, inFiles, outFile):

       #Calling the Implementation function
       from dbsApiRemapFiles_DEPRECATED import dbsApiImplRemapFiles_DEPRECATED
       return  dbsApiImplRemapFiles_DEPRECATED(self, inFiles, outFile)

  def insertBlock(self, dataset, block=None, storage_element_list=None, open_for_writing='y'):

       #Calling the Implementation function
       from dbsApiInsertBlock import dbsApiImplInsertBlock
       return  dbsApiImplInsertBlock(self, dataset, block, storage_element_list, open_for_writing)

  def deleteReplicaFromBlock(self, block, storage_element):

       #Calling the Implementation function
       from dbsApiDeleteReplicaFromBlock import dbsApiImplDeleteReplicaFromBlock
       return  dbsApiImplDeleteReplicaFromBlock(self, block, storage_element)

  def renameSE(self, storage_element_from, storage_element_to):

       #Calling the Implementation function
       from dbsApiRenameSE import dbsApiImplRenameSE
       return  dbsApiImplRenameSE(self, storage_element_from, storage_element_to)

  def updateSEBlock(self, blockName, storage_element_from, storage_element_to):

       #Calling the Implementation function
       from dbsApiUpdateSEBlock import dbsApiImplUpdateSEBlock
       return  dbsApiImplUpdateSEBlock(self, blockName, storage_element_from, storage_element_to)

  def openBlock(self, block=None ):

       #Calling the Implementation function
       from dbsApiOpenBlock import dbsApiImplOpenBlock
       return  dbsApiImplOpenBlock(self, block )

  def closeBlock(self, block=None ):

       #Calling the Implementation function
       from dbsApiCloseBlock import dbsApiImplCloseBlock
       return  dbsApiImplCloseBlock(self, block )

  def addReplicaToBlock(self, block, storageElement):

       #Calling the Implementation function
       from dbsApiAddReplicaToBlock import dbsApiImplAddReplicaToBlock
       return  dbsApiImplAddReplicaToBlock(self, block, storageElement)

  def insertTier(self, tier_name):

       #Calling the Implementation function
       from dbsApiInsertTier import dbsApiImplInsertTier
       return  dbsApiImplInsertTier(self, tier_name)

  def insertTierInPD(self, dataset, tier_name):

       #Calling the Implementation function
       from dbsApiInsertTierInPD import dbsApiImplInsertTierInPD
       return  dbsApiImplInsertTierInPD(self, dataset, tier_name)

  def insertTierInFile(self, lfn, tier_name):

       #Calling the Implementation function
       from dbsApiInsertTierInFile import dbsApiImplInsertTierInFile
       return  dbsApiImplInsertTierInFile(self, lfn, tier_name)

  def insertParentInPD(self, dataset, parentDS):

       #Calling the Implementation function
       from dbsApiInsertParentInPD import dbsApiImplInsertParentInPD
       return  dbsApiImplInsertParentInPD(self, dataset, parentDS)

  def insertAlgoInPD(self, dataset, algorithm):

       #Calling the Implementation function
       from dbsApiInsertAlgoInPD import dbsApiImplInsertAlgoInPD
       return  dbsApiImplInsertAlgoInPD(self, dataset, algorithm)

  def insertRunInPD(self, dataset, run):

       #Calling the Implementation function
       from dbsApiInsertRunInPD import dbsApiImplInsertRunInPD
       return  dbsApiImplInsertRunInPD(self, dataset, run)

  def insertLumiSection(self, lumi):

       #Calling the Implementation function
       from dbsApiInsertLumiSection import dbsApiImplInsertLumiSection
       return  dbsApiImplInsertLumiSection(self, lumi)

  def insertMergedDataset(self, dataset, merege_ds_name, merge_algo):

       #Calling the Implementation function
       from dbsApiInsertMergedDataset import dbsApiImplInsertMergedDataset
       return  dbsApiImplInsertMergedDataset(self, dataset, merege_ds_name, merge_algo)

  def insertMergedFile(self, parents, outputFile):

       #Calling the Implementation function
       from dbsApiInsertMergedFile import dbsApiImplInsertMergedFile
       return  dbsApiImplInsertMergedFile(self, parents, outputFile)

  def createAnalysisDataset(self, analysisdataset, defName):

       #Calling the Implementation function
       from dbsApiCreateAnalysisDataset import dbsApiImplCreateAnalysisDataset
       return  dbsApiImplCreateAnalysisDataset(self, analysisdataset, defName)

  def createAnalysisDatasetDefinition(self, analysisDatasetDefinition ):

       #Calling the Implementation function
       from dbsApiCreateAnalysisDatasetDefinition import dbsApiImplCreateAnalysisDatasetDefinition
       return  dbsApiImplCreateAnalysisDatasetDefinition(self, analysisDatasetDefinition )

  def remap_DEPRECATED(self, files, outFile):

       #Calling the Implementation function
       from dbsApiRemap_DEPRECATED import dbsApiImplRemap_DEPRECATED
       return  dbsApiImplRemap_DEPRECATED(self, files, outFile)

  def versionDQ(self, version, description=""):

       #Calling the Implementation function
       from dbsApiVersionDQ import dbsApiImplVersionDQ
       return  dbsApiImplVersionDQ(self, version, description)

  def insertSubSystem(self, name, parent="CMS"):

       #Calling the Implementation function
       from dbsApiInsertSubSystem import dbsApiImplInsertSubSystem
       return  dbsApiImplInsertSubSystem(self, name, parent)

  def listSubSystems(self):

       #Calling the Implementation function
       from dbsApiListSubSystems import dbsApiImplListSubSystems
       return  dbsApiImplListSubSystems(self)

  def listRunLumiDQ(self, runLumiDQList=[], timeStamp="", dqVersion=""):

       #Calling the Implementation function
       from dbsApiListRunLumiDQ import dbsApiImplListRunLumiDQ
       return  dbsApiImplListRunLumiDQ(self, runLumiDQList, timeStamp, dqVersion)

  def updateRunLumiDQ(self, runLumiDQList):

       #Calling the Implementation function
       from dbsApiUpdateRunLumiDQ import dbsApiImplUpdateRunLumiDQ
       return  dbsApiImplUpdateRunLumiDQ(self, runLumiDQList)

  def insertRunLumiDQ(self, runLumiDQList):

       #Calling the Implementation function
       from dbsApiInsertRunLumiDQ import dbsApiImplInsertRunLumiDQ
       return  dbsApiImplInsertRunLumiDQ(self, runLumiDQList)

  def insertRunRangeDQ(self, startRun, endRun, dqFlagList):

       #Calling the Implementation function
       from dbsApiInsertRunRangeDQ import dbsApiImplInsertRunRangeDQ
       return  dbsApiImplInsertRunRangeDQ(self, startRun, endRun, dqFlagList)

  def insertLumiRangeDQ(self, runNumber, startLumi, endLumi, dqFlagList):

       #Calling the Implementation function
       from dbsApiInsertLumiRangeDQ import dbsApiImplInsertLumiRangeDQ
       return  dbsApiImplInsertLumiRangeDQ(self, runNumber, startLumi, endLumi, dqFlagList)

  def listDQVersions(self):

       #Calling the Implementation function
       from dbsApiListDQVersions import dbsApiImplListDQVersions
       return  dbsApiImplListDQVersions(self)

#############################################################################
# Unit testing: see $PWD/UnitTests
############################################################################

from dbsException import *
from dbsApiException import *
from dbsOptions import DbsOptionParser

if __name__ == "__main__":

  try:

    optManager  = DbsOptionParser()
    (opts,args) = optManager.getOpt()

    api = DbsApi(opts.__dict__)
    serverInfo = api.getServerInfo()
    print api.getServerUrl()
    print "Server Version : ", serverInfo['ServerVersion']
    print "Schema Version : ", serverInfo['SchemaVersion']

    #print api.listSubSystems()
    #print api.listDQVersions()

  except DbsApiException, ex:
    print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
    if ex.getErrorCode() not in (None, ""):
      print "DBS Exception Error Code: ", ex.getErrorCode()

