-- ======================================================================
create sequence seq_file_status;
create sequence seq_file_type;
create sequence seq_file;
create sequence seq_evcoll_file;

-- ======================================================================
create table t_file_status
  (id				integer		not null,
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

create table t_file_type
  (id				integer		not null,
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

create table t_file
  (id				integer		not null,
   logical_name			varchar (1000)	not null,
   checksum			varchar (1000)	not null,
   filesize			varchar (1000)	not null,
   status			integer         not null,
   type				integer         not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

create table t_evcoll_file
  (id				integer		not null,
   evcoll			integer		not null,
   fileid			integer		not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

-- ======================================================================
alter table t_file_status
  add constraint pk_file_status
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_file_status
  add constraint uq_file_status_key
  unique (name);

alter table t_file_status
  add constraint fk_file_status_creat
  foreign key (created_by) references t_person (id);

alter table t_file_status
  add constraint fk_file_status_mod
  foreign key (modified_by) references t_person (id);

--
alter table t_file_type
  add constraint pk_file_type
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_file_type
  add constraint uq_file_type
  unique (name);

alter table t_file_type
  add constraint fk_file_type_creat
  foreign key (created_by) references t_person (id);

alter table t_file_type
  add constraint fk_file_type_mod
  foreign key (modified_by) references t_person (id);

--
alter table t_file
  add constraint pk_file
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

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
  add constraint fk_file_creat
  foreign key (created_by) references t_person (id);

alter table t_file
  add constraint fk_file_mod
  foreign key (modified_by) references t_person (id);

--
alter table t_evcoll_file
  add constraint pk_evcoll_file
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_evcoll_file
  add constraint uq_evcoll_file_key
  unique (evcoll, fileid);

alter table t_evcoll_file
  add constraint fk_evcoll_file_evcoll
  foreign key (evcoll) references t_event_collection (id)
  /* on upfloat cascade */ on delete cascade;

alter table t_evcoll_file
  add constraint fk_evcoll_file_fileid
  foreign key (fileid) references t_file (id);

alter table t_evcoll_file
  add constraint fk_evcoll_file_creat
  foreign key (created_by) references t_person (id);

alter table t_evcoll_file
  add constraint fk_evcoll_file_mod
  foreign key (modified_by) references t_person (id);

-- ======================================================================
create index ix_file_status_creat
  on t_file_status (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_file_status_mod
  on t_file_status (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_file_type_creat
  on t_file_type (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_file_type_mod
  on t_file_type (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_file_status
  on t_file (status)
  tablespace CMS_DBS_INDX01;

create index ix_file_type
  on t_file (type)
  tablespace CMS_DBS_INDX01;

create index ix_file_creat
  on t_file (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_file_mod
  on t_file (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_evcoll_file_creat
  on t_evcoll_file (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_evcoll_file_mod
  on t_evcoll_file (modified_by)
  tablespace CMS_DBS_INDX01;
