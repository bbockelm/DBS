/**
 * @author sekhri
 $Revision: 1.2 $"
 $Id: DBSApiLogic.java,v 1.2 2006/10/26 21:49:07 afaq Exp $"
 *
 */


package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.io.Writer;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import java.util.Hashtable;
import db.DBManagement;
import dbs.sql.DBSSql;
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
	private static String VALID_PATH = "^/([^/]+)/([^/]+)/([^/]+)";
	private static String VALID_PATTERN = "^/([^/]+)/([^/]+)/([^/]+)/([^/]+)/([^/]+)/([^/]+)";
	private String userDN;
	//private static String SUCCESS_HEADER = "Dbs-status-message: Success\nDbs-status-code: 100\nContent-Type: text/plain; charset=ISO-8859-1\n\n";

	public DBSApiLogic() {
		//System.out.println("Constructor DBSApiLogic");
	}

	
	public void listPrimaryDatasets(Connection conn, Writer out, String pattern) throws Exception {
		pattern = pattern.replace('*','%');
		checkName(pattern);
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
		checkPath(path);
		String[] data = path.split("/");
		String procDSID = getProcessedDSID(conn, data[1], data[2], data[3]);
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
		checkPath(path);
		String[] data = path.split("/");
		String procDSID = getProcessedDSID(conn, data[1], data[2], data[3]);
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
		checkPath(path);
		String[] data = path.split("/");
		String procDSID = getProcessedDSID(conn, data[1], data[2], data[3]);
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
		checkName(patternLFN);
		String procDSID = null;
		String blockID = null;
		if(path != null) {
			checkPath(path);
			String[] data = path.split("/");
			procDSID = getProcessedDSID(conn, data[1], data[2], data[3]);
		}
		if(blockName != null) {
			checkName(blockName);
			blockID = getBlockID(conn, blockName);
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

	public void insertPrimaryDataset(Connection conn, String primaryDatasetName, Hashtable dbsUser) throws Exception {
		checkName(primaryDatasetName);
		boolean rs =  DBManagement.execute(conn, DBSSql.insertPrimaryDataset(primaryDatasetName));
	}


       public void insertBlock(Connection conn, Hashtable block_atribs) throws Exception {
           //Verify here that the block name is in right format  
           String name = (String)block_atribs.get("block_name");
           boolean rs =  DBManagement.execute(conn, DBSSql.insertBlock(block_atribs));
                     
       }

       public void closeBlock(Connection conn, Hashtable block_atribs) throws Exception {
           //Verify here that the block name is in right format  
           String name = (String)block_atribs.get("block_name");
           boolean rs =  DBManagement.execute(conn, DBSSql.closeBlock(block_atribs));

       }

       public void insertProcessedDataset(Connection conn, Hashtable dataset_atribs) throws Exception {
           //Verify here that the dataset name is in right format  
           String name = (String)dataset_atribs.get("proceseed_name");
           String  tier = (String)dataset_atribs.get("tier_name");
           //checkName(name);

           boolean rs =  DBManagement.execute(conn, DBSSql.insertProcessedDataset(dataset_atribs));

           rs =  DBManagement.execute(conn, DBSSql.insertProcAlgoMap(dataset_atribs));
           
           //Check if already exists, if not insert DataTier
           if ( ! (DBManagement.executeQuery(conn, DBSSql.listDataTiers(dataset_atribs))).next() ) {
               rs =  DBManagement.execute(conn, DBSSql.insertDataTier(dataset_atribs));  
           }

           System.out.println("\n\n After listDataTiers \n\n"); 
           //Check if entry already exists in ProcDSTier, if not insert
           if ( ! ( DBManagement.executeQuery(conn, DBSSql.listProcDSTiers(dataset_atribs))).next() ) {
             rs = DBManagement.execute(conn, DBSSql.insertProcDSTiers(dataset_atribs));  
           } 
           
       }

	public void insertPerson(Connection conn, Hashtable dbsUser) throws Exception {
	}

	private String getProcessedDSID(Connection conn, String prim, String dt, String proc) throws Exception {
		if(prim == null || dt == null || proc == null) {
			throw new DBSException("Bad Data", "300", "Null Fields. Expected /DATASET/TIER/OWNER");
		}
		if(prim.length() < 1 || dt.length() < 1 || proc.length() < 1) {
			throw new DBSException("Bad Data", "300", "Null Fields. Expected /DATASET/TIER/OWNER");
		}
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getProcessedDSID(prim, dt, proc));
		if(!rs.next()) {
			throw new DBSException("Bad Data", "300", "No such processed dataset /" + prim + "/" + dt + "/" +proc );
		}
		return  rs.getString("id");
	}

	private String getBlockID(Connection conn, String blockName) throws Exception {
		if(blockName == null) {
			throw new DBSException("Bad Data", "300", "Null Fields. Expected a Block Name");
		}
		if(blockName.length() < 1 ) {
			throw new DBSException("Bad Data", "300", "Null Fields. Expected a Block Name");
		}
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getBlockID(blockName));
		if(!rs.next()) {
			throw new DBSException("Bad Data", "300", "No such Block " + blockName );
		}
		return  rs.getString("id");
	}

	private void checkPattern(String pattern) throws Exception {
		if(pattern == null) {
			throw new DBSException("Bad Data", "300", "Expected /PRIMARY/TIER/PROCESSED/VERSION/FAMILY/EXECUTABLE");
		}
		if (! Pattern.matches(VALID_PATTERN, pattern) ) {
			throw new DBSException("Bad Data", "300", "Expected /PRIMARY/TIER/PROCESSED/VERSION/FAMILY/EXECUTABLE");
		}
		if( ! Pattern.matches(SAFE_PATH, pattern) ) {
			throw new DBSException("Bad Data", "300", "Invalid Characters in " + pattern + " Expected "+ SAFE_PATH);
		}
	}
	
	private void checkPath(String path) throws Exception {
		if(path == null) {
			throw new DBSException("Bad Data", "300", "Expected /PRIMARY/TIER/PROCESSED or /APPVERSION/APPFAMILY/APPEXE");
		}
		if (! Pattern.matches(VALID_PATH, path) ) {
			throw new DBSException("Bad Data", "300", "Expected /PRIMARY/TIER/PROCESSED or /APPVERSION/APPFAMILY/APPEXE");
		}
		if( ! Pattern.matches(SAFE_PATH, path) ) {
			throw new DBSException("Bad Data", "300", "Invalid Characters in " + path + " Expected "+ SAFE_PATH);
		}
	}
		
	private void checkName(String pattern) throws Exception {
		if (! Pattern.matches(SAFE_NAME, pattern)) {
			throw new DBSException("Bad Data", "300", "Invalid Characters in " + pattern + " Expected "+ SAFE_NAME);
		}
	}

}
