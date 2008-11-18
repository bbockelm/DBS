package gov.fnal.nvs.dm.dao;

import java.util.List;
import gov.fnal.nvs.dm.entity.Primarydataset;

public interface PrimaryDao {
	public abstract List<Primarydataset> listPrimaryDatasets();
	public abstract List<Primarydataset> listPrimaryDatasets(final String name);
}
