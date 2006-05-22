#!/usr/bin/env python
#
# Unit tests for the DBS CGI implementation.

import sys
from dbsCgiApi import DbsCgiApi, DbsCgiObjectExists
from dbsException import DbsException
from dbsFile import DbsFile
from dbsFileBlock import DbsFileBlock
from dbsEventCollection import DbsEventCollection
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsProcessedDataset import DbsProcessedDataset
from dbsProcessing import DbsProcessing
from dbsApi import DbsApi, DbsApiException, InvalidDataTier

#DEFAULT_URL = "http://cmsdoc.cern.ch/cms/aprom/DBS/CGIServer/prodquery"
DEFAULT_URL = "exec:../CGIServer/prodquery"

try:
  args = {}
  if len(sys.argv) == 2: args['instance'] = sys.argv[1]
  api = DbsCgiApi(DEFAULT_URL, args)

  primary = DbsPrimaryDataset (datasetName = "test_primary_anzar")
  print ""
  processing = DbsProcessing (primaryDataset = primary,
                              processingName = "test_process_anzar",
                              applicationConfig = {
                                'application' : { 'executable' : 'testexe',
                                                  'version' : 'test',
                                                  'family' : 'test' },
                                'parameterSet' : { 'hash' : 'test',
                                                   'content' : 'test' }})
  
  print "Creating processing %s" % processing
  try:
    api.createProcessing (processing)
    print "Result: %s" % processing
  except DbsCgiObjectExists, ex:
    print "Object existed already, passing"
  
  

  # Attempt to create a file block
  print ""
  block = DbsFileBlock (processing = processing, blockName = "test")
  
  print "Creating file block %s" % block
  try:
    api.createFileBlock (block)
    print "Result: %s" % block
    print "Block creation commented out!"
  except DbsCgiObjectExists, ex:
    print "Object existed already, passing"
  
  # Attempt to create hits, digi and dst datasets
  print ""
  
  
  hits = DbsProcessedDataset (primaryDataset=primary,
		              datasetName="test_process_anzar",
			      dataTier="Hit")
			      
  digi = DbsProcessedDataset (primaryDataset=primary,
		              datasetName="test_process_anzar",
			      dataTier="Digi")
  dst = DbsProcessedDataset (primaryDataset=primary,
		             datasetName="test_process_anzar",
			     dataTier="DST")
  
  print "Creating datasets\n %s\n %s\n %s" % (hits, digi, dst)
  try:
    api.createProcessedDataset (hits)
    api.createProcessedDataset (digi)
    api.createProcessedDataset (dst)
  except DbsCgiObjectExists, ex:
    print "Object existed already, passing"
  


  file = DbsFile (logicalFileName="LFN1", fileSize=1, checkSum="cksum:1", fileType="EVD")
  file2 = DbsFile (logicalFileName="LFN5", fileSize=1, checkSum="cksum:1", fileType="EVD")
  ech = DbsEventCollection (collectionName="DC2", numberOfEvents=1, fileList=[file])
  
  file1 = DbsFile (logicalFileName="myLFN1", fileSize=1, checkSum="cksum:1", fileType="EVD")
  eout = DbsEventCollection (collectionName="ec2and5", numberOfEvents=10, fileList=[file1])
  e1 = DbsEventCollection (collectionName="E1", numberOfEvents=10, fileList=[file1])
  e2 = DbsEventCollection (collectionName="E2", numberOfEvents=10, fileList=[file1],  parentageList=[ { 'parent' : e1, 'type' : 'Hit' } ])
  e3 = DbsEventCollection (collectionName="E3", numberOfEvents=10, fileList=[file1],  parentageList=[ { 'parent' : e2, 'type' : 'Digi' } ])

  e4 = DbsEventCollection (collectionName="E4", numberOfEvents=10, fileList=[file1])
  e5 = DbsEventCollection (collectionName="E5", numberOfEvents=10, fileList=[file1],  parentageList=[ { 'parent' : e4, 'type' : 'Hit' } ])
  e6 = DbsEventCollection (collectionName="E6", numberOfEvents=10, fileList=[file1],  parentageList=[ { 'parent' : e5, 'type' : 'Digi' } ])

  ecd = DbsEventCollection (collectionName="DC2", numberOfEvents=1, fileList=[file] )
  
  print ""
  print "Inserting file %s" % file
  try:
    #api.insertFiles (block, [ file2 ])
    api.insertFiles (block, [ file1 ])
  except DbsCgiObjectExists, ex:
    print "Object existed already, passing"
    
  
  print "Inserting event collections\n %s" % (eout)
  try:
     #api.insertEventCollections (hits, [ eout])
     api.insertEventCollections (hits, [e1])
     api.insertEventCollections (digi, [e2])
     api.insertEventCollections (dst, [e3])
     api.insertEventCollections (hits, [e4])
     api.insertEventCollections (digi, [e5])
     api.insertEventCollections (dst, [e6])
  except DbsCgiObjectExists, ex:
     print "Object existed already, passing",ex
  except:
     print "Unexpected error:", sys.exc_info()[0]
									      
  
  print "Remapping event collections\n %s\n " % (ech)
  try:
    #api.mergeAndRemap([ ech ], eout, hits)
    api.mergeAndRemap([ e2,e5 ], eout, hits)
  except DbsCgiObjectExists, ex:
    print "Object existed already, passing"
  
  
except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"
