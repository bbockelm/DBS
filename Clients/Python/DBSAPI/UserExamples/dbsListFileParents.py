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
   # List all parents of the file
   print ""
   for file in api.listFileParents("aaaa2233-uuuuu-9767-8764aaaa"):
   #for file in api.listFileParents("TEST_LFN_1_20544a80-6eba-41db-98a5-07948a060c72_input_2"):
   #for file in api.listFileParents("TEST_LFN_1_20544a80-6eba-41db-98a5-07948a060c72_input_1"):
   #for file in api.listFileParents("TEST_LFN_1_20544a80-6eba-41db-98a5-07948a060c72_child_1"):
   #for file in api.listFileParents("TEST_LFN_1_20544a80-6eba-41db-98a5-07948a060c72_child_2"):
   #for file in api.listFileParents("TEST_LFN_1_20544a80-6eba-41db-98a5-07948a060c72_OUTPUT_MERGED"):
     print "  %s" % file
  except DbsDatabaseError,e:
   print e
  
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

