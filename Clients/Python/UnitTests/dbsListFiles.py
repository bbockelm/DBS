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

DEFAULT_URL = "http://cmssrv17.fnal.gov:8989/DBS/servlet/DBSServlet"

try:
  args = {}
  if len(sys.argv) == 2: args['instance'] = sys.argv[1]
  print args
  api = DbsApi(DEFAULT_URL, args)
  
  try:
   # List all parameter sets
   print ""
   print "Blocks...."
   for file in api.listFiles("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01"):
     print "  %s" % file
  except DbsDatabaseError,e:
   print e
  
except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"

