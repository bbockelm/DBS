-- ======================================================================
create sequence seq_person;
create sequence seq_physics_group;

-- ======================================================================
create table t_person
  (id				integer		not null,
   name				varchar (1000)	not null,
   distinguished_name		varchar (1000)	not null,
   contact_info			varchar (1000)	not null);

create table t_physics_group
  (id				integer		not null,
   name				varchar (1000)	not null,
   convenor			integer		/* not null? */);

-- ======================================================================
alter table t_person
  add constraint pk_person
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_person
  add constraint uq_person_name
  unique (name);

alter table t_person
  add constraint uq_person_distinguished
  unique (distinguished_name);

--
alter table t_physics_group
  add constraint pk_physicsgroup
  primary key (id)
  using index tablespace CMS_DBS_INDX01;

alter table t_physics_group
  add constraint uq_physicsgroup_name
  unique (name);

alter table t_physics_group
  add constraint fk_physicsgroup_convener
  foreign key (convenor) references t_person (id);

-- ======================================================================
create index ix_physicsgroup_convener
  on t_physics_group (convenor)
  tablespace CMS_DBS_INDX01;
