package tom.dm.service;

import java.util.List;

import tom.dm.exception.FileException;
import tom.dm.entity.FileBase;
import tom.dm.entity.StreamerFile;

public interface FileService {
	public List<FileBase> listMergedFilesByRun(final long runId) throws FileException;
	public List<FileBase> listUnmergedFilesByRun(final long runId) throws FileException;
	public List<FileBase> listFilesByJob(final long jobId) throws FileException;
	public List<StreamerFile> listStreamerFilesByRun(final long runId) throws FileException;
	public List<StreamerFile> listStreamerFilesByJob(final long jobId) throws FileException;
}
