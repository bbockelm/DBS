#!/usr/bin/env python
#
# API Unit tests for the DBS JavaServer.

import sys
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsOptions import DbsOptionParser
from dbsUnitTestApi import DbsUnitTestApi

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

f = open("result.txt", "a+")
apiObj = DbsUnitTestApi(api.listPrimaryDatasets, f)
apiObj.setVerboseLevel(opts.verbose)

pathfile = open('pathfile', 'r')
path= pathfile.readline()
pathfile.close()

blockfile = open('blockfile', 'r')
block = blockfile.readline()
blockfile.close()

lfnfile = open('lfnfile', 'r')
lfn = lfnfile.readline()
lfnfile.close()

#path =  apiObj.getExistingPDPath()
#block =  apiObj.getExistingBlock()

f.write("\n\n***********************listPrimaryDatasets API tests***************************")
apiObj.run("*", excep = False)
apiObj.run("*test*", excep = False)
apiObj.run(excep = False)
apiObj.run("", excep = False)
apiObj.run("abc*", excep = False)
apiObj.run("ab;bc", excep = True)
apiObj.run("ab/bc", excep = False)
apiObj.run("//*/ab/bc", excep = False)   ## / and * are allowed in pattern
apiObj.run("abc bc", excep = True)
apiObj.run("","", excep = True)
f.write("\n***********************listPrimaryDatasets API tests***************************")

apiObj = DbsUnitTestApi(api.listAlgorithms,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listAlgorithm API tests***************************")
apiObj.run("*","*", excep = False)
apiObj.run(excep = False)
apiObj.run("MyVersion22","sss", excep = False)
apiObj.run("MyVersion22","","", excep = False)
apiObj.run("MyVersion12","MyFamily12","MyExe12", excep = False)
apiObj.run("*","*12","MyExe12","*", excep = False)
apiObj.run("My Version22","sss", excep = True)
apiObj.run("ab;2","sss", excep = True)
apiObj.run("ab","s;", excep = True)
apiObj.run("/*", excep = False)
apiObj.run("*","abcd/jdg", excep = False)
apiObj.run("*","*","","","", excep = True)
f.write("\n***********************listAlgorithm API tests***************************")


apiObj = DbsUnitTestApi(api.listProcessedDatasets,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listProcessedDatasets API tests***************************")
apiObj.run("*","*", excep = False)
apiObj.run(excep = False)
apiObj.run("abc","sss", excep = False)
apiObj.run("","","","","","*1*","", excep = False)
apiObj.run("","","","","","MyExe1","", excep = False)
apiObj.run("","","","","","MyExe1", excep = False)
#apiObj.run("*/","","","","","MyExe1", excep = True)
#apiObj.run("*/","","","","","MyExe1", excep = True)
apiObj.run("*",";","exe", excep = True)
apiObj.run("","","","","","","","", excep = True)
f.write("\n***********************\tlistProcessedDatasets API tests***************************")

apiObj = DbsUnitTestApi(api.listBlocks,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listBlock API tests***************************")
apiObj.run(path,excep = False)
#apiObj.run("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01",excep = False)
#apiObj.run("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05", excep = False)
#apiObj.run(excep = True)
apiObj.run("", excep = False)
apiObj.run("/*/*/anzar-procds-01", excep = True)
apiObj.run("/Primaryaaaaa/sdldljd/slkdscds-05", excep = True)
apiObj.run("/*/sssjd/slkdscds-05", excep = True)
apiObj.run("/abd def/sssjd/slkdscds-05", excep = True)
apiObj.run("/Primary;DS_ANZAR_01/test-tier-01/anzar-procds-05", excep = True)
apiObj.run("/sjh","", excep = True)
f.write("\n***********************listBlock API tests***************************")


apiObj = DbsUnitTestApi(api.listRuns,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listRuns API tests***************************")
apiObj.run(path,excep = False)
#apiObj.run("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01",excep = False)
#apiObj.run("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05", excep = False)
apiObj.run(excep = True)
apiObj.run("", excep = True)
apiObj.run("/*/*/anzar-procds-01", excep = True)
apiObj.run("/Primaryaaaaa/sdldljd/slkdscds-05", excep = True)
apiObj.run("/*/sssjd/slkdscds-05", excep = True)
apiObj.run("/abd def/sssjd/slkdscds-05", excep = True)
apiObj.run("/Primary;DS_ANZAR_01/test-tier-01/anzar-procds-05", excep = True)
apiObj.run("/sjh","", excep = True)
f.write("\n\n***********************listRuns API tests***************************")


apiObj = DbsUnitTestApi(api.listTiers,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listTiers API tests***************************")
apiObj.run(path,excep = False)
#apiObj.run("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01",excep = False)
#apiObj.run("/PrimaryDS_ANZAR_01/test-tier-01/anzar-procds-05", excep = False)
apiObj.run(excep = True)
apiObj.run("", excep = True)
apiObj.run("/*/*/anzar-procds-01", excep = True)
apiObj.run("/Primaryaaaaa/sdldljd/slkdscds-05", excep = True)
apiObj.run("/*/sssjd/slkdscds-05", excep = True)
apiObj.run("/abd def/sssjd/slkdscds-05", excep = True)
apiObj.run("/Primary;DS_ANZAR_01/test-tier-01/anzar-procds-05", excep = True)
apiObj.run("/sjh","", excep = True)
f.write("\n\n***********************listTiers API tests***************************")



apiObj = DbsUnitTestApi(api.listFiles,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listFiles API tests***************************")
apiObj.run(path,"","","",excep = False)
#apiObj.run("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01","","",excep = False)
#apiObj.run("","/PrimaryDS_ANZAR_01/anzar-procds-05#4219afa9-0608-4222-a995-714bab16fc81","",excep = False)
apiObj.run("","", block,"",excep = False)
#apiObj.run("/PrimaryDS_ANZAR_01/SIM/anzar-procds-01","/PrimaryDS_ANZAR_01/anzar-procds-05#4219afa9-0608-4222-a995-714bab16fc81","*",excep = False)
#apiObj.run(path,"/PrimaryDS_ANZAR_01/anzar-procds-05#4219afa9-0608-4222-a995-714bab16fc81","*",excep = False)
apiObj.run(path,"", block,"*",excep = False)
apiObj.run("","", "/PrimaryDS_ANZAR_01/anzardjhk","",excep = True)
apiObj.run("","", "/Pr;imaryDS_ANZAR_01/anzardjhk","",excep = True)
apiObj.run("/dsds/","", "","",excep = True)
apiObj.run("/dsds//","", "","",excep = True)
apiObj.run("/dsds//","", "","",excep = True)
apiObj.run("/dsds/a b/de","", "","",excep = True)
apiObj.run("","", "","*",excep = False)
f.write("\n\n***********************listFiles API tests***************************")


apiObj = DbsUnitTestApi(api.listFileParents,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listFileParents API tests***************************")
apiObj.run(lfn,excep = False)
apiObj.run(lfn + ".nowaythiswillexist",excep = True)
apiObj.run(lfn + "noway thiswillexist",excep = True)
apiObj.run(lfn + "noway;thiswillexist",excep = True)
apiObj.run(lfn ,"", excep = True)
f.write("\n\n***********************listFileParents API tests***************************")


apiObj = DbsUnitTestApi(api.listFileTiers,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listFileTiers API tests***************************")
apiObj.run(lfn,excep = False)
apiObj.run(lfn + ".nowaythiswillexist",excep = True)
apiObj.run(lfn + "noway thiswillexist",excep = True)
apiObj.run(lfn + "noway;thiswillexist",excep = True)
apiObj.run(lfn ,"", excep = True)
f.write("\n\n***********************listFileTiers API tests***************************")


apiObj = DbsUnitTestApi(api.listFileBranches,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listFileBranches API tests***************************")
apiObj.run(lfn,excep = False)
apiObj.run(lfn + ".nowaythiswillexist",excep = True)
apiObj.run(lfn + "noway thiswillexist",excep = True)
apiObj.run(lfn + "noway;thiswillexist",excep = True)
apiObj.run(lfn ,"", excep = True)
f.write("\n\n***********************listFileBranches API tests***************************")

apiObj = DbsUnitTestApi(api.listFileAlgorithms,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listFileAlgorithms API tests***************************")
apiObj.run(lfn,excep = False)
apiObj.run(lfn + ".nowaythiswillexist",excep = True)
apiObj.run(lfn + "noway thiswillexist",excep = True)
apiObj.run(lfn + "noway;thiswillexist",excep = True)
apiObj.run(lfn ,"", excep = True)
f.write("\n\n***********************listFileAlgorithms API tests***************************")


apiObj = DbsUnitTestApi(api.listFileLumis,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listFileLumis API tests***************************")
apiObj.run(lfn,excep = False)
apiObj.run(lfn + ".nowaythiswillexist",excep = True)
apiObj.run(lfn + "noway thiswillexist",excep = True)
apiObj.run(lfn + "noway;thiswillexist",excep = True)
apiObj.run(lfn ,"", excep = True)
f.write("\n\n***********************listFileLumis API tests***************************")


apiObj = DbsUnitTestApi(api.listAnalysisDatasetDefinition,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listAnalysisDatasetDefinition API tests***************************")
apiObj.run(excep = False)
apiObj.run("*",excep = False)
apiObj.run("this;xist",excep = True)
apiObj.run("ahsdef", "",excep = True)
f.write("\n\n***********************listAnalysisDatasetDefinition API tests***************************")


apiObj = DbsUnitTestApi(api.listAnalysisDataset,f)
apiObj.setVerboseLevel(opts.verbose)
f.write("\n\n***********************listAnalysisDataset API tests***************************")
apiObj.run(excep = False)
apiObj.run("*",excep = False)
apiObj.run("*",path,excep = False)
apiObj.run("*","/this/will/notexist",excep = True)
apiObj.run("ahs def", path,excep = True)
apiObj.run("ahs*", path,"", excep = True)
f.write("\n\n***********************listAnalysisDataset API tests***************************")

f.close()
