#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
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
  #print opts.__dict__
  api = DbsApi(opts.__dict__)
  #for block in api.listBlocks("/TestPrimary_001_20070315_03h12m26s/TestProcessed_20070315_03h12m26s/GEN-SIM"):
  #for block in api.listBlocks("/test_primary_001/TestProcessedDS001/GEN-SIM"):
  for block in api.listBlocks("/test_Primary_bbd59eba-8b48-4858-b524-8a5c66d4bbd2/test_processed_1_bbd59eba-8b48-4858-b524-8a5c66d4bbd2/GEN-SIM"):
  #for block in api.listBlocks(block_name="/test_primary_001*"):
  #for block in api.listBlocks("", "/TestPrimary_001_20070315_02h26m11s/TestProcessed_20070315_02h26m11s/GEN-SIM#016712"):
  #for block in api.listBlocks("/test_primary_001/TestProcessedDS001/GEN-SIM", "/test_primary_001/TestProcessedDS001/GEN*"):
  #for block in api.listBlocks("/TestPrimary_001_20070315_02h53m32s/TestPrimary_001_20070315_02h53m32s/GEN-SIM"):
     #print "%s  %s" % (block['Name'], block['StorageElementList'])
     #print "  %s" % block['Name']
     print "  %s" % block

except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()


