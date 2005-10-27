#!/usr/bin/env python
#
# $Id: dbsPhedexUtility.py,v 1.1 2005/10/21 22:50:51 lat Exp $
#
# Class which uses PHEDEX utilities to extract info from the db.
#

import os
import string

import dbsException
import dbsUtility
import dbsApi
import dbsDatasetContentsXmlParser
import dbsDatasetProvenanceXmlParser
import dbsLogManager

PHEDEX_DIR_ENV_VAR_ = "PHEDEX_DIR"
PHEDEX_DB_CONNECT_FILE_ENV_VAR_ = "PHEDEX_DB_CONNECT_FILE"
PHEDEX_DBS_XML_DUMP_SCRIPT_ = "Utilities/DBSXMLDump"

##############################################################################
# DBS Phedex utility class exceptions.

class DbsPhedexUtilityException(dbsException.DbsException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

class InvalidPhedexScriptPath(DbsPhedexUtilityException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsPhedexUtilityException.__init__(self, **kwargs)

class InvalidPhedexDbConnectionFile(DbsPhedexUtilityException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsPhedexUtilityException.__init__(self, **kwargs)

class InvalidPhedexDbSectionString(DbsPhedexUtilityException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsPhedexUtilityException.__init__(self, **kwargs)

class PhedexToolError(DbsPhedexUtilityException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsPhedexUtilityException.__init__(self, **kwargs)

class InvalidXML(DbsPhedexUtilityException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsPhedexUtilityException.__init__(self, **kwargs)

  
##############################################################################
# Phedex utility class.

class DbsPhedexUtility:

  def __init__(self, phedexDir=None, phedexDbConnectFile = None,
	       phedexDbSectionString = None):
    """ Constructor. """
    self._phedexDir = phedexDir
    self._phedexDbConnectFile = phedexDbConnectFile
    self._phedexDbSectionString = phedexDbSectionString
    self._logManager = dbsLogManager.getInstance()
    
  def setPhedexDir(self, phedexDir):
    """ Set phedex directory. """
    self._phedexDir = phedexDir

  def setPhedexDbConnectFile(self, phedexDbConnectFile):
    """ Set phedex db connect file. """
    self._phedexDbConnectFile = phedexDbConnectFile

  def setPhedexDbSectionString(self, phedexDbSectionString):
    """ Set phedex db connect file. """
    self._phedexDbSectionString = phedexDbSectionString

  def getPhedexScriptPath(self, script):
    """ Construct the script path and make sure it is valid. """
    funcName = "%s.%s" % (self.__class__.__name__, "getPhedexScriptPath()")
    self._logManager.log(what="Getting Phedex script path.",
			 where=funcName,
			 logLevel=dbsLogManager.LOG_LEVEL_INFO_)
    scriptPath = "%s" % (script)
    if self._phedexDir != None:
      scriptPath = "%s/%s" % (self._phedexDir, script)
    try:
      os.stat(scriptPath)
      self._logManager.log(what="Phedex script path: %s." % scriptPath,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)
      return scriptPath
    except OSError, ex:
      self._logManager.log(what="Invalid phedex script path: %s." % ex,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise InvalidPhedexScriptPath(exception=ex)

  def getPhedexDbConnectFile(self):
    """ Construct the db connect file path and make sure it is valid. """

    funcName = "%s.%s" % (self.__class__.__name__, "getPhedexDbConnectFile()")
    self._logManager.log(what="Checking db connection file.",
			 where=funcName,
			 logLevel=dbsLogManager.LOG_LEVEL_INFO_)
    if self._phedexDbConnectFile == None:
      errMsg = "No db connection file provided."
      self._logManager.log(
	what=errMsg,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise InvalidPhedexDbConnectionFile(args=errMsg)

    try:
      os.stat(self._phedexDbConnectFile)
      self._logManager.log(
	what="DB connection file %s ok." % self._phedexDbConnectFile,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)
      return self._phedexDbConnectFile
    except OSError, ex:
      self._logManager.log(
	what="Invalid DB connection file: %s" % ex,
	where=funcName,
	logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise InvalidPhedexDbConnectionFile(exception=ex)

  def getPhedexDbSectionString(self):
    """ Make sure connect string is valid. """
    funcName = "%s.%s" % (self.__class__.__name__, "getPhedexDbSectionString()")
    if self._phedexDbSectionString == None:
      errMsg = "No db section string provided."
      self._logManager.log(errMsg,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise InvalidPhedexDbSectionString(args=errMsg)

    self._logManager.log(
      what="DB section string: %s." % self._phedexDbSectionString,
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)
    return self._phedexDbSectionString
      

  def getDatasetContents(self, datasetPathName):
    """ Retrieve event collections given the dataset path name string. """

    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetContents()")
    
    # Construct executable path.
    scriptPath = self.getPhedexScriptPath(PHEDEX_DBS_XML_DUMP_SCRIPT_)

    # Check connection file.
    dbConnectFile = self.getPhedexDbConnectFile()


    # Check db section.
    dbSectionString = self.getPhedexDbSectionString()

    # Get the temporary xml file.
    xmlOutputFile = dbsUtility.DbsCreateTmpFileName("/tmp/tmpFile", "xml")
    
    # Construct and execute the command.
    cmd = "%s -from DBS -db %s:%s -getDatasetContents -to %s '%s'" % (
      scriptPath, dbConnectFile, dbSectionString, xmlOutputFile,
      datasetPathName)
    self._logManager.log(
      what="Retrieving dataset contents.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_INFO_)
    subprocessInfo = dbsUtility.DbsExecSubprocess(cmd)
    if subprocessInfo.getExitStatus():
      # Phedex failed for some reason, raise exception.
      self._logManager.log(what="Phedex tool failed.\n%s " % subprocessInfo,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise PhedexToolError(args=subprocessInfo.getStdErr())
    self._logManager.log(
      what="Retrieved dataset contents.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)

    
    # Parse the resulting xml file.
    self._logManager.log(
      what="Parsing XML file.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)
    try:
      datasetContentsParser = dbsDatasetContentsXmlParser.DbsDatasetContentsXmlParser(xmlFile=xmlOutputFile)
      fileBlockList = datasetContentsParser.getFileBlockList()
    except Exception, ex:
      raise InvalidXML(exception=ex)
      
    # Remove temporary file.
    os.remove(xmlOutputFile)
    
    return fileBlockList

  def getDatasetProvenance(self, datasetPathName, dataTierList):
    """ Retrieve dataset parents given the dataset path name string. """

    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetProvenance()")
    
    # Construct executable path.
    scriptPath = self.getPhedexScriptPath(PHEDEX_DBS_XML_DUMP_SCRIPT_)

    # Check connection file.
    dbConnectFile = self.getPhedexDbConnectFile()

    # Check db section.
    dbSectionString = self.getPhedexDbSectionString()

    # Get the temporary xml file.
    xmlOutputFile = dbsUtility.DbsCreateTmpFileName("/tmp/tmpFile", "xml")
    
    # Construct and execute the command.
    cmd = "%s -from DBS -db %s:%s -datatier %s -getDatasetProvenance -to %s '%s'" % (
      scriptPath, dbConnectFile, dbSectionString,
      string.join(dataTierList, ","), xmlOutputFile,
      datasetPathName)
    self._logManager.log(
      what="Retrieving dataset provenance. Executing: %s" % cmd,
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_INFO_)
    subprocessInfo = dbsUtility.DbsExecSubprocess(cmd)
    if subprocessInfo.getExitStatus():
      # Phedex failed for some reason, raise exception.
      self._logManager.log(what="Phedex tool failed.\n%s " % subprocessInfo,
			   where=funcName,
			   logLevel=dbsLogManager.LOG_LEVEL_ERROR_)
      raise PhedexToolError(args=subprocessInfo.getStdErr())
    self._logManager.log(
      what="Retrieved dataset provenance.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)

    
    # Parse the resulting xml file.
    self._logManager.log(
      what="Parsing XML file.",
      where=funcName,
      logLevel=dbsLogManager.LOG_LEVEL_DEBUG_)
    try:
      datasetProvenanceParser = dbsDatasetProvenanceXmlParser.DbsDatasetProvenanceXmlParser(xmlFile=xmlOutputFile)
      datasetParentList = datasetProvenanceParser.getDatasetParentList()
    except Exception, ex:
      raise InvalidXML(exception=ex)
      
    # Remove temporary file.
    os.remove(xmlOutputFile)
    
    return datasetParentList

##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    logLevel = dbsLogManager.LOG_LEVEL_ALL_
    dbsLogManager.getInstance().setLogLevel(logLevel)

    datasetPath = "bt_DST8713_2x1033PU_g133_CMS/bt03_wtb_2tauj"
    phedexUtility = DbsPhedexUtility(
      phedexDir="/home/veseli/work/dbs/PHEDEX",
      phedexDbSectionString="Production/Admin",
      phedexDbConnectFile="/home/veseli/work/dbs/db_keys.out")

    
    print "Getting dataset contents for: %s" % datasetPath
    fileBlockList = phedexUtility.getDatasetContents(datasetPath)
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
    
    datasetParentList = phedexUtility.getDatasetProvenance(
      datasetPath, dataTierList)
    print "Dataset provenance for: %s (dataTiers: %s)" % (
      datasetPath, dataTierList)

    for datasetParent in datasetParentList:
      print "%s" % (datasetParent)


  except dbsException.DbsException, ex:  
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
