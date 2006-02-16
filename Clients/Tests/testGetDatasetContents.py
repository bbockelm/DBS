import dbsException
import testCaseInterface
# Unit testing.

class testGetDatasetContents(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.getDatasetContents)
    #self.addTestCase(self.getDatasetContentsWithFiles)

    #self.datasetPath = "/eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC"
    self.datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
    #self.datasetPath = "/jm03b_qcd_80_120/Hit/jm_Hit245_2_g133"

  def getDatasetContents(self):
    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetContents")
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
    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetContentsWithFiles")
    print "Now executing ", funcName

    try:

       # Non-Default behaviour of getDatasetContents
       fileBlockList = self.api.getDatasetContents(self.datasetPath, True)
       for fileBlock in fileBlockList:
          print "File block name/id: %s/%s" % (fileBlock.getBlockName(),fileBlock.getBlockId())
          for eventCollection in fileBlock.getEventCollectionList():
             print "\n\n  %s" % eventCollection

    except dbsException.DbsException, ex:
       return 1

    return 0


