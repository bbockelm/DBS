package gov.fnal.nvs.dm.dao;

import java.util.List;
import gov.fnal.nvs.dm.entity.Processeddataset;

public interface ProcessedDao {
	public abstract List<Processeddataset> listProcessedDatasets();
}
