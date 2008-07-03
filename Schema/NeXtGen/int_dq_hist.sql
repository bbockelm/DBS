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

PROMPT creating sequence seq_intqualityhistory ;
create sequence seq_intqualityhistory ;

-- ====================================================
-- AUTO INC TRIGGER FOR intqualityhistory.ID using SEQ seq_intqualityhistory

PROMPT AUTO INC TRIGGER FOR Trigger for Table: intqualityhistory
 CREATE OR REPLACE TRIGGER intqualityhistory_TRIG before insert on intqualityhistory    for each row begin     if inserting then       if :NEW.ID is null then          select seq_intqualityhistory.nextval into :NEW.ID from dual;       end if;    end if; end;
/


