import dbsException
import dbsApplication
import dbsProcessingPath
import dbsProcessedDataset
import dbsFile
import dbsEventCollection
import testCaseInterface

class testCreateEventCollection(testCaseInterface.testCaseInterface) : 

  def __init__(self):

    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.createEventCollection)
    self.addTestCase(self.createEvCollFilesShouldHaveSameBlockID)
    self.addTestCase(self.createEvCollNoFileNoInsertion)

    datasetPath = "/ThisIsATestDataset/Digi/ThisIsATestProcDataset"
    app = dbsApplication.DbsApplication(
     family="CMSAppFam",
     executable="cmsRun",
     version="CMSSW_XYZ",
     parameterSet="pSetDummy")

    processingPath = dbsProcessingPath.DbsProcessingPath(
      dataTier="Digi",
      application=app)

    self.dataset = dbsProcessedDataset.DbsProcessedDataset(
      primaryDatasetName="ThisIsATestDataset",
      isDatasetOpen="y",
      datasetName="ThisIsATestProcDataset",
      processingPath=processingPath)


  def createEventCollection(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEventCollection: Creates Event Collection with 02 files")
    print "Now executing ", funcName

    try:
       # Test for inserting event collections.
       f1 = dbsFile.DbsFile(logicalFileName="myFileF10",
           fileStatus = "file dummy status",
           guid = "7C8A55-DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileBlockId=1,
           fileSize=100
           )
       f2 = dbsFile.DbsFile(logicalFileName="myFileF12",
           fileStatus = "file dummy status",
           guid = "7C8A55DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileBlockId=1,
           fileSize=100
           )
       fList=dbsFile.DbsFileList([f1, f2])
       #fList.append(f2)

       ec = dbsEventCollection.DbsEventCollection(
         collectionName="myLFN",
         numberOfEvents=123,
         collectionIndex=100,
         isPrimary="y",
         fileList=fList)
       ecList = dbsEventCollection.DbsEventCollectionList([ec])
       print "Inserting event collections for: %s" % self.dataset.getDatasetName()
       self.api.insertEventCollections(self.dataset, ecList)


    except dbsException.DbsException, ex:
       return 1
   
    return 0

  def createEvCollFilesShouldHaveSameBlockID(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEvCollFilesShouldHaveSameBlockID : All files in EvColl \n \
                                                           Should have Same Block ID, Else they don't get inserted")
    print "Now executing ", funcName

    try:
       #TEST   All files in same EvColl should have same blockId
       file1 = dbsFile.DbsFile(logicalFileName="myFile5",
           fileStatus = "file dummy status",
           guid = "7C8A55-DE62-D811-892C-00E081250436",
           checkSum="BA7C8A55-DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileBlockId=11,
           fileSize=100
           )
       file2 = dbsFile.DbsFile(logicalFileName="myFile6",
           fileStatus = "file dummy status",
           guid = "7C8A55-DE62-D811-892C-00E081250436a",
           checkSum="BA7C8A55-DE62-D811-892C-00E081250a436",
           fileType="EVDZip",
           fileBlockId=10,
           fileSize=100
           )
       fileListDifferentBlockIds=dbsFile.DbsFileList([file1, file2])

       ecFileDifferentBlockIds = dbsEventCollection.DbsEventCollection(
         collectionName="myLFN",
         numberOfEvents=123456,
         collectionIndex=1000,
         isPrimary="y",
         fileList=fileListDifferentBlockIds)

       ecListFileDifferentBlockIds = dbsEventCollection.DbsEventCollectionList([ecFileDifferentBlockIds])
       self.api.insertEventCollections(self.dataset, ecListFileDifferentBlockIds)

    except dbsException.DbsException, ex:
       # Return Success, Negative testcase
       return 0

    return 1

  def createEvCollNoFileNoInsertion(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEvCollNoFileNoInsertion: Cannot insert EvColl without files")
    print "Now executing ", funcName

    try:

       #TEST If No FileList is provided EventCollection shouldn't be inserted
       ecNoFileTest = dbsEventCollection.DbsEventCollection(
         collectionName="myLFN",
         numberOfEvents=123,
         collectionIndex=100,
         isPrimary="y",
         fileList=[])

       ecListNoFileTest = dbsEventCollection.DbsEventCollectionList([ecNoFileTest])

       self.api.insertEventCollections(self.dataset, ecListNoFileTest)
    except dbsException.DbsException, ex:
       # Return Success. negative testcase
       return 0

    return 1

