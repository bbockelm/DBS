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
	private static Hashtable persons = new Hashtable();
	private static Boolean mutexProcDS = new Boolean(true);
	private static Hashtable procDS = new Hashtable();

	public static DBSDataCache getDBSDataCacheInstance(Connection conn) throws Exception {
		if( instance != null) return instance;
		synchronized(mutex) {
			if( instance != null ) return instance;
			instance = new DBSDataCache(conn);
		}
		return instance;
	}
	

	private DBSDataCache(Connection conn) throws Exception {
		System.out.println("\n\nLoading Person information in cache ......");
		refreshPersons(conn);
		System.out.println("\n\nLoading Processed DS information in cache ......");
		refreshProcDS(conn);
		
	}

	public String getUserID(Connection conn, String userDN) throws Exception {
		String id = get(persons, userDN);
		if (isNull(id)) { 
			refreshPersons(conn);
			System.out.println("CACHE-MIS getUserID " + userDN + " Reloading");
			return get(persons, userDN);
		} else 	System.out.println("CACHE-HIT getUserID " + userDN);
		return id;
	}

	private void refreshPersons(Connection conn) throws Exception {
		synchronized(mutexPerson) {
			persons = new Hashtable();
			PreparedStatement ps = null;
			ResultSet rs =  null;
			try {
				ps = DBSSql.listPersons(conn);
				rs =  ps.executeQuery();
				while(rs.next()) {
					System.out.println("DN " + get(rs, "DN") + "  ID " + get(rs, "ID"));
					persons.put(get(rs, "DN") ,  get(rs, "ID"));
				}
			} finally { 
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			}
		}
	}
	
	public String getProcessedDSID(Connection conn, String path) throws Exception {
		String[] data = path.split("/");
		String pathToQuery = "/" + data[1] + "/" + data[2];
		String id = get(procDS, pathToQuery);
		if (isNull(id)) { 
			refreshProcDS(conn);
			System.out.println("CACHE-MIS getProcessedDSID " + pathToQuery + " Reloading");
			return get(procDS, pathToQuery);
		} else 	System.out.println("CACHE-HIT getProcessedDSID " + pathToQuery);
		return id;

	}

	private void refreshProcDS(Connection conn) throws Exception {
		synchronized(mutexProcDS) {
			procDS = new Hashtable();
			PreparedStatement ps = null;
			ResultSet rs =  null;
			try {
				//ps = DBSSql.getProcessedDSID(conn, "", "");
				ps = DBSSql.listProcessedDatasets(conn);
				rs =  ps.executeQuery();
				while(rs.next()) {
					System.out.println("Path /" + get(rs, "PRIMARY_DATATSET_NAME") + "/" +  get(rs, "PROCESSED_DATATSET_NAME") + "  ID " + get(rs, "ID"));
					procDS.put("/" + get(rs, "PRIMARY_DATATSET_NAME") + "/" +  get(rs, "PROCESSED_DATATSET_NAME") ,  get(rs, "ID"));
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
