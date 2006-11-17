#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
# Unit tests for the DBS CGI implementation.
#
#   Mind that examples here are more elaborate and can be compacted


import sys
import random
from dbsApi import DbsApi
from dbsException import *
from dbsApiException import *
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsFileBlock import DbsFileBlock
from dbsProcessedDataset import DbsProcessedDataset
from dbsOptions import DbsOptionParser


optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

primary = DbsPrimaryDataset (Name = "test_primary_anzar_03")

proc = DbsProcessedDataset (
         PrimaryDataset=primary,
         Name="TestProcessedDS01",
         TierList=['SIM', 'RECO'],
         )
ran = str(int(random.random()*10000000))
block = DbsFileBlock (
		Name= "/this/isaqqqqqqqqqxstbddddlock#016712"+ ran,
         )

print "Creating block %s" % block

try:
    api.insertBlock (proc, block)
    api.insertBlock (proc)
    api.insertBlock ("/test_primary_anzar_03/SIM/TestProcessedDS01")
    api.insertBlock ("/test_primary_anzar_03/SIM/TestProcessedDS01", "/this/isaqww" + ran + "wwwqqqxstblock#016712")
    print "Result: %s" % primary
except DbsObjectExists, ex:
    print "Object existed already, passing"


print "Done"

