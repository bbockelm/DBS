
/**
 $Revision: 1.134 $"
 $Id: MSSql.java,v 1.134 2007/12/12 22:31:09 sekhri Exp $"
 *
 */
package ms.sql;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Vector;
import java.util.ArrayList;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.Timestamp;
//import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ms.util.MSUtil;
import db.DBManagement;
import java.util.Date;



/**
 * This class is the SQL generator. All the methods in this class are static method. The main function of this class is to construct a <code>java.sql.PreparedStatement</code> with the proper query. All the methods returns the constructed <code>java.sql.PreparedStatement</code> after packing it with proper variables' values that are passed in as parameters to various method calls. All the methods can throw and <code>java.sql.SQLException</code> if the prepared statement fails to construct.
 */
public class MSSql {
	/**
	 * 
	 */
	public static String getDual() throws SQLException {
		return "SELECT 1 FROM dual";
	}

	public static PreparedStatement insertName(Connection conn, String tableName, String key, String value) throws SQLException {	
		Hashtable table = new Hashtable();
		table.put(key, value);
		return getInsertSQL(conn, tableName, table);
	}

	
	public static PreparedStatement getID(Connection conn, String table, String key, String value) throws SQLException {
		String sql = "SELECT DISTINCT ID \n " +
			"FROM " + table + "\n " +
			"WHERE " + key + " = ? \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, value);
                MSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}


	public static PreparedStatement addRequest(Connection conn, String srcUrlID, String dstUrlID, String path, String withParents, String withForce, String userID, String status, String progress, String detail, String notify) throws SQLException {
		Hashtable table = new Hashtable();
		table.put("SrcUrl", srcUrlID);
		table.put("DstUrl", dstUrlID);
		table.put("Path", path);
		table.put("WithParents", withParents);
		table.put("WithForce", withForce);
		table.put("CreatedBy", userID);
		table.put("Status", status);
		table.put("Progress", progress);
		table.put("Detail", detail);
		table.put("Notify", notify);
		return getInsertSQL(conn, "Request", table);
	}


	public static PreparedStatement updateRequest(Connection conn, String requestID, String status, String progress, String detail) throws SQLException {
		String sql = "UPDATE Request SET \n";
		boolean useComma = false;
		if(!MSUtil.isNull(status)) {
			if(useComma) sql += ",";
		       	sql += " Status = ?\n";
			useComma = true;
		}
		if(!MSUtil.isNull(progress)) {
			if(useComma) sql += ",";
		       	sql += " Progress = ?\n";
			useComma = true;
		}
		if(!MSUtil.isNull(detail)) {
			if(useComma) sql += ",";
		       	sql += " Detail = ?\n";
			useComma = true;
		}
		sql += "WHERE ID = ?\n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx=1;
		if(!MSUtil.isNull(status)) ps.setString(columnIndx++, status);
		if(!MSUtil.isNull(progress)) ps.setString(columnIndx++, progress);
		if(!MSUtil.isNull(detail)) ps.setString(columnIndx++, detail);
		ps.setString(columnIndx++, requestID);
		MSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;


	}
	
	public static PreparedStatement deleteRequest(Connection conn, String requestID) throws SQLException {
		String sql = "DELETE FROM Request \n" +
			"WHERE ID = ?\n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx=1;
		ps.setString(columnIndx++, requestID);
		MSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;


	}

	public static PreparedStatement listRequest(Connection conn, String srcUrl, String dstUrl, String path, String dn, String id, String status) throws SQLException {
		String sql = "SELECT r.ID as ID, \n" +
			"srcUrl.Url as SRC_URL, \n" +
			"dstUrl.Url as DST_URL, \n" +
			"r.Path as PATH, \n" +
			"r.WithParents as WITH_PARENTS, \n" +
			"r.WithForce as WITH_FORCE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"r.Status as STATUS, \n" +
			"r.Progress as PROGRESS, \n" +
			"r.Detail as DETAIL, \n" +
			"r.Notify as NOTIFY \n" +
			"FROM Request r \n" +
			"JOIN Person percb \n" +
				"ON percb.id = r.CreatedBy \n" +
			"JOIN DBSUrl srcUrl \n" +
				"ON srcUrl.id = r.SrcUrl \n" +
			"JOIN DBSUrl dstUrl \n" +
				"ON dstUrl.id = r.DstUrl \n" +
			"WHERE \n";
		boolean useAnd = false;
		if(!MSUtil.isNull(srcUrl)) {
			if(useAnd) sql += " AND ";
			sql += "srcUrl.Url = ? \n";
			useAnd = true;
		}
		if (!MSUtil.isNull(dstUrl)) {
			if(useAnd) sql += " AND ";
			sql += "dstUrl.Url = ? \n";
			useAnd = true;
		}
		if(!MSUtil.isNull(path)) {
			if(useAnd) sql += " AND ";
			sql += "r.Path = ? \n";
			useAnd = true;
		}
		if (!MSUtil.isNull(dn)) {
			if(useAnd) sql += " AND ";
			sql += "percb.DistinguishedName = ? \n";
			useAnd = true;
		}
		if (!MSUtil.isNull(id)) {
			if(useAnd) sql += " AND ";
			sql += "r.ID = ? \n";
			useAnd = true;
		}
		if (!MSUtil.isNull(status)) {
			if(useAnd) sql += " AND ";
			sql += "r.Status = ? \n";
			useAnd = true;
		}

		PreparedStatement ps = DBManagement.getStatement(conn, sql);
                int columnIndx=1;
		if(!MSUtil.isNull(srcUrl)) ps.setString(columnIndx++, srcUrl);
		if(!MSUtil.isNull(dstUrl)) ps.setString(columnIndx++, dstUrl);
		if(!MSUtil.isNull(path)) ps.setString(columnIndx++, path);
		if(!MSUtil.isNull(dn)) ps.setString(columnIndx++, dn);
		if(!MSUtil.isNull(id)) ps.setString(columnIndx++, id);
		if(!MSUtil.isNull(status)) ps.setString(columnIndx++, status);
		MSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;
	}


	private static PreparedStatement getInsertSQL (Connection conn, String tableName, Hashtable table) throws SQLException	{
		String sql = "INSERT INTO " + tableName + " ( \n";
		String sqlKeys = "  ";
		String sqlValues = "  ";
		Enumeration e = table.keys();
		while(e.hasMoreElements()) {
			String key = (String)e.nextElement();
			if(!MSUtil.isNull( MSUtil.get(table, key) )) {
				sqlKeys += "\t" + key + ",\n";
				sqlValues += "\t?,\n";
			}
		}
		sql += sqlKeys.substring(0, sqlKeys.length() - 2) + 
			"\n ) VALUES ( \n" + 
			sqlValues.substring(0, sqlValues.length() - 2) + 
			"\n)\n";
		
		//System.out.println("THE QUERY IS " +sql);
		
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		e = table.keys();
                int columnIndx = 1;
		while(e.hasMoreElements()) {
			String key = (String)e.nextElement();
			String value = MSUtil.get(table, key);
			if(!MSUtil.isNull(value)) ps.setString(columnIndx++, value);
		}
		MSUtil.writeLog("\n\n" + ps + "\n\n");
		return ps;

	}
	
	private static String getSQL(String key, String value) {
		if(!MSUtil.isNull(value)) {
			return key;
		}
		return "";

	}
	

}
