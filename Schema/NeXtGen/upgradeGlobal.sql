-- DBS Global instances UPGRADE 104 TO 105
-- Anzar Afaq; 06/29/2007
-- =============================================================================

-- DO NOT Forget to do the Syonyms and Role Permissions


-- NEW Tables 

-- ===============================================================================================================================
-- Future Composite ADS

 CREATE TABLE COMPOSITEADS
 (
 ID INTEGER,
 NAME VARCHAR2(500) NOT NULL,
 DESCRIPTION VARCHAR2(1000) NOT NULL,
 CREATEDBY INTEGER,
 CREATIONDATE INTEGER,
 LASTMODIFIEDBY INTEGER,
 LASTMODIFICATIONDATE INTEGER
 );

 CREATE TABLE COMPADSMAP
 (
 ID INTEGER,
 COMPADS INTEGER NOT NULL,
 ADS INTEGER NOT NULL,
 CREATIONDATE INTEGER,
 CREATEDBY INTEGER,
 LASTMODIFICATIONDATE INTEGER,
 LASTMODIFIEDBY INTEGER
 );

-- ==============================================================DQ Tables==========================================================

CREATE TABLE QUALITYVALUES
 (
 ID INTEGER,
 VALUE VARCHAR2(500) NOT NULL,
 CREATEDBY INTEGER,
 CREATIONDATE INTEGER,
 LASTMODIFIEDBY INTEGER,
 LASTMODIFICATIONDATE INTEGER
 );

 CREATE TABLE SUBSYSTEM
 (
 ID INTEGER,
 NAME VARCHAR2(500) NOT NULL,
 PARENT VARCHAR2(500) DEFAULT 'CMS' NOT NULL,
 CREATEDBY INTEGER,
 CREATIONDATE INTEGER,
 LASTMODIFIEDBY INTEGER,
 LASTMODIFICATIONDATE INTEGER
 );

 CREATE TABLE RUNLUMIQUALITY
 (
 ID INTEGER,
 RUN INTEGER NOT NULL,
 LUMI INTEGER,
 SUBSYSTEM INTEGER NOT NULL,
 DQVALUE INTEGER NOT NULL,
 CREATIONDATE INTEGER,
 CREATEDBY INTEGER,
 LASTMODIFICATIONDATE INTEGER,
 LASTMODIFIEDBY INTEGER
 );

 CREATE TABLE QUALITYHISTORY
 (
 ID INTEGER,
 HISTORYOF INTEGER,
 HISTORYTIMESTAMP INTEGER NOT NULL,
 RUN INTEGER NOT NULL,
 LUMI INTEGER,
 SUBSYSTEM INTEGER NOT NULL,
 DQVALUE INTEGER NOT NULL,
 CREATIONDATE INTEGER,
 CREATEDBY INTEGER,
 LASTMODIFICATIONDATE INTEGER,
 LASTMODIFIEDBY INTEGER
 );

 --YG. Removed VERSIONNAME.
 CREATE TABLE QUALITYVERSION
 (
 ID INTEGER,
 VERSION INTEGER NOT NULL,
 VERSIONTIMESTAMP INTEGER NOT NULL,
 DESCRIPTION VARCHAR2(1000),
 CREATIONDATE INTEGER,
 CREATEDBY INTEGER,
 LASTMODIFICATIONDATE INTEGER,
 LASTMODIFIEDBY INTEGER
 );


 CREATE TABLE FILEASSOC
 (
 ID INTEGER,
 THISFILE INTEGER NOT NULL,
 ITSASSOC INTEGER NOT NULL,
 CREATEDBY INTEGER,
 CREATIONDATE INTEGER,
 LASTMODIFIEDBY INTEGER,
 LASTMODIFICATIONDATE INTEGER
 ); 

-- NEW tables constraints

ALTER TABLE COMPOSITEADS ADD PRIMARY KEY (ID);
ALTER TABLE COMPOSITEADS ADD UNIQUE (NAME);
ALTER TABLE COMPOSITEADS ADD CONSTRAINT COMPOSITEADS_CREATEDBY_FK FOREIGN KEY (CREATEDBY)
REFERENCES PERSON (ID);
ALTER TABLE COMPOSITEADS ADD CONSTRAINT COMPOSITEADS_LASTMODIFIEDBY_FK FOREIGN KEY
(LASTMODIFIEDBY)
 REFERENCES PERSON (ID);

 ALTER TABLE QUALITYVALUES ADD PRIMARY KEY (ID);
 ALTER TABLE QUALITYVALUES ADD UNIQUE (VALUE);
 ALTER TABLE QUALITYVALUES ADD CONSTRAINT QUALITYVALUES_CREATEDBY_FK FOREIGN KEY
(CREATEDBY)
 REFERENCES PERSON (ID);
 ALTER TABLE QUALITYVALUES ADD CONSTRAINT QUALITYVALUESLASTMODIFIEDBY_FK FOREIGN KEY
(LASTMODIFIEDBY)
 REFERENCES PERSON (ID);

ALTER TABLE SUBSYSTEM ADD PRIMARY KEY (ID);
 ALTER TABLE SUBSYSTEM ADD UNIQUE (NAME);
 ALTER TABLE SUBSYSTEM ADD CONSTRAINT SUBSYSTEM_CREATEDBY_FK FOREIGN KEY (CREATEDBY)
 REFERENCES PERSON (ID);
 ALTER TABLE SUBSYSTEM ADD CONSTRAINT SUBSYSTEM_LASTMODIFIEDBY_FK FOREIGN KEY
(LASTMODIFIEDBY)
 REFERENCES PERSON (ID);

 ALTER TABLE RUNLUMIQUALITY ADD PRIMARY KEY (ID);
 ALTER TABLE RUNLUMIQUALITY ADD UNIQUE (RUN, LUMI, SUBSYSTEM);
 ALTER TABLE RUNLUMIQUALITY ADD CONSTRAINT RUNLUMIQUALITYLASTMODIFIEDB_FK FOREIGN KEY
(LASTMODIFIEDBY)
 REFERENCES PERSON (ID);
 ALTER TABLE RUNLUMIQUALITY ADD CONSTRAINT RUNLUMIQUALITY_RUN_FK FOREIGN KEY (RUN)
 REFERENCES RUNS (ID);
 ALTER TABLE RUNLUMIQUALITY ADD CONSTRAINT RUNLUMIQUALITY_LUMI_FK FOREIGN KEY (LUMI)
 REFERENCES LUMISECTION (ID);
 ALTER TABLE RUNLUMIQUALITY ADD CONSTRAINT RUNLUMIQUALITY_SUBSYSTEM_FK FOREIGN KEY
(SUBSYSTEM)
REFERENCES SUBSYSTEM (ID)
ON DELETE CASCADE;
ALTER TABLE RUNLUMIQUALITY ADD CONSTRAINT RUNLUMIQUALITY_DQVALUE_FK FOREIGN KEY (DQVALUE)
REFERENCES QUALITYVALUES (ID)
ON DELETE CASCADE;
ALTER TABLE RUNLUMIQUALITY ADD CONSTRAINT RUNLUMIQUALITY_CREATEDBY_FK FOREIGN KEY
(CREATEDBY)
REFERENCES PERSON (ID);

ALTER TABLE QUALITYHISTORY ADD PRIMARY KEY (ID);
ALTER TABLE QUALITYHISTORY ADD UNIQUE (HISTORYTIMESTAMP, RUN, LUMI, SUBSYSTEM, DQVALUE);
ALTER TABLE QUALITYHISTORY ADD CONSTRAINT QUALITYHISTORY_HISTORYOF_FK FOREIGN KEY
(HISTORYOF)
 REFERENCES RUNLUMIQUALITY (ID);
 ALTER TABLE QUALITYHISTORY ADD CONSTRAINT QUALITYHISTORY_RUN_FK FOREIGN KEY (RUN)
 REFERENCES RUNS (ID);
 ALTER TABLE QUALITYHISTORY ADD CONSTRAINT QUALITYHISTORY_LUMI_FK FOREIGN KEY (LUMI)
 REFERENCES LUMISECTION (ID);
 ALTER TABLE QUALITYHISTORY ADD CONSTRAINT QUALITYHISTORY_SUBSYSTEM_FK FOREIGN KEY
(SUBSYSTEM)
 REFERENCES SUBSYSTEM (ID)
 ON DELETE CASCADE;
 ALTER TABLE QUALITYHISTORY ADD CONSTRAINT QUALITYHISTORY_DQVALUE_FK FOREIGN KEY (DQVALUE)
 REFERENCES QUALITYVALUES (ID)
 ON DELETE CASCADE;
 ALTER TABLE QUALITYHISTORY ADD CONSTRAINT QUALITYHISTORY_CREATEDBY_FK FOREIGN KEY
(CREATEDBY)
 REFERENCES PERSON (ID);
 ALTER TABLE QUALITYHISTORY ADD CONSTRAINT QUALITYHISTORYLASTMODIFIEDB_FK FOREIGN KEY
(LASTMODIFIEDBY)
REFERENCES PERSON (ID);

ALTER TABLE QUALITYVERSION ADD PRIMARY KEY (ID);
ALTER TABLE QUALITYVERSION ADD UNIQUE (VERSIONTIMESTAMP);
ALTER TABLE QUALITYVERSION ADD UNIQUE (VERSION);
ALTER TABLE QUALITYVERSION ADD CONSTRAINT QUALITYVERSION_CREATEDBY_FK FOREIGN KEY
(CREATEDBY)
 REFERENCES PERSON (ID);
 ALTER TABLE QUALITYVERSION ADD CONSTRAINT QUALITYVERSIONLASTMODIFIEDB_FK FOREIGN KEY
(LASTMODIFIEDBY)
 REFERENCES PERSON (ID);

ALTER TABLE COMPADSMAP ADD PRIMARY KEY (ID);
 ALTER TABLE COMPADSMAP ADD UNIQUE (COMPADS, ADS);
 ALTER TABLE COMPADSMAP ADD CONSTRAINT COMPADSMAP_COMPADS_FK FOREIGN KEY (COMPADS)
 REFERENCES COMPOSITEADS (ID);
 ALTER TABLE COMPADSMAP ADD CONSTRAINT COMPADSMAP_ADS_FK FOREIGN KEY (ADS)
 REFERENCES ANALYSISDATASET (ID)
 ON DELETE CASCADE;
 ALTER TABLE COMPADSMAP ADD CONSTRAINT COMPADSMAP_CREATEDBY_FK FOREIGN KEY (CREATEDBY)
 REFERENCES PERSON (ID);
 ALTER TABLE COMPADSMAP ADD CONSTRAINT COMPADSMAP_LASTMODIFIEDBY_FK FOREIGN KEY
(LASTMODIFIEDBY)
 REFERENCES PERSON (ID);

 ALTER TABLE FILEASSOC ADD PRIMARY KEY (ID);
 ALTER TABLE FILEASSOC ADD UNIQUE (THISFILE, ITSASSOC);
 ALTER TABLE FILEASSOC ADD CONSTRAINT FILEASSOC_THISFILE_FK FOREIGN KEY (THISFILE)
 REFERENCES FILES (ID)
 ON DELETE CASCADE;
 ALTER TABLE FILEASSOC ADD CONSTRAINT FILEASSOC_ITSASSOC_FK FOREIGN KEY (ITSASSOC)
 REFERENCES FILES (ID)
 ON DELETE CASCADE;
 ALTER TABLE FILEASSOC ADD CONSTRAINT FILEASSOC_CREATEDBY_FK FOREIGN KEY (CREATEDBY)
 REFERENCES PERSON (ID);
 ALTER TABLE FILEASSOC ADD CONSTRAINT FILEASSOC_LASTMODIFIEDBY_FK FOREIGN KEY
(LASTMODIFIEDBY)
 REFERENCES PERSON (ID);

-- INDEXES
--YG. Removed as well in upgradeLocal.sql July 09, 07
--CREATE INDEX IX_COMPOSITEADS_DESCRIPTION ON COMPOSITEADS(DESCRIPTION);
--CREATE INDEX IX_QUALITYVERSION_VERSIONNAME ON QUALITYVERSION(VERSIONNAME);
--CREATE INDEX IX_QUALITYVERSION_DESCRIPTION ON QUALITYVERSION(DESCRIPTION);

--YG. Why is it comment out here, but it in DBS105 deploy scripts?
--YG. Will fix it in 105. 
-- CREATE INDEX IX_ANALYSISDATASET_DESCRIPTION ON ANALYSISDATASET(DESCRIPTION); 

DROP INDEX IX_ANALYSISDATASET_ANNOTATION;
 

-- SEQ

 CREATE SEQUENCE seq_compositeads;
 CREATE SEQUENCE seq_qualityvalues;
 CREATE SEQUENCE seq_subsystem;
 CREATE SEQUENCE seq_runlumiquality;
 CREATE SEQUENCE seq_qualityhistory;
 CREATE SEQUENCE seq_qualityversion;
 CREATE SEQUENCE seq_compadsmap;
 CREATE SEQUENCE seq_fileassoc;

-- AUTO INC ID Triggers
-- ====================================================
-- AUTO INC TRIGGER FOR compositeads.ID using SEQ seq_compositeads

PROMPT AUTO INC TRIGGER FOR Trigger for Table: compositeads
 CREATE OR REPLACE TRIGGER compositeads_TRIG before insert on compositeads    for each row begin     if inserting then       if :NEW.ID is null then          select seq_compositeads.nextval into :NEW.ID from dual;       end if;    end if; end;
/

-- ====================================================
-- AUTO INC TRIGGER FOR qualityvalues.ID using SEQ seq_qualityvalues

PROMPT AUTO INC TRIGGER FOR Trigger for Table: qualityvalues
 CREATE OR REPLACE TRIGGER qualityvalues_TRIG before insert on qualityvalues    for each row begin     if inserting then       if :NEW.ID is null then          select seq_qualityvalues.nextval into :NEW.ID from dual;       end if;    end if; end;
/

-- ====================================================
-- AUTO INC TRIGGER FOR subsystem.ID using SEQ seq_subsystem

PROMPT AUTO INC TRIGGER FOR Trigger for Table: subsystem
 CREATE OR REPLACE TRIGGER subsystem_TRIG before insert on subsystem    for each row begin     if inserting then       if :NEW.ID is null then          select seq_subsystem.nextval into :NEW.ID from dual;       end if;    end if; end;
/

-- ====================================================
-- AUTO INC TRIGGER FOR runlumiquality.ID using SEQ seq_runlumiquality

PROMPT AUTO INC TRIGGER FOR Trigger for Table: runlumiquality
 CREATE OR REPLACE TRIGGER runlumiquality_TRIG before insert on runlumiquality    for each row begin     if inserting then       if :NEW.ID is null then          select seq_runlumiquality.nextval into :NEW.ID from dual;       end if;    end if; end;
/

-- ====================================================
-- AUTO INC TRIGGER FOR qualityhistory.ID using SEQ seq_qualityhistory

PROMPT AUTO INC TRIGGER FOR Trigger for Table: qualityhistory
 CREATE OR REPLACE TRIGGER qualityhistory_TRIG before insert on qualityhistory    for each row begin     if inserting then       if :NEW.ID is null then          select seq_qualityhistory.nextval into :NEW.ID from dual;       end if;    end if; end;
/

-- ====================================================
-- AUTO INC TRIGGER FOR qualityversion.ID using SEQ seq_qualityversion

PROMPT AUTO INC TRIGGER FOR Trigger for Table: qualityversion
 CREATE OR REPLACE TRIGGER qualityversion_TRIG before insert on qualityversion    for each row begin     if inserting then       if :NEW.ID is null then          select seq_qualityversion.nextval into :NEW.ID from dual;       end if;    end if; end;
/

-- ====================================================
-- AUTO INC TRIGGER FOR compadsmap.ID using SEQ seq_compadsmap

PROMPT AUTO INC TRIGGER FOR Trigger for Table: compadsmap
 CREATE OR REPLACE TRIGGER compadsmap_TRIG before insert on compadsmap    for each row begin     if inserting then       if :NEW.ID is null then          select seq_compadsmap.nextval into :NEW.ID from dual;       end if;    end if; end;
/

-- ====================================================
-- AUTO INC TRIGGER FOR fileassoc.ID using SEQ seq_fileassoc

PROMPT AUTO INC TRIGGER FOR Trigger for Table: fileassoc
 CREATE OR REPLACE TRIGGER fileassoc_TRIG before insert on fileassoc    for each row begin     if inserting then       if :NEW.ID is null then          select seq_fileassoc.nextval into :NEW.ID from dual;       end if;    end if; end;
/

-- LastModified Time Stamp Trigger

 CREATE OR REPLACE TRIGGER TRTSQUALITYVALUES BEFORE INSERT OR UPDATE ON QUALITYVALUES
 FOR EACH ROW
 DECLARE
 unixtime INTEGER
 := (86400 * (SYSDATE - TO_DATE('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) -
(TO_NUMBER(SUBSTR(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
:NEW.LASTMODIFICATIONDATE := unixtime;
END;
/
SHOW ERRORS;


CREATE OR REPLACE TRIGGER TRTSSUBSYSTEM BEFORE INSERT OR UPDATE ON SUBSYSTEM
FOR EACH ROW
DECLARE
unixtime INTEGER
:= (86400 * (SYSDATE - TO_DATE('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) -
(TO_NUMBER(SUBSTR(tz_offset(sessiontimezone),1,3))) * 3600 ;
 BEGIN
 :NEW.LASTMODIFICATIONDATE := unixtime;
 END;
 /
 SHOW ERRORS;


 CREATE OR REPLACE TRIGGER TRTSRUNLUMIQUALITY BEFORE INSERT OR UPDATE ON RUNLUMIQUALITY
 FOR EACH ROW
 DECLARE
 unixtime INTEGER
 := (86400 * (SYSDATE - TO_DATE('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) -
(TO_NUMBER(SUBSTR(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
:NEW.LASTMODIFICATIONDATE := unixtime;
END;
/
SHOW ERRORS;


CREATE OR REPLACE TRIGGER TRTSQUALITYVERSION BEFORE INSERT OR UPDATE ON QUALITYVERSION
FOR EACH ROW
DECLARE
unixtime INTEGER
:= (86400 * (SYSDATE - TO_DATE('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) -
(TO_NUMBER(SUBSTR(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
:NEW.LASTMODIFICATIONDATE := unixtime;
END;
/
SHOW ERRORS;

CREATE OR REPLACE TRIGGER TRTSCOMPOSITEADS BEFORE INSERT OR UPDATE ON COMPOSITEADS
 FOR EACH ROW
 DECLARE
 unixtime INTEGER
 := (86400 * (SYSDATE - TO_DATE('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) -
(TO_NUMBER(SUBSTR(tz_offset(sessiontimezone),1,3))) * 3600 ;
 BEGIN
 :NEW.LASTMODIFICATIONDATE := unixtime;
 END;
 /
 SHOW ERRORS;

 CREATE OR REPLACE TRIGGER TRTSCOMPADSMAP BEFORE INSERT OR UPDATE ON COMPADSMAP
 FOR EACH ROW
 DECLARE
 unixtime INTEGER
 := (86400 * (SYSDATE - TO_DATE('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) -
(TO_NUMBER(SUBSTR(tz_offset(sessiontimezone),1,3))) * 3600 ;
 BEGIN
 :NEW.LASTMODIFICATIONDATE := unixtime;
 END;
 /
 SHOW ERRORS;


 CREATE OR REPLACE TRIGGER TRTSFILEASSOC BEFORE INSERT OR UPDATE ON FILEASSOC
 FOR EACH ROW
 DECLARE
 unixtime INTEGER
 := (86400 * (SYSDATE - TO_DATE('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) -
(TO_NUMBER(SUBSTR(tz_offset(sessiontimezone),1,3))) * 3600 ;
 BEGIN
 :NEW.LASTMODIFICATIONDATE := unixtime;
 END;
 /
 SHOW ERRORS;


-- CHANGES to existing tables
ALTER TABLE ANALYSISDATASET DROP UNIQUE(Name);
ALTER TABLE ANALYSISDATASET DROP CONSTRAINT ANALYSISDATASET_PARENT_FK;
ALTER TABLE ANALYSISDATASET ADD (VERSION INTEGER NOT NULL);
--YG Path is in the column already in table. So need to change it to NOT NULL.
--YG Some are not have the PATH column, then need to add it.
ALTER TABLE ANALYSISDATASET ADD (PATH VARCHAR2(500) NOT NULL);
ALTER TABLE ANALYSISDATASET MODIFY (PATH VARCHAR2(500) NOT NULL);

ALTER TABLE ANALYSISDATASET ADD (DESCRIPTION VARCHAR2(1000));
ALTER TABLE ANALYSISDATASET MODIFY(TYPE NULL);
ALTER TABLE ANALYSISDATASET MODIFY(STATUS NULL);
--YG ALTER TABLE ANALYSISDATASET DROP (ANNOTATION, PARENT); should do the next 4 
UPDATE ANALYSISDATASET SET ANNOTATION=null;
UPDATE ANALYSISDATASET SET PARENT=null;
ALTER TABLE ANALYSISDATASET DROP COLUMN ANNOTATION;
ALTER TABLE ANALYSISDATASET DROP COLUMN PARENT;

ALTER TABLE ANALYSISDATASET ADD UNIQUE (NAME, VERSION);

--YG Same here . ALTER TABLE ANALYSISDSDEF DROP(TIERS, ANALYSISDATASETS);
UPDATE ANALYSISDSDEF SET TIERS=null;
UPDATE ANALYSISDSDEF SET ANALYSISDATASETS=null;
ALTER TABLE ANALYSISDSDEF DROP COLUMN TIERS;
ALTER TABLE ANALYSISDSDEF DROP COLUMN ANALYSISDATASETS;


ALTER TABLE DATATIERORDER ADD CONSTRAINT DATATIERORDERLASTMODIFIEDBY_FK FOREIGN KEY
(LASTMODIFIEDBY) REFERENCES PERSON (ID);


ALTER TABLE SEBLOCK DROP CONSTRAINT SEBLOCK_SEID_FK;
ALTER TABLE SEBLOCK ADD CONSTRAINT SEBLOCK_SEID_FK FOREIGN KEY (SEID)
REFERENCES STORAGEELEMENT (ID)
ON DELETE CASCADE;

ALTER TABLE SEBLOCK DROP CONSTRAINT SEBLOCK_BLOCKID_FK;
ALTER TABLE SEBLOCK ADD CONSTRAINT SEBLOCK_BLOCKID_FK FOREIGN KEY (BLOCKID)
REFERENCES BLOCK (ID)
ON DELETE CASCADE;

--YG This cause ORA-01722 error when there is a string in STARTOFRUN/ENDOFRUN
-- and so on. It should check if it is a string and set it to -1 or 0.
ALTER TABLE RUNS ADD TMP_STARTOFRUN INTEGER;
UPDATE RUNS SET TMP_STARTOFRUN=STARTOFRUN;
UPDATE RUNS SET STARTOFRUN=null;
ALTER TABLE RUNS modify (STARTOFRUN INTEGER);
UPDATE RUNS SET STARTOFRUN=TMP_STARTOFRUN;
ALTER TABLE RUNS DROP COLUMN TMP_STARTOFRUN;

ALTER TABLE RUNS ADD TMP_ENDOFRUN INTEGER;
UPDATE RUNS SET TMP_ENDOFRUN=ENDOFRUN;
UPDATE RUNS SET ENDOFRUN=null;
ALTER TABLE RUNS modify (ENDOFRUN INTEGER);
UPDATE RUNS SET ENDOFRUN=TMP_ENDOFRUN;
ALTER TABLE RUNS DROP COLUMN TMP_ENDOFRUN;

ALTER TABLE LUMISECTION ADD TMP_LUMISTARTTIME INTEGER;
UPDATE LUMISECTION SET TMP_LUMISTARTTIME=LUMISTARTTIME;
UPDATE LUMISECTION SET LUMISTARTTIME=null;
ALTER TABLE LUMISECTION modify (LUMISTARTTIME INTEGER);
UPDATE LUMISECTION SET LUMISTARTTIME=TMP_LUMISTARTTIME;
ALTER TABLE LUMISECTION DROP COLUMN TMP_LUMISTARTTIME;

ALTER TABLE LUMISECTION ADD TMP_LUMIENDTIME INTEGER;
UPDATE LUMISECTION SET TMP_LUMIENDTIME=LUMIENDTIME;
UPDATE LUMISECTION SET LUMIENDTIME=null;
ALTER TABLE LUMISECTION modify (LUMIENDTIME INTEGER);
UPDATE LUMISECTION SET LUMIENDTIME=TMP_LUMIENDTIME;
ALTER TABLE LUMISECTION DROP COLUMN TMP_LUMIENDTIME;

UPDATE SchemaVersion SET SchemaVersion='DBS_1_0_5' WHERE ID=1;

INSERT INTO FileType(Type, CREATIONDATE) VALUES ('STREAMER', (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual)) ;

-- Do No Forget to do the Synonym and Grants and Roles

commit;

 
