-- ======================================================================

drop database if exists MS;
create database MS;
use MS;
-- ======================================================================

CREATE TABLE Person
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    DistinguishedName     varchar(500)      unique not null,
    primary key(ID),
    unique(DistinguishedName)
  ) ENGINE = InnoDB ;

-- ======================================================================


CREATE TABLE DBSUrl
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    Url			  varchar(500)      unique not null,
    --Version               varchar(100)   not null,
    primary key(ID),
    unique(Url)
  ) ENGINE = InnoDB ;

-- ======================================================================

CREATE TABLE Request
  (
    ID                    BIGINT UNSIGNED not null auto_increment,
    SrcUrl                BIGINT UNSIGNED not null,
    DstUrl                BIGINT UNSIGNED not null,
    Path                  varchar(500)   not null,
    WithParents           ENUM('y', 'n') ,
    WithForce             ENUM('y', 'n') ,
    CreatedBy             BIGINT UNSIGNED ,
    Status                ENUM('Queued', 'InProgress', 'Halted', 'Finished')   not null,
    Progress              INT not null,
    Detail                varchar(500),
    Notify                varchar(100) ,
    primary key(ID),
    unique(SrcUrl, DstUrl, Path)
  ) ENGINE = InnoDB ;

-- ======================================================================

--CREATE TABLE Status
 -- (
  --  ID                    BIGINT UNSIGNED not null auto_increment,
   -- Name                  ENUM('Queued', 'InProgess', 'Halted', 'Finished')   not null,
  --  Detail                varchar(500),
  --  primary key(ID)
 -- ) ENGINE = InnoDB ;

-- ======================================================================

ALTER TABLE Request ADD CONSTRAINT 
    Request_SrcUrl_FK foreign key(SrcUrl) references DBSUrl(ID)
;
ALTER TABLE Request ADD CONSTRAINT 
    Request_DstUrl_FK foreign key(DstUrl) references DBSUrl(ID)
;
ALTER TABLE Request ADD CONSTRAINT 
    Request_CreatedBy_FK foreign key(CreatedBy) references Person(ID)
;
--ALTER TABLE Request ADD CONSTRAINT 
--    Request_Status_FK foreign key(Status) references Status(ID)
--;

commit;
