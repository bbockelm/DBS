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
from xml.sax import SAXParseException

from dbsConfig import DbsConfig
import urlparse
import urllib2

import logging
import inspect

from dbsLogger import *

from dbsUtil import *

#from dbsApiListPrimaryDatasets import *



#DBS Api version
__version__ = "DBS_1_0_8"
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
     try:
       #Calling the Implementation function
       from dbsApiGetServerInfo import dbsApiImplGetServerInfo
       return  dbsApiImplGetServerInfo(self)

     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex, SAXParseException)):
                raise ex
	else:
		raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")

  def listPrimaryDatasets(self, pattern="*"):
     try:
       #Calling the Implementation function
       from dbsApiListPrimaryDatasets import dbsApiImplListPrimaryDatasets
       return  dbsApiImplListPrimaryDatasets(self, pattern)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listProcessedDatasets(self, patternPrim="*", patternDT="*", patternProc="*",  patternVer="*", patternFam="*", patternExe="*", patternPS="*"):
     try:
       #Calling the Implementation function
       from dbsApiListProcessedDatasets import dbsApiImplListProcessedDatasets
       return  dbsApiImplListProcessedDatasets(self, patternPrim, patternDT, patternProc,  patternVer, patternFam, patternExe, patternPS)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listAlgorithms(self, patternVer="*", patternFam="*", patternExe="*", patternPS="*"):
     try:
       #Calling the Implementation function
       from dbsApiListAlgorithms import dbsApiImplListAlgorithms
       return  dbsApiImplListAlgorithms(self, patternVer, patternFam, patternExe, patternPS)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listRuns(self, dataset):
     try:
       #Calling the Implementation function
       from dbsApiListRuns import dbsApiImplListRuns
       return  dbsApiImplListRuns(self, dataset)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listTiers(self, dataset):
     try:
       #Calling the Implementation function
       from dbsApiListTiers import dbsApiImplListTiers
       return  dbsApiImplListTiers(self, dataset)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listBlocks(self, dataset=None, block_name="*", storage_element_name="*"):
     try:
       #Calling the Implementation function
       from dbsApiListBlocks import dbsApiImplListBlocks
       return  dbsApiImplListBlocks(self, dataset, block_name, storage_element_name)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listStorageElements(self, storage_element_name="*"):
     try:
       #Calling the Implementation function
       from dbsApiListStorageElements import dbsApiImplListStorageElements
       return  dbsApiImplListStorageElements(self, storage_element_name)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listLFNs(self, path="", queryableMetaData=""):
     try:
       #Calling the Implementation function
       from dbsApiListLFNs import dbsApiImplListLFNs
       return  dbsApiImplListLFNs(self, path, queryableMetaData)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listDatasetFiles(self, datasetPath):
     try:
       #Calling the Implementation function
       from dbsApiListDatasetFiles import dbsApiImplListDatasetFiles
       return  dbsApiImplListDatasetFiles(self, datasetPath)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listFiles(self, path="", primary="", proc="", tier_list=[], analysisDataset="",blockName="", patternLFN="", runNumber="", details=None, retriveList=()):
     try:
       #Calling the Implementation function
       from dbsApiListFiles import dbsApiImplListFiles
       return  dbsApiImplListFiles(self, path, primary, proc, tier_list, analysisDataset,blockName, patternLFN, runNumber, details, retriveList)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listFileParents(self, lfn):
     try:
       #Calling the Implementation function
       from dbsApiListFileParents import dbsApiImplListFileParents
       return  dbsApiImplListFileParents(self, lfn)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listFileAlgorithms(self, lfn):
     try:
       #Calling the Implementation function
       from dbsApiListFileAlgorithms import dbsApiImplListFileAlgorithms
       return  dbsApiImplListFileAlgorithms(self, lfn)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listFileTiers(self, lfn):
     try:
       #Calling the Implementation function
       from dbsApiListFileTiers import dbsApiImplListFileTiers
       return  dbsApiImplListFileTiers(self, lfn)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listFileBranches(self, lfn):
     try:
       #Calling the Implementation function
       from dbsApiListFileBranches import dbsApiImplListFileBranches
       return  dbsApiImplListFileBranches(self, lfn)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listFileLumis(self, lfn):
     try:
       #Calling the Implementation function
       from dbsApiListFileLumis import dbsApiImplListFileLumis
       return  dbsApiImplListFileLumis(self, lfn)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listAnalysisDatasetDefinition(self, pattern="*"):
     try:
       #Calling the Implementation function
       from dbsApiListAnalysisDatasetDefinition import dbsApiImplListAnalysisDatasetDefinition
       return  dbsApiImplListAnalysisDatasetDefinition(self, pattern)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listAnalysisDataset(self, pattern="*", path="", version=None):
     try:
       #Calling the Implementation function
       from dbsApiListAnalysisDataset import dbsApiImplListAnalysisDataset
       return  dbsApiImplListAnalysisDataset(self, pattern, path, version)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")

  def listCompADS(self, pattern="*"):
     try:
       #Calling the Implementation function
       from dbsApiListCompAnalysisDataset import dbsApiImplListCompADS 
       return dbsApiImplListCompADS(self, pattern)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")

  def listDatasetParents(self, dataset):
     try:
       #Calling the Implementation function
       from dbsApiListDatasetParents import dbsApiImplListDatasetParents
       return  dbsApiImplListDatasetParents(self, dataset)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listDatasetContents(self, path, block_name):
     try:
       #Calling the Implementation function
       from dbsApiListDatasetContents import dbsApiImplListDatasetContents
       return  dbsApiImplListDatasetContents(self, path, block_name)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertDatasetContents(self, xmlinput, ignore_parent = False):
     try:
       #Calling the Implementation function
       from dbsApiInsertDatasetContents import dbsApiImplInsertDatasetContents
       return  dbsApiImplInsertDatasetContents(self, xmlinput, ignore_parent)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def migrateDatasetContents(self, srcURL, dstURL, path, block_name="", noParentsReadOnly = False, pruneBranches = False):
     try:
       #Calling the Implementation function
       from dbsApiMigrateDatasetContents import dbsApiImplMigrateDatasetContents
       return  dbsApiImplMigrateDatasetContents(self, srcURL, dstURL, path, block_name, noParentsReadOnly , pruneBranches )
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertPrimaryDataset(self, dataset):
     try:
       #Calling the Implementation function
       from dbsApiInsertPrimaryDataset import dbsApiImplInsertPrimaryDataset
       return  dbsApiImplInsertPrimaryDataset(self, dataset)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertAlgorithm(self, algorithm):
     try:
       #Calling the Implementation function
       from dbsApiInsertAlgorithm import dbsApiImplInsertAlgorithm
       return  dbsApiImplInsertAlgorithm(self, algorithm)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertProcessedDataset(self, dataset):
     try:
       #Calling the Implementation function
       from dbsApiInsertProcessedDataset import dbsApiImplInsertProcessedDataset
       return  dbsApiImplInsertProcessedDataset(self, dataset)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertRun(self, run):
     try:
       #Calling the Implementation function
       from dbsApiInsertRun import dbsApiImplInsertRun
       return  dbsApiImplInsertRun(self, run)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def updateFileStatus(self, lfn, status, description=""):
     try:
       #Calling the Implementation function
       from dbsApiUpdateFileStatus import dbsApiImplUpdateFileStatus
       return  dbsApiImplUpdateFileStatus(self, lfn, status, description)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def updateFileMetaData(self, lfn, metaData):
     try:
       #Calling the Implementation function
       from dbsApiUpdateFileMetaData import dbsApiImplUpdateFileMetaData
       return  dbsApiImplUpdateFileMetaData(self, lfn, metaData)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def updateProcDSStatus(self, dataset, status):
     try:
       #Calling the Implementation function
       from dbsApiUpdateProcDSStatus import dbsApiImplUpdateProcDSStatus
       return  dbsApiImplUpdateProcDSStatus(self, dataset, status)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def updateRun(self, run):
     try:
       #Calling the Implementation function
       from dbsApiUpdateRun import dbsApiImplUpdateRun
       return  dbsApiImplUpdateRun(self, run)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")     
  def updateLumiSection(self, lumi):
     try:
       #Calling the Implementation function
       from dbsApiUpdateLumiSection import dbsApiImplUpdateLumiSection
       return  dbsApiImplUpdateLumiSection(self, lumi)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertBranchInfo(self, branchInfo):
     try:
       #Calling the Implementation function
       from dbsApiInsertBranchInfo import dbsApiImplInsertBranchInfo
       return  dbsApiImplInsertBranchInfo(self, branchInfo)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertFiles(self, dataset=None, files=[], block=None):
     try:
       #Calling the Implementation function
       from dbsApiInsertFiles import dbsApiImplInsertFiles
       return  dbsApiImplInsertFiles(self, dataset, files, block)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def remapFiles_DEPRECATED(self, inFiles, outFile):
     try:
       #Calling the Implementation function
       from dbsApiRemapFiles_DEPRECATED import dbsApiImplRemapFiles_DEPRECATED
       return  dbsApiImplRemapFiles_DEPRECATED(self, inFiles, outFile)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertBlock(self, dataset, block=None, storage_element_list=None, open_for_writing='y'):
     try:
       #Calling the Implementation function
       from dbsApiInsertBlock import dbsApiImplInsertBlock
       return  dbsApiImplInsertBlock(self, dataset, block, storage_element_list, open_for_writing)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def deleteReplicaFromBlock(self, block, storage_element):
     try:
       #Calling the Implementation function
       from dbsApiDeleteReplicaFromBlock import dbsApiImplDeleteReplicaFromBlock
       return  dbsApiImplDeleteReplicaFromBlock(self, block, storage_element)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def renameSE(self, storage_element_from, storage_element_to):
     try:
       #Calling the Implementation function
       from dbsApiRenameSE import dbsApiImplRenameSE
       return  dbsApiImplRenameSE(self, storage_element_from, storage_element_to)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def updateSEBlock(self, blockName, storage_element_from, storage_element_to):
     try:
       #Calling the Implementation function
       from dbsApiUpdateSEBlock import dbsApiImplUpdateSEBlock
       return  dbsApiImplUpdateSEBlock(self, blockName, storage_element_from, storage_element_to)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def openBlock(self, block=None ):
     try:
       #Calling the Implementation function
       from dbsApiOpenBlock import dbsApiImplOpenBlock
       return  dbsApiImplOpenBlock(self, block )
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def closeBlock(self, block=None ):
     try:
       #Calling the Implementation function
       from dbsApiCloseBlock import dbsApiImplCloseBlock
       return  dbsApiImplCloseBlock(self, block )
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def addReplicaToBlock(self, block, storageElement):
     try:
       #Calling the Implementation function
       from dbsApiAddReplicaToBlock import dbsApiImplAddReplicaToBlock
       return  dbsApiImplAddReplicaToBlock(self, block, storageElement)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertTier(self, tier_name):
     try:
       #Calling the Implementation function
       from dbsApiInsertTier import dbsApiImplInsertTier
       return  dbsApiImplInsertTier(self, tier_name)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertTierInPD(self, dataset, tier_name):
     try:
       #Calling the Implementation function
       from dbsApiInsertTierInPD import dbsApiImplInsertTierInPD
       return  dbsApiImplInsertTierInPD(self, dataset, tier_name)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertTierInFile(self, lfn, tier_name):
     try:
       #Calling the Implementation function
       from dbsApiInsertTierInFile import dbsApiImplInsertTierInFile
       return  dbsApiImplInsertTierInFile(self, lfn, tier_name)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertParentInPD(self, dataset, parentDS):
     try:
       #Calling the Implementation function
       from dbsApiInsertParentInPD import dbsApiImplInsertParentInPD
       return  dbsApiImplInsertParentInPD(self, dataset, parentDS)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertAlgoInPD(self, dataset, algorithm):
     try:
       #Calling the Implementation function
       from dbsApiInsertAlgoInPD import dbsApiImplInsertAlgoInPD
       return  dbsApiImplInsertAlgoInPD(self, dataset, algorithm)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertRunInPD(self, dataset, run):
     try:
       #Calling the Implementation function
       from dbsApiInsertRunInPD import dbsApiImplInsertRunInPD
       return  dbsApiImplInsertRunInPD(self, dataset, run)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertLumiSection(self, lumi):
     try:
       #Calling the Implementation function
       from dbsApiInsertLumiSection import dbsApiImplInsertLumiSection
       return  dbsApiImplInsertLumiSection(self, lumi)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertMergedDataset(self, dataset, merege_ds_name, merge_algo):
     try:
       #Calling the Implementation function
       from dbsApiInsertMergedDataset import dbsApiImplInsertMergedDataset
       return  dbsApiImplInsertMergedDataset(self, dataset, merege_ds_name, merge_algo)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertMergedFile(self, parents, outputFile):
     try:
       #Calling the Implementation function
       from dbsApiInsertMergedFile import dbsApiImplInsertMergedFile
       return  dbsApiImplInsertMergedFile(self, parents, outputFile)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def createAnalysisDataset(self, analysisdataset, defName):
     try:
       #Calling the Implementation function
       from dbsApiCreateAnalysisDataset import dbsApiImplCreateAnalysisDataset
       return  dbsApiImplCreateAnalysisDataset(self, analysisdataset, defName)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def createAnalysisDatasetDefinition(self, analysisDatasetDefinition ):
     try:
       #Calling the Implementation function
       from dbsApiCreateAnalysisDatasetDefinition import dbsApiImplCreateAnalysisDatasetDefinition
       return  dbsApiImplCreateAnalysisDatasetDefinition(self, analysisDatasetDefinition )
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def createCompADS(self, compADS):
     try:
      #Calling the Implementation function
      from dbsApiCreateCompADS import dbsApiImplCreateCompADS
      dbsApiImplCreateCompADS(self, compADS)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def remap_DEPRECATED(self, files, outFile):
     try:
       #Calling the Implementation function
       from dbsApiRemap_DEPRECATED import dbsApiImplRemap_DEPRECATED
       return  dbsApiImplRemap_DEPRECATED(self, files, outFile)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def versionDQ(self, version, description=""):
     try:
       #Calling the Implementation function
       from dbsApiVersionDQ import dbsApiImplVersionDQ
       return  dbsApiImplVersionDQ(self, version, description)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertSubSystem(self, name, parent="CMS"):
     try:
       #Calling the Implementation function
       from dbsApiInsertSubSystem import dbsApiImplInsertSubSystem
       return  dbsApiImplInsertSubSystem(self, name, parent)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listSubSystems(self):
     try:
       #Calling the Implementation function
       from dbsApiListSubSystems import dbsApiImplListSubSystems
       return  dbsApiImplListSubSystems(self)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listRunLumiDQ(self, runLumiDQList=[], timeStamp="", dqVersion=""):
     try:
       #Calling the Implementation function
       from dbsApiListRunLumiDQ import dbsApiImplListRunLumiDQ
       return  dbsApiImplListRunLumiDQ(self, runLumiDQList, timeStamp, dqVersion)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def updateRunLumiDQ(self, runLumiDQList):
     try:
       #Calling the Implementation function
       from dbsApiUpdateRunLumiDQ import dbsApiImplUpdateRunLumiDQ
       return  dbsApiImplUpdateRunLumiDQ(self, runLumiDQList)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertRunLumiDQ(self, runLumiDQList):
     try:
       #Calling the Implementation function
       from dbsApiInsertRunLumiDQ import dbsApiImplInsertRunLumiDQ
       return  dbsApiImplInsertRunLumiDQ(self, runLumiDQList)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertRunRangeDQ(self, startRun, endRun, dqFlagList):
     try:
       #Calling the Implementation function
       from dbsApiInsertRunRangeDQ import dbsApiImplInsertRunRangeDQ
       return  dbsApiImplInsertRunRangeDQ(self, startRun, endRun, dqFlagList)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def insertLumiRangeDQ(self, runNumber, startLumi, endLumi, dqFlagList):
     try:
       #Calling the Implementation function
       from dbsApiInsertLumiRangeDQ import dbsApiImplInsertLumiRangeDQ
       return  dbsApiImplInsertLumiRangeDQ(self, runNumber, startLumi, endLumi, dqFlagList)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
  def listDQVersions(self):
     try:
       #Calling the Implementation function
       from dbsApiListDQVersions import dbsApiImplListDQVersions
       return  dbsApiImplListDQVersions(self)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")

  def deleteRecycleBin(self, path, block):

     try:
        #Calling the Implementation function
        from dbsApiDeleteRecycleBin import dbsApiImplDeleteRecycleBin
        return  dbsApiImplDeleteRecycleBin(self, path, block)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")

  def deleteBlock(self, path, block):

     try:
        #Calling the Implementation function
        from dbsApiDeleteBlock import dbsApiImplDeleteBlock
        return  dbsApiImplDeleteBlock(self, path, block)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
 
  def undeleteBlock(self, path, block):

     try:
        #Calling the Implementation function
        from dbsApiDeleteBlock import dbsApiImplUndeleteBlock
        return  dbsApiImplUndeleteBlock(self, path, block)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
 
  def deleteProcDS(self, path):

     try:
        #Calling the Implementation function
        from dbsApiDeleteProcDS import dbsApiImplDeleteProcDS
        return  dbsApiImplDeleteProcDS(self, path)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")
 
  def undeleteProcDS(self, path):

     try:
        #Calling the Implementation function
        from dbsApiDeleteProcDS import dbsApiImplUndeleteProcDS
        return  dbsApiImplUndeleteProcDS(self, path)
     except Exception, ex:
        if (isinstance(ex,DbsApiException) or isinstance(ex,SAXParseException)):
                raise ex
        else:
                raise DbsApiException(args="Unhandled Exception: "+str(ex), code="5991")


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
    args={}
    url_list=['https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_01_writer/servlet/DBSServlet',
		'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_01_writer/servlet/DBSServlet',
		'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_02_writer/servlet/DBSServlet',
		'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_03_writer/servlet/DBSServlet',
		'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_04_writer/servlet/DBSServlet',
		'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_05_writer/servlet/DBSServlet',
		'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_06_writer/servlet/DBSServlet',
		'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_07_writer/servlet/DBSServlet',
		'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_08_writer/servlet/DBSServlet',
		'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_09_writer/servlet/DBSServlet',
		'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet',
		'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_tier0_writer/servlet/DBSServlet',
		'https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_global_writer/servlet/DBSServlet',
		'http://cmsdbsprod.cern.ch/cms_dbs_prod_local_01/servlet/DBSServlet',
		'http://cmsdbsprod.cern.ch/cms_dbs_prod_local_02/servlet/DBSServlet',
		'http://cmsdbsprod.cern.ch/cms_dbs_prod_local_03/servlet/DBSServlet',
		'http://cmsdbsprod.cern.ch/cms_dbs_prod_local_04/servlet/DBSServlet',
		'http://cmsdbsprod.cern.ch/cms_dbs_prod_local_05/servlet/DBSServlet',
		'http://cmsdbsprod.cern.ch/cms_dbs_prod_local_06/servlet/DBSServlet',
		'http://cmsdbsprod.cern.ch/cms_dbs_prod_local_07/servlet/DBSServlet',
		'http://cmsdbsprod.cern.ch/cms_dbs_prod_local_08/servlet/DBSServlet',
		'http://cmsdbsprod.cern.ch/cms_dbs_prod_local_09/servlet/DBSServlet',
		'http://cmsdbsprod.cern.ch/cms_dbs_prod_local_10/servlet/DBSServlet',
		'http://cmsdbsprod.cern.ch/cms_dbs_prod_tier0/servlet/DBSServlet',
		'http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet']

    args['mode']='POST'
    args['version']='DBS_1_0_8'
    args['level']='DBSINFO'
    #api = DbsApi(args)

    for aurl in url_list:
       args['url']=aurl
       print aurl
       api = DbsApi(args) 
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

