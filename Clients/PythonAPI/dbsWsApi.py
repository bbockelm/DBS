#!/usr/bin/env python
#
# $Id: dbsWsApi.py,v 1.8 2005/12/12 17:45:41 sveseli Exp $
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
import dbsEventCollection
import dbsApplication
import dbsFile
import dbsFileBlock

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

  def insertEventCollections(self, processedDatasetName, eventCollectionList):
    """
    Insert event collections for a given processed dataset.

    Returns: void
    Exceptions: DbsApiException
    """
    try:
      return self._wsClient.insertEventCollections(processedDatasetName,
						   eventCollectionList)
    except dbsWsClient.DbsWsClientException, ex:
      raise dbsApi.DbsApiException(exception=ex)

  def createFileBlock(self, fileBlock):
    """
    Create new file block.

    Returns: file block id.
    Exceptions: DbsApiException
    """
    try:
      return self._wsClient.createFileBlock(fileBlock)
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
    ##print "Getting dataset contents for: %s" % datasetPath
    

    ##fileBlockList = api.getDatasetContents(datasetPath)
    ##print "Dataset contents for: %s" % datasetPath
    ##for fileBlock in fileBlockList:
      ##print ""
      ##print "File block name/id: %s/%s" % (fileBlock.getBlockName(),
	##				   fileBlock.getBlockId())
      ##for eventCollection in fileBlock.getEventCollectionList():
	##print "  %s" % eventCollection

    # Get dataset provenance. It returns list of dataset parents.
    ##print ""
    ##dataTierList = [ "Digi", "Hit" ]
    ##dataTierList = [ "Hit" ]
    ##print "Getting dataset provenance for: %s (dataTiers: %s)" % (
      ##datasetPath, dataTierList)
    
    ##datasetParentList = api.getDatasetProvenance(
      ##datasetPath, dataTierList)
    ##print "Dataset provenance for: %s (dataTiers: %s)" % (
      ##datasetPath, dataTierList)

    ##for datasetParent in datasetParentList:
      ##print "%s" % (datasetParent)

    # Test for create primary dataset.
    ##mc = dbsMonteCarloDescription.DbsMonteCarloDescription(
      ##description="MyMonteCarloDescription",
      ##production="production",
      ##decayChain="decayChain",
      ##isMcData="true")

    ##dataset = dbsPrimaryDataset.DbsPrimaryDataset(datasetName="ds1",
	##			datasetDescription="my dataset desc",
	##			physicsGroupName="top",
	##			monteCarloDescription=mc)

    ##print "Creating primary dataset: %s" % dataset.getDatasetName()
    ##primaryDatasetId = api.createPrimaryDataset(dataset)
    ##print "Got primary dataset id: %s" % primaryDatasetId

    # Test for create processed dataset.
    application = dbsApplication.DbsApplication(
      family="reco", executable="dummy", version="p1")
    processingPath = dbsProcessingPath.DbsProcessingPath(
      fullPath="/x/y/z", dataTier="hit", application=application)
    processingPath2 = dbsProcessingPath.DbsProcessingPath(
      fullPath="/x22/y22/z22", dataTier="Digi", parentPath=processingPath)

    dataset = dbsProcessedDataset.DbsProcessedDataset(
      datasetName="processedDataset", processingPath=processingPath2)

    ##print "Creating processed dataset: %s" % dataset.getDatasetName()
    ##processedDatasetId = api.createProcessedDataset(dataset)
    ##print "Got processed dataset id: %s" % processedDatasetId    

    # Test for inserting event collections.
    f1 = dbsFile.DbsFile(logicalFileName="myFile1")
    f2 = dbsFile.DbsFile(logicalFileName="myFile2")
    ec = dbsEventCollection.DbsEventCollection(
      collectionName="ec1", numberOfEvents=123, fileList=[f1])
    ecList = dbsEventCollection.DbsEventCollectionList([ec])
    ecList.append(dbsEventCollection.DbsEventCollection(collectionName="ec2", numberOfEvents=228, fileList=[f2]))
    print "Event collection list: \n", ecList
    print "Inserting event collections for: %s" % dataset.getDatasetName()
    api.insertEventCollections(dataset.getDatasetName(), ecList)

    #Test for creating file blocks.
    fb1 = dbsFileBlock.DbsFileBlock(blockId=765, blockName="myFirstBlock", processedDatasetName="ds1")
    print "Creating file block: %s" % fb1
    fbId = api.createFileBlock(fb1)
    print "Got file block id: %s" % fbId
    
  except dbsException.DbsException, ex:
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
   
