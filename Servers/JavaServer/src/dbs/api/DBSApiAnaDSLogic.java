/**
 $Revision: 1.42 $"
 $Id: DBSApiAnaDSLogic.java,v 1.42 2008/03/03 22:00:08 afaq Exp $"
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
import java.util.ArrayList;

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


	/** Creates a .cfg Framework file for an ADS (Version specified) **/
         public void createSourceFromADS(Connection conn, Writer out, String adsName, String adsVersion, String xml) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs =  null;
		String procDSID = null; //Just a place holder, not used in this query !

		try {
                     	ps = DBSSql.listAnalysisDataset(conn, adsName, adsVersion , procDSID);
                        rs =  ps.executeQuery();
			//This must return A Version, or the latest version
                        if (rs.next()) {
				if ( !isNull(xml) ) out.write( ((String)  "<config value='" ));
				out.write(((String)  	" cms.PSet(\n" +
								" ANALYSIS_DATASET_NAME=(" + adsName +  "),\n" +
								" PATH=cms.string(" + get(rs, "ANALYSIS_DATASET_PATH") + "),\n" +
                                                        	" DESCRIPTION=cms.string(" + get(rs, "DESCRIPTION") + "),\n" +
								" STATUS=cms.string(" + get(rs, "STATUS") + "),\n" +			
                                                        	" TYPE=cms.string(" + get(rs, "TYPE") + "),\n" +
								" VERSION=cms.string(" + get(rs, "VERSION") + "),\n" +
								" PHYSICS_GROUP_NAME=cms.string(" + get(rs, "PHYSICS_GROUP_NAME") + "),\n" +
								" CREATION_DATE=cms.string(" + getTime(rs, "CREATION_DATE") + "),\n" +
                                                        	" LAST_MODIFICATION_DATE=cms.string(" + get(rs, "LAST_MODIFICATION_DATE") + "),\n" +
                                                        	" CREATED_BY=cms.string(" + get(rs, "CREATED_BY") + "),\n" +
                                                        	" LAST_MODIFIED_BY=cms.string(" + get(rs, "LAST_MODIFIED_BY") + "),\n" +
								" DEFINITION=cms.string(\"Should we have one here?\"),\n" ));
				//List files for this ADS
				String aDSID = get(rs, "ID");
				ArrayList attributes = new ArrayList();

             /*   attributes.add("retrive_invalid_files");
                attributes.add("retrive_status");
                attributes.add("retrive_type");
                attributes.add("retrive_date");
                attributes.add("retrive_person");
                attributes.add("retrive_parent");
                attributes.add("retrive_algo");
                attributes.add("retrive_tier");
                attributes.add("retrive_lumi");
                attributes.add("retrive_run");
                attributes.add("retrive_branch"); */

				attributes.add("retrive_block");
				out.write(((String)    "FILES=cms.vPSet(" ));
				Vector temp = new Vector();
                		try {
                        		ps = DBSSql.listFiles(conn, null, null, null, aDSID, null, temp, null, attributes);
                        		rs =  ps.executeQuery();
                        		while(rs.next()) {
						out.write( (String)   "\ncms.PSet(filename=cms.string("+ get(rs, "LFN") +"),\n" +
						//List ADS Runs and Excluded Lumis Here (From Def) ?"
						"runs=vuint32(123456,234567),\n" +
						"excludeLumis=vPSet() ),\n" +
						" ))\n" );
					}
                		} finally {
                        		if (rs != null) rs.close();
                        		if (ps != null) ps.close();
                		}

				out.write((String)    ")\n");							
				if (!isNull(xml)) out.write((String)  "' />");
			} else {
                                String msg = "No such Analysis Dataset" + adsName;
                                if (!isNull(adsVersion)) msg += " and version: "+adsVersion;
                                throw new DBSException("Unavailable data", "1011", msg);
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
		 String prevADS = "";
		 //String version = null;
		 //
		 boolean first = true; 
 		 if(!isNull(path)) 
 			 procDSID = (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true);
 		 try {
 			 ps = DBSSql.listAnalysisDataset(conn, getPattern(patternName, "analysis_dataset_name_pattern"), version, procDSID);
 			 rs =  ps.executeQuery();
 			 while(rs.next()) {
				 String adsName = get(rs, "ANALYSIS_DATASET_NAME");
 				 if( !prevADS.equals(adsName) || first) {
       					out.write(((String) "<analysis_dataset id='" +  get(rs, "ID") +
								"' analysis_dataset_name='" + adsName +
								"' path='" + get(rs, "ANALYSIS_DATASET_PATH") +
								"' description='" + get(rs, "DESCRIPTION") +
								"' type='" + get(rs, "TYPE") +
								"' status='" + get(rs, "STATUS") +
								"' version ='" + get(rs, "VERSION") +
								"' physics_group_name='" + get(rs, "PHYSICS_GROUP_NAME") +
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
								"' description='" + get(rs, "ADD_DESCRIPTION") +
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
					first = false;
				 }
 				 prevADS = adsName;
			}
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}
	 }



	//Creates Composite Analysis Datasets
        public void createCompositeAnalysisDataset(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {

		String compAdsName = get(table, "comp_analysisds_name", true);
		String compAdsDesc = getStr(table, "comp_analysisds_desc", true);

                String lmbUserID = personApi.getUserID(conn, dbsUser);
                String cbUserID = personApi.getUserID(conn, get(table, "created_by"), dbsUser );
                String creationDate = getTime(table, "creation_date", false);

		if( getID(conn, "CompositeADS", "Name", compAdsName, false) == null ) {
                        PreparedStatement ps = null;
                        try {
                                ps = DBSSql.insertCompADS(conn,
                                                compAdsName,
                                                compAdsDesc,
                                                cbUserID,
                                                lmbUserID,
                                                creationDate);
                                ps.execute();
                        } finally {
                                if (ps != null) ps.close();
                        }

                } else {
                        throw new DBSException("Already Exists", "1075", "Composite Analysis Dataset " + compAdsName + " Already Exists");
		}

		String comAdsID = getID(conn, "CompositeADS", "Name", compAdsName, true);
		Vector adsVector = DBSUtil.getVector(table, "analysis_datasets");

		if ( adsVector.size() == 0 ) {
			throw new DBSException("Cannot create", "1076", "Composite Analysis Dataset " + compAdsName + " Provide cnstituent Analysis Datasets ");
		}

		for (int j = 0; j < adsVector.size(); ++j) {
			Hashtable hashTable = (Hashtable)adsVector.get(j);
			String adsName = get(hashTable, "analysis_dataset_name", true);
			String adsVer = get(hashTable, "version", false);

			String adsID = null;
			if (isNull(adsVer)) {
				adsID = getADSID(conn, adsName, false);
			}
			else {
				adsID = getADSVersionID(conn, adsName, adsVer, false);
			}
				
			if (isNull(adsID)) {
				String msg = "No such Analysis Dataset" + adsName;
				if (!isNull(adsVer)) msg += " and version: "+adsVer;
                        	throw new DBSException("Unavailable data", "1011", msg);
			}
			else {
				insertMap(conn, out, "CompADSMap", "CompADS", "ADS", 
                                                comAdsID,
						adsID,
                                                cbUserID, lmbUserID, creationDate);
			}
		}
	}




        /**
         * Lists all the composite ADS .  
         * <code> <""/></code>
         * @param conn a database connection <code>java.sql.Connection</code> object created externally.
         * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
         * @param patternName a parameter passed in from the client that can contain wild card characters for COMP analysis dataset defination name. 
         * This pattern is used to restrict the SQL query results by sustitution it in the WHERE clause.
         * @throws Exception Various types of exceptions can be thrown. 
         * Commonly they are thrown if the supplied lfn is invalid, the database connection is unavailable or the file is not found.
         */
         public void listCompADS(Connection conn, Writer out, String patternName) throws Exception {
                PreparedStatement ps = null;
                ResultSet rs =  null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
                try {
                        ps = DBSSql.listCompADS(conn, getPattern(patternName, "pattern_comp_ads"));
                        rs =  ps.executeQuery();
                        while(rs.next()) {
				String comAdsID = get(rs, "ID");
                                out.write(((String) "<comp_analysis_dataset id='" + comAdsID +
                                        "' name='" + get(rs, "COMPADS_NAME") +
                                        "' description='" + get(rs, "DESCRIPTION") +
                                        "' creation_date='" + getTime(rs, "CREATION_DATE") +
                                        "' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
                                        "' created_by='" + get(rs, "CREATED_BY") +
                                        "' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
                                        "'>\n"));
				
				ps1 = DBSSql.listADSForCompADS(conn, comAdsID);
				rs1= ps1.executeQuery();
                        	while(rs1.next()) {
                                        out.write(((String) "<analysis_dataset id='" +  get(rs1, "ID") +
                                                                "' analysis_dataset_name='" + get(rs1, "ANALYSIS_DATASET_NAME") +
                                                                "' path='" + get(rs1, "ANALYSIS_DATASET_PATH") +
                                                                "' description='" + get(rs1, "DESCRIPTION") +
                                                                "' type='" + get(rs1, "TYPE") +
                                                                "' status='" + get(rs1, "STATUS") +
                                                                "' version ='" + get(rs1, "VERSION") +
                                                                "' physics_group_name='" + get(rs1, "PHYSICS_GROUP_NAME") +
                                                                "' creation_date='" + getTime(rs1, "CREATION_DATE") +
                                                                "' last_modification_date='" + get(rs1, "LAST_MODIFICATION_DATE") +
                                                                "' created_by='" + get(rs1, "CREATED_BY") +
                                                                "' last_modified_by='" + get(rs1, "LAST_MODIFIED_BY") +
                                                                "'/>\n"));
				}
				out.write((String)"</comp_analysis_dataset>");
				rs1.close();
				ps1.close();
                        } 
                } finally { 
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                        if (rs1 != null) rs1.close();
                        if (ps1 != null) ps1.close();

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

	public void createAnalysisDatasetFromLFNs(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {

		//Check N See if ADS already exists
		Hashtable ads = DBSUtil.getTable(table, "analysis_dataset");
		// get the path and verify that it exists in DBS
		Hashtable pds = DBSUtil.getTable(table, "dataset");
                String path = get(pds, "path", true);
		String procDSID = (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true);

		if (isNull (procDSID) ) {
			throw new DBSException("Dataset Does Not Exists", "1039", "Dataset Path " + path + "Does not exists in this DBS");
		}

		String analysisDatasetName = get(ads, "name", true);
		String aDSID = null;

		//Not doing any versioning AS of YET
		if( ! isNull((aDSID = getADSID(conn, analysisDatasetName, false))) ) {
			throw new DBSException("Already Exists", "10299", "AnalysisDataset " + analysisDatasetName + " Already Exists in DBS ");
		}

		// Get the Definition of the analysis dataset first 

		Hashtable ads_def = DBSUtil.getTable(table, "analysis_dataset_def");
                String analysisDatasetDefinitionName = get(ads_def, "name", true);
		
		System.out.println("Verify the ADS Def Exists, if not Create it ?!");
                String anaDSDefID = null;
		anaDSDefID = getID(conn, "AnalysisDSDef", "Name", analysisDatasetDefinitionName, true);

		if (isNull(anaDSDefID)) {
			System.out.println("CALL createAnalysisDatasetDefinition HERE !!!!!!!");
			//createAnalysisDatasetDefinition(Connection conn, Writer out, ads_def, Hashtable dbsUser);
		}
	
		anaDSDefID = getID(conn, "AnalysisDSDef", "Name", analysisDatasetDefinitionName, true);
		if (isNull(anaDSDefID)) {
			throw new DBSException("Unavailable data", "1021", "No such analysis dataset definition " + analysisDatasetDefinitionName );
		}

		//Verify the ADS Def Exists, if not Create it ?!
                PreparedStatement ps = null;
                ResultSet rs =  null;
                try {
                        ps = DBSSql.listAnalysisDatasetDefinition(conn, analysisDatasetDefinitionName);
                        rs =  ps.executeQuery();
			if (rs.next()) {
				anaDSDefID = get(rs, "ID");
			}
			else {
				System.out.println("CALL createAnalysisDatasetDefinition HERE !!!!!!!");
				//createAnalysisDatasetDefinition(Connection conn, Writer out, ads_def, Hashtable dbsUser);
			}
                } finally {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                }

		String desc = getStr(ads, "description", false);
                String lmbUserID = personApi.getUserID(conn, dbsUser);
                String cbUserID = personApi.getUserID(conn, get(ads, "created_by"), dbsUser );
                String creationDate = getTime(ads, "creation_date", false);
		String type = getStr(ads, "type", true);
		String status = getStr(ads, "status", true);
		
		//Lets create the first version ONLY, if user wants to create MORE versions, use other APSs, or we will revisit
		String adsVer = "0";

		try {
			ps = DBSSql.insertAnalysisDataset(conn,
                                                        analysisDatasetName,
                                                        path,
                                                        procDSID,
                                                        anaDSDefID,
                                                        String.valueOf(adsVer),
                                                        getID(conn, "AnalysisDSType", "Type", type, true),
                                                        getID(conn, "AnalysisDSStatus", "Status", status, true),
                                                        getID(conn, "PhysicsGroup", "PhysicsGroupName",
                                                                get(ads, "physics_group_name", true),
                                                                true),
                                                        desc,
                                                        cbUserID, lmbUserID, creationDate);
			ps.execute();
		} finally {
			if (ps != null) ps.close();
		}

		java.util.ArrayList files = DBSUtil.getArrayList(table, "files");		
		System.out.println("Looking for files: " + files.size());
		for (int i=0;i<files.size();++i) {
			Hashtable aFile = (Hashtable) files.get(i);
			String lfn = get(aFile, "lfn");
			System.out.println("LFN: "+lfn);
			java.util.ArrayList lumis = DBSUtil.getArrayList(aFile, "file_lumi_section");
			if (lumis.size() < 1 ) lumis.add("");
			insertMapBatch(conn, out, "AnalysisDSFileLumi", "AnalysisDataset", "Lumi", "Fileid",
									aDSID, lumis, getID(conn, "Files", "LogicalFileName", lfn, true), 
									cbUserID, lmbUserID, creationDate);

			//for (int j=0;j<lumis.size();++j) {
			//	Hashtable aLumi = (Hashtable) lumis.get(i);
			//	String lsNumber = get(aLumi, "lumi_section_number", false);
			//}
		}


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
				String abc = get(rs, "RUNS_RANGES");
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
							rs1.next();
							rs.next();
							if(!(get(rs1, "LUMIID").equals(get(rs, "LUMIID"))) ||
									!(get(rs1, "FILEID").equals(get(rs, "FILEID")))) {
								insert = true;
								break;
							}
						}
						
					} else {
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

		    		aDSID = getADSID(conn, analysisDatasetName, true);
				rs.beforeFirst();
				while(rs.next()){
					//System.out.println("ADSID, Lumi ID , File ID = " + aDSID + "," + get(rs, "LUMIID") + "," + get(rs, "FILEID"));					
					insertMap(conn, out, "AnalysisDSFileLumi", "AnalysisDataset", "Lumi", "Fileid",
						aDSID,
						get(rs, "LUMIID"),
						get(rs, "FILEID"),
						cbUserID, lmbUserID, creationDate);
				}
	
			} else 	writeWarning(out, "Already Exists", "1020", "AnalysisDataset " + 
					analysisDatasetName + " with same entries already exists");

       		    } finally {
    			    if (rs != null) rs.close();
    			    if (ps != null) ps.close();
		    }
	 }


	//Returns ID of an analysis dataset for a particular version
        public String getADSVersionID(Connection conn, String analysisDatasetName, String version, boolean excep) throws Exception {
                //if(isNull(analysisDatasetName)) {
                //       if(excep) throw new DBSException("Unavailable data", "1011", "" + analysisDatasetName);
                //        return null;
                //}
                String id = "";
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                        ps =  DBSSql.getADSVersionID(conn, analysisDatasetName, version);
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
			//ps = DBSSql.listAnalysisDataset(conn, analysisDatasetName, "", "");
			ps =  DBSSql.getADSVersion(conn, analysisDatasetName);
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