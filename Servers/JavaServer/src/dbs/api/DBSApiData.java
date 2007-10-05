/**
 $Revision: 1.2 $"
 $Id: DBSApiData.java,v 1.2 2007/03/12 17:19:20 afaq Exp $"
 *
 */

package dbs.api;
import java.util.Hashtable;
import java.util.Vector;

/**
* @author sekhri
*/
public class DBSApiData {
	//We can store the path id once and everytime the id is needed it can be fetched from this table instead of fetching it through database.
	public Hashtable globalUser = new Hashtable();
	public Hashtable globalFile = new Hashtable();
	public Hashtable globalPDPath = new Hashtable();
	public Vector dbOrderedList = new Vector();
	public String apiName = "";
	/**
	* Constructs a DBSApiData object. The constructor does notthing.
	*/
	public DBSApiData() {}


	
}
