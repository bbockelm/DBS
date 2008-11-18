package gov.fnal.nvs.dm.service;

import java.util.List;
import gov.fnal.nvs.dm.exception.TierException;
import gov.fnal.nvs.dm.entity.Datatier;

public interface TierService {
	public List<Datatier> listDataTiers() throws TierException;
	public List<String> listNames() throws TierException;
}
