-- ======================================================================
create sequence seq_person;

-- ======================================================================
create table t_person
  (id				integer		not null,
   name				varchar (1000)	not null,
   distinguished_name		varchar (1000)	not null,
   contact_info			varchar (1000)	not null);

-- ======================================================================
alter table t_person
  add constraint pk_person
  primary key (id)
  using index tablespace INDX01;

alter table t_person
  add constraint uq_person_name
  unique (name);

alter table t_person
  add constraint uq_person_distinguished
  unique (distinguished_name);
