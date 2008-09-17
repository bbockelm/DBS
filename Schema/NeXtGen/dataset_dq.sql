ALTER TABLE RunLumiQuality ADD ( Dataset integer  not null);

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


