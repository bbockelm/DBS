package tom.dm.entity;

import java.io.Serializable;

public class Reco implements Serializable {
	private static final long serialVersionUID = 6598695031234223468L;
	private boolean recoEnabled;
	private String cmsswVersion;
	private String globalTag;
	private String configUrl;
	private String procVersion;
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
	public String getGlobalTag() {
		return globalTag;
	}
	public void setGlobalTag(String globalTag) {
		this.globalTag = globalTag;
	}
	public String getProcVersion() {
		return procVersion;
	}
	public void setProcVersion(String procVersion) {
		this.procVersion = procVersion;
	}
	public boolean getRecoEnabled() {
		return recoEnabled;
	}
	public void setRecoEnabled(boolean recoEnabled) {
		this.recoEnabled = recoEnabled;
	}
}

