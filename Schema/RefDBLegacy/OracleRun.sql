-- ======================================================================
create sequence seq_run_quality;
create sequence seq_run;
create sequence seq_evcoll_run;

-- ======================================================================
create table t_run_quality
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_run
  (id		                integer		not null,
   run_number			integer		not null,
   run_quality			integer		not null);

create table t_evcoll_run
  (event_collection		integer		not null,
   run				integer		not null);

-- ======================================================================
alter table t_run_quality
  add constraint pk_run_quality
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_run_quality
  add constraint uq_run_quality_name
  unique (name);

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
