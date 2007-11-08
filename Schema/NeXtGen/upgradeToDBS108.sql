-- DBS_1_0_8 Upgrade (from 1_0_7)
-- Anzar Afaq (11/08/2007)

-- DROP TABLE Branch;
-- DROP TABLE FileBranch

PROMPT "Adding FileBranch TO files"

ALTER TABLE Files ADD (FileBranch integer);

PROMPT "Creating table RecycleBin"

CREATE TABLE RecycleBin
  (
    ID                    integer,
    Path                  varchar(500)      not null,
    Name                  varchar(700)      not null,
    Xml                   CLOB,
    CreationDate          integer,
    CreatedBy             integer,
    LastModificationDate  integer,
    LastModifiedBy        integer,
    primary key(ID)
  );

PROMPT "Creating table BranchHash"

CREATE TABLE BranchHash
  (
    ID                    integer,
    Hash                  varchar(700)      unique not null,
    Description           varchar(1000),
    Content               CLOB,
    CreationDate          integer,
    CreatedBy             integer,
    LastModificationDate  integer,
    LastModifiedBy        integer,
    primary key(ID)
  );

ALTER TABLE RecycleBin ADD CONSTRAINT 
    RecycleBin_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE RecycleBin ADD CONSTRAINT 
    RecycleBin_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

 
ALTER TABLE BranchHash ADD CONSTRAINT 
    BranchHash_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE BranchHash ADD CONSTRAINT 
    BranchHash_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/


ALTER TABLE Files ADD CONSTRAINT
    Files_FileBranch_FK foreign key(FileBranch) references BranchHash(ID)
/   

-- ====================================================
-- AUTO INC TRIGGER FOR recyclebin.ID using SEQ seq_recyclebin

create sequence seq_recyclebin ;

PROMPT AUTO INC TRIGGER FOR Trigger for Table: recyclebin
 CREATE OR REPLACE TRIGGER recyclebin_TRIG before insert on recyclebin    for each row begin     if inserting then       if :NEW.ID is null then          select seq_recyclebin.nextval into :NEW.ID from dual;       end if;    end if; end;
/
 
-- ====================================================
-- AUTO INC TRIGGER FOR branchhash.ID using SEQ seq_branchhash

create sequence seq_branchhash ;

PROMPT AUTO INC TRIGGER FOR Trigger for Table: branchhash
 CREATE OR REPLACE TRIGGER branchhash_TRIG before insert on branchhash    for each row begin     if inserting then       if :NEW.ID is null then          select seq_branchhash.nextval into :NEW.ID from dual;       end if;    end if; end;
/

-- ====================================================
-- LastModified Time Stamp Trigger


PROMPT LastModified Time Stamp Trigger for Table: recyclebin
CREATE OR REPLACE TRIGGER TRTSrecyclebin BEFORE INSERT OR UPDATE ON recyclebin
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/

-- ====================================================
-- LastModified Time Stamp Trigger

PROMPT LastModified Time Stamp Trigger for Table: branchhash
CREATE OR REPLACE TRIGGER TRTSbranchhash BEFORE INSERT OR UPDATE ON branchhash
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/


UPDATE SchemaVersion SET SchemaVersion='DBS_1_0_8';


PROMPT "Do the GRANTS and SYNONYMS NOW"
PROMPT "Do the GRANTS and SYNONYMS NOW"
PROMPT "Do the GRANTS and SYNONYMS NOW"
PROMPT "Do the GRANTS and SYNONYMS NOW"
PROMPT "Do the GRANTS and SYNONYMS NOW"


