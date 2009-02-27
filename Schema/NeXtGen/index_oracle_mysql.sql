--These indexes are for both Oracle and Mysql

CREATE INDEX ix_BLK_OpenForWriting ON Block(OpenForWriting);
create unique index IX_UNQ_BLK_PATH_ID on Block (PATH,ID);
create unique index IX_UNQ_BLK_DS_ID on Block(DATASET, ID);

CREATE INDEX ix_FL_QueryableMetadata ON Files(QueryableMetadata);
create unique index IX_UNQ_FILES_BLOCK_ID on Files(Block,ID);

create index IX_FILERUN_LUMI_lumi_run on FileRunLumi(lumi,run);

create unique index IX_UNQ_PROCESSEDDS_NAME_ID on ProcessedDataset(Name, ID);
create unique index IX_UNQ_PROCESSEDDS_Primary_ID on ProcessedDataset(PrimaryDataset, ID);
create unique index IX_UNQ_PROCESSEDDS_AQU_ID on ProcessedDataset(AQUISITIONERA, ID);
create unique index IX_UNQ_PROCESSEDDS_GTAG_ID on ProcessedDataset(GLOBALTAG, ID);

