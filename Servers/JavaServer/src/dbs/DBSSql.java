
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

	public static String getPrimaryDS(String pattern) {
		String sql = "select pd.ID as id, \n" +
		       "pd.Annotation as annotation, \n" +
		       "pd.Name as name, \n" +
		       "tpd.TriggerPathDescription as trigger_path_description, \n" +
		       "md.MCChannelDescription as mc_channel_description, \n" +
		       "md.MCProduction as mc_production, \n" +
		       "md.MCDecayChain as mc_decay_chain, \n" +
		       "od.Description as other_description, \n" +
		       "pd.StartDate as start_date, \n"  +
		       "pd.EndDate as end_date, \n" + 
		       "pdt.FileType as type, \n" +
		       "pd.CreatedBy as created_by, \n" +
		       "pd.CreationDate as creation_date, \n" +
		       "pd.LastModificationDate as last_modification_by, \n" +
		       "pd.LastModifiedBy as last_modified_by \n" +
		       "from PrimaryDataset pd \n" +
		       "left outer join PrimaryDSType pdt \n" +
			       "on pdt.id = pd.Type \n" +
			"left outer join PrimaryDatasetDescription pdd \n" +
				"on pdd.id = pd.Description \n" +
			"left outer join TriggerPathDescription tpd \n" +
				"on tpd.id = pdd.TriggerDescriptionID \n" + 
			"left outer join MCDescription md \n" +
				"on md.id = pdd.MCChannelDescriptionID \n" +
			"left outer join OtherDescription od \n" +
				"on od.id = pdd.OtherDescriptionID \n";
		if(pattern != null) {
			sql += "where pd.Name like '" + pattern + "'\n";
		}
		System.out.println("\n\n" + sql + "\n\n");
		return sql;
	}

	public static String getProcessedDS(String patternPrim, String patternDt, String patternProc) {
		String sql = "select  procds.id as id , primds.name as primary, dt.name as tier, procname.name as processed" +
				"from t_processed_dataset procds " +
				"join t_primary_dataset primds " +
				"on primds.id = procds.primary_dataset " +
				"join t_processing_name procname " +
				"on procname.id = procds.name " +
				"join t_data_tier dt " +
				"on dt.id = procds.data_tier ";

		if(patternPrim == null) patternPrim = "%";
		if(patternDt == null) patternDt = "%";
		if(patternProc == null) patternProc = "%";

		sql += "where primds.name like '" + patternPrim + "' " +
			"and dt.name like '" + patternDt + "' " +
			"and procname.name like '" + patternProc + "' ";
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
		String sql = "select procds.id as id" +
				"from t_processed_dataset procds " +
				"join t_primary_dataset primds " +
				"on primds.id = procds.primary_dataset " +
				"join t_processing_name proname " +
				"on proname.id = procds.name " +
				"join t_data_tier dt " +
				"on dt.id = procds.data_tier ";
		if(prim == null || dt == null || proc == null) {
			return sql;
		}
		sql += "where primds.name = '" + prim + "' " +
			"and dt.name = '" + dt + "' " +
			"and proname.name = '" + proc + "' ";
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
