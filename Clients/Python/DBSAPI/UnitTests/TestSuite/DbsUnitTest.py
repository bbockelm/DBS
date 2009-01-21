import sys
import unittest
from testconfig import *
from TestCheckServer import TestCheckServer
from TestCheckDB import TestCheckDB
from TestInsertData import TestInsertData
from TestListData import TestListData
from TestQL import TestQL
from TestDeleteData import TestDeleteData


def runUnitTests():
	suite_0=unittest.makeSuite(TestCheckServer)
	suite_1=unittest.makeSuite(TestCheckDB)
	suite_2=unittest.makeSuite(TestInsertData)
	suite_3=unittest.makeSuite(TestListData)
	suite_4=unittest.makeSuite(TestQL)
	suite_5=unittest.makeSuite(TestDeleteData)
	        
	fsock = open(sys.argv[1], 'w')
	fsock.write("Instance: " + test_instance['name']+ "\n\n")
	
	print "Checking the connection to the Database..."
	testResult0=unittest.TextTestRunner(stream=fsock, verbosity=2).run(suite_0)
	if testResult0.wasSuccessful():
		print "Checking that tables are empty..."
		testResult1=unittest.TextTestRunner(stream=fsock, verbosity=2).run(suite_1)
		if not testResult1.wasSuccessful(): 
			raise Exception, "Please redeploy the schema so that all tables are empty. For now dbs unittests can be run only on a fresh instance."
		else:
			print 'Inserting Data...'
			testResult2=unittest.TextTestRunner(stream=fsock, verbosity=2).run(suite_2)
			if not testResult2.wasSuccessful(): raise Exception, 'Unittests failed while inserting the unit test data. Further tests will not be run until the problem is fixed.'
			else:
				print 'Running various api unittests...'
				testResult3=unittest.TextTestRunner(stream=fsock, verbosity=2).run(suite_3)
				print 'Running query language unittests...'
				testResult4=unittest.TextTestRunner(stream=fsock, verbosity=2).run(suite_4)
				print 'Deleting Data...'
				testResult5=unittest.TextTestRunner(stream=fsock, verbosity=2).run(suite_5)
	fsock.close()

if __name__=="__main__":
	runUnitTests()
