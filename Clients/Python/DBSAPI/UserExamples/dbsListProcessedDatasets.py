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
  
  try:
   # List all parameter sets
   print ""
   print "Processed Datasets"
   print "This API's parameters MUST Change"
   print "**********\n\n\n"
   #NEGATIVE TEST for proc in api.listProcessedDatasets("/DBS2-TEST-QCD_pt_0_15/*/*"):
   #for proc in api.listProcessedDatasets("DBS2-TEST-QCD_pt_0_15", "*", "*"):

   #for proc in api.listProcessedDatasets("*","*","CMSSW_0_9_0-RAW-Run-000033*"):
   for proc in api.listProcessedDatasets("*"):
     print "  %s" % proc
  except DbsDatabaseError,e:
   print e
  
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

