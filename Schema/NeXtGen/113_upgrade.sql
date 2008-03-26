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


-- create synonym PROCADSPARENT for CMS_DBS_PH_ANALYSIS_01.ProcADSParent

grant select on ProcADSParent to CMS_DBS_CDPA01_READER_ROLE;
grant insert, update on ProcADSParent to CMS_DBS_CDPA01_WRITER_ROLE;
grant delete on ProcADSParent to CMS_DBS_CDPA01_ADMIN_ROLE;




