package tom.dm.service.impl.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import tom.dm.exception.FileException;
import tom.dm.entity.FileBase;
import tom.dm.entity.StreamerFile;
import tom.dm.dao.FileDao;
import tom.dm.service.FileService;

public class FileServiceImpl implements FileService {
	private Log logger = LogFactory.getLog(this.getClass());
	private FileDao fileDao;
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}
	public FileDao getFileDao() {
		return this.fileDao;
	}
	
	public List<FileBase> listMergedFilesByRun(final long runId) throws FileException {
		return this.fileDao.listFilesByRun(runId, "Transferable");
	}
	public List<FileBase> listUnmergedFilesByRun(final long runId) throws FileException {
		return this.fileDao.listFilesByRun(runId, "Mergeable");
	}
	public List<FileBase> listFilesByJob(final long jobId) throws FileException {
		return this.fileDao.listFilesByJob(jobId);
	}

	public List<StreamerFile> listStreamerFilesByRun(final long runId) throws FileException {
		return this.fileDao.listStreamerFilesByRun(runId);
	}
	public List<StreamerFile> listStreamerFilesByJob(final long jobId) throws FileException {
		return this.fileDao.listStreamerFilesByJob(jobId);
	}



}
