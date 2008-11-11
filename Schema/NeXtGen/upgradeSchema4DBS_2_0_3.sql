REM == ADDING XtCrossSection to Dataset

alter table ProcessedDataset add(  XtCrossSection        float );

REM == ADDING AutoCrossSection to Files 

alter table Files add(  AutoCrossSection      float );

REM == Adding IntQualityHistory table

CREATE TABLE IntQualityHistory
  (
    ID                    integer,
    HistoryOf             integer,
    HistoryTimeStamp      integer   not null,
    Run                   integer   not null,
    Lumi                  integer,
    SubSystem             integer   not null,
    IntDQValue            integer   not null,
    CreationDate          integer,
    CreatedBy             integer,
    LastModificationDate  integer,
    LastModifiedBy        integer,
    primary key(ID),
    unique(HistoryTimeStamp,Run,Lumi,SubSystem, IntDQValue)
  );

ALTER TABLE IntQualityHistory ADD CONSTRAINT
    IntQualityHistory_HistoryOf_FK foreign key(HistoryOf) references RunLumiDQInt(ID)
/
ALTER TABLE IntQualityHistory ADD CONSTRAINT
    IntQualityHistory_Run_FK foreign key(Run) references Runs(ID)
/
ALTER TABLE IntQualityHistory ADD CONSTRAINT
    IntQualityHistory_Lumi_FK foreign key(Lumi) references LumiSection(ID)
/
ALTER TABLE IntQualityHistory ADD CONSTRAINT
    IntQualityHistory_SubSystem_FK foreign key(SubSystem) references SubSystem(ID) on delete CASCADE
/
ALTER TABLE IntQualityHistory ADD CONSTRAINT
    IntQualityHistory_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE IntQualityHistory ADD CONSTRAINT
    IntQHistLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
/

create sequence seq_intqualityhistory ;

CREATE OR REPLACE TRIGGER intqualityhistory_TRIG before insert on intqualityhistory    for each row begin     if inserting then       if :NEW.ID is null then          select seq_intqualityhistory.nextval into :NEW.ID from dual;       end if;    end if; end;
/


REM == Adding Dataset column to DQ tables (RunLumiQuality, RunLumiDQInt, QualityHistory, IntQualityHistory)

alter table RunLumiQuality add Dataset int check (Dataset is not null);
ALTER TABLE RunLumiQuality ADD CONSTRAINT
    RunLumiQuality_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

--

alter table RunLumiDQInt add Dataset int check (Dataset is not null);
ALTER TABLE RunLumiDQInt ADD CONSTRAINT
    RunLumiDQInt_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

--
alter table QualityHistory add Dataset int check (Dataset is not null);
ALTER TABLE QualityHistory ADD CONSTRAINT
    QualityHistory_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

--
ALTER TABLE IntQualityHistory ADD Dataset int check ( Dataset is not null);
ALTER TABLE IntQualityHistory ADD CONSTRAINT
    IntQualityHistory_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/


REM == DQ table grants

grant select on RunLumiQuality to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on RunLumiQuality to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on RunLumiQuality to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on QualityHistory to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on QualityHistory to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on QualityHistory to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on QualityValues to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on QualityValues to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on QualityValues to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on QualityVersion to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on QualityVersion to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on QualityVersion to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on SubSystem to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on SubSystem to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on SubSystem to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on IntQualityHistory to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on IntQualityHistory to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on IntQualityHistory to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on RunLumiDQInt to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on RunLumiDQInt to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on RunLumiDQInt to CMS_DBS___dbabbr___ADMIN_ROLE;

REM == GRANTS on ADS Tables

grant select on CompADSMap to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on CompADSMap to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on CompADSMap to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on AnalysisDataset to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on AnalysisDataset to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on AnalysisDataset to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on AnalysisDSType to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on AnalysisDSType to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on AnalysisDSType to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on AnalysisDSStatus to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on AnalysisDSStatus to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on AnalysisDSStatus to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on AnalysisDSDef to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on AnalysisDSDef to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on AnalysisDSDef to CMS_DBS___dbabbr___ADMIN_ROLE;

grant select on CompositeADS to CMS_DBS___dbabbr___READER_ROLE;
grant insert, update on  CompositeADS to CMS_DBS___dbabbr___WRITER_ROLE;
grant delete on CompositeADS to CMS_DBS___dbabbr___ADMIN_ROLE;


rem == schema version upgrade

update SchemaVersion set schemaversion='DBS_1_1_3';



