package gov.fnal.rss.dm.service.impl;

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.dao.DataIntegrityViolationException;

import gov.fnal.rss.dm.exception.RunSeqException;
import gov.fnal.rss.dm.exception.DuplicateRunSeqException;
import gov.fnal.rss.dm.entity.RunSeq;
import gov.fnal.rss.dm.dao.RunSeqDao;
import gov.fnal.rss.dm.service.RunSeqService;

public class RunSeqServiceImpl implements RunSeqService {
	private static final long DEFAULT_START_VALUE = 1;
	private static final long DEFAULT_END_VALUE = -1;
	private static final String SAFE_WORD = "[-\\w_\\.%]+";
	private Log logger = LogFactory.getLog(this.getClass());
	private RunSeqDao runSeqDao;
	public void setRunSeqDao(RunSeqDao runSeqDao) {
		this.runSeqDao = runSeqDao;
	}
	public RunSeqDao getRunSeqDao() {
		return this.runSeqDao;
	}
	
	private void throwRunSeqExcepion(String msg) throws RunSeqException {
		throwRunSeqExcepion(msg, null);
	}
	private void throwRunSeqExcepion(String msg, Throwable e) throws RunSeqException {
		if(e == null) {
			this.logger.error(msg);
			throw new RunSeqException(msg);
		}
		this.logger.error(msg, e);
		throw new RunSeqException(msg, e);
	}

	public long getCurrRunNumber(final String name) throws RunSeqException {
		this.logger.debug(("method getCurrRunNumberr(String name)"));
		try {
			validateName(name);
			RunSeq runSeq = this.runSeqDao.getRunSeq(name);
			if(runSeq  == null) throwRunSeqExcepion("Sequence name " + name + " does NOT exist");
			return runSeq.getCurrentNumber().longValue();
		} catch(RunSeqException re) {
			throw re; 
		} catch(Exception e) {
			throwRunSeqExcepion("Could not get RunSeq", e);
		}
		return 0;
	}
	public long getNextRunNumber(final String name) throws RunSeqException {
		this.logger.debug(("method getNextRunNumber(String name)"));
		try {
			validateName(name);
			RunSeq runSeq = this.runSeqDao.getRunSeq(name);
			if(runSeq  == null) throwRunSeqExcepion("Sequence " + name + " does NOT exist", null);
			long currNumber = runSeq.getCurrentNumber().longValue();
			long endNumber = runSeq.getEndNumber().longValue();
			if(endNumber != DEFAULT_END_VALUE) {
				if((currNumber + 1) > endNumber) throwRunSeqExcepion("Max limit " + endNumber+ " reached for this sequence " + name);
			}
			runSeq.setCurrentNumber(++currNumber);
			this.runSeqDao.updateRunSeq(runSeq);
			return currNumber;
		} catch(RunSeqException re) {
			throw re; 
		} catch(Exception e) {
			throwRunSeqExcepion("Could not get RunSeq", e);
		}
		return 0;
	}

	private void validateName(String name) throws RunSeqException {
		String msg = "Sequence name CANNOT be null ";
		if(name  == null) throwRunSeqExcepion(msg, null);
		if(name.length()  == 0) throwRunSeqExcepion(msg, null);
		if (!Pattern.matches(SAFE_WORD, name)) throwRunSeqExcepion("Invalid characters in the name " + name);
	}
	/*public void createRunSequence(String name) throws RunSeqException, DuplicateRunSeqException {
		createRunSequence(name, null, null);
	}
	public void createRunSequence(String name, long startNumber) throws RunSeqException, DuplicateRunSeqException {
		createRunSequence(name, startNumber, null);
	}*/
	public void createRunSequence(String name, long startNumber, long endNumber) throws RunSeqException, DuplicateRunSeqException {
		this.logger.debug(("method createRunSequence(String name, long beginRunNumber, long endRunNumber)"));
		try {
			validateName(name);
			if(startNumber == 0) startNumber = DEFAULT_START_VALUE;
			if(endNumber == 0) endNumber = DEFAULT_END_VALUE;
			if(startNumber < 0) throwRunSeqExcepion("Run start number cannot be negative " + String.valueOf(startNumber));
			if(endNumber - startNumber < 0) throwRunSeqExcepion("Run end number is smaller than Run start number");
			RunSeq runSeq = new RunSeq();
			runSeq.setName(name);
			runSeq.setStartNumber(startNumber);
			runSeq.setEndNumber(endNumber);
			runSeq.setCurrentNumber(startNumber);
			this.runSeqDao.saveRunSeq(runSeq);
		} catch (DataIntegrityViolationException de) {
			String msg = "Could not create run sequence, duplicate sequence name " + name;
			this.logger.error(msg, de);
			throw new DuplicateRunSeqException(msg, de);
		} catch(RunSeqException re) {
			throw re; 
		} catch(Exception e) {
			throwRunSeqExcepion("Could not create RunSeq", e);
		}
	}


}
