-- ======================================================================
create sequence seq_run;
create sequence seq_info_anads;
create sequence seq_info_evcoll;
create sequence seq_evcoll_run;
create sequence seq_event_collection_ana_data;

-- ======================================================================

create table t_info_anads
  (analysis_dataset		integer		not null,
   events			integer		not null,
   estimated_luminosity		varchar (1000),
   status			integer		not null,
   validation_status		integer		not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

create table t_info_evcoll
  (event_collection		integer		not null,
   events			integer		not null,
   estimated_luminosity		varchar (1000),
   status			integer		not null,
   validation_status		integer		not null,
   -- parameter_set		integer		not null,
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

create table t_run
  (id		                integer		not null,
   run_number			integer		not null,
   run_quality			integer		not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

create table t_evcoll_run
  (event_collection		integer		not null,
   run				integer		not null);

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

alter table t_info_anads
  add constraint fk_info_anads_creatby
  foreign key (created_by) references t_person (id);

alter table t_info_anads
  add constraint fk_info_anads_modifby
  foreign key (modified_by) references t_person (id);

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

alter table t_info_evcoll
  add constraint fk_info_evcoll_creatby
  foreign key (created_by) references t_person (id);

alter table t_info_evcoll
  add constraint fk_info_evcoll_modifby
  foreign key (modified_by) references t_person (id);

--
alter table t_run
  add constraint pk_run
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_run
  add constraint uq_run_number
  unique (run_number);

alter table t_run
  add constraint fk_run_quality
  foreign key (run_quality) references t_run_quality (id);

alter table t_run
  add constraint fk_run_creatby
  foreign key (created_by) references t_person (id);

alter table t_run
  add constraint fk_run_modifby
  foreign key (modified_by) references t_person (id);

--
alter table t_evcoll_run
  add constraint pk_evcoll_run
  primary key (event_collection, run)
  using index tablespace CMS_DBS_INDX01;

alter table t_evcoll_run
  add constraint fk_evcoll_run_evcoll
  foreign key (event_collection) references t_event_collection (id);

alter table t_evcoll_run
  add constraint fk_evcoll_run_run
  foreign key (run) references t_run (id);

-- ======================================================================

create index ix_info_anads_status
  on t_info_anads (status)
  tablespace CMS_DBS_INDX01;

create index ix_info_anads_valid
  on t_info_anads (validation_status)
  tablespace CMS_DBS_INDX01;

create index ix_info_anads_creatby
  on t_info_anads (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_info_anads_modifby
  on t_info_anads (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_info_evcoll_status
  on t_info_evcoll (status)
  tablespace CMS_DBS_INDX01;

create index ix_info_evcoll_valid
  on t_info_evcoll (validation_status)
  tablespace CMS_DBS_INDX01;

create index ix_info_evcoll_creatby
  on t_info_evcoll (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_info_evcoll_modifby
  on t_info_evcoll (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_run_creatby
  on t_run (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_run_modifby
  on t_run (modified_by)
  tablespace CMS_DBS_INDX01;
