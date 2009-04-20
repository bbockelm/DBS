package tom.dm.service;

import java.util.List;

import tom.dm.exception.RunException;
import tom.dm.entity.Run;
import tom.dm.entity.RunDetail;

public interface RunService {
        public List<Run> listRuns() throws RunException ;
        public List<RunDetail> listRunDetail(final long runId) throws RunException ;
}
