/**
 $Revision: 1.36 $"
 $Id: DBSApiLogic.java,v 1.36 2006/11/29 21:52:52 afaq Exp $"
 *
 */

package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
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

import dbs.DBSConstants;

public class DBSApiLogic {
	private static String SAFE_PATH = "[-\\w_\\.%/]+";
	//private static String SAFE_PATH = "[-A-Za-z0-9_./\\p{%}]";
	//private static String SAFE_NAME = "[-A-Za-z0-9_.]";
	private static String SAFE_WORD = "[-\\w_\\.%]+";
	private static String SAFE_STR = "[-\\w_\\.% ]+";
	private static String SAFE_BLOCK = "[-\\w_\\.%#/]+";
	private static String VALID_PATH = "^/([^/]+)/([^/]+)/([^/]+)";
	private static String VALID_BLOCK = "^/([^/]+)/([^/]+)#([^/]+)";
	//private static String VALID_PATTERN = "^/([^/]+)/([^/]+)/([^/]+)/([^/]+)/([^/]+)/([^/]+)";

	public DBSApiLogic() {
		//System.out.println("Constructor DBSApiLogic");
	}

	
	public void listPrimaryDatasets(Connection conn, Writer out, String pattern) throws Exception {
                 
		pattern = getPattern(pattern, "primary_dataset_name_pattern");
		//ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.listPrimaryDatasets(pattern));
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listPrimaryDatasets(conn, pattern);
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<primary-dataset id='" + get(rs, "ID") +
						"' annotation='" + get(rs, "ANNOTATION") +
						"' primary_name='" + get(rs, "PRIMARY_NAME") +
						"' start_date='" + get(rs, "START_DATE") +
						"' end_date='" + get(rs, "END_DATE") +
						"' creation_date='" + get(rs, "CREATION_DATE") +
						"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
						"' trigger_path_description='" + get(rs, "TRIGGER_PATH_DESCRIPTION") +
						"' mc_channel_description='" + get(rs, "MC_CHANNEL_DESCRIPTION") +
						"' mc_production='" + get(rs, "MC_PRODUCTION") +
						"' mc_decay_chain='" + get(rs, "MC_DECAY_CHAIN") +
						"' other_description='" + get(rs, "OTHER_DESCRIPTION") +
						"' type='" + get(rs, "TYPE") +
						"' created_by='" + get(rs, "CREATED_BY") +
						"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
						"'/>\n"));
			}
		} finally { close(ps, rs); }
	}

	//public void listProcessedDatasets(Connection conn, Writer out, String pattern) throws Exception {
	public void listProcessedDatasets(Connection conn, Writer out, String patternPrim, String patternDT, String patternProc, String patternVer, String patternFam, String patternExe, String patternPS) throws Exception {
		patternPrim 	= getPattern(patternPrim, "primary_datatset_name_pattern");
		patternDT 	= getPattern(patternDT, "data_tier_name_pattern");
		patternProc 	= getPattern(patternProc, "processed_datatset_name_pattern");
		patternVer	= getPattern(patternVer, "app_version");
		patternFam	= getPattern(patternFam, "app_family_name");
		patternExe	= getPattern(patternExe, "app_executable_name");
		patternPS 	= getPattern(patternPS, "parameterset_name");

		/*String patternPrim 	= getPattern(pattern, "primary_datatset_name_pattern", 1);
                String patternDT	= getPattern(pattern, "data_tier_name_pattern", 2);
                String patternProc	= getPattern(pattern, "processed_datatset_name_pattern", 3);
                String patternVer	= getPattern(pattern, "app_version", 4);
                String patternFam	= getPattern(pattern, "app_family_name", 5);
                String patternExe	= getPattern(pattern, "app_executable_name", 6);
                String patternPS	= getPattern(pattern, "parameterset_name", 7);*/
		String prevDS = "";
		String prevTier = "";
		String prevExe = "";
		String prevFam = "";
		String prevVer = "";
		String prevPS = "";
		Vector dtVec = null;
		boolean first = true;

		//ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.listProcessedDatasets(patternPrim, patternDT, patternProc, patternVer, patternFam, patternExe, patternPS));
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listProcessedDatasets(conn, patternPrim, patternDT, patternProc, patternVer, patternFam, patternExe, patternPS);
			rs =  ps.executeQuery();
			while(rs.next()) {
				//String path = "/" + get(rs, "primary_name") + "/" + get(rs, "data_tier") + "/" + get(rs, "processed_name");
				String procDSID = get(rs, "ID");
				String tier = get(rs, "DATA_TIER");
				String fam = get(rs, "APP_FAMILY_NAME");
				String exe = get(rs, "APP_EXECUTABLE_NAME");
				String ver = get(rs, "APP_VERSION");
				String pset = get(rs, "PS_NAME");
	
				if( !prevDS.equals(procDSID) && ! first) {
					out.write(((String) "</processed-dataset>\n")); 
				}
				if( !prevDS.equals(procDSID) || first) {
					out.write(((String) "<processed-dataset id='" + get(rs, "ID") + 
							//"' path='" +  get(rs, "PATH") +
							"' primary_datatset_name='" +  get(rs, "PRIMARY_DATATSET_NAME") +
							"' processed_datatset_name='" +  get(rs, "PROCESSED_DATATSET_NAME") +
							//"' open_for_writing='" + get(rs, "OPEN_FOR_WRITING") +
							"' creation_date='" + get(rs, "CREATION_DATE") +
							"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
							"' physics_group_name='" + get(rs, "PHYSICS_GROUP_NAME") +
							"' physics_group_convener='" + get(rs, "PHYSICS_GROUP_CONVENER") +
							//"' app_version='" + get(rs, "APP_VERSION") +
							//"' app_family_name='" + get(rs, "APP_FAMILY_NAME") +
							//"' app_executable_name='" + get(rs, "APP_EXECUTABLE_NAME") +
							"' created_by='" + get(rs, "CREATED_BY") +
							"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
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
				if( !prevExe.equals(exe) || !prevFam.equals(fam) || !prevVer.equals(ver) || !prevPS.equals(pset) || first) {
					out.write(((String) "\t<algorithm app_version='" + ver + 
                                                            "' app_family_name='" + fam + "' app_executable_name='" + 
                                                            exe + "' ps_name='" + pset + "'/>\n"));
					prevExe = exe;
					prevFam = fam;
					prevVer = ver;
					prevPS = pset;
				}
			}
		} finally { close(ps, rs); }

                if (!first) out.write(((String) "</processed-dataset>\n")); 
	}

	/*public void listAlgorithms(Connection conn, Writer out, String pattern) throws Exception {
		//FIXME name should be changed to hash
		String patternVer	= getPattern(pattern, "app_version", 1);
		String patternFam	= getPattern(pattern, "app_family_name", 2);
		String patternExe	= getPattern(pattern, "app_executable_name", 3);
		String patternPS 	= getPattern(pattern, "parameterset_name", 4);*/
	public void listAlgorithms(Connection conn, Writer out, String patternVer, String patternFam, String patternExe, String patternPS) throws Exception {
		System.out.println("Inside liet algo");
		//name should be changed to hash
		patternVer	= getPattern(patternVer, "app_version");
		patternFam	= getPattern(patternFam, "app_family_name");
		patternExe	= getPattern(patternExe, "app_executable_name");
		patternPS 	= getPattern(patternPS, "parameterset_name");

		//ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.listAlgorithms(patternVer, patternFam, patternExe, patternPS));
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBSSql.listAlgorithms(conn, patternVer, patternFam, patternExe, patternPS);
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<algorithm id='" + get(rs, "ID") + 
						"' app_version='" + get(rs, "APP_VERSION") +
						"' app_family_name='" + get(rs, "APP_FAMILY_NAME") +
						"' app_executable_name='" + get(rs, "APP_EXECUTABLE_NAME") +
						"' ps_name='" + get(rs, "PS_NAME") +
						"' ps_hash='" + get(rs, "PS_HASH") +
						"' creation_date='" + get(rs, "CREATION_DATE") +
						"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
						"' created_by='" + get(rs, "CREATED_BY") +
						"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
						"'/>\n"));
			}
		} finally { close(ps, rs); }

	}


	public void listRuns(Connection conn, Writer out, String path) throws Exception {
		String procDSID = getProcessedDSID(conn, path);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBSSql.listRuns(conn, procDSID);
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<run id='" + get(rs, "ID") +
					"' run_number='" + get(rs, "RUN_NUMBER") +
					"' number_of_events='" + get(rs, "NUMBER_OF_EVENTS") +
					"' number_of_lumi_sections='" + get(rs, "NUMBER_OF_LUMI_SECTIONS") +
					"' total_luminosity='" + get(rs, "TOTAL_LUMINOSITY") +
					"' store_number='" + get(rs, "STRORE_NUMBER") +
					"' start_of_run='" + get(rs, "START_OF_RUN") +
					"' end_of_run='" + get(rs, "END_OF_RUN") +
					"' creation_date='" + get(rs, "CREATION_DATE") +
					"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
					"' created_by='" + get(rs, "CREATED_BY") +
					"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
					"'/>\n"));
			}
		} finally { close(ps, rs); }

	}

	public void listTiers(Connection conn, Writer out, String path) throws Exception {
		String procDSID = getProcessedDSID(conn, path);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps =  DBSSql.listTiers(conn, procDSID);
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<data_tier id='" + get(rs, "ID") +
					"' name='" + get(rs, "NAME") +
					"' creation_date='" + get(rs, "CREATION_DATE") +
					"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
					"' created_by='" + get(rs, "CREATED_BY") +
					"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
					"'/>\n"));
			}
		} finally { close(ps, rs); }

	}

	public void listBlocks(Connection conn, Writer out, String path) throws Exception {
		String procDSID = getProcessedDSID(conn, path);
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps =  DBSSql.listBlocks(conn, procDSID);
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<block id='" + get(rs, "ID") +
					"' name='" + get(rs, "NAME") +
					"' size='" + get(rs, "BLOCKSIZE") +
					"' number_of_files='" + get(rs, "NUMBER_OF_FILES") +
					//"' open_for_writing='" + get(rs, "OPEN_FOR_WRITING") +
					"' creation_date='" + get(rs, "CREATION_DATE") +
					"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
					"' created_by='" + get(rs, "CREATED_BY") +
					"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
					"'/>\n"));
			}
		} finally { close(ps, rs); }

	}

	public void listFiles(Connection conn, Writer out, String path, String blockName, String patternLFN) throws Exception {

		patternLFN = getPattern(patternLFN, "pattern_lfn");
                String prevTier = "";
		boolean first = true;
		String prevFileID = "";
		
		String procDSID = null;
		String blockID = null;
		if(!isNull(path)) {
			procDSID = getProcessedDSID(conn, path);
		}
		if(!isNull(blockName)) {
			blockID = getBlockID(conn, blockName, true);
		}
		if(blockID == null && procDSID == null) {
			throw new DBSException("Bad Data", "300", "Null Fields. Expected either a Processed Dataset or a Block");
		}
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listFiles(conn, procDSID, blockID, patternLFN);
			rs =  ps.executeQuery();
			while(rs.next()) {
				String fileID = get(rs, "ID");
				String tier = get(rs, "DATA_TIER");

				if( !prevFileID.equals(fileID) && ! first) {
					out.write( (String) "</file> \n");
				}
                         
				if( !prevFileID.equals(fileID) || first) {  
					out.write(((String) "<file id='" + fileID +
					"' lfn='" + get(rs, "LFN") +
					"' checksum='" + get(rs, "CHECKSUM") +
					"' size='" + get(rs, "FILESIZE") +
					"' queryable_meta_data='" + get(rs, "QUERYABLE_META_DATA") +
					"' number_of_events='" + get(rs, "NUMBER_OF_EVENTS") +
					"' validation_status='" + get(rs, "VALIDATION_STATUS") +
					"' type='" + get(rs, "TYPE") +
					"' status='" + get(rs, "STATUS") +
					"' block_name='" + get(rs, "BLOCK_NAME") +
					"' creation_date='" + get(rs, "CREATION_DATE") +
					"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
					"' created_by='" + get(rs, "CREATED_BY") +
					"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
					"'>\n"));
					//"'/>\n"));
					first = false;
               	                 prevFileID = fileID;
				}
      
 				if( !prevTier.equals(tier) || first ) {
					out.write(((String) "\t<data_tier name='" + tier + "'/>\n"));
					prevTier = tier;
				}
			}
		} finally { close(ps, rs); }

                if (!first) out.write(((String) "</file>\n"));
	}

	
	public void insertPrimaryDataset(Connection conn, Writer out, Hashtable dataset, Hashtable dbsUser) throws Exception {
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
		insertName(conn, "PrimaryDSType", "Type", type , userID);
		
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
		PreparedStatement ps = null;
		try {
			ps = DBSSql.insertPrimaryDataset(conn, 
					ann,
					name,
					"0",//FIXME Should not be in the schema
					startDate,
					endDate,
					getID(conn, "PrimaryDSType", "Type", type, true), 
					userID);
			ps.execute();
		} finally { close(ps); }

		//} else {
			//Append Warnning message that run eixts
		//}

	}

	public void insertRun(Connection conn, Writer out, Hashtable run, Hashtable dbsUser) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = DBSSql.insertRun(conn, 
				get(run, "run_number", true),
				get(run, "number_of_events", true),
				get(run, "number_of_lumi_sections", true),
				get(run, "total_luminosity", true),
				get(run, "store_number", true),
				get(run, "start_of_run", false),
				get(run, "end_of_run", false),
				getUserID(conn, dbsUser));
			ps.execute();
		} finally { close(ps); }

	}

	public void insertTier(Connection conn, Writer out, String tierName, Hashtable dbsUser) throws Exception {
		insertName(conn, "DataTier", "Name", tierName , getUserID(conn, dbsUser));
	}

	public void insertBlock(Connection conn, Writer out, Hashtable block, Hashtable dbsUser) throws Exception {
		String path = get(block, "path");
		String name = get(block, "name");
		String openForWriting = get(block, "open_for_writing", false);
	
		String procDSID = getProcessedDSID(conn, path);//Getting ID before spliting the path will type chech the path also.
		//Set defaults Values
		String[] data = path.split("/");
		if (isNull(name)) name = "/" + data[1] + "/" + data[3] +"#" + UUID.randomUUID(); 
		if (isNull(openForWriting)) openForWriting = "1";
		checkBlock(name);
		PreparedStatement ps = null;
		try {
			ps = DBSSql.insertBlock(conn,
				"0",// A new block should always have 0 size
				name,
				procDSID,
				"0",// A new block should always have 0 files
				openForWriting,
				getUserID(conn, dbsUser));

			ps.execute();
		} finally { close(ps); }

		//FIXME Return blockNameback to the user
		out.write("<block block_name='" + name + "'/>");

	}


	public void insertAlgorithm(Connection conn, Writer out, Hashtable algo, Hashtable dbsUser) throws Exception {
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
		PreparedStatement ps = null;
		try {
			ps = DBSSql.insertApplication(conn, 
				getID(conn, "AppExecutable", "ExecutableName", exe, true), 
				getID(conn, "AppVersion", "Version", version, true), 
				getID(conn, "AppFamily", "FamilyName", family, true), 
				getID(conn, "QueryableParameterSet", "Name", psName, true), 
				userID);
			ps.execute();
		} finally { close(ps); }

	}

	//public void insertFiles(Connection conn, Vector files, Hashtable dbsUser) throws Exception {
	public void insertFiles(Connection conn, Writer out, String path, String blockName, Vector files, Hashtable dbsUser) throws Exception {
		
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
			checkWord(fileStatus, "FileStatus");
			checkWord(type, "FileType");
			checkWord(valStatus, "ValidationStatus");
			//Insert a File by fetching the fileStatus, type and validationStatus
			//if( (fileID = getID(conn, "Files", "LogicalFileName", lfn, false)) == null ) {
			//TODO Exception of null status or type should be catched and parsed and 
			//a proper message should be returned back to the user. Different Database can have different error message YUK
			//Status should be defaulted to something in the database itself. A wrong status may insert a dafult value.
			//User will never know about this YUK
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertFile(conn, 
						procDSID, 
						blockID, 
						lfn, 
						checksum, 
						nOfEvents, 
						size, 
						fileStatus, 
						type, 
						valStatus, 
						qMetaData, 
						userID);
				ps.execute();
			} finally { close(ps); }

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
				insertMap(conn, "FileAlgo", "Fileid", "Algorithm", 
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
				insertLumiSection(conn, out, hashTable, userID);
				insertMap(conn, "FileLumi", "Fileid", "Lumi", 
						fileID, 
						getID(conn, "LumiSection", "LumiSectionNumber", get(hashTable, "lumi_section_number") , true), 
						userID);
			}
		
		}//For loop
		//Update Block numberOfFiles and Size
		PreparedStatement ps = null;
		try {
			ps = DBSSql.updateBlock(conn, blockID);
			ps.executeUpdate();
		} finally { close(ps); }

	}


	public void insertProcessedDataset(Connection conn, Writer out, Hashtable dataset, Hashtable dbsUser) throws Exception {
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
		if (isNull(status)) status = "VALID";
		if (isNull(phyGroupName)) phyGroupName = "ALLGROUP";
		if (isNull(phyGroupCon)) phyGroupCon = "ANZARDN";//FIXME Some default convenor name should be used
		
		//Insert a Processed Dataset status if it does not exists
		//insertName(conn, "Status", "Status", status , userID);
		
		//Insert a Physics Group if it does not exists
		insertPhysicsGroup(conn, out,  phyGroupName, phyGroupCon, userID);
		
		//Insert a Processed Datatset before by fetching the primDSID, status
		//if( (procDSID = getID(conn, "ProcessedDataset", "Name", procDSName, false)) == null ) {
		PreparedStatement ps = null;
		try {
			ps = DBSSql.insertProcessedDatatset(conn, 
					procDSName,
					getID(conn, "PrimaryDataset", "Name", primDSName, true),
					openForWriting,
					getID(conn, "PhysicsGroup", "PhysicsGroupName", phyGroupName, true), 
					getID(conn, "ProcDSStatus", "Status", status, true), 
					userID);
			ps.execute();
		} finally { close(ps); }

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
			insertMap(conn, "ProcAlgo", "Dataset", "Algorithm", 
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

	

	public void insertTierInPD(Connection conn, Writer out, String path, String tierName, Hashtable dbsUser) throws Exception {
		insertMap(conn, "ProcDSTier", "Dataset", "DataTier", 
				getProcessedDSID(conn, path), 
				getID(conn, "DataTier", "Name", tierName , true), 
				getUserID(conn, dbsUser));
	}

	public void insertParentInPD(Connection conn, Writer out, String path, String parentPath, Hashtable dbsUser) throws Exception {
		insertMap(conn, "DatasetParentage", "ThisDataset", "ItsParent", 
					getProcessedDSID(conn, path), 
					getProcessedDSID(conn, parentPath), 
					getUserID(conn, dbsUser));
	}

	public void insertAlgoInPD(Connection conn, Writer out, String path, Hashtable algo, Hashtable dbsUser) throws Exception {
		insertMap(conn, "ProcAlgo", "Dataset", "Algorithm", 
					getProcessedDSID(conn, path), 
					getAlgorithmID(conn, get(algo, "app_version"), 
							get(algo, "app_family_name"), 
							get(algo, "app_executable_name"),
							get(algo, "ps_name")), 
					getUserID(conn, dbsUser));
	}

	public void insertRunInPD(Connection conn, Writer out, String path, String runNumber, Hashtable dbsUser) throws Exception {
		insertMap(conn, "ProcDSRuns", "Dataset", "Run", 
				getProcessedDSID(conn, path), 
				getID(conn, "Runs", "RunNumber", runNumber , true), 
				getUserID(conn, dbsUser));
	}

	public void insertTierInFile(Connection conn, Writer out, String lfn, String tierName, Hashtable dbsUser) throws Exception {
		insertMap(conn,	"FileTier", "Fileid", "DataTier", 
				getID(conn, "Files", "LogicalFileName", lfn, true), 
				getID(conn, "DataTier", "Name", tierName , true), 
				getUserID(conn, dbsUser));

	}
	
	public void insertParentInFile(Connection conn, Writer out, String lfn, String parentLFN, Hashtable dbsUser) throws Exception {
		insertMap(conn, "FileParentage", "ThisFile", "itsParent", 
				getID(conn, "Files", "LogicalFileName", lfn, true),
			 	getID(conn, "Files", "LogicalFileName", parentLFN, true),
				getUserID(conn, dbsUser));
	}

	public void insertAlgoInFile(Connection conn, Writer out, String lfn, Hashtable algo, Hashtable dbsUser) throws Exception {
		insertMap(conn, "FileAlgo", "Fileid", "Algorithm", 
				getID(conn, "Files", "LogicalFileName", lfn, true), 
				getAlgorithmID(conn, get(algo, "app_version"), 
						get(algo, "app_family_name"), 
						get(algo, "app_executable_name"),
						get(algo, "ps_name")), 
				getUserID(conn, dbsUser));
	}

	public void insertLumiInFile(Connection conn, Writer out, String lfn, String lsNumber, Hashtable dbsUser) throws Exception {
		insertMap(conn, "FileLumi", "Fileid", "Lumi", 
				getID(conn, "Files", "LogicalFileName", lfn, true), 
				getID(conn, "LumiSection", "LumiSectionNumber", lsNumber, true), 
				getUserID(conn, dbsUser));
	}




        private void insertLumiSection(Connection conn, Writer out, Hashtable table, String userID) throws Exception {
		String lsNumber = get(table, "lumi_section_number", true);
		//Insert a new Lumi Section by feting the run ID 
		if( getID(conn, "LumiSection", "LumiSectionNumber", lsNumber, false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertLumiSection(conn,
						lsNumber,
						getID(conn, "Runs", "RunNumber",
							get(table, "run_number", true),
							true),
						get(table, "start_event_number", true),
						get(table, "end_event_number", true),
						get(table, "lumi_start_time", false),
						get(table, "lumi_end_time", false),
						userID);
				ps.execute();
			} finally { close(ps); }

		} else {
			writeWarning(out, "Already Exists", "401", "LumiSection "+lsNumber+" ALready Exists");
		}
	}


	private static void writeWarning(Writer out, String message, String code, String detail) throws Exception {
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



	public void insertLumiSection(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {
                insertLumiSection(conn, out, table, getUserID(conn, dbsUser));
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
		//System.out.println("inserting table " + table + "  key " + key + " value " + value);
		if(isNull(value)) throw new DBSException("Bad Data", "300", "Null Field " + key );
		if(isNull(userID)) throw new DBSException("Bad Data", "300", "Null Field UserDN ");
		if( getID(conn, table, key, value, false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertName(conn, table, key, value, userID);
				ps.execute();
			} finally { close(ps); }
		}
	}
	
	
	private void insertMap(Connection conn, String tableName, String key1, String key2, String value1, String value2, String userID) throws Exception {
		if( getMapID(conn, tableName, key1, key2, value1, value2, false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertMap(conn, tableName, key1, key2, value1, value2, userID);
				ps.execute();
			} finally { close(ps); }
		}

	}


	//private void insertParameterSet(Connection conn, String hash, String name, String version, String type, String annotation, String content, String userID) throws Exception {
	private void insertParameterSet(Connection conn, Hashtable algo, String userID) throws Exception {
		String psName = get(algo, "ps_name", true);
		if( getID(conn, "QueryableParameterSet", "Name", psName, false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertParameterSet(conn,
						get(algo, "ps_hash", true), 
						psName, 
						getStr(algo, "ps_version", true), 
						getStr(algo, "ps_type", true), 
						getStr(algo, "ps_annotation", true), 
                                                //FIXME We are allowing every thing in content, need to fix it
						get(algo, "ps_content"), 
						userID);
				ps.execute();
			} finally { close(ps); }
		}
	}

	public void insertPhysicsGroup(Connection conn, Writer out, String name, String phyGroupCon, String userID) throws Exception {
		//Insert a new Person if it does not exists
		insertPerson(conn, out,  "", phyGroupCon, "", userID); //FIXME Get userName and contactInfo also
		if( getID(conn, "PhysicsGroup", "PhysicsGroupName", name, false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertPhysicsGroup(conn,
					name, 
					getID(conn, "Person", "DistinguishedName", phyGroupCon, true), 
					userID);
				ps.execute();
			} finally { close(ps); }
		}
	}

	public void insertPerson(Connection conn, Writer out, String userName, String userDN, String contactInfo, String userID) throws Exception {
		if (isNull(userID)) userID = "0";//0 is user not created by anyone
		if( getID(conn, "Person", "DistinguishedName", userDN , false) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertPerson(conn, userName, userDN, contactInfo,  userID);
				ps.execute();
			} finally { close(ps); }
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
		checkWord(prim, "primary_dataset_name");
		checkWord(dt, "data_tier");
		checkWord(proc, "processed_dataset_name");
		//ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getProcessedDSID(prim, dt, proc));
		String id = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBSSql.getProcessedDSID(conn, prim, dt, proc);
			rs =  ps.executeQuery();
			if(!rs.next()) {
				throw new DBSException("Bad Data", "300", "No such processed dataset /" + prim + "/" + dt + "/" +proc );
			}
			id = get(rs, "ID");
		} finally { close(ps, rs); }
		return  id;
	}

	private String getAlgorithmID(Connection conn, String ver, String fam, String exe, String psName) throws Exception {
		checkWord(ver, "app_version");
		checkWord(fam, "app_family_name");
		checkWord(exe, "app_executable_name");
		checkWord(psName, "ps_name");
		//ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getAlgorithmID(ver, fam, exe, ps));
		String id = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps =  DBSSql.getAlgorithmID(conn, ver, fam, exe, psName);
			rs =  ps.executeQuery();
			if(!rs.next()) {
				throw new DBSException("Bad Data", "300", "No such Application " + ver + " " + fam + " " + exe + " " + psName);
			}
			id = get(rs, "ID");
		} finally { close(ps, rs); }
		return  id;
	}

	/*private String getMCDescID(Connection conn, String des, String prod, String chain, boolean excep) throws Exception {
		if(excep) checkWord(des, "mc_channel_description");
		else if(!isNull(des) checkWord(des, "mc_channel_description");
		if(!isNull(prod)) checkWord(prod, "mc_production");
		if(!isNull(chain)) checkWord(chain, "mc_decay_chain");
		PreparedStatement ps = null;
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getMCDescID(des, prod, chain));
		if(!rs.next()) {
			if(excep) throw new DBSException("Bad Data", "300", "No such MCDescription " + des + " " + prod + " " + chain);
			else return null;
		}
		return  get(rs, "id");
	}*/

	private String getBlockID(Connection conn, String name, boolean excep) throws Exception {
		checkBlock(name);
		//ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getID( "Block", "Name", name));
		String id = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBSSql.getID(conn, "Block", "Name", name);
			rs =  ps.executeQuery();
			if(!rs.next()) {
				if(excep) throw new DBSException("Bad Data", "300", "No such Block : Name : "  + name );
				else return null;
			}
			id = get(rs, "ID");
		} finally { close(ps, rs); }
		return  id;
	}

	private String getID(Connection conn, String tableName, String key, String value, boolean excep) throws Exception {
		if(isNull(tableName) || isNull(key) || isNull(value)) return null;
		if (excep) checkWord(value, key);
		else if(!isNull(value)) checkWord(value, key);

		//ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getID(tableName, key, value));
		String id = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps =  DBSSql.getID(conn, tableName, key, value);
			rs =  ps.executeQuery();
		//System.out.println("tableName "+ tableName+ " key "+ key + " value " + value + " excep "+ excep);
			if(!rs.next()) {
				if(excep) throw new DBSException("Bad Data", "300", "No such " + tableName + " : " + key + " : " + value );
				else return null;
			}
			id = get(rs, "ID");
		} finally { close(ps, rs); }
		return  id;
	}

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
				if(excep) throw new DBSException("Bad Data", "300", "No such " + tableName + " : " + key1 + " : " + value1 + " : " + key2 + " : " + value2);
				else return null;
			}
			id = get(rs, "ID");
		} finally { close(ps, rs); }
		return  id;
	}

	private String getUserID(Connection conn, Hashtable dbsUser) throws Exception {
		String id = "";
		String userDN = get(dbsUser, "user_dn", true);
		if ( (id = getID(conn, "Person", "DistinguishedName", userDN , false)) == null) {
			//FIXME instead of passing null for out stream writer , pass teh actual stream
			insertPerson(conn, null,  "", userDN, "", ""); //FIXME Get userName and contactInfo also and the userID shoudl be decicde?
			id = getID(conn, "Person", "DistinguishedName", userDN , true);
		}
		return id;
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
	
	private void checkWord(String pattern, String key) throws Exception {
		if(isNull(pattern))
			throw new DBSException("Bad Data", "300", "Null Fields. Expected a " + key);
		if (! Pattern.matches(SAFE_WORD, pattern)) 
			throw new DBSException("Bad Data", "300", "Invalid Characters in " + pattern + " for " + key + " Expected "+ SAFE_WORD);
	}
	

        private void checkString(String pattern, String key) throws Exception {
                if(isNull(pattern))
                        throw new DBSException("Bad Data", "300", "Null Fields. Expected a " + key);
                if (! Pattern.matches(SAFE_STR, pattern))
                        throw new DBSException("Bad Data", "300", "Invalid Characters in " + pattern + " for " + key + " Expected "+ SAFE_STR);
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
		if(excep) checkWord(value, key);
		else if(! isNull(value)) checkWord(value, key);
		return value;
	}

        private String getStr(Hashtable table, String key, boolean excep) throws Exception{
                //System.out.println(key);
                String value = DBSUtil.get(table, key);
                if(excep) checkString(value, key);
                else if(! isNull(value)) checkString(value, key);
                return value;
        }


	private String get(Hashtable table, String key) {
		//System.out.println(key);
		return DBSUtil.get(table, key);
	}

	private String get(ResultSet rs, String key) throws Exception {

                //System.out.println("Looking for :"+key); 
		String value = rs.getString(key);
                //System.out.println("Value is: "+value);
		if(isNull(value)) return "";
		return value;
	}
	private String getPattern(String pattern, String key) throws Exception {
		if(isNull(pattern))  return "%";
		pattern = pattern.replace('*','%');
		checkWord(pattern,key);
        	return pattern;
	}

	private String getPattern(String pattern, String key, int index) throws Exception {
                // Gets the pattern string, checks if indexed key is possible,
                // 
                // if yes, check for its validity
                // else returns %
                // index starts at 1  
                // I suspect there is double check, here and then when writing SQL
                if (isNull(pattern)) return "%";
                String[] tokens = pattern.split("/");
		if (tokens.length == 0) return "%";
		if (tokens.length-1 >= index )
			return getPattern(tokens[index], key);            
                return "%";
	}

	private void close(PreparedStatement ps, ResultSet rs) throws Exception {
		if (rs != null) rs.close();
		close(ps);
	}
	private void close(PreparedStatement ps) throws Exception {
		if (ps != null) ps.close();
	}


}
