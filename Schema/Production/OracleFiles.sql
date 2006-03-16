prompt File data

-- ======================================================================
create sequence seq_block_status;
create sequence seq_block;
create sequence seq_file_status;
create sequence seq_file_type;
create sequence seq_file;
create sequence seq_evcoll_file;

-- ======================================================================
create table t_block_status
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_block
  (id				integer		not null,
   processing			integer		not null,
   status			integer		not null,
   files			integer		not null,
   bytes			integer		not null);

create table t_file_status
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_file_type
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_file
  (id				integer		not null,
   guid				varchar (1000)	/* not null */,
   logical_name			varchar (1000)	not null,
   checksum			varchar (1000)	/* not null */,
   filesize			integer		/* not null */,
   status			integer         /* not null */,
   type				integer         not null,
   inblock			integer		not null);

create table t_evcoll_file
  (id				integer		not null,
   evcoll			integer		not null,
   fileid			integer		not null);

-- ======================================================================
alter table t_block_status
  add constraint pk_block_status
  primary key (id)
  using index tablespace INDX01;

alter table t_block_status
  add constraint uq_block_status_key
  unique (name);

--
alter table t_block
  add constraint pk_block
  primary key (id)
  using index tablespace INDX01;

alter table t_block
  add constraint fk_block_status
  foreign key (status) references t_block_status (id);

alter table t_block
   add constraint fk_block_processing
   foreign key (processing) references t_processing (id);

--
alter table t_file_status
  add constraint pk_file_status
  primary key (id)
  using index tablespace INDX01;

alter table t_file_status
  add constraint uq_file_status_key
  unique (name);

--
alter table t_file_type
  add constraint pk_file_type
  primary key (id)
  using index tablespace INDX01;

alter table t_file_type
  add constraint uq_file_type
  unique (name);

--
alter table t_file
  add constraint pk_file
  primary key (id)
  using index tablespace INDX01;

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
  foreign key (inblock) references t_block (id);

--
alter table t_evcoll_file
  add constraint pk_evcoll_file
  primary key (id)
  using index tablespace INDX01;

alter table t_evcoll_file
  add constraint uq_evcoll_file_key
  unique (evcoll, fileid);

alter table t_evcoll_file
  add constraint fk_evcoll_file_evcoll
  foreign key (evcoll) references t_event_collection (id);

alter table t_evcoll_file
  add constraint fk_evcoll_file_fileid
  foreign key (fileid) references t_file (id);

-- ======================================================================
create index ix_block_status
  on t_block (status)
  tablespace INDX01;

--
create index ix_file_status
  on t_file (status)
  tablespace INDX01;

create index ix_file_type
  on t_file (type)
  tablespace INDX01;

create index ix_file_inblock
  on t_file (inblock)
  tablespace INDX01;
