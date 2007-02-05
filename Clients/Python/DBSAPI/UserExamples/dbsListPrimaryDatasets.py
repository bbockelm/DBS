#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
# Unit tests for the DBS CGI implementation.

import sys
#from dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser
from DBSAPI.dbsApi import DbsApi

try:
  optManager  = DbsOptionParser()
  (opts,args) = optManager.getOpt()
  #args={}
  #args['url']='http://cmssrv17.fnal.gov:8989/DBS/servlet/DBSServlet' 
  #args['version']='v00_00_05'
  #api = DbsApi(args)
  api = DbsApi(opts.__dict__)
  #import pdb
  #pdb.set_trace()
  try:
   # List all parameter sets
   print ""
   print "Primary Datasets"
   for primary in api.listPrimaryDatasets(''):
   #for primary in api.listPrimaryDatasets('ab;bc'):
     print "  %s" % primary
  except DbsDatabaseError,e:
   print e
  
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()      

print "Done"

