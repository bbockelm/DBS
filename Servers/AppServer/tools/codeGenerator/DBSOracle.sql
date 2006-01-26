-- ======================================================================
create table t_schema_revision
  (revision			varchar (1000)	not null);

-- ======================================================================
create sequence seq_person;
create sequence seq_physics_group;

-- ======================================================================
create table t_person
  (id				integer		not null,
   name				varchar (1000)	not null,
   distinguished_name		varchar (1000)	not null,
   contact_info			varchar (1000)	not null);

create table t_physics_group
  (id				integer		not null,
   name				varchar (1000)	not null,
   convenor			integer		/* not null? */);

-- ======================================================================
alter table t_person
  add constraint pk_person
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_person
  add constraint uq_person_name
  unique (name);

alter table t_person
  add constraint uq_person_distinguished
  unique (distinguished_name);

--
alter table t_physics_group
  add constraint pk_physicsgroup
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_physics_group
  add constraint uq_physicsgroup_name
  unique (name);

alter table t_physics_group
  add constraint fk_physicsgroup_convener
  foreign key (convenor) references t_person (id);

-- ======================================================================
create index ix_physicsgroup_convener
  on t_physics_group (convenor)
  tablespace CMS_DBS_INDX01;
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
  on t_object_history (person)
  tablespace CMS_DBS_INDX01;

create index ix_object_history_mediator
  on t_object_history (mediator)
  tablespace CMS_DBS_INDX01;
-- ======================================================================
create sequence seq_collection_type;
create sequence seq_app_family;
create sequence seq_application;
create sequence seq_app_config;

-- ======================================================================
create table t_collection_type
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_app_family
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_application
  (id				integer		not null,
   executable			varchar (1000)	not null,
   app_version			varchar (1000)	not null,
   app_family			integer		not null,
   input_type			integer		/* not null */,
   output_type			integer		/* not null */);

create table t_app_config
  (id				integer		not null,
   application			integer		not null,
   parameter_set		varchar (1000)	not null,  -- FIXME!
   conditions_version 		varchar (1000)	/* not null */);

-- ======================================================================
alter table t_collection_type
  add constraint pk_collection_type
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_collection_type
  add constraint uq_collection_type_name
  unique (name);

--
alter table t_app_family
  add constraint pk_app_family
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_app_family
  add constraint uq_app_family_name
  unique (name);

--
alter table t_application
  add constraint pk_application
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_application
  add constraint uq_application_key
  unique (executable, app_version, app_family);

alter table t_application
  add constraint fk_app_family
  foreign key (app_family) references t_app_family (id);

alter table t_application
  add constraint fk_application_intype
  foreign key (input_type) references t_collection_type (id);

alter table t_application
  add constraint fk_application_outtype
  foreign key (output_type) references t_collection_type (id);

--
alter table t_app_config
  add constraint pk_app_config
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_app_config
  add constraint uq_app_config
  unique (application, parameter_set, conditions_version);

alter table t_app_config
  add constraint fk_app_config_app
  foreign key (application) references t_application (id);

-- ======================================================================
create index ix_application_intype
  on t_application (input_type)
  tablespace CMS_DBS_INDX01;

create index ix_application_outtype
  on t_application (output_type)
  tablespace CMS_DBS_INDX01;
-- ======================================================================
create sequence seq_desc_trigger;
create sequence seq_desc_mc;
create sequence seq_desc_primary;

-- ======================================================================
create table t_desc_trigger
  (id				integer		not null,
   description			varchar (1000)  not null);

create table t_desc_mc
  (id				integer		not null,
   description			varchar (1000)  not null,
   production			varchar (1000),
   decay_chain			varchar (1000));

create table t_desc_primary
  (id				integer		not null,
   trigger_path			integer,
   mc_channel			integer,
   is_mc_data			char (1)	not null);

-- ======================================================================
alter table t_desc_trigger
  add constraint pk_desc_trigger
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_desc_trigger
  add constraint uq_desc_trigger_desc
  unique (description);

--
alter table t_desc_mc
  add constraint pk_desc_mc
  primary key (id);

alter table t_desc_mc
  add constraint uq_desc_desc_mc
  unique (description);
--
alter table t_desc_primary
  add constraint pk_desc_primary
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_desc_primary
  add constraint uq_desc_primary_key
  unique (trigger_path, mc_channel);

alter table t_desc_primary
  add constraint fk_desc_primary_trigger
  foreign key (trigger_path) references t_desc_trigger (id);

alter table t_desc_primary
  add constraint fk_desc_primary_mc
  foreign key (mc_channel) references t_desc_mc (id);

alter table t_desc_primary
  add constraint ck_desc_primary_ismc
  check (is_mc_data in ('y', 'n'));
-- ======================================================================
create sequence seq_data_tier;
create sequence seq_primary_dataset;
create sequence seq_processing_path;
create sequence seq_processed_dataset;
create sequence seq_event_collection;
create sequence seq_analysis_dataset;
create sequence seq_anads_data;

-- ======================================================================
create table t_data_tier
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_primary_dataset
  (id				integer		not null,
   name				varchar (1000)	not null,
   description			integer		not null,
   physics_group		integer		/* not null */);

create table t_processing_path
  (id				integer		not null,
   parent			integer		/* not null? */,
   app_config			integer		not null,
   full_path			varchar (1000)	/* not null */,
   data_tier			integer		not null);

create table t_processed_dataset
  (id				integer		not null,
   primary_dataset		integer		not null,
   processing_path		integer		not null,
   name				varchar (1000)	not null,
   is_open			char (1)	not null);

create table t_event_collection
  (id				integer		not null,
   processed_dataset		integer		not null,
   collection_index		integer		not null,
   is_primary			char (1)	/* not null */);

create table t_analysis_dataset
  (id				integer		not null,
   processed_dataset		integer		not null,
   name				varchar (1000)	not null);

create table t_anads_data
  (id				integer		not null,
   analysis_dataset		integer		not null,
   event_collection		integer		not null);

-- ======================================================================
alter table t_data_tier
  add constraint pk_data_tier
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_data_tier
  add constraint uq_data_tier_name
  unique (name);

--
alter table t_primary_dataset
  add constraint pk_primary_dataset
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_primary_dataset
  add constraint uq_primary_dataset_name
  unique (name);

alter table t_primary_dataset
  add constraint uq_primary_dataset_physics
  unique (name, description /* , physics_group */);

alter table t_primary_dataset
  add constraint fk_primary_dataset_desc
  foreign key (description) references t_desc_primary (id);

alter table t_primary_dataset
  add constraint fk_primary_dataset_physicsgrp
  foreign key (physics_group) references t_physics_group (id);

--
alter table t_processing_path
  add constraint pk_processing_path
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_processing_path
  add constraint uq_processing_path_key
  unique (parent, app_config);

alter table t_processing_path
  add constraint fk_processing_path_parent
  foreign key (parent) references t_processing_path (id);

alter table t_processing_path
  add constraint fk_processing_path_appcfg
  foreign key (app_config) references t_app_config (id);

alter table t_processing_path
  add constraint fk_processing_path_tier
  foreign key (data_tier) references t_data_tier (id);

--
alter table t_processed_dataset
  add constraint pk_processed_dataset
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_processed_dataset
  add constraint uq_processed_dataset_key
  unique (primary_dataset, processing_path);

alter table t_processed_dataset
  add constraint fk_processed_dataset_primary
  foreign key (primary_dataset) references t_primary_dataset (id)
  /* on update set null */ on delete set null;

alter table t_processed_dataset
  add constraint fk_processed_dataset_path
  foreign key (processing_path) references t_processing_path (id)
  /* on update set null */ on delete set null;

alter table t_processed_dataset
  add constraint ck_processed_dataset_open
  check (is_open in ('y', 'n'));

--
alter table t_event_collection
  add constraint pk_event_collection
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_event_collection
  add constraint uq_event_collection_key
  unique (processed_dataset, collection_index);

alter table t_event_collection
  add constraint fk_event_collection_dataset
  foreign key (processed_dataset) references t_processed_dataset (id)
  /* on update set null */ on delete set null;

alter table t_event_collection
  add constraint ck_event_collection_primary
  check (is_primary in ('y', 'n'));

--
alter table t_analysis_dataset
  add constraint pk_analysis_dataset
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_analysis_dataset
  add constraint fk_analysis_dataset_dataset
  foreign key (processed_dataset) references t_processed_dataset (id);

--
alter table t_anads_data
  add constraint pk_anads_data
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_anads_data
  add constraint uq_anads_data_key
  unique (analysis_dataset, event_collection);

alter table t_anads_data
  add constraint fk_anads_data_ds
  foreign key (analysis_dataset) references t_analysis_dataset (id)
  /* on update cascade */ on delete cascade;

alter table t_anads_data
  add constraint fk_anads_data_evc
  foreign key (event_collection) references t_event_collection (id);

-- ======================================================================
create index ix_processing_path_tier
  on t_processing_path (data_tier)
  tablespace CMS_DBS_INDX01;
-- ======================================================================
create sequence seq_parentage_type;
create sequence seq_evcoll_parentage;

-- ======================================================================
create table t_parentage_type
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_evcoll_parentage
  (id				integer		not null,
   parent			integer		not null,
   child			integer		not null,
   type				integer		not null);

-- ======================================================================
alter table t_parentage_type
  add constraint pk_parentage_type
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_parentage_type
  add constraint uq_parentage_type_name
  unique (name);

--
alter table t_evcoll_parentage
  add constraint pk_evcoll_parentage
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_evcoll_parentage
  add constraint uq_evcoll_parentage
  unique (parent, child);

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_type
  foreign key (type) references t_parentage_type (id)
  /* on update cascade */ on delete cascade;

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_parent
  foreign key (parent) references t_event_collection (id)
  /* on update cascade */ on delete cascade;

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_child
  foreign key (child) references t_event_collection (id)
  /* on update set null */ on delete set null;

-- ======================================================================
create index ix_evcoll_parentage_type
  on t_evcoll_parentage (type)
  tablespace CMS_DBS_INDX01;
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
   processed_dataset		integer		not null,
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
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_block_status
  add constraint uq_block_status_key
  unique (name);

--
alter table t_block
  add constraint pk_block
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_block
  add constraint fk_block_status
  foreign key (status) references t_block_status (id);

--
alter table t_file_status
  add constraint pk_file_status
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_file_status
  add constraint uq_file_status_key
  unique (name);

--
alter table t_file_type
  add constraint pk_file_type
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_file_type
  add constraint uq_file_type
  unique (name);

--
alter table t_file
  add constraint pk_file
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

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
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_evcoll_file
  add constraint uq_evcoll_file_key
  unique (evcoll, fileid);

alter table t_evcoll_file
  add constraint fk_evcoll_file_evcoll
  foreign key (evcoll) references t_event_collection (id)
  /* on update cascade */ on delete cascade;

alter table t_evcoll_file
  add constraint fk_evcoll_file_fileid
  foreign key (fileid) references t_file (id);

-- ======================================================================
create index ix_block_status
  on t_block (status)
  tablespace CMS_DBS_INDX01;

--
create index ix_file_status
  on t_file (status)
  tablespace CMS_DBS_INDX01;

create index ix_file_type
  on t_file (type)
  tablespace CMS_DBS_INDX01;

create index ix_file_inblock
  on t_file (inblock)
  tablespace CMS_DBS_INDX01;
-- ======================================================================
create sequence seq_validation_status;
create sequence seq_dataset_status;
create sequence seq_evcoll_status;

-- ======================================================================
create table t_validation_status
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_dataset_status
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_evcoll_status
  (id				integer		not null,
   name				varchar (1000)	not null);

-- ======================================================================
alter table t_validation_status
  add constraint pk_validation_status
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_validation_status
  add constraint uq_validation_status_name
  unique (name);

--
alter table t_dataset_status
  add constraint pk_dataset_status
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_dataset_status
  add constraint uq_dataset_status_name
  unique (name);

--
alter table t_evcoll_status
  add constraint pk_evcoll_status
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_evcoll_status
  add constraint uq_evcoll_status_name
  unique (name);
-- ======================================================================
create sequence seq_info_anads;
create sequence seq_info_evcoll;

-- ======================================================================
create table t_info_anads
  (analysis_dataset		integer		not null,
   events			integer		not null,
   estimated_luminosity		varchar (1000),
   status			integer		/* not null */,
   validation_status		integer		/* not null */);

create table t_info_evcoll
  (event_collection		integer		not null,
   events			integer		not null,
   estimated_luminosity		varchar (1000),
   status			integer		/* not null */,
   validation_status		integer		/* not null */,
   name				varchar (1000)	not null);

-- ======================================================================
alter table t_info_anads
  add constraint pk_info_anads
  primary key (analysis_dataset)
  using index tablespace CMS_DBS_INDX01;

alter table t_info_anads
  add constraint fk_info_anads_ds
  foreign key (analysis_dataset) references t_analysis_dataset (id)
  /* on update cascade */ on delete cascade;

alter table t_info_anads
  add constraint fk_info_anads_status
  foreign key (status) references t_dataset_status (id);

alter table t_info_anads
  add constraint fk_info_anads_valid
  foreign key (validation_status) references t_validation_status (id);

--
alter table t_info_evcoll
  add constraint pk_info_evcoll
  primary key (event_collection)
  using index tablespace CMS_DBS_INDX01;

alter table t_info_evcoll
  add constraint fk_info_evcoll_ds
  foreign key (event_collection) references t_event_collection (id)
  /* on update cascade */ on delete cascade;

alter table t_info_evcoll
  add constraint fk_info_evcoll_status
  foreign key (status) references t_evcoll_status (id);

alter table t_info_evcoll
  add constraint fk_info_evcoll_valid
  foreign key (validation_status) references t_validation_status (id);

-- ======================================================================
create index ix_info_anads_status
  on t_info_anads (status)
  tablespace CMS_DBS_INDX01;

create index ix_info_anads_valid
  on t_info_anads (validation_status)
  tablespace CMS_DBS_INDX01;

--
create index ix_info_evcoll_status
  on t_info_evcoll (status)
  tablespace CMS_DBS_INDX01;

create index ix_info_evcoll_valid
  on t_info_evcoll (validation_status)
  tablespace CMS_DBS_INDX01;
