--ALTER TABLE RunLumiQuality ADD ( Dataset integer  not null);
alter table RunLumiQuality add Dataset int check (Dataset is not null);
:wq

ALTER TABLE RunLumiQuality ADD CONSTRAINT
    RunLumiQuality_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

--

ALTER TABLE RunLumiDQInt ADD ( Dataset integer not null);

ALTER TABLE RunLumiDQInt ADD CONSTRAINT
    RunLumiDQInt_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

--

ALTER TABLE QualityHistory ADD ( Dataset integer not null);

ALTER TABLE QualityHistory ADD CONSTRAINT
    QualityHistory_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

--

ALTER TABLE IntQualityHistory ADD ( Dataset integer  not null);

ALTER TABLE IntQualityHistory ADD CONSTRAINT
    IntQualityHistory_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/



select * from RunLumiQuality;

ALTER TABLE RunLumiQuality ADD ( Dataset integer);
alter table RunLumiQuality add Dataset int check (Dataset is not null);


alter table RunLumiQuality modify (Dataset integer);
desc RunLumiQuality;
alter table RunLumiQuality drop column dataset;

ALTER TABLE RunLumiQuality ADD CONSTRAINT
    RunLumiQuality_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

--
alter table RunLumiDQInt add Dataset int check (Dataset is not null);
ALTER TABLE RunLumiDQInt ADD ( Dataset integer not null);
desc RunLumiDQInt;
ALTER TABLE RunLumiDQInt ADD CONSTRAINT
    RunLumiDQInt_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

--
alter table QualityHistory add Dataset int check (Dataset is not null);
ALTER TABLE QualityHistory ADD ( Dataset integer not null);
Desc QualityHistory;
ALTER TABLE QualityHistory ADD CONSTRAINT
    QualityHistory_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

--
desc IntQualityHistory;
ALTER TABLE IntQualityHistory ADD ( Dataset integer  not null);
alter table IntQualityHistory add Dataset int check (Dataset is not null);
ALTER TABLE IntQualityHistory ADD CONSTRAINT
    IntQualityHistory_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

alter table IntQualityHistory drop column dataset;



select * from SchemaVersion;

update cms_dbs_prod_global.SchemaVersion set schemaversion='DBS_1_1_3';
















