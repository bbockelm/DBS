package gov.fnal.rss.ui.bean;

import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import gov.fnal.rss.ui.util.FacesUtils;
import gov.fnal.rss.dm.service.*;
import gov.fnal.rss.common.BeanNames;

public class ServiceLocatorBean implements ServiceLocator {
	
	private Log logger = LogFactory.getLog(this.getClass());
	private ApplicationContext appContext;
	private RunSeqService runSeqService;
	
	public ServiceLocatorBean() {
		ServletContext context = FacesUtils.getServletContext();
		this.appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		this.runSeqService = (RunSeqService)this.lookupService(BeanNames.RUN_SEQUENCE_SERVICE_BEAN_NAME);
		this.logger.info("Service locator bean is initialized");
	}
	
	public Object lookupService(String serviceBeanName) {
		return appContext.getBean(serviceBeanName);
	}
		
	public RunSeqService getRunSeqService() {
		return this.runSeqService;
	}

}
