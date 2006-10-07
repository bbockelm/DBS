-- Use this ascript after creating hte fields with the 
-- OracleAddT_blockGuidField.sql script. Once data is added to the 
-- quid field, then it can be modified to be "not null" using this script
-- L. Lueking 13 Sept. 2006

alter table t_block modify guid varchar (1000) not null;
