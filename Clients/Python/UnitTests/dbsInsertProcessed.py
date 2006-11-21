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
from dbsPrimaryDataset import DbsPrimaryDataset
from dbsProcessedDataset import DbsProcessedDataset
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

primary = DbsPrimaryDataset (Name = "test_primary_anzar_001")
proc = DbsProcessedDataset (
                            PrimaryDataset=primary, 
                            Name="TestProcessedDS002", 
                            PhysicsGroup="BPositive",
                            Status="Valid",
                            TierList=['SIM', 'RECO'],
                            AlgoList=[algo],
                            )
                             
print "Creating a processed dataset %s" % proc

try:
    api.insertProcessedDataset (proc)
    print "Result: %s" % proc
except DbsObjectExists, ex:
    print "Object existed already, passing"


print "Done"

