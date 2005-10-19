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
