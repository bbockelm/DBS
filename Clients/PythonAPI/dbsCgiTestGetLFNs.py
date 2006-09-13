#!/usr/bin/env python
#
# Unit tests for the DBS CGI implementation.

import sys
from dbsCgiApi import DbsCgiApi
from dbsException import DbsException
from dbsApi import DbsApi, DbsApiException, InvalidDataTier

DEFAULT_URL = "http://cmsdoc.cern.ch/cms/test/aprom/DBS/CGIServer/prodquerytest2"

try:
  args = {}
  if len(sys.argv) == 2: args['instance'] = sys.argv[1]
  api = DbsCgiApi(DEFAULT_URL, args)
  # api.setDebug(1)

  # List all datasets and count of files in all their blocks
  print "Listing datasets blocks/files"
  #dataset = "/CSA06-081-os-minbias/DIGI/CMSSW_0_8_1-GEN-SIM-DIGI-1154005302"
  dataset = "/CSA06-082-os-TTbar/SIM/CMSSW_0_8_2-GEN-SIM-DIGI-1155826011-merged"
  #valentineList = []
  #for block in api.getLFNs (dataset, "341"):
  print api.getLFNs (dataset, "0ec79c44-59bb-4b1f-a748-4d268d9dd091")
  #for a in api.getLFNs (dataset, "341"):
      #print a
      #files = block['fileList']
      #name = block['blockName']
      #print "  %s: %d files" % (name, len(files) )
      #for f in files:
      #   print f
      #print files  
      #print files[0]  
      #print files[0].values()  

      #for i in range(len(block['fileList'])):
        #valentineList.append( block['fileList'][i].values() )
  #print valentineList 
except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"
