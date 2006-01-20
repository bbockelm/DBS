-- ======================================================================
create table t_object_history
  (object_type			varchar (32)	not null,
   object_id			integer		not null,
   operation			varchar (32)	not null,
   at				float		not null,
   person			integer		not null,
   mediator			integer		not null);
   -- comment			varchar (1000)

-- ======================================================================
alter table t_object_history
  add constraint fk_object_history_person
  foreign key (person) references t_person (id);

alter table t_object_history
  add constraint fk_object_history_mediator
  foreign key (mediator) references t_person (id);

-- ======================================================================
create index ix_object_history_person
  on t_object_history (person)
  tablespace INDX01;

create index ix_object_history_mediator
  on t_object_history (mediator)
  tablespace INDX01;
