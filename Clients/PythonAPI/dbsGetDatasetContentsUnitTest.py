#!/usr/bin/env python
#
# $Id: dbsGetDatasetContentsUnitTest.py,v 1.1 2005/11/08 21:46:54 sveseli Exp $
#
# Base class for DBS unit tests.
#

import string
import unittest

import dbsUnitTest
import dbsLogManager
import dbsException


##############################################################################
# Exception classes.

class DbsGetDatasetContentsUnitTestException(dbsUnitTest.DbsUnitTestException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsUnitTest.DbsUnitTestException.__init__(self, **kwargs)

##############################################################################
# Unit tests for getDatasetContents api call.

class DbsGetDatasetContentsUnitTest(dbsUnitTest.DbsUnitTest):

  def __init__(self, datasetPathName=None, *args):
    """
    Constructor.
    """
    dbsUnitTest.DbsUnitTest.__init__(self, *args)
    self._datasetPathName = datasetPathName

  def runTest(self, *args):
    return self.testGetDatasetContents(*args)
  
  def testGetDatasetContents(self, *args):
    funcName = "%s.%s" % (self.__class__.__name__, "testGetDatasetContents()")
    api = self.getApi()
    fileBlockList = api.getDatasetContents(self._datasetPathName)
    self.getLogManager().log(what="len(fileBlockList)=%s" % len(fileBlockList),
			     where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)
    self.failIf(len(fileBlockList) != 1)
    self.getLogManager().log(what="len(fileBlockList[0].getEventCollectionList())=%s"
		             % len(fileBlockList[0].getEventCollectionList()),
			     where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_INFO_)
    self.failIf(len(fileBlockList[0].getEventCollectionList()) != 1972)


class DbsGetDatasetContentsInvalidDatasetPathNameUnitTest(dbsUnitTest.DbsUnitTest):

  def __init__(self, datasetPathName=None, *args):
    """
    Constructor.
    """
    dbsUnitTest.DbsUnitTest.__init__(self, *args)
    self._datasetPathName = datasetPathName

  def runTest(self, *args):
    funcName = "%s.%s" % (self.__class__.__name__, "runTest()")
    apiModule = self.getApiModule()
    try:
      result = self.getDatasetContents(*args)
      self.fail()
    except apiModule.InvalidDatasetPathName, ex:
      # Test worked.
      self.getLogManager().log(
	what="Exception test successful, caught InvalidDatasetPathName",
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      
  def getDatasetContents(self, *args):
    api = self.getApi()
    return api.getDatasetContents(self._datasetPathName)


##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    test = DbsGetDatasetContentsUnitTest(datasetPathName="/eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC")
    test2 = DbsGetDatasetContentsUnitTest(datasetPathName="test_will_fail")
    test3 = DbsGetDatasetContentsInvalidDatasetPathNameUnitTest(datasetPathName="non_existing_dataset")
    test.setApi(
      apiClassName="dbsCgiApi.DbsCgiApi",
      apiClassInitArgsString="cgiUrl='http://cmsdoc.cern.ch/cms/aprom/DBS/CGIServer'")


    suite = unittest.makeSuite(
      DbsGetDatasetContentsUnitTest, "mytest")
    suite.addTest(test)
    suite.addTest(test2)
    suite.addTest(test3)
    unittest.TextTestRunner(verbosity=5).run(suite)


  except dbsException.DbsException, ex:  
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
