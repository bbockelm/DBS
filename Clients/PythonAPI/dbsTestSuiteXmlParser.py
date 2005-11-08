#!/usr/bin/env python
#
# $Id: dbsDatasetContentsXmlParser.py,v 1.3 2005/10/28 15:39:09 sveseli Exp $
#
# Class which parses dataset contents xml file.
#

import string
import dbsTestSuite
import dbsObjectInfo
import dbsLogManager
import dbsXmlParser
import dbsException

API_TAG_ = "api"
TEST_TAG_ = "test"

CLASS_NAME_ATTR_ = "className"
ARGUMENTS_STRING_ATTR_ = "argumentsString"


##############################################################################
# Exception classes.

class DbsTestSuiteXmlParserException(dbsXmlParser.DbsXmlParserException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsXmlParser.DbsXmlParserException.__init__(self, **kwargs)

  
##############################################################################
# Parser class.

class DbsTestSuiteXmlParser(dbsXmlParser.DbsXmlParser):

  def __init__(self, xmlString=None, xmlFile=None):
    """ Constructor. """
    dbsXmlParser.DbsXmlParser.__init__(self, xmlString=xmlString,
				       xmlFile=xmlFile)

  def parseDocument(self):
    """ Implementation of the abstract base class method. """
    return self.getTestSuite()

  def getTestSuite(self):
    """
    Process the document tree, return the DBS Test Suite object.
    """
    funcName = "%s.%s" % (self.__class__.__name__, "getTestSuite()")
    logManager = dbsLogManager.getInstance()

    testSuite = dbsTestSuite.DbsTestSuite()

    # Get api info.
    apiNode = self.getDom().getElementsByTagName(API_TAG_)[0]
    apiClassName = apiNode.getAttribute(CLASS_NAME_ATTR_)
    apiClassInitString = apiNode.getAttribute(ARGUMENTS_STRING_ATTR_)

    logManager.log(
      what="Will use API: %s(%s)" % (apiClassName, apiClassInitString),
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_INFO_)
    testSuite.setApiObjectInfo(
      dbsObjectInfo.DbsObjectInfo(className=apiClassName,
				  argumentsString=apiClassInitString))

    # Get list of all tests.
    testList = []
    testNodeList = self.getDom().getElementsByTagName(TEST_TAG_)
    for testNode in testNodeList:
      try:
	testClassName = testNode.getAttribute(CLASS_NAME_ATTR_)
	testClassInitString = testNode.getAttribute(ARGUMENTS_STRING_ATTR_)

	logManager.log(
	  what="Found test: %s(%s)" % (testClassName, testClassInitString),
	  where=funcName,
	  logLevel=dbsLogManager.LOG_LEVEL_INFO_)

	testSuite.addTestObjectInfo(
	  dbsObjectInfo.DbsObjectInfo(className=testClassName,
				      argumentsString=testClassInitString))

      except Exception, ex:
	logManager.log(what="Parsing XML file failed: %s" % ex,
		       where=funcName,
		       logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise DbsTestSuiteXmlParserException(exception=ex)

    return testSuite

##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    parser = DbsTestSuiteXmlParser(xmlFile="test_suite.xml")
    ts = parser.getTestSuite()
    print ts.getApiObjectInfo()
    print ts.getTestObjectInfoList()
  except dbsException.DbsException, ex:  
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
