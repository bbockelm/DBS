import dbsWsApi
import dbsApi
import sys

class testCaseInterface:

     def __init__(self):
        """ Constructor calls the run method """
        # Construct api object.
        self.api = dbsWsApi.DbsWsApi(wsdlUrl="../python/DbsDatasetService.wsdl.xml")
        # Configure logging.
        self.api.setLogLevel(dbsApi.DBS_LOG_LEVEL_ALL_)
        
        self.RegisteredTestCases=[]
    
     def run(self):
        """ Calls upon child Test Cases """
        callAbleFunc = None
        print "Looping over available Test Cases"
        for aTestCase in self.RegisteredTestCases :
            #sys.stdout.flush() 
            print "\n\n**************** S T A R T **************************\n"
            callAbleFunc = aTestCase
            ret = callAbleFunc()
            if ret == 1:
              print "\nTestCase FAILED"
            else:
              print "\nTestCase PASSED"
            print "\n\n***************** E N D *************************\n"
        print "\n\n*******************TEST CASES DONE**************************"
        return 0 

     def addTestCase(self, funcRef): 
       """ Register a new testcase with testcase framework """
       #print "Registering a new testcase with testcase framework"
       self.RegisteredTestCases.append(funcRef)
       return 
         



