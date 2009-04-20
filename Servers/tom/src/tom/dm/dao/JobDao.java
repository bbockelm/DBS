package tom.dm.dao;

import java.util.List;

import tom.dm.entity.Job;
import tom.dm.exception.JobException;

public interface JobDao {
	public abstract List<Job> listRepackMergedAcquiredJobs() throws JobException;
	public abstract List<Job> listRecoMergedAcquiredJobs() throws JobException;
	public abstract List<Job> listAlcaSkimMergedAcquiredJobs() throws JobException;
	public abstract List<Job> listRecoAcquiredJobs() throws JobException;
	public abstract List<Job> listAlcaSkimAcquiredJobs() throws JobException;
	public abstract List<Job> listRepackRunningJobs() throws JobException;
	public abstract List<Job> listJobsByRun(final String name, final long runId) throws JobException;
	public abstract List<Job> listRepackJobsByRun(final long runId) throws JobException;
}
