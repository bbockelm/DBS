import os
import unittest
from testhelpfuncs import *
from testdata import *
from TestDefineObjects import TestDefineObjects

class TestListData(TestDefineObjects):

	def test_Primary(self):
		"""listPrimaryDatasets() should return what was inserted with insertPrimaryDatasets()."""
		primaryList = self.api.listPrimaryDatasets()
		self.assertEqual(len(primaryList), 1)
		for primaryInDBS in primaryList:
                        self.assertEqual(primaryInDBS['Name'], PrimaryDataset_Name)
                        self.assertEqual(primaryInDBS['Type'], PrimaryDSType_Type)


	def test_Algorithm(self):
        	"""listAlgorithms should return what was inserted with insertAlgorithm(). """
        	algoList = self.api.listAlgorithms()
                self.assertEqual(len(algoList), 3)
		algoList = self.api.listAlgorithms(patternVer = AppVersion_Version__Parent, patternFam = AppFamily_FamilyName__Parent, patternExe = AppExecutable_ExecutableName__Parent, patternPS = QueryableParameterSet_Hash__Parent)
		self.assertEqual(len(algoList), 1)
		for algoInDBS in algoList:
                        assertAlgoPS(self, algoInDBS, self.obj_DbsAlgorithm__Parent)

        def test_Processed(self):
		"""listProcessedDataset should return what was inserted with insertProcessedDataset(). """
		procList = self.api.listProcessedDatasets()
                self.assertEqual(len(procList), 3)
                procList = self.api.listProcessedDatasets(patternPrim = PrimaryDataset_Name, patternProc = ProcessedDataset_Name__Parent)
                self.assertEqual(len(procList), 1)
                for processedInDBS in procList:
                        assertProc(self, processedInDBS, self.obj_DbsProcessedDataset__Parent)
                        self.assertEqual(len(processedInDBS['AlgoList']), 1)
                        for algoInDBS in processedInDBS['AlgoList']:
                                assertAlgo(self, algoInDBS, self.obj_DbsAlgorithm__Parent)

                        self.assertEqual(len(processedInDBS['TierList']), 2)
                        for tierInDBS in processedInDBS['TierList']:
                                if(tierInDBS != DataTier_Name__GEN and tierInDBS != DataTier_Name__SIM):
                                        print 'tier %s is not expected', tierInDBS
                                        self.assertEqual(1, 2)

        def test_Block(self):

                blockList = self.api.listBlocks(self.obj_DbsProcessedDataset__Parent)
                self.assertEqual(len(blockList), 2)
	        blockList = self.api.listBlocks(self.obj_DbsProcessedDataset)
                self.assertEqual(len(blockList), 2)
	        blockList = self.api.listBlocks(self.obj_DbsProcessedDataset__Child)
                self.assertEqual(len(blockList), 1)

                for blockInDBS in self.api.listBlocks(self.obj_DbsProcessedDataset__Child):
                        self.assertEqual(Block_Name__Child, blockInDBS['Name'])
                        self.assertEqual(path__Child, blockInDBS['Path'])
                        self.assertEqual(len(blockInDBS['StorageElementList']), 1)
                        for seInDBS in blockInDBS['StorageElementList']:
                              	self.assertEqual(seInDBS['Name'], StorageElement_SEName__3)

        def test_Run(self):
                runList = self.api.listRuns(self.obj_DbsProcessedDataset__Parent)
                self.assertEqual(len(runList), 1)
                for runInDBS in runList:
                        assertRun(self, self.obj_DbsRun, runInDBS)

        def test_Files(self):
		
                fileList = self.api.listFiles(path = path__Parent, retriveList = ['all'], otherDetails = True)
                self.assertEqual(len(fileList), 4)
                fileList = self.api.listFiles(path = path, retriveList = ['all'], otherDetails = True)
                self.assertEqual(len(fileList), 3)
                fileList = self.api.listFiles(path = path__Child, retriveList = ['all'], otherDetails = True)
                self.assertEqual(len(fileList), 2)
                fileList = self.api.listFiles(blockName = Block_Name__2, retriveList = ['all'], otherDetails = True) #just choosing one with 1 file in it
                self.assertEqual(len(fileList), 1)
		
                for fileInDBS in fileList:
                        assertFile(self, self.obj_DbsFile__3, fileInDBS)
                        algoList = fileInDBS['AlgoList']
                        self.assertEqual(len(algoList), 1)
                        for algoInDBS in algoList:
                                assertAlgo(self, self.obj_DbsAlgorithm__Child, algoInDBS)

                        lumiList = fileInDBS['LumiList']
                        self.assertEqual(len(lumiList), 1)
                        for lumiInDBS in lumiList:
                                assertLumi(self, self.obj_DbsLumiSection__2, lumiInDBS)

                        tierList = fileInDBS['TierList']
                        self.assertEqual(len(tierList), 1)
                        for tierInDBS in tierList:
				self.assertEqual(tierInDBS, DataTier_Name__RECO)

                        parentList = fileInDBS['ParentList']
                        self.assertEqual(len(parentList), 1)
                        for parentInDBS in parentList:
                                assertFile(self, self.obj_DbsFile__Parent4, parentInDBS)
                                pFileBlock=parentInDBS['Block']
                                assertBlock(self, pFileBlock, self.obj_DbsFileBlock__Parent2)

                        runList = fileInDBS['RunsList']
                        self.assertEqual(len(runList), 1)
                        for runInDBS in runList:
                                self.assertEqual(Runs_NumberOfLumiSections, runInDBS['NumberOfLumiSections'])
                                runInDBS['NumberOfLumiSections'] = self.obj_DbsRun['NumberOfLumiSections']
                                assertRun(self, self.obj_DbsRun, runInDBS)


        def test_ParentOfProcDS(self):
                parentList = self.api.listDatasetParents(self.obj_DbsProcessedDataset)
                self.assertEqual(len(parentList), 1)
                for parentProcInDBS in parentList:
                        self.assertEqual(PrimaryDataset_Name, parentProcInDBS['PrimaryDataset']['Name'])
                        self.assertEqual(ProcessedDataset_Name__Parent, parentProcInDBS['Name'])
                parentList = self.api.listDatasetParents(self.obj_DbsProcessedDataset__Child)
                self.assertEqual(len(parentList), 2)


        def test_InvalidFile(self):
                self.api.updateFileStatus(Files_LogicalFileName__3, ProcDSStatus_Status__Invalid, "No Description")
                fileList = self.api.listFiles(blockName = Block_Name__2, retriveList = ['all'])
                self.assertEqual(len(fileList), 1)
                for fileInDBS in fileList:
                        self.assertEqual(ProcDSStatus_Status__Invalid, fileInDBS['Status'])

                fileList = self.api.listFiles(blockName = Block_Name__2)
                self.assertEqual(len(fileList), 0)

                self.api.updateFileStatus(Files_LogicalFileName__3, ProcDSStatus_Status__Valid, "No Description")
                fileList = self.api.listFiles(path = path)
                self.assertEqual(len(fileList), 3)


        def test_BlockParents(self):
		pbsDBS=self.api.listBlockParents(block_name=Block_Name__1)
		self.assertEqual(len(pbsDBS),2)
		for pbDBS in pbsDBS:
			if (pbDBS['Name'] !=Block_Name__Parent1 and pbDBS['Name'] !=Block_Name__Parent2):
				print "Unexpected parent for the block" + Block_Name__1
				self.assertEqual(1,2)
		pbsDBS=self.api.listBlockParents(block_name=Block_Name__2)
		self.assertEqual(len(pbsDBS),1)
		for pbDBS in pbsDBS:
			assertBlock(self, pbDBS, self.obj_DbsFileBlock__Parent2)
		pbsDBS=self.api.listBlockParents(block_name=Block_Name__Child)
		self.assertEqual(len(pbsDBS), 3)
		for pbDBS in pbsDBS:
			if(pbDBS['Name'] !=Block_Name__Parent2 and pbDBS['Name'] !=Block_Name__1 and pbDBS['Name'] !=Block_Name__2):
				print "Unexpected parent for the block" + Block_Name__Child
				self.assertEqual(1,2)
	

        def test_BlockChildren(self):
                chldbsDBS=self.api.listBlockChildren(block_name=Block_Name__Parent1)
                self.assertEqual(len(chldbsDBS), 1)
                for chldbDBS in chldbsDBS: 
			assertBlock(self, chldbDBS, self.obj_DbsFileBlock__1)
                chldbsDBS=self.api.listBlockChildren(block_name=Block_Name__Parent2)
                self.assertEqual(len(chldbsDBS), 3)
                for chldbDBS in chldbsDBS: 
			if(chldbDBS['Name'] !=Block_Name__Child and chldbDBS['Name'] !=Block_Name__1 and chldbDBS['Name'] !=Block_Name__2):
				print "Unexpected child for the block" + Block_Name__Child
				self.assertEqual(1,2)
                chldbsDBS=self.api.listBlockChildren(block_name=Block_Name__1)
                self.assertEqual(len(chldbsDBS), 1)
                for chldbDBS in chldbsDBS: 
			assertBlock(self, chldbDBS, self.obj_DbsFileBlock__Child)
                chldbsDBS=self.api.listBlockChildren(block_name=Block_Name__2)
                self.assertEqual(len(chldbsDBS), 1)
                for chldbDBS in chldbsDBS: 
			assertBlock(self, chldbDBS, self.obj_DbsFileBlock__Child)
                chldbsDBS=self.api.listBlockChildren(block_name=Block_Name__Child)
                self.assertEqual(len(chldbsDBS), 0)

if __name__== "__main__":
	unittest.main()

