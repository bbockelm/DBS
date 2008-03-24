#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
import sys
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsAlgorithm import DbsAlgorithm
from DBSAPI.dbsFileBlock import DbsFileBlock
from DBSAPI.dbsFile import DbsFile
from DBSAPI.dbsLumiSection import DbsLumiSection
from DBSAPI.dbsQueryableParameterSet import DbsQueryableParameterSet
from DBSAPI.dbsPrimaryDataset import DbsPrimaryDataset
from DBSAPI.dbsProcessedDataset import DbsProcessedDataset
from DBSAPI.dbsOptions import DbsOptionParser
from DBSAPI.dbsAnalysisDatasetDefinition import DbsAnalysisDatasetDefinition
optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

algo = DbsAlgorithm (
         ExecutableName="TestExe01",
         ApplicationVersion= "TestVersion01",
         ApplicationFamily="AppFamily01",
         ParameterSetID=DbsQueryableParameterSet(
           Hash="001234565798685",
           )
         )

analdsdef = DbsAnalysisDatasetDefinition(Name="TestAnalysisDSDef_001",
                                         ProcessedDatasetPath="/test_primary_001/TestProcessedDS001/SIM",
                                         FileList=['NEW_TEST0001', 'NEW_TEST0001', 'NOTYETINFILE'],
                                         #FileList=['NEW_TEST0001', 'NEW_TEST0001', 'NEW_TEST1111'],
                                         #AlgoList=[algo],
                                         #TierList=['SIM', 'GEN'],
                                         #LumiList=['1', '2', '3'],
                                         #RunsList=[1, 2],
                                         #AnalysisDSList=[],
                                         #LumiRangeList=[('3333', '4444'), ('1', '2000')],
                                         RunRangeList=[('0', '9'), ('11', '21000')],
                                         #UserCut="get all blah blah from x=1, y=6, z=j, lumi=all",
                                         Description="This is a test Analysis Dataset",
                                         )

"""
analdsdef = DbsAnalysisDatasetDefinition(Name="ALLFILES", 
					Description="Template ADS Def, when used to create a ADS, the ADS will result in having ALL Files in the Dataset at the point in time")

"""

try:
    api.createAnalysisDatasetDefinition (analdsdef)
    print "Result: %s" % analdsdef

except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()


print "Done"
