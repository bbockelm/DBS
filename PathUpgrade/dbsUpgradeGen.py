#!/usr/bin/env python
#
# Script to upgrade DBS Database
#
# Revision: $"
# Id: $"
#
# Autor Anzar Afaq (anzar@fnal.gov)
# 01/22/2009
#
import sys
#from dbsApi import DbsApi
from DBSAPI.dbsException import *
from DBSAPI.dbsApiException import *
from DBSAPI.dbsOptions import DbsOptionParser
from DBSAPI.dbsApi import DbsApi

#instance_name='cms_dbs_test'
instance_name='cms_dbs_prod_global_intr2'
#
#
logfile=open(instance_name+'.log', 'w')
sqlfile=open(instance_name+'.sql', 'w')

def log_this(msg):
	print msg
	logfile.write("\n"+msg)

try:
  optManager  = DbsOptionParser()
  (opts,args) = optManager.getOpt()

  args={}
  #args['url']='http://cmssrv17.fnal.gov:8989/DBSINT2R/servlet/DBSServlet' 
  args['url']='http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet' 
  #args['url']='http://cmssrv17.fnal.gov:8989/DBSTEST/servlet/DBSServlet'

  args['version']='DBS_2_0_5'
  args['mode']='POST'
  api = DbsApi(args)

  log_this("Creating TMP_ProcessedDataset Table entry")
  ddl_stmt="""CREATE TABLE TMP_ProcessedDataset
(
  ID                    integer,
  Name                  varchar(500)      not null,
  PrimaryDataset        integer   not null,
  DataTier                INTEGER not null,
  PhysicsGroup          integer   not null,
  Status                integer   not null,
  AquisitionEra         varchar(255),
  GlobalTag             varchar(255),
  XtCrossSection        float,
  CreatedBy             integer,
  CreationDate          integer,
  LastModifiedBy        integer,
  LastModificationDate  integer,
  primary key(ID),
  unique(Name,PrimaryDataset, DataTier)
);
ALTER TABLE TMP_ProcessedDataset ADD CONSTRAINT
  TProcDSPrimaryData_FK foreign key(PrimaryDataset) references PrimaryDataset(ID) on delete CASCADE
/
ALTER TABLE TMP_ProcessedDataset ADD CONSTRAINT
  TProcDSPhysicsGrou_FK foreign key(PhysicsGroup) references PhysicsGroup(ID)
/
ALTER TABLE TMP_ProcessedDataset ADD CONSTRAINT
  TProcDS_Status_FK foreign key(Status) references ProcDSStatus(ID)
/
ALTER TABLE TMP_ProcessedDataset ADD CONSTRAINT
  TProcDS_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE TMP_ProcessedDataset ADD CONSTRAINT
  TProcDSLastModifie_FK foreign key(LastModifiedBy) references Person(ID)
/
ALTER TABLE TMP_ProcessedDataset ADD CONSTRAINT
 TProcDSDataTier_FK foreign key(DataTier) references DataTier(ID)
/

PROMPT creating sequence seq_tprocesseddataset ;
create sequence seq_tprocesseddataset ;

PROMPT AUTO INC TRIGGER FOR Trigger for Table: tmp_processeddataset
CREATE OR REPLACE TRIGGER tprocesseddataset_TRIG before insert on tmp_processeddataset    for each row begin     if inserting then       if :NEW.ID is null then          select seq_tprocesseddataset.nextval into :NEW.ID from dual;       end if;    end if; end;
/

PROMPT LastModified Time Stamp Trigger for Table: tmp_processeddataset
CREATE OR REPLACE TRIGGER TRTStprocesseddataset BEFORE INSERT OR UPDATE ON tmp_processeddataset
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/

--  TMP_Dataset column added to Files, ProcAlgo,ProcDSRuns,RunLumiQuality,RunLumiDQInt,QualityHistory,IntQualityHistory

ALTER TABLE Files ADD TMP_Dataset INTEGER check (TMP_Dataset is not null);
ALTER TABLE Files ADD CONSTRAINT
    Files_TDataset_FK foreign key(TMP_Dataset) references TMP_ProcessedDataset(ID) on delete CASCADE
/

ALTER TABLE RunLumiQuality ADD TMP_Dataset INTEGER check (TMP_Dataset is not null);
ALTER TABLE RunLumiQuality ADD CONSTRAINT
    RunLumiQuality_TDataset_FK foreign key(TMP_Dataset) references TMP_ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE RunLumiDQInt ADD TMP_Dataset INTEGER check (TMP_Dataset is not null);
ALTER TABLE RunLumiDQInt ADD CONSTRAINT
    RunLumiDQInt_TDataset_FK foreign key(TMP_Dataset) references TMP_ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE QualityHistory ADD TMP_Dataset INTEGER check (TMP_Dataset is not null);
ALTER TABLE QualityHistory ADD CONSTRAINT
    QualityHistory_TDataset_FK foreign key(TMP_Dataset) references TMP_ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE IntQualityHistory ADD TMP_Dataset INTEGER check (TMP_Dataset is not null);
ALTER TABLE IntQualityHistory ADD CONSTRAINT
    IntQualityHistory_TDataset_FK foreign key(TMP_Dataset) references TMP_ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE Block ADD TMP_Dataset INTEGER check (TMP_Dataset is not null);
ALTER TABLE Block ADD CONSTRAINT
    Block_TDataset_FK foreign key(TMP_Dataset) references TMP_ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE FileProcQuality ADD TMP_Dataset INTEGER check (TMP_Dataset is not null);
ALTER TABLE FileProcQuality ADD CONSTRAINT
    FPQDS_FK foreign key(TMP_Dataset) references TMP_ProcessedDataset(ID) on delete CASCADE
/

CREATE TABLE TMP_ProcDSParent
(
  ID                    integer,
  ThisDataset           integer   not null,
  ItsParent             integer   not null,
  CreatedBy             integer,
  CreationDate          integer,
  LastModifiedBy        integer,
  LastModificationDate  integer,
  primary key(ID),
  unique(ThisDataset,ItsParent)
);

ALTER TABLE TMP_ProcDSParent ADD CONSTRAINT
  TProcDSParent_ThisDataset_FK foreign key(ThisDataset) references TMP_ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE TMP_ProcDSParent ADD CONSTRAINT
  TProcDSParent_ItsParent_FK foreign key(ItsParent) references TMP_ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE TMP_ProcDSParent ADD CONSTRAINT
  TProcDSParent_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE TMP_ProcDSParent ADD CONSTRAINT
  TProcDSParent_LastMod_FK foreign key(LastModifiedBy) references Person(ID)
/ 

PROMPT creating sequence seq_tprocdsparent ;
create sequence seq_tprocdsparent ;
PROMPT AUTO INC TRIGGER FOR Trigger for Table: tmp_procdsparent
 CREATE OR REPLACE TRIGGER tprocdsparent_TRIG before insert on tmp_procdsparent    for each row begin     if inserting then       if :NEW.ID is null then          select seq_tprocdsparent.nextval into :NEW.ID from dual;       end if;    end if; end;
/
PROMPT LastModified Time Stamp Trigger for Table: tmp_procdsparent
CREATE OR REPLACE TRIGGER TRTStprocdsparent BEFORE INSERT OR UPDATE ON tmp_procdsparent
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/


CREATE TABLE TMP_BlockParent
  (
    ID                    integer,
    ThisBlock             integer   not null,
    ItsParent             integer   not null,
    CreatedBy             integer,
    CreationDate          integer,
    LastModifiedBy        integer,
    LastModificationDate  integer,
    primary key(ID),
    unique(ThisBlock,ItsParent)
  );

ALTER TABLE TMP_BlockParent ADD CONSTRAINT
    TMP_BlockParent_ThisBlock_FK foreign key(ThisBlock) references Block(ID) on delete CASCADE
/
ALTER TABLE TMP_BlockParent ADD CONSTRAINT
    TMP_BlockParent_ItsParent_FK foreign key(ItsParent) references Block(ID) on delete CASCADE
/
ALTER TABLE TMP_BlockParent ADD CONSTRAINT
    TMP_BlockParent_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE TMP_BlockParent ADD CONSTRAINT
    TMP_BlockParentLastModBy_FK foreign key(LastModifiedBy) references Person(ID)
/

PROMPT creating sequence seq_tblockparent ;
create sequence seq_tblockparent ;

PROMPT AUTO INC TRIGGER FOR Trigger for Table: tmp_blockparent
 CREATE OR REPLACE TRIGGER tblockparent_TRIG before insert on tmp_blockparent    for each row begin     if inserting then       if :NEW.ID is null then          select seq_tblockparent.nextval into :NEW.ID from dual;       end if;    end if; end;
/

PROMPT LastModified Time Stamp Trigger for Table: tmp_blockparent
CREATE OR REPLACE TRIGGER TRTStblockparent BEFORE INSERT OR UPDATE ON TMP_BlockParent
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/

CREATE TABLE TMP_ProcAlgo
  (
    ID                    integer,
    Dataset               integer   not null,
    Algorithm             integer   not null,
    CreationDate          integer,
    CreatedBy             integer,
    LastModificationDate  integer,
    LastModifiedBy        integer,
    primary key(ID),
    unique(Dataset,Algorithm)
  );
ALTER TABLE TMP_ProcAlgo ADD CONSTRAINT
    TMP_PAlgo_DS_FK foreign key(Dataset) references TMP_ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE TMP_ProcAlgo ADD CONSTRAINT
    TMP_PAlgo_Algo_FK foreign key(Algorithm) references AlgorithmConfig(ID) on delete CASCADE
/
ALTER TABLE TMP_ProcAlgo ADD CONSTRAINT
    TMP_PAlgo_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE TMP_ProcAlgo ADD CONSTRAINT
    TMP_PAlgo_LastMod_FK foreign key(LastModifiedBy) references Person(ID)
/
PROMPT creating sequence seq_tprocalgo ;
create sequence seq_tprocalgo ;
PROMPT AUTO INC TRIGGER FOR Trigger for Table: tmp_procalgo
 CREATE OR REPLACE TRIGGER tprocalgo_TRIG before insert on tmp_procalgo    for each row begin     if inserting then       if :NEW.ID is null then          select seq_tprocalgo.nextval into :NEW.ID from dual;       end if;    end if; end;
/
PROMPT LastModified Time Stamp Trigger for Table: tmp_procalgo
CREATE OR REPLACE TRIGGER TRTStprocalgo BEFORE INSERT OR UPDATE ON tmp_procalgo
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/


CREATE TABLE TMP_ProcDSRuns
  (
    ID                    integer,
    Dataset               integer   not null,
    Run                   integer   not null,
    Complete              integer default 0,
    CreationDate          integer,
    CreatedBy             integer,
    LastModificationDate  integer,
    LastModifiedBy        integer,
    primary key(ID),
    unique(Dataset,Run)
  );
ALTER TABLE TMP_ProcDSRuns ADD CONSTRAINT
    TMP_PDSR_Dataset_FK foreign key(Dataset) references TMP_ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE TMP_ProcDSRuns ADD CONSTRAINT
    TMP_PDSR_Run_FK foreign key(Run) references Runs(ID)
/
ALTER TABLE TMP_ProcDSRuns ADD CONSTRAINT
    TMP_PDSR_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE TMP_ProcDSRuns ADD CONSTRAINT
    TMP_PDSR_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/
PROMPT creating sequence seq_tprocdsruns ;
create sequence seq_tprocdsruns ;
PROMPT AUTO INC TRIGGER FOR Trigger for Table: tmp_procdsruns
 CREATE OR REPLACE TRIGGER tprocdsruns_TRIG before insert on tmp_procdsruns    for each row begin     if inserting then       if :NEW.ID is null then          select seq_tprocdsruns.nextval into :NEW.ID from dual;       end if;    end if; end;
/
PROMPT LastModified Time Stamp Trigger for Table: tmp_procdsruns
CREATE OR REPLACE TRIGGER TRTStprocdsruns BEFORE INSERT OR UPDATE ON tmp_procdsruns
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/

-- THis table, TMPPathID, is going to be used ONLY during upgrade and then we drop it

CREATE TABLE TMPPathID
(
ID                    integer,
Path                  varchar(500)
);

create index tmp_path_id_indx on TMPPathID (ID);
create index tmp_path_path_indx on TMPPathID (Path);

update SchemaVersion set SCHEMAVERSION='DBS_1_1_5';

"""

  sqlfile.write("\nset spool on;")
  sqlfile.write("\nSPOOL "+instance_name+"_CHANGE.log")
  sqlfile.write("\nSET ECHO ON;")
  sqlfile.write("\nset serveroutput on size unlimited format wrapped")

  #schemaddl=open("schema_file.sql", "w")
  #schemaddl.write(ddl_stmt)
  #schemaddl.close()
  #sys.exit(0)

  sqlfile.write("\nPROMPT SCRIPT STARTED AT")
  sqlfile.write("\nselect systimestamp from dual;")

  #%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% commented out ddl_stmt, uncommenet in real world
  log_this(ddl_stmt)
  sqlfile.write("\n"+ddl_stmt)

  query  = "\nBEGIN"
  query += "\n FOR item in (SELECT DISTINCT ID, PATH FROM Block B)"
  query += "\n LOOP"
  query += "\n    INSERT INTO TMPPathID(ID, Path) select item.ID, item.PATH from DUAL;"
  query += "\n END LOOP;"
  query += "\nEND;"
  query += "\n/"
  sqlfile.write("\n--===========================================================================")
  sqlfile.write("\n--=== SCRIPT to create temporary entries in TMPPathID table")
  sqlfile.write(query)
  sqlfile.write("\n--===========================================================================")
  query  = "\nBEGIN"
  query += "\n FOR item in (SELECT DISTINCT DataTierOrder FROM DataTierOrder)"
  query += "\n LOOP"
  query += "\n    BEGIN"
  query += "\n        INSERT INTO DataTier(Name) select item.DataTierOrder from DUAL;"
  query += "\n        EXCEPTION"
  query += "\n        WHEN DUP_VAL_ON_INDEX THEN"
  query += "\n               DBMS_OUTPUT.PUT_LINE('Duplicate value found, ignoring');"
  query += "\n    END;"
  query += "\n END LOOP;"
  query += "\nEND;"
  query += "\n/"
  sqlfile.write("\n--===========================================================================")
  sqlfile.write("\n--=== SCRIPT to create entries in DataTier Table")
  sqlfile.write(query)
  sqlfile.write("\n--===========================================================================")

  allPaths = api.listDatasetPaths()
  for aPath in allPaths:
	log_this("Now processing %s " %aPath)
	sqlfile.write("\n\n"+"--===========================================================================")
	sqlfile.write("\n\n"+"--Now processing %s " %aPath)
	sqlfile.write("\n\n"+"--===========================================================================")
	comps=aPath.split('/')
	primary=comps[1]
	processed=comps[2]
	tiers=comps[3]
	log_this("Primary: %s, Processed: %s, DataTier: %s" % (primary, processed, tiers))
	query  = "\nINSERT INTO TMP_ProcessedDataset "
  	query += "\n (Name, PrimaryDataset, DataTier, PhysicsGroup, Status, AquisitionEra, "
   	query += "\n GlobalTag, XtCrossSection, CreatedBy, CreationDate, LastModifiedBy) "
	query += "\n select '"+processed+ "', "
        query += "\n (select ID from PrimaryDataset where Name='"+primary+"'),"
        query += "\n (select ID from DataTier where Name='"+tiers+"'),"
        query += "\n PhysicsGroup, Status, AquisitionEra, GlobalTag, XtCrossSection, CreatedBy, CreationDate, "
        query += "\n LastModifiedBy FROM ProcessedDataset WHERE ID="
        query += "\n (select ID from ProcessedDataset where Name='"+processed+"' AND PRIMARYDATASET="
        query += "\n (select ID from  PrimaryDataset where Name='"+primary+"')"
        query += "\n );"
	log_this("Generated Query To Insert In TMP_ProcessedDataset")
	log_this(query)
	sqlfile.write("\n--Generated Query To Insert In TMP_ProcessedDataset")
	sqlfile.write("\n--INSERT ALL THE TMP_ProcessedDataset first, required for resolving Parentage etc.")
	sqlfile.write("\nPROMPT Running query to make an entry into TMP_ProcessedDataset")
	sqlfile.write(query)
        #break

  #count = 0 #just do 10 datasets
  for aPath in allPaths:
        comps=aPath.split('/')
        primary=comps[1]
        processed=comps[2]
        tiers=comps[3]
        log_this("Primary: %s, Processed: %s, DataTier: %s" % (primary, processed, tiers))
        sqlfile.write("\n\n"+"--===========================================================================")
        sqlfile.write("\n\n"+"--===========================================================================")
        sqlfile.write("\n\n"+"--Now processing %s " %aPath)
        sqlfile.write("\n\n"+"--===========================================================================")
        sqlfile.write("\n\n"+"--===========================================================================")

	#ProcAlgo, ASSUMING THAT EVERY FILE HAS A ALGO (TESTES THE CONDITION IN GLOBAL)
        log_this("Fixing ProcAlgo table")
        query   = "\nBEGIN"
        query  += "\n  FOR item IN"
        query  += "\n  ("
        query  += "\n        SELECT DISTINCT ALGORITHM FROM FileAlgo WHERE Fileid IN"
        query  += "\n                (SELECT ID FROM Files WHERE Block IN"
        query  += "\n                        (SELECT ID FROM TMPPathID WHERE PATH='"+aPath+"')"
        query  += "\n                )"
        query  += "\n  )"
        query  += "\n  LOOP"
	query  += "\n       BEGIN"
        query  += "\n           INSERT INTO TMP_ProcAlgo (DATASET, ALGORITHM) VALUES ("
        query  += "\n                (SELECT ID from TMP_ProcessedDataset where Name='"+processed+"' AND PRIMARYDATASET="
        query  += "\n                                (select ID from  PrimaryDataset where Name='"+primary+"') AND DataTier="
        query  += "\n                                (select ID from DataTier where Name='"+tiers+"')"
        query  += "\n                ), item.ALGORITHM);"
	query  += "\n           EXCEPTION"
	query  += "\n           WHEN DUP_VAL_ON_INDEX THEN"
	query  += "\n       	     DBMS_OUTPUT.PUT_LINE('Duplicate value found, ignoring');"
        query  += "\n       END;"
        query  += "\n  END LOOP;"
        query  += "\nEND;"
        query  += "\n/"
        log_this("Generated Query To Insert In TMP_ProcAlgo")
        log_this(query)
        sqlfile.write("\n--Generated Query To Insert In TMP_ProcAlgo")
        sqlfile.write("\nPROMPT Running query to make an entry into TMP_ProcAlgo")
        sqlfile.write(query)

	#ProcDSRuns ASSUMING THAT IF THERE IS A RUN, IT MUST HAVE A FILE IN DBS
        query  = "\nBEGIN"
        query  += "\n  FOR item IN"
        query  += "\n  ("
        query  += "\n        SELECT DISTINCT RUN FROM FileRunLumi WHERE Fileid IN"
        query  += "\n                (SELECT ID FROM Files WHERE Block IN"
        query  += "\n                        (SELECT ID FROM TMPPathID WHERE PATH='"+aPath+"')"
        query  += "\n                )"
        query  += "\n  )"
        query  += "\n  LOOP"
        query  += "\n       BEGIN"
        query  += "\n           INSERT INTO TMP_ProcDSRuns(DATASET, RUN) VALUES("
        query  += "\n                (SELECT ID from TMP_ProcessedDataset where Name='"+processed+"' AND PRIMARYDATASET="
        query  += "\n                                (select ID from  PrimaryDataset where Name='"+primary+"') AND DataTier="
        query  += "\n                                (select ID from DataTier where Name='"+tiers+"')"
        query  += "\n                ), item.RUN);"
        query  += "\n           EXCEPTION"
        query  += "\n           WHEN DUP_VAL_ON_INDEX THEN"
        query  += "\n                DBMS_OUTPUT.PUT_LINE('Duplicate value found, ignoring');"
        query  += "\n       END;"
        query  += "\n  END LOOP;"
        query  += "\nEND;"
        query  += "\n/"
        log_this("Generated Query To Insert In TMP_ProcDSRuns")
        log_this(query)
        sqlfile.write("\n--Generated Query To Insert In TMP_ProcDSRuns")
        sqlfile.write("\nPROMPT Running query to make an entry into TMP_ProcDSRuns")
        sqlfile.write(query)

	#Assuming that ALL Blocks of a Path belongs to same dataset 
        query   = "\nDECLARE"
	query  += "\n path_id NUMBER;"
	query  += "\nBEGIN"
        query  += "\n  SELECT ID INTO path_id FROM TMP_ProcessedDataset"
        query  += "\n     WHERE Name='"+processed+"' AND PRIMARYDATASET="
        query  += "\n     (select ID from  PrimaryDataset where Name='"+primary+"') AND DataTier="
        query  += "\n     (select ID from DataTier where Name='"+tiers+"');"
	sqlfile.write(query)
	for tableName in ['Block', 'Files', 'RunLumiQuality','RunLumiDQInt','QualityHistory','IntQualityHistory', 'FileProcQuality']:
		query  = "\n\n   	BEGIN"
		query  += "\n     	UPDATE "+tableName+" SET TMP_Dataset=path_id"
		if tableName=='FileProcQuality':
			query += "\n    	   WHERE ChildDataset=(SELECT DISTINCT Dataset FROM Block WHERE Path='"+aPath+"');"
        	else:
			query += "\n    	   WHERE Dataset=(SELECT DISTINCT Dataset FROM Block WHERE Path='"+aPath+"');"
		query += "\n    	EXCEPTION"
		query += "\n    	WHEN NO_DATA_FOUND THEN"
		query += "\n    		DBMS_OUTPUT.PUT_LINE('No data found, ignoring');"
		query += "\n            END;"
        	log_this("Generated Query To Update Dataset column in %s with Proper value " %tableName)
        	log_this(query)
        	sqlfile.write(query)
        query   = "\nEXCEPTION"
	query  += "\nWHEN NO_DATA_FOUND THEN"
	query  += "\n        DBMS_OUTPUT.PUT_LINE('No data found, ignoring');"
	query  += "\nEND;" 
	query  += "\n/"
	sqlfile.write(query)

	########AnalysisDataset
        query  = "\nUPDATE AnalysisDataset SET ProcessedDS=(SELECT ID FROM TMP_ProcessedDataset WHERE Name='"+processed+"' AND PRIMARYDATASET="
        query += "\n (select ID from  PrimaryDataset where Name='"+primary+"') AND DataTier="
        query += "\n (select ID from DataTier where Name='"+tiers+"')"
        query += "\n )"
        query += "\n WHERE Path='"+aPath+"';"
        log_this("Generated Query To Update ProcessedDS column in AnalysisDataset with Proper value")
        log_this(query)
        sqlfile.write("\n--Generated Query To Update ProcessedDS column in AnalysisDataset with Proper value")
	sqlfile.write("\nPROMPT running query To Update ProcessedDS column in AnalysisDataset with Proper value")
        sqlfile.write(query)

        log_this("Generated query to fill in Block and Dataset parentage : %s" %aPath)
        sqlfile.write("\n--Generated query To fill in Block and Dataset parentage %s" %aPath)
        query  = "\nDECLARE"
        query += "\n  CURSOR c1 IS"
        query += "\n   SELECT DISTINCT ID, TMP_DATASET FROM Block WHERE PATH='"+aPath+"';"
        query += "\nBEGIN"
        query += "\n  FOR item IN c1 LOOP"
        query += "\n   DECLARE"
        query += "\n   CURSOR c2 IS"
        query += "\n        SELECT DISTINCT B.ID AS BID, B.TMP_Dataset as BDS FROM Block B"
        query += "\n                JOIN Files FL"
        query += "\n                        ON FL.Block=B.ID"
        query += "\n                WHERE FL.ID in"
        query += "\n                        (SELECT FP.ItsParent"
        query += "\n                                from FileParentage FP"
        query += "\n                                        WHERE FP.ThisFile in"
	query += "\n                                        	(SELECT FLS.ID FROM FILES FLS WHERE FLS.Block=item.ID));"
        query += "\n   BEGIN FOR pitem IN c2  LOOP"
	query += "\n          BEGIN"
        query += "\n        	INSERT INTO TMP_BlockParent(ThisBlock, ItsParent)"
	query += "\n             	SELECT item.ID, pitem.BID FROM DUAL;"
        query += "\n        	EXCEPTION"
        query += "\n             	WHEN DUP_VAL_ON_INDEX THEN"
        query += "\n                   		DBMS_OUTPUT.PUT_LINE('Duplicate value found, ignoring');"
	query += "\n          END;"
        query += "\n          BEGIN"
        query += "\n        	   INSERT INTO TMP_ProcDSParent(ThisDataset, ItsParent) "
        query += "\n             	SELECT item.TMP_DATASET, pitem.BDS FROM DUAL;"
        query += "\n               EXCEPTION"
        query += "\n             	WHEN DUP_VAL_ON_INDEX THEN"
        query += "\n                   		DBMS_OUTPUT.PUT_LINE('Duplicate value found, ignoring');"
	query += "\n          END;"
        query += "\n      END LOOP;"
        query += "\n   END;"
        query += "\n  END LOOP;"
        query += "\nEND;"
        query += "\n/"
	log_this(query)
	sqlfile.write("\nPROMPT running query To fill in Block and Dataset parentage for path %s" %aPath)
	sqlfile.write(query)

	logfile.flush()
	sqlfile.flush()
        #count += 1 
	#if count > 10: break
	#break

  log_this("RENAMING the tables")

  sqlfile.write("\n--RENAMING the tables")
  query  = "\nalter table ProcessedDataset rename to ORIGProcessedDataset;"
  query += "\nalter table TMP_ProcessedDataset rename to ProcessedDataset;"
  query += "\nalter table ProcDSParent rename to ORIGProcDSParent;"
  query += "\nalter table TMP_ProcDSParent rename to ProcDSParent;"
  query += "\nalter table BlockParent rename to ORIGBlockParent;"
  query += "\nalter table TMP_BlockParent rename to BlockParent;"
  query += "\nalter table ProcAlgo rename to ORIGProcAlgo;"
  query += "\nalter table TMP_ProcAlgo rename to ProcAlgo;"
  query += "\nalter table ProcDSRuns rename to ORIGProcDSRuns;"
  query += "\nalter table TMP_ProcDSRuns rename to ProcDSRuns;"

  #%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% commented out this query, UNCOMMENT in real world
  #log_this(query)
  #sqlfile.write(query)

  log_this("RENAMING TMP_Dataset to Dataset in Block, Files, RunLumiQuality, RunLumiDQInt, QualityHistory, IntQualityHistory and FileProcQuality Tables")
  sqlfile.write("\n--RENAMING TMP_Dataset to Dataset in Block, Files, RunLumiQuality, RunLumiDQInt, QualityHistory, IntQualityHistory and FileProcQuality Tables")
  for tableName in ['Block', 'Files', 'RunLumiQuality','RunLumiDQInt','QualityHistory','IntQualityHistory','FileProcQuality']:
	if tableName=='FileProcQuality': 
		#query  = "\nALTER TABLE "+tableName+" MODIFY ChildDataset null;"
		query  = "\nALTER TABLE "+tableName+" DROP COLUMN ChildDataset CASCADE CONSTRAINTS;"
		query += "\nALTER TABLE "+tableName+" RENAME COLUMN TMP_Dataset to ChildDataset;"
	else:		
		query  = "\nALTER TABLE "+tableName+" DROP COLUMN Dataset CASCADE CONSTRAINTS;"
		query += "\nALTER TABLE "+tableName+" RENAME COLUMN TMP_Dataset to Dataset;"

	#SET new UQ Keys here
  	#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% commented out this query, UNCOMMENT in real world
  	#log_this(query)
  	#sqlfile.write(query)

  sqlfile.write("\nPROMPT SCRIPT FINISHED AT")
  sqlfile.write("\nselect systimestamp from dual;")

  sqlfile.write("\ncommit;")
  sqlfile.write("\nSPOOL OFF")
  logfile.close()
  sqlfile.close()

  print "Rename Sequesces, Triggers???" 

except DbsApiException, ex:
  print "Caught API Exception %s: %s "  % (ex.getClassName(), ex.getErrorMessage() )
  if ex.getErrorCode() not in (None, ""):
    print "DBS Exception Error Code: ", ex.getErrorCode()

print "Done"

