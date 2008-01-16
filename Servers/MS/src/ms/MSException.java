/**
 *
 */
package ms;

/**
* A thin wrapper around <code>java.lang.Exception</code> that allows to store exception information divided among three different variables. This type of exception is raised when any business rule for the API fails.<br> 
* 1) Exception message contains the brief message of the type of exception. <br>
* 2) Exception code contains the numeric value of the exception type. <br>
* 3) Exception detail contains the verbose information of the exception. <br>
* @author sekhri
*/
public class MSException extends Exception{
	private String code;
	private String detail;

	/**
	* Constructs a MSException object similar to its parent Exception class
	* @param message The exception message in string format to be stored in this MSException class
	*/
	public MSException(String message) {
		super(message);
	}

	/**
	* Constructs a MSException object 
	* @param message The exception berief message in string format to be stored in this MSException class
	* @param code The exception general integer  code in string format to be stored in this MSException class
	* @param detail The exception verbose detail in string format to be stored in this MSException class
	*/
	public MSException(String message, String code, String detail) {
		super(message);
		this.code = code;
		this.detail = detail;
	}

	/**
	* Returns the error integer code of this throwable object.
	* @return 
	* the error integer code of this <code>Throwable</code> object if it was created with an error code; or null if it was created with no error code.
	*/
	public String getCode() {
		return code;
	}

	/**
	* Returns the error verbose detail of this throwable object.
	* @return 
	* the error verbose detail of this <code>Throwable</code> object if it was created with an error detail; or null if it was created with no error detail.
	*/
	public String getDetail() {
		return detail;
	}

}
