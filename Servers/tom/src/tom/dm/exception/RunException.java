package tom.dm.exception;

public class RunException extends Exception {
	static final long serialVersionUID = 981263560912769355L;
	public RunException(String msg) {
		super(msg);
	}
 	public RunException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

