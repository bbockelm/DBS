#!/usr/bin/env python
#
# $Id: dbsApi.py,v 1.7 2005/12/13 14:44:20 sveseli Exp $
#
# Base DBS API class. All implementation should implement interfaces
# listed here. Logging configuration methods are provided here for convenience
# (it can be done directly using the DbsLogManager singleton).
#

import dbsException
import dbsLogManager

# Log levels used as masks (defined in dbsLogManager). 
DBS_LOG_LEVEL_QUIET_ = dbsLogManager.LOG_LEVEL_QUIET_       # no output
DBS_LOG_LEVEL_INFO_ = dbsLogManager.LOG_LEVEL_INFO_         # info for users
DBS_LOG_LEVEL_TRACE_ = dbsLogManager.LOG_LEVEL_TRACE_       # execution trace
DBS_LOG_LEVEL_DEBUG_ = dbsLogManager.LOG_LEVEL_DEBUG_       # debugging
DBS_LOG_LEVEL_WARNING_ = dbsLogManager.LOG_LEVEL_WARNING_   # warnings
DBS_LOG_LEVEL_ERROR_ = dbsLogManager.LOG_LEVEL_ERROR_       # errors
DBS_LOG_LEVEL_ALL_ = dbsLogManager.LOG_LEVEL_ALL_           # all messages

##############################################################################
# DBS API exceptions.

class DbsApiException(dbsException.DbsException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

class InvalidDatasetPathName(DbsApiException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    DbsApiException.__init__(self, **kwargs)


##############################################################################
# DBS API interface class.

class DbsApi:

  # No constructor.
  
  # Methods which should be implemented in the derived classes.
  def getDatasetContents(self, datasetPathName):
    """ Retrieve event collections given the dataset path name string. """
    raise dbsException.MethodNotImplemented(args="This method should be overridden in the derived DBS API class.")

  def getDatasetProvenance(self, datasetPathName, dataTierList):
    """
    Retrieve list of dataset parents for the given dataTiers.
    """
    raise dbsException.MethodNotImplemented(args="This method should be overridden in the derived DBS API class.")

  def createPrimaryDataset(self, primaryDataset):
    """
    Create primary dataset.
    """
    raise dbsException.MethodNotImplemented(args="This method should be overridden in the derived DBS API class.")

  def createProcessedDataset(self, processedDataset):
    """
    Create processed dataset.
    """
    raise dbsException.MethodNotImplemented(args="This method should be overridden in the derived DBS API class.")

  def insertEventCollections(self, processedDataset, eventCollectionList):
    """
    Insert event collections for a given processed dataset.
    """
    raise dbsException.MethodNotImplemented(args="This method should be overridden in the derived DBS API class.")

  def createFileBlock(self, processedDataset, fileBlock):
    """
    Insert event collections for a given processed dataset.
    """
    raise dbsException.MethodNotImplemented(args="This method should be overridden in the derived DBS API class.")


  # Methods common for all API implementations.
  def getLogManagerInstance(self):
    """ Return log manager instance. """
    return dbsLogManager.getInstance()
    
  def setLogLevel(self, logLevel):
    """
    Set logging level. Example of usage would be:

    Get info messages and warnings:
      dbsApi.setLogLevel(DBS_LOG_LEVEL_INFO_|DBS_LOG_LEVEL_WARNING_)

    Get all messages:
      dbsApi.setLogLevel(DBS_LOG_LEVEL_ALL_)

    """
    dbsLogManager.getInstance().setLogLevel(logLevel)

  def setWriteToStdOut(self, writeToStdOut):
    """ Set flag which determines logging into stdout. """
    dbsLogManager.getInstance().setWriteToStdOut(writeToStdOut)

  def setLogFileName(self, fileName):
    """ This method is used to enable logging into a file. """
    dbsLogManager.getInstance().setLogFileName(fileName)
    
##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    api = DbsApi()
    api.setLogLevel(DBS_LOG_LEVEL_INFO_|DBS_LOG_LEVEL_ERROR_)
    api.getDatasetContents("myowner/mydataset")
  except dbsException.DbsException, ex:
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
