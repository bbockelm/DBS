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
  #if len(args) < 1:
  #   print "You must provide a log file name "
  #   sys.exit()
  #logfile=args[0]
  api = DbsApi(opts.__dict__)
  
  try:
   print len(api.listFiles("/DBSStressTestPrimaryDataset/DBSStressTestSIM/DBSStressTestProcessedDS", "", "")) 
   #f = open(logfile, "w")
   #print ""
   #for file in api.listFiles("/DBSStressTestPrimaryDataset/DBSStressTestSIM/DBSStressTestProcessedDS/", "", ""):
   #  print "\n  %s" % file["LogicalFileName"]
     #f.write(file["LogicalFileName"])
   #f.close()

  except DbsDatabaseError,e:
   print e
  
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()
print "Done"

