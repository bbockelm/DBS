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
from dbsLumiSection import DbsLumiSection
from dbsOptions import DbsOptionParser

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

lumi = DbsLumiSection (
         LumiSectionNumber=99,
         StartEventNumber=100,
         EndEventNumber=200,
         LumiStartTime='notime',
         LumiEndTime='neverending',
         RunNumber=1,
         )
                            
print "Adding a Lumi Section"

try:
    api.insertLumiSection(lumi)

    print "Result: %s" % lumi
except DbsObjectExists, ex:
    print "Object existed already, passing"


print "Done"

