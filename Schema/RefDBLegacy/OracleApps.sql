-- ======================================================================
create sequence seq_collection_type;
create sequence seq_app_family;
create sequence seq_application;
create sequence seq_app_config;

-- ======================================================================
create table t_collection_type
  (id				integer		not null,
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

create table t_app_family
  (id				integer		not null,
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

create table t_application
  (id				integer		not null,
   executable			varchar (1000)	not null,
   app_version			varchar (1000)	not null,
   app_family			integer		not null,
   input_type			integer		not null,
   output_type			integer		not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

create table t_app_config
  (id				integer		not null,
   application			integer		not null,
   conditions_version		varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

-- ======================================================================
alter table t_collection_type
  add constraint pk_collection_type
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_collection_type
  add constraint uq_collection_type_name
  unique (name);

alter table t_collection_type
  add constraint fk_collection_type_creatby
  foreign key (created_by) references t_person (id);

alter table t_collection_type
  add constraint fk_collection_type_modifby
  foreign key (modified_by) references t_person (id);

--
alter table t_app_family
  add constraint pk_app_family
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_app_family
  add constraint uq_app_family_name
  unique (name);

alter table t_app_family
  add constraint fk_app_family_creatby
  foreign key (created_by) references t_person (id);

alter table t_app_family
  add constraint fk_app_family_modifby
  foreign key (modified_by) references t_person (id);

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

alter table t_application
  add constraint fk_application_creatby
  foreign key (created_by) references t_person (id);

alter table t_application
  add constraint fk_application_modifby
  foreign key (modified_by) references t_person (id);

--
alter table t_app_config
  add constraint pk_app_config
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_app_config
  add constraint uq_app_config
  unique (application, conditions_version);

alter table t_app_config
  add constraint fk_app_config_app
  foreign key (application) references t_application (id);

alter table t_app_config
  add constraint fk_app_config_creatby
  foreign key (created_by) references t_person (id);

alter table t_app_config
  add constraint fk_app_config_modifby
  foreign key (modified_by) references t_person (id);

-- ======================================================================
create index ix_collection_type_creatby
  on t_collection_type (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_collection_type_modifby
  on t_collection_type (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_app_family_creatby
  on t_app_family (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_app_family_modifby
  on t_app_family (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_application_intype
  on t_application (input_type)
  tablespace CMS_DBS_INDX01;

create index ix_application_outtype
  on t_application (output_type)
  tablespace CMS_DBS_INDX01;

create index ix_application_creatby
  on t_application (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_application_modifby
  on t_application (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_app_config_creatby
  on t_app_config (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_app_config_modifby
  on t_app_config (modified_by)
  tablespace CMS_DBS_INDX01;
