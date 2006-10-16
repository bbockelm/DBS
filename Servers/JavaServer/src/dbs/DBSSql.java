
/**
 * @author sekhri
 *
 */
package dbs;
public class DBSSql {
	/**
	 * 
	 */
	public static String getDual() {
		//return "select dn from user";
		return "select 1 from dual";
	}

        public static String createPrimaryDataset(String primaryDSName) {
                String sql = "INSERT INTO PrimaryDataset(Annotation, Name, Description, Type)" +
                             " VALUES ('Another PrimaryDS in new era', '"+primaryDSName+"'," +
                                        " 1, 1)";
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

	public static String getApplications(String pattern) {
		String sql = "select a.id as id, af.name as family, a.executable as executable, a.app_version as version" +
				"from t_application a " +
				"join t_app_family af on af.id = a.app_family ";
		if(pattern != null) {
			sql += "where af.name like '" + pattern + "' " +
				"or a.executable like '" + pattern + "' " +
				"or a.app_version like '" + pattern + "' " ;
		}
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getApplicationConfigs(String pattern) {
		String sql = " select ac.id as app_config_id, application_id as , af.name as family, a.executable as executable, a.app_version as version, p.id as pset_id, p.hash as hash, p.content as content" +
				"from t_app_config ac " +
				"join t_application a on a.id = ac.application " +
				"join t_app_family af on af.id = a.app_family " +
				"join t_parameter_set p on p.id = ac.parameter_set ";

		if(pattern != null) {
			sql += "where af.name like '" + pattern + "' " +
				"or a.executable like '" + pattern + "' " +
				"or a.app_version like '" + pattern + "' " ;
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

	public static String getEventCollections(String procID) {
		String sql = "select distinct evc.id as evc_id, evc.name as evc_name, evc.events as events, evs.name as evc_status, b.id as block_id, bs.name as block_status" +
				"from t_event_collection evc " +
				"join t_evcoll_file evf " +
				"on evf.evcoll = evc.id " +
				"left join t_evcoll_status evs " +
				"on evs.id = evc.status " +
				"join t_file f " +
				"on f.id = evf.fileid " +
				"join t_block b " +
				"on b.id = f.inblock " +
				"join t_block_status bs " +
				"on bs.id = b.status ";

		if(procID != null) {
			sql += "where evc.processed_dataset = '" + procID + "' ";
		}
		sql += "order by b.id, evc.name ";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getFiles(String procID) {
		String sql = "select f.id as file_id, f.logical_name as lfn, f.guid as guid, f.filesize as size, f.checksum as checksum, fs.name as status, ft.name as type, b.id as block_id, b.files files, b.bytes as bytes, bs.name as block_status" +
				"from t_processed_dataset pd " +
				"join t_processing p " +
				"on p.primary_dataset = pd.primary_dataset " +
				"and p.name = pd.name " +
				"join t_block b " +
				"on b.processing = p.id " +
				"join t_block_status bs " +
				"on bs.id = b.status " +
				"left join t_file f " +
				"on f.inblock = b.id " +
				"left join t_file_status fs " +
				"on fs.id = f.status " +
				"left join t_file_type ft " +
				"on ft.id = f.type " ;
		if(procID != null) {
			sql += "where pd.id = '" + procID + "' ";
		}
		sql += "order by b.id, f.logical_name ";
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

}
