#!/usr/bin/env python
#
# Unit tests for the DBS CGI implementation.

import sys
from dbsCgiApi import DbsCgiApi, DbsCgiDatabaseError
from dbsException import DbsException
from dbsApi import DbsApi, DbsApiException, InvalidDataTier

#DEFAULT_URL = "http://cmsdoc.cern.ch/cms/aprom/DBS/CGIServer/prodquery"
#DEFAULT_URL = "http://cmsdoc.cern.ch/cms/aprom/DBS/CGIServer/dbsxml"
DEFAULT_URL = "exec:../CGIServer/prodquery"

try:
  args = {}
  if len(sys.argv) == 2: args['instance'] = sys.argv[1]
  api = DbsCgiApi(DEFAULT_URL, args)
  #api.setLogLevel(DBS_LOG_LEVEL_ALL_)
  # api.setDebug(1)
  try:
   # List all parameter sets
   print ""
   print "Listing parameter sets"
   for pset in api.listParameterSets():
     print "  %s" % pset
  except DbsCgiDatabaseError,e:
   print e
  
  try:
   # List all applications
   print ""
   print "Listing applications"
   for app in api.listApplications():
     print "  %s" % app
   # List all application configurations
   print ""
   print "Listing application configurations"

   for appc in api.listApplicationConfigs():
     print "  %s" % appc
  except DbsCgiDatabaseError,e:
   print e

  try:
   # List all primary datasets
   print ""
   print "Listing primary datasets t*"
   for p in api.listPrimaryDatasets("t*"):
     print "  %s" % p
  except DbsCgiDatabaseError,e:
   print e

  # Datasets we play with
  datasetPattern = "/*/*/test*"

  try:
   # List some datasets
   print ""
   print "Listing datasets %s" % datasetPattern
   datasets = api.listProcessedDatasets (datasetPattern)
   for dataset in datasets:
     print "  %s" % dataset
  except DbsCgiDatabaseError,e:
   print e

  
  try:
   # Get dataset provenance. It returns list of dataset parents.
   print ""
   tiers = [ "Hit" ]
   datasetPath = datasets[1]
   print "Provenance for: %s (dataTiers: %s)" % (datasetPath, tiers)
   for parent in api.getDatasetProvenance(datasetPath, tiers):
     print "  %s" % parent
  except DbsCgiDatabaseError,e:
   print e

  try:
   print ""
   tiers = [ "Digi", "Hit" ]
   otherDatasetPath = datasets[2]
   print "Provenance for: %s (dataTiers: %s)" % (otherDatasetPath, tiers)
   for parent in api.getDatasetProvenance(otherDatasetPath, tiers):
     print "  %s" % parent
  except DbsCgiDatabaseError,e:
   print e

  try:
   # Get dataset contents, returning a list of blocks with event collections
   print ""
   print "Dataset contents for: %s" % otherDatasetPath
   for block in api.getDatasetContents(otherDatasetPath):
     print "  File block name/id: %s/%d, %d event collections}" % \
       (block.get('blockName'), block.get('objectId'), len(block.get('eventCollectionList')) )
  except DbsCgiDatabaseError,e:
   print e
 
  try:
   # Get dataset contents as a list of blocks with files
   print ""
   print "Dataset files for: %s" % datasets[0]
   for block in api.getDatasetFileBlocks (datasets[0]):
     print "  File block name/id: %s/%d, %d files}" % \
       (block.get('blockName'), block.get('objectId'), len(block.get('fileList')) )
  except DbsCgiDatabaseError,e:
   print e

except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"
