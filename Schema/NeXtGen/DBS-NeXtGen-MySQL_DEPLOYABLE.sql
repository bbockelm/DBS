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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
    Name                  varchar(100)                                                      not null,
    PrimaryDataset        int                                                               not null,
    OpenForWriting        char(1)                                                           not null,
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
    foreign key(LastModifiedBy) references Person(ID),

    CHECK(OpenForWriting IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE PrimaryDataset
  (
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
    Name                  varchar(100)                                                      unique not null,
    Dataset               int                                                               not null,
    BlockSize             int                                                               not null,
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

CREATE TABLE AnalysisDSStatus
  (
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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
    ID                    int,
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

-- ======================================================================

insert into SchemaVersion(SchemaVersion, CreationDate) values ('v00_00_02', NOW());
commit;
-- ======================================================================

