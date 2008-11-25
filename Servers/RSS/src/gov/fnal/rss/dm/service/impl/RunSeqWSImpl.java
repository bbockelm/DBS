package gov.fnal.rss.dm.service.impl;

import javax.xml.ws.WebServiceContext;
import javax.jws.WebService;

import javax.annotation.Resource;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.fnal.rss.dm.exception.*;
import gov.fnal.rss.dm.service.*;
import gov.fnal.rss.dm.service.impl.util.ServiceLocator;

@WebService(endpointInterface = "gov.fnal.rss.dm.service.RunSeqWS")
public class RunSeqWSImpl implements RunSeqWS {
	@Resource
	private WebServiceContext wsContext;
	private RunSeqService service;
	private Log logger = LogFactory.getLog(this.getClass());

	public RunSeqWSImpl() throws RunSeqException {
		/*ServiceLocator locator = new ServiceLocator(wsContext);
		if(locator != null) service = locator.getRunSeqService();
		else {
			String msg = "Could not initialize service context " ;
			this.logger.error(msg);
			throw new RunSeqException(msg);
		}*/
	}

	private void init() throws RunSeqException {
		if(service != null) return;
		ServiceLocator locator = new ServiceLocator(wsContext);
		if(locator != null) service = locator.getRunSeqService();
		else {
			String msg = "Could not initialize service context " ;
			this.logger.error(msg);
			throw new RunSeqException(msg);
		}
	
	}

	public long getCurrRunNumber(String name) throws RunSeqException {
		init();
		return service.getCurrRunNumber(name);
	}
	
	public long getNextRunNumber(String name) throws RunSeqException {
		init();
		return service.getNextRunNumber(name);
	}

	public void createRunSequence(String name, long startNumber, long endNumber) throws RunSeqException, DuplicateRunSeqException {
		init();
		service.createRunSequence(name, startNumber, endNumber);
 	} 

}
