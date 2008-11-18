package gov.fnal.nvs.dm.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.fnal.nvs.dm.exception.ProcessedException;
import gov.fnal.nvs.dm.entity.Processeddataset;
import gov.fnal.nvs.dm.dao.ProcessedDao;
import gov.fnal.nvs.dm.service.ProcessedService;

public class ProcessedServiceImpl implements ProcessedService {
	private Log logger = LogFactory.getLog(this.getClass());
	private ProcessedDao processedDao;
	public void setProcessedDao(ProcessedDao processedDao) {
		this.processedDao = processedDao;
	}
	public ProcessedDao getProcessedDao() {
		return this.processedDao;
	}

	public List<Processeddataset> listProcessedDatasets() throws ProcessedException {
		this.logger.debug(("method listProcessedDatasets()"));
		try {
			return this.processedDao.listProcessedDatasets();
		} catch(Exception e) {
			String msg = "Could not get list of Processed Datasets";
			this.logger.error(msg, e);
			throw new ProcessedException(msg, e);
		}
	}

	public List<String> listNames() throws ProcessedException {
		List<String> toReturn = new ArrayList<String>();
		this.logger.debug(("method listNames() for Processed Dataset"));
		List<Processeddataset> myList = listProcessedDatasets();
		for (Processeddataset aObj: myList) toReturn.add(aObj.getName());
		return toReturn;
	}


}
