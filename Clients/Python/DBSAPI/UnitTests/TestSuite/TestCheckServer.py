import unittest
from testhelpfuncs import *
from results import *
from TestDefineObjects import TestDefineObjects


class TestCheckServer(TestDefineObjects):
	def test_01_check_reader_instance(self):
		"""Checking the Connection to the READER"""
		try:
			self.apidict['reader'].getServerInfo()
		except:
			self.fail("getServerInfo could not be issued. Fix the Database connection.")
	def test_02_check_writer_instance(self):
		"""Checking the Connection to the WRITER"""
		try:
			self.apidict['writer'].getServerInfo()
		except:
			self.fail("getServerInfo could not be issued. Fix the Database connection.")
	def test_03_check_admin_instance(self):
		"""Checking the Connection to the ADMIN"""
		try:
			self.apidict['admin'].getServerInfo()
		except:
			self.fail("getServerInfo could not be issued. Fix the Database connection.")
if __name__== "__main__":
	unittest.main()
