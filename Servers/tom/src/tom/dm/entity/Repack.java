package tom.dm.entity;

import java.io.Serializable;

public class Repack implements Serializable {
	private static final long serialVersionUID = 3476732218965766633L;
	private String procVersion;
	private String hltDebug;
	public String getHltDebug() {
		return hltDebug;
	}
	public void setHltDebug(String hltDebug) {
		this.hltDebug = hltDebug;
	}
	public String getProcVersion() {
		return procVersion;
	}
	public void setProcVersion(String procVersion) {
		this.procVersion = procVersion;
	}

}

