/**
 * 
 $Revision: 1.3 $"
 $Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"


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
