package gov.fnal.rss.ui.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class BaseBean {
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	protected ServiceLocator serviceLocator;
	
	public BaseBean() {}
	
	public ServiceLocator getServiceLocator() {
		return this.serviceLocator;
	}
	
	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.logger.debug("service locator initilized");
		this.serviceLocator = serviceLocator;
	}
	
}
