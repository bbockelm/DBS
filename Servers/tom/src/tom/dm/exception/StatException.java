package tom.dm.exception;

public class StatException extends Exception {
	static final long serialVersionUID = 875412386412769355L;
	public StatException(String msg) {
		super(msg);
	}
 	public StatException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

