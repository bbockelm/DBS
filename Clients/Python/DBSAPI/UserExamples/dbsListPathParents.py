#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
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
  print "Processed Datasets:"
  #for proc in api.listProcessedDatasets("test_primary_001", "*", "TestProcessedDS002"):
  print  api.listPathParents("/Cosmics/BeamCommissioning08-PromptReco-v1/RECO")
  #for proc in api.listPathParents("/test_primary_001/TestProcessedDS002/GEN-SIM")['PathList']:
  #   print "  %s" % proc
  
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

