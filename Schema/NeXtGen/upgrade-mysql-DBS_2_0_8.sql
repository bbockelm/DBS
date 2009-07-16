use CMS_DBS;
alter table ProcessedDataset add DESCRIPTION varchar(1000);
update SchemaVersion set SchemaVersion='DBS_1_1_6';
