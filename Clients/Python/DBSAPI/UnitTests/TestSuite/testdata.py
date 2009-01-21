PrimaryDataset_Name       = 'DBS_UNIT_TEST'
PrimaryDataset_Annotation = 'Test_PrimaryDataset_Annotation'
PrimaryDSType_Type        = 'test'


AppExecutable_ExecutableName      = 'Test_AppExecutable_ExecutableName'
AppVersion_Version                = 'Test_AppVersion_Version'
AppFamily_FamilyName              = 'Test_AppFamily_Familyame'
QueryableParameterSet_Hash        = 'Test_QueryableParameterSet_Hash'
QueryableParameterSet_Name        = 'Test_QueryableParameterSet_Name'
QueryableParameterSet_Version     = 'Test_QueryableParameterSet_Version'
QueryableParameterSet_Type        = 'Test_QueryableParameterSet_Type'
QueryableParameterSet_Annotation  = 'Test_QueryableParameterSet_Annotation'
QueryableParameterSet_Content     = 'Test_QueryableParameterSet_Content'


AppExecutable_ExecutableName__Parent      = 'Test_AppExecutable_ExecutableName__Parent'
AppVersion_Version__Parent                = 'Test_AppVersion_Version__Parent'
AppFamily_FamilyName__Parent              = 'Test_AppFamily_Familyame__Parent'
QueryableParameterSet_Hash__Parent        = 'Test_QueryableParameterSet_Hash__Parent'
QueryableParameterSet_Name__Parent        = 'Test_QueryableParameterSet_Name__Parent'
QueryableParameterSet_Version__Parent     = 'Test_QueryableParameterSet_Version__Parent'
QueryableParameterSet_Type__Parent        = 'Test_QueryableParameterSet_Type__Parent'
QueryableParameterSet_Annotation__Parent  = 'Test_QueryableParameterSet_Annotation__Parent'
QueryableParameterSet_Content__Parent     = 'Test_QueryableParameterSet_Content__Parent'


AppExecutable_ExecutableName__Child      = 'Test_AppExecutable_ExecutableName__Child'
AppVersion_Version__Child                = 'Test_AppVersion_Version__Child'
AppFamily_FamilyName__Child              = 'Test_AppFamily_Familyame__Child'
QueryableParameterSet_Hash__Child        = 'Test_QueryableParameterSet_Hash__Child'
QueryableParameterSet_Name__Child        = 'Test_QueryableParameterSet_Name__Child'
QueryableParameterSet_Version__Child     = 'Test_QueryableParameterSet_Version__Child'
QueryableParameterSet_Type__Child        = 'Test_QueryableParameterSet_Type__Child'
QueryableParameterSet_Annotation__Child  = 'Test_QueryableParameterSet_Annotation__Child'
QueryableParameterSet_Content__Child     = 'Test_QueryableParameterSet_Content__Child'


Runs_RunNumber                  = 7777
Runs_NumberOfEvents             = 20000
Runs_NumberOfLumiSections       = 2 
Runs_StoreNumber                = 777
Runs_StartOfRun                 = 19811019
Runs_EndOfRun                   = 20081208
Runs_TotalLuminosity            = 777


ProcDSStatus_Status__Valid             = 'VALID'
ProcDSStatus_Status__Invalid             = 'INVALID'

ProcessedDataset_Name                   = 'Test_ProcessedDataset_Name'
ProcessedDataset_Name__Parent           = 'Test_ProcessedDataset_Name__Parent'
ProcessedDataset_Name__Child            = 'Test_ProcessedDataset_Name__Child'

ProcessedDataset_XtCrossSection  = 1000.
ProcessedDataset_AcquisitionEra  = 'Test_ProcessedDataset_AcquisitionEra'
ProcessedDataset_GlobalTag       = 'Test_ProcessedDataset_GlobalTag'

PhysicsGroup_PhysicsGroupName    = 'EWK'

DataTier_Name__USER                   = 'USER'
DataTier_Name__GEN                    = 'GEN'
DataTier_Name__SIM                    = 'SIM'
DataTier_Name__RECO                   = 'RECO'

LumiSection_LumiSectionNumber__1    = 1
LumiSection_StartEventNumber__1     = 100000
LumiSection_EndEventNumber__1       = 110000
LumiSection_LumiStartTime__1        = 19811019
LumiSection_LumiEndTime__1          = 20081208

LumiSection_LumiSectionNumber__2    = 2
LumiSection_StartEventNumber__2     = 110001
LumiSection_EndEventNumber__2       = 120000
LumiSection_LumiStartTime__2        = 19811019
LumiSection_LumiEndTime__2          = 20081208


path          = '/' + PrimaryDataset_Name + '/' + ProcessedDataset_Name + '/' + DataTier_Name__RECO
path__Parent  = '/' + PrimaryDataset_Name + '/' + ProcessedDataset_Name__Parent + '/' + DataTier_Name__GEN+'-'+DataTier_Name__SIM
path__Child   = '/' + PrimaryDataset_Name + '/' + ProcessedDataset_Name__Child + '/' + DataTier_Name__USER


StorageElement_SEName__1            = "Test_StorageElement_SEName__1"
StorageElement_SEName__2            = "Test_StorageElement_SEName__2"
StorageElement_SEName__3            = "Test_StorageElement_SEName__3"

Block_Name__Parent1                     = path__Parent + '#' + '11111'
Block_Name__Parent2                     = path__Parent + '#' + '22222'
Block_Name__1                           = path + '#' + '11111' 
Block_Name__2                           = path + '#' + '22222'
Block_Name__Child                       = path__Child + '#' + '11111'

Block_BlockSize                  = 777
Block_NumberOfFiles              = 7
Block_NumberOfEvents             = 77777



Files_LogicalFileName__Parent1           = "Test_Files_LogicalFileName__Parent1"
Files_LogicalFileName__Parent2           = "Test_Files_LogicalFileName__Parent2"
Files_LogicalFileName__Parent3           = "Test_Files_LogicalFileName__Parent3"
Files_LogicalFileName__Parent4           = "Test_Files_LogicalFileName__Parent4"

Files_LogicalFileName__1           = "Test_Files_LogicalFileName__1"
Files_LogicalFileName__2           = "Test_Files_LogicalFileName__2"
Files_LogicalFileName__3           = "Test_Files_LogicalFileName__3"

Files_LogicalFileName__Child1           = "Test_Files_LogicalFileName__Child1"
Files_LogicalFileName__Child2           = "Test_Files_LogicalFileName__Child2"

Files_Checksum__1                  = '777'
Files_Adler32__1                   = "Test_Files_Adler32"
Files_MD5__1                       = "Test_Files_MD5"
Files_NumberOFEvents__1            = 7777
Files_FileSize__1                  = 777
Files_AutoCrossSection__1          = 1001.
FileStatus_Status__1               = "VALID"
FileValidStatus_Status__1          = "VALID"
FileTyme_Type__1                   = "STREAMER"

Files_Checksum__2                  = '777'
Files_Adler32__2                   = "Test_Files_Adler32"
Files_MD5__2                       = "Test_Files_MD5"
Files_NumberOFEvents__2            = 7777
Files_FileSize__2                  = 777
Files_AutoCrossSection__2          = 1001.
FileStatus_Status__2               = "VALID"
FileValidStatus_Status__2          = "VALID"
FileTyme_Type__2                   = "STREAMER"
