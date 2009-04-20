package tom.dm.entity;

public class RunDetail {
	private static final long serialVersionUID = -6799331148292596544L;
	private String dataset;
	private Repack repack;
	private Reco reco;
	private AlcaSkim alcaSkim;
	private Tier1Skim tier1Skim;
	public String getDataset() {
		return dataset;
	}
	public void setDataset(String dataset) {
		this.dataset = dataset;
	}
	public AlcaSkim getAlcaSkim() {
		return alcaSkim;
	}
	public void setAlcaSkim(AlcaSkim alcaSkim) {
		this.alcaSkim = alcaSkim;
	}
	public Tier1Skim getTier1Skim() {
		return tier1Skim;
	}
	public void setTier1Skim(Tier1Skim tier1Skim) {
		this.tier1Skim = tier1Skim;
	}

	public Reco getReco() {
		return reco;
	}
	public void setReco(Reco reco) {
		this.reco = reco;
	}
	public Repack getRepack() {
		return repack;
	}
	public void setRepack(Repack repack) {
		this.repack = repack;
	}

}
