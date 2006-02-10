import dbsException
import dbsApplication
import dbsProcessingPath
import dbsProcessedDataset
import testCaseInterface

# Unit testing.


class testCreateProcessedDS(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.createProcessedDS)
    self.addTestCase(self.createProcessedDSWithParent)
    self.addTestCase(self.createProcessedDSBadReInsertion)
    self.addTestCase(self.createProcessedDSWithSameParent)

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


  def createProcessedDSBadReInsertion(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createProcessedDS")
    print "Now executing ", funcName

    try:
       datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
       app = dbsApplication.DbsApplication(
         family="CMSAppFam",
         #executable="cmsRun2",
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
       return 0

    return 1




  def createProcessedDSWithParent(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createProcessedDSWithParent")
    print "Now executing ", funcName

    try:
       datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
       appParent = dbsApplication.DbsApplication(
         family="CMSAppFam1",
         executable="cmsRun1",
         version="CMSSW_XYZ1",
         parameterSet="pSetDummy1")

       appChild = dbsApplication.DbsApplication(
         family="CMSAppFam3",
         executable="cmsRun4",
         version="CMSSW_XYZ5",
         parameterSet="pSetDummy6")

       parentProcessingPath = dbsProcessingPath.DbsProcessingPath(
         dataTier="Digi",
         application=appParent)

       childprocessingPath = dbsProcessingPath.DbsProcessingPath(
         dataTier="Hit",
         parentPath=parentProcessingPath,
         application=appChild)

       dataset = dbsProcessedDataset.DbsProcessedDataset(
         primaryDatasetName="ThisIsATestDataset",
         isDatasetOpen="y",
         datasetName="ThisIsATestProcDataset",
         processingPath=childprocessingPath)

       processedDatasetId = self.api.createProcessedDataset(dataset)
       print "Got processed dataset id: %s" % processedDatasetId

    except dbsException.DbsException, ex:
       return 1

    return 0






  def createProcessedDSWithSameParent(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createProcessedDSWithSameParent")
    print "Now executing ", funcName

    try:
       datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
       app = dbsApplication.DbsApplication(
         family="CMSAppFam3",
         executable="cmsRun4",
         version="CMSSW_XYZ5",
         parameterSet="pSetDummy6")

       parentProcessingPath = dbsProcessingPath.DbsProcessingPath(
         dataTier="Digi",
         application=app)

       childprocessingPath = dbsProcessingPath.DbsProcessingPath(
         dataTier="Hit",
         parentPath=parentProcessingPath,
         application=app)

       dataset = dbsProcessedDataset.DbsProcessedDataset(
         primaryDatasetName="ThisIsATestDataset",
         isDatasetOpen="y",
         datasetName="ThisIsATestProcDataset",
         processingPath=childprocessingPath)

       processedDatasetId = self.api.createProcessedDataset(dataset)
       print "Got processed dataset id: %s" % processedDatasetId

    except dbsException.DbsException, ex:
       return 0

    return 1

