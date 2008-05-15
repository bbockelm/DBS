--
--
-- sql file to update Schema for DBS_1_1_5 release
--
--
-- ==================================================================
ALTER TABLE AnalysisDSDef DROP (
			LumiSections, 
			LumiSectionRanges, 
			Runs, 
			RunsRanges, 
			Algorithms, 
			LFNs, 
			UserCut
			);

ALTER TABLE AnalysisDSDef ADD (
			UserInput CLOB,
			SQLQuery CLOB
			);

-- ====================================================================

ALTER TABLE ProcessedDataset ADD (
			AquisitionEra         varchar(255),
			GlobalTag             varchar(255)
			);

-- ====================================================================

PROMPT Following changes to RecycleBin may be unnecessary;

ALTER TABLE RecycleBin RENAME COLUMN Name TO BlockName;
 
-- ====================================================================
PROMPT Following changes to TimeLog may be unnecessary;

ALTER TABLE TimeLog MODIFY (
			Action                varchar(500),
			Cause                 varchar(500), 
			Effect                varchar(500),
			Description           varchar(1000) 
			);

-- =====================================================================

CREATE TABLE RunLumiDQInt
  (
    ID                    integer,
    Run                   integer  not null,
    Lumi                  integer,
    SubSystem             integer  not null,
    IntDQValue            integer  not null,
    CreationDate          integer,
    CreatedBy             integer,
    LastModificationDate  integer,
    LastModifiedBy        integer,
    primary key(ID),
    unique(Run,Lumi,SubSystem)
  );

ALTER TABLE RunLumiDQInt ADD CONSTRAINT
    RunLumiDQInt_Run_FK foreign key(Run) references Runs(ID)
/
ALTER TABLE RunLumiDQInt ADD CONSTRAINT
    RunLumiDQInt_Lumi_FK foreign key(Lumi) references LumiSection(ID)
/
ALTER TABLE RunLumiDQInt ADD CONSTRAINT
    RunLumiDQInt_SubSystem_FK foreign key(SubSystem) references SubSystem(ID) on delete CASCADE
/
ALTER TABLE RunLumiDQInt ADD CONSTRAINT
    RunLumiDQInt_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE RunLumiDQInt ADD CONSTRAINT
    RunLumiDQIntLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
/

PROMPT creating sequence seq_runlumidqint ;
create sequence seq_runlumidqint ;

PROMPT AUTO INC TRIGGER FOR Trigger for Table: runlumidqint
CREATE OR REPLACE TRIGGER runlumidqint_TRIG before insert on runlumidqint    for each row begin     if inserting then       if :NEW.ID is null then          select seq_runlumidqint.nextval into :NEW.ID from dual;       end if;    end if; end;
/

PROMPT LastModified Time Stamp Trigger for Table: runlumidqint
CREATE OR REPLACE TRIGGER TRTSrunlumidqint BEFORE INSERT OR UPDATE ON runlumidqint
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/

-- =====================================================================

CREATE TABLE ProcADSParent
  (
     ID                    integer,
     ThisDataset           integer   not null,
     ItsParentADS          integer   not null,
     CreatedBy             integer,
     CreationDate          integer,
     LastModifiedBy        integer,
     LastModificationDate  integer,
     primary key(ID),
     unique(ThisDataset,ItsParentADS)
   );

ALTER TABLE ProcADSParent ADD CONSTRAINT 
    ProcADSParent_ThisDataset_FK foreign key(ThisDataset) references ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE ProcADSParent ADD CONSTRAINT 
    ProcADSParent_ItsParentADS_FK foreign key(ItsParentADS) references AnalysisDataset(ID) on delete CASCADE
/
ALTER TABLE ProcADSParent ADD CONSTRAINT 
    ProcADSParent_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ProcADSParent ADD CONSTRAINT 
    ProcADSParentLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

PROMPT creating sequence seq_procadsparent ;
create sequence seq_procadsparent ;

-- AUTO INC TRIGGER FOR procadsparent.ID using SEQ seq_procadsparent

PROMPT AUTO INC TRIGGER FOR Trigger for Table: procadsparent
 CREATE OR REPLACE TRIGGER procadsparent_TRIG before insert on procadsparent    for each row begin     if inserting then       if :NEW.ID is null then          select seq_procadsparent.nextval into :NEW.ID from dual;       end if;    end if; end;
/

PROMPT LastModified Time Stamp Trigger for Table: procadsparent
CREATE OR REPLACE TRIGGER TRTSprocadsparent BEFORE INSERT OR UPDATE ON procadsparent
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/

UPDATE SchemaVersion SET SchemaVersion='DBS_1_1_2';


