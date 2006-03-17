import dbsException
import testCaseInterface
# Unit testing.

class testListDataset(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    testCaseInterface.testCaseInterface.__init__(self)
    #self.addTestCase(self.listDataset)
    self.addTestCase(self.listDatasetMany)

    #self.datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
    #self.datasetPath = "/jm03b_qcd_80_120/Hit/jm_Hit245_2_g133"

  def listDataset(self):
    datasetPath = "/eg03_jets_1e_pt2550/*/eg_2x1033PU761_TkMu_2_g133_OSC"
    funcName = "%s.%s" % (self.__class__.__name__, "listDataset")
    print "Now executing ", funcName

    try:
       dsList = self.api.listDataset(datasetPath)
       if dsList != None:
         for ds in dsList:
            print "Dataset ",ds
       else:
         print "No dataset returned"

    except dbsException.DbsException, ex:
       print ex
       return 1
   
    return 0

  def listDatasetMany(self):
    datasetPath = "/*/*/eg_2x1033PU761_TkMu_2_g133_OSC"
    funcName = "%s.%s" % (self.__class__.__name__, "listDatasetMany")
    print "Now executing ", funcName

    try:
       dsList = self.api.listDataset(datasetPath)
       if dsList != None:
         for ds in dsList:
            print "Dataset ",ds
       else:
         print "No dataset returned"


    except dbsException.DbsException, ex:
       print ex
       return 1
   
    return 0


