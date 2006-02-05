import dbsException
import dbsEventCollection
import dbsPrimaryDataset
import testCaseInterface
# Unit testing.


class testCreateEventCollection(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    funcName = "%s.%s" % (self.__class__.__name__, "__init__()")
    print "Now executing ", funcName
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.createEventCollection)
    #self.addTestCase(self.printYahoo)

  def createEventCollection(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEventCollection")
    print "Now executing ", funcName

    try:


    except dbsException.DbsException, ex:
       return 1
   
    return 0

  def printYahoo(self) :
     print "YAhooooooooooooooooooooooooo"
     return 0 


