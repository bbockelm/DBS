package tom.ui.bean;

import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import tom.ui.util.FacesUtils;
import tom.dm.service.*;
import tom.common.BeanNames;

public class ServiceLocatorBean implements ServiceLocator {
	
	private Log logger = LogFactory.getLog(this.getClass());
	private ApplicationContext appContext;
	private RunService runService;
	private JobService jobService;
	private FileService fileService;
	private StatService statService;
	
	public ServiceLocatorBean() {
		ServletContext context = FacesUtils.getServletContext();
		this.appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		this.runService = (RunService)this.lookupService(BeanNames.RUN_SERVICE_BEAN_NAME);
		this.jobService = (JobService)this.lookupService(BeanNames.JOB_SERVICE_BEAN_NAME);
		this.fileService = (FileService)this.lookupService(BeanNames.FILE_SERVICE_BEAN_NAME);
		this.statService = (StatService)this.lookupService(BeanNames.STAT_SERVICE_BEAN_NAME);
		this.logger.info("Service locator bean is initialized");
	}
	
	public Object lookupService(String serviceBeanName) {
		return appContext.getBean(serviceBeanName);
	}
		
	public RunService getRunService() {
		return this.runService;
	}
	public JobService getJobService() {
		return this.jobService;
	}
	public FileService getFileService() {
		return this.fileService;
	}
	public StatService getStatService() {
		return this.statService;
	}


}
