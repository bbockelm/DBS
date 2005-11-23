#!/usr/bin/env python
#
# $Id: dbsWsClient.py,v 1.1 2005/11/23 18:30:31 sveseli Exp $
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
      exec "wsEx = %s(args='%s')" % (wsExClassName, ex.faultstring)
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
      exec "wsEx = %s(args='%s')" % (wsExClassName, ex.faultstring)
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

##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    logLevel = dbsLogManager.LOG_LEVEL_ALL_
    dbsLogManager.getInstance().setLogLevel(logLevel)

    #datasetPath = "bt03_B0sJPsiX/Hit/bt_Hit245_2_g133"
    datasetPath = "eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC"
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
