import dbsException
import dbsProcessedDataset
import dbsApplication
import dbsProcessingPath
import dbsProcessedDataset
import dbsFileBlock
import testCaseInterface
# Unit testing.


class testCreateBlock(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    funcName = "%s.%s" % (self.__class__.__name__, "__init__()")
    print "Now executing ", funcName
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.createBlock)
    #self.addTestCase(self.printYahoo)

  def createBlock(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createBlock")
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

       block = dbsFileBlock.DbsFileBlock(
         blockStatusName="Dummy Block Status",
         numberOfBytes=1024,
         numberOfFiles=10)

       print "Trying to create fileBlock"
       fbId = self.api.createFileBlock(dataset, block)
       print "Got file block id: %s" % fbId

    except dbsException.DbsException, ex:
       return 1
   
    return 0



