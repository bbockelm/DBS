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
  
  print "Analysis Daatset...."
  for analysis in api.listAnalysisDataset("*", "/This_is_a_test_primary_b97b1762-a97f-4348-be9b-d10a9445e7ae/This_is_a_test_tier_SIM_b97b1762-a97f-4348-be9b-d10a9445e7ae/CHILD_This_is_a_test_processed_b97b1762-a97f-4348-be9b-d10a9445e7ae"):
     print "  %s" % analysis
  
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

