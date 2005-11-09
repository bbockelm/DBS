#!/usr/bin/env python
#
# $Id: dbsUnitTest.py,v 1.1 2005/11/08 21:46:54 sveseli Exp $
#
# Base class for DBS unit tests.
#

import string
import unittest

import dbsUtility
import dbsException
import dbsLogManager


##############################################################################
# Exception classes.

class DbsUnitTestException(dbsException.DbsException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

class DbsApiNotInitialized(DbsUnitTestException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsUnitTestException.__init__(self, **kwargs)

class DbsApiAlreadyInitialized(DbsUnitTestException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsUnitTestException.__init__(self, **kwargs)

class DbsApiImportError(DbsUnitTestException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsUnitTestException.__init__(self, **kwargs)

##############################################################################
# Base DBS unit test class.

class DbsUnitTest(unittest.TestCase):

  # Api object should be initialized once. All subsequent attempts
  # to re-set it will result in an exception.
  __api = None
  __apiModule = None
  __logManager = dbsLogManager.getInstance()
  
  # Methods which manipulate static objects.
  def setApi(apiClassName=None,
	     apiClassInitArgsString=""):
    """ Set API class instance. """
    funcName = "%s.%s" % ("DbsUnitTest", "setApi()")
    if DbsUnitTest.__api is not None:
      errMsg = "Dbs Api has been initialized already."
      DbsUnitTest.__logManager.log(what=errMsg,
				   where=funcName,
				   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise DbsApiAlreadyInitialized(args=errMsg)

    try:
      apiStrList = None
      if apiClassName != None:
	apiStrList = string.split(str(apiClassName), ".")
      if apiClassName is None or len(apiStrList) < 2:
	errMsg = "Invalid argument apiClassName: it should be a string of the form dbsApiModule.DbsApiClass."
	DbsUnitTest.__logManager.log(what=errMsg,
				     where=funcName,
				     logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise dbsException.InvalidArgument(args=errMsg)


      apiModuleName = apiStrList[0]
      apiClassName = string.join(apiStrList[1:], ".")
      importCmd = "import %s as dbsApiModule; api = dbsApiModule.%s(%s)" % (
	apiModuleName, apiClassName, apiClassInitArgsString)

      DbsUnitTest.__logManager.log(what="Executing: %s" % importCmd,
				   where=funcName,
				   logLevel=dbsLogManager.LOG_LEVEL_INFO_)

      exec importCmd
      DbsUnitTest.__api = api
      DbsUnitTest.__apiModule = dbsApiModule
    except Exception, ex:
      DbsUnitTest.__logManager.log(
        what="Could not import api class: %s" % (ex),
        where=funcName,
        logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise DbsApiImportError(exception=ex)

  # Static method for setting api.
  setApi = dbsUtility.StaticMethod(setApi)

  def getApi():
    """ Get API class instance. """
    funcName = "%s.%s" % ("DbsUnitTest", "getApi()")
    if DbsUnitTest.__api is None:
      errMsg = "Dbs Api has not been initialized."
      DbsUnitTest.__logManager.log(what=errMsg,
				   where=funcName,
				   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise DbsApiNotInitialized(args=errMsg)
    return DbsUnitTest.__api

  # Static method for getting api.
  getApi = dbsUtility.StaticMethod(getApi)

  def getApiModule():
    """ Get API class instance. """
    funcName = "%s.%s" % ("DbsUnitTest", "getApiModule()")
    if DbsUnitTest.__api is None:
      errMsg = "Dbs Api has not been initialized."
      DbsUnitTest.__logManager.log(what=errMsg,
				   where=funcName,
				   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise DbsApiNotInitialized(args=errMsg)
    return DbsUnitTest.__apiModule

  # Static method for getting api module.
  getApiModule = dbsUtility.StaticMethod(getApiModule)

  def getLogManager():
    """ Get log manager instance. """
    return DbsUnitTest.__logManager

  # Static method for getting api module.
  getLogManager = dbsUtility.StaticMethod(getLogManager)

  def __init__(self, *args):
    """ Constructor. """
    unittest.TestCase.__init__(self, *args)
  
  def runTest(self, result=None):
    """ Method needed by unittest.TestCase. """
    raise dbsException.MethodNotImplemented(args="This method should be overridden in the derived unit test class.")


##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    unitTest = DbsUnitTest()
    DbsUnitTest.setApi(
      apiClassName="dbsCgiApi.DbsCgiApi",
      apiClassInitArgsString="cgiUrl='http://cern.ch/cms-dbs/cgi-bin'")
    api = DbsUnitTest.getApi()
    apiModule = DbsUnitTest.getApiModule()
    print "API module: ", apiModule
    datasetPath = "eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC"
    print "Getting dataset contents for: %s" % datasetPath
    fileBlockList = api.getDatasetContents(datasetPath)
    print "Dataset contents for: %s" % datasetPath
    for fileBlock in fileBlockList:
      print ""
      print "File block name/id: %s/%s" % (fileBlock.getBlockName(),
					   fileBlock.getBlockId())
      for eventCollection in fileBlock.getEventCollectionList():
	print "  %s" % eventCollection

  except dbsException.DbsException, ex:  
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
