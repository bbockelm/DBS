
/**
 $Revision: 1.23 $"
 $Id: DBSSql.java,v 1.23 2006/11/28 19:41:38 sekhri Exp $"
 *
 */
package dbs.sql;
import java.util.Hashtable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dbs.util.DBSUtil;
import db.DBManagement;

public class DBSSql {
	/**
	 * 
	 */
	public static String getDual() throws SQLException {
		return "SELECT 1 FROM dual";
	}

	public static PreparedStatement getSchemaVersion(Connection conn) throws SQLException {

		String sql = "SELECT SchemaVersion FROM SchemaVersion";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
        }

       	public static PreparedStatement insertName(Connection conn, String table, String key, String value, String userID) throws SQLException {
		String sql = "INSERT INTO " + table + " ( \n" +
				         key + ", \n" +
			        	"CreatedBy, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"?, \n" +
					"?, \n" +
					"? \n" +
				") \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, value);
		ps.setString(2, userID);
		ps.setString(3, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}
	
	public static PreparedStatement insertMap(Connection conn, String table, String key1, String key2, String value1, String value2, String userID) throws SQLException {
		String sql = "INSERT INTO " + table + " ( \n" +
					key1 + ", \n" +
					key2 + ", \n" +
					"CreatedBy, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"? \n" +
				") \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, value1);
		ps.setString(2, value2);
		ps.setString(3, userID);
		ps.setString(4, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}
	

	public static PreparedStatement insertPrimaryDataset(Connection conn, String ann, String name, String descID, String startDate, String endDate, String typeID , String userID) throws SQLException {
		String sql = "INSERT INTO PrimaryDataset ( \n" +
				        "Annotation, \n" +
				        "Name, \n" +
				        //"Description, \n" +
				        "StartDate, \n" +
				        "EndDate, \n" +
				        "Type, \n" +
			        	"CreatedBy, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"?, \n" +
					"?, \n" +
					//"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"? \n" +
				") \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, ann);
		ps.setString(2, name);
		//ps.setString(3, descID);
		ps.setString(3, startDate);
		ps.setString(4, endDate);
		ps.setString(5, typeID);
		ps.setString(6, userID);
		ps.setString(7, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement insertRun(Connection conn, String runNumber, String nOfEvents, String nOfLumiSections, String totalLumi, String storeNumber, String startOfRun, String endOfRun, String userID) throws SQLException {
		String sql = "INSERT INTO Runs ( \n" +
				        "RunNumber, \n" +
				        "NumberOfEvents, \n" +
				        "NumberOfLumiSections, \n" +
				        "TotalLuminosity, \n" +
				        "StoreNumber, \n" +
				        "StartOfRun, \n" +
				        "EndOfRun, \n" +
			        	"CreatedBy, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"? \n" +
				") \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, runNumber);
		ps.setString(2, nOfEvents);
		ps.setString(3, nOfLumiSections);
		ps.setString(4, totalLumi);
		ps.setString(5, storeNumber);
		ps.setString(6, startOfRun);
		ps.setString(7, endOfRun);
		ps.setString(8, userID);
		ps.setString(9, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement insertBlock(Connection conn, String size, String name, String procDSID, String nOfFiles, String openForWriting, String userID) throws SQLException {
		String sql = "INSERT INTO Block ( \n" +
				        "BlockSize, \n" +
				        "Name, \n" +
				        "Dataset, \n" +
				        "NumberOfFiles, \n" +
				        "OpenForWriting, \n" +
			        	"CreatedBy, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"? \n" +
				") \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, size);
		ps.setString(2, name);
		ps.setString(3, procDSID);
		ps.setString(4, nOfFiles);
		ps.setString(5, openForWriting);
		ps.setString(6, userID);
		ps.setString(7, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement insertLumiSection(Connection conn, String lsNumber, String runID, String startEvNumber, String endEvNumber, String lStartTime, String lEndTime, String userID) throws SQLException {
		String sql = "INSERT INTO LumiSection ( \n" +
				        "LumiSectionNumber, \n" +
				        "RunNumber, \n" +
				        "StartEventNumber, \n" +
				        "EndEventNumber, \n" +
				        "LumiStartTime, \n" +
				        "LumiEndTime, \n" +
			        	"CreatedBy, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"? \n" +
				") \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, lsNumber);
		ps.setString(2, runID);
		ps.setString(3, startEvNumber);
		ps.setString(4, endEvNumber);
		ps.setString(5, lStartTime);
		ps.setString(6, lEndTime);
		ps.setString(7, userID);
		ps.setString(8, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}
		       
        public static PreparedStatement insertPerson(Connection conn, String userName, String userDN, String contactInfo, String userID) throws SQLException {
		String sql = "INSERT INTO Person ( \n" +
					"Name, \n" +
				        "DistinguishedName, \n" +
					"ContactInfo, \n" +
					"CreatedBy, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"? \n" +
				") \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, userName);
		ps.setString(2, userDN);
		ps.setString(3, contactInfo);
		ps.setString(4, userID);
		ps.setString(5, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}



	public static PreparedStatement insertParameterSet(Connection conn, String hash, String name, String version, String type, String annotation, String content, String userID) throws SQLException {
		String sql = "INSERT INTO QueryableParameterSet ( \n" +
				        "Hash, \n" +
				        "Name, \n" +
				        "Version, \n" +
				        "Type, \n" +
				        "Annotation, \n" +
				        "Content, \n" +
			        	"CreatedBy, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"? \n" +
				") \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, hash);
		ps.setString(2, name);
		ps.setString(3, version);
		ps.setString(4, type);
		ps.setString(5, annotation);
		ps.setString(6, content);
		ps.setString(7, userID);
		ps.setString(8, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}
				       
        public static PreparedStatement insertApplication(Connection conn, String exeID, String versionID, String familyID, String psID, String userID) throws SQLException {
		String sql = "INSERT INTO AlgorithmConfig ( \n" +
					"ExecutableName, \n" +
				        "ApplicationVersion, \n" +
					"ApplicationFamily, \n" +
					"ParameterSetID, \n" +
					"CreatedBy, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"? \n" +
				") \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, exeID);
		ps.setString(2, versionID);
		ps.setString(3, familyID);
		ps.setString(4, psID);
		ps.setString(5, userID);
		ps.setString(6, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	// SQL for inserting ProcessedDatatset and its related tables.
	// ____________________________________________________

	public static PreparedStatement insertProcessedDatatset(Connection conn, String name, String primDSID, String openForWriting, String phyGroupID, String statusID, String userID) throws SQLException {
		String sql = "INSERT INTO ProcessedDataset ( \n" +
					"Name, \n" +
					"PrimaryDataset, \n" +
					"OpenForWriting, \n" +
					"PhysicsGroup, \n" +
					"Status, \n" +
					"CreatedBy, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"? \n" +
				") \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, name);
		ps.setString(2, primDSID);
		ps.setString(3, openForWriting);
		ps.setString(4, phyGroupID);
		ps.setString(5, statusID);
		ps.setString(6, userID);
		ps.setString(7, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}
	
	public static PreparedStatement insertPhysicsGroup(Connection conn, String name, String conID, String userID) throws SQLException {
		String sql = "INSERT INTO PhysicsGroup ( \n" +
					"PhysicsGroupName, \n" +
					"PhysicsGroupConvener, \n" +
					"CreatedBy, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"? \n" +
				") \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, name);
		ps.setString(2, conID);
		ps.setString(3, userID);
		ps.setString(4, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}


	// SQL for inserting File and its related tables.
	// ____________________________________________________
	
	//public static String insertFile(Connection conn, String procDSID, String blockID, String lfn, String checksum, String nOfEvents, String size, String fileStatusID, String typeID, String valStatusID, String qMetaData, String userID) throws SQLException {
	public static PreparedStatement insertFile(Connection conn, String procDSID, String blockID, String lfn, String checksum, String nOfEvents, String size, String fileStatus, String type, String valStatus, String qMetaData, String userID) throws SQLException {


		System.out.println("SERIOUS WARNING:: Validation Status Table is not in Schema, check that again");

		String sql = "INSERT INTO Files ( \n" +
					"LogicalFileName, \n" +
					"Dataset, \n" +
					"Block, \n" +
					"Checksum, \n" +
					"NumberOfEvents, \n" +
					"FileSize, \n" +
					"FileStatus, \n" +
					"FileType, \n" +
					"ValidationStatus, \n" +
					"QueryableMetadata, \n" +
					"CreatedBy, \n" +
				        "LastModifiedBy \n" +
				") VALUES ( \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"?, \n" +
					"(" + getID(conn, "FileStatus", "Status", fileStatus) + "), \n" +
					"(" + getID(conn, "FileType", "Type", type) + "), \n" +
					"(" + getID(conn, "FileStatus", "Status", valStatus) + "), \n" +
					"?, \n" +
					"?, \n" +
					"? \n" +
				") \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, lfn);
		ps.setString(2, procDSID);
		ps.setString(3, blockID);
		ps.setString(4, checksum);
		ps.setString(5, nOfEvents);
		ps.setString(6, size);
		ps.setString(7, qMetaData);
		ps.setString(8, userID);
		ps.setString(9, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}
	

	public static PreparedStatement updateBlock(Connection conn, String blockID) throws SQLException {
		String sql = "UPDATE Block \n" +
			"SET BlockSize = (SELECT SUM(FileSize) FROM Files f WHERE f.Block = ?) , \n" +
			"NumberOfFiles = (SELECT COUNT(*) FROM Files f WHERE f.Block = ?) \n" +
			"WHERE ID = ?" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, blockID);
		ps.setString(2, blockID);
		ps.setString(3, blockID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}
	// ____________________________________________________
	
	
	

	
	
	
	public static PreparedStatement listPrimaryDatasets(Connection conn, String pattern) throws SQLException {
		String sql = "SELECT pd.ID as ID, \n" +
			"pd.Annotation as ANNOTATION, \n" +
			"pd.Name as PRIMARY_NAME, \n" +
			"pd.StartDate as START_DATE, \n"  +
			"pd.EndDate as END_DATE, \n" + 
			"pd.CreationDate as CREATION_DATE, \n" +
			"pd.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"tpd.TriggerPathDescription as TRIGGER_PATH_DESCRIPTION, \n" +
			"md.MCChannelDescription as MC_CHANNEL_DESCRIPTION, \n" +
			"md.MCProduction as MC_PRODUCTION, \n" +
			"md.MCDecayChain as MC_DECAY_CHAIN, \n" +
			"od.Description as OTHER_DESCRIPTION, \n" +
			"ty.Type as TYPE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM PrimaryDataset pd \n" +
			"LEFT OUTER JOIN PrimaryDSType ty \n" +
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
			sql += "WHERE pd.Name like ?\n" +
				"ORDER BY PRIMARY_NAME";
		}
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, pattern);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listProcessedDatasets(Connection conn, String patternPrim, String patternDT, String patternProc, String patternVer, String patternFam, String patternExe, String patternPS) throws SQLException {
		String sql = "SELECT procds.id as id, \n" +
			/*"concat( \n" +
				"concat( \n" +
					"concat( \n" +
						"concat( \n" +
							"concat('/', primds.Name \n" +
							"),'/' \n" +
						"),dt.Name \n" +
					"),'/' \n" +
				"), procds.name \n" +
			") as path, \n" +*/
			"primds.Name as PRIMARY_DATATSET_NAME, \n" +
			"dt.Name as DATA_TIER, \n" +
			"procds.name as PROCESSED_DATATSET_NAME, \n" +
			"procds.OpenForWriting as OPEN_FOR_WRITING, \n" +
			"procds.CreationDate as CREATION_DATE, \n" +
			"procds.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"pg.PhysicsGroupName as PHYSICS_GROUP_NAME, \n" +
			"perpg.DistinguishedName as PHYSICS_GROUP_CONVENER, \n" +
			"av.Version as APP_VERSION, \n" +
			"af.FamilyName as APP_FAMILY_NAME, \n" +
			"ae.ExecutableName as APP_EXECUTABLE_NAME, \n" +
			"ps.Name as PS_NAME, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM ProcessedDataset procds \n" +
			"JOIN PrimaryDataset primds \n" +
				"ON primds.id = procds.PrimaryDataset \n" +
			"LEFT OUTER JOIN ProcDSTier pdst \n" +
				"ON pdst.Dataset = procds.id \n" +
			"LEFT OUTER JOIN DataTier dt \n" +
				"ON dt.id = pdst.DataTier " +
			"LEFT OUTER JOIN PhysicsGroup pg \n" +
				"ON pg.id = procds.PhysicsGroup \n" +
			"LEFT OUTER JOIN Person perpg \n" +
				"ON perpg.id = pg.PhysicsGroupConvener \n" +
			"LEFT OUTER JOIN ProcAlgo pa \n" +
				"ON pa.Dataset = procds.id \n" +
			"LEFT OUTER JOIN AlgorithmConfig algo \n" +
				"ON algo.id = pa.Algorithm \n" +
			"LEFT OUTER JOIN AppVersion av \n" +
				"ON av.id = algo.ApplicationVersion \n" +
			"LEFT OUTER JOIN AppFamily af \n" +
				"ON af.id = algo.ApplicationFamily \n" +
			"LEFT OUTER JOIN AppExecutable ae \n" +
				"ON ae.id = algo.ExecutableName \n" +
			"LEFT OUTER JOIN QueryableParameterSet ps \n" +
				"ON ps.id = algo.ParameterSetID \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = procds.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = procds.LastModifiedBy \n";


		if(patternPrim == null) patternPrim = "%";
		if(patternDT == null) patternDT = "%";
		if(patternProc == null) patternProc = "%";
		if(patternVer == null) patternVer = "%";
		if(patternFam == null) patternFam = "%";
		if(patternExe == null) patternExe = "%";
		if(patternPS == null) patternPS = "%";

		sql += "WHERE primds.Name like ? \n" +
			"and dt.Name like ? \n" +
			"and procds.name like ? \n" +
			"and av.Version like ? \n" +
			"and af.FamilyName like ? \n" +
			"and ae.ExecutableName like ? \n" +
			"and ps.Name like ? \n" +
			//"ORDER BY path";
			"ORDER BY id, app_version, app_family_name, app_executable_name, ps_name, data_tier";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, patternPrim);
		ps.setString(2, patternDT);
		ps.setString(3, patternProc);
		ps.setString(4, patternVer);
		ps.setString(5, patternFam);
		ps.setString(6, patternExe);
		ps.setString(7, patternPS);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listAlgorithms(Connection conn, String patternVer, String patternFam, String patternExe, String patternPS) throws SQLException {
		String sql = "SELECT algo.id as ID, \n" +
			"av.Version as APP_VERSION, \n" +
			"af.FamilyName as APP_FAMILY_NAME, \n" +
			"ae.ExecutableName as APP_EXECUTABLE_NAME, \n" +
			"ps.Name as PS_NAME, \n" +
			"ps.Hash as PS_HASH, \n" +
			"algo.CreationDate as CREATION_DATE, \n" +
			"algo.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM AlgorithmConfig algo \n" +
			"JOIN AppVersion av \n" +
				"ON av.id = algo.ApplicationVersion \n" +
			"JOIN AppFamily af \n" +
				"ON af.id = algo.ApplicationFamily \n" +
			"JOIN AppExecutable ae \n" +
				"ON ae.id = algo.ExecutableName \n" +
			"JOIN QueryableParameterSet ps \n" +
				"ON ps.id = algo.ParameterSetID \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = algo.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = algo.LastModifiedBy \n";

		if(patternVer == null) patternVer = "%";
		if(patternFam == null) patternFam = "%";
		if(patternExe == null) patternExe = "%";
		if(patternPS == null) patternPS = "%";
		
		sql += "WHERE av.Version like ? \n" +
			"and af.FamilyName like ? \n" +
			"and ae.ExecutableName like ? \n" +
			"and ps.Name like ? \n" +
			"ORDER BY app_family_name, app_executable_name, app_version, ps_name";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, patternVer);
		ps.setString(2, patternFam);
		ps.setString(3, patternExe);
		ps.setString(4, patternPS);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listRuns(Connection conn, String procDSID) throws SQLException {
		String sql = "SELECT run.ID as ID, \n " +
			"run.RunNumber as RUN_NUMBER, \n" +
			"run.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
			"run.NumberOfLumiSections as NUMBER_OF_LUMI_SECTIONS, \n" +
			"run.TotalLuminosity as TOTAL_LUMINOSITY, \n" +
			"run.StoreNumber as STRORE_NUMBER, \n" +
			"run.StartOfRun as START_OF_RUN, \n" +
			"run.EndOfRun as END_OF_RUN, \n" +
			"run.CreationDate as CREATION_DATE, \n" +
			"run.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM Runs run \n" +
			"JOIN ProcDSRuns pdsr \n" +
				"ON pdsr.Run = run.id \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = run.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = run.LastModifiedBy \n";

		if(procDSID != null) {
			sql += "WHERE pdsr.Dataset = ? \n";
		}
		sql +=	"ORDER BY run_number";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, procDSID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listTiers(Connection conn, String procDSID) throws SQLException {
		String sql = "SELECT dt.ID as ID, \n " +
			"dt.Name as NAME, \n" +
			"dt.CreationDate as CREATION_DATE, \n" +
			"dt.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM DataTier dt \n" +
			"JOIN ProcDSTier pdst \n" +
				"ON pdst.DataTier = dt.id \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = dt.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = dt.LastModifiedBy \n";

		if(procDSID != null) {
			sql += "WHERE pdst.Dataset = ? \n";
		}
		sql +=	"ORDER BY name";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, procDSID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listBlocks(Connection conn, String procDSID) throws SQLException {
		String sql = "SELECT b.ID as ID, \n " +
			"b.Name as NAME, \n" +
			"b.BlockSize as SIZE, \n" +
			"b.NumberOfFiles as NUMBER_OF_FILES, \n" +
			"b.OpenForWriting as OPEN_FOR_WRITING, \n" +
			"b.CreationDate as CREATION_DATE, \n" +
			"b.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY \n" +
			"FROM Block b \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = b.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = b.LastModifiedBy \n";

		if(procDSID != null) {
			sql += "WHERE b.Dataset = ? \n";
		}
		sql +=	"ORDER BY name";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, procDSID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement listFiles(Connection conn, String procDSID, String blockID, String patternLFN) throws SQLException {
		String sql = "SELECT f.ID as ID, \n " +
			"f.LogicalFileName as LFN, \n" +
			"f.Checksum as CHECKSUM, \n" +
			"f.FileSize as SIZE, \n" +
			"f.QueryableMetaData as QUERYABLE_META_DATA, \n" +
			"f.CreationDate as CREATION_DATE, \n" +
			"f.LastModificationDate as LAST_MODIFICATION_DATE, \n" +
			"f.NumberOfEvents as NUMBER_OF_EVENTS, \n" +
			"f.ValidationStatus as VALIDATION_STATUS, \n" +
			"st.Status as STATUS, \n" +
			"ty.Type as TYPE, \n" +
			"percb.DistinguishedName as CREATED_BY, \n" +
			"perlm.DistinguishedName as LAST_MODIFIED_BY, \n" +
                        "b.Name as BLOCK_NAME, \n"+ 
			"dt.Name as DATA_TIER \n"+ 
			"FROM Files f \n" +
                        "LEFT OUTER JOIN Block b \n" +
                                "ON b.id = f.Block \n "+  
                        "LEFT OUTER JOIN FileTier fdt \n" +
                                "ON fdt.Fileid = f.id \n" +
			"LEFT OUTER JOIN DataTier dt \n" +
				"ON dt.id = fdt.DataTier " +
			"LEFT OUTER JOIN FileType ty \n" +
				"ON ty.id = f.FileType \n" +
			"LEFT OUTER JOIN FileStatus st \n" +
				"ON st.id = f.FileStatus \n" +
			"LEFT OUTER JOIN Person percb \n" +
				"ON percb.id = f.CreatedBy \n" +
			"LEFT OUTER JOIN Person perlm \n" +
				"ON perlm.id = f.LastModifiedBy \n";

		if(patternLFN == null) patternLFN = "%";
		sql += "WHERE f.LogicalFileName like ? \n" ;
		if(procDSID != null) {
			sql += "and f.Dataset = ? \n";
		}
		if(blockID != null) {
			sql += "and f.Block = ? \n";
		}
		sql +=	"ORDER BY lfn";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, patternLFN);
		if(procDSID != null) {
			ps.setString(2, procDSID);
		}
		if(blockID != null) {
			ps.setString(3, blockID);
		}
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}


	public static PreparedStatement getID(Connection conn, String table, String key, String value) throws SQLException {
		String sql = "SELECT ID \n " +
			"FROM " + table + "\n " +
			"WHERE " + key + " = ? \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, value);
		System.out.println("\n\n" + ps + "\n\n");
		//return ((String)("SELECT ID AS id FROM " + table + " WHERE " + key + " = '" + value + "'")); 
		return ps;
	}

	public static PreparedStatement getMapID(Connection conn, String table, String key1, String key2, String value1, String value2) throws SQLException {
		String sql = "SELECT ID \n " +
			"FROM " + table + "\n " +
			"WHERE " + key1 + " = ? \n" +
			"AND " + key2 + " = ? \n" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, value1);
		ps.setString(2, value2);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}
	public static PreparedStatement getProcessedDSID(Connection conn, String prim, String dt ,String proc) throws SQLException {
		String sql = "SELECT procds.ID as ID \n" +
				"FROM ProcessedDataset procds \n" +
				"JOIN PrimaryDataset primds \n" +
					"ON primds.id = procds.PrimaryDataset \n" +
				"JOIN ProcDSTier pdst \n" +
					"ON pdst.Dataset = procds.id \n" +
				"JOIN DataTier dt \n" +
					"ON dt.id = pdst.DataTier \n";
		if(prim == null || dt == null || proc == null) {
			return DBManagement.getStatement(conn, sql);
		}
		sql += "WHERE primds.Name = ? \n" +
			"and dt.Name = ? \n" +
			"and procds.Name = ? \n";
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, prim);
		ps.setString(2, dt);
		ps.setString(3, proc);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}


	public static PreparedStatement getAlgorithmID(Connection conn, String ver, String fam, String exe, String psName) throws SQLException {
		String sql = "SELECT algo.id \n" +
			"FROM AlgorithmConfig algo \n" +
			"JOIN AppVersion av \n" +
				"ON av.id = algo.ApplicationVersion \n" +
			"JOIN AppFamily af \n" +
				"ON af.id = algo.ApplicationFamily \n" +
			"JOIN AppExecutable ae \n" +
				"ON ae.id = algo.ExecutableName \n" +
			"JOIN QueryableParameterSet ps \n" +
				"ON ps.id = algo.ParameterSetID \n" +
			"WHERE av.Version = ? \n" +
			"and af.FamilyName = ? \n" +
			"and ae.ExecutableName = ? \n" +
			"and ps.Name = ? \n" ;
		PreparedStatement ps = DBManagement.getStatement(conn, sql);
		ps.setString(1, ver);
		ps.setString(2, fam);
		ps.setString(3, exe);
		ps.setString(4, psName);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}


	/*
	public static PreparedStatement getAppVersionID(Connection conn, String version) throws SQLException {
		String sql = getID("AppVersion", "Version", version);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}
	
	public static PreparedStatement getAppExecutableID(Connection conn, String exe) throws SQLException {
		String sql = getID("AppExecutable", "ExecutableName", exe);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}
	
	public static PreparedStatement getAppFamilyID(Connection conn, String family) throws SQLException {
		String sql = getID("AppFamily", "FamilyName", family);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement getParameterSetID(Connection conn, String psName) throws SQLException {
		String sql = getID("QueryableParameterSet", "Name", psName);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement getPersonID(Connection conn, String userDN) throws SQLException {
		String sql = getID("Person", "DistinguishedName", userDN);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement getBlockID(Connection conn, String blockName) throws SQLException {
		String sql = getID("Block", "Name", blockName);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement getDataTierID(Connection conn, String tierName) throws SQLException {
		String sql = getID("DataTier", "Name", tierName);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement getLumiSectionID(Connection conn, String lsNumber) throws SQLException {
		String sql = getID("LumiSection", "LumiSectionNumber", lsNumber);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement getFileID(Connection conn, String lfn) throws SQLException {
		String sql = getID("Files", "LogicalFileName", lfn);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement getStatusID(Connection conn, String status) throws SQLException {
		String sql = getID("Status", "Status", status);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement getTypeID(Connection conn, String type) throws SQLException {
		String sql = getID("Type", "Type", type);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement insertFileTier(Connection conn, String fileID, String tierID, String userID) throws SQLException {
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
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement insertFileLumi(Connection conn, String fileID, String lumiID, String userID) throws SQLException {
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
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}
	
	public static PreparedStatement insertFileAlgo(Connection conn, String fileID, String appID, String userID) throws SQLException {
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
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement insertFileParentage(Connection conn, String fileID, String parentID, String userID) throws SQLException {
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
					"'" + userID + "' \n" +
				") \n";
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}
	public static PreparedStatement insertStatus(Connection conn, String status, String userID) throws SQLException {
		String sql = insertName("Status", "Status", status, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement insertType(Connection conn, String type, String userID) throws SQLException {
		String sql = insertName("Type", "Type", type, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}


	// SQL for inserting Application and its related tables.
	// ____________________________________________________
        public static String insertAppVersion(Connection conn, String version, String userID) throws SQLException {
		String sql = insertName("AppVersion", "Version", version, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement insertAppFamily(Connection conn, String family, String userID) throws SQLException {
		String sql = insertName("AppFamily", "FamilyName", family, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}

	public static PreparedStatement insertAppExecutable(Connection conn, String exe, String userID) throws SQLException {
		String sql = insertName("AppExecutable", "ExecutableName", exe, userID);
		System.out.println("\n\n" + ps + "\n\n");
		return ps;
	}*/

	

}
