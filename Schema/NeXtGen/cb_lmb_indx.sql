CREATE INDEX ix_lmb_Person on Person(LastModifiedBy);
CREATE INDEX ix_cb_Person on Person(CreatedBy);

CREATE INDEX ix_lmb_Role on Role(LastModifiedBy);
CREATE INDEX ix_cb_Role on Role(CreatedBy);

CREATE INDEX ix_lmb_AssignedRole on AssignedRole(LastModifiedBy);
CREATE INDEX ix_cb_AssignedRole on AssignedRole(CreatedBy);

CREATE INDEX ix_lmb_PhysicsGroup on PhysicsGroup(LastModifiedBy);
CREATE INDEX ix_cb_PhysicsGroup on PhysicsGroup(CreatedBy);

CREATE INDEX ix_lmb_SchemaVersion on SchemaVersion(LastModifiedBy);
CREATE INDEX ix_cb_SchemaVersion on SchemaVersion(CreatedBy);

CREATE INDEX ix_lmb_Runs on Runs(LastModifiedBy);
CREATE INDEX ix_cb_Runs on Runs(CreatedBy);

CREATE INDEX ix_lmb_DataTier on DataTier(LastModifiedBy);
CREATE INDEX ix_cb_DataTier on DataTier(CreatedBy);

CREATE INDEX ix_lmb_LumiSection on LumiSection(LastModifiedBy);
CREATE INDEX ix_cb_LumiSection on LumiSection(CreatedBy);

CREATE INDEX ix_lmb_TimeLog on TimeLog(LastModifiedBy);
CREATE INDEX ix_cb_TimeLog on TimeLog(CreatedBy);

CREATE INDEX ix_lmb_ReasonCode on ReasonCode(LastModifiedBy);
CREATE INDEX ix_cb_ReasonCode on ReasonCode(CreatedBy);

CREATE INDEX ix_lmb_RecycleBin on RecycleBin(LastModifiedBy);
CREATE INDEX ix_cb_RecycleBin on RecycleBin(CreatedBy);

CREATE INDEX ix_lmb_AppFamily on AppFamily(LastModifiedBy);
CREATE INDEX ix_cb_AppFamily on AppFamily(CreatedBy);

CREATE INDEX ix_lmb_AppVersion on AppVersion(LastModifiedBy);
CREATE INDEX ix_cb_AppVersion on AppVersion(CreatedBy);

CREATE INDEX ix_lmb_AppExecutable on AppExecutable(LastModifiedBy);
CREATE INDEX ix_cb_AppExecutable on AppExecutable(CreatedBy);

CREATE INDEX ix_lmb_QueryableParameterSet on QueryableParameterSet(LastModifiedBy);
CREATE INDEX ix_cb_QueryableParameterSet on QueryableParameterSet(CreatedBy);

CREATE INDEX ix_lmb_ParameterBinding on ParameterBinding(LastModifiedBy);
CREATE INDEX ix_cb_ParameterBinding on ParameterBinding(CreatedBy);

CREATE INDEX ix_lmb_TriggerPathDescription on TriggerPathDescription(LastModifiedBy);
CREATE INDEX ix_cb_TriggerPathDescription on TriggerPathDescription(CreatedBy);

CREATE INDEX ix_lmb_MCDescription on MCDescription(LastModifiedBy);
CREATE INDEX ix_cb_MCDescription on MCDescription(CreatedBy);

CREATE INDEX ix_lmb_OtherDescription on OtherDescription(LastModifiedBy);
CREATE INDEX ix_cb_OtherDescription on OtherDescription(CreatedBy);

CREATE INDEX ix_lmb_FileStatus on FileStatus(LastModifiedBy);
CREATE INDEX ix_cb_FileStatus on FileStatus(CreatedBy);

CREATE INDEX ix_lmb_FileType on FileType(LastModifiedBy);
CREATE INDEX ix_cb_FileType on FileType(CreatedBy);

CREATE INDEX ix_lmb_FileValidStatus on FileValidStatus(LastModifiedBy);
CREATE INDEX ix_cb_FileValidStatus on FileValidStatus(CreatedBy);

CREATE INDEX ix_lmb_AnalysisDSType on AnalysisDSType(LastModifiedBy);
CREATE INDEX ix_cb_AnalysisDSType on AnalysisDSType(CreatedBy);

CREATE INDEX ix_lmb_AnalysisDSStatus on AnalysisDSStatus(LastModifiedBy);
CREATE INDEX ix_cb_AnalysisDSStatus on AnalysisDSStatus(CreatedBy);

CREATE INDEX ix_lmb_AnalysisDSDef on AnalysisDSDef(LastModifiedBy);
CREATE INDEX ix_cb_AnalysisDSDef on AnalysisDSDef(CreatedBy);

CREATE INDEX ix_lmb_CompositeADS on CompositeADS(LastModifiedBy);
CREATE INDEX ix_cb_CompositeADS on CompositeADS(CreatedBy);

CREATE INDEX ix_lmb_StorageElement on StorageElement(LastModifiedBy);
CREATE INDEX ix_cb_StorageElement on StorageElement(CreatedBy);

CREATE INDEX ix_lmb_ProcDSStatus on ProcDSStatus(LastModifiedBy);
CREATE INDEX ix_cb_ProcDSStatus on ProcDSStatus(CreatedBy);

CREATE INDEX ix_lmb_PrimaryDSType on PrimaryDSType(LastModifiedBy);
CREATE INDEX ix_cb_PrimaryDSType on PrimaryDSType(CreatedBy);

CREATE INDEX ix_lmb_QualityValues on QualityValues(LastModifiedBy);
CREATE INDEX ix_cb_QualityValues on QualityValues(CreatedBy);

CREATE INDEX ix_lmb_SubSystem on SubSystem(LastModifiedBy);
CREATE INDEX ix_cb_SubSystem on SubSystem(CreatedBy);

CREATE INDEX ix_lmb_RunLumiQuality on RunLumiQuality(LastModifiedBy);
CREATE INDEX ix_cb_RunLumiQuality on RunLumiQuality(CreatedBy);

CREATE INDEX ix_lmb_RunLumiDQInt on RunLumiDQInt(LastModifiedBy);
CREATE INDEX ix_cb_RunLumiDQInt on RunLumiDQInt(CreatedBy);

CREATE INDEX ix_lmb_QualityHistory on QualityHistory(LastModifiedBy);
CREATE INDEX ix_cb_QualityHistory on QualityHistory(CreatedBy);

CREATE INDEX ix_lmb_IntQualityHistory on IntQualityHistory(LastModifiedBy);
CREATE INDEX ix_cb_IntQualityHistory on IntQualityHistory(CreatedBy);

CREATE INDEX ix_lmb_QualityVersion on QualityVersion(LastModifiedBy);
CREATE INDEX ix_cb_QualityVersion on QualityVersion(CreatedBy);

CREATE INDEX ix_lmb_Branch on Branch(LastModifiedBy);
CREATE INDEX ix_cb_Branch on Branch(CreatedBy);

CREATE INDEX ix_lmb_BranchHash on BranchHash(LastModifiedBy);
CREATE INDEX ix_cb_BranchHash on BranchHash(CreatedBy);

CREATE INDEX ix_lmb_BranchHashMap on BranchHashMap(LastModifiedBy);
CREATE INDEX ix_cb_BranchHashMap on BranchHashMap(CreatedBy);

CREATE INDEX ix_lmb_AlgorithmConfig on AlgorithmConfig(LastModifiedBy);
CREATE INDEX ix_cb_AlgorithmConfig on AlgorithmConfig(CreatedBy);

CREATE INDEX ix_lmb_PrimaryDatasetDescription on PrimaryDatasetDescription(LastModifiedBy);
CREATE INDEX ix_cb_PrimaryDatasetDescription on PrimaryDatasetDescription(CreatedBy);

CREATE INDEX ix_lmb_PrimaryDataset on PrimaryDataset(LastModifiedBy);
CREATE INDEX ix_cb_PrimaryDataset on PrimaryDataset(CreatedBy);

CREATE INDEX ix_lmb_ProcessedDataset on ProcessedDataset(LastModifiedBy);
CREATE INDEX ix_cb_ProcessedDataset on ProcessedDataset(CreatedBy);

CREATE INDEX ix_lmb_ProcDSRuns on ProcDSRuns(LastModifiedBy);
CREATE INDEX ix_cb_ProcDSRuns on ProcDSRuns(CreatedBy);

CREATE INDEX ix_lmb_ProcDSParent on ProcDSParent(LastModifiedBy);
CREATE INDEX ix_cb_ProcDSParent on ProcDSParent(CreatedBy);

CREATE INDEX ix_lmb_ProcAlgo on ProcAlgo(LastModifiedBy);
CREATE INDEX ix_cb_ProcAlgo on ProcAlgo(CreatedBy);

CREATE INDEX ix_lmb_AnalysisDataset on AnalysisDataset(LastModifiedBy);
CREATE INDEX ix_cb_AnalysisDataset on AnalysisDataset(CreatedBy);

CREATE INDEX ix_lmb_CompADSMap on CompADSMap(LastModifiedBy);
CREATE INDEX ix_cb_CompADSMap on CompADSMap(CreatedBy);

CREATE INDEX ix_lmb_Block on Block(LastModifiedBy);
CREATE INDEX ix_cb_Block on Block(CreatedBy);

CREATE INDEX ix_lmb_BlockParent on BlockParent(LastModifiedBy);
CREATE INDEX ix_cb_BlockParent on BlockParent(CreatedBy);

CREATE INDEX ix_lmb_FileProcQuality on FileProcQuality(LastModifiedBy);
CREATE INDEX ix_cb_FileProcQuality on FileProcQuality(CreatedBy);

CREATE INDEX ix_lmb_ProcessingStatus on ProcessingStatus(LastModifiedBy);
CREATE INDEX ix_cb_ProcessingStatus on ProcessingStatus(CreatedBy);

CREATE INDEX ix_lmb_Files on Files(LastModifiedBy);
CREATE INDEX ix_cb_Files on Files(CreatedBy);

CREATE INDEX ix_lmb_FileParentage on FileParentage(LastModifiedBy);
CREATE INDEX ix_cb_FileParentage on FileParentage(CreatedBy);

CREATE INDEX ix_lmb_FileRunLumi on FileRunLumi(LastModifiedBy);
CREATE INDEX ix_cb_FileRunLumi on FileRunLumi(CreatedBy);

CREATE INDEX ix_lmb_FileAlgo on FileAlgo(LastModifiedBy);
CREATE INDEX ix_cb_FileAlgo on FileAlgo(CreatedBy);

CREATE INDEX ix_lmb_FileTriggerTag on FileTriggerTag(LastModifiedBy);
CREATE INDEX ix_cb_FileTriggerTag on FileTriggerTag(CreatedBy);

CREATE INDEX ix_lmb_FileAssoc on FileAssoc(LastModifiedBy);
CREATE INDEX ix_cb_FileAssoc on FileAssoc(CreatedBy);

CREATE INDEX ix_lmb_ProcADSParent on ProcADSParent(LastModifiedBy);
CREATE INDEX ix_cb_ProcADSParent on ProcADSParent(CreatedBy);

CREATE INDEX ix_lmb_AnalysisDSFileLumi on AnalysisDSFileLumi(LastModifiedBy);
CREATE INDEX ix_cb_AnalysisDSFileLumi on AnalysisDSFileLumi(CreatedBy);

CREATE INDEX ix_lmb_SEBlock on SEBlock(LastModifiedBy);
CREATE INDEX ix_cb_SEBlock on SEBlock(CreatedBy);

