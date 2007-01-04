#!/usr/bin/env python
#
# API Unit tests for the DBS JavaServer.

import sys
from dbsApi import DbsApi
from dbsException import *
from dbsApiException import *
from dbsOptions import DbsOptionParser

try:
  optManager  = DbsOptionParser()
  (opts,args) = optManager.getOpt()
  api = DbsApi(opts.__dict__)
  #for block in api.listBlocks("/test_primary_anzar_001/SIM/TestProcessedDS002"):
  for block in api.listBlocks("/TestPrimary1167862926.47/SIM1167862926.47/TestProcessed1167862926.47"):
     print "  %s" % block

except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()


