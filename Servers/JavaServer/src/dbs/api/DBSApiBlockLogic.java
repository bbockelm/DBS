/**
 $Revision: 1.51 $"
 $Id: DBSApiBlockLogic.java,v 1.51 2008/03/14 20:50:54 sekhri Exp $"
 *
 */

package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.Vector;
import java.util.UUID;
import codec.Base64;
import dbs.DBSConstants;
import dbs.sql.DBSSql;
import dbs.util.DBSConfig;
import dbs.util.DBSUtil;
import dbs.DBSException;
import dbs.api.parser.DBSApiParser;

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
		listBlocks(conn, out, path, patternBlockName, patternSEName, "NORMAL");
	}
	public void listBlocks(Connection conn, Writer out, String path, String patternBlockName, String patternSEName, String userType) throws Exception {
		boolean first = true; 
		String prevBlock = "";
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			String procDSID = "";
			String patternPath = "";
			if (!isNull(path)) {
				procDSID = (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true);
				patternPath = path;
			}
			ps =  DBSSql.listBlocks(conn, procDSID, patternPath, getBlockPattern(patternBlockName), getPattern(patternSEName, "storage_element_name"));
			rs =  ps.executeQuery();
			//System.out.println("userType " + userType);
			if(isNull(userType)) userType = "NORMAL";
			while(rs.next()) {
				String blockID = get(rs, "ID");
				if( !prevBlock.equals(blockID) && ! first) {
					out.write(((String) "</block>\n")); 
				}
				if( !prevBlock.equals(blockID) || first) {
					out.write(((String) "<block id='" + get(rs, "ID") +
						"' path='" + get(rs, "PATH") +
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
				String role = get(rs, "ROLES");
				//System.out.println("Role is " + role);
				if(!isNull(role)) {
					if(role.equals("Y") || userType.equals("SUPER")) {
						String se = get(rs, "STORAGE_ELEMENT_NAME");
						//System.out.println("SE name is " + se);
						if(!isNull(se)) out.write(((String) "\t<storage_element storage_element_name='" + se +"'/>\n"));
					}
				}

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
	 * Lists all the storage elements in the database in a xml format. This method makes one sql query, execute it, fetch the results and packs and write it in xml format to the output stream. The query that it executes get generated by <code>dbs.DBSSql.listStorageElements</code> method. 
	 * A sample XML that is written to the output stream is like <br>
	 * <code> <"storage_element id='2' name='/test/test#9ac2b28b-781f-4907-a87a-40e233ab139a' creation_date='2006-12-06 16:29:34.0' last_modification_date='2006-12-06 16:29:34.0' created_by='ANZARDN' last_modified_by='ANZARDN'"/></code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param patternSEName a pattern for storage element name that can contain wild card characters.  This is not a mandatory field.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied path is invalid, the database connection is unavailable  or processed dataset is not found.
	 */
	public void listStorageElements(Connection conn, Writer out, String patternSEName) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps =  DBSSql.listStorageElements(conn, getPattern(patternSEName, "storage_element_name"));
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<storage_element id='" + get(rs, "ID") +
						"' storage_element_name='" + get(rs, "STORAGE_ELEMENT_NAME") +
						"' creation_date='" + getTime(rs, "CREATION_DATE") +
						"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
						"' created_by='" + get(rs, "CREATED_BY") +
						"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
						"'/>\n"));

			}
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
		String path = getPath(block, "path", true);
		String name = getBlock(block, "name", false);
		String openForWriting = get(block, "open_for_writing", false);
                String lmbUserID = personApi.getUserID(conn, dbsUser);
                String userID = personApi.getUserID(conn, dbsUser);
		String cbUserID = personApi.getUserID(conn, get(block, "created_by"), dbsUser );
		String creationDate = getTime(block, "creation_date", false);

		DBSApiProcDSLogic procDSApiObj = new DBSApiProcDSLogic(this.data);
		//Getting ID before spliting the path will type chech the path also.
		String procDSID = procDSApiObj.getProcessedDSID(conn, path, true);
		procDSApiObj.checkProcDSStatus(conn, out, path, procDSID);

                Vector procDSTierVec = procDSApiObj.getProcDSTierVec(conn, procDSID);

		String[] datapath = path.split("/");
                Vector pathTierVec = parseTierVec(datapath[3]);
		if ( ! procDSTierVec.containsAll(pathTierVec) )
				throw new DBSException("Tier Mismatch", "1044",
                                                        "Provided Tier(s) combinition " + pathTierVec.toString() + 
							" is not present in dataset "+ path + " Path contains " + 
							procDSTierVec.toString());

		Vector seVector = DBSUtil.getVector(block, "storage_element");
		//Set defaults Values

		String correctedPath = "/" + datapath[1] + "/" + datapath[2]
                                                                + "/"+ makeOrderedTierList(conn, pathTierVec);

		if (!isNull(name)) {
			checkBlock(name);
			String[] data = name.split("#");
			String[] blockPath = data[0].split("/");
			
			String[] pathToks = path.split("/");
			//Anzar Afaq: 04/17/2007
                        // This check is NOT required for the period when WE WANT
			// DATA Inconsistencies in DBS
			//if ( ! pathToks[1].equals(blockPath[1])  ||
			//		! pathToks[2].equals(blockPath[2]) ) {
				//throw new DBSException("Path mismatch", "1045", 
				//		"Block path portion "  + data[0] + " does not match with Path " + path);
			//}

			//Check the Order of Tier list	
			if(blockPath.length == 4) {
				Vector blockTierVec = parseTierVec(blockPath[3]);
				if ((blockTierVec.size() != pathTierVec.size()) || (!pathTierVec.containsAll(blockTierVec)) )
					throw new DBSException("Tier Mismatch", "1043",
							"Tiers of path portion of the block " + name + " does not match with " +
							"tiers of given path " + path ) ;

				name = "/" + blockPath[1] + 
				"/" + blockPath[2] + 
				"/" + makeOrderedTierList(conn, parseTierVec(blockPath[3])) +
				"#" + data[1];
				
			}

		} else 	name = correctedPath  + "#" + UUID.randomUUID(); 

		//if (isNull(name)) name = path +"#" + UUID.randomUUID(); 
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
					correctedPath,
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
		DBSApiPersonLogic personApi = new DBSApiPersonLogic(this.data);
		insertStorageElement(conn, out, 
				getBlockID(conn, getBlock(table, "block_name", true), false, true),
				get(table, "storage_element_name", true),
				personApi.getUserID(conn, get(table, "created_by"), dbsUser ),
				personApi.getUserID(conn, dbsUser),
				getTime(table, "creation_date", false)
				);
	}
	
	private void insertStorageElement(Connection conn, Writer out, String blockID, String seName, String cbUserID, String lmbUserID, String creationDate) throws Exception {
		insertNameInfo(conn, out, "StorageElement", "SEName", seName , cbUserID, lmbUserID, creationDate);
		insertMap(conn, out, "SEBlock", "SEID", "BlockID", 
					getID(conn, "StorageElement", "SEName", seName , true),
					blockID, 
					cbUserID, lmbUserID, creationDate);

	}

	/*public void deleteSEFromBlockPermissive(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {
		String seName = getPattern(get(table, "storage_element_name"), "storage_element_name");
		String blockName = getBlockPattern(get(table, "block_name"));
		String seID = "%";
		String blockID = "%";
		if (seName != "%") seID = getID(conn, "StorageElement", "SEName", seName , true);
		if (blockName != "%") blockID = getBlockID(conn, blockName, false, true);
		deleteMap(conn,	out, "SEBlock", "SEID", "BlockID", seID, blockID);

	}*/
	
	public void deleteSEFromBlock(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {
		String seName = get(table, "storage_element_name", true);
		String seID = getID(conn, "StorageElement", "SEName", seName , true);
		deleteMap(conn,	out, "SEBlock", "SEID", "BlockID", 
				seID, 
				getBlockID(conn, getBlock(table, "block_name", true), false, true));
		
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps =  DBSSql.getMap(conn, "SEBlock", "SEID", "BlockID", seID, "");
			rs =  ps.executeQuery();
			if(!rs.next()) {
				deleteName(conn, out, "StorageElement", "SEName", seName);
			}
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}

	}

	//insertBlock USED by dbsManagedBlockID
        private String insertBlock(Connection conn, Writer out, String procDSID, String path, Hashtable block, Vector seVector, Hashtable dbsUser) throws Exception {
                //String[] data = path.split("/");
                //String name = "/" + data[1] + "/" + data[3] +"#" + UUID.randomUUID();
		String name = path +"#" + UUID.randomUUID();
                String lmbUserID = personApi.getUserID(conn, dbsUser);
                String userID = personApi.getUserID(conn, dbsUser);
                String cbUserID = personApi.getUserID(conn, get(block, "created_by"), dbsUser );
                String creationDate = getTime(block, "creation_date", false);
		
		(new DBSApiProcDSLogic(this.data)).checkProcDSStatus(conn, out, path, procDSID);

                //checkBlock(name);
                PreparedStatement ps = null;
                try {
                        ps = DBSSql.insertBlock(conn,
                                "0",// A new block should always have 0 size
                                name,
                                procDSID,
                                "0",// A new block should always have 0 files
                                "0",// A new block should always have 0 events ??
                                "1",// A new block should always be openForWriting = 1
				path,
				cbUserID,
                                personApi.getUserID(conn, dbsUser),
				creationDate);

                        ps.execute();
                } finally {
                        if (ps != null) ps.close();
                }

                //Storage Element will be added to an existing block
                String blockID = getBlockID(conn, name, false, true);

		for(int i = 0 ; i != seVector.size(); ++i) {
				insertStorageElement(conn, out, blockID, (String)seVector.get(i), cbUserID, lmbUserID, creationDate);
		}

                out.write("<block block_name='" + name + "'/>");
                return blockID;
        }

	/**
	 * Updates the name of the storage element. 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param seNameFrom the name ofthe Storage Element that need to be changed.
	 * @param seNameTo the name ofthe Storage Element that it has to be changed to.
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid, the database connection is unavailable or a procsssed dataset is not found.
	 */
	public void updateSEName(Connection conn, Writer out, String seNameFrom, String seNameTo, Hashtable dbsUser) throws Exception {
		String seIDNew = getID(conn, "StorageElement", "SEName", seNameTo , false);
		String seIDOld = getID(conn, "StorageElement", "SEName", seNameFrom , true);
		String deleteSeMaps = "";
		String updatedSE = "";
		if (isNull(seIDNew)) {
			updateName(conn, out, "StorageElement", "SEName", seNameFrom, seNameTo, personApi.getUserID(conn, dbsUser));
			insertTimeLog(conn, "UpdateSEName", "User called UpdateSEName", 
						"Renamed "+seNameFrom+" to "+seNameTo,
						"The new SE "+seNameTo+" provided was not in DBS, so older SE "+seNameFrom+" is changed to newer",
						dbsUser);
		} else {
			Vector oldBlockIDs = getBlockIDListFromMap(conn, seIDOld);
			Vector newBlockIDs = getBlockIDListFromMap(conn, seIDNew);
			for (int i = 0; i != oldBlockIDs.size() ; ++i) {
				String blockID = (String)oldBlockIDs.elementAt(i);
				if(newBlockIDs.contains(blockID)) {
					//Delete this entry beacuse of duplication
					deleteMap(conn, out, "SEBlock", "BlockID", "SEID", blockID, seIDOld);
					//System.out.println("deleting SEID " + seIDOld + " blockID " + blockID);

					deleteSeMaps += "blockID:" + blockID + "seID:" + seIDOld + " deleted ";
					
				} else {
					//Just change the SE for this Block
					updateMap(conn, out, "SEBlock", "BlockID", "SEID", 
							blockID,
							seIDNew,
							seIDOld,
							personApi.getUserID(conn, dbsUser));
					updatedSE += "Updating SEID:" + seIDOld + " blockID: " + blockID + " to SEID: " + seIDNew;
					//System.out.println("updating SEID " + seIDOld + " blockID " + blockID + " to SEID " + seIDNew);

				}
			}
			//Finally delete the old Storage Element
			deleteName(conn, out, "StorageElement", "SEName", seNameFrom);
			insertTimeLog(conn, "UpdateSEName", "User called UpdateSEName",
					"Some older SE-Block maps may have been deleted, or renamed",
					deleteSeMaps + updatedSE + ".Further Old Storage Element " + seNameFrom + "is deleted ",
					dbsUser);
	
		}
	
	}

	private Vector getBlockIDListFromMap(Connection conn, String seID) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs =  null;
		Vector toReturn = new Vector();
		try {
			ps =  DBSSql.getMap(conn, "SEBlock", "SEID", "BlockID", seID, "");
			rs =  ps.executeQuery();
			while(rs.next()) toReturn.add(get(rs, "BlockID"));
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}
		return toReturn;

	}
	

	public void updateSEBlock(Connection conn, Writer out, String blockName, String seNameFrom, String seNameTo, Hashtable dbsUser) throws Exception {
		String blockID = getBlockID(conn, blockName, false, true);
		String seIDNew = getID(conn, "StorageElement", "SEName", seNameTo , true);
		if( !isNull(getMapID(conn, "SEBlock", "SEID", "BlockID", seIDNew, blockID, false)))  
			throw new DBSException("Already exists", "1082", "Block " + blockName + "  with Storage Element " + seNameTo + "  Already exists. Cannot change storage element " + seNameFrom + " to " + seNameTo);
		updateMap(conn, out, "SEBlock", "BlockID", "SEID", 
				blockID,
				seIDNew,
				getID(conn, "StorageElement", "SEName", seNameFrom , true),
				personApi.getUserID(conn, dbsUser));

	}

	public void updateSEBlockRole(Connection conn, Writer out, String blockName, String seName, String role, Hashtable dbsUser) throws Exception {
		if(!role.equals("N") && !role.equals("Y")) 
			throw new DBSException("Invalid Role", "1098", "Role can have just these values Y or N. Given " + role);
			
		updateValue(conn, out, "SEBlock", 
				getMapID(conn, "SEBlock", "SEID", "BlockID", 
					getID(conn, "StorageElement", "SEName", seName , true), 
					getBlockID(conn, blockName, false, true),
					true),
				"Roles", 
				role, 
				personApi.getUserID(conn, dbsUser));
		insertTimeLog(conn, "updateSEBlockRole", "User called updateSEBlockRole", 
						"Chaged role to " + role,
						"The Role in Block " + blockName + " Storage Element " + seName + " is changed to  " + role,
						dbsUser);


	}

	public void updateBlock(Connection conn, Writer out,  String blockID, String lmbUserID) throws Exception {
		PreparedStatement ps = null;
                ResultSet rs = null;
		try {
			ps = DBSSql.listBlockValues(conn, blockID);
			rs =  ps.executeQuery();
			if(rs.next()) {
				updateBlock( conn, out, 
						blockID, 
						get(rs, "FILE_SIZE"), 
						get(rs, "NUMBER_OF_FILES"), 
						get(rs, "NUMBER_OF_EVENTS"),
						lmbUserID);
			} else throw new DBSException("Unavailable data", "1011", "No such Files : Block : " + blockID );
		} finally { 
                        if (rs != null) rs.close();
			if (ps != null) ps.close();
               	}
	}

	private void updateBlock(Connection conn, Writer out, String blockID, String blockSize, String numberOfFiles, String numberOfEvents, String lmbUserID) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = DBSSql.updateBlock(conn, blockID, blockSize, numberOfFiles, numberOfEvents, lmbUserID);
			ps.executeUpdate();
		} finally { 
			if (ps != null) ps.close();
               	}
	}

	//Get the details of a OPEN Block for certain procDSID, blockNamePattern (includes Tiers list), seVector
	//Returns null if NO Block is found matching the criteria
	//Return Vector with ID (Vector[0]) BlockSize (Vector[1]) and NumberOfFiles Vector[2].	
	public Vector getOpenBlockDetails(Connection conn, String procDSID, String blockNamePath, Hashtable block) 
	throws Exception

	{

                PreparedStatement ps = null; 
                ResultSet rs = null;

		Vector blockInfoVec = new Vector();

                Vector storageVector = DBSUtil.getVector(block, "storage_element");
                Vector seVector = new Vector();
                for (int j = 0; j < storageVector.size(); ++j)
                        seVector.add(get((Hashtable)storageVector.get(j), "storage_element_name"));

                try {
                        ps = DBSSql.getOpenBlockID(conn, procDSID, blockNamePath, seVector);
                        rs =  ps.executeQuery();
			
			if (!rs.next()) {
				return blockInfoVec;
			}
			blockInfoVec.add(get(rs, "ID"));
			blockInfoVec.add(Long.parseLong((String)get(rs, "BLOCKSIZE")));
			blockInfoVec.add(Long.parseLong((String)get(rs, "NUMBER_OF_FILES")));
                } finally {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                }
		
		return blockInfoVec;

	}

	public Boolean mustOpenNewBlock(Vector blockInfoVec)  throws Exception {

                DBSConfig config = DBSConfig.getInstance();

                long configuredBlkSize = config.getMaxBlockSize();
                long configuredNumFiles = config.getMaxBlockFiles();
                //DBSUtil.writeLog("*********************TEST BLOCK****************");  
                //DBSUtil.writeLog("configuredBlkSize: "+configuredBlkSize);
                //DBSUtil.writeLog("configuredNumFiles: "+configuredNumFiles);

                //int blockSize = Long.parseLong((String)blockInfoVec.get(1));
                //int numberOfFiles = Long.parseLong((String)blockInfoVec.get(2));
		long blockSize = (Long)(blockInfoVec.get(1));
		long numberOfFiles = (Long)(blockInfoVec.get(2));
                //DBSUtil.writeLog("blockSize: "+blockSize);
                //DBSUtil.writeLog("numberOfFiles: "+numberOfFiles);
                if (blockSize >= configuredBlkSize || numberOfFiles >= configuredNumFiles ) {
			//System.out.println("MUSt Open New Block");	
			return true;
		}
		return false;
	}


        public String dbsManagedBlockID (Connection conn, Writer out, String procDSID, 
						String blockNamePath, Hashtable block, Hashtable dbsUser) throws Exception {
                String id = "";

		Vector blockInfoVec = getOpenBlockDetails(conn, procDSID, blockNamePath, block);


                Vector storageVector = DBSUtil.getVector(block, "storage_element");
                Vector seVector = new Vector();
                for (int j = 0; j < storageVector.size(); ++j)
                        seVector.add(get((Hashtable)storageVector.get(j), "storage_element_name"));

		if(blockInfoVec.size() <= 0 ) {
			//Unable to find an Open Block, Create one.
			id = insertBlock(conn, out, procDSID, blockNamePath, block, seVector, dbsUser);
			return id;
                }

		id = (String)blockInfoVec.get(0);

		if (mustOpenNewBlock(blockInfoVec)) {
			closeBlock(conn, id, personApi.getUserID(conn, dbsUser));
                	insertTimeLog(conn, "CloseBlock", "Close Block Condition Sensed",
                                    "Block Closed", "Auto Closing Block",
                                     dbsUser);
                	id = insertBlock(conn, out, procDSID, blockNamePath, block, seVector, dbsUser);
		}

                return id;
        }


        public void openBlock(Connection conn, Writer out, String name, Hashtable dbsUser) throws Exception {

                PreparedStatement ps = null;
                try {
                        ps = DBSSql.openBlock(conn, getBlockID(conn, name, false, true), personApi.getUserID(conn, dbsUser));
                        ps.executeUpdate();
                } finally {
                        if (ps != null) ps.close();
                }

                insertTimeLog(conn, "OpenBlock", "Open Block Called By User",
                                                  "Block Open For Writing", "Block Name: "+name,
                                                   dbsUser);
        }

	public void closeBlock(Connection conn, Writer out, String name, Hashtable dbsUser) throws Exception {

		closeBlock(conn, getBlockID(conn, name, false, true), personApi.getUserID(conn, dbsUser));
                insertTimeLog(conn, "CloseBlock", "Close Block Called By User",
                                                  "Block Closed", "Block Name: "+name,
                                                   dbsUser);

	}

	private void closeBlock(Connection conn, String blockID, String lmbUserID) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = DBSSql.closeBlock(conn, blockID, lmbUserID);
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
                              throw new DBSException("Block Closed", "1024", "Block : " + name + " not open for further files");
                        }
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}

		return  id;
	}



	private void checkFilesChildern(Connection conn, Writer out, String blockName) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBSSql.listFilesChildern(conn, getBlockID(conn, blockName, false, true));
			rs =  ps.executeQuery();
			if(rs.next()) 
				throw new DBSException("Files cannot be Orphaned", "1091", "This Block " + blockName + " has files which have childern. Delete those files/datatset first " );
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}
	}
	
	public void deleteBlock(Connection conn, Writer out, String path, String blockName, Hashtable dbsUser, String clientVersion) throws Exception {
		checkPath(path);
		checkFilesChildern(conn, out, blockName);
		String lmbUserID = personApi.getUserID(conn, dbsUser);
		String cbUserID = lmbUserID;
		String creationDate = getTime(new Hashtable(), "creation_date", false);
		DBSApiTransferLogic transferApi = new DBSApiTransferLogic(this.data);
		StringWriter sout = new StringWriter();
		sout.write(DBSConstants.XML_HEADER); 
		//System.out.println("Block name is " + blockName);
		transferApi.listDatasetContents(conn, sout, path,
				blockName,
				"DOES_NOT_MATTER",
				clientVersion,
				true
				);
		sout.write(DBSConstants.XML_SUCCESS);
		sout.write(DBSConstants.XML_FOOTER);
		sout.flush();

		//System.out.println("XML is " + sout.toString());
		//Write the XML in Recycle Bin
		//(new DBSApiRecycleBin(this.data)).insertRecycleBin(conn, out, path, blockName, sout.toString(), lmbUserID, cbUserID, creationDate);
		(new DBSApiRecycleBin(this.data)).insertRecycleBin(conn, out, path, blockName, Base64.encodeBytes(sout.toString().getBytes()), lmbUserID, cbUserID, creationDate);
		//Delete the actual Block
		deleteName(conn, out, "Block", "Name", blockName);
		//Record history in TimeLog
		insertTimeLog(conn, "deleteBlock", "Delete Block called By User",
			"deleteBlock",
			"Block " + blockName + " moved to RecycleBin ",
			dbsUser);
	}

	public void undeleteBlock(Connection conn, Writer out, String path, String blockName, Hashtable dbsUser, String clientVersion) throws Exception {
		checkPath(path);
		checkBlock(blockName);
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			//Get the xml of the Blocks from the recycle bin
			ps =  DBSSql.listBlockContentsInRecycleBin(conn, path, blockName);
			rs =  ps.executeQuery();
			if(rs.next()) {
		       		undeleteBlock(conn, out, path, blockName, get(rs, "XML"), dbsUser, clientVersion);
			       	//undeleteBlock(conn, out, path, blockName, xml, dbsUser, clientVersion);
				//Delete the actual block from Recycle Bin
				(new DBSApiRecycleBin(this.data)).deleteRecycleBin(conn, out, path, blockName);
			}

		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}
	}

	public void undeleteBlock(Connection conn, Writer out, String path, String blockName, String xml, Hashtable dbsUser, String clientVersion) throws Exception {
			if(!isNull(xml)) xml = new String(Base64.decode(xml));
	
			(new DBSApiTransferLogic(this.data)).insertDatasetContents(conn, out,
				DBSApiParser.parseDatasetContents(xml), 
				dbsUser,
				false,
				clientVersion);

			//Record history in TimeLog
			insertTimeLog(conn, "undeleteBlock", "UNDelete Block called By User",
				"undeleteBlock",
				"Block: " + blockName + " moved from RecycleBin back to DBS",
				dbsUser);
	}


}