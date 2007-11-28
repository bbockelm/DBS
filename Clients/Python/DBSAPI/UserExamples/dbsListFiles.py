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
from DBSAPI.dbsOptions import DbsOptionParser

try:

  optManager  = DbsOptionParser()
  (opts,args) = optManager.getOpt()
  api = DbsApi(opts.__dict__)
  
  #print api.listFiles(patternLFN="TRIG_TEST_FILE", details=True)
  #for aFile in api.listFiles(patternLFN="TRIG_TEST_FILE_with_assoc", details=True):
	#print aFile['LogicalFileName'], "Assocciated: ", aFile['FileAssoc']['LogicalFileName']

  # List all parameter sets
  #def listFiles(self, path, pri="", proc="", tier_list=[], analysisDataset="",blockName="", patternLFN="*", details=None)
  
  #for afile in api.listFiles(analysisDataset="/RelVal131QCD_pt600_800/CMSSW_1_3_1-1176201507/GEN-SIM-DIGI-RECO/ALLFILES"):
  #for afile in api.listFiles(analysisDataset="/RelVal131QCD_pt15_20/CMSSW_1_3_1-1176201507/GEN-SIM-DIGI-RECO/ALLFILES"):
  #for afile in api.listFiles("", "test_primary_001", "TestProcessedDS001", ['GEN', 'SIM'], "", "", "", False):
  #for afile in api.listFiles(path="/test_primary_001/TestProcessedDS001/GEN-SIM"):
  for afile in api.listFiles(patternLFN="/store/mc/2007/10/1/HLT-Wmunu-1191261655/0005/E2E68481-EE86-DC11-81A7-0019B9E4FE51.root", details=True):
     print "  %s" % afile
     #print "  %s" % afile['LogicalFileName']
     	
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

