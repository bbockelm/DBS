package gov.fnal.rss.dm.exception;

public class DuplicateRunSeqException extends RunSeqException {
	public DuplicateRunSeqException(String msg) {
		super(msg);
	}
 	public DuplicateRunSeqException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

