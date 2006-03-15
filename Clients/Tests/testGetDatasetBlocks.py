import dbsException
import testCaseInterface
from dbsClientDatastructures import *
# Unit testing.

class testGetDatasetBlocks(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.getDatasetBlocks)


  def getDatasetBlocks(self):
    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetBlocks : Get a list of File Blocks in a Dataset")
    print "Now executing ", funcName

    datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
    try:

       fileBlockList = self.api.getDatasetFileBlocks(datasetPath)
       for fileBlock in fileBlockList:
          #print "File block name/id: %s/#/%s" % (datasetPath[1:], fileBlock._blockId)
          print "File block name/id: %s" % (fileBlock._blockName)
          #for eventCollection in fileBlock.getEventCollectionList():
          #   print "  %s" % eventCollection

    except dbsException.DbsException, ex:
       print ex
       return 1
   
    return 0


