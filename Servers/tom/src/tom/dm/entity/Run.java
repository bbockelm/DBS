package tom.dm.entity;

import java.io.Serializable;
import java.util.Date;

public class Run implements Serializable {
	private static final long serialVersionUID = 487018839892731957L;
	private long id;
	private Date start;
	private Date end;
	private String process;
	private String version;
	private String hltKey;
	private String acqEra;
	private String status;
	private long streamers;
	private String repacked;
	private String reconstructed;
	private String alcaSkim;
	public String getAcqEra() {
		return acqEra;
	}
	public void setAcqEra(String acqEra) {
		this.acqEra = acqEra;
	}
	public String getAlcaSkim() {
		return alcaSkim;
	}
	public void setAlcaSkim(String alcaSkim) {
		this.alcaSkim = alcaSkim;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getHltKey() {
		return hltKey;
	}
	public void setHltKey(String hltKey) {
		this.hltKey = hltKey;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getReconstructed() {
		return reconstructed;
	}
	public void setReconstructed(String reconstructed) {
		this.reconstructed = reconstructed;
	}
	public String getRepacked() {
		return repacked;
	}
	public void setRepacked(String repacked) {
		this.repacked = repacked;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getStreamers() {
		return streamers;
	}
	public void setStreamers(long streamers) {
		this.streamers = streamers;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}

