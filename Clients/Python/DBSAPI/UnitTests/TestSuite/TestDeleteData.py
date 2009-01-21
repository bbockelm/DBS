import os
import unittest
from testhelpfuncs import *
from testdata import *
from TestDefineObjects import TestDefineObjects

class TestDeleteData(TestDefineObjects):
	def test_01_DeleteDataset(self):
		"""Testing the deletion of a dataset"""
		aResult = getDataFromDBSServer(self.apidict['admin'], "find dataset")	
		self.assertEqual(len(aResult), 3, "There must be 3 datasetes left before deletion")
		self.apidict['admin'].deleteProcDS("/DBS_UNIT_TEST/Test_ProcessedDataset_Name__Child/USER")
		aResult = getDataFromDBSServer(self.apidict['admin'], "find dataset")	
		self.assertEqual(len(aResult), 2, "There must be 2 datasetes left after deletion")

if __name__== "__main__":
	unittest.main()

