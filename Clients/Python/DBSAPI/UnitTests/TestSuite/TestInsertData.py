import unittest
from testdata import *
from TestDefineObjects import TestDefineObjects

class TestInsertData(TestDefineObjects):
	def test_01_InsertPrimaryDataset(self):
		self.api.insertPrimaryDataset(self.obj_DbsPrimaryDataset)
	def test_02_InsertAlgorithm(self):

		self.api.insertAlgorithm (self.obj_DbsAlgorithm__Parent)
		self.api.insertAlgorithm (self.obj_DbsAlgorithm)
		self.api.insertAlgorithm (self.obj_DbsAlgorithm__Child)

	def test_03_InsertRun(self):
	        self.api.insertRun (self.obj_DbsRun)

	def test_04_IsnertProcessedDataset(self):
		self.api.insertProcessedDataset (self.obj_DbsProcessedDataset__Parent)
        	self.api.insertProcessedDataset (self.obj_DbsProcessedDataset)
        	self.api.insertProcessedDataset (self.obj_DbsProcessedDataset__Child)

	def test_05_InsertBlock(self):
	        self.api.insertBlock (self.obj_DbsProcessedDataset__Parent, self.obj_DbsFileBlock__Parent1, [StorageElement_SEName__1,StorageElement_SEName__2, StorageElement_SEName__3])
	        self.api.insertBlock (self.obj_DbsProcessedDataset__Parent, self.obj_DbsFileBlock__Parent2, [StorageElement_SEName__1,StorageElement_SEName__2, StorageElement_SEName__3])
	        self.api.insertBlock (self.obj_DbsProcessedDataset, self.obj_DbsFileBlock__1, [StorageElement_SEName__2, StorageElement_SEName__3])
	        self.api.insertBlock (self.obj_DbsProcessedDataset, self.obj_DbsFileBlock__2, [StorageElement_SEName__2, StorageElement_SEName__3])
	        self.api.insertBlock (self.obj_DbsProcessedDataset__Child, self.obj_DbsFileBlock__Child, [StorageElement_SEName__3])

	def test_06_InsertFiles(self):
	        self.api.insertFiles(self.obj_DbsProcessedDataset__Parent, [self.obj_DbsFile__Parent1, self.obj_DbsFile__Parent2], self.obj_DbsFileBlock__Parent1)
	        self.api.insertFiles(self.obj_DbsProcessedDataset__Parent, [self.obj_DbsFile__Parent3, self.obj_DbsFile__Parent4], self.obj_DbsFileBlock__Parent2)
	        self.api.insertFiles(self.obj_DbsProcessedDataset, [self.obj_DbsFile__1, self.obj_DbsFile__2], self.obj_DbsFileBlock__1)
	        self.api.insertFiles(self.obj_DbsProcessedDataset, [self.obj_DbsFile__3], self.obj_DbsFileBlock__2)
	        self.api.insertFiles(self.obj_DbsProcessedDataset__Child, [self.obj_DbsFile__Child1, self.obj_DbsFile__Child2], self.obj_DbsFileBlock__Child)


if __name__=="__main__":
	#unittest.TextTestRunner().run('testDataSetup')
	unittest.main()
