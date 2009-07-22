use CMS_DBS;
INSERT INTO FileType(Type, CreationDate) VALUES ('LUMI', UNIX_TIMESTAMP()) ;
INSERT INTO FileType(Type, CreationDate) VALUES ('LUMI-SA', UNIX_TIMESTAMP()) ;
INSERT INTO FileType(Type, CreationDate) VALUES ('LUMI-VDM', UNIX_TIMESTAMP()) ;

alter table ProcessedDataset add DESCRIPTION varchar(1000);
update SchemaVersion set SchemaVersion='DBS_1_1_6';
alter table SchemaVersion add DBS_RELEASE_VERSION varchar(100);
update SchemaVersion set DBS_RELEASE_VERSION='DBS_2_0_8';
commit;
