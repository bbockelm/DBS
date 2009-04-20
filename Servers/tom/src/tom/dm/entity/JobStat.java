package tom.dm.entity;

import java.io.Serializable;
import java.util.Date;

public class JobStat implements Serializable {
	private static final long serialVersionUID = 8130002166819440823L;
	private long jobId;
	private String name;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

