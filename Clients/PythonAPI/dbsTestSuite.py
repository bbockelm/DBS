#!/usr/bin/env python
#
# $Id: dbsTestSuite.py,v 1.1 2005/11/08 21:46:54 sveseli Exp $
#
# DBS Test Suite class.
#

import string
import unittest

import dbsObjectInfo
import dbsObjectFactory
import dbsUnitTest
import dbsLogManager
import dbsException


##############################################################################
# Exception classes.

class DbsTestSuiteException(dbsException.DbsException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

##############################################################################
# Test suite class.

class DbsTestSuite:

  def __init__(self):
    """ Constructor. """
    self._apiObjectInfo = None
    self._testObjectInfoList = []
    self._testSuite = unittest.TestSuite()
    self._logManager = dbsLogManager.getInstance()

  def setApiObjectInfo(self, apiObjectInfo):
    """ Set api object info. """
    self._apiObjectInfo = apiObjectInfo

  def getApiObjectInfo(self):
    """ Get api object info. """
    return self._apiObjectInfo

  def addTestObjectInfo(self, testObjectInfo):
    """ Add test. """
    self._testObjectInfoList.append(testObjectInfo)

  def getTestObjectInfoList(self):
    """ Set api object info. """
    return self._testObjectInfoList

  def getTestSuite(self):
    """ Reterun test suite object. """
    return self._testSuite
  
  def setUp(self):
    """ Prepare for running tests. """
    funcName = "%s.%s" % (self.__class__.__name__, "setUp()")
    apiObjectInfo = self.getApiObjectInfo()
    try:
      self._logManager.log(
	what="Setting up api: %s" % (apiObjectInfo),
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      dbsUnitTest.DbsUnitTest.setApi(
	apiClassName=apiObjectInfo.getClassName(),
	apiClassInitArgsString=apiObjectInfo.getArgumentsString())

      self._logManager.log(
	what="Creating test suite",
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      for testObjectInfo in self.getTestObjectInfoList():
	self._logManager.log(
	  what="Adding test: %s" % testObjectInfo,
	  where=funcName,
	  logLevel=dbsLogManager.LOG_LEVEL_INFO_)
	testObject = dbsObjectFactory.DbsObjectFactory.createObject(
	  testObjectInfo)
	self._testSuite.addTest(testObject)


    except dbsException.DbsException, ex:
      self._logManager.log(
	what="Could not prepare tests: %s" % (ex),
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)


##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    testSuite = DbsTestSuite()
    testSuite.setApiObjectInfo(dbsObjectInfo.DbsObjectInfo("mymodule.myclass", "myarg=77"))
    print "Got api object: ", testSuite.getApiObjectInfo()
    
  except dbsException.DbsException, ex:  
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
