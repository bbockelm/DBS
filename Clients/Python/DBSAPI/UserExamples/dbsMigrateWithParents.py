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
	args = {}
	args['url']='http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet' 
	#args['version']='DBS_1_0_7'
	args['mode']='POST'
	api = DbsApi(args)
      
	#api = DbsApi(opts.__dict__)
	srcURL = sys.argv[1]
	dstURL = sys.argv[2]
	path = sys.argv[3]
	block = ""
	if len(sys.argv) > 4 :
		block = sys.argv[4]

	api.migrateDatasetContents(srcURL, dstURL, path, block )
	#print api.listPrimaryDatasets();

except DbsApiException, ex:
	print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
	if ex.getErrorCode() not in (None, ""):
		print "DBS Exception Error Code: ", ex.getErrorCode()
print "Done"
			
