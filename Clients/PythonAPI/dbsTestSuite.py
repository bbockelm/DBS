#!/usr/bin/env python
#
# $Id: dbsTestSuite.py,v 1.2 2005/10/27 19:47:46 sveseli Exp $
#
# DBS Test Suite class.
#

import string
import unittest

import dbsObjectInfo
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
