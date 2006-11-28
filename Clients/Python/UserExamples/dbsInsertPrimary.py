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
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsOptions import DbsOptionParser


optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)
 
primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")
print "Creating primary dataset %s" % primary

try:
    api.insertPrimaryDataset (primary)
    print "Result: %s" % primary
except DbsObjectExists, ex:
    print "Object existed already, passing"


print "Done"

