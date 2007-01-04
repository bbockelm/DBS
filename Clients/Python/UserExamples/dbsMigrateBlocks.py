#!/usr/bin/env python
#
# Unit tests for the DBS CGI implementation.
import sys
from dbsApi import DbsApi
from dbsException import *
from dbsApiException import *
from dbsOptions import DbsOptionParser


try:
	optManager  = DbsOptionParser()
	(opts,args) = optManager.getOpt()
	      
	argsSrc = {}
	argsTar = {}
	usage = "\n****************************************************************" + \
		"\npython dbsMigrateBlocks.py source_url targert_url datasetPath [op]" + \
		"\nop" + \
		"\n\tboth: This will fetch the contents of the dataset and save it in a xml file and then write the contents of that xml to another DBS instance " + \
		"\n\tget: This will just fetch the contents of the dataset and save it in a xml file. It will not write the contents to any other DBS instance." + \
		"\n\tset: This will just write the contents of xml file to another DBS instance. It will not fetch the dataset contents first." + \
		"\nIf you do not supply this op parameter then the dafault is assumed which is both." + \
		"\nExample :" + \
		"\npython dbsMigrateBlocks.py SOURCE_DBS_URL TARGET_DBS_URL  /CSA06-081-os-minbias/DIGI/CMSSW_0_8_1-GEN-SIM-DIGI-1154005302-merged" + \
		"\nYou can give the keywrod LOCALDBS which will read the configuration file dbs.config to get the DBS URL" + \
		"\nThis keywrod can be used for both source or target DBS" + \
		"\npython dbsMigrateBlocks.py LOCALDBS TARGET_DBS_URL  /CSA06-081-os-minbias/DIGI/CMSSW_0_8_1-GEN-SIM-DIGI-1154005302-merged" + \
		"\n****************************************************************" 
	if (len(sys.argv) < 4) :
		print usage
		sys.exit(1)

	op = "both"
	if (len(sys.argv) == 5) :
		op =  sys.argv[4]
	
	argsSrc['url'] = sys.argv[1]
	argsTar['url'] = sys.argv[2]
	
	if(argsSrc['url'] == "LOCALDBS" ):
		apiSrc = DbsApi(opts.__dict__)
	else :
		apiSrc = DbsApi(argsSrc)
		
	if(argsTar['url'] == "LOCALDBS" ):
		apiTar = DbsApi(opts.__dict__)	
	else:
		apiTar = DbsApi(argsTar)
		
	path =  sys.argv[3]  

	print path
	#name = argsSrc['url'].replace('/','_') + "_" + argsTar['url'].replace('/', '_') + path.replace('/', '_')
	name = argsSrc['url'].replace('/','_').replace(':', '_') + "_" + argsTar['url'].replace('/', '_').replace(':', '_') 

	blocks = apiSrc.listBlocks(path)
	if ((op == "both") | (op == "get")) :
		#Fetch the dataset contents and save them in a file
		for i in blocks:
			blockName = i['Name']
			fileName = blockName.replace('/', '_').replace('#', '_') + ".xml"
			print "Fetching information for Block %s " %  blockName
			xmlinput = apiSrc.listDatasetContents(path,  blockName)
			f = open(name + fileName, "w");
			f.write(xmlinput)
			f.close()
			print "Dataset information fetched from " + argsSrc['url'] + " in XML format is saved in " + name +  fileName

	if ((op == "both") | (op == "set")) :
		#Insert the saved contents into another DBS instance
		for i in blocks:
			blockName = i['Name']
			fileName = blockName.replace('/', '_').replace('#', '_') + ".xml"
			print "Inserting information for Block %s " %  blockName
			f = open(name + fileName, "r");
			xmlinput = f.read()
			f.close()

			flog =  open(name + fileName + ".log", "w");
			#flog.write(api1.insertDatasetInfo(xmlinput))
			flog.write(apiTar.insertDatasetContents(xmlinput))
			flog.close()
			print "The transfer log for " + argsTar['url'] + " in XML format is saved in " + name + fileName + ".log"

except DbsApiException, ex:
	print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
	if ex.getErrorCode() not in (None, ""):
		print "DBS Exception Error Code: ", ex.getErrorCode()
print "Done"
			
