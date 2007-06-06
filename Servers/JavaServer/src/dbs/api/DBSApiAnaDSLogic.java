/**
 $Revision: 1.34 $"
 $Id: DBSApiAnaDSLogic.java,v 1.34 2007/06/06 15:23:45 sekhri Exp $"
 *
 */

package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import dbs.sql.DBSSql;
import dbs.util.DBSUtil;
import dbs.DBSException;
import java.util.Map;
import java.util.Iterator;

/**
* A class that has the core business logic of all the Analysis datasets APIs.  The signature for the API is internal to DBS and is not exposed to the clients. There is another class <code>dbs.api.DBSApi</code> that has an interface for the clients. All these low level APIs are invoked from <code>dbs.api.DBSApi</code>. This class inherits from DBSApiLogic class.
* @author sekhri
*/
public class DBSApiAnaDSLogic extends DBSApiLogic {
		
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs.
	*/

	DBSApiPersonLogic personApi = null;
	DBSApiData data = null;
	public DBSApiAnaDSLogic(DBSApiData data) {
		super(data);
		this.data = data;
		personApi = new DBSApiPersonLogic(data);
	}

	/**
	 * Lists all the definitions .  
	 * <code> <""/></code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param patternName a parameter passed in from the client that can contain wild card characters for analysis dataset defination name. This pattern is used to restrict the SQL query results by sustitution it in the WHERE clause.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied lfn is invalid, the database connection is unavailable or the file is not found.
	 */
	 public void listAnalysisDatasetDefinition(Connection conn, Writer out, String patternName) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listAnalysisDatasetDefinition(conn, getPattern(patternName, "pattern_analysis_dataset_definition_name"));
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<analysis_dataset_definition id='" +  get(rs, "ID") +
					"' analysis_dataset_definition_name='" + get(rs, "ANALYSIS_DATASET_DEF_NAME") +
					"' lumi_sections='" + get(rs, "LUMI_SECTIONS") +
					"' lumi_section_ranges='" + get(rs, "LUMI_SECTION_RANGES") +
					"' runs='" + get(rs, "RUNS") +
					"' runs_ranges='" + get(rs, "RUNS_RANGES") +
					"' algorithms='" + get(rs, "ALGORITHMS") +
					"' lfns='" + get(rs, "LFNS") +
					"' path='" + get(rs, "PATH") +
					"' user_cut='" + get(rs, "USER_CUT") +
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
         * Lists analysis dataset along with its definition, ALL, or for a particular PATH  
         * <code> <""/></code>
         * @param conn a database connection <code>java.sql.Connection</code> object created externally.
         * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
         * @param patternName a parameter passed in from the client that can contain wild card characters for analysis dataset name. This pattern is used to restrict the SQL query results by sustitution it in the WHERE clause.
         * @param path a parameter passed in from the client that contains Path of a Processed Dataset name. It is used to restrict the SQL query results by sustitution it in the WHERE clause.
         * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied lfn is invalid, the database connection is unavailable or the file is not found.
         */
	 public void listAnalysisDataset(Connection conn, Writer out, String patternName, String path, String version) throws Exception {
 		 PreparedStatement ps = null;
 		 ResultSet rs =  null;
		 String procDSID = null;
		 //String version = null;

 		 if(!isNull(path)) 
 			 procDSID = (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true);
 		 try {
 			 ps = DBSSql.listAnalysisDataset(conn, getPattern(patternName, "analysis_dataset_name_pattern"), version, procDSID);
 			 rs =  ps.executeQuery();
 			 while(rs.next()) {
 				 out.write(((String) "<analysis_dataset id='" +  get(rs, "ID") +
							"' analysis_dataset_name='" + get(rs, "ANALYSIS_DATASET_NAME") +
							"' path='" + get(rs, "ANALYSIS_DATASET_PATH") +
							"' type='" + get(rs, "TYPE") +
							"' status='" + get(rs, "STATUS") +
							"' version ='" + get(rs, "VERSION") +
							"' creation_date='" + getTime(rs, "CREATION_DATE") +
							"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
							"' created_by='" + get(rs, "CREATED_BY") +
							"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
							"'>\n"));
				//Add the details of definition also for this dataset
				out.write(((String) "<analysis_dataset_definition id='" +  get(rs, "ADDID") +
							"' analysis_dataset_definition_name='" + get(rs, "ANALYSIS_DATASET_DEF_NAME") +
							"' lumi_sections='" + get(rs, "LUMI_SECTIONS") +
							"' lumi_section_ranges='" + get(rs, "LUMI_SECTION_RANGES") +
							"' runs='" + get(rs, "RUNS") +
							"' runs_ranges='" + get(rs, "RUNS_RANGES") +
							"' algorithms='" + get(rs, "ALGORITHMS") +
							"' lfns='" + get(rs, "LFNS") +
							"' path='" + get(rs, "ANALYSIS_DATASET_DEF_PATH") +
							"' user_cut='" + get(rs, "USER_CUT") +
							"' creation_date='" + getTime(rs, "ADD_CREATION_DATE") +
							"' last_modification_date='" + get(rs, "ADD_LAST_MODIFICATION_DATE") +
							"' created_by='" + get(rs, "ADD_CREATED_BY") +
							"' last_modified_by='" + get(rs, "ADD_LAST_MODIFIED_BY") +
							"'/>\n"));
				out.write("</analysis_dataset>\n");
			}
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}
	 }



		
        public void createAnalysisDatasetDefinition(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception { 
		String adsDefName = get(table, "analysisds_def_name", true);
		String path = get(table, "path");
		String userCut = get(table, "user_cut");
		String desc = getStr(table, "description", true);
		Vector lumiVector = DBSUtil.getVector(table, "lumi_section");
		Vector runVector = DBSUtil.getVector(table, "run");
		Vector algoVector = DBSUtil.getVector(table, "algorithm");
		Vector fileVector = DBSUtil.getVector(table, "file");

		String lumiNumberList = "";
		String lumiRangeList = "";
		String runNumberList = "";
		String runRangeList = "";
		String algoList = "";
		String fileList = "";

		for (int j = 0; j < lumiVector.size(); ++j) {
			Hashtable hashTable = (Hashtable)lumiVector.get(j);
			String lumiNumber = get(hashTable, "lumi_section_number");
			String lumiRange = get(hashTable, "lumi_section_range");
			if(!isNull(lumiNumber)) {
				if(!isNull(lumiNumberList)) lumiNumberList += ",";
				lumiNumberList += lumiNumber;
			}
			if(!isNull(lumiRange)) {
				if(!isNull(lumiRangeList)) lumiRangeList += ";";
			       	//lumiRangeList += parseRange(lumiRange);
			       	lumiRangeList += lumiRange;
			}
	 	}
		for (int j = 0; j < runVector.size(); ++j) {
			Hashtable hashTable = (Hashtable)runVector.get(j);
			String runNumber = get(hashTable, "run_number");
			String runRange = get(hashTable, "run_range");
			if(!isNull(runNumber)) {
				if(!isNull(runNumberList)) runNumberList += ",";
			       	runNumberList += runNumber;
			}
			if(!isNull(runRange)) {
				if(!isNull(runRangeList)) runRangeList += ";";
				//runRangeList += parseRange(runRange);
				runRangeList += runRange;
			}
	 	}
		for (int j = 0; j < fileVector.size(); ++j) {
			if(!isNull(fileList)) fileList += ",";
			fileList += get((Hashtable)fileVector.get(j), "lfn", true);
	 	}
		for (int j = 0; j < algoVector.size(); ++j) {
			Hashtable hashTable = (Hashtable)algoVector.get(j);
			if(!isNull(algoList)) algoList += ",";
			algoList +=  get(hashTable, "app_version", true) + 
				";" + get(hashTable, "app_family_name", true) + 
				";" + get(hashTable, "app_executable_name", true) + 
				";" + get(hashTable, "ps_hash", true);
	 	}
		createAnalysisDatasetDefinition(conn, out, adsDefName, path, lumiNumberList, lumiRangeList, 
				runNumberList, runRangeList, fileList, algoList, userCut, desc,
				personApi.getUserID(conn, get(table, "created_by"), dbsUser ),
				personApi.getUserID(conn, dbsUser),
				getTime(table, "creation_date", false) );

	 }
	 
         private void createAnalysisDatasetDefinition(Connection conn, Writer out, 
			 String adsDefName, String path,
			 String lumiNumberList, String lumiRangeList, 
			 String runNumberList, String runRangeList, 
			 String fileList, String algoList,  
			 String userCut, String desc,
			 String cbUserID, String lmbUserID, 
			 String creationDate) throws Exception { 
		 //Check if ProcDS exists or not
		if(!isNull(path))
			(new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true); 
		//check if fileList is provided but path is not. Template defination
		else if (!isNull(fileList))
			throw new DBSException("Wrong Parameters", "1064", "Path from template definition is not provided and fileList is provided. In a template defination which do not have path fileList cannot be provided. The fileList is " + fileList);
			
		 
		 if( getID(conn, "AnalysisDSDef", "Name", adsDefName, false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertAnalysisDatasetDefinition(conn, 
						adsDefName, 
						path, 
						lumiNumberList, 
						lumiRangeList, 
						runNumberList, 
						runRangeList, 
						fileList, 
						algoList, 
						userCut,
						desc,
						cbUserID, 
						lmbUserID, 
						creationDate);
				ps.execute();
			} finally {
				if (ps != null) ps.close();
			}

		} else 
			//Instead of warnning it is throwing an exception because the clients will know that there already exists 
			//a definition and he/she needs to create a new one. Warnning can be ignored but exception cannot.
			//writeWarning(out, "Already Exists", "1020", "Analysis Dataset Defintaion " + adsDefName + " Already Exists");
			throw new DBSException("Already Exists", "1020", "Analysis Dataset Defintaion " + adsDefName + " Already Exists");
		

	 }
	 
         //public void createAnalysisDataset(Connection conn, Writer out, String analysisDatasetDefinitionName, Hashtable dbsUser) throws Exception {
         public void createAnalysisDataset(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {

		//Get the Definition of the analysis dataset first and get the path and its id to be used for inserting analysis dataset
		String analysisDatasetDefinitionName = get(table, "analysisds_def_name", true);
		String pathFromDS = get(table, "path");
		String desc = getStr(table, "description", false);
		String lmbUserID = personApi.getUserID(conn, dbsUser);
		String cbUserID = personApi.getUserID(conn, get(table, "created_by"), dbsUser );
		String creationDate = getTime(table, "creation_date", false);
		String logicalOp = "AND";

		String anaDSDefID = ""; 
		String procDSID = ""; 
		String lumiNumberList =  ""; 
		String runNumberList = "";
		String path = "";
                Vector lumiIDList = new Vector();
                Vector runIDList = new Vector();
		Vector fileList = new Vector(); 
		String algoList = ""; 
		Vector algoIDList = new Vector(); 
		String userCut = "";
		Vector lumiRangeList = new Vector(); 
		Vector runRangeList = new Vector(); 

		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listAnalysisDatasetDefinition(conn, analysisDatasetDefinitionName);
			rs =  ps.executeQuery();
			if(rs.next()) {
				anaDSDefID = get(rs, "ID");
				String pathFromDef = get(rs, "PATH");
				//Check if both path from defination and path from dataset exists
                		if(!isNull(pathFromDef) && !isNull(pathFromDS)) 
					throw new DBSException("Wrong Parameters", "1062", "Path from definition " + pathFromDef + " and path from dataset " + pathFromDS + " cannot be provided together");
				
				//Check if niether  path from defination nor path from dataset is provided
                		if(isNull(pathFromDef) && isNull(pathFromDS)) 
					throw new DBSException("Missing data", "1063", "Neither Path from definition nor path from dataset provided");
				
                		if(!isNull(pathFromDef) && isNull(pathFromDS)) 	path = pathFromDef;
				if(isNull(pathFromDef) && !isNull(pathFromDS)) 	path = pathFromDS;

				procDSID = (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true); 

				lumiNumberList = get(rs, "LUMI_SECTIONS");
				lumiRangeList = parseRangeList(get(rs, "LUMI_SECTION_RANGES"));
				runNumberList = get(rs, "RUNS");
				runRangeList = parseRangeList(get(rs, "RUNS_RANGES"));
				algoList = get(rs, "ALGORITHMS");
				userCut = get(rs, "USER_CUT");
				//FIXME insert it in definition first and change the query to fetch it
				//logicalOp = get(rs, "LOGICAL_OP");
				fileList = listToVector(get(rs, "LFNS"));
			
			} else {
				throw new DBSException("Unavailable data", "1021", "No such analysis dataset definition " + analysisDatasetDefinitionName );
			}
		} finally { 
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}

                //get all lumi from lumiList
                if(!isNull(lumiNumberList)) {
                        String[] data = lumiNumberList.split(",");
                        for (int i = 0; i != data.length ; ++i) {
                                lumiIDList.add(getID(conn, "LumiSection", "LumiSectionNumber", data[i].trim(), true));
                        }
                }

                //get all runs from runList
                if(!isNull(runNumberList)) {
                        String[] data = runNumberList.split(",");
                        for (int i = 0; i != data.length ; ++i) {
                                runIDList.add(getID(conn, "Runs", "RunNumber", data[i].trim(), true));
                        } 
                }

		//Get all the algo IDs
		if(!isNull(algoList)) {
			String[] algo = algoList.split(",");
			for (int i = 0; i != algo.length ; ++i) {
				String data[] = algo[i].split(";");
				if (data.length != 4) throw new DBSException("Invalid format", "1023", "Algorithm not stored in proper format in AnalysisDSDef Table. Proper format is version1;family1;exe1;pshash1,vrsion2;family2;exe2;pshash Given " + algo[i]);
				algoIDList.add((new DBSApiAlgoLogic(this.data)).getAlgorithmID(conn, data[0].trim(), data[1].trim(), data[2].trim(),data[3].trim(), true));
			}
		}


		//We can fecth and store what will go in this analysis dataset, if anything will.
		Vector tobeFileLumis = new Vector();
		String analysisDatasetName = path + "/" + analysisDatasetDefinitionName; 
		String status = get(table, "status", false).toUpperCase();
		String type = get(table, "type", false).toUpperCase();

		//FIXME Dafults should no be set by the server
		if (isNull(status)) status = "NEW";
		if (isNull(type)) type = "TEST";
		
		Vector existingFileLumis = new Vector();
		long adsVer = 0;
		boolean insert = false;

		String aDSID = "";

		//No prior version of this ADS exists

                //NOW Lets get the Dataset TO BE's LUMIIDs and FILEIDs
		ps = null;
		rs = null;
		try {
			ps = DBSSql.listAnalysisDSFileLumi(conn,
					procDSID,
					algoIDList,
					fileList,
					lumiIDList,
					runIDList,
					lumiRangeList,
					runRangeList,
					userCut,
					logicalOp,
					cbUserID, lmbUserID, creationDate);
                        rs =  ps.executeQuery();

			//if( isNull((aDSID = getID(conn, "AnalysisDataset", "Name", analysisDatasetName, false))) ) {
			if( isNull((aDSID = getADSID(conn, analysisDatasetName, false))) ) {
				adsVer = 0;
				insert = true;
			} else {
				PreparedStatement ps1 = null;
				ResultSet rs1 =  null;
	    			try {
					ps1 = DBSSql.listExADSFileLumiIDs(conn, aDSID);
					rs1 =  ps1.executeQuery();
					int numberOfRowsExisting = DBSUtil.getNumberOfRows(rs1);

					if (numberOfRowsExisting == DBSUtil.getNumberOfRows(rs)) { 
						for (int j = 0 ; j!= numberOfRowsExisting ; ++j) {
							if(!(get(rs1, "LUMIID").equals(get(rs, "LUMIID"))) ||
									!(get(rs1, "FILEID").equals(get(rs, "FILEID")))) {
								insert = true;
								break;
							}
							rs1.next();
							rs.next();
						}
						
					} else {

						System.out.println("line 3");
						insert = true;
					}
					
	    			} finally {
					if (rs1 != null) rs1.close();
					if (ps1 != null) ps1.close();
	    			}
				if(insert) {
					adsVer = Long.parseLong(getADSVersion(conn, analysisDatasetName, false));
					++adsVer;
					writeWarning(out, "Version Already Exists", "10299", 
							"AnalysisDataset " + analysisDatasetName + 
							" Already Exists with Version: "+ (adsVer-1) +
							" Creating a new one with Version: "+adsVer);

				}
			}
			if(insert) {
				//System.out.println("create the analysis dataset ........");
	    			PreparedStatement ps1 = null;
	    			try {
					ps1 = DBSSql.insertAnalysisDataset(conn,
							analysisDatasetName,
							path,
							procDSID,
							anaDSDefID,
							String.valueOf(adsVer),
							getID(conn, "AnalysisDSType", "Type", type, true),
							getID(conn, "AnalysisDSStatus", "Status", status, true),
							getID(conn, "PhysicsGroup", "PhysicsGroupName",
								get(table, "physics_group_name", true),
								true),
							desc,
							cbUserID, lmbUserID, creationDate);
					ps1.execute();
	    			} finally {		
					if (ps1 != null) ps1.close();
	    			}

		    		//if(isNull(aDSID)) aDSID = getID(conn, "AnalysisDataset", "Name", analysisDatasetName, true);
		    		aDSID = getADSID(conn, analysisDatasetName, true);
				//This is important beacuse the resultset is already on the first element
				for (int j = 0 ; j!= DBSUtil.getNumberOfRows(rs) ; ++j) {
					//System.out.println("ADSID, Lumi ID , File ID = " + aDSID + "," + get(rs, "LUMIID") + "," + get(rs, "FILEID"));
					insertMap(conn, out, "AnalysisDSFileLumi", "AnalysisDataset", "Lumi", "Fileid",
						aDSID,
						get(rs, "LUMIID"),
						get(rs, "FILEID"),
						cbUserID, lmbUserID, creationDate);
					rs.next();
				}

			} else 	writeWarning(out, "Already Exists", "1020", "AnalysisDataset " + 
					analysisDatasetName + " with same entries already exists");

       		    } finally {
    			    if (rs != null) rs.close();
    			    if (ps != null) ps.close();
		    }
	 }


	public String getADSID(Connection conn, String analysisDatasetName, boolean excep) throws Exception {
	    	if(isNull(analysisDatasetName)) {
			if(excep) throw new DBSException("Unavailable data", "1011", "No such Analysis Dataset" + analysisDatasetName);
			return null;
    		}
	    	String id = "";
    		PreparedStatement ps = null;
    		ResultSet rs = null;
    		try {
			ps =  DBSSql.getADSID(conn, analysisDatasetName);
			rs =  ps.executeQuery();
			if(!rs.next()) {
	    			if(excep) throw new DBSException("Unavailable data", "1011", "No such Analysis Dataset" + analysisDatasetName);
	    			else return null;
			}
			id = get(rs, "ID");
    		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
	    	}
	    	return  id;
	}

        public String getADSVersion(Connection conn, String analysisDatasetName, boolean excep) throws Exception {
      		if(isNull(analysisDatasetName)) {
			if(excep) throw new DBSException("Unavailable data", "1011", "No such Analysis Dataset" + analysisDatasetName);
			return null;
    		}
    		String ver = "";
    		PreparedStatement ps = null;
    		ResultSet rs = null;
    		try {
			ps = DBSSql.listAnalysisDataset(conn, analysisDatasetName, "", "");
			//ps =  DBSSql.getADSVersion(conn, analysisDatasetName);
			rs =  ps.executeQuery();
			if(!rs.next()) {
	    			if(excep) throw new DBSException("Unavailable data", "1011", "No such Analysis Dataset" + analysisDatasetName);
	    			else return null;
			}
			ver = get(rs, "VERSION");
    		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
    		}
    		return  ver;
 	}

 
 	 /**
	 * Updates the type of the analysis dataset. 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it fetches the analysis ID and call a generic methods updateName that fetches the type id and updates it in AnalysisDataset table.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param analName the name ofthe primarydataset.
	 * @param value a value of the type field to be set in this primary dataset
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid, the database connection is unavailable or a procsssed dataset is not found.
	 */
	public void updateAnalDSType(Connection conn, Writer out, String analName, String value, Hashtable dbsUser) throws Exception {
		updateName(conn, out, "AnalysisDataset",  getID(conn, "AnalysisDataset", "Name", analName, true),
				                        "Type", "AnalysisDSType", "Type", value, personApi.getUserID(conn, dbsUser));
	}

	/**
	 * Updates the status of the analysis dataset. 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it fetches the analysis ID and call a generic methods updateName that fetches the status id and updates it in AnalysisDataset table.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param analName the name ofthe primarydataset.
	 * @param value a value of the status field to be set in this primary dataset
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid, the database connection is unavailable or a procsssed dataset is not found.
	 */
	public void updateAnalDSStatus(Connection conn, Writer out, String analName, String value, Hashtable dbsUser) throws Exception {
		updateName(conn, out, "AnalysisDataset",  getID(conn, "AnalysisDataset", "Name", analName, true),
				                        "Status", "AnalysisDSStatus", "Status", value, personApi.getUserID(conn, dbsUser));
	}

 	private Vector listToVector(String list) throws Exception {
		Vector v = new Vector();
		if(isNull(list)) return v;
		String[] data = list.split(",");
		if(data.length == 0) {
			return v;
		}
		for (int i = 0; i != data.length ; ++i) {
			v.add(data[i]);
		}
		return v;
	}

	 private String parseRange(String range) throws Exception {
		String[] data = range.split(",");
		if(data.length != 2) {
			throw new DBSException("Invalid format", "1037", " Expected a range in number format. The given number is " + range);
		}
		return data[0] + " AND " + data[1];
	}

	private Vector parseRangeList(String range) throws Exception {
		Vector v = new Vector();
		if(isNull(range)) return v;
		String[] data = range.split(";");
		if(data.length == 0) {
			return v;
		}
		for (int i = 0; i != data.length ; ++i) {
			String[] finalData = data[i].split(",");
			if(finalData.length != 2) throw new DBSException("Invalid format", "1037", " Expected a range in number. The given number is " + data[i]);
			v.add(finalData);
		}
		return v;
	}

}
