/**
 $Revision: 1.14 $"
 $Id: DBSApiBlockLogic.java,v 1.14 2007/02/09 20:09:47 sekhri Exp $"
 *
 */

package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.util.Hashtable;
import java.util.Vector;
import java.util.UUID;
import dbs.sql.DBSSql;
import dbs.util.DBSConfig;
import dbs.util.DBSUtil;
import dbs.DBSException;

/**
* A class that has the core business logic of all the Block APIs.  The signature for the API is internal to DBS and is not exposed to the clients. There is another class <code>dbs.api.DBSApi</code> that has an interface for the clients. All these low level APIs are invoked from <code>dbs.api.DBSApi</code>. This class inherits from DBSApiLogic class.
* @author sekhri
*/
public class DBSApiBlockLogic extends DBSApiLogic {
		
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs. The constructor does notthing.
	*/

	DBSApiPersonLogic personApi = null;
	DBSApiData data = null;
	public DBSApiBlockLogic(DBSApiData data) {
		super(data);
		this.data = data;
		personApi = new DBSApiPersonLogic(data);
	}

	/**
	 * Lists all the blocks within a processed dataset from the database in a xml format. This method makes one sql query, execute it, fetch the results and packs and write it in xml format to the output stream. The query that it executes get generated by <code>dbs.DBSSql.listBlocks</code> method. First it fetches the processed dataset ID from the database by calling a private <code>getProcessedDSID<code> method using the path provided in the parameter. If the processed dataset id does not esists then it throws an exception. A sample XML that is written to the output stream is like <br>
	 * <code> <"block id='2' name='/test/test#9ac2b28b-781f-4907-a87a-40e233ab139a' size='0' number_of_files='0' creation_date='2006-12-06 16:29:34.0' last_modification_date='2006-12-06 16:29:34.0' created_by='ANZARDN' last_modified_by='ANZARDN'"/></code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param path a dataset path in the format of /primary/tier/processed. If this path is not provided or the dataset id could not be found then an exception is thrown.
	 * @param patternBlockName a pattern for block name that can contain wild card characters. The pattern can have format of /*primary/*processed*#GUID* . This is not a mandatory field.
	 * @param patternSEName a pattern for storage element name that can contain wild card characters.  This is not a mandatory field.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied path is invalid, the database connection is unavailable  or processed dataset is not found.
	 */
	public void listBlocks(Connection conn, Writer out, String path, String patternBlockName, String patternSEName) throws Exception {
		boolean first = true; 
		String prevBlock = "";
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			//ps =  DBSSql.listBlocks(conn, (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true), getBlockPattern(patternBlockName), getPattern(patternSEName, "storage_element_name"));
			ps =  DBSSql.listBlocks(conn, (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, false), getBlockPattern(patternBlockName), getPattern(patternSEName, "storage_element_name"));
			rs =  ps.executeQuery();
			while(rs.next()) {
				String blockID = get(rs, "ID");
				if( !prevBlock.equals(blockID) && ! first) {
					out.write(((String) "</block>\n")); 
				}
				if( !prevBlock.equals(blockID) || first) {
					out.write(((String) "<block id='" + get(rs, "ID") +
						"' path='" + path +
						"' name='" + get(rs, "NAME") +
						"' size='" + get(rs, "BLOCKSIZE") +
						"' number_of_files='" + get(rs, "NUMBER_OF_FILES") +
						"' number_of_events='" + get(rs, "NUMBER_OF_EVENTS") +
						"' open_for_writing='" + get(rs, "OPEN_FOR_WRITING") +
						"' creation_date='" + getTime(rs, "CREATION_DATE") +
						"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
						"' created_by='" + get(rs, "CREATED_BY") +
						"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
						"'>\n"));
				}
				String se = get(rs, "STORAGE_ELEMENT_NAME");
				if(!isNull(se)) out.write(((String) "\t<storage_element storage_element_name='" + se +"'/>\n"));

				prevBlock = blockID;
				first = false;
			}
			if (!first) out.write(((String) "</block>\n"));
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}
	}


	/**
	 * Insert a block whose parameters are provided in the passed block <code>java.util.Hashtable</code>. This hashtable block is generated externally and filled in with the block parameters by parsing the xml input provided by the client. This method inserts entry into just one table called Block table. The the main query that it executes to insert in Block table, get generated by <code>dbs.DBSSql.insertBlock</code> method.<br> 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it fetches the inserts the processed dataset id using the path suppiled in the block hashtable by calling a protected <code>getProcessedDSID</code> method. If the processed dataset id is not found then an exception is thrown. <br>
	 * Then it either takes the block name suppiled in the block hashtable or generates one first generating a new GUID and then by concating /Primary/Process#GUID. Finally it inserts a new block in the Block table and writes the block name on the output stream in xml format so that the cleint can get the name of the newly created block.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param block a <code>java.util.Hashtable</code> that contains all the necessary key value pairs required for inserting a new block. The keys along with its values that it may or may not contain are <br>
	 * <code>path, name, open_for_writing, created_by, creation_date</code>
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable or a duplicate entry is being added.
	 */
	public void insertBlock(Connection conn, Writer out, Hashtable block, Hashtable dbsUser) throws Exception {
		String path = get(block, "path");
		String name = getBlock(block, "name", false);
		String openForWriting = get(block, "open_for_writing", false);
                String lmbUserID = personApi.getUserID(conn, dbsUser);
                String userID = personApi.getUserID(conn, dbsUser);
		String cbUserID = personApi.getUserID(conn, get(block, "created_by"), dbsUser );
		String creationDate = getTime(block, "creation_date", false);

		String procDSID = (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true);//Getting ID before spliting the path will type chech the path also.
		Vector seVector = DBSUtil.getVector(block, "storage_element");
		//Set defaults Values
		String[] data = path.split("/");
		if (isNull(name)) name = "/" + data[1] + "/" + data[3] +"#" + UUID.randomUUID(); 
		if (isNull(openForWriting)) openForWriting = "1";

		if(getBlockID(conn, name, false, false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertBlock(conn,
					"0",// A new block should always have 0 size
					name,
					procDSID,
					"0",// A new block should always have 0 files
                                        "0",// A new block should always have 0 events ??
					openForWriting, //openForWriting must be 1 fr a new block
					cbUserID,
					userID,
					creationDate);

				ps.execute();
			} finally { 
				if (ps != null) ps.close();
	                }
		} else {
			writeWarning(out, "Already Exists", "1020", "Block " + name + " Already Exists");
		}
		//Storage Element will be added to an existing block
		String blockID = "";
		//System.out.println("seVector.size() " + seVector.size() );
       		if(seVector.size() > 0) blockID = getBlockID(conn, name, false, true);
		//System.out.println("BLOCK ID is " + blockID);
		for (int j = 0; j < seVector.size(); ++j) {
			String seName = get((Hashtable)seVector.get(j), "storage_element_name");
			//System.out.println("storage_element_name " + seName);
			insertStorageElement(conn, out, blockID, seName , cbUserID, lmbUserID, creationDate);
		}

                        
		out.write("<block block_name='" + name + "'/>");
	}

	/**
	 * Insert a storage element in a block whose parameters are provided in the passed table <code>java.util.Hashtable</code>. This hashtable table is generated externally and filled in with the storage element parameters by parsing the xml input provided by the client.  The the main query that it executes to insert in StorageElement table, get generated by <code>dbs.DBSSql.insertName</code> method.<br> 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it fetches the block id using the block_name suppiled in the table hashtable by calling the <code>getBlockID</code> method. If the block id is not found then an exception is thrown. <br>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param table a <code>java.util.Hashtable</code> that contains all the necessary key value pairs required for inserting a new storage element. The keys along with its values that it may or may not contain are <br>
	 * <code>path, name, open_for_writing, created_by, creation_date</code>
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable or a duplicate entry is being added.
	 */
	public void insertStorageElement(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {
		String name = getBlock(table, "block_name", true);
		String blockID = getBlockID(conn, name, false, true);
		DBSApiPersonLogic personApi = new DBSApiPersonLogic(this.data);
		insertStorageElement(conn, out, 
				blockID,
				get(table, "storage_element_name", true),
				personApi.getUserID(conn, get(table, "created_by"), dbsUser ),
				personApi.getUserID(conn, dbsUser),
				getTime(table, "creation_date", false)
				);
	}
	
	private void insertStorageElement(Connection conn, Writer out, String blockID, String seName, String cbUserID, String lmbUserID, String creationDate) throws Exception {
		insertName(conn, out, "StorageElement", "SEName", seName , cbUserID, lmbUserID, creationDate);
		insertMap(conn, out, "SEBlock", "SEID", "BlockID", 
					getID(conn, "StorageElement", "SEName", seName , true),
					blockID, 
					cbUserID, lmbUserID, creationDate);

	}



	//insertBlock USED by dbsManagedBlockID
        private String insertBlock(Connection conn, Writer out, String procDSID, String path, Hashtable block, Hashtable dbsUser) throws Exception {
                String[] data = path.split("/");
                String name = "/" + data[1] + "/" + data[3] +"#" + UUID.randomUUID();
                String openForWriting = "1";
                String lmbUserID = personApi.getUserID(conn, dbsUser);
                String userID = personApi.getUserID(conn, dbsUser);
                String cbUserID = personApi.getUserID(conn, get(block, "created_by"), dbsUser );
                String creationDate = getTime(block, "creation_date", false);

                //checkBlock(name);
                PreparedStatement ps = null;
                try {
                        ps = DBSSql.insertBlock(conn,
                                "0",// A new block should always have 0 size
                                name,
                                procDSID,
                                "0",// A new block should always have 0 files
                                "0",// A new block should always have 0 events ??
                                openForWriting,
				"",
                                personApi.getUserID(conn, dbsUser),
				"");

                        ps.execute();
                } finally {
                        if (ps != null) ps.close();
                }

                //SEs defined in block
                Vector seVector = DBSUtil.getVector(block, "storage_element");

                //Storage Element will be added to an existing block
                String blockID = getBlockID(conn, name, false, true);
                //System.out.println("seVector.size() " + seVector.size() );
                //System.out.println("BLOCK ID is " + blockID);
                for (int j = 0; j < seVector.size(); ++j) {
                        String seName = get((Hashtable)seVector.get(j), "storage_element_name");
                        //System.out.println("storage_element_name " + seName);
                        insertStorageElement(conn, out, blockID, seName , cbUserID, lmbUserID, creationDate);
                }

                out.write("<block block_name='" + name + "'/>");
                return blockID;
        }

        public String dbsManagedBlockID (Connection conn, Writer out, String procDSID, String path, Hashtable block, Hashtable dbsUser) throws Exception {
                String id = "";
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                        ps = DBSSql.getOpenBlockID(conn, procDSID);
                        rs =  ps.executeQuery();
                        if(!rs.next()) {
                           //Unable to find an Open Block, Create one.
                           id = insertBlock(conn, out, procDSID, path, block, dbsUser);
                           return id;
                        }
                        id = get(rs, "ID");

                        DBSConfig config = DBSConfig.getInstance();

                        int configuredBlkSize = config.getMaxBlockSize();
                        int configuredNumFiles = config.getMaxBlockFiles();
                        DBSUtil.writeLog("*********************TEST BLOCK****************");  
                        DBSUtil.writeLog("configuredBlkSize: "+configuredBlkSize);
                        DBSUtil.writeLog("configuredNumFiles: "+configuredNumFiles);

                        int blockSize = Integer.parseInt(get(rs, "BLOCKSIZE"));
                        int numberOfFiles = Integer.parseInt(get(rs, "NUMBER_OF_FILES"));
                        DBSUtil.writeLog("blockSize: "+blockSize);
                        DBSUtil.writeLog("numberOfFiles: "+numberOfFiles);
                        if (blockSize > configuredBlkSize || numberOfFiles >= configuredNumFiles ) {
                           DBSUtil.writeLog("*********************CLOSING BLOCK****************");
                           closeBlock(conn, id);
                           id = insertBlock(conn, out, procDSID, path, block, dbsUser);
                        }
                } finally {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                }
                return id;
        }





/*

	
        private String insertBlock(Connection conn, String procDSID, String path, Hashtable dbsUser) throws Exception {
                String[] data = path.split("/");
                String name = "/" + data[1] + "/" + data[3] +"#" + UUID.randomUUID();
                String openForWriting = "1";

                //checkBlock(name);
                PreparedStatement ps = null;
                try {
                        ps = DBSSql.insertBlock(conn,
                                "0",// A new block should always have 0 size
                                name,
                                procDSID,
                                "0",// A new block should always have 0 files
                                "0",// A new block should always have 0 events ??
                                openForWriting,
				"",
                                personApi.getUserID(conn, dbsUser),
				"");

                        ps.execute();
                } finally {
                        if (ps != null) ps.close();
                }
                return getBlockID(conn, name, false, true);
        }

        public String dbsManagedBlockID (Connection conn, String procDSID, String path, Hashtable dbsUser) throws Exception {
                String id = "";
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                        ps = DBSSql.getOpenBlockID(conn, procDSID);
                        rs =  ps.executeQuery();
                        if(!rs.next()) {
                           //Unable to find an Open Block, Create one.
                           id = insertBlock(conn, procDSID, path, dbsUser);
                           return id;
                        }
                        id = get(rs, "ID");

                        DBSConfig config = DBSConfig.getInstance();

                        int configuredBlkSize = config.getMaxBlockSize();
                        int configuredNumFiles = config.getMaxBlockFiles();
                        //System.out.println("configuredBlkSize: "+configuredBlkSize);
                        //System.out.println("configuredNumFiles: "+configuredNumFiles);

                        int blockSize = Integer.parseInt(get(rs, "BLOCKSIZE"));
                        int numberOfFiles = Integer.parseInt(get(rs, "NUMBER_OF_FILES"));
                        if (blockSize > configuredBlkSize || numberOfFiles >= configuredNumFiles ) {
                           //System.out.println("*********************CLOSING BLOCK****************");
                           closeBlock(conn, id);
                           id = insertBlock(conn, procDSID, path, dbsUser);
                        }
                } finally {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                }
                return id;
        }


*/


	public void closeBlock(Connection conn, Writer out, String name) throws Exception {
		closeBlock(conn, getBlockID(conn, name, false, true));
	}

	private void closeBlock(Connection conn, String blockID) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = DBSSql.closeBlock(conn, blockID);
			ps.executeUpdate();
		} finally {
			if (ps != null) ps.close();
		}
	}
 


	
		
	/**
	 * Gets a block id from the database by using the block name as the unique key. This actually generates the sql by calling a generic private <code>dbs.sql.DBSSql.getID</code> method. 
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param name the name of the block whose id needs to be fetched.
	 * @param checkOpen a boolean flag that determines if the block is open for witing nor not. An exception is raised if the block is not open.
	 * @param excep a boolean flag that determines if the exception needs to be raised if the block is not found.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid or  the database connection is unavailable, or the block is not found.
	 */
	public String getBlockID(Connection conn, String name, boolean checkOpen, boolean excep) throws Exception {
		checkBlock(name);
		//ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getID( "Block", "Name", name));
		String id = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//ps = DBSSql.getID(conn, "Block", "Name", name);
			ps = DBSSql.getBlockID(conn, name);
			rs =  ps.executeQuery();
			if(!rs.next()) {
				if(excep) throw new DBSException("Unavailable data", "1010", "No such block : name : "  + name );
				else return null;
			}
			id = get(rs, "ID");
                        if (checkOpen) {
                           String openForWriting = get(rs, "OPEN_FOR_WRITING");
                           if ( ! openForWriting.equals("1") )
                              throw new DBSException("Data insert error", "1066", "Block : " + name + " not open for further files");
                        }
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}

		return  id;
	}



}
