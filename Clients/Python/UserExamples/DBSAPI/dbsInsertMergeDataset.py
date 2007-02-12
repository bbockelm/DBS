#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
# Unit tests for the DBS CGI implementation.

import sys
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser
from DBSAPI.dbsAlgorithm import DbsAlgorithm

try:
   optManager  = DbsOptionParser()
   (opts,args) = optManager.getOpt()
   api = DbsApi(opts.__dict__)
  
   merge_algo = DbsAlgorithm (
                    ExecutableName="EdmFastMerge",
                    ApplicationVersion= "v101",
                    ApplicationFamily="Merge",
                    )
   path = "/test_primary_anzar_001/SIM/TestProcessedDS001"
   proc = api.insertMergedDataset(path, "ThisISMergedDataset001", merge_algo)

   print "Result: %s" % proc
	
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

