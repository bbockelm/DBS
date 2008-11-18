package gov.fnal.nvs.dm.exception;

public class TierException extends Exception {
	public TierException(String msg) {
		super(msg);
	}
 	public TierException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

