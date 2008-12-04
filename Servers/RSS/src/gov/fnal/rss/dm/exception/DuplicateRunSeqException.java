package gov.fnal.rss.dm.exception;

public class DuplicateRunSeqException extends RunSeqException {
	static final long serialVersionUID = 426543433;
	public DuplicateRunSeqException(String msg) {
		super(msg);
	}
 	public DuplicateRunSeqException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

