#!/usr/bin/env python
#
# API Unit tests for the DBS JavaServer.

import sys
from dbsApi import DbsApi
from dbsException import *
from dbsApiException import *
from dbsOptions import DbsOptionParser
optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)
      
lapiObj = ""
f = ""
index = 0
class DbsUnitTestApi:
	def __init__(self, obj, fileObj):
		global lapiObj
		global f
		global index
		lapiObj = obj
		f = fileObj
		index = 0
	def run(*listArgs, **dictArgs):
		try:
			global lapiObj
			global f
			global index
			index = index + 1
			info =  str(lapiObj.im_func.func_name) + str(listArgs[1:])
			#print info
			excep = dictArgs['excep']
			lapiObj(*listArgs[1:])
			#for data in apiObj(*listArgs):
				#print "  %s" % data
			if excep:
				f.write("\n" + str(index) + ") FAILED. \t" + info + " AN EXCEPTION WAS EXPECTED BUT NONE WAS RAISED")
			else:
				f.write("\n" + str(index) + ") PASSED. \t" + info + " AN EXCEPTION WAS NOT EXPECTED AND NONE WAS RAISED ")
		except:
			exception =  str(sys.exc_info()[0]) + " : " +  str(sys.exc_info()[1])
			print exception
			if excep:
				f.write("\n" + str(index) + ") PASSED. \t" + info + " AN EXCEPTION WAS EXPECTED AND RAISED. THE EXCEPTION IS : " + exception)
			else:
				f.write("\n" + str(index) + ") FAILED. \t" + info + " AN EXCEPTION WAS NOT EXPECTED BUT RAISED. THE EXCEPTION IS : " + exception)

	def getExistingPDPath(self):
		for proc in api.listProcessedDatasets("*"):
			return "/" + str(proc['PrimaryDataset']['Name']) + "/" + str(proc['tierList'][0]) + "/" + str(proc['Name'])

	def getExistingBlock(self):
		for block in api.listBlocks(self.getExistingPDPath()):
			return block['Name']
	"""
	def getExistingRunNumber(self):
		for proc in api.listProcessedDatasets("*"):
			path =  "/" + str(proc['PrimaryDataset']['Name']) + "/" + str(proc['tierList'][0]) + "/" + str(proc['Name'])
			for run in api.listRuns(path):
				print run
				#print run['run_number']
	"""
a = DbsUnitTestApi(None, None)
print a.getExistingBlock()
#print a.getExistingPDPath()
#print a.getExistingRunNumber()
