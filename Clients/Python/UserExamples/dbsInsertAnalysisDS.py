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
from dbsAlgorithm import DbsAlgorithm
from dbsFileBlock import DbsFileBlock
from dbsFile import DbsFile
from dbsLumiSection import DbsLumiSection
from dbsQueryableParameterSet import DbsQueryableParameterSet
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsProcessedDataset import DbsProcessedDataset
from dbsOptions import DbsOptionParser
from dbsAnalysisDatasetDefinition import DbsAnalysisDatasetDefinition
from dbsAnalysisDataset import DbsAnalysisDataset


optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)



analysis=DbsAnalysisDataset(
                            Name='TestAnalysisDataset001',
                            Annotation='testdataset',
                            Type='TEST',
                            Status='NEW',
                            PhysicsGroup='BPositive'
                           )

try:
    #api.insertFiles (proc, [myfile1], block)
    api.createAnalysisDataset(analysis, "TestAnalysisDSDef_002")
    print "DONE", analysis

except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()


print "Done"

