--QualityValues
CREATE TABLE QualityValues
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Value                 varchar(500)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--PrimaryDatasetDescription
CREATE TABLE PrimaryDatasetDescription
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    TriggerDescriptionID    BIGINT UNSIGNED,
    MCChannelDescriptionID  BIGINT UNSIGNED,
    OtherDescriptionID      BIGINT UNSIGNED,
    CreatedBy               BIGINT UNSIGNED,
    CreationDate            BIGINT,
    LastModifiedBy          BIGINT UNSIGNED,
    LastModificationDate    BIGINT,

    unique(TriggerDescriptionID,MCChannelDescriptionID,OtherDescriptionID)
    foreign key(TriggerDescriptionID) references TriggerPathDescription(ID)
    foreign key(MCChannelDescriptionID) references MCDescription(ID)
    foreign key(OtherDescriptionID) references OtherDescription(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--TriggerPathDescription
CREATE TABLE TriggerPathDescription
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    TriggerPathDescription  varchar(100)      unique not null,
    CreatedBy               BIGINT UNSIGNED,
    CreationDate            BIGINT,
    LastModifiedBy          BIGINT UNSIGNED,
    LastModificationDate    BIGINT,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--CompADSMap
CREATE TABLE CompADSMap
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    CompADS               BIGINT UNSIGNED   not null,
    ADS                   BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(CompADS,ADS)
    foreign key(CompADS) references CompositeADS(ID)
    foreign key(ADS) references AnalysisDataset(ID) on delete CASCADE
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--OtherDescription
CREATE TABLE OtherDescription
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Description           varchar(100)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--FileTier
CREATE TABLE FileTier
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Fileid                BIGINT UNSIGNED   not null,
    DataTier              BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(Fileid,DataTier)
    foreign key(Fileid) references Files(ID) on delete CASCADE
    foreign key(DataTier) references DataTier(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--FileStatus
CREATE TABLE FileStatus
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Status                varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--Role
CREATE TABLE Role
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    RoleName              varchar(100)      unique not null,
    RoleDescription       varchar(500)      not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--Branch
CREATE TABLE Branch
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Name                  varchar(500)      unique not null,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,

    foreign key(LastModifiedBy) references Person(ID)
    foreign key(CreatedBy) references Person(ID)
);




--ProcDSStatus
CREATE TABLE ProcDSStatus
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Status                varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--ProcDSTier
CREATE TABLE ProcDSTier
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Dataset               BIGINT UNSIGNED   not null,
    DataTier              BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(Dataset,DataTier)
    foreign key(Dataset) references ProcessedDataset(ID) on delete CASCADE
    foreign key(DataTier) references DataTier(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--ProcDSParent
CREATE TABLE ProcDSParent
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    ThisDataset           BIGINT UNSIGNED   not null,
    ItsParent             BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    unique(ThisDataset,ItsParent)
    foreign key(ThisDataset) references ProcessedDataset(ID) on delete CASCADE
    foreign key(ItsParent) references ProcessedDataset(ID) on delete CASCADE
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--QualityHistory
CREATE TABLE QualityHistory
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
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

    unique(HistoryTimeStamp,Run,Lumi,SubSystem,DQValue)
    foreign key(HistoryOf) references RunLumiQuality(ID)
    foreign key(Run) references Runs(ID)
    foreign key(Lumi) references LumiSection(ID)
    foreign key(SubSystem) references SubSystem(ID) on delete CASCADE
    foreign key(DQValue) references QualityValues(ID) on delete CASCADE
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--Files
CREATE TABLE Files
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
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

    foreign key(Dataset) references ProcessedDataset(ID) on delete CASCADE
    foreign key(Block) references Block(ID) on delete CASCADE
    foreign key(FileStatus) references FileStatus(ID)
    foreign key(FileType) references FileType(ID)
    foreign key(ValidationStatus) references FileValidStatus(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--ParameterBinding
CREATE TABLE ParameterBinding
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Self                  BIGINT UNSIGNED   not null,
    Contains              BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    foreign key(Self) references QueryableParameterSet(ID)
    foreign key(Contains) references QueryableParameterSet(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--ProcDSRuns
CREATE TABLE ProcDSRuns
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Dataset               BIGINT UNSIGNED   not null,
    Run                   BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(Dataset,Run)
    foreign key(Dataset) references ProcessedDataset(ID) on delete CASCADE
    foreign key(Run) references Runs(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--PhysicsGroup
CREATE TABLE PhysicsGroup
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    PhysicsGroupName      varchar(500)      unique not null,
    PhysicsGroupConvener  BIGINT UNSIGNED,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(PhysicsGroupConvener) references Person(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--QualityVersion
CREATE TABLE QualityVersion
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Version               varchar(500)      unique not null,
    VersionTimeStamp      BIGINT            unique not null,
    Description           varchar(1000),
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--DataTier
CREATE TABLE DataTier
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Name                  varchar(100)      unique not null,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,

    foreign key(LastModifiedBy) references Person(ID)
    foreign key(CreatedBy) references Person(ID)
);




--FileParentage
CREATE TABLE FileParentage
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    ThisFile              BIGINT UNSIGNED   not null,
    ItsParent             BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    unique(ThisFile,ItsParent)
    foreign key(ThisFile) references Files(ID) on delete CASCADE
    foreign key(ItsParent) references Files(ID) on delete CASCADE
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--StorageElement
CREATE TABLE StorageElement
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    SEName                varchar(500)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--TimeLog
CREATE TABLE TimeLog
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Action                varchar(100)      not null,
    Cause                 varchar(100)      not null,
    Effect                varchar(100)      not null,
    Description           varchar(500)      not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--FileAlgo
CREATE TABLE FileAlgo
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Fileid                BIGINT UNSIGNED   not null,
    Algorithm             BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(Fileid,Algorithm)
    foreign key(Fileid) references Files(ID) on delete CASCADE
    foreign key(Algorithm) references AlgorithmConfig(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--RunLumiQuality
CREATE TABLE RunLumiQuality
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Run                   BIGINT UNSIGNED   not null,
    Lumi                  BIGINT UNSIGNED,
    SubSystem             BIGINT UNSIGNED   not null,
    DQValue               BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(Run,Lumi,SubSystem)
    foreign key(Run) references Runs(ID)
    foreign key(Lumi) references LumiSection(ID)
    foreign key(SubSystem) references SubSystem(ID) on delete CASCADE
    foreign key(DQValue) references QualityValues(ID) on delete CASCADE
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--PrimaryDataset
CREATE TABLE PrimaryDataset
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
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

    foreign key(Description) references PrimaryDatasetDescription(ID)
    foreign key(Type) references PrimaryDSType(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--FileAssoc
CREATE TABLE FileAssoc
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    ThisFile              BIGINT UNSIGNED   not null,
    ItsAssoc              BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    unique(ThisFile,ItsAssoc)
    foreign key(ThisFile) references Files(ID) on delete CASCADE
    foreign key(ItsAssoc) references Files(ID) on delete CASCADE
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--SubSystem
CREATE TABLE SubSystem
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Name                  varchar(500)      unique not null,
    Parent                varchar(500)      not null default 'CMS',
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--Runs
CREATE TABLE Runs
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    RunNumber             BIGINT UNSIGNED   unique not null,
    NumberOfEvents        BIGINT UNSIGNED   not null,
    NumberOfLumiSections  BIGINT UNSIGNED   not null,
    TotalLuminosity       BIGINT UNSIGNED   not null,
    StoreNumber           BIGINT UNSIGNED   not null,
    StartOfRun            BIGINT,
    EndOfRun              BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--LumiSection
CREATE TABLE LumiSection
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    LumiSectionNumber     BIGINT UNSIGNED   not null,
    RunNumber             BIGINT UNSIGNED   not null,
    StartEventNumber      BIGINT UNSIGNED   not null,
    EndEventNumber        BIGINT UNSIGNED   not null,
    LumiStartTime         BIGINT,
    LumiEndTime           BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    unique(LumiSectionNumber,RunNumber)
    foreign key(RunNumber) references Runs(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--AppExecutable
CREATE TABLE AppExecutable
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    ExecutableName        varchar(100)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--FileValidStatus
CREATE TABLE FileValidStatus
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Status                varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--FileBranch
CREATE TABLE FileBranch
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Fileid                BIGINT UNSIGNED   not null,
    Branch                BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(Fileid,Branch)
    foreign key(Fileid) references Files(ID) on delete CASCADE
    foreign key(Branch) references Branch(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--FileType
CREATE TABLE FileType
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Type                  varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--AppVersion
CREATE TABLE AppVersion
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Version               varchar(100)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--CompositeADS
CREATE TABLE CompositeADS
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Name                  varchar(500)      unique not null,
    Description           varchar(1000)     not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--DataTierOrder
CREATE TABLE DataTierOrder
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    DataTierOrder         varchar(250)      unique not null,
    Description           varchar(1000),
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--AnalysisDSType
CREATE TABLE AnalysisDSType
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Type                  varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--ProcessedDataset
CREATE TABLE ProcessedDataset
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Name                  varchar(500)      not null,
    PrimaryDataset        BIGINT UNSIGNED   not null,
    PhysicsGroup          BIGINT UNSIGNED   not null,
    Status                BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    unique(Name,PrimaryDataset)
    foreign key(PrimaryDataset) references PrimaryDataset(ID) on delete CASCADE
    foreign key(PhysicsGroup) references PhysicsGroup(ID)
    foreign key(Status) references ProcDSStatus(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--AlgorithmConfig
CREATE TABLE AlgorithmConfig
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    ExecutableName        BIGINT UNSIGNED   not null,
    ApplicationVersion    BIGINT UNSIGNED   not null,
    ApplicationFamily     BIGINT UNSIGNED   not null,
    ParameterSetID        BIGINT UNSIGNED   not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(ExecutableName,ApplicationVersion,ApplicationFamily,ParameterSetID)
    foreign key(ExecutableName) references AppExecutable(ID)
    foreign key(ApplicationVersion) references AppVersion(ID)
    foreign key(ApplicationFamily) references AppFamily(ID)
    foreign key(ParameterSetID) references QueryableParameterSet(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--Block
CREATE TABLE Block
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
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

    foreign key(Dataset) references ProcessedDataset(ID) on delete CASCADE
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--ProcAlgo
CREATE TABLE ProcAlgo
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Dataset               BIGINT UNSIGNED   not null,
    Algorithm             BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(Dataset,Algorithm)
    foreign key(Dataset) references ProcessedDataset(ID) on delete CASCADE
    foreign key(Algorithm) references AlgorithmConfig(ID) on delete CASCADE
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--AssignedRole
CREATE TABLE AssignedRole
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    PersonID              BIGINT UNSIGNED   not null,
    RoleID                BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(PersonID) references Person(ID)
    foreign key(RoleID) references Role(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--Person
CREATE TABLE Person
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Name                  varchar(100),
    DistinguishedName     varchar(500)      unique not null,
    ContactInfo           varchar(250),
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--SEBlock
CREATE TABLE SEBlock
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    SEID                  BIGINT UNSIGNED   not null,
    BlockID               BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(SEID,BlockID)
    foreign key(SEID) references StorageElement(ID) on delete CASCADE
    foreign key(BlockID) references Block(ID) on delete CASCADE
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--FileRunLumi
CREATE TABLE FileRunLumi
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Fileid                BIGINT UNSIGNED   not null,
    Lumi                  BIGINT UNSIGNED,
    Run                   BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(Fileid,Lumi,Run)
    foreign key(Fileid) references Files(ID) on delete CASCADE
    foreign key(Lumi) references LumiSection(ID)
    foreign key(Run) references Runs(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--AnalysisDSDef
CREATE TABLE AnalysisDSDef
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
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

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--QueryableParameterSet
CREATE TABLE QueryableParameterSet
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
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

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--MCDescription
CREATE TABLE MCDescription
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    MCChannelDescription  varchar(100)      not null,
    MCProduction          varchar(100),
    MCDecayChain          varchar(100),
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    unique(MCChannelDescription,MCProduction,MCDecayChain)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--AppFamily
CREATE TABLE AppFamily
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    FamilyName            varchar(100)      unique not null,
    CreatedBy             BIGINT UNSIGNED,
    CreationDate          BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    LastModificationDate  BIGINT,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--PrimaryDSType
CREATE TABLE PrimaryDSType
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Type                  varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--FileTriggerTag
CREATE TABLE FileTriggerTag
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Fileid                BIGINT UNSIGNED   not null,
    TriggerTag            varchar(500)      not null,
    NumberOfEvents        BIGINT,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(Fileid,TriggerTag)
    foreign key(Fileid) references Files(ID) on delete CASCADE
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--AnalysisDSFileLumi
CREATE TABLE AnalysisDSFileLumi
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    AnalysisDataset       BIGINT UNSIGNED   not null,
    Lumi                  BIGINT UNSIGNED,
    Fileid                BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    unique(AnalysisDataset,Lumi,Fileid)
    foreign key(AnalysisDataset) references AnalysisDataset(ID) on delete CASCADE
    foreign key(Lumi) references LumiSection(ID)
    foreign key(Fileid) references Files(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--AnalysisDSStatus
CREATE TABLE AnalysisDSStatus
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    Status                varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--AnalysisDataset
CREATE TABLE AnalysisDataset
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
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

    unique(Name,Version)
    foreign key(Definition) references AnalysisDSDef(ID)
    foreign key(PhysicsGroup) references PhysicsGroup(ID)
    foreign key(ProcessedDS) references ProcessedDataset(ID) on delete CASCADE
    foreign key(Type) references AnalysisDSType(ID)
    foreign key(Status) references AnalysisDSStatus(ID)
    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




--SchemaVersion
CREATE TABLE SchemaVersion
  (
    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,
    SchemaVersion         varchar(100)      unique not null,
    InstanceName          varchar(100)      unique not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    foreign key(CreatedBy) references Person(ID)
    foreign key(LastModifiedBy) references Person(ID)
);




CREATE TRIGGER TR_Person AFTER INSERT ON Person
FOR EACH ROW
	BEGIN
		UPDATE Person SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_Role AFTER INSERT ON Role
FOR EACH ROW
	BEGIN
		UPDATE Role SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_AssignedRole AFTER INSERT ON AssignedRole
FOR EACH ROW
	BEGIN
		UPDATE AssignedRole SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_PhysicsGroup AFTER INSERT ON PhysicsGroup
FOR EACH ROW
	BEGIN
		UPDATE PhysicsGroup SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_SchemaVersion AFTER INSERT ON SchemaVersion
FOR EACH ROW
	BEGIN
		UPDATE SchemaVersion SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_PrimaryDataset AFTER INSERT ON PrimaryDataset
FOR EACH ROW
	BEGIN
		UPDATE PrimaryDataset SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_ProcessedDataset AFTER INSERT ON ProcessedDataset
FOR EACH ROW
	BEGIN
		UPDATE ProcessedDataset SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_Runs AFTER INSERT ON Runs
FOR EACH ROW
	BEGIN
		UPDATE Runs SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_Files AFTER INSERT ON Files
FOR EACH ROW
	BEGIN
		UPDATE Files SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_DataTier AFTER INSERT ON DataTier
FOR EACH ROW
	BEGIN
		UPDATE DataTier SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_LumiSection AFTER INSERT ON LumiSection
FOR EACH ROW
	BEGIN
		UPDATE LumiSection SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_Branch AFTER INSERT ON Branch
FOR EACH ROW
	BEGIN
		UPDATE Branch SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_TimeLog AFTER INSERT ON TimeLog
FOR EACH ROW
	BEGIN
		UPDATE TimeLog SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_DataTierOrder AFTER INSERT ON DataTierOrder
FOR EACH ROW
	BEGIN
		UPDATE DataTierOrder SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_AlgorithmConfig AFTER INSERT ON AlgorithmConfig
FOR EACH ROW
	BEGIN
		UPDATE AlgorithmConfig SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_AppFamily AFTER INSERT ON AppFamily
FOR EACH ROW
	BEGIN
		UPDATE AppFamily SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_AppVersion AFTER INSERT ON AppVersion
FOR EACH ROW
	BEGIN
		UPDATE AppVersion SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_AppExecutable AFTER INSERT ON AppExecutable
FOR EACH ROW
	BEGIN
		UPDATE AppExecutable SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_QueryableParameterSet AFTER INSERT ON QueryableParameterSet
FOR EACH ROW
	BEGIN
		UPDATE QueryableParameterSet SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_ParameterBinding AFTER INSERT ON ParameterBinding
FOR EACH ROW
	BEGIN
		UPDATE ParameterBinding SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_PrimaryDatasetDescription AFTER INSERT ON PrimaryDatasetDescription
FOR EACH ROW
	BEGIN
		UPDATE PrimaryDatasetDescription SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_TriggerPathDescription AFTER INSERT ON TriggerPathDescription
FOR EACH ROW
	BEGIN
		UPDATE TriggerPathDescription SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_MCDescription AFTER INSERT ON MCDescription
FOR EACH ROW
	BEGIN
		UPDATE MCDescription SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_OtherDescription AFTER INSERT ON OtherDescription
FOR EACH ROW
	BEGIN
		UPDATE OtherDescription SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_FileTier AFTER INSERT ON FileTier
FOR EACH ROW
	BEGIN
		UPDATE FileTier SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_FileParentage AFTER INSERT ON FileParentage
FOR EACH ROW
	BEGIN
		UPDATE FileParentage SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_FileRunLumi AFTER INSERT ON FileRunLumi
FOR EACH ROW
	BEGIN
		UPDATE FileRunLumi SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_FileAlgo AFTER INSERT ON FileAlgo
FOR EACH ROW
	BEGIN
		UPDATE FileAlgo SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_FileStatus AFTER INSERT ON FileStatus
FOR EACH ROW
	BEGIN
		UPDATE FileStatus SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_FileType AFTER INSERT ON FileType
FOR EACH ROW
	BEGIN
		UPDATE FileType SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_FileBranch AFTER INSERT ON FileBranch
FOR EACH ROW
	BEGIN
		UPDATE FileBranch SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_FileValidStatus AFTER INSERT ON FileValidStatus
FOR EACH ROW
	BEGIN
		UPDATE FileValidStatus SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_FileTriggerTag AFTER INSERT ON FileTriggerTag
FOR EACH ROW
	BEGIN
		UPDATE FileTriggerTag SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_FileAssoc AFTER INSERT ON FileAssoc
FOR EACH ROW
	BEGIN
		UPDATE FileAssoc SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_ProcDSRuns AFTER INSERT ON ProcDSRuns
FOR EACH ROW
	BEGIN
		UPDATE ProcDSRuns SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_ProcDSTier AFTER INSERT ON ProcDSTier
FOR EACH ROW
	BEGIN
		UPDATE ProcDSTier SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_ProcDSParent AFTER INSERT ON ProcDSParent
FOR EACH ROW
	BEGIN
		UPDATE ProcDSParent SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_ProcAlgo AFTER INSERT ON ProcAlgo
FOR EACH ROW
	BEGIN
		UPDATE ProcAlgo SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_AnalysisDataset AFTER INSERT ON AnalysisDataset
FOR EACH ROW
	BEGIN
		UPDATE AnalysisDataset SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_AnalysisDSType AFTER INSERT ON AnalysisDSType
FOR EACH ROW
	BEGIN
		UPDATE AnalysisDSType SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_AnalysisDSStatus AFTER INSERT ON AnalysisDSStatus
FOR EACH ROW
	BEGIN
		UPDATE AnalysisDSStatus SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_AnalysisDSFileLumi AFTER INSERT ON AnalysisDSFileLumi
FOR EACH ROW
	BEGIN
		UPDATE AnalysisDSFileLumi SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_AnalysisDSDef AFTER INSERT ON AnalysisDSDef
FOR EACH ROW
	BEGIN
		UPDATE AnalysisDSDef SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_CompositeADS AFTER INSERT ON CompositeADS
FOR EACH ROW
	BEGIN
		UPDATE CompositeADS SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_CompADSMap AFTER INSERT ON CompADSMap
FOR EACH ROW
	BEGIN
		UPDATE CompADSMap SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_SEBlock AFTER INSERT ON SEBlock
FOR EACH ROW
	BEGIN
		UPDATE SEBlock SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_StorageElement AFTER INSERT ON StorageElement
FOR EACH ROW
	BEGIN
		UPDATE StorageElement SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_Block AFTER INSERT ON Block
FOR EACH ROW
	BEGIN
		UPDATE Block SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_ProcDSStatus AFTER INSERT ON ProcDSStatus
FOR EACH ROW
	BEGIN
		UPDATE ProcDSStatus SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_PrimaryDSType AFTER INSERT ON PrimaryDSType
FOR EACH ROW
	BEGIN
		UPDATE PrimaryDSType SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_QualityValues AFTER INSERT ON QualityValues
FOR EACH ROW
	BEGIN
		UPDATE QualityValues SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_SubSystem AFTER INSERT ON SubSystem
FOR EACH ROW
	BEGIN
		UPDATE SubSystem SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_RunLumiQuality AFTER INSERT ON RunLumiQuality
FOR EACH ROW
	BEGIN
		UPDATE RunLumiQuality SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER TR_QualityVersion AFTER INSERT ON QualityVersion
FOR EACH ROW
	BEGIN
		UPDATE QualityVersion SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_Person BEFORE UPDATE ON Person
FOR EACH ROW
	BEGIN
		UPDATE Person SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_Role BEFORE UPDATE ON Role
FOR EACH ROW
	BEGIN
		UPDATE Role SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_AssignedRole BEFORE UPDATE ON AssignedRole
FOR EACH ROW
	BEGIN
		UPDATE AssignedRole SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_PhysicsGroup BEFORE UPDATE ON PhysicsGroup
FOR EACH ROW
	BEGIN
		UPDATE PhysicsGroup SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_SchemaVersion BEFORE UPDATE ON SchemaVersion
FOR EACH ROW
	BEGIN
		UPDATE SchemaVersion SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_PrimaryDataset BEFORE UPDATE ON PrimaryDataset
FOR EACH ROW
	BEGIN
		UPDATE PrimaryDataset SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_ProcessedDataset BEFORE UPDATE ON ProcessedDataset
FOR EACH ROW
	BEGIN
		UPDATE ProcessedDataset SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_Runs BEFORE UPDATE ON Runs
FOR EACH ROW
	BEGIN
		UPDATE Runs SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_Files BEFORE UPDATE ON Files
FOR EACH ROW
	BEGIN
		UPDATE Files SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_DataTier BEFORE UPDATE ON DataTier
FOR EACH ROW
	BEGIN
		UPDATE DataTier SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_LumiSection BEFORE UPDATE ON LumiSection
FOR EACH ROW
	BEGIN
		UPDATE LumiSection SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_Branch BEFORE UPDATE ON Branch
FOR EACH ROW
	BEGIN
		UPDATE Branch SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_TimeLog BEFORE UPDATE ON TimeLog
FOR EACH ROW
	BEGIN
		UPDATE TimeLog SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_DataTierOrder BEFORE UPDATE ON DataTierOrder
FOR EACH ROW
	BEGIN
		UPDATE DataTierOrder SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_AlgorithmConfig BEFORE UPDATE ON AlgorithmConfig
FOR EACH ROW
	BEGIN
		UPDATE AlgorithmConfig SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_AppFamily BEFORE UPDATE ON AppFamily
FOR EACH ROW
	BEGIN
		UPDATE AppFamily SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_AppVersion BEFORE UPDATE ON AppVersion
FOR EACH ROW
	BEGIN
		UPDATE AppVersion SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_AppExecutable BEFORE UPDATE ON AppExecutable
FOR EACH ROW
	BEGIN
		UPDATE AppExecutable SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_QueryableParameterSet BEFORE UPDATE ON QueryableParameterSet
FOR EACH ROW
	BEGIN
		UPDATE QueryableParameterSet SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_ParameterBinding BEFORE UPDATE ON ParameterBinding
FOR EACH ROW
	BEGIN
		UPDATE ParameterBinding SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_PrimaryDatasetDescription BEFORE UPDATE ON PrimaryDatasetDescription
FOR EACH ROW
	BEGIN
		UPDATE PrimaryDatasetDescription SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_TriggerPathDescription BEFORE UPDATE ON TriggerPathDescription
FOR EACH ROW
	BEGIN
		UPDATE TriggerPathDescription SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_MCDescription BEFORE UPDATE ON MCDescription
FOR EACH ROW
	BEGIN
		UPDATE MCDescription SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_OtherDescription BEFORE UPDATE ON OtherDescription
FOR EACH ROW
	BEGIN
		UPDATE OtherDescription SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_FileTier BEFORE UPDATE ON FileTier
FOR EACH ROW
	BEGIN
		UPDATE FileTier SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_FileParentage BEFORE UPDATE ON FileParentage
FOR EACH ROW
	BEGIN
		UPDATE FileParentage SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_FileRunLumi BEFORE UPDATE ON FileRunLumi
FOR EACH ROW
	BEGIN
		UPDATE FileRunLumi SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_FileAlgo BEFORE UPDATE ON FileAlgo
FOR EACH ROW
	BEGIN
		UPDATE FileAlgo SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_FileStatus BEFORE UPDATE ON FileStatus
FOR EACH ROW
	BEGIN
		UPDATE FileStatus SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_FileType BEFORE UPDATE ON FileType
FOR EACH ROW
	BEGIN
		UPDATE FileType SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_FileBranch BEFORE UPDATE ON FileBranch
FOR EACH ROW
	BEGIN
		UPDATE FileBranch SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_FileValidStatus BEFORE UPDATE ON FileValidStatus
FOR EACH ROW
	BEGIN
		UPDATE FileValidStatus SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_FileTriggerTag BEFORE UPDATE ON FileTriggerTag
FOR EACH ROW
	BEGIN
		UPDATE FileTriggerTag SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_FileAssoc BEFORE UPDATE ON FileAssoc
FOR EACH ROW
	BEGIN
		UPDATE FileAssoc SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_ProcDSRuns BEFORE UPDATE ON ProcDSRuns
FOR EACH ROW
	BEGIN
		UPDATE ProcDSRuns SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_ProcDSTier BEFORE UPDATE ON ProcDSTier
FOR EACH ROW
	BEGIN
		UPDATE ProcDSTier SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_ProcDSParent BEFORE UPDATE ON ProcDSParent
FOR EACH ROW
	BEGIN
		UPDATE ProcDSParent SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_ProcAlgo BEFORE UPDATE ON ProcAlgo
FOR EACH ROW
	BEGIN
		UPDATE ProcAlgo SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_AnalysisDataset BEFORE UPDATE ON AnalysisDataset
FOR EACH ROW
	BEGIN
		UPDATE AnalysisDataset SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_AnalysisDSType BEFORE UPDATE ON AnalysisDSType
FOR EACH ROW
	BEGIN
		UPDATE AnalysisDSType SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_AnalysisDSStatus BEFORE UPDATE ON AnalysisDSStatus
FOR EACH ROW
	BEGIN
		UPDATE AnalysisDSStatus SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_AnalysisDSFileLumi BEFORE UPDATE ON AnalysisDSFileLumi
FOR EACH ROW
	BEGIN
		UPDATE AnalysisDSFileLumi SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_AnalysisDSDef BEFORE UPDATE ON AnalysisDSDef
FOR EACH ROW
	BEGIN
		UPDATE AnalysisDSDef SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_CompositeADS BEFORE UPDATE ON CompositeADS
FOR EACH ROW
	BEGIN
		UPDATE CompositeADS SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_CompADSMap BEFORE UPDATE ON CompADSMap
FOR EACH ROW
	BEGIN
		UPDATE CompADSMap SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_SEBlock BEFORE UPDATE ON SEBlock
FOR EACH ROW
	BEGIN
		UPDATE SEBlock SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_StorageElement BEFORE UPDATE ON StorageElement
FOR EACH ROW
	BEGIN
		UPDATE StorageElement SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_Block BEFORE UPDATE ON Block
FOR EACH ROW
	BEGIN
		UPDATE Block SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_ProcDSStatus BEFORE UPDATE ON ProcDSStatus
FOR EACH ROW
	BEGIN
		UPDATE ProcDSStatus SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_PrimaryDSType BEFORE UPDATE ON PrimaryDSType
FOR EACH ROW
	BEGIN
		UPDATE PrimaryDSType SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_QualityValues BEFORE UPDATE ON QualityValues
FOR EACH ROW
	BEGIN
		UPDATE QualityValues SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_SubSystem BEFORE UPDATE ON SubSystem
FOR EACH ROW
	BEGIN
		UPDATE SubSystem SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_RunLumiQuality BEFORE UPDATE ON RunLumiQuality
FOR EACH ROW
	BEGIN
		UPDATE RunLumiQuality SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;

CREATE TRIGGER UTR_QualityVersion BEFORE UPDATE ON QualityVersion
FOR EACH ROW
	BEGIN
		UPDATE QualityVersion SET LastModificationDate = strftime('%s','now')
		WHERE rowid = new.rowid;
	END;


INSERT INTO SchemaVersion(SchemaVersion, InstanceName, CreationDate) values ('DBS_1_0_7', 'LOCAL', strftime('%s','now'));
INSERT INTO AnalysisDSStatus (Status, CreationDate) VALUES ('NEW', strftime('%s','now'));
INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('VALID', strftime('%s','now'));
INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('INVALID', strftime('%s','now'));
INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('IMPORTED', strftime('%s','now'));
INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('EXPORTED', strftime('%s','now'));
INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('RO', strftime('%s','now'));

INSERT INTO FileStatus (Status, CreationDate) VALUES ('VALID', strftime('%s','now'));
INSERT INTO FileStatus (Status, CreationDate) VALUES ('INVALID', strftime('%s','now'));
INSERT INTO FileStatus (Status, CreationDate) VALUES ('MERGED', strftime('%s','now'));
INSERT INTO FileStatus (Status, CreationDate) VALUES ('IMPORTED', strftime('%s','now'));
INSERT INTO FileStatus (Status, CreationDate) VALUES ('EXPORTED', strftime('%s','now'));

INSERT INTO FileValidStatus (Status, CreationDate) VALUES ('VALID', strftime('%s','now'));
INSERT INTO FileValidStatus (Status, CreationDate) VALUES ('INVALID', strftime('%s','now'));

INSERT INTO FileType(Type, CreationDate) VALUES ('EDM', strftime('%s','now')) ;
INSERT INTO FileType(Type, CreationDate) VALUES ('STREAMER', strftime('%s','now')) ;
INSERT INTO AnalysisDSType(Type, CreationDate) VALUES ('TEST', strftime('%s','now'));
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('test', strftime('%s','now'));
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('data',  strftime('%s','now'));
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('raw', strftime('%s','now')) ;
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('mc', strftime('%s','now'));
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('cosmic', strftime('%s','now'));
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('align', strftime('%s','now'));
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('calib', strftime('%s','now'));

INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('SIM', 'Simulated output from GEANT/OSCAR processing of GEN data  PSimHitContainer, EmbdSimVertexContainer, PCaloHitContainer, CrossingFrame');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('DIGI', 'Digitixed output from the various Digitizers that act on the SIM data    EBDigiCollection, HBHEDigiCollection, HFDigiCollection, StripDigiCollection, CSCStripDigiCollection, CSCWireDigiCollection');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('RECO', 'Reconstructed products produced from either real data or DIGI data       TBA');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('AOD', 'Analysis Object Data products TBA');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('AODSIM', 'AODSIM Tier asked by Skim Team');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('RAW', 'Raw detector output from the HLT system   TBA');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('ALCARECO', 'IS ITS A TIER ? TBA');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('USER', 'Things that users make afte AOD. The analysis equivalent of the kitchen sink TBA');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM-DIGI', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('DIGI-RECO', 'Min bias data');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM-DIGI-RECO', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM-DIGI-RAW', 'SV Support 102463 for CSA 07');

INSERT INTO DataTier (Name, CreationDate) VALUES ('GEN', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('SIM', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('DIGI', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('RECO', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('ALCARECO', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('USER', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES  ('RAW', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('AOD', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('AODSIM', strftime('%s','now')) ;

INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Individual', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Higgs', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('SUSYBSM', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('EWK', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Top', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('QCD', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Diffraction', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('OnSel', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Bphys', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Muons', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Egamma', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('JetMet', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('PFlowTau', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Btag', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('RelVal', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Tracker', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('PhysVal', strftime('%s','now'));

INSERT INTO QualityValues (Value, CreationDate) VALUES ('GOOD', strftime('%s','now'));
INSERT INTO QualityValues (Value, CreationDate) VALUES ('BAD', strftime('%s','now'));
INSERT INTO QualityValues (Value, CreationDate) VALUES ('UNKNOWN', strftime('%s','now'));
