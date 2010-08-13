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
  print ""
  print "RUNS...."
  rlist=[]
  #for run in api.listRuns(dataset="/Cosmics/Commissioning08-MW32_v1/RAW"):
  #for run in api.listRuns(dataset="/MinimumBias/Commissioning10-v4/RAW"):
  for run in api.listRuns(block="/MinimumBias/Commissioning10-v4/RAW#06a63d9b-d9a9-4af8-89fa-268a9ac9760d"):
     print run
     #print "  %s" % run['RunNumber']
     #rlist.append(run['RunNumber'])
     
  rlist.sort()
  for r in rlist:
	print r
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

