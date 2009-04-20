package tom.dm.service.impl.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import tom.dm.exception.StatException;
import tom.dm.entity.Stat;
import tom.dm.entity.JobStat;
import tom.dm.entity.RepackedStat;
import tom.dm.dao.StatDao;
import tom.dm.service.StatService;

public class StatServiceImpl implements StatService {
	private Log logger = LogFactory.getLog(this.getClass());
	private StatDao statDao;
	public void setStatDao(StatDao statDao) {
		this.statDao = statDao;
	}
	public StatDao getStatDao() {
		return this.statDao;
	}
	
	public RepackedStat getRepackedStat() throws StatException {
		return this.statDao.getRepackedStat();
	}
	public Stat getMergedStat() throws StatException {
		return this.statDao.getStat("Merge%");
	}
	public Stat getRecoStat() throws StatException {
		return this.statDao.getStat("PromptReco%");
	}
	public Stat getAlcaSkimStat() throws StatException {
		return this.statDao.getStat("AlcaSkim%");
	}

	/*public List<JobStat> listMergedStatsByRun(final long runId) throws StatException {
		return this.statDao.listStatsByRun("Merge%", runId);
	}
	public List<JobStat> listRecoStatsByRun(final long runId) throws StatException {
		return this.statDao.listStatsByRun("PromptReco%", runId);
	}
	public List<JobStat> listAlcaSkimStatsByRun(final long runId) throws StatException {
		return this.statDao.listStatsByRun("AlcaSkim%", runId);
	}*/



}
