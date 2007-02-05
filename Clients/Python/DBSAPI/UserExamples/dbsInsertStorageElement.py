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


#block = DbsFileBlock (
#         Name="/TestPrimary1164751189.48/HIT1164751189.48/TestProcessed1164751189.48"
#         )

print "Inserting SE in a block /this/hahah#12345"

try:
	api.insertStorageElement ( "/this/hahah#12345" , 'seaa1')
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()


print "Done"

