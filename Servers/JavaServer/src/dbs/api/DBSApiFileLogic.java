/**
 $Revision: 1.34 $"
 $Id: DBSApiFileLogic.java,v 1.34 2007/03/12 20:59:51 sekhri Exp $"
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


	//This api call WILL only take into consideration the PATH parameter
        private void listFiles(Connection conn, Writer out, String path, String detail) throws Exception {

		String procDSID = (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true);

		PreparedStatement ps = null;
                ResultSet rs =  null;

                try {
                        ps = DBSSql.listFiles(conn, procDSID, path);
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
                                        //listFileParents(conn, out, lfn); 
                                        listFileProvenence(conn, out, lfn, true);//Parents
                                        listFileProvenence(conn, out, lfn, false);//Children
                                        listFileAlgorithms(conn, out, lfn);
                                        listFileTiers(conn, out, lfn);
                                        listFileBranches(conn, out, lfn);
                                        listFileLumis(conn, out, lfn);
                                        listFileRuns(conn, out, lfn);
                                }
                                out.write(((String) "</file>\n"));
                        }
                } finally {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                }

        }


	public void listFiles(Connection conn, Writer out, String path, 
					String primary, String proc, String dataTierList, String aDSName, 
					String blockName, String patternLFN, String detail) throws Exception {

		//By default a file detail is not needed


		//if path is given we will only regard it to be sufficient criteria for listing files.
		if (!isNull(path)) {  
			listFiles(conn, out, path, detail);
			return;
		}
				

		//User asks to search on Other parameters then.

		String procDSID = null;
		String aDSID = null;
		String blockID = null;

                //String tierID = null;
		Vector tierIDList = new Vector();

		//Get the procDSID and TierIDs if Tiers are given
		if(!isNull(primary) && !isNull(proc) ) {
			procDSID = (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, primary, proc, true);
		}

		//dataTierList is a "-" separated list of ALLL tiers that user wants to look for
		String[] tierList = parseTier(dataTierList);
		for (int j = 0; j < tierList.length; ++j) {
				tierIDList.add(getID(conn, "DataTier", "Name", tierList[j] , true));
		}

		//Search can be based on Analysis Dataset
		if (!isNull(aDSName)) {
			aDSID = getID(conn, "AnalysisDataset", "Name", aDSName, true);
		}

		//Search can be based on Block Name
		if(!isNull(blockName)) {
			blockID = (new DBSApiBlockLogic(this.data)).getBlockID(conn, blockName, false, true);
		}

		//Search can be based on LFN pattern
                String patternlfn= getPattern(patternLFN, "pattern_lfn");

		if(isNull(blockID) && isNull(procDSID) && isNull(aDSID) && isNull(patternlfn)  ) {
			throw new DBSException("Missing data", "1005", 
					"Null Fields. Expected either a (Primary, Processed) Dataset, Analysis Dataset, Block or LFN pattern");
		}

		if(!isNull(procDSID) && !isNull(aDSName)) {
			writeWarning(out, "Both Processed dataset and Analysis dataset given", "1090", 
						"If Analysis dataset is given then there is no need for a processed dataset. " + 
						"While fetcing the files processed dataset will be ignored." +
						" Given Processed Dataset is  " +proc+ " and given Analysis Dataset is " + aDSName);
			procDSID = "";
		}

		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listFiles(conn, procDSID, aDSID, blockID, tierIDList, patternlfn);
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
					//listFileParents(conn, out, lfn);
					listFileProvenence(conn, out, lfn, true);//Parents
					listFileProvenence(conn, out, lfn, false);//Children
					listFileAlgorithms(conn, out, lfn);
					listFileTiers(conn, out, lfn);
					listFileBranches(conn, out, lfn);
					listFileLumis(conn, out, lfn);
					listFileRuns(conn, out, lfn);

				}
                		out.write(((String) "</file>\n"));
      
			}
		} finally { 
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}

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
	//public void listFiles(Connection conn, Writer out, String path, String blockName, String patternLFN, String detail) throws Exception {
	public void listFilesOLD(Connection conn, Writer out, String path, String aDSName, String blockName, String patternLFN, String detail) throws Exception {

		//By default a file detail is not needed

		String procDSID = null;
		String aDSID = null;
		String blockID = null;

                //String tierID = null;
		Vector tierIDList = new Vector();
		if(!isNull(path)) {
			procDSID = (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true);
			String[] tierList = parseTier(parseDSPath(path)[3]);
			for (int j = 0; j < tierList.length; ++j) {
				tierIDList.add(getID(conn, "DataTier", "Name", tierList[j] , true));
			}
			//tierID = getID(conn, "DataTier", "Name",  parseDSPath(path)[3], true);  
		}
		if (!isNull(aDSName)) {
			aDSID = getID(conn, "AnalysisDataset", "Name", aDSName, true);
		}
		if(!isNull(blockName)) {
			blockID = (new DBSApiBlockLogic(this.data)).getBlockID(conn, blockName, false, true);
                        //FIXME: We need to make sure that we MUST have ONLY an OPEN Block for adding a file to !
                       
		}

                String patternlfn= getPattern(patternLFN, "pattern_lfn");

		if(isNull(blockID) && isNull(procDSID) && isNull(aDSID) && isNull(patternlfn)  ) {
			throw new DBSException("Missing data", "1005", "Null Fields. Expected either a Processed Dataset, Analysis Dataset, Block or LFN pattern");
		}

		if(!isNull(path) && !isNull(aDSName)) {
			writeWarning(out, "Both Processed dataset and Analysis dataset given", "1090", "If Analysis dataset is given then there is no need for a processed dataset. While fetcing the files processed dataset will be ignored. Given Processed Dataset is  " + path + " and given analysis dataset is " + aDSName);
			procDSID = "";
		}

		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			//ps = DBSSql.listFiles(conn, procDSID, blockID, tierID, getPattern(patternLFN, "pattern_lfn"));
			//ps = DBSSql.listFiles(conn, procDSID, aDSID, blockID, tierID, patternlfn);
			ps = DBSSql.listFiles(conn, procDSID, aDSID, blockID, tierIDList, patternlfn);
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
					//listFileParents(conn, out, lfn);
					listFileProvenence(conn, out, lfn, true);//Parents
					listFileProvenence(conn, out, lfn, false);//Children
					listFileAlgorithms(conn, out, lfn);
					listFileTiers(conn, out, lfn);
					listFileBranches(conn, out, lfn);
					listFileLumis(conn, out, lfn);
					listFileRuns(conn, out, lfn);

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
	 * @param parentOrChild a logical flag to determine weather parent or child is asked for. True - is for parent and False is for child
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied lfn is invalid, the database connection is unavailable or the file is not found.
	 */
	 //public void listFileParents(Connection conn, Writer out, String lfn) throws Exception {
	 public void listFileProvenence(Connection conn, Writer out, String lfn, boolean parentOrChild) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			//ps = DBSSql.listFileParents(conn, getFileID(conn, lfn, true));
			ps = DBSSql.listFileProvenence(conn, getFileID(conn, lfn, true), parentOrChild);
			rs =  ps.executeQuery();
			String tag = "";
			if(parentOrChild) tag = "file_parent";
			else tag = "file_child";
			while(rs.next()) {
				out.write(((String) "<" + tag + " id='" +  get(rs, "ID") +
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

         public void listFileRuns(Connection conn, Writer out, String lfn) throws Exception {
                PreparedStatement ps = null;
                ResultSet rs =  null;
                try {
                        ps = DBSSql.listFileRuns(conn, getFileID(conn, lfn, true));
                        rs =  ps.executeQuery();
                        while(rs.next()) {
                                out.write(((String) "<file_run id='" +  get(rs, "ID") +
                                        "' run_number='" + get(rs, "RUN_NUMBER") +
					"' number_of_events='" + get(rs, "NUMBER_OF_EVENTS") +
                                        "' number_of_lumi_sections='" + get(rs, "NUMBER_OF_LUMI_SECTIONS") +
                                        "' total_luminosity='" + get(rs, "TOTAL_LUMINOSITY") +
                                        "' store_number='" + get(rs, "STRORE_NUMBER") +
                                        "' start_of_run='" + get(rs, "START_OF_RUN") +
                                        "' end_of_run='" + get(rs, "END_OF_RUN") +
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

		DBSApiProcDSLogic procDSApiObj = new DBSApiProcDSLogic(this.data);
		String procDSID = procDSApiObj.getProcessedDSID(conn, path, true);
                String[] tmpPath = path.split("/");
                String notierpath = "/" + tmpPath[1] + "/" + tmpPath[2];

		//List Tiers from ProcDS
		Vector pathTierVec = procDSApiObj.getProcDSTierVec(conn, procDSID);
                /**String[] pathTiers = parseTier(tmpPath[3]);
                for (int j=0; j != pathTiers.length ; ++j)
                                pathTierVec.add(pathTiers[j]);**/

		Vector firstFileTiers = new Vector();
		//File Tiers must be within ProcDS Tiers
		for (int i = 0; i != files.size() ; ++i) {
			Hashtable file = (Hashtable)files.get(i);
                        Vector tierVector = DBSUtil.getVector(file,"file_data_tier");
                        Vector tierVec = new Vector();

                        for (int j=0; j != tierVector.size() ; ++j) {
                                tierVec.add(((String) get((Hashtable)tierVector.get(j), "name")).toUpperCase());
				//System.out.println("insertFiles: tierVector.get(j):"+get((Hashtable)tierVector.get(j), "name"));
			}

                        if ( ! pathTierVec.containsAll(tierVec) ) {
                               throw new DBSException("Tier Mismatch", "1037",
                                                        "Provided Tier(s) combinition " + tierVec.toString() +
                                                        " is not present in dataset "+path + " Path contains " +
                                                        pathTierVec.toString());

                        }

			if (i > 0) 
				if (firstFileTiers.size() != tierVec.size() || 
						(! firstFileTiers.containsAll(tierVec)) ) {
					throw new DBSException("Tier Mismatch", "1038",
                                                        "All files must belong to same path (Set of tiers) " + 
							"given tiers in this file are "+ tierVec.toString() +
							" and in other file within this call are "+firstFileTiers.toString() );
				}
			if (i == 0) firstFileTiers.addAll(tierVec);
		}

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

		//These tables are used to store the types and staus fileds along with thier id
		//If the id can be fetched from here then we do not have to fietch it from database again and again
		boolean newFileInserted = false;
		Hashtable statusTable = new Hashtable();
		Hashtable typeTable = new Hashtable();
		Hashtable valStatusTable = new Hashtable();
		String statusID = "";
		String typeID = "";
		String valStatusID = "";
				
		String orderedTiers = "";

		Vector blockInfoVec = new Vector();
		Boolean dbsBlockManagment = false;
		String correctedPath = null;


                String blockID = null;
                //If user INSIST to provide a BlockName
                if ( ! isNull(blockName) && !(blockName.equals("")) ) {
                    blockID = (new DBSApiBlockLogic(this.data)).getBlockID(conn, blockName, true, true);
                    //FIXME: We must need to verify that Block is OpenForWriting

                }
                //Do the DBS Block management
                else {
                        dbsBlockManagment = true;
                }

		for (int i = 0; i < files.size() ; ++i) {
			Hashtable file = (Hashtable)files.get(i);
		
			String fileID = "";
			//String path = get(file, "path");
			//String blockName = get(file, "block_name");
			String lfn = get(file, "lfn", true);
			String fileStatus = get(file, "file_status", false);
			String type = get(file, "type", false);
			String valStatus = get(file, "validation_status", false);
			String cbUserID = personApi.getUserID(conn, get(file, "created_by"), dbsUser );
			String creationDate = getTime(file, "creation_date", false);

			Vector lumiVector = DBSUtil.getVector(file,"file_lumi_section");
			Vector tierVector = DBSUtil.getVector(file,"file_data_tier");
			

			//Save the Tier List ONCE, if User wants DBS to manage Blocks
			if ( i == 0 && dbsBlockManagment ) {  
				Vector tierVec = new Vector();
				for (int j=0; j != tierVector.size() ; ++j)
					tierVec.add((String) get((Hashtable)tierVector.get(j), "name"));
				//Make the orderedTiers
                        	orderedTiers = makeOrderedTierList(conn, tierVec);
                                correctedPath = notierpath + "/" + orderedTiers;
			}

			Vector parentVector = DBSUtil.getVector(file,"file_parent");
			Vector childVector = DBSUtil.getVector(file,"file_child");
			Vector algoVector = DBSUtil.getVector(file,"file_algorithm");
			Vector branchVector = DBSUtil.getVector(file,"file_branch");
		
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
					valStatusID = getID(conn, "FileValidStatus", "Status", valStatus, true);
					valStatusTable.put(valStatus, valStatusID);
				}

                                if( dbsBlockManagment ) {                       // && i % 10 ) {  //Every 10th file ???
					//Either this is first time, OR we have reached the BlockSize/NumberOfFiles limit

					if (blockInfoVec.size() <= 0 || (new DBSApiBlockLogic(this.data)).mustOpenNewBlock(blockInfoVec) ) {
		                                        blockID = (new DBSApiBlockLogic(this.data)).dbsManagedBlockID(conn, out, 
												procDSID, correctedPath, block, dbsUser);

							//Grab this blocks details
							//recycle vector	
							blockInfoVec.removeAllElements();
							blockInfoVec.addAll((new DBSApiBlockLogic(this.data)).getOpenBlockDetails(conn, 
												procDSID, correctedPath, block));
					}
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

					//Update the Block stats in case it is dbsManaged Block
					if ( blockInfoVec.size() > 0 ) {
						blockInfoVec.set(1, (Integer)blockInfoVec.get(1) + 
									Integer.parseInt((String)get(file, "size", false)));
						blockInfoVec.set(2, (Integer)blockInfoVec.get(2) + 1 );
					}
 
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
		                String psHash = get(hashTable, "ps_hash", false);
		
                		if (isNull (psHash) ) {
              		          psHash =  "NO_PSET_HASH";
                		}

				insertMap(conn, out, "FileAlgo", "Fileid", "Algorithm", 
						fileID, 
						(new DBSApiAlgoLogic(this.data)).getAlgorithmID(conn, get(hashTable, "app_version"), 
								get(hashTable, "app_family_name"), 
								get(hashTable, "app_executable_name"),
								psHash,
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

			//Insert FileParentage for all the child of give by the client. Used during Merge operation
			for (int j = 0; j < childVector.size(); ++j) {
				insertMap(conn, out, "FileParentage", "ThisFile", "itsParent", 
						getFileID(conn, get((Hashtable)childVector.get(j), "lfn"), true),
						fileID, 
						cbUserID, lmbUserID, creationDate);
			}

			//TODO Discussion about Lumi section is needed
			//Insert FileLumi table by first inserting and then fetching Lumi Section ID
			for (int j = 0; j < lumiVector.size(); ++j) {
				Hashtable hashTable = (Hashtable)lumiVector.get(j);
				//Insert A lumi Section if it does not exists
				//Only when User provides a lumi section
				//There can be cases when NO lumi Section number is give (Run only)
				String lsNumber = get(hashTable, "lumi_section_number", false);
				if (!isNull(lsNumber)) {
					insertLumiSection(conn, out, hashTable, cbUserID, lmbUserID, creationDate);
				}
				if (!isNull(lsNumber)) {

					insertMap(conn, out, "FileRunLumi", "Fileid", "Lumi", "Run",
						fileID, 
						getID(conn, "LumiSection", "LumiSectionNumber", lsNumber , true), 
						getID(conn, "Runs", "RunNumber",  get(hashTable, "run_number", true), true),
						cbUserID, lmbUserID, creationDate);
				}
				//Just add Run-Fileid map
				else {
					insertMap(conn, out, "FileRunLumi", "Fileid", "Run",
						fileID, 
                                                getID(conn, "Runs", "RunNumber",  get(hashTable, "run_number", true), true),
						cbUserID, lmbUserID, creationDate);
				}

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
				getID(conn, "DataTier", "Name", tierName.toUpperCase() , true), 
				personApi.getUserID(conn, get(table, "created_by"), dbsUser ),
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
				personApi.getUserID(conn, get(table, "created_by"), dbsUser ),
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
		//String psHash = get(algo, "ps_hash");
		//if (isNull (psHash) ) {
		//	psHash =  "NO_PSET_HASH";
		//}
		insertMap(conn, out, "FileAlgo", "Fileid", "Algorithm", 
				getFileID(conn, get(table, "lfn", true), true), 
				(new DBSApiAlgoLogic(this.data)).getAlgorithmID(conn, get(algo, "app_version"), 
						get(algo, "app_family_name"), 
						get(algo, "app_executable_name"),
						get(algo, "ps_hash"), 
						//psHash,
						true), 
				personApi.getUserID(conn, get(table, "created_by"), dbsUser ),
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
				personApi.getUserID(conn, get(table, "created_by"), dbsUser ),
				personApi.getUserID(conn, dbsUser),
				getTime(table, "creation_date", false));
	}

 	/**
	 * Remaps a list parents and childern of input Files to one single output File. The list of input file is provided as  <code>java.util.Vector</code> that contains the list of LFN. The output file is provided by just one LFN passed in as a String
	 * Finally it updates the block information with correct number of files and size in bytes by calling the updateBlock method.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param inFiles a <code>java.util.Vector</code> that contains a list of <code>java.lang.String</code> that represents the logical name of the files.  
	 * @param outFileTable a <code>java.util.Hashtable</code> that contains a the logical name of the the output file and/or created by , creation date.  
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable or a duplicate entry is being added.
	 */
        public void remapFiles(Connection conn, Writer out, Vector inFiles, Hashtable outFileTable, Hashtable dbsUser) throws Exception { 
		String outFileID = getFileID(conn, get(outFileTable, "lfn", true), true);
		String lmbUserID = personApi.getUserID(conn, dbsUser);
		String cbUserID = personApi.getUserID(conn, get(outFileTable, "created_by"), dbsUser );
		String creationDate = getTime(outFileTable, "creation_date", false);

		for (int j = 0; j < inFiles.size(); ++j) {
			String inFileID = getFileID(conn, (String)inFiles.get(j), true);
			PreparedStatement ps = null;
			ResultSet rs =  null;
			try {
				//Get all the parents
				ps = DBSSql.listFileProvenence(conn, inFileID, true);
				rs =  ps.executeQuery();
				while(rs.next()) {
					insertMap(conn, out, "FileParentage", "ThisFile", "itsParent", 
						outFileID, 
						get(rs, "ID"),
						cbUserID, lmbUserID, creationDate);
				}
			} finally { 
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			}
			
			ps = null;
			rs = null;
			try {
				//Get all the childern
				ps = DBSSql.listFileProvenence(conn, inFileID, false);
				rs =  ps.executeQuery();
				while(rs.next()) {
					insertMap(conn, out, "FileParentage", "ThisFile", "itsParent", 
						get(rs, "ID"),
						outFileID, 
						cbUserID, lmbUserID, creationDate);
				}
			} finally { 
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			}

			//Set the status of the inFile to merged
		
		}
		
	}

 	 /**
	 * Updates the type of a file. 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it fetches the file ID and call a generic methods updateValue that fetches the type id and updates it in Files table.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param lfn the logocal file name.
	 * @param value a value of the type field to be set in this file.
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid, the database connection is unavailable or a procsssed dataset is not found.
	 */
	public void updateFileType(Connection conn, Writer out, String lfn, String value, Hashtable dbsUser) throws Exception {
		updateValue(conn, out, "Files",  getFileID(conn, lfn, true),
				                        "FileType", "FileType", "Type", value, personApi.getUserID(conn, dbsUser));
	}

	/**
	 * Updates the status of a file. 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it fetches the file ID and call a generic methods updateValue that fetches the status id and updates it in Files table.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param lfn the logocal file name.
	 * @param value a value of the status field to be set in this file.
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid, the database connection is unavailable or a procsssed dataset is not found.
	 */
	public void updateFileStatus(Connection conn, Writer out, String lfn, String value, Hashtable dbsUser) throws Exception {
		updateValue(conn, out, "Files",  getFileID(conn, lfn, true),
				                        "FileStatus", "FileStatus", "Status", value, personApi.getUserID(conn, dbsUser));
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
