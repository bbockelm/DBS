#!/usr/bin/env python
#
# API Unit tests for the DBS JavaServer.

import sys
import os
import time
from dbsApi import DbsApi
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsAlgorithm import DbsAlgorithm
from dbsProcessedDataset import DbsProcessedDataset
from dbsQueryableParameterSet import DbsQueryableParameterSet
from dbsFileBlock import DbsFileBlock
from dbsRun import DbsRun
from dbsFile import DbsFile
from dbsLumiSection import DbsLumiSection
from dbsOptions import DbsOptionParser
from dbsUnitTestApi import DbsUnitTestApi
import random
#import pdb

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()

#pdb.set_trace()

if len(args) < 3:
   print "You must provide number of files <inserted> in how many <iterations> and <logfile-pretext>" 
   print "python dbsStressTest.py <number-of-files> <iterations> <logfile-pretext>"
   sys.exit(1)
else:
   totalfiles=int(args[0]) 
   iters=int(args[1])
   logext=str(args[2])

api = DbsApi(opts.__dict__)
mytime = str(time.time())

maxDS = totalfiles/iters
maxFiles = totalfiles

filename="bulkDataResult."+logext
#print "FILENAME: "+filename
f = open(filename, "w")
fileList = []
for i in range(maxDS):
	#mytime = str(time.time())
        mytime=os.popen('uuidgen').readline().strip()
        #mytime = str(random.random())
	#Insert Primary
	apiObj = DbsUnitTestApi(api.insertPrimaryDataset, f)
	primary = 'TestPrimary' + mytime
	pri1 = DbsPrimaryDataset (Name = primary)
	apiObj.run(pri1, excep = False)

	#Insert Algorithm
	apiObj = DbsUnitTestApi(api.insertAlgorithm,f)
	algo1 = DbsAlgorithm (ExecutableName="TestExe01", 
		ApplicationVersion= "TestVersion01" + mytime, 
		ApplicationFamily="AppFamily01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstParam01", 
							Version="V001", 
							Type="test", 
							Annotation="This is test", 
							Content="int a= {}, b={c=1, d=33}, f={}, x, y, x"
			                              )
	)
	apiObj.run(algo1, excep = False)
	
	#Insert Tier
	apiObj = DbsUnitTestApi(api.insertTier, f)
	tierName1 = "HIT" + mytime
	tierName2 = "SIM" + mytime
	apiObj.run(tierName1, excep = False)
	apiObj.run(tierName2, excep = False)

	tierList = [tierName1, tierName2]
	
	#Insert Processed Datatset
	apiObj = DbsUnitTestApi(api.insertProcessedDataset,f)
	proc1 = DbsProcessedDataset(PrimaryDataset=pri1,
			Name="TestProcessed" + mytime,
			PhysicsGroup="BPositive",
			Status="VALID",
			TierList=tierList,
			AlgoList=[algo1])
	apiObj.run(proc1, excep = False)

	apiObj = DbsUnitTestApi(api.insertBlock, f)
	path = "/" + str(proc1['PrimaryDataset']['Name']) + "/" + str(proc1['TierList'][0]) + "/" + str(proc1['Name'])

	#Insert Block
	block1 = DbsFileBlock (Name = "/" + mytime + "this/isatestblock#016712", Path = path)
	apiObj.run(path, "/" + mytime + "this/isatestblock#016712" , excep = False)

	#Insert Run
	apiObj = DbsUnitTestApi(api.insertRun, f)
	runNumber1 = 101 + int(time.time()%1000)
	run1 = DbsRun (RunNumber=runNumber1,
			NumberOfEvents= 100,
			NumberOfLumiSections= 20,
			TotalLuminosity= 2222,
			StoreNumber= 123,
			StartOfRun= 'now',
			EndOfRun= 'never',
	)
	apiObj.run(run1, excep = False)

	#Insert Lumi Section
	apiObj = DbsUnitTestApi(api.insertLumiSection, f)
	lumiNumber1 = 111 + int(time.time()%100)
	lumiNumber2 = 112 + int(time.time()%100)

	lumi1 = DbsLumiSection (LumiSectionNumber=lumiNumber1,
			StartEventNumber=100,
			EndEventNumber=200,
			LumiStartTime='notime',
			LumiEndTime='neverending',
			RunNumber=runNumber1,
			)

	apiObj.run(lumi1, excep = False)

	lumi2 = DbsLumiSection (LumiSectionNumber=lumiNumber2,
			StartEventNumber=100,
			EndEventNumber=200,
			RunNumber=runNumber1)
	apiObj.run(lumi2, excep = False)

	#Insert File
	for j in range(maxFiles/2):
		#mytime = str(time.time())
                #mytime = os.popen('uuidgen').readline()
		apiObj = DbsUnitTestApi(api.insertFiles, f)
		lfn1 = os.popen('uuidgen').readline().strip()+str(random.random())
		lfn2 = os.popen('uuidgen').readline().strip()+str(random.random())

		#lfn1 = '1111-0909-9767-8764' + mytime
		#lfn2 = '1111-0909-9767-876411' + mytime
		file1= DbsFile (
			Checksum= '999',
			LogicalFileName= lfn1,
			#QueryableMetadata= 'This is a test file',
			NumberOfEvents= 10000,
			FileSize= 12340,
			Status= 'VALID',
			FileType= 'EVD',
			LumiList= [lumi1, lumi2],
			TierList= tierList,
			)
	
		file2= DbsFile (
			Checksum= '999',
			LogicalFileName= lfn2,
			#QueryableMetadata= 'This is a test file',
			NumberOfEvents= 10000,
			FileSize= 12340,
			Status= 'VALID',
			FileType= 'EVD',
			LumiList= [lumi1, lumi2],
			TierList= tierList,
			)
		fileList.append(file1)
	        fileList.append(file2)
                       
	apiObj.run(proc1 ,fileList, block1,  excep = False)

f.close()
