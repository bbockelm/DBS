package tom.dm.service;

import java.util.List;

import tom.dm.exception.JobException;
import tom.dm.entity.Job;

public interface JobService {
	public List<Job> listRepackMergedAcquiredJobs() throws JobException;
	public List<Job> listRecoMergedAcquiredJobs() throws JobException;
	public List<Job> listAlcaSkimMergedAcquiredJobs() throws JobException;
	public List<Job> listRecoAcquiredJobs() throws JobException;
	public List<Job> listAlcaSkimAcquiredJobs() throws JobException;
	public List<Job> listRepackRunningJobs() throws JobException;
	public List<Job> listMergedJobsByRun(final long runId) throws JobException;
	public List<Job> listRecoJobsByRun(final long runId) throws JobException;
	public List<Job> listAlcaSkimJobsByRun(final long runId) throws JobException;
	public List<Job> listRepackJobsByRun(final long runId) throws JobException;


}
