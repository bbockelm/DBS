/**
 * @author sekhri
 *
 */


package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.io.Writer;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;
import db.DBManagement;
import dbs.sql.DBSSql;
import dbs.util.DBSUtil;
import dbs.DBSException;

public class DBSApiLogic {
	private static String XML_HEADERa = "Dbs-status-message: Success\n" +
						"Dbs-status-code: 100\n" +
						"Content-Type: text/plain; charset=ISO-8859-1\n\n" +
						"<?xml version='1.0' standalone='yes'?><dbs>";
	private static String XML_HEADER =  "<?xml version='1.0' standalone='yes'?>\n<!-- DBS Version 1 -->\n<dbs>\n";
	private static String XML_FOOTER = "</dbs>\n";
	private static String SAFE_PATH = "[-\\w_\\.%/]+";
	//private static String SAFE_PATH = "[-A-Za-z0-9_./\\p{%}]";
	//private static String SAFE_NAME = "[-A-Za-z0-9_.]";
	private static String SAFE_NAME = "[-\\w_\\.%]+";
	private static String SAFE_BLOCK = "[-\\w_\\.%#/]+";
	private static String VALID_PATH = "^/([^/]+)/([^/]+)/([^/]+)";
	private static String VALID_BLOCK = "^/([^/]+)/([^/]+)#([^/]+)";
	private static String VALID_PATTERN = "^/([^/]+)/([^/]+)/([^/]+)/([^/]+)/([^/]+)/([^/]+)";
	//private String userDN;
	//private static String SUCCESS_HEADER = "Dbs-status-message: Success\nDbs-status-code: 100\nContent-Type: text/plain; charset=ISO-8859-1\n\n";

	public DBSApiLogic() {
		//System.out.println("Constructor DBSApiLogic");
	}

	
	public void listPrimaryDatasets(Connection conn, Writer out, String pattern) throws Exception {
		pattern = pattern.replace('*','%');
		checkName(pattern,"primary_dataset_name_pattern");
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.listPrimaryDatasets(pattern));
		out.write(XML_HEADER); 
		while(rs.next()) {
			//for(int i = 0 ; i!= 2; ++i)
			out.write(((String) "<primary-dataset id='" + rs.getString("id") + 
						"' annotation='" + rs.getString("annotation") +
						"' primary_name='" + rs.getString("primary_name") +
						"' start_date='" + rs.getString("start_date") +
						"' end_date='" + rs.getString("end_date") +
						"' creation_date='" + rs.getString("creation_date") +
						"' last_modification_date='" + rs.getString("last_modification_date") +
						"' trigger_path_description='" + rs.getString("trigger_path_description") +
						"' mc_channel_description='" + rs.getString("mc_channel_description") +
						"' mc_production='" + rs.getString("mc_production") +
						"' mc_decay_chain='" + rs.getString("mc_decay_chain") +
						"' other_description='" + rs.getString("other_description") +
						"' type='" + rs.getString("type") +
						"' created_by='" + rs.getString("created_by") +
						"' last_modified_by='" + rs.getString("last_modified_by") +
						"'/>\n"));
			//out.flush();
		}
		out.write(XML_FOOTER);
	}

	public void listProcessedDatasets(Connection conn, Writer out, String pattern) throws Exception {
		pattern = pattern.replace('*','%');
		checkPattern(pattern);
		String[] data = pattern.split("/");
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.listProcessedDatasets(data[1], data[2], data[3], data[4], data[5], data[6]));
		out.write(XML_HEADER);
		while(rs.next()) {
			//String path = "/" + rs.getString("primary_name") + "/" + rs.getString("data_tier") + "/" + rs.getString("processed_name");
			out.write(((String) "<processed-dataset id='" + rs.getString("id") + 
						"' path='" +  rs.getString("path") +
						"' open_for_writing='" + rs.getString("open_for_writing") +
						"' creation_date='" + rs.getString("creation_date") +
						"' last_modification_date='" + rs.getString("last_modification_date") +
						"' physics_group_name='" + rs.getString("physics_group_name") +
						"' physics_group_convener='" + rs.getString("physics_group_convener") +
						"' app_version='" + rs.getString("app_version") +
						"' app_family_name='" + rs.getString("app_family_name") +
						"' app_executable_name='" + rs.getString("app_executable_name") +
						"' created_by='" + rs.getString("created_by") +
						"' last_modified_by='" + rs.getString("last_modified_by") +
						"'/>\n"));
		}
		out.write(XML_FOOTER);
	}

	public void listApplications(Connection conn, Writer out, String pattern) throws Exception {
		pattern = pattern.replace('*','%');
		checkPath(pattern);
		String[] data = pattern.split("/");
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.listApplications(data[1], data[2], data[3]));
		out.write(XML_HEADER);
		while(rs.next()) {
			out.write(((String) "<algorithm id='" + rs.getString("id") + 
						"' app_version='" + rs.getString("app_version") +
						"' app_family_name='" + rs.getString("app_family_name") +
						"' app_executable_name='" + rs.getString("app_executable_name") +
						"' creation_date='" + rs.getString("creation_date") +
						"' last_modification_date='" + rs.getString("last_modification_date") +
						"' created_by='" + rs.getString("created_by") +
						"' last_modified_by='" + rs.getString("last_modified_by") +
						"'/>\n"));
		}
		out.write(XML_FOOTER);
	}


	public void listRuns(Connection conn, Writer out, String path) throws Exception {
		String procDSID = getProcessedDSID(conn, path);
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.listRuns(procDSID));
		out.write(XML_HEADER);
		while(rs.next()) {
				out.write(((String) "<run id='" + rs.getString("id") +
						"' run_number='" + rs.getString("run_number") +
						"' number_of_events='" + rs.getString("number_of_events") +
						"' number_of_lumi_sections='" + rs.getString("number_of_lumi_sections") +
						"' total_luminosity='" + rs.getString("total_luminosity") +
						"' strore_number='" + rs.getString("strore_number") +
						"' start_of_run='" + rs.getString("start_of_run") +
						"' end_of_run='" + rs.getString("end_of_run") +
						"' creation_date='" + rs.getString("creation_date") +
						"' last_modification_date='" + rs.getString("last_modification_date") +
						"' created_by='" + rs.getString("created_by") +
						"' last_modified_by='" + rs.getString("last_modified_by") +
						"'/>\n"));
		}
		out.write(XML_FOOTER);
	}

	public void listTiers(Connection conn, Writer out, String path) throws Exception {
		String procDSID = getProcessedDSID(conn, path);
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.listTiers(procDSID));
		out.write(XML_HEADER);
		while(rs.next()) {
				out.write(((String) "<data_tier id='" + rs.getString("id") +
						"' name='" + rs.getString("name") +
						"' creation_date='" + rs.getString("creation_date") +
						"' last_modification_date='" + rs.getString("last_modification_date") +
						"' created_by='" + rs.getString("created_by") +
						"' last_modified_by='" + rs.getString("last_modified_by") +
						"'/>\n"));
		}
		out.write(XML_FOOTER);
	}

	public void listBlocks(Connection conn, Writer out, String path) throws Exception {
		String procDSID = getProcessedDSID(conn, path);
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.listBlocks(procDSID));
		out.write(XML_HEADER);
		while(rs.next()) {
				out.write(((String) "<block id='" + rs.getString("id") +
						"' name='" + rs.getString("name") +
						"' size='" + rs.getString("size") +
						"' number_of_files='" + rs.getString("number_of_files") +
						"' open_for_writing='" + rs.getString("open_for_writing") +
						"' creation_date='" + rs.getString("creation_date") +
						"' last_modification_date='" + rs.getString("last_modification_date") +
						"' created_by='" + rs.getString("created_by") +
						"' last_modified_by='" + rs.getString("last_modified_by") +
						"'/>\n"));
		}
		out.write(XML_FOOTER);
	}

	public void listFiles(Connection conn, Writer out, String path, String blockName, String patternLFN) throws Exception {
		patternLFN = patternLFN.replace('*','%');
		checkName(patternLFN, "lfn");
		String procDSID = null;
		String blockID = null;
		if(path != null) {
			procDSID = getProcessedDSID(conn, path);
		}
		if(blockName != null) {
			blockID = getBlockID(conn, blockName, true);
		}
		if(blockID == null && procDSID == null) {
			throw new DBSException("Bad Data", "300", "Null Fields. Expected either a Processed Dataset or a Block");
		}

		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.listFiles(procDSID, blockID, patternLFN));
		out.write(XML_HEADER);
		while(rs.next()) {
				out.write(((String) "<file id='" + rs.getString("id") +
						"' lfn='" + rs.getString("lfn") +
						"' checksum='" + rs.getString("checksum") +
						"' size='" + rs.getString("size") +
						"' queryable_meta_data='" + rs.getString("queryable_meta_data") +
						"' creation_date='" + rs.getString("creation_date") +
						"' last_modification_date='" + rs.getString("last_modification_date") +
						"' created_by='" + rs.getString("created_by") +
						"' last_modified_by='" + rs.getString("last_modified_by") +
						"'/>\n"));
		}
		out.write(XML_FOOTER);
	}

	
	public void insertPrimaryDataset(Connection conn, Hashtable dataset, Hashtable dbsUser) throws Exception {
		String warMsg ;
		String userDN = get(dbsUser, "user_dn", true);
		//Get the User ID from USERDN
		String userID = getID(conn, "Person", "DistinguishedName", userDN, true);
		
		String ann = get(dataset, "annotation", true);
		String name = get(dataset, "primary_name", true);
		String startDate = get(dataset, "start_date", true);
		String endDate = get(dataset, "end_date", false);
		String type = get(dataset, "type", false);
		String mcDesc = get(dataset, "mc_channel_description", false);
		String mcPro = get(dataset, "mc_production", false);
		String mcDecay = get(dataset, "mc_decay_chain", false);
		String oDesc = get(dataset, "other_description", false);
		String tpDesc = get(dataset, "trigger_path_description", false);
		
		//Insert a Dataset Type if it does not exists
		//insertName(conn, "Type", "Type", type , userID);
		
		//Insert a Dataset Trigger Desc if it does not exists
                //<<<<<<<<<<<<<<<< Don't like general function<<<<<<<<<<<<<<<<<<<<<<
		insertName(conn, "TriggerPathDescription", "TriggerPathDescription", tpDesc , userID);
		
		//Insert a Dataset Other Desc if it does not exists
                //<<<<<<<<<<<<<<<< Don't like general function<<<<<<<<<<<<<<<<<<<<<<
		insertName(conn, "OtherDescription", "Description", oDesc , userID);

		//TODO Insert MCDesc
		//FIXME The schemna should be changed so that PrimaryDatasetDescription should have PrimarYdataset ID as forign key. 
		//Not the other way around

		//TODO Insert PrimaryDatasetDescription table also

                
		//String primDSID;
                //Why check for existence ???? 
		//if( (primDSID = getID(conn, "PrimaryDataset", "Name", name, false)) == null ) {
		
                //Need to rewite the insertPrimaryDataset so one call must do the insert, instead of more than one<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  
                	DBManagement.execute(conn, DBSSql.insertPrimaryDataset(
							ann,
							name,
							"0",//FIXME Should not be in the schema
							startDate,
							endDate,
							getID(conn, "PrimaryDatasetType", "Type", type, false), 
							userID));
		//} else {
		//	//Append Warnning message that run eixts
		//}

	}

	public void insertRun(Connection conn, Hashtable run, Hashtable dbsUser) throws Exception {
		String warMsg ;
		String userDN = get(dbsUser, "user_dn", true);
		//Get the User ID from USERDN
		String userID = getID(conn, "Person", "DistinguishedName", userDN, true);
		
		String path = get(run, "path");
		String runNumber = get(run, "run_number", true);
		String nOfEvents = get(run, "number_of_events", false);
		String nOfLumiSections = get(run, "number_of_lumi_sections", false);
		String totalLumi = get(run, "total_luminosity", false);
		String storeNumber = get(run, "store_number", false);
		String startOfRun = get(run, "start_of_run", false);
		String endOfRun = get(run, "end_of_run", false);
	

                DBManagement.execute(conn, DBSSql.insertRun(
                                                        runNumber,
                                                        nOfEvents,
                                                        nOfLumiSections,
                                                        totalLumi,
                                                        storeNumber,
                                                        startOfRun,
                                                        endOfRun,
                                                        userID));
          
                String[] data = path.split("/");
                if(data.length != 4) {
                        throw new DBSException("Bad Data", "300", "Invalid Format. Expected /PRIMARY/TIER/PROCESSED");
                }
 
                //We can do it in one shot, why multiple calls to data base.
                DBManagement.execute(conn, DBSSql.insertProcDSRuns(runNumber, data[1], data[2]));

                /**

		//Get Processed Datatset ID		
		String procDSID = getProcessedDSID(conn, path);
		String runID;
		if( (runID = getID(conn, "Runs", "RunNumber", runNumber, false)) == null ) {
			DBManagement.execute(conn, DBSSql.insertRun(
							runNumber,
							nOfEvents,
							nOfLumiSections,
							totalLumi,
							storeNumber,
							startOfRun,
							endOfRun,
							userID));
		} else {
			//Append Warnning message that run eixts
		}

		if(isNull(runID)) runID = getID(conn, "Runs", "RunNumber", runNumber , true);
		
		//Insert ProcDSRuns table by fetching run ID that just got inserted. 
		insertMap(conn, "ProcDSRuns", "Dataset", "Run", procDSID, runID, userID);  */

	}

	public void insertBlock(Connection conn, Hashtable block, Hashtable dbsUser) throws Exception {

                //Better Block management will be required here <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


		String warMsg ;
		String userDN = get(dbsUser, "user_dn", true);
		//Get the User ID from USERDN
		String userID = getID(conn, "Person", "DistinguishedName", userDN, true);
		
		String path = get(block, "path");
		String size = get(block, "size", false);
		String name = get(block, "name");//FIXME Generate name via GUIDs
		String nOfFiles = get(block, "number_of_files", false);
		String openForWriting = get(block, "open_for_writing", false);
	
		//Set defaults Values
		if (isNull(name)) name = "/abd/def#guid";//FIXME Generate this
		if (isNull(size)) size = "0";
		if (isNull(nOfFiles)) nOfFiles = "0";
		if (isNull(openForWriting)) openForWriting = "1";

                String[] data = path.split("/");
                if(data.length != 4) {
                        throw new DBSException("Bad Data", "300", "Invalid Format. Expected /PRIMARY/TIER/PROCESSED");
                }

                //One call 
                DBManagement.execute(conn, DBSSql.insertBlock(size, nOfFiles, name, data[1], data[3])); 

                //We don't need to do it in multiple steps and check before we insert in this case

                /** 
		//Get Processed Datatset ID		
		String procDSID = getProcessedDSID(conn, path);
		if( getBlockID(conn,  name, false) == null ) {
			DBManagement.execute(conn, DBSSql.insertBlock(
							size,
							name,
							procDSID,
							nOfFiles,
							openForWriting,
							userID));
		} else {
			//Append Warnning message that block eixts
		}

		//FIXME Return blockNameback to the user
                */

	}

		public void insertApplication(Connection conn, Hashtable app, Hashtable dbsUser) throws Exception {
		/*for (Enumeration e = app.elements() ; e.hasMoreElements() ;) {
			checkName((String)e.nextElement());
		}*/
		String version = get(app, "app_version", true);
		String family = get(app, "app_family_name", true);
		String exe = get(app, "app_executable_name", true);
		String psHash = get(app, "ps_hash", false);
		String psName = get(app, "ps_name", true);
		String psVersion = get(app, "ps_version", false);
		String psType = get(app, "ps_type", false);
		String psAnnotation = get(app, "ps_annotation", false);
		String psContent = get(app, "ps_content", false);
		String userDN = get(dbsUser, "user_dn", true);
		
		//Get the User ID from USERDN
		String userID = getID(conn, "Person", "DistinguishedName", userDN, true);

		//Insert the application version if it does not exists
		insertName(conn, "AppVersion", "Version", version, userID);
		
		//Insert the Application Family if it does not exists
		insertName(conn, "AppFamily", "FamilyName", family, userID);
		
		//Insert the Application Executable if it does not exists
		insertName(conn, "AppExecutable", "ExecutableName", exe, userID);

		//Insert the ParameterSet if it does not exists
		insertParameterSet(conn, psHash, psName, psVersion, psType, psAnnotation, psContent, userID);
		
		//Insert the Algorithm by fetching the ID of exe, version, family and parameterset


                //<<<<<<<<<<<<<<<<<<<<Must be done in Single SQL statement, insterad of getting all IDs separately
		DBManagement.execute(conn, DBSSql.insertApplication(
                                                                        getID(conn, "AppExecutable", "ExecutableName", exe, true), 
									getID(conn, "AppVersion", "Version", version, true), 
									getID(conn, "AppFamily", "FamilyName", family, true), 
									getID(conn, "QueryableParameterSet", "Name", psName, true), 
									userID));
	}

	public void insertFiles(Connection conn, Vector files, Hashtable dbsUser) throws Exception {
		String userDN = get(dbsUser, "user_dn", true);
		//Get the User ID from USERDN
		String userID = getID(conn, "Person", "DistinguishedName", userDN, true);

		for (int i = 0; i < files.size() ; ++i) {
			Hashtable file = (Hashtable)files.get(i);
		
			String path = get(file, "path");
			String blockName = get(file, "block_name");
			String lfn = get(file, "lfn", true);
			String checksum = get(file, "checksum", false);
			String nOfEvents = get(file, "number_of_events", false);
			String size = get(file, "size", false);
			String fileStatus = get(file, "file_status", false);
			String type = get(file, "type", false);
			String valStatus = get(file, "validation_status", false);
			String qMetaData = get(file, "queryable_meta_data", false);
			Vector lumiVector = DBSUtil.getVector(file,"lumi_section");
			Vector tierVector = DBSUtil.getVector(file,"data_tier");
			Vector parentVector = DBSUtil.getVector(file,"parent");
			Vector algoVector = DBSUtil.getVector(file,"algorithm");
		
			String procDSID = getProcessedDSID(conn, path);
			String blockID = getBlockID(conn, blockName, true);
			//FIXME Update block information at the end of this api call
			//
			//Set defaults Values
			if (isNull(fileStatus)) fileStatus = "NEW";
			if (isNull(type)) type = "EVD";
			if (isNull(valStatus)) valStatus = "NOTVALIDATED";
			
			//Insert a File status if it does not exists
			insertName(conn, "Status", "Status", fileStatus , userID);

			//Insert a File Validation status if it does not exists
			insertName(conn, "Status", "Status", valStatus , userID);

			//Insert a File Type if it does not exists
			insertName(conn, "Type", "Type", type , userID);

			//Insert a File by fetching the fileStatus, type and validationStatus
			String fileID;
			if( (fileID = getID(conn, "Files", "LogicalFileName", lfn, false)) == null ) {
				DBManagement.execute(conn, DBSSql.insertFile(procDSID, blockID, lfn, checksum, nOfEvents,  size, 
								getID(conn, "Status", "Status", fileStatus, false), 
								getID(conn, "Type", "Type", type, false), 
								getID(conn, "Status", "Status", valStatus, false), 
								qMetaData, userID));
			}

			//Fetch the File ID that was just inseted to be used for subsequent insert of other tables.
			if(isNull(fileID)) fileID = getID(conn, "Files", "LogicalFileName", lfn, true);

			//Insert FileAlgo table by fetching application ID. 
			//Use get with 2 params so that it does not do type checking, since it will be done in getID call.
			for (int j = 0; j < algoVector.size(); ++j) {
				Hashtable hashTable = (Hashtable)algoVector.get(j);
				String appID = getApplicationID(conn, get(hashTable, "app_version"), 
								get(hashTable, "app_family_name"), 
								get(hashTable, "app_executable_name"));

				insertMap(conn, "FileAlgoMap", "Fileid", "Algorithm", fileID, appID, userID);
			}

			//Insert FileTier table by fetching data tier ID
			for (int j = 0; j < tierVector.size(); ++j) {
				Hashtable hashTable = (Hashtable)tierVector.get(j);
				String tierID = getID(conn, "DataTier", "Name", get(hashTable, "name") , true);
				insertMap(conn, "FileTier", "Fileid", "DataTier", fileID, tierID, userID);
			}

			//Insert FileLumi table by first inserting and then fetching Lumi Section ID
			for (int j = 0; j < lumiVector.size(); ++j) {
				Hashtable hashTable = (Hashtable)lumiVector.get(j);
				//Insert A lumi Section if it does not exists
				insertLumiSection(conn, hashTable, userID);
				String lsID = getID(conn, "LumiSection", "LumiSectionNumber", get(hashTable, "lumi_section_number") , true);
				insertMap(conn, "FileLumi", "Fileid", "Lumi", fileID, lsID, userID);
			}


			//Insert FileParentage table by fetching parent File ID
			for (int j = 0; j < parentVector.size(); ++j) {
				Hashtable hashTable = (Hashtable)parentVector.get(j);
				String parentFileID = getID(conn, "Files", "LogicalFileName", get(hashTable, "lfn") , true);
				insertMap(conn, "FileParentage", "ThisFile", "itsParent", fileID, parentFileID, userID);
			}
		
		}//For loop
	}


	public void insertProcessedDatatset(Connection conn, Hashtable dataset, Hashtable dbsUser) throws Exception {
		String warMsg ;
		String userDN = get(dbsUser, "user_dn", true);
		//Get the User ID from USERDN
		String userID = getID(conn, "Person", "DistinguishedName", userDN, true);
		
		String primDSName = get(dataset, "primary_datatset_name", true);
		String procDSName = get(dataset, "processed_datatset_name", true);
		String openForWriting = get(dataset, "open_for_writing", false);
		String phyGroupName = get(dataset, "physics_group_name", false);
		String phyGroupCon = get(dataset, "physics_group_convener", false);
		String status = get(dataset, "status", false);
		Vector tierVector = DBSUtil.getVector(dataset,"data_tier");
		Vector parentVector = DBSUtil.getVector(dataset,"parent");
		Vector algoVector = DBSUtil.getVector(dataset,"algorithm");
	

		//Set defaults Values
		if (isNull(status)) status = "NEW";
		if (isNull(phyGroupName)) phyGroupName = "ALLGROUP";
		if (isNull(phyGroupCon)) phyGroupCon = "ANZARDN";//FIXME Some default convenor name should be used
		
		//Insert a Processed Dataset status if it does not exists
		insertName(conn, "Status", "Status", status , userID);
		
		//Insert a Physics Group if it does not exists
		insertPhysicsGroup(conn, phyGroupName, phyGroupCon, userID);
		

                // 3 selects and then one Insert, that can be done in One INSERT Call. 

		//Insert a Processed Datatset if it does not exits before by fetching the primDSID, status
		String procDSID;
		if( (procDSID = getID(conn, "ProcessedDataset", "Name", procDSName, false)) == null ) {
			DBManagement.execute(conn, DBSSql.insertProcessedDatatset(
							procDSName,
							getID(conn, "PrimaryDataset", "Name", primDSName, true),
							openForWriting,
							getID(conn, "PhysicsGroup", "PhysicsGroupName", phyGroupName, true), 
							getID(conn, "Status", "Status", status, false), 
							userID));
		} else {
			//warMsg =+ (String)"ProcessedDataset Name " + procDSName + " already exists but ignored.\n";
		}


		//Fetch the Processed Datatset ID that was just inseted or fetched , to be used for subsequent insert of other tables.
		//FIXME this might use processed datatset with primary datatset combination instead of just proDSName
                //I think it MUST
		if(isNull(procDSID)) procDSID = getID(conn, "ProcessedDataset", "Name", procDSName, true);
		
		//Insert ProcAlgoMap table by fetching application ID. 
		for (int j = 0; j < algoVector.size(); ++j) {
			Hashtable hashTable = (Hashtable)algoVector.get(j);
			String appID = getApplicationID(conn, get(hashTable, "app_version"), 
							get(hashTable, "app_family_name"), 
							get(hashTable, "app_executable_name"));
			insertMap(conn, "ProcAlgoMap", "Dataset", "Algorithm", procDSID, appID, userID);
		}

		//Insert FileTier table by fetching data tier ID
		for (int j = 0; j < tierVector.size(); ++j) {
			Hashtable hashTable = (Hashtable)tierVector.get(j);
			String tierName = get(hashTable, "name", true);
			//Insert DataTier if it does not exists
			insertName(conn, "DataTier", "Name", tierName , userID);
			String tierID = getID(conn, "DataTier", "Name", tierName , true);
			insertMap(conn, "ProcDSTier", "Dataset", "DataTier", procDSID, tierID, userID);
		}

		//Insert FileParentage table by fetching parent File ID
		for (int j = 0; j < parentVector.size(); ++j) {
			Hashtable hashTable = (Hashtable)parentVector.get(j);
			String parentProcDSID = getProcessedDSID(conn,  get(hashTable, "path"));
			insertMap(conn, "DatasetParentage", "ThisDataset", "ItsParent", procDSID, parentProcDSID, userID);
		}

	}

	private void insertLumiSection(Connection conn, Hashtable table, String userID) throws Exception {
		String lsNumber = get(table, "lumi_section_number");
		String runNumber = get(table, "run_number", true);
		String startEvNumber = get(table, "start_event_number", false);
		String endEvNumber = get(table, "end_event_number", false);
		String lStartTime = get(table, "lumi_start_time", false);
		String lEndTime = get(table, "lumi_end_time", false);


                //Agein this can be done in ONE INSERT statement.<<<<<<<<<<<<<<<<

		//Insert a new Lumi Section by feting the run ID 
		if( getID(conn, "LumiSection", "LumiSectionNumber", lsNumber, false) == null ) {
			DBManagement.execute(conn, DBSSql.insertLumiSection(lsNumber, 
									getID(conn, "Runs", "RunNumber", runNumber, true),
									startEvNumber, endEvNumber, lStartTime, lEndTime, userID));
		}
	}


	private void insertName(Connection conn, String table, String key, String value, String userID) throws Exception {
		if(isNull(table) || isNull(key) || isNull(value) || isNull(userID) ) return;
		if( getID(conn, table, key, value, false) == null ) {
			DBManagement.execute(conn, DBSSql.insertName(table, key, value, userID));
		}
	}
	
	
	private void insertMap(Connection conn, String tableName, String key1, String key2, String value1, String value2, String userID) throws Exception {
		if( getMapID(conn, tableName, key1, key2, value1, value2, false) == null )
			DBManagement.execute(conn, DBSSql.insertMap(tableName, key1, key2, value1, value2, userID));

	}


	private void insertParameterSet(Connection conn, String hash, String name, String version, String type, String annotation, String content, String userID) throws Exception {
		if( getID(conn, "QueryableParameterSet", "Name", name, false) == null ) {
			DBManagement.execute(conn, DBSSql.insertParameterSet(hash, name, version, type, annotation, content, userID));
		}
	}

	public void insertPhysicsGroup(Connection conn, String name, String phyGroupCon, String userID) throws Exception {
		//Insert a new Person if it does not exists
		insertPerson(conn, "", phyGroupCon, "", userID); //FIXME Get userName and contactInfo also
		if( getID(conn, "PhysicsGroup", "PhysicsGroupName", name, false) == null ) {
			DBManagement.execute(conn, DBSSql.insertPhysicsGroup(name, 
										getID(conn, "Person", "DistinguishedName", phyGroupCon, true), 
										userID));
		}
	}

	public void insertPerson(Connection conn, String userName, String userDN, String contactInfo, String userID) throws Exception {
		if (isNull(userID)) userID = "0";//0 is user not created by anyone
		if( getID(conn, "Person", "DistinguishedName", userDN , false) == null ) {
			DBManagement.execute(conn, DBSSql.insertPerson(userName, userDN, contactInfo,  userID));
		}
	}


	private String getProcessedDSID(Connection conn, String path) throws Exception {
		if(path == null) {
			throw new DBSException("Bad Data", "300", "Null path. Expected /PRIMARY/TIER/PROCESSED");
		}
		checkPath(path);
		String[] data = path.split("/");
		if(data.length != 4) {
			throw new DBSException("Bad Data", "300", "Invalid Format. Expected /PRIMARY/TIER/PROCESSED");
		}
		return  getProcessedDSID(conn, data[1], data[2], data[3]);
	}

	private String getProcessedDSID(Connection conn, String prim, String dt, String proc) throws Exception {
		checkName(prim, "primary_dataset_name");
		checkName(dt, "data_tier");
		checkName(proc, "processed_dataset_name");
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getProcessedDSID(prim, dt, proc));
		if(!rs.next()) {
			throw new DBSException("Bad Data", "300", "No such processed dataset /" + prim + "/" + dt + "/" +proc );
		}
		return  rs.getString("id");
	}

	private String getApplicationID(Connection conn, String version, String family, String exe) throws Exception {
		checkName(version, "app_version");
		checkName(family, "app_family_name");
		checkName(exe, "app_executable_name");
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getApplicationID(version, family, exe));
		if(!rs.next()) {
			throw new DBSException("Bad Data", "300", "No such Application " + version + " " + family + " " + exe );
		}
		return  rs.getString("id");
	}

	private String getBlockID(Connection conn, String name, boolean excep) throws Exception {
		checkBlock(name);
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getID( "Block", "Name", name));
		if(!rs.next()) {
			if(excep) throw new DBSException("Bad Data", "300", "No such Block : Name : "  + name );
			else return null;
		}
		return  rs.getString("id");
	}

	private String getID(Connection conn, String tableName, String key, String value, boolean excep) throws Exception {
		if(isNull(tableName) || isNull(key) || isNull(value)) return null;
		if (excep) checkName(value, key);
		else if(!isNull(value)) checkName(value, key);

		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getID(tableName, key, value));
		//System.out.println("tableName "+ tableName+ " key "+ key + " value " + value + " excep "+ excep);
		if(!rs.next()) {
			if(excep) throw new DBSException("Bad Data", "300", "No such " + tableName + " : " + key + " : " + value );
			else return null;
		}
		return  rs.getString("id");
	}

	private String getMapID(Connection conn, String tableName, String key1, String key2, String value1, String value2,  boolean excep) throws Exception {
		if(isNull(tableName) || isNull(key1) || isNull(value1) || isNull(key2) || isNull(key2) ) return null;
		if (excep) {
			checkName(value1, key1);
			checkName(value2, key2);
		} else {
			if(!isNull(value1)) checkName(value1, key1);
			if(!isNull(value2)) checkName(value2, key2);
		}
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getMapID(tableName, key1, key2, value1, value2));
		if(!rs.next()) {
			if(excep) throw new DBSException("Bad Data", "300", "No such " + tableName + " : " + key1 + " : " + value1 + " : " + key2 + " : " + value2);
			else return null;
		}
		return  rs.getString("id");
	}

	private void checkPattern(String pattern) throws Exception {
		if(pattern == null) {
			throw new DBSException("Bad Data", "300", "Null pattern. Expected /PRIMARY/TIER/PROCESSED/VERSION/FAMILY/EXECUTABLE");
		}
		if (! Pattern.matches(VALID_PATTERN, pattern) ) {
			throw new DBSException("Bad Data", "300", "Invalid pattern. Expected /PRIMARY/TIER/PROCESSED/VERSION/FAMILY/EXECUTABLE in format " + VALID_PATTERN);
		}
		if( ! Pattern.matches(SAFE_PATH, pattern) ) {
			throw new DBSException("Bad Data", "300", "Invalid Characters in " + pattern + " Expected /PRIMARY/TIER/PROCESSED/VERSION/FAMILY/EXECUTABLE in fortmat "+ SAFE_PATH);
		}
	}
	
	private void checkPath(String path) throws Exception {
		if(path == null) {
			throw new DBSException("Bad Data", "300", "Null path. Expected /PRIMARY/TIER/PROCESSED");
		}
		if (! Pattern.matches(VALID_PATH, path) ) {
			throw new DBSException("Bad Data", "300", "Invalid path. Expected /PRIMARY/TIER/PROCESSED in format " + VALID_PATH);
		}
		if( ! Pattern.matches(SAFE_PATH, path) ) {
			throw new DBSException("Bad Data", "300", "Invalid Characters in " + path + " Expected /PRIMARY/TIER/PROCESSED in format "+ SAFE_PATH);
		}
	}
	
	private void checkBlock(String blockName) throws Exception {
		if(blockName == null) {
			throw new DBSException("Bad Data", "300", "Null blockName. Expected /PRIMARY/PROCESSED#GUID");
		}
		if (! Pattern.matches(VALID_BLOCK, blockName) ) {
			throw new DBSException("Bad Data", "300", "Invalid path. Expected /PRIMARY/PROCESSED#GUID in format " + VALID_BLOCK);
		}
		if( ! Pattern.matches(SAFE_BLOCK, blockName) ) {
			throw new DBSException("Bad Data", "300", "Invalid Characters in " + blockName + " Expected /PRIMARY/PROCESSED#GUID in format "+ SAFE_BLOCK);
		}
	}
	
	private void checkName(String pattern, String key) throws Exception {
		if(pattern == null) {
			throw new DBSException("Bad Data", "300", "Null Fields. Expected a " + key);
		}
		if(pattern.length() < 1 ) {
			throw new DBSException("Bad Data", "300", "Null Fields. Expected a " + key);
		}
		if (! Pattern.matches(SAFE_NAME, pattern)) {
			throw new DBSException("Bad Data", "300", "Invalid Characters in " + pattern + " for " + key + " Expected "+ SAFE_NAME);
		}
	}
	
	private boolean isNull(String pattern) {
		if(pattern == null) {
			return true;
		}
		if(pattern.length() < 1 ) {
			return true;
		}
		return false;
	}
	
	private String get(Hashtable table, String key, boolean excep) throws Exception{
		//System.out.println(key);
		String value = DBSUtil.get(table, key);
		if(excep) checkName(value, key);
		else if(! isNull(value)) checkName(value, key);
		return value;
	}

	private String get(Hashtable table, String key) {
		//System.out.println(key);
		return DBSUtil.get(table, key);
	}
}
