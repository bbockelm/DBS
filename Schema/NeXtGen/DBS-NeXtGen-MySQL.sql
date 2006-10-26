-- ======================================================================
-- ===   Sql Script for Database : DBS_NEW_ERA
-- ===
-- === Build : 440
-- ======================================================================

use dbs_new_era_v03;
-- ======================================================================

DROP TABLE ProcAlgoMap;
DROP TABLE DatasetParentage;
DROP TABLE ProcDSTier;
DROP TABLE ProcDSRuns;
DROP TABLE FileAlgoMap;
DROP TABLE FileLumi;
DROP TABLE FileParentage;
DROP TABLE FileTier;
DROP TABLE OtherDescription;
DROP TABLE MCDescription;
DROP TABLE TriggerPathDescription;
DROP TABLE PrimaryDatasetDescription;
DROP TABLE ParameterBinding;
DROP TABLE QueryableParameterSet;
DROP TABLE AppExecutable;
DROP TABLE AppVersion;
DROP TABLE AppFamily;
DROP TABLE AlgorithmConfig;
DROP TABLE TimeLog;
DROP TABLE AnalysisDatasetLumi;
DROP TABLE Description;
DROP TABLE Type;
DROP TABLE Status;
DROP TABLE Block;
DROP TABLE LumiSection;
DROP TABLE DataTier;
DROP TABLE Runs;
DROP TABLE PrimaryDataset;
DROP TABLE ProcessedDataset;
DROP TABLE Files;
DROP TABLE AnalysisDataset;
DROP TABLE SchemaVersion;
DROP TABLE PhysicsGroup;
DROP TABLE AssignedRole;
DROP TABLE Role;
DROP TABLE Person;

-- ======================================================================

CREATE TABLE Person
  (
    ID                    int not null auto_increment,
    Name                  varchar(100)                                                      not null,
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
    unique(PersonID,RoleID),

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
    Annotation            varchar(1000)                                                     not null,
    Query                 varchar(1000)                                                     not null,
    ProcessedDS           int                                                               not null,
    Type                  int                                                               not null,
    Status                int                                                               not null,
    Parent                int,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(ProcessedDS) references ProcessedDataset(ID),
    foreign key(Type) references Type(ID) on update SET NULL on delete SET NULL,
    foreign key(Status) references Status(ID) on update SET NULL on delete SET NULL,
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
    NumberOfEvents        smallint                                                          not null,
    FileSize              int                                                               not null,
    Status                int                                                               not null,
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
    foreign key(Status) references Status(ID),
    foreign key(FileType) references Type(ID),
    foreign key(ValidationStatus) references Status(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ProcessedDataset
  (
    ID                    int not null auto_increment,
    Name                  varchar(100)                                                      not null,
    PrimaryDataset        int                                                               not null,
    OpenForWriting        char(1)                                                           not null,
    PhysicsGroup          int,
    Status                int                                                               not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),
    unique(Name,PrimaryDataset),

    foreign key(PrimaryDataset) references PrimaryDataset(ID) on update CASCADE on delete CASCADE,
    foreign key(PhysicsGroup) references PhysicsGroup(ID),
    foreign key(Status) references Status(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID),

    CHECK(OpenForWriting IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE PrimaryDataset
  (
    ID                    int not null auto_increment,
    Annotation            varchar(1000)                                                     not null,
    Name                  varchar(100)                                                      unique not null,
    Description           int                                                               not null,
    StartDate             varchar(100),
    EndDate               varchar(100),
    Type                  int                                                               not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(Description) references PrimaryDatasetDescription(ID),
    foreign key(Type) references Type(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE Runs
  (
    ID                    int not null auto_increment,
    RunNumber             smallint                                                          unique not null,
    NumberOfEvents        smallint,
    NumberOfLumiSections  smallint,
    TotalLuminosity       smallint,
    StoreNumber           smallint,
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
    LumiSectionNumber     smallint                                                          not null,
    RunNumber             int                                                               not null,
    StartEventNumber      smallint,
    EndEventNumber        smallint,
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
    BlockSize             int                                                               not null,
    Name                  varchar(100)                                                      unique not null,
    Dataset               int                                                               not null,
    NumberOfFiles         int                                                               not null,
    OpenForWriting        char(1)                                                           not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

    foreign key(Dataset) references ProcessedDataset(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID),

    CHECK(OpenForWriting IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE Status
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

CREATE TABLE Type
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

    foreign key(AnalysisDataset) references AnalysisDataset(ID),
    foreign key(Lumi) references LumiSection(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE TimeLog
  (
    ID                    int not null auto_increment,
    LogEntry              varchar(1000)                                                     unique not null,
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
    Hash                  varchar(1000)                                                     not null,
    Name                  varchar(100)                                                      unique not null,
    Version               varchar(100)                                                      not null,
    Type                  varchar(100)                                                      not null,
    Annotation            varchar(1000)                                                     not null,
    Content               varchar(1000)                                                     not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

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
    unique(Self,Contains),

    foreign key(Self) references QueryableParameterSet(ID),
    foreign key(Contains) references QueryableParameterSet(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE PrimaryDatasetDescription
  (
    ID                      int,
    TriggerDescriptionID    int                                                               unique,
    MCChannelDescriptionID  int                                                               unique,
    OtherDescriptionID      int                                                               unique,
    CreatedBy               int,
    CreationDate            TIMESTAMP DEFAULT 0,
    LastModifiedBy          int,
    LastModificationDate    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ID),

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
    ThisFile              int                                                               not null,
    ItsParent             int                                                               not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ThisFile,ItsParent),

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

CREATE TABLE FileAlgoMap
  (
    ID                    int not null auto_increment,
    Fileid                int                                                               not null,
    Algorithm             int                                                               not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(Fileid) references Files(ID),
    foreign key(Algorithm) references AlgorithmConfig(ID),
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
    ThisDataset           int                                                               not null,
    ItsParent             int                                                               not null,
    CreatedBy             int,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    primary key(ThisDataset,ItsParent),

    foreign key(ThisDataset) references ProcessedDataset(ID) on update SET NULL on delete SET NULL,
    foreign key(ItsParent) references ProcessedDataset(ID) on update CASCADE on delete CASCADE,
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE ProcAlgoMap
  (
    ID                    int not null auto_increment,
    Dataset               int                                                               not null,
    Algorithm             int                                                               not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             int,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        int,

    primary key(ID),

    foreign key(Dataset) references ProcessedDataset(ID),
    foreign key(Algorithm) references AlgorithmConfig(ID),
    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

