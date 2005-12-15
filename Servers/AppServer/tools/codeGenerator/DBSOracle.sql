-- ======================================================================
-- ===   Sql Script for Database : Oracle db
-- ===
-- === Build : 52
-- ======================================================================

CREATE TABLE t_schema_revision
  (
    revision  varchar   not null
  );

-- ======================================================================

CREATE TABLE t_person
  (
    id                 integer   not null,
    name               varchar   unique not null,
    distinguised_name  varchar   not null,
    contactinfo        varchar   not null,

    primary key(id,distinguised_name)
  );

-- ======================================================================

CREATE TABLE t_physics_group
  (
    id        integer   not null,
    name      varchar   unique not null,
    convenor  integer,

    primary key(id),

    foreign key(convenor) references t_person(id)
  );

-- ======================================================================

CREATE TABLE t_collection_type
  (
    id    integer   not null,
    name  varchar   unique not null,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_app_family
  (
    id    integer   not null,
    name  varchar   unique not null,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_application
  (
    id           integer   not null,
    executable   varchar   not null,
    app_version  varchar   not null,
    app_family   integer   not null,
    input_type   integer   not null,
    output_type  integer   not null,

    primary key(id),
    unique(executable,app_version,app_family),

    foreign key(app_family) references t_app_family(id),
    foreign key(input_type) references t_collection_type(id),
    foreign key(output_type) references t_collection_type(id)
  );

-- ======================================================================

CREATE TABLE t_app_config
  (
    id                  integer   not null,
    application         integer   not null,
    parameter_set       varchar   not null,
    conditions_version  varchar   not null,

    primary key(id),
    unique(application,parameter_set,conditions_version),

    foreign key(application) references t_application(id)
  );

-- ======================================================================

CREATE TABLE t_desc_trigger
  (
    id           integer   not null,
    description  varchar   unique not null,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_desc_mc
  (
    id           integer   not null,
    description  varchar   unique not null,
    production   varchar   unique,
    decay_chain  varchar   unique,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_desc_primary
  (
    id            integer   not null,
    trigger_path  integer   not null,
    mc_channel    integer,
    is_mc_data    char,

    primary key(id),
    unique(trigger_path,mc_channel),

    foreign key(trigger_path) references t_desc_trigger(id),
    foreign key(mc_channel) references t_desc_mc(id)
  );

-- ======================================================================

CREATE TABLE t_data_tier
  (
    id    integer   not null,
    name  varchar   unique not null,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_primary_dataset
  (
    id             integer   not null,
    name           varchar   unique not null,
    description    integer   unique not null,
    physics_group  integer   unique not null,

    primary key(id),

    foreign key(description) references t_desc_primary(id),
    foreign key(physics_group) references t_physics_group(id)
  );

-- ======================================================================

CREATE TABLE t_processing_path
  (
    id          integer   not null,
    parent      integer,
    app_config  integer   not null,
    full_path   varchar   not null,
    data_tier   integer   not null,

    primary key(id),
    unique(parent,app_config),

    foreign key(parent) references t_processing_path(id),
    foreign key(app_config) references t_app_config(id),
    foreign key(data_tier) references t_data_tier(id)
  );

-- ======================================================================

CREATE TABLE t_processed_dataset
  (
    id               integer   not null,
    primary_dataset  integer   not null,
    processing_path  integer   not null,
    name             varchar   not null,
    is_open          char      not null,

    primary key(id),
    unique(primary_dataset,processing_path),

    foreign key(primary_dataset) references t_primary_dataset(id),
    foreign key(processing_path) references t_processing_path(id)
  );

-- ======================================================================

CREATE TABLE t_event_collection
  (
    id                 integer   not null,
    processed_dataset  integer   not null,
    collection_index   integer   not null,
    is_primary         char      not null,

    primary key(id),
    unique(processed_dataset,collection_index),

    foreign key(processed_dataset) references t_processed_dataset(id)
  );

-- ======================================================================

CREATE TABLE t_analysis_dataset
  (
    id                 integer   not null,
    processed_dataset  integer   unique not null,
    name               varchar   not null,

    primary key(id),

    foreign key(processed_dataset) references t_processed_dataset(id)
  );

-- ======================================================================

CREATE TABLE t_anads_data
  (
    id                integer   not null,
    analysis_dataset  integer   not null,
    event_collection  integer   not null,
    is_primary        char      not null,

    primary key(id),
    unique(analysis_dataset,event_collection),

    foreign key(analysis_dataset) references t_analysis_dataset(id) on delete CASCADE,
    foreign key(event_collection) references t_event_collection(id)
  );

-- ======================================================================

CREATE TABLE t_parentage_type
  (
    id    integer   not null,
    name  varchar   unique not null,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_evcoll_parentage
  (
    id      integer   not null,
    parent  integer   not null,
    child   integer   not null,
    type    integer   not null,
    primary key(id)

    unique(parent,child),

    foreign key(parent) references t_event_collection(id) on delete CASCADE,
    foreign key(child) references t_event_collection(id) on delete SET NULL,
    foreign key(type) references t_parentage_type(id) on delete CASCADE
  );

-- ======================================================================

CREATE TABLE t_block_status
  (
    id    integer   not null,
    name  varchar   unique not null,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_block
  (
    id                 integer   not null,
    processed_dataset  integer   not null,
    status             integer   not null,
    files              integer   not null,
    bytes              integer   not null,

    primary key(id),

    foreign key(processed_dataset) references t_processed_dataset(id),
    foreign key(status) references t_block_status(id)
  );

-- ======================================================================

CREATE TABLE t_file_status
  (
    id    integer   not null,
    name  varchar   unique not null,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_file_type
  (
    id    integer   not null,
    name  varchar   unique not null,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_file
  (
    id            integer   not null,
    guid          varchar,
    logical_name  varchar   unique not null,
    checksum      varchar   not null,
    filesize      varchar   not null,
    status        integer   not null,
    type          integer   not null,
    inblock       integer   not null,

    primary key(id),

    foreign key(status) references t_file_status(id),
    foreign key(type) references t_file_type(id),
    foreign key(inblock) references t_block(id)
  );

-- ======================================================================

CREATE TABLE t_evcoll_file
  (
    id      integer   not null,
    evcoll  integer   not null,
    fileid  integer   not null,

    primary key(id),
    unique(evcoll,fileid)
    foreign key(evcoll) references t_event_collection(id),
    foreign key(fileid) references t_file(id)
  );

-- ======================================================================

CREATE TABLE t_validation_status
  (
    id    integer   not null,
    name  varchar   unique not null,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_dataset_status
  (
    id    integer   not null,
    name  varchar   unique not null,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_evcoll_status
  (
    id    integer   not null,
    name  varchar   unique not null,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_run_quality
  (
    id    integer   not null,
    name  varchar   unique not null,

    primary key(id)
  );

-- ======================================================================

CREATE TABLE t_info_anads
  (
    analysis_dataset       integer   unique not null,
    events                 integer   unique not null,
    estimated_luminiosity  varchar,
    status                 integer   not null,
    validation_status      integer   not null,

    foreign key(analysis_dataset) references t_analysis_dataset(id) on delete CASCADE,
    foreign key(status) references t_dataset_status(id),
    foreign key(validation_status) references t_validation_status(id)
  );

-- ======================================================================

CREATE TABLE t_info_evcoll
  (
    event_collection      integer   unique not null,
    events                integer   not null,
    estimated_luminosity  varchar,
    validation_status     integer   not null,
    name                  varchar   not null,
    status                integer   not null,

    primary key(event_collection),

    foreign key(event_collection) references t_event_collection(id),
    foreign key(validation_status) references t_validation_status(id),
    foreign key(status) references t_evcoll_status(id)
  );

-- ======================================================================

CREATE TABLE t_run
  (
    id           integer   not null,
    run_number   integer   unique not null,
    run_quality  integer   not null,

    primary key(id),

    foreign key(run_quality) references t_run_quality(id)
  );

-- ======================================================================

CREATE TABLE t_evcoll_run
  (
    event_collection  integer   not null,
    run               integer   unique not null,

    primary key(event_collection),

    foreign key(event_collection) references t_event_collection(id),
    foreign key(run) references t_run(id)
  );

-- ======================================================================

CREATE TABLE t_object_history
  (
    object_type  varchar   not null,
    object_id    integer   not null,
    operation    varchar   not null,
    at           float     not null,
    person       integer   not null,
    mediator     integer   not null,

    foreign key(person) references t_person(id),
    foreign key(mediator) references t_person(id)
  );

-- ======================================================================

