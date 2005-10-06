-- ======================================================================
create sequence seq_validation_status;
create sequence seq_dataset_status;
create sequence seq_evcoll_status;
create sequence seq_runq_uality;

-- ======================================================================
create table t_validation_status
  (id				integer		not null,
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

create table t_dataset_status
  (id				integer		not null,
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

create table t_evcoll_status
  (id				integer		not null,
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

create table t_run_quality
  (id				integer		not null,
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

-- ======================================================================
alter table t_validation_status
  add constraint pk_validation_status
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_validation_status
  add constraint uq_validation_status_name
  unique (name);

alter table t_validation_status
  add constraint fk_validation_status_creat
  foreign key (created_by) references t_person (id);

alter table t_validation_status
  add constraint fk_validation_status_mod
  foreign key (modified_by) references t_person (id);

--
alter table t_dataset_status
  add constraint pk_dataset_status
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_dataset_status
  add constraint uq_dataset_status_name
  unique (name);

alter table t_dataset_status
  add constraint fk_dataset_status_creat
  foreign key (created_by) references t_person (id);

alter table t_dataset_status
  add constraint fk_dataset_status_mod
  foreign key (modified_by) references t_person (id);

--
alter table t_evcoll_status
  add constraint pk_evcoll_status
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_evcoll_status
  add constraint uq_evcoll_status_name
  unique (name);

alter table t_evcoll_status
  add constraint fk_evcoll_status_creat
  foreign key (created_by) references t_person (id);

alter table t_evcoll_status
  add constraint fk_evcoll_status_mod
  foreign key (modified_by) references t_person (id);

--
alter table t_run_quality
  add constraint pk_run_quality
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_run_quality
  add constraint uq_run_quality_name
  unique (name);

alter table t_run_quality
  add constraint fk_run_quality_creat
  foreign key (created_by) references t_person (id);

alter table t_run_quality
  add constraint fk_run_quality_mod
  foreign key (modified_by) references t_person (id);

-- ======================================================================
create index ix_validation_status_creat
  on t_validation_status (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_validation_status_mod
  on t_validation_status (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_dataset_status_creat
  on t_dataset_status (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_dataset_status_mod
  on t_dataset_status (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_evcoll_status_creat
  on t_evcoll_status (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_evcoll_status_mod
  on t_evcoll_status (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_run_quality_creat
  on t_run_quality (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_run_quality_mod
  on t_run_quality (modified_by)
  tablespace CMS_DBS_INDX01;
