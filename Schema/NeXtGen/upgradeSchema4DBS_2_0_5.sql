CREATE TABLE BlockParent
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

CREATE TABLE FileProcQuality
  (
    ID                    integer,
    ParentFile            integer   not null,
    ChildDataset          integer   not null,
    ProcessingStatus      integer   not null,
    FailedEventCount      integer,
    FailedEventList       integer,
    Description           varchar(1000),
    CreatedBy             integer,
    CreationDate          integer,
    LastModifiedBy        integer,
    LastModificationDate  integer,
    primary key(ID),
    unique(ParentFile,ChildDataset)
  );

--

ALTER TABLE BlockParent ADD CONSTRAINT
    BlockParent_ThisBlock_FK foreign key(ThisBlock) references Block(ID) on delete CASCADE
/
ALTER TABLE BlockParent ADD CONSTRAINT
    BlockParent_ItsParent_FK foreign key(ItsParent) references Block(ID) on delete CASCADE
/
ALTER TABLE BlockParent ADD CONSTRAINT
    BlockParent_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE BlockParent ADD CONSTRAINT
    BlockParentLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

--

ALTER TABLE FileProcQuality ADD CONSTRAINT
    FPQ_ParentFile_FK foreign key(ParentFile) references Files(ID) on delete CASCADE
/
ALTER TABLE FileProcQuality ADD CONSTRAINT
    FPQ_ChildDataset_FK foreign key(ChildDataset) references ProcessedDataset(ID) on delete CASCADE
/
ALTER TABLE FileProcQuality ADD CONSTRAINT
    FPQ_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileProcQuality ADD CONSTRAINT
    FPQLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

PROMPT creating sequence seq_blockparent ;
create sequence seq_blockparent ;

PROMPT creating sequence seq_fileprocquality;
create sequence seq_fileprocquality;

-- ====================================================
-- AUTO INC TRIGGER FOR blockparent.ID using SEQ seq_blockparent

PROMPT AUTO INC TRIGGER FOR Trigger for Table: blockparent
 CREATE OR REPLACE TRIGGER blockparent_TRIG before insert on blockparent    for each row begin     if inserting then       if :NEW.ID is null then          select seq_blockparent.nextval into :NEW.ID from dual;       end if;    end if; end;
/

-- ====================================================
-- AUTO INC TRIGGER FOR fileprocquality.ID using SEQ seq_fileprocquality

PROMPT AUTO INC TRIGGER FOR Trigger for Table: fileprocquality
 CREATE OR REPLACE TRIGGER fileprocquality_TRIG before insert on fileprocquality    for each row begin     if inserting then       if :NEW.ID is null then          select seq_fileprocquality.nextval into :NEW.ID from dual;       end if;    end if; end;
/

-- ====================================================
-- LastModified Time Stamp Trigger

PROMPT LastModified Time Stamp Trigger for Table: blockparent
CREATE OR REPLACE TRIGGER TRTSblockparent BEFORE INSERT OR UPDATE ON BlockParent
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/

-- ====================================================
-- LastModified Time Stamp Trigger

PROMPT LastModified Time Stamp Trigger for Table: fileprocquality
CREATE OR REPLACE TRIGGER TRTSfileprocquality BEFORE INSERT OR UPDATE ON FileProcQuality
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/
--


