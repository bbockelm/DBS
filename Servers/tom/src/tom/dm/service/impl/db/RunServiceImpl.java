package tom.dm.service.impl.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import tom.dm.exception.RunException;
import tom.dm.entity.Run;
import tom.dm.entity.RunDetail;
import tom.dm.dao.RunDao;
import tom.dm.service.RunService;

public class RunServiceImpl implements RunService {
	private Log logger = LogFactory.getLog(this.getClass());
	private RunDao runDao;
	public void setRunDao(RunDao runDao) {
		this.runDao = runDao;
	}
	public RunDao getRunDao() {
		return this.runDao;
	}
	
	public List<Run> listRuns()  throws RunException {
		return this.runDao.listRuns();
	}
	public List<RunDetail> listRunDetail(final long runId)  throws RunException {
		return this.runDao.listRunDetail(runId);
	}


}
