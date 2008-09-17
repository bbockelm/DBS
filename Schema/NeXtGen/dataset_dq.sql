ALTER TABLE RunLumiQuality ADD ( Dataset integer );

ALTER TABLE RunLumiQuality ADD CONSTRAINT
    RunLumiQuality_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

--

ALTER TABLE RunLumiDQInt ADD ( Dataset integer);

ALTER TABLE RunLumiDQInt ADD CONSTRAINT
    RunLumiDQInt_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

--

ALTER TABLE QualityHistory ADD ( Dataset integer);

ALTER TABLE QualityHistory ADD CONSTRAINT
    QualityHistory_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/

--

ALTER TABLE IntQualityHistory ADD ( Dataset integer );

ALTER TABLE IntQualityHistory ADD CONSTRAINT
    IntQualityHistory_Dataset_FK foreign key(Dataset) references ProcessedDataset(ID)
/


