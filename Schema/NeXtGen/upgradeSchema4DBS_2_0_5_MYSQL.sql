CREATE TABLE BlockParent
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    ThisBlock             BIGINT UNSIGNED not null,
    ItsParent             BIGINT UNSIGNED not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    primary key(ID),
    unique(ThisBlock,ItsParent)
  );

-- 

CREATE TABLE FileProcQuality
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    ParentFile            BIGINT UNSIGNED not null,
    ChildDataset          BIGINT UNSIGNED not null,
    ProcessingStatus      BIGINT UNSIGNED not null,
    FailedEventCount      BIGINT UNSIGNED,
    FailedEventList       varchar(500),
    Description           varchar(1000),
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,

    primary key(ID),
    unique(ParentFile,ChildDataset)
  );

--

ALTER TABLE ProcDSRuns ADD Complete integer default 0;

--

CREATE TABLE ProcessingStatus
   (
    ID                    BIGINT UNSIGNED not null auto_increment,
    ProcessingStatus      varchar(50),
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    primary key(ID)
   );

--

ALTER TABLE BlockParent ADD CONSTRAINT
    BlockParent_ThisBlock_FK foreign key(ThisBlock) references Block(ID) on delete CASCADE;

ALTER TABLE BlockParent ADD CONSTRAINT
    BlockParent_ItsParent_FK foreign key(ItsParent) references Block(ID) on delete CASCADE;

ALTER TABLE BlockParent ADD CONSTRAINT
    BlockParent_CreatedBy_FK foreign key(CreatedBy) references Person(ID);

ALTER TABLE BlockParent ADD CONSTRAINT
    BlockParentLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID);

--

ALTER TABLE FileProcQuality ADD CONSTRAINT
    FPQ_ParentFile_FK foreign key(ParentFile) references Files(ID) on delete CASCADE;

ALTER TABLE FileProcQuality ADD CONSTRAINT
    FPQ_ChildDataset_FK foreign key(ChildDataset) references ProcessedDataset(ID) on delete CASCADE;

ALTER TABLE FileProcQuality ADD CONSTRAINT
    FPQ_CreatedBy_FK foreign key(CreatedBy) references Person(ID);

ALTER TABLE FileProcQuality ADD CONSTRAINT
    FPQLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID);

ALTER TABLE FileProcQuality ADD CONSTRAINT
    FPQ_Status_FK foreign key(ProcessingStatus) references ProcessingStatus(ID) on delete CASCADE;

---

ALTER TABLE ProcessingStatus ADD CONSTRAINT
    ProcStatus_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE ProcessingStatus ADD CONSTRAINT
    ProcStatusLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

-- LastModified Time Stamp Trigger

CREATE TRIGGER TR_BlockParent BEFORE INSERT ON BlockParent
FOR EACH ROW SET NEW.LastModificationDate = UNIX_TIMESTAMP();

CREATE TRIGGER TR_FileProcQuality BEFORE INSERT ON FileProcQuality
FOR EACH ROW SET NEW.LastModificationDate = UNIX_TIMESTAMP();

CREATE TRIGGER TR_ProcessStatus BEFORE INSERT ON ProcessingStatus
FOR EACH ROW SET NEW.LastModificationDate = UNIX_TIMESTAMP();

--

CREATE TRIGGER UTR_FileProcQuality BEFORE UPDATE ON FileProcQuality
FOR EACH ROW SET NEW.LastModificationDate = UNIX_TIMESTAMP();

CREATE TRIGGER UTR_BlockParent BEFORE UPDATE ON BlockParent
FOR EACH ROW SET NEW.LastModificationDate = UNIX_TIMESTAMP();

CREATE TRIGGER UTR_ProcessStatus BEFORE UPDATE ON ProcessingStatus
FOR EACH ROW SET NEW.LastModificationDate = UNIX_TIMESTAMP();

--

insert into ProcessingStatus(PROCESSINGSTATUS) values ('FAILED');
insert into ProcessingStatus(PROCESSINGSTATUS) values ('SUCCESS');

commit;



