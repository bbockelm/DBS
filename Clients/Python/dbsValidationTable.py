#
# Revision: 0.0 $"
# Id: dbsValidationTable.py,v 0.0 2006/1/1 18:26:04 afaq Exp $"
#
""" This file is generated on Wed Nov  8 13:50:06 2006 """ 

"""SERIOUS WARNING:

         This file is a generated file,
         in case you have made manual changes to  
         any of generated files, make sure you DO NOT
         end up over-writting them by re-running the
         generator and copying them here.

         Either make changes to generator, or carefully
         preserve the manual changes. 
"""

from dbsValidateTools import *
ValidationTable = {

"DbsPrimaryDataset" : {
         "Annotation" : { "Comment" : "A required variable", "Validator" : isStringType },
         "Name" : { "Comment" : "A required variable, UNIQUE", "Validator" : isStringType },
         "StartDate" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "EndDate" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Type" : { "Comment" : "A required variable", "Validator" : isDictType },
         "Description" : { "Comment" : "User may not need to set this variable always", "Validator" : isDictType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
"DbsAnalysisDataset" : {
         "Annotation" : { "Comment" : "A required variable", "Validator" : isStringType },
         "Name" : { "Comment" : "A required variable, UNIQUE", "Validator" : isStringType },
         "Type" : { "Comment" : "A required variable", "Validator" : isStringType },
         "Status" : { "Comment" : "A required variable", "Validator" : isStringType },
         "PhysicsGroup" : { "Comment" : "A required variable", "Validator" : isStringType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
"DbsAlgorithm" : {
         "ExecutableName" : { "Comment" : "A required variable", "Validator" : isStringType },
         "ApplicationVersion" : { "Comment" : "A required variable", "Validator" : isStringType },
         "ApplicationFamily" : { "Comment" : "A required variable", "Validator" : isStringType },
         "ParameterSetID" : { "Comment" : "A required variable", "Validator" : isDictType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
"DbsQueryableParameterSet" : {
         "Hash" : { "Comment" : "A required variable, UNIQUE", "Validator" : isStringType },
         "Name" : { "Comment" : "A required variable", "Validator" : isStringType },
         "Version" : { "Comment" : "A required variable", "Validator" : isStringType },
         "Type" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Annotation" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Content" : { "Comment" : "A required variable", "Validator" : isStringType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
"DbsProcessedDataset" : {
         "Name" : { "Comment" : "A required variable, UNIQUE", "Validator" : isStringType },
         "PhysicsGroup" : { "Comment" : "A required variable", "Validator" : isStringType },
         "Status" : { "Comment" : "A required variable", "Validator" : isStringType },
         "OpenForWriting" : { "Comment" : "Not required (Defalted to 'y' when new Dataset is created)", "Validator" : isStringType },
         "PrimaryDataset" : { "Comment" : "A required variable", "Validator" : isDictType },
         "AlgoList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "TierList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "ParentList" : { "Comment" : "User may need to provide PATH list of parents", "Validator" : isListType },
         "RunList" : { "Comment" : "User may need to provide run list", "Validator" : isListType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
"DbsFileBlock" : {
         "Name" : { "Comment" : "Required and UNIQUE", "Validator" : isStringType },
         "StorageElement" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "BlockSize" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "NumberOfFiles" : { "Comment" : "Optional, Defaulted to ZERO for new block", "Validator" : isIntType },
         "NumberOfEvents" : { "Comment" : "Optional, Defaulted to ZERO for new block", "Validator" : isIntType },
         "OpenForWriting" : { "Comment" : "Optional, Defaulted to 'y' for new block", "Validator" : isStringType },
         "Dataset" : { "Comment" : "Required ", "Validator" : isDictType },
         "FileList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
"DbsFile" : {
         "Checksum" : { "Comment" : "Required", "Validator" : isStringType },
         "LogicalFileName" : { "Comment" : "REQUIRED and UNIQUE", "Validator" : isStringType },
         "QueryableMetadata" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "NumberOfEvents" : { "Comment" : "Required", "Validator" : isIntType },
         "FileSize" : { "Comment" : "Required", "Validator" : isIntType },
         "Status" : { "Comment" : "Required", "Validator" : isStringType },
         "FileType" : { "Comment" : "Required", "Validator" : isStringType },
         "ValidationStatus" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Dataset" : { "Comment" : "User may not need to set this variable always", "Validator" : isDictType },
         "Block" : { "Comment" : "Required", "Validator" : isDictType },
         "LumiList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "TierList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "AlgoList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "ParentList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
"DbsDataTier" : {
         "Name" : { "Comment" : "REQUIRED and UNIQUE", "Validator" : isStringType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
"DbsRun" : {
         "RunNumber" : { "Comment" : "REQUIRED and UNIQUE", "Validator" : isIntType },
         "NumberOfEvents" : { "Comment" : "Required", "Validator" : isIntType },
         "NumberOfLumiSections" : { "Comment" : "Required", "Validator" : isIntType },
         "TotalLuminosity" : { "Comment" : "Required", "Validator" : isIntType },
         "StoreNumber" : { "Comment" : "Required", "Validator" : isIntType },
         "StartOfRun" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "EndOfRun" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Dataset" : { "Comment" : "Required", "Validator" : isListType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },

          },
"DbsLumiSection" : {
         "LumiSectionNumber" : { "Comment" : "REQUIRED and UNIQUE", "Validator" : isIntType },
         "StartEventNumber" : { "Comment" : "Required", "Validator" : isIntType },
         "EndEventNumber" : { "Comment" : "Required", "Validator" : isIntType },
         "LumiStartTime" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "LumiEndTime" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "RunNumber" : { "Comment" : "Required", "Validator" : isIntType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
}

# To generate the doc uncomment these lines
#for aTable in ValidationTable:
#    print "\n      ", aTable, ":"
#    for aKey in ValidationTable[aTable].keys():
#        print "           ", aKey, ":" #, ValidationTable[aTable][aKey]["Comment"]
#        print "                      ", ValidationTable[aTable][aKey]["Comment"]
       

