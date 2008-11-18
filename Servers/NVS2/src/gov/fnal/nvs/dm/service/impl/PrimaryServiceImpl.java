package gov.fnal.nvs.dm.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.fnal.nvs.dm.exception.PrimaryException;
import gov.fnal.nvs.dm.entity.Primarydataset;
import gov.fnal.nvs.dm.dao.PrimaryDao;
import gov.fnal.nvs.dm.service.PrimaryService;

public class PrimaryServiceImpl implements PrimaryService {
	private Log logger = LogFactory.getLog(this.getClass());
	private PrimaryDao primaryDao;
	public void setPrimaryDao(PrimaryDao primaryDao) {
		this.primaryDao = primaryDao;
	}
	public PrimaryDao getPrimaryDao() {
		return this.primaryDao;
	}

	public List<Primarydataset> listPrimaryDatasets() throws PrimaryException {
		this.logger.debug(("method listPrimaryDatasets()"));
		try {
			return this.primaryDao.listPrimaryDatasets();
		} catch(Exception e) {
			String msg = "Could not get list of Primary Datasets";
			this.logger.error(msg, e);
			throw new PrimaryException(msg, e);
		}
	}
	public List<Primarydataset> listPrimaryDatasets(String name) throws PrimaryException {
		this.logger.debug(("method listPrimaryDatasetsString name()"));
		try {
			return this.primaryDao.listPrimaryDatasets(name);
		} catch(Exception e) {
			String msg = "Could not get list of Primary Datasets based on name " + name;
			this.logger.error(msg, e);
			throw new PrimaryException(msg, e);
		}

	}

	public List<String> listNames() throws PrimaryException {
		List<String> toReturn = new ArrayList<String>();
		this.logger.debug(("method listNames() for Primary datasets"));
		List<Primarydataset> myList = listPrimaryDatasets();
		for (Primarydataset aObj: myList) toReturn.add(aObj.getName());
		return toReturn;
	}


}
