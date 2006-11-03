#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
# Unit tests for the DBS CGI implementation.

import sys
from dbsApi import DbsApi
from dbsException import *
from dbsApiException import *

#DEFAULT_URL = "http://cmsdoc.cern.ch/cms/test/aprom/DBS/CGIServer/prodquery"
DEFAULT_URL = "http://cmssrv17.fnal.gov:8989/DBS/servlet/DBSServlet"
#DEFAULT_URL = "exec:../../Servers/CGIServer/prodquerytest2"

try:
  args = {}
  if len(sys.argv) == 2: args['instance'] = sys.argv[1]
  print args
  api = DbsApi(DEFAULT_URL, args)
  
  try:
   # List all parameter sets
   print ""
   print "Listing parameter sets"
   for pset in api.listParameterSets("**"):
     print "  %s" % pset
  except DbsDatabaseError,e:
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
   
  except DbsDatabaseError,e:
   print e
  
  try:
   # List all primary datasets
   print ""
   print "Listing primary datasets t*"
   for p in api.listPrimaryDatasets("*"):
     print "  %s" % p
  except DbsDatabaseError,e:
   print e
  # Datasets we play with
  datasetPattern = "/*/*/*"
  
  try:
   # List some datasets
   print ""
   print "Listing datasets %s" % datasetPattern
   datasets = api.listProcessedDatasets (datasetPattern)
   for dataset in datasets:
     print "  %s" % dataset
  except DbsDatabaseError,e:
   print e

  otherDatasetPath = "/PreProdR2Pion10GeV/SIM/GEN-SIM-DIGI"
  otherDatasetPath = "/CSA06-081-os-minbias/DIGI/CMSSW_0_8_1-GEN-SIM-DIGI-1154005302-merged"
  otherDatasetPath = "/CSA06-082-os-TTbar/SIM/CMSSW_0_8_2-GEN-SIM-DIGI-1155826011-merged"

  try:
   # Get dataset contents, returning a list of blocks with event collections
   print ""
   print "Dataset contents for: %s" % otherDatasetPath
   for block in api.getDatasetContents(otherDatasetPath):
     print block.get('blockName')
     #print block['guid']
     #print "  File block name/id: %s/%d, %d event collections}" % \
     #  (block.get('blockName'), block.get('objectId'), len(block.get('eventCollectionList')) )
     #for ev in block.get('eventCollectionList') :
     #  print "evc ", ev
  except DbsDatabaseError,e:
   print e
   
  #otherDatasetPath = "/PreProdR2Pion10GeV/SIM/GEN-SIM-DIGI"
  #otherDatasetPath = "/test_primary_anzar/DST/test_process_anzar"
  try:
   # Get dataset contents as a list of blocks with files
   print ""
   #print "Dataset files for: %s" % datasets[0]
   for block in api.getDatasetFileBlocks (otherDatasetPath):
     print "  File block name/id: %s/%d, %d files}" % \
       (block.get('blockName'), block.get('objectId'), len(block.get('fileList')) )
     print "Status is ", block.get('blockStatus')
     for ev in block.get('eventCollectionList') :
       print "evc ", ev
  except DbsDatabaseError,e:
   print e
   
except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"
