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

    #datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
    datasetPath = "/eg03_jets_1e_pt50170/Hit/eg_Hit245_2_g133"
    #datasetPath = "/AF_PrimDataset1/AF_Hit/AF_ProcDataset1"
    try:

       fileBlockList = self.api.getDatasetFileBlocks(datasetPath)
       if fileBlockList != None:
         for fileBlock in fileBlockList:
            #print "File block name/id: %s/#/%s" % (datasetPath[1:], fileBlock._blockId)
            print "File block name/id: %s" % (fileBlock._blockName)
            #for eventCollection in fileBlock.getEventCollectionList():
            #   print "  %s" % eventCollection
       else:
         print "No fileBlockList returned"


    except dbsException.DbsException, ex:
       print ex
       return 1
   
    return 0


