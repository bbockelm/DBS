/* ---------------------------------------------------------------------- */
/* Script generated with: DeZign for Databases v5.1.1                     */
/* Target DBMS:           Oracle 11g                                      */
/* Project file:          Project2.dez                                    */
/* Project name:                                                          */
/* Author:                                                                */
/* Script type:           Database creation script                        */
/* Created on:            2008-09-08 13:57                                */
/* Model version:         Version 2008-09-08                              */
/* ---------------------------------------------------------------------- */


/* ---------------------------------------------------------------------- */
/* Tables                                                                 */
/* ---------------------------------------------------------------------- */

/* ---------------------------------------------------------------------- */
/* Add table "Person"                                                     */
/* ---------------------------------------------------------------------- */

CREATE TABLE Person (
    ID NUMBER(19) CONSTRAINT PrNN_ID NOT NULL,
    Name VARCHAR2(100),
    DistinguishedName VARCHAR2(500) CONSTRAINT NN_DistinguishedName NOT NULL,
    ContactInfo VARCHAR2(250),
    CreationDate NUMBER(19),
    CreatedBy NUMBER(19),
    LastModificationDate NUMBER(19),
    LastModifiedBy NUMBER(19),
    CONSTRAINT PK_Person PRIMARY KEY (ID),
    CONSTRAINT TUC_Person_2 UNIQUE (DistinguishedName)
);

/* ---------------------------------------------------------------------- */
/* Add table "Registration"                                               */
/* ---------------------------------------------------------------------- */

CREATE TABLE Registration (
    ID NUMBER(19) CONSTRAINT RegNN_ID NOT NULL,
    URL VARCHAR2(700) CONSTRAINT NN_URL NOT NULL,
    Alias VARCHAR2(250) CONSTRAINT NN_Alias NOT NULL,
    AccountName VARCHAR2(250),
    PhysicalLocation VARCHAR2(250),
    NodeName VARCHAR2(250),
    DbName VARCHAR2(250),
    DbPort NUMBER(10),
    Status VARCHAR2(100),
    Critical VARCHAR2(100),
    SchemaVersion VARCHAR2(250),
    ServerVersion VARCHAR2(250),
    CreationDate NUMBER(19),
    LastModificationDate NUMBER(19),
    Admin NUMBER(19),
    CONSTRAINT PK_Registration PRIMARY KEY (ID),
    CONSTRAINT TUC_Registration_3 UNIQUE (URL),
    CONSTRAINT TUC_Registration_4 UNIQUE (Alias)
);

/* ---------------------------------------------------------------------- */
/* Foreign key constraints                                                */
/* ---------------------------------------------------------------------- */

ALTER TABLE Registration ADD CONSTRAINT Registration_Admin_FK
    FOREIGN KEY (Admin) REFERENCES Person (ID);

--sequences

create sequence seq_person ;
create sequence seq_request ;

-- Triggers

 CREATE OR REPLACE TRIGGER Person_TRIG before insert on Person for each row begin  if inserting then  if :NEW.ID is null then select seq_person.nextval into :NEW.ID from dual; end if;    end if; end;
/


 CREATE OR REPLACE TRIGGER Registration_TRIG before insert on Registration for each row begin  if inserting then  if :NEW.ID is null then select seq_request.nextval into :NEW.ID from dual; end if;    end if; end;
/



