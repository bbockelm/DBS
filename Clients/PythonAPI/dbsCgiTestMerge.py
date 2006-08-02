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

DEFAULT_URL = "http://cmsdoc.cern.ch/cms/aprom/DBS/CGIServer/prodquery"
#DEFAULT_URL = "exec:../CGIServer/prodquery"

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
    pass	  
    #api.createProcessedDataset (hits)
    #api.createProcessedDataset (digi)
    #api.createProcessedDataset (dst)
  except DbsCgiObjectExists, ex:
    print "Object existed already, passing"
  


  file1 = DbsFile (logicalFileName="tmpaLFN1", fileSize=1, checkSum="cksum:1", fileType="EVD")
  file2 = DbsFile (logicalFileName="tmpaLFN2", fileSize=1, checkSum="cksum:1", fileType="EVD")
  file3 = DbsFile (logicalFileName="tmpaLFN3", fileSize=1, checkSum="cksum:1", fileType="EVD")
  file4 = DbsFile (logicalFileName="tmpaLFN4", fileSize=1, checkSum="cksum:1", fileType="EVD")
  file5 = DbsFile (logicalFileName="tmpaLFN5", fileSize=1, checkSum="cksum:1", fileType="EVD")
  file6 = DbsFile (logicalFileName="tmpaLFN6", fileSize=1, checkSum="cksum:1", fileType="EVD")
  fileout = DbsFile (logicalFileName="tmpaLFNout", fileSize=1, checkSum="cksum:1", fileType="EVD")

  
  
  eout = DbsEventCollection (collectionName="tmpwE2and5", numberOfEvents=10, fileList=[fileout])
  e1 = DbsEventCollection (collectionName="tmpwE1", numberOfEvents=10, fileList=[file1])
  e2 = DbsEventCollection (collectionName="tmpwE2", numberOfEvents=10, fileList=[file2],  parentageList=[ { 'parent' : e1, 'type' : 'Hit' } ])
  e3 = DbsEventCollection (collectionName="tmpwE3", numberOfEvents=10, fileList=[file3],  parentageList=[ { 'parent' : e2, 'type' : 'Hit' } ])

  e4 = DbsEventCollection (collectionName="tmpwE4", numberOfEvents=10, fileList=[file4])
  e5 = DbsEventCollection (collectionName="tmpwE5", numberOfEvents=10, fileList=[file5],  parentageList=[ { 'parent' : e4, 'type' : 'Hit' } ])
  e6 = DbsEventCollection (collectionName="tmpwE6", numberOfEvents=10, fileList=[file6],  parentageList=[ { 'parent' : e5, 'type' : 'Hit' } ])

  
  print ""
  print "Inserting file " 
  try:
    pass
    api.insertFiles (block, [ file1, file2, file3, file4, file5, file6, fileout])
  except DbsCgiObjectExists, ex:
    print "Object existed already, passing"
    
  
  print "Inserting event collections\n %s" % (eout)
  try:
     #pass	
     
     api.insertEventCollections (hits, [e1])
     api.insertEventCollections (hits, [e2])
     api.insertEventCollections (hits, [e3])
     api.insertEventCollections (hits, [e4])
     api.insertEventCollections (hits, [e5])
     api.insertEventCollections (hits, [e6])
     api.insertEventCollections (digi, [ eout])
  except DbsCgiObjectExists, ex:
     print "Object existed already, passing",ex
  except:
     print "Unexpected error:", sys.exc_info()[0]
									      
  
  print "Remapping file\n %s\n " % (file2)
  print "and file\n %s\n " % (file5)
  try:
    api.remap([ file2, file5 ], fileout)
  except DbsCgiObjectExists, ex:
    print "Object existed already, passing"
  
  
except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"
