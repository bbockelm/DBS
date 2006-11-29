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
from dbsAlgorithm import DbsAlgorithm
from dbsQueryableParameterSet import DbsQueryableParameterSet
from dbsOptions import DbsOptionParser

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)
 
algo = DbsAlgorithm (
         ExecutableName="TestExe01",
         ApplicationVersion= "TestVersion01",
         ApplicationFamily="AppFamily01",
         ParameterSetID=DbsQueryableParameterSet(
                              Hash="001234565798685", 
                              Name="MyFirstParam01", 
                              Version="V001",
                              Type="test",
                              Annotation="This is test",
                              Content="int a= {}, b={c=1, d=33}, f={}, x, y, x"
                              )
         )

print "Creating an algorithm %s" % algo

try:
    api.insertAlgorithm (algo)
    print "Result: %s" % algo
except DbsObjectExists, ex:
    print "Object existed already, passing"

print "Done"

