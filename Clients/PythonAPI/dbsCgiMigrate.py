#!/usr/bin/env python
#
# Unit tests for the DBS CGI implementation.

import sys
from dbsCgiApi import DbsCgiApi, DbsCgiDatabaseError
from dbsException import DbsException
from dbsApi import DbsApi, DbsApiException, InvalidDataTier

#DEFAULT_URL = "http://cmsdoc.cern.ch/cms/test/aprom/DBS/CGIServer/prodquerytest2"
DEFAULT_URL = "exec:../../Servers/CGIServer/prodquerytest2"
#DEFAULT_URL = "http://cmslcgco01.cern.ch/cms/test/aprom/DBS/CGIServer/prodquerytest2"

try:
	args = {}
	args1 = {}
	usage = "\n****************************************************************" + \
		"\npython dbsCgiMigrate.py instanceFrom instanceTo datasetPath [op]" + \
		"\nop" + \
		"\n\tboth: This will fetch the contents of the dataset and save it in a xml file and then write the contents of that xml to another DBS instance " + \
		"\n\tget: This will just fetch the contents of the dataset and save it in a xml file. It will not write the contents to any other DBS instance." + \
		"\n\tset: This will just write the contents of xml file to another DBS instance. It will not fetch the dataset contents first." + \
		"\nIf you do not supply this op parameter then the dafault is assumed which is both." + \
		"\nExample :" + \
		"\npython dbsCgiMigrate.py MCLocal_1/Writer DevMC/Writer /CSA06-081-os-minbias/DIGI/CMSSW_0_8_1-GEN-SIM-DIGI-1154005302-merged" + \
		"\n****************************************************************" 
	if (len(sys.argv) < 4) :
		print usage
		sys.exit(1)

	op = "both"
	if (len(sys.argv) == 5) :
		op =  sys.argv[4]
	args['instance'] = sys.argv[1]
	args1['instance'] = sys.argv[2]
	api = DbsCgiApi(DEFAULT_URL, args)
	api1 = DbsCgiApi(DEFAULT_URL, args1)
	path =  sys.argv[3]  

	print path
	name = args['instance'].replace('/','_') + "_" + args1['instance'].replace('/', '_') + path.replace('/', '_')

	if ((op == "both") | (op == "get")) :
		#Fetch the dataset contents and save them in a file
		xmlinput = api.getDatasetInfo(path)
		f = open(name + ".xml", "w");
		f.write(xmlinput)
		f.close()
		print "Dataset information fetched from " + args['instance'] + " in XML format is saved in " + name + ".xml"

	if ((op == "both") | (op == "set")) :
		#Insert the saved contents into another DBS instance
		f = open(name + ".xml", "r");
		xmlinput = f.read()
		f.close()

		flog =  open(name + ".log", "w");
		flog.write(api1.insertDatasetInfo(xmlinput))
		flog.close()
		print "The transfer log for " + args1['instance'] + " in XML format is saved in " + name + ".log"


except DbsCgiDatabaseError,e:
   print e
except InvalidDataTier, ex:
  print "Caught InvalidDataTier API exception: %s" % (ex.getErrorMessage())
except DbsApiException, ex:
  print "Caught API exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
except DbsException, ex:
  print "Caught exception %s: %s" % (ex.getClassName(), ex.getErrorMessage())
