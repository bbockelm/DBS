INSERT INTO Person(Name, DistinguishedName, ContactInfo, CreationDate) Values ('ANZAR', 'ANZARDN', 'WH9X', SYSTIMESTAMP);

INSERT INTO TriggerPathDescription (TriggerPathDescription, CreationDate) VALUES ('This is Trig Path and whats u gonna do about it', SYSTIMESTAMP);
INSERT INTO OtherDescription (Description, CreationDate) VALUES ('This is Other Description', SYSTIMESTAMP);
INSERT INTO MCDescription (MCChannelDescription, MCProduction, MCDecayChain, CreationDate) VALUES ('This is MCChannel Description', 'p', 'c', SYSTIMESTAMP);

INSERT INTO PrimaryDatasetDescription(TriggerDescriptionID, MCChannelDescriptionID, OtherDescriptionID, CreationDate) VALUES (1,1,1,SYSTIMESTAMP);

INSERT INTO PrimaryDataset(Annotation, Name, StartDate, EndDate, Type, CreationDate) VALUES ('First Primary in new era', 'PrimaryDS_ANZAR_01', '2006-10-05', '2007-10-05', 1, SYSTIMESTAMP);

INSERT INTO ProcessedDataset(Name, PrimaryDataset, OpenForWriting, CreationDate) VALUES ('anzar-procds-01', 1, 'y', SYSTIMESTAMP);


INSERT INTO Block (BlockSize, Name, Dataset, NumberOfFiles, OpenForWriting, CreationDate) VALUES (20, 'Block_001', 1, 2, 'y', SYSTIMESTAMP);

INSERT INTO DataTier(Name, CreationDate) VALUES ('SIM', SYSTIMESTAMP);
INSERT INTO DataTier(Name, CreationDate) VALUES ('RECO', SYSTIMESTAMP);


INSERT INTO ProcDSTier(Dataset, DataTier, CreationDate) VALUES ( 1, 1, SYSTIMESTAMP);
INSERT INTO ProcDSTier(Dataset, DataTier, CreationDate) VALUES (1, 2, SYSTIMESTAMP);

INSERT INTO ProcDSRuns(Dataset, Run, CreationDate) VALUES ( 1, 1, SYSTIMESTAMP);
INSERT INTO ProcDSRuns(Dataset, Run, CreationDate) VALUES (1, 2, SYSTIMESTAMP);


INSERT INTO Status (Status, CreationDate) VALUES ('Valid', SYSTIMESTAMP);
INSERT INTO Status (Status, CreationDate) VALUES ('New', SYSTIMESTAMP);
INSERT INTO Status (Status, CreationDate) VALUES ('Dummy', SYSTIMESTAMP);
INSERT INTO Status (Status, CreationDate) VALUES ('Merged', SYSTIMESTAMP);
INSERT INTO Status (Status, CreationDate) VALUES ('UnMerged', SYSTIMESTAMP);


INSERT INTO Files (LogicalFileName, Dataset, Checksum, FileSize, Status, FileType, CreationDate) VALUES ('testfile_01', 1, 111, 123, 1, 1, SYSTIMESTAMP);

INSERT INTO Files (LogicalFileName, Dataset, Checksum, FileSize, Status, FileType, CreationDate) VALUES ('testfile_02', 1, 222, 456, 1, 1, SYSTIMESTAMP);

INSERT INTO Type(Type, CreationDate) VALUES ('VALID', SYSTIMESTAMP);
INSERT INTO Type(Type, CreationDate) VALUES ('TEST', SYSTIMESTAMP);

INSERT INTO Files (LogicalFileName, Dataset, Checksum, FileSize, Status, FileType, CreationDate) VALUES ('testfile_03', 1, 333, 123, (SELECT ID FROM Status WHERE Status='Valid');
INSERT INTO Files (LogicalFileName, Dataset, Checksum, FileSize, Status, FileType, CreationDate) VALUES (SELECT ID FROM Type WHERE Type='Valid'), SYSTIMESTAMP );   

#File 1, DT1, 2
INSERT INTO FileTier(Fileid, DataTier, CreationDate) VALUES (1, 2, SYSTIMESTAMP);
INSERT INTO FileTier(Fileid, DataTier, CreationDate) VALUES (1,1, SYSTIMESTAMP);
#File 2, DT1, 2
INSERT INTO FileTier(Fileid, DataTier, CreationDate) VALUES (2, 2, SYSTIMESTAMP);
INSERT INTO FileTier(Fileid, DataTier, CreationDate) VALUES (2,1, SYSTIMESTAMP);


INSERT INTO FileLumi(Fileid, Lumi, CreationDate) VALUES (2, 2, SYSTIMESTAMP);
INSERT INTO FileLumi(Fileid, Lumi, CreationDate) VALUES (2,1, SYSTIMESTAMP);
INSERT INTO FileLumi(Fileid, Lumi, CreationDate) VALUES (1, 2, SYSTIMESTAMP);
INSERT INTO FileLumi(Fileid, Lumi, CreationDate) VALUES  (1,1, SYSTIMESTAMP);



INSERT INTO Runs (RunNumber, NumberOfEvents, NumberOfLumiSections, TotalLuminosity, StoreNumber, StartOfRun, EndOfRun, CreationDate)
           VALUE (1,100,2,222,1,'NOW', 'NEVER', SYSTIMESTAMP);
INSERT INTO Runs (RunNumber, NumberOfEvents, NumberOfLumiSections, TotalLuminosity, StoreNumber, StartOfRun, EndOfRun, CreationDate)
            VALUE (2,100,2,222,1,'NOW', 'NEVER', SYSTIMESTAMP);

INSERT INTO LumiSection (LumiSectionNumber, RunNumber, StartEventNumber, EndEventNumber, LumiStartTime, LumiEndTime, CreationDate) VALUES  (2, 1, 51, 100, 'NOW', 'NEVER', SYSTIMESTAMP), (1, 1, 1, 50, 'NOW', 'NEVER', SYSTIMESTAMP);

INSERT INTO AnalysisDataset (Annotation, ProcessedDS, Type, Status, CreationDate) VALUES ('A TEST ANALYSIS DATASET', 1, 1,1, SYSTIMESTAMP);


INSERT INTO AnalysisDatasetLumi(AnalysisDataset, Lumi, CreationDate) VALUES (1,1,SYSTIMESTAMP), (1,2,SYSTIMESTAMP);

INSERT INTO QueryableParameterSet(Hash, Name, Version, Type, Annotation, Content, CreationDate) VALUES ('jsdhjdmkjf', 'param1', 'type1', 'version1.1', 'this is a test param', 'streamer a ={int x=1}', SYSTIMESTAMP);

 INSERT INTO QueryableParameterSet(Hash, Name, Version, Type, Annotation, Content, CreationDate) VALUES ('jsdhjdmkjf', 'param2', 'type1', 'version1.1', 'this is a test param', 'streamer a ={int x=2}', SYSTIMESTAMP);

INSERT INTO QueryableParameterSet(Hash, Name, Version, Type, Annotation, Content, CreationDate) VALUES ('jsdhjdmkjf', 'param3', 'type1', 'version1.1', 'this is a test param', 'streamer a ={int x=3}', SYSTIMESTAMP);

INSERT INTO ParameterBinding (Self, Contains, CreationDate) VALUES (3,1, SYSTIMESTAMP);
INSERT INTO ParameterBinding (Self, Contains, CreationDate) VALUES (3,2, SYSTIMESTAMP);

INSERT INTO AlgorithmConfig(ExecutableName, ApplicationVersion, ApplicationFamily, ParameterSetID, CreationDate) VALUES (1,1,1,3,SYSTIMESTAMP);
INSERT INTO AppVersion(Version, CreationDate) VALUES ('cmssw_1_0_0_pre1', SYSTIMESTAMP);
INSERT INTO AppFamily(FamilyName, CreationDate) VALUES ('CMSSW', SYSTIMESTAMP);
INSERT INTO AppExecutable(ExecutableName, CreationDate) VALUES ('CMSSW', SYSTIMESTAMP);

INSERT INTO ProcAlgo(Dataset, Algorithm, CreationDate) VALUES(1,1,SYSTIMESTAMP);
INSERT INTO ProcAlgo(Dataset, Algorithm, CreationDate) VALUES(1,2,SYSTIMESTAMP);
INSERT INTO ProcAlgo(Dataset, Algorithm, CreationDate) VALUES(2,1,SYSTIMESTAMP);
INSERT INTO ProcAlgo(Dataset, Algorithm, CreationDate) VALUES(2,2,SYSTIMESTAMP);

INSERT INTO FileAlgo(Fileid, Algorithm, CreationDate) VALUES(1,2,SYSTIMESTAMP);
INSERT INTO FileAlgo(Fileid, Algorithm, CreationDate) VALUES(2,2,SYSTIMESTAMP);
INSERT INTO FileAlgo(Fileid, Algorithm, CreationDate) VALUES(1,1,SYSTIMESTAMP);
INSERT INTO FileAlgo(Fileid, Algorithm, CreationDate) VALUES(1,2,SYSTIMESTAMP);

-- select table_name from user_tables;
-- select * from Person;
-- select * from TriggerPathDescription;
-- select * from PrimaryDataset;
-- select * from ProcessedDataset;
-- select * from Block;
-- select * from DataTier;
-- select * from ProcDSTier;
-- select * from Files;
-- select * from FileTier;
-- select * from FileType;

