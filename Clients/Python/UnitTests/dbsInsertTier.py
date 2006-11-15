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

                            
print "Adding a TIER"

try:
    tier_name = "GEN-SIM-TEST"
    api.insertTier (tier_name)

    print "Result: %s" % tier_name
except DbsObjectExists, ex:
    print "Object existed already, passing"


print "Done"

