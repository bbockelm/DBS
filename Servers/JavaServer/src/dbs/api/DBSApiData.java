/**
 $Revision: 1.63 $"
 $Id: DBSApiLogic.java,v 1.63 2007/01/11 21:16:43 sekhri Exp $"
 *
 */

package dbs.api;
import java.util.Hashtable;

/**
* @author sekhri
*/
public class DBSApiData {
	//We can store the path id once and everytime the id is needed it can be fetched from this table instead of fetching it through database.
	public Hashtable globalUser = new Hashtable();
	public Hashtable globalFile = new Hashtable();
	public Hashtable globalPDPath = new Hashtable();
		
	/**
	* Constructs a DBSApiData object. The constructor does notthing.
	*/
	public DBSApiData() {}


	
}
