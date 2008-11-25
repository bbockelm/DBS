package gov.fnal.rss.dm.dao;

import gov.fnal.rss.dm.entity.RunSeq;

public interface RunSeqDao {
	public abstract RunSeq saveRunSeq(RunSeq runSeq);
	public abstract RunSeq getRunSeq(final String name);
	public abstract void updateRunSeq(RunSeq runSeq);
}
