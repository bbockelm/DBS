/**
 * 
 */
package dbs;

/**
 * @author sekhri
 *
 */
public class DBSException extends Exception{
	/**
	 * 
	 */
	private String code;
	private String detail;
	public DBSException(String message) {
		super(message);
	}
	public DBSException(String message, String code, String detail) {
		super(message);
		this.code = code;
		this.detail = detail;
	}
	public String getCode() {
		return code;
	}
	public String getDetail() {
		return detail;
	}

}
