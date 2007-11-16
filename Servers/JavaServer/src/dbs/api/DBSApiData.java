/**
 $Revision: 1.5 $"
 $Id: DBSApiData.java,v 1.5 2007/11/15 21:02:27 sekhri Exp $"
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
	private DBSDataCache globalCache ;
	public Hashtable localUser = new Hashtable();
	public Hashtable localTier = new Hashtable();
	public Hashtable localFile = new Hashtable();
	public Hashtable localPDPath = new Hashtable();
	public Hashtable person = new Hashtable();
	public Vector dbOrderedList = new Vector();
	public String apiName = "";
	public String instanceName = "";
	/**
	* Constructs a DBSApiData object. The constructor does notthing.
	*/
	public DBSDataCache getGlobalCache() {
		return globalCache;
	}
	public void setGlobalCache(DBSDataCache globalCache) {
		this.globalCache = globalCache;
	}
	public DBSApiData() {}


	
}
