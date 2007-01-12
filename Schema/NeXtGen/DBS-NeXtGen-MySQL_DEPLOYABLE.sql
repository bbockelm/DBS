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
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE Role
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    RoleName              varchar(100)                                                      unique not null,
    RoleDescription       varchar(500)                                                      not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE AssignedRole
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE PhysicsGroup
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE SchemaVersion
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    SchemaVersion         varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE PrimaryDataset
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE ProcessedDataset
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE Runs
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE AnalysisDataset
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE Block
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE Files
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE DataTier
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    Name                  varchar(100)                                                      unique not null,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,

    primary key(ID),

    foreign key(LastModifiedBy) references Person(ID),
    foreign key(CreatedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE LumiSection
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE StorageElement
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    SEName                varchar(500)                                                      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE Branch
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    Name                  varchar(500)                                                      unique not null,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,

    primary key(ID),

    foreign key(LastModifiedBy) references Person(ID),
    foreign key(CreatedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE Description
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE TimeLog
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE PrimaryDSType
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    Type                  varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE ProcDSStatus
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE SEBlock
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE AlgorithmConfig
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE AppFamily
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    FamilyName            varchar(100)                                                      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE AppVersion
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    Version               varchar(100)                                                      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE AppExecutable
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    ExecutableName        varchar(100)                                                      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE QueryableParameterSet
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE ParameterBinding
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

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
  ) ENGINE = InnoDB ;

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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE MCDescription
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE OtherDescription
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    Description           varchar(100)                                                      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE FileTier
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE FileParentage
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE FileLumi
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE FileAlgo
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE FileStatus
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE FileType
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    Type                  varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE FileBranch
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE ProcDSRuns
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE ProcDSTier
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE ProcDSParent
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE ProcAlgo
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE AnalysisDSType
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    Type                  varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE AnalysisDSStatus
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE AnalysisDatasetLumi
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE AnalysisDSDef
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
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
  ) ENGINE = InnoDB ;

-- ======================================================================


-- ======================================================================
-- Initialize status tables There can be better ways to do it ( laters ) 
-- ======================================================================

INSERT INTO SchemaVersion(SchemaVersion, CreationDate) values ('v00_00_04', NOW());
INSERT INTO AnalysisDSStatus (Status, CreationDate) VALUES ('NEW', NOW());
INSERT INTO FileStatus (Status, CreationDate) VALUES ('VALID', NOW()), ('INVALID', NOW()), ('MERGED', NOW()), ('PROMOTED', NOW());
INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('VALID', NOW()), ('INVALID', NOW()), ('PROMOTED', NOW());
INSERT INTO FileType(Type, CreationDate) VALUES ('EVD', NOW()) ;
INSERT INTO AnalysisDSType(Type, CreationDate) VALUES ('TEST', NOW());
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('TEST', NOW());
commit;
