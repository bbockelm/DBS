##
#WE NEED to craete FOLLOWINg Views (Both of them) at every DBS GLOBALA deployment:
----------------------- CMS_DBS_PROD_GLOBAL
ALTER TABLE SEBlock rename to SEBlock_ORIG;

CREATE OR REPLACE FORCE VIEW CMS_DBS_PROD_GLOBAL.SEBlock (SEID, BLOCKID) AS SELECT distinct se.ID as SEID, b.ID as BlockID from CMS_DBS_PROD_GLOBAL.StorageElement se, cms_transfermgmt.v_dbs_block_replica blkreplica, CMS_DBS_PROD_GLOBAL.Block b where b.Name=blkreplica.block_name AND se.SENAME=blkreplica.se_name;


GRANT SELECT ON CMS_DBS_PROD_GLOBAL.SEBlock TO CMS_DBS_PROD_GLOBAL_ADMIN;
GRANT SELECT ON CMS_DBS_PROD_GLOBAL.SEBlock TO CMS_DBS_PROD_GLOBAL_WRITER;
GRANT SELECT ON CMS_DBS_PROD_GLOBAL.SEBlock TO CMS_DBS_PROD_GLOBAL_READER;

--TEST the view from READER account
select count(*) from CMS_DBS_PROD_GLOBAL.SEBlock;
select * from cms_transfermgmt.v_dbs_block_replica where rownum = 1;

###
ALTER TABLE StorageElement rename to StorageElement_ORIG;

CREATE OR REPLACE FORCE VIEW CMS_DBS_INT_GLOBAL.StorageElement(ID, SENAME) AS select rownum as ID, X.se_name as SENAME from (SELECT distinct se_name from cms_transfermgmt.v_dbs_block_replica) X;


GRANT SELECT ON CMS_DBS_INT_GLOBAL.StorageElement TO CMS_DBS_INT_GLOBAL_ADMIN;
GRANT SELECT ON CMS_DBS_INT_GLOBAL.StorageElement TO CMS_DBS_INT_GLOBAL_WRITER;
GRANT SELECT ON CMS_DBS_INT_GLOBAL.StorageElement TO CMS_DBS_INT_GLOBAL_READER;

--TEST the view from READER account

select * from CMS_DBS_INT_GLOBAL.StorageElement;
















----------############ ONLY on the test bed

alter table SEBlock rename to SEBlock_ORIG;

CREATE OR REPLACE FORCE VIEW CMS_DBS_TEST_GLOBAL.SEBlock (SEID, BLOCKID) AS SELECT distinct se.ID as SEID, b.ID as BlockID from CMS_DBS_TEST_GLOBAL.StorageElement se, cms_transfermgmt_testbed.v_dbs_block_replica blkreplica, CMS_DBS_TEST_GLOBAL.Block b where b.Name=blkreplica.block_name AND se.SENAME=blkreplica.se_name;


GRANT SELECT ON CMS_DBS_TEST_GLOBAL.SEBlock TO CMS_DBS_TEST_GLOBAL_ADMIN;
GRANT SELECT ON CMS_DBS_TEST_GLOBAL.SEBlock TO CMS_DBS_TEST_GLOBAL_WRITER;
GRANT SELECT ON CMS_DBS_TEST_GLOBAL.SEBlock TO CMS_DBS_TEST_GLOBAL_READER;


select count(*) from CMS_DBS_TEST_GLOBAL.SEBlock;


---------------         ####  CMS_DBS_PROD_LOCAL_10
alter table SEBlock rename to SEBlock_ORIG;

CREATE OR REPLACE FORCE VIEW CMS_DBS_PROD_LOCAL_10.SEBlock (SEID, BLOCKID) AS SELECT distinct se.ID as SEID, b.ID as BlockID from CMS_DBS_PROD_LOCAL_10.StorageElement se, cms_transfermgmt.v_dbs_block_replica blkreplica, CMS_DBS_PROD_LOCAL_10.Block b where b.Name=blkreplica.block_name AND se.SENAME=blkreplica.se_name;


GRANT SELECT ON CMS_DBS_PROD_LOCAL_10.SEBlock TO CMS_DBS_PROD_LOCAL_10_ADMIN;
GRANT SELECT ON CMS_DBS_PROD_LOCAL_10.SEBlock TO CMS_DBS_PROD_LOCAL_10_WRITER;
GRANT SELECT ON CMS_DBS_PROD_LOCAL_10.SEBlock TO CMS_DBS_PROD_LOCAL_10_READER;


select count(*) from CMS_DBS_PROD_LOCAL_10.SEBlock;

--------------------------  CMS_DBS_INT_GLOBAL

CREATE OR REPLACE FORCE VIEW CMS_DBS_INT_GLOBAL.SEBlock (SEID, BLOCKID) AS SELECT distinct se.ID as SEID, b.ID as BlockID from CMS_DBS_INT_GLOBAL.StorageElement se, cms_transfermgmt.v_dbs_block_replica blkreplica, CMS_DBS_INT_GLOBAL.Block b where b.Name=blkreplica.block_name AND se.SENAME=blkreplica.se_name;


GRANT SELECT ON CMS_DBS_INT_GLOBAL.SEBlock TO CMS_DBS_INT_GLOBAL_ADMIN;
GRANT SELECT ON CMS_DBS_INT_GLOBAL.SEBlock TO CMS_DBS_INT_GLOBAL_WRITER;
GRANT SELECT ON CMS_DBS_INT_GLOBAL.SEBlock TO CMS_DBS_INT_GLOBAL_READER;


select count(*) from CMS_DBS_INT_GLOBAL.SEBlock;
select * from cms_transfermgmt.v_dbs_block_replica where rownum = 1;


----------------------- CMS_DBS_PROD_GLOBAL
CREATE OR REPLACE FORCE VIEW CMS_DBS_PROD_GLOBAL.SEBlock (SEID, BLOCKID) AS SELECT distinct se.ID as SEID, b.ID as BlockID from CMS_DBS_PROD_GLOBAL.StorageElement se, cms_transfermgmt.v_dbs_block_replica blkreplica, CMS_DBS_PROD_GLOBAL.Block b where b.Name=blkreplica.block_name AND se.SENAME=blkreplica.se_name;


GRANT SELECT ON CMS_DBS_PROD_GLOBAL.SEBlock TO CMS_DBS_PROD_GLOBAL_ADMIN;
GRANT SELECT ON CMS_DBS_PROD_GLOBAL.SEBlock TO CMS_DBS_PROD_GLOBAL_WRITER;
GRANT SELECT ON CMS_DBS_PROD_GLOBAL.SEBlock TO CMS_DBS_PROD_GLOBAL_READER;

GRANT SELECT ON CMS_DBS_PROD_GLOBAL.SEBlock TO CMS_DBS_GENERAL_ADMIN;
GRANT SELECT ON CMS_DBS_PROD_GLOBAL.SEBlock TO CMS_DBS_GENERAL_WRITER;
GRANT SELECT ON CMS_DBS_PROD_GLOBAL.SEBlock TO CMS_DBS_GENERAL_READER;

select count(*) from CMS_DBS_PROD_GLOBAL.SEBlock;
select * from cms_transfermgmt.v_dbs_block_replica where rownum = 1;



SELECT distinct se_name as SENAME, (select seq_se.nextval from dual) as ID from cms_transfermgmt.v_dbs_block_replica ;


SELECT distinct X.SENAME, Y.ID from (SELECT distinct se_name from cms_transfermgmt.v_dbs_block_replica) X, (select seq_se.nextval as ID from dual) Y;

select 1, (select seq_se.nextval as ID from dual) Y from dual;



select (select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual), X.se_name from (SELECT distinct se_name from cms_transfermgmt.v_dbs_block_replica) X;


CREATE OR REPLACE FORCE VIEW CMS_DBS_INT_GLOBAL.StorageElement(ID, SENAME) AS select rownum as ID, X.se_name as SENAME from (SELECT distinct se_name from cms_transfermgmt.v_dbs_block_replica) X;


GRANT SELECT ON CMS_DBS_INT_GLOBAL.StorageElement TO CMS_DBS_INT_GLOBAL_ADMIN;
GRANT SELECT ON CMS_DBS_INT_GLOBAL.StorageElement TO CMS_DBS_INT_GLOBAL_WRITER;
GRANT SELECT ON CMS_DBS_INT_GLOBAL.StorageElement TO CMS_DBS_INT_GLOBAL_READER; 




SELECT distinct 


create sequence seq_se MAXVALUE 1000 cycle;

CREATE OR REPLACE FORCE VIEW CMS_DBS_INT_GLOBAL.StorageElement(SENAME) AS SELECT distinct se_name as SENAME from cms_transfermgmt.v_dbs_block_replica ;






