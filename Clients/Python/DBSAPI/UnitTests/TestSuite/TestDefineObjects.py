import unittest
from testdata import *
from testmodules import *
from testconfig import *

class TestDefineObjects(unittest.TestCase):
	def setUp(self):
		self.apidict={}
		self.apidict['reader']=DbsApi({'url':test_instance['reader']})
		self.apidict['writer']=DbsApi({'url':test_instance['writer']})
		self.apidict['admin']=DbsApi({'url':test_instance['admin']})

		self.api=self.apidict['writer']

		self.obj_DbsPrimaryDataset = DbsPrimaryDataset(Name=PrimaryDataset_Name, Type=PrimaryDSType_Type, Annotation=PrimaryDataset_Annotation)

		self.obj_DbsAlgorithm      = DbsAlgorithm(
                                       			ExecutableName        = AppExecutable_ExecutableName,
                                        		ApplicationVersion    = AppVersion_Version,
                                        		ApplicationFamily     = AppFamily_FamilyName,
                                        		ParameterSetID        = DbsQueryableParameterSet(
                                        		        Hash       =  QueryableParameterSet_Hash,
                                        		        Name       =  QueryableParameterSet_Name,
                                        		        Version    =  QueryableParameterSet_Version,
                                        		        Type       =  QueryableParameterSet_Type,
                                        		        Annotation =  QueryableParameterSet_Annotation,
                                        		        Content    =  QueryableParameterSet_Content
                                        		        )
                                       			 )


		self.obj_DbsAlgorithm__Parent  = DbsAlgorithm(
                                    		    ExecutableName        = AppExecutable_ExecutableName__Parent,
                                    		    ApplicationVersion    = AppVersion_Version__Parent,
                                    		    ApplicationFamily     = AppFamily_FamilyName__Parent,
                                    		    ParameterSetID        = DbsQueryableParameterSet(
                                    		            Hash       =  QueryableParameterSet_Hash__Parent,
                                    		            Name       =  QueryableParameterSet_Name__Parent,
                                    		            Version    =  QueryableParameterSet_Version__Parent,
                                    		            Type       =  QueryableParameterSet_Type__Parent,
                                    		            Annotation =  QueryableParameterSet_Annotation__Parent,
                                    		            Content    =  QueryableParameterSet_Content__Parent
                                    		            )
                                    		    )

		self.obj_DbsAlgorithm__Child    = DbsAlgorithm(
								ExecutableName        = AppExecutable_ExecutableName__Child,
                                   			        ApplicationVersion    = AppVersion_Version__Child,
                                   		     		ApplicationFamily     = AppFamily_FamilyName__Child,
                                        			ParameterSetID        = DbsQueryableParameterSet(
                                                			Hash       =  QueryableParameterSet_Hash__Child,
                                               				 Name       =  QueryableParameterSet_Name__Child,
                                               				 Version    =  QueryableParameterSet_Version__Child,
                                               				 Type       =  QueryableParameterSet_Type__Child,
                                              				  Annotation =  QueryableParameterSet_Annotation__Child,
                                             				   Content    =  QueryableParameterSet_Content__Child
                                             				   )
                                        				)
		self.obj_DbsRun      = DbsRun(
                				   RunNumber = Runs_RunNumber,
                   		       NumberOfEvents = Runs_NumberOfEvents,
                   		       NumberOfLumiSections = Runs_NumberOfLumiSections,
                   		       TotalLuminosity = Runs_TotalLuminosity,
                   		       StoreNumber = Runs_StoreNumber,
                   		       StartOfRun = Runs_StartOfRun,
                   		       EndOfRun = Runs_EndOfRun
                   		       )


		self.obj_DbsProcessedDataset__Parent     = DbsProcessedDataset(
                                   PrimaryDataset = self.obj_DbsPrimaryDataset,
                                   Name = ProcessedDataset_Name__Parent,
                                   AcquisitionEra = ProcessedDataset_AcquisitionEra,
                                   GlobalTag = ProcessedDataset_GlobalTag,
                                   PhysicsGroup = PhysicsGroup_PhysicsGroupName,
                                   Status = ProcDSStatus_Status__Valid,
                                   TierList = [DataTier_Name__GEN, DataTier_Name__SIM],
                                   AlgoList = [self.obj_DbsAlgorithm__Parent],
                                   RunsList = [Runs_RunNumber],
                                   XtCrossSection = ProcessedDataset_XtCrossSection
                                   )

        
		self.obj_DbsProcessedDataset     = DbsProcessedDataset(
                                   PrimaryDataset = self.obj_DbsPrimaryDataset,
                                   Name = ProcessedDataset_Name,
                                   AcquisitionEra = ProcessedDataset_AcquisitionEra,
                                   GlobalTag = ProcessedDataset_GlobalTag,
                                   PhysicsGroup = PhysicsGroup_PhysicsGroupName,
                                   Status = ProcDSStatus_Status__Valid,
                                   TierList = [DataTier_Name__RECO],
                                   AlgoList = [self.obj_DbsAlgorithm__Parent, self.obj_DbsAlgorithm__Child],
                                   ParentList = [path__Parent],
                                   RunsList = [Runs_RunNumber],
                                   XtCrossSection = ProcessedDataset_XtCrossSection
                                   )

		self.obj_DbsProcessedDataset__Child     = DbsProcessedDataset(
                                   PrimaryDataset = self.obj_DbsPrimaryDataset,
                                   Name = ProcessedDataset_Name__Child,
                                   AcquisitionEra = ProcessedDataset_AcquisitionEra,
                                   GlobalTag = ProcessedDataset_GlobalTag,
                                   PhysicsGroup = PhysicsGroup_PhysicsGroupName,
                                   Status = ProcDSStatus_Status__Valid,
                                   TierList = [DataTier_Name__USER],
                                   AlgoList = [self.obj_DbsAlgorithm__Child],
                                   ParentList = [path__Parent, path],
                                   RunsList = [Runs_RunNumber],
                                   XtCrossSection = ProcessedDataset_XtCrossSection
                                   )

		self.obj_DbsLumiSection__1   = DbsLumiSection(
                           LumiSectionNumber = LumiSection_LumiSectionNumber__1,
                           StartEventNumber = LumiSection_StartEventNumber__1,
                           EndEventNumber = LumiSection_EndEventNumber__1,
                           LumiStartTime = LumiSection_LumiStartTime__1,
                           LumiEndTime = LumiSection_LumiEndTime__1,
                           RunNumber = Runs_RunNumber,
                   )               
		self.obj_DbsLumiSection__2   = DbsLumiSection(
                           LumiSectionNumber = LumiSection_LumiSectionNumber__2,
                           StartEventNumber = LumiSection_StartEventNumber__2,
                           EndEventNumber = LumiSection_EndEventNumber__2,
                           LumiStartTime = LumiSection_LumiStartTime__2,
                           LumiEndTime = LumiSection_LumiEndTime__2,
                           RunNumber = Runs_RunNumber,
                   )               

		self.obj_DbsFileBlock__Parent1   = DbsFileBlock(  Name = Block_Name__Parent1  )
		self.obj_DbsFileBlock__Parent2   = DbsFileBlock(  Name = Block_Name__Parent2  )
		self.obj_DbsFileBlock__1         = DbsFileBlock(  Name = Block_Name__1  )
		self.obj_DbsFileBlock__2         = DbsFileBlock(  Name = Block_Name__2  )
		self.obj_DbsFileBlock__Child     = DbsFileBlock(  Name = Block_Name__Child  )


		self.obj_DbsFile__Parent1   = DbsFile(
                        Checksum = Files_Checksum__1,
                        Adler32 = Files_Adler32__1,
                        Md5 = Files_MD5__1,
                        LogicalFileName = Files_LogicalFileName__Parent1,
                        NumberOfEvents = Files_NumberOFEvents__1,
                        FileSize = Files_FileSize__1,
                        Status = FileStatus_Status__1,
                        ValidationStatus = FileValidStatus_Status__1,
                        FileType = FileTyme_Type__1,
                        Dataset = self.obj_DbsProcessedDataset__Parent,
                        AlgoList = [self.obj_DbsAlgorithm__Parent],
                        LumiList = [self.obj_DbsLumiSection__1, self.obj_DbsLumiSection__2],
                        TierList = [DataTier_Name__GEN, DataTier_Name__SIM],
                        AutoCrossSection = Files_AutoCrossSection__1
		)		

		self.obj_DbsFile__Parent2   = DbsFile(
                        Checksum = Files_Checksum__1,
                        Adler32 = Files_Adler32__1,
                        Md5 = Files_MD5__1,
                        LogicalFileName = Files_LogicalFileName__Parent2,
                        NumberOfEvents = Files_NumberOFEvents__1,
                        FileSize = Files_FileSize__1,
                        Status = FileStatus_Status__1,
                        ValidationStatus = FileValidStatus_Status__1,
                        FileType = FileTyme_Type__1,
                        Dataset = self.obj_DbsProcessedDataset__Parent,
                        AlgoList = [self.obj_DbsAlgorithm__Parent],
                        LumiList = [self.obj_DbsLumiSection__1, self.obj_DbsLumiSection__2],
                        TierList = [DataTier_Name__GEN, DataTier_Name__SIM],
                        AutoCrossSection = Files_AutoCrossSection__1
		)

		self.obj_DbsFile__Parent3   = DbsFile(
                        Checksum = Files_Checksum__1,
                        Adler32 = Files_Adler32__1,
                        Md5 = Files_MD5__1,
                        LogicalFileName = Files_LogicalFileName__Parent3,
                        NumberOfEvents = Files_NumberOFEvents__1,
                        FileSize = Files_FileSize__1,
                        Status = FileStatus_Status__1,
                        ValidationStatus = FileValidStatus_Status__1,
                        FileType = FileTyme_Type__1,
                        Dataset = self.obj_DbsProcessedDataset__Parent,
                        AlgoList = [self.obj_DbsAlgorithm__Parent],
                        LumiList = [self.obj_DbsLumiSection__1, self.obj_DbsLumiSection__2],
                        TierList = [DataTier_Name__GEN, DataTier_Name__SIM],
                        AutoCrossSection = Files_AutoCrossSection__1
		)

		self.obj_DbsFile__Parent4   = DbsFile(
                        Checksum = Files_Checksum__1,
                        Adler32 = Files_Adler32__1,
                        Md5 = Files_MD5__1,
                        LogicalFileName = Files_LogicalFileName__Parent4,
                        NumberOfEvents = Files_NumberOFEvents__1,
                        FileSize = Files_FileSize__1,
                        Status = FileStatus_Status__1,
                        ValidationStatus = FileValidStatus_Status__1,
                        FileType = FileTyme_Type__1,
                        Dataset = self.obj_DbsProcessedDataset__Parent,
                        AlgoList = [self.obj_DbsAlgorithm__Parent],
                        LumiList = [self.obj_DbsLumiSection__1, self.obj_DbsLumiSection__2],
                        TierList = [DataTier_Name__GEN, DataTier_Name__SIM],
                        AutoCrossSection = Files_AutoCrossSection__1
		)

		self.obj_DbsFile__1   = DbsFile(
                        Checksum = Files_Checksum__2,
                        Adler32 = Files_Adler32__2,
                        Md5 = Files_MD5__2,
                        LogicalFileName = Files_LogicalFileName__1,
                        NumberOfEvents = Files_NumberOFEvents__2,
                        FileSize = Files_FileSize__2,
                        Status = FileStatus_Status__2,
                        ValidationStatus = FileValidStatus_Status__2,
                        FileType = FileTyme_Type__2,
                        Dataset = self.obj_DbsProcessedDataset,
                        AlgoList = [self.obj_DbsAlgorithm__Parent, self.obj_DbsAlgorithm__Child],
                        LumiList = [self.obj_DbsLumiSection__1, self.obj_DbsLumiSection__2],
                        TierList = [DataTier_Name__RECO],
                        ParentList = [self.obj_DbsFile__Parent1, self.obj_DbsFile__Parent2],
                        AutoCrossSection = Files_AutoCrossSection__2
		)

		self.obj_DbsFile__2   = DbsFile(
                        Checksum = Files_Checksum__2,
                        Adler32 = Files_Adler32__2,
                        Md5 = Files_MD5__2,
                        LogicalFileName = Files_LogicalFileName__2,
                        NumberOfEvents = Files_NumberOFEvents__2,
                        FileSize = Files_FileSize__2,
                        Status = FileStatus_Status__2,
                        ValidationStatus = FileValidStatus_Status__2,
                        FileType = FileTyme_Type__2,
                        Dataset = self.obj_DbsProcessedDataset,
                        AlgoList = [self.obj_DbsAlgorithm__Parent, self.obj_DbsAlgorithm__Child],
                        LumiList = [self.obj_DbsLumiSection__1, self.obj_DbsLumiSection__2],
                        TierList = [DataTier_Name__RECO],
                        ParentList = [self.obj_DbsFile__Parent2, self.obj_DbsFile__Parent3],
                        AutoCrossSection = Files_AutoCrossSection__2
		)

		self.obj_DbsFile__3   = DbsFile(
                        Checksum = Files_Checksum__2,
                        Adler32 = Files_Adler32__2,
                        Md5 = Files_MD5__2,
                        LogicalFileName = Files_LogicalFileName__3,
                        NumberOfEvents = Files_NumberOFEvents__2,
                        FileSize = Files_FileSize__2,
                        Status = FileStatus_Status__2,
                        ValidationStatus = FileValidStatus_Status__2,
                        FileType = FileTyme_Type__2,
                        Dataset = self.obj_DbsProcessedDataset,
                        AlgoList = [self.obj_DbsAlgorithm__Child],
                        LumiList = [self.obj_DbsLumiSection__2],
                        TierList = [DataTier_Name__RECO],
                        ParentList = [self.obj_DbsFile__Parent4],
                        AutoCrossSection = Files_AutoCrossSection__2
		)

		self.obj_DbsFile__Child1   = DbsFile(
                        Checksum = Files_Checksum__2,
                        Adler32 = Files_Adler32__2,
                        Md5 = Files_MD5__2,
                        LogicalFileName = Files_LogicalFileName__Child1,
                        NumberOfEvents = Files_NumberOFEvents__2,
                        FileSize = Files_FileSize__2,
                        Status = FileStatus_Status__2,
                        ValidationStatus = FileValidStatus_Status__2,
                        FileType = FileTyme_Type__2,
                        Dataset = self.obj_DbsProcessedDataset,
                        AlgoList = [self.obj_DbsAlgorithm__Child],
                        LumiList = [self.obj_DbsLumiSection__1],
                        TierList = [DataTier_Name__USER],
                        ParentList = [self.obj_DbsFile__1, self.obj_DbsFile__2],
                        AutoCrossSection = Files_AutoCrossSection__2
		)
		self.obj_DbsFile__Child2   = DbsFile(
                        Checksum = Files_Checksum__2,
                        Adler32 = Files_Adler32__2,
                        Md5 = Files_MD5__2,
                        LogicalFileName = Files_LogicalFileName__Child2,
                        NumberOfEvents = Files_NumberOFEvents__2,
                        FileSize = Files_FileSize__2,
                        Status = FileStatus_Status__2,
                        ValidationStatus = FileValidStatus_Status__2,
                        FileType = FileTyme_Type__2,
                        Dataset = self.obj_DbsProcessedDataset,
                        AlgoList = [self.obj_DbsAlgorithm__Child],
                        LumiList = [self.obj_DbsLumiSection__1],
                        TierList = [DataTier_Name__USER],
                        ParentList = [self.obj_DbsFile__3, self.obj_DbsFile__Parent4],
                        AutoCrossSection = Files_AutoCrossSection__2
		)
		
