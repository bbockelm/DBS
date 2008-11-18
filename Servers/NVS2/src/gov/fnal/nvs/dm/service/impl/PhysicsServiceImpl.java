package gov.fnal.nvs.dm.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.fnal.nvs.dm.exception.PhysicsException;
import gov.fnal.nvs.dm.entity.Physicsgroup;
import gov.fnal.nvs.dm.dao.PhysicsDao;
import gov.fnal.nvs.dm.service.PhysicsService;

public class PhysicsServiceImpl implements PhysicsService {
	private Log logger = LogFactory.getLog(this.getClass());
	private PhysicsDao processedDao;
	public void setPhysicsDao(PhysicsDao processedDao) {
		this.processedDao = processedDao;
	}
	public PhysicsDao getPhysicsDao() {
		return this.processedDao;
	}

	public List<Physicsgroup> listPhysicsGroups() throws PhysicsException {
		this.logger.debug(("method listPhysicsGroups()"));
		try {
			return this.processedDao.listPhysicsGroups();
		} catch(Exception e) {
			String msg = "Could not get list of Physics Groups";
			this.logger.error(msg, e);
			throw new PhysicsException(msg, e);
		}
	}

	public List<String> listNames() throws PhysicsException {
		List<String> toReturn = new ArrayList<String>();
		this.logger.debug(("method listNames()"));
		List<Physicsgroup> myList = listPhysicsGroups();
		for (Physicsgroup aObj: myList) toReturn.add(aObj.getPhysicsgroupname());
		return toReturn;
	}

}
