-- Add the column "guid" to the t_block table
-- Make a unique constraint for it
-- After guid values area inserted for existing blocks, run the 
-- OracleAlterT_blockGuidNotNull.ora script to make the field not null
-- L. Lueking 13 Sept. 2006

alter table t_block add guid varchar (1000);
alter table t_block add constraint uq_block_guid unique (guid);