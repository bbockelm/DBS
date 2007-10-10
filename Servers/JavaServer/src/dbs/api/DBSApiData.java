/**
 $Revision: 1.3 $"
 $Id: DBSApiData.java,v 1.3 2007/10/05 16:57:43 sekhri Exp $"
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
	public String instanceName = "";
	/**
	* Constructs a DBSApiData object. The constructor does notthing.
	*/
	public DBSApiData() {}


	
}
