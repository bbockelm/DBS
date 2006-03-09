#!/usr/bin/env python
#
# $Id: dbsCgiUtility.py,v 1.12 2006/03/08 13:49:43 lat Exp $
#
# Class which uses CGI utilities to extract info from the db.
#

import os
import string
import urllib2
from xml.dom.minidom import parseString
from xml.parsers.expat import ExpatError

import dbsException
import dbsStaticMethod
import dbsApi
import dbsLogManager
from dbsDataset import *
from dbsFileBlock import *
from dbsEventCollection import *
from dbsLogManager import *

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

class DbsXmlParserException(dbsException.DbsException):
  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

class InvalidInputXmlFile(DbsXmlParserException):
  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsXmlParserException.__init__(self, **kwargs)

class InvalidXmlInput(DbsXmlParserException):
  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsXmlParserException.__init__(self, **kwargs)

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
# Utility functions.

def dbsCgiError (log, code, message, reason, context, chain):
  exclass = DbsCgiExceptionMapper.getExceptionClassName (code)
  log.log ("Raising %s due to %s" % (exclass, reason), context, LOG_LEVEL_ERROR_)
  exec "raise %s (exception = chain, args = \"\"\"%s\"\"\")" % (exclass, message)

def dbsCgiCall (context, log, url):
  try:
    # Announce intent
    log.log("Starting CGI call to %s" % url, LOG_LEVEL_INFO_)

    # Fetch result from the CGI server
    result = urllib2.urlopen (url)
    data = result.read ();
    statusCode = int(result.headers.get ('Dbs-status-code'))
    statusMessage = result.headers.get ('Dbs-status-message')
    statusDetail = result.headers.get ('Dbs-status-detail')

    # Log result
    log.log("CGI call completed, status code: %s (status message: '%s', status detail: '%s')."
	% (statusCode, statusMessage, statusDetail), context, LOG_LEVEL_INFO_)

    # If there was a server-side error, raise an appropriate exception
    if statusCode != 100:
      exmsg = "Status message: '%s', Status detail: '%s'" % (statusMessage, statusDetail)
      dbsCgiError (log, statusCode, exmsg, "server error", context, None)

    # All is ok, return the data
    return data

  except urllib2.URLError, ex:
    # URL access failed, raise an exception
    dbsCgiError (log, ex.code, ex, "URL access error", context, ex)

def dbsCgiXmlParse (context, log, xmlString):
  """ Constructor. """
  if xmlString == None:
    errmsg = "Tried to parse non-existent XML"
    log.log (errmsg, context, LOG_LEVEL_ERROR_)
    raise InvalidXmlInput(args=errmsg)

  try:
    dom = parseString(xmlString)
    log.log("XML parsed successfully", context, LOG_LEVEL_DEBUG_)
    return dom
  except ExpatError, ex:
    log.log("XML failed to parse: %s (input %s)" % (ex, xmlString), context, LOG_LEVEL_ERROR_)
    raise InvalidXmlInput(exception=ex)
  except Exception, ex:
    log.log("XML failed to parse: %s" % ex, context, LOG_LEVEL_ERROR_)
    raise DbsXmlParserException(exception=ex)

##############################################################################
# CGI utility class.

class DbsCgiUtility:
  def __init__(self, cgiUrl=None):
    """ Constructor. """
    self._cgiUrl = cgiUrl
    if cgiUrl is None:
      self._cgiUrl = "http://cern.ch/cms-dbs/cgi-bin/dbsxml"
      self._cgiUrl = "http://cmsdoc.cern.ch/cms/aprom/DBS/CGIServer/dbsxml"
    self._log = dbsLogManager.getInstance()
    
  def setCgiUrl(self, cgiUrl):
    """ Set cgi script url. """
    self._cgiUrl = cgiUrl

  def listDatasets(self, pattern):
    """ Retrieve datasets matching a pattern string. """
    funcName = "%s.%s" % (self.__class__.__name__, "listDatasets()")

    # Invoke cgi script.
    url = "%s?api=listDatasets&pattern=%s" % (self._cgiUrl, pattern)
    data = dbsCgiCall (funcName, self._log, url)

    # Parse the resulting xml output.
    self._log.log("Parsing CGI result", funcName, LOG_LEVEL_DEBUG_)
    try:
      datasetList = []
      dom = dbsCgiXmlParse (funcName, self._log, data);
      for element in dom.getElementsByTagName ('processed-dataset'):
	path = element.getAttribute('path').encode ('ascii')
	datasetList.append (DbsDataset (datasetPath = path))
      return datasetList
    except Exception, ex:
      raise InvalidXML(exception=ex)

  def getDatasetContents(self, datasetPathName):
    """ Retrieve event collections given the dataset path name string. """
    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetContents()")

    # Check path.
    DbsDataset.verifyDatasetPathName(datasetPathName)

    # Invoke cgi script.
    url = "%s?api=getDatasetContents&path=%s" % (self._cgiUrl, datasetPathName)
    data = dbsCgiCall (funcName, self._log, url)

    # Parse the resulting xml output.  The output consits of a list of blocks,
    # each with its list of event collections.
    self._log.log("Parsing CGI result", funcName, LOG_LEVEL_DEBUG_)
    try:
      fileBlockMap = {}
      dom = dbsCgiXmlParse (funcName, self._log, data)
      # Parse blocks
      for element in dom.getElementsByTagName ('block'):
	name = element.getAttribute('name').encode('ascii')
	id = int(element.getAttribute('id').encode('ascii'))
        if not fileBlockMap.has_key (id):
	  fileBlockMap [id] = DbsFileBlock (blockName = name, blockId = id)
	self._log.log ("Found file block %s (id %d)" % (name, id), funcName, LOG_LEVEL_DEBUG_)

      # Parse event collections and associated with blocks
      for element in dom.getElementsByTagName ('event-collection'):
	name = element.getAttribute ('name').encode ('ascii')
	events = int(element.getAttribute ('events'))
	inblock = int(element.parentNode.getAttribute ('id'))
	fileBlockMap [inblock].addEventCollection (
	  DbsEventCollection (collectionName = name, numberOfEvents = events))

      return fileBlockMap.values ()
    except Exception, ex:
      raise InvalidXML(exception=ex)

  def getDatasetProvenance(self, datasetPathName, dataTierList):
    """ Retrieve dataset parents given the dataset path name string. """
    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetProvenance()")

    # Check path.
    DbsDataset.verifyDatasetPathName(datasetPathName)

    # Invoke cgi script.
    url = "%s?api=getDatasetProvenance&path=%s" % (self._cgiUrl, datasetPathName)
    if len(dataTierList):
      url = "%s&datatier=%s" % (url, string.join(dataTierList, ","))
    data = dbsCgiCall (funcName, self._log, url)

    # Parse the resulting xml output.
    self._log.log("Parsing CGI result", funcName, LOG_LEVEL_DEBUG_)
    try:
      parentList = []
      dom = dbsCgiXmlParse (funcName, self._log, data)
      for element in dom.getElementsByTagName ('parent'):
	path = element.getAttribute('path').encode('ascii')
	tier = element.getAttribute('tier').encode('ascii')
	type = element.getAttribute('type').encode('ascii')
	parentList.append (DbsDataset (datasetPath = path,
				       dataTier = tier,
				       dataType = type))
      return parentList
    except Exception, ex:
      raise InvalidXML(exception=ex)

##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    logLevel = LOG_LEVEL_ALL_
    dbsLogManager.getInstance().setLogLevel(logLevel)

    datasetPath = "/bt03_B0sJPsiX/Hit/bt_Hit245_2_g133"
    cgiUtility = DbsCgiUtility()

    
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
