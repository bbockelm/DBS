import dbsException
import testCaseInterface
from dbsClientDatastructures import *

class testCreateEventCollection(testCaseInterface.testCaseInterface) : 

  def __init__(self):

    testCaseInterface.testCaseInterface.__init__(self)
    #self.addTestCase(self.createEventCollection)
    #self.addTestCase(self.createEvCollWithOneFile)
    self.addTestCase(self.createEvCollWithParent)
    #self.addTestCase(self.createEvCollFilesShouldHaveSameBlockID)
    #self.addTestCase(self.createEvCollNoFileNoInsertion)

    self.datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
    

  def createEventCollection(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEventCollection: Creates Event Collection with 02 files")
    print "Now executing ", funcName

    try:
       # Test for inserting event collections.
       f1 = DbsFile(logicalFileName="myFileF10",
           guid = "7C8A55-DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileStatus="dummy",
           fileBlockId=1,
           fileSize=100
           )
       f2 = DbsFile(logicalFileName="myFileF12",
           guid = "7C8A55DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileStatus="dummy",
           fileBlockId=1,
           fileSize=100
           )
       fList=[f1, f2]

       ec = DbsEventCollection(
         collectionName="myLFN",
         numberOfEvents=123,
         collectionIndex=100,
         datasetPathName = self.datasetPath,
         fileList=fList)
       print "Inserting event collections for: %s" % self.datasetPath
       self.api.insertEventCollections([ec])


    except dbsException.DbsException, ex:
       print ex
       return 1
   
    return 0

  def createEvCollFilesShouldHaveSameBlockID(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEvCollFilesShouldHaveSameBlockID : All files in EvColl \n \
                                                           Should have Same Block ID, Else they don't get inserted")
    print "Now executing ", funcName

    try:
       #TEST   All files in same EvColl should have same blockId
       f1 = DbsFile(logicalFileName="myFileF10",
           guid = "7C8A55-DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileStatus="dummy",
           fileBlockId=1,
           fileSize=100
           )
       f2 = DbsFile(logicalFileName="myFileF12",
           guid = "7C8A55DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileStatus="dummy",
           fileBlockId=2,
           fileSize=100
           )
       fList=[f1, f2]

       ec = DbsEventCollection(
         collectionName="myLFN",
         numberOfEvents=123,
         collectionIndex=100,
         datasetPathName = self.datasetPath,
         fileList=fList)
       print "Inserting event collections for: %s" % self.datasetPath
       self.api.insertEventCollections([ec])

    except dbsException.DbsException, ex:
       print ex
       # Return Success, Negative testcase
       return 0

    return 1

  def createEvCollNoFileNoInsertion(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEvCollNoFileNoInsertion: Cannot insert EvColl without files")
    print "Now executing ", funcName

    try:
       ec = DbsEventCollection(
         collectionName="myLFN",
         numberOfEvents=123,
         collectionIndex=100,
         datasetPathName = self.datasetPath,
         fileList=[])
       print "Inserting event collections for: %s" % self.datasetPath
       self.api.insertEventCollections([ec])

    except dbsException.DbsException, ex:
       print ex
       # Return Success. negative testcase
       return 0

    return 1


  def createEvCollWithOneFile(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEvCollWithOneFile ")
    print "Now executing ", funcName

    try:
       f1 = DbsFile(logicalFileName="myFileF10",
           guid = "7C8A55-DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileStatus="dummy",
           fileBlockId=1,
           fileSize=100
           )
       fList=[f1]

       ec = DbsEventCollection(
         collectionName="myLFN",
         numberOfEvents=123,
         collectionIndex=100,
         datasetPathName = self.datasetPath,
         fileList=fList)
       print "Inserting event collections for: %s" % self.datasetPath
       self.api.insertEventCollections([ec])

    except dbsException.DbsException, ex:
       print ex
       return 1

    return 0

  def createEvCollWithParent(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEvCollWithParent ")
    print "Now executing ", funcName

    try:
       f1 = DbsFile(logicalFileName="myFileF10",
           guid = "7C8A55-DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileStatus="dummy",
           fileBlockId=1,
           fileSize=100
           )
       fList=[f1]

       ecParent = DbsEventCollection(
         collectionName="myLFN",
         numberOfEvents=123,
         collectionIndex=100,
         datasetPathName = self.datasetPath,
         fileList=fList)
 
       ec = DbsEventCollection(
         collectionName="myLFN",
         numberOfEvents=123,
         parentageType="Hit",
         collectionIndex=100,
         parent=ecParent,
         datasetPathName = self.datasetPath,
         fileList=fList)
       print "Inserting event collections for: %s" % self.datasetPath
       self.api.insertEventCollections([ec])

    except dbsException.DbsException, ex:
       print ex
       return 1

    return 0

