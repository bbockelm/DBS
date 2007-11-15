/**
 * @author sekhri
 */


package dbs.data;
import java.util.Hashtable;
import java.util.Vector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import db.DBManagement;
import dbs.util.DBSConfig;
import dbs.util.DBSUtil;
import dbs.sql.DBSSql;

public class DBSDataCache {
	private static DBSDataCache instance = null;
	private static Boolean mutex = new Boolean(true);
	private static Boolean mutexPerson = new Boolean(true);
	private static Hashtable person = new Hashtable();
	
	public static DBSDataCache getDBSDataCacheInstance(Connection conn) throws Exception {
		if( instance != null) return instance;
		synchronized(mutex) {
			if( instance != null ) return instance;
			instance = new DBSDataCache(conn);
		}
		return instance;
	}
	

	private DBSDataCache(Connection conn) throws Exception {
		refreshPersons(conn);
	}

	private void refreshPersons(Connection conn) throws Exception {
		synchronized(mutexPerson) {
			person = new Hashtable();
			PreparedStatement ps = null;
			ResultSet rs =  null;
			try {
				ps = DBSSql.listPersons(conn);
				rs =  ps.executeQuery();
				while(rs.next()) {
					System.out.println("DN " + get(rs, "DN") + "  ID " + get(rs, "ID"));
					person.put(get(rs, "DN") ,  get(rs, "ID"));
				}
			} finally { 
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			}
		}
	}
	
	private String get(ResultSet rs, String key) throws Exception {
		return DBSUtil.get(rs, key);
	}

	private String get(Hashtable table, String key) throws Exception {
		return DBSUtil.get(table, key);
	}
		
	private boolean isNull(String pattern) {
		return DBSUtil.isNull(pattern);
	}
	
	public String getUserID(Connection conn, String userDN) throws Exception {
		String id = get(person, userDN);
		if (isNull(id)) { 
			refreshPersons(conn);
			return get(person, userDN);
		}
		return id;
	}

		

	public static void main (String args[]) {
		try {
			DBSConfig config = DBSConfig.getInstance();
			Connection conn = DBManagement.getConnection( config.getDbDriver(),
					config.getDbURL(), 
					config.getDbUserName(),
					config.getDbUserPasswd());
					
			DBSDataCache data = DBSDataCache.getDBSDataCacheInstance(conn);
			System.out.println( "id for ANZARDN " + data.getUserID(conn, "ANZARDN"));
			System.out.println( "again id for ANZARDN " + data.getUserID(conn, "ANZARDN"));
			System.out.println( "again id for ANZARDN " + data.getUserID(conn, "ANZARDN"));
			System.out.println( "new id for ANZARDNaaa " + data.getUserID(conn, "ANZARDNaaa"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
