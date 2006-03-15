import dbsException
import dbsApplication
import dbsProcessingPath
import dbsProcessedDataset
import testCaseInterface

from dbsClientDatastructures import *
# Unit testing.


class testCreateProcessedDS(testCaseInterface.testCaseInterface) : 

  def __init__(self):
    testCaseInterface.testCaseInterface.__init__(self)
    self.addTestCase(self.createProcessedDS)
    #self.addTestCase(self.createProcessedDSWithParent)
    #self.addTestCase(self.createProcessedDSBadReInsertion)
    #self.addTestCase(self.createProcessedDSWithSameParent)

  def createProcessedDS(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createProcessedDS")
    print "Now executing ", funcName

    try:
       app = DbsApplication(parameterSet="Dummy", 
             family="CMSAppFam", 
             version="CMSSW_XYZ", 
             executable="cmsRun")
       proPath =  DbsProcessingPath(dataTier="Digi", 
             application=app)
       dataset = DbsProcessedDataset(isDatasetOpen=False,
             processingPath=proPath,
             primaryDatasetName="ThisIsATestDataset", 
             processedDatasetName="ThisIsATestProcDataset")

       processedDatasetId = self.api.createProcessedDataset(dataset)
       print "Got processed dataset id: %s" % processedDatasetId    

    except dbsException.DbsException, ex:
       print ex
       return 1
   
    return 0


  def createProcessedDSBadReInsertion(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createProcessedDS")
    print "Now executing ", funcName

    try:
       app = DbsApplication(parameterSet="Dummy", 
             family="CMSAppFam", 
             version="CMSSW_XYZ")
       proPath =  DbsProcessingPath(dataTier="Digi", 
             application=app)
       dataset = DbsProcessedDataset(isDatasetOpen=False,
             processingPath=proPath,
             primaryDatasetName="ThisIsATestDataset", 
             processedDatasetName="ThisIsATestProcDataset")

       processedDatasetId = self.api.createProcessedDataset(dataset)
       print "Got processed dataset id: %s" % processedDatasetId

    except dbsException.DbsException, ex:
       print ex
       return 0

    return 1




  def createProcessedDSWithParent(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createProcessedDSWithParent")
    print "Now executing ", funcName

    try:
       app = DbsApplication(parameterSet="Dummy", 
             family="CMSAppFam1", 
             version="CMSSW_XYZ1",
             executable="cmsRun1")

       childProcessingPath =  DbsProcessingPath(dataTier="Hit",
             application=app,
             parent="/ThisIsATestDataset/Digi/ThisIsATestProcDataset")
       dataset = DbsProcessedDataset(isDatasetOpen=False,
             processingPath=childProcessingPath,
             primaryDatasetName="ThisIsATestDataset", 
             processedDatasetName="ThisIsATestProcDataset")

       processedDatasetId = self.api.createProcessedDataset(dataset)
       print "Got processed dataset id: %s" % processedDatasetId
    except dbsException.DbsException, ex:
       print ex
       return 1
    return 0



"""
  def createProcessedDSWithSameParent(self):
    funcName = "%s.%s" % (self.__class__.__name__, "createProcessedDSWithSameParent")
    print "Now executing ", funcName

    try:
       app = DbsApplication(parameterSet="Dummy", 
             family="CMSAppFam1", 
             version="CMSSW_XYZ1",
             executable="cmsRun1")
       childProcessingPath =  DbsProcessingPath(dataTier="Hit",
             application=app,
             parentPath="/ThisIsATestDataset/Digi/ThisIsATestProcDataset")
       dataset = DbsProcessedDataset(isDatasetOpen=False,
             processingPath=childProcessingPath,
             primaryDatasetName="ThisIsATestDataset", 
             processedDatasetName="ThisIsATestProcDataset")


       processedDatasetId = self.api.createProcessedDataset(dataset)
       print "Got processed dataset id: %s" % processedDatasetId

    except dbsException.DbsException, ex:
       return 0

    return 1
"""
