-- ======================================================================
create table t_evcoll_parentage
  (parent			integer		not null,
   child			integer		not null,
   created_at			float		not null,
   created_by			integer		not null,
   modified_at			float		not null,
   modified_by			integer		not null);

-- ======================================================================
alter table t_evcoll_parentage
  add constraint pk_evcoll_parentage
  primary key (parent, child)
  using index tablespace CMS_DBS_INDX01;

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_parent
  foreign key (parent) references t_event_collection (id)
  /* on upfloat cascade */ on delete cascade;

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_child
  foreign key (child) references t_event_collection (id)
  /* on upfloat set null */ on delete set null;

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_creat
  foreign key (created_by) references t_person (id);

alter table t_evcoll_parentage
  add constraint fk_evcoll_parentage_mod
  foreign key (modified_by) references t_person (id);

-- ======================================================================
create index ix_evcoll_parentage_creat
  on t_evcoll_parentage (created_by)
  tablespace CMS_DBS_INDX01;

create index ix_evcoll_parentage_mod
  on t_evcoll_parentage (modified_by)
  tablespace CMS_DBS_INDX01;
