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

block = DbsFileBlock (
         Name= "/this/isatestblock#016712",
         #BlockSize= 0, these are default values, sever will ignore anything else
         #NumberOfFiles= 0, these are default values, sever will ignore anything else
         #OpenForWriting= "y", these are default values, sever will ignore anything else
         Dataset= proc,
         fileList= ['2222-0909-9767-8764', '1111-0909-9767-8764'],
         )

print "Creating block %s" % block

try:
    api.insertBlock (block)
    print "Result: %s" % primary
except DbsObjectExists, ex:
    print "Object existed already, passing"


print "Done"

