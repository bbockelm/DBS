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
 
  # USE either dataset or block, if both are provided, dataset takes precedence 
  # List summary information for the dataset
  print api.getSummary(dataset="/MinimumBias/Commissioning10-v4/RAW")
  #print api.getSummary(dataset="/rs1gg_1500GeV_c001/CMSSW_1_4_6-CSA07-2729/GEN-SIM")
  # List summary information for the block
  print api.getSummary(block="/MinimumBias/Commissioning10-v4/RAW#ce361a5b-2393-4660-aa11-ed116eaac1c7")
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

