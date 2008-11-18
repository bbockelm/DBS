package gov.fnal.nvs.ui.bean;
import gov.fnal.nvs.dm.service.*;

public interface ServiceLocator {
	public PrimaryService getPrimaryService();
	public ProcessedService getProcessedService();
	public TierService getTierService();
	public PhysicsService getPhysicsService();
	public MainService getMainService();
}
