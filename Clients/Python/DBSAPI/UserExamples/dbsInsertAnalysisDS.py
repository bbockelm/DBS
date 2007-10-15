#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
# Unit tests for the DBS CGI implementation.

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
from DBSAPI.dbsAnalysisDataset import DbsAnalysisDataset


optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

adef = DbsAnalysisDatasetDefinition(Name="TestAnalysisDSDef_005" + mytime,
                ProcessedDatasetPath=path,
                FileList=[file1['LogicalFileName'], file2['LogicalFileName']],
                AlgoList = [algo1, algo2],
                TierList= tierList,
                AnalysisDSList=[],
                LumiRangeList=[('1', '4444'), ('5000', '90000')],
                RunRangeList=[('0', '5000'), ('6000', '99999')],
                UserCut="get all blah blah from x=1, y=6, z=j, lumi=all",
                Description="This is a test Analysis Dataset" + mytime,
                )
"""

analysis=DbsAnalysisDataset(
                            Type='TEST',
                            Status='NEW',
                            PhysicsGroup='RelVal',
			    Path="/RelVal131QCD_pt600_800/CMSSW_1_3_1-1176201507/GEN-SIM-DIGI-RECO",
			    Description="This is a test Analysis Dataset for /RelVal131QCD_pt600_800/CMSSW_1_3_1-1176201507/GEN-SIM-DIGI-RECO",
                           )



analysis=DbsAnalysisDataset(
                            Type='TEST',
                            Status='NEW',
                            PhysicsGroup='RelVal',
                            Path="/RelVal131QCD_pt15_20/CMSSW_1_3_1-1176201507/GEN-SIM-DIGI-RECO",
			    Description="This is a test Analysis Dataset for /RelVal131QCD_pt15_20/CMSSW_1_3_1-1176201507/GEN-SIM-DIGI-RECO"
			   )

"""

try:
    #api.insertFiles (proc, [myfile1], block)
    api.createAnalysisDataset(analysis, "ALLFILES")
    print "DONE", analysis

except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()


print "Done"

