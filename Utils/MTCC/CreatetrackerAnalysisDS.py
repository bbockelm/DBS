#!/usr/bin/env python

import sys
import random
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsAnalysisDataset import DbsAnalysisDataset
from DBSAPI.dbsAnalysisDatasetDefinition import DbsAnalysisDatasetDefinition
#from dbsProcessedDataset import DbsProcessedDataset
from DBSAPI.dbsOptions import DbsOptionParser

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

#Carsten Noeding is list of Tracker data from MTCC1

path1='/MTCC-090-os-DAQ-MTCC1/MTCC1/RAW'
path2='/MTCC-070-os-DAQ-MTCC1/MTCC1/RAW'
NodeingRunList=[2476,2478,2479,2480,2501,2502,2503,2505,2506,2507,2515,2516,2517,2518,2523, 
                2524,2525,2526,2527,2529,2530,2531,2532,2534,2544,2545,2546,2547,2548,2549,
                2550,2552,2555,2556,2557,2559,2560,2561,2564,2565,2567,2568,2569,2570,2571,
                2577,2578,2579,2580,2581,2583,2584,2587,2589,2590,2591,2593,2594,2596,2599,
                2600,2602,2603,2605,2610,2613,2614,2615,2616,2617,2618,2621,2622,2623,2624,
                2625,2626,2631,2632,2636,2637,2638,2639,2640,2641,2642,2643,2645,2647,2649,
                2654,2658,2659,2660,2661,2197,2198,2224,2225,2240,2241,2242,2243,2244,2246,
                2247,2249,2251,2255,2256,2262,2264,2267,2269,2327,2358,2460,2461,2462,2465,
                2469]

runList1 = []
#listRuns return DBS run objects. The run numbers is in dictionary with key word 'RunNumber'. 
for r in api.listRuns(path1):
    runList1.append(r['RunNumber'])

runList2 = []
for r2 in api.listRuns(path2):
    runList2.append(r2['RunNumber'])

NodeingRunList1=[]
NodeingRunList2=[]

for nr in NodeingRunList:
    if(nr in runList1):
        NodeingRunList1.append(nr)
    elif(nr in runList2):
        NodeingRunList2.append(nr)
    else:
        print 'This run is not in MTCC1 %d' %(nr)

print "Nodeing List 1 %d" %(len(NodeingRunList1))
print "Nodeing List 2 %d" %(len(NodeingRunList2))

#print NodeingRunList1;
#print NodeingRunList2;

#Now we can create two analysis ds

#RunsList will be L in the future.
adsdef1 = DbsAnalysisDatasetDefinition(
                        Name="MTCC-090-RAW-Tracker-Noeding-AnalysisDS-Def",
                        ProcessedDatasetPath=path1,
                        TierList=['RAW'],
                        AnalysisDSList=[],
                        RunsList=NodeingRunList1,
                        Description="This is Carsten Noeding Analysis Dataset for MTCC1 runs in path MTCC-090-os-DAQ-MTCC1.These runs have Trackers.",
                        )

adsdef2 = DbsAnalysisDatasetDefinition(
                        Name="MTCC-070-RAW-Tracker-Noeding-AnalysisDS-Def",
                        ProcessedDatasetPath=path2,
                        TierList=['RAW'],
                        AnalysisDSList=[],
                        RunsList=NodeingRunList2,
                        Description="This is Carsten Noeding Analysis Dataset for MTCC1 runs. These runs have Trackers",
                        )

## adsdefTEST = DbsAnalysisDatasetDefinition(
##                         Name="MTCC-090-RAW-TEST-AnalysisDS",
##                         ProcessedDatasetPath=path1,
##                         TierList=['RAW'],
##                         AnalysisDSList=[],
##                         RunRangeList=[('2476','2478'),('2501','2503')],
##                         Description="This is TEST Analysis Dataset for MTCC1 runs.",
##                         )
## try:
##     api.createAnalysisDatasetDefinition (adsdef2)
##     print "created analysis DS1"
## except DbsApiException, ex:
##     print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
##     if ex.getErrorCode() not in (None, ""):
##         print "DBS Exception Error Code: ", ex.getErrorCode()

#Now create the Analysis DS instance 
analysis1 = DbsAnalysisDataset(
                Name='MTCC-090-RAW-Tracker-Noeding-AnalysisDS',
                Annotation='TEST',
                Type='TEST',
                Status='NEW',
                PhysicsGroup='ALLGROUP'
        )

analysis2 = DbsAnalysisDataset(
                Name="MTCC-070-RAW-Tracker-Noeding-AnalysisDS",
                Annotation="The Ana DS is for Carsten Noeding Tracker MTCC data",
                Type='TEST',
                Status='NEW',
                PhysicsGroup='ALLGROUP'
        )

try:
    api.createAnalysisDatasetDefinition (adsdef1)
    api.createAnalysisDataset (analysis1, "MTCC-090-RAW-Tracker-Noeding-AnalysisDS-Def")
    print "created analysis DS1"
except DbsApiException, ex:
    print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
    if ex.getErrorCode() not in (None, ""):
        print "DBS Exception Error Code: ", ex.getErrorCode()


try:
    api.createAnalysisDatasetDefinition (adsdef2)
    api.createAnalysisDataset (analysis2, "MTCC-070-RAW-Tracker-Noeding-AnalysisDS-Def")
    print "created analysis DS2"
except DbsApiException, ex:
    print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
    if ex.getErrorCode() not in (None, ""):
        print "DBS Exception Error Code: ", ex.getErrorCode()


print "all Done"
        
