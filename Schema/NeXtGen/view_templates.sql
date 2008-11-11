PROMPT Create View  FileSummary

Drop view FileSummary
/

CREATE VIEW FileSummary (LogicalFileName, CreationDate, CreatedBy, Checksum, NumberOfEvents, 
FileSize, Type, Status) AS SELECT tf.LogicalFileName, tf.CreationDate, tp.DistinguishedName, 
tf.Checksum, tf.NumberOfEvents, tf.FileSize, tft.Type, tfs.Status 
FROM Files tf 
JOIN FileType tft ON tf.FileType=tft.ID 
JOIN FileStatus tfs ON tf.FileStatus=tfs.ID JOIN person tp ON tf.CreatedBy = tp.ID
/

PROMPT Create VIEW ReleaseSummary

drop VIEW ReleaseSummary
/

CREATE VIEW ReleaseSummary (Version, CreationDate, CreatedBy, FamilyName, ExecutableName) 
AS SELECT tav.Version, tav.CreationDate, tp.DistinguishedName, taf.FamilyName, tae.ExecutableName 
FROM AlgorithmConfig tac JOIN AppFamily taf ON taf.ID=tac.ApplicationFamily 
JOIN AppVersion tav ON tav.ID=tac.ApplicationVersion 
JOIN AppExecutable tae ON tae.ID=tac.ExecutableName 
JOIN person tp ON tav.CreatedBy = tp.ID
/

PROMPT Create VIEW RunSummary

drop VIEW RunSummary
/

CREATE VIEW RunSummary (RunNumber, CreationDate, CreatedBy, ModificationDate, ModifiedBy, TotLumi, StoreNumber, StartOfRun, EndOfRun, NumberOfEvents, StartEvent, EndEvent, NumberOfLumis)
AS SELECT tr.RunNumber, tr.CreationDate, tp.DistinguishedName, 
tr.LastModificationDate, tp2.DistinguishedName,
tr.TotalLuminosity, tr.StoreNumber, tr.StartOfRun, tr.EndOfRun, tr.NumberOfEvents,
tls.StartEventNumber, tls.EndEventNumber, count(tls.LumiSectionNumber) 
FROM Runs tr JOIN LumiSection tls ON tr.ID=tls.RunNumber 
JOIN person tp ON tr.CreatedBy = tp.ID 
JOIN person tp2 ON tr.LastModifiedBy = tp2.ID
GROUP BY tr.RunNumber, tr.CreationDate, tp.DistinguishedName, 
tr.LastModificationDate, tp2.DistinguishedName, tr.TotalLuminosity,
tr.StoreNumber, tr.StartOfRun, tr.EndOfRun, tr.NumberOfEvents, tls.StartEventNumber, tls.EndEventNumber
/

PROMPT Create VIEW RunManagerSummary

drop VIEW RunManagerSummary
/

CREATE VIEW RunManagerSummary (RunNumber, CreationDate, CreatedBy, TotLumi, StoreNumber, StartOfRun, EndOfRun, StartEvent, 
EndEvent, Path, NumberOfLumis, NumberOfFiles) 
AS SELECT DISTINCT tr.RunNumber, tr.CreationDate, tp.DistinguishedName, tr.TotalLuminosity, 
tr.StoreNumber, tr.StartOfRun, tr.EndOfRun, tls.StartEventNumber, tls.EndEventNumber, tblk.Path, 
count(DISTINCT tls.LumiSectionNumber), count(tf.LogicalFileName) 
FROM Runs tr JOIN LumiSection tls ON tr.ID=tls.RunNumber 
JOIN FileRunLumi tfrl ON tfrl.Run=tr.ID 
JOIN Files tf ON tf.ID=tfrl.FileId 
JOIN Block tblk ON tblk.ID=tf.Block 
JOIN person tp ON tr.CreatedBy = tp.ID 
GROUP BY tr.RunNumber, tr.CreationDate, tp.DistinguishedName, tr.TotalLuminosity, 
tr.StoreNumber, tr.StartOfRun, tr.EndOfRun, tls.StartEventNumber, tls.EndEventNumber, tblk.Path
/

PROMPT Create VIEW SiteSummary

drop VIEW SiteSummary
/

CREATE VIEW SiteSummary (SEName, CreationDate, CreatedBy, NumberOfProcDS) 
AS SELECT tse.SEName, tse.CreationDate, tp.DistinguishedName, count(DISTINCT tblk.Path) 
FROM StorageElement tse 
JOIN SEBlock tseb ON tseb.SEID=tse.ID 
JOIN Block tblk ON tblk.ID=tseb.BlockID 
JOIN person tp ON tse.CreatedBy = tp.ID 
GROUP BY tse.SEName, tse.CreationDate, tp.DistinguishedName
/

PROMPT CREATE VIEW PrimSummary

drop VIEW PrimSummary
/

CREATE VIEW PrimSummary (Name, CreationDate, CreatedBy, PrimType, NumberOfProcDS) 
AS SELECT tprm.Name, tprm.CreationDate, tp.DistinguishedName, tprmt.Type, count(tprd.Name) 
FROM PrimaryDataset tprm 
JOIN PrimaryDSType tprmt ON tprmt.ID=tprm.Type 
JOIN ProcessedDataset tprd ON tprm.ID=tprd.PrimaryDataset 
JOIN person tp ON tprm.CreatedBy = tp.ID 
GROUP BY tprm.Name, tprm.CreationDate, tp.DistinguishedName, tprmt.Type
/

PROMPT CREATE VIEW ProcSummary

drop VIEW ProcSummary
/

CREATE VIEW ProcSummary (Name, CreationDate, CreatedBy, NumberOfBlocks, BlocksSize, NumberOfFiles, NumberOfEvents) 
AS SELECT tprd.Name, tprd.CreationDate, tp.DistinguishedName, count(tblk.Name), 
sum(tblk.BlockSize), sum(tblk.NumberOfFiles), sum(tblk.NumberOfEvents) 
FROM ProcessedDataset tprd 
JOIN Block tblk ON tprd.ID=tblk.Dataset 
JOIN person tp ON tprd.CreatedBy = tp.ID 
GROUP BY tprd.Name, tprd.CreationDate, tp.DistinguishedName
/

PROMPT CREATE VIEW TierSummary


drop VIEW TierSummary
/

CREATE VIEW TierSummary (Name, CreationDate, CreatedBy, NumberOfProcDS) 
AS SELECT tdt.Name, tdt.CreationDate, tp.DistinguishedName, count(tprd.Name) 
FROM ProcessedDataset tprd 
JOIN ProcDSTier tpdst ON tprd.ID=tpdst.Dataset 
JOIN DataTier tdt ON tpdst.DataTier=tdt.ID 
JOIN person tp ON tdt.CreatedBy = tp.ID GROUP BY tdt.Name, tdt.CreationDate, tp.DistinguishedName

/

PROMPT CREATE VIEW DatasetSummary

drop VIEW DatasetSummary
/

CREATE VIEW DatasetSummary (Path, CreationDate, CreatedBy, TotalSize,  NumberOfBlocks, NumberOfFiles,
NumberOfEvents, NumberOfSites) 
AS SELECT DISTINCT tblk.Path, tprd.CreationDate, tp.DistinguishedName,  sum(tblk.BlockSize),
count(tblk.Name), sum(tblk.NumberOfFiles), sum(tblk.NumberOfEvents),
(SELECT COUNT(DISTINCT tse.SEName) FROM storageelement tse JOIN  seblock tseb ON tseb.SEID = tse.ID
LEFT OUTER JOIN block tblk2 ON tseb.BlockID = tblk2.ID WHERE  tblk2.path=tblk.path
)
FROM processeddataset tprd JOIN block tblk ON tblk.Dataset = tprd.ID
JOIN primarydataset tpm ON tprd.PrimaryDataset = tpm.ID
JOIN person tp ON tprd.CreatedBy = tp.ID
GROUP BY tblk.Path, tprd.CreationDate, tp.DistinguishedName
/

PROMPT CREATE VIEW AdsBigSummary

drop VIEW AdsBigSummary
/

CREATE VIEW AdsBigSummary 
(Name, Version, Path, Type, Status, PhGroup, CreationDate, CreatedBy, DefName, DefPath, DefDesc, DefInput, DefQuery)
AS 
SELECT 
tads.Name, tads.Version, tads.Path, 
tadstype.Type, tadsstat.Status, 
tpg.PhysicsGroupName, 
tads.CreationDate, tp.DistinguishedName, 
tadsdef.Name, tadsdef.Path,  
tadsdef.Description, tadsdef.UserInput, tadsdef.SQLQuery 
FROM AnalysisDataset tads 
JOIN AnalysisDSDef tadsdef ON tads.Definition=tadsdef.ID 
JOIN AnalysisDSStatus tadsstat ON tads.Status=tadsstat.ID 
JOIN AnalysisDSType tadstype ON tads.Type=tadstype.ID 
JOIN PhysicsGroup tpg ON tads.PhysicsGroup=tpg.ID 
JOIN person tp ON tads.CreatedBy = tp.ID 
/

PROMPT CREATE VIEW AdsSummary

drop VIEW AdsSummary
/

CREATE VIEW AdsSummary 
(Name, Version, Path, Type, Status, PhGroup, CreationDate, CreatedBy)
AS 
SELECT
tads.Name, tads.Version, tads.Path, 
tadstype.Type, tadsstat.Status, 
tpg.PhysicsGroupName, tads.CreationDate, tp.DistinguishedName 
FROM AnalysisDataset tads 
JOIN AnalysisDSStatus tadsstat ON tads.Status=tadsstat.ID 
JOIN AnalysisDSType tadstype ON tads.Type=tadstype.ID 
JOIN PhysicsGroup tpg ON tads.PhysicsGroup=tpg.ID 
JOIN person tp ON tads.CreatedBy = tp.ID
/

PROMPT Grant select on FileSummary to  '@build.schema.owner.name@_READER'
Grant select on FileSummary to @build.schema.owner.name@_READER
/


PROMPT Grant select on ReleaseSummary to  '@build.schema.owner.name@_READER'
Grant select on ReleaseSummary to @build.schema.owner.name@_READER
/

PROMPT Grant select on RunSummary to  '@build.schema.owner.name@_READER'
Grant select on RunSummary to @build.schema.owner.name@_READER
/

PROMPT Grant select on RunManagerSummary to  '@build.schema.owner.name@_READER'
Grant select on RunManagerSummary to @build.schema.owner.name@_READER
/

PROMPT Grant select on SiteSummary to  '@build.schema.owner.name@_READER'
Grant select on SiteSummary to @build.schema.owner.name@_READER
/

PROMPT Grant select on PrimSummary to  '@build.schema.owner.name@_READER'
Grant select on PrimSummary to @build.schema.owner.name@_READER
/

PROMPT Grant select on ProcSummary to  '@build.schema.owner.name@_READER'
Grant select on ProcSummary to @build.schema.owner.name@_READER
/

PROMPT Grant select on TierSummary to  '@build.schema.owner.name@_READER'
Grant select on TierSummary to @build.schema.owner.name@_READER
/

PROMPT Grant select on DatasetSummary to  '@build.schema.owner.name@_READER'
Grant select on DatasetSummary to @build.schema.owner.name@_READER
/

PROMPT Grant select on AdsBigSummary to  '@build.schema.owner.name@_READER'
Grant select on AdsBigSummary to @build.schema.owner.name@_READER
/

PROMPT Grant select on AdsSummary to  '@build.schema.owner.name@_READER'
Grant select on AdsSummary to @build.schema.owner.name@_READER
/
