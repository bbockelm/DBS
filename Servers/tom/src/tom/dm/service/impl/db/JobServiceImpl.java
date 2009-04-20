package tom.dm.service.impl.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import tom.dm.exception.JobException;
import tom.dm.entity.Job;
import tom.dm.dao.JobDao;
import tom.dm.service.JobService;

public class JobServiceImpl implements JobService {
	private Log logger = LogFactory.getLog(this.getClass());
	private JobDao jobDao;
	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}
	public JobDao getJobDao() {
		return this.jobDao;
	}
	
	public List<Job> listRepackMergedAcquiredJobs() throws JobException { 
		return this.jobDao.listRepackMergedAcquiredJobs();
	}
	public List<Job> listRecoMergedAcquiredJobs() throws JobException { 
		return this.jobDao.listRecoMergedAcquiredJobs();
	}
	public List<Job> listAlcaSkimMergedAcquiredJobs() throws JobException { 
		return this.jobDao.listAlcaSkimMergedAcquiredJobs();
	}
	public List<Job> listRecoAcquiredJobs() throws JobException { 
		return this.jobDao.listRecoAcquiredJobs();
	}
	public List<Job> listAlcaSkimAcquiredJobs() throws JobException { 
		return this.jobDao.listAlcaSkimAcquiredJobs();
	}
	public List<Job> listRepackRunningJobs() throws JobException { 
		return this.jobDao.listRepackRunningJobs();
	}

	public List<Job> listMergedJobsByRun(final long runId) throws JobException {
		return this.jobDao.listJobsByRun("Merge%", runId);
	}
	public List<Job> listRecoJobsByRun(final long runId) throws JobException {
		return this.jobDao.listJobsByRun("PromptReco%", runId);
	}
	public List<Job> listAlcaSkimJobsByRun(final long runId) throws JobException {
		return this.jobDao.listJobsByRun("AlcaSkim%", runId);
	}

	public List<Job> listRepackJobsByRun(final long runId) throws JobException {
		return this.jobDao.listRepackJobsByRun(runId);
	}

}
