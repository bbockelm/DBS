INSERT INTO FileType(Type, CreationDate) VALUES ('LUMI-VDM', (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual)) ;
INSERT INTO FileType(Type, CreationDate) VALUES ('LUMI-SA', (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual)) ;
INSERT INTO FileType(Type, CreationDate) VALUES ('LUMI', (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual)) ;

alter table ProcessedDataset add (DESCRIPTION varchar2(1000));
update SCHEMAVERSION set SCHEMAVERSION='DBS_1_1_6';
alter table SchemaVersion add DBS_RELEASE_VERSION varchar2(100);
update SchemaVersion set DBS_RELEASE_VERSION='DBS_2_0_8';
commit;
