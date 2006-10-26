use dbs_new_era_v03;

INSERT INTO Person(Name, DistinguishedName, ContactInfo, CreationDate) Values ('ANZAR', 'ANZARDN', 'WH9X', NOW());

INSERT INTO TriggerPathDescription (TriggerPathDescription, CreationDate) VALUES ('This is Trig Path and whats u gonna do about it', NOW());
INSERT INTO OtherDescription (Description, CreationDate) VALUES ('This is Other Description', NOW());
INSERT INTO MCDescription (MCChannelDescription, MCProduction, MCDecayChain, CreationDate) VALUES ('This is MCChannel Description', 'p', 'c', NOW());

INSERT INTO PrimaryDatasetDescription(TriggerDescriptionID, MCChannelDescriptionID, OtherDescriptionID, CreationDate) VALUES (1,1,1,NOW());

INSERT INTO PrimaryDataset(Annotation, Name, Description, StartDate, EndDate, Type, CreationDate) VALUES ('First Primary in new era', 'PrimaryDS_ANZAR_01', 'This is a test dataset', '2006-10-05', '2007-10-05', 1, NOW());

INSERT INTO ProcessedDataset(Name, PrimaryDataset, OpenForWriting, CreationDate) VALUES ('anzar-procds-01', 1, 'y', NOW());


INSERT INTO Block (BlockSize, Name, Dataset, NumberOfFiles, OpenForWriting, CreationDate) VALUES (20, 'Block_001', 1, 2, 'y', NOW());

INSERT INTO DataTier(Name, CreationDate) VALUES ('SIM', NOW()), ('RECO', NOW());


INSERT INTO ProcDSTier(Dataset, DataTier, CreationDate) VALUES ( 1, 1, NOW()), (1, 2, NOW());

INSERT INTO ProcDSRuns(Dataset, Run, CreationDate) VALUES ( 1, 1, NOW()), (1, 2, NOW());


INSERT INTO Status (Status, CreationDate) VALUES ('Valid', NOW()), ('New', NOW()), ('Dummy', NOW()), ('Merged', NOW()), ('UnMerged', NOW());


INSERT INTO Files (LogicalFileName, Dataset, Checksum, FileSize, Status, FileType, CreationDate) VALUES ('testfile_01', 1, 111, 123, 1, 1, NOW());

INSERT INTO Files (LogicalFileName, Dataset, Checksum, FileSize, Status, FileType, CreationDate) VALUES ('testfile_02', 1, 222, 456, 1, 1, NOW());

INSERT INTO Type(Type, CreationDate) VALUES ('VALID', NOW()), ('TEST', NOW());

INSERT INTO Files (LogicalFileName, Dataset, Checksum, FileSize, Status, FileType, CreationDate) VALUES ('testfile_03', 1, 333, 123, (SELECT ID FROM Status WHERE Status='Valid') , (SELECT ID FROM Type WHERE Type='Valid'), NOW() );   

#File 1, DT1, 2
INSERT INTO FileTier(Fileid, DataTier, CreationDate) VALUES (1, 2, NOW()), (1,1, NOW());
#File 2, DT1, 2
INSERT INTO FileTier(Fileid, DataTier, CreationDate) VALUES (2, 2, NOW()), (2,1, NOW());


INSERT INTO FileLumi(Fileid, Lumi, CreationDate) VALUES (2, 2, NOW()), (2,1, NOW());
INSERT INTO FileLumi(Fileid, Lumi, CreationDate) VALUES (1, 2, NOW()), (1,1, NOW());




INSERT INTO Runs (RunNumber, NumberOfEvents, NumberOfLumiSections, TotalLuminosity, StoreNumber, StartOfRun, EndOfRun, CreationDate)
           VALUE (1,100,2,222,1,'NOW', 'NEVER', NOW()), (2,100,2,222,1,'NOW', 'NEVER', NOW());

INSERT INTO LumiSection (LumiSectionNumber, RunNumber, StartEventNumber, EndEventNumber, LumiStartTime, LumiEndTime, CreationDate) VALUES  (2, 1, 51, 100, 'NOW', 'NEVER', NOW()), (1, 1, 1, 50, 'NOW', 'NEVER', NOW());

INSERT INTO AnalysisDataset (Annotation, ProcessedDS, Type, Status, CreationDate) VALUES ('A TEST ANALYSIS DATASET', 1, 1,1, now());


INSERT INTO AnalysisDatasetLumi(AnalysisDataset, Lumi, CreationDate) VALUES (1,1,NOW()), (1,2,NOW());

INSERT INTO QueryableParameterSet(Hash, Name, Version, Type, Annotation, Content, CreationDate) VALUES ('jsdhjdmkjf', 'param1', 'type1', 'version1.1', 'this is a test param', 'streamer a ={int x=1}', NOW());

 INSERT INTO QueryableParameterSet(Hash, Name, Version, Type, Annotation, Content, CreationDate) VALUES ('jsdhjdmkjf', 'param2', 'type1', 'version1.1', 'this is a test param', 'streamer a ={int x=2}', NOW());

INSERT INTO QueryableParameterSet(Hash, Name, Version, Type, Annotation, Content, CreationDate) VALUES ('jsdhjdmkjf', 'param3', 'type1', 'version1.1', 'this is a test param', 'streamer a ={int x=3}', NOW());

INSERT INTO ParameterBinding (Self, Contains, CreationDate) VALUES (3,1, NOW());
INSERT INTO ParameterBinding (Self, Contains, CreationDate) VALUES (3,2, NOW());

INSERT INTO AlgorithmConfig(ExecutableName, ApplicationVersion, ApplicationFamily, ParameterSetID, CreationDate) VALUES (1,1,1,3,NOW());
INSERT INTO AppVersion(Version, CreationDate) VALUES ('cmssw_1_0_0_pre1', NOW());
INSERT INTO AppFamily(FamilyName, CreationDate) VALUES ('CMSSW', NOW());
INSERT INTO AppExecutable(ExecutableName, CreationDate) VALUES ('CMSSW', NOW());

INSERT INTO ProcAlgoMap(Dataset, Algorithm, CreationDate) VALUES(1,1,NOW());
INSERT INTO ProcAlgoMap(Dataset, Algorithm, CreationDate) VALUES(1,2,NOW());
INSERT INTO ProcAlgoMap(Dataset, Algorithm, CreationDate) VALUES(2,1,NOW());
INSERT INTO ProcAlgoMap(Dataset, Algorithm, CreationDate) VALUES(2,2,NOW());

INSERT INTO FileAlgoMap(Fileid, Algorithm, CreationDate) VALUES(1,2,NOW());
INSERT INTO FileAlgoMap(Fileid, Algorithm, CreationDate) VALUES(2,2,NOW());
INSERT INTO FileAlgoMap(Fileid, Algorithm, CreationDate) VALUES(1,1,NOW());
INSERT INTO FileAlgoMap(Fileid, Algorithm, CreationDate) VALUES(1,2,NOW());

select table_name from user_tables;
select * from Person;
select * from TriggerPathDescription;
select * from PrimaryDataset;
select * from ProcessedDataset;
select * from Block;
select * from DataTier;
select * from ProcDSTier;
select * from Files;
select * from FileTier;
select * from FileType;

