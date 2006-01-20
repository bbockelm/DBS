-- ======================================================================
create sequence seq_info_evcoll;

-- ======================================================================
create table t_info_evcoll
  (event_collection		integer		not null,
   events			integer		not null,
   name				varchar (1000)	not null);

-- ======================================================================
alter table t_info_evcoll
  add constraint pk_info_evcoll
  primary key (event_collection)
  using index tablespace CMS_DBS_INDX01;

alter table t_info_evcoll
  add constraint fk_info_evcoll_ds
  foreign key (event_collection) references t_event_collection (id)
  /* on update cascade */ on delete cascade;
