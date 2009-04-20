package tom.dm.exception;

public class JobException extends Exception {
	static final long serialVersionUID = 4793741287460823525L;
	public JobException(String msg) {
		super(msg);
	}
 	public JobException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

