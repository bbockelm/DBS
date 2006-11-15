#!/usr/bin/env python
#
# API Unit tests for the DBS JavaServer.

import sys
from dbsApi import DbsApi
from dbsException import *
from dbsApiException import *
from dbsOptions import DbsOptionParser

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)
f = open("result.txt", "w")
apiObj = ""
	
def run(*listArgs, **dictArgs):
	try:
		#import pdb
		#pdb.set_trace()
		info = "TEST CASE  " + str(apiObj.im_func.func_name) + str(listArgs)
		excep = dictArgs['excep']
		apiObj(*listArgs)
		#for data in apiObj(*listArgs):
			#print "  %s" % data
		if excep:
			f.write("\nFAILED. " + info + " AN EXCEPTION WAS EXPECTED BUT NONE WAS RAISED")
		else:
			f.write("\nPASSED. " + info + " AN EXCEPTION WAS NOT EXPECTED AND NONE WAS RAISED ")
	except:
		exception =  str(sys.exc_info()[0]) + " : " +  str(sys.exc_info()[1])
		print exception
		if excep:
			f.write("\nPASSED. " + info + " AN EXCEPTION WAS EXPECTED AND RAISED. THE EXCEPTION IS : " + exception)
		else:
			f.write("\nFAILED. " + info + " AN EXCEPTION WAS NOT EXPECTED BUT RAISED. THE EXCEPTION IS : " + exception)

apiObj = api.listPrimaryDatasets
f.write("\n\n***********************listPrimaryDatasets API tests***************************")
run("*", excep = False)
run("*test*", excep = False)
run(excep = False)
run("", excep = False)
run("abc*", excep = False)
run("ab;bc", excep = True)
run("ab/bc", excep = True)
run("//*/ab/bc", excep = True)
run("abc bc", excep = True)
run("","", excep = True)
f.write("\n***********************listPrimaryDatasets API tests***************************")


apiObj = api.listAlgorithms
f.write("\n\n***********************listAlgorithm API tests***************************")
run("*","*", excep = False)
run(excep = False)
run("MyVersion22","sss", excep = False)
run("MyVersion22","","", excep = False)
run("MyVersion12","MyFamily12","MyExe12", excep = False)
run("*","*12","MyExe12","*", excep = False)
run("My Version22","sss", excep = True)
run("ab;2","sss", excep = True)
run("ab","s;", excep = True)
run("/*", excep = True)
run("*","abcd/jdg", excep = True)
run("*","*","","","", excep = True)
f.write("\n***********************listAlgorithm API tests***************************")


apiObj = api.listProcessedDatasets
f.write("\n\n***********************listProcessedDatasets API tests***************************")
run("*","*", excep = False)
run(excep = False)
run("abc","sss", excep = False)
run("","","","","","*1*","", excep = False)
run("","","","","","MyExe1","", excep = False)
run("","","","","","MyExe1", excep = False)
run("*/","","","","","MyExe1", excep = True)
run("*/","","","","","MyExe1", excep = True)
run("*",";","exe", excep = True)
run("","","","","","","","", excep = True)
f.write("\n***********************\tlistProcessedDatasets API tests***************************")

apiObj = api.listBlocks
f.write("\n\n***********************listBlock API tests***************************")
run("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01",excep = False)
run("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05", excep = False)
run(excep = True)
run("", excep = True)
run("/*/*/anzar-procds-01", excep = True)
run("/Primaryaaaaa/sdldljd/slkdscds-05", excep = True)
run("/*/sssjd/slkdscds-05", excep = True)
run("/abd def/sssjd/slkdscds-05", excep = True)
run("/Primary;DS_ANZAR_01/test-tier-01/anzar-procds-05", excep = True)
run("/sjh","", excep = True)
f.write("\n***********************listBlock API tests***************************")


apiObj = api.listRuns
f.write("\n\n***********************listRuns API tests***************************")
run("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01",excep = False)
run("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05", excep = False)
run(excep = True)
run("", excep = True)
run("/*/*/anzar-procds-01", excep = True)
run("/Primaryaaaaa/sdldljd/slkdscds-05", excep = True)
run("/*/sssjd/slkdscds-05", excep = True)
run("/abd def/sssjd/slkdscds-05", excep = True)
run("/Primary;DS_ANZAR_01/test-tier-01/anzar-procds-05", excep = True)
run("/sjh","", excep = True)
f.write("\n\n***********************listRuns API tests***************************")


apiObj = api.listTiers
f.write("\n\n***********************listTiers API tests***************************")
run("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01",excep = False)
run("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05", excep = False)
run(excep = True)
run("", excep = True)
run("/*/*/anzar-procds-01", excep = True)
run("/Primaryaaaaa/sdldljd/slkdscds-05", excep = True)
run("/*/sssjd/slkdscds-05", excep = True)
run("/abd def/sssjd/slkdscds-05", excep = True)
run("/Primary;DS_ANZAR_01/test-tier-01/anzar-procds-05", excep = True)
run("/sjh","", excep = True)
f.write("\n\n***********************listTiers API tests***************************")



apiObj = api.listFiles
f.write("\n\n***********************listFiles API tests***************************")
run("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01","","",excep = False)
run("","/PrimaryDS_ANZAR_01/anzar-procds-05#4219afa9-0608-4222-a995-714bab16fc81","",excep = False)
run("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01","/PrimaryDS_ANZAR_01/anzar-procds-05#4219afa9-0608-4222-a995-714bab16fc81","*",excep = False)
run("","","",excep = True)
run("","/PrimaryDS_ANZAR_01/anzardjhk","",excep = True)
run("","/Pr;imaryDS_ANZAR_01/anzardjhk","",excep = True)
run("/dsds/","","",excep = True)
run("/dsds//","","",excep = True)
run("/dsds//","","",excep = True)
run("/dsds/a b/de","","",excep = True)
run("","","*",excep = True)
run("","","*",excep = True)
run("","","",excep = True)
run("","","","", excep = True)
f.write("\n\n***********************listFiles API tests***************************")

f.close()
