--
--
-- sql file to update Schema for DBS_1_1_5 release
--
--

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

-- ======================================================================

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

-- SEQ
 
PROMPT creating sequence seq_procadsparent ;
create sequence seq_procadsparent ;

-- AUTO INC TRIGGER FOR procadsparent.ID using SEQ seq_procadsparent

PROMPT AUTO INC TRIGGER FOR Trigger for Table: procadsparent
 CREATE OR REPLACE TRIGGER procadsparent_TRIG before insert on procadsparent    for each row begin     if inserting then       if :NEW.ID is null then          select seq_procadsparent.nextval into :NEW.ID from dual;       end if;    end if; end;
/

-- ====================================================

PROMPT LastModified Time Stamp Trigger for Table: procadsparent
CREATE OR REPLACE TRIGGER TRTSprocadsparent BEFORE INSERT OR UPDATE ON procadsparent
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/

UPDATE SchemaVersion SET SchemaVersion='DBS_1_1_0';


