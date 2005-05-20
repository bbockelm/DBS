-- ======================================================================
-- ===   Sql Script for Database : DBS Prototype
-- ===
-- === Build : 136
-- ======================================================================

CREATE TABLE Person
  (
    PersonID              int,
    Name                  varchar(80)    unique not null,
    DistinguishedName     varchar(256)   unique not null,
    ContactInfo           varchar(256)   not null,
    CreationDate          date,
    CreatedBy             int,
    LastModificationDate  date,
    LastModifiedBy        int,

    primary key(PersonID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE Role
  (
    RoleID                int,
    RoleName              varchar(80)     unique not null,
    RoleDescription       varchar(1024)   not null,
    CreationDate          date,
    CreatedBy             int,
    LastModificationDate  date,
    LastModifiedBy        int,

    primary key(RoleID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE AssignedRole
  (
    AssignedRoleID        int,
    PersonID              int    not null,
    RoleID                int    not null,
    CreationDate          date,
    CreatedBy             int,
    LastModificationDate  date,
    LastModifiedBy        int,

    primary key(AssignedRoleID),
    unique(PersonID,RoleID),

    foreign key(PersonID) references Person(PersonID) on update CASCADE on delete CASCADE,
    foreign key(RoleID) references Role(RoleID) on update CASCADE on delete CASCADE,
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE PhysicsGroup
  (
    PhysicsGroupID        int,
    PhysicsGroupName      varchar(80)   unique not null,
    PhysicsGroupConvener  int,
    CreationDate          date,
    CreatedBy             int,
    LastModificationDate  date,
    LastModifiedBy        int,

    primary key(PhysicsGroupID),

    foreign key(PhysicsGroupConvener) references Person(PersonID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE AnalysisDatasetSubtype
  (
    AnalysisDatasetTypeID          int,
    AnalysisDatasetTypeName        varchar(80)     unique not null,
    AnalysisDatasetTypeAnnotation  varchar(1024)   not null,
    CreatedBy                      int,
    CreationDate                   date,
    LastModifiedBy                 int,
    LastModificationDate           date,

    primary key(AnalysisDatasetTypeID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE ParameterSet
  (
    ParameterSetID          int,
    ParameterSetName        varchar(80)     not null,
    ParameterSetVersion     varchar(80)     not null,
    ParameterSetAnnotation  varchar(1024)   not null,
    Composite               char(1)         not null,
    CreationDate            date,
    CreatedBy               int,
    LastModificationDate    date,
    LastModifiedBy          int,

    primary key(ParameterSetID),
    unique(ParameterSetName,ParameterSetVersion),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID),

    CHECK(Composite IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE ParameterBinding
  (
    ParameterBindingID    int,
    ParameterName         varchar(80)    not null,
    ParameterValue        varchar(256)   not null,
    ExternalDataType      varchar(80),
    ParameterSetID        int            not null,
    CreatedBy             int,
    CreationDate          date,
    LastModifiedBy        int,
    LastModificationDate  date,

    primary key(ParameterBindingID),
    unique(ParameterName,ParameterSetID),

    foreign key(ParameterSetID) references ParameterSet(ParameterSetID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE TriggerPathDescription
  (
    TriggerPathDescriptionID  int,
    TriggerPathDescription    varchar(256)   unique not null,
    CreatedBy                 int,
    CreationDate              date,
    LastModifiedBy            int,
    LastModificationDate      date,

    primary key(TriggerPathDescriptionID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE MCDescription
  (
    MCDescriptionID       int,
    MCChannelDescription  varchar(256)   not null,
    MCProduction          varchar(256),
    MCDecayChain          varchar(256),
    CreatedBy             int,
    CreationDate          date,
    LastModifiedBy        int,
    LastModificationDate  date,

    primary key(MCDescriptionID),
    unique(MCChannelDescription,MCProduction,MCDecayChain),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE CompositeParameterSet
  (
    CompositeParameterSetID  int,
    ChildParameterSetID      int,

    primary key(CompositeParameterSetID,ChildParameterSetID),

    foreign key(CompositeParameterSetID) references ParameterSet(ParameterSetID) on update CASCADE on delete CASCADE,
    foreign key(ChildParameterSetID) references ParameterSet(ParameterSetID)
  );

-- ======================================================================

CREATE TABLE AnalysisDataset
  (
    AnalysisDatasetID          int,
    AnalysisDatasetAnnotation  varchar(1024)   not null,
    AnalysisDatasetTypeID      int             not null,
    CompositeAnalysisDataset   char(1)         not null,
    AuxilliaryPOOLCatalog      varchar(256),
    CreatedBy                  int,
    CreationDate               date,
    LastModifiedBy             int,
    LastModificationDate       date,

    primary key(AnalysisDatasetID),

    foreign key(AnalysisDatasetTypeID) references AnalysisDatasetSubtype(AnalysisDatasetTypeID) on update SET NULL on delete SET NULL,
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID),

    CHECK(CompositeAnalysisDataset IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE Stream
  (
    StreamID              int             not null,
    StreamName            varchar(80)     unique not null,
    StreamAnnotation      varchar(1024)   not null,
    StartDate             date,
    EndDate               date,
    CreationDate          date,
    CreatedBy             int,
    LastModificationDate  date,
    LastModifiedBy        int,

    primary key(StreamID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE FileStatus
  (
    FileStatusID          int,
    FileStatus            varchar(80)   unique not null,
    CreationDate          date,
    CreatedBy             int,
    LastModificationDate  date,
    LastModifiedBy        int,

    primary key(FileStatusID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE AnalysisCollectionStatus
  (
    AnalysisCollectionStatusID  int,
    AnalysisCollectionStatus    varchar(80)   unique not null,
    CreationDate                date,
    CreatedBy                   int,
    LastModificationDate        date,
    LastModifiedBy              int,

    primary key(AnalysisCollectionStatusID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE ValidationStatus
  (
    ValidationStatusID    int,
    ValidationStatus      varchar(80)   unique,
    CreationDate          date,
    CreatedBy             int,
    LastModificationDate  date,
    LastModifiedBy        int,

    primary key(ValidationStatusID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE FileType
  (
    FileTypeID            int,
    FileType              varchar(80)   unique,
    CreationDate          date,
    CreatedBy             int,
    LastModificationDate  date,
    LastModifiedBy        int,

    primary key(FileTypeID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE CollectionType
  (
    CollectionTypeID      int,
    CollectionType        varchar(80)   unique,
    CreationDate          date,
    CreatedBy             int,
    LastModificationDate  date,
    LastModifiedBy        int,

    primary key(CollectionTypeID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE ApplicationFamily
  (
    ApplicationFamilyID    int,
    ApplicationFamilyName  varchar(80)   unique not null,
    CreatedBy              int,
    CreationDate           date,
    LastModifiedBy         int,
    LastModificationDate   date,

    primary key(ApplicationFamilyID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE ProductionAssignment
  (
    ProductionAssignmentID   int,
    ProductionRequestNumber  int           not null,
    ProductionEra            varchar(80)   not null,

    primary key(ProductionAssignmentID),
    unique(ProductionRequestNumber,ProductionEra),

    foreign key(ProductionAssignmentID) references AnalysisDataset(AnalysisDatasetID) on update CASCADE on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Block
  (
    BlockID         int,
    Size            numeric(10,4),
    Checksum        numeric(10,4),
    OpenForWriting  char(1)         not null,

    primary key(BlockID),

    foreign key(BlockID) references AnalysisDataset(AnalysisDatasetID) on update CASCADE on delete CASCADE,

    CHECK(OpenForWriting IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE UserCollection
  (
    UserCollectionID          int,
    UserCollectionAnnotation  varchar(1024)   not null,

    primary key(UserCollectionID),

    foreign key(UserCollectionID) references AnalysisDataset(AnalysisDatasetID) on update CASCADE on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Snapshot
  (
    SnapshotID          int,
    Query               varchar(1024)   not null,
    SnapshotAnnotation  varchar(1024)   not null,

    primary key(SnapshotID),

    foreign key(SnapshotID) references AnalysisDataset(AnalysisDatasetID) on update CASCADE on delete CASCADE
  );

-- ======================================================================

CREATE TABLE Application
  (
    ApplicationID         int,
    ExecutableName        varchar(80)   not null,
    ApplicationVersion    varchar(80)   not null,
    ApplicationFamily     int           not null,
    InputCollectionType   int           not null,
    OutputCollectionType  int           not null,
    CreatedBy             int,
    CreationDate          date,
    LastModifiedBy        int,
    LastModificationDate  date,

    primary key(ApplicationID),
    unique(ExecutableName,ApplicationVersion,ApplicationFamily),

    foreign key(ApplicationFamily) references ApplicationFamily(ApplicationFamilyID),
    foreign key(InputCollectionType) references CollectionType(CollectionTypeID),
    foreign key(OutputCollectionType) references CollectionType(CollectionTypeID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE CompositeAnalysisDataset
  (
    CompositeAnalysisDatasetID  int,
    ChildAnalysisDatasetID      int,
    CreatedBy                   int,
    CreationDate                date,
    LastModifiedBy              int,
    LastModificationDate        date,

    primary key(CompositeAnalysisDatasetID,ChildAnalysisDatasetID),

    foreign key(CompositeAnalysisDatasetID) references AnalysisDataset(AnalysisDatasetID) on update CASCADE on delete CASCADE,
    foreign key(ChildAnalysisDatasetID) references AnalysisDataset(AnalysisDatasetID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE PrimaryDatasetDescription
  (
    PrimaryDatasetDescriptionID  int,
    TriggerDescriptionID         int,
    MCChannelDescriptionID       int,
    MCDataset                    char(1)   not null,
    CreatedBy                    int,
    CreationDate                 date,
    LastModifiedBy               int,
    LastModificationDate         date,

    primary key(PrimaryDatasetDescriptionID),
    unique(TriggerDescriptionID,MCChannelDescriptionID),

    foreign key(TriggerDescriptionID) references TriggerPathDescription(TriggerPathDescriptionID),
    foreign key(MCChannelDescriptionID) references MCDescription(MCDescriptionID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID),

    CHECK(MCDataset IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE File
  (
    FileID                int,
    LogicalFileName       varchar(256)   unique not null,
    Checksum              varchar(256)   not null,
    Size                  varchar(256)   not null,
    FileStatus            int            not null,
    FileType              int            not null,
    CreatedBy             int,
    CreationDate          date,
    LastModifiedBy        int,
    LastModificationDate  date,

    primary key(FileID),

    foreign key(FileStatus) references FileStatus(FileStatusID),
    foreign key(FileType) references FileType(FileTypeID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE PrimaryDataset
  (
    PrimaryDatasetID             int,
    PrimaryDatasetName           varchar(80)     unique not null,
    COBRADatasetName             varchar(256)    unique not null,
    PrimaryDatasetAnnotation     varchar(1024)   not null,
    PrimaryDatasetDescriptionID  int             not null,
    StreamID                     int             not null,
    PhysicsGroupID               int             not null,
    OpenForWriting               char(1)         not null,
    StartDate                    date,
    EndDate                      date,
    CreatedBy                    int,
    CreationDate                 date,
    LastModificationDate         date,
    LastModifiedBy               int,

    primary key(PrimaryDatasetID),
    unique(PrimaryDatasetName,COBRADatasetName,PrimaryDatasetDescriptionID,StreamID,PhysicsGroupID),

    foreign key(PrimaryDatasetDescriptionID) references PrimaryDatasetDescription(PrimaryDatasetDescriptionID),
    foreign key(StreamID) references Stream(StreamID) on update SET NULL on delete SET NULL,
    foreign key(PhysicsGroupID) references PhysicsGroup(PhysicsGroupID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID),

    CHECK(OpenForWriting IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE ApplicationConfiguration
  (
    ApplicationConfigurationID  int,
    ApplicationID               int    not null,
    ParameterSetID              int    not null,
    CreatedBy                   int,
    CreationDate                date,
    LastModificationDate        date,
    LastModifiedBy              int,

    primary key(ApplicationConfigurationID),
    unique(ApplicationID,ParameterSetID),

    foreign key(ApplicationID) references Application(ApplicationID),
    foreign key(ParameterSetID) references ParameterSet(ParameterSetID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE ProcessingPath
  (
    ProcessingPathID        int,
    ParentProcessingPathID  int,
    ProcessingRecordID      int            not null,
    AggregatedPath          varchar(256),
    CreationDate            date,
    CreatedBy               int,
    LastModificationDate    date,
    LastModifiedBy          int,

    primary key(ProcessingPathID),
    unique(ParentProcessingPathID,ProcessingRecordID),

    foreign key(ParentProcessingPathID) references ProcessingPath(ProcessingPathID),
    foreign key(ProcessingRecordID) references ApplicationConfiguration(ApplicationConfigurationID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE ProcessedDataset
  (
    ProcessedDatasetID    int,
    PrimaryDatasetID      int           not null,
    ProcessingPathID      int           not null,
    OpenForWriting        char(1)       not null,
    COBRAOwnerName        varchar(80)   not null,
    CreatedBy             int,
    CreationDate          date,
    LastModifiedBy        int,
    LastModificationDate  date,

    primary key(ProcessedDatasetID),
    unique(PrimaryDatasetID,ProcessingPathID),

    foreign key(PrimaryDatasetID) references PrimaryDataset(PrimaryDatasetID) on update SET NULL on delete SET NULL,
    foreign key(ProcessingPathID) references ProcessingPath(ProcessingPathID) on update SET NULL on delete SET NULL,
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID),

    CHECK(OpenForWriting IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE EventCollection
  (
    EventCollectionID         int,
    ProcessedDatasetID        int           not null,
    EventCollectionIndex      int           not null,
    PrimaryEventCollection    char(1)       not null,
    CompositeEventCollection  char(1)       not null,
    RunIDString               varchar(80),
    CreationDate              date,
    CreatedBy                 int,
    LastModificationDate      date,
    LastModifiedBy            int,

    primary key(EventCollectionID),
    unique(ProcessedDatasetID,EventCollectionIndex),

    foreign key(ProcessedDatasetID) references ProcessedDataset(ProcessedDatasetID) on update SET NULL on delete SET NULL,
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID),

    CHECK(PrimaryEventCollection IN ('y', 'n')),
    CHECK(CompositeEventCollection IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE PrimaryEventCollection
  (
    PrimaryECID                int,
    PointerToExternalParamsDB  int   not null,

    primary key(PrimaryECID),

    foreign key(PrimaryECID) references EventCollection(EventCollectionID) on update CASCADE on delete CASCADE
  );

-- ======================================================================

CREATE TABLE CompositeEventCollection
  (
    CompositeECID         int,
    ChildECID             int,
    CreatedBy             int,
    CreationDate          date,
    LastModifiedBy        int,
    LastModificationDate  date,

    primary key(CompositeECID,ChildECID),
    unique(CompositeECID,ChildECID),

    foreign key(CompositeECID) references EventCollection(EventCollectionID) on update CASCADE on delete CASCADE,
    foreign key(ChildECID) references EventCollection(EventCollectionID) on update SET NULL on delete SET NULL,
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE AnalysisCollectionData
  (
    AnalysisCollectionDataID  int,
    AnalysisDatasetID         int             unique,
    EventCollectionID         int             unique,
    NumberOfEvents            numeric(10,4)   not null,
    EstimatedLuminosity       varchar(80),
    AnalysisCollectionStatus  int             not null,
    ValidationStatus          int             not null,
    COBRACollectionName       varchar(256)    not null,
    StartingRunNumber         int,
    EndingRunNumber           int,
    ContinuousRunRange        char(1),
    StartingEventNumber       int,
    EndingEventNumber         int,
    OtherQueryableMetadata    int,
    CreationDate              date,
    CreatedBy                 int,
    LastModificationDate      date,
    LastModifiedBy            int,

    primary key(AnalysisCollectionDataID),
    unique(AnalysisDatasetID,EventCollectionID),

    foreign key(AnalysisDatasetID) references AnalysisDataset(AnalysisDatasetID) on update CASCADE on delete CASCADE,
    foreign key(EventCollectionID) references EventCollection(EventCollectionID) on update CASCADE on delete CASCADE,
    foreign key(AnalysisCollectionStatus) references AnalysisCollectionStatus(AnalysisCollectionStatusID),
    foreign key(ValidationStatus) references ValidationStatus(ValidationStatusID),
    foreign key(OtherQueryableMetadata) references ParameterSet(ParameterSetID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID),

    CHECK(ContinuousRunRange IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE EvCollAnData
  (
    EvCollAnDataID        int,
    AnalysisDatasetID     int    not null,
    EventCollectionID     int    not null,
    CreationDate          date,
    CreatedBy             int,
    LastModificationDate  date,
    LastModifiedBy        int,

    primary key(EvCollAnDataID),
    unique(AnalysisDatasetID,EventCollectionID),

    foreign key(AnalysisDatasetID) references AnalysisDataset(AnalysisDatasetID) on update CASCADE on delete CASCADE,
    foreign key(EventCollectionID) references EventCollection(EventCollectionID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE EvCollFile
  (
    EvCollFileID          int,
    EvCollID              int    not null,
    FileID                int    not null,
    CreationDate          date,
    CreatedBy             int,
    LastModificationDate  date,
    LastModifiedBy        int,

    primary key(EvCollFileID),
    unique(EvCollID,FileID),

    foreign key(EvCollID) references EventCollection(EventCollectionID) on update CASCADE on delete CASCADE,
    foreign key(FileID) references File(FileID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

