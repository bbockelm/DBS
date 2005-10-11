-- ======================================================================
create sequence seq_parentage_type;

-- ======================================================================
create table t_parentage_type
  (id				integer		not null,
   name				varchar (1000)	not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

create table t_evcoll_parentage
  (parent			integer		not null,
   child			integer		not null,
   type				integer		not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float,
   modified_by			integer);

-- ======================================================================
alter table t_parentage_type
  add constraint pk_parentage_type
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_parentage_type
  add constraint uq_parentage_type_name
  unique (name);

alter table t_parentage_type
  add constraint fk_parentage_type_creatby
  foreign key (created_by) references t_person (id);

alter table t_parentage_type
  add constraint fk_parentage_type_modifby
  foreign key (modified_by) references t_person (id);

--
alter table t_evcoll_parentage
  add constraint pk_evcoll_parentage
  primary key (parent, child)
  using index tablespace CMS_DBS_INDX01;

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

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_creatby
  foreign key (created_by) references t_person (id);

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_modifby
  foreign key (modified_by) references t_person (id);

-- ======================================================================
create index ix_parentage_creatby
  on t_parentage_type (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_parentage_modifby
  on t_parentage_type (modified_by)
  tablespace CMS_DBS_INDX01;

--
create index ix_evcoll_parentage_type
  on t_evcoll_parentage (type)
  tablespace CMS_DBS_INDX01;

create index ix_evcoll_parentage_creatby
  on t_evcoll_parentage (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_evcoll_parentage_modifby
  on t_evcoll_parentage (modified_by)
  tablespace CMS_DBS_INDX01;
