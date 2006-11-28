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
f.write("\n\n***********************listBlock API tests***************************")

def run(*listArgs, **dictArgs):
	try:
		info = "Test Case with params " + str(*listArgs)
		excep = dictArgs['excep']
		for data in api.listBlocks(*listArgs):
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

run("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01",excep = False)
run("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05", excep = False)
run(excep = True)
run("", excep = True)
run("/*/*/anzar-procds-01", excep = True)
run("/Primaryaaaaa/sdldljd/slkdscds-05", excep = True)
run("/*/sssjd/slkdscds-05", excep = True)
run("/abd def/sssjd/slkdscds-05", excep = True)
run("/Primary;DS_ANZAR_01/test-tier-01/anzar-procds-05", excep = True)

f.write("\n***********************\tlistBlock API tests***************************")
f.close()
