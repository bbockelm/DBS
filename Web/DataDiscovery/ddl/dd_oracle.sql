Drop table DD_HISTORY
/
DROP SEQUENCE SEQ_HISTORY
/
Drop table DD_COMMAND
/
DROP SEQUENCE SEQ_COMMAND
/
Drop table DD_INSTANCE
/
DROP SEQUENCE SEQ_INSTANCE
/
Drop table DD_USER
/
DROP SEQUENCE SEQ_USER
/
CREATE SEQUENCE SEQ_USER
/
CREATE TABLE DD_USER (
        id INTEGER NOT NULL, 
        userid VARCHAR2(60) NOT NULL, 
        PRIMARY KEY (id), 
         UNIQUE (userid)
)
/
CREATE SEQUENCE SEQ_INSTANCE
/
CREATE TABLE DD_INSTANCE (
        id INTEGER NOT NULL, 
        dbsinstance VARCHAR2(60) NOT NULL, 
        PRIMARY KEY (id), 
         UNIQUE (dbsinstance)
)
/
CREATE SEQUENCE SEQ_COMMAND
/
CREATE TABLE DD_COMMAND (
        id INTEGER NOT NULL, 
        command VARCHAR2(1000), 
        alias VARCHAR2(1000), 
        PRIMARY KEY (id)
)
/
CREATE SEQUENCE SEQ_HISTORY
/
CREATE TABLE DD_HISTORY (
        id INTEGER NOT NULL, 
        userid INTEGER NOT NULL, 
        cmdid INTEGER NOT NULL, 
        dbsid INTEGER NOT NULL, 
        history_date DATE,
        history_time VARCHAR2(100),
        PRIMARY KEY (id), 
         FOREIGN KEY(userid) REFERENCES "DD_USER" (id), 
         FOREIGN KEY(dbsid) REFERENCES "DD_INSTANCE" (id), 
         FOREIGN KEY(cmdid) REFERENCES "DD_COMMAND" (id)
)
/
grant select, update, delete, insert on DD_INSTANCE to CMS_DBS_DISCOVERY_W
/
grant select, update, delete, insert on DD_COMMAND to CMS_DBS_DISCOVERY_W
/
grant select, update, delete, insert on DD_HISTORY to CMS_DBS_DISCOVERY_W
/
grant select, update, delete, insert on DD_USER to CMS_DBS_DISCOVERY_W
/
grant select on SEQ_HISTORY to CMS_DBS_DISCOVERY_W
/
grant select on SEQ_COMMAND to CMS_DBS_DISCOVERY_W
/
grant select on SEQ_USER to CMS_DBS_DISCOVERY_W
/
grant select on SEQ_INSTANCE to CMS_DBS_DISCOVERY_W
/

