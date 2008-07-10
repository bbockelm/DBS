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
  allowedRetriveValue = ['all',
		    #'retrive_invalid_files', 
		    #'retrive_status',
		    #'retrive_type',
		    #'retrive_block',
		    #'retrive_date',
		    #'retrive_person',
		    #'retrive_parent',
		    #'retrive_child',
		    #'retrive_algo',
		    #'retrive_tier',
		    #'retrive_lumi',
		    'retrive_lumi_excluded',
		    #'retrive_run',
		    #'retrive_branch',
		    ]

  for afile in api.listFiles(path="/Upsilon2S/Summer08_STARTUP_V2_Upsilon2S_v3/GEN-SIM-RAW", retriveList=allowedRetriveValue, otherDetails = False):
  #for afile in api.listFiles(patternLFN="/store/mc/2007/10/1/HLT-Wmunu-1191261655/0005/E2E68481-EE86-DC11-81A7-0019B9E4FE51.root", details=True):
  #for afile in api.listFiles(analysisDataset="/test00_testbeam_HCalEcalCombined/h2tb2007_default_v1/RAW/VijayTestADS", retriveList=allowedRetriveValue):
     print "  %s" % afile
     #print "  %s" % afile['LogicalFileName']
     	
except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

