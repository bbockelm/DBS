package gov.fnal.nvs.dm.service;

import java.util.List;
import gov.fnal.nvs.dm.exception.PrimaryException;
import gov.fnal.nvs.dm.entity.Primarydataset;

public interface PrimaryService {
	public List<Primarydataset> listPrimaryDatasets() throws PrimaryException;
	public List<Primarydataset> listPrimaryDatasets(String name) throws PrimaryException;
	public List<String> listNames() throws PrimaryException;
	//public void setPrimaryDao(gov.fnal.nvs.dm.dao.PrimaryDao primaryDao);
}
