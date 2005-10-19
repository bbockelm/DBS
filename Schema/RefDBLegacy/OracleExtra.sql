-- ======================================================================
create sequence seq_info_anads;
create sequence seq_info_evcoll;

-- ======================================================================
create table t_info_anads
  (analysis_dataset		integer		not null,
   events			integer		not null,
   estimated_luminosity		varchar (1000),
   status			integer		/* not null */,
   validation_status		integer		/* not null */);

create table t_info_evcoll
  (event_collection		integer		not null,
   events			integer		not null,
   estimated_luminosity		varchar (1000),
   status			integer		/* not null */,
   validation_status		integer		/* not null */,
   name				varchar (1000)	not null);

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

-- ======================================================================
create index ix_info_anads_status
  on t_info_anads (status)
  tablespace CMS_DBS_INDX01;

create index ix_info_anads_valid
  on t_info_anads (validation_status)
  tablespace CMS_DBS_INDX01;

--
create index ix_info_evcoll_status
  on t_info_evcoll (status)
  tablespace CMS_DBS_INDX01;

create index ix_info_evcoll_valid
  on t_info_evcoll (validation_status)
  tablespace CMS_DBS_INDX01;
