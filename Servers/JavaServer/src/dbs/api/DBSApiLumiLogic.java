/**
 $Revision: 1.2 $"
 $Id: DBSApiLumiLogic.java,v 1.2 2008/05/30 16:40:04 sekhri Exp $"
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
import java.sql.SQLException;
import dbs.DBSConstants;

/**
* A class that has the core business logic of all the Primary dataset APIs.  The signature for the API is internal to DBS and is not exposed to the clients. There is another class <code>dbs.api.DBSApi</code> that has an interface for the clients. All these low level APIs are invoked from <code>dbs.api.DBSApi</code>. This class inherits from DBSApiLogic class.
* @author sekhri
*/
public class DBSApiLumiLogic extends DBSApiLogic {
		
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs.
	*/

	DBSApiPersonLogic personApi = null;
	DBSApiData data = null;
	public DBSApiLumiLogic(DBSApiData data) {
		super(data);
		this.data = data;
		personApi = new DBSApiPersonLogic(data);
	}

	
	public void getIntegratedLuminosity(Connection conn, Writer out, String path, String run, String runRange, String tag) throws Exception {
                String procDSID =  (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true);
		if(isNull(tag))	tag = "current";

		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			
			ps = DBSSql.getIntegratedLuminosity(conn, procDSID, "", run, runRange, tag);
			if (DBSConstants.DEBUG) pushQuery(ps);
			rs =  ps.executeQuery();
			Double sumInstantLumi = 0.0;
			Double sumInstantLumiErr = 0.0;
			while(rs.next()) {
				 Double instantLumi = Double.parseDouble(get(rs, "INSTANT_LUMI"));
				 Double instantLumiErr = Double.parseDouble(get(rs, "INSTANT_LUMI_ERR"));
				 Double deadTime = Double.parseDouble(get(rs, "DEADTIME_NORMALIZATION"));
				 Double normal = Double.parseDouble(get(rs, "NORMALIZATION"));
				 sumInstantLumi += instantLumi * 93 * (1 - deadTime) * normal;
				 sumInstantLumiErr += instantLumiErr * instantLumiErr;
				 System.out.println("instantLumi " + instantLumi);
			}
			sumInstantLumiErr += Math.sqrt(sumInstantLumiErr);
			out.write(((String) "<lumi integrated_luminosity='" + sumInstantLumi + "' error='" + sumInstantLumiErr + "'/>"));
		} finally { 
			if (rs != null) rs.close();
			if (ps != null) ps.close(); 
		}
	}

	
}
