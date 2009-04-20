package tom.dm.entity;

import java.io.Serializable;

public class Stat implements Serializable {
	private static final long serialVersionUID = 1149506355169835573L;
	private long acquiredJobs;
	private long successfulJobs;
	private long failedJobs;
	public long getAcquiredJobs() {
		return acquiredJobs;
	}
	public void setAcquiredJobs(long acquiredJobs) {
		this.acquiredJobs = acquiredJobs;
	}
	public long getFailedJobs() {
		return failedJobs;
	}
	public void setFailedJobs(long failedJobs) {
		this.failedJobs = failedJobs;
	}
	public long getSuccessfulJobs() {
		return successfulJobs;
	}
	public void setSuccessfulJobs(long successfulJobs) {
		this.successfulJobs = successfulJobs;
	}
}

