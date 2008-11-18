package gov.fnal.nvs.dm.service;

import java.util.List;
import gov.fnal.nvs.dm.exception.PhysicsException;
import gov.fnal.nvs.dm.entity.Physicsgroup;

public interface PhysicsService {
	public List<Physicsgroup> listPhysicsGroups() throws PhysicsException;
	public List<String> listNames() throws PhysicsException;
}
