package tom.dm.entity;

import java.io.Serializable;

public class FileBase implements Serializable {
	private static final long serialVersionUID = -3174130157960058684L;
	private long id;
	private long fileSize;
	private long events;
	private String lfn;
	private String primaryDataset;
	private String processedDataset;
	private String dataTier;

	public String getPrimaryDataset() {
		return primaryDataset;
	}
	public void setPrimaryDataset(String primaryDataset) {
		this.primaryDataset = primaryDataset;
	}
	public String getProcessedDataset() {
		return processedDataset;
	}
	public void setProcessedDataset(String processedDataset) {
		this.processedDataset = processedDataset;
	}
	public String getDataTier() {
		return dataTier;
	}
	public void setDataTier(String dataTier) {
		this.dataTier = dataTier;
	}

	public long getEvents() {
		return events;
	}
	public void setEvents(long events) {
		this.events = events;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLfn() {
		return lfn;
	}
	public void setLfn(String lfn) {
		this.lfn = lfn;
	}
}

