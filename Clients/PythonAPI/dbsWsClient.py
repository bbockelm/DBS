#!/usr/bin/env python
#
# $Id: dbsWsClient.py,v 1.9 2005/12/13 17:55:27 sveseli Exp $
#
# Class which uses web services to extract info from the db.
#

import os
import string
import SOAPpy


import dbsException
import dbsUtility
import dbsFileBlock
import dbsDataset
import dbsStaticMethod

import dbsLogManager
import dbsPrimaryDataset
import dbsProcessedDataset

# Uncommenting the following line results in SOAP debugging messages.
#SOAPpy.Config.debug=1


##############################################################################
# DBS Ws utility class exceptions.

class DbsWsClientException(dbsException.DbsException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

class InvalidDatasetPathName(DbsWsClientException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsWsClientException.__init__(self, **kwargs)


##############################################################################
# Fault mapper.

class DbsWsFaultMapper:

  __exceptionMap = {
    "Client.InvalidDatasetPathNameFault" : "InvalidDatasetPathName"
    }

  def getExceptionClassName(fault):
    """ Map fault code into an exception. """
    return DbsWsFaultMapper.__exceptionMap.get(
      fault.faultcode, "DbsWsClientException")

  getExceptionClassName = dbsStaticMethod.DbsStaticMethod(getExceptionClassName)
  
##############################################################################
# WS client class.

class DbsWsClient:

  def __init__(self, wsdlUrl=None):
    """ Constructor. """
    self.setWsdlUrl(wsdlUrl)
    self._logManager = dbsLogManager.getInstance()
    
  def setWsdlUrl(self, wsdlUrl):
    """ Set wsdl url. """
    self._wsdlUrl = wsdlUrl
    self.setWsdlProxy()
    
  def setWsdlProxy(self):
    """ Set wsdl proxy if url is known. """
    if self._wsdlUrl is not None:
      self._wsdlProxy = SOAPpy.WSDL.Proxy(self._wsdlUrl)
    else:
      self._wsdlProxy = None
      
  def getDatasetContents(self, datasetPathName):
    """ Retrieve event collections given the dataset path name string. """

    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetContents()")

    # Check path.
    try:
      dbsDataset.DbsDataset.verifyDatasetPathName(datasetPathName)
    except dbsDataset.InvalidDatasetPathName, ex:
      raise InvalidDatasetPathName(args="%s" % ex)
        
    # Invoke web service call.
    try:
      self._logManager.log(
	what="Retrieving dataset contents, wsdlUrl: %s." % self._wsdlUrl,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      result = self._wsdlProxy.getDatasetContents(
	datasetPathName=datasetPathName)
      fileBlockList = dbsFileBlock.DbsFileBlockList(result.fileBlockList.data)

    except SOAPpy.faultType, ex:
      wsExClassName = DbsWsFaultMapper.getExceptionClassName(ex)
      exec "wsEx = %s(args=\"\"\"%s\"\"\")" % (wsExClassName, ex.faultstring)
      errMsg = "%s caught: %s (Will raise: %s)" % (
	ex.faultcode, ex.faultstring, wsEx.__class__.__name__)
      self._logManager.log(what=errMsg,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise wsEx
      
    except Exception, ex:
      # General exception.
      self._logManager.log(what="Web service client failed.\n%s " % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise DbsWsClientException(exception=ex)
    self._logManager.log(
      what="Retrieved dataset contents.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)

      
    return fileBlockList

  def getDatasetProvenance(self, datasetPathName, dataTierList):
    """ Retrieve dataset parents given the dataset path name string. """

    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetProvenance()")

    # Check path.
    try:
      dbsDataset.DbsDataset.verifyDatasetPathName(datasetPathName)
    except dbsDataset.InvalidDatasetPathName, ex:
      raise InvalidDatasetPathName(args="%s" % ex)

    # Invoke web service call.
    try:
      self._logManager.log(
	what="Retrieving dataset contents, wsdlUrl: %s." % self._wsdlUrl,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      result = self._wsdlProxy.getDatasetProvenance(
	datasetPathName=datasetPathName, dataTierList=dataTierList)
      datasetParentList = dbsDataset.DbsDatasetList(result.datasetParentList.data)

    except SOAPpy.faultType, ex:
      wsExClassName = DbsWsFaultMapper.getExceptionClassName(ex)
      exec "wsEx = %s(args=\"\"\"%s\"\"\")" % (wsExClassName, ex.faultstring)
      errMsg = "%s caught: %s (Will raise: %s)" % (
	ex.faultcode, ex.faultstring, wsEx.__class__.__name__)
      self._logManager.log(what=errMsg,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise wsEx
      
    except Exception, ex:
      # General exception.
      self._logManager.log(what="Web service client failed.\n%s " % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise DbsWsClientException(exception=ex)
    self._logManager.log(
      what="Retrieved dataset provenance.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)
    
    return datasetParentList

  def createPrimaryDataset(self, primaryDataset):
    """ Create primary dataset. """

    funcName = "%s.%s" % (self.__class__.__name__, "createPrimaryDataset()")

    # Invoke web service call.
    try:
      self._logManager.log(
	what="Creating primary dataset, wsdlUrl: %s." % self._wsdlUrl,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      primaryDatasetId = self._wsdlProxy.createPrimaryDataset(
	primaryDataset=primaryDataset.getWsRep())[dbsPrimaryDataset.PRIMARY_DATASET_ID_TAG_]
      self._logManager.log(
	what="Got primary dataset id: %s." % primaryDatasetId,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)

    except SOAPpy.faultType, ex:
      wsExClassName = DbsWsFaultMapper.getExceptionClassName(ex)
      exec "wsEx = %s(args=\"\"\"%s\"\"\")" % (wsExClassName, ex.faultstring)
      errMsg = "%s caught: %s (Will raise: %s)" % (
	ex.faultcode, ex.faultstring, wsEx.__class__.__name__)
      self._logManager.log(what=errMsg,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise wsEx
      
    except Exception, ex:
      # General exception.
      self._logManager.log(what="Web service client failed.\n%s " % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise DbsWsClientException(exception=ex)
    self._logManager.log(
      what="Created primary dataset with id %d." % primaryDatasetId,
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)
    
    return primaryDatasetId


  def createProcessedDataset(self, processedDataset):
    """ Create processed dataset. """

    funcName = "%s.%s" % (self.__class__.__name__, "createProcessedDataset()")

    # Invoke web service call.
    try:
      self._logManager.log(
	what="Creating processed dataset, wsdlUrl: %s." % self._wsdlUrl,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      processedDatasetId = self._wsdlProxy.createProcessedDataset(
	processedDataset=processedDataset.getWsRep())[dbsProcessedDataset.PROCESSED_DATASET_ID_TAG_]
      self._logManager.log(
	what="Got processed dataset id: %s." % processedDatasetId,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)

    except SOAPpy.faultType, ex:
      wsExClassName = DbsWsFaultMapper.getExceptionClassName(ex)
      exec "wsEx = %s(args=\"\"\"%s\"\"\")" % (wsExClassName, ex.faultstring)
      errMsg = "%s caught: %s (Will raise: %s)" % (
	ex.faultcode, ex.faultstring, wsEx.__class__.__name__)
      self._logManager.log(what=errMsg,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise wsEx
      
    except Exception, ex:
      # General exception.
      self._logManager.log(what="Web service client failed.\n%s " % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise DbsWsClientException(exception=ex)
    self._logManager.log(
      what="Created processed dataset with id %d." % processedDatasetId,
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)
    
    return processedDatasetId

  def insertEventCollections(self, processedDataset, eventCollectionList):
    """ Insert event collections for a given processed dataset. """

    funcName = "%s.%s" % (self.__class__.__name__, "insertEventCollections()")

    # Invoke web service call.
    try:
      processedDatasetName = processedDataset.getDatasetName()
      self._logManager.log(
	what="Inserting %s event collections for processed dataset: %s." % (
	len(eventCollectionList), processedDatasetName),
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      self._wsdlProxy.insertEventCollections(
	processedDataset=processedDataset.getWsRep(),
	eventCollectionList=eventCollectionList.getWsRep())

    except SOAPpy.faultType, ex:
      wsExClassName = DbsWsFaultMapper.getExceptionClassName(ex)
      exec "wsEx = %s(args=\"\"\"%s\"\"\")" % (wsExClassName, ex.faultstring)
      errMsg = "%s caught: %s (Will raise: %s)" % (
	ex.faultcode, ex.faultstring, wsEx.__class__.__name__)
      self._logManager.log(what=errMsg,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise wsEx
      
    except Exception, ex:
      # General exception.
      self._logManager.log(what="Web service client failed.\n%s " % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise DbsWsClientException(exception=ex)
    
    self._logManager.log(
      what="Successfully inserted %s event collections for processed dataset: %s." % (
      len(eventCollectionList), processedDatasetName),
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_INFO_)

    return

  def createFileBlock(self, processedDataset, fileBlock):
    """ Create a file block for a given processed dataset. """

    funcName = "%s.%s" % (self.__class__.__name__, "createFileBlock()")

    # Invoke web service call.
    try:
      self._logManager.log(
	what="Inserting file block: %s." % (fileBlock),
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      fileBlockId = self._wsdlProxy.createFileBlock(
	processedDataset=processedDataset.getWsRep(),
	fileBlock=fileBlock.getWsRep())[dbsFileBlock.FILE_BLOCK_ID_TAG_]

    except SOAPpy.faultType, ex:
      wsExClassName = DbsWsFaultMapper.getExceptionClassName(ex)
      exec "wsEx = %s(args=\"\"\"%s\"\"\")" % (wsExClassName, ex.faultstring)
      errMsg = "%s caught: %s (Will raise: %s)" % (
	ex.faultcode, ex.faultstring, wsEx.__class__.__name__)
      self._logManager.log(what=errMsg,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise wsEx
      
    except Exception, ex:
      # General exception.
      self._logManager.log(what="Web service client failed.\n%s " % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise DbsWsClientException(exception=ex)
    
    self._logManager.log(
      what="File block id: %s." % (fileBlockId),
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_INFO_)

    return fileBlockId
	
##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    logLevel = dbsLogManager.LOG_LEVEL_ALL_
    dbsLogManager.getInstance().setLogLevel(logLevel)

    #datasetPath = "bt03_B0sJPsiX/Hit/bt_Hit245_2_g133"
    datasetPath = "/eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC"
    wsClient = DbsWsClient(wsdlUrl="./DbsDatasetService.wsdl.xml")

    
    print "Getting dataset contents for: %s" % datasetPath
    fileBlockList = wsClient.getDatasetContents(datasetPath)
    print "Dataset contents for: %s" % datasetPath
    for fileBlock in fileBlockList:
      print ""
      print fileBlock
      print "File block name/id: %s/%s" % (fileBlock.getBlockName(),
					   fileBlock.getBlockId())
      for eventCollection in fileBlock.getEventCollectionList():
	print "  %s" % eventCollection

    print ""
    dataTierList = [ "Digi", "Hit" ]
    print "Getting dataset provenance for: %s (dataTiers: %s)" % (
      datasetPath, dataTierList)
    
    datasetParentList = wsClient.getDatasetProvenance(
      datasetPath, dataTierList)
    print "Dataset provenance for: %s (dataTiers: %s)" % (
      datasetPath, dataTierList)

    for datasetParent in datasetParentList:
      print "%s" % (datasetParent)


  except dbsException.DbsException, ex:  
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
