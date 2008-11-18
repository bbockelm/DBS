package gov.fnal.nvs.dm.exception;

public class ValidateException extends Exception {
	public ValidateException(String msg) {
		super(msg);
	}
 	public ValidateException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

