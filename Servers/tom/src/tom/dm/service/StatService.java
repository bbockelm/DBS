package tom.dm.service;

import java.util.List;

import tom.dm.exception.StatException;
import tom.dm.entity.Stat;
import tom.dm.entity.JobStat;
import tom.dm.entity.RepackedStat;

public interface StatService {
	public RepackedStat getRepackedStat() throws StatException;
	public Stat getMergedStat() throws StatException;
	public Stat getRecoStat() throws StatException;
	public Stat getAlcaSkimStat() throws StatException;
	/*public List<JobStat> listMergedStatsByRun(final long runId) throws StatException;
	public List<JobStat> listRecoStatsByRun(final long runId) throws StatException;
	public List<JobStat> listAlcaSkimStatsByRun(final long runId) throws StatException;*/

}
