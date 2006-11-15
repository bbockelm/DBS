#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
# Unit tests for the DBS CGI implementation.

import sys
from dbsApi import DbsApi
from dbsException import *
from dbsApiException import *
from dbsOptions import DbsOptionParser

try:
  optManager  = DbsOptionParser()
  (opts,args) = optManager.getOpt()
  api = DbsApi(opts.__dict__)
  
  try:
   # List all parameter sets
   print ""
   print "RUNS...."
   #for app in api.listRuns("/PrimaryDS_ANZAR_01/KIM/anzar-procds-01"):
   #for app in api.listRuns("/PrimaryDS_ANZAR_01/DIGI/anzar-procds-01"):
   #for app in api.listRuns("/*/*/*"):
   #for app in api.listRuns("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01"):
   for app in api.listRuns("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05"):
     print "  %s" % app
  except DbsDatabaseError,e:
   print e
  
except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"

