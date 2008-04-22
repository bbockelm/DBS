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
from DBSAPI.dbsPrimaryDataset import DbsPrimaryDataset
from DBSAPI.dbsOptions import DbsOptionParser
from DBSAPI.dbsDQFlag import DbsDQFlag
from DBSAPI.dbsRunLumiDQ import DbsRunLumiDQ

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

tagList=["Tracker_Global",
"TIB_Local",
"TIB_Power",
"TIB_Cooling",
"TIB_DCS",
"TIB_DAQ",
"TIB_Online_DQM",
"TIB_Offline_DQM",
"TOB_Local",
"TOB_Power",
"TOB_Cooling",
"TOB_DCS",
"TOB_DAQ",
"TOB_Online_DQM",
"TOB_Offline_DQM",
"TEC_Local",
"TEC_Power",
"TEC_Cooling",
"TEC_DCS",
"TEC_DAQ",
"TEC_Online_DQM",
"TEC_Offline_DQM",
"TID_Local",
"TID_Power",
"TID_Cooling",
"TID_DCS",
"TID_DAQ",
"TID_Online_DQM",
"TID_Offline_DQM",
"PXB_Local",
"PXB_Power",
"PXB_Cooling",
"PXB_DCS",
"PXB_DAQ",
"PXB_Online_DQM",
"PXB_Offline_DQM",
"PXF_Local",
"PXF_Power",
"PXF_Cooling",
"PXF_DCS",
"PXF_DAQ",
"PXF_Online_DQM",
"PXF_Offline_DQM",
"MUON_Global",
"CSC_Local",
"CSC_Power",
"CSC_Cooling",
"CSC_DCS",
"CSC_DAQ",
"CSC_Online_DQM",
"CSC_Offline_DQM",
"RPC_Local",
"RPC_Power",
"RPC_Cooling",
"RPC_DCS",
"RPC_DAQ",
"RPC_Online_DQM",
"RPC_Offline_DQM",
"DT_Local",
"DT_Power",
"DT_Cooling",
"DT_DCS",
"DT_DAQ",
"DT_Online_DQM",
"DT_Offline_DQM",
"ECAL_Global",
"EB_Local",
"EB_Power",
"EB_Cooling",
"EB_DCS",
"EB_DAQ",
"EB_Online_DQM",
"EB_Offline_DQM",
"EE_Local",
"EE_Power",
"EE_Cooling",
"EE_DCS",
"EE_DAQ",
"EE_Online_DQM",
"EE_Offline_DQM",
"ES_Local",
"ES_Power",
"ES_Cooling",
"ES_DCS",
"ES_DAQ",
"ES_Online_DQM",
"ES_Offline_DQM",
"HCAL_Global",
"HB_Local",
"HB_Power",
"HB_Cooling",
"HB_DCS",
"HB_DAQ",
"HB_Online_DQM",
"HB_Offline_DQM",
"HE_Local",
"HE_Power",
"HE_Cooling",
"HE_DCS",
"HE_DAQ",
"HE_Online_DQM",
"HE_Offline_DQM",
"HF_Local",
"HF_Power",
"HF_Cooling",
"HF_DCS",
"HF_DAQ",
"HF_Online_DQM",
"HF_Offline_DQM",
]

run_dq_list=[]
for aTag in tagList:
    flag=DbsDQFlag(
           Name=aTag,
           Value="GOOD"
         )
    run_dq_list.append(flag)

for aRun in range (20000, 22000):
         run_dq = DbsRunLumiDQ (
                  RunNumber=aRun,
                  DQFlagList = run_dq_list
                 )

         # Single Run, Multiple Flags (Some sub systems have sub-sub systems, some don't)
         api.insertRunLumiDQ( [run_dq] )
	 #print "\n",run_dq
	 print aRun



