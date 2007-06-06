#
# Revision: 0.0 $"
# Id: dbsValidationTable.py,v 0.0 2006/1/1 18:26:04 afaq Exp $"
#

from dbsValidateTools import *
ValidationTable = {

"DbsPrimaryDataset" : {
         "Annotation" : { "Comment" : "A required variable", "Validator" : isStringType },
         "Name" : { "Comment" : "A required variable, UNIQUE", "Validator" : isStringType },
         "StartDate" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "EndDate" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Type" : { "Comment" : "A required variable", "Validator" : isStringType },
         "Description" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
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
         "Version" : { "Comment" : "A required variable", "Validator" : isStringType },
         "PhysicsGroup" : { "Comment" : "A required variable", "Validator" : isStringType },
         "Definition" : { "Comment" : "Only used when a value is returned by listAnalysisDataset, details of AnalysisDS Definition", "Validator" : isDictType },
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
         "PhysicsGroup" : { "Comment" : "An optional variable", "Validator" : isStringType },
         "PhysicsGroupConverner" : { "Comment" : "A optional variable", "Validator" : isStringType },
         "Status" : { "Comment" : "A required variable", "Validator" : isStringType },
         "OpenForWriting" : { "Comment" : "Not required (Defalted to 'y' when new Dataset is created)", "Validator" : isStringType },
         "PrimaryDataset" : { "Comment" : "A required variable", "Validator" : isDictType },
         "AlgoList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "TierList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "PathList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "ParentList" : { "Comment" : "User may need to provide PATH list of parents", "Validator" : isListType },
         "RunsList" : { "Comment" : "User may need to provide run list", "Validator" : isListType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
"DbsAnalysisDatasetDefinition" : {
         "Name" : { "Comment" : "A required variable, UNIQUE", "Validator" : isStringType },
         #"PhysicsGroup" : { "Comment" : "A required variable", "Validator" : isStringType },
         #"Status" : { "Comment" : "A required variable", "Validator" : isStringType },
         "ProcessedDatasetPath" : { "Comment" : "Not a required var, user can provide if desired", "Validator" : isStringType },
         "FileList" : { "Comment" : "List of LFNs, User may need to provide PATH list of parents", "Validator" : isListType },
         "AlgoList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         #"TierList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "LumiList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "RunsList" : { "Comment" : "User may need to provide PATH list of parents", "Validator" : isListType },
         "AnalysisDSList" : { "Comment" : "User may need to provide PATH list of parents", "Validator" : isListType },
         "LumiRangeList" : { "Comment" : "Tuples if (Start, End) ranges User may need to provide run list", "Validator" : isListType },
         "RunRangeList" : { "Comment" : "User may need to provide run list", "Validator" : isListType },
         "UserCut" : { "Comment" : "The selection criteria in user's format, Not a required var, user can provide if desired", "Validator" : isStringType },
         "Description" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         },
"DbsFileBlock" : {
         "Name" : { "Comment" : "Required and UNIQUE", "Validator" : isStringType },
         "StorageElementList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "BlockSize" : { "Comment" : "User may not need to set this variable always", "Validator" : isLongType },
         "NumberOfFiles" : { "Comment" : "Optional, Defaulted to ZERO for new block", "Validator" : isLongType },
         "NumberOfEvents" : { "Comment" : "Optional, Defaulted to ZERO for new block", "Validator" : isLongType },
         "OpenForWriting" : { "Comment" : "Optional, Defaulted to 'y' for new block", "Validator" : isStringType },
         "Path" : { "Comment" : "Dataset Path, User may not need to set this variable always", "Validator" : isStringType },
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
         "NumberOfEvents" : { "Comment" : "Required", "Validator" : isLongType },
         "FileSize" : { "Comment" : "Required", "Validator" : isLongType },
         "Status" : { "Comment" : "Required", "Validator" : isStringType },
         "FileType" : { "Comment" : "Required", "Validator" : isStringType },
         "ValidationStatus" : { "Comment" : "User may not need to set this variable always", "Validator" : isStringType },
         "Dataset" : { "Comment" : "User may not need to set this variable always", "Validator" : isDictType },
         "Block" : { "Comment" : "Required", "Validator" : isDictType },
         "LumiList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "TierList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "AlgoList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "ChildList" : { "Comment" : "List of THIS file's children files", "Validator" : isListType },
         "RunsList" : { "Comment" : "List of THIS file's Runs", "Validator" : isListType },
         "ParentList" : { "Comment" : "User may not need to set this variable always", "Validator" : isListType },
         "BranchList" : { "Comment" : "List of ROOT Branch names", "Validator" : isListType },
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
"DbsStorageElement" : {
         "Name" : { "Comment" : "REQUIRED and UNIQUE", "Validator" : isStringType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
"DbsFileTriggerTag" : {
	 "TriggerTag" : {"Comment" : "Trigger tag", "Validator" : isStringType},
	 "NumberOfEvents" : {"Comment" : "Number of Events in this trigger tag", "Validator" : isLongType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
	},
"DbsFileBranch" : {
         "Name" : { "Comment" : "REQUIRED and UNIQUE", "Validator" : isStringType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
"DbsRun" : {
         "RunNumber" : { "Comment" : "REQUIRED and UNIQUE", "Validator" : isLongType },
         "NumberOfEvents" : { "Comment" : "Required", "Validator" : isLongType },
         "NumberOfLumiSections" : { "Comment" : "Required", "Validator" : isLongType },
         "TotalLuminosity" : { "Comment" : "Required", "Validator" : isLongType },
         "StoreNumber" : { "Comment" : "Required", "Validator" : isLongType },
         "StartOfRun" : { "Comment" : "User may not need to set this variable always", "Validator" : isLongType},
         "EndOfRun" : { "Comment" : "User may not need to set this variable always", "Validator" : isLongType },
         "Dataset" : { "Comment" : "Required", "Validator" : isListType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },

          },
"DbsLumiSection" : {
         "LumiSectionNumber" : { "Comment" : "REQUIRED and UNIQUE", "Validator" : isLongType },
         "StartEventNumber" : { "Comment" : "Required", "Validator" : isLongType },
         "EndEventNumber" : { "Comment" : "Required", "Validator" : isLongType },
         "LumiStartTime" : { "Comment" : "User may not need to set this variable always", "Validator" : isLongType},
         "LumiEndTime" : { "Comment" : "User may not need to set this variable always", "Validator" : isLongType },
         "RunNumber" : { "Comment" : "Required", "Validator" : isLongType },
         "CreationDate" : { "Comment" : "TimeStamp, object created in database (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN, who created this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "LastModificationDate" : { "Comment" : "Last Modification, (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
         "CreatedBy" : { "Comment" : "User DN of who last modified this object (AUTO set by DBS, you can over ride, why ?)", "Validator" : isStringType },
          },
}

# To generate the doc uncomment these lines
"""
for aTable in ValidationTable:
    print "\n      ", aTable, ":"
    for aKey in ValidationTable[aTable].keys():
        print "           ", aKey, ":" #, ValidationTable[aTable][aKey]["Comment"]
        print "                      ", ValidationTable[aTable][aKey]["Comment"]
 
"""      

