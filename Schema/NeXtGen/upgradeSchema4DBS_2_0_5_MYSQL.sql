CREATE TABLE BlockParent
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    ThisBlock             BIGINT UNSIGNED   not null,
    ItsParent             BIGINT UNSIGNED   not null,
    CreationDate          BIGINT,
    CreatedBy             BIGINT UNSIGNED,
    LastModificationDate  BIGINT,
    LastModifiedBy        BIGINT UNSIGNED,
    primary key(ID),
    unique(ThisBlock,ItsParent)
  );

ALTER TABLE BlockParent ADD CONSTRAINT
    BlockParent_ThisBlock_FK foreign key(ThisBlock) references Block(ID) on delete CASCADE;

ALTER TABLE BlockParent ADD CONSTRAINT
    BlockParent_ItsParent_FK foreign key(ItsParent) references Block(ID) on delete CASCADE;

ALTER TABLE BlockParent ADD CONSTRAINT
    BlockParent_CreatedBy_FK foreign key(CreatedBy) references Person(ID);

ALTER TABLE BlockParent ADD CONSTRAINT
    BlockParentLastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID);

-- LastModified Time Stamp Trigger

CREATE TRIGGER TR_BlockParent BEFORE INSERT ON BlockParent
FOR EACH ROW SET NEW.LastModificationDate = UNIX_TIMESTAMP();

CREATE TRIGGER UTR_BlockParent BEFORE UPDATE ON BlockParent
FOR EACH ROW SET NEW.LastModificationDate = UNIX_TIMESTAMP();


