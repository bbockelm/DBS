-- ======================================================================
-- ===   sql script for database : dbs prototype
-- ===
-- === build : 171
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

create table queryableparameterset
  (
    queryableparametersetid  int,
    qpsname                  varchar(80)    not null,
    qpsversion               varchar(80)    not null,
    qpsannotation            varchar(255)   not null,
    composite                char(1)        not null,
    creationdate             date,
    createdby                int,
    lastmodificationdate     date,
    lastmodifiedby           int,

    primary key(queryableparametersetid),
    unique(qpsname,qpsversion),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid),

    check(composite in ('y', 'n'))
  );

-- ======================================================================

create table parameterbinding
  (
    parameterbindingid    int,
    parametername         varchar(80)    not null,
    parametervalue        varchar(255)   not null,
    externaldatatype      varchar(80),
    gpgid                 int            not null,
    createdby             int,
    creationdate          date,
    lastmodifiedby        int,
    lastmodificationdate  date,

    primary key(parameterbindingid),
    unique(parametername,gpgid),

    foreign key(gpgid) references queryableparameterset(queryableparametersetid),
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

create table compositequeryableparameterset
  (
    compositequeryableparametersetid  int,
    childparametersetid               int,

    primary key(compositequeryableparametersetid,childparametersetid),

    foreign key(compositequeryableparametersetid) references queryableparameterset(queryableparametersetid) on update cascade on delete cascade,
    foreign key(childparametersetid) references queryableparameterset(queryableparametersetid)
  );

-- ======================================================================

create table analysisdataset
  (
    analysisdatasetid          int,
    analysisdatasetannotation  varchar(255)   not null,
    analysisdatasettypeid      int            not null,
    compositeanalysisdataset   char(1)        not null,
    auxilliarypoolcatalog      varchar(255),
    createdby                  int,
    creationdate               date,
    lastmodifiedby             int,
    lastmodificationdate       date,

    primary key(analysisdatasetid),

    foreign key(analysisdatasettypeid) references analysisdatasetsubtype(analysisdatasettypeid) on update set null on delete set null,
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid),

    check(compositeanalysisdataset in ('y', 'n'))
  );

-- ======================================================================

create table stream
  (
    streamid              int            not null,
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

create table datatier
  (
    lastmodifiedby        int,
    lastmodificationdate  date,
    creationdate          date,
    createdby             int,
    datatierid            int,
    datatiername          varchar(255)   unique,

    primary key(datatierid),

    foreign key(lastmodifiedby) references person(personid),
    foreign key(createdby) references person(personid)
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

create table analysisdatasetstatus
  (
    analysisdatasetstatusid   int,
    analysiscollectionstatus  varchar(80)   unique not null,
    creationdate              date,
    createdby                 int,
    lastmodificationdate      date,
    lastmodifiedby            int,

    primary key(analysisdatasetstatusid),

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

create table eventcollectionstatus
  (
    eventcollectionstatusid   int,
    analysiscollectionstatus  varchar(80)   unique not null,
    creationdate              date,
    createdby                 int,
    lastmodificationdate      date,
    lastmodifiedby            int,

    primary key(eventcollectionstatusid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table runquality
  (
    runqualityid          int,
    runqualityname        varchar(80)   unique not null,
    creationdate          date,
    createdby             int,
    lastmodificationdate  date,
    lastmodifiedby        int,

    primary key(runqualityid),

    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table block
  (
    blockid               int,
    size                  numeric(10,4),
    checksum              numeric(10,4),
    openforwriting        char(1)         not null,
    createdby             int,
    creationdate          date,
    lastmodifiedby        int,
    lastmodificationdate  date,

    primary key(blockid),

    foreign key(blockid) references analysisdataset(analysisdatasetid) on update cascade on delete cascade,
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid),

    check(openforwriting in ('y', 'n'))
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

create table analysisdatasetparentage
  (
    analysisdatasetparentageid  int,
    childanalysisdatasetid      int,
    createdby                   int,
    creationdate                date,
    lastmodifiedby              int,
    lastmodificationdate        date,

    primary key(analysisdatasetparentageid,childanalysisdatasetid),

    foreign key(analysisdatasetparentageid) references analysisdataset(analysisdatasetid) on update cascade on delete cascade,
    foreign key(childanalysisdatasetid) references analysisdataset(analysisdatasetid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table primarydatasetdescription
  (
    abstractdatasetdescriptionid  int,
    triggerdescriptionid          int,
    mcchanneldescriptionid        int,
    mcdataset                     char(1)   not null,
    createdby                     int,
    creationdate                  date,
    lastmodifiedby                int,
    lastmodificationdate          date,

    primary key(abstractdatasetdescriptionid),
    unique(triggerdescriptionid,mcchanneldescriptionid),

    foreign key(triggerdescriptionid) references triggerpathdescription(triggerpathdescriptionid),
    foreign key(mcchanneldescriptionid) references mcdescription(mcdescriptionid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid),

    check(mcdataset in ('y', 'n'))
  );

-- ======================================================================

create table analysisdatasetdata
  (
    analysisdatasetdataid   int,
    analysisdatasetid       int             unique,
    numberofevents          numeric(10,4)   not null,
    estimatedluminosity     varchar(80),
    analysisdatasetstatus   int             not null,
    validationstatus        int             not null,
    cobraaccessorname       varchar(255),
    otherqueryablemetadata  int,
    creationdate            date,
    createdby               int,
    lastmodificationdate    date,
    lastmodifiedby          int,

    primary key(analysisdatasetdataid),

    foreign key(analysisdatasetid) references analysisdataset(analysisdatasetid) on update cascade on delete cascade,
    foreign key(analysisdatasetstatus) references analysisdatasetstatus(analysisdatasetstatusid),
    foreign key(validationstatus) references validationstatus(validationstatusid),
    foreign key(otherqueryablemetadata) references queryableparameterset(queryableparametersetid),
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
    primarydatasetid              int,
    abstractdatasetname           varchar(80)    unique not null,
    cobradatasetname              varchar(255)   unique not null,
    abstractdatasetannotation     varchar(255)   not null,
    abstractdatasetdescriptionid  int            not null,
    streamid                      int            not null,
    physicsgroupid                int            not null,
    openforwriting                char(1)        not null,
    startdate                     date,
    enddate                       date,
    createdby                     int,
    creationdate                  date,
    lastmodificationdate          date,
    lastmodifiedby                int,

    primary key(primarydatasetid),
    unique(abstractdatasetname,cobradatasetname,abstractdatasetdescriptionid,streamid,physicsgroupid),

    foreign key(abstractdatasetdescriptionid) references primarydatasetdescription(abstractdatasetdescriptionid),
    foreign key(streamid) references stream(streamid) on update set null on delete set null,
    foreign key(physicsgroupid) references physicsgroup(physicsgroupid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid),

    check(openforwriting in ('y', 'n'))
  );

-- ======================================================================

create table run
  (
    runid                 int,
    runnumber             smallint   unique not null,
    runquality            int        not null,
    firsteventnumber      smallint   not null,
    lasteventnumber       smallint   not null,
    startofrun            date       not null,
    endofrun              date       not null,
    createdby             int,
    creationdate          date,
    lastmodifiedby        int,
    lastmodificationdate  date,

    primary key(runid),

    foreign key(runquality) references runquality(runqualityid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table applicationconfiguration
  (
    applicationconfigurationid  int,
    applicationid               int           not null,
    parametersetid              int           not null,
    calibrationversiontag       varchar(80)   not null,
    conditionsversiontag        varchar(80)   not null,
    createdby                   int,
    creationdate                date,
    lastmodificationdate        date,
    lastmodifiedby              int,

    primary key(applicationconfigurationid),
    unique(applicationid,parametersetid,calibrationversiontag,conditionsversiontag),

    foreign key(applicationid) references application(applicationid),
    foreign key(parametersetid) references queryableparameterset(queryableparametersetid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table processingpath
  (
    processingpathid            int,
    parentprocessingpathid      int,
    applicationconfigurationid  int,
    aggregatedpath              varchar(255),
    datatierid                  int,
    creationdate                date,
    createdby                   int,
    lastmodificationdate        date,
    lastmodifiedby              int,

    primary key(processingpathid),
    unique(parentprocessingpathid,applicationconfigurationid),

    foreign key(parentprocessingpathid) references processingpath(processingpathid),
    foreign key(applicationconfigurationid) references applicationconfiguration(applicationconfigurationid),
    foreign key(datatierid) references datatier(datatierid),
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table processeddataset
  (
    processeddatasetid    int,
    primarydatasetid      int           not null,
    processingpathid      int           not null,
    openforwriting        char(1)       not null,
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
    foreign key(lastmodifiedby) references person(personid),

    check(openforwriting in ('y', 'n'))
  );

-- ======================================================================

create table eventcollection
  (
    eventcollectionid       int,
    processeddatasetid      int       not null,
    eventcollectionindex    int       not null,
    primaryeventcollection  char(1)   not null,
    creationdate            date,
    createdby               int,
    lastmodificationdate    date,
    lastmodifiedby          int,

    primary key(eventcollectionid),
    unique(processeddatasetid,eventcollectionindex),

    foreign key(processeddatasetid) references processeddataset(processeddatasetid) on update set null on delete set null,
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid),

    check(primaryeventcollection in ('y', 'n'))
  );

-- ======================================================================

create table eventcollectionrun
  (
    eventcollectionrunid  int,
    eventcollectionid     int    not null,
    runid                 int    not null,
    lastmodifiedby        int,
    lastmodificationdate  date,
    creationdate          date,
    createdby             int,

    primary key(eventcollectionrunid),
    unique(eventcollectionid,runid),

    foreign key(eventcollectionid) references eventcollection(eventcollectionid),
    foreign key(runid) references run(runid),
    foreign key(lastmodifiedby) references person(personid),
    foreign key(createdby) references person(personid)
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
    memberecid            int,
    createdby             int,
    creationdate          date,
    lastmodifiedby        int,
    lastmodificationdate  date,

    primary key(compositeecid,memberecid),
    unique(compositeecid,memberecid),

    foreign key(compositeecid) references eventcollection(eventcollectionid) on update cascade on delete cascade,
    foreign key(memberecid) references eventcollection(eventcollectionid) on update set null on delete set null,
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table eventcollectionparentage
  (
    parentecid            int,
    childecid             int,
    createdby             int,
    creationdate          date,
    lastmodifiedby        int,
    lastmodificationdate  date,

    primary key(parentecid,childecid),
    unique(parentecid,childecid),

    foreign key(parentecid) references eventcollection(eventcollectionid) on update cascade on delete cascade,
    foreign key(childecid) references eventcollection(eventcollectionid) on update set null on delete set null,
    foreign key(createdby) references person(personid),
    foreign key(lastmodifiedby) references person(personid)
  );

-- ======================================================================

create table eventcollectiondata
  (
    eventcollectiondataid   int,
    eventcollectionid       int             unique,
    numberofevents          numeric(10,4)   not null,
    estimatedluminosity     varchar(80),
    eventcollectionstatus   int             not null,
    validationstatus        int             not null,
    cobracollectionname     varchar(255)    not null,
    firsteventnumber        smallint,
    lasteventnumber         smallint,
    otherqueryablemetadata  int,
    creationdate            date,
    createdby               int,
    lastmodificationdate    date,
    lastmodifiedby          int,

    primary key(eventcollectiondataid),

    foreign key(eventcollectionid) references eventcollection(eventcollectionid) on update cascade on delete cascade,
    foreign key(eventcollectionstatus) references eventcollectionstatus(eventcollectionstatusid),
    foreign key(validationstatus) references validationstatus(validationstatusid),
    foreign key(otherqueryablemetadata) references queryableparameterset(queryableparametersetid),
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

