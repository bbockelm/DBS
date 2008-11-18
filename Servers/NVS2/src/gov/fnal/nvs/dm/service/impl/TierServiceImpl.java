package gov.fnal.nvs.dm.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.fnal.nvs.dm.exception.TierException;
import gov.fnal.nvs.dm.entity.Datatier;
import gov.fnal.nvs.dm.dao.TierDao;
import gov.fnal.nvs.dm.service.TierService;

public class TierServiceImpl implements TierService {
	private Log logger = LogFactory.getLog(this.getClass());
	private TierDao tierDao;
	public void setTierDao(TierDao tierDao) {
		this.tierDao = tierDao;
	}
	public TierDao getTierDao() {
		return this.tierDao;
	}

	public List<Datatier> listDataTiers() throws TierException {
		this.logger.debug(("method listDataTiers()"));
		try {
			return this.tierDao.listDataTiers();
		} catch(Exception e) {
			String msg = "Could not get list of Data Tiers";
			this.logger.error(msg, e);
			throw new TierException(msg, e);
		}
	}

	public List<String> listNames() throws TierException {
		List<String> toReturn = new ArrayList<String>();
		this.logger.debug(("method listNames() for Data Tiers"));
		List<Datatier> myList = listDataTiers();
		for (Datatier aObj: myList) toReturn.add(aObj.getName());
		return toReturn;
	}


}
