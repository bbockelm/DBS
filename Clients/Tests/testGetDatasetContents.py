import dbsException
import testCaseInterface
# Unit testing.

class testGetDatasetContents(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    funcName = "%s.%s" % (self.__class__.__name__, "__init__()")
    print "Now executing ", funcName
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.getDatasetContents)
    self.addTestCase(self.getDatasetContentsWithFiles)

    self.datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"

  def getDatasetContents(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createPrimaryDS")
    print "Now executing ", funcName

    try:

       # Default behaviour of getDatasetContents
       fileBlockList = self.api.getDatasetContents(self.datasetPath)
       for fileBlock in fileBlockList:
          print "File block name/id: %s/%s" % (fileBlock.getBlockName(),fileBlock.getBlockId())
          for eventCollection in fileBlock.getEventCollectionList():
             print "  %s" % eventCollection

    except dbsException.DbsException, ex:
       return 1
   
    return 0

  def getDatasetContentsWithFiles(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createPrimaryDS")
    print "Now executing ", funcName

    try:

       # Non-Default behaviour of getDatasetContents
       fileBlockList = self.api.getDatasetContents(self.datasetPath, True)
       for fileBlock in fileBlockList:
          print "File block name/id: %s/%s" % (fileBlock.getBlockName(),fileBlock.getBlockId())
          for eventCollection in fileBlock.getEventCollectionList():
             print "  %s" % eventCollection

    except dbsException.DbsException, ex:
       return 1

    return 0


