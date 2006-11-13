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
   print "Processed Datasets"
   print "This API's parameters MUST Change"
   print "**********\n\n\n"
   #for proc in api.listProcessedDatasets("/PrimaryDS_ANZAR_01/*/anzar-procds-05/*/*/*/*"):
   #for proc in api.listProcessedDatasets("/*/*/anzar-procds-05/*/*/*/*"):
   #for proc in api.listProcessedDatasets("/*/*/anzar-procds-05"):
   for proc in api.listProcessedDatasets("/*/*/anzar-procds-05/*"):
   #for proc in api.listProcessedDatasets("*"):
     print "  %s" % proc
  except DbsDatabaseError,e:
   print e
  
except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"

