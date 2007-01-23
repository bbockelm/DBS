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
optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

algo = DbsAlgorithm (
         ExecutableName="TestExe01",
         ApplicationVersion= "TestVersion01",
         ApplicationFamily="AppFamily01",
         ParameterSetID=DbsQueryableParameterSet(
           Hash="001234565798685",
           Name="MyFirstParam01",
           Version="V001",
           Type="test",
           Annotation="This is test",
           Content="int a= {}, b={c=1, d=33}, f={}, x, y, x"
           )
         )

analdsdef = DbsAnalysisDatasetDefinition(Name="TestAnalysisDSDef_001",
                                         ProcessedDatasetPath="/test_primary_anzar_001/SIM/TestProcessedDS002",
                                         FileList=['NEW-AUTO-BLOCK-001', 'NEW-AUTO-BLOCK-002'],
                                         AlgoList=[algo],
                                         TierList=['SIM', 'RECO'],
                                         LumiList=['1234', '1222'],
                                         RunList=['1', '2'],
                                         AnalysisDSList=[],
                                         LumiRangeList=[('3333', '4444'), ('5555', '6666')],
                                         RunRangeList=[('5', '9'), ('11', '21')],
                                         UserCut="get all crap from x=1, y=6, z=j, lumi=all",
                                         Description="This is a ridiculous Analysis Dataset",
                                         )
try:
    #api.insertFiles (proc, [myfile1], block)
    api.createAnalysisDatasetDefinition (analdsdef)
    print "Result: %s" % analdsdef

except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()


print "Done"

