package tom.dm.entity;

import java.io.Serializable;

public class StreamerFile extends FileBase implements Serializable {
	private static final long serialVersionUID = -3663099779053771359L;
	private long lumiId;
	private long fileSize;
	private long streamerId;
	private String streamerName;
	private String dataset;
	private String lfn;
	private long events;
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

	public long getLumiId() {
		return lumiId;
	}
	public void setLumiId(long lumiId) {
		this.lumiId = lumiId;
	}
	public long getStreamerId() {
		return streamerId;
	}
	public void setStreamerId(long streamerId) {
		this.streamerId = streamerId;
	}
	public String getStreamerName() {
		return streamerName;
	}
	public void setStreamerName(String streamerName) {
		this.streamerName = streamerName;
	}
	public String getDataset() {
		return dataset;
	}
	public void setDataset(String dataset) {
		this.dataset = dataset;
	}
	public String getLfn() {
		return lfn;
	}
	public void setLfn(String lfn) {
		this.lfn = lfn;
	}

}

