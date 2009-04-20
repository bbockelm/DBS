package tom.dm.entity;

import java.io.Serializable;

public class AlcaSkim implements Serializable {
	private static final long serialVersionUID = 4094556579769176941L;
	private boolean alcaEnabled;
	private String cmsswVersion;
	private String configUrl;
	private String procVersion;
	public boolean getAlcaEnabled() {
		return alcaEnabled;
	}
	public void setAlcaEnabled(boolean alcaEnabled) {
		this.alcaEnabled = alcaEnabled;
	}
	public String getCmsswVersion() {
		return cmsswVersion;
	}
	public void setCmsswVersion(String cmsswVersion) {
		this.cmsswVersion = cmsswVersion;
	}
	public String getConfigUrl() {
		return configUrl;
	}
	public void setConfigUrl(String configUrl) {
		this.configUrl = configUrl;
	}
	public String getProcVersion() {
		return procVersion;
	}
	public void setProcVersion(String procVersion) {
		this.procVersion = procVersion;
	}
}

