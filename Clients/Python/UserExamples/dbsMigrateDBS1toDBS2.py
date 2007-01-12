#!/usr/bin/env python
#
#
#

import os, re, string, xml.sax, xml.sax.handler, sys
from xml.sax.saxutils import escape
from cStringIO import StringIO

# DBS specific modules
from dbsApi import DbsApi
from dbsHttpService import DbsHttpService
from dbsExecService import DbsExecService

from dbsException import DbsException
from dbsApiException import *

from dbsBaseObject import *
from dbsRun import DbsRun
from dbsQueryableParameterSet import DbsQueryableParameterSet
from dbsProcessedDataset import DbsProcessedDataset
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsLumiSection import DbsLumiSection
from dbsFile import DbsFile
from dbsFileBlock import DbsFileBlock
from dbsDataTier import DbsDataTier
from dbsAlgorithm import DbsAlgorithm

from dbsParent import DbsParent
from dbsConfig import DbsConfig
from dbsOptions import DbsOptionParser

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
				self.fileName = name[length] + '.root'
				self.evc[self.fileName] = int(attrs['events'])
				
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
					ofw = '0';
					if (str(attrs['status']) == 'OPEN'):
						ofw = '1'
					self.block = DbsFileBlock (
						Name = str(attrs['name']),
						#OpenForWriting = ofw
						)
					print "Inserting block  %s " % self.block
					api.insertBlock (self.processed, self.block)
				
			if name == 'afile':
				print "afile found %s " % str(attrs['lfn'])
				if (len(self.fileList) == 30) :
					print "Inserting files  %s " % self.fileList
					print "block  %s " % self.block
					api.insertFiles (self.processed, self.fileList, self.block)
					self.fileList = []
					
				name = attrs['lfn'].split('/')
				length = len(name) - 1
				noOfEvents = 0
				rootName = name[length]
				if (rootName in self.evc.keys()):
					noOfEvents = self.evc[rootName]
					
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
						#Dataset = self.processed,
						ParentList = [parentName],
						AlgoList = [self.algo],
						TierList = self.tierList
						)
				self.fileList.append(self.dbsfile)
				
		def endElement(self, name):
			if name == 'processing':
				if (len(self.fileList) > 0) :
					print "Inserting files  %s " % self.fileList
					print "block  %s " % self.block
					api.insertFiles (self.processed, self.fileList, self.block)
								
	xml.sax.parseString (data, Handler ())
except Exception, ex:
	print "Exception %s " % ex
print "Done"

