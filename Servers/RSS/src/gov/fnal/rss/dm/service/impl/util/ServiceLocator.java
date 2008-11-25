package gov.fnal.rss.dm.service.impl.util;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import gov.fnal.rss.dm.service.RunSeqService;
import gov.fnal.rss.common.BeanNames;

public class ServiceLocator {
	private Log logger = LogFactory.getLog(this.getClass());
	private ApplicationContext appContext;

	public ServiceLocator(WebServiceContext wsContext) {
		if(wsContext == null ) {
			this.logger.info("wsContext is NULL");
			return ;
		}
		ServletContext servletContext =  (ServletContext) wsContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		if(servletContext == null ) {
			this.logger.info("servletContext is NULL");
			return;
		}
		appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}
	
	public RunSeqService getRunSeqService() {
		if(appContext == null) return null;
		return (RunSeqService)appContext.getBean(BeanNames.RUN_SEQUENCE_SERVICE_BEAN_NAME); 
	}
}

