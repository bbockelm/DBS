-- ======================================================================
create table t_schema_revision
  (revision			varchar (1000)	not null);
-- ======================================================================
create sequence seq_person;

-- ======================================================================
create table t_person
  (id				integer		not null,
   name				varchar (1000)	not null,
   distinguished_name		varchar (1000)	not null,
   contact_info			varchar (1000)	not null);

-- ======================================================================
alter table t_person
  add constraint pk_person
  primary key (id);

alter table t_person
  add constraint uq_person_name
  unique (name);

alter table t_person
  add constraint uq_person_distinguished
  unique (distinguished_name);
-- ======================================================================
create table t_object_history
  (object_type			varchar (32)	not null,
   object_id			integer		not null,
   operation			varchar (32)	not null,
   at				float		not null,
   person			integer		not null,
   mediator			integer		not null);
   -- comment			varchar (1000)

-- ======================================================================
alter table t_object_history
  add constraint fk_object_history_person
  foreign key (person) references t_person (id);

alter table t_object_history
  add constraint fk_object_history_mediator
  foreign key (mediator) references t_person (id);

-- ======================================================================
create index ix_object_history_person
  on t_object_history (person);

create index ix_object_history_mediator
  on t_object_history (mediator);
-- ======================================================================
create sequence seq_parameter_set;
create sequence seq_app_family;
create sequence seq_application;
create sequence seq_app_config;

-- ======================================================================
create table t_parameter_set
  (id				integer		not null,
   hash				varchar (1000)	not null,
   content			varchar (1000)	not null);
   --content			clob		not null);

create table t_app_family
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_application
  (id				integer		not null,
   executable			varchar (1000)	not null,
   app_version			varchar (1000)	not null,
   app_family			integer		not null);

create table t_app_config
  (id				integer		not null,
   application			integer		not null,
   parameter_set		integer		not null);

-- ======================================================================
alter table t_parameter_set
  add constraint pk_parameter_set
  primary key (id);

alter table t_parameter_set
  add constraint uq_parameter_set_hash
  unique (hash);

--
alter table t_app_family
  add constraint pk_app_family
  primary key (id);

alter table t_app_family
  add constraint uq_app_family_name
  unique (name);

--
alter table t_application
  add constraint pk_application
  primary key (id);

alter table t_application
  add constraint uq_application_key
  unique (executable, app_version, app_family);

alter table t_application
  add constraint fk_app_family
  foreign key (app_family) references t_app_family (id);

--
alter table t_app_config
  add constraint pk_app_config
  primary key (id);

alter table t_app_config
  add constraint uq_app_config
  unique (application, parameter_set);

alter table t_app_config
  add constraint fk_app_config_app
  foreign key (application) references t_application (id);

alter table t_app_config
  add constraint fk_app_config_pset
  foreign key (parameter_set) references t_parameter_set (id);
-- ======================================================================
create sequence seq_data_tier;
create sequence seq_parentage_type;
create sequence seq_primary_dataset;
create sequence seq_processing_name;
create sequence seq_processing;
create sequence seq_processed_dataset;
create sequence seq_event_collection;
create sequence seq_evcoll_parentage;

-- ======================================================================
create table t_data_tier
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_parentage_type
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_evcoll_status
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_primary_dataset
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_processing_name
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_processing
  (id				integer		not null,
   primary_dataset		integer		not null,
   app_config			integer		not null,
   name				integer		not null,
   is_open			char (1)	not null,
   input			integer);

create table t_processed_dataset
  (id				integer		not null,
   primary_dataset		integer		not null,
   data_tier			integer		not null,
   name				integer		not null,
   input			integer);

create table t_event_collection
  (id				integer		not null,
   processed_dataset		integer		not null,
   name				varchar (1000)	not null,
   events			integer		not null,
   status			integer);

create table t_evcoll_parentage
  (id				integer		not null,
   parent			integer		not null,
   child			integer		not null,
   type				integer		not null);

-- ======================================================================
alter table t_data_tier
  add constraint pk_data_tier
  primary key (id);

alter table t_data_tier
  add constraint uq_data_tier_name
  unique (name);

--
alter table t_parentage_type
  add constraint pk_parentage_type
  primary key (id);

alter table t_parentage_type
  add constraint uq_parentage_type_name
  unique (name);

--
alter table t_evcoll_status
  add constraint pk_evcoll_status
  primary key (id);

alter table t_evcoll_status
  add constraint uq_evcoll_status_name
  unique (name);

--
alter table t_primary_dataset
  add constraint pk_primary_dataset
  primary key (id);

alter table t_primary_dataset
  add constraint uq_primary_dataset_name
  unique (name);

--
alter table t_processing_name
  add constraint pk_processing_name
  primary key (id);

alter table t_processing_name
  add constraint uq_processing_name_name
  unique (name);

--
alter table t_processing
  add constraint pk_processing
  primary key (id);

alter table t_processing
  add constraint uq_processing_key
  unique (input, app_config);

alter table t_processing
  add constraint fk_processing_primary
  foreign key (primary_dataset) references t_primary_dataset (id);

alter table t_processing
  add constraint fk_processing_appcfg
  foreign key (app_config) references t_app_config (id);

alter table t_processing
  add constraint fk_processing_name
  foreign key (name) references t_processing_name (id);

alter table t_processing
  add constraint fk_processing_input
  foreign key (input) references t_processing (id);

alter table t_processing
  add constraint ck_processing_open
  check (is_open in ('y', 'n'));

--
alter table t_processed_dataset
  add constraint pk_processed_dataset
  primary key (id);

alter table t_processed_dataset
  add constraint uq_processed_dataset_key
  unique (primary_dataset, data_tier, name);

alter table t_processed_dataset
  add constraint fk_processed_dataset_primary
  foreign key (primary_dataset) references t_primary_dataset (id);

alter table t_processed_dataset
  add constraint fk_processed_dataset_tier
  foreign key (data_tier) references t_data_tier (id);

alter table t_processed_dataset
  add constraint fk_processed_dataset_name
  foreign key (name) references t_processing_name (id);

alter table t_processed_dataset
  add constraint fk_processed_dataset_input
  foreign key (input) references t_processed_dataset (id);

--
alter table t_event_collection
  add constraint pk_event_collection
  primary key (id);

alter table t_event_collection
  add constraint uq_event_collection_name
  unique (name);

alter table t_event_collection
  add constraint fk_event_collection_dataset
  foreign key (processed_dataset) references t_processed_dataset (id);

alter table t_event_collection
  add constraint fk_event_collection_status
  foreign key (status) references t_evcoll_status (id);

--
alter table t_evcoll_parentage
  add constraint pk_evcoll_parentage
  primary key (id);

alter table t_evcoll_parentage
  add constraint uq_evcoll_parentage
  unique (parent, child);

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_type
  foreign key (type) references t_parentage_type (id);

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_parent
  foreign key (parent) references t_event_collection (id);

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_child
  foreign key (child) references t_event_collection (id);

-- ======================================================================
create index ix_processing_primary
  on t_processing (primary_dataset);

create index ix_processing_name
  on t_processing (name);

--
create index ix_processed_dataset_tier
  on t_processed_dataset (data_tier);

--
create index ix_evcoll_parentage_type
  on t_evcoll_parentage (type);
-- ======================================================================
create sequence seq_block_status;
create sequence seq_block;
create sequence seq_file_status;
create sequence seq_file_type;
create sequence seq_file;
create sequence seq_evcoll_file;

-- ======================================================================
create table t_block_status
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_block
  (id				integer		not null,
   processing			integer		not null,
   status			integer		not null,
   files			integer		not null,
   bytes			integer		not null);

create table t_file_status
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_file_type
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_file
  (id				integer		not null,
   guid				varchar (1000)	/* not null */,
   logical_name			varchar (1000)	not null,
   checksum			varchar (1000)	/* not null */,
   filesize			integer		/* not null */,
   status			integer         /* not null */,
   type				integer         not null,
   inblock			integer		not null);

create table t_evcoll_file
  (id				integer		not null,
   evcoll			integer		not null,
   fileid			integer		not null);

-- ======================================================================
alter table t_block_status
  add constraint pk_block_status
  primary key (id);

alter table t_block_status
  add constraint uq_block_status_key
  unique (name);

--
alter table t_block
  add constraint pk_block
  primary key (id);

alter table t_block
  add constraint fk_block_status
  foreign key (status) references t_block_status (id);

alter table t_block
   add constraint fk_block_processing
   foreign key (processing) references t_processing (id);

--
alter table t_file_status
  add constraint pk_file_status
  primary key (id);

alter table t_file_status
  add constraint uq_file_status_key
  unique (name);

--
alter table t_file_type
  add constraint pk_file_type
  primary key (id);

alter table t_file_type
  add constraint uq_file_type
  unique (name);

--
alter table t_file
  add constraint pk_file
  primary key (id);

alter table t_file
  add constraint uq_file_lfn
  unique (logical_name);

alter table t_file
  add constraint fk_file_status
  foreign key (status) references t_file_status (id);

alter table t_file
  add constraint fk_file_type
  foreign key (type) references t_file_type (id);

alter table t_file
  add constraint fk_file_inblock
  foreign key (inblock) references t_block (id);

--
alter table t_evcoll_file
  add constraint pk_evcoll_file
  primary key (id);

alter table t_evcoll_file
  add constraint uq_evcoll_file_key
  unique (evcoll, fileid);

alter table t_evcoll_file
  add constraint fk_evcoll_file_evcoll
  foreign key (evcoll) references t_event_collection (id);

alter table t_evcoll_file
  add constraint fk_evcoll_file_fileid
  foreign key (fileid) references t_file (id);

-- ======================================================================
create index ix_block_status
  on t_block (status);

--
create index ix_file_status
  on t_file (status);

create index ix_file_type
  on t_file (type);

create index ix_file_inblock
  on t_file (inblock);
