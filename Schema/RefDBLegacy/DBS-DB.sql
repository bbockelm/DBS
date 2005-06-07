-- ======================================================================
-- ===   sql script for database : dbs prototype
-- ===
-- === build : 137
-- ======================================================================

create table person
  (
    personid              int,
    name                  varchar(80)    unique not null,
    distinguishedname     varchar(255)   unique not null,
    contactinfo           varchar(255)   not null,
    creationdate          date,
    createdby             int,
    lastmodificationdate  date,
    lastmodifiedby        int,

    primary key(personid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table role
  (
    roleid                int,
    rolename              varchar(80)    unique not null,
    roledescription       varchar(255)   not null,
    creationdate          date,
    createdby             int,
    lastmodificationdate  date,
    lastmodifiedby        int,

    primary key(roleid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table assignedrole
  (
    assignedroleid        int,
    personid              int    not null,
    roleid                int    not null,
    creationdate          date,
    createdby             int,
    lastmodificationdate  date,
    lastmodifiedby        int,

    primary key(assignedroleid),
    unique(personid,roleid),

    foreign key(personid) references person(personid) on update cascade on delete cascade,
    foreign key(roleid) references role(roleid) on update cascade on delete cascade,
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table physicsgroup
  (
    physicsgroupid        int,
    physicsgroupname      varchar(80)   unique not null,
    physicsgroupconvener  int,
    creationdate          date,
    createdby             int,
    lastmodificationdate  date,
    lastmodifiedby        int,

    primary key(physicsgroupid),

    foreign key(physicsgroupconvener) references person(personid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table analysisdatasetsubtype
  (
    analysisdatasettypeid          int,
    analysisdatasettypename        varchar(80)    unique not null,
    analysisdatasettypeannotation  varchar(255)   not null,
    createdby                      int,
    creationdate                   date,
    lastmodifiedby                 int,
    lastmodificationdate           date,

    primary key(analysisdatasettypeid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table parameterset
  (
    parametersetid          int,
    parametersetname        varchar(80)    not null,
    parametersetversion     varchar(80)    not null,
    parametersetannotation  varchar(255)   not null,
    composite               varchar(80)    not null,
    creationdate            date,
    createdby               int,
    lastmodificationdate    date,
    lastmodifiedby          int,

    primary key(parametersetid),
    unique(parametersetname,parametersetversion),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table parameterbinding
  (
    parameterbindingid    int,
    parametername         varchar(80)    not null,
    parametervalue        varchar(255)   not null,
    externaldatatype      varchar(80),
    parametersetid        int            not null,
    createdby             int,
    creationdate          date,
    lastmodifiedby        int,
    lastmodificationdate  date,

    primary key(parameterbindingid),
    unique(parametername,parametersetid),

    foreign key(parametersetid) references parameterset(parametersetid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table triggerpathdescription
  (
    triggerpathdescriptionid  int,
    triggerpathdescription    varchar(255)   unique not null,
    createdby                 int,
    creationdate              date,
    lastmodifiedby            int,
    lastmodificationdate      date,

    primary key(triggerpathdescriptionid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table mcdescription
  (
    mcdescriptionid       int,
    mcchanneldescription  varchar(255)   not null,
    mcproduction          varchar(255),
    mcdecaychain          varchar(255),
    createdby             int,
    creationdate          date,
    lastmodifiedby        int,
    lastmodificationdate  date,

    primary key(mcdescriptionid),
    unique(mcchanneldescription,mcproduction,mcdecaychain),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table compositeparameterset
  (
    compositeparametersetid  int,
    childparametersetid      int,

    primary key(compositeparametersetid,childparametersetid),

    foreign key(compositeparametersetid) references parameterset(parametersetid) on update cascade on delete cascade,
    foreign key(childparametersetid) references parameterset(parametersetid)
  );

-- ======================================================================

create table analysisdataset
  (
    analysisdatasetid          int,
    analysisdatasetannotation  varchar(255)   not null,
    analysisdatasettypeid      int            not null,
    compositeanalysisdataset   varchar(80)    not null,
    auxilliarypoolcatalog      varchar(255),
    createdby                  int,
    creationdate               date,
    lastmodifiedby             int,
    lastmodificationdate       date,

    primary key(analysisdatasetid),

    foreign key(analysisdatasettypeid) references analysisdatasetsubtype(analysisdatasettypeid) on update set null on delete set null,
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table stream
  (
    streamid              int,
    streamname            varchar(80)    unique not null,
    streamannotation      varchar(255)   not null,
    startdate             date,
    enddate               date,
    creationdate          date,
    createdby             int,
    lastmodificationdate  date,
    lastmodifiedby        int,

    primary key(streamid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table filestatus
  (
    filestatusid          int,
    filestatus            varchar(80)   unique not null,
    creationdate          date,
    createdby             int,
    lastmodificationdate  date,
    lastmodifiedby        int,

    primary key(filestatusid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table analysiscollectionstatus
  (
    analysiscollectionstatusid  int,
    analysiscollectionstatus    varchar(80)   unique not null,
    creationdate                date,
    createdby                   int,
    lastmodificationdate        date,
    lastmodifiedby              int,

    primary key(analysiscollectionstatusid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table validationstatus
  (
    validationstatusid    int,
    validationstatus      varchar(80)   unique,
    creationdate          date,
    createdby             int,
    lastmodificationdate  date,
    lastmodifiedby        int,

    primary key(validationstatusid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table filetype
  (
    filetypeid            int,
    filetype              varchar(80)   unique,
    creationdate          date,
    createdby             int,
    lastmodificationdate  date,
    lastmodifiedby        int,

    primary key(filetypeid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table collectiontype
  (
    collectiontypeid      int,
    collectiontype        varchar(80)   unique,
    creationdate          date,
    createdby             int,
    lastmodificationdate  date,
    lastmodifiedby        int,

    primary key(collectiontypeid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table applicationfamily
  (
    applicationfamilyid    int,
    applicationfamilyname  varchar(80)   unique not null,
    createdby              int,
    creationdate           date,
    lastmodifiedby         int,
    lastmodificationdate   date,

    primary key(applicationfamilyid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table productionassignment
  (
    productionassignmentid   int,
    productionrequestnumber  int           not null,
    productionera            varchar(80)   not null,

    primary key(productionassignmentid),
    unique(productionrequestnumber,productionera),

    foreign key(productionassignmentid) references analysisdataset(analysisdatasetid) on update cascade on delete cascade
  );

-- ======================================================================

create table block
  (
    blockid         int,
    size            numeric(10,4),
    checksum        numeric(10,4),
    openforwriting  varchar(80)     not null,

    primary key(blockid),

    foreign key(blockid) references analysisdataset(analysisdatasetid) on update cascade on delete cascade
  );

-- ======================================================================

create table usercollection
  (
    usercollectionid          int,
    usercollectionannotation  varchar(255)   not null,

    primary key(usercollectionid),

    foreign key(usercollectionid) references analysisdataset(analysisdatasetid) on update cascade on delete cascade
  );

-- ======================================================================

create table snapshot
  (
    snapshotid          int,
    query               varchar(255)   not null,
    snapshotannotation  varchar(255)   not null,

    primary key(snapshotid),

    foreign key(snapshotid) references analysisdataset(analysisdatasetid) on update cascade on delete cascade
  );

-- ======================================================================

create table application
  (
    applicationid         int,
    executablename        varchar(80)   not null,
    applicationversion    varchar(80)   not null,
    applicationfamily     int           not null,
    inputcollectiontype   int           not null,
    outputcollectiontype  int           not null,
    createdby             int,
    creationdate          date,
    lastmodifiedby        int,
    lastmodificationdate  date,

    primary key(applicationid),
    unique(executablename,applicationversion,applicationfamily),

    foreign key(applicationfamily) references applicationfamily(applicationfamilyid),
    foreign key(inputcollectiontype) references collectiontype(collectiontypeid),
    foreign key(outputcollectiontype) references collectiontype(collectiontypeid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table compositeanalysisdataset
  (
    compositeanalysisdatasetid  int,
    childanalysisdatasetid      int,
    createdby                   int,
    creationdate                date,
    lastmodifiedby              int,
    lastmodificationdate        date,

    primary key(compositeanalysisdatasetid,childanalysisdatasetid),

    foreign key(compositeanalysisdatasetid) references analysisdataset(analysisdatasetid) on update cascade on delete cascade,
    foreign key(childanalysisdatasetid) references analysisdataset(analysisdatasetid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table primarydatasetdescription
  (
    primarydatasetdescriptionid  int,
    triggerdescriptionid         int,
    mcchanneldescriptionid       int,
    mcdataset                    varchar(80)   not null,
    createdby                    int,
    creationdate                 date,
    lastmodifiedby               int,
    lastmodificationdate         date,

    primary key(primarydatasetdescriptionid),
    unique(triggerdescriptionid,mcchanneldescriptionid),

    foreign key(triggerdescriptionid) references triggerpathdescription(triggerpathdescriptionid),
    foreign key(mcchanneldescriptionid) references mcdescription(mcdescriptionid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table file
  (
    fileid                int,
    logicalfilename       varchar(255)   unique not null,
    checksum              varchar(255)   not null,
    size                  varchar(255)   not null,
    filestatus            int            not null,
    filetype              int            not null,
    createdby             int,
    creationdate          date,
    lastmodifiedby        int,
    lastmodificationdate  date,

    primary key(fileid),

    foreign key(filestatus) references filestatus(filestatusid),
    foreign key(filetype) references filetype(filetypeid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table primarydataset
  (
    primarydatasetid             int,
    primarydatasetname           varchar(80)    unique not null,
    cobradatasetname             varchar(255)   unique not null,
    primarydatasetannotation     varchar(255)   not null,
    primarydatasetdescriptionid  int            not null,
    streamid                     int            not null,
    physicsgroupid               int            not null,
    openforwriting               varchar(80)    not null,
    startdate                    date,
    enddate                      date,
    createdby                    int,
    creationdate                 date,
    lastmodificationdate         date,
    lastmodifiedby               int,

    primary key(primarydatasetid),
    unique(primarydatasetname,cobradatasetname,primarydatasetdescriptionid,streamid,physicsgroupid),

    foreign key(primarydatasetdescriptionid) references primarydatasetdescription(primarydatasetdescriptionid),
    foreign key(streamid) references stream(streamid) on update set null on delete set null,
    foreign key(physicsgroupid) references physicsgroup(physicsgroupid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table applicationconfiguration
  (
    applicationconfigurationid  int,
    applicationid               int    not null,
    parametersetid              int    not null,
    createdby                   int,
    creationdate                date,
    lastmodificationdate        date,
    lastmodifiedby              int,

    primary key(applicationconfigurationid),
    unique(applicationid,parametersetid),

    foreign key(applicationid) references application(applicationid),
    foreign key(parametersetid) references parameterset(parametersetid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table processingpath
  (
    processingpathid        int,
    parentprocessingpathid  int,
    processingrecordid      int            not null,
    aggregatedpath          varchar(255),
    creationdate            date,
    createdby               int,
    lastmodificationdate    date,
    lastmodifiedby          int,

    primary key(processingpathid),
    unique(parentprocessingpathid,processingrecordid),

    foreign key(parentprocessingpathid) references processingpath(processingpathid),
    foreign key(processingrecordid) references applicationconfiguration(applicationconfigurationid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table processeddataset
  (
    processeddatasetid    int,
    primarydatasetid      int           not null,
    processingpathid      int           not null,
    openforwriting        varchar(80)   not null,
    cobraownername        varchar(80)   not null,
    createdby             int,
    creationdate          date,
    lastmodifiedby        int,
    lastmodificationdate  date,

    primary key(processeddatasetid),
    unique(primarydatasetid,processingpathid),

    foreign key(primarydatasetid) references primarydataset(primarydatasetid) on update set null on delete set null,
    foreign key(processingpathid) references processingpath(processingpathid) on update set null on delete set null,
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table eventcollection
  (
    eventcollectionid         int,
    processeddatasetid        int           not null,
    eventcollectionindex      int           not null,
    primaryeventcollection    varchar(80)   not null,
    compositeeventcollection  varchar(80)   not null,
    runidstring               varchar(80),
    creationdate              date,
    createdby                 int,
    lastmodificationdate      date,
    lastmodifiedby            int,

    primary key(eventcollectionid),
    unique(processeddatasetid,eventcollectionindex),

    foreign key(processeddatasetid) references processeddataset(processeddatasetid) on update set null on delete set null,
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table primaryeventcollection
  (
    primaryecid                int,
    pointertoexternalparamsdb  int   not null,

    primary key(primaryecid),

    foreign key(primaryecid) references eventcollection(eventcollectionid) on update cascade on delete cascade
  );

-- ======================================================================

create table compositeeventcollection
  (
    compositeecid         int,
    childecid             int,
    createdby             int,
    creationdate          date,
    lastmodifiedby        int,
    lastmodificationdate  date,

    primary key(compositeecid,childecid),
    unique(compositeecid,childecid),

    foreign key(compositeecid) references eventcollection(eventcollectionid) on update cascade on delete cascade,
    foreign key(childecid) references eventcollection(eventcollectionid) on update set null on delete set null,
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table analysiscollectiondata
  (
    analysiscollectiondataid  int,
    analysisdatasetid         int             unique,
    eventcollectionid         int             unique,
    numberofevents            numeric(10,4)   not null,
    estimatedluminosity       varchar(80),
    analysiscollectionstatus  int             not null,
    validationstatus          int             not null,
    cobracollectionname       varchar(255)    not null,
    startingrunnumber         int,
    endingrunnumber           int,
    continuousrunrange        varchar(80),
    startingeventnumber       int,
    endingeventnumber         int,
    otherqueryablemetadata    int,
    creationdate              date,
    createdby                 int,
    lastmodificationdate      date,
    lastmodifiedby            int,

    primary key(analysiscollectiondataid),
    unique(analysisdatasetid,eventcollectionid),

    foreign key(analysisdatasetid) references analysisdataset(analysisdatasetid) on update cascade on delete cascade,
    foreign key(eventcollectionid) references eventcollection(eventcollectionid) on update cascade on delete cascade,
    foreign key(analysiscollectionstatus) references analysiscollectionstatus(analysiscollectionstatusid),
    foreign key(validationstatus) references validationstatus(validationstatusid),
    foreign key(otherqueryablemetadata) references parameterset(parametersetid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table evcollandata
  (
    evcollandataid        int,
    analysisdatasetid     int    not null,
    eventcollectionid     int    not null,
    creationdate          date,
    createdby             int,
    lastmodificationdate  date,
    lastmodifiedby        int,

    primary key(evcollandataid),
    unique(analysisdatasetid,eventcollectionid),

    foreign key(analysisdatasetid) references analysisdataset(analysisdatasetid) on update cascade on delete cascade,
    foreign key(eventcollectionid) references eventcollection(eventcollectionid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table evcollfile
  (
    evcollfileid          int,
    evcollid              int    not null,
    fileid                int    not null,
    creationdate          date,
    createdby             int,
    lastmodificationdate  date,
    lastmodifiedby        int,

    primary key(evcollfileid),
    unique(evcollid,fileid),

    foreign key(evcollid) references eventcollection(eventcollectionid) on update cascade on delete cascade,
    foreign key(fileid) references file(fileid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

