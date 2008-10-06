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


