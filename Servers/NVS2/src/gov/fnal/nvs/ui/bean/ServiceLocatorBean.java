package gov.fnal.nvs.ui.bean;

import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import gov.fnal.nvs.ui.util.FacesUtils;
import gov.fnal.nvs.dm.service.*;

public class ServiceLocatorBean implements ServiceLocator {
	
	private Log logger = LogFactory.getLog(this.getClass());
	private ApplicationContext appContext;
	private PrimaryService primaryService;
	private ProcessedService processedService;
	private TierService tierService;
	private PhysicsService physicsService;
	private MainService mainService;
	
	public ServiceLocatorBean() {
		ServletContext context = FacesUtils.getServletContext();
		this.appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		this.primaryService = (PrimaryService)this.lookupService(BeanNames.PRIMARY_SERVICE_BEAN_NAME);
		this.processedService = (ProcessedService)this.lookupService(BeanNames.PROCESSED_SERVICE_BEAN_NAME);
		this.tierService = (TierService)this.lookupService(BeanNames.TIER_SERVICE_BEAN_NAME);
		this.physicsService = (PhysicsService)this.lookupService(BeanNames.PHYSICS_SERVICE_BEAN_NAME);
		this.mainService = (MainService)this.lookupService(BeanNames.MAIN_SERVICE_BEAN_NAME);
		this.logger.info("Service locator bean is initialized");
	}
	
	public Object lookupService(String serviceBeanName) {
		return appContext.getBean(serviceBeanName);
	}
		
	public PrimaryService getPrimaryService() {
		return this.primaryService;
	}
	public ProcessedService getProcessedService() {
		return this.processedService;
	}
	public TierService getTierService() {
		return this.tierService;
	}
	public PhysicsService getPhysicsService() {
		return this.physicsService;
	}
	public MainService getMainService() {
		return this.mainService;
	}

}
