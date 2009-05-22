import unittest
import validate
#import ValidateMigration
import dbsAPIUnitTests
import dbsQLUnitTests

suite1 = dbsAPIUnitTests.suite()
suite2 = dbsQLUnitTests.fillsuite()
suite3 = validate.suite()
#suite4 = ValidateMigration.suite()
Suite  = unittest.TestSuite()
Suite.addTest(suite1)
Suite.addTest(suite2)
Suite.addTest(suite3)
#suite.addTest(suite4)
unittest.TextTestRunner(verbosity=2).run(Suite)

#suite4 = ValidateMigration.suite()
#Suite2  = unittest.TestSuite()
#Suite2.addTest(suite4)
#unittest.TextTestRunner(verbosity=2).run(Suite2)

