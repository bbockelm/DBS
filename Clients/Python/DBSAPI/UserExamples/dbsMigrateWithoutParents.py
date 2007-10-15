#!/usr/bin/env python
#
# Unit tests for the DBS CGI implementation.
import sys
import os
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser


try:
	optManager  = DbsOptionParser()
	(opts,args) = optManager.getOpt()
	      
	api = DbsApi(opts.__dict__)
	srcURL = sys.argv[1]
	dstURL = sys.argv[2]
	path = sys.argv[3]
	block = ""
	if len(sys.argv) > 4 :
		block = sys.argv[4]
	
	api.migrateDatasetContents(srcURL, dstURL, path, block, True)

except DbsApiException, ex:
	print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
	if ex.getErrorCode() not in (None, ""):
		print "DBS Exception Error Code: ", ex.getErrorCode()
print "Done"
			
