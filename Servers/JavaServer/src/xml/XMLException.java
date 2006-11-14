/**
 * 
 */
package xml;

/**
 * @author sekhri
 *
 */
public class XMLException extends Exception{
	/**
	 * 
	 */
	private String code;
	private String detail;
	public XMLException(String message) {
		super(message);
	}
	public XMLException(String message, String code, String detail) {
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
