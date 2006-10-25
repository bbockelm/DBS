REM ======================================================================
REM ===   Sql Script for Database : DBS_NEW_ERA
REM ===
REM === Build : 458
REM ======================================================================

CREATE TABLE Person
  (
    ID                    int,
    Name                  varchar(100)                     not null,
    DistinguishedName     varchar(100)                     not null,
    ContactInfo           varchar(100),
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID),
    unique(Name,DistinguishedName)
  );

REM ======================================================================

CREATE TABLE Role
  (
    ID                    int,
    RoleName              varchar(100)                     unique not null,
    RoleDescription       varchar(100)                     not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AssignedRole
  (
    ID                    int,
    PersonID              int                              not null,
    RoleID                int                              not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID),
    unique(PersonID,RoleID)
  );

REM ======================================================================

CREATE TABLE PhysicsGroup
  (
    ID                    int,
    PhysicsGroupName      varchar(100)                     unique not null,
    PhysicsGroupConvener  int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE SchemaVersion
  (
    ID                    int,
    SchemaVersion         varchar(100)                     unique not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE Runs
  (
    ID                    int,
    RunNumber             int                              unique not null,
    NumberOfEvents        int,
    NumberOfLumiSections  int,
    TotalLuminosity       int,
    StoreNumber           int,
    StartOfRun            varchar(100),
    EndOfRun              varchar(100),
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE DataTier
  (
    ID                    int,
    Name                  varchar(100)                     unique not null,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE LumiSection
  (
    ID                    int,
    LumiSectionNumber     int                              not null,
    RunNumber             int                              not null,
    StartEventNumber      int,
    EndEventNumber        int,
    LumiStartTime         varchar(100),
    LumiEndTime           varchar(100),
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID),
    unique(LumiSectionNumber,RunNumber)
  );

REM ======================================================================

CREATE TABLE Status
  (
    ID                    int,
    Status                varchar(100)                     unique not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE Type
  (
    ID                    int,
    Type                  varchar(100)                     unique not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE Description
  (
    ID                    int,
    Status                varchar(100)                     unique not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE TimeLog
  (
    ID                    int,
    LogEntry              varchar(1000)                    unique not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AppFamily
  (
    ID                    int,
    FamilyName            varchar(100)                     unique not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AppVersion
  (
    ID                    int,
    Version               varchar(100)                     unique not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AppExecutable
  (
    ID                    int,
    ExecutableName        varchar(100)                     unique not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE QueryableParameterSet
  (
    ID                    int,
    Hash                  varchar(1000)                    not null,
    Name                  varchar(100)                     unique not null,
    Version               varchar(100)                     not null,
    Type                  varchar(100)                     not null,
    Annotation            varchar(1000)                    not null,
    Content               varchar(1000)                    not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE ParameterBinding
  (
    ID                    int,
    Self                  int                              not null,
    Contains              int                              not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID),
    unique(Self,Contains)
  );

REM ======================================================================

CREATE TABLE TriggerPathDescription
  (
    ID                      int,
    TriggerPathDescription  varchar(100)                     unique not null,
    CreatedBy               int,
    CreationDate            TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy          int,
    LastModificationDate    TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE MCDescription
  (
    ID                    int,
    MCChannelDescription  varchar(100)                     not null,
    MCProduction          varchar(100),
    MCDecayChain          varchar(100),
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID),
    unique(MCChannelDescription,MCProduction,MCDecayChain)
  );

REM ======================================================================

CREATE TABLE OtherDescription
  (
    ID                    int,
    Description           varchar(100)                     unique not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AlgorithmConfig
  (
    ID                    int,
    ExecutableName        int                              not null,
    ApplicationVersion    int                              not null,
    ApplicationFamily     int                              not null,
    ParameterSetID        int                              not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID),
    unique(ExecutableName,ApplicationVersion,ApplicationFamily,ParameterSetID)
  );

REM ======================================================================

CREATE TABLE PrimaryDatasetDescription
  (
    ID                      int,
    TriggerDescriptionID    int                              unique,
    MCChannelDescriptionID  int                              unique,
    OtherDescriptionID      int                              unique,
    CreatedBy               int,
    CreationDate            TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy          int,
    LastModificationDate    TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE PrimaryDataset
  (
    ID                    int,
    Annotation            varchar(1000)                    not null,
    Name                  varchar(100)                     unique not null,
    Description           int                              not null,
    StartDate             varchar(100),
    EndDate               varchar(100),
    Type                  int                              not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE ProcessedDataset
  (
    ID                    int,
    Name                  varchar(100)                     not null,
    PrimaryDataset        int                              not null,
    OpenForWriting        char(1)                          not null,
    PhysicsGroup          int,
    Status                int                              not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID),
    unique(Name,PrimaryDataset),
    CHECK(OpenForWriting IN ('y', 'n'))
  );

REM ======================================================================

CREATE TABLE Block
  (
    ID                    int,
    BlockSize             int                              not null,
    Name                  varchar(100)                     unique not null,
    Dataset               int                              not null,
    NumberOfFiles         int                              not null,
    OpenForWriting        char(1)                          not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID),
    CHECK(OpenForWriting IN ('y', 'n'))
  );

REM ======================================================================

CREATE TABLE ProcDSRuns
  (
    ID                    int,
    Dataset               int                              not null,
    Run                   int                              not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID),
    unique(Dataset,Run)
  );

REM ======================================================================

CREATE TABLE ProcDSTier
  (
    ID                    int,
    Dataset               int                              not null,
    DataTier              int                              not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID),
    unique(Dataset,DataTier)
  );

REM ======================================================================

CREATE TABLE DatasetParentage
  (
    ThisDataset           int                              not null,
    ItsParent             int                              not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ThisDataset,ItsParent)
  );

REM ======================================================================

CREATE TABLE ProcAlgoMap
  (
    ID                    int,
    Dataset               int                              not null,
    Algorithm             int                              not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AnalysisDataset
  (
    ID                    int,
    Annotation            varchar(1000)                    not null,
    Query                 varchar(1000)                    not null,
    ProcessedDS           int                              not null,
    DatasetType           int                              not null,
    Status                int                              not null,
    Parent                int,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE Files
  (
    ID                    int,
    LogicalFileName       varchar(500)                     unique not null,
    Dataset               int                              not null,
    Block                 int                              not null,
    Checksum              varchar(100)                     not null,
    NumberOfEvents        int                              not null,
    FileSize              int                              not null,
    Status                int                              not null,
    FileType              int                              not null,
    ValidationStatus      int,
    QueryableMetadata     varchar(1000),
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AnalysisDatasetLumi
  (
    ID                    int,
    AnalysisDataset       int                              not null,
    Lumi                  int                              not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE FileTier
  (
    ID                    int,
    FileID                int                              not null,
    DataTier              int                              not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID),
    unique(FileID,DataTier)
  );

REM ======================================================================

CREATE TABLE FileParentage
  (
    ThisFile              int                              not null,
    ItsParent             int                              not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    primary key(ThisFile,ItsParent)
  );

REM ======================================================================

CREATE TABLE FileLumi
  (
    ID                    int,
    FileID                int                              not null,
    Lumi                  int                              not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID),
    unique(FileID,Lumi)
  );

REM ======================================================================

CREATE TABLE FileAlgoMap
  (
    ID                    int,
    FileID                int                              not null,
    Algorithm             int                              not null,
    CreationDate          TIMESTAMP DEFAULT SYSTIMESTAMP,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT SYSTIMESTAMP,
    LastModifiedBy        int,
    primary key(ID)
  );

REM ======================================================================

ALTER TABLE Person ADD CONSTRAINT 
    Person_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE Person ADD CONSTRAINT 
    Person_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE Role ADD CONSTRAINT 
    Role_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE Role ADD CONSTRAINT 
    Role_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE AssignedRole ADD CONSTRAINT 
    AssignedRole_PersonID_FK foreign key(PersonID) references Person(ID)
/
ALTER TABLE AssignedRole ADD CONSTRAINT 
    AssignedRole_RoleID_FK foreign key(RoleID) references Role(ID)
/
ALTER TABLE AssignedRole ADD CONSTRAINT 
    AssignedRole_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AssignedRole ADD CONSTRAINT 
    AssignedRole_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE PhysicsGroup ADD CONSTRAINT 
    PhysicsGroupPhysicsGroupCon_FK foreign key(PhysicsGroupConvener) references Person(ID)
/
ALTER TABLE PhysicsGroup ADD CONSTRAINT 
    PhysicsGroup_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE PhysicsGroup ADD CONSTRAINT 
    PhysicsGroup_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE SchemaVersion ADD CONSTRAINT 
    SchemaVersion_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE SchemaVersion ADD CONSTRAINT 
    SchemaVersionLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE Runs ADD CONSTRAINT 
    Runs_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE Runs ADD CONSTRAINT 
    Runs_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE DataTier ADD CONSTRAINT 
    DataTier_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/
ALTER TABLE DataTier ADD CONSTRAINT 
    DataTier_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/

ALTER TABLE LumiSection ADD CONSTRAINT 
    LumiSection_RunNumber_FK foreign key(RunNumber) references Runs(ID)
/
ALTER TABLE LumiSection ADD CONSTRAINT 
    LumiSection_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE LumiSection ADD CONSTRAINT 
    LumiSection_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE Status ADD CONSTRAINT 
    Status_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE Status ADD CONSTRAINT 
    Status_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE Type ADD CONSTRAINT 
    Type_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE Type ADD CONSTRAINT 
    Type_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE Description ADD CONSTRAINT 
    Description_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE Description ADD CONSTRAINT 
    Description_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE TimeLog ADD CONSTRAINT 
    TimeLog_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE TimeLog ADD CONSTRAINT 
    TimeLog_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE AppFamily ADD CONSTRAINT 
    AppFamily_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AppFamily ADD CONSTRAINT 
    AppFamily_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE AppVersion ADD CONSTRAINT 
    AppVersion_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AppVersion ADD CONSTRAINT 
    AppVersion_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE AppExecutable ADD CONSTRAINT 
    AppExecutable_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AppExecutable ADD CONSTRAINT 
    AppExecutableLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE QueryableParameterSet ADD CONSTRAINT 
    QueryableParameterSetCreate_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE QueryableParameterSet ADD CONSTRAINT 
    QueryableParameterSetLastMo_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE ParameterBinding ADD CONSTRAINT 
    ParameterBinding_Self_FK foreign key(Self) references QueryableParameterSet(ID)
/
ALTER TABLE ParameterBinding ADD CONSTRAINT 
    ParameterBinding_Contains_FK foreign key(Contains) references QueryableParameterSet(ID)
/
ALTER TABLE ParameterBinding ADD CONSTRAINT 
    ParameterBinding_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ParameterBinding ADD CONSTRAINT 
    ParameterBindingLastModifie_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE TriggerPathDescription ADD CONSTRAINT 
    TriggerPathDescriptionCreat_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE TriggerPathDescription ADD CONSTRAINT 
    TriggerPathDescriptionLastM_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE MCDescription ADD CONSTRAINT 
    MCDescription_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE MCDescription ADD CONSTRAINT 
    MCDescriptionLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE OtherDescription ADD CONSTRAINT 
    OtherDescription_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE OtherDescription ADD CONSTRAINT 
    OtherDescriptionLastModifie_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE AlgorithmConfig ADD CONSTRAINT 
    AlgorithmConfigExecutableNa_FK foreign key(ExecutableName) references AppExecutable(ID)
/
ALTER TABLE AlgorithmConfig ADD CONSTRAINT 
    AlgorithmConfigApplicationV_FK foreign key(ApplicationVersion) references AppVersion(ID)
/
ALTER TABLE AlgorithmConfig ADD CONSTRAINT 
    AlgorithmConfigApplicationF_FK foreign key(ApplicationFamily) references AppFamily(ID)
/
ALTER TABLE AlgorithmConfig ADD CONSTRAINT 
    AlgorithmConfigParameterSet_FK foreign key(ParameterSetID) references QueryableParameterSet(ID)
/
ALTER TABLE AlgorithmConfig ADD CONSTRAINT 
    AlgorithmConfig_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AlgorithmConfig ADD CONSTRAINT 
    AlgorithmConfigLastModified_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE PrimaryDatasetDescription ADD CONSTRAINT 
    PrimaryDatasetDescriptionTr_FK foreign key(TriggerDescriptionID) references TriggerPathDescription(ID)
/
ALTER TABLE PrimaryDatasetDescription ADD CONSTRAINT 
    PrimaryDatasetDescriptionMC_FK foreign key(MCChannelDescriptionID) references MCDescription(ID)
/
ALTER TABLE PrimaryDatasetDescription ADD CONSTRAINT 
    PrimaryDatasetDescriptionOt_FK foreign key(OtherDescriptionID) references OtherDescription(ID)
/
ALTER TABLE PrimaryDatasetDescription ADD CONSTRAINT 
    PrimaryDatasetDescriptionCr_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE PrimaryDatasetDescription ADD CONSTRAINT 
    PrimaryDatasetDescriptionLa_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE PrimaryDataset ADD CONSTRAINT 
    PrimaryDataset_Description_FK foreign key(Description) references PrimaryDatasetDescription(ID)
/
ALTER TABLE PrimaryDataset ADD CONSTRAINT 
    PrimaryDataset_Type_FK foreign key(Type) references Type(ID)
/
ALTER TABLE PrimaryDataset ADD CONSTRAINT 
    PrimaryDataset_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE PrimaryDataset ADD CONSTRAINT 
    PrimaryDatasetLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDatasetPrimaryData_FK foreign key(PrimaryDataset) references PrimaryDataset(ID)
/
ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDatasetPhysicsGrou_FK foreign key(PhysicsGroup) references PhysicsGroup(ID)
/
ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDataset_Status_FK foreign key(Status) references Status(ID)
/
ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDataset_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDatasetLastModifie_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE Block ADD CONSTRAINT 
    Block_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/
ALTER TABLE Block ADD CONSTRAINT 
    Block_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE Block ADD CONSTRAINT 
    Block_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE ProcDSRuns ADD CONSTRAINT 
    ProcDSRuns_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/
ALTER TABLE ProcDSRuns ADD CONSTRAINT 
    ProcDSRuns_Run_FK foreign key(Run) references Runs(ID)
/
ALTER TABLE ProcDSRuns ADD CONSTRAINT 
    ProcDSRuns_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ProcDSRuns ADD CONSTRAINT 
    ProcDSRuns_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE ProcDSTier ADD CONSTRAINT 
    ProcDSTier_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/
ALTER TABLE ProcDSTier ADD CONSTRAINT 
    ProcDSTier_DataTier_FK foreign key(DataTier) references DataTier(ID)
/
ALTER TABLE ProcDSTier ADD CONSTRAINT 
    ProcDSTier_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ProcDSTier ADD CONSTRAINT 
    ProcDSTier_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE DatasetParentage ADD CONSTRAINT 
    DatasetParentageThisDataset_FK foreign key(ThisDataset) references ProcessedDataset(ID)
/
ALTER TABLE DatasetParentage ADD CONSTRAINT 
    DatasetParentage_ItsParent_FK foreign key(ItsParent) references ProcessedDataset(ID)
/
ALTER TABLE DatasetParentage ADD CONSTRAINT 
    DatasetParentage_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE DatasetParentage ADD CONSTRAINT 
    DatasetParentageLastModifie_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE ProcAlgoMap ADD CONSTRAINT 
    ProcAlgoMap_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/
ALTER TABLE ProcAlgoMap ADD CONSTRAINT 
    ProcAlgoMap_Algorithm_FK foreign key(Algorithm) references AlgorithmConfig(ID)
/
ALTER TABLE ProcAlgoMap ADD CONSTRAINT 
    ProcAlgoMap_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ProcAlgoMap ADD CONSTRAINT 
    ProcAlgoMap_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_ProcessedDS_FK foreign key(ProcessedDS) references ProcessedDataset(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_DatasetType_FK foreign key(DatasetType) references Type(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Status_FK foreign key(Status) references Status(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Parent_FK foreign key(Parent) references AnalysisDataset(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDatasetLastModified_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE Files ADD CONSTRAINT 
    Files_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_Block_FK foreign key(Block) references Block(ID)
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_Status_FK foreign key(Status) references Status(ID)
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_FileType_FK foreign key(FileType) references Type(ID)
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_ValidationStatus_FK foreign key(ValidationStatus) references Status(ID)
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE AnalysisDatasetLumi ADD CONSTRAINT 
    AnalysisDatasetLumiAnalysis_FK foreign key(AnalysisDataset) references AnalysisDataset(ID)
/
ALTER TABLE AnalysisDatasetLumi ADD CONSTRAINT 
    AnalysisDatasetLumi_Lumi_FK foreign key(Lumi) references LumiSection(ID)
/
ALTER TABLE AnalysisDatasetLumi ADD CONSTRAINT 
    AnalysisDatasetLumiCreatedB_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AnalysisDatasetLumi ADD CONSTRAINT 
    AnalysisDatasetLumiLastModi_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE FileTier ADD CONSTRAINT 
    FileTier_FileID_FK foreign key(FileID) references Files(ID)
/
ALTER TABLE FileTier ADD CONSTRAINT 
    FileTier_DataTier_FK foreign key(DataTier) references DataTier(ID)
/
ALTER TABLE FileTier ADD CONSTRAINT 
    FileTier_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileTier ADD CONSTRAINT 
    FileTier_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE FileParentage ADD CONSTRAINT 
    FileParentage_ThisFile_FK foreign key(ThisFile) references Files(ID)
/
ALTER TABLE FileParentage ADD CONSTRAINT 
    FileParentage_ItsParent_FK foreign key(ItsParent) references Files(ID)
/
ALTER TABLE FileParentage ADD CONSTRAINT 
    FileParentage_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileParentage ADD CONSTRAINT 
    FileParentageLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE FileLumi ADD CONSTRAINT 
    FileLumi_FileID_FK foreign key(FileID) references Files(ID)
/
ALTER TABLE FileLumi ADD CONSTRAINT 
    FileLumi_Lumi_FK foreign key(Lumi) references LumiSection(ID)
/
ALTER TABLE FileLumi ADD CONSTRAINT 
    FileLumi_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileLumi ADD CONSTRAINT 
    FileLumi_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE FileAlgoMap ADD CONSTRAINT 
    FileAlgoMap_FileID_FK foreign key(FileID) references Files(ID)
/
ALTER TABLE FileAlgoMap ADD CONSTRAINT 
    FileAlgoMap_Algorithm_FK foreign key(Algorithm) references AlgorithmConfig(ID)
/
ALTER TABLE FileAlgoMap ADD CONSTRAINT 
    FileAlgoMap_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileAlgoMap ADD CONSTRAINT 
    FileAlgoMap_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/


CREATE INDEX  ON Person(DistinguishedName);

CREATE INDEX  ON Role(RoleName);

CREATE INDEX  ON PhysicsGroup(PhysicsGroupName);

CREATE INDEX  ON Runs(RunNumber,StoreNumber);

CREATE INDEX  ON DataTier(Name);

CREATE INDEX  ON AppFamily(FamilyName);

CREATE INDEX  ON AppVersion(Version);

CREATE INDEX  ON AppExecutable(ExecutableName);

CREATE INDEX  ON TriggerPathDescription(TriggerPathDescription);

CREATE INDEX  ON PrimaryDataset(Name);

CREATE INDEX  ON ProcessedDataset(Name);

CREATE INDEX  ON Block(Name,Dataset);

CREATE INDEX  ON AnalysisDataset(Query);

CREATE INDEX  ON Files(LogicalFileName);
