/**
 * @author sekhri
 */


package dbs.data;
import java.util.Hashtable;
import java.util.Vector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

import db.DBManagement;
import dbs.DBSConstants;
import dbs.util.DBSConfig;
import dbs.util.DBSUtil;
import dbs.sql.DBSSql;

public class DBSDataCache {
	private ReadWriteLock globalPersonLock = new ReentrantReadWriteLock();
	private Lock readPersonLock = globalPersonLock.readLock();
	private Lock writePersonLock = globalPersonLock.writeLock();
	private static Hashtable persons = new Hashtable();

	private ReadWriteLock globalProcDSLock = new ReentrantReadWriteLock();
	private Lock readProcDSLock = globalProcDSLock.readLock();
	private Lock writeProcDSLock = globalProcDSLock.writeLock();
	private static Hashtable procDSs = new Hashtable();

	private ReadWriteLock globalAlgoLock = new ReentrantReadWriteLock();
	private Lock readAlgoLock = globalAlgoLock.readLock();
	private Lock writeAlgoLock = globalAlgoLock.writeLock();
	private static Hashtable algos = new Hashtable();

	private ReadWriteLock globalTierLock = new ReentrantReadWriteLock();
	private Lock readTierLock = globalTierLock.readLock();
	private Lock writeTierLock = globalTierLock.writeLock();
	private static Hashtable tiers = new Hashtable();

	private ReadWriteLock globalFileStatusLock = new ReentrantReadWriteLock();
	private Lock readFileStatusLock = globalFileStatusLock.readLock();
	private Lock writeFileStatusLock = globalFileStatusLock.writeLock();
	private static Hashtable fileStatus = new Hashtable();

	private ReadWriteLock globalFileTypeLock = new ReentrantReadWriteLock();
	private Lock readFileTypeLock = globalFileTypeLock.readLock();
	private Lock writeFileTypeLock = globalFileTypeLock.writeLock();
	private static Hashtable fileTypes = new Hashtable();

	private ReadWriteLock globalFileValStatusLock = new ReentrantReadWriteLock();
	private Lock readFileValStatusLock = globalFileValStatusLock.readLock();
	private Lock writeFileValStatusLock = globalFileValStatusLock.writeLock();
	private static Hashtable fileValStatus = new Hashtable();

	
	//private static ReadWriteLock globalInstanceLock = new ReentrantReadWriteLock();
	//private static Lock readInstanceLock = globalInstanceLock.readLock();
	//private static Lock writeInstanceLock = globalInstanceLock.writeLock();

	
	private static DBSDataCache instance = null;
	private static Boolean mutex = new Boolean(true);
	/*private static Boolean mutexPerson = new Boolean(true);
	private static Boolean mutexProcDS = new Boolean(true);
	private static Boolean mutexAlgo = new Boolean(true);
	private static Boolean mutexTier = new Boolean(true);*/
	
	/*public static DBSDataCache getDBSDataCacheInstance(Connection conn) throws Exception {
		readInstanceLock.lock(); 
		try {
			if( instance != null) return instance;
		} finally { 
			readInstanceLock.unlock();
		}

		writeInstanceLock.lock();
		try {
			instance = new DBSDataCache(conn);
		} finally { 
			writeInstanceLock.unlock();
		}

		return instance;
	}*/

	public static DBSDataCache getDBSDataCacheInstance(Connection conn) throws Exception {
		if( instance != null) return instance;
		synchronized(mutex) {
			if( instance != null ) return instance;
			instance = new DBSDataCache(conn);
		}
		return instance;
	}
	

	private DBSDataCache(Connection conn) throws Exception {
		if (DBSConstants.USECACHE) {
			System.out.print("\nLoading Person information in cache ...... ");
			refreshPersons(conn);
			System.out.println("Done");
			System.out.print("\nLoading Processed DS information in cache ...... ");
			refreshProcDSs(conn);
			System.out.println("Done");
			System.out.print("\nLoading Algorithm information in cache ...... ");
			refreshAlgos(conn);
			System.out.println("Done");
			System.out.print("\nLoading Tier information in cache ...... ");
			refreshTiers(conn);
			System.out.println("Done");
			System.out.print("\nLoading File Status information in cache ...... ");
			refreshFileStatus(conn);
			System.out.println("Done");
			System.out.print("\nLoading File Type information in cache ...... ");
			refreshFileTypes(conn);
			System.out.println("Done");
			System.out.print("\nLoading File ValStatus information in cache ...... ");
			refreshFileValStatus(conn);
			System.out.println("Done\n");
		}

	}

	//----------------------------------------------------------------------------------------------------------------------------
	// Person cache
	private String readUserIDSycnronized(Connection conn, String userDN) throws Exception {
		String id = "";
		readPersonLock.lock(); 
		try { 
			id = get(persons, userDN); 
		} finally { 
			readPersonLock.unlock();
		}
		return id;
	}
	
	public String getUserID(Connection conn, String userDN) throws Exception {
		if (!DBSConstants.USECACHE) {System.out.println("CACHE-DISABLED"); return "";}
		String id = readUserIDSycnronized(conn, userDN);
		if (isNull(id)) { 
			refreshPersons(conn);
			System.out.println("CACHE-MIS getUserID " + userDN + " .. Reloading ..");
			return readUserIDSycnronized(conn, userDN);
		} else 	System.out.println("CACHE-HIT getUserID " + userDN);
		return id;
	}

	private void refreshPersons(Connection conn) throws Exception {
		writePersonLock.lock();
		try {
			persons = new Hashtable();
			PreparedStatement ps = null;
			ResultSet rs =  null;
			try {
				ps = DBSSql.listPersons(conn);
				rs =  ps.executeQuery();
				while(rs.next()) persons.put(get(rs, "DN") ,  get(rs, "ID"));
				//System.out.println("DN " + get(rs, "DN") + "  ID " + get(rs, "ID"));
			} finally { 
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			}
		} finally { 
			writePersonLock.unlock();
		}
	}
	

	//----------------------------------------------------------------------------------------------------------------------------
	// Processed dataset cache

	public String readProcessedDSIDSycnronized(Connection conn, String pathToQuery) throws Exception {
		String id = "";
		readProcDSLock.lock();
		try { 
			id =  get(procDSs, pathToQuery); 
		} finally { 
			readProcDSLock.unlock();
		}
		return id;
	}


	public String getProcessedDSID(Connection conn, String path) throws Exception {
		if (!DBSConstants.USECACHE) {System.out.println("CACHE-DISABLED"); return "";}
		String[] data = path.split("/");
		String pathToQuery = "/" + data[1] + "/" + data[2];
		String id = readProcessedDSIDSycnronized(conn, pathToQuery);
		if (isNull(id)) { 
			System.out.println("CACHE-MIS getProcessedDSID " + pathToQuery + " .. Reloading ..");
			refreshProcDSs(conn);
			return readProcessedDSIDSycnronized(conn, pathToQuery);
		} else 	System.out.println("CACHE-HIT getProcessedDSID " + pathToQuery);
		return id;

	}

	private void refreshProcDSs(Connection conn) throws Exception {
		writeProcDSLock.lock();
		try {
			procDSs = new Hashtable();
			PreparedStatement ps = null;
			ResultSet rs =  null;
			try {
				ps = DBSSql.listProcessedDatasets(conn);
				rs =  ps.executeQuery();
				while(rs.next()) procDSs.put("/" + get(rs, "PRIMARY_DATATSET_NAME") + "/" +  get(rs, "PROCESSED_DATATSET_NAME") ,  get(rs, "ID"));
				//System.out.println("Path /" + get(rs, "PRIMARY_DATATSET_NAME") + "/" +  get(rs, "PROCESSED_DATATSET_NAME") + "  ID " + get(rs, "ID"));
			} finally { 
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			}
		} finally { 
			writeProcDSLock.unlock();
		}
	}

	
	//----------------------------------------------------------------------------------------------------------------------------
	// Algorithm cache
	
	public String readAlgorithmIDSycnronized(Connection conn, String algoToQuery) throws Exception {
		String id = "";
		readAlgoLock.lock(); 
		try { 
			id = get(algos, algoToQuery); 
		} finally { 
			readAlgoLock.unlock();
		}
		return id;
	}
	
	public String getAlgorithmID(Connection conn, String ver, String fam, String exe, String psHash) throws Exception {
		if (!DBSConstants.USECACHE) {System.out.println("CACHE-DISABLED"); return "";}
		String algoToQuery = "/" + ver + "/" + fam + "/" + exe + "/" + psHash;
		String id = readAlgorithmIDSycnronized(conn, algoToQuery);
		if (isNull(id)) { 
			System.out.println("CACHE-MIS getAlgorithmID " + algoToQuery + " .. Reloading ..");
			refreshAlgos(conn);
			return readAlgorithmIDSycnronized(conn, algoToQuery);
		} else 	System.out.println("CACHE-HIT getAlgorithmID " + algoToQuery);
		return id;

	}


	private void refreshAlgos(Connection conn) throws Exception {
		writeAlgoLock.lock();
		try {
			algos = new Hashtable();
			PreparedStatement ps = null;
			ResultSet rs =  null;
			try {
				ps = DBSSql.listAlgorithms(conn);
				rs =  ps.executeQuery();
				while(rs.next()) algos.put("/" + get(rs, "APP_VERSION") + "/" +  get(rs, "APP_FAMILY_NAME") + "/" +  get(rs, "APP_EXECUTABLE_NAME") + "/" + get(rs, "PS_HASH") ,  get(rs, "ID"));
				//System.out.println("Algorithm /" +  get(rs, "APP_VERSION") + "/" +  get(rs, "APP_FAMILY_NAME") + "/" +  get(rs, "APP_EXECUTABLE_NAME") + "/" + get(rs, "PS_HASH") + "  ID " + get(rs, "ID"));
			} finally { 
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			}
		} finally { 
			writeAlgoLock.unlock();
		}
	}

	//----------------------------------------------------------------------------------------------------------------------------
	// Tier cache
	
	
	public String readTierIDSycnronized(Connection conn, String name) throws Exception {
		String id = "";
		readTierLock.lock();
		try { 
			id = get(tiers, name); 
		} finally { 
			readTierLock.unlock();
		}
		return id;

	}
	
	public String getTierID(Connection conn, String name) throws Exception {
		if (!DBSConstants.USECACHE) {System.out.println("CACHE-DISABLED"); return "";}
		String id = readTierIDSycnronized(conn, name);
		if (isNull(id)) { 
			System.out.println("CACHE-MIS getTierID " + name + " .. Reloading ..");
			refreshTiers(conn);
			return readTierIDSycnronized(conn, name);
		} else 	System.out.println("CACHE-HIT getTierID " + name);
		return id;
	}

	private void refreshTiers(Connection conn) throws Exception {
		writeTierLock.lock();
		try {
			tiers = new Hashtable();
			PreparedStatement ps = null;
			ResultSet rs =  null;
			try {
				ps = DBSSql.listTiers(conn);
				rs =  ps.executeQuery();
				while(rs.next()) tiers.put(get(rs, "NAME") ,  get(rs, "ID"));
				//System.out.println("DN " + get(rs, "DN") + "  ID " + get(rs, "ID"));
			} finally { 
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			}
		} finally { 
			writeTierLock.unlock();
		}
	}
	

	//----------------------------------------------------------------------------------------------------------------------------
	// File Status cache
	
	
	public String readFileStatusIDSycnronized(Connection conn, String status) throws Exception {
		String id = "";
		readFileStatusLock.lock();
		try { 
			id = get(fileStatus, status); 
		} finally { 
			readFileStatusLock.unlock();
		}
		return id;

	}
	
	public String getFileStatusID(Connection conn, String status) throws Exception {
		if (!DBSConstants.USECACHE) {System.out.println("CACHE-DISABLED"); return "";}
		String id = readFileStatusIDSycnronized(conn, status);
		if (isNull(id)) { 
			System.out.println("CACHE-MIS getFileStatusID " + status + " .. Reloading ..");
			refreshFileStatus(conn);
			return readFileStatusIDSycnronized(conn, status);
		} else 	System.out.println("CACHE-HIT getFileStatusID " + status);
		return id;
	}

	private void refreshFileStatus(Connection conn) throws Exception {
		writeFileStatusLock.lock();
		try {
			fileStatus = new Hashtable();
			PreparedStatement ps = null;
			ResultSet rs =  null;
			try {
				ps = DBSSql.listFileStatus(conn);
				rs =  ps.executeQuery();
				while(rs.next()) fileStatus.put(get(rs, "STATUS") ,  get(rs, "ID"));
				//System.out.println("DN " + get(rs, "STATUS") + "  ID " + get(rs, "ID"));
			} finally { 
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			}
		} finally { 
			writeFileStatusLock.unlock();
		}
	}
	
	//----------------------------------------------------------------------------------------------------------------------------
	// File Type cache
	
	
	public String readFileTypeIDSycnronized(Connection conn, String type) throws Exception {
		String id = "";
		readFileTypeLock.lock();
		try { 
			id = get(fileTypes, type); 
		} finally { 
			readFileTypeLock.unlock();
		}
		return id;

	}
	
	public String getFileTypeID(Connection conn, String type) throws Exception {
		if (!DBSConstants.USECACHE) {System.out.println("CACHE-DISABLED"); return "";}
		String id = readFileTypeIDSycnronized(conn, type);
		if (isNull(id)) { 
			System.out.println("CACHE-MIS getFileTypeID " + type + " .. Reloading ..");
			refreshFileTypes(conn);
			return readFileTypeIDSycnronized(conn, type);
		} else 	System.out.println("CACHE-HIT getFileTypeID " + type);
		return id;
	}

	private void refreshFileTypes(Connection conn) throws Exception {
		writeFileTypeLock.lock();
		try {
			fileTypes = new Hashtable();
			PreparedStatement ps = null;
			ResultSet rs =  null;
			try {
				ps = DBSSql.listFileTypes(conn);
				rs =  ps.executeQuery();
				while(rs.next()) fileTypes.put(get(rs, "TYPE") ,  get(rs, "ID"));
				//System.out.println("DN " + get(rs, "TYPE") + "  ID " + get(rs, "ID"));
			} finally { 
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			}
		} finally { 
			writeFileTypeLock.unlock();
		}
	}
	

	//----------------------------------------------------------------------------------------------------------------------------
	// File ValStatus cache
	
	
	public String readFileValStatusIDSycnronized(Connection conn, String status) throws Exception {
		String id = "";
		readFileValStatusLock.lock();
		try { 
			id = get(fileValStatus, status); 
		} finally { 
			readFileValStatusLock.unlock();
		}
		return id;

	}
	
	public String getFileValStatusID(Connection conn, String status) throws Exception {
		if (!DBSConstants.USECACHE) {System.out.println("CACHE-DISABLED"); return "";}
		String id = readFileValStatusIDSycnronized(conn, status);
		if (isNull(id)) { 
			System.out.println("CACHE-MIS getFileValStatusID " + status + " .. Reloading ..");
			refreshFileValStatus(conn);
			return readFileValStatusIDSycnronized(conn, status);
		} else 	System.out.println("CACHE-HIT getFileValStatusID " + status);
		return id;
	}

	private void refreshFileValStatus(Connection conn) throws Exception {
		writeFileValStatusLock.lock();
		try {
			fileValStatus = new Hashtable();
			PreparedStatement ps = null;
			ResultSet rs =  null;
			try {
				ps = DBSSql.listFileValStatus(conn);
				rs =  ps.executeQuery();
				while(rs.next()) fileValStatus.put(get(rs, "STATUS") ,  get(rs, "ID"));
				//System.out.println("DN " + get(rs, "STATUS") + "  ID " + get(rs, "ID"));
			} finally { 
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			}
		} finally { 
			writeFileValStatusLock.unlock();
		}
	}

	//----------------------------------------------------------------------------------------------------------------------------

















	

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
