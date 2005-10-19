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
