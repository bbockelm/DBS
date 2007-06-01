-- ======================================================================
-- ===   Sql Script for Database : DBS_NEW_ERA
-- ===
-- === Build : 698
-- ======================================================================

drop database if exists dbs_new_era_v25;
create database dbs_new_era_v25;
use dbs_new_era_v25;
-- ======================================================================

CREATE TABLE Person
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(100),
    DistinguishedName     varchar(500)      unique not null,
    ContactInfo           varchar(250),
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE Role
  (
    ID                    BIGINT UNSIGNED,
    RoleName              varchar(100)      unique not null,
    RoleDescription       varchar(500)      not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE AssignedRole
  (
    ID                    BIGINT UNSIGNED,
    PersonID              BIGINT UNSIGNED   not null,
    RoleID                BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE PhysicsGroup
  (
    ID                    BIGINT UNSIGNED,
    PhysicsGroupName      varchar(500)      unique not null,
    PhysicsGroupConvener  BIGINT UNSIGNED,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE SchemaVersion
  (
    ID                    BIGINT UNSIGNED,
    SchemaVersion         varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE PrimaryDataset
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(100)      unique not null,
    Annotation            varchar(1000)     not null,
    Description           BIGINT UNSIGNED,
    StartDate             varchar(100),
    EndDate               varchar(100),
    Type                  BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE ProcessedDataset
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(500)      not null,
    PrimaryDataset        BIGINT UNSIGNED   not null,
    PhysicsGroup          BIGINT UNSIGNED   not null,
    Status                BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID),
    unique(Name,PrimaryDataset)
  );

-- ======================================================================

CREATE TABLE Runs
  (
    ID                    BIGINT UNSIGNED,
    RunNumber             BIGINT UNSIGNED   unique not null,
    NumberOfEvents        BIGINT UNSIGNED   not null,
    NumberOfLumiSections  BIGINT UNSIGNED   not null,
    TotalLuminosity       BIGINT UNSIGNED   not null,
    StoreNumber           BIGINT UNSIGNED   not null,
    StartOfRun            varchar(100),
    EndOfRun              varchar(100),
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE Files
  (
    ID                    BIGINT UNSIGNED,
    LogicalFileName       varchar(500)      unique not null,
    Dataset               BIGINT UNSIGNED   not null,
    Block                 BIGINT UNSIGNED   not null,
    Checksum              varchar(100)      not null,
    NumberOfEvents        BIGINT UNSIGNED   not null,
    FileSize              BIGINT UNSIGNED   not null,
    FileStatus            BIGINT UNSIGNED   not null,
    FileType              BIGINT UNSIGNED   not null,
    ValidationStatus      BIGINT UNSIGNED,
    QueryableMetadata     varchar(1000)     default 'NOTSET',
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE DataTier
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(100)      unique not null,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE LumiSection
  (
    ID                    BIGINT UNSIGNED,
    LumiSectionNumber     BIGINT UNSIGNED   not null,
    RunNumber             BIGINT UNSIGNED   not null,
    StartEventNumber      BIGINT UNSIGNED   not null,
    EndEventNumber        BIGINT UNSIGNED   not null,
    LumiStartTime         varchar(100),
    LumiEndTime           varchar(100),
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID),
    unique(LumiSectionNumber,RunNumber)
  );

-- ======================================================================

CREATE TABLE Branch
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(500)      unique not null,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE TimeLog
  (
    ID                    BIGINT UNSIGNED,
    Action                varchar(100)      not null,
    Cause                 varchar(100)      not null,
    Effect                varchar(100)      not null,
    Description           varchar(500)      not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE DataTierOrder
  (
    ID                    BIGINT UNSIGNED,
    DataTierOrder         varchar(250)      unique not null,
    Description           varchar(1000),
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE AlgorithmConfig
  (
    ID                    BIGINT UNSIGNED,
    ExecutableName        BIGINT UNSIGNED   not null,
    ApplicationVersion    BIGINT UNSIGNED   not null,
    ApplicationFamily     BIGINT UNSIGNED   not null,
    ParameterSetID        BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(ExecutableName,ApplicationVersion,ApplicationFamily,ParameterSetID)
  );

-- ======================================================================

CREATE TABLE AppFamily
  (
    ID                    BIGINT UNSIGNED,
    FamilyName            varchar(100)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE AppVersion
  (
    ID                    BIGINT UNSIGNED,
    Version               varchar(100)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE AppExecutable
  (
    ID                    BIGINT UNSIGNED,
    ExecutableName        varchar(100)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE QueryableParameterSet
  (
    ID                    BIGINT UNSIGNED,
    Hash                  varchar(700)      unique not null,
    Name                  varchar(1000),
    Version               varchar(100),
    Type                  varchar(100),
    Annotation            varchar(1000),
    Content               LONGTEXT,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE ParameterBinding
  (
    ID                    BIGINT UNSIGNED,
    Self                  BIGINT UNSIGNED   not null,
    Contains              BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE PrimaryDatasetDescription
  (
    ID                      BIGINT UNSIGNED,
    TriggerDescriptionID    BIGINT UNSIGNED,
    MCChannelDescriptionID  BIGINT UNSIGNED,
    OtherDescriptionID      BIGINT UNSIGNED,
    CreatedBy               BIGINT UNSIGNED,
    CreationDate            BIGINT,
    LastModifiedBy          BIGINT UNSIGNED,
    LastModificationDate    BIGINT,

    primary key(ID),
    unique(TriggerDescriptionID,MCChannelDescriptionID,OtherDescriptionID)
  );

-- ======================================================================

CREATE TABLE TriggerPathDescription
  (
    ID                      BIGINT UNSIGNED,
    TriggerPathDescription  varchar(100)      unique not null,
    CreatedBy               BIGINT UNSIGNED,
    CreationDate            BIGINT,
    LastModifiedBy          BIGINT UNSIGNED,
    LastModificationDate    BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE MCDescription
  (
    ID                    BIGINT UNSIGNED,
    MCChannelDescription  varchar(100)      not null,
    MCProduction          varchar(100),
    MCDecayChain          varchar(100),
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID),
    unique(MCChannelDescription,MCProduction,MCDecayChain)
  );

-- ======================================================================

CREATE TABLE OtherDescription
  (
    ID                    BIGINT UNSIGNED,
    Description           varchar(100)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE FileTier
  (
    ID                    BIGINT UNSIGNED,
    Fileid                BIGINT UNSIGNED   not null,
    DataTier              BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Fileid,DataTier)
  );

-- ======================================================================

CREATE TABLE FileParentage
  (
    ID                    BIGINT UNSIGNED,
    ThisFile              BIGINT UNSIGNED   not null,
    ItsParent             BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID),
    unique(ThisFile,ItsParent)
  );

-- ======================================================================

CREATE TABLE FileRunLumi
  (
    ID                    BIGINT UNSIGNED,
    Fileid                BIGINT UNSIGNED   not null,
    Lumi                  BIGINT UNSIGNED,
    Run                   BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Fileid,Lumi,Run)
  );

-- ======================================================================

CREATE TABLE FileAlgo
  (
    ID                    BIGINT UNSIGNED,
    Fileid                BIGINT UNSIGNED   not null,
    Algorithm             BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Fileid,Algorithm)
  );

-- ======================================================================

CREATE TABLE FileStatus
  (
    ID                    BIGINT UNSIGNED,
    Status                varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE FileType
  (
    ID                    BIGINT UNSIGNED,
    Type                  varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE FileBranch
  (
    ID                    BIGINT UNSIGNED,
    Fileid                BIGINT UNSIGNED   not null,
    Branch                BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Fileid,Branch)
  );

-- ======================================================================

CREATE TABLE FileValidStatus
  (
    ID                    BIGINT UNSIGNED,
    Status                varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE FileTriggerTag
  (
    ID                    BIGINT UNSIGNED,
    Fileid                BIGINT UNSIGNED   not null,
    TriggerTag            varchar(500)      not null,
    NumberOfEvents        BIGINT,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Fileid,TriggerTag)
  );

-- ======================================================================

CREATE TABLE FileAssoc
  (
    ID                    BIGINT UNSIGNED,
    ThisFile              BIGINT UNSIGNED   not null,
    ItsAssoc              BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID),
    unique(ThisFile,ItsAssoc)
  );

-- ======================================================================

CREATE TABLE ProcDSRuns
  (
    ID                    BIGINT UNSIGNED,
    Dataset               BIGINT UNSIGNED   not null,
    Run                   BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Dataset,Run)
  );

-- ======================================================================

CREATE TABLE ProcDSTier
  (
    ID                    BIGINT UNSIGNED,
    Dataset               BIGINT UNSIGNED   not null,
    DataTier              BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Dataset,DataTier)
  );

-- ======================================================================

CREATE TABLE ProcDSParent
  (
    ID                    BIGINT UNSIGNED,
    ThisDataset           BIGINT UNSIGNED   not null,
    ItsParent             BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID),
    unique(ThisDataset,ItsParent)
  );

-- ======================================================================

CREATE TABLE ProcAlgo
  (
    ID                    BIGINT UNSIGNED,
    Dataset               BIGINT UNSIGNED   not null,
    Algorithm             BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Dataset,Algorithm)
  );

-- ======================================================================

CREATE TABLE AnalysisDataset
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(500)      not null,
    Version               BIGINT            not null,
    Path                  varchar(500)      not null,
    Definition            BIGINT UNSIGNED   not null,
    Description           varchar(1000),
    PhysicsGroup          BIGINT UNSIGNED   not null,
    ProcessedDS           BIGINT UNSIGNED   not null,
    Type                  BIGINT UNSIGNED,
    Status                BIGINT UNSIGNED,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID),
    unique(Name,Version)
  );

-- ======================================================================

CREATE TABLE AnalysisDSType
  (
    ID                    BIGINT UNSIGNED,
    Type                  varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE AnalysisDSStatus
  (
    ID                    BIGINT UNSIGNED,
    Status                varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE AnalysisDSFileLumi
  (
    ID                    BIGINT UNSIGNED,
    AnalysisDataset       BIGINT UNSIGNED   not null,
    Lumi                  BIGINT UNSIGNED,
    Fileid                BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(AnalysisDataset,Lumi,Fileid)
  );

-- ======================================================================

CREATE TABLE AnalysisDSDef
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(700)      unique not null,
    Path                  varchar(1000),
    Description           TEXT,
    LumiSections          TEXT,
    LumiSectionRanges     TEXT,
    Runs                  TEXT,
    RunsRanges            TEXT,
    Algorithms            varchar(1000),
    LFNs                  TEXT,
    UserCut               TEXT,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE CompositeADS
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(500)      unique not null,
    Description           varchar(1000)     not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE CompADSMap
  (
    ID                    BIGINT UNSIGNED,
    CompADS               BIGINT UNSIGNED   not null,
    ADS                   BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(CompADS,ADS)
  );

-- ======================================================================

CREATE TABLE SEBlock
  (
    ID                    BIGINT UNSIGNED,
    SEID                  BIGINT UNSIGNED   not null,
    BlockID               BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(SEID,BlockID)
  );

-- ======================================================================

CREATE TABLE StorageElement
  (
    ID                    BIGINT UNSIGNED,
    SEName                varchar(500)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE Block
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(500)      unique not null,
    Path                  varchar(500)      not null,
    Dataset               BIGINT UNSIGNED   not null,
    BlockSize             BIGINT UNSIGNED   not null,
    NumberOfFiles         BIGINT UNSIGNED   not null,
    NumberOfEvents        BIGINT UNSIGNED   not null,
    OpenForWriting        int               not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE ProcDSStatus
  (
    ID                    BIGINT UNSIGNED,
    Status                varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE PrimaryDSType
  (
    ID                    BIGINT UNSIGNED,
    Type                  varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE SubSystem
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(500)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE QualityValues
  (
    ID                    BIGINT UNSIGNED,
    Value                 varchar(500)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID)
  );

-- ======================================================================

CREATE TABLE RunLumiQuality
  (
    ID                    BIGINT UNSIGNED,
    Run                   BIGINT UNSIGNED   not null,
    Lumi                  BIGINT UNSIGNED,
    SubSystem             BIGINT UNSIGNED   not null,
    DQValue               BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(ID,Run,Lumi,SubSystem)
  );

-- ======================================================================

CREATE TABLE QualityHistory
  (
    ID                    BIGINT UNSIGNED,
    HistoryOf             BIGINT UNSIGNED,
    HistoryTimeStamp      BIGINT            not null,
    Run                   BIGINT UNSIGNED   not null,
    Lumi                  BIGINT UNSIGNED,
    SubSystem             BIGINT UNSIGNED   not null,
    DQValue               BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(HistoryTimeStamp,Run,Lumi,SubSystem,DQValue)
  );

-- ======================================================================

CREATE TABLE QFlagAssoc
  (
    ID                    BIGINT UNSIGNED,
    ThisFlag              BIGINT UNSIGNED   not null,
    ItsAssoc              BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    primary key(ID),
    unique(ThisFlag,ItsAssoc)
  );

-- ======================================================================

CREATE TABLE QualityVersion
  (
    ID                    BIGINT UNSIGNED,
    Version               BIGINT            unique not null,
    VersionTimeStamp      BIGINT            not null,
    VersionName           varchar(1000),
    Description           varchar(1000),
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(VersionTimeStamp)
  );

-- ======================================================================

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

ALTER TABLE PrimaryDataset ADD CONSTRAINT 
    PrimaryDataset_Description_FK foreign key(Description) references PrimaryDatasetDescription(ID)
/
ALTER TABLE PrimaryDataset ADD CONSTRAINT 
    PrimaryDataset_Type_FK foreign key(Type) references PrimaryDSType(ID)
/
ALTER TABLE PrimaryDataset ADD CONSTRAINT 
    PrimaryDataset_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE PrimaryDataset ADD CONSTRAINT 
    PrimaryDatasetLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDatasetPrimaryData_FK foreign key(PrimaryDataset) references PrimaryDataset(ID) on delete CASCADE
/
ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDatasetPhysicsGrou_FK foreign key(PhysicsGroup) references PhysicsGroup(ID)
/
ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDataset_Status_FK foreign key(Status) references ProcDSStatus(ID)
/
ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDataset_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDatasetLastModifie_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE Runs ADD CONSTRAINT 
    Runs_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE Runs ADD CONSTRAINT 
    Runs_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE Files ADD CONSTRAINT 
    Files_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_Block_FK foreign key(Block) references Block(ID) on delete CASCADE
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_FileStatus_FK foreign key(FileStatus) references FileStatus(ID)
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_FileType_FK foreign key(FileType) references FileType(ID)
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_ValidationStatus_FK foreign key(ValidationStatus) references FileValidStatus(ID)
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
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

ALTER TABLE Branch ADD CONSTRAINT 
    Branch_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/
ALTER TABLE Branch ADD CONSTRAINT 
    Branch_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/

ALTER TABLE TimeLog ADD CONSTRAINT 
    TimeLog_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE TimeLog ADD CONSTRAINT 
    TimeLog_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE DataTierOrder ADD CONSTRAINT 
    DataTierOrder_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE DataTierOrder ADD CONSTRAINT 
    DataTierOrderLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
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

ALTER TABLE FileTier ADD CONSTRAINT 
    FileTier_Fileid_FK foreign key(Fileid) references Files(ID) on delete CASCADE
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
    FileParentage_ThisFile_FK foreign key(ThisFile) references Files(ID) on delete CASCADE
/
ALTER TABLE FileParentage ADD CONSTRAINT 
    FileParentage_ItsParent_FK foreign key(ItsParent) references Files(ID) on delete CASCADE
/
ALTER TABLE FileParentage ADD CONSTRAINT 
    FileParentage_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileParentage ADD CONSTRAINT 
    FileParentageLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE FileRunLumi ADD CONSTRAINT 
    FileRunLumi_Fileid_FK foreign key(Fileid) references Files(ID) on delete CASCADE
/
ALTER TABLE FileRunLumi ADD CONSTRAINT 
    FileRunLumi_Lumi_FK foreign key(Lumi) references LumiSection(ID)
/
ALTER TABLE FileRunLumi ADD CONSTRAINT 
    FileRunLumi_Run_FK foreign key(Run) references Runs(ID)
/
ALTER TABLE FileRunLumi ADD CONSTRAINT 
    FileRunLumi_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileRunLumi ADD CONSTRAINT 
    FileRunLumi_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE FileAlgo ADD CONSTRAINT 
    FileAlgo_Fileid_FK foreign key(Fileid) references Files(ID) on delete CASCADE
/
ALTER TABLE FileAlgo ADD CONSTRAINT 
    FileAlgo_Algorithm_FK foreign key(Algorithm) references AlgorithmConfig(ID)
/
ALTER TABLE FileAlgo ADD CONSTRAINT 
    FileAlgo_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileAlgo ADD CONSTRAINT 
    FileAlgo_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE FileStatus ADD CONSTRAINT 
    FileStatus_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileStatus ADD CONSTRAINT 
    FileStatus_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE FileType ADD CONSTRAINT 
    FileType_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileType ADD CONSTRAINT 
    FileType_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE FileBranch ADD CONSTRAINT 
    FileBranch_Fileid_FK foreign key(Fileid) references Files(ID) on delete CASCADE
/
ALTER TABLE FileBranch ADD CONSTRAINT 
    FileBranch_Branch_FK foreign key(Branch) references Branch(ID)
/
ALTER TABLE FileBranch ADD CONSTRAINT 
    FileBranch_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileBranch ADD CONSTRAINT 
    FileBranch_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE FileValidStatus ADD CONSTRAINT 
    FileValidStatus_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileValidStatus ADD CONSTRAINT 
    FileValidStatusLastModified_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE FileTriggerTag ADD CONSTRAINT 
    FileTriggerTag_Fileid_FK foreign key(Fileid) references Files(ID) on delete CASCADE
/
ALTER TABLE FileTriggerTag ADD CONSTRAINT 
    FileTriggerTag_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileTriggerTag ADD CONSTRAINT 
    FileTriggerTagLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE FileAssoc ADD CONSTRAINT 
    FileAssoc_ThisFile_FK foreign key(ThisFile) references Files(ID) on delete CASCADE
/
ALTER TABLE FileAssoc ADD CONSTRAINT 
    FileAssoc_ItsAssoc_FK foreign key(ItsAssoc) references Files(ID) on delete CASCADE
/
ALTER TABLE FileAssoc ADD CONSTRAINT 
    FileAssoc_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileAssoc ADD CONSTRAINT 
    FileAssoc_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE ProcDSRuns ADD CONSTRAINT 
    ProcDSRuns_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID) on delete CASCADE
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
    ProcDSTier_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID) on delete CASCADE
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

ALTER TABLE ProcDSParent ADD CONSTRAINT 
    ProcDSParent_ThisDataset_FK foreign key(ThisDataset) references ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE ProcDSParent ADD CONSTRAINT 
    ProcDSParent_ItsParent_FK foreign key(ItsParent) references ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE ProcDSParent ADD CONSTRAINT 
    ProcDSParent_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ProcDSParent ADD CONSTRAINT 
    ProcDSParent_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE ProcAlgo ADD CONSTRAINT 
    ProcAlgo_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE ProcAlgo ADD CONSTRAINT 
    ProcAlgo_Algorithm_FK foreign key(Algorithm) references AlgorithmConfig(ID) on delete CASCADE
/
ALTER TABLE ProcAlgo ADD CONSTRAINT 
    ProcAlgo_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ProcAlgo ADD CONSTRAINT 
    ProcAlgo_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Definition_FK foreign key(Definition) references AnalysisDSDef(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDatasetPhysicsGroup_FK foreign key(PhysicsGroup) references PhysicsGroup(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_ProcessedDS_FK foreign key(ProcessedDS) references ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Type_FK foreign key(Type) references AnalysisDSType(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Status_FK foreign key(Status) references AnalysisDSStatus(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDatasetLastModified_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE AnalysisDSType ADD CONSTRAINT 
    AnalysisDSType_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AnalysisDSType ADD CONSTRAINT 
    AnalysisDSTypeLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE AnalysisDSStatus ADD CONSTRAINT 
    AnalysisDSStatus_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AnalysisDSStatus ADD CONSTRAINT 
    AnalysisDSStatusLastModifie_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE AnalysisDSFileLumi ADD CONSTRAINT 
    AnalysisDSFileLumiAnalysisD_FK foreign key(AnalysisDataset) references AnalysisDataset(ID) on delete CASCADE
/
ALTER TABLE AnalysisDSFileLumi ADD CONSTRAINT 
    AnalysisDSFileLumi_Lumi_FK foreign key(Lumi) references LumiSection(ID)
/
ALTER TABLE AnalysisDSFileLumi ADD CONSTRAINT 
    AnalysisDSFileLumi_Fileid_FK foreign key(Fileid) references Files(ID)
/
ALTER TABLE AnalysisDSFileLumi ADD CONSTRAINT 
    AnalysisDSFileLumiCreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AnalysisDSFileLumi ADD CONSTRAINT 
    AnalysisDSFileLumiLastModif_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE AnalysisDSDef ADD CONSTRAINT 
    AnalysisDSDef_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AnalysisDSDef ADD CONSTRAINT 
    AnalysisDSDefLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE CompositeADS ADD CONSTRAINT 
    CompositeADS_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE CompositeADS ADD CONSTRAINT 
    CompositeADS_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE CompADSMap ADD CONSTRAINT 
    CompADSMap_CompADS_FK foreign key(CompADS) references CompositeADS(ID)
/
ALTER TABLE CompADSMap ADD CONSTRAINT 
    CompADSMap_ADS_FK foreign key(ADS) references AnalysisDataset(ID) on delete CASCADE
/
ALTER TABLE CompADSMap ADD CONSTRAINT 
    CompADSMap_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE CompADSMap ADD CONSTRAINT 
    CompADSMap_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE SEBlock ADD CONSTRAINT 
    SEBlock_SEID_FK foreign key(SEID) references StorageElement(ID) on delete CASCADE
/
ALTER TABLE SEBlock ADD CONSTRAINT 
    SEBlock_BlockID_FK foreign key(BlockID) references Block(ID) on delete CASCADE
/
ALTER TABLE SEBlock ADD CONSTRAINT 
    SEBlock_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE SEBlock ADD CONSTRAINT 
    SEBlock_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE StorageElement ADD CONSTRAINT 
    StorageElement_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE StorageElement ADD CONSTRAINT 
    StorageElementLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE Block ADD CONSTRAINT 
    Block_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE Block ADD CONSTRAINT 
    Block_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE Block ADD CONSTRAINT 
    Block_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE ProcDSStatus ADD CONSTRAINT 
    ProcDSStatus_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ProcDSStatus ADD CONSTRAINT 
    ProcDSStatus_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE PrimaryDSType ADD CONSTRAINT 
    PrimaryDSType_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE PrimaryDSType ADD CONSTRAINT 
    PrimaryDSTypeLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE SubSystem ADD CONSTRAINT 
    SubSystem_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE SubSystem ADD CONSTRAINT 
    SubSystem_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE QualityValues ADD CONSTRAINT 
    QualityValues_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE QualityValues ADD CONSTRAINT 
    QualityValuesLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE RunLumiQuality ADD CONSTRAINT 
    RunLumiQuality_Run_FK foreign key(Run) references Runs(ID)
/
ALTER TABLE RunLumiQuality ADD CONSTRAINT 
    RunLumiQuality_Lumi_FK foreign key(Lumi) references LumiSection(ID)
/
ALTER TABLE RunLumiQuality ADD CONSTRAINT 
    RunLumiQuality_SubSystem_FK foreign key(SubSystem) references SubSystem(ID) on delete CASCADE
/
ALTER TABLE RunLumiQuality ADD CONSTRAINT 
    RunLumiQuality_DQValue_FK foreign key(DQValue) references QualityValues(ID) on delete CASCADE
/
ALTER TABLE RunLumiQuality ADD CONSTRAINT 
    RunLumiQuality_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE RunLumiQuality ADD CONSTRAINT 
    RunLumiQualityLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE QualityHistory ADD CONSTRAINT 
    QualityHistory_HistoryOf_FK foreign key(HistoryOf) references RunLumiQuality(ID)
/
ALTER TABLE QualityHistory ADD CONSTRAINT 
    QualityHistory_Run_FK foreign key(Run) references Runs(ID)
/
ALTER TABLE QualityHistory ADD CONSTRAINT 
    QualityHistory_Lumi_FK foreign key(Lumi) references LumiSection(ID)
/
ALTER TABLE QualityHistory ADD CONSTRAINT 
    QualityHistory_SubSystem_FK foreign key(SubSystem) references SubSystem(ID) on delete CASCADE
/
ALTER TABLE QualityHistory ADD CONSTRAINT 
    QualityHistory_DQValue_FK foreign key(DQValue) references QualityValues(ID) on delete CASCADE
/
ALTER TABLE QualityHistory ADD CONSTRAINT 
    QualityHistory_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE QualityHistory ADD CONSTRAINT 
    QualityHistoryLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE QFlagAssoc ADD CONSTRAINT 
    QFlagAssoc_ThisFlag_FK foreign key(ThisFlag) references RunLumiQuality(ID) on delete CASCADE
/
ALTER TABLE QFlagAssoc ADD CONSTRAINT 
    QFlagAssoc_ItsAssoc_FK foreign key(ItsAssoc) references RunLumiQuality(ID) on delete CASCADE
/
ALTER TABLE QFlagAssoc ADD CONSTRAINT 
    QFlagAssoc_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE QFlagAssoc ADD CONSTRAINT 
    QFlagAssoc_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE QualityVersion ADD CONSTRAINT 
    QualityVersion_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE QualityVersion ADD CONSTRAINT 
    QualityVersionLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
/

