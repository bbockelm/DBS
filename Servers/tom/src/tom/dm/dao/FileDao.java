package tom.dm.dao;

import java.util.List;

import tom.dm.entity.FileBase;
import tom.dm.entity.StreamerFile;
import tom.dm.exception.FileException;

public interface FileDao {
	public abstract List<FileBase> listFilesByRun(final long runId, final String name) throws FileException;
	public abstract List<FileBase> listFilesByJob(final long jobId) throws FileException;
	public abstract List<StreamerFile> listStreamerFilesByRun(final long runId) throws FileException;
	public abstract List<StreamerFile> listStreamerFilesByJob(final long jobId) throws FileException;
}
