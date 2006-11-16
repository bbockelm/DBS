#!/usr/bin/env python
#
# API Unit tests for the DBS JavaServer.

import sys
import time
from dbsApi import DbsApi
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsAlgorithm import DbsAlgorithm
from dbsProcessedDataset import DbsProcessedDataset
from dbsQueryableParameterSet import DbsQueryableParameterSet
from dbsFileBlock import DbsFileBlock
from dbsRun import DbsRun
from dbsOptions import DbsOptionParser
from dbsUnitTestApi import DbsUnitTestApi

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)
mytime = str(time.time())

f = open("result.txt", "a+")

apiObj = DbsUnitTestApi(api.insertPrimaryDataset, f)
f.write("\n\n***********************insertPrimaryDataset API tests***************************")

primary = 'TestPrimary' + mytime

pri1 = DbsPrimaryDataset (Name = primary)
apiObj.run(pri1, excep = False)

apiObj.run(pri1, excep = True)

pri = DbsPrimaryDataset ()
apiObj.run(pri, excep = True)

pri = DbsPrimaryDataset (Name = "Test Het")
apiObj.run(pri, excep = True)

pri = DbsPrimaryDataset (Name = "Test;Het")
apiObj.run(pri, excep = True)

pri = DbsPrimaryDataset (Name = "Test*Het")
apiObj.run(pri, excep = True)

pri = DbsPrimaryDataset (Name = "Ta/estHet")
apiObj.run(pri, excep = True)

pri = DbsPrimaryDataset (Name = primary)
apiObj.run(pri,"", excep = True)

f.write("\n***********************insertPrimaryDataset API tests***************************")



apiObj = DbsUnitTestApi(api.insertAlgorithm,f)
f.write("\n\n***********************insertAlgorithm API tests***************************")
algo1 = DbsAlgorithm (ExecutableName="TestExe01", 
		ApplicationVersion= "TestVersion01" + mytime, 
		ApplicationFamily="AppFamily01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstParam01", 
							Version="V001", 
							Type="test", 
							#Annotation="This is test", 
							#Content="int a= {}, b={c=1, d=33}, f={}, x, y, x"
			                              )
	)
apiObj.run(algo1, excep = False)
apiObj.run(algo1, excep = True)


algo2 = DbsAlgorithm (ExecutableName="TestExe011", 
		ApplicationVersion= "TestVersion011" + mytime, 
		ApplicationFamily="AppFamily011", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstParam01" 
			                              )
	)
apiObj.run(algo2, excep = False)


algo = DbsAlgorithm (ExecutableName="TestExe01", 
		ApplicationVersion= "TestVersion01" + mytime, 
		ApplicationFamily="AppFamily01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Version="V001", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)


algo = DbsAlgorithm (ExecutableName="TestExe01", 
		ApplicationVersion= "TestVersion01" + mytime, 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstParam01", 
							Version="V001", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)


algo = DbsAlgorithm (ExecutableName="TestExe01", 
		ApplicationFamily="AppFamily01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstParam01", 
							Version="V001", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)


algo = DbsAlgorithm (
		ApplicationVersion= "TestVersion01" + mytime, 
		ApplicationFamily="AppFamily01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstParam01", 
							Version="V001", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)


algo = DbsAlgorithm (ExecutableName="TestExe01", 
		ApplicationVersion= "TestVersion01" + mytime, 
		ApplicationFamily="AppFamily01" 
	)
apiObj.run(algo, excep = True)


algo = DbsAlgorithm (ExecutableName="TestE xe01", 
		ApplicationVersion= "TestVersion01" + mytime, 
		ApplicationFamily="AppFamily01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstParam01", 
							Version="V001", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)

algo = DbsAlgorithm (ExecutableName="TestExe01", 
		ApplicationVersion= "TestVersion01" + mytime, 
		ApplicationFamily="AppFamily01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstPddaram01", 
							Version="V00/1", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)


algo = DbsAlgorithm (ExecutableName="TestExe01", 
		ApplicationVersion= "Tea/stVe/rsion01" + mytime, 
		ApplicationFamily="AppFamily01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstParam01", 
							Version="V001", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)


algo = DbsAlgorithm (ExecutableName="TestExe01", 
		ApplicationVersion= "TestVersQQQ01" + mytime, 
		ApplicationFamily="AppFami;ly01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstParam01", 
							Version="V001", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)


algo = DbsAlgorithm (ExecutableName="TestEDDDe01", 
		ApplicationVersion= "TestVersion01" + mytime, 
		ApplicationFamily="AppFamily01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="00123 4565798685", 
							Name="MyaaaaddddffFirstParam01", 
							Version="V001", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)


algo = DbsAlgorithm (ExecutableName="TestExe01", 
		ApplicationVersion= "Tewwwrsion01" + mytime, 
		ApplicationFamily="AppFamily01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstP;aram01", 
							Version="V001", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)


algo = DbsAlgorithm (ExecutableName="TestExe01", 
		ApplicationVersion= "TestVezzzn01" + mytime, 
		ApplicationFamily="AppFamily01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFir/stP/aram01", 
							Version="V001", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)


algo = DbsAlgorithm (ExecutableName="Testaae01", 
		ApplicationVersion= "TestVeggrsion01" + mytime, 
		ApplicationFamily="AppFami*ly01", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstParam01", 
							Version="V001", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)

algo = DbsAlgorithm (ExecutableName="TestE01", 
		ApplicationVersion= "TestVn01" , 
		ApplicationFamily="AppFami1", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234a5*5798685", 
							Name="MyFirstPzzzzaram01", 
							Version="V001", 
							Type="test", 
			                              )
	)
apiObj.run(algo, excep = True)

f.write("\n***********************insertAlgorithm API tests***************************")


f.write("\n\n***********************insertProcessedDataset API tests***************************")
tierList = ['SIM' + mytime, 'RECO' + mytime]

apiObj = DbsUnitTestApi(api.insertProcessedDataset,f)
proc1 = DbsProcessedDataset(PrimaryDataset=pri1,
		Name="TestProcessed" + mytime,
		PhysicsGroup="BPositive",
		Status="Valid",
		TierList=tierList,
		AlgoList=[algo1, algo2])
apiObj.run(proc1, excep = False)
apiObj.run(proc1, excep = True)


proc2 = DbsProcessedDataset(PrimaryDataset=pri1,
		Name="TestProcessed1" + mytime)
apiObj.run(proc2, excep = False)

proc = DbsProcessedDataset(PrimaryDataset=DbsPrimaryDataset (Name = "Ta/estHet"),
		Name="TestProcessed2" + mytime)
apiObj.run(proc, excep = True)


proc = DbsProcessedDataset(PrimaryDataset=DbsPrimaryDataset (Name = "Ta estHet"),
		Name="TestProcessed2" + mytime)
apiObj.run(proc, excep = True)

proc = DbsProcessedDataset(PrimaryDataset=DbsPrimaryDataset (Name = "Ta*estHet"),
		Name="TestProcessed2" + mytime)
apiObj.run(proc, excep = True)

proc = DbsProcessedDataset(PrimaryDataset=DbsPrimaryDataset (Name = "Taes;tHet"),
		Name="TestProcessed2" + mytime)
apiObj.run(proc, excep = True)

proc = DbsProcessedDataset(PrimaryDataset=pri1,
		Name="TestProce ssed2" + mytime)
apiObj.run(proc, excep = True)

proc = DbsProcessedDataset(PrimaryDataset=pri1,
		Name="TestProce*ssed2" + mytime)
apiObj.run(proc, excep = True)

proc = DbsProcessedDataset(PrimaryDataset=pri1,
		Name="TestProce/ssed2" + mytime)
apiObj.run(proc, excep = True)

proc = DbsProcessedDataset(PrimaryDataset=pri1,
		Name="TestPro;cessed2" + mytime)
apiObj.run(proc, excep = True)

proc = DbsProcessedDataset(Name="TestProcessed2" + mytime)
apiObj.run(proc, excep = True)

proc = DbsProcessedDataset(PrimaryDataset=pri1,
		Name="TestProcesssssssed" + mytime,
		PhysicsGroup="BPosit*aive")
apiObj.run(proc, excep = True)

proc = DbsProcessedDataset(PrimaryDataset=pri1,
		Name="TestProcewwwwwwwed" + mytime,
		PhysicsGroup="BPosit;aive")
apiObj.run(proc, excep = True)

algo = DbsAlgorithm (ExecutableName="TeaaaaaastExe011", 
		ApplicationVersion= "TestVersaaaaaaaaion011" + mytime, 
		ApplicationFamily="AppFamilaaaaaaay011", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstaaaaaParam01" 
			                              )
	)
proc = DbsProcessedDataset(PrimaryDataset=pri1,
		Name="TestProceqqqqqqd" + mytime,
		AlgoList=[algo])
apiObj.run(proc, excep = True)

algo = DbsAlgorithm (ExecutableName="Teaaaaa/tExe011", 
		ApplicationVersion= "TestVersaaaaaaaaion011" + mytime, 
		ApplicationFamily="AppFamilaaaaaaay011", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstaaaaaParam01" 
			                              )
	)
proc = DbsProcessedDataset(PrimaryDataset=pri1,
		Name="TestProceqqqqqqd" + mytime,
		AlgoList=[algo])
apiObj.run(proc, excep = True)


algo = DbsAlgorithm (ExecutableName="TeaaaaatExe011", 
		ParameterSetID=DbsQueryableParameterSet(Hash="001234565798685", 
							Name="MyFirstaaaaaParam01" 
			                              )
	)
proc = DbsProcessedDataset(PrimaryDataset=pri1,
		Name="TestProceqqqqqqd" + mytime,
		AlgoList=[algo])
apiObj.run(proc, excep = True)


algo = DbsAlgorithm (ExecutableName="TeaaaaatExe011", 
		ApplicationVersion= "TestVersaaaaaaaaion011" + mytime, 
		ApplicationFamily="AppFamilaaaaaaay011")
proc = DbsProcessedDataset(PrimaryDataset=pri1,
		Name="TestProceqqqqqqd" + mytime,
		AlgoList=[algo])
apiObj.run(proc, excep = True)
apiObj.run(proc,"", excep = True)

f.write("\n***********************insertProcessedDataset API tests***************************")


apiObj = DbsUnitTestApi(api.insertBlock, f)
f.write("\n\n***********************insertBlock API tests***************************")
path = "/" + str(proc1['PrimaryDataset']['Name']) + "/" + str(proc1['TierList'][0]) + "/" + str(proc1['Name'])

block = DbsFileBlock (Path = path)
apiObj.run(block, excep = False)

block = DbsFileBlock (Name = "/" + mytime + "this/isatestblock#016712", Path = path)
apiObj.run(block, excep = False)

block = DbsFileBlock (Name = "/" + mytime + "this/isatestblock#016712", Path = path)
apiObj.run(block, excep = True)

block = DbsFileBlock (Name = "/" + mytime + "this/isatestblock016712", Path = path)
apiObj.run(block, excep = True)

block = DbsFileBlock (Path = "/absssssssc/dessssssf/hijaaaaaaa")
apiObj.run(block, excep = True)

block = DbsFileBlock (Name = "/this/isatestblock#016712", Path = "/abcaaaa/deaaaaaaf/hiaaaaaaaj")
apiObj.run(block, excep = True)

block = DbsFileBlock (Name = "/thisisatestblock#016712", Path = path)
apiObj.run(block, excep = True)

block = DbsFileBlock (Name = "/thisis atestblock#016712", Path = path)
apiObj.run(block, excep = True)

block = DbsFileBlock (Name = "thisisat/ae/stblock#016712", Path = path)
apiObj.run(block, excep = True)

block = DbsFileBlock (Path = "/ddd/hd*/hdhd")
apiObj.run(block, excep = True)

block = DbsFileBlock (Path = "/dd d/hd/hdhd")
apiObj.run(block, excep = True)

block = DbsFileBlock (Path = "/ddd/hd/hd;hd")
apiObj.run(block, excep = True)

f.write("\n***********************insertBlock API tests***************************")

apiObj = DbsUnitTestApi(api.insertRun, f)
f.write("\n\n***********************insertRun API tests***************************")
runNumber1 = 101 + int(time.time()%1000)
runNumber2 = 102 + int(time.time()%1000)
runNumber3 = 103 + int(time.time()%1000)

run1 = DbsRun (RunNumber=runNumber1,
		NumberOfEvents= 100,
		NumberOfLumiSections= 20,
		TotalLuminosity= 2222,
		StoreNumber= 123,
		StartOfRun= 'now',
		EndOfRun= 'never',
)
apiObj.run(run1, excep = False)
apiObj.run(run1, excep = True)

run2 = DbsRun (RunNumber=runNumber2)
apiObj.run(run2, excep = True)

run = DbsRun (RunNumber=runNumber3,
		StartOfRun= 'no*w')
apiObj.run(run, excep = True)

run = DbsRun (RunNumber=runNumber3,
		StartOfRun= 'no w')
apiObj.run(run, excep = True)

run = DbsRun (RunNumber=runNumber3,
		StartOfRun= 'no;w')
apiObj.run(run, excep = True)

run = DbsRun (RunNumber=runNumber3,
		EndOfRun= 'nev*er')
apiObj.run(run, excep = True)

run = DbsRun (RunNumber=runNumber3,
		EndOfRun= 'nev er')
apiObj.run(run, excep = True)

run = DbsRun (RunNumber=runNumber3,
		EndOfRun= 'nev;er')
apiObj.run(run, excep = True)

f.write("\n***********************insertRun API tests***************************")

apiObj = DbsUnitTestApi(api.insertTier, f)
f.write("\n\n***********************insertTier API tests***************************")
tierName = "HIT" + mytime
apiObj.run(tierName, excep = False)
apiObj.run(tierName, excep = False)
apiObj.run("", excep = True)
apiObj.run(tierName + "sjhd*lk", excep = True)
apiObj.run(tierName + "sjhd;lk", excep = True)
apiObj.run(tierName + "sjhd lk", excep = True)
apiObj.run(tierName, "",  excep = True)

f.write("\n***********************insertTier API tests***************************")


f.close()
