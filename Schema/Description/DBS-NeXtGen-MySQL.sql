-- ======================================================================
-- ===   Sql Script for Database : DBS_NEW_ERA
-- ===
-- === Build : 400
-- ======================================================================

use dbs_new_era;
-- ======================================================================

CREATE TABLE Person
  (
    ID                 int,
    Name               varchar(100)   not null,
    DistinguishedName  varchar(100)   not null,
    ContactInfo        varchar(100),
    TimeLog            int,

    primary key(ID),
    unique(Name,DistinguishedName),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE Role
  (
    ID               int,
    RoleName         varchar(100)   unique not null,
    RoleDescription  varchar(100)   not null,
    TimeLog          int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE AssignedRole
  (
    ID        int,
    PersonID  int   not null,
    RoleID    int   not null,
    TimeLog   int,

    primary key(ID),
    unique(PersonID,RoleID),

    foreign key(PersonID) references Person(ID) on update CASCADE on delete CASCADE,
    foreign key(RoleID) references Role(ID) on update CASCADE on delete CASCADE,
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE PhysicsGroup
  (
    ID                    int,
    PhysicsGroupName      varchar(100)   unique not null,
    PhysicsGroupConvener  int,
    TimeLog               int,

    primary key(ID),

    foreign key(PhysicsGroupConvener) references Person(ID),
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE SchemaVersion
  (
    ID             int,
    SchemaVersion  varchar(100)   unique not null,
    TimeLog        int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE AnalysisDataset
  (
    ID           int,
    Annotation   varchar(1000)   not null,
    ProcessedDS  int             not null,
    Type         int             not null,
    Status       int             not null,
    Parent       int,
    TimeLog      int,

    primary key(ID),

    foreign key(ProcessedDS) references ProcessedDataset(ID),
    foreign key(Type) references Type(ID) on update SET NULL on delete SET NULL,
    foreign key(Status) references Status(ID) on update SET NULL on delete SET NULL,
    foreign key(Parent) references AnalysisDataset(ID),
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE File
  (
    ID                 int,
    LogicalFileName    varchar(100)    unique not null,
    ProcessedDataset   int             not null,
    Block              int             not null,
    Checksum           varchar(100)    not null,
    Size               int             not null,
    FileStatus         int             not null,
    FileType           int             not null,
    LumiSection        int             not null,
    ValidationStatus   int,
    QueryableMetadata  varchar(1000),
    TimeLog            int,

    primary key(ID),

    foreign key(ProcessedDataset) references ProcessedDataset(ID),
    foreign key(Block) references Block(ID),
    foreign key(FileStatus) references Status(ID),
    foreign key(FileType) references Type(ID),
    foreign key(LumiSection) references LumiSection(ID),
    foreign key(ValidationStatus) references Status(ID),
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE ProcessedDataset
  (
    ID              int,
    Name            varchar(100)   not null,
    PrimaryDataset  int            not null,
    OpenForWriting  char(1)        not null,
    Run             int,
    PhysicsGroup    int,
    TimeLog         int,

    primary key(ID),
    unique(Name,PrimaryDataset),

    foreign key(PrimaryDataset) references PrimaryDataset(ID) on update CASCADE on delete CASCADE,
    foreign key(Run) references Run(ID),
    foreign key(PhysicsGroup) references PhysicsGroup(ID),
    foreign key(TimeLog) references TimeLog(ID),

    CHECK(OpenForWriting IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE PrimaryDataset
  (
    ID           int,
    Annotation   varchar(1000)   not null,
    Name         varchar(100)    unique not null,
    Description  int             not null,
    StartDate    varchar(100),
    EndDate      varchar(100),
    Type         int             not null,
    TimeLog      int,

    primary key(ID),

    foreign key(Description) references PrimaryDatasetDescription(ID),
    foreign key(Type) references Type(ID),
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE Run
  (
    ID                    int,
    RunNumber             smallint       unique not null,
    NumberOfEvents        smallint,
    NumberOfLumiSections  smallint,
    TotalLuminosity       smallint,
    StoreNumber           smallint,
    StartOfRun            varchar(100),
    EndOfRun              varchar(100),
    TimeLog               int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE DataTier
  (
    ID       int,
    Name     varchar(100)   unique not null,
    TimeLog  int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE LumiSection
  (
    ID                 int,
    LuniSectionNumber  smallint       unique not null,
    RunNumber          int            unique not null,
    LumiStartTime      varchar(100),
    LumiEndTime        varchar(100),
    TimeLog            int,

    primary key(ID),

    foreign key(RunNumber) references Run(ID),
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE Block
  (
    ID                int,
    Size              int            not null,
    Name              varchar(100)   unique not null,
    ProcessedDataset  int            not null,
    NumberOfFiles     int            not null,
    OpenForWriting    char(1)        not null,
    TimeLog           int,

    primary key(ID),

    foreign key(ProcessedDataset) references ProcessedDataset(ID),
    foreign key(TimeLog) references TimeLog(ID),

    CHECK(OpenForWriting IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE Status
  (
    ID       int,
    Status   varchar(100)   unique not null,
    TimeLog  int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE Type
  (
    ID       int,
    Type     varchar(100)   unique not null,
    TimeLog  int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE Description
  (
    ID           int,
    Description  varchar(100)   unique not null,
    TimeLog      int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE ProcDSTier
  (
    ID        int,
    Dataset   int   not null,
    DataTier  int   not null,
    TimeLog   int,

    primary key(ID),
    unique(Dataset,DataTier),

    foreign key(Dataset) references ProcessedDataset(ID),
    foreign key(DataTier) references DataTier(ID),
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE DatasetParentage
  (
    ThisDataset  int   not null,
    ItsParent    int   not null,
    TimeLog      int,

    primary key(ThisDataset,ItsParent),
    unique(ThisDataset,ItsParent),

    foreign key(ThisDataset) references ProcessedDataset(ID) on update SET NULL on delete SET NULL,
    foreign key(ItsParent) references ProcessedDataset(ID) on update CASCADE on delete CASCADE,
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE AnalysisDatasetLumi
  (
    ID               int,
    AnalysisDataset  int   unique not null,
    Lumi             int   not null,
    TimeLog          int,

    primary key(ID),
    unique(Lumi),

    foreign key(AnalysisDataset) references AnalysisDataset(ID),
    foreign key(Lumi) references LumiSection(ID),
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE TimeLog
  (
    ID                    int,
    CreationDate          varchar(100),
    CreatedBy             int,
    LastModificationDate  varchar(100),
    LastModifiedBy        int,
    Comments              varchar(100)   not null,

    primary key(ID),

    foreign key(CreatedBy) references Person(ID),
    foreign key(LastModifiedBy) references Person(ID)
  );

-- ======================================================================

CREATE TABLE AlgoritmConfig
  (
    ID                  int,
    ExecutableName      int   not null,
    ApplicationVersion  int   not null,
    ApplicationFamily   int   not null,
    ParameterSetID      int   not null,
    ConditionsID        int   not null,
    ProcessedDS         int   not null,
    TimeLog             int,

    primary key(ID),
    unique(ExecutableName,ApplicationVersion,ApplicationFamily,ParameterSetID,ConditionsID,ProcessedDS),

    foreign key(ExecutableName) references AppExecutable(ID),
    foreign key(ApplicationVersion) references AppVersion(ID),
    foreign key(ApplicationFamily) references ApplicationFamily(ID),
    foreign key(ParameterSetID) references QueryableParameterSet(ID),
    foreign key(ConditionsID) references Conditions(ID),
    foreign key(ProcessedDS) references ProcessedDataset(ID),
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE ApplicationFamily
  (
    ID                     int,
    ApplicationFamilyName  varchar(100)   unique not null,
    TimeLog                int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE AppVersion
  (
    ID          int,
    AppVersion  varchar(100)   unique not null,
    TimeLog     int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE AppExecutable
  (
    ID              int,
    ExecutableName  varchar(100)   unique not null,
    TimeLog         int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE Conditions
  (
    ID          int,
    Conditions  varchar(100)   unique not null,
    TimeLog     int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE QueryableParameterSet
  (
    ID          int,
    Hash        varchar(1000)   not null,
    Name        varchar(100)    not null,
    Version     varchar(100)    not null,
    Type        varchar(100)    not null,
    Annotation  varchar(1000)   not null,
    Content     varchar(1000)   not null,
    TimeLog     int,

    primary key(ID),
    unique(Name,Version,Type),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE ParameterBinding
  (
    ID        int,
    Self      int   not null,
    Contains  int   not null,
    TimeLog   int,

    primary key(ID),
    unique(Self,Contains),

    foreign key(Self) references QueryableParameterSet(ID),
    foreign key(Contains) references QueryableParameterSet(ID),
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE PrimaryDatasetDescription
  (
    ID                      int,
    TriggerDescriptionID    int   unique,
    MCChannelDescriptionID  int   unique,
    OtherDescriptionID      int   unique,
    TimeLog                 int,

    primary key(ID),

    foreign key(TriggerDescriptionID) references TriggerPathDescription(ID),
    foreign key(MCChannelDescriptionID) references MCDescription(ID),
    foreign key(OtherDescriptionID) references OtherDescription(ID),
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE TriggerPathDescription
  (
    ID                      int,
    TriggerPathDescription  varchar(100)   unique not null,
    TimeLog                 int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE MCDescription
  (
    ID                    int,
    MCChannelDescription  varchar(100)   not null,
    MCProduction          varchar(100),
    MCDecayChain          varchar(100),
    TimeLog               int,

    primary key(ID),
    unique(MCChannelDescription,MCProduction,MCDecayChain),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE OtherDescription
  (
    ID           int,
    Description  varchar(100)   unique not null,
    TimeLog      int,

    primary key(ID),

    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE FileTier
  (
    ID        int,
    File      int   not null,
    DataTier  int   not null,
    TimeLog   int,

    primary key(ID),
    unique(File,DataTier),

    foreign key(File) references File(ID),
    foreign key(DataTier) references DataTier(ID),
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

CREATE TABLE FileParentage
  (
    ThisFile   int   not null,
    ItsParent  int   not null,
    TimeLog    int,

    primary key(ThisFile,ItsParent),
    unique(ThisFile,ItsParent),

    foreign key(ThisFile) references File(ID) on update SET NULL on delete SET NULL,
    foreign key(ItsParent) references File(ID) on update CASCADE on delete CASCADE,
    foreign key(TimeLog) references TimeLog(ID)
  );

-- ======================================================================

