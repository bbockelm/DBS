#!/usr/bin/env python
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
import sys
import os
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser
from DBSAPI.dbsApi import DbsApi


def isIn(aparent, parentList):
	for i in parentList:
		if i['LogicalFileName'] == aparent['LogicalFileName']:
			return True
		return False
	

def getRealParent(merged2ParentList, merged1FileList):
	for afile in merged1FileList:
		parentList = afile['ParentList']
		for aparent in parentList:
			if isIn(aparent, parentList):
				return afile['LogicalFileName']
			#print 'checking %s ' %aparent
			#if aparent in merged2ParentList:
			#	return afile[LogicalFileName]
			
	


optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)

try:
	#unmerged1 = sys.argv[1]
	#unmerged2 = sys.argv[2]
	merged1 = sys.argv[1]
	merged2 = sys.argv[2]

	merged2FileList = api.listFiles(path = merged2, retriveList=['retrive_parent'])
	merged1FileList = api.listFiles(path = merged1, retriveList=['retrive_parent'])
	#unmerged1FileList = api.listFiles(path = unmerged1, retriveList=['retrive_child'])
	for afile in merged2FileList:
		print "Checking File %s" %afile['LogicalFileName']
		parentList = afile['ParentList']
		#for aparent in parentList:
		#	print '\t %s' %aparent['LogicalFileName']
		realParent = getRealParent(parentList, merged1FileList)	
		#print '\t REAL PARENT %s' %realParent
		if realParent not in (None, ""):
			for aparent in parentList:
				if aparent['LogicalFileName'] != realParent:
					print 'Deleting the parent %s' %aparent['LogicalFileName']
					api.deleteFileParent(afile['LogicalFileName'], aparent['LogicalFileName'])
		print 'Inserting the real parent %s' %realParent
		api.insertFileParent(afile['LogicalFileName'], realParent)

	"""
	
	for afile in merged1FileList:
		print afile['LogicalFileName']
		parentList = afile['ParentList']
		for aparent in parentList:
			print '\t %s' %aparent['LogicalFileName']

	for afile in unmerged1FileList:
		print afile['LogicalFileName']
		childList = afile['ChildList']
		for achild in childList:
			print '\t %s' %achild['LogicalFileName']
		print "-----------------------UNMERGED-----------------------"
	"""

except DbsApiException, ex:
	print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
	if ex.getErrorCode() not in (None, ""):
		print "DBS Exception Error Code: ", ex.getErrorCode()
print "Done"
			
