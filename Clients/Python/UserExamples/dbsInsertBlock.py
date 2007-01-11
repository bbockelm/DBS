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

#primary = DbsPrimaryDataset (Name = "test_primary_anzar_03")
#primary = DbsPrimaryDataset (Name = "TestPrimary1164750596.79")
primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")

proc = DbsProcessedDataset (
         PrimaryDataset=primary,
         Name="TestProcessedDS002",
         TierList=['SIM', 'RECO'],
         )
#ran = str(int(random.random()*10000000))
#block = DbsFileBlock (
#		Name= "/this/isaqqqqqqqqqxstbddddlock#016712"+ ran,
#         )


block = DbsFileBlock (
         Name="/TestPrimary1164751189.48/HIT1164751189.48/TestProcessed1164751189.48"
         )

print "Creating block %s" % block

try:
    #api.insertBlock (proc, block)
    #api.insertBlock ('/TestPrimary1164751189.48/HIT1164751189.48/TestProcessed1164751189.48') 
    #api.insertBlock (proc)
    print api.insertBlock ("/test_primary_anzar_001/SIM/TestProcessedDS002", block=None, storage_element=["thisIsMyOnlySE"])
    #api.insertBlock ("/test_primary_anzar_001/SIM/TestProcessedDS002" , "/this/hahah#12345" , ['se1', 'se2', 'se3'])
    #api.insertBlock ("/test_primary_anzar_001/SIM/TestProcessedDS002" , "/this/hahaah#12345" , ['sea1', 'sea2', 'sea3'])
    #api.insertBlock ("/TestPrimary1167862926.47/SIM1167862926.47/TestProcessed1167862926.47", "/this/hahah#12345", ['se1', 'se2', 'se3'])
    #print "Result: %s" % primary
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()


print "Done"

