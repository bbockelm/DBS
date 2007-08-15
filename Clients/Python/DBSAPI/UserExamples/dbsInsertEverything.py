#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
# Unit tests for the DBS CGI implementation.

import sys
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsAlgorithm import DbsAlgorithm
from DBSAPI.dbsFileBlock import DbsFileBlock
from DBSAPI.dbsRun import DbsRun
from DBSAPI.dbsFile import DbsFile
from DBSAPI.dbsLumiSection import DbsLumiSection
from DBSAPI.dbsQueryableParameterSet import DbsQueryableParameterSet
from DBSAPI.dbsPrimaryDataset import DbsPrimaryDataset
from DBSAPI.dbsProcessedDataset import DbsProcessedDataset
from DBSAPI.dbsOptions import DbsOptionParser

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)


primary = DbsPrimaryDataset (Name = "test_primary_001", Type="test")

algo = DbsAlgorithm (
         ExecutableName="TestExe01",
         ApplicationVersion= "TestVersion01",
         ApplicationFamily="AppFamily01",
         ParameterSetID=DbsQueryableParameterSet(
           Hash="001234565798685",
           )
         )

proc = DbsProcessedDataset (
        PrimaryDataset=primary, 
        Name="TestProcessedDS001", 
        PhysicsGroup="BPositive",
        Status="Valid",
        TierList=['SIM', 'GEN'],
        AlgoList=[algo],
        )

procChild = DbsProcessedDataset (
        PrimaryDataset=primary, 
        Name="TestProcessedDS002", 
        PhysicsGroup="BPositive",
        Status="Valid",
        TierList=['SIM', 'GEN'],
        AlgoList=[algo],
	ParentList=['/test_primary_001/TestProcessedDS001/GEN-SIM']
        )


run = DbsRun (
         RunNumber=1,
         NumberOfEvents= 100,
         NumberOfLumiSections= 20,
         TotalLuminosity= 2222,
         StoreNumber= 123,
         StartOfRun= 12345,
         EndOfRun= 45678,
         )

lumi1 = DbsLumiSection (
         LumiSectionNumber=1222,
         StartEventNumber=100,
         EndEventNumber=200,
         LumiStartTime=1234,
         LumiEndTime=1234,
         RunNumber=1,
         )
lumi2 = DbsLumiSection (
         LumiSectionNumber=1333,
         StartEventNumber=100,
         EndEventNumber=200,
         LumiStartTime=1234,
         LumiEndTime=1234,
         RunNumber=1,
         )


myfile1= DbsFile (
        Checksum= '999',
        LogicalFileName= 'NEW_TEST0001',
        #QueryableMetadata= 'This is a test file',
        NumberOfEvents= 10000,
        FileSize= 12340,
        Status= 'VALID',
	ValidationStatus = 'VALID',
        FileType= 'EDM',
        Dataset= proc,
        #Block= isDictType,
        AlgoList = [algo],
        LumiList= [lumi1, lumi2],
        TierList= ['SIM', 'GEN'],
        #ParentList = ['NEW_TEST0003']  
         )

myfile2= DbsFile (
        Checksum= '000',
        LogicalFileName= 'NEW_TEST0002',
        #QueryableMetadata= 'This is a test file',
        NumberOfEvents= 10000,
        FileSize= 12340,
        Status= 'VALID',
	ValidationStatus = 'VALID',
        FileType= 'EDM',
        Dataset= proc,
        #Block= isDictType,
        LumiList= [lumi1, lumi2],
        TierList= ['SIM', 'GEN'],
        AlgoList = [algo],
        BranchList=['testbranch01', 'testbranch02'],
        #ParentList = ['NEW_TEST0004']  
         )
        

myfile3= DbsFile (
        Checksum= '999',
        LogicalFileName= 'NEW_TEST0004',
        #QueryableMetadata= 'This is a test file',
        NumberOfEvents= 10000,
        FileSize= 12340,
        Status= 'VALID',
        ValidationStatus = 'VALID',
        FileType= 'EDM',
        Dataset= proc,
        #Block= isDictType,
        AlgoList = [algo],
        LumiList= [lumi1, lumi2],
        TierList= ['SIM', 'GEN'],
        ParentList = ['NEW_TEST0001']  
         )

myfile4= DbsFile (
        Checksum= '000',
        LogicalFileName= 'NEW_TEST0005',
        #QueryableMetadata= 'This is a test file',
        NumberOfEvents= 10000,
        FileSize= 12340,
        Status= 'VALID',
        ValidationStatus = 'VALID',
        FileType= 'EDM',
        Dataset= proc,
        #Block= isDictType,
        LumiList= [lumi1, lumi2],
        TierList= ['SIM', 'GEN'],
        AlgoList = [algo],
        BranchList=['testbranch01', 'testbranch02'],
        ParentList = ['NEW_TEST0002']  
         )


                   
block = DbsFileBlock (
         StorageElement=['test1', 'test3'],
	 Name="/test_primary_001/TestProcessedDS001/GEN-SIM#12345"
         )

block2 = DbsFileBlock (
         StorageElement=['test1', 'test3'],
	 Name="/test_primary_001/TestProcessedDS002/GEN-SIM#44444"
         )

try:
	
    print "\n\nInserting primary %s" % primary
    print api.insertPrimaryDataset (primary)

    print "\n\nInserting Algorithm %s" % algo	
    print api.insertAlgorithm (algo)
    
    print "\n\nInserting Run %s" % run	
    print api.insertRun (run)
    
    print "\n\nInserting Processed %s" % proc	
    print api.insertProcessedDataset (proc)

    print "\n\nInserting Block %s" % block	
    print api.insertBlock (proc, block)
    
    print "\n\nInserting Files %s" % [myfile1, myfile2]	
    print api.insertFiles (proc, [myfile1, myfile2], block)

    print "\n\nInserting Child Processed %s" % procChild
    print api.insertProcessedDataset (procChild)

    print "\n\nInserting Block2 %s" % block2	
    print api.insertBlock (procChild, block2)
 
    print "\n\nInserting Files %s" % [myfile1, myfile2]	
    print api.insertFiles (procChild, [myfile3, myfile4], block2)


except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()


print "Done"
