/**
 $Revision: 1.4 $"
 $Id: DBSApiData.java,v 1.4 2007/10/10 22:04:18 afaq Exp $"
 *
 */

package dbs.api;
import java.util.Hashtable;
import java.util.Vector;
import dbs.data.DBSDataCache;

/**
* @author sekhri
*/
public class DBSApiData {
	//We can store the path id once and everytime the id is needed it can be fetched from this table instead of fetching it through database.
	private DBSDataCache cache ;
	public Hashtable globalUser = new Hashtable();
	public Hashtable globalFile = new Hashtable();
	public Hashtable globalPDPath = new Hashtable();
	public Hashtable person = new Hashtable();
	public Vector dbOrderedList = new Vector();
	public String apiName = "";
	public String instanceName = "";
	/**
	* Constructs a DBSApiData object. The constructor does notthing.
	*/
	public DBSDataCache getCache() {
		return cache;
	}
	public void setCache(DBSDataCache cache) {
		this.cache = cache;
	}
	public DBSApiData() {}


	
}
