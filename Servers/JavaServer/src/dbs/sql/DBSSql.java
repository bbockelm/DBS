
/**
 $Revision: 1.3 $"
 $Id: DBSXMLParser.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
 *
 */
package dbs.sql;
import java.util.Hashtable;
import dbs.util.DBSUtil;

public class DBSSql {
	/**
	 * 
	 */
	//public static String getDual() {
	//	return "SELECT 1 FROM dual";
	//}

       	public static String insertName(String table, String key, String value, String userID) {
		String sql = "INSERT INTO " + table + " ( \n" +
				         key + ", \n" +
			        	"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + value + "', \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}
	
	public static String insertMap(String table, String key1, String key2, String value1, String value2, String userID) {
		String sql = "INSERT INTO " + table + " ( \n" +
					key1 + ", \n" +
					key2 + ", \n" +
					"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + value1 + "', \n" +
					"'" + value2 + "' , \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}
	

	public static String insertPrimaryDataset(String ann, String name, String descID, String startDate, String endDate, String typeID , String userID) {
		String sql = "INSERT INTO PrimaryDataset ( \n" +
				        "Annotation, \n" +
				        "Name, \n" +
				        "Description, \n" +
				        "StartDate, \n" +
				        "EndDate, \n" +
				        "Type, \n" +
			        	"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + ann + "', \n" +
					"'" + name + "', \n" +
					"'" + descID + "', \n" +
					"'" + startDate + "', \n" +
					"'" + endDate + "', \n" +
					"'" + typeID + "', \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String insertRun(String runNumber, String nOfEvents, String nOfLumiSections, String totalLumi, String storeNumber, String startOfRun, String endOfRun, String userID) {
		String sql = "INSERT INTO Runs ( \n" +
				        "RunNumber, \n" +
				        "NumberOfEvents, \n" +
				        "NumberOfLumiSections, \n" +
				        "TotalLuminosity, \n" +
				        "StoreNumber, \n" +
				        "StartOfRun, \n" +
				        "EndOfRun, \n" +
			        	"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + runNumber + "', \n" +
					"'" + nOfEvents + "', \n" +
					"'" + nOfLumiSections + "', \n" +
					"'" + totalLumi + "', \n" +
					"'" + storeNumber + "', \n" +
					"'" + startOfRun + "', \n" +
					"'" + endOfRun + "', \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String insertBlock(String size, String name, String procDSID, String nOfFiles, String openForWriting, String userID) {
		String sql = "INSERT INTO Block ( \n" +
				        "BlockSize, \n" +
				        "Name, \n" +
				        "Dataset, \n" +
				        "NumberOfFiles, \n" +
				        "OpenForWriting, \n" +
			        	"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + size + "', \n" +
					"'" + name + "', \n" +
					"'" + procDSID + "', \n" +
					"'" + nOfFiles + "', \n" +
					"'" + openForWriting + "', \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String insertLumiSection(String lsNumber, String runID, String startEvNumber, String endEvNumber, String lStartTime, String lEndTime, String userID) {
		String sql = "INSERT INTO LumiSection ( \n" +
				        "LumiSectionNumber, \n" +
				        "RunNumber, \n" +
				        "StartEventNumber, \n" +
				        "EndEventNumber, \n" +
				        "LumiStartTime, \n" +
				        "LumiEndTime, \n" +
			        	"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + lsNumber + "', \n" +
					"'" + runID + "', \n" +
					"'" + startEvNumber + "', \n" +
					"'" + endEvNumber + "', \n" +
					"'" + lStartTime + "', \n" +
					"'" + lEndTime + "', \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}
		       
        public static String insertPerson(String userName, String userDN, String contactInfo, String userID) {
		String sql = "INSERT INTO Pserson ( \n" +
					"Name, \n" +
				        "DistinguishedName, \n" +
					"ContactInfo, \n" +
					"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + userName + "', \n" +
					"'" + userDN + "', \n" +
					"'" + contactInfo + "', \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}



	public static String insertParameterSet(String hash, String name, String version, String type, String annotation, String content, String userID) {
		String sql = "INSERT INTO QueryableParameterSet ( \n" +
				        "Hash, \n" +
				        "Name, \n" +
				        "Version, \n" +
				        "Type, \n" +
				        "Annotation, \n" +
				        "Content, \n" +
			        	"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + hash + "', \n" +
					"'" + name + "', \n" +
					"'" + version + "', \n" +
					"'" + type + "', \n" +
					"'" + annotation + "', \n" +
					"'" + content + "', \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}
				       
        public static String insertApplication(String exeID, String versionID, String familyID, String psID, String userID) {
		String sql = "INSERT INTO AlgorithmConfig ( \n" +
					"ExecutableName, \n" +
				        "ApplicationVersion, \n" +
					"ApplicationFamily, \n" +
					"ParameterSetID, \n" +
					"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + exeID + "', \n" +
					"'" + versionID + "', \n" +
					"'" + familyID + "', \n" +
					"'" + psID + "', \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	// SQL for inserting ProcessedDatatset and its related tables.
	// ____________________________________________________

	public static String insertProcessedDatatset(String name, String primDSID, String openForWriting, String phyGroupID, String statusID, String userID) {
		String sql = "INSERT INTO ProcessedDataset ( \n" +
					"Name, \n" +
					"PrimaryDataset, \n" +
					"OpenForWriting, \n" +
					"PhysicsGroup, \n" +
					"Status, \n" +
					"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + name + "', \n" +
					"'" + primDSID + "', \n" +
					"'" + openForWriting + "', \n" +
					"'" + phyGroupID + "', \n" +
					"'" + statusID + "', \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}
	
	public static String insertPhysicsGroup(String name, String conID, String userID) {
		String sql = "INSERT INTO PhysicsGroup ( \n" +
					"PhysicsGroupName, \n" +
					"PhysicsGroupConvener, \n" +
					"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + name + "', \n" +
					"'" + conID + "', \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}


	// SQL for inserting File and its related tables.
	// ____________________________________________________
	
	public static String insertFile(String procDSID, String blockID, String lfn, String checksum, String nOfEvents, String size, String fileStatusID, String typeID, String valStatusID, String qMetaData, String userID) {
		String sql = "INSERT INTO Files ( \n" +
					"LogicalFileName, \n" +
					"Dataset, \n" +
					"Block, \n" +
					"Checksum, \n" +
					"NumberOfEvents, \n" +
					"FileSize, \n" +
					"Status, \n" +
					"FileType, \n" +
					"ValidationStatus, \n" +
					"QueryableMetadata, \n" +
					"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + lfn + "', \n" +
					"'" + procDSID + "', \n" +
					"'" + blockID + "', \n" +
					"'" + checksum + "', \n" +
					"'" + nOfEvents + "', \n" +
					"'" + size + "', \n" +
					"'" + fileStatusID + "', \n" +
					"'" + typeID + "', \n" +
					"'" + valStatusID + "', \n" +
					"'" + qMetaData + "', \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}
	

	// ____________________________________________________
	
	
	

	
	
	
	public static String listPrimaryDatasets(String pattern) {
		String sql = "SELECT pd.ID as id, \n" +
			"pd.Annotation as annotation, \n" +
			"pd.Name as primary_name, \n" +
			"pd.StartDate as start_date, \n"  +
			"pd.EndDate as end_date, \n" + 
			"pd.CreationDate as creation_date, \n" +
			"pd.LastModificationDate as last_modification_date, \n" +
			"tpd.TriggerPathDescription as trigger_path_description, \n" +
			"md.MCChannelDescription as mc_channel_description, \n" +
			"md.MCProduction as mc_production, \n" +
			"md.MCDecayChain as mc_decay_chain, \n" +
			"od.Description as other_description, \n" +
			"ty.Type as type, \n" +
			"percb.DistinguishedName as created_by, \n" +
			"perlm.DistinguishedName as last_modified_by \n" +
			"FROM PrimaryDataset pd \n" +
			"LEFT OUTER JOIN Type ty \n" +
				"ON ty.id = pd.Type \n" +
			"LEFT OUTER JOIN PrimaryDatasetDescription pdd \n" +
				"ON pdd.id = pd.Description \n" +
			"LEFT OUTER JOIN TriggerPathDescription tpd \n" +
				"ON tpd.id = pdd.TriggerDescriptionID \n" + 
			"LEFT OUTER JOIN MCDescription md \n" +
				"ON md.id = pdd.MCChannelDescriptionID \n" +
			"LEFT OUTER JOIN OtherDescription od \n" +
				"ON od.id = pdd.OtherDescriptionID \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = pd.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = pd.LastModifiedBy \n";
		if(pattern != null) {
			sql += "WHERE pd.Name like '" + pattern + "'\n" +
				"ORDER BY primary_name";
		}
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String listProcessedDatasets(String patternPrim, String patternDt, String patternProc, String patternVersion, String patternFamily, String patternExe) {
		String sql = "SELECT procds.id as id, \n" +
			"concat( \n" +
				"concat( \n" +
					"concat( \n" +
						"concat( \n" +
							"concat('/', primds.Name \n" +
							"),'/' \n" +
						"),dt.Name \n" +
					"),'/' \n" +
				"), procds.name \n" +
			") as path, \n" +
			"procds.OpenForWriting as open_for_writing, \n" +
			"procds.CreationDate as creation_date, \n" +
			"procds.LastModificationDate as last_modification_date, \n" +
			"pg.PhysicsGroupName as physics_group_name, \n" +
			"perpg.DistinguishedName as physics_group_convener, \n" +
			"av.Version as app_version, \n" +
			"af.FamilyName as app_family_name, \n" +
			"ae.ExecutableName as app_executable_name, \n" +
			"percb.DistinguishedName as created_by, \n" +
			"perlm.DistinguishedName as last_modified_by \n" +
			"FROM ProcessedDataset procds \n" +
			"LEFT OUTER JOIN PrimaryDataset primds \n" +
				"ON primds.id = procds.PrimaryDataset \n" +
			"LEFT OUTER JOIN ProcDSTier pdst \n" +
				"ON pdst.Dataset = procds.id \n" +
			"LEFT OUTER JOIN DataTier dt \n" +
				"ON dt.id = pdst.DataTier " +
			"LEFT OUTER JOIN PhysicsGroup pg \n" +
				"ON pg.id = procds.PhysicsGroup \n" +
			"LEFT OUTER JOIN Person perpg \n" +
				"ON perpg.id = pg.PhysicsGroupConvener \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = procds.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = procds.LastModifiedBy \n" +
			"LEFT OUTER JOIN ProcAlgoMap pam \n" +
				"ON pam.Dataset = procds.id \n" +
			"LEFT OUTER JOIN AlgorithmConfig algo \n" +
				"ON algo.id = pam.Algorithm \n" +
			"LEFT OUTER JOIN AppVersion av \n" +
				"ON av.id = algo.ApplicationVersion \n" +
			"LEFT OUTER JOIN AppFamily af \n" +
				"ON af.id = algo.ApplicationFamily \n" +
			"LEFT OUTER JOIN AppExecutable ae \n" +
				"ON ae.id = algo.ExecutableName \n";

		if(patternPrim == null) patternPrim = "%";
		if(patternDt == null) patternDt = "%";
		if(patternProc == null) patternProc = "%";
		if(patternVersion == null) patternVersion = "%";
		if(patternFamily == null) patternFamily = "%";
		if(patternExe == null) patternExe = "%";

		sql += "WHERE primds.Name like '" + patternPrim + "' \n" +
			"and dt.Name like '" + patternDt + "' \n" +
			"and procds.name like '" + patternProc + "' \n" +
			"and av.Version like '" + patternVersion + "' \n" +
			"and af.FamilyName like '" + patternFamily + "' \n" +
			"and ae.ExecutableName like '" + patternExe + "' \n" +
			"ORDER BY path";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String listApplications(String patternVersion, String patternFamily, String patternExe) {
		String sql = "SELECT algo.id as id, \n" +
			"av.Version as app_version, \n" +
			"af.FamilyName as app_family_name, \n" +
			"ae.ExecutableName as app_executable_name, \n" +
			"algo.CreationDate as creation_date, \n" +
			"algo.LastModificationDate as last_modification_date, \n" +
			"percb.DistinguishedName as created_by, \n" +
			"perlm.DistinguishedName as last_modified_by \n" +
			"FROM AlgorithmConfig algo \n" +
			"JOIN AppVersion av \n" +
				"ON av.id = algo.ApplicationVersion \n" +
			"JOIN AppFamily af \n" +
				"ON af.id = algo.ApplicationFamily \n" +
			"JOIN AppExecutable ae \n" +
				"ON ae.id = algo.ExecutableName \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = algo.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = algo.LastModifiedBy \n";

		if(patternVersion == null) patternVersion = "%";
		if(patternFamily == null) patternFamily = "%";
		if(patternExe == null) patternExe = "%";

		sql += "WHERE av.Version like '" + patternVersion + "' \n" +
			"and af.FamilyName like '" + patternFamily + "' \n" +
			"and ae.ExecutableName like '" + patternExe + "' \n" +
			"ORDER BY app_family_name, app_executable_name, app_version";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String listRuns(String procDSID) {
		String sql = "SELECT run.ID as id, \n " +
			"run.RunNumber as run_number, \n" +
			"run.NumberOfEvents as number_of_events, \n" +
			"run.NumberOfLumiSections as number_of_lumi_sections, \n" +
			"run.TotalLuminosity as total_luminosity, \n" +
			"run.StoreNumber as strore_number, \n" +
			"run.StartOfRun as start_of_run, \n" +
			"run.EndOfRun as end_of_run, \n" +
			"run.CreationDate as creation_date, \n" +
			"run.LastModificationDate as last_modification_date, \n" +
			"percb.DistinguishedName as created_by, \n" +
			"perlm.DistinguishedName as last_modified_by \n" +
			"FROM Runs run \n" +
			"JOIN ProcDSRuns pdsr \n" +
				"ON pdsr.Run = run.id \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = run.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = run.LastModifiedBy \n";

		if(procDSID != null) {
			sql += "WHERE pdsr.Dataset = " + procDSID + " \n";
		}
		sql +=	"ORDER BY run_number";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String listTiers(String procDSID) {
		String sql = "SELECT dt.ID as id, \n " +
			"dt.Name as name, \n" +
			"dt.CreationDate as creation_date, \n" +
			"dt.LastModificationDate as last_modification_date, \n" +
			"percb.DistinguishedName as created_by, \n" +
			"perlm.DistinguishedName as last_modified_by \n" +
			"FROM DataTier dt \n" +
			"JOIN ProcDSTier pdst \n" +
				"ON pdst.DataTier = dt.id \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = dt.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = dt.LastModifiedBy \n";

		if(procDSID != null) {
			sql += "WHERE pdst.Dataset = " + procDSID + " \n";
		}
		sql +=	"ORDER BY name";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String listBlocks(String procDSID) {
		String sql = "SELECT b.ID as id, \n " +
			"b.Name as name, \n" +
			"b.Size as size, \n" +
			"b.NumberOfFiles as number_of_files, \n" +
			"b.OpenForWriting as open_for_writing, \n" +
			"b.CreationDate as creation_date, \n" +
			"b.LastModificationDate as last_modification_date, \n" +
			"percb.DistinguishedName as created_by, \n" +
			"perlm.DistinguishedName as last_modified_by \n" +
			"FROM Block b \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = b.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = b.LastModifiedBy \n";

		if(procDSID != null) {
			sql += "WHERE b.ProcessedDataset = " + procDSID + " \n";
		}
		sql +=	"ORDER BY name";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String listFiles(String procDSID, String blockID, String patternLFN) {
		String sql = "SELECT f.ID as id, \n " +
			"f.LogicalFileName as lfn, \n" +
			"f.Checksum as checksum, \n" +
			"f.Size as size, \n" +
			"f.QueryableMetaData as queryable_meta_data, \n" +
			"f.CreationDate as creation_date, \n" +
			"f.LastModificationDate as last_modification_date, \n" +
			"st.Status as status, \n" +
			"ty.Type as type, \n" +
			"percb.DistinguishedName as created_by, \n" +
			"perlm.DistinguishedName as last_modified_by \n" +
			"FROM File f \n" +
			"LEFT OUTER JOIN Type ty \n" +
				"ON ty.id = f.FileType \n" +
			"LEFT OUTER JOIN Status st \n" +
				"ON st.id = f.FileStatus \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = f.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = f.LastModifiedBy \n";

		if(patternLFN == null) patternLFN = "%";
		sql += "WHERE f.LogicalFileName like '" + patternLFN + "' \n" ;
		if(procDSID != null) {
			sql += "and f.ProcessedDataset = " + procDSID + " \n";
		}
		if(blockID != null) {
			sql += "and f.Block = " + blockID + " \n";
		}
		sql +=	"ORDER BY lfn";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getPrameterSets(String pattern) {
		String sql = "SELECT id, hash, content FROM t_parameter_set ";
		if(pattern != null) {
			sql += "WHERE content like '" + pattern + "' " +
				"or hash like '" + pattern + "' " ;
		}
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getID(String table, String key, String value) {
		System.out.println("\n\n" + "SELECT ID AS id FROM " + table + " WHERE " + key + " = '" + value + "'" + "\n\n");
		return ((String)("SELECT ID AS id FROM " + table + " WHERE " + key + " = '" + value + "'")); 
	}

	public static String getMapID(String table, String key1, String key2, String value1, String value2) {
		System.out.println((String)("SELECT ID AS id FROM " + table + " WHERE " + key1 + " = '" + value1 + "' AND " + key2 + " = '" + value2 + "'"));
		return ((String)("SELECT ID AS id FROM " + table + " WHERE " + key1 + " = '" + value1 + "' AND " + key2 + " = '" + value2 + "'"));
	}
	public static String getProcessedDSID(String prim, String dt ,String proc) {
		String sql = "SELECT procds.ID as id \n" +
				"FROM ProcessedDataset procds \n" +
				"JOIN PrimaryDataset primds \n" +
					"ON primds.id = procds.PrimaryDataset \n" +
				"JOIN ProcDSTier pdst \n" +
					"ON pdst.Dataset = procds.id \n" +
				"JOIN DataTier dt \n" +
					"ON dt.id = pdst.DataTier \n";
		if(prim == null || dt == null || proc == null) {
			return sql;
		}
		sql += "WHERE primds.Name = '" + prim + "' " +
			"and dt.Name = '" + dt + "' " +
			"and procds.Name = '" + proc + "' ";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}


	public static String getApplicationID(String version, String family, String exe) {
		String sql = "SELECT algo.id \n" +
			"FROM AlgorithmConfig algo \n" +
			"JOIN AppVersion av \n" +
				"ON av.id = algo.ApplicationVersion \n" +
			"JOIN AppFamily af \n" +
				"ON af.id = algo.ApplicationFamily \n" +
			"JOIN AppExecutable ae \n" +
				"ON ae.id = algo.ExecutableName \n" +
			"WHERE av.Version = '" + version + "' \n" +
			"and af.FamilyName = '" + family + "' \n" +
			"and ae.ExecutableName = '" + exe + "' \n" ;
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}


	/*
	public static String getAppVersionID(String version) {
		String sql = getID("AppVersion", "Version", version);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}
	
	public static String getAppExecutableID(String exe) {
		String sql = getID("AppExecutable", "ExecutableName", exe);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}
	
	public static String getAppFamilyID(String family) {
		String sql = getID("AppFamily", "FamilyName", family);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getParameterSetID(String psName) {
		String sql = getID("QueryableParameterSet", "Name", psName);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getPersonID(String userDN) {
		String sql = getID("Person", "DistinguishedName", userDN);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getBlockID(String blockName) {
		String sql = getID("Block", "Name", blockName);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getDataTierID(String tierName) {
		String sql = getID("DataTier", "Name", tierName);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getLumiSectionID(String lsNumber) {
		String sql = getID("LumiSection", "LumiSectionNumber", lsNumber);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getFileID(String lfn) {
		String sql = getID("Files", "LogicalFileName", lfn);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getStatusID(String status) {
		String sql = getID("Status", "Status", status);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getTypeID(String type) {
		String sql = getID("Type", "Type", type);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String insertFileTier(String fileID, String tierID, String userID) {
		String sql = "INSERT INTO FileTier ( \n" +
					"Fileid, \n" +
				        "DataTier, \n" +
					"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + fileID + "', \n" +
					"'" + tierID + "' , \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String insertFileLumi(String fileID, String lumiID, String userID) {
		String sql = "INSERT INTO FileLumi ( \n" +
					"Fileid, \n" +
				        "Lumi, \n" +
					"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + fileID + "', \n" +
					"'" + lumiID + "' , \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}
	
	public static String insertFileAlgo(String fileID, String appID, String userID) {
		String sql = "INSERT INTO FileAlgoMap ( \n" +
					"Fileid, \n" +
				        "Algorithm, \n" +
					"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + fileID + "', \n" +
					"'" + appID + "' , \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String insertFileParentage(String fileID, String parentID, String userID) {
		String sql = "INSERT INTO FileParentage ( \n" +
					"ThisFile, \n" +
				        "itsParent, \n" +
					"CreatedBy, \n" +
			        	"CreationDate, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"'" + fileID + "', \n" +
					"'" + parentID + "' , \n" +
					"'" + userID + "', \n" +
					"'" + DBSUtil.getDate() + "', \n" +
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}
	public static String insertStatus(String status, String userID) {
		String sql = insertName("Status", "Status", status, userID);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String insertType(String type, String userID) {
		String sql = insertName("Type", "Type", type, userID);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}


	// SQL for inserting Application and its related tables.
	// ____________________________________________________
        public static String insertAppVersion(String version, String userID) {
		String sql = insertName("AppVersion", "Version", version, userID);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String insertAppFamily(String family, String userID) {
		String sql = insertName("AppFamily", "FamilyName", family, userID);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String insertAppExecutable(String exe, String userID) {
		String sql = insertName("AppExecutable", "ExecutableName", exe, userID);
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}*/

	

}
