#!/usr/bin/env python
#
# $Id: dbsCgiUtility.py,v 1.9 2006/01/24 18:57:10 sveseli Exp $
#
# Class which uses CGI utilities to extract info from the db.
#

import os
import string
import urllib2

import dbsException
import dbsStaticMethod
import dbsApi
import dbsDatasetContentsXmlParser
import dbsDatasetProvenanceXmlParser
import dbsLogManager
import dbsDataset

CGI_DBS_XML_DUMP_SCRIPT_ = "dbsxml"

DBS_STATUS_CODE_TAG_ = "Dbs-status-code"
DBS_STATUS_MESSAGE_TAG_ = "Dbs-status-message"
DBS_STATUS_DETAIL_TAG_ = "Dbs-status-detail"

# CGI status codes.
CGI_SUCCESS_CODE_ = 100
CGI_INVALID_DATA_TIER_CODE_ = 301
CGI_GENERIC_FAILURE_CODE_ = 400

##############################################################################
# DBS Cgi utility class exceptions.

class DbsCgiUtilityException(dbsException.DbsException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

class InvalidCgiScriptUrl(DbsCgiUtilityException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsCgiUtilityException.__init__(self, **kwargs)


class CgiToolError(DbsCgiUtilityException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsCgiUtilityException.__init__(self, **kwargs)

class InvalidXML(DbsCgiUtilityException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsCgiUtilityException.__init__(self, **kwargs)

class InvalidDatasetPathName(DbsCgiUtilityException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsCgiUtilityException.__init__(self, **kwargs)

class InvalidDataTier(DbsCgiUtilityException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsCgiUtilityException.__init__(self, **kwargs)

  
##############################################################################
# HTTP exception mapper.

class DbsCgiExceptionMapper:

  __exceptionMap = {
    CGI_INVALID_DATA_TIER_CODE_ : "InvalidDataTier",
    CGI_GENERIC_FAILURE_CODE_ : "CgiToolError",
    }

  def getExceptionClassName(errorCode):
    """ Map error code into an exception class name. """
    return DbsCgiExceptionMapper.__exceptionMap.get(
      errorCode, "CgiToolError")

  getExceptionClassName = dbsStaticMethod.DbsStaticMethod(getExceptionClassName)

  
##############################################################################
# CGI utility class.

class DbsCgiUtility:

  def __init__(self, cgiUrl=None):
    """ Constructor. """
    self._cgiUrl = cgiUrl
    self._logManager = dbsLogManager.getInstance()
    
  def setCgiUrl(self, cgiUrl):
    """ Set cgi script url. """
    self._cgiUrl = cgiUrl

  def getDatasetContents(self, datasetPathName):
    """ Retrieve event collections given the dataset path name string. """

    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetContents()")

    # Check path.
    try:
      dbsDataset.DbsDataset.verifyDatasetPathName(datasetPathName)
    except dbsDataset.InvalidDatasetPathName, ex:
      raise InvalidDatasetPathName(args="%s" % ex)
    
    # Construct cgi path.
    cgiUrl = "%s/%s?api=getDatasetContents&path=%s" % (
      self._cgiUrl, CGI_DBS_XML_DUMP_SCRIPT_, datasetPathName)

    # Invoke cgi script.
    try:
      self._logManager.log(
	what="Retrieving dataset contents, cgiUrl: %s." % cgiUrl,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      urlResult = urllib2.urlopen(cgiUrl)
      xmlString = urlResult.read() 
      dbsStatusCode = int(urlResult.headers.get(DBS_STATUS_CODE_TAG_))
      dbsStatusMessage = urlResult.headers.get(DBS_STATUS_MESSAGE_TAG_)
      dbsStatusDetail = urlResult.headers.get(DBS_STATUS_DETAIL_TAG_)
      self._logManager.log(
	what="CGI call completed, status code: %s (status message: '%s', status detail: '%s')." % (dbsStatusCode, dbsStatusMessage, dbsStatusDetail),
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      if dbsStatusCode != CGI_SUCCESS_CODE_:
	# Cgi failed for some reason, determine exception to be raised.
	cgiExClassName = DbsCgiExceptionMapper.getExceptionClassName(dbsStatusCode)
	cgiExArgs = "Status message: '%s', Status detail: '%s'" % (dbsStatusMessage, dbsStatusDetail)
	exec "cgiEx = %s(args=\"\"\"%s\"\"\")" % (cgiExClassName, cgiExArgs)
	errMsg = "CGI Error caught, will raise %s" % (cgiEx.__class__.__name__)
	self._logManager.log(what=errMsg,
			     where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise cgiEx
    except DbsCgiUtilityException, ex:
      raise
    except urllib2.URLError, ex:
      # Cgi failed for some reason, determine exception to be raised.
      cgiExClassName = DbsCgiExceptionMapper.getExceptionClassName(ex.code)
      exec "cgiEx = %s(exception=ex, args=\"\"\"%s (Input argument datasetPathName=\"%s\")\"\"\")" % (cgiExClassName, ex.read(), datasetPathName)
      errMsg = "URLError caught: \n%s\nWill raise %s" % (
	ex, cgiEx.__class__.__name__)
      self._logManager.log(what=errMsg,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise cgiEx
    except Exception, ex:
      # General exception.
      self._logManager.log(what="Cgi tool failed.\n%s " % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise CgiToolError(exception=ex)
    self._logManager.log(
      what="Retrieved dataset contents.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)

    # Parse the resulting xml output.
    self._logManager.log(
      what="Parsing XML CGI output string.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)
    try:
      datasetContentsParser = dbsDatasetContentsXmlParser.DbsDatasetContentsXmlParser(xmlString=xmlString)
      fileBlockList = datasetContentsParser.getFileBlockList()
    except Exception, ex:
      raise InvalidXML(exception=ex)
      
    return fileBlockList

  def getDatasetProvenance(self, datasetPathName, dataTierList):
    """ Retrieve dataset parents given the dataset path name string. """

    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetProvenance()")

    # Check path.
    try:
      dbsDataset.DbsDataset.verifyDatasetPathName(datasetPathName)
    except dbsDataset.InvalidDatasetPathName, ex:
      raise InvalidDatasetPathName(args="%s" % ex)
    
    # Construct cgi path.
    cgiUrl = "%s/%s?api=getDatasetProvenance&path=%s" % (
      self._cgiUrl, CGI_DBS_XML_DUMP_SCRIPT_, datasetPathName)
    if len(dataTierList):
      cgiUrl = "%s&datatier=%s" % (cgiUrl, string.join(dataTierList, ","))
    
    # Invoke cgi script.
    try:
      self._logManager.log(
	what="Retrieving dataset provenance, cgiUrl: %s." % cgiUrl,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      urlResult = urllib2.urlopen(cgiUrl)
      xmlString = urlResult.read() 
      dbsStatusCode = int(urlResult.headers.get(DBS_STATUS_CODE_TAG_))
      dbsStatusMessage = urlResult.headers.get(DBS_STATUS_MESSAGE_TAG_)
      dbsStatusDetail = urlResult.headers.get(DBS_STATUS_DETAIL_TAG_)
      self._logManager.log(
	what="CGI call completed, status code: %s (status message: '%s', status detail: '%s')." % (dbsStatusCode, dbsStatusMessage, dbsStatusDetail),
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_INFO_)
      if dbsStatusCode != CGI_SUCCESS_CODE_:
	# Cgi failed for some reason, determine exception to be raised.
	cgiExClassName = DbsCgiExceptionMapper.getExceptionClassName(dbsStatusCode)
	cgiExArgs = "Status message: '%s', Status detail: '%s'" % (dbsStatusMessage, dbsStatusDetail)
	exec "cgiEx = %s(args=\"\"\"%s\"\"\")" % (cgiExClassName, cgiExArgs)
	errMsg = "CGI Error caught, will raise %s" % (cgiEx.__class__.__name__)
	self._logManager.log(what=errMsg,
			     where=funcName,
			     logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
	raise cgiEx
    except DbsCgiUtilityException, ex:
      raise
    except urllib2.URLError, ex:
      # Cgi failed for some reason, determine exception to be raised.
      cgiExClassName = DbsCgiExceptionMapper.getExceptionClassName(ex.code)
      exec "cgiEx = %s(exception=ex, args=\"\"\"%s (Input argument datasetPathName=\"%s\")\"\"\")" % (cgiExClassName, ex.read(), datasetPathName)
      errMsg = "URLError caught: \n%s\nWill raise %s" % (
	ex, cgiEx.__class__.__name__)
      self._logManager.log(what=errMsg,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise cgiEx
    except Exception, ex:
      # General exception.
      self._logManager.log(what="Cgi tool failed.\n%s " % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise CgiToolError(exception=ex)
    self._logManager.log(
      what="Retrieved dataset provenance.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)

    
    # Parse the resulting xml output.
    self._logManager.log(
      what="Parsing XML CGI output string.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)

    try:
      datasetProvenanceParser = dbsDatasetProvenanceXmlParser.DbsDatasetProvenanceXmlParser(xmlString=xmlString)
      datasetParentList = datasetProvenanceParser.getDatasetParentList()
    except Exception, ex:
      raise InvalidXML(exception=ex)
      
    return datasetParentList

##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    logLevel = dbsLogManager.LOG_LEVEL_ALL_
    dbsLogManager.getInstance().setLogLevel(logLevel)

    datasetPath = "bt03_B0sJPsiX/Hit/bt_Hit245_2_g133"
    cgiUtility = DbsCgiUtility(cgiUrl="http://cern.ch/cms-dbs/cgi-bin")

    
    print "Getting dataset contents for: %s" % datasetPath
    fileBlockList = cgiUtility.getDatasetContents(datasetPath)
    print "Dataset contents for: %s" % datasetPath
    for fileBlock in fileBlockList:
      print ""
      print "File block name/id: %s/%s" % (fileBlock.getBlockName(),
					   fileBlock.getBlockId())
      for eventCollection in fileBlock.getEventCollectionList():
	print "  %s" % eventCollection

    print ""
    dataTierList = [ "Digi", "Hit" ]
    print "Getting dataset provenance for: %s (dataTiers: %s)" % (
      datasetPath, dataTierList)
    
    datasetParentList = cgiUtility.getDatasetProvenance(
      datasetPath, dataTierList)
    print "Dataset provenance for: %s (dataTiers: %s)" % (
      datasetPath, dataTierList)

    for datasetParent in datasetParentList:
      print "%s" % (datasetParent)


  except dbsException.DbsException, ex:  
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
