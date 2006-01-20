-- ======================================================================
create sequence seq_parentage_type;
create sequence seq_evcoll_parentage;

-- ======================================================================
create table t_parentage_type
  (id				integer		not null,
   name				varchar (1000)	not null);

create table t_evcoll_parentage
  (id				integer		not null,
   parent			integer		not null,
   child			integer		not null,
   type				integer		not null);

-- ======================================================================
alter table t_parentage_type
  add constraint pk_parentage_type
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_parentage_type
  add constraint uq_parentage_type_name
  unique (name);

--
alter table t_evcoll_parentage
  add constraint pk_evcoll_parentage
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_evcoll_parentage
  add constraint uq_evcoll_parentage
  unique (parent, child);

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_type
  foreign key (type) references t_parentage_type (id)
  /* on update cascade */ on delete cascade;

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_parent
  foreign key (parent) references t_event_collection (id)
  /* on update cascade */ on delete cascade;

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_child
  foreign key (child) references t_event_collection (id)
  /* on update set null */ on delete set null;

-- ======================================================================
create index ix_evcoll_parentage_type
  on t_evcoll_parentage (type)
  tablespace CMS_DBS_INDX01;
