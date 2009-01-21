import unittest
from testhelpfuncs import *
from results import *
from TestDefineObjects import TestDefineObjects

class TestQL(TestDefineObjects):
	def test_0(self):
		"""find primds"""
		aQuery = "find primds"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_1(self):
		"""find dataset"""
		aQuery = "find dataset"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_2(self):
		"""find site"""
		aQuery = "find site"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_3(self):
		"""find release"""
		aQuery = "find release"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_4(self):
		"""find procds"""
		aQuery = "find procds"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_5(self):
		"""find procds.release, procds.tag, procds.status, procds.parent, procds.tier where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name/RECO"""
		aQuery = "find procds.release, procds.tag, procds.status, procds.parent, procds.tier where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name/RECO"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_6(self):
		"""find block where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name__Parent/GEN-SIM"""
		aQuery = "find block where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name__Parent/GEN-SIM"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_7(self):
		"""find block.size, block.dataset where dataset like *Child*"""
		aQuery = "find block.size, block.dataset where dataset like *Child*"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_8(self):
		"""find count(block) where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name/RECO"""
		aQuery = "find count(block) where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name/RECO"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_9(self):
		"""find sum(block.size) where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name__Parent/GEN-SIM"""
		aQuery = "find sum(block.size) where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name__Parent/GEN-SIM"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_10(self):
		"""find run where primds=DBS_UNIT_TEST"""
		aQuery = "find run where primds=DBS_UNIT_TEST"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_11(self):
		"""find count(run) where dataset like *DBS_UNIT_TEST*"""
		aQuery = "find count(run) where dataset like *DBS_UNIT_TEST*"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_12(self):
		"""find sum(run.numevents) where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name/RECO and file.size > 100"""
		aQuery = "find sum(run.numevents) where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name/RECO and file.size > 100"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_13(self):
		"""find file, file.release, file.size, file.type, file.tier, file.status where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name__Parent/GEN-SIM"""
		aQuery = "find file, file.release, file.size, file.type, file.tier, file.status where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name__Parent/GEN-SIM"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_14(self):
		"""find file.count where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name/RECO"""
		aQuery = "find file.count where dataset=/DBS_UNIT_TEST/Test_ProcessedDataset_Name/RECO"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
	def test_15(self):
		"""find sum(file.size) where dataset like *Child*"""
		aQuery = "find sum(file.size) where dataset like *Child*"
		aResult = getDataFromDBSServer(self.api, aQuery)
		self.assertEqual(aResult, known_values[aQuery])
if __name__== "__main__":
	suite = unittest.makeSuite(TestQL)
	unittest.TextTestRunner().run(suite)
	#unittest.main()
