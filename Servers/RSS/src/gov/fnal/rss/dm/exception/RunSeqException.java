package gov.fnal.rss.dm.exception;

public class RunSeqException extends Exception {
	static final long serialVersionUID = 476543432;
	public RunSeqException(String msg) {
		super(msg);
	}
 	public RunSeqException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

