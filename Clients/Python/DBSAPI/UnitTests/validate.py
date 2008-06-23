import random
import os
import unittest
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsApiException import *
from DBSAPI.dbsPrimaryDataset import DbsPrimaryDataset
from DBSAPI.dbsOptions import DbsOptionParser


optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)
 

def genRandom():
	return  os.popen('uuidgen').readline().strip()
class TestPrimary(unittest.TestCase):
	def setUp(self):
		self.seq = range(330)
	def testPrimary(self):
		print 'testPrimary'
		name = 'test_primary_' + genRandom()
		type = 'test'
		primary = DbsPrimaryDataset (Name = name, Type = type)
		api.insertPrimaryDataset (primary)
		for primaryInDBS in api.listPrimaryDatasets(name):
			self.assertEqual(name, primaryInDBS['Name'])
			self.assertEqual(type, primaryInDBS['Type'])

if __name__ == '__main__':
	unittest.main()
