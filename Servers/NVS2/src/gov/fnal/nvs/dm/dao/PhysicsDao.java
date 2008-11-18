package gov.fnal.nvs.dm.dao;

import java.util.List;
import gov.fnal.nvs.dm.entity.Physicsgroup;

public interface PhysicsDao {
	public abstract List<Physicsgroup> listPhysicsGroups();
}
