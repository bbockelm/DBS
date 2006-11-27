#!/usr/bin/env python
#
# API Unit tests for the DBS JavaServer.

import sys
from dbsApi import DbsApi
from dbsException import *
from dbsApiException import *
from dbsOptions import DbsOptionParser
import pdb

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)
      
class DbsUnitTestApi:
	def __init__(self, obj, fileObj):
		self.lapiObj = obj
		self.f = fileObj
		self.index = 0
                self.verbose=0
        def setVerboseLevel(self,level):
            self.verbose=level
        def printTestStatus(self,info,status,iMsg,exp=None):
            msg = "\n\nTest started : %s"%str(self.lapiObj.im_func.func_name)
            msg+= "\n"+info
            if self.verbose:
               msg+="\nComment      : %s"%iMsg
            if self.verbose==2:
               msg+="\nException    : %s"%exp
            msg+=   "\nTest ended   : [%6s]"%status
            self.f.write(msg)

	def run(self, *listArgs, **dictArgs):
		try:

			self.index = self.index + 1
			info =  str(self.lapiObj.im_func.func_name) + str(listArgs)
			#info =  str(self.lapiObj.im_func.func_name) + str(listArgs[1:])
			#print info
			excep = dictArgs['excep']
			self.lapiObj(*listArgs)
			#self.lapiObj(*listArgs[1:])
			#for data in apiObj(*listArgs):
				#print "  %s" % data
			if excep:
                                self.printTestStatus(info,"FAILED","AN EXCEPTION WAS EXPECTED BUT NONE WAS RAISED")
			else:
                                self.printTestStatus(info,"PASSED","AN EXCEPTION WAS NOT EXPECTED AND NONE WAS RAISED")
		except:
			exception =  str(sys.exc_info()[0]) + " : " +  str(sys.exc_info()[1])
                        print exception
			if excep:
                                self.printTestStatus(info,"PASSED","AN EXCEPTION WAS EXPECTED AND RAISED. THE EXCEPTION IS",exception)
			else:
                                self.printTestStatus(info,"FAILED","AN EXCEPTION WAS NOT EXPECTED BUT RAISED. THE EXCEPTION IS",exception)

	def getExistingPDPath(self):
              try:
		for proc in api.listProcessedDatasets("*"):
			return "/" + str(proc['PrimaryDataset']['Name']) + "/" + str(proc['tierList'][0]) + "/" + str(proc['Name'])
              except:
                        exception =  str(sys.exc_info()[0]) + " : " +  str(sys.exc_info()[1])
                        self.f.write("\n " + str(exception))
                        
	def getExistingBlock(self):
              try:
		for block in api.listBlocks(self.getExistingPDPath()):
			return block['Name']
              except:
                        exception =  str(sys.exc_info()[0]) + " : " +  str(sys.exc_info()[1])
                        self.f.write("\n " + str(exception))

	"""
	def getExistingRunNumber(self):
		for proc in api.listProcessedDatasets("*"):
			path =  "/" + str(proc['PrimaryDataset']['Name']) + "/" + str(proc['tierList'][0]) + "/" + str(proc['Name'])
			for run in api.listRuns(path):
				print run
				#print run['run_number']
	"""
#a = DbsUnitTestApi(None, None)
#print a.getExistingBlock()
#print a.getExistingPDPath()
#print a.getExistingRunNumber()
