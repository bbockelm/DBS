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

class TestFunctions(unittest.TestCase):
	def setUp(self):
		print "Do nothing"
	def testNothing(self):
		print "Do nothing"

class CompareInstances(unittest.TestCase):

        def __init__(self):
		#, srcURL, dstURL):
		try:
			srcURL = "http://cmsdbsdev1.cern.ch:8880/DBSANZ/servlet/DBSServlet"
			dstURL = "http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet"
	
        		optManager  = DbsOptionParser()
        		(opts,args) = optManager.getOpt()
        		args = {}
        		args['url']=srcURL
        		args['mode']='POST'
			args['version']='DBS_2_0_5'
        		self.srcApi = DbsApi(args)
			args['url']=dstURL
			self.dstApi = DbsApi(args)

		except DbsApiException, ex:
			print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
			if ex.getErrorCode() not in (None, ""):
				print "DBS Exception Error Code: ", ex.getErrorCode()

	def run(self):
		count=0
		paths = ['/Cosmics/Commissioning08-MW32_v1/RAW',
                         '/zz2j-alpgen/CMSSW_1_6_7-CSA07-1205616825/GEN-SIM-DIGI-RAW',
                         '/zz1j-alpgen/CMSSW_1_6_7-HLT-1205617620/GEN-SIM-DIGI-RECO',
                         '/zcc2j-alpgen/CMSSW_1_6_7-HLT-1204121116/GEN-SIM-DIGI-RECO',
                         '/Cosmics/Commissioning08-MW32_v1/RAW',
                         '/zz3j-alpgen/CMSSW_1_6_7-HLT-1205907476/GEN-SIM-DIGI-RECO',
                         '/zz3j-alpgen/CMSSW_1_6_7-HLT-1205907476/GEN-SIM-DIGI-RECO',
                         '/zz3j-alpgen/CMSSW_1_6_7-CSA07-1205907722/RECO',
                         '/zz3j-alpgen/CMSSW_1_6_7-CSA07-1205736930/GEN-SIM-DIGI-RAW',
                         '/zz3j-alpgen/CMSSW_1_4_9-CSA07-4131/GEN-SIM',
                         '/zz2j-alpgen/CMSSW_1_6_7-HLT-1205617522/GEN-SIM-DIGI-RECO',
                         '/zz2j-alpgen/CMSSW_1_6_7-CSA07-1205618250/RECO',
                         '/zz2j-alpgen/CMSSW_1_4_9-CSA07-4130/GEN-SIM',
                         '/zz1j-alpgen/CMSSW_1_6_7-HLT-1205617620/GEN-SIM-DIGI-RECO',
                         '/zz1j-alpgen/CMSSW_1_6_7-CSA07-1205618302/RECO',
                         '/zz1j-alpgen/CMSSW_1_6_7-CSA07-1205616888/GEN-SIM-DIGI-RAW',
                         '/Bd2PiKp/CMSSW_1_6_7-CSA07-1193556527/RECO',
                         '/SUSY_LM2-sftsht/Summer08_IDEAL_V11_redigi_v1/AODSIM',
                         '/TestBeam2007Ecal-A/Online/RAW',
                         '/TrackerTIF/CMSSW_1_7_5-Pass4-A4/RECO',
                         '/TrackerTIF/CMSSW_1_7_5-Pass4Skim-B2/USER',
                         '/TrackerTIF/Online/RAW',
                         '/TrackerTIF/Online-CMSSW_1_1_0/RAW',
                         '/zz3j-alpgen/CMSSW_1_6_7-CSA07-1205736930/GEN-SIM-DIGI-RAW',
                         '/znn5j_1600ptz3200-alpgen/CMSSW_1_6_7-CSA07-1207117287/GEN-SIM-DIGI-RAW',
                         '/testbeam_HCalEcalCombined/h2tb2007_default_v1/DIGI-RECO',
                         '/ppMuX/Summer08_STARTUP_V5_v1/GEN-SIM-RAW',
                         '/ph4j_300_7000-alpgen/CMSSW_1_6_7-CSA07-1201630273/RECO',
                         '/mcatnlo_tbq_mu/CMSSW_1_6_7-HLT-1203716749/GEN-SIM-DIGI-RECO',
                         '/mcatnloWWem/CMSSW_1_6_7-CSA07-3255/GEN-SIM-DIGI-RAW',
                         '/excitedel_3000GeV/CMSSW_1_6_7-CSA07-3787/GEN-SIM-DIGI-RAW',
                         '/comphep-bbll/CMSSW_1_6_7-CSA07-3713/GEN-SIM-DIGI-RAW',
                         '/Exotica_BPrimeBZCW_M250/Summer08_IDEAL_V9_v1/GEN-SIM-RECO',
                         '/RelValTTbar/CMSSW_2_1_0_pre6-RelVal-1213920853-IDEAL_V2-2nd/GEN-SIM-DIGI-RAW-HLTDEBUG-RECO',
                         '/RelValTTbar/CMSSW_2_1_0_pre8-RelVal-StorageTest-0_ld_ad-IDEAL_V5/GEN-SIM-DIGI-RAW-HLTDEBUG-RECO',
                         '/RelValWE/CMSSW_1_7_0_pre11-RelVal-HLT/GEN-SIM-DIGI-RECO',
                         '/RelValZPrimeJJM700/CMSSW_1_7_0_pre11-RelVal-HLT/GEN-SIM-DIGI-RECO']
		#paths = ['/QCDenriched_Pt50to80/Summer08_IDEAL_V11_redigi_v1/GEN-SIM-RECO']
	       	#paths = self.srcApi.listDatasetPaths()
		for path in paths:
			self.path=path
			print "Now processing : %s " % path
			pathTokens = path.split("/")
			self.primName = pathTokens[1]
			self.procName = pathTokens[2]
			try:
				self.testPrimary()
                        except DbsApiException, ex:
                                print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
                                if ex.getErrorCode() not in (None, ""):
                                        print "DBS Exception Error Code: ", ex.getErrorCode()
                        except AssertionError, ex:
                                print "FAILED testPrimary"
				print "Exception %s" %str(ex)
                        except:
                                print "FAILED testPrimary, unknown error"
			try:
				self.testProcessed()
                        except DbsApiException, ex:
                                print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
                                if ex.getErrorCode() not in (None, ""):
                                        print "DBS Exception Error Code: ", ex.getErrorCode()
                        except AssertionError, ex:
                                print "FAILED testProcessed"
				print "Exception %s" %str(ex)
                        except:
                                print "FAILED testProcessed, unknown error"
                        try:

				self.testBlock()
                        except DbsApiException, ex:
                                print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
                                if ex.getErrorCode() not in (None, ""):
                                        print "DBS Exception Error Code: ", ex.getErrorCode()
                        except AssertionError, ex:
                                print "FAILED testBlock"
				print "Exception %s" %str(ex)
                        except:
                                print "FAILED testBlock, unknown error"
			"""
                        try:
				self.testRun()
				
                        except DbsApiException, ex:
                                print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
                                if ex.getErrorCode() not in (None, ""):
                                        print "DBS Exception Error Code: ", ex.getErrorCode()
                        except AssertionError, ex:
                                print "FAILED testRun"
				print "Exception %s" %str(ex)
                        except:
                                print "FAILED testRun, unknown error"
			"""
                        try:
				self.testFile()
               		except DbsApiException, ex:
                       		print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
                       		if ex.getErrorCode() not in (None, ""):
                               		print "DBS Exception Error Code: ", ex.getErrorCode()	
               		except AssertionError, ex:
                       		print "FAILED testFile"
				print "Exception %s" %str(ex)
               		except:
                       		print "FAILED testFile, unknown error"
				print "back..."

			if count > 10: break
			count += 1
			print "Done"

	def testPrimary(self):
		print 'testPrimary'
		primarySrcList = self.srcApi.listPrimaryDatasets(self.primName)
		primaryDstList = self.dstApi.listPrimaryDatasets(self.primName)
		valid.assertPrimary(self, primarySrcList[0], primaryDstList[0])	

	def testProcessed(self):
		print 'testProcessed'
		procSrcList = self.srcApi.listProcessedDatasets(patternPrim = self.primName, patternProc = self.procName)
		procDstList = self.dstApi.listProcessedDatasets(patternPrim = self.primName, patternProc = self.procName)
		valid.assertProc(self, procSrcList[0], procDstList[0])
		for i in range(len(procSrcList[0]['AlgoList'])):
			valid.assertAlgo(self, procSrcList[0]['AlgoList'][i], procDstList[0]['AlgoList'][i])
			
		parentSrcList = self.srcApi.listDatasetParents(self.path)
		parentDstList = self.dstApi.listDatasetParents(self.path)
		parentSrcList.sort(key=lambda obj: obj['Name'])
		parentDstList.sort(key=lambda obj: obj['Name'])
		for i in range(len(parentSrcList)):
			self.assertEqual(parentSrcList[i]['PrimaryDataset']['Name'], parentDstList[i]['PrimaryDataset']['Name'])
			self.assertEqual(parentSrcList[i]['Name'], parentDstList[i]['Name'])
		
	def testBlock(self):
		print 'testBlock'
		blockSrcList = self.srcApi.listBlocks(self.path)
		blockDstList = self.dstApi.listBlocks(self.path)
		blockSrcList.sort(key=lambda obj: obj['Name'])
		blockDstList.sort(key=lambda obj: obj['Name'])
		for i in range(len(blockSrcList)):
			valid.assertBlock(self, blockSrcList[i], blockDstList[i])

			#Block Parentage test can only be applied to new servers
			parentSrcBlock = self.srcApi.listBlockParents(blockSrcList[i])
			parentDstBlock = self.srcApi.listBlockParents(blockDstList[i])
			parentSrcBlock.sort(key=lambda obj: obj['Name'])
			parentDstBlock.sort(key=lambda obj: obj['Name'])
			for j in range(len(parentDstBlock)):
				valid.assertBlock(self, parentSrcBlock[j], parentDstBlock[j])

	def testRun(self):
		print 'testRun'
		runSrcList = self.srcApi.listRuns(self.path)
		runDstList = self.dstApi.listRuns(self.path)
		runSrcList.sort(key=lambda obj: obj['RunNumber'])
		runDstList.sort(key=lambda obj: obj['RunNumber'])
		#print runSrcList
		for i in range(len(runSrcList)):
		#for runInDBS in runList:
			valid.assertRun(self, runSrcList[i], runDstList[i])

	def testFile(self):
		print 'testFile'

		fileSrcList = self.srcApi.listFiles(path = self.path, retriveList = ['all'], otherDetails = True)
		fileDstList = self.dstApi.listFiles(path = self.path, retriveList = ['all'], otherDetails = True)
		fileSrcList.sort(key=lambda obj: obj['LogicalFileName'])
		fileDstList.sort(key=lambda obj: obj['LogicalFileName'])

		for i in range(len(fileSrcList)):
			valid.assertFile(self, fileSrcList[i], fileDstList[i])

			algoSrcList = fileSrcList[i]['AlgoList']
			algoDstList = fileDstList[i]['AlgoList']
			for j in range(len(algoSrcList)):
				valid.assertAlgo(self, algoSrcList[j], algoDstList[j])

			lumiSrcList = fileSrcList[i]['LumiList']
			lumiDstList = fileDstList[i]['LumiList']
			lumiSrcList.sort(key=lambda obj: obj['LumiSectionNumber'])
			lumiDstList.sort(key=lambda obj: obj['LumiSectionNumber'])
			for j in range(len(lumiSrcList)):
				valid.assertLumi(self, lumiSrcList[j], lumiSrcList[j])


			parentSrcList = fileSrcList[i]['ParentList']
			parentDstList = fileDstList[i]['ParentList']
			parentSrcList.sort(key=lambda obj: obj['LogicalFileName'])
			parentDstList.sort(key=lambda obj: obj['LogicalFileName'])
			for j in range(len(parentSrcList)):
				valid.assertFile(self, parentSrcList[j], parentDstList[j])

			runSrcList = fileSrcList[i]['RunsList']	
			runDstList = fileDstList[i]['RunsList']	

			runSrcList.sort(key=lambda obj: obj['RunNumber'])
			runDstList.sort(key=lambda obj: obj['RunNumber'])
			for j in range(len(runSrcList)):
				valid.assertRun(self, runSrcList[j], runDstList[j])



if __name__ == '__main__':
	#unittest.main()
	compare=CompareInstances()
	compare.run()

