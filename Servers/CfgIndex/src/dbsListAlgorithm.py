#!/usr/bin/env python
#
# Revision: 1.3 $"
# Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
#
#
import sys
from DBSAPI.dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser
from string import *

try:
  optManager  = DbsOptionParser()
  (opts,args) = optManager.getOpt()
  api = DbsApi(opts.__dict__)
  
  #print ""
  #print "Algorithms...."
  numentries=0
  numpsets=0
  suffix=sys.argv[2]
  for app in api.listAlgorithms(patternVer=sys.argv[1]): 
    numentries=numentries+1
    version=app['ApplicationVersion']
    hash=app['ParameterSetID']['Hash']
    print numpsets,"Hash:",hash
    content=app['ParameterSetID']['Content']
    name=app['ParameterSetID']['Name'].replace("$Source: ","").replace(",v $","")
    if hash != "NO_PSET_HASH":
      numpsets=numpsets+1
      out=open(hash+suffix,"w")
      out.write("%s"%content)
      out.close()
      out=open(hash+".info","w")
      out.write(" %s %s %s "%(version,hash,name))
      out.close()
    #num=name.find("Configuration")
    #if num>0: 
        #name=name[num:]
        #words=name.split("/")
        #nw=len(words)
        #location=join(words[0:nw-2],"/")+"/"
        #name=words[nw-1]
        #version,hash,location,name
        #print content
        
#{'ApplicationVersion': 'CMSSW_2_1_1', 'ExecutableName': 'cmsRun', 'ParameterSetID': {'Content': '',
#'Version': '', 'Hash': 'NO_PSET_HASH', 'Name': '', 'Type': '', 'Annotation': ''}, 'LastModifiedBy':
#'/DC=ch/DC=cern/OU=computers/CN=vocms39.cern.ch', 'CreatedBy': '/DC=ch/DC=cern/OU=computers/CN=vocms
#39.cern.ch', 'CreationDate': '1218622460', 'LastModificationDate': '1218749947', 'ApplicationFamily'
#: 'Merged'}

#print "Num psets:",numpsets,", Num no_psets:",numnopsets

except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

#print "Done"
print "Num entries:",numentries,", Num psets:",numpsets
