-- ======================================================================
create sequence seq_block;
create sequence seq_file_status;
create sequence seq_file_type;
create sequence seq_file;
create sequence seq_evcoll_file;

-- ======================================================================
create table t_block_status
  (id				integer		not null,
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

create table t_block
  (id				integer		not null,
   processed_dataset		integer		not null,
   status			integer		not null,
   files			integer		not null,
   bytes			integer		not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

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
   guid				varchar (1000)	/* not null */,
   logical_name			varchar (1000)	not null,
   checksum			varchar (1000)	not null,
   filesize			integer		not null,
   status			integer         not null,
   type				integer         not null,
   inblock			integer		not null,
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
alter table t_block_status
  add constraint pk_block_status
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_block_status
  add constraint uq_block_status_key
  unique (name);

alter table t_block_status
  add constraint fk_block_status_creatby
  foreign key (created_by) references t_person (id);

alter table t_block_status
  add constraint fk_block_status_modifby
  foreign key (modified_by) references t_person (id);

--
alter table t_block
  add constraint pk_block
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_block
  add constraint fk_block_status
  foreign key (status) references t_block_status (id);

alter table t_block
  add constraint fk_block_creatby
  foreign key (created_by) references t_person (id);

alter table t_block
  add constraint fk_block_modifby
  foreign key (modified_by) references t_person (id);

--
alter table t_file_status
  add constraint pk_file_status
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_file_status
  add constraint uq_file_status_key
  unique (name);

alter table t_file_status
  add constraint fk_file_status_creatby
  foreign key (created_by) references t_person (id);

alter table t_file_status
  add constraint fk_file_status_modifby
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
  add constraint fk_file_type_creatby
  foreign key (created_by) references t_person (id);

alter table t_file_type
  add constraint fk_file_type_modifby
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
  add constraint fk_file_inblock
  foreign key (status) references t_block (id);

alter table t_file
  add constraint fk_file_creatby
  foreign key (created_by) references t_person (id);

alter table t_file
  add constraint fk_file_modifby
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
  /* on update cascade */ on delete cascade;

alter table t_evcoll_file
  add constraint fk_evcoll_file_fileid
  foreign key (fileid) references t_file (id);

alter table t_evcoll_file
  add constraint fk_evcoll_file_creatby
  foreign key (created_by) references t_person (id);

alter table t_evcoll_file
  add constraint fk_evcoll_file_modifby
  foreign key (modified_by) references t_person (id);

-- ======================================================================
create index ix_block_status_creatby
  on t_block_status (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_block_status_modifby
  on t_block_status (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_block_status
  on t_block (status)
  tablespace CMS_DBS_INDX01;

create index ix_block_creatby
  on t_block (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_block_modifby
  on t_block (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_file_status_creatby
  on t_file_status (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_file_status_modifby
  on t_file_status (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_file_type_creatby
  on t_file_type (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_file_type_modifby
  on t_file_type (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_file_status
  on t_file (status)
  tablespace CMS_DBS_INDX01;

create index ix_file_type
  on t_file (type)
  tablespace CMS_DBS_INDX01;

create index ix_file_inblock
  on t_file (inblock)
  tablespace CMS_DBS_INDX01;

create index ix_file_creatby
  on t_file (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_file_modifby
  on t_file (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_evcoll_file_creatby
  on t_evcoll_file (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_evcoll_file_modifby
  on t_evcoll_file (modified_by)
  tablespace CMS_DBS_INDX01;
