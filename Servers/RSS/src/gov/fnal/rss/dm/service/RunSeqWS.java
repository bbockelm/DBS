package gov.fnal.rss.dm.service;

import javax.jws.WebService;
import javax.jws.WebMethod;

import gov.fnal.rss.dm.exception.DuplicateRunSeqException;
import gov.fnal.rss.dm.exception.RunSeqException;


@WebService
public interface RunSeqWS {

	@WebMethod
	public long getNextRunNumber(final String name) throws RunSeqException;
	@WebMethod
	public long getCurrRunNumber(final String name) throws RunSeqException;
	@WebMethod
	public void createRunSequence(String name, long startNumber, long endNumber) throws RunSeqException, DuplicateRunSeqException;

}
