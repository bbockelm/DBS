-- ======================================================================
-- ===   Sql Script for Database : DBS_NEW_ERA
-- ===
-- === Build : 546
-- ======================================================================

drop database dbs_new_era_v10;
create database dbs_new_era_v10;
use dbs_new_era_v10;
-- ======================================================================

CREATE TABLE Person
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(100),
    DistinguishedName     varchar(500)                                                      unique not null,
    ContactInfo           varchar(250),
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE Role
  (
    ID                    BIGINT UNSIGNED,
    RoleName              varchar(100)                                                      unique not null,
    RoleDescription       varchar(500)                                                      not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AssignedRole
  (
    ID                    BIGINT UNSIGNED,
    PersonID              BIGINT UNSIGNED                                                   not null,
    RoleID                BIGINT UNSIGNED                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(PersonID) references Person(ID),
    foreign key(RoleID) references Role(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE PhysicsGroup
  (
    ID                    BIGINT UNSIGNED,
    PhysicsGroupName      varchar(500)                                                      unique not null,
    PhysicsGroupConvener  BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(PhysicsGroupConvener) references Person(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE SchemaVersion
  (
    ID                    BIGINT UNSIGNED,
    SchemaVersion         varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE PrimaryDataset
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(100)                                                      unique not null,
    Annotation            varchar(1000)                                                     not null,
    Description           BIGINT UNSIGNED,
    StartDate             varchar(100),
    EndDate               varchar(100),
    Type                  BIGINT UNSIGNED                                                   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(Description) references PrimaryDatasetDescription(ID),
    foreign key(Type) references PrimaryDSType(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ProcessedDataset
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(500)                                                      not null,
    PrimaryDataset        BIGINT UNSIGNED                                                   not null,
    PhysicsGroup          BIGINT UNSIGNED                                                   not null,
    Status                BIGINT UNSIGNED                                                   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),
    unique(Name,PrimaryDataset),

    foreign key(PrimaryDataset) references PrimaryDataset(ID),
    foreign key(PhysicsGroup) references PhysicsGroup(ID),
    foreign key(Status) references ProcDSStatus(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE Runs
  (
    ID                    BIGINT UNSIGNED,
    RunNumber             int                                                               unique not null,
    NumberOfEvents        BIGINT UNSIGNED                                                   not null,
    NumberOfLumiSections  int                                                               not null,
    TotalLuminosity       BIGINT UNSIGNED                                                   not null,
    StoreNumber           int                                                               not null,
    StartOfRun            varchar(100),
    EndOfRun              varchar(100),
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AnalysisDataset
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(500)                                                      unique not null,
    Annotation            varchar(1000)                                                     not null,
    ProcessedDS           BIGINT UNSIGNED                                                   not null,
    Definition            BIGINT UNSIGNED                                                   not null,
    Type                  BIGINT UNSIGNED                                                   not null,
    Status                BIGINT UNSIGNED                                                   not null,
    Parent                BIGINT UNSIGNED,
    PhysicsGroup          BIGINT UNSIGNED                                                   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(ProcessedDS) references ProcessedDataset(ID),
    foreign key(Definition) references AnalysisDSDef(ID),
    foreign key(Type) references AnalysisDSType(ID),
    foreign key(Status) references AnalysisDSStatus(ID),
    foreign key(Parent) references AnalysisDataset(ID),
    foreign key(PhysicsGroup) references PhysicsGroup(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE Block
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(500)                                                      unique not null,
    Dataset               BIGINT UNSIGNED                                                   not null,
    BlockSize             BIGINT UNSIGNED                                                   not null,
    NumberOfFiles         BIGINT UNSIGNED                                                   not null,
    NumberOfEvents        BIGINT UNSIGNED                                                   not null,
    OpenForWriting        int                                                               not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(Dataset) references ProcessedDataset(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE Files
  (
    ID                    BIGINT UNSIGNED,
    LogicalFileName       varchar(500)                                                      unique not null,
    Dataset               BIGINT UNSIGNED                                                   not null,
    Block                 BIGINT UNSIGNED                                                   not null,
    Checksum              varchar(100)                                                      not null,
    NumberOfEvents        int                                                               not null,
    FileSize              int                                                               not null,
    FileStatus            BIGINT UNSIGNED                                                   not null,
    FileType              BIGINT UNSIGNED                                                   not null,
    ValidationStatus      BIGINT UNSIGNED,
    QueryableMetadata     varchar(1000),
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(Dataset) references ProcessedDataset(ID),
    foreign key(Block) references Block(ID),
    foreign key(FileStatus) references FileStatus(ID),
    foreign key(FileType) references FileType(ID),
    foreign key(ValidationStatus) references AnalysisDSStatus(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE DataTier
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(100)                                                      unique not null,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,

    primary key(ID),

    foreign key(LastModifiedBy) references Person(ID),
    foreign key(CreatedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE LumiSection
  (
    ID                    BIGINT UNSIGNED,
    LumiSectionNumber     BIGINT UNSIGNED                                                   not null,
    RunNumber             BIGINT UNSIGNED                                                   not null,
    StartEventNumber      int                                                               not null,
    EndEventNumber        int                                                               not null,
    LumiStartTime         varchar(100),
    LumiEndTime           varchar(100),
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),
    unique(LumiSectionNumber,RunNumber),

    foreign key(RunNumber) references Runs(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE StorageElement
  (
    ID                    BIGINT UNSIGNED,
    SEName                varchar(500)                                                      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE Branch
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(500)                                                      unique not null,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,

    primary key(ID),

    foreign key(LastModifiedBy) references Person(ID),
    foreign key(CreatedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE Description
  (
    ID                    BIGINT UNSIGNED,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE TimeLog
  (
    ID                    BIGINT UNSIGNED,
    Action                varchar(100)                                                      not null,
    Cause                 varchar(100)                                                      not null,
    Effect                varchar(100)                                                      not null,
    Description           varchar(500)                                                      not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE PrimaryDSType
  (
    ID                    BIGINT UNSIGNED,
    Type                  varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ProcDSStatus
  (
    ID                    BIGINT UNSIGNED,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE SEBlock
  (
    ID                    BIGINT UNSIGNED,
    SEID                  BIGINT UNSIGNED                                                   not null,
    BlockID               BIGINT UNSIGNED                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(SEID,BlockID),

    foreign key(SEID) references StorageElement(ID),
    foreign key(BlockID) references Block(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AlgorithmConfig
  (
    ID                    BIGINT UNSIGNED,
    ExecutableName        BIGINT UNSIGNED                                                   not null,
    ApplicationVersion    BIGINT UNSIGNED                                                   not null,
    ApplicationFamily     BIGINT UNSIGNED                                                   not null,
    ParameterSetID        BIGINT UNSIGNED                                                   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(ExecutableName,ApplicationVersion,ApplicationFamily,ParameterSetID),

    foreign key(ExecutableName) references AppExecutable(ID),
    foreign key(ApplicationVersion) references AppVersion(ID),
    foreign key(ApplicationFamily) references AppFamily(ID),
    foreign key(ParameterSetID) references QueryableParameterSet(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AppFamily
  (
    ID                    BIGINT UNSIGNED,
    FamilyName            varchar(100)                                                      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AppVersion
  (
    ID                    BIGINT UNSIGNED,
    Version               varchar(100)                                                      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AppExecutable
  (
    ID                    BIGINT UNSIGNED,
    ExecutableName        varchar(100)                                                      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE QueryableParameterSet
  (
    ID                    BIGINT UNSIGNED,
    Hash                  varchar(500)                                                      unique not null,
    Name                  varchar(100),
    Version               varchar(100),
    Type                  varchar(100),
    Annotation            varchar(1000),
    Content               TEXT,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ParameterBinding
  (
    ID                    BIGINT UNSIGNED,
    Self                  BIGINT UNSIGNED                                                   not null,
    Contains              BIGINT UNSIGNED                                                   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(Self) references QueryableParameterSet(ID),
    foreign key(Contains) references QueryableParameterSet(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE PrimaryDatasetDescription
  (
    ID                      BIGINT UNSIGNED,
    TriggerDescriptionID    BIGINT UNSIGNED,
    MCChannelDescriptionID  BIGINT UNSIGNED,
    OtherDescriptionID      BIGINT UNSIGNED,
    CreatedBy               BIGINT UNSIGNED,
    CreationDate            TIMESTAMP DEFAULT 0,
    LastModifiedBy          BIGINT UNSIGNED,
    LastModificationDate    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),
    unique(TriggerDescriptionID,MCChannelDescriptionID,OtherDescriptionID),

    foreign key(TriggerDescriptionID) references TriggerPathDescription(ID),
    foreign key(MCChannelDescriptionID) references MCDescription(ID),
    foreign key(OtherDescriptionID) references OtherDescription(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE TriggerPathDescription
  (
    ID                      BIGINT UNSIGNED,
    TriggerPathDescription  varchar(100)                                                      unique not null,
    CreatedBy               BIGINT UNSIGNED,
    CreationDate            TIMESTAMP DEFAULT 0,
    LastModifiedBy          BIGINT UNSIGNED,
    LastModificationDate    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE MCDescription
  (
    ID                    BIGINT UNSIGNED,
    MCChannelDescription  varchar(100)                                                      not null,
    MCProduction          varchar(100),
    MCDecayChain          varchar(100),
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),
    unique(MCChannelDescription,MCProduction,MCDecayChain),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE OtherDescription
  (
    ID                    BIGINT UNSIGNED,
    Description           varchar(100)                                                      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE FileTier
  (
    ID                    BIGINT UNSIGNED,
    Fileid                BIGINT UNSIGNED                                                   not null,
    DataTier              BIGINT UNSIGNED                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Fileid,DataTier),

    foreign key(Fileid) references Files(ID),
    foreign key(DataTier) references DataTier(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE FileParentage
  (
    ID                    BIGINT UNSIGNED,
    ThisFile              BIGINT UNSIGNED                                                   not null,
    ItsParent             BIGINT UNSIGNED                                                   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),
    unique(ThisFile,ItsParent),

    foreign key(ThisFile) references Files(ID),
    foreign key(ItsParent) references Files(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE FileLumi
  (
    ID                    BIGINT UNSIGNED,
    Fileid                BIGINT UNSIGNED                                                   not null,
    Lumi                  BIGINT UNSIGNED                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Fileid,Lumi),

    foreign key(Fileid) references Files(ID),
    foreign key(Lumi) references LumiSection(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE FileAlgo
  (
    ID                    BIGINT UNSIGNED,
    Fileid                BIGINT UNSIGNED                                                   not null,
    Algorithm             BIGINT UNSIGNED                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Fileid,Algorithm),

    foreign key(Fileid) references Files(ID),
    foreign key(Algorithm) references AlgorithmConfig(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE FileStatus
  (
    ID                    BIGINT UNSIGNED,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE FileType
  (
    ID                    BIGINT UNSIGNED,
    Type                  varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE FileBranch
  (
    ID                    BIGINT UNSIGNED,
    Fileid                BIGINT UNSIGNED                                                   not null,
    Branch                BIGINT UNSIGNED                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Fileid,Branch),

    foreign key(Fileid) references Files(ID),
    foreign key(Branch) references Branch(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ProcDSRuns
  (
    ID                    BIGINT UNSIGNED,
    Dataset               BIGINT UNSIGNED                                                   not null,
    Run                   BIGINT UNSIGNED                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Dataset,Run),

    foreign key(Dataset) references ProcessedDataset(ID),
    foreign key(Run) references Runs(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ProcDSTier
  (
    ID                    BIGINT UNSIGNED,
    Dataset               BIGINT UNSIGNED                                                   not null,
    DataTier              BIGINT UNSIGNED                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Dataset,DataTier),

    foreign key(Dataset) references ProcessedDataset(ID),
    foreign key(DataTier) references DataTier(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ProcDSParent
  (
    ID                    BIGINT UNSIGNED,
    ThisDataset           BIGINT UNSIGNED                                                   not null,
    ItsParent             BIGINT UNSIGNED                                                   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),
    unique(ThisDataset,ItsParent),

    foreign key(ThisDataset) references ProcessedDataset(ID),
    foreign key(ItsParent) references ProcessedDataset(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ProcAlgo
  (
    ID                    BIGINT UNSIGNED,
    Dataset               BIGINT UNSIGNED                                                   not null,
    Algorithm             BIGINT UNSIGNED                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(Dataset,Algorithm),

    foreign key(Dataset) references ProcessedDataset(ID),
    foreign key(Algorithm) references AlgorithmConfig(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AnalysisDSType
  (
    ID                    BIGINT UNSIGNED,
    Type                  varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AnalysisDSStatus
  (
    ID                    BIGINT UNSIGNED,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AnalysisDatasetLumi
  (
    ID                    BIGINT UNSIGNED,
    AnalysisDataset       BIGINT UNSIGNED                                                   not null,
    Lumi                  BIGINT UNSIGNED                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(AnalysisDataset,Lumi),

    foreign key(AnalysisDataset) references AnalysisDataset(ID),
    foreign key(Lumi) references LumiSection(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AnalysisDSDef
  (
    ID                    BIGINT UNSIGNED,
    Name                  varchar(500)                                                      unique not null,
    Query                 TEXT                                                              not null,
    Description           varchar(1000)                                                     not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

