#!/usr/bin/env python
#
# $Id: dbsCliApi.py,v 1.2 2005/10/28 16:19:02 sveseli Exp $
#
# CLI implementation of the DBS API class. This version of API
# relies on various sql tools to retrieve information out of the db.
#
# Base API class provides some common functionality (e.g., logging
# configuration).
#

import dbsException
import dbsApi
import dbsPhedexUtility


##############################################################################
# CLI API exception classes.

class DbsCliApiException(dbsException.DbsException):

  def __init__ (self, **kwargs):
    """ Initialization. """
    dbsException.DbsException.__init__(self, **kwargs)

##############################################################################
# CLI implementation of the DBS API class.

class DbsCliApi(dbsApi.DbsApi):

  def __init__(self, phedexDir=None, phedexDbConnectFile=None,
	       phedexDbSectionString=None):
    """ Constructor. """
    self._phedexUtility = dbsPhedexUtility.DbsPhedexUtility(
      phedexDir=phedexDir, phedexDbConnectFile=phedexDbConnectFile,
      phedexDbSectionString=phedexDbSectionString)

  def getDatasetContents(self, datasetPathName):
    """
    Retrieve list of file blocks, each containing a set of event collections,
    for a given the dataset path name string. """
    try:
      return self._phedexUtility.getDatasetContents(datasetPathName)
    except dbsPhedexUtility.DbsPhedexUtilityException, ex:
      raise DbsCliApiException(exception=ex)

  def getDatasetProvenance(self, datasetPathName, dataTierList=[]):
    """
    Retrieve list of dataset parents for the given dataTiers. """
    try:
      return self._phedexUtility.getDatasetProvenance(
	datasetPathName, dataTierList)
    except dbsPhedexUtility.DbsPhedexUtilityException, ex:
      raise DbsCliApiException(exception=ex)
      


##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    # Dataset we need.
    datasetPath = "bt_Hit245_2_g133/bt03_B0sJPsiX"

    # Construct api object.
    api = DbsCliApi(
      phedexDir="/home/veseli/work/dbs/PHEDEX",
      phedexDbSectionString="Production/Admin",
      phedexDbConnectFile="/home/veseli/work/dbs/db_keys.out")

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
