package tom.dm.dao;

import java.util.List;

import tom.dm.entity.Stat;
//import tom.dm.entity.JobStat;
import tom.dm.entity.RepackedStat;
import tom.dm.exception.StatException;

public interface StatDao {
	public abstract RepackedStat getRepackedStat() throws StatException;
	public abstract Stat getStat(final String name) throws StatException;
	//public abstract List<JobStat> listStatsByRun(final String name, final long runId) throws StatException;
}
