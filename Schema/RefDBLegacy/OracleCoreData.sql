-- ======================================================================
create sequence seq_data_tier;
create sequence seq_primary_dataset;
create sequence seq_processing_path;
create sequence seq_processed_dataset;
create sequence seq_event_collection;

-- ======================================================================
create table t_data_tier
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_primary_dataset
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_processing_path
  (id				integer		not null,
   parent			integer		/* not null? */,
   app_config			integer		not null,
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
   collection_index		integer		not null);

-- ======================================================================
alter table t_data_tier
  add constraint pk_data_tier
  primary key (id)
  using index tablespace INDX01;

alter table t_data_tier
  add constraint uq_data_tier_name
  unique (name);

--
alter table t_primary_dataset
  add constraint pk_primary_dataset
  primary key (id)
  using index tablespace INDX01;

alter table t_primary_dataset
  add constraint uq_primary_dataset_name
  unique (name);

--
alter table t_processing_path
  add constraint pk_processing_path
  primary key (id)
  using index tablespace INDX01;

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
  using index tablespace INDX01;

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
  using index tablespace INDX01;

alter table t_event_collection
  add constraint uq_event_collection_key
  unique (processed_dataset, collection_index);

alter table t_event_collection
  add constraint fk_event_collection_dataset
  foreign key (processed_dataset) references t_processed_dataset (id)
  /* on update set null */ on delete set null;

-- ======================================================================
create index ix_processing_path_tier
  on t_processing_path (data_tier)
  tablespace INDX01;
