package tom.dm.entity;

import java.io.Serializable;

public class RepackedStat implements Serializable {
	private static final long serialVersionUID = 3502630892560491056L;
	private long newJobs;
	private long usedJobs;
	private long successfulJobs;
	private long failedJobs;
	
	public long getFailedJobs() {
		return failedJobs;
	}
	public void setFailedJobs(long failedJobs) {
		this.failedJobs = failedJobs;
	}
	public long getNewJobs() {
		return newJobs;
	}
	public void setNewJobs(long newJobs) {
		this.newJobs = newJobs;
	}
	public long getSuccessfulJobs() {
		return successfulJobs;
	}
	public void setSuccessfulJobs(long successfulJobs) {
		this.successfulJobs = successfulJobs;
	}
	public long getUsedJobs() {
		return usedJobs;
	}
	public void setUsedJobs(long usedJobs) {
		this.usedJobs = usedJobs;
	}
}
