-- ======================================================================
-- ===   Sql Script for Database : DBS Prototype 0
-- ===
-- ===   $Id:$
-- ======================================================================

CREATE TABLE SchemaRevision 
  (
    Revision              varchar(255)
  );

INSERT INTO SchemaRevision VALUES ('$Revision:$');

CREATE TABLE Person
  (
    PersonID              int,
    Name                  varchar(80)    unique not null,
    DistinguishedName     varchar(255)   unique not null,
    ContactInfo           varchar(255)   not null,
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
    RoleName              varchar(80)    unique not null,
    RoleDescription       varchar(255)   not null,
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
    AnalysisDatasetTypeName        varchar(80)    unique not null,
    AnalysisDatasetTypeAnnotation  varchar(255)   not null,
    CreatedBy                      int,
    CreationDate                   date,
    LastModifiedBy                 int,
    LastModificationDate           date,

    primary key(AnalysisDatasetTypeID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE QueryableParameterSet
  (
    QueryableParameterSetID  int,
    QPSName                  varchar(80)    not null,
    QPSVersion               varchar(80)    not null,
    QPSAnnotation            varchar(255)   not null,
    Composite                char(1)        not null,
    CreationDate             date,
    CreatedBy                int,
    LastModificationDate     date,
    LastModifiedBy           int,

    primary key(QueryableParameterSetID),
    unique(QPSName,QPSVersion),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID),

    CHECK(Composite IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE ParameterBinding
  (
    ParameterBindingID    int,
    ParameterName         varchar(80)    not null,
    ParameterValue        varchar(255)   not null,
    ExternalDataType      varchar(80),
    GPGID                 int            not null,
    CreatedBy             int,
    CreationDate          date,
    LastModifiedBy        int,
    LastModificationDate  date,

    primary key(ParameterBindingID),
    unique(ParameterName,GPGID),

    foreign key(GPGID) references QueryableParameterSet(QueryableParameterSetID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE TriggerPathDescription
  (
    TriggerPathDescriptionID  int,
    TriggerPathDescription    varchar(255)   unique not null,
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
    MCChannelDescription  varchar(255)   not null,
    MCProduction          varchar(255),
    MCDecayChain          varchar(255),
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

CREATE TABLE CompositeQueryableParameterSet
  (
    CompositeQueryableParameterSetID  int,
    ChildParameterSetID               int,

    primary key(CompositeQueryableParameterSetID,ChildParameterSetID),

    foreign key(CompositeQueryableParameterSetID) references QueryableParameterSet(QueryableParameterSetID) on update CASCADE on delete CASCADE,
    foreign key(ChildParameterSetID) references QueryableParameterSet(QueryableParameterSetID)
  );

-- ======================================================================

CREATE TABLE AnalysisDataset
  (
    AnalysisDatasetID          int,
    AnalysisDatasetAnnotation  varchar(255)   not null,
    AnalysisDatasetTypeID      int            not null,
    CompositeAnalysisDataset   char(1)        not null,
    AuxilliaryPOOLCatalog      varchar(255),
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
    StreamID              int            not null,
    StreamName            varchar(80)    unique not null,
    StreamAnnotation      varchar(255)   not null,
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

CREATE TABLE DataTier
  (
    LastModifiedBy        int,
    LastModificationDate  date,
    CreationDate          date,
    CreatedBy             int,
    DataTierID            int,
    DataTierName          varchar(255)   unique,

    primary key(DataTierID),

    foreign key(LastModifiedBy) references Person(PersonID),
    foreign key(CreatedBy) references Person(PersonID)
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

CREATE TABLE AnalysisDatasetStatus
  (
    AnalysisDatasetStatusID   int,
    AnalysisCollectionStatus  varchar(80)   unique not null,
    CreationDate              date,
    CreatedBy                 int,
    LastModificationDate      date,
    LastModifiedBy            int,

    primary key(AnalysisDatasetStatusID),

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

CREATE TABLE EventCollectionStatus
  (
    EventCollectionStatusID   int,
    AnalysisCollectionStatus  varchar(80)   unique not null,
    CreationDate              date,
    CreatedBy                 int,
    LastModificationDate      date,
    LastModifiedBy            int,

    primary key(EventCollectionStatusID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE RunQuality
  (
    RunQualityID          int,
    RunQualityName        varchar(80)   unique not null,
    CreationDate          date,
    CreatedBy             int,
    LastModificationDate  date,
    LastModifiedBy        int,

    primary key(RunQualityID),

    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE Block
  (
    BlockID               int,
    Size                  numeric(10,4),
    Checksum              numeric(10,4),
    OpenForWriting        char(1)         not null,
    CreatedBy             int,
    CreationDate          date,
    LastModifiedBy        int,
    LastModificationDate  date,

    primary key(BlockID),

    foreign key(BlockID) references AnalysisDataset(AnalysisDatasetID) on update CASCADE on delete CASCADE,
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID),

    CHECK(OpenForWriting IN ('y', 'n'))
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

CREATE TABLE AnalysisDatasetParentage
  (
    AnalysisDatasetParentageID  int,
    ChildAnalysisDatasetID      int,
    CreatedBy                   int,
    CreationDate                date,
    LastModifiedBy              int,
    LastModificationDate        date,

    primary key(AnalysisDatasetParentageID,ChildAnalysisDatasetID),

    foreign key(AnalysisDatasetParentageID) references AnalysisDataset(AnalysisDatasetID) on update CASCADE on delete CASCADE,
    foreign key(ChildAnalysisDatasetID) references AnalysisDataset(AnalysisDatasetID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE PrimaryDatasetDescription
  (
    AbstractDatasetDescriptionID  int,
    TriggerDescriptionID          int,
    MCChannelDescriptionID        int,
    MCDataset                     char(1)   not null,
    CreatedBy                     int,
    CreationDate                  date,
    LastModifiedBy                int,
    LastModificationDate          date,

    primary key(AbstractDatasetDescriptionID),
    unique(TriggerDescriptionID,MCChannelDescriptionID),

    foreign key(TriggerDescriptionID) references TriggerPathDescription(TriggerPathDescriptionID),
    foreign key(MCChannelDescriptionID) references MCDescription(MCDescriptionID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID),

    CHECK(MCDataset IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE AnalysisDatasetData
  (
    AnalysisDatasetDataID   int,
    AnalysisDatasetID       int             unique,
    NumberOfEvents          numeric(10,4)   not null,
    EstimatedLuminosity     varchar(80),
    AnalysisDatasetStatus   int             not null,
    ValidationStatus        int             not null,
    COBRAAccessorName       varchar(255),
    OtherQueryableMetadata  int,
    CreationDate            date,
    CreatedBy               int,
    LastModificationDate    date,
    LastModifiedBy          int,

    primary key(AnalysisDatasetDataID),

    foreign key(AnalysisDatasetID) references AnalysisDataset(AnalysisDatasetID) on update CASCADE on delete CASCADE,
    foreign key(AnalysisDatasetStatus) references AnalysisDatasetStatus(AnalysisDatasetStatusID),
    foreign key(ValidationStatus) references ValidationStatus(ValidationStatusID),
    foreign key(OtherQueryableMetadata) references QueryableParameterSet(QueryableParameterSetID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE File
  (
    FileID                int,
    LogicalFileName       varchar(255)   unique not null,
    Checksum              varchar(255)   not null,
    Size                  varchar(255)   not null,
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
    PrimaryDatasetID              int,
    AbstractDatasetName           varchar(80)    unique not null,
    COBRADatasetName              varchar(255)   unique not null,
    AbstractDatasetAnnotation     varchar(255)   not null,
    AbstractDatasetDescriptionID  int            not null,
    StreamID                      int            not null,
    PhysicsGroupID                int            not null,
    OpenForWriting                char(1)        not null,
    StartDate                     date,
    EndDate                       date,
    CreatedBy                     int,
    CreationDate                  date,
    LastModificationDate          date,
    LastModifiedBy                int,

    primary key(PrimaryDatasetID),
    unique(AbstractDatasetName,COBRADatasetName,AbstractDatasetDescriptionID,StreamID,PhysicsGroupID),

    foreign key(AbstractDatasetDescriptionID) references PrimaryDatasetDescription(AbstractDatasetDescriptionID),
    foreign key(StreamID) references Stream(StreamID) on update SET NULL on delete SET NULL,
    foreign key(PhysicsGroupID) references PhysicsGroup(PhysicsGroupID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID),

    CHECK(OpenForWriting IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE Run
  (
    RunID                 int,
    RunNumber             smallint   unique not null,
    RunQuality            int        not null,
    FirstEventNumber      smallint   not null,
    LastEventNumber       smallint   not null,
    StartOfRun            date       not null,
    EndOfRun              date       not null,
    CreatedBy             int,
    CreationDate          date,
    LastModifiedBy        int,
    LastModificationDate  date,

    primary key(RunID),

    foreign key(RunQuality) references RunQuality(RunQualityID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE ApplicationConfiguration
  (
    ApplicationConfigurationID  int,
    ApplicationID               int           not null,
    ParameterSetID              int           not null,
    CalibrationVersionTag       varchar(80)   not null,
    ConditionsVersionTag        varchar(80)   not null,
    CreatedBy                   int,
    CreationDate                date,
    LastModificationDate        date,
    LastModifiedBy              int,

    primary key(ApplicationConfigurationID),
    unique(ApplicationID,ParameterSetID,CalibrationVersionTag,ConditionsVersionTag),

    foreign key(ApplicationID) references Application(ApplicationID),
    foreign key(ParameterSetID) references QueryableParameterSet(QueryableParameterSetID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE ProcessingPath
  (
    ProcessingPathID            int,
    ParentProcessingPathID      int,
    ApplicationConfigurationID  int,
    AggregatedPath              varchar(255),
    DataTierID                  int,
    CreationDate                date,
    CreatedBy                   int,
    LastModificationDate        date,
    LastModifiedBy              int,

    primary key(ProcessingPathID),
    unique(ParentProcessingPathID,ApplicationConfigurationID),

    foreign key(ParentProcessingPathID) references ProcessingPath(ProcessingPathID),
    foreign key(ApplicationConfigurationID) references ApplicationConfiguration(ApplicationConfigurationID),
    foreign key(DataTierID) references DataTier(DataTierID),
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
    EventCollectionID       int,
    ProcessedDatasetID      int       not null,
    EventCollectionIndex    int       not null,
    PrimaryEventCollection  char(1)   not null,
    CreationDate            date,
    CreatedBy               int,
    LastModificationDate    date,
    LastModifiedBy          int,

    primary key(EventCollectionID),
    unique(ProcessedDatasetID,EventCollectionIndex),

    foreign key(ProcessedDatasetID) references ProcessedDataset(ProcessedDatasetID) on update SET NULL on delete SET NULL,
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID),

    CHECK(PrimaryEventCollection IN ('y', 'n'))
  );

-- ======================================================================

CREATE TABLE EventCollectionRun
  (
    EventCollectionRunID  int,
    EventCollectionID     int    not null,
    RunID                 int    not null,
    LastModifiedBy        int,
    LastModificationDate  date,
    CreationDate          date,
    CreatedBy             int,

    primary key(EventCollectionRunID),
    unique(EventCollectionID,RunID),

    foreign key(EventCollectionID) references EventCollection(EventCollectionID),
    foreign key(RunID) references Run(RunID),
    foreign key(LastModifiedBy) references Person(PersonID),
    foreign key(CreatedBy) references Person(PersonID)
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
    MemberECID            int,
    CreatedBy             int,
    CreationDate          date,
    LastModifiedBy        int,
    LastModificationDate  date,

    primary key(CompositeECID,MemberECID),
    unique(CompositeECID,MemberECID),

    foreign key(CompositeECID) references EventCollection(EventCollectionID) on update CASCADE on delete CASCADE,
    foreign key(MemberECID) references EventCollection(EventCollectionID) on update SET NULL on delete SET NULL,
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE EventCollectionParentage
  (
    ParentECID            int,
    ChildECID             int,
    CreatedBy             int,
    CreationDate          date,
    LastModifiedBy        int,
    LastModificationDate  date,

    primary key(ParentECID,ChildECID),
    unique(ParentECID,ChildECID),

    foreign key(ParentECID) references EventCollection(EventCollectionID) on update CASCADE on delete CASCADE,
    foreign key(ChildECID) references EventCollection(EventCollectionID) on update SET NULL on delete SET NULL,
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
  );

-- ======================================================================

CREATE TABLE EventCollectionData
  (
    EventCollectionDataID   int,
    EventCollectionID       int             unique,
    NumberOfEvents          numeric(10,4)   not null,
    EstimatedLuminosity     varchar(80),
    EventCollectionStatus   int             not null,
    ValidationStatus        int             not null,
    COBRACollectionName     varchar(255)    not null,
    FirstEventNumber        smallint,
    LastEventNumber         smallint,
    OtherQueryableMetadata  int,
    CreationDate            date,
    CreatedBy               int,
    LastModificationDate    date,
    LastModifiedBy          int,

    primary key(EventCollectionDataID),

    foreign key(EventCollectionID) references EventCollection(EventCollectionID) on update CASCADE on delete CASCADE,
    foreign key(EventCollectionStatus) references EventCollectionStatus(EventCollectionStatusID),
    foreign key(ValidationStatus) references ValidationStatus(ValidationStatusID),
    foreign key(OtherQueryableMetadata) references QueryableParameterSet(QueryableParameterSetID),
    foreign key(CreatedBy) references Person(PersonID),
    foreign key(LastModifiedBy) references Person(PersonID)
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

