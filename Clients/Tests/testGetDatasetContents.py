import dbsException
import testCaseInterface
# Unit testing.

class testGetDatasetContents(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    testCaseInterface.testCaseInterface.__init__(self)
    #self.addTestCase(self.getDatasetContents)
    self.addTestCase(self.getDatasetContentsWithFiles)

    #self.datasetPath = "/eg03_jets_1e_pt50170/Hit/eg_Hit245_2_g133"
    #self.datasetPath = "/eg03_jets_1e_pt2550/Digi/eg_2x1033PU761_TkMu_2_g133_OSC"
    #self.datasetPath = "/AF_PrimDataset1/AF_Hit/AF_ProcDataset1"
    self.datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
    #self.datasetPath = "/TestPreProdMu10GeV/GENSIMDIGI/GenSimDigi"
    #self.datasetPath = "/jm03b_qcd_80_120/Hit/jm_Hit245_2_g133"

  def getDatasetContents(self):
    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetContents")
    print "Now executing ", funcName

    try:
       fileBlockList = self.api.getDatasetContents(self.datasetPath,False)
       if fileBlockList != None:
         for fileBlock in fileBlockList:
            print "block name ",fileBlock._blockName
            for eventCollection in fileBlock._eventCollectionList:
               print "collectionName ", eventCollection._collectionName
               print "***************************************************"
       else:
         print "No fileBlockList returned"

    except dbsException.DbsException, ex:
       print ex
       return 1
   
    return 0

  def getDatasetContentsWithFiles(self):
    funcName = "%s.%s" % (self.__class__.__name__, "getDatasetContentsWithFiles")
    print "Now executing ", funcName

    try:

       # Non-Default behaviour of getDatasetContents
       fileBlockList = self.api.getDatasetContents(self.datasetPath, True)
       if fileBlockList != None:
         for fileBlock in fileBlockList:
            print "block name ",fileBlock._blockName
            for eventCollection in fileBlock._eventCollectionList:
               print "collectionName ", eventCollection._collectionName
               print "***************************************************"
               for aFile in eventCollection._fileList:
                   print "   id ", aFile._id
                   print "   guid ", aFile._guid
                   print "   logicalFileName ", aFile._logicalFileName
                   print "   checksum ", aFile._checksum
                   print "   fileSize ", aFile._fileSize
                   print "   fileStatus ", aFile._fileStatus
                   print "   fileType ", aFile._fileType
                   print "   fileBlockId ", aFile._fileBlockId
                   print "***************************************************"
       else:
         print "No fileBlockList returned"

  
    except dbsException.DbsException, ex:
       print ex
       return 1

    return 0


