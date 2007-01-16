-- ======================================================================
-- ===   Sql Script for Database : DBS_NEW_ERA
-- ===
-- === Build : 552
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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
    unique(Name,PrimaryDataset)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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
    unique(LumiSectionNumber,RunNumber)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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
    unique(SEID,BlockID)
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
    unique(ExecutableName,ApplicationVersion,ApplicationFamily,ParameterSetID)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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

    primary key(ID)
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
    unique(TriggerDescriptionID,MCChannelDescriptionID,OtherDescriptionID)
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

    primary key(ID)
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
    unique(MCChannelDescription,MCProduction,MCDecayChain)
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

    primary key(ID)
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
    unique(Fileid,DataTier)
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
    unique(ThisFile,ItsParent)
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
    unique(Fileid,Lumi)
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
    unique(Fileid,Algorithm)
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

    primary key(ID)
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

    primary key(ID)
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
    unique(Fileid,Branch)
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
    unique(Dataset,Run)
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
    unique(Dataset,DataTier)
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
    unique(ThisDataset,ItsParent)
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
    unique(Dataset,Algorithm)
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

    primary key(ID)
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

    primary key(ID)
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
    unique(AnalysisDataset,Lumi)
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

    primary key(ID)
  ) ENGINE = InnoDB ;

-- ======================================================================

ALTER TABLE Person ADD CONSTRAINT 
    Person_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE Person ADD CONSTRAINT 
    Person_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE Role ADD CONSTRAINT 
    Role_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE Role ADD CONSTRAINT 
    Role_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE AssignedRole ADD CONSTRAINT 
    AssignedRole_PersonID_FK foreign key(PersonID) references Person(ID)
;
ALTER TABLE AssignedRole ADD CONSTRAINT 
    AssignedRole_RoleID_FK foreign key(RoleID) references Role(ID)
;
ALTER TABLE AssignedRole ADD CONSTRAINT 
    AssignedRole_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE AssignedRole ADD CONSTRAINT 
    AssignedRole_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE PhysicsGroup ADD CONSTRAINT 
    PhysicsGroupPhysicsGroupCon_FK foreign key(PhysicsGroupConvener) references Person(ID)
;
ALTER TABLE PhysicsGroup ADD CONSTRAINT 
    PhysicsGroup_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE PhysicsGroup ADD CONSTRAINT 
    PhysicsGroup_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE SchemaVersion ADD CONSTRAINT 
    SchemaVersion_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE SchemaVersion ADD CONSTRAINT 
    SchemaVersionLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE PrimaryDataset ADD CONSTRAINT 
    PrimaryDataset_Description_FK foreign key(Description) references PrimaryDatasetDescription(ID)
;
ALTER TABLE PrimaryDataset ADD CONSTRAINT 
    PrimaryDataset_Type_FK foreign key(Type) references PrimaryDSType(ID)
;
ALTER TABLE PrimaryDataset ADD CONSTRAINT 
    PrimaryDataset_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE PrimaryDataset ADD CONSTRAINT 
    PrimaryDatasetLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDatasetPrimaryData_FK foreign key(PrimaryDataset) references PrimaryDataset(ID)
;
ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDatasetPhysicsGrou_FK foreign key(PhysicsGroup) references PhysicsGroup(ID)
;
ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDataset_Status_FK foreign key(Status) references ProcDSStatus(ID)
;
ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDataset_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE ProcessedDataset ADD CONSTRAINT 
    ProcessedDatasetLastModifie_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE Runs ADD CONSTRAINT 
    Runs_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE Runs ADD CONSTRAINT 
    Runs_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_ProcessedDS_FK foreign key(ProcessedDS) references ProcessedDataset(ID)
;
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Definition_FK foreign key(Definition) references AnalysisDSDef(ID)
;
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Type_FK foreign key(Type) references AnalysisDSType(ID)
;
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Status_FK foreign key(Status) references AnalysisDSStatus(ID)
;
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Parent_FK foreign key(Parent) references AnalysisDataset(ID)
;
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDatasetPhysicsGroup_FK foreign key(PhysicsGroup) references PhysicsGroup(ID)
;
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDatasetLastModified_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE Block ADD CONSTRAINT 
    Block_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
;
ALTER TABLE Block ADD CONSTRAINT 
    Block_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE Block ADD CONSTRAINT 
    Block_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE Files ADD CONSTRAINT 
    Files_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
;
ALTER TABLE Files ADD CONSTRAINT 
    Files_Block_FK foreign key(Block) references Block(ID)
;
ALTER TABLE Files ADD CONSTRAINT 
    Files_FileStatus_FK foreign key(FileStatus) references FileStatus(ID)
;
ALTER TABLE Files ADD CONSTRAINT 
    Files_FileType_FK foreign key(FileType) references FileType(ID)
;
ALTER TABLE Files ADD CONSTRAINT 
    Files_ValidationStatus_FK foreign key(ValidationStatus) references AnalysisDSStatus(ID)
;
ALTER TABLE Files ADD CONSTRAINT 
    Files_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE Files ADD CONSTRAINT 
    Files_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE DataTier ADD CONSTRAINT 
    DataTier_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;
ALTER TABLE DataTier ADD CONSTRAINT 
    DataTier_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;

ALTER TABLE LumiSection ADD CONSTRAINT 
    LumiSection_RunNumber_FK foreign key(RunNumber) references Runs(ID)
;
ALTER TABLE LumiSection ADD CONSTRAINT 
    LumiSection_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE LumiSection ADD CONSTRAINT 
    LumiSection_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE StorageElement ADD CONSTRAINT 
    StorageElement_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE StorageElement ADD CONSTRAINT 
    StorageElementLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE Branch ADD CONSTRAINT 
    Branch_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;
ALTER TABLE Branch ADD CONSTRAINT 
    Branch_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;

ALTER TABLE Description ADD CONSTRAINT 
    Description_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE Description ADD CONSTRAINT 
    Description_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE TimeLog ADD CONSTRAINT 
    TimeLog_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE TimeLog ADD CONSTRAINT 
    TimeLog_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE PrimaryDSType ADD CONSTRAINT 
    PrimaryDSType_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE PrimaryDSType ADD CONSTRAINT 
    PrimaryDSTypeLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE ProcDSStatus ADD CONSTRAINT 
    ProcDSStatus_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE ProcDSStatus ADD CONSTRAINT 
    ProcDSStatus_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE SEBlock ADD CONSTRAINT 
    SEBlock_SEID_FK foreign key(SEID) references StorageElement(ID)
;
ALTER TABLE SEBlock ADD CONSTRAINT 
    SEBlock_BlockID_FK foreign key(BlockID) references Block(ID)
;
ALTER TABLE SEBlock ADD CONSTRAINT 
    SEBlock_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE SEBlock ADD CONSTRAINT 
    SEBlock_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE AlgorithmConfig ADD CONSTRAINT 
    AlgorithmConfigExecutableNa_FK foreign key(ExecutableName) references AppExecutable(ID)
;
ALTER TABLE AlgorithmConfig ADD CONSTRAINT 
    AlgorithmConfigApplicationV_FK foreign key(ApplicationVersion) references AppVersion(ID)
;
ALTER TABLE AlgorithmConfig ADD CONSTRAINT 
    AlgorithmConfigApplicationF_FK foreign key(ApplicationFamily) references AppFamily(ID)
;
ALTER TABLE AlgorithmConfig ADD CONSTRAINT 
    AlgorithmConfigParameterSet_FK foreign key(ParameterSetID) references QueryableParameterSet(ID)
;
ALTER TABLE AlgorithmConfig ADD CONSTRAINT 
    AlgorithmConfig_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE AlgorithmConfig ADD CONSTRAINT 
    AlgorithmConfigLastModified_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE AppFamily ADD CONSTRAINT 
    AppFamily_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE AppFamily ADD CONSTRAINT 
    AppFamily_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE AppVersion ADD CONSTRAINT 
    AppVersion_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE AppVersion ADD CONSTRAINT 
    AppVersion_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE AppExecutable ADD CONSTRAINT 
    AppExecutable_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE AppExecutable ADD CONSTRAINT 
    AppExecutableLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE QueryableParameterSet ADD CONSTRAINT 
    QueryableParameterSetCreate_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE QueryableParameterSet ADD CONSTRAINT 
    QueryableParameterSetLastMo_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE ParameterBinding ADD CONSTRAINT 
    ParameterBinding_Self_FK foreign key(Self) references QueryableParameterSet(ID)
;
ALTER TABLE ParameterBinding ADD CONSTRAINT 
    ParameterBinding_Contains_FK foreign key(Contains) references QueryableParameterSet(ID)
;
ALTER TABLE ParameterBinding ADD CONSTRAINT 
    ParameterBinding_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE ParameterBinding ADD CONSTRAINT 
    ParameterBindingLastModifie_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE PrimaryDatasetDescription ADD CONSTRAINT 
    PrimaryDatasetDescriptionTr_FK foreign key(TriggerDescriptionID) references TriggerPathDescription(ID)
;
ALTER TABLE PrimaryDatasetDescription ADD CONSTRAINT 
    PrimaryDatasetDescriptionMC_FK foreign key(MCChannelDescriptionID) references MCDescription(ID)
;
ALTER TABLE PrimaryDatasetDescription ADD CONSTRAINT 
    PrimaryDatasetDescriptionOt_FK foreign key(OtherDescriptionID) references OtherDescription(ID)
;
ALTER TABLE PrimaryDatasetDescription ADD CONSTRAINT 
    PrimaryDatasetDescriptionCr_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE PrimaryDatasetDescription ADD CONSTRAINT 
    PrimaryDatasetDescriptionLa_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE TriggerPathDescription ADD CONSTRAINT 
    TriggerPathDescriptionCreat_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE TriggerPathDescription ADD CONSTRAINT 
    TriggerPathDescriptionLastM_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE MCDescription ADD CONSTRAINT 
    MCDescription_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE MCDescription ADD CONSTRAINT 
    MCDescriptionLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE OtherDescription ADD CONSTRAINT 
    OtherDescription_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE OtherDescription ADD CONSTRAINT 
    OtherDescriptionLastModifie_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE FileTier ADD CONSTRAINT 
    FileTier_Fileid_FK foreign key(Fileid) references Files(ID)
;
ALTER TABLE FileTier ADD CONSTRAINT 
    FileTier_DataTier_FK foreign key(DataTier) references DataTier(ID)
;
ALTER TABLE FileTier ADD CONSTRAINT 
    FileTier_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE FileTier ADD CONSTRAINT 
    FileTier_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE FileParentage ADD CONSTRAINT 
    FileParentage_ThisFile_FK foreign key(ThisFile) references Files(ID)
;
ALTER TABLE FileParentage ADD CONSTRAINT 
    FileParentage_ItsParent_FK foreign key(ItsParent) references Files(ID)
;
ALTER TABLE FileParentage ADD CONSTRAINT 
    FileParentage_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE FileParentage ADD CONSTRAINT 
    FileParentageLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE FileLumi ADD CONSTRAINT 
    FileLumi_Fileid_FK foreign key(Fileid) references Files(ID)
;
ALTER TABLE FileLumi ADD CONSTRAINT 
    FileLumi_Lumi_FK foreign key(Lumi) references LumiSection(ID)
;
ALTER TABLE FileLumi ADD CONSTRAINT 
    FileLumi_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE FileLumi ADD CONSTRAINT 
    FileLumi_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE FileAlgo ADD CONSTRAINT 
    FileAlgo_Fileid_FK foreign key(Fileid) references Files(ID)
;
ALTER TABLE FileAlgo ADD CONSTRAINT 
    FileAlgo_Algorithm_FK foreign key(Algorithm) references AlgorithmConfig(ID)
;
ALTER TABLE FileAlgo ADD CONSTRAINT 
    FileAlgo_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE FileAlgo ADD CONSTRAINT 
    FileAlgo_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE FileStatus ADD CONSTRAINT 
    FileStatus_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE FileStatus ADD CONSTRAINT 
    FileStatus_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE FileType ADD CONSTRAINT 
    FileType_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE FileType ADD CONSTRAINT 
    FileType_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE FileBranch ADD CONSTRAINT 
    FileBranch_Fileid_FK foreign key(Fileid) references Files(ID)
;
ALTER TABLE FileBranch ADD CONSTRAINT 
    FileBranch_Branch_FK foreign key(Branch) references Branch(ID)
;
ALTER TABLE FileBranch ADD CONSTRAINT 
    FileBranch_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE FileBranch ADD CONSTRAINT 
    FileBranch_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE ProcDSRuns ADD CONSTRAINT 
    ProcDSRuns_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
;
ALTER TABLE ProcDSRuns ADD CONSTRAINT 
    ProcDSRuns_Run_FK foreign key(Run) references Runs(ID)
;
ALTER TABLE ProcDSRuns ADD CONSTRAINT 
    ProcDSRuns_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE ProcDSRuns ADD CONSTRAINT 
    ProcDSRuns_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE ProcDSTier ADD CONSTRAINT 
    ProcDSTier_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
;
ALTER TABLE ProcDSTier ADD CONSTRAINT 
    ProcDSTier_DataTier_FK foreign key(DataTier) references DataTier(ID)
;
ALTER TABLE ProcDSTier ADD CONSTRAINT 
    ProcDSTier_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE ProcDSTier ADD CONSTRAINT 
    ProcDSTier_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE ProcDSParent ADD CONSTRAINT 
    ProcDSParent_ThisDataset_FK foreign key(ThisDataset) references ProcessedDataset(ID)
;
ALTER TABLE ProcDSParent ADD CONSTRAINT 
    ProcDSParent_ItsParent_FK foreign key(ItsParent) references ProcessedDataset(ID)
;
ALTER TABLE ProcDSParent ADD CONSTRAINT 
    ProcDSParent_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE ProcDSParent ADD CONSTRAINT 
    ProcDSParent_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE ProcAlgo ADD CONSTRAINT 
    ProcAlgo_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
;
ALTER TABLE ProcAlgo ADD CONSTRAINT 
    ProcAlgo_Algorithm_FK foreign key(Algorithm) references AlgorithmConfig(ID)
;
ALTER TABLE ProcAlgo ADD CONSTRAINT 
    ProcAlgo_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE ProcAlgo ADD CONSTRAINT 
    ProcAlgo_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE AnalysisDSType ADD CONSTRAINT 
    AnalysisDSType_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE AnalysisDSType ADD CONSTRAINT 
    AnalysisDSTypeLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE AnalysisDSStatus ADD CONSTRAINT 
    AnalysisDSStatus_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE AnalysisDSStatus ADD CONSTRAINT 
    AnalysisDSStatusLastModifie_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE AnalysisDatasetLumi ADD CONSTRAINT 
    AnalysisDatasetLumiAnalysis_FK foreign key(AnalysisDataset) references AnalysisDataset(ID)
;
ALTER TABLE AnalysisDatasetLumi ADD CONSTRAINT 
    AnalysisDatasetLumi_Lumi_FK foreign key(Lumi) references LumiSection(ID)
;
ALTER TABLE AnalysisDatasetLumi ADD CONSTRAINT 
    AnalysisDatasetLumiCreatedB_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE AnalysisDatasetLumi ADD CONSTRAINT 
    AnalysisDatasetLumiLastModi_FK foreign key(LastModifiedBy) references Person(ID)
;

ALTER TABLE AnalysisDSDef ADD CONSTRAINT 
    AnalysisDSDef_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
ALTER TABLE AnalysisDSDef ADD CONSTRAINT 
    AnalysisDSDefLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
;


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
