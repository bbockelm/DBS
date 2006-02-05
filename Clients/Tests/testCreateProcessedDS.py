import dbsException
import dbsProcessedDataset
import dbsApplication
import dbsProcessingPath
import dbsProcessedDataset
import testCaseInterface

# Unit testing.


class testCreateProcessedDS(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    funcName = "%s.%s" % (self.__class__.__name__, "__init__()")
    print "Now executing ", funcName
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.createProcessedDS)
    #self.addTestCase(self.createProcessedDSWithParent)

  def createProcessedDS(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createProcessedDS")
    print "Now executing ", funcName

    try:
       datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
       app = dbsApplication.DbsApplication(
         family="CMSAppFam",
         executable="cmsRun",
         version="CMSSW_XYZ",
         parameterSet="pSetDummy")

       processingPath = dbsProcessingPath.DbsProcessingPath(
         dataTier="Digi",
         application=app)

       dataset = dbsProcessedDataset.DbsProcessedDataset(
         primaryDatasetName="ThisIsATestDataset",
         isDatasetOpen="y",
         datasetName="ThisIsATestProcDataset",
         processingPath=processingPath)

       processedDatasetId = self.api.createProcessedDataset(dataset)
       print "Got processed dataset id: %s" % processedDatasetId    

    except dbsException.DbsException, ex:
       return 1
   
    return 0

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


  def createProcessedDSWithParent(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createProcessedDS")
    print "Now executing ", funcName

    try:
       datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
       app = dbsApplication.DbsApplication(
         family="CMSAppFam",
         executable="cmsRun",
         version="CMSSW_XYZ",
         parameterSet="pSetDummy")

       parentProcessingPath = dbsProcessingPath.DbsProcessingPath(
         dataTier="Digi",
         application=app)

       childprocessingPath = dbsProcessingPath.DbsProcessingPath(
         pathId="2",
         fullPath=datasetPath,
         dataTier="Digi",
         parentPath=parentProcessingPath,
         application=app)

       dataset = dbsProcessedDataset.DbsProcessedDataset(
         primaryDatasetName="ThisIsATestDataset",
         isDatasetOpen="y",
         datasetName="ThisIsATestProcDataset",
         processingPath=processingPath)

       processedDatasetId = self.api.createProcessedDataset(dataset)
       print "Got processed dataset id: %s" % processedDatasetId

    except dbsException.DbsException, ex:
       return 1

    return 0







