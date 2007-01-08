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
         "Annotation" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Name" : { "Comment" : "Probably a required variable", "Validator" : isStringType },
         "StartDate" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "EndDate" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Type" : { "Comment" : "User may not need to set this variable always", "Validator" : isDictType },
         "Description" : { "Comment" : "User may not need to set this variable always", "Validator" : isDictType },
         "CreationDate" : { "Comment" : "Time stamp when this object was created in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who created this object in database", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Time stamp when this object was last modified in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who last modified this object in database", "Validator" : isStringType },
          },
"DbsAnalysisDataset" : {
         "Annotation" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Name" : { "Comment" : "Probably a required variable", "Validator" : isStringType },
         "Type" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Status" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "PhysicsGroup" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "CreationDate" : { "Comment" : "Time stamp when this object was created in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who created this object in database", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Time stamp when this object was last modified in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who last modified this object in database", "Validator" : isStringType },
          },
"DbsAlgorithm" : {
         "ExecutableName" : { "Comment" : "Probably a required variable", "Validator" : isStringType },
         "ApplicationVersion" : { "Comment" : "Probably a required variable", "Validator" : isStringType },
         "ApplicationFamily" : { "Comment" : "Probably a required variable", "Validator" : isStringType },
         "ParameterSetID" : { "Comment" : "Probably a required variable", "Validator" : isDictType },
         "CreationDate" : { "Comment" : "Time stamp when this object was created in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who created this object in database", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Time stamp when this object was last modified in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who last modified this object in database", "Validator" : isStringType },
          },
"DbsQueryableParameterSet" : {
         "Hash" : { "Comment" : "Probably a required variable", "Validator" : isStringType },
         "Name" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Version" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Type" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Annotation" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Content" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "CreationDate" : { "Comment" : "Time stamp when this object was created in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who created this object in database", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Time stamp when this object was last modified in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who last modified this object in database", "Validator" : isStringType },
          },
"DbsProcessedDataset" : {
         "Name" : { "Comment" : "Probably a required variable", "Validator" : isStringType },
         "PhysicsGroup" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Status" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "OpenForWriting" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "PrimaryDataset" : { "Comment" : "Probably a required variable", "Validator" : isDictType },
         "AlgoList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "TierList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "ParentList" : { "Comment" : "User may need to provide PATH list of parents", "Validator" : isListType },
         "RunList" : { "Comment" : "User may need to provide run list", "Validator" : isListType },
         "CreationDate" : { "Comment" : "Time stamp when this object was created in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who created this object in database", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Time stamp when this object was last modified in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who last modified this object in database", "Validator" : isStringType },
         "datasetPathName" : { "Comment" : "For backward compatability with CRAB", "Validator" : isStringType },
          },
"DbsFileBlock" : {
         "Name" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "StorageElement" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "Status" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "BlockSize" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "NumberOfFiles" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "NumberOfEvents" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "OpenForWriting" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Dataset" : { "Comment" : "User may not need to set this variable always", "Validator" : isDictType },
         "FileList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "CreationDate" : { "Comment" : "Time stamp when this object was created in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who created this object in database", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Time stamp when this object was last modified in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who last modified this object in database", "Validator" : isStringType },
          },
"DbsFile" : {
         "Checksum" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "LogicalFileName" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "QueryableMetadata" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "NumberOfEvents" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "FileSize" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "Status" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "FileType" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "ValidationStatus" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Dataset" : { "Comment" : "User may not need to set this variable always", "Validator" : isDictType },
         "Block" : { "Comment" : "User may not need to set this variable always", "Validator" : isDictType },
         "LumiList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "TierList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "AlgoList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "ParentList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "CreationDate" : { "Comment" : "Time stamp when this object was created in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who created this object in database", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Time stamp when this object was last modified in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who last modified this object in database", "Validator" : isStringType },
          },
"DbsDataTier" : {
         "Name" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "CreationDate" : { "Comment" : "Time stamp when this object was created in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who created this object in database", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Time stamp when this object was last modified in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who last modified this object in database", "Validator" : isStringType },
          },
"DbsRun" : {
         "RunNumber" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "NumberOfEvents" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "NumberOfLumiSections" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "TotalLuminosity" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "StoreNumber" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "StartOfRun" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "EndOfRun" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Dataset" : { "Comment" : "Probably a required variable", "Validator" : isListType },
         "CreationDate" : { "Comment" : "Time stamp when this object was created in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who created this object in database", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Time stamp when this object was last modified in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who last modified this object in database", "Validator" : isStringType },
          },
"DbsLumiSection" : {
         "LumiSectionNumber" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "StartEventNumber" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "EndEventNumber" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "LumiStartTime" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "LumiEndTime" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "RunNumber" : { "Comment" : "User may not need to set this variable always", "Validator" : isIntType },
         "CreationDate" : { "Comment" : "Time stamp when this object was created in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who created this object in database", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Time stamp when this object was last modified in database", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "DN of user who last modified this object in database", "Validator" : isStringType },
          },
}


