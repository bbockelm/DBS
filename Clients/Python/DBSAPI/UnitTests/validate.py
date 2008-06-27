import random
import os
import unittest
from DBSAPI.dbsApi import DbsApi
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
 

def genRandom():
	return  os.popen('uuidgen').readline().strip()

ran = genRandom()
primName = 'test_Primary_' + ran
primType = 'test'

algoExe1 = 'test_Exe1_' + ran
algoVer1 = 'test_Ver1_' + ran
algoFam1 = 'test_Fam1_' + ran
psHash1 = 'test_Hash1_' + ran
psName1 = 'test_Pname1_' + ran
psVer1 = 'test_Pver1_' + ran
psType1 = 'test_Ptype1_' + ran
psAnno1 = 'test_Panno1_' + ran
psCon1 = 'test_Con1_' + ran

algoExe2 = 'test_Exe2_' + ran
algoVer2 = 'test_Ver2_' + ran
algoFam2 = 'test_Fam2_' + ran
psHash2 = 'test_Hash2_' + ran
psName2 = 'test_Pname2_' + ran
psVer2 = 'test_Pver2_' + ran
psType2 = 'test_Ptype2_' + ran
psAnno2 = 'test_Panno2_' + ran
psCon2 = 'test_Con2_' + ran

algoExeM = 'test_ExeM_' + ran
algoVerM = 'test_VerM_' + ran
algoFamM = 'test_FamM_' + ran
psHashM = 'test_HashM_' + ran
psNameM = 'test_PnameM_' + ran
psVerM = 'test_PverM_' + ran
psTypeM = 'test_PtypeM_' + ran
psAnnoM = 'test_PannoM_' + ran
psConM = 'test_ConM_' + ran



runNumber = random.choice(range(10000))
runNumEvents = 1200
numLumi = 1234
totalLumi = 3456
storeNum = 8907
startRun = 23456
endRun = 12345

invalidStatus = 'INVALID'
validStatus = 'VALID'
procName1 = 'test_processed_1_' + ran
procName2 = 'test_processed_2_' + ran
procNameM = 'test_processed_M_' + ran
phyGrp = 'BPositive'
procStatus = validStatus
tier1 = 'GEN'
tier2 = 'SIM'
era = 'test_Era_' + ran
tag = 'test_Tag_' + ran
path1 = '/' + primName + '/' + procName1 + '/' + tier1 + '-' + tier2
path2 = '/' + primName + '/' + procName2 + '/' + tier1 + '-' + tier2
pathM = '/' + primName + '/' + procNameM + '/' + tier1 + '-' + tier2

lsNumber1 = random.choice(range(10000))
stEvNum1 = 1234
endEvNum1 = 3234
stLumiTime1 = 67890
endLumiTime1 = 98990

lsNumber2 = random.choice(range(10000))
stEvNum2 = 2234
endEvNum2 = 3239
stLumiTime2 = 47890
endLumiTime2 = 88990

se1 = 'test_se1_' + ran
se2 = 'test_se2_' + ran
seM = 'test_seM_' + ran
blockName1 = path1 + '#' + str(random.choice(range(10000)))
blockName2 = path2 + '#' + str(random.choice(range(10000)))
blockNameM = pathM + '#' + str(random.choice(range(10000)))

fileName1 = 'test_file_name_1_' + ran
fileCkecksum1 = 'test_cksum_1_' + ran
fileNumEvents1 = 34567
fileSize1 = 123987
fileStatus1 = validStatus
fileValidStatus1 = validStatus
fileType1 = 'EDM'

fileName2 = 'test_file_name_2_' + ran
fileNumEvents2 = 84567
fileCkecksum2 = 'test_cksum_2_' + ran
fileSize2 = 122657
fileStatus2 = validStatus
fileValidStatus2 = validStatus
fileType2 = 'STREAMER'

fileNameM = 'test_file_name_M_' + ran
fileNumEventsM = 234567
fileCkecksumM = 'test_cksum_M_' + ran
fileSizeM = 56322657
fileStatusM = validStatus
fileValidStatusM = validStatus
fileTypeM = 'STREAMER'


primObj = DbsPrimaryDataset (Name = primName, Type = primType)
algoObj1 = DbsAlgorithm (
		ExecutableName = algoExe1,
		ApplicationVersion = algoVer1,
		ApplicationFamily = algoFam1,
		ParameterSetID=DbsQueryableParameterSet(
			Hash = psHash1,
			Name = psName1,
			Version = psVer1,
			Type = psType1,
			Annotation = psAnno1,
			Content = psCon1
			)
		)
algoObj2 = DbsAlgorithm (
		ExecutableName = algoExe2,
		ApplicationVersion = algoVer2,
		ApplicationFamily = algoFam2,
		ParameterSetID=DbsQueryableParameterSet(
			Hash = psHash2,
			Name = psName2,
			Version = psVer2,
			Type = psType2,
			Annotation = psAnno2,
			Content = psCon2
			)
		)

algoObjM = DbsAlgorithm (
		ExecutableName = algoExeM,
		ApplicationVersion = algoVerM,
		ApplicationFamily = algoFamM,
		ParameterSetID=DbsQueryableParameterSet(
			Hash = psHashM,
			Name = psNameM,
			Version = psVerM,
			Type = psTypeM,
			Annotation = psAnnoM,
			Content = psConM
			)
		)

runObj = DbsRun (
		RunNumber = runNumber,
		NumberOfEvents = runNumEvents,
		NumberOfLumiSections = numLumi,
		TotalLuminosity = totalLumi,
		StoreNumber = storeNum,
		StartOfRun = startRun,
		EndOfRun = endRun,
		)

procObj1 = DbsProcessedDataset (
		PrimaryDataset = primObj, 
		Name = procName1, 
		AcquisitionEra = era,
		GlobalTag = tag,
		PhysicsGroup = phyGrp,
		Status = procStatus,
		TierList = [tier1, tier2],
		AlgoList = [algoObj1, algoObj2],
		)

procObj2 = DbsProcessedDataset (
		PrimaryDataset = primObj, 
		Name = procName2, 
		AcquisitionEra = era,
		GlobalTag = tag,
		PhysicsGroup = phyGrp,
		Status = procStatus,
		TierList = [tier1, tier2],
		AlgoList = [algoObj1, algoObj2],
		ParentList = [path1],
		RunsList = [runNumber]
		)

procObjM = DbsProcessedDataset (
		PrimaryDataset = primObj, 
		Name = procNameM, 
		AcquisitionEra = era,
		GlobalTag = tag,
		PhysicsGroup = phyGrp,
		Status = procStatus,
		TierList = [tier1, tier2],
		AlgoList = [algoObjM],
		RunsList = [runNumber]
		)

lumiObj1 = DbsLumiSection (
		LumiSectionNumber = lsNumber1,
		StartEventNumber = stEvNum1,
		EndEventNumber = endEvNum1,
		LumiStartTime = stLumiTime1,
		LumiEndTime = endLumiTime1,
		RunNumber = runNumber,
		)

lumiObj2 = DbsLumiSection (
		LumiSectionNumber = lsNumber2,
		StartEventNumber = stEvNum2,
		EndEventNumber = endEvNum2,
		LumiStartTime = stLumiTime2,
		LumiEndTime = endLumiTime2,
		RunNumber = runNumber,
		)


blockObj1 = DbsFileBlock (
		Name = blockName1
		)

blockObj2 = DbsFileBlock (
		Name = blockName2
		)

blockObjM = DbsFileBlock (
		Name = blockNameM
		)

fileObj1 = DbsFile (
		Checksum = fileCkecksum1,
		LogicalFileName = fileName1,
		NumberOfEvents = fileNumEvents1,
		FileSize = fileSize1,
		Status = fileStatus1,
		ValidationStatus = fileValidStatus1,
		FileType = fileType1,
		Dataset = procObj1,
		AlgoList = [algoObj1],
		LumiList = [lumiObj1],
		TierList = [tier1, tier2]
		)

fileObj2 = DbsFile (
		Checksum = fileCkecksum2,
		LogicalFileName = fileName2,
		NumberOfEvents = fileNumEvents2,
		FileSize = fileSize2,
		Status = fileStatus2,
		ValidationStatus = fileValidStatus2,
		FileType = fileType2,
		Dataset = procObj2,
		AlgoList = [algoObj2],
		LumiList = [lumiObj2],
		TierList = [tier1, tier2],
		ParentList = [fileName1]
		)

fileObjM = DbsFile (
		Checksum = fileCkecksumM,
		LogicalFileName = fileNameM,
		NumberOfEvents = fileNumEventsM,
		FileSize = fileSizeM,
		Status = fileStatusM,
		ValidationStatus = fileValidStatusM,
		FileType = fileTypeM,
		Dataset = procObjM,
		AlgoList = [algoObjM],
		TierList = [tier1, tier2],
		Block = blockObjM
		)


def assertAlgo(test, algoIn1, algoIn2):
	test.assertEqual(algoIn1['ExecutableName'], algoIn2['ExecutableName'])
	test.assertEqual(algoIn1['ApplicationVersion'], algoIn2['ApplicationVersion'])
	test.assertEqual(algoIn1['ApplicationFamily'], algoIn2['ApplicationFamily'])
	test.assertEqual(algoIn1['ParameterSetID']['Hash'], algoIn2['ParameterSetID']['Hash'])

def assertAlgoPS(test, algoIn1, algoIn2):
	assertAlgo(test, algoIn1, algoIn2)
	test.assertEqual(algoIn1['ParameterSetID']['Hash'], algoIn2['ParameterSetID']['Hash'])
	test.assertEqual(algoIn1['ParameterSetID']['Name'], algoIn2['ParameterSetID']['Name'])
	test.assertEqual(algoIn1['ParameterSetID']['Version'], algoIn2['ParameterSetID']['Version'])
	test.assertEqual(algoIn1['ParameterSetID']['Type'], algoIn2['ParameterSetID']['Type'])
	test.assertEqual(algoIn1['ParameterSetID']['Annotation'], algoIn2['ParameterSetID']['Annotation'])
	test.assertEqual(algoIn1['ParameterSetID']['Content'], algoIn2['ParameterSetID']['Content'])

def assertProc(test, procIn1, procIn2):
	test.assertEqual(procIn1['Name'], procIn2['Name'])
	test.assertEqual(procIn1['PrimaryDataset']['Name'], procIn2['PrimaryDataset']['Name'])
	test.assertEqual(procIn1['AcquisitionEra'], procIn2['AcquisitionEra'])
	test.assertEqual(procIn1['GlobalTag'], procIn2['GlobalTag'])
	test.assertEqual(procIn1['PhysicsGroup'], procIn2['PhysicsGroup'])
	test.assertEqual(procIn1['Status'], procIn2['Status'])


class Test1(unittest.TestCase):
	def testPrimary(self):
		print 'testPrimary'
		api.insertPrimaryDataset (primObj)
		primaryList = api.listPrimaryDatasets(primName)
		self.assertEqual(len(primaryList), 1)
		for primaryInDBS in primaryList:
			self.assertEqual(primName, primaryInDBS['Name'])
			self.assertEqual(primType, primaryInDBS['Type'])

class Test2(unittest.TestCase):
	def testAlgorithm(self):
		print 'testAlgorithm'
		api.insertAlgorithm (algoObj1)
		algoList = api.listAlgorithms(patternVer = algoVer1, patternFam = algoFam1, patternExe = algoExe1, patternPS = psHash1)
		self.assertEqual(len(algoList), 1)
		for algoInDBS in algoList:
			assertAlgoPS(self, algoObj1, algoInDBS)


class Test3(unittest.TestCase):
	def testProcessed(self):
		print 'testProcessed'
		api.insertPrimaryDataset (primObj)
		api.insertAlgorithm (algoObj1)
		api.insertAlgorithm (algoObj2)

	
		api.insertRun (runObj)
		api.insertProcessedDataset (procObj1)
		api.insertProcessedDataset (procObj2)
		procList = api.listProcessedDatasets(patternPrim = primName, patternProc = procName2)
		self.assertEqual(len(procList), 1)
		for processedInDBS in procList:
			#print processedInDBS
			assertProc(self, procObj2, processedInDBS)
			self.assertEqual(len(processedInDBS['AlgoList']), 2)
			for algoInDBS in processedInDBS['AlgoList']:
				if(algoInDBS['ExecutableName'] == algoExe1):
					assertAlgo(self, algoObj1, algoInDBS)
				else:
					if (algoInDBS['ExecutableName'] == algoExe2):
						assertAlgo(self, algoObj2, algoInDBS)
					else:
						print 'algo %s is not expected', algoInDBS
						self.assertEqual(1, 2)


			self.assertEqual(len(processedInDBS['TierList']), 2)
			for tierInDBS in processedInDBS['TierList']:
				if(tierInDBS != tier1 and tierInDBS != tier2):
					print 'tier %s is not expected', tierInDBS
					self.assertEqual(1, 2)
					
			
			parentList = api.listDatasetParents(procObj2)
			#FIXME
			#self.assertEqual(len(parentList), 1)
			for parentProcInDBS in parentList:
				self.assertEqual(primName, parentProcInDBS['PrimaryDataset']['Name'])
				self.assertEqual(procName1, parentProcInDBS['Name'])
				


class Test4(unittest.TestCase):
	def testBlock(self):
		print 'testBlock'
		api.insertBlock (procObj1, blockObj1, [se1, se2])
		api.insertBlock (procObj2, blockObj2, [se2])
		blockList = api.listBlocks(procObj1)
		self.assertEqual(len(blockList), 1)
		for blockInDBS in api.listBlocks(procObj1):
			#print blockInDBS
			self.assertEqual(blockName1, blockInDBS['Name'])
			self.assertEqual(path1, blockInDBS['Path'])
			self.assertEqual(len(blockInDBS['StorageElementList']), 2)
			for seInDBS in blockInDBS['StorageElementList']:
				if(seInDBS['Name'] != se1 and seInDBS['Name'] != se2):
					print 'storage element %s is not expected', seInDBS
					self.assertEqual(1, 2)

				


class Test5(unittest.TestCase):
	def testRun(self):
		print 'testRun'
		runList = api.listRuns(procObj2)
		self.assertEqual(len(runList), 1)
		for runInDBS in runList:
			self.assertEqual(runNumber, runInDBS['RunNumber'])
			self.assertEqual(runNumEvents, runInDBS['NumberOfEvents'])
			self.assertEqual(numLumi, runInDBS['NumberOfLumiSections'])
			self.assertEqual(totalLumi, runInDBS['TotalLuminosity'])
			self.assertEqual(storeNum, runInDBS['StoreNumber'])
			self.assertEqual(startRun, runInDBS['StartOfRun'])
			self.assertEqual(endRun, runInDBS['EndOfRun'])

class Test6(unittest.TestCase):
	def testFile(self):
		print 'testFile'
		api.insertFiles(procObj1, [fileObj1], blockObj1)
		api.insertFiles(procObj2, [fileObj2], blockObj2)
		fileList = api.listFiles(path = path2, retriveList = ['all'])
		self.assertEqual(len(fileList), 1)
		for fileInDBS in fileList:
			self.assertEqual(fileCkecksum2, fileInDBS['Checksum'])
			self.assertEqual(fileName2, fileInDBS['LogicalFileName'])
			self.assertEqual(fileNumEvents2, fileInDBS['NumberOfEvents'])
			self.assertEqual(fileSize2, fileInDBS['FileSize'])
			self.assertEqual(fileStatus2, fileInDBS['Status'])
			#self.assertEqual(fileValidStatus2, fileInDBS['ValidationStatus'])
			self.assertEqual(fileType2, fileInDBS['FileType'])
			algoList = fileInDBS['AlgoList']
			self.assertEqual(len(algoList), 1)
			for algoInDBS in algoList:
				assertAlgo(self, algoObj2, algoInDBS)

			lumiList = fileInDBS['LumiList']
			self.assertEqual(len(lumiList), 1)
			for lumiInDBS in lumiList:
				self.assertEqual(lsNumber2, lumiInDBS['LumiSectionNumber'])
				self.assertEqual(stEvNum2, lumiInDBS['StartEventNumber'])
				self.assertEqual(endEvNum2, lumiInDBS['EndEventNumber'])
				self.assertEqual(stLumiTime2, lumiInDBS['LumiStartTime'])
				self.assertEqual(endLumiTime2, lumiInDBS['LumiEndTime'])
				self.assertEqual(runNumber, lumiInDBS['RunNumber'])
		
			tierList = fileInDBS['TierList']
			self.assertEqual(len(tierList), 2)
			for tierInDBS in tierList:
				if(tierInDBS != tier1 and tierInDBS != tier2):
					print 'tier %s is not expected', tierInDBS
					self.assertEqual(1, 2)

			parentList = fileInDBS['ParentList']
			self.assertEqual(len(parentList), 1)
			for parentInDBS in parentList:
				self.assertEqual(fileCkecksum1, parentInDBS['Checksum'])
				self.assertEqual(fileName1, parentInDBS['LogicalFileName'])
				self.assertEqual(fileNumEvents1, parentInDBS['NumberOfEvents'])
				self.assertEqual(fileSize1, parentInDBS['FileSize'])
				self.assertEqual(fileStatus1, parentInDBS['Status'])
				#self.assertEqual(fileValidStatus1, parentInDBS['ValidationStatus'])
				self.assertEqual(fileType1, parentInDBS['FileType'])

	def testParentOfProcDS(self):
		print 'testParentOfProcDS'
		parentList = api.listDatasetParents(procObj2)
		self.assertEqual(len(parentList), 1)
		for parentProcInDBS in parentList:
			self.assertEqual(primName, parentProcInDBS['PrimaryDataset']['Name'])
			self.assertEqual(procName1, parentProcInDBS['Name'])


	def testInvalidFile(self):
		print 'testInvalidFile'
		api.updateFileStatus(fileName1, invalidStatus, "No Description")
		fileList = api.listFiles(path = path1, retriveList = ['all'])
		self.assertEqual(len(fileList), 1)
		for fileInDBS in fileList:
			self.assertEqual(invalidStatus, fileInDBS['Status'])

		fileList = api.listFiles(path = path1)
		self.assertEqual(len(fileList), 0)

		api.updateFileStatus(fileName1, validStatus, "No Description")
		fileList = api.listFiles(path = path1)
		self.assertEqual(len(fileList), 1)

class Test7(unittest.TestCase):
	def testMergedProcessed(self):
		print 'testMergedProcessed'
		api.insertMergedDataset (procObj2, procNameM, algoObjM)
		procList = api.listProcessedDatasets(patternPrim = primName, patternProc = procNameM)
		self.assertEqual(len(procList), 1)
		for processedInDBS in procList:
			assertProc(self, procObjM, processedInDBS)
			self.assertEqual(len(processedInDBS['AlgoList']), 3)
			
			for algoInDBS in processedInDBS['AlgoList']:
				if(algoInDBS['ExecutableName'] == algoExe1):
					assertAlgo(self, algoObj1, algoInDBS)
				else:
					if (algoInDBS['ExecutableName'] == algoExe2):
						assertAlgo(self, algoObj2, algoInDBS)
					else:
						if (algoInDBS['ExecutableName'] == algoExeM):
							assertAlgo(self, algoObjM, algoInDBS)
						else:
							print 'algo %s is not expected', algoInDBS
							self.assertEqual(1, 2)


			self.assertEqual(len(processedInDBS['TierList']), 2)
			for tierInDBS in processedInDBS['TierList']:
				if(tierInDBS != tier1 and tierInDBS != tier2):
					print 'tier %s is not expected', tierInDBS
					self.assertEqual(1, 2)
					
				
class Test8(unittest.TestCase):
	def testMergedFile(self):
		print 'testMergedFile'
		api.insertBlock (procObjM, blockObjM, [seM])
		api.insertMergedFile([fileName1, fileName2], fileObjM)
		fileList = api.listFiles(path = pathM, retriveList = ['all'])
		self.assertEqual(len(fileList), 1)
		for fileInDBS in fileList:
			self.assertEqual(fileCkecksumM, fileInDBS['Checksum'])
			self.assertEqual(fileNameM, fileInDBS['LogicalFileName'])
			self.assertEqual(fileNumEventsM, fileInDBS['NumberOfEvents'])
			self.assertEqual(fileSizeM, fileInDBS['FileSize'])
			self.assertEqual(fileStatusM, fileInDBS['Status'])
			#self.assertEqual(fileValidStatusM, fileInDBS['ValidationStatus'])
			self.assertEqual(fileTypeM, fileInDBS['FileType'])
			algoList = fileInDBS['AlgoList']
			self.assertEqual(len(algoList), 3)
			for algoInDBS in algoList:
				if(algoInDBS['ExecutableName'] == algoExe1):
					assertAlgo(self, algoObj1, algoInDBS)
				else:
					if (algoInDBS['ExecutableName'] == algoExe2):
						assertAlgo(self, algoObj2, algoInDBS)
					else:
						if (algoInDBS['ExecutableName'] == algoExeM):
							assertAlgo(self, algoObjM, algoInDBS)


			lumiList = fileInDBS['LumiList']
			self.assertEqual(len(lumiList), 2)
			for lumiInDBS in lumiList:
				if(lumiInDBS['LumiSectionNumber'] == lsNumber1):
					self.assertEqual(lsNumber1, lumiInDBS['LumiSectionNumber'])
					self.assertEqual(stEvNum1, lumiInDBS['StartEventNumber'])
					self.assertEqual(endEvNum1, lumiInDBS['EndEventNumber'])
					self.assertEqual(stLumiTime1, lumiInDBS['LumiStartTime'])
					self.assertEqual(endLumiTime1, lumiInDBS['LumiEndTime'])
					self.assertEqual(runNumber, lumiInDBS['RunNumber'])
				else:
					if(lumiInDBS['LumiSectionNumber'] == lsNumber2):
						self.assertEqual(lsNumber2, lumiInDBS['LumiSectionNumber'])
						self.assertEqual(stEvNum2, lumiInDBS['StartEventNumber'])
						self.assertEqual(endEvNum2, lumiInDBS['EndEventNumber'])
						self.assertEqual(stLumiTime2, lumiInDBS['LumiStartTime'])
						self.assertEqual(endLumiTime2, lumiInDBS['LumiEndTime'])
						self.assertEqual(runNumber, lumiInDBS['RunNumber'])

					
		
			tierList = fileInDBS['TierList']
			self.assertEqual(len(tierList), 2)
			for tierInDBS in tierList:
				if(tierInDBS != tier1 and tierInDBS != tier2):
					print 'tier %s is not expected', tierInDBS
					self.assertEqual(1, 2)

			parentList = fileInDBS['ParentList']
			self.assertEqual(len(parentList), 2)
			for parentInDBS in parentList:
				if(parentInDBS['LogicalFileName'] == fileName1):
					self.assertEqual(fileCkecksum1, parentInDBS['Checksum'])
					self.assertEqual(fileName1, parentInDBS['LogicalFileName'])
					self.assertEqual(fileNumEvents1, parentInDBS['NumberOfEvents'])
					self.assertEqual(fileSize1, parentInDBS['FileSize'])
					self.assertEqual(fileStatus1, parentInDBS['Status'])
					#self.assertEqual(fileValidStatus1, parentInDBS['ValidationStatus'])
					self.assertEqual(fileType1, parentInDBS['FileType'])
				else:
					if(parentInDBS['LogicalFileName'] == fileName2):	
						self.assertEqual(fileCkecksum2, parentInDBS['Checksum'])
						self.assertEqual(fileName2, parentInDBS['LogicalFileName'])
						self.assertEqual(fileNumEvents2, parentInDBS['NumberOfEvents'])
						self.assertEqual(fileSize2, parentInDBS['FileSize'])
						self.assertEqual(fileStatus2, parentInDBS['Status'])
						#self.assertEqual(fileValidStatus2, parentInDBS['ValidationStatus'])
						self.assertEqual(fileType2, parentInDBS['FileType'])

					

	

if __name__ == '__main__':
	unittest.main()
