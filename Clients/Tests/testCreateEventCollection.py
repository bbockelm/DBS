import dbsException
import testCaseInterface
from dbsClientDatastructures import *

class testCreateEventCollection(testCaseInterface.testCaseInterface) : 

  def __init__(self):

    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.createEventCollection100Files)
    #self.addTestCase(self.createEventCollection)
    #self.addTestCase(self.createEventCollectionOneFile)
    #self.addTestCase(self.createEvCollWithOneFile)
    #self.addTestCase(self.createEvCollWithParent)
    #self.addTestCase(self.createTwoEvCollWithParent)
    #self.addTestCase(self.createEvCollFilesShouldHaveSameBlockID)
    #self.addTestCase(self.createEvCollNoFileNoInsertion)

    self.datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
    
  def createEventCollection100Files(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEventCollection: Creates Event Collection with 02 files")
    print "Now executing ", funcName

    try:
       ecList = []
       for i in range(1,500) :
         # Test for inserting event collections.
         fList = []
         f  = DbsFile(logicalFileName="myFileFxyz%s" %i,
           guid = "7C8A55-DE62-D811-892C-00E08125bb%s" %i,
           fileType="EVDZip",
           fileStatus="dummy",
           fileBlockId=1,
           checksum="abcd",
           fileSize=100
           )
         fList.append(f)
         
         ec = DbsEventCollection(
           collectionName="myLFNxyz%s" %i,
           numberOfEvents=123,
           status="NEW",
           collectionIndex=11 + i,
           datasetPathName = self.datasetPath,
           fileList=fList)

         ecList.append(ec)

       print "Inserting event collections for: %s" % self.datasetPath
       self.api.insertEventCollections(ecList)

    except dbsException.DbsException, ex:
       print ex
       return 1

    return 0


  def createEventCollection(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEventCollection: Creates Event Collection with 02 files")
    print "Now executing ", funcName

    try:
       # Test for inserting event collections.
       f1 = DbsFile(logicalFileName="myFileF310",
           guid = "7C8A55-DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileStatus="dummy",
           checksum="2325",
           fileBlockId=1,
           fileSize=100
           )
       f2 = DbsFile(logicalFileName="myFileF312",
           guid = "7C8A55DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileStatus="dummy",
           checksum="52225",
           fileBlockId=1,
           fileSize=100
           )
       fList=[f1, f2]

       ec = DbsEventCollection(
         collectionName="myLFN",
         status="DUMMY",
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


  def createEventCollectionOneFile(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEventCollection: Creates Event Collection with 01 files")
    print "Now executing ", funcName

    try:
       # Test for inserting event collections.
       f1 = DbsFile(logicalFileName="myFileF311",
           guid = "7C8A55-DE62-D811-892C-00E0812504361",
           fileType="EVDZip",
           fileStatus="dummy1",
           checksum="23251",
           fileBlockId=1,
           fileSize=100
           )
       fList=[f1]

       ec = DbsEventCollection(
         collectionName="myLFN1",
         status="DUMMY1",
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
         checksum=25,
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

  def createTwoEvCollWithParent(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEvCollWithParent ")
    print "Now executing ", funcName

    try:
       f1 = DbsFile(logicalFileName="myFileF1",
           guid = "7C8A55-DE62-D811-892C-00E0812504361",
           fileType="EVDZip1",
           fileStatus="dummy1",
           fileBlockId=1,
           fileSize=100
           )
       fList1=[f1]

       f2 = DbsFile(logicalFileName="myFileF2",
           guid = "7C8A55-DE62-D811-892C-00E0812504362",
           fileType="EVDZip2",
           fileStatus="dummy2",
           fileBlockId=1,
           fileSize=100
           )
       fList2=[f2]

       ecParent1 = DbsEventCollection(
         collectionName="myLFN1",
         numberOfEvents=1,
         collectionIndex=1,
         status="NEW",
         datasetPathName = self.datasetPath,
         fileList=fList1)
 
       ecChild1 = DbsEventCollection(
         collectionName="myLFN2",
         numberOfEvents=2,
         parentageType="Hit",
         collectionIndex=2,
         parent=ecParent1,
         status="NEW",
         datasetPathName = self.datasetPath,
         fileList=fList1)

       ecGrandChild1 = DbsEventCollection(
         collectionName="myLFN3",
         numberOfEvents=3,
         parentageType="Sim",
         collectionIndex=3,
         parent=ecChild1,
         status="NEW",
         datasetPathName = self.datasetPath,
         fileList=fList1)


       """
       ecParent2 = DbsEventCollection(
         collectionName="myLFN4",
         numberOfEvents=4,
         collectionIndex=4,
         status="NEW",
         datasetPathName = self.datasetPath,
         fileList=fList2)

       ecChild2 = DbsEventCollection(
         collectionName="myLFN5",
         numberOfEvents=5,
         parentageType="Hit",
         collectionIndex=5,
         parent=ecParent2,
         status="NEW",
         datasetPathName = self.datasetPath,
         fileList=fList2)

       ecGrandChild2 = DbsEventCollection(
         collectionName="myLFN6",
         numberOfEvents=6,
         parentageType="Sim",
         collectionIndex=6,
         parent=ecChild2,
         status="NEW",
         datasetPathName = self.datasetPath,
         fileList=fList2)
       """
       print "Inserting event collections for: %s" % self.datasetPath
       #self.api.insertEventCollections([ecGrandChild1,ecGrandChild2])
       self.api.insertEventCollections([ecGrandChild1])
       #self.api.insertEventCollections([ecChild1])

    except dbsException.DbsException, ex:
       print ex
       return 1

    return 0

