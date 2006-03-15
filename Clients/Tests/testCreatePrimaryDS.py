import dbsException
from dbsClientDatastructures import DbsPrimaryDataset
import testCaseInterface
# Unit testing.


class testCreatePrimaryDS(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.createPrimaryDS)
    #self.addTestCase(self.printYahoo)

  def createPrimaryDS(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createPrimaryDS")
    print "Now executing ", funcName

    try:

       dataset = DbsPrimaryDataset(name="ThisIsATestDataset")

       primaryDatasetId = self.api.createPrimaryDataset(dataset)
       print "Got primary dataset id: %s" % primaryDatasetId

    except dbsException.DbsException, ex:
       print ex
       return 1
   
    return 0

  def printYahoo(self) :
     print "YAhooooooooooooooooooooooooo"
     return 0 


