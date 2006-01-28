#!/usr/bin/env python
#
# $Id: dbsCgiApi.py,v 1.9 2006/01/24 18:57:10 sveseli Exp $
#
# CGI implementation of the DBS API class. This version of API
# relies on cgi scripts providing xml output. 
#
# Base API class provides some common functionality (e.g., logging
# configuration). Exception modules are defined in the dbsApi module.
#

import dbsException
import dbsApi
import dbsCgiUtility


##############################################################################
# CLI implementation of the DBS API class.

class DbsCgiApi(dbsApi.DbsApi):

  def __init__(self, cgiUrl=None):
    """ Constructor. """
    self._cgiUtility = dbsCgiUtility.DbsCgiUtility(cgiUrl=cgiUrl)

  def getDatasetContents(self, datasetPathName, listFiles=False):
    """
    Retrieve list of file blocks, each containing a set of event collections,
    for a given the dataset path name string.

    Returns: list of DbsFileBlock objects.
    Exceptions: InvalidDatasetPathName
                DbsCgiApiException
    """
    try:
      return self._cgiUtility.getDatasetContents(datasetPathName)
    except dbsCgiUtility.InvalidDatasetPathName, ex:
      raise dbsApi.InvalidDatasetPathName(exception=ex)
    except dbsCgiUtility.DbsCgiUtilityException, ex:
      raise dbsApi.DbsApiException(exception=ex)

  def getDatasetProvenance(self, datasetPathName, dataTierList=[]):
    """
    Retrieve list of dataset parents for the given dataTiers.

    Returns: list of DbsDataset objects.
    Exceptions: InvalidDatasetPathName
                InvalidDataTier
                DbsCgiApiException
    """
    try:
      return self._cgiUtility.getDatasetProvenance(
	datasetPathName, dataTierList)
    except dbsCgiUtility.InvalidDatasetPathName, ex:
      raise dbsApi.InvalidDatasetPathName(exception=ex)
    except dbsCgiUtility.InvalidDataTier, ex:
      raise dbsApi.InvalidDataTier(exception=ex)
    except dbsCgiUtility.DbsCgiUtilityException, ex:
      raise dbsApi.DbsApiException(exception=ex)
      


##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    # Dataset we need.
    datasetPath = "/eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC"
    #datasetPath = "/eg03_jets_1e_pt2550/Digi/MissingDataset"

    # Construct api object.
    api = DbsCgiApi(cgiUrl="http://cern.ch/cms-dbs/cgi-bin")

    # Configure logging.
    api.setLogLevel(dbsApi.DBS_LOG_LEVEL_ALL_)
    
    # Get dataset contents. It returns list of file blocks, each
    # file block containing a set of event collections.
    #print "Getting dataset contents for: %s" % datasetPath
    #fileBlockList = api.getDatasetContents(datasetPath)
    #print "Dataset contents for: %s" % datasetPath
    #for fileBlock in fileBlockList:
    #  print ""
    #  print "File block name/id: %s/%s" % (fileBlock.getBlockName(),
	#				   fileBlock.getBlockId())
      #for eventCollection in fileBlock.getEventCollectionList():
	#print "  %s" % eventCollection

    # Get dataset provenance. It returns list of dataset parents.
    print ""
    dataTierList = [ "Blah" ]
    print "Getting dataset provenance for: %s (dataTiers: %s)" % (
      datasetPath, dataTierList)
    
    datasetParentList = api.getDatasetProvenance(
      datasetPath, dataTierList)
    print "Dataset provenance for: %s (dataTiers: %s)" % (
      datasetPath, dataTierList)

    for datasetParent in datasetParentList:
      print "%s" % (datasetParent)


  except dbsApi.InvalidDataTier, ex:
    print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
  except dbsApi.DbsApiException, ex:
    print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  except dbsException.DbsException, ex:
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
