-- Update to SchemaVersion
DELETE FROM SchemaVersion;
ALTER TABLE SchemaVersion ADD InstanceName varchar(100) unique not null;
INSERT INTO SchemaVersion(SCHEMAVERSION, INSTANCENAME, CREATIONDATE) VALUES ('DBS_1_0_7', 'LOCAL', (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual));

-- New Processed Dataset Status
INSERT INTO ProcDSStatus (Status, CREATIONDATE) VALUES ('RO', (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual));

-- New values for DataTierOrder : AODSIM and GEN-SIM-DIGI-RAW
INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('AODSIM', 'Analysis Object Data SIM asked by Skim team', (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual));

INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('GEN-SIM-DIGI-RAW', 'SV Support 102463 for CSA 07', (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual));

-- New Data Tier AODSIM
INSERT INTO DataTier (Name, CREATIONDATE) VALUES ('AODSIM', (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual));
commit;

