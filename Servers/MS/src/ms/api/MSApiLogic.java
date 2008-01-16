/**
 $Revision: 1.110 $"
 $Id: MSApiLogic.java,v 1.110 2007/11/16 22:20:35 sekhri Exp $"
 *
 */

package ms.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.util.regex.Pattern;
import java.util.Hashtable;
import java.sql.ResultSetMetaData;
import ms.sql.MSSql;
import ms.util.MSUtil;
import ms.MSException;
import ms.MSConstants;
import java.util.Arrays;
import java.util.Vector;



public class MSApiLogic {
	//private static String SAFE_STR = "[-\\w_\\.%/%:]+";
	private static String SAFE_STR = "[-\\w_\\.%/%:]+";
	//private static String SAFE_PTH = "[-\\w_\\.%/]+";
		
	/**
	* Constructs a MSApiLogic object that can be used to invoke several APIs. The constructor does notthing.
	*/
	public MSApiLogic() {
	}

	private String addPerson(Connection conn, Writer out, String dn) throws Exception {
		return insertName(conn, out, "Person", "DistinguishedName", dn);
	}
	
	private String addUrl(Connection conn, Writer out, String url) throws Exception {
		return insertName(conn, out, "DBSUrl", "Url", url);
	}

	private String getRequestID(Connection conn, Writer out, String srcUrl, String dstUrl, String path, String dn, String idIn) throws Exception {
		String id = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps =  MSSql.listRequest(conn, srcUrl, dstUrl, path, dn, idIn, "");
			rs =  ps.executeQuery();
			if(rs.next()) id = get(rs, "ID");
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}
		return  id;

	}
	
	public void getRequestByID(Connection conn, Writer out, String id) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps =  MSSql.listRequest(conn, "", "", "", "", id, "");
			rs =  ps.executeQuery();
			if(rs.next()) 
				out.write(((String) "<request id='" + id + 
							"'\n src_url='" + get(rs, "SRC_URL") +
							"'\n dst_url='" + get(rs, "DST_URL") +
							"'\n path='" + get(rs, "PATH") +
							"'\n dn='" + get(rs, "CREATED_BY") +
							"'\n with_parents='" + get(rs, "WITH_PARENTS") +
							"'\n with_force='" + get(rs, "WITH_FORCE") +
							"'\n notify='" + get(rs, "NOTIFY") +
							"'\n detail='" + get(rs, "DETAIL") +
							"'\n progress='" + get(rs, "PROGRESS") +
							"'\n status='" + get(rs, "STATUS") +
							"'\n />"));
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}
	}
	public void getRequestByUser(Connection conn, Writer out, String dn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps =  MSSql.listRequest(conn, "", "", "", dn, "", "");
			rs =  ps.executeQuery();
			while(rs.next()) 
				out.write(((String) "<request id='" + get(rs, "ID") + 
							"'\n src_url='" + get(rs, "SRC_URL") +
							"'\n dst_url='" + get(rs, "DST_URL") +
							"'\n path='" + get(rs, "PATH") +
							"'\n dn='" + dn +
							"'\n with_parents='" + get(rs, "WITH_PARENTS") +
							"'\n with_force='" + get(rs, "WITH_FORCE") +
							"'\n notify='" + get(rs, "NOTIFY") +
							"'\n detail='" + get(rs, "DETAIL") +
							"'\n progress='" + get(rs, "PROGRESS") +
							"'\n status='" + get(rs, "STATUS") +
							"'\n />"));
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}

	}

	public String addRequest(Connection conn, Writer out, String srcUrl, String dstUrl, String path, String dn, boolean withParents, boolean withForce, String notify) throws Exception {
		String userID = addPerson(conn, out, dn);
		String srcUrlID = addUrl(conn, out, srcUrl);
		String dstUrlID = addUrl(conn, out, dstUrl);
		String requestID = "";
		
		String parentStr = "y";
		String forceStr = "y";
		if (!withParents) parentStr = "n";
		if (!withForce) forceStr = "n";
		if(!isNull(requestID = getRequestID(conn, out, srcUrl, dstUrl, path, "", ""))) 
			 throw new MSException("Already Exists", "1001", "A request to migrate with requestID " + requestID + " already Exists" );

		PreparedStatement ps = null;
		try {
			ps = MSSql.addRequest(conn, 
				srcUrlID,
				dstUrlID,
				path,
				parentStr,
				forceStr,
				userID,
				"Queued",
				"0",
				"Waiting to be picked up",
				notify);
			ps.execute();
		} finally { 
			if (ps != null) ps.close();
       	        }
		requestID = getRequestID(conn, out, srcUrl, dstUrl, path, "", "");
		out.write(((String) "<request id='" + requestID + "'/>"));
		return requestID;
	}

	public void updateRequest(Connection conn, Writer out, String srcUrl, String dstUrl, String path, String status, String progress, String detail) throws Exception {	

		/*System.out.println("srcUrl " + srcUrl);
		System.out.println("dstUrl " + dstUrl);
		System.out.println("path " + path);
		System.out.println("status " + status);
		System.out.println("progress " + progress);
		System.out.println("detail " + detail);*/
		String requestID = "";
		if(!isNull(requestID = getRequestID(conn, out, srcUrl, dstUrl, path, "", ""))) {
			PreparedStatement ps = null;
			try {
				ps = MSSql.updateRequest(conn,  
						requestID,
						status, 
						progress,
						detail
						);
				ps.execute();
			} finally {
				if (ps != null) ps.close();
			}
		}
	}

	public void deleteRequest(Connection conn, Writer out, String srcUrl, String dstUrl, String path, String dn, String id) throws Exception {	

		if( (isNull(srcUrl) || isNull(dstUrl) || isNull(path) ) && isNull(dn) && isNull(id)) 
			 throw new MSException("Invalid input", "1005", "Either provide [src_url AND dst_url AND path] OR [request_id]");
		String requestID = "";
		if(!isNull(requestID = getRequestID(conn, out, srcUrl, dstUrl, path, dn, id))) {
			PreparedStatement ps = null;
			try {
				ps = MSSql.deleteRequest(conn, requestID);
				ps.execute();
			} finally {
				if (ps != null) ps.close();
			}
		}
	}


	
	private String insertName(Connection conn, Writer out, String table, String key, String value) throws Exception {
		String id = "";
		if(isNull( (id = getID(conn, table, key, value, false)))) {
			PreparedStatement ps = null;
			try {
				ps = MSSql.insertName(conn, table, key, value);
				ps.execute();
			} finally {
				if (ps != null) ps.close();
			}
			return getID(conn, table, key, value, true);
		} 
		return id;
	}

	

	/**
	 * Gets a id of a table from the given database table using the key value pair specified in the parameters. This method can be called to fetch the id of any table that has just one unique key. The sql is generated by calling a generic private <code>ns.sql.MSSql.getID</code> method. 
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param tableName the name of the table in the database whose id needs to fetched.
	 * @param key the name of the only unique coloumn in the given table whose id needs to fetched.
	 * @param value the value of the only unique coloumn in the given table whose id needs to fetched.
	 * @param excep a boolean flag that determines if the exception needs to be raised if the block is not found.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid or  the database connection is unavailable, or the table id is not found.
	 */
	protected String getID(Connection conn, String tableName, String key, String value, boolean excep) throws Exception {
		if (excep) checkStr(value, key);
		else if(!isNull(value)) checkStr(value, key);

		if(isNull(tableName) || isNull(key) || isNull(value)) {
			if(excep) throw new MSException("Unavailable data", "1011", "No such " + 
					tableName + " : " + key + " : " + value );
   			return null;
		} 

		return getIDNoCheck(conn, tableName, key, value, excep);
	}


	protected String getIDNoCheck(Connection conn, String tableName, String key, String value, boolean excep) throws Exception {
		if(isNull(tableName) || isNull(key) || isNull(value)) {
			if(excep) throw new MSException("Unavailable data", "1011", "No such " + 
					tableName + " : " + key + " : " + value );
   			return null;
		} 
		String id = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps =  MSSql.getID(conn, tableName, key, value);
			rs =  ps.executeQuery();
			if(!rs.next()) {
				if(excep) throw new MSException("Unavailable data", "1011", "No such " + tableName + " : " + key + " : " + value );
				else return null;
			}
			id = get(rs, "ID");
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}
		return  id;
	}


	/**
	 * Checks a sentence  against a regular expression that validates a english sentence without any special characters.
	 * @param pattern the value of the word that needs to be validated.
	 * @param key the name of the key which is used to throw an exception in case the word fails to validate. This make the exception message more intutive as it states which key was being checked.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid.
	 */
        protected void checkStr(String pattern, String key) throws Exception {
		//System.out.println("pattern is " + pattern);
                if(isNull(pattern))
                        throw new MSException("Missing data", "1006", "Null Fields. Expected a valid " + key);
                if (! Pattern.matches(SAFE_STR, pattern))
                        throw new MSException("Invalid format", "1017", "Invalid Characters in " + pattern + " for " + key + " Expected a valid " + key + " which should satisfy the regular expression " + SAFE_STR);
        }

	
	protected boolean isNull(String pattern) {
		return MSUtil.isNull(pattern);
	}
	
	protected String get(Hashtable table, String key, boolean excep) throws Exception{
		String value = MSUtil.get(table, key);
		if(excep) checkStr(value, key);
		else if(! isNull(value)) checkStr(value, key);
		return value;
	}

        protected String getStr(Hashtable table, String key, boolean excep) throws Exception{
                String value = MSUtil.get(table, key);
                if(excep) checkStr(value, key);
                else if(! isNull(value)) checkStr(value, key);
                return value;
        }

	
	protected String get(Hashtable table, String key) {
		return MSUtil.get(table, key);
	}

	protected String get(ResultSet rs, String key) throws Exception {
		String value = rs.getString(key);
		if(isNull(value)) return "";
		return value;
	}
	
}
