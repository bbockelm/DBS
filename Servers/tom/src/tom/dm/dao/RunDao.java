package tom.dm.dao;

import java.util.List;

import tom.dm.entity.Run;
import tom.dm.entity.RunDetail;
import tom.dm.exception.RunException;

public interface RunDao {
	public abstract List<Run> listRuns() throws RunException;
	public abstract List<RunDetail> listRunDetail(final long runId) throws RunException;
}
