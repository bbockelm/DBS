prompt Application data

-- ======================================================================
create sequence seq_parameter_set;
create sequence seq_app_family;
create sequence seq_application;
create sequence seq_app_config;

-- ======================================================================
create table t_parameter_set
  (id				integer		not null,
   hash				varchar (1000)	not null,
   content			blob		not null);

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
  primary key (id)
  using index tablespace INDX01;

alter table t_parameter_set
  add constraint uq_parameter_set_hash
  unique (hash);

--
alter table t_app_family
  add constraint pk_app_family
  primary key (id)
  using index tablespace INDX01;

alter table t_app_family
  add constraint uq_app_family_name
  unique (name);

--
alter table t_application
  add constraint pk_application
  primary key (id)
  using index tablespace INDX01;

alter table t_application
  add constraint uq_application_key
  unique (executable, app_version, app_family);

alter table t_application
  add constraint fk_app_family
  foreign key (app_family) references t_app_family (id);

--
alter table t_app_config
  add constraint pk_app_config
  primary key (id)
  using index tablespace INDX01;

alter table t_app_config
  add constraint uq_app_config
  unique (application, parameter_set);

alter table t_app_config
  add constraint fk_app_config_app
  foreign key (application) references t_application (id);

alter table t_app_config
  add constraint fk_app_config_pset
  foreign key (parameter_set) references t_parameter_set (id);
