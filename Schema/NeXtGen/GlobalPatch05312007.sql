-- Add new columns to TimeLog table
ALTER TABLE TimeLog 
	ADD LastModifiedBy        integer;

ALTER TABLE TimeLog ADD CONSTRAINT
    TimeLog_LastModifiedBy_FK foreign key(LastModifiedBy) references Person(ID)
/

-- Change Defualt value of QueryableMetadata in Files table

ALTER TABLE Files
    MODIFY QueryableMetadata     varchar(1000)     default 'NOTSET';


-- CREATE FileTriggerTag Table
CREATE TABLE FileTriggerTag
(
     ID                    integer,
     Fileid                integer   not null,
     TriggerTag            varchar(500)      not null,
     NumberOfEvents        integer,
     CreationDate          integer,
     CreatedBy             integer,
     LastModificationDate  integer,
     LastModifiedBy        integer,
     primary key(ID),
     unique(Fileid,TriggerTag)
);

ALTER TABLE FileTriggerTag ADD CONSTRAINT 
    FileTriggerTag_Fileid_FK foreign key(Fileid) references Files(ID) on delete CASCADE
/
ALTER TABLE FileTriggerTag ADD CONSTRAINT 
    FileTriggerTag_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
/
ALTER TABLE FileTriggerTag ADD CONSTRAINT 
    FileTriggerTagLastModifiedB_FK foreign key(LastModifiedBy) references Person(ID)
/


grant select on FileTriggerTag to CMS_DBS_PRODG_READER_ROLE;
grant insert, update on FileTriggerTag to CMS_DBS_PRODG_WRITER_ROLE;
grant delete on FileTriggerTag to CMS_DBS_PRODG_ADMIN_ROLE;






create index ix_files_queryablemetadata on Files (QueryableMetadata);
create sequence seq_filetriggertag ;

-- AUTO INC TRIGGER FOR filetriggertag.ID using SEQ seq_filetriggertag
CREATE OR REPLACE TRIGGER filetriggertag_TRIG before insert on filetriggertag    for each row begin     if inserting then       if :NEW.ID is null then          select seq_filetriggertag.nextval into :NEW.ID from dual;       end if;    end if; end;
/

CREATE OR REPLACE TRIGGER TRTSfiletriggertag BEFORE INSERT OR UPDATE ON filetriggertag
FOR EACH ROW declare
  unixtime integer
     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;
BEGIN
  :NEW.LASTMODIFICATIONDATE := unixtime;
END;
/

INSERT INTO FileType(Type, CREATIONDATE) VALUES ('STREAMER', (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual)) ;

delete from SchemaVersion where SchemaVersion='v00_00_06';
INSERT INTO SchemaVersion(SCHEMAVERSION, CREATIONDATE) values ('DBS_1_0_4', (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual));



