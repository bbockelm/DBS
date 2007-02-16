REM ======================================================================
REM ===   Sql Script for Database : DBS_NEW_ERA
REM ===
REM === Build : 614
REM ======================================================================

CREATE TABLE Person
  (
    ID                    integer,
    Name                  varchar(100),
    DistinguishedName     varchar(500)                                                      unique not null,
    ContactInfo           varchar(250),
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE Role
  (
    ID                    integer,
    RoleName              varchar(100)                                                      unique not null,
    RoleDescription       varchar(500)                                                      not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AssignedRole
  (
    ID                    integer,
    PersonID              integer                                                   not null,
    RoleID                integer                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE PhysicsGroup
  (
    ID                    integer,
    PhysicsGroupName      varchar(500)                                                      unique not null,
    PhysicsGroupConvener  integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE SchemaVersion
  (
    ID                    integer,
    SchemaVersion         varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE Runs
  (
    ID                    integer,
    RunNumber             integer                                                   unique not null,
    NumberOfEvents        integer                                                   not null,
    NumberOfLumiSections  integer                                                   not null,
    TotalLuminosity       integer                                                   not null,
    StoreNumber           integer                                                   not null,
    StartOfRun            varchar(100),
    EndOfRun              varchar(100),
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE DataTier
  (
    ID                    integer,
    Name                  varchar(100)                                                      unique not null,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE LumiSection
  (
    ID                    integer,
    LumiSectionNumber     integer                                                   not null,
    RunNumber             integer                                                   not null,
    StartEventNumber      integer                                                   not null,
    EndEventNumber        integer                                                   not null,
    LumiStartTime         varchar(100),
    LumiEndTime           varchar(100),
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID),
    unique(LumiSectionNumber,RunNumber)
  );

REM ======================================================================

CREATE TABLE StorageElement
  (
    ID                    integer,
    SEName                varchar(500)                                                      unique not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE Branch
  (
    ID                    integer,
    Name                  varchar(500)                                                      unique not null,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE Description
  (
    ID                    integer,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE TimeLog
  (
    ID                    integer,
    Action                varchar(100)                                                      not null,
    Cause                 varchar(100)                                                      not null,
    Effect                varchar(100)                                                      not null,
    Description           varchar(500)                                                      not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE PrimaryDSType
  (
    ID                    integer,
    Type                  varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE ProcDSStatus
  (
    ID                    integer,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AppFamily
  (
    ID                    integer,
    FamilyName            varchar(100)                                                      unique not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AppVersion
  (
    ID                    integer,
    Version               varchar(100)                                                      unique not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AppExecutable
  (
    ID                    integer,
    ExecutableName        varchar(100)                                                      unique not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE QueryableParameterSet
  (
    ID                    integer,
    Hash                  varchar(700)                                                      unique not null,
    Name                  varchar(1000),
    Version               varchar(100),
    Type                  varchar(100),
    Annotation            varchar(1000),
    Content               CLOB,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE ParameterBinding
  (
    ID                    integer,
    Self                  integer                                                   not null,
    Contains              integer                                                   not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE TriggerPathDescription
  (
    ID                      integer,
    TriggerPathDescription  varchar(100)                                                      unique not null,
    CreatedBy               integer,
    CreationDate            TIMESTAMP DEFAULT 0,
    LastModifiedBy          integer,
    LastModificationDate    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE MCDescription
  (
    ID                    integer,
    MCChannelDescription  varchar(100)                                                      not null,
    MCProduction          varchar(100),
    MCDecayChain          varchar(100),
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID),
    unique(MCChannelDescription,MCProduction,MCDecayChain)
  );

REM ======================================================================

CREATE TABLE OtherDescription
  (
    ID                    integer,
    Description           varchar(100)                                                      unique not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE FileStatus
  (
    ID                    integer,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE FileType
  (
    ID                    integer,
    Type                  varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AnalysisDSType
  (
    ID                    integer,
    Type                  varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AnalysisDSStatus
  (
    ID                    integer,
    Status                varchar(100)                                                      unique not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AnalysisDSDef
  (
    ID                    integer,
    Name                  varchar(700)                                                      unique not null,
    LumiSections          CLOB,
    LumiSectionRanges     CLOB,
    Runs                  CLOB,
    RunsRanges            CLOB,
    Algorithms            varchar(1000),
    LFNs                  CLOB,
    Path                  varchar(1000),
    Tiers                 varchar(250),
    AnalysisDatasets      CLOB,
    UserCut               CLOB,
    Description           CLOB,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE AlgorithmConfig
  (
    ID                    integer,
    ExecutableName        integer                                                   not null,
    ApplicationVersion    integer                                                   not null,
    ApplicationFamily     integer                                                   not null,
    ParameterSetID        integer                                                   not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID),
    unique(ExecutableName,ApplicationVersion,ApplicationFamily,ParameterSetID)
  );

REM ======================================================================

CREATE TABLE PrimaryDatasetDescription
  (
    ID                      integer,
    TriggerDescriptionID    integer,
    MCChannelDescriptionID  integer,
    OtherDescriptionID      integer,
    CreatedBy               integer,
    CreationDate            TIMESTAMP DEFAULT 0,
    LastModifiedBy          integer,
    LastModificationDate    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID),
    unique(TriggerDescriptionID,MCChannelDescriptionID,OtherDescriptionID)
  );

REM ======================================================================

CREATE TABLE PrimaryDataset
  (
    ID                    integer,
    Name                  varchar(100)                                                      unique not null,
    Annotation            varchar(1000)                                                     not null,
    Description           integer,
    StartDate             varchar(100),
    EndDate               varchar(100),
    Type                  integer                                                   not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE ProcessedDataset
  (
    ID                    integer,
    Name                  varchar(500)                                                      not null,
    PrimaryDataset        integer                                                   not null,
    PhysicsGroup          integer                                                   not null,
    Status                integer                                                   not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID),
    unique(Name,PrimaryDataset)
  );

REM ======================================================================

CREATE TABLE AnalysisDataset
  (
    ID                    integer,
    Name                  varchar(500)                                                      unique not null,
    Annotation            varchar(1000)                                                     not null,
    ProcessedDS           integer                                                   not null,
    Definition            integer                                                   not null,
    Type                  integer                                                   not null,
    Status                integer                                                   not null,
    Parent                integer,
    PhysicsGroup          integer                                                   not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE Block
  (
    ID                    integer,
    Name                  varchar(500)                                                      unique not null,
    Dataset               integer                                                   not null,
    BlockSize             integer                                                   not null,
    NumberOfFiles         integer                                                   not null,
    NumberOfEvents        integer                                                   not null,
    OpenForWriting        integer                                                               not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE Files
  (
    ID                    integer,
    LogicalFileName       varchar(500)                                                      unique not null,
    Dataset               integer                                                   not null,
    Block                 integer                                                   not null,
    Checksum              varchar(100)                                                      not null,
    NumberOfEvents        integer                                                   not null,
    FileSize              integer                                                   not null,
    FileStatus            integer                                                   not null,
    FileType              integer                                                   not null,
    ValidationStatus      integer,
    QueryableMetadata     varchar(1000),
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID)
  );

REM ======================================================================

CREATE TABLE SEBlock
  (
    ID                    integer,
    SEID                  integer                                                   not null,
    BlockID               integer                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID),
    unique(SEID,BlockID)
  );

REM ======================================================================

CREATE TABLE FileTier
  (
    ID                    integer,
    Fileid                integer                                                   not null,
    DataTier              integer                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID),
    unique(Fileid,DataTier)
  );

REM ======================================================================

CREATE TABLE FileParentage
  (
    ID                    integer,
    ThisFile              integer                                                   not null,
    ItsParent             integer                                                   not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID),
    unique(ThisFile,ItsParent)
  );

REM ======================================================================

CREATE TABLE FileRunLumi
  (
    ID                    integer,
    Fileid                integer                                                   not null,
    Lumi                  integer,
    Run                   integer                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID),
    unique(Fileid,Lumi,Run)
  );

REM ======================================================================

CREATE TABLE FileAlgo
  (
    ID                    integer,
    Fileid                integer                                                   not null,
    Algorithm             integer                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID),
    unique(Fileid,Algorithm)
  );

REM ======================================================================

CREATE TABLE FileBranch
  (
    ID                    integer,
    Fileid                integer                                                   not null,
    Branch                integer                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID),
    unique(Fileid,Branch)
  );

REM ======================================================================

CREATE TABLE ProcDSRuns
  (
    ID                    integer,
    Dataset               integer                                                   not null,
    Run                   integer                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID),
    unique(Dataset,Run)
  );

REM ======================================================================

CREATE TABLE ProcDSTier
  (
    ID                    integer,
    Dataset               integer                                                   not null,
    DataTier              integer                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID),
    unique(Dataset,DataTier)
  );

REM ======================================================================

CREATE TABLE ProcDSParent
  (
    ID                    integer,
    ThisDataset           integer                                                   not null,
    ItsParent             integer                                                   not null,
    CreatedBy             integer,
    CreationDate          TIMESTAMP DEFAULT 0,
    LastModifiedBy        integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(ID),
    unique(ThisDataset,ItsParent)
  );

REM ======================================================================

CREATE TABLE ProcAlgo
  (
    ID                    integer,
    Dataset               integer                                                   not null,
    Algorithm             integer                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID),
    unique(Dataset,Algorithm)
  );

REM ======================================================================

CREATE TABLE AnalysisDSFileLumi
  (
    ID                    integer,
    AnalysisDataset       integer                                                   not null,
    Lumi                  integer                                                   not null,
    Fileid                integer                                                   not null,
    CreationDate          TIMESTAMP DEFAULT 0,
    CreatedBy             integer,
    LastModificationDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    LastModifiedBy        integer,
    primary key(ID),
    unique(AnalysisDataset,Lumi,Fileid)
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

ALTER TABLE StorageElement ADD CONSTRAINT 
    StorageElement_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE StorageElement ADD CONSTRAINT 
    StorageElementLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE Branch ADD CONSTRAINT 
    Branch_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/
ALTER TABLE Branch ADD CONSTRAINT 
    Branch_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
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

ALTER TABLE PrimaryDSType ADD CONSTRAINT 
    PrimaryDSType_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE PrimaryDSType ADD CONSTRAINT 
    PrimaryDSTypeLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE ProcDSStatus ADD CONSTRAINT 
    ProcDSStatus_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ProcDSStatus ADD CONSTRAINT 
    ProcDSStatus_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
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

ALTER TABLE AnalysisDSDef ADD CONSTRAINT 
    AnalysisDSDef_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AnalysisDSDef ADD CONSTRAINT 
    AnalysisDSDefLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
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

ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_ProcessedDS_FK foreign key(ProcessedDS) references ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Definition_FK foreign key(Definition) references AnalysisDSDef(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Type_FK foreign key(Type) references AnalysisDSType(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Status_FK foreign key(Status) references AnalysisDSStatus(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_Parent_FK foreign key(Parent) references AnalysisDataset(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDatasetPhysicsGroup_FK foreign key(PhysicsGroup) references PhysicsGroup(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDataset_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE AnalysisDataset ADD CONSTRAINT 
    AnalysisDatasetLastModified_FK foreign key(LastModifiedBy) references Person(ID)
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
    Files_ValidationStatus_FK foreign key(ValidationStatus) references AnalysisDSStatus(ID)
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE Files ADD CONSTRAINT 
    Files_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

ALTER TABLE SEBlock ADD CONSTRAINT 
    SEBlock_SEID_FK foreign key(SEID) references StorageElement(ID)
/
ALTER TABLE SEBlock ADD CONSTRAINT 
    SEBlock_BlockID_FK foreign key(BlockID) references Block(ID)
/
ALTER TABLE SEBlock ADD CONSTRAINT 
    SEBlock_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE SEBlock ADD CONSTRAINT 
    SEBlock_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
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


CREATE INDEX  ON Person(Name);

CREATE INDEX  ON ProcessedDataset(Name);

CREATE INDEX  ON AnalysisDataset(Annotation);
