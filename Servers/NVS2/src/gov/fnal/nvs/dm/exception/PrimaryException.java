package gov.fnal.nvs.dm.exception;

public class PrimaryException extends Exception {
	public PrimaryException(String msg) {
		super(msg);
	}
 	public PrimaryException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

