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

try:

  optManager  = DbsOptionParser()
  (opts,args) = optManager.getOpt()
  api = DbsApi(opts.__dict__)
  
  # List all parameter sets
  print ""
  for file in api.listFiles("/test_primary_anzar_001/SIM/TestProcessedDS001", "", "", False):
     print "  %s" % file

  #for file in api.listFiles("", "", "MERGEDFILE_003", True):
  #for file in api.listFiles("/test_primary_anzar_001/SIM/ThisISMergedDataset001", "", "MERGEDFILE_003", True):
  #for file in api.listFiles("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01"):
  #for file in api.listFiles("", "", "NEW_TEST0006", True):

  ## Details and a file with ONLY RunList, No Lumis
  for file in api.listFiles("", "", "NEW_TEST0070", True):
    print "  %s" % file
  
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

