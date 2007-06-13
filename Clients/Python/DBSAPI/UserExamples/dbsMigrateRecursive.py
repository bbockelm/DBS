#!/usr/bin/env python
#
# Unit tests for the DBS CGI implementation.
import sys
import os
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser

class DbsMigrateApi:
	
	apiSrc = None
	apiDst = None
	def __init__(self, srcURL, dstURL):
		self.apiSrc = self.makeAPI(srcURL)
		self.apiDst = self.makeAPI(dstURL)
		
	def getAPISrc(self):
		return self.apiSrc
	
	def getAPIDst(self):
		return self.apiDst

	def makeAPI(self, url):
		args = {}
		args['url'] = url
		return DbsApi(args)

	def getParentPathList(self, api, path):
		pathList = []
		#print "listing parents for %s" %path
		datasets = api.listDatasetParents(path)
		#print "parents are %s " %datasets
		if datasets not in [[], None] :
			for dataset in datasets:
				#import pdb
				#pdb.set_trace()
				for proc in api.listProcessedDatasets(patternPrim = dataset['PrimaryDataset']['Name'],  patternProc = dataset['Name']):
					for aPath in proc['PathList']:
						pathList.append(aPath)
		#print "parents %s " %pathList				
		return pathList

	def doesPathExist(self, api, path):
		tokens = path.split('/')
		datasets = api.listProcessedDatasets(patternPrim = tokens[1], patternProc = tokens[2])
		if datasets in [[], None] :
			return False;
		else:
			return True;
			
	def migratePath(self, path):

		#Get the parents of the path
		datasets = self.getParentPathList(self.apiSrc, path)
		if datasets not in [[], None] :
			for dataset in datasets:
				#Does the parent exist in dst DBS
				if not self.doesPathExist(self.apiDst, dataset):
					#Transfer all the blocks in this parent dataset
					self.migratePath(dataset)
					
		#print "checking path %s in dest " %path
		if not self.doesPathExist(self.apiDst, path):
			#print "path does not exists "
			#Transfer all the blocks in this child path
			#print "listing blocks in path "
			for block in self.apiSrc.listBlocks(path):
				self.migrateBlockBasic(path, block['Name'])
	



	
	def migrateBlockBasic(self, path, blockName):
		try:
			print "Transferring path %s " %path
			print "            block %s " %blockName
			self.apiDst.insertDatasetContents(self.apiSrc.listDatasetContents(path,  blockName))
		except DbsBadRequest, ex:
			print ex
			# If not block excep then raise it again
			if int(ex.getErrorCode()) != 1024:
				raise ex
		
		
	def migrateBlock(self, path, blockName):
		#Get the parents of the path
		datasets = self.getParentPathList(self.apiSrc, path)
		if datasets not in [[], None] :
			for dataset in datasets:
				#Check if the child path is in dest DBS
				if not self.doesPathExist(self.apiDst, dataset):
					self.migratePath( dataset)
		self.migrateBlockBasic(path, blockName)
		
		
	
	


usage = "\n****************************************************************" + \
	"\npython dbsMigrateRecursive.py source_url targert_url datasetPath blockName" + \
	"\nIf you do not supply this op parameter then the default is assumed which is both." + \
	"\nExample :" + \
	"\npython dbsMigrateRecursive.py SOURCE_DBS_URL TARGET_DBS_URL  /CSA06-081-os-minbias/DIGI/CMSSW_0_8_1-GEN-SIM-DIGI-1154005302-merged /CSA06-081-os-minbias/DIGI/CMSSW_0_8_1-GEN-SIM-DIGI-1154005302-merged#ABCDEGFH-12345-WERTY" + \
	"\n****************************************************************" 
	
if (len(sys.argv) < 4) :
	print usage
	sys.exit(1)


path = sys.argv[3]
transfer = DbsMigrateApi(sys.argv[1], sys.argv[2])
api = transfer.getAPISrc()
for block in api.listBlocks(path):
	transfer.migrateBlock(path, block['Name'])
#transfer.migratePath(path)

print "Done"
			
