package tom.dm.exception;

public class FileException extends Exception {
	static final long serialVersionUID = 1287348799479378225L;
	public FileException(String msg) {
		super(msg);
	}
 	public FileException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

