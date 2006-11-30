#!/usr/bin/env python
#
# API Unit tests for the DBS JavaServer.

import sys
from dbsApi import DbsApi
from dbsException import *
from dbsApiException import *
from dbsOptions import DbsOptionParser

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)
for block in api.listBlocks("/test_primary_anzar_001/SIM/TestProcessedDS002/"):
     print "  %s" % block


