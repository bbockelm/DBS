import unittest
from testhelpfuncs import *
from results import *
import DBSAPI.dbsApi
class TestCheckDB(unittest.TestCase):
	def setUp(self):
		self.api=DBSAPI.dbsApi.DbsApi()
	def test_0(self):
		"""Checking that db tables are empty: """
		aQuery = "find primds"
		self.assertEqual(getDataFromDBSServer(self.api, aQuery),[])

		aQuery = "find dataset"
		self.assertEqual(getDataFromDBSServer(self.api, aQuery),[])

		aQuery = "find site"
		self.assertEqual(getDataFromDBSServer(self.api, aQuery),[])

		aQuery = "find release"
		self.assertEqual(getDataFromDBSServer(self.api, aQuery),[])

		aQuery = "find run"
		self.assertEqual(getDataFromDBSServer(self.api, aQuery),[])

		aQuery = "find file"
		self.assertEqual(getDataFromDBSServer(self.api, aQuery),[])

if __name__== "__main__":
	unittest.main()
