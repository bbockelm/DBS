#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
import sys
#from dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser
from DBSAPI.dbsApi import DbsApi

try:
  optManager  = DbsOptionParser()
  (opts,args) = optManager.getOpt()

  api = DbsApi(opts.__dict__)

  #print api.executeQuery("select file,file.size,path where procds.name=Online")
  #print api.executeQuery("select file,file.size,path where procds.name=Online and file.size>546294916")
  #print api.executeQuery("select file,file.size,path where path=/GlobalMar08-Express/Online/RAW and file.size>546294916")
  #api.executeQuery("select file,ls where path=/GlobalMar08-Express/Online/RAW")
  #print api.executeQuery("select file,run where path=/GlobalMar08-Express/Online/RAW")
  print api.executeQuery("select file,ls where path=/GlobalMar08-Express/Online/RAW")

except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()      

print "Done"

