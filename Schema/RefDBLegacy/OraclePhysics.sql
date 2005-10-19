-- ======================================================================
create sequence seq_desc_trigger;
create sequence seq_desc_mc;
create sequence seq_desc_primary;

-- ======================================================================
create table t_desc_trigger
  (id				integer		not null,
   description			varchar (1000)  not null);

create table t_desc_mc
  (id				integer		not null,
   description			varchar (1000)  not null,
   production			varchar (1000),
   decay_chain			varchar (1000));

create table t_desc_primary
  (id				integer		not null,
   trigger_path			integer,
   mc_channel			integer,
   is_mc_data			char (1)	not null);

-- ======================================================================
alter table t_desc_trigger
  add constraint pk_desc_trigger
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_desc_trigger
  add constraint uq_desc_trigger_desc
  unique (description);

--
alter table t_desc_mc
  add constraint pk_desc_mc
  primary key (id);

--
alter table t_desc_primary
  add constraint pk_desc_primary
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_desc_primary
  add constraint uq_desc_primary_key
  unique (trigger_path, mc_channel);

alter table t_desc_primary
  add constraint fk_desc_primary_trigger
  foreign key (trigger_path) references t_desc_trigger (id);

alter table t_desc_primary
  add constraint fk_desc_primary_mc
  foreign key (mc_channel) references t_desc_mc (id);

alter table t_desc_primary
  add constraint ck_desc_primary_ismc
  check (is_mc_data in ('y', 'n'));
