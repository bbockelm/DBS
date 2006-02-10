import dbsException
import dbsApplication
import dbsProcessingPath
import dbsProcessedDataset
import testCaseInterface
# Unit testing.

class testGetDatasetBlocks(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.getDatasetBlocks)

    datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
    app = dbsApplication.DbsApplication(
       family="CMSAppFam",
       executable="cmsRun",
       version="CMSSW_XYZ",
       parameterSet="pSetDummy")

    processingPath = dbsProcessingPath.DbsProcessingPath(
       dataTier="Digi",
       application=app)

    self.dataset = dbsProcessedDataset.DbsProcessedDataset(
       primaryDatasetName="ThisIsATestDataset",
       isDatasetOpen="y",
       datasetName="ThisIsATestProcDataset",
       processingPath=processingPath)

  def getDatasetBlocks(self):
    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetBlocks : Get a list of File Blocks in a Dataset")
    print "Now executing ", funcName

    try:

       fileBlockList = self.api.getDatasetFileBlocks(self.dataset)
       for fileBlock in fileBlockList:
          print "File block name/id: %s/%s" % (fileBlock.getBlockName(),fileBlock.getBlockId())
          for eventCollection in fileBlock.getEventCollectionList():
             print "  %s" % eventCollection

    except dbsException.DbsException, ex:
       return 1
   
    return 0


