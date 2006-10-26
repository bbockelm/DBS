/**
 * @author sekhri
 * 
 $Revision: 1.2 $"
 $Id: DBSSql.java,v 1.2 2006/10/26 17:09:32 afaq Exp $"
 */
package dbs.sql;
import java.util.Hashtable;

public class DBSSql {
	/**
	 * 
	 */

	public static String getDual() {
		return "select 1 from dual";
	}

        public static String insertPrimaryDataset(String primaryDSName) {
                String sql = "INSERT INTO PrimaryDataset(Annotation, Name, Description, Type)" +
                             " VALUES ('Another PrimaryDS in new era', '"+primaryDSName+"'," +
                                        " 1, 1)";
                System.out.println("\n\n" + sql + "\n\n");
                return sql;
        }

        public static String insertBlock(Hashtable atribs) throws Exception {

            String size = (String)atribs.get("BlockSize");
            String name = (String)atribs.get("Name");
            String dataset = (String)atribs.get("Dataset");
            String files = (String)atribs.get("NumberOfFiles");

            String sql = "INSERT INTO Block (BlockSize, Name, Dataset, NumberOfFiles, OpenForWriting, CreationDate)" + 
                         " VALUES ("+size+", '"+name+"', "+dataset+", "+files+", 'y', NOW()";
            System.out.println("\n\n" + sql + "\n\n");
            return sql; 
        }

        public static String closeBlock(Hashtable atribs) throws Exception {

            String name = (String)atribs.get("Name");

            String sql = "UPDATE Block SET OpenForWriting='n'"; 
            System.out.println("\n\n" + sql + "\n\n");
            return sql;
        }

        public static String insertProcessedDataset(Hashtable atribs) throws Exception {

            String name = (String)atribs.get("Name");
            String primary = (String)atribs.get("PrimaryDataset");
 
            String sql = "INSERT INTO ProcessedDataset(Name, PrimaryDataset, OpenForWriting, CreationDate)"+
                         " VALUES ('"+name+"', "+primary+", 'y', NOW())";
            System.out.println("\n\n" + sql + "\n\n");
            return sql;
        }

	public static String listPrimaryDatasets(String pattern) {
		String sql = "select pd.ID as id, \n" +
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
			"from PrimaryDataset pd \n" +
			"left outer join Type ty \n" +
				"on ty.id = pd.Type \n" +
			"left outer join PrimaryDatasetDescription pdd \n" +
				"on pdd.id = pd.Description \n" +
			"left outer join TriggerPathDescription tpd \n" +
				"on tpd.id = pdd.TriggerDescriptionID \n" + 
			"left outer join MCDescription md \n" +
				"on md.id = pdd.MCChannelDescriptionID \n" +
			"left outer join OtherDescription od \n" +
				"on od.id = pdd.OtherDescriptionID \n" +
			"left outer join Person percb \n" +
				"on percb.id = pd.CreatedBy \n" +
			"left outer join Person perlm \n" +
				"on perlm.id = pd.LastModifiedBy \n";
		if(pattern != null) {
			sql += "where pd.Name like '" + pattern + "'\n" +
				"order by primary_name";
		}
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String listProcessedDatasets(String patternPrim, String patternDt, String patternProc, String patternVersion, String patternFamily, String patternExe) {
		String sql = "select procds.id as id, \n" +
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
			"av.AppVersion as app_version, \n" +
			"af.ApplicationFamilyName as app_family_name, \n" +
			"ae.ExecutableName as app_executable_name, \n" +
			"percb.DistinguishedName as created_by, \n" +
			"perlm.DistinguishedName as last_modified_by \n" +
			"from ProcessedDataset procds \n" +
			"left outer join PrimaryDataset primds \n" +
				"on primds.id = procds.PrimaryDataset \n" +
			"left outer join ProcDSTier pdst \n" +
				"on pdst.Dataset = procds.id \n" +
			"left outer join DataTier dt \n" +
				"on dt.id = pdst.DataTier " +
			"left outer join PhysicsGroup pg \n" +
				"on pg.id = procds.PhysicsGroup \n" +
			"left outer join Person perpg \n" +
				"on perpg.id = pg.PhysicsGroupConvener \n" +
			"left outer join Person percb \n" +
				"on percb.id = procds.CreatedBy \n" +
			"left outer join Person perlm \n" +
				"on perlm.id = procds.LastModifiedBy \n" +
			"left outer join ProcAlgoMap pam \n" +
				"on pam.Dataset = procds.id \n" +
			"left outer join AlgorithmConfig algo \n" +
				"on algo.id = pam.Algorithm \n" +
			"left outer join AppVersion av \n" +
				"on av.id = algo.ApplicationVersion \n" +
			"left outer join ApplicationFamily af \n" +
				"on af.id = algo.ApplicationFamily \n" +
			"left outer join AppExecutable ae \n" +
				"on ae.id = algo.ExecutableName \n";

		if(patternPrim == null) patternPrim = "%";
		if(patternDt == null) patternDt = "%";
		if(patternProc == null) patternProc = "%";
		if(patternVersion == null) patternVersion = "%";
		if(patternFamily == null) patternFamily = "%";
		if(patternExe == null) patternExe = "%";

		sql += "where primds.Name like '" + patternPrim + "' \n" +
			"and dt.Name like '" + patternDt + "' \n" +
			"and procds.name like '" + patternProc + "' \n" +
			"and av.AppVersion like '" + patternVersion + "' \n" +
			"and af.ApplicationFamilyName like '" + patternFamily + "' \n" +
			"and ae.ExecutableName like '" + patternExe + "' \n" +
			"order by path";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String listApplications(String patternVersion, String patternFamily, String patternExe) {
		String sql = "select algo.id as id, \n" +
			"av.AppVersion as app_version, \n" +
			"af.ApplicationFamilyName as app_family_name, \n" +
			"ae.ExecutableName as app_executable_name, \n" +
			"algo.CreationDate as creation_date, \n" +
			"algo.LastModificationDate as last_modification_date, \n" +
			"percb.DistinguishedName as created_by, \n" +
			"perlm.DistinguishedName as last_modified_by \n" +
			"from AlgorithmConfig algo \n" +
			"join AppVersion av \n" +
				"on av.id = algo.ApplicationVersion \n" +
			"join ApplicationFamily af \n" +
				"on af.id = algo.ApplicationFamily \n" +
			"join AppExecutable ae \n" +
				"on ae.id = algo.ExecutableName \n" +
			"left outer join Person percb \n" +
				"on percb.id = algo.CreatedBy \n" +
			"left outer join Person perlm \n" +
				"on perlm.id = algo.LastModifiedBy \n";

		if(patternVersion == null) patternVersion = "%";
		if(patternFamily == null) patternFamily = "%";
		if(patternExe == null) patternExe = "%";

		sql += "where av.AppVersion like '" + patternVersion + "' \n" +
			"and af.ApplicationFamilyName like '" + patternFamily + "' \n" +
			"and ae.ExecutableName like '" + patternExe + "' \n" +
			"order by app_family_name, app_executable_name, app_version";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String listRuns(String procDSID) {
		String sql = "select run.ID as id, \n " +
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
			"from Runs run \n" +
			"join ProcDSRuns pdsr \n" +
				"on pdsr.Run = run.id \n" +
			"left outer join Person percb \n" +
				"on percb.id = run.CreatedBy \n" +
			"left outer join Person perlm \n" +
				"on perlm.id = run.LastModifiedBy \n";

		if(procDSID != null) {
			sql += "where pdsr.Dataset = " + procDSID + " \n";
		}
		sql +=	"order by run_number";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String listTiers(String procDSID) {
		String sql = "select dt.ID as id, \n " +
			"dt.Name as name, \n" +
			"dt.CreationDate as creation_date, \n" +
			"dt.LastModificationDate as last_modification_date, \n" +
			"percb.DistinguishedName as created_by, \n" +
			"perlm.DistinguishedName as last_modified_by \n" +
			"from DataTier dt \n" +
			"join ProcDSTier pdst \n" +
				"on pdst.DataTier = dt.id \n" +
			"left outer join Person percb \n" +
				"on percb.id = dt.CreatedBy \n" +
			"left outer join Person perlm \n" +
				"on perlm.id = dt.LastModifiedBy \n";

		if(procDSID != null) {
			sql += "where pdst.Dataset = " + procDSID + " \n";
		}
		sql +=	"order by name";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String listBlocks(String procDSID) {
		String sql = "select b.ID as id, \n " +
			"b.Name as name, \n" +
			"b.Size as size, \n" +
			"b.NumberOfFiles as number_of_files, \n" +
			"b.OpenForWriting as open_for_writing, \n" +
			"b.CreationDate as creation_date, \n" +
			"b.LastModificationDate as last_modification_date, \n" +
			"percb.DistinguishedName as created_by, \n" +
			"perlm.DistinguishedName as last_modified_by \n" +
			"from Block b \n" +
			"left outer join Person percb \n" +
				"on percb.id = b.CreatedBy \n" +
			"left outer join Person perlm \n" +
				"on perlm.id = b.LastModifiedBy \n";

		if(procDSID != null) {
			sql += "where b.ProcessedDataset = " + procDSID + " \n";
		}
		sql +=	"order by name";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String listFiles(String procDSID, String blockID, String patternLFN) {
		String sql = "select f.ID as id, \n " +
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
			"from File f \n" +
			"left outer join Type ty \n" +
				"on ty.id = f.FileType \n" +
			"left outer join Status st \n" +
				"on st.id = f.FileStatus \n" +
			"left outer join Person percb \n" +
				"on percb.id = f.CreatedBy \n" +
			"left outer join Person perlm \n" +
				"on perlm.id = f.LastModifiedBy \n";

		if(patternLFN == null) patternLFN = "%";
		sql += "where f.LogicalFileName like '" + patternLFN + "' \n" ;
		if(procDSID != null) {
			sql += "and f.ProcessedDataset = " + procDSID + " \n";
		}
		if(blockID != null) {
			sql += "and f.Block = " + blockID + " \n";
		}
		sql +=	"order by lfn";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getPrameterSets(String pattern) {
		String sql = "select id, hash, content from t_parameter_set ";
		if(pattern != null) {
			sql += "where content like '" + pattern + "' " +
				"or hash like '" + pattern + "' " ;
		}
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}


	public static String getProcessedDSID(String prim, String dt ,String proc) {
		String sql = "select procds.id as id \n" +
				"from ProcessedDataset procds \n" +
				"join PrimaryDataset primds \n" +
					"on primds.id = procds.PrimaryDataset \n" +
				"join ProcDSTier pdst \n" +
					"on pdst.Dataset = procds.id \n" +
				"join DataTier dt \n" +
					"on dt.id = pdst.DataTier \n";
		if(prim == null || dt == null || proc == null) {
			return sql;
		}
		sql += "where primds.Name = '" + prim + "' " +
			"and dt.Name = '" + dt + "' " +
			"and procds.Name = '" + proc + "' ";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getBlockID(String blockName) {
		String sql = "select b.id as id \n" +
				"from Block b \n";
		if(blockName == null) {
			return sql;
		}
		sql += "where b.Name = '" + blockName + "' ";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}



}
