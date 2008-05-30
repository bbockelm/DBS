/**
 *
 */

package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.util.Hashtable;
import dbs.sql.DBSSql;
import dbs.util.DBSUtil;
import dbs.DBSException;

/**
* A class that has the core business logic of all the RecycleBin APIs.  
* @author sekhri
*/
public class DBSApiRecycleBin extends DBSApiLogic {
		
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs.
	*/

	DBSApiData data = null;
	public DBSApiRecycleBin(DBSApiData data) {
		super(data);
		this.data = data;
	}

	public void insertRecycleBin(Connection conn, Writer out, String path, String blockName, String xml, String lmbUserID, String cbUserID, String creationDate) throws Exception {
		if( !isNull(getMapIDNoCheck(conn, "RecycleBin", "Path", "BlockName", path, blockName, false))) 
			throw new DBSException("Already Exists", "1020", "RecycleBin with Path " + path + " and block " + blockName + " Already Exists. Remove this entry first");

		PreparedStatement ps = null;
		try {
			ps = DBSSql.insertRecycleBin(conn, 
				path,
				blockName,
				xml,
				//Base64.encodeBytes(xml.getBytes()),
				cbUserID,
				cbUserID,
				creationDate);
			pushQuery(ps);
			ps.execute();
                } finally {
			if (ps != null) ps.close();
		}
	}

	public void deleteRecycleBin(Connection conn, Writer out, String path, String blockName) throws Exception {
		//NOTE no checling if this entry already exists because it is called when the entry actaully exists
		PreparedStatement ps = null;
		try {
			ps = DBSSql.deleteMap(conn, "RecycleBin", "Path", "BlockName", path, blockName);
			pushQuery(ps);
			ps.execute();
                } finally {
			if (ps != null) ps.close();
		}
	}

	
	
}
