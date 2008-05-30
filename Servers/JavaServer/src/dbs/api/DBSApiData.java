/**
 $Revision: 1.7 $"
 $Id: DBSApiData.java,v 1.7 2007/11/16 22:20:35 sekhri Exp $"
 *
 */

package dbs.api;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Stack;
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
	public Hashtable localFileStatus = new Hashtable();
	public Hashtable localFileValStatus = new Hashtable();
	public Hashtable localFileType = new Hashtable();
	public Hashtable localPDPath = new Hashtable();
	public Hashtable person = new Hashtable();
	public Vector dbOrderedList = new Vector();
	public Stack qStack = new Stack();
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
