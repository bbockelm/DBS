#!/usr/bin/env python
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
import sys
import os
import unittest	
import validate as valid
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser
from DBSAPI.dbsApi import DbsApi
"""
srcURL = sys.argv[1]
dstURL = sys.argv[2]
path = sys.argv[3]
"""
srcURL = "http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet"
dstURL = "http://cmssrv48.fnal.gov:8383/DBSlocal/servlet/DBSServlet"
path = "/DY_mumu_10/CMSSW_1_3_1-Spring07-1349/GEN-SIM-DIGI-RECO"

try:
	optManager  = DbsOptionParser()
	(opts,args) = optManager.getOpt()
	args = {}
	args['url']='http://cmssrv17.fnal.gov:8989/DBS_1_0_5_STABLE/servlet/DBSServlet' 
	args['mode']='POST'
	api = DbsApi(args)
      
	#api = DbsApi(opts.__dict__)
	block = ""
	if len(sys.argv) > 4 :
		block = sys.argv[4]

	#api.migrateDatasetContents(srcURL, dstURL, path, block , False, True)

except DbsApiException, ex:
	print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
	if ex.getErrorCode() not in (None, ""):
		print "DBS Exception Error Code: ", ex.getErrorCode()
print "Done"

args = {}
args['url'] = srcURL 
args['mode']='POST'
srcApi = DbsApi(args)
args['url'] = dstURL 
dstApi = DbsApi(args)
pathTokens = path.split("/")
primName = pathTokens[1]
procName = pathTokens[2]

class Test_001(unittest.TestCase):
	def testPrimary(self):
		print 'testPrimary'
		primarySrcList = srcApi.listPrimaryDatasets(primName)
		primaryDstList = dstApi.listPrimaryDatasets(primName)
		valid.assertPrimary(self, primarySrcList[0], primaryDstList[0])	

class Test_002(unittest.TestCase):
	def testProcessed(self):
		print 'testProcessed'
		procSrcList = srcApi.listProcessedDatasets(patternPrim = primName, patternProc = procName)
		procDstList = dstApi.listProcessedDatasets(patternPrim = primName, patternProc = procName)
		#print processedInDBS
		valid.assertProc(self, procSrcList[0], procDstList[0])
		for i in range(len(procSrcList[0]['AlgoList'])):
			valid.assertAlgo(self, procSrcList[0]['AlgoList'][i], procDstList[0]['AlgoList'][i])

			
		parentSrcList = srcApi.listDatasetParents(path)
		parentDstList = dstApi.listDatasetParents(path)
		for i in range(len(parentSrcList)):
			self.assertEqual(parentSrcList[i]['PrimaryDataset']['Name'], parentDstList[i]['PrimaryDataset']['Name'])
			self.assertEqual(parentSrcList[i]['Name'], parentDstList[i]['Name'])
				
class Test_003(unittest.TestCase):
	def testBlock(self):
		print 'testBlock'
		blockSrcList = srcApi.listBlocks(path)
		blockDstList = dstApi.listBlocks(path)
		blockSrcList.sort(key=lambda obj: obj['Name'])
		blockDstList.sort(key=lambda obj: obj['Name'])
		print blockSrcList
		print "------------------"
		print blockDstList
		for i in range(len(blockSrcList)):
			valid.assertBlock(self, blockSrcList[i], blockDstList[i])



if __name__ == '__main__':

        unittest.main()


