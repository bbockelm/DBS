create database if not exists DBSNEW;
use DBSNEW;

-- ======================================================================
create table t_schema_revision
  (revision			varchar (1000)	not null);

-- ======================================================================

-- ======================================================================
create table t_person
  (id				integer		not null AUTO_INCREMENT,
   name				varchar (1000)	not null,
   distinguished_name		varchar (1000)	not null,
   contact_info			varchar (1000)	not null,
   PRIMARY KEY (id)
);

-- ======================================================================

alter table t_person
  add constraint uq_person_name
  unique (name);

alter table t_person
  add constraint uq_person_distinguished
  unique (distinguished_name);
-- ======================================================================
create table t_object_history
  (object_type			varchar (32)	not null,
   object_id			integer		not null AUTO_INCREMENT,
   operation			varchar (32)	not null,
   at				float		not null,
   person			integer		not null,
   mediator			integer		not null,
   PRIMARY KEY (object_id)
);
   -- comment			varchar (1000)

-- ======================================================================
alter table t_object_history
  add constraint fk_object_history_person
  foreign key (person) references t_person (id);

alter table t_object_history
  add constraint fk_object_history_mediator
  foreign key (mediator) references t_person (id);

-- ======================================================================

-- ======================================================================

-- ======================================================================
create table t_app_family
  (id				integer		not null AUTO_INCREMENT,
   name				varchar (1000)	not null,
   PRIMARY KEY (id)
);

create table t_application
  (id				integer		not null AUTO_INCREMENT,
   executable			varchar (400)	not null,
   app_version			varchar (400)	not null,
   app_family			integer		not null,
   PRIMARY KEY (id)
);

create table t_app_config
  (id				integer		not null AUTO_INCREMENT,
   application			integer		not null,
   parameter_set		varchar (500)	not null,
   PRIMARY KEY (id)
);

-- ======================================================================

alter table t_app_family
  add constraint uq_app_family_name
  unique (name);

--

alter table t_application
  add constraint uq_application_key
  unique (executable, app_version, app_family);

alter table t_application
  add constraint fk_app_family
  foreign key (app_family) references t_app_family (id);

--

alter table t_app_config
  add constraint uq_app_config
  unique (application, parameter_set);

alter table t_app_config
  add constraint fk_app_config_app
  foreign key (application) references t_application (id);
-- ======================================================================

-- ======================================================================
create table t_data_tier
  (id				integer		not null AUTO_INCREMENT,
   name				varchar (1000)	not null,
   PRIMARY KEY (id)
);

create table t_primary_dataset
  (id				integer		not null AUTO_INCREMENT,
   name				varchar (1000)	not null,
   PRIMARY KEY (id)
);

create table t_processing_path
  (id				integer		not null AUTO_INCREMENT,
   parent			integer		/* not null? */,
   app_config			integer		not null,
   data_tier			integer		not null,
   PRIMARY KEY (id)
);

create table t_processed_dataset
  (id				integer		not null AUTO_INCREMENT,
   primary_dataset		integer		not null,
   processing_path		integer		not null,
   name				varchar (1000)	not null,
   is_open			char (1)	not null,
   PRIMARY KEY (id)
);

create table t_event_collection
  (id				integer		not null AUTO_INCREMENT,
   processed_dataset		integer		not null,
   collection_index		integer		not null,
   PRIMARY KEY (id)
);

-- ======================================================================

alter table t_data_tier
  add constraint uq_data_tier_name
  unique (name);

--

alter table t_primary_dataset
  add constraint uq_primary_dataset_name
  unique (name);

--

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
  add constraint uq_event_collection_key
  unique (processed_dataset, collection_index);

alter table t_event_collection
  add constraint fk_event_collection_dataset
  foreign key (processed_dataset) references t_processed_dataset (id)
  /* on update set null */ on delete set null;

-- ======================================================================
-- ======================================================================

-- ======================================================================
create table t_parentage_type
  (id				integer		not null AUTO_INCREMENT,
   name				varchar (1000)	not null,
   PRIMARY KEY (id)
);

create table t_evcoll_parentage
  (id				integer		not null AUTO_INCREMENT,
   parent			integer		,
   child			integer		not null,
   type				integer		not null,
   PRIMARY KEY (id)
);

-- ======================================================================

alter table t_parentage_type
  add constraint uq_parentage_type_name
  unique (name);

--

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
-- ======================================================================

-- ======================================================================
create table t_block_status
  (id				integer		not null AUTO_INCREMENT,
   name				varchar (1000)	not null,
   PRIMARY KEY (id)
);

create table t_block
  (id				integer		not null AUTO_INCREMENT,
   processed_dataset		integer		not null,
   status			integer		not null,
   files			integer		not null,
   bytes			integer		not null,
   PRIMARY KEY (id)
);

create table t_file_status
  (id				integer		not null AUTO_INCREMENT,
   name				varchar (1000)	not null,
   PRIMARY KEY (id)
);

create table t_file_type
  (id				integer		not null AUTO_INCREMENT,
   name				varchar (1000)	not null,
   PRIMARY KEY (id)
);

create table t_file
  (id				integer		not null AUTO_INCREMENT,
   guid				varchar (1000)	/* not null */,
   logical_name			varchar (1000)	not null,
   checksum			varchar (1000)	/* not null */,
   filesize			integer		/* not null */,
   status			integer         /* not null */,
   type				integer         not null,
   inblock			integer		not null,
   PRIMARY KEY (id)
);

create table t_evcoll_file
  (id				integer		not null AUTO_INCREMENT,
   evcoll			integer		not null,
   fileid			integer		not null,
   PRIMARY KEY (id)
);

-- ======================================================================

alter table t_block_status
  add constraint uq_block_status_key
  unique (name);

--

alter table t_block
  add constraint fk_block_status
  foreign key (status) references t_block_status (id);

alter table t_block
   add constraint fk_processed_dataset
   foreign key (processed_dataset) references t_processed_dataset (id);

--

alter table t_file_status
  add constraint uq_file_status_key
  unique (name);

--

alter table t_file_type
  add constraint uq_file_type
  unique (name);

--

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

--


-- ======================================================================

-- ======================================================================
create table t_info_evcoll
  (event_collection		integer		not null AUTO_INCREMENT,
   events			integer		not null,
   name				varchar (1000)	not null,
   PRIMARY KEY (event_collection)
);

-- ======================================================================

alter table t_info_evcoll
  add constraint fk_info_evcoll_ds
  foreign key (event_collection) references t_event_collection (id)
  /* on update cascade */ on delete cascade;
