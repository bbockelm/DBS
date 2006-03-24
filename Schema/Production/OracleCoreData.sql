prompt Core data

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
   collection_index		integer		not null);

create table t_info_evcoll
  (event_collection		integer		not null,
   events			integer		not null,
   name				varchar (1000)	not null);

create table t_evcoll_parentage
  (id				integer		not null,
   parent			integer,
   child			integer		not null,
   type				integer		not null);

-- ======================================================================
alter table t_data_tier
  add constraint pk_data_tier
  primary key (id)
  using index tablespace INDX01;

alter table t_data_tier
  add constraint uq_data_tier_name
  unique (name);

--
alter table t_parentage_type
  add constraint pk_parentage_type
  primary key (id)
  using index tablespace INDX01;

alter table t_parentage_type
  add constraint uq_parentage_type_name
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
alter table t_processing_name
  add constraint pk_processing_name
  primary key (id)
  using index tablespace INDX01;

alter table t_processing_name
  add constraint uq_processing_name_name
  unique (name);

--
alter table t_processing
  add constraint pk_processing
  primary key (id)
  using index tablespace INDX01;

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
  primary key (id)
  using index tablespace INDX01;

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
  primary key (id)
  using index tablespace INDX01;

alter table t_event_collection
  add constraint uq_event_collection_key
  unique (processed_dataset, collection_index);

alter table t_event_collection
  add constraint fk_event_collection_dataset
  foreign key (processed_dataset) references t_processed_dataset (id);

--
alter table t_info_evcoll
  add constraint pk_info_evcoll
  primary key (event_collection)
  using index tablespace INDX01;

alter table t_info_evcoll
  add constraint fk_info_evcoll_ds
  foreign key (event_collection) references t_event_collection (id);

--
alter table t_evcoll_parentage
  add constraint pk_evcoll_parentage
  primary key (id)
  using index tablespace INDX01;

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
  on t_processing (primary_dataset)
  tablespace INDX01;

create index ix_processing_name
  on t_processing (name)
  tablespace INDX01;

--
create index ix_processed_dataset_tier
  on t_processed_dataset (data_tier)
  tablespace INDX01;

--
create index ix_evcoll_parentage_type
  on t_evcoll_parentage (type)
  tablespace INDX01;
