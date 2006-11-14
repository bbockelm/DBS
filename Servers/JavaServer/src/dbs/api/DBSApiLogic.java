/**
 $Revision: 1.18 $"
 $Id: DBSApiLogic.java,v 1.18 2006/11/14 18:17:47 sekhri Exp $"
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
import java.util.UUID;
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
	//private static String VALID_PATTERN = "^/([^/]+)/([^/]+)/([^/]+)/([^/]+)/([^/]+)/([^/]+)";
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

		String patternPrim 	= getPattern(pattern, 1, "primary_datatset_name_pattern");
                String patternDT       = getPattern(pattern, 2, "data_tier_name_pattern");
                String patternProc     = getPattern(pattern, 3, "processed_datatset_name_pattern");
                String patternVer      = getPattern(pattern, 4, "app_version");
                String patternFam      = getPattern(pattern, 5, "app_family_name");
                String patternExe      = getPattern(pattern, 6, "app_executable_name");
                String patternPS       = getPattern(pattern, 7, "parameterset_name");
		String prevDS = "";
		String prevTier = "";
		String prevExe = "";
		String prevFam = "";
		String prevVer = "";
		String prevPS = "";
		Vector dtVec = null;
		boolean first = true;

		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.listProcessedDatasets(patternPrim, patternDT, patternProc, patternVer, patternFam, patternExe, patternPS));
		out.write(XML_HEADER);
		                
		while(rs.next()) {
			//String path = "/" + rs.getString("primary_name") + "/" + rs.getString("data_tier") + "/" + rs.getString("processed_name");
			String procDSID = rs.getString("id");
			String tier = rs.getString("data_tier");
			String fam = rs.getString("app_family_name");
			String exe = rs.getString("app_executable_name");
			String ver = rs.getString("app_version");
			String ps = rs.getString("ps_name");

			if( !prevDS.equals(procDSID) && ! first) {
				out.write(((String) "</processed-dataset>\n")); 
			}
			if( !prevDS.equals(procDSID) || first) {
				out.write(((String) "<processed-dataset id='" + rs.getString("id") + 
						//"' path='" +  rs.getString("path") +
						"' primary_datatset_name='" +  rs.getString("primary_datatset_name") +
						"' processed_datatset_name='" +  rs.getString("processed_datatset_name") +
						"' open_for_writing='" + rs.getString("open_for_writing") +
						"' creation_date='" + rs.getString("creation_date") +
						"' last_modification_date='" + rs.getString("last_modification_date") +
						"' physics_group_name='" + rs.getString("physics_group_name") +
						"' physics_group_convener='" + rs.getString("physics_group_convener") +
						//"' app_version='" + rs.getString("app_version") +
						//"' app_family_name='" + rs.getString("app_family_name") +
						//"' app_executable_name='" + rs.getString("app_executable_name") +
						"' created_by='" + rs.getString("created_by") +
						"' last_modified_by='" + rs.getString("last_modified_by") +
						"'>\n"));
						//"'/>\n"));
				first = false;
				prevDS = procDSID;
				dtVec = new Vector();// Or dtVec.removeAllElements();
			}
			if( (!prevTier.equals(tier) || first) && !dtVec.contains(tier) ) {
				out.write(((String) "\t<data_tier name='" + tier + "'/>\n"));
				dtVec.add(tier);
				prevTier = tier;
			}
			if( !prevExe.equals(exe) || !prevFam.equals(fam) || !prevVer.equals(ver) || !prevPS.equals(ps) || first) {
				out.write(((String) "\t<algorithm app_version='" + ver + "' app_family_name='" + fam + "' app_executable_name='" + exe + "' ps_name='" + ps + "'/>\n"));
				prevExe = exe;
				prevFam = fam;
				prevVer = ver;
				prevPS = ps;
			}
		}

                if (!first) out.write(((String) "</processed-dataset>\n")); 
		out.write(XML_FOOTER);
	}

	public void listAlgorithms(Connection conn, Writer out, String pattern) throws Exception {
		//name should be changed to hash
		String patternVer	= getPattern(pattern, 1, "app_version");
		String patternFam	= getPattern(pattern, 2, "app_family_name");
		String patternExe	= getPattern(pattern, 3, "app_executable_name");
		String patternPS 	= getPattern(pattern, 4, "parameterset_name");

		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.listAlgorithms(patternVer, patternFam, patternExe, patternPS));
		out.write(XML_HEADER);
		while(rs.next()) {
			out.write(((String) "<algorithm id='" + rs.getString("id") + 
						"' app_version='" + rs.getString("app_version") +
						"' app_family_name='" + rs.getString("app_family_name") +
						"' app_executable_name='" + rs.getString("app_executable_name") +
						"' ps_name='" + rs.getString("ps_name") +
						"' ps_hash='" + rs.getString("ps_hash") +
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

                String prevTier = "";
		Vector dtVec = null;
		boolean first = true;

		String prevfileid = "";

		if ( patternLFN != null) {
                   patternLFN = patternLFN.replace('*','%');
		   checkName(patternLFN, "lfn");
                }
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
                        String tier = rs.getString("data_tier");
                        String fileid = rs.getString("id");

                        if( ! first) {  
                        //This is stupid, I need to look at its more ANZAR: FIXME
                        //if( !prevfileid.equals(fileid) && ! first) {  
                             out.write( (String) "</file> \n");
                        }   
                         
                        if( !prevfileid.equals(fileid) || ! first) {  
				out.write(((String) "<file id='" + rs.getString("id") +
					"' lfn='" + rs.getString("lfn") +
					"' checksum='" + rs.getString("checksum") +
					"' size='" + rs.getString("size") +
					"' queryable_meta_data='" + rs.getString("queryable_meta_data") +
					"' number_of_events='" + rs.getString("number_of_events") +
					"' validation_status='" + rs.getString("validation_status") +
					"' type='" + rs.getString("type") +
					"' status='" + rs.getString("status") +
					"' block_name='" + rs.getString("block_name") +
					"' creation_date='" + rs.getString("creation_date") +
					"' last_modification_date='" + rs.getString("last_modification_date") +
					"' created_by='" + rs.getString("created_by") +
					"' last_modified_by='" + rs.getString("last_modified_by") +
					"'>\n"));
					//"'/>\n"));
                                  first = false;
                                  prevfileid=fileid;
                                  dtVec = new Vector();// Or dtVec.removeAllElements();
                           }
                       
                        System.out.println("DONE 1"); 
 
                        if (tier != null) {
                           System.out.println("DONE 2: "+tier); 
                           if( (!prevTier.equals(tier) || first) && !dtVec.contains(tier) ) {
                                System.out.println("DONE 3"); 
                                out.write(((String) "\t<data_tier name='" + tier + "'/>\n"));
                                dtVec.add(tier);
                                prevTier = tier;
                           }
                         }

		}
                if (!first) out.write(((String) "</file>\n"));
		out.write(XML_FOOTER);
	}

	
	public void insertPrimaryDataset(Connection conn, Hashtable dataset, Hashtable dbsUser) throws Exception {
		String warMsg ;
		//Get the User ID from USERDN
		String userID = getUserID(conn, dbsUser); 
		
		String ann = get(dataset, "annotation", false);
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
		//FIXME some problem with this table while insertng rows
		//insertName(conn, "TriggerPathDescription", "TriggerPathDescription", tpDesc , userID);
		
		//Insert a Dataset Other Desc if it does not exists
		//insertName(conn, "OtherDescription", "Description", oDesc , userID);

		//TODO Insert MCDesc . Change in the schema is required.
		//FIXME The schemna should be changed so that PrimaryDatasetDescription should have PrimarYdataset ID as forign key. 
		//Not the other way around

		//TODO Insert PrimaryDatasetDescription table also
		//String primDSID;
		//if( (primDSID = getID(conn, "PrimaryDataset", "Name", name, false)) == null ) {
		DBManagement.execute(conn, DBSSql.insertPrimaryDataset(
							ann,
							name,
							"0",//FIXME Should not be in the schema
							startDate,
							endDate,
							getID(conn, "PrimaryDatasetType", "Type", type, false), 
							userID));
		//} else {
			//Append Warnning message that run eixts
		//}

	}

	public void insertRun(Connection conn, Hashtable run, Hashtable dbsUser) throws Exception {
		DBManagement.execute(conn, DBSSql.insertRun(
							get(run, "run_number", true),
							get(run, "number_of_events", false),
							get(run, "number_of_lumi_sections", false),
							get(run, "total_luminosity", false),
							get(run, "store_number", false),
							get(run, "start_of_run", false),
							get(run, "end_of_run", false),
							getUserID(conn, dbsUser)));

	}

	public void insertTier(Connection conn, String tierName, Hashtable dbsUser) throws Exception {
		insertName(conn, "DataTier", "Name", tierName , getUserID(conn, dbsUser));
	}

	public void insertBlock(Connection conn, Hashtable block, Hashtable dbsUser) throws Exception {
		String path = get(block, "path");
		String name = get(block, "name");
		String openForWriting = get(block, "open_for_writing", false);
	
		String procDSID = getProcessedDSID(conn, path);//Getting ID before spliting the path will type chech the path also.
		//Set defaults Values
		String[] data = path.split("/");
		if (isNull(name)) name = "/" + data[1] + "/" + data[3] +"#" + UUID.randomUUID(); 
		if (isNull(openForWriting)) openForWriting = "1";
		checkBlock(name);
		DBManagement.execute(conn, DBSSql.insertBlock(
							"0",// A new block should always have 0 size
							name,
							procDSID,
							"0",// A new block should always have 0 files
							openForWriting,
							getUserID(conn, dbsUser)));

		//FIXME Return blockNameback to the user

	}


	public void insertAlgorithm(Connection conn, Hashtable algo, Hashtable dbsUser) throws Exception {
		String version = get(algo, "app_version", true);
		String family = get(algo, "app_family_name", true);
		String exe = get(algo, "app_executable_name", true);
		String psName = get(algo, "ps_name", true);
		
		//Get the User ID from USERDN
		String userID = getUserID(conn, dbsUser);
		//Insert the application version if it does not exists
		insertName(conn, "AppVersion", "Version", version, userID);
		
		//Insert the Application Family if it does not exists
		insertName(conn, "AppFamily", "FamilyName", family, userID);
		
		//Insert the Application Executable if it does not exists
		insertName(conn, "AppExecutable", "ExecutableName", exe, userID);

		//Insert the ParameterSet if it does not exists
		//insertParameterSet(conn, psHash, psName, psVersion, psType, psAnnotation, psContent, userID);
		insertParameterSet(conn, algo, userID);
		
		//Insert the Algorithm by fetching the ID of exe, version, family and parameterset
		DBManagement.execute(conn, DBSSql.insertApplication(getID(conn, "AppExecutable", "ExecutableName", exe, true), 
									getID(conn, "AppVersion", "Version", version, true), 
									getID(conn, "AppFamily", "FamilyName", family, true), 
									getID(conn, "QueryableParameterSet", "Name", psName, true), 
									userID));
	}

	//public void insertFiles(Connection conn, Vector files, Hashtable dbsUser) throws Exception {
	public void insertFiles(Connection conn, String path, String blockName, Vector files, Hashtable dbsUser) throws Exception {
		
		//Get the User ID from USERDN
		String userID = getUserID(conn, dbsUser);
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
		String procDSID = getProcessedDSID(conn, path);
		String blockID = getBlockID(conn, blockName, true);

		
		for (int i = 0; i < files.size() ; ++i) {
			Hashtable file = (Hashtable)files.get(i);
		
			//String path = get(file, "path");
			//String blockName = get(file, "block_name");
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
		
			//FIXME Update block information at the end of this api call
			//
			//Set defaults Values
			if (isNull(fileStatus)) fileStatus = "NEW";
			if (isNull(type)) type = "EVD";
			if (isNull(valStatus)) valStatus = "NOTVALIDATED";
			
			//Insert a File status if it does not exists
			//insertName(conn, "Status", "Status", fileStatus , userID);

			//Insert a File Validation status if it does not exists
			//insertName(conn, "Status", "Status", valStatus , userID);

			//Insert a File Type if it does not exists
			//insertName(conn, "Type", "Type", type , userID);
			checkName(fileStatus, "FileStatus");
			checkName(type, "FileType");
			checkName(valStatus, "ValidationStatus");
			//Insert a File by fetching the fileStatus, type and validationStatus
			//if( (fileID = getID(conn, "Files", "LogicalFileName", lfn, false)) == null ) {
			//TODO Exception of null status or type should be catched and parsed and 
			//a proper message should be returned back to the user. Different Database can have different error message YUK
			//Status should be defaulted to something in the database itself. A wrong status may insert a dafult value.
			//User will never know about this YUK
			DBManagement.execute(conn, DBSSql.insertFile(procDSID, blockID, lfn, checksum, nOfEvents, size, fileStatus, type, valStatus, qMetaData, userID));
			//}

			//if(isNull(fileID)) fileID = getID(conn, "Files", "LogicalFileName", lfn, true);
			//Fetch the File ID that was just inseted to be used for subsequent insert of other tables only if it is needed.
			//FileID is needed if any of the other table information is provided i.e the vector size is > 0
			String fileID = "";
			if(algoVector.size() > 0 || tierVector.size() > 0 || parentVector.size() > 0 || lumiVector.size() > 0) 
				fileID = getID(conn, "Files", "LogicalFileName", lfn, true);

			//Insert FileAlgo table by fetching application ID. 
			//Use get with 2 params so that it does not do type checking, since it will be done in getID call.
			for (int j = 0; j < algoVector.size(); ++j) {
				Hashtable hashTable = (Hashtable)algoVector.get(j);
				insertMap(conn, "FileAlgoMap", "Fileid", "Algorithm", 
						fileID, 
						getAlgorithmID(conn, get(hashTable, "app_version"), 
								get(hashTable, "app_family_name"), 
								get(hashTable, "app_executable_name"),
								get(hashTable, "ps_name")), 
						userID);
			}

			//Insert FileTier table by fetching data tier ID
			for (int j = 0; j < tierVector.size(); ++j) {
				insertMap(conn,	"FileTier", "Fileid", "DataTier", 
					fileID, 
					getID(conn, "DataTier", "Name", 
						get((Hashtable)tierVector.get(j), "name") , 
						true), 
					userID);
			}
			
			//Insert FileParentage table by fetching parent File ID
			for (int j = 0; j < parentVector.size(); ++j) {
				insertMap(conn, "FileParentage", "ThisFile", "itsParent", 
						fileID, 
						getID(conn, "Files", "LogicalFileName", 
							get((Hashtable)parentVector.get(j), "lfn") , 
							true), 
						userID);
			}
			//TODO Discussion about Lumi section is needed
			//Insert FileLumi table by first inserting and then fetching Lumi Section ID
			for (int j = 0; j < lumiVector.size(); ++j) {
				Hashtable hashTable = (Hashtable)lumiVector.get(j);
				//Insert A lumi Section if it does not exists
				insertLumiSection(conn, hashTable, userID);
				insertMap(conn, "FileLumi", "Fileid", "Lumi", 
						fileID, 
						getID(conn, "LumiSection", "LumiSectionNumber", get(hashTable, "lumi_section_number") , true), 
						userID);
			}
		
		}//For loop
		//Update Block numberOfFiles and Size
		DBManagement.executeUpdate(conn, DBSSql.updateBlock(blockID));
	}


	public void insertProcessedDatatset(Connection conn, Hashtable dataset, Hashtable dbsUser) throws Exception {
		String warMsg ;
		//Get the User ID from USERDN
		String userID = getUserID(conn, dbsUser);

		String primDSName = get(dataset, "primary_datatset_name", true);
		String procDSName = get(dataset, "processed_datatset_name", true);
		String openForWriting = get(dataset, "open_for_writing", false);
		String phyGroupName = get(dataset, "physics_group_name", false);
		String phyGroupCon = get(dataset, "physics_group_convener", false);
		String status = get(dataset, "status", false);
		Vector tierVector = DBSUtil.getVector(dataset,"data_tier");
		Vector parentVector = DBSUtil.getVector(dataset,"parent");
		Vector algoVector = DBSUtil.getVector(dataset,"algorithm");
		Vector runVector = DBSUtil.getVector(dataset,"run");
	

		//Set defaults Values
		if (isNull(status)) status = "NEW";
		if (isNull(phyGroupName)) phyGroupName = "ALLGROUP";
		if (isNull(phyGroupCon)) phyGroupCon = "ANZARDN";//FIXME Some default convenor name should be used
		
		//Insert a Processed Dataset status if it does not exists
		//insertName(conn, "Status", "Status", status , userID);
		
		//Insert a Physics Group if it does not exists
		insertPhysicsGroup(conn, phyGroupName, phyGroupCon, userID);
		
		//Insert a Processed Datatset before by fetching the primDSID, status
		//if( (procDSID = getID(conn, "ProcessedDataset", "Name", procDSName, false)) == null ) {
		DBManagement.execute(conn, DBSSql.insertProcessedDatatset(
							procDSName,
							getID(conn, "PrimaryDataset", "Name", primDSName, true),
							openForWriting,
							getID(conn, "PhysicsGroup", "PhysicsGroupName", phyGroupName, true), 
							getID(conn, "Status", "Status", status, false), 
							userID));
		//} else {
			//warMsg =+ (String)"ProcessedDataset Name " + procDSName + " already exists but ignored.\n";
		//}


		//Fetch the Processed Datatset ID that was just inseted or fetched , to be used for subsequent insert of other tables.
		//FIXME this might use processed datatset with primary datatset combination instead of just proDSName
		//if(isNull(procDSID)) procDSID = getID(conn, "ProcessedDataset", "Name", procDSName, true);
		String procDSID = "";
		if(algoVector.size() > 0 || tierVector.size() > 0 || parentVector.size() > 0) 
			procDSID = getID(conn, "ProcessedDataset", "Name", procDSName, true);
		
		//Insert ProcAlgoMap table by fetching application ID. 
		for (int j = 0; j < algoVector.size(); ++j) {
			Hashtable hashTable = (Hashtable)algoVector.get(j);
			insertMap(conn, "ProcAlgoMap", "Dataset", "Algorithm", 
					procDSID, 
					getAlgorithmID(conn, get(hashTable, "app_version"), 
							get(hashTable, "app_family_name"), 
							get(hashTable, "app_executable_name"),
							get(hashTable, "ps_name")), 
					userID);
		}

		//Insert ProcDSTier table by fetching data tier ID
		for (int j = 0; j < tierVector.size(); ++j) {
			Hashtable hashTable = (Hashtable)tierVector.get(j);
			String tierName = get(hashTable, "name", true);
			//Insert DataTier if it does not exists
			insertName(conn, "DataTier", "Name", tierName , userID);
			insertMap(conn, "ProcDSTier", "Dataset", "DataTier", 
					procDSID, 
					getID(conn, "DataTier", "Name", tierName , true), 
					userID);
		}

		//Insert DatasetParentage table by fetching parent File ID
		for (int j = 0; j < parentVector.size(); ++j) {
			insertMap(conn, "DatasetParentage", "ThisDataset", "ItsParent", 
					procDSID, 
					getProcessedDSID(conn,  get((Hashtable)parentVector.get(j), "path")), 
					userID);
		}

		//Insert ProcDSRun table by fetching Run ID
		for (int j = 0; j < runVector.size(); ++j) {
			insertMap(conn, "ProcDSRuns", "Dataset", "Run", 
					procDSID, 
					getID(conn, "Runs", "RunNumber", get((Hashtable)runVector.get(j), "run_number") , true), 
					userID);
		}

	}

	

	public void insertTierInPD(Connection conn, String path, String tierName, Hashtable dbsUser) throws Exception {
		insertMap(conn, "ProcDSTier", "Dataset", "DataTier", 
				getProcessedDSID(conn, path), 
				getID(conn, "DataTier", "Name", tierName , true), 
				getUserID(conn, dbsUser));
	}

	public void insertParentInPD(Connection conn, String path, String parentPath, Hashtable dbsUser) throws Exception {
		insertMap(conn, "DatasetParentage", "ThisDataset", "ItsParent", 
					getProcessedDSID(conn, path), 
					getProcessedDSID(conn, parentPath), 
					getUserID(conn, dbsUser));
	}

	public void insertAlgoInPD(Connection conn, String path, Hashtable algo, Hashtable dbsUser) throws Exception {
		insertMap(conn, "ProcAlgoMap", "Dataset", "Algorithm", 
					getProcessedDSID(conn, path), 
					getAlgorithmID(conn, get(algo, "app_version"), 
							get(algo, "app_family_name"), 
							get(algo, "app_executable_name"),
							get(algo, "ps_name")), 
					getUserID(conn, dbsUser));
	}

	public void insertRunInPD(Connection conn, String path, String runNumber, Hashtable dbsUser) throws Exception {
		insertMap(conn, "ProcDSRuns", "Dataset", "Run", 
				getProcessedDSID(conn, path), 
				getID(conn, "Runs", "RunNumber", runNumber , true), 
				getUserID(conn, dbsUser));
	}

	public void insertTierInFile(Connection conn, String lfn, String tierName, Hashtable dbsUser) throws Exception {
		insertMap(conn,	"FileTier", "Fileid", "DataTier", 
				getID(conn, "Files", "LogicalFileName", lfn, true), 
				getID(conn, "DataTier", "Name", tierName , true), 
				getUserID(conn, dbsUser));

	}
	
	public void insertParentInFile(Connection conn, String lfn, String parentLFN, Hashtable dbsUser) throws Exception {
		insertMap(conn, "FileParentage", "ThisFile", "itsParent", 
				getID(conn, "Files", "LogicalFileName", lfn, true),
			 	getID(conn, "Files", "LogicalFileName", parentLFN, true),
				getUserID(conn, dbsUser));
	}

	public void insertAlgoInFile(Connection conn, String lfn, Hashtable algo, Hashtable dbsUser) throws Exception {
		insertMap(conn, "FileAlgoMap", "Fileid", "Algorithm", 
				getID(conn, "Files", "LogicalFileName", lfn, true), 
				getAlgorithmID(conn, get(algo, "app_version"), 
						get(algo, "app_family_name"), 
						get(algo, "app_executable_name"),
						get(algo, "ps_name")), 
				getUserID(conn, dbsUser));
	}

	public void insertLumiInFile(Connection conn, String lfn, String lsNumber, Hashtable dbsUser) throws Exception {
		insertMap(conn, "FileLumi", "Fileid", "Lumi", 
				getID(conn, "Files", "LogicalFileName", lfn, true), 
				getID(conn, "LumiSection", "LumiSectionNumber", lsNumber, true), 
				getUserID(conn, dbsUser));
	}




	public void insertLumiSection(Connection conn, Hashtable table, String userID) throws Exception {
		String lsNumber = get(table, "lumi_section_number");
		//Insert a new Lumi Section by feting the run ID 
		if( getID(conn, "LumiSection", "LumiSectionNumber", lsNumber, false) == null ) {
			DBManagement.execute(conn, DBSSql.insertLumiSection(lsNumber, 
									getID(conn, "Runs", "RunNumber", 
										get(table, "run_number", true), 
										true),
									get(table, "start_event_number", false), 
									get(table, "end_event_number", false), 
									get(table, "lumi_start_time", false), 
									get(table, "lumi_end_time", false), 
									userID));
		}
	}

	/*TODO more information needed and change in the schema required,
	 * private void insertMCDesc(Connection conn, Hashtable table, String userID) throws Exception {
		String mcDesc = get(table, "mc_channel_description", true);
		String mcProd = get(table, "mc_production", false);
		String mcChain = get(table, "mc_decay_chain", false);

		//Insert a new Lumi Section by feting the run ID 
		if( getMCDescID(conn, mcDesc, mcProd, mcChain) == null ) {
			DBManagement.execute(conn, DBSSql.insertMCDesc(mcDesc, mcProd, mcChain,	userID));
		}
	}*/

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


	//private void insertParameterSet(Connection conn, String hash, String name, String version, String type, String annotation, String content, String userID) throws Exception {
	private void insertParameterSet(Connection conn, Hashtable algo, String userID) throws Exception {
		String psName = get(algo, "ps_name", true);
		if( getID(conn, "QueryableParameterSet", "Name", psName, false) == null ) {
			DBManagement.execute(conn, DBSSql.insertParameterSet(
									get(algo, "ps_hash", false), 
									psName, 
									get(algo, "ps_version", false), 
									get(algo, "ps_type", false), 
									get(algo, "ps_annotation", false), 
									get(algo, "ps_content", false), 
									userID));
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
                System.out.println("PATH: "+path);
		//checkPath(path);
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

	private String getAlgorithmID(Connection conn, String ver, String fam, String exe, String ps) throws Exception {
		checkName(ver, "app_version");
		checkName(fam, "app_family_name");
		checkName(exe, "app_executable_name");
		checkName(ps, "ps_name");
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getAlgorithmID(ver, fam, exe, ps));
		if(!rs.next()) {
			throw new DBSException("Bad Data", "300", "No such Application " + ver + " " + fam + " " + exe + " " + ps);
		}
		return  rs.getString("id");
	}

	/*private String getMCDescID(Connection conn, String des, String prod, String chain, boolean excep) throws Exception {
		if(excep) checkName(des, "mc_channel_description");
		else if(!isNull(des) checkName(des, "mc_channel_description");
		if(!isNull(prod)) checkName(prod, "mc_production");
		if(!isNull(chain)) checkName(chain, "mc_decay_chain");
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getMCDescID(des, prod, chain));
		if(!rs.next()) {
			if(excep) throw new DBSException("Bad Data", "300", "No such MCDescription " + des + " " + prod + " " + chain);
			else return null;
		}
		return  rs.getString("id");
	}*/

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

	private String getUserID(Connection conn, Hashtable dbsUser) throws Exception {
		return (getID(conn, "Person", "DistinguishedName", 
					get(dbsUser, "user_dn", true), 
					true));
	
	}

	private void checkPath(String path) throws Exception {
		if(isNull(path)) 
			throw new DBSException("Bad Data", "300", "Null path. Expected /PRIMARY/TIER/PROCESSED");
		if (! Pattern.matches(VALID_PATH, path) ) 
			throw new DBSException("Bad Data", "300", "Invalid path " + path + ". Expected /PRIMARY/TIER/PROCESSED in format " + VALID_PATH);
		if( ! Pattern.matches(SAFE_PATH, path) ) 
			throw new DBSException("Bad Data", "300", "Invalid Characters in " + path + " Expected /PRIMARY/TIER/PROCESSED in format "+ SAFE_PATH);
	}
	
	private void checkBlock(String blockName) throws Exception {
		if(isNull(blockName)) 
			throw new DBSException("Bad Data", "300", "Null blockName. Expected /PRIMARY/PROCESSED#GUID");
		if (! Pattern.matches(VALID_BLOCK, blockName) ) 
			throw new DBSException("Bad Data", "300", "Invalid blockName " + blockName + ". Expected /PRIMARY/PROCESSED#GUID in format " + VALID_BLOCK);
		if( ! Pattern.matches(SAFE_BLOCK, blockName) ) 
			throw new DBSException("Bad Data", "300", "Invalid Characters in " + blockName + " Expected /PRIMARY/PROCESSED#GUID in format "+ SAFE_BLOCK);
	}
	
	private void checkName(String pattern, String key) throws Exception {
		if(isNull(pattern))
			throw new DBSException("Bad Data", "300", "Null Fields. Expected a " + key);
		if (! Pattern.matches(SAFE_NAME, pattern)) 
			throw new DBSException("Bad Data", "300", "Invalid Characters in " + pattern + " for " + key + " Expected "+ SAFE_NAME);
	}
	
	
	private boolean isNull(String pattern) {
		return DBSUtil.isNull(pattern);
		/*if(pattern == null) {
			return true;
		}
		if(pattern.length() < 1 ) {
			return true;
		}
		return false;*/
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
	

	private String getPattern(String pattern, String key) throws Exception {
              return "%";
        }
	private String getPattern(String pattern, int index, String key) throws Exception {
                // Gets the pattern string, checks if indexed key is possible,
                // 
                // if yes, check for its validity
                // else returns %
                // index starts at 1  
                // I suspect there is double check, here and then when writing SQL

                System.out.println("Index: "+index);  
                System.out.println("\n\n\npattern:"+pattern+"Thats");
                if ( isNull(pattern)) { System.out.println("Returning B");  return "%";}
                if ( pattern.equals("*") ) { System.out.println("Returning A"); return "%";}

                String ret_value="%";
                String[] pattern_toks = pattern.split("/");
                System.out.println("pattern_toks_length: "+pattern_toks.length);
                if (pattern_toks.length == 0)
                   ret_value = "%";
 
                if ( pattern_toks.length-1 >= index ) {
                    
                   ret_value = pattern_toks[index];          
		   ret_value = ret_value.replace('*','%');
                   System.out.println("Returning A"+ret_value);
                   checkName(ret_value, key);
                   
                }

                System.out.println("ret_value: "+ret_value); 
                return ret_value;
	}

}
