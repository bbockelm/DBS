import dbsException
import dbsPrimaryDataset
import testCaseInterface
# Unit testing.


class testCreatePrimaryDS(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    funcName = "%s.%s" % (self.__class__.__name__, "__init__()")
    print "Now executing ", funcName
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.createPrimaryDS)
    #self.addTestCase(self.printYahoo)

  def createPrimaryDS(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createPrimaryDS")
    print "Now executing ", funcName

    try:

       dataset = dbsPrimaryDataset.DbsPrimaryDataset(datasetName="ThisIsATestDataset")

       primaryDatasetId = self.api.createPrimaryDataset(dataset)
       print "Got primary dataset id: %s" % primaryDatasetId

    except dbsException.DbsException, ex:
       return 1
   
    return 0

  def printYahoo(self) :
     print "YAhooooooooooooooooooooooooo"
     return 0 


