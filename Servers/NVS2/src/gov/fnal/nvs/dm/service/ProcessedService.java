package gov.fnal.nvs.dm.service;

import java.util.List;
import gov.fnal.nvs.dm.exception.ProcessedException;
import gov.fnal.nvs.dm.entity.Processeddataset;

public interface ProcessedService {
	public List<Processeddataset> listProcessedDatasets() throws ProcessedException;
	public List<String> listNames() throws ProcessedException;
}
