-- ======================================================================
-- ===   Sql Script for Database : DBS_NEW_ERA
-- ===
-- === Build : 477
-- ======================================================================

drop database dbs_new_era_v04;
create database dbs_new_era_v04;
use dbs_new_era_v04;
-- ======================================================================

CREATE TABLE Person
  (
    ID                    int not null auto_increment,
    Name                  varchar(100) ,
    DistinguishedName     varchar(100)                                                      not null,
    ContactInfo           varchar(100),
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),
    unique(Name,DistinguishedName),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE Role
  (
    ID                    int not null auto_increment,
    RoleName              varchar(100)                                                      unique not null,
    RoleDescription       varchar(100)                                                      not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AssignedRole
  (
    ID                    int not null auto_increment,
    PersonID              int                                                               not null,
    RoleID                int                                                               not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(PersonID) references Person(ID) on update CASCADE on delete CASCADE,
    foreign key(RoleID) references Role(ID) on update CASCADE on delete CASCADE,
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE PhysicsGroup
  (
    ID                    int not null auto_increment,
    PhysicsGroupName      varchar(100)                                                      unique not null,
    PhysicsGroupConvener  int,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(PhysicsGroupConvener) references Person(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE SchemaVersion
  (
    ID                    int not null auto_increment,
    SchemaVersion         varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AnalysisDataset
  (
    ID                    int not null auto_increment,
    Name                  varchar(100)                                                      unique not null,
    Annotation            varchar(1000)                                                     not null,
    Query                 varchar(1000)                                                     not null,
    ProcessedDS           int                                                               not null,
    Type                  int                                                               not null,
    PhysicsGroup          int                                                               not null,
    Status                int                                                               not null,
    Parent                int,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(ProcessedDS) references ProcessedDataset(ID),
    foreign key(Type) references AnalysisDSType(ID) on update SET NULL on delete SET NULL,
    foreign key(PhysicsGroup) references PhysicsGroup(ID),
    foreign key(Status) references AnalysisDSStatus(ID) on update SET NULL on delete SET NULL,
    foreign key(Parent) references AnalysisDataset(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE Files
  (
    ID                    int not null auto_increment,
    LogicalFileName       varchar(500)                                                      unique not null,
    Dataset               int                                                               not null,
    Block                 int                                                               not null,
    Checksum              varchar(100)                                                      not null,
    NumberOfEvents        int                                                               not null,
    FileSize              int                                                               not null,
    FileStatus            int                                                               not null,
    FileType              int                                                               not null,
    ValidationStatus      int,
    QueryableMetadata     varchar(1000),
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
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

CREATE TABLE ProcessedDataset
  (
    ID                    int not null auto_increment,
    Name                  varchar(100)                                                      not null,
    PrimaryDataset        int                                                               not null,
    PhysicsGroup          int                                                               not null,
    Status                int                                                               not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),
    unique(Name,PrimaryDataset),

    foreign key(PrimaryDataset) references PrimaryDataset(ID) on update CASCADE on delete CASCADE,
    foreign key(PhysicsGroup) references PhysicsGroup(ID),
    foreign key(Status) references ProcDSStatus(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)

  );

-- ======================================================================

CREATE TABLE PrimaryDataset
  (
    ID                    int not null auto_increment,
    Name                  varchar(100)                                                      unique not null,
    Annotation            varchar(1000)                                                     not null,
    Description           int,
    StartDate             varchar(100),
    EndDate               varchar(100),
    Type                  int                                                               not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(Description) references PrimaryDatasetDescription(ID),
    foreign key(Type) references PrimaryDSType(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE Runs
  (
    ID                    int not null auto_increment,
    RunNumber             int                                                               unique not null,
    NumberOfEvents        int                                                               not null,
    NumberOfLumiSections  int                                                               not null,
    TotalLuminosity       int                                                               not null,
    StoreNumber           int                                                               not null,
    StartOfRun            varchar(100),
    EndOfRun              varchar(100),
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE DataTier
  (
    ID                    int not null auto_increment,
    Name                  varchar(100)                                                      unique not null,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,

    primary key(ID),

    foreign key(LastModifiedBy) references Person(ID),
    foreign key(CreatedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE LumiSection
  (
    ID                    int not null auto_increment,
    LumiSectionNumber     int                                                               not null,
    RunNumber             int                                                               not null,
    StartEventNumber      int                                                               not null,
    EndEventNumber        int                                                               not null,
    LumiStartTime         varchar(100),
    LumiEndTime           varchar(100),
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),
    unique(LumiSectionNumber,RunNumber),

    foreign key(RunNumber) references Runs(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE Block
  (
    ID                    int not null auto_increment,
    Name                  varchar(250)                                                      unique not null,
    Dataset               int                                                               not null,
    BlockSize             int                                                               not null,
    NumberOfFiles         int                                                               not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(Dataset) references ProcessedDataset(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)

  );

-- ======================================================================

CREATE TABLE AnalysisDSStatus
  (
    ID                    int not null auto_increment,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AnalysisDSType
  (
    ID                    int not null auto_increment,
    Type                  varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE Description
  (
    ID                    int not null auto_increment,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AnalysisDatasetLumi
  (
    ID                    int not null auto_increment,
    AnalysisDataset       int                                                               not null,
    Lumi                  int                                                               not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),
    unique(AnalysisDataset,Lumi),

    foreign key(AnalysisDataset) references AnalysisDataset(ID),
    foreign key(Lumi) references LumiSection(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE TimeLog
  (
    ID                    int not null auto_increment,
    Action                varchar(100)                                                      not null,
    Cause                 varchar(100)                                                      not null,
    Effect                varchar(100)                                                      not null,
    Description           varchar(100)                                                      not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE PrimaryDSType
  (
    ID                    int not null auto_increment,
    Type                  varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ProcDSStatus
  (
    ID                    int not null auto_increment,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AlgorithmConfig
  (
    ID                    int not null auto_increment,
    ExecutableName        int                                                               not null,
    ApplicationVersion    int                                                               not null,
    ApplicationFamily     int                                                               not null,
    ParameterSetID        int                                                               not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

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
    ID                    int not null auto_increment,
    FamilyName            varchar(100)                                                      unique not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AppVersion
  (
    ID                    int not null auto_increment,
    Version               varchar(100)                                                      unique not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AppExecutable
  (
    ID                    int not null auto_increment,
    ExecutableName        varchar(100)                                                      unique not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE QueryableParameterSet
  (
    ID                    int not null auto_increment,
    Hash                  varchar(100)                                                      not null,
    Name                  varchar(100)                                                      not null,
    Version               varchar(100)                                                      not null,
    Type                  varchar(100)                                                      not null,
    Annotation            varchar(1000)                                                     not null,
    Content               varchar(1000)                                                     not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),
    unique(Hash,Name,Version),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ParameterBinding
  (
    ID                    int not null auto_increment,
    Self                  int                                                               not null,
    Contains              int                                                               not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
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
    ID                      int,
    TriggerDescriptionID    int,
    MCChannelDescriptionID  int,
    OtherDescriptionID      int,
    CreatedBy               int,
    CreationDate            TIMESTAMP DEFAULT 0,
    LastModifiedBy          int,
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
    ID                      int,
    TriggerPathDescription  varchar(100)                                                      unique not null,
    CreatedBy               int,
    CreationDate            TIMESTAMP DEFAULT 0,
    LastModifiedBy          int,
    LastModificationDate    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE MCDescription
  (
    ID                    int not null auto_increment,
    MCChannelDescription  varchar(100)                                                      not null,
    MCProduction          varchar(100),
    MCDecayChain          varchar(100),
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),
    unique(MCChannelDescription,MCProduction,MCDecayChain),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE OtherDescription
  (
    ID                    int not null auto_increment,
    Description           varchar(100)                                                      unique not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE FileTier
  (
    ID                    int not null auto_increment,
    Fileid                int                                                               not null,
    DataTier              int                                                               not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

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
    ID                    int not null auto_increment,
    ThisFile              int                                                               not null,
    ItsParent             int                                                               not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),
    unique(ThisFile,ItsParent),

    foreign key(ThisFile) references Files(ID) on update SET NULL on delete SET NULL,
    foreign key(ItsParent) references Files(ID) on update CASCADE on delete CASCADE,
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE FileLumi
  (
    ID                    int not null auto_increment,
    Fileid                int                                                               not null,
    Lumi                  int                                                               not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

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
    ID                    int not null auto_increment,
    Fileid                int                                                               not null,
    Algorithm             int                                                               not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

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
    ID                    int not null auto_increment,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE FileType
  (
    ID                    int not null auto_increment,
    Type                  varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ProcDSRuns
  (
    ID                    int not null auto_increment,
    Dataset               int                                                               not null,
    Run                   int                                                               not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

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
    ID                    int not null auto_increment,
    Dataset               int                                                               not null,
    DataTier              int                                                               not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),
    unique(Dataset,DataTier),

    foreign key(Dataset) references ProcessedDataset(ID),
    foreign key(DataTier) references DataTier(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE DatasetParentage
  (
    ID                    int not null auto_increment,
    ThisDataset           int                                                               not null,
    ItsParent             int                                                               not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),
    unique(ThisDataset,ItsParent),

    foreign key(ThisDataset) references ProcessedDataset(ID) on update SET NULL on delete SET NULL,
    foreign key(ItsParent) references ProcessedDataset(ID) on update CASCADE on delete CASCADE,
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ProcAlgo
  (
    ID                    int not null auto_increment,
    Dataset               int                                                               not null,
    Algorithm             int                                                               not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),
    unique(Dataset,Algorithm),

    foreign key(Dataset) references ProcessedDataset(ID),
    foreign key(Algorithm) references AlgorithmConfig(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================= TRIGGERS FOR CreationDate

CREATE TRIGGER TR_TS_person BEFORE INSERT ON Person
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_algorithmconfig BEFORE INSERT ON AlgorithmConfig
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_role BEFORE INSERT ON Role
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_assignedrole BEFORE INSERT ON AssignedRole
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_physicsgroup BEFORE INSERT ON PhysicsGroup
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_schemaversion BEFORE INSERT ON SchemaVersion
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_runs BEFORE INSERT ON Runs
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_datatier BEFORE INSERT ON DataTier
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_lumisection BEFORE INSERT ON LumiSection
FOR EACH ROW SET NEW.CreationDate = NOW();
--CREATE TRIGGER TR_TS_status BEFORE INSERT ON Status
--FOR EACH ROW SET NEW.CreationDate = NOW();
--CREATE TRIGGER TR_TS_type BEFORE INSERT ON Type
--FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_description BEFORE INSERT ON Description
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_timelog BEFORE INSERT ON TimeLog
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_analysisdsstatus BEFORE INSERT ON AnalysisDSStatus
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_analysisdstype BEFORE INSERT ON AnalysisDSType
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_primarydstype BEFORE INSERT ON PrimaryDSType
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_appfamily BEFORE INSERT ON AppFamily
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_appversion BEFORE INSERT ON AppVersion
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_appexecutable BEFORE INSERT ON AppExecutable
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_queryableparameterset BEFORE INSERT ON QueryableParameterSet
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_parameterbinding BEFORE INSERT ON ParameterBinding
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_triggerpathdescription BEFORE INSERT ON TriggerPathDescription
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_mcdescription BEFORE INSERT ON MCDescription
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_otherdescription BEFORE INSERT ON OtherDescription
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_filestatus BEFORE INSERT ON FileStatus
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_filetype BEFORE INSERT ON FileType
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_procdsstatus BEFORE INSERT ON ProcDSStatus
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_primarydataset BEFORE INSERT ON PrimaryDataset
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_processeddataset BEFORE INSERT ON ProcessedDataset
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_block BEFORE INSERT ON Block
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_procdsruns BEFORE INSERT ON ProcDSRuns
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_procdstier BEFORE INSERT ON ProcDSTier
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_datasetparentage BEFORE INSERT ON DatasetParentage
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_procalgo BEFORE INSERT ON ProcAlgo
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_analysisdataset BEFORE INSERT ON AnalysisDataset
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_files BEFORE INSERT ON Files
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_analysisdatasetlumi BEFORE INSERT ON AnalysisDatasetLumi
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_filetier BEFORE INSERT ON FileTier
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_fileparentage BEFORE INSERT ON FileParentage
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_filelumi BEFORE INSERT ON FileLumi
FOR EACH ROW SET NEW.CreationDate = NOW();
CREATE TRIGGER TR_TS_filealgo BEFORE INSERT ON FileAlgo
FOR EACH ROW SET NEW.CreationDate = NOW();

-- ======================================================================

insert into SchemaVersion(SchemaVersion, CreationDate) values ('v00_00_02', NOW());
commit;
-- ======================================================================
-- Initialize status tables There are better ways to do it, get to that laters

INSERT INTO AnalysisDSStatus (Status, CreationDate) VALUES ('NEW', NOW());

INSERT INTO FileStatus (Status, CreationDate) VALUES ('VALID', NOW()), ('INVALID', NOW()), ('MERGED', NOW()), ('PROMOTED', NOW());

INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('VALID', NOW()), ('INVALID', NOW()), ('PROMOTED', NOW());

INSERT INTO FileType(Type, CreationDate) VALUES ('EVD', NOW()) ;

INSERT INTO AnalysisDSType(Type, CreationDate) VALUES ('TEST', NOW());

INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('TEST', NOW());


