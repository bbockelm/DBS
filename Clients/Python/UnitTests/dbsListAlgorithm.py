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
fileName = sys.argv[1]
f = open(fileName, "a+")
f.write("\n\n***********************listAlgorithms API tests***************************")

def run(*listArgs, **dictArgs):
	try:
		info = "Test Case with params " + str(*listArgs)
		excep = dictArgs['excep']
		for data in api.listAlgorithms(*listArgs):
			print "  %s" % data
		if excep:
			f.write("\nFAILED. " + info + " without any exception that was expected")
		else:
			f.write("\nPASSED. " + info + " without any exception ")
	except:
		exception =  str(sys.exc_info()[0]) + " : " +  str(sys.exc_info()[1])
		if excep:
			f.write("\nPASSED. " + info + " with expected exception " + exception)
		else:
			f.write("\nFAILED. " + info + " with unexpected exception " + exception)

run("*",excep = False)
run("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05////", excep = False)
run(excep = False)
run("", excep = False)
run("/MyVersion1/*/*/*", excep = False)
run("/*/MyFamily1/MyExe1/*", excep = False)
run("/*/*/*/DUMMY_ps_hash1", excep = False)
run("/abd;*/*/*/*", excep = True)
run("/a*", excep = False)
run("/My*", excep = False)
f.write("\n***********************\tlistAlgorithm API tests***************************")
f.close()
