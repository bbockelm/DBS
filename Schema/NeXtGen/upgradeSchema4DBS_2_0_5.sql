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
    FailedEventList       varchar(500),
    Description           varchar(1000),
    CreatedBy             integer,
    CreationDate          integer,
    LastModifiedBy        integer,
    LastModificationDate  integer,
    primary key(ID),
    unique(ParentFile,ChildDataset)
  );

CREATE TABLE ProcessingStatus
   (
    ID                    integer,
    ProcessingStatus      varchar(50),
    CreatedBy             integer,
    CreationDate          integer,
    LastModifiedBy        integer,
    LastModificationDate  integer,
    primary key(ID)
   );

--

ALTER TABLE ProcDSRuns ADD Complete integer default 0;

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
ALTER TABLE FileProcQuality ADD CONSTRAINT
    FPQ_Status_FK foreign key(ProcessingStatus) references ProcessingStatus(ID) on delete CASCADE
/

--

ALTER TABLE ProcessingStatus ADD CONSTRAINT
    ProcStatus_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ProcessingStatus ADD CONSTRAINT
    ProcStatusLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

PROMPT creating sequence seq_blockparent ;
create sequence seq_blockparent ;

PROMPT creating sequence seq_fileprocquality;
create sequence seq_fileprocquality;

PROMPT creating sequence processingstatus;
create sequence seq_processingstatus;


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
-- AUTO INC TRIGGER FOR processingstatus.ID using SEQ seq_processingstatus
PROMPT AUTO INC TRIGGER FOR Trigger for Table: processingstatus 
CREATE OR REPLACE TRIGGER processingstatusTRIG before insert on processingstatus   for each row begin     if inserting then       if :NEW.ID is null then          select seq_processingstatus.nextval into :NEW.ID from dual;       end if;    end if; end;
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

-- ====================================================
-- LastModified Time Stamp Trigger

PROMPT LastModified Time Stamp Trigger for Table: processingstatus
CREATE OR REPLACE TRIGGER TRTSprocessingstatus BEFORE INSERT OR UPDATE ON ProcessingStatus
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/

----===========

DELETE FROM SchemaVersion;
ALTER TABLE SchemaVersion ADD InstanceType varchar(10) unique not null;
INSERT INTO SchemaVersion (SchemaVersion, InstanceName, InstanceType, CreationDate) VALUES ('DBS_1_1_4', 'LOCAL', 'ORACLE', (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual));

--=============

ALTER TABLE Files ADD    Adler32  varchar(100)  default 'NOTSET';
ALTER TABLE Files ADD    MD5      varchar(100)  default 'NOTSET';


--==============================

insert into ProcessingStatus(PROCESSINGSTATUS) values ('FAILED');
insert into ProcessingStatus(PROCESSINGSTATUS) values ('SUCCESS');

--==============================

INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('HLTDEBUG', 'Adding HLTDEBUG as it can be a standalone tier now');

--=============================

grant select on BlockParent to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on BlockParent to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on BlockParent  to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on FileProcQuality to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on FileProcQuality to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on FileProcQuality  to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on ProcessingStatus to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on ProcessingStatus to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on ProcessingStatus to CMS_DBS___dbabbr___ADMIN_ROLE;

commit;

