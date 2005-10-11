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
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

create table t_primary_dataset
  (id				integer		not null,
   name				varchar (1000)	not null,
   description			integer		not null,
   physics_group		integer		not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

create table t_processing_path
  (id				integer		not null,
   parent			integer		/* not null? */,
   app_config			integer		not null,
   full_path			varchar (1000)	not null,
   data_tier			integer		not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

create table t_processed_dataset
  (id				integer		not null,
   primary_dataset		integer		not null,
   processing_path		integer		not null,
   name				varchar (1000)	not null,
   is_open			char (1)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

create table t_event_collection
  (id				integer		not null,
   processed_dataset		integer		not null,
   collection_index		integer		not null,
   is_primary			char (1)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

create table t_analysis_dataset
  (id				integer		not null,
   processed_dataset		integer		not null,
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

create table t_anads_data
  (id				integer		not null,
   analysis_dataset		integer		not null,
   event_collection		integer		not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

-- ======================================================================
alter table t_data_tier
  add constraint pk_data_tier
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_data_tier
  add constraint uq_data_tier_name
  unique (name);

alter table t_data_tier
  add constraint fk_data_tier_creatby
  foreign key (created_by) references t_person (id);

alter table t_data_tier
  add constraint fk_data_tier_modifby
  foreign key (modified_by) references t_person (id);

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
  unique (name, description, physics_group);

alter table t_primary_dataset
  add constraint fk_primary_dataset_desc
  foreign key (description) references t_desc_primary (id);

alter table t_primary_dataset
  add constraint fk_primary_dataset_physicsgrp
  foreign key (physics_group) references t_physics_group (id);

alter table t_primary_dataset
  add constraint fk_primary_dataset_creatby
  foreign key (created_by) references t_person (id);

alter table t_primary_dataset
  add constraint fk_primary_dataset_modifby
  foreign key (modified_by) references t_person (id);

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

alter table t_processing_path
  add constraint fk_processing_path_creatby
  foreign key (created_by) references t_person (id);

alter table t_processing_path
  add constraint fk_processing_path_modifby
  foreign key (modified_by) references t_person (id);

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

alter table t_processed_dataset
  add constraint fk_processed_dataset_creatby
  foreign key (created_by) references t_person (id);

alter table t_processed_dataset
  add constraint fk_processed_dataset_modifby
  foreign key (modified_by) references t_person (id);

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

alter table t_event_collection
  add constraint fk_event_collection_creatby
  foreign key (created_by) references t_person (id);

alter table t_event_collection
  add constraint fk_event_collection_modifby
  foreign key (modified_by) references t_person (id);

--
alter table t_analysis_dataset
  add constraint pk_analysis_dataset
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_analysis_dataset
  add constraint fk_analysis_dataset_dataset
  foreign key (processed_dataset) references t_processed_dataset (id);

alter table t_analysis_dataset
  add constraint fk_analysis_dataset_creatby
  foreign key (created_by) references t_person (id);

alter table t_analysis_dataset
  add constraint fk_analysis_dataset_modifby
  foreign key (modified_by) references t_person (id);

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

alter table t_anads_data
  add constraint fk_anads_data_creatby
  foreign key (created_by) references t_person (id);

alter table t_anads_data
  add constraint fk_anads_data_modifby
  foreign key (modified_by) references t_person (id);

-- ======================================================================
create index ix_primary_dataset_creatby
  on t_primary_dataset (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_primary_dataset_modifby
  on t_primary_dataset (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_data_tier_creatby
  on t_data_tier (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_data_tier_modifby
  on t_data_tier (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_processing_path_tier
  on t_processing_path (data_tier)
  tablespace CMS_DBS_INDX01;

create index ix_processing_path_creatby
  on t_processing_path (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_processing_path_modifby
  on t_processing_path (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_processed_dataset_creatby
  on t_processed_dataset (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_processed_dataset_modifby
  on t_processed_dataset (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_event_collection_creatby
  on t_event_collection (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_event_collection_modifby
  on t_event_collection (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_analysis_dataset_creatby
  on t_analysis_dataset (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_analysis_dataset_modifby
  on t_analysis_dataset (modified_by)
  tablespace CMS_DBS_INDX01;


--
create index ix_anads_data_creatby
  on t_anads_data (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_anads_data_modifby
  on t_anads_data (modified_by)
  tablespace CMS_DBS_INDX01;

