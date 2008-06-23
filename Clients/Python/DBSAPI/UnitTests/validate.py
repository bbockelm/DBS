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

class TestPrimary(unittest.TestCase):
	def setUp(self):
		self.ran = genRandom()
	def testPrimary(self):
		print 'testPrimary'
		name = 'test_Primary_' + self.ran
		type = 'test'
		primary = DbsPrimaryDataset (Name = name, Type = type)
		api.insertPrimaryDataset (primary)
		for primaryInDBS in api.listPrimaryDatasets(name):
			self.assertEqual(name, primaryInDBS['Name'])
			self.assertEqual(type, primaryInDBS['Type'])

class TestAlgorithm(unittest.TestCase):
	def setUp(self):
		self.ran = genRandom()
	def testAlgorithm(self):
		print 'testAlgorithm'
		exe = 'test_Exe_' + self.ran
		ver = 'test_Ver_' + self.ran
		fam = 'test_Fam_' + self.ran
		hash = 'test_Hash_' + self.ran
		pname = 'test_Pname_' + self.ran
		pver = 'test_Pver_' + self.ran
		ptype = 'test_Ptype_' + self.ran
		panno = 'test_Panno_' + self.ran
		pcon = 'test_Con_' + self.ran

		algo = DbsAlgorithm (
				ExecutableName = exe,
				ApplicationVersion = ver,
				ApplicationFamily = fam,
				ParameterSetID=DbsQueryableParameterSet(
					Hash = hash,
					Name = pname,
					Version = pver,
					Type = ptype,
					Annotation = panno,
					Content = pcon
					)
				)
		api.insertAlgorithm (algo)
		for algoInDBS in api.listAlgorithms(patternVer = ver, patternFam = fam, patternExe = exe, patternPS = hash):
			self.assertEqual(exe, algoInDBS['ExecutableName'])
			self.assertEqual(ver, algoInDBS['ApplicationVersion'])
			self.assertEqual(fam, algoInDBS['ApplicationFamily'])
			self.assertEqual(hash, algoInDBS['ParameterSetID']['Hash'])
			self.assertEqual(pname, algoInDBS['ParameterSetID']['Name'])
			self.assertEqual(pver, algoInDBS['ParameterSetID']['Version'])
			self.assertEqual(ptype, algoInDBS['ParameterSetID']['Type'])
			self.assertEqual(panno, algoInDBS['ParameterSetID']['Annotation'])
			self.assertEqual(pcon, algoInDBS['ParameterSetID']['Content'])

"""
class TestRun(unittest.TestCase):
	def setUp(self):
		self.ran = random.choice(range(10000))
	def testRun(self):
		print 'testRun'
		runNumber = self.ran
		numEvents = 1200
		numLumi = 1234
		totalLumi = 3456
		storeNum = 8907
		startRun = 23456
		endRun = 12345
		run = DbsRun (
				RunNumber = runNumber,
				NumberOfEvents = numEvents,
				NumberOfLumiSections = numLumi,
				TotalLuminosity = totalLumi,
				StoreNumber = storeNum,
				StartOfRun = startRun,
				EndOfRun = endRun,
				)
		api.insertRun (run)
		for runInDBS in api.listPrimaryDatasets(name):
			self.assertEqual(runNumber, runInDBS['RunNumber'])
			self.assertEqual(numEvents, runInDBS['NumberOfEvents'])
			self.assertEqual(numLumi, runInDBS['NumberOfLumiSections'])
			self.assertEqual(totalLumi, runInDBS['TotalLuminosity'])
			self.assertEqual(storeNum, runInDBS['StoreNumber'])
			self.assertEqual(startRun, runInDBS['StartOfRun'])
			self.assertEqual(endRun, runInDBS['EndOfRun'])
"""

class TestProcessed(unittest.TestCase):
	def setUp(self):
		self.ran = genRandom()
	def testProcessed(self):
		print 'testProcessed'
		name = 'test_Primary_' + self.ran
		type = 'test'
		primary = DbsPrimaryDataset (Name = name, Type = type)
		api.insertPrimaryDataset (primary)
		
		
		exe1 = 'test_Exe1_' + self.ran
		ver1 = 'test_Ver1_' + self.ran
		fam1 = 'test_Fam1_' + self.ran
		hash1 = 'test_Hash1_' + self.ran
		pname1 = 'test_Pname1_' + self.ran
		pver1 = 'test_Pver1_' + self.ran
		ptype1 = 'test_Ptype1_' + self.ran
		panno1 = 'test_Panno1_' + self.ran
		pcon1 = 'test_Con1_' + self.ran

		algo1 = DbsAlgorithm (
				ExecutableName = exe1,
				ApplicationVersion = ver1,
				ApplicationFamily = fam1,
				ParameterSetID=DbsQueryableParameterSet(
					Hash = hash1,
					Name = pname1,
					Version = pver1,
					Type = ptype1,
					Annotation = panno1,
					Content = pcon1
					)
				)

		exe2 = 'test_Exe2_' + self.ran
		ver2 = 'test_Ver2_' + self.ran
		fam2 = 'test_Fam2_' + self.ran
		hash2 = 'test_Hash2_' + self.ran
		pname2 = 'test_Pname2_' + self.ran
		pver2 = 'test_Pver2_' + self.ran
		ptype2 = 'test_Ptype2_' + self.ran
		panno2 = 'test_Panno2_' + self.ran
		pcon2 = 'test_Con2_' + self.ran

		algo2 = DbsAlgorithm (
				ExecutableName = exe2,
				ApplicationVersion = ver2,
				ApplicationFamily = fam2,
				ParameterSetID=DbsQueryableParameterSet(
					Hash = hash2,
					Name = pname2,
					Version = pver2,
					Type = ptype2,
					Annotation = panno2,
					Content = pcon2
					)
				)

		api.insertAlgorithm (algo1)
		api.insertAlgorithm (algo2)

		procName = 'test_processed_' + self.ran
		phyGrp = 'BPositive'
		status = 'VALID'
		tier1 = 'GEN'
		tier2 = 'SIM'
		era = 'test_Era_' + self.ran
		tag = 'test_Tag_' + self.ran

		

		proc = DbsProcessedDataset (
				PrimaryDataset = primary, 
				Name = procName, 
				AcquisitionEra = era,
				GlobalTag = tag,
				PhysicsGroup = phyGrp,
				Status = status,
				TierList = [tier1, tier2],
				AlgoList = [algo1, algo2],
				)
		
		api.insertProcessedDataset (proc)
		for processedInDBS in api.listProcessedDatasets(patternPrim = name, patternProc = procName):
			self.assertEqual(procName, processedInDBS['Name'])
			self.assertEqual(name, processedInDBS['PrimaryDataset']['Name'])
			self.assertEqual(era, processedInDBS['AcquisitionEra'])
			self.assertEqual(tag, processedInDBS['GlobalTag'])
			self.assertEqual(phyGrp, processedInDBS['PhysicsGroup'])
			self.assertEqual(status, processedInDBS['Status'])
			for algoInDBS in processedInDBS['AlgoList']:
				if(algoInDBS['ExecutableName'] == exe1):
					self.assertEqual(exe1, algoInDBS['ExecutableName'])
					self.assertEqual(ver1, algoInDBS['ApplicationVersion'])
					self.assertEqual(fam1, algoInDBS['ApplicationFamily'])
					self.assertEqual(hash1, algoInDBS['ParameterSetID']['Hash'])
				else:
					if (algoInDBS['ExecutableName'] == exe2):
						self.assertEqual(exe2, algoInDBS['ExecutableName'])
						self.assertEqual(ver2, algoInDBS['ApplicationVersion'])
						self.assertEqual(fam2, algoInDBS['ApplicationFamily'])
						self.assertEqual(hash2, algoInDBS['ParameterSetID']['Hash'])
					else:
						print 'algo %s is not expected', algoInDBS
						self.assertEqual(1, 2)

			
			for tierInDBS in processedInDBS['TierList']:
				if(tierInDBS != tier1 and tierInDBS != tier2):
					print 'tier %s is not expected', tierInDBS
					self.assertEqual(1, 2)


if __name__ == '__main__':
	unittest.main()
