#!/usr/bin/env python
#
# $Id: dbsTestSuiteRunner.py,v 1.1 2005/11/08 21:46:54 sveseli Exp $
#
# Base class for DBS unit tests.
#

import string
import unittest

import dbsUnitTest
import dbsLogManager
import dbsException
import dbsTestSuiteXmlParser

##############################################################################
# Exception classes.

class DbsTestSuiteRunnerException(dbsException.DbsException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

##############################################################################
# Unit tests for getDatasetContents api call.

class DbsTestSuiteRunner(dbsUnitTest.DbsUnitTest):

  def __init__(self, testConfigXmlFile):
    """ Constructor. """
    # Parse the config file.
    funcName = "%s.%s" % (self.__class__.__name__, "__init__()")
    self._logManager = dbsLogManager.getInstance()
    self._logManager.log(
      what="Using test config file: %s" % (testConfigXmlFile),
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_INFO_)
    try:
      parser = dbsTestSuiteXmlParser.DbsTestSuiteXmlParser(
	xmlFile=testConfigXmlFile)
      self._testSuite = parser.getTestSuite()
    except dbsException.DbsException, ex:
      self._logManager.log(what="Invalid test config XML file: %s" % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise DbsTestSuiteRunnerException(exception=ex)

  def setUp(self):
    """ Prepare for running tests. """
    self._testSuite.setUp()

  def runTests(self, verbosityLevel=1):
    """ Run tests. """
    funcName = "%s.%s" % (self.__class__.__name__, "__runTests__()")
    self._logManager.log(
      what="Starting tests.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_INFO_)
    unittest.TextTestRunner(verbosity=verbosityLevel).run(self._testSuite.getTestSuite())
    self._logManager.log(
      what="Tests complete.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_INFO_)
    
##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    testRunner = DbsTestSuiteRunner("test_suite.xml")
    testRunner.setUp()
    testRunner.runTests()


  except dbsException.DbsException, ex:  
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
