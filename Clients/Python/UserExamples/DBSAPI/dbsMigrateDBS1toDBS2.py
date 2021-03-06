#!/usr/bin/env python
#
#
#

import os, re, string, xml.sax, xml.sax.handler, sys
from xml.sax.saxutils import escape
from cStringIO import StringIO

# DBS specific modules
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsHttpService import DbsHttpService
from DBSAPI.dbsExecService import DbsExecService

from DBSAPI.dbsException import DbsException
from DBSAPI.dbsApiException import *

from DBSAPI.dbsBaseObject import *
from DBSAPI.dbsRun import DbsRun
from DBSAPI.dbsQueryableParameterSet import DbsQueryableParameterSet
from DBSAPI.dbsProcessedDataset import DbsProcessedDataset
from DBSAPI.dbsPrimaryDataset import DbsPrimaryDataset
from DBSAPI.dbsLumiSection import DbsLumiSection
from DBSAPI.dbsFile import DbsFile
from DBSAPI.dbsFileBlock import DbsFileBlock
from DBSAPI.dbsDataTier import DbsDataTier
from DBSAPI.dbsAlgorithm import DbsAlgorithm

from DBSAPI.dbsParent import DbsParent
from DBSAPI.dbsConfig import DbsConfig
from DBSAPI.dbsOptions import DbsOptionParser

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

fileName =  sys.argv[1]
f = open(fileName, "r")
x = " "
data = f.readline()
while(x):
	x = f.readline()
	data += x
#print data

try:
	class Handler (xml.sax.handler.ContentHandler):
		def __init__ (self):
			self.tierList = []
			self.evc = {}
			self.parent = {}
			self.first = True
			self.fileList = []
			self.blockList = []
			self.block = None
			print "Initilized"

		def startElement(self, name, attrs):
			if name == 'primary-dataset':
				print "primary-dataset found %s " % str(attrs['name'])
				self.primary = DbsPrimaryDataset (Name = str(attrs['name']))
			if name == 'processed-dataset':
				print "processed-dataset found %s " % str(attrs['path'])
				path = str(attrs['path']).split('/')
				self.tierList = [path[2]]
				self.processed = DbsProcessedDataset (
						PrimaryDataset = self.primary,
						Name = path[3],
						PhysicsGroup = "PRODUCTION",
						Status = "VALID",
						TierList = self.tierList
						)
				
			if name == 'event-collection':
				print "event-collection found %s " % str(attrs['name'])
				name = str(attrs['name']).split('/')
				length = len(name) - 1
				if name[length].endswith(".root"):
					self.fileName = name[length]
				else:
					self.fileName = name[length] + '.root'
				self.evc[self.fileName] = int(attrs['events'])
				print "Saving  events for " + self.fileName + " = " + str(self.evc[self.fileName])
				
			if name == 'parent':
				print "parent found %s " % str(attrs['name'])
				name = str(attrs['name']).split('_')[1:]
				pName = str(name[0])
				for tmp in name[1:]:
					pName = pName + '_' + str(tmp)
				pName += '.root'
				length = len(name) - 1
				self.parent[self.fileName] = pName
				
				
			if name == 'processing':
				print "processing found %s " % str(attrs['executable'])
				origHash = str(attrs['hash'])
				tmp = origHash.split(';')
				if(len(tmp) == 1) :
					hash = origHash
				else :
					tmp = tmp[0].split('=')
					if(len(tmp) == 1) :
						hash = tmp[0]
					else :
						hash = tmp[1]
				print hash
				self.algo = DbsAlgorithm (
						ExecutableName = str(attrs['executable']),
						ApplicationVersion = str(attrs['version']),
						ApplicationFamily = str(attrs['family']),
						ParameterSetID = DbsQueryableParameterSet(
							Hash = hash,
							Name = hash,
							Version="NOT KNOWN",
							Type = "CSA06",
							Annotation = "NOT KNOWN",
							Content = "NOT KNOWN"
							)
						)
				
			if name == 'block':
				print "block found %s " % str(attrs['name'])
				if(self.first):
					print "Inserting primary %s " % self.primary
					api.insertPrimaryDataset (self.primary)
					print "Inserting algorithm  %s " % self.algo
					api.insertAlgorithm (self.algo)
					self.processed['AlgoList'] = [self.algo]
					print "Inserting processed  %s " % self.processed
					api.insertProcessedDataset (self.processed)
					self.first = False
					
				if (self.block != None) :
					if (len(self.fileList) > 0) :
						print "Inserting files  %s " % self.fileList
						print "block  %s " % self.block
						api.insertFiles (self.processed, self.fileList, self.block)
						self.fileList = []
						
				if (attrs['files'] != '0'):
					#ofw = '0';
					if (str(attrs['status']).upper() != 'OPEN'):
						self.blockList.append(str(attrs['name']))
						#ofw = '1'
					self.block = DbsFileBlock (
						Name = str(attrs['name']),
						#OpenForWriting = ofw
						)
					print "Inserting block  %s " % self.block
					api.insertBlock (self.processed, self.block)
				
			if name == 'afile':
				print "afile found %s " % str(attrs['lfn'])
				if (len(self.fileList) == 50) :
					print "Inserting files  %s " % self.fileList
					print "block  %s " % self.block
					api.insertFiles (self.processed, self.fileList, self.block)
					self.fileList = []
					
				name = attrs['lfn'].split('/')
				length = len(name) - 1
				noOfEvents = 0
				rootName = name[length]
				#print "checking no of events for " + rootName
				if (rootName in self.evc.keys()):
					noOfEvents = self.evc[rootName]
					print "Found noOfEvents = " + str(noOfEvents)
					
				parentName = ''
				if (rootName in self.parent.keys()):
					parentName = self.parent[rootName]

					
				tmp =  str(attrs['checksum']).split(':')
				if (len(tmp) == 1):
					checkSum = tmp[0]
				else:
					checkSum = tmp[1]

				self.dbsfile = DbsFile (
						Checksum = checkSum,
						LogicalFileName = str(attrs['lfn']),
						NumberOfEvents = noOfEvents,
						FileSize = int(attrs['size']),
						Status = str(attrs['status']),
						ValidationStatus = 'VALID',
						FileType = str(attrs['type']),
						AlgoList = [self.algo],
						TierList = self.tierList
						)
				if len(parentName) > 0 :
					self.dbsfile['ParentList'] =  [parentName]
				self.fileList.append(self.dbsfile)
				
		def endElement(self, name):
			if name == 'processing':
				if (len(self.fileList) > 0) :
					print "Inserting files  %s " % self.fileList
					print "block  %s " % self.block
					api.insertFiles (self.processed, self.fileList, self.block)
				for b in self.blockList:
					print "Closing block %s " % b
					api.closeBlock (b)
								
	xml.sax.parseString (data, Handler ())
except Exception, ex:
	print "Exception %s " % ex
print "Done"

