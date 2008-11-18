package gov.fnal.nvs.dm.dao;

import java.util.List;
import gov.fnal.nvs.dm.entity.Datatier;

public interface TierDao {
	public abstract List<Datatier> listDataTiers();
}
