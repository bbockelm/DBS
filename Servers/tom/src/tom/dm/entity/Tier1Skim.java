package tom.dm.entity;

import java.io.Serializable;

public class Tier1Skim implements Serializable {
	private static final long serialVersionUID = 6868128003366123434L;
	private String skimName;
	private String tierName;
	private String cmsswVersion;
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
	public String getProcVersion() {
		return procVersion;
	}
	public void setProcVersion(String procVersion) {
		this.procVersion = procVersion;
	}
	public String getSkimName() {
		return skimName;
	}
	public void setSkimName(String skimName) {
		this.skimName = skimName;
	}
	public String getTierName() {
		return tierName;
	}
	public void setTierName(String tierName) {
		this.tierName = tierName;
	}

}

