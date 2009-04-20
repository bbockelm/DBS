package tom.dm.entity;

import java.io.Serializable;
import java.util.Date;

public class Job implements Serializable {
	private static final long serialVersionUID = 4739128194460823525L;
	private long jobId;
	private long runId;
	private Date definitionTime;
	private long numberOfFiles;
	private String dataset;
	private String status;
	private String name;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDataset() {
		return dataset;
	}
	public void setDataset(String dataset) {
		this.dataset = dataset;
	}
	public Date getDefinitionTime() {
		return definitionTime;
	}
	public void setDefinitionTime(Date definitionTime) {
		this.definitionTime = definitionTime;
	}
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public long getNumberOfFiles() {
		return numberOfFiles;
	}
	public void setNumberOfFiles(long numberOfFiles) {
		this.numberOfFiles = numberOfFiles;
	}
	public long getRunId() {
		return runId;
	}
	public void setRunId(long runId) {
		this.runId = runId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}

