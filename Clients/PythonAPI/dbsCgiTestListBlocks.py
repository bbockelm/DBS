#!/usr/bin/env python
#
# Unit tests for the DBS CGI implementation.

import sys
from dbsCgiApi import DbsCgiApi
from dbsException import DbsException
from dbsApi import DbsApi, DbsApiException, InvalidDataTier

DEFAULT_URL = "http://cmsdoc.cern.ch/cms/test/aprom/DBS/CGIServer/prodquerytest2"
#DEFAULT_URL = "exec:../../Servers/CGIServer/prodquerytest2"

try:
  args = {}
  if len(sys.argv) == 2: args['instance'] = sys.argv[1]
  api = DbsCgiApi(DEFAULT_URL, args)

  #dataset = "/CSA06-081-os-minbias/DIGI/CMSSW_0_8_1-GEN-SIM-DIGI-1154005302"
  #dataset = "/CSA06-083-os-SoftMuon/DIGI/CMSSW_0_8_3-GEN-SIM-DIGI-HLT-1156877643-merged"
  datasets = [
              "/CSA06-083-os-EWKSoup/GEN/CMSSW_0_8_3-GEN-SIM-DIGI-HLT-1156877645",
              "/CSA06-083-os-EWKSoup/DIGI/CMSSW_0_8_3-GEN-SIM-DIGI-HLT-1156877645",
              "/CSA06-083-os-EWKSoup/SIM/CMSSW_0_8_3-GEN-SIM-DIGI-HLT-1156877645",
              "/CSA06-083-os-EWKSoup/HLT/CMSSW_0_8_3-GEN-SIM-DIGI-HLT-1156877645",
             ]


  datasetsa = [
		"/CSA06-082-os-TTbar/DIGI/CMSSW_0_8_2-GEN-SIM-DIGI-1155826011-merged",
		"/CSA06-082-os-TTbar/SIM/CMSSW_0_8_2-GEN-SIM-DIGI-1155826011-merged",
		"/CSA06-082-os-TTbar/GEN/CMSSW_0_8_2-GEN-SIM-DIGI-1155826011-merged"
             ]


  for dataset in datasets:
	  print dataset
	  mylist = api.listBlocks(dataset,"yes")
	  events = long(0)
	  files = long(0)
	  print "__________________________________________"
	  for i in mylist.keys() :
		print mylist[i]
		events = events +  mylist[i][0]
		files = files +  mylist[i][2]
	  print "__________________________________________"
	  print "EVENTS --> ", events
	  print "FILES -->  ", files
	  print "\n"
  #print api.listBlocks(dataset)

except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())

print "Done"
