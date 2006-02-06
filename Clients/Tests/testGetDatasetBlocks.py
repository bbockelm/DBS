import dbsException
import dbsApplication
import dbsProcessingPath
import dbsProcessedDataset
import testCaseInterface
# Unit testing.


class testGetDatasetBlocks(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    funcName = "%s.%s" % (self.__class__.__name__, "__init__()")
    print "Now executing ", funcName
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.getDatasetBlocks)
    #self.addTestCase(self.printYahoo)

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
    funcName = "%s.%s" % (self.__class__.__name__, "createPrimaryDS")
    print "Now executing ", funcName

    try:

       fileBlockList = self.api.getDatasetFileBlocks(self.dataset)
       print fileBlockList

    except dbsException.DbsException, ex:
       return 1
   
    return 0


