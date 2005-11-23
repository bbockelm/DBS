#!/usr/bin/env python
#
# $Id: dbsDatasetService.py,v 1.3 2005/11/07 21:40:02 sveseli Exp $
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
import dbsWebServiceException
import dbsWebService
import dbsLogManager

DATASET_PATH_NAME_PAR_ = "datasetPathName"
DATA_TIER_LIST_PAR_ = "dataTierList"

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



