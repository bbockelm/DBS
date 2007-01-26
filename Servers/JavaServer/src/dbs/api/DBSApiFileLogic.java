/**
 $Revision: 1.15 $"
 $Id: DBSApiFileLogic.java,v 1.15 2007/01/24 17:08:57 sekhri Exp $"
 *
 */

package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.util.Hashtable;
import java.util.Vector;
import dbs.sql.DBSSql;
import dbs.util.DBSUtil;
import dbs.DBSException;

/**
* A class that has the core business logic of all the File APIs.  The signature for the API is internal to DBS and is not exposed to the clients. There is another class <code>dbs.api.DBSApi</code> that has an interface for the clients. All these low level APIs are invoked from <code>dbs.api.DBSApi</code>. This class inherits from DBSApiLogic class.
* @author sekhri
*/
public class DBSApiFileLogic extends DBSApiLogic {
		
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs.
	*/

	DBSApiPersonLogic personApi = null;
	DBSApiData data = null;
	public DBSApiFileLogic(DBSApiData data) {
		super(data);
		this.data = data;
		personApi = new DBSApiPersonLogic(data);
	}


	/**
	 * Lists all the files within a processed dataset or within a block from the database in a xml format. This method makes one sql query, execute it, fetch the results and packs and write it in xml format to the output stream. The query that it executes get generated by <code>dbs.DBSSql.listFiles</code> method. First it fetches the processed dataset ID from the database by calling a private <code>getProcessedDSID<code> method using the path provided in the parameter. It also fetches the block id from the database by calling a private method <code>getBlockID</code> If niether the processed dataset id nor the block id exists then it throws an exception. A sample XML that is written to the output stream is like <br>
	 * <code> <"file id='9' lfn='TEST_LFN' checksum='CHKSUM' size='200' queryable_meta_data='any' number_of_events='200' validation_status='1' type='EVD' status='VALID' block_name='/test/test#8a99a0' creation_date='2006-12-07 09:52:55.0' last_modification_date='2006-12-07 09:52:55.0' created_by='ANZARDN' last_modified_by='ANZARDN'"><"data_tier name='HIT'"/><"data_tier name='SIM'"/><"/file"></code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param path a dataset path in the format of /primary/tier/processed. This path is used to find the existing processed dataset id.
	 * @param blockName a block name in the format of /primary/processed#GUID. This block name is used to find the existing block id.
	 * @param patternLFN a parameter passed in from the client that can contain wild card characters for logical file name. This pattern is used to restrict the SQL query results by sustitution it in the WHERE clause.
	 * @param detail a parameter to determine whether the details of the files needs to be listed or not.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied patternLFN is invalid, the database connection is unavailable or processed dataset or block is not found.
	 */
	public void listFiles(Connection conn, Writer out, String path, String blockName, String patternLFN, String detail) throws Exception {

		//By default a file detail is not needed

		String procDSID = null;
		String blockID = null;

                String tierID = null;
		if(!isNull(path)) {
			procDSID = (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true);
                        tierID = getID(conn, "DataTier", "Name",  parseDSPath(path)[2], true);  
		}
		if(!isNull(blockName)) {
			blockID = (new DBSApiBlockLogic(this.data)).getBlockID(conn, blockName, false, true);
                        //FIXME: We need to make sure that we MUST have ONLY an OPEN Block for adding a file to !
                       
		}
		if(blockID == null && procDSID == null) {
			throw new DBSException("Missing data", "1005", "Null Fields. Expected either a Processed Dataset or a Block");
		}

		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listFiles(conn, procDSID, blockID, tierID, getPattern(patternLFN, "pattern_lfn"));
			rs =  ps.executeQuery();
			while(rs.next()) {
				String fileID = get(rs, "ID");
				String lfn = get(rs, "LFN");
				out.write(((String) "<file id='" + fileID +
					"' lfn='" + lfn +
					"' checksum='" + get(rs, "CHECKSUM") +
					"' size='" + get(rs, "FILESIZE") +
					"' queryable_meta_data='" + get(rs, "QUERYABLE_META_DATA") +
					"' number_of_events='" + get(rs, "NUMBER_OF_EVENTS") +
					"' validation_status='" + get(rs, "VALIDATION_STATUS") +
					"' type='" + get(rs, "TYPE") +
					"' status='" + get(rs, "STATUS") +
					"' block_name='" + get(rs, "BLOCK_NAME") +
					"' creation_date='" + getTime(rs, "CREATION_DATE") +
					"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
					"' created_by='" + get(rs, "CREATED_BY") +
					"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
					"'>\n"));
				if (!isNull(detail)) {
					this.data.globalFile = new Hashtable();
					this.data.globalFile.put(lfn, fileID);
					listFileParents(conn, out, lfn);
					listFileAlgorithms(conn, out, lfn);
					listFileTiers(conn, out, lfn);
					listFileBranches(conn, out, lfn);
					listFileLumis(conn, out, lfn);
				}
                		out.write(((String) "</file>\n"));
      
			}
		} finally { 
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}

	}

	/**
	 * Lists all the parents of the given file.  This method makes one sql query, execute it, fetch the results and packs and write it in xml format to the output stream. The query that it executes get generated by <code>dbs.DBSSql.listFileParents</code> method. It writes a list of logical file names on the output stream.  A sample XML that is written to the output stream is like <br>
	 * <code> <""/></code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param lfn the logical file name of the file whose parents needs to be listed.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied lfn is invalid, the database connection is unavailable or the file is not found.
	 */
	 public void listFileParents(Connection conn, Writer out, String lfn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listFileParents(conn, getFileID(conn, lfn, true));
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<file_parent id='" +  get(rs, "ID") +
					"' lfn='" + get(rs, "LFN") +
					"' checksum='" + get(rs, "CHECKSUM") +
					"' size='" + get(rs, "FILESIZE") +
					"' queryable_meta_data='" + get(rs, "QUERYABLE_META_DATA") +
					"' number_of_events='" + get(rs, "NUMBER_OF_EVENTS") +
					"' validation_status='" + get(rs, "VALIDATION_STATUS") +
					"' type='" + get(rs, "TYPE") +
					"' status='" + get(rs, "STATUS") +
					"' block_name='" + get(rs, "BLOCK_NAME") +
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
	 * Lists all the algorithm configs of the given file.  This method makes one sql query, execute it, fetch the results and packs and write it in xml format to the output stream. The query that it executes get generated by <code>dbs.DBSSql.listFileAlgorithms</code> method. It writes a list of algorithms on the output stream.  A sample XML that is written to the output stream is like <br>
	 * <code> <""/></code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param lfn the logical file name of the file whose algorithms needs to be listed.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied lfn is invalid, the database connection is unavailable or the file is not found.
	 */
	 public void listFileAlgorithms(Connection conn, Writer out, String lfn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listFileAlgorithms(conn, getFileID(conn, lfn, true));
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<file_algorithm id='" + get(rs, "ID") + 
					"' app_version='" + get(rs, "APP_VERSION") +
					"' app_family_name='" + get(rs, "APP_FAMILY_NAME") +
					"' app_executable_name='" + get(rs, "APP_EXECUTABLE_NAME") +
					"' ps_name='" + get(rs, "PS_NAME") +
					"' ps_hash='" + get(rs, "PS_HASH") +
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
	 * Lists all the data tiers of the given file.  This method makes one sql query, execute it, fetch the results and packs and write it in xml format to the output stream. The query that it executes get generated by <code>dbs.DBSSql.listFileTiers</code> method. It writes a list of date tiers on the output stream.  A sample XML that is written to the output stream is like <br>
	 * <code> <""/></code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param lfn the logical file name of the file whose data tiers needs to be listed.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied lfn is invalid, the database connection is unavailable or the file is not found.
	 */
	 public void listFileTiers(Connection conn, Writer out, String lfn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listFileTiers(conn, getFileID(conn, lfn, true));
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<file_data_tier id='" + get(rs, "ID") +
					"' name='" + get(rs, "NAME") +
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


	public void listFileBranches(Connection conn, Writer out, String lfn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listFileBranches(conn, getFileID(conn, lfn, true));
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<file_branch id='" + get(rs, "ID") +
							"' name='" + get(rs, "NAME") +
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
	 * Lists all the lumi sections of the given file.  This method makes one sql query, execute it, fetch the results and packs and write it in xml format to the output stream. The query that it executes get generated by <code>dbs.DBSSql.listFileLumis</code> method. It writes a list of lumi sections on the output stream.  A sample XML that is written to the output stream is like <br>
	 * <code> <""/></code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param lfn the logical file name of the file whose lumi sections needs to be listed.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied lfn is invalid, the database connection is unavailable or the file is not found.
	 */
	 public void listFileLumis(Connection conn, Writer out, String lfn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listFileLumis(conn, getFileID(conn, lfn, true));
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<file_lumi_section id='" +  get(rs, "ID") +
					"' lumi_section_number='" + get(rs, "LUMI_SECTION_NUMBER") +
					"' run_number='" + get(rs, "RUN_NUMBER") +
					"' start_event_number='" + get(rs, "START_EVENT_NUMBER") +
					"' end_event_number='" + get(rs, "END_EVENT_NUMBER") +
					"' lumi_start_time='" + get(rs, "LUMI_START_TIME") +
					"' lumi_end_time='" + get(rs, "LUMI_END_TIME") +
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
	 * Insert a list of Files whose parameters are provided in the passed files <code>java.util.Vector</code>. This vector contains a list of hashtable and is generated externally and filled in with the file parameters by parsing the xml input provided by the client. This method inserts entries into more than one table associated with File table. The the main query that it executes to insert in File table, get generated by <code>dbs.DBSSql.insertFile</code> method.<br> 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it fetches the processed dataset id and the block id by calling a private getProcessedDSID method and private getBlockID method with parameters path and blockName. The log of insert file bahaves in a way such that all the files to be inserted per this method call, should belong to the same processed dataset and same block <br>
	 * Then it iterates through all the files Hashtable provided in the files vector and inserts one file at a time. <br>
	 * Then in the same loop it fetches all the algorithm list of the file that just got inserted and inserts a new row in FileAlgoMap table by calling a generic private insertMap method. <br>
	 * Then in the same loop it fetches all the tier list of the file that just got inserted and inserts a new row in FileTier table by calling a generic private insertMap method. <br>
	 * Then in the same loop it fetches all the parent list of the file that just got inserted and inserts a new row in FileParentage table by calling a generic private insertMap method. <br>
	 * Then in the same loop it fetches all the lumi section list of the file that just got inserted and inserts a new row in FileLumi table by calling a generic private insertMap method. Before it insert in to this FileLumi table , it first insert the LumiSection by calling insertLumiSection method<br>
	 * Finally it updates the block information with correct number of files and size in bytes by calling the updateBlock method.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param files a <code>java.util.Vector</code> that contains a list of <code>java.util.Hastable</code>  that contain all the necessary key value pairs required for inserting a new file. The keys along with its values that it may or may not contain are <br>
	 * <code>lfn, checksum, number_of_events, size, queryable_meta_data, file_status, type, validation_status, lumi_section, data_tier, parent, algorithm, created_by, creation_date </code> <br>
	 * Further the keys <code>lumi_section, data_tier, parent, algorithm </code> are itself vector of Hashtable. <br>
	 * The key that <code>parent </code> hashtable may or may not contain is <code>lfn</code> <br>
	 * The key that <code>data_tier </code> hashtable may or may not contain is <code>name</code> <br>
	 * The keys that <code>lumi_section </code> hashtable may or may not contain are <br>
	 * <code>lumi_section_number, run_number, start_event_number, end_event_number, lumi_start_time, lumi_end_time</code> <br>
	 * The keys that <code>algorithm </code> hashtable may or may not contain are <br> 
	 * <code>app_version, app_family_name, app_executable_name, ps_name</code> <br>
	  * @param path a dataset path in the format of /primary/tier/processed. This path is used to find the existing processed dataset id.
	 * @param blockName a block name in the format of /primary/processed#GUID. This block name is used to find the existing block id.
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable or a duplicate entry is being added.
	 */
        public void insertFiles(Connection conn, Writer out, String path, Hashtable block, Vector files, Hashtable dbsUser) throws Exception { 
	//public void insertFiles(Connection conn, Writer out, String path, String blockName, Vector files, Hashtable dbsUser) throws Exception {
                //Get the Block name (if provided)
                String blockName = DBSUtil.get(block, "block_name");
		
		//Get the User ID from USERDN
		String lmbUserID = personApi.getUserID(conn, dbsUser);

		/*//Check if all the path is in the files are same.
		if(files.size() > 0) {
			String path = get((Hashtable)files.get(0), "path");
			for (int i = 1; i < files.size() ; ++i) {
				String tmpPath = get((Hashtable)files.get(i), "path");
				if(!tmpPath.equals(path)) {
					throw new DBSException("Bad Data", "300", "Different Processed Datatsets. All files in the list should belong to same processed datatset. Dataset1 " + path + " Datatset2 " + tmpPath);
				}
			
			}
		}*/
		String procDSID = (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true);
                String blockID = null;

                //If user INSIST to provide a BlockName
                if ( ! isNull(blockName) && !(blockName.equals("")) ) {
		    blockID = (new DBSApiBlockLogic(this.data)).getBlockID(conn, blockName, true, true);
                    //FIXME: We must need to verify that Block is OpenForWriting
                    
                }
		//These tables are used to store the types and staus fileds along with thier id
		//If the id can be fetched from here then we do not have to fietch it from database again and again
		boolean newFileInserted = false;
		Hashtable statusTable = new Hashtable();
		Hashtable typeTable = new Hashtable();
		Hashtable valStatusTable = new Hashtable();
		String statusID = "";
		String typeID = "";
		String valStatusID = "";
		for (int i = 0; i < files.size() ; ++i) {
			Hashtable file = (Hashtable)files.get(i);
		
			String fileID = "";
			//String path = get(file, "path");
			//String blockName = get(file, "block_name");
			String lfn = get(file, "lfn", true);
			String fileStatus = get(file, "file_status", false);
			String type = get(file, "type", false);
			String valStatus = get(file, "validation_status", false);
			String cbUserID = personApi.getUserID(conn, get(file, "created_by", false), dbsUser );
			String creationDate = getTime(file, "creation_date", false);

			Vector lumiVector = DBSUtil.getVector(file,"lumi_section");
			Vector tierVector = DBSUtil.getVector(file,"data_tier");
			Vector parentVector = DBSUtil.getVector(file,"parent");
			Vector algoVector = DBSUtil.getVector(file,"algorithm");
			Vector branchVector = DBSUtil.getVector(file,"branch");
		
			//Set defaults Values
			if (isNull(fileStatus)) fileStatus = "VALID";
			if (isNull(type)) type = "EVD";
			if (isNull(valStatus)) valStatus = "VALID";
			
			//Insert a File status if it does not exists
			//insertName(conn, out, "Status", "Status", fileStatus , lmbUserID);

			//Insert a File Validation status if it does not exists
			//insertName(conn, out, "Status", "Status", valStatus , lmbUserID);

			//Insert a File Type if it does not exists
			//insertName(conn, out, "Type", "Type", type , lmbUserID);
			
			//Insert a File by fetching the fileStatus, type and validationStatus
			if( (fileID = getFileID(conn, lfn, false)) == null ) {
				newFileInserted = true;
				//TODO Exception of null status or type should be catched and parsed and 
				//a proper message should be returned back to the user. Different Database can have different error message YUK
				//Status should be defaulted to something in the database itself. A wrong status may insert a dafult value.
				//User will never know about this YUK
				if( isNull(statusID = get(statusTable, fileStatus)) ) {
					statusID = getID(conn, "FileStatus", "Status", fileStatus, true);
					statusTable.put(fileStatus, statusID);
				}
				if( isNull(typeID = get(typeTable, type)) ) {
					typeID = getID(conn, "FileType", "Type", type, true);
					typeTable.put(type, typeID);
				}
				if( isNull(valStatusID = get(valStatusTable, valStatus)) ) {
					valStatusID = getID(conn, "FileStatus", "Status", valStatus, true);
					valStatusTable.put(valStatus, valStatusID);
				}
                                if( isNull(blockID) ) {
                                        //Let DBS choose the Block, And that could be slow
                                        //We need to have Storage Element List here !!!!!!!
                                        blockID = (new DBSApiBlockLogic(this.data)).dbsManagedBlockID(conn, out, procDSID, path, block, dbsUser);
                                }
	
				PreparedStatement ps = null;
				try {
					ps = DBSSql.insertFile(conn, 
							procDSID, 
							blockID, 
							lfn, 
							get(file, "checksum", false), 
							get(file, "number_of_events", false), 
							get(file, "size", false), 
							statusID,
							typeID,
							valStatusID,
							get(file, "queryable_meta_data", false), 
							cbUserID, lmbUserID, creationDate);
					ps.execute();
				} finally { 
					if (ps != null) ps.close();
				}

			} else {
				//Write waring message that file exists already
				writeWarning(out, "Already Exists", "1020", "File " + lfn + " Already Exists");
			}

			//if(isNull(fileID)) fileID = getFileID(conn, lfn);
			//Fetch the File ID that was just inseted to be used for subsequent insert of other tables only if it is needed.
			//FileID is needed if any of the other table information is provided i.e the vector size is > 0
			if(algoVector.size() > 0 || tierVector.size() > 0 || parentVector.size() > 0 || lumiVector.size() > 0) 
				if(isNull(fileID)) fileID = getFileID(conn, lfn, true);
				//fileID = getFileID(conn, lfn, true);

			//Insert FileAlgo table by fetching application ID. 
			//Use get with 2 params so that it does not do type checking, since it will be done in getID call.
			for (int j = 0; j < algoVector.size(); ++j) {
				Hashtable hashTable = (Hashtable)algoVector.get(j);
				insertMap(conn, out, "FileAlgo", "Fileid", "Algorithm", 
						fileID, 
						(new DBSApiAlgoLogic(this.data)).getAlgorithmID(conn, get(hashTable, "app_version"), 
								get(hashTable, "app_family_name"), 
								get(hashTable, "app_executable_name"),
								get(hashTable, "ps_hash"),
							       	true), 
						cbUserID, lmbUserID, creationDate);
			}

			//Insert FileTier table by fetching data tier ID
			for (int j = 0; j < tierVector.size(); ++j) {
				insertMap(conn, out,	"FileTier", "Fileid", "DataTier", 
					fileID, 
					getID(conn, "DataTier", "Name", 
						get((Hashtable)tierVector.get(j), "name") , 
						true), 
					cbUserID, lmbUserID, creationDate);
			}
			
			//Insert FileParentage table by fetching parent File ID
			for (int j = 0; j < parentVector.size(); ++j) {
				insertMap(conn, out, "FileParentage", "ThisFile", "itsParent", 
						fileID, 
						getFileID(conn, get((Hashtable)parentVector.get(j), "lfn"), true),
						cbUserID, lmbUserID, creationDate);
			}
			//TODO Discussion about Lumi section is needed
			//Insert FileLumi table by first inserting and then fetching Lumi Section ID
			for (int j = 0; j < lumiVector.size(); ++j) {
				Hashtable hashTable = (Hashtable)lumiVector.get(j);
				//Insert A lumi Section if it does not exists
				insertLumiSection(conn, out, hashTable, cbUserID, lmbUserID, creationDate);
 
                                System.out.println("BACK..........");
				insertMap(conn, out, "FileLumi", "Fileid", "Lumi", 
						fileID, 
						getID(conn, "LumiSection", "LumiSectionNumber", get(hashTable, "lumi_section_number") , true), 
						cbUserID, lmbUserID, creationDate);
			}
			//Insert Branch and then FileBranch (Map)
			for (int j = 0; j < branchVector.size(); ++j) {
				//insert Branch, if not already there
				String branchName = get((Hashtable)branchVector.get(j), "name");
				insertName(conn, out, "Branch", "Name", branchName, cbUserID, lmbUserID, creationDate);
				//insert File-Branch Map now.
				insertMap(conn, out,  "FileBranch", "Fileid", "Branch",
						fileID,
						getID(conn, "Branch", "Name", branchName, true),
						cbUserID, lmbUserID, creationDate);
			}

			if ( i%100 == 0) conn.commit(); //For Every 100 files commit the changes
		}//For loop
		//Update Block numberOfFiles and Size
		if (newFileInserted) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.updateBlock(conn, blockID);
				ps.executeUpdate();
			} finally { 
				if (ps != null) ps.close();
                	}
		}

	}

	/**
	 * Insert a data tier in file. 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it inserts entry into just one table FileTier by calling a generic private <code>insertMap</code> method. It first fetches the file id by calling a generic private getID method.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param table a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single file. The keys along with its values that it may or may not contain are <br>
	 * <code>lfn, created_by, creation_date </code> <br>
	 * If this lfn is not provided or the file id could not be found then an exception is thrown.
	 * @param tierName a data tier name which is assumed to be already present in the database.
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable or the file is not found.
	 */
	public void insertTierInFile(Connection conn, Writer out, Hashtable table, String tierName, Hashtable dbsUser) throws Exception {
		insertMap(conn, out, "FileTier", "Fileid", "DataTier", 
				getFileID(conn, get(table, "lfn", true), true), 
				getID(conn, "DataTier", "Name", tierName , true), 
				personApi.getUserID(conn, get(table, "created_by", false), dbsUser ),
				personApi.getUserID(conn, dbsUser),
				getTime(table, "creation_date", false));

	}
	
	/**
	 * Insert a parent in a file. 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it inserts entry into just one table FileParentage by calling a generic private <code>insertMap</code> method. It first fetches the file id by calling a generic private getID method.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param table a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single file. The keys along with its values that it may or may not contain are <br>
	 * <code>lfn, created_by, creation_date </code> <br>
	 * If this lfn is not provided or the file id could not be found then an exception is thrown.
	 * @param parentLFN a logical file name of the parent file.
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable or the file is not found.
	 */
	public void insertParentInFile(Connection conn, Writer out, Hashtable table, String parentLFN, Hashtable dbsUser) throws Exception {
		insertMap(conn, out, "FileParentage", "ThisFile", "itsParent", 
				getFileID(conn, get(table, "lfn", true), true),
				getFileID(conn, parentLFN, true),
				personApi.getUserID(conn, get(table, "created_by", false), dbsUser ),
				personApi.getUserID(conn, dbsUser),
				getTime(table, "creation_date", false));
	}


	/**
	 * Insert a algorithm in a file. 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it inserts entry into just one table FileAlgoMap by calling a generic private <code>insertMap</code> method. It first fetches the file id by calling a generic private getID method.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param table a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single file. The keys along with its values that it may or may not contain are <br>
	 * <code>lfn, created_by, creation_date </code> <br>
	 * If this lfn is not provided or the file id could not be found then an exception is thrown.
	 * @param algo a <code>java.util.Hashtable</code> that conatin the parameter that defines an algorithm. The keys that <code>algo </code> hashtable may or may not contain are <br> 
	 * <code>app_version, app_family_name, app_executable_name, ps_name</code> <br>
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable or the file is not found.
	 */
	public void insertAlgoInFile(Connection conn, Writer out, Hashtable table, Hashtable algo, Hashtable dbsUser) throws Exception {
		insertMap(conn, out, "FileAlgo", "Fileid", "Algorithm", 
				getFileID(conn, get(table, "lfn", true), true), 
				(new DBSApiAlgoLogic(this.data)).getAlgorithmID(conn, get(algo, "app_version"), 
						get(algo, "app_family_name"), 
						get(algo, "app_executable_name"),
						//get(algo, "ps_name"), 
						get(algo, "ps_hash"), 
						true), 
				personApi.getUserID(conn, get(table, "created_by", false), dbsUser ),
				personApi.getUserID(conn, dbsUser),
				getTime(table, "creation_date", false));
	}

	/**
	 * Insert a lumi section in a file. 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it inserts entry into just one table FileLumi by calling a generic private <code>insertMap</code> method. It first fetches the file id by calling a generic private getID method.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param table a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single file. The keys along with its values that it may or may not contain are <br>
	 * <code>lfn, created_by, creation_date </code> <br>
	 * If this lfn is not provided or the file id could not be found then an exception is thrown.
	 * @param lsNumber a lumi section number that uniquely identifies a lumi section.
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable or the file is not found.
	 */
	public void insertLumiInFile(Connection conn, Writer out, Hashtable table, String lsNumber, Hashtable dbsUser) throws Exception {
		insertMap(conn, out, "FileLumi", "Fileid", "Lumi", 
				getFileID(conn, get(table, "lfn", true), true), 
				getID(conn, "LumiSection", "LumiSectionNumber", lsNumber, true), 
				personApi.getUserID(conn, get(table, "created_by", false), dbsUser ),
				personApi.getUserID(conn, dbsUser),
				getTime(table, "creation_date", false));
	}



	private String getFileID(Connection conn, String lfn, boolean excep) throws Exception {
		String id = "";
		if(!isNull( id = get(this.data.globalFile, lfn) )) {
			return id;
		}
		if( !isNull(id = getID(conn, "Files", "LogicalFileName", lfn, excep)) ) {
			this.data.globalFile = new Hashtable();//Just store one file id only
			this.data.globalFile.put(lfn, id);
		}
		return id;
	}
	
	
}
