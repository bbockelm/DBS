package gov.fnal.nvs.dm.exception;

public class ProcessedException extends Exception {
	public ProcessedException(String msg) {
		super(msg);
	}
 	public ProcessedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

