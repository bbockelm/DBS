package gov.fnal.rss.dm.service;

import gov.fnal.rss.dm.exception.RunSeqException;
import gov.fnal.rss.dm.exception.DuplicateRunSeqException;
import gov.fnal.rss.dm.entity.RunSeq;

public interface RunSeqService {
	public long getNextRunNumber(final String name) throws RunSeqException;
	public long getCurrRunNumber(final String name) throws RunSeqException;
	//public void createRunSequence(String name) throws RunSeqException, DuplicateRunSeqException;
	//public void createRunSequence(String name, long beginRunNumber) throws RunSeqException, DuplicateRunSeqException;
	public void createRunSequence(String name, long startNumber, long endNumber) throws RunSeqException, DuplicateRunSeqException;

}
