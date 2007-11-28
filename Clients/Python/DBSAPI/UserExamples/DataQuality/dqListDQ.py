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
from DBSAPI.dbsPrimaryDataset import DbsPrimaryDataset
from DBSAPI.dbsOptions import DbsOptionParser
from DBSAPI.dbsDQFlag import DbsDQFlag
from DBSAPI.dbsRunLumiDQ import DbsRunLumiDQ

optManager  = DbsOptionParser()
(opts,args) = optManager.getOpt()
api = DbsApi(opts.__dict__)


def print_flags_nice(dqHierarchyList):
    for aDQ in dqHierarchyList:
        print "\nRunNumber: ", aDQ['RunNumber']
        print "LumiSectionNumber: ", aDQ['LumiSectionNumber']
        for aSubDQ in aDQ['DQFlagList']:
                print "      ", aSubDQ['Name'], aSubDQ['Value']
                for aSubSubDQ in aSubDQ['SubSysFlagList']:
                        print "                ", aSubSubDQ['Name'], aSubSubDQ['Value']


#-------------------------------------------------------------------------------
# Sub-Sub System Flag
flag1 = DbsDQFlag (
        Name = "HCAL+",
        Value = "GOOD",
        )
# Sub-Sub System Flag
flag2 = DbsDQFlag (
        Name = "HCAL-",
        Value = "GOOD",
        )

# Sub System Flag (Including sub-sub system flags)
flag3 = DbsDQFlag (
        Name = "HCAL",
        Value = "GOOD",
	#Well no one stops you from specifying Sub Flags
	#SubSysFlagList = [flag11, flag12]
        )
#-------------------------------------------------------------------------------

#Create RunDQ Object, for RunNumber , RunNumber  already exists in DBS

run_dq_search_criteria = DbsRunLumiDQ (
        RunNumber=1,
	#LumiSectionNumber can be part of this serach criteria
        LumiSectionNumber=123,
        #DQFlagList = [flag1]
        DQFlagList = [flag1, flag2, flag3]
        )

try:


    # One can pass a LIST of DbsRunLumiDQ Objects, that tells the API
    # What Runs/LumiSections to Look for and what Flags to look for
    # If the Objects are prepared with "hierarch or NOT, they will be pulled 
    # in hierarch.	

    # Mind that run_dq_search_criteria is just one object, API takes a LIST of such objects
    # So you must pass it as list

    #dqHierarchyList =  api.listRunLumiDQ(  runLumiDQList=[run_dq_search_criteria]  )
 
    # ALL of them, ARE U CRAZY ?
    print "\n-------------------CURRENT...."
    dqHierarchyList =  api.listRunLumiDQ(   )
    print_flags_nice(dqHierarchyList)

    """ 
    # List a specific Version DQ_00_00_00
    print "\n-------------------Version DQ_00_00_00..."
    dqHierarchyList =  api.listRunLumiDQ(dqVersion="DQ_00_00_00")
    print_flags_nice(dqHierarchyList)

    print "\n-------------------Version DQ_00_00_01..."
    dqHierarchyList =  api.listRunLumiDQ(dqVersion="DQ_00_00_01")
    print_flags_nice(dqHierarchyList)

    print "\n-------------------Version DQ_00_00_02..."
    dqHierarchyList =  api.listRunLumiDQ(dqVersion="DQ_00_00_02")
    print_flags_nice(dqHierarchyList)
    """

except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

