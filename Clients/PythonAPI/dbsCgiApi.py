#!/usr/bin/env python
#
# $Id: dbsCgiApi.py,v 1.3 2005/10/28 15:39:07 sveseli Exp $
#
# CGI implementation of the DBS API class. This version of API
# relies on cgi scripts providing xml output. 
#
# Base API class provides some common functionality (e.g., logging
# configuration).
#

import dbsException
import dbsApi
import dbsCgiUtility


##############################################################################
# CLI API exception classes.

class DbsCgiApiException(dbsException.DbsException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

##############################################################################
# CLI implementation of the DBS API class.

class DbsCgiApi(dbsApi.DbsApi):

  def __init__(self, cgiUrl=None):
    """ Constructor. """
    self._cgiUtility = dbsCgiUtility.DbsCgiUtility(cgiUrl=cgiUrl)

  def getDatasetContents(self, datasetPathName):
    """
    Retrieve list of file blocks, each containing a set of event collections,
    for a given the dataset path name string.
    """
    try:
      return self._cgiUtility.getDatasetContents(datasetPathName)
    except dbsCgiUtility.DbsCgiUtilityException, ex:
      raise DbsCgiApiException(exception=ex)

  def getDatasetProvenance(self, datasetPathName, dataTierList=[]):
    """
    Retrieve list of dataset parents for the given dataTiers.
    """
    try:
      return self._cgiUtility.getDatasetProvenance(
	datasetPathName, dataTierList)
    except dbsCgiUtility.DbsCgiUtilityException, ex:
      raise DbsCgiApiException(exception=ex)
      


##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    # Dataset we need.
    datasetPath = "bt03_B0sJPsiX/Hit/bt_Hit245_2_g133"

    # Construct api object.
    api = DbsCgiApi(cgiUrl="http://cern.ch/cms-dbs/cgi-bin")

    # Configure logging.
    api.setLogLevel(dbsApi.DBS_LOG_LEVEL_ALL_)
    
    # Get dataset contents. It returns list of file blocks, each
    # file block containing a set of event collections.
    print "Getting dataset contents for: %s" % datasetPath
    fileBlockList = api.getDatasetContents(datasetPath)
    print "Dataset contents for: %s" % datasetPath
    for fileBlock in fileBlockList:
      print ""
      print "File block name/id: %s/%s" % (fileBlock.getBlockName(),
					   fileBlock.getBlockId())
      for eventCollection in fileBlock.getEventCollectionList():
	print "  %s" % eventCollection

    # Get dataset provenance. It returns list of dataset parents.
    print ""
    dataTierList = [ "Digi", "Hit" ]
    print "Getting dataset provenance for: %s (dataTiers: %s)" % (
      datasetPath, dataTierList)
    
    datasetParentList = api.getDatasetProvenance(
      datasetPath, dataTierList)
    print "Dataset provenance for: %s (dataTiers: %s)" % (
      datasetPath, dataTierList)

    for datasetParent in datasetParentList:
      print "%s" % (datasetParent)


  except dbsException.DbsException, ex:
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
