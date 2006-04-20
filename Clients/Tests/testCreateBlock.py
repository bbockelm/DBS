import dbsException
import testCaseInterface
from dbsClientDatastructures import *
# Unit testing.


class testCreateBlock(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.createBlock)
    #self.addTestCase(self.printYahoo)

  def createBlock(self):
    funcName = "%s.%s" % (self.__class__.__name__, "CreateBlock: Creates a File Block")
    print "Now executing ", funcName

    try:

       datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"

       block = DbsBlock(
         blockStatusName="DummyBlockStatus",
         numberOfBytes=1024,
         numberOfFiles=10)

       #print "Trying to create fileBlock"
       fbId = self.api.createFileBlock(datasetPath, block)
       print "Got file block id: %s" % fbId

    except dbsException.DbsException, ex:
       print ex
       return 1
   
    return 0



