package gov.fnal.nvs.dm.service.impl;

import java.util.List;
import java.util.ArrayList;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.jws.WebService;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.fnal.nvs.dm.exception.*;
import gov.fnal.nvs.dm.entity.NameObject;
import gov.fnal.nvs.dm.service.*;
@WebService(endpointInterface = "gov.fnal.nvs.dm.service.MainServiceWS")
public class MainServiceWSImpl implements  MainServiceWS {
	@Resource
	private WebServiceContext wsContext;


	private Log logger = LogFactory.getLog(this.getClass());

	public List<NameObject> validate(String name, String type) throws ValidateException {
		this.logger.debug(("method validate()"));
		List<NameObject> toReturn = new ArrayList<NameObject>();
		if(wsContext == null ) {
			this.logger.info("wsContext is NULL");
			return toReturn;
		}
		ServletContext servletContext =  (ServletContext) wsContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		if(servletContext == null ) {
			this.logger.info("servletContext is NULL");
			return toReturn;
		}
		if(servletContext == null ) {
			this.logger.info("MainService is NULL");
			return toReturn;
		}
		MainService service = (MainService)WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("mainService"); 
		return service.validate(name, type);
 	} 

}
