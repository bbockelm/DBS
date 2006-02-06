import dbsException
import dbsApplication
import dbsProcessingPath
import dbsProcessedDataset
import dbsFile
import dbsEventCollection
import testCaseInterface

class testCreateEventCollection(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    funcName = "%s.%s" % (self.__class__.__name__, "__init__()")
    print "Now executing ", funcName
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.createEventCollection)
    #self.addTestCase(self.createEvCollFilesShouldHaveSameBlockID)
    #self.addTestCase(self.createEvCollNoFileNoInsertion)

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
    funcName = "%s.%s" % (self.__class__.__name__, "createEventCollection")
    print "Now executing ", funcName

    try:
       # Test for inserting event collections.
       f1 = dbsFile.DbsFile(logicalFileName="myFileF1",
           fileStatus = "file dummy status",
           guid = "7C8A55-DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileBlockId=9,
           fileSize=100
           )
       f2 = dbsFile.DbsFile(logicalFileName="myFileF2",
           fileStatus = "file dummy status",
           guid = "7C8A55DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileBlockId=9,
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
    funcName = "%s.%s" % (self.__class__.__name__, "createEvCollFilesShouldHaveSameBlockID")
    print "Now executing ", funcName

    try:
       #TEST   All files in same EvColl should have same blockId
       file1 = dbsFile.DbsFile(logicalFileName="myFile3",
           fileStatus = "file dummy status",
           guid = "7C8A55-DE62-D811-892C-00E081250436",
           checkSum="BA7C8A55-DE62-D811-892C-00E081250436",
           fileType="EVDZip",
           fileBlockId=10,
           fileSize=100
           )
       file2 = dbsFile.DbsFile(logicalFileName="myFile4",
           fileStatus = "file dummy status",
           guid = "7C8A55-DE62-D811-892C-00E081250436a",
           checkSum="BA7C8A55-DE62-D811-892C-00E081250a436",
           fileType="EVDZip",
           fileBlockId=10,
           fileSize=100
           )
       #fileListDifferentBlockIds=dbsFile.DbsFileList([file1, file2])
       fileListDifferentBlockIds=dbsFile.DbsFileList([file1])

       ecFileDifferentBlockIds = dbsEventCollection.DbsEventCollection(
         collectionName="myLFN",
         numberOfEvents=123456,
         collectionIndex=1000,
         isPrimary="y",
         fileList=fileListDifferentBlockIds)

       ecListFileDifferentBlockIds = dbsEventCollection.DbsEventCollectionList([ecFileDifferentBlockIds])
       self.api.insertEventCollections(self.dataset, ecListFileDifferentBlockIds)

    except dbsException.DbsException, ex:
       return 1

    return 0

  def createEvCollNoFileNoInsertion(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createEvCollNoFileNoInsertion")
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
       return 1

    return 0

