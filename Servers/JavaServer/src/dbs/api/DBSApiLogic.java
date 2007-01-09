/**
 $Revision: 1.60 $"
 $Id: DBSApiLogic.java,v 1.60 2007/01/08 17:48:22 sekhri Exp $"
 *
 */

package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.util.regex.Pattern;
import java.util.Hashtable;
import java.util.Date;
import dbs.sql.DBSSql;
import dbs.util.DBSUtil;
import dbs.DBSException;
import dbs.DBSConstants;

/**
* A class that has the core business logic of some of the general DBS API. Here some of the API is defined and implemented. The signature for the API is internal to DBS and is not exposed to the clients. There is another class <code>dbs.api.DBSApi</code> that has an interface for the clients. All these low level APIs are invoked from <code>dbs.api.DBSApi</code>. The APIs are group together into other classes that inherits from this class. For example all the file APIs are in DBSApiFileLogic class.
* @author sekhri
*/
public class DBSApiLogic {
	//private static String SAFE_PATH = "[-A-Za-z0-9_./\\p{%}]";
	private static String SAFE_TIME = "[-0-9.]+";

	//A regular expression used to validate a path that will not contain any special characters or blank space but can contain slashes.
	private static String SAFE_PATH = "[-\\w_\\.%/]+";
	//A regular expression used to validate a word. This word will not contain any blank space or special characters.
	private static String SAFE_WORD = "[-\\w_\\.%]+";
	//A regular expression used to validate a sentence. This word will not contain any special characters but can contain blank spaces.
	private static String SAFE_STR = "[-\\w_\\.% ]+";
	//A regular expression used to validate a block name. This word will not contain any special characters or blank spaces but can contain slashes.
	private static String SAFE_BLOCK = "[-\\w_\\.%#/]+";
	private static String SAFE_BLOCK_LIST = "[-\\w_\\.%#/\\%]+";
	//A regular expression used to validate a path that will contain exactly three slashes.
	private static String VALID_PATH = "^/([^/]+)/([^/]+)/([^/]+)";
	//A regular expression used to validate a block name that will contain exactly thw slashes and a hash.
	private static String VALID_BLOCK = "^/([^/]+)/([^/]+)#([^/]+)";
	private static String VALID_BLOCK_LIST = "^/([^/]+)/([^/]+)#([^/]+)|%";
	//We can store the path id once and everytime the id is needed it can be fetched from this table instead of fetching it through database.
	protected static Hashtable globalUser = new Hashtable();
	protected static Hashtable globalFile = new Hashtable();
	protected static Hashtable globalPDPath = new Hashtable();
		
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs. The constructor does notthing.
	*/
	//DBSApiPersonLogic personApi = null;
	public DBSApiLogic() {
		//personApi = new DBSApiPersonLogic();
	}



	/**
	 * Insert a run  whose parameters are provided in the passed run <code>java.util.Hashtable</code>. This hashtable run is generated externally and filled in with the run parameters by parsing the xml input provided by the client. This method inserts entry into just one table called Run table. The the main query that it executes to insert in Run table, get generated by <code>dbs.DBSSql.insertRun</code> method.<br> 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. Then it finally inserts a new run in Run table.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param run a <code>java.util.Hashtable</code> that contains all the necessary key value pairs required for inserting a new run. The keys along with its values that it may or may not contain are <br>
	 * <code>run_number, number_of_events, number_of_lumi_sections, total_luminosity, store_number, start_of_run, end_of_run</code>
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable or a duplicate entry is being added.
	 */
	public void insertRun(Connection conn, Writer out, Hashtable run, Hashtable dbsUser) throws Exception {
		DBSApiPersonLogic personApi = new DBSApiPersonLogic();
		String runNumber = get(run, "run_number", true);
		if(getID(conn, "Runs", "RunNumber", runNumber, false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertRun(conn, 
					runNumber,
					get(run, "number_of_events", true),
					get(run, "number_of_lumi_sections", true),
					get(run, "total_luminosity", true),
					get(run, "store_number", true),
					get(run, "start_of_run", false),
					get(run, "end_of_run", false),
					personApi.getUserID(conn, get(run, "created_by", false), dbsUser ),
					personApi.getUserID(conn, dbsUser),
					getTime(run, "creation_date", false));

				ps.execute();
			} finally { 
				if (ps != null) ps.close();
        	        }
		} else {
			writeWarning(out, "Already Exists", "1020", "Run " + runNumber + " Already Exists");
		}

	}

	/**
	 * Insert a tier whose name is provided in the parameter tierName. This method inserts entry into just one table table called DataTier table. The the main query that it executes to insert in DataTier table, get generated by a generic <code>dbs.DBSSql.insertName</code> method.<br> 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. Then it finally inserts a new tier in DataTier table.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param table a <code>java.util.Hashtable</code> that contains the name of the data tier to be inserted and may or may not contain created_by and creation_date fields.
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameter tierName is invalid, the database connection is unavailable or a duplicate entry is being added.
	 */
	/*public void insertTier(Connection conn, Writer out, String tierName, Hashtable dbsUser) throws Exception {
		//insertName(conn, out, "DataTier", "Name", tierName , (new DBSApiPersonLogic()).getUserID(conn, dbsUser));
		insertTier(conn, out, "DataTier", "Name", tierName , (new DBSApiPersonLogic()).getUserID(conn, dbsUser));
	}*/

	public void insertTier(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {
		DBSApiPersonLogic personApi = new DBSApiPersonLogic();
		insertTier(conn, out, 
				get(table, "tier_name", true),
				personApi.getUserID(conn, get(table, "created_by", false), dbsUser ),
				personApi.getUserID(conn, dbsUser),
				getTime(table, "creation_date", false)
				);
	}
	
	protected void insertTier(Connection conn, Writer out, String tierName, String cbUserID, String lmbUserID, String creationDate) throws Exception {
		insertName(conn, out, "DataTier", "Name", tierName , cbUserID, lmbUserID, creationDate);
	}


	/**
	 * Insert a lumi section whose parameters are provided in the passed lumi <code>java.util.Hashtable</code>. This hashtable is generated externally and filled in with the lumi section parameters by parsing the xml input provided by the client. This method inserts entry into just one  LumiSection table. The the main query that it executes to insert in LumiSection table, get generated by <code>dbs.DBSSql.insertLumiSection</code> method.<br> 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it insert a new lumi section whose sql query is generated by calling <code>dbs.sql.insertLumiSection<code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param table a <code>java.util.Hastable</code> that contain all the necessary key value pairs required for inserting a new lumi section. The keys along with its values that it may or may not contain are <br>
	 * <code>lumi_section_number, run_number, start_event_number, end_event_number, lumi_start_time, lumi_end_time, created_by, creation_date </code> <br>
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable.
	 */
	public void insertLumiSection(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {
		DBSApiPersonLogic personApi = new DBSApiPersonLogic();
		insertLumiSection(conn, out, table, 
				personApi.getUserID(conn, get(table, "created_by", false), dbsUser ),
				personApi.getUserID(conn, dbsUser),
				getTime(table, "creation_date", false)
				);
	}
	protected void insertLumiSection(Connection conn, Writer out, Hashtable lumi, String cbUserID, String lmbUserID, String creationDate) throws Exception {
		String lsNumber = get(lumi, "lumi_section_number", true);
		//Insert a new Lumi Section by feting the run ID 
		if( getID(conn, "LumiSection", "LumiSectionNumber", lsNumber, false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertLumiSection(conn,
						lsNumber,
						getID(conn, "Runs", "RunNumber",
							get(lumi, "run_number", true),
							true),
						get(lumi, "start_event_number", true),
						get(lumi, "end_event_number", true),
						get(lumi, "lumi_start_time", false),
						get(lumi, "lumi_end_time", false),
						cbUserID,
						lmbUserID,
						creationDate);
				ps.execute();
			} finally {
				if (ps != null) ps.close();
			}

		} else {
			writeWarning(out, "Already Exists", "1020", "LumiSection " + lsNumber + " Already Exists");
		}
	}


	protected static void writeWarning(Writer out, String message, String code, String detail) throws Exception {
		//out.write(DBSConstants.XML_EXCEPTION_HEADER);
		message = message.replace('\'',' ');
		detail= detail.replace('\'',' ');
		code =code.replace('\'',' ');
		out.write("<warning message='" + message + "' ");
		out.write(" code ='" + code + "' ");
		out.write(" detail ='" + detail + "' />\n");
		out.flush();
		//out.write(DBSConstants.XML_EXCEPTION_FOOTER);
	}




	/**
	 * This is a private generic method that can insert entry into any table that has just one coloum in it which is unique. Since there are many such tables in the schema that has such kind of tables, therefore this method is resued several times to insert rows in them. It first checks of the row already exist in the database or not. Only if it does not exist, it goes ahead and performs a new insert.
	 * @param table the table name of the table in the database schema.
	 * @param key the coloumn name of the table in the database schema that is unique.
	 * @param value the value to be inserted in the coloumn name of the table.
	 * @param cbUserID a user id of the person who is inserting this new row into this given database table.
	 * @param lmbUserID a user id of the person who is updating this new row into this given database table. The user id correspond to the Person table id in database. This is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @param creationDate a user provided date that will be inserted along with the row. If this date is not provided, then the system date is used instead.
	 */
	protected void insertName(Connection conn, Writer out, String table, String key, String value, String cbUserID, String lmbUserID, String creationDate) throws Exception {
		if(isNull(value)) throw new DBSException("Missing data", "1006", "Null field. Expected a valid " + key );
		if(isNull(lmbUserID)) throw new DBSException("Missing data", "1006", "Null field. Expected a valid UserDN");
		if( getID(conn, table, key, value, false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertName(conn, table, key, value, cbUserID, lmbUserID, creationDate);
				ps.execute();
			} finally {
				if (ps != null) ps.close();
			}
		} else {
			writeWarning(out, "Already Exists", "1020", "Table " + table + " " + key + " with value " + value +  " Already Exists");
		}	

	}
	
	/**
	 * This is a private generic method that can insert entry into any table that has just two coloum in it which are unique. Since there are many such tables in the schema that has such kind of tables, therefore this method is resued several times to insert rows in them. It first checks of the row already exist in the database or not. Only if it does not exist, it goes ahead and performs a new insert.
	 * @param tableName the table name of the table in the database schema.
	 * @param key1 the first coloumn name of the table in the database schema.
	 * @param key2 the second coloumn name of the table in the database schema.
	 * @param value1 the first value to be inserted in the first coloumn name of the table.
	 * @param value2 the second value to be inserted in the second coloumn name of the table.
	 * @param cbUserID a user id of the person who is inserting this new row into this given database table.
	 * @param lmbUserID a user id of the person who is updating this new row into this given database table. The user id correspond to the Person table id in database. This is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @param creationDate a user provided date that will be inserted along with the row. If this date is not provided, then the system date is used instead.
	 */
	protected void insertMap(Connection conn, Writer out, String tableName, String key1, String key2, String value1, String value2, String cbUserID, String lmbUserID, String creationDate) throws Exception {
		if( getMapID(conn, tableName, key1, key2, value1, value2, false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertMap(conn, tableName, key1, key2, value1, value2, cbUserID, lmbUserID, creationDate);
				ps.execute();
			} finally {
				if (ps != null) ps.close();
			}
		} else {
			writeWarning(out, "Already Exists", "1020", "Table " + tableName + " " + key1 + " " + key2 + " with values " + value1 + " " + value2 + " Already Exists");
		}	

	}

	/**
	 * Insert a physics group whose parameters. This method inserts entry into just one PhysicsGroup table. The the main query that it executes to insert in PhysicsGroup table, get generated by <code>dbs.DBSSql.insertPhysicsGroup</code> method.<br> 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it insert a new physics group if it does not already exist.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param table a <code>java.util.Hastable</code> that contain all the necessary key value pairs required for inserting a new physics group. The keys along with its values that it may or may not contain are <br>
	* <code>physics_group_name, physics_group_convener, created_by, creation_date </code> <br> 
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid or the database connection is unavailable.
	 */
	public void insertPhysicsGroup(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {
		DBSApiPersonLogic personApi = new DBSApiPersonLogic();
		insertPhysicsGroup(conn, out, 
				get(table, "physics_group_name", true),
				get(table, "physics_group_convener", true),
				personApi.getUserID(conn, get(table, "created_by", false), dbsUser ),
				personApi.getUserID(conn, dbsUser),
				getTime(table, "creation_date", false)
				);

	}

	protected void insertPhysicsGroup(Connection conn, Writer out, String name, String phyGroupCon, String cbUserID, String lmbUserID, String creationDate) throws Exception {
		//Insert a new Person if it does not exists
		(new DBSApiPersonLogic()).insertPerson(conn, out,  "", phyGroupCon, "", cbUserID, lmbUserID, creationDate); //FIXME Get userName and contactInfo also
		if( getID(conn, "PhysicsGroup", "PhysicsGroupName", name, false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertPhysicsGroup(conn,
					name, 
					getID(conn, "Person", "DistinguishedName", phyGroupCon, true), 
					cbUserID,
					lmbUserID,
					creationDate);
				ps.execute();
			} finally {
				if (ps != null) ps.close();
			}
		} else {
			writeWarning(out, "Already Exists", "1020", "Physics Group " + name + " Already Exists");
		}	

	}



	
	/**
	 * Gets a id of a table from the given database table using the key value pair specified in the parameters. This method can be called to fetch the id of any table that has just one unique key. The sql is generated by calling a generic private <code>dbs.sql.DBSSql.getID</code> method. 
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param tableName the name of the table in the database whose id needs to fetched.
	 * @param key the name of the only unique coloumn in the given table whose id needs to fetched.
	 * @param value the value of the only unique coloumn in the given table whose id needs to fetched.
	 * @param excep a boolean flag that determines if the exception needs to be raised if the block is not found.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid or  the database connection is unavailable, or the table id is not found.
	 */
	protected String getID(Connection conn, String tableName, String key, String value, boolean excep) throws Exception {
		if(isNull(tableName) || isNull(key) || isNull(value)) {

                     if(excep) throw new DBSException("Unavailable data", "1011", "No such " + 
                                                                                 tableName + " : " + key + " : " + value );
                     return null;
                } 
		//if(tableName.equals("Files")) System.out.println("key is "+ key + " value is "+ value + " len  is " + value.length() + " excep is " + excep);
		if (excep) checkWord(value, key);
		else if(!isNull(value)) checkWord(value, key);

		//ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getID(tableName, key, value));
		String id = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps =  DBSSql.getID(conn, tableName, key, value);
			rs =  ps.executeQuery();
			if(!rs.next()) {
				if(excep) throw new DBSException("Unavailable data", "1011", "No such " + tableName + " : " + key + " : " + value );
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
	 * Gets a id of a table from the given database table using the two key value pair specified in the parameters. This method can be called to fetch the id of any table that has excatly two unique keys. The sql is generated by calling a generic private <code>dbs.sql.DBSSql.getMapID</code> method. 
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param tableName the name of the table in the database whose id needs to fetched.
	 * @param key1 the name of the first unique coloumn in the given table whose id needs to fetched.
	 * @param key2 the name of the second unique coloumn in the given table whose id needs to fetched.
	 * @param value1 the value of the first unique coloumn in the given table whose id needs to fetched.
	 * @param value2 the value of the second unique coloumn in the given table whose id needs to fetched.
	 * @param excep a boolean flag that determines if the exception needs to be raised if the block is not found.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid or  the database connection is unavailable, or the table id is not found.
	 */
	private String getMapID(Connection conn, String tableName, String key1, String key2, String value1, String value2,  boolean excep) throws Exception {
		if(isNull(tableName) || isNull(key1) || isNull(value1) || isNull(key2) || isNull(key2) ) return null;
		if (excep) {
			checkWord(value1, key1);
			checkWord(value2, key2);
		} else {
			if(!isNull(value1)) checkWord(value1, key1);
			if(!isNull(value2)) checkWord(value2, key2);
		}
		//ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getMapID(tableName, key1, key2, value1, value2));
		String id = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps =  DBSSql.getMapID(conn, tableName, key1, key2, value1, value2);
			rs =  ps.executeQuery();
			if(!rs.next()) {
				if(excep) throw new DBSException("Unavailable data", "1012", "No such " + tableName + " : " + key1 + " : " + value1 + " : " + key2 + " : " + value2);
				else return null;
			}
			id = get(rs, "ID");
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}

		return  id;
	}


	protected String[] parseDSPath(String path) throws Exception {
		checkPath(path);
		String[] data = path.split("/");
		if(data.length != 4) {
			throw new DBSException("Invalid format", "1007", " Expected a path in format /PRIMARY/TIER/PROCESSED given " + path);
		}
		return data;
	}

	/**
	 * Checks the dataset path against a regular expression that validates a valid dataset path.
	 * @param path a dataset path in the format of /primary/tier/processed. If this path is not provided or the dataset id could not be found then an exception is thrown.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid.
	 */
	private void checkPath(String path) throws Exception {
		if(isNull(path)) 
			throw new DBSException("Missing data", "1006", "Null Fields. Expected a valid path in format /PRIMARY/TIER/PROCESSED");
		if (! Pattern.matches(VALID_PATH, path) ) 
			throw new DBSException("Invalid format", "1007", "Expected a path in format /PRIMARY/TIER/PROCESSED which should satisfy the regular expression " + VALID_PATH + " The given path is " + path);
		if( ! Pattern.matches(SAFE_PATH, path) ) 
			throw new DBSException("Invalid format", "1013", "Invalid Characters in " + path + " for path. Expected a path in format /PRIMARY/TIER/PROCESSED  which should satisfy the regular expression  "+ SAFE_PATH);
	}
	
	/**
	 * Checks the dataset block name against a regular expression that validates a valid block name.
	 * @param blockName the name of the block in the format of /primary/processed#GUID. If this blockName is not provided or the block id could not be found then an exception is thrown.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid.
	 */

	protected void checkBlock(String blockName) throws Exception {
		if(isNull(blockName)) 
			throw new DBSException("Missing data", "1006", "Null Fields. Expected a valid block_name in format /PRIMARY/PROCESSED#GUID");
		if (! Pattern.matches(VALID_BLOCK, blockName) ) 
			throw new DBSException("Invalid format", "1014", "Expected a block_name in format /PRIMARY/PROCESSED#GUID which should satisfy the regular expression " + VALID_BLOCK + " The given block_name is " + blockName);
		if( ! Pattern.matches(SAFE_BLOCK, blockName) ) 
			throw new DBSException("Invalid format", "1015", "Invalid Characters in " + blockName + " for block_name. Expected a block_name in format /PRIMARY/PROCESSED#GUID which should satisfy the regular expression " + SAFE_BLOCK);
	}


        protected void checkBlock4List(String blockName) throws Exception {
                if(isNull(blockName))
                        throw new DBSException("Missing data", "1006", "Null Fields. Expected a valid block_name in format /PRIMARY/PROCESSED#GUID");
               // if (! Pattern.matches(VALID_BLOCK_LIST, blockName) )
               //         throw new DBSException("Invalid format", "1014", "Expected a block_name in format /PRIMARY/PROCESSED#GUID which should satisfy the regular expression " + VALID_BLOCK_LIST + " The given block_name is " + blockName);
                if( ! Pattern.matches(SAFE_BLOCK_LIST, blockName) )
                        throw new DBSException("Invalid format", "1015", "Invalid Characters in " + blockName + " for block_name. Expected a block_name in format /PRIMARY/PROCESSED#GUID which should satisfy the regular expression " + SAFE_BLOCK_LIST);
        }


	/**
	 * Checks a word as whole against a regular expression that validates a english word without any special characters.
	 * @param pattern the value of the word that needs to be validated.
	 * @param key the name of the key which is used to throw an exception in case the word fails to validate. This make the exception message more intutive as it states which key was being checked.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid.
	 */
	protected void checkWord(String pattern, String key) throws Exception {
		if(isNull(pattern))
			throw new DBSException("Missing data", "1006", "Null Fields. Expected a valid " + key);
		if (! Pattern.matches(SAFE_WORD, pattern)) 
			throw new DBSException("Invalid format", "1016", "Invalid Characters in " + pattern + " for " + key + " Expected a valid " + key + " which should satisfy the regular expression "+ SAFE_WORD);
	}
	
	/**
	 * Checks a sentence  against a regular expression that validates a english sentence without any special characters.
	 * @param pattern the value of the word that needs to be validated.
	 * @param key the name of the key which is used to throw an exception in case the word fails to validate. This make the exception message more intutive as it states which key was being checked.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid.
	 */
        private void checkString(String pattern, String key) throws Exception {
                if(isNull(pattern))
                        throw new DBSException("Missing data", "1006", "Null Fields. Expected a valid " + key);
                if (! Pattern.matches(SAFE_STR, pattern))
                        throw new DBSException("Invalid format", "1017", "Invalid Characters in " + pattern + " for " + key + " Expected a valid " + key + " which should satisfy the regular expression " + SAFE_STR);
        }

	/**
	 * Checks the time against a regular expression that validates a long numberics without any special characters.
	 * @param pattern the value of the word that needs to be validated.
	 * @param key the name of the key which is used to throw an exception in case the word fails to validate. This make the exception message more intutive as it states which key was being checked.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid.
	 */
	protected void checkTime(String pattern, String key) throws Exception {
		if(isNull(pattern))
			throw new DBSException("Missing data", "1006", "Null Fields. Expected a valid " + key);
		if (! Pattern.matches(SAFE_TIME, pattern)) 
			throw new DBSException("Invalid format", "1036", "Invalid Characters in " + pattern + " for " + key + " Expected a valid " + key + " which should satisfy the regular expression "+ SAFE_TIME);
	}

	
	protected boolean isNull(String pattern) {
		return DBSUtil.isNull(pattern);
	}
	
	protected String get(Hashtable table, String key, boolean excep) throws Exception{
		String value = DBSUtil.get(table, key);
		if(excep) checkWord(value, key);
		else if(! isNull(value)) checkWord(value, key);
		return value;
	}

        protected String getStr(Hashtable table, String key, boolean excep) throws Exception{
                String value = DBSUtil.get(table, key);
                if(excep) checkString(value, key);
                else if(! isNull(value)) checkString(value, key);
                return value;
        }

	
	protected String getPath(Hashtable table, String key, boolean excep) throws Exception{
                String value = DBSUtil.get(table, key);
                if(excep) checkPath(value);
                else if(! isNull(value)) checkPath(value);
                return value;
        }

	protected String getTime(Hashtable table, String key, boolean excep) throws Exception{
                String value = DBSUtil.get(table, key);
                if(excep) checkTime(value, key);
                else if(! isNull(value)) { 
			checkTime(value, key);
		} else {
			value = Long.toString( (new Date()).getTime() );
		}
                return value;
        }

	protected String get(Hashtable table, String key) {
		return DBSUtil.get(table, key);
	}

	protected String get(ResultSet rs, String key) throws Exception {
		String value = rs.getString(key);
		if(isNull(value)) return "";
		return value;
	}
	
	protected String getTime(ResultSet rs, String key) throws Exception {
		Timestamp value = rs.getTimestamp(key);
		if(value == null) return "";
		return Long.toString(value.getTime());
	}

	protected String getPattern(String pattern, String key) throws Exception {
		System.out.println("pattern  in getPattern is " + pattern);
		if(isNull(pattern))  return "%";
		pattern = pattern.replace('*','%');
		checkWord(pattern,key);
		System.out.println("pattern before returnning  in getPattern is " + pattern);
        	return pattern;
	}

	protected String getBlock(Hashtable table, String key, boolean excep) throws Exception{
                String value = DBSUtil.get(table, key);
                if(excep) checkBlock(value);
                else if(! isNull(value)) checkBlock(value);
                return value;
        }

	protected String getBlockPattern(String pattern) throws Exception {
		if(isNull(pattern))  return "%";
		pattern = pattern.replace('*','%');
		(new DBSApiBlockLogic()).checkBlock4List(pattern);
		//(new DBSApiBlockLogic()).checkBlock(pattern);
        	return pattern;
	}

	
}
