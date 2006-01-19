#!/usr/bin/env python
#
# $Id: dbsWsApi.py,v 1.15 2005/12/15 22:52:40 sekhri Exp $
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

  def insertEventCollections(self, processedDataset, eventCollectionList):
    """
    Insert event collections for a given processed dataset.

    Returns: void
    Exceptions: DbsApiException
    """
    try:
      return self._wsClient.insertEventCollections(processedDataset,
						   eventCollectionList)
    except dbsWsClient.DbsWsClientException, ex:
      raise dbsApi.DbsApiException(exception=ex)

  def createFileBlock(self, processedDataset, fileBlock):
    """
    Create new file block.

    Returns: file block id.
    Exceptions: DbsApiException
    """
    try:
      return self._wsClient.createFileBlock(processedDataset, fileBlock)
    except dbsWsClient.DbsWsClientException, ex:
      raise dbsApi.DbsApiException(exception=ex)
      


##############################################################################
# Unit testing.

if __name__ == "__main__":
  try:

    # Construct api object.
    api = DbsWsApi(wsdlUrl="./DbsDatasetService.wsdl.xml")

    # Configure logging.
    api.setLogLevel(dbsApi.DBS_LOG_LEVEL_ALL_)
    
    # Get dataset contents. It returns list of file blocks, each
    # file block containing a set of event collections.
    
    # Test for create primary dataset.

    mc = dbsMonteCarloDescription.DbsMonteCarloDescription(
      description="MyMonteCarloDescription",
      production="production",
      decayChain="decayChain",
      isMcData="y")

    #dataset = dbsPrimaryDataset.DbsPrimaryDataset(datasetName="eg03_jets_1e_pt2550",
    dataset = dbsPrimaryDataset.DbsPrimaryDataset(datasetName="My_Primary_eg03_jets_1e_pt2550",
      datasetDescription="my dataset desc",
      physicsGroupName="top",
      triggerDescription="Dummy triggerDescription",
      monteCarloDescription=mc)
    """
    primaryDatasetId = api.createPrimaryDataset(dataset)
    print "Got primary dataset id: %s" % primaryDatasetId
    """
    # Test for create processed dataset.
    datasetPath = "/sw04_Anzar/DST/sw_DST813_2_g133_OSC"
    #datasetPath = "/sw04_Anzar/Digi/sw_2x1033PU761_TkMu_2_g133_OSC"
    #datasetPath = "/sw04_Anzar/Hit/sw_Hit245_2_g133"
    #datasetPath = "/eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC"
    #datasetPath1 = "/eg03_jets_1e_pt2550/Hit/eg_2x1033PU761_TkMu_2_g133_OSC1"
    app = dbsApplication.DbsApplication(
      family="reco1", 
      executable="dummy1", 
      version="p11", 
      configConditionsVersion ="abcd1",
      parameterSet="psetdummy1",
      outputTypeName="odummy1",
      inputTypeName="idummy1")

    processingPath = dbsProcessingPath.DbsProcessingPath(
      fullPath=datasetPath, 
      dataTier="Digi", 
      application=app)
    processingPath2 = dbsProcessingPath.DbsProcessingPath(
      pathId="2",
      fullPath=datasetPath, 
      dataTier="Digi", 
      parentPath=processingPath, 
      application=app)

    dataset = dbsProcessedDataset.DbsProcessedDataset(
      primaryDatasetName="My_Primary_eg03_jets_1e_pt2550",
      isDatasetOpen="y",
      datasetName="My_Processed_eg_2x1033PU761_TkMu_2_g133_OSC", 
      processingPath=processingPath2)

    #processedDatasetId,ppathId = api.createProcessedDataset(dataset)
    #print "Got processed dataset id: %s" % processedDatasetId    
    #print "Got processing path  id: %s" % ppathId    
    
    block = dbsFileBlock.DbsFileBlock(
      #blockName=None,
      blockStatusName="Dummy Block Status", 
      numberOfBytes=1024, 
      numberOfFiles=10
      )
    #fbId = api.createFileBlock(dataset, block)
    #print "Got file block id: %s" % fbId
    
    # Test for inserting event collections.
    f1 = dbsFile.DbsFile(logicalFileName="myFile8",
	fileStatus = "file dummy status",
	guid = "7C8A55-DE62-D811-892C-00E081250436",
        checkSum="BA7C8A55-DE62-D811-892C-00E081250436", 
        fileType="EVDZip",
        fileBlockId=273, 
        fileSize=100
        ) 
    f2 = dbsFile.DbsFile(logicalFileName="myFile9",
	fileStatus = "file dummy status",
	guid = "7C8A55-DE62-D811-892C-00E081250436a",
        checkSum="BA7C8A55-DE62-D811-892C-00E081250a436", 
        fileType="EVDZip",
        fileBlockId=274, 
        fileSize=100
        )
    f3 = dbsFile.DbsFile(logicalFileName="myFile5",
	fileStatus = "file dummy status",
	guid = "7C8A55-DE62-D811-892C-00E081250436a",
        checkSum="BA7C8A55-DE62-D811-892C-00E081250a436", 
        fileType="EVDZip",
        fileBlockId=9, 
        fileSize=100
        )
    fList=dbsFile.DbsFileList([f1])
    fList1=dbsFile.DbsFileList([f2])

    #fList=dbsFile.DbsFileList([f1])
    #fList.append(f2)

    ec = dbsEventCollection.DbsEventCollection(
      collectionName="ec1", 
      numberOfEvents=123, 
      collectionIndex=100,
      isPrimary="y",
      parentageType="DST",
      fileList=fList)
    ec1 = dbsEventCollection.DbsEventCollection(
      collectionName="ec2", 
      numberOfEvents=123, 
      collectionIndex=106,
      isPrimary="y",
      parentageType="OSCAR",
      parentEventCollection=ec,
      fileList=fList1)
    """
    ectestp = dbsEventCollection.DbsEventCollection(
      collectionId=12,
      fileList=[]
    )
    ectest = dbsEventCollection.DbsEventCollection(
      numberOfEvents=0,
      collectionName="collectionName",
      collectionIndex=160800002,
      parentEventCollection=ectestp,
      parentageType="Digi",
      fileList=[],
      isPrimary='n'
    )
    """


#ecList  [{'numberOfEvents': 0, 'collectionName': 'EvC_Run160800002', 'collectionIndex': 160800002, 'parentEventCollection': {'fileList': [], 'collectionId': 12}, 'parentageType': 'Digi', 'fileList': [], 'isPrimary': 'n'}]
    #ecList = dbsEventCollection.DbsEventCollectionList([ec])
    ecList = dbsEventCollection.DbsEventCollectionList([ec1])
    #ecList = dbsEventCollection.DbsEventCollectionList([ectest])
    #ecList.append(ec1)
    print "Inserting event collections for: %s" % dataset.getDatasetName()
    ecid = api.insertEventCollections(dataset, ecList)
    print "ecid %s"%ecid['collectionId']

    """
       

    print "Getting dataset contents for: %s" % datasetPath
    
    
    fileBlockList = api.getDatasetContents(datasetPath)
    for fileBlock in fileBlockList:
      print "File block name/id: %s/%s" % (fileBlock.getBlockName(),fileBlock.getBlockId())
      for eventCollection in fileBlock.getEventCollectionList():
	print "  %s" % eventCollection
    
    # Get dataset provenance. It returns list of dataset parents.
    
    dataTierList = [ "Hit","Digi" ]
    print "Getting dataset provenance for: %s (dataTiers: %s)" % (datasetPath, dataTierList)
    
    datasetParentList = api.getDatasetProvenance(datasetPath, dataTierList)
    print "Dataset provenance for: %s (dataTiers: %s)" % (datasetPath, dataTierList)

    for datasetParent in datasetParentList:
      print "%s" % (datasetParent)
    """ 
  except dbsException.DbsException, ex:
    print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
  print "Done"
   
