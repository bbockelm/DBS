#!/usr/bin/env python
# MTCC data parser
# Yuyi Guo
#
# 
#  @cvsid $Id: MTCCdbs2Writer.py,v 1.1 2007/02/21 17:12:05 yuyi Exp $
#
#
import sys
import fileinput
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
from DBSAPI.dbsRun import DbsRun

sys.stdout = sys.stderr


optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)
files=['./MTCC1_files.txt']
#files=['/home/yuyi/MTCC/data/MTCC2_files_primaryDSchanged.txt']
#Run dic use run number as key and number of event as value
runDic={}
#verDic
verDic={}
#
tireDic={};
#runPsDS dic use priDSName/PsDSName as key and a list of Run as value
runPsDSDic={}
#verPsDSDic use priDSName/PsDSName as key and ":" seperated version number as value
verPsDSDic={}
##verPsDSDic use priDSName/PsDSName as key and ":" seperated tire  as value
tirePsDSDic={}
#primary DS dic use primary DS names as key, total number of files in the primary dataset as the value
priDSDic={}
#processed DB dic use primaryDSNAME/processedDSNAme as key, total number of files as the values.
psDSDic ={}
LFNDic = {}  #key is  LFN, value is number of files under this LFN. It should be always be 1
LFNPsDic = {} # key LFN, value is primayDS/ProcessedDS
LFNRunDic = {}   #key LFN, value is a list of runs
LFNEvnDic ={}    #Event number
LFNSzDic = {}  # file size
LFNVerDic={}
LFNTireDic = {}
LFNChsmDic = {}

for line in fileinput.input(files):
    if(fileinput.lineno()<2):
            #print '%5d %s' %(fileinput.lineno(), line)
            parts=line.split()
            primaryDS = parts[0]
            processedDS = parts[1]
            hash = parts[2]
            cmsswV = parts[3]
            LFN = parts[4]
            checkSum = parts[5]
            fileSize = parts[6]
            numEvent = parts[7]
            ##GUID = parts[8]
            #print '****** New File **********'
            #print '%10d %s' %(fileinput.lineno(), line)
            #print "PrimaryDS = ", primaryDS
            #print "processedDS =", processedDS
            #print "hash = ", hash
            #print "cmsswV = ", cmsswV
            #print "LFN = ", LFN
            #print "checkSum = ", checkSum
            #print "fileSize = " ,  fileSize
            #print "numEvent = ", numEvent
            ##print "GUID = " , GUID
            #
            #processedDS = CMSSW_0_7_0-RAW-Run-00001541
            #try to get out run number from it
            processedInfo = processedDS.split('-')
            runNum = int(processedInfo[3])
            dataTire = processedInfo[1]
            ##primaryDS=MTCC-070-os-DAQ-MTCC1
            processdDSName = primaryDS.split('-')[4]
            #print 'runNum, numEvent: %s %s' %(runNum, numEvent)
            #find LFN
            if(LFN in LFNDic):
                print "ERROR: repeated file name: %s" %LFN
                sys.exit()
            else:
                LFNDic[LFN] = 1
            LFNEvnDic[LFN] = numEvent
            LFNSzDic[LFN] = fileSize
            LFNChsmDic[LFN] = checkSum
            #find total number of event in a run
            if(runNum in runDic):
                runDic[runNum]=runDic[runNum]+int(numEvent)
            else:
                runDic[runNum]=int(numEvent)
            #find RUN & LFN assocation
            if(LFN not in LFNRunDic):
                LFNRunDic[LFN] = [runNum]
            else:
                LFNRunDic[LFN] = LFNRunDic[LFN].append(runNum)
                
            #find promary DS
            if(primaryDS in priDSDic):
                priDSDic[primaryDS]= priDSDic[primaryDS]+1
            else:
                priDSDic[primaryDS]=1

            #find processed DS
            myPsDS = primaryDS + '/' + processdDSName;
            if(myPsDS in psDSDic):
                psDSDic[myPsDS]= psDSDic[myPsDS]+1
            else:
                psDSDic[myPsDS]= 0

            #Assocated LFN with /primay/datatire/process
            LFNPsDic[LFN] = "/"+primaryDS+"/"+dataTire+"/"+processdDSName

            #find version
            if(cmsswV in verDic):
                verDic[cmsswV] = verDic[cmsswV]+1
            else:
                verDic[cmsswV] =1
            #asscocated LFN with version
            LFNVerDic[LFN] = cmsswV
            #find tire
            if(dataTire in tireDic):
                tireDic[dataTire]=tireDic[dataTire]+1
            else:
                tireDic[dataTire]= 1
            #assocated LFN with Tire
            LFNTireDic[LFN] = dataTire

            #
            if(myPsDS in runPsDSDic):
                if(runNum not in runPsDSDic[myPsDS]):
                     runPsDSDic[myPsDS] =  runPsDSDic[myPsDS].append(runNum)
            else:
                 runPsDSDic[myPsDS] = [runNum]
            #
            if(myPsDS in verPsDSDic):
                if(cmsswV not in verPsDSDic[myPsDS]):
                     verPsDSDic[myPsDS] =  verPsDSDic[myPsDS]+':'+ cmsswV
            else:
                 verPsDSDic[myPsDS] = cmsswV
            #
            if(myPsDS in tirePsDSDic):
                if(dataTire not in tirePsDSDic[myPsDS]):
                     tirePsDSDic[myPsDS] =  tirePsDSDic[myPsDS]+':'+ dataTire
            else:
                 tirePsDSDic[myPsDS] = dataTire
            #
print "## total number of LFNs: %10d ##" %fileinput.lineno()
fileinput.close()

#create all the runs in db
for runNum in runDic.keys():
     run = DbsRun (RunNumber=runNum,
                          #MTCC has one run per file. So the number of events in a run is the same as in the file???
                          NumberOfEvents= runDic[runNum],
                          #MTCC does not have lumi, no store
                          NumberOfLumiSections= 0,
                          TotalLuminosity = 0,
                          StoreNumber= 0,
            )
     try:
          api.insertRun (run)
          #print "## Inserted Run: %s ##" % run
     except DbsApiException, ex:
                print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
                if ex.getErrorCode() not in (None, ""):
                   print "DBS Exception Error Code: ", ex.getErrorCode()
                       


# create all the algo
for cmsswV in verDic.keys():
     algo = DbsAlgorithm (ExecutableName="FUEventProcessor",
                                 ApplicationVersion=cmsswV ,
                                 ApplicationFamily="Online",
                                 ParameterSetID=DbsQueryableParameterSet(
                                 Hash="No_parameter_set_No_hash.",
                                 #Name="MTCC Parameter Set",
                                 #Version="V0.0.0",
                                 #Type="Fake",
                                 #Annotation="Fake MTCC Parameter set to satisfy the db",
                                 #Content="int a= {}, b={c=1, d=33}, f={}, x, y, x"
                                 )
            )
     try:
         api.insertAlgorithm (algo)
         print "## Inserted Algorithm: %s ##" % algo

     except DbsApiException, ex:
                print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
                if ex.getErrorCode() not in (None, ""):
                   print "DBS Exception Error Code: ", ex.getErrorCode()

#Create all the primary DS
for primary in  priDSDic.keys():
    try:
        api.insertPrimaryDataset (DbsPrimaryDataset (Name = primary))
        print "##Inserted Primary: %s ##" % primary

    except DbsApiException, ex:
                print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
                if ex.getErrorCode() not in (None, ""):
                   print "DBS Exception Error Code: ", ex.getErrorCode()

#create all the processed DS & blocks in db
for processed in psDSDic.keys():
    algoList=[]
    primaryName = processed.split('/')[0]
    processedName = processed.split('/')[1]
    versions = verPsDSDic[processed]
    versionList = versions.split(':')
    for v in versionList:
        algoList.append(DbsAlgorithm (ExecutableName="FUEventProcessor",
                                 ApplicationVersion=v ,
                                 ApplicationFamily="Online",
                                 ParameterSetID=DbsQueryableParameterSet(
                                 Hash="No_parameter_set_No_hash.",
                                 #Name="MTCC Parameter Set",
                                 #Version="V0.0.0",
                                 #Type="Fake",
                                 #Annotation="Fake MTCC Parameter set to satisfy the db",
                                 #Content="int a= {}, b={c=1, d=33}, f={}, x, y, x"
                                 )
                             )
                        )
    tireList = tirePsDSDic[processed].rsplit(':')
    runList =  runPsDSDic[processed]
                    
    print '## Creating  processed DS ... ##'
    proc = DbsProcessedDataset (PrimaryDataset=DbsPrimaryDataset(Name = primaryName),
                                        Name=processedName, 
                                        TierList=tireList,
                                        AlgoList=algoList,
                                        RunsList=runList,


                                )
    for t in tireList:
        tire=t 
        
    #Create block
    block = DbsFileBlock (Name= "/"+ primaryName+ "/"+ processedName + "/"+ tire +"#01")                   
    try:
         api.insertProcessedDataset (proc)
         print "## Inserted Processed: %s ##" % proc
         import pdb
         pdb.set_trace()
         api.insertBlock (proc, block)
         print "## Inserted Block: %s ##" % block
    except DbsApiException, ex:
                print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
                if ex.getErrorCode() not in (None, ""):
                   print "DBS Exception Error Code: ", ex.getErrorCode()

#Now insert LFNs
numberOfFile = 0
fileList = []
LFNList = LFNDic.keys()
lastPsName = LFNPsDic[LFNList[0]]
for LFN in LFNList:
    if(lastPsName == LFNPsDic[LFN] and numberOfFile < 350): 
        numberOfFile = numberOfFile + 1
        fileList.append( DbsFile (Checksum= LFNChsmDic[LFN],
                            LogicalFileName= LFN,
                            #QueryableMetadata= 'This is a test file',
                            NumberOfEvents= int(LFNEvnDic[LFN]),
                            FileSize= int(LFNSzDic[LFN]),
                            Status= 'VALID',
	                    ValidationStatus = 'VALID',
                            FileType= 'EVD',
                            #Dataset= LFNPsDic[LFN],
                            AlgoList=[DbsAlgorithm (ExecutableName="FUEventProcessor",
                                 ApplicationVersion=LFNVerDic[LFN] ,
                                 ApplicationFamily="Online",
                                 ParameterSetID=DbsQueryableParameterSet(
                                 Hash="No_parameter_set_No_hash.",
                                 #Name="MTCC Parameter Set",
                                 #Version="V0.0.0",
                                 #Type="Fake",
                                 #Annotation="Fake MTCC Parameter set to satisfy the db",
                                 #Content="int a= {}, b={c=1, d=33}, f={}, x, y, x"
                                 ))],
                            #Block= isDictType,
                            #LumiList= [lumi1, lumi2],
                            TierList= [LFNTireDic[LFN]],
                            RunsList = LFNRunDic[LFN],))
    elif(lastPsName !=  LFNPsDic[LFN] or numberOfFile >= 350):
        pNames = lastPsName.split('/')
        print "### LastPsName: %s ###" % lastPsName
        #print "###pName[0]: %s, PName[1]: %s, pName[2]: %s " %(pNames[0], pNames[1], pNames[2])
        #print "###pName[3]: %s" %(pNames[3])
        block = DbsFileBlock (Name= "/"+ pNames[1]+ "/"+ pNames[3] + "/"+ pNames[2] + "#01") 
        #       
        try:
            api.insertFiles(lastPsName, fileList, block)
            print "## Inserted File: %s ##" % fileList 
        except DbsApiException, ex:
                print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
                if ex.getErrorCode() not in (None, ""):
                   print "DBS Exception Error Code: ", ex.getErrorCode()
                   print "EEEEEEEEEEE   Error for Process: %s" %lastPsName
        lastPsName =  LFNPsDic[LFN]
        numberOfFile = 1
        fileList = []
        fileList.append( DbsFile (Checksum= LFNChsmDic[LFN],
                            LogicalFileName= LFN,
                            #QueryableMetadata= 'This is a test file',
                            NumberOfEvents= int(LFNEvnDic[LFN]),
                            FileSize= int(LFNSzDic[LFN]),
                            Status= 'VALID',
	                    ValidationStatus = 'VALID',
                            FileType= 'EVD',
                            #Dataset= LFNPsDic[LFN],
                            #Dataset = '/MTCC-070-os-DAQ-MTCC1/RAW/MTCC1',      
                            AlgoList=[DbsAlgorithm (ExecutableName="FUEventProcessor",
                                 ApplicationVersion=LFNVerDic[LFN] ,
                                 ApplicationFamily="Online",
                                 ParameterSetID=DbsQueryableParameterSet(
                                 Hash="No_parameter_set_No_hash.",
                                 #Name="MTCC Parameter Set",
                                 #Version="V0.0.0",
                                 #Type="Fake",
                                 #Annotation="Fake MTCC Parameter set to satisfy the db",
                                 #Content="int a= {}, b={c=1, d=33}, f={}, x, y, x"
                                 ))],
                            #Block= isDictType,
                            #LumiList= [lumi1, lumi2],
                            TierList= [LFNTireDic[LFN]],
                            RunsList = LFNRunDic[LFN],))
#taking care the last N (N<350) LFNs as the last insertation
print "## Taking care the last banch of runs. ##"
pNames = lastPsName.split('/')
print "### LastPsName: %s ###" % lastPsName
block = DbsFileBlock (Name= "/"+ pNames[1]+ "/"+ pNames[3] + "/" + pNames[2] + "#01") 
#       
try:
    api.insertFiles(lastPsName, fileList, block)
    print "## Inserted File: %s ##" % fileList
except DbsApiException, ex:
    print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
    if ex.getErrorCode() not in (None, ""):
        print "DBS Exception Error Code: ", ex.getErrorCode()
        print "EEEEEEEEEEE   Error for Process: %s" %lastPsName
    


print "ALL Done"

            

