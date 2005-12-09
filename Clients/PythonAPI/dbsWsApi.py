#!/usr/bin/env python
#
# $Id: dbsWsApi.py,v 1.6 2005/12/08 16:40:25 sekhri Exp $
#
# Web service implementation of the DBS API class.
#
# Base API class provides some common functionality (e.g., logging
# configuration). Exception modules are defined in the dbsApi module.
#

import dbsException
import dbsApi
import dbsWsClient

import dbsMonteCarloDescription
import dbsPrimaryDataset
import dbsProcessedDataset
import dbsProcessingPath
import dbsApplication

##############################################################################
# Web service implementation of the DBS API class. Exceptions are defined
# in dbsApi module.

class DbsWsApi(dbsApi.DbsApi):

  def __init__(self, wsdlUrl=None):
    """ Constructor. """
    self._wsClient = dbsWsClient.DbsWsClient(wsdlUrl=wsdlUrl)

  def getDatasetContents(self, datasetPathName):
    """
    Retrieve list of file blocks, each containing a set of event collections,
    for a given the dataset path name string.

    Returns: list of DbsFileBlock objects.
    Exceptions: InvalidDatasetPathName
                DbsApiException
    """
    try:
      return self._wsClient.getDatasetContents(datasetPathName)
    except dbsWsClient.InvalidDatasetPathName, ex:
      raise dbsApi.InvalidDatasetPathName(exception=ex)
    except dbsWsClient.DbsWsClientException, ex:
      raise dbsApi.DbsApiException(exception=ex)

  def getDatasetProvenance(self, datasetPathName, dataTierList=[]):
    """
    Retrieve list of dataset parents for the given dataTiers.

    Returns: list of DbsDataset objects.
    Exceptions: InvalidDatasetPathName
                DbsApiException
    """
    try:
      return self._wsClient.getDatasetProvenance(
	datasetPathName, dataTierList)
    except dbsWsClient.InvalidDatasetPathName, ex:
      raise dbsApi.InvalidDatasetPathName(exception=ex)
    except dbsWsClient.DbsWsClientException, ex:
      raise dbsApi.DbsApiException(exception=ex)

  def createPrimaryDataset(self, primaryDataset):
    """
    Create primary dataset.

    Returns: primary dataset id.
    Exceptions: DbsApiException
    """
    try:
      return self._wsClient.createPrimaryDataset(primaryDataset)
    except dbsWsClient.DbsWsClientException, ex:
      raise dbsApi.DbsApiException(exception=ex)

  def createProcessedDataset(self, processedDataset):
    """
    Create processed dataset.

    Returns: processed dataset id.
    Exceptions: DbsApiException
    """
    try:
      return self._wsClient.createProcessedDataset(processedDataset)
    except dbsWsClient.DbsWsClientException, ex:
      raise dbsApi.DbsApiException(exception=ex)
      


##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:
    # Dataset we need.
    datasetPath = "eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC"

    # Construct api object.
    api = DbsWsApi(wsdlUrl="./DbsDatasetService.wsdl.xml")

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
    dataTierList = [ "Hit" ]
    print "Getting dataset provenance for: %s (dataTiers: %s)" % (
      datasetPath, dataTierList)
    
    datasetParentList = api.getDatasetProvenance(
      datasetPath, dataTierList)
    print "Dataset provenance for: %s (dataTiers: %s)" % (
      datasetPath, dataTierList)

    for datasetParent in datasetParentList:
      print "%s" % (datasetParent)

    # Test for create primary dataset.
    mc = dbsMonteCarloDescription.DbsMonteCarloDescription(
      description="MyMonteCarloDescription",
      production="production",
      decayChain="decayChain",
      isMcData="true")

    dataset = dbsPrimaryDataset.DbsPrimaryDataset(datasetName="ds1",
				datasetDescription="my dataset desc",
				physicsGroupName="top",
				monteCarloDescription=mc)

    print "Creating primary dataset: %s" % dataset.getDatasetName()
    primaryDatasetId = api.createPrimaryDataset(dataset)
    print "Got primary dataset id: %s" % primaryDatasetId

    # Test for create processed dataset.
    ##application = dbsApplication.DbsApplication(
    ##  family="reco", executable="dummy", version="p1")
    ##processingPath = dbsProcessingPath.DbsProcessingPath(
    ##  fullPath="/x/y/z", dataTier="hit", application=application)
    ##processingPath2 = dbsProcessingPath.DbsProcessingPath(
    ##  fullPath="/x22/y22/z22", dataTier="Digi", parentPath=processingPath)

    ##dataset = dbsProcessedDataset.DbsProcessedDataset(
    ##  datasetName="processedDataset", processingPath=processingPath2)

    ##print "Creating processed dataset: %s" % dataset.getDatasetName()
    ##processedDatasetId = api.createProcessedDataset(dataset)
    ##print "Got processed dataset id: %s" % processedDatasetId    
    
  except dbsException.DbsException, ex:
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
   
