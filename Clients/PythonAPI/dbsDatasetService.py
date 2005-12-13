#!/usr/bin/env python
#
# $Id: dbsDatasetService.py,v 1.7 2005/12/13 14:44:20 sveseli Exp $
#
# DBS Dataset Web Service class. 
#


###############################################################################
# Python imports.
import threading
import SOAPpy

import dbsApi
import dbsEventCollection
import dbsFileBlock
import dbsDataset
import dbsPrimaryDataset
import dbsProcessedDataset
import dbsWebServiceException
import dbsWebService
import dbsLogManager

DATASET_PATH_NAME_PAR_ = "datasetPathName"
DATA_TIER_LIST_PAR_ = "dataTierList"
PRIMARY_DATASET_PAR_ = "primaryDataset"
PROCESSED_DATASET_PAR_ = "processedDataset"
EVENT_COLLECTION_LIST_PAR_ = "eventCollectionList"
FILE_BLOCK_PAR_ = "fileBlock"

FILE_BLOCK_LIST_KWD_ = "fileBlockList"
DATASET_PARENT_LIST_KWD_ = "datasetParentList"

WSDL_NAMESPACE_ = "DbsDatasetService.wsdl.xml"

###############################################################################
# Exception classes.

# Generic error class.
class DbsDatasetServiceFault(dbsWebServiceException.DbsWebServiceFault):

  def __init__(self, **kwargs):
    """ Initialization. """
    dbsWebServiceException.DbsWebServiceFault.__init__(self, **kwargs)
    # Namespace/type isn't working in soappy for exceptions.
    #self.setNamespace(WSDL_NAMESPACE_)
    #self.setType(self.__class__.__name__)

class DbsDatasetServiceClientFault(dbsWebServiceException.DbsWebServiceClientFault):

  def __init__(self, **kwargs):
    """ Initialization. """
    dbsWebServiceException.DbsWebServiceClientFault.__init__(self, **kwargs)

class InvalidDatasetPathNameFault(DbsDatasetServiceClientFault):

  def __init__(self, **kwargs):
    """ Initialization. """
    DbsDatasetServiceClientFault.__init__(self, **kwargs)

class GetDatasetContentsFault(DbsDatasetServiceFault):

  def __init__(self, **kwargs):
    """ Initialization. """
    DbsDatasetServiceFault.__init__(self, **kwargs)

class GetDatasetProvenanceFault(DbsDatasetServiceFault):

  def __init__(self, **kwargs):
    """ Initialization. """
    DbsDatasetServiceFault.__init__(self, **kwargs)

class CreatePrimaryDatasetFault(DbsDatasetServiceFault):

  def __init__(self, **kwargs):
    """ Initialization. """
    DbsDatasetServiceFault.__init__(self, **kwargs)

class CreateProcessedDatasetFault(DbsDatasetServiceFault):

  def __init__(self, **kwargs):
    """ Initialization. """
    DbsDatasetServiceFault.__init__(self, **kwargs)


class InsertEventCollectionsFault(DbsDatasetServiceFault):

  def __init__(self, **kwargs):
    """ Initialization. """
    DbsDatasetServiceFault.__init__(self, **kwargs)

class CreateFileBlockFault(DbsDatasetServiceFault):

  def __init__(self, **kwargs):
    """ Initialization. """
    DbsDatasetServiceFault.__init__(self, **kwargs)


###############################################################################
# Dataset service singleton class.

class DbsDatasetService(dbsWebService.DbsWebService):

  # Singleton support.
  __instance = None
  __instanceLock = threading.RLock()

  # Initialization.
  def __init__(self):
    """ Initialization. """
    DbsDatasetService.__instanceLock.acquire()
    try:
        
      funcName = "%s.%s" % (self.__class__, "__init__()")

      # Singleton stuff.
      if DbsDatasetService.__instance:
        raise DbsDatasetService.__instance
      DbsDatasetService.__instance = self

      # Log manager.
      self._logManager = dbsLogManager.getInstance()

      # Base class.
      dbsWebService.DbsWebService.__init__(self, self.__class__.__name__)

    finally:
      DbsDatasetService.__instanceLock.release()

  def getDatasetContents(self, datasetPathName=None,
			 *args, **kwargs):
    """
    Retrieve list of file blocks, each containing a set of event collections,
    for a given the dataset path name string.

    Returns: list of DbsFileBlock objects.
    Faults: InvalidDatasetPathNameFault
            DbsDatasetServiceFault
    """

    # Check arguments.
    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetContents()")
    try:
      datasetPathName = self.getParameter(
        DATASET_PATH_NAME_PAR_, datasetPathName, **kwargs)
    except dbsWebServiceException.MissingParameterFault, ex:
      self._logManager.log(what="%s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise GetDatasetContentsFault(exception=ex)


    # Acquire servant.
    try:
      servantId = self.acquireServant(funcName)
    except DbsWebServiceException, ex:
      self._logManager.log(what="%s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise GetDatasetContentsFault(exception=ex)

    # Do the work.
    try:
      try:
        msg = "Retrieving dataset contents for datasetPathName: %s" % datasetPathName
	self._logManager.log(what=msg, where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)
	fileBlockList = dbsFileBlock.DbsFileBlockList(
	  self.getApi().getDatasetContents(datasetPathName))
        msg = "Retrieved %s file blocks for datasetPathName : %s" % (
	  len(fileBlockList), datasetPathName)
	self._logManager.log(what=msg, where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)

      except dbsApi.InvalidDatasetPathName, ex:
	self._logManager.log(what="%s" % ex,
			     where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise InvalidDatasetPathNameFault(exception=ex)
      except Exception, ex:
	self._logManager.log(what="%s" % ex,
			     where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise GetDatasetContentsFault(exception=ex)

      return { FILE_BLOCK_LIST_KWD_ : fileBlockList.getWsRep() }
    finally:
      self.releaseServant(servantId)


  def getDatasetProvenance(self, datasetPathName=None,
			   dataTierList=None,
			   *args, **kwargs):
    """
    Retrieve list of parent datasets
    for a given the dataset path name string.

    Returns: list of DbsDataset objects.
    Faults: InvalidDatasetPathNameFault
            DbsDatasetServiceFault
    """

    # Check arguments.
    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetProvenance()")
    try:
      datasetPathName = self.getParameter(
        DATASET_PATH_NAME_PAR_, datasetPathName, **kwargs)
      dataTierList = self.getParameter(
        DATA_TIER_LIST_PAR_, dataTierList, **kwargs)
    except dbsWebServiceException.MissingParameterFault, ex:
      self._logManager.log(what="%s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise GetDatasetProvenanceFault(exception=ex)


    # Acquire servant.
    try:
      servantId = self.acquireServant(funcName)
    except DbsWebServiceException, ex:
      self._logManager.log(what="%s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise GetDatasetProvenanceFault(exception=ex)

    # Do the work.
    try:
      try:
        msg = "Retrieving dataset provenance for datasetPathName %s and dataTierList %s" % (datasetPathName, dataTierList)
	self._logManager.log(what=msg, where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)
	datasetList = dbsDataset.DbsDatasetList(
	  self.getApi().getDatasetProvenance(datasetPathName, dataTierList))
        msg = "Retrieved %s parent datasets for datasetPathName : %s" % (
	  len(datasetList), datasetPathName)
	self._logManager.log(what=msg, where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)

      except dbsApi.InvalidDatasetPathName, ex:
	self._logManager.log(what="%s" % ex,
			     where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise InvalidDatasetPathNameFault(exception=ex)
      except Exception, ex:
	self._logManager.log(what="%s" % ex,
			     where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise GetDatasetProvenanceFault(exception=ex)

      return { DATASET_PARENT_LIST_KWD_ : datasetList.getWsRep() }
    finally:
      self.releaseServant(servantId)


  def createPrimaryDataset(self, primaryDataset=None,
			   *args, **kwargs):
    """
    Create primary dataset.

    Returns: primary dataset id.
    Faults: CreatePrimaryDatasetFault
    """

    # Check arguments.
    funcName = "%s.%s" % (self.__class__.__name__, "createPrimaryDataset()")
    try:
      primaryDatasetDict = self.getParameter(
        PRIMARY_DATASET_PAR_, primaryDataset, **kwargs)
      primaryDataset = dbsPrimaryDataset.DbsPrimaryDataset(datasetDict=primaryDatasetDict)
    except dbsWebServiceException.MissingParameterFault, ex:
      self._logManager.log(what="%s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise CreatePrimaryDatasetFault(exception=ex)


    # Acquire servant.
    try:
      servantId = self.acquireServant(funcName)
    except DbsWebServiceException, ex:
      self._logManager.log(what="%s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise CreatePrimaryDatasetFault(exception=ex)

    # Do the work.
    try:
      try:
        msg = "Creating primary dataset %s" % (primaryDataset)
	self._logManager.log(what=msg, where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)
	primaryDatasetId = self.getApi().createPrimaryDataset(primaryDataset)
        msg = "Created primary dataset with id %s: " % (primaryDatasetId)
	self._logManager.log(what=msg, where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)

      except Exception, ex:
	self._logManager.log(what="%s" % ex,
			     where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise CreatePrimaryDatasetFault(exception=ex)

      return { dbsPrimaryDataset.PRIMARY_DATASET_ID_TAG_ : primaryDatasetId }
    finally:
      self.releaseServant(servantId)



  def createProcessedDataset(self, processedDataset=None,
			   *args, **kwargs):
    """
    Create processed dataset.

    Returns: processed dataset id.
    Faults: CreateProcessedDatasetFault
    """

    # Check arguments.
    funcName = "%s.%s" % (self.__class__.__name__, "createProcessedDataset()")
    try:
      processedDatasetDict = self.getParameter(
        PROCESSED_DATASET_PAR_, processedDataset, **kwargs)
      processedDataset = dbsProcessedDataset.DbsProcessedDataset(datasetDict=processedDatasetDict)
    except dbsWebServiceException.MissingParameterFault, ex:
      self._logManager.log(what="%s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise CreateProcessedDatasetFault(exception=ex)


    # Acquire servant.
    try:
      servantId = self.acquireServant(funcName)
    except DbsWebServiceException, ex:
      self._logManager.log(what="%s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise CreateProcessedDatasetFault(exception=ex)

    # Do the work.
    try:
      try:
        msg = "Creating processed dataset %s" % (processedDataset)
	self._logManager.log(what=msg, where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)
	processedDatasetId = self.getApi().createProcessedDataset(processedDataset)
        msg = "Created processed dataset with id %s: " % (processedDatasetId)
	self._logManager.log(what=msg, where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)

      except Exception, ex:
	self._logManager.log(what="%s" % ex,
			     where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise CreateProcessedDatasetFault(exception=ex)

      return { dbsProcessedDataset.PROCESSED_DATASET_ID_TAG_ : processedDatasetId }
    finally:
      self.releaseServant(servantId)


  def insertEventCollections(self, processedDataset=None,
			     eventCollectionList=None,
			     *args, **kwargs):
    """
    Insert event collections for a given processed dataset.

    Returns: processed dataset id.
    Faults: InsertEventCollectionsFault
    """

    # Check arguments.
    funcName = "%s.%s" % (self.__class__.__name__, "insertEventCollections()")
    try:
      processedDatasetDict = self.getParameter(
        PROCESSED_DATASET_PAR_, processedDataset, **kwargs)
      processedDataset = dbsProcessedDataset.DbsProcessedDataset(datasetDict=processedDatasetDict)
      eventCollections = self.getParameter(
        EVENT_COLLECTION_LIST_PAR_, eventCollectionList, **kwargs)
      eventCollectionList = dbsEventCollection.DbsEventCollectionList(eventCollections)
    except dbsWebServiceException.MissingParameterFault, ex:
      self._logManager.log(what="%s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise InsertEventCollectionsFault(exception=ex)


    # Acquire servant.
    try:
      servantId = self.acquireServant(funcName)
    except DbsWebServiceException, ex:
      self._logManager.log(what="%s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise InsertEventCollectionsFault(exception=ex)

    # Do the work.
    try:
      try:
	processedDatasetName = processedDataset.getDatasetName()
        msg = "Inserting %s event collections for dataset %s" % (
	  len(eventCollectionList), processedDatasetName)
	self._logManager.log(what=msg, where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)
	#################
	## Here comes API call.
	# self.getApi().insertEventCollections(processedDataset, eventCollectionList)
	#################
        msg = "Inserted %s event collections for dataset %s" % (
	  len(eventCollectionList), processedDatasetName)
	self._logManager.log(what=msg, where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)

      except Exception, ex:
	self._logManager.log(what="%s" % ex,
			     where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise InsertEventCollectionsFault(exception=ex)

      return {}
    finally:
      self.releaseServant(servantId)

  def createFileBlock(self, processedDataset=None, fileBlock=None,
		      *args, **kwargs):
    """
    Create new file block.

    Returns: file block id.
    Faults: CreateFileBlockFault
    """

    # Check arguments.
    funcName = "%s.%s" % (self.__class__.__name__, "createFileBlock()")
    try:
      processedDatasetDict = self.getParameter(
        PROCESSED_DATASET_PAR_, processedDataset, **kwargs)
      processedDataset = dbsProcessedDataset.DbsProcessedDataset(datasetDict=processedDatasetDict)
      fileBlockDict = self.getParameter(
        FILE_BLOCK_PAR_, fileBlock, **kwargs)
      fileBlock = dbsFileBlock.DbsFileBlock(blockDict=fileBlockDict)
    except dbsWebServiceException.MissingParameterFault, ex:
      self._logManager.log(what="%s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise CreateFileBlockFault(exception=ex)


    # Acquire servant.
    try:
      servantId = self.acquireServant(funcName)
    except DbsWebServiceException, ex:
      self._logManager.log(what="%s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise CreateFileBlockFault(exception=ex)

    # Do the work.
    try:
      try:
	processedDatasetName = processedDataset.getDatasetName()
        msg = "Creating new file block for dataset %s" % (
	  processedDatasetName)
	self._logManager.log(what=msg, where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)
	#################
	## Here comes API call.
	# fileBlockId = self.getApi().createFileBlock(processedDataset, fileBlock)
	fileBlockId = 12345
	#################
        msg = "Created file block with id %s dataset %s" % (
	  fileBlockId, processedDatasetName)
	self._logManager.log(what=msg, where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)

      except Exception, ex:
	self._logManager.log(what="%s" % ex,
			     where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise CreateFileBlockFault(exception=ex)

      return { dbsFileBlock.FILE_BLOCK_ID_TAG_ : fileBlockId }

    finally:
      self.releaseServant(servantId)


###############################################################################
# Singleton instance.

__dbsDatasetServiceLock = threading.Lock()
def getInstance():
  """ Return DatasetService instance. """
  __dbsDatasetServiceLock.acquire()
  try:
    try:
      df = DbsDatasetService()
    except DbsDatasetService, ex:
      df = ex
    return df
  finally:
    __dbsDatasetServiceLock.release()
    

###############################################################################
# Unit testing.

if __name__ == "__main__":
  datasetPath = "eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC"
  ds = getInstance()
  fbList = ds.getDatasetContents(datasetPath)
  print "File block list: ", fbList
  print "Done"



