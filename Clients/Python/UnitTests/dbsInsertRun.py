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
from dbsRun import DbsRun
from dbsOptions import DbsOptionParser

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

                            
run = DbsRun (
         RunNumber=101,
         NumberOfEvents= 100,
         NumberOfLumiSections= 20,
         TotalLuminosity= 2222,
         StoreNumber= 123,
         StartOfRun= 'now',
         EndOfRun= 'never',
         )
 
print "Creating a run"

try:
    api.insertRun (run)

    print "Result: %s" % run
except DbsObjectExists, ex:
    print "Object existed already, passing"


print "Done"

