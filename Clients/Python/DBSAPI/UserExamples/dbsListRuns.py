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
  print "RUNS...."
  #for app in api.listRuns("/PrimaryDS_ANZAR_01/KIM/anzar-procds-01"):
  #for app in api.listRuns("/PrimaryDS_ANZAR_01/DIGI/anzar-procds-01"):
  #for app in api.listRuns("/*/*/*"):
  #for app in api.listRuns("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01"):
  #for app in api.listRuns("/test_primary_anzar_001/test-tier-01/anzar-procds-05"):
  for app in api.listRuns("/test_primary_anzar_001/SIM/TestProcessedDS001"):
     print "  %s" % app
  
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

