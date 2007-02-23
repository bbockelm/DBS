#!/usr/bin/env python
#
# API Unit tests for the DBS JavaServer.

import sys
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser

try:
  optManager  = DbsOptionParser()
  (opts,args) = optManager.getOpt()
  api = DbsApi(opts.__dict__)
  for block in api.listBlocks("/test_primary_anzar_001/SIM/TestProcessedDS001"):
  #for block in api.listBlocks(block_name="/MTCC-090-os-DAQ*"):
  #for block in api.listBlocks("/TestPrimary1167862926.47/SIM1167862926.47/TestProcessed1167862926.47", "/*hahah#12345"):
  #for block in api.listBlocks("/TestPrimary1167862926.47/SIM1167862926.47/TestProcessed1167862926.47", "/this/*"):
  #for block in api.listBlocks("/TestPrimary1167862926.47/SIM1167862926.47/TestProcessed1167862926.47", "/this/ff*"):
  #for block in api.listBlocks("/TestPrimary1167862926.47/SIM1167862926.47/TestProcessed1167862926.47", "/this/hahah#12345"):
  #for block in api.listBlocks("/TestPrimary1167862926.47/SIM1167862926.47/TestProcessed1167862926.47", "/this/hahah#12345"):
     print "  %s" % block

except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()


