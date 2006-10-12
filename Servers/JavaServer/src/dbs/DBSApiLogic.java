/**
 * @author sekhri
 *
 */


package dbs;
import java.sql.Connection;
import java.sql.ResultSet;
import java.io.Writer;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import db.DBManagement;
public class DBSApiLogic {
	private static String XML_HEADERa = "Dbs-status-message: Success\n" +
						"Dbs-status-code: 100\n" +
						"Content-Type: text/plain; charset=ISO-8859-1\n\n" +
						"<?xml version='1.0' standalone='yes'?><dbs>";
	private static String XML_HEADER =  "<?xml version='1.0' standalone='yes'?>\n<dbs>\n";
	private static String XML_FOOTER = "</dbs>\n";
	private static String SAFE_PATH = "[-\\w_\\.%/]+";
	//private static String SAFE_PATH = "[-A-Za-z0-9_./\\p{%}]";
	//private static String SAFE_NAME = "[-A-Za-z0-9_.]";
	private static String SAFE_NAME = "[-\\w_\\.%]+";
	private static String VALID_PATH = "^/([^/]+)/([^/]+)/([^/]+)";
	//private static String SUCCESS_HEADER = "Dbs-status-message: Success\nDbs-status-code: 100\nContent-Type: text/plain; charset=ISO-8859-1\n\n";

	public DBSApiLogic() {
		//System.out.println("Constructor DBSApiLogic");
	}

        public void createPrimaryDataset(Connection conn, String primaryDatasetName) throws Exception {
                checkName(primaryDatasetName);
                boolean rs =  DBManagement.execute(conn, DBSSql.createPrimaryDataset(primaryDatasetName));
        }

	
	public void getDatasetInfo(Connection conn, Writer out, String dsPath) throws Exception {
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getPrimaryDS(""));
		int col = 2;
		while(rs.next()) {
			System.out.println(rs.getString(col));
		}
	}

	public void listPrimaryDatasets(Connection conn, Writer out, String pattern) throws Exception {
		pattern = pattern.replace('*','%');
		checkName(pattern);
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getPrimaryDS(pattern));
		out.write(XML_HEADER); 
		while(rs.next()) {
			//for(int i = 0 ; i!= 2; ++i)
			out.write(((String) "<primary-dataset id='" + rs.getString("id") + 
						"' annotation='" + rs.getString("annotation") +
						"' primary_name='" + rs.getString("primary_name") +
						"' trigger_path_description='" + rs.getString("trigger_path_description") +
						"' mc_channel_description='" + rs.getString("mc_channel_description") +
						"' mc_production='" + rs.getString("mc_production") +
						"' mc_decay_chain='" + rs.getString("mc_decay_chain") +
						"' other_description='" + rs.getString("other_description") +
						"' start_date='" + rs.getString("start_date") +
						"' end_date='" + rs.getString("end_date") +
						"' file_type='" + rs.getString("file_type") +
						"' created_by='" + rs.getString("created_by") +
						"' creation_date='" + rs.getString("creation_date") +
						"' last_modification_by='" + rs.getString("last_modification_by") +
						"' last_modified_by='" + rs.getString("last_modified_by") +
						"'/>\n"));
			//out.flush();
		}
		out.write(XML_FOOTER);
	}

	public void listProcessedDatasets(Connection conn, Writer out, String patternDs, String patternApp) throws Exception {
		patternDs = patternDs.replace('*','%');
		patternApp = patternApp.replace('*','%');
		checkPath(patternDs);
		checkPath(patternApp);

		String[] dataDs = patternDs.split("/");
		String[] dataApp = patternDs.split("/");

		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getProcessedDS(dataDs[1], dataDs[2], dataDs[3], dataApp[1], dataApp[2], dataApp[3]));
		out.write(XML_HEADER);
		while(rs.next()) {
			//String path = "/" + rs.getString("primary_name") + "/" + rs.getString("data_tier") + "/" + rs.getString("processed_name");
			out.write(((String) "<processed-dataset id='" + rs.getString("id") + 
						"' path='" +  rs.getString("path") +
						"' open_for_writing='" + rs.getString("open_for_writing") +
						"' creation_date='" + rs.getString("creation_date") +
						"' last_modification_date='" + rs.getString("last_modification_date") +
						"' run_number='" + rs.getString("run_number") +
						"' first_event_number='" + rs.getString("first_event_number") +
						"' last_event_number='" + rs.getString("last_event_number") +
						"' start_of_run='" + rs.getString("start_of_run") +
						"' end_of_run='" + rs.getString("end_of_run") +
						"' physics_group_name='" + rs.getString("physics_group_name") +
						"' physics_group_convener='" + rs.getString("physics_group_convener") +
						"' conditions='" + rs.getString("conditions") +
						"' app_version='" + rs.getString("app_version") +
						"' app_family_name='" + rs.getString("app_family_name") +
						"' app_executable_name='" + rs.getString("app_executable_name") +
						"'/>\n"));
		}
		out.write(XML_FOOTER);
	}

	public void listParameterSets(Connection conn, Writer out, String pattern) throws Exception {
		pattern = pattern.replace('*','%');
		checkName(pattern);
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getPrameterSets(pattern));
		out.write(XML_HEADER);
		while(rs.next()) {
			out.write(((String) "<parameter-set id='" + rs.getString("id") + 
						"' hash='" + rs.getString("hash") + 
						"' content='" + rs.getString("content") + "'/>"));
		}
		out.write(XML_FOOTER);
	}

	public void listApplications(Connection conn, Writer out, String pattern) throws Exception {
		pattern = pattern.replace('*','%');
		checkName(pattern);
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getApplications(pattern));
		out.write(XML_HEADER);
		while(rs.next()) {
			out.write(((String) "<application id='" + rs.getString("id") + 
						"' family='" + rs.getString("family") + 
						"' executable='" + rs.getString("executable") + 
						"' version='" +  rs.getString("version") + "'/>"));
		}
		out.write(XML_FOOTER);
	}

	public void listApplicationConfigs(Connection conn, Writer out, String pattern) throws Exception {
		pattern = pattern.replace('*','%');
		checkName(pattern);
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getApplicationConfigs(pattern));
		out.write(XML_HEADER);
		while(rs.next()) {
			out.write(((String) "<application id='" + rs.getString("application_id") + 
						"' family='" + rs.getString("family") + 
						"' executable='" + rs.getString("executable") + 
						"' version='" +  rs.getString("version") + 
						"'><app-config id='" + rs.getString("app_config_id") + 
						"' psetid='" + rs.getString("pset_id") + 
						"' hash='" +  rs.getString("hash") + 
						"' content='" +  rs.getString("content") + "'/></application>"));
		}
		out.write(XML_FOOTER);
	}

	public void getDatasetContents(Connection conn, Writer out, String dsPath) throws Exception {
		checkPath(dsPath);

		String[] data = dsPath.split("/");
		String procID = getProcessedDSID(conn, data[1], data[2], data[3]);
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getEventCollections(procID));
		out.write(XML_HEADER);
		out.write(((String) "<processed-dataset id='" + procID + 
					"' path='" + dsPath + "'>"));
		String prev = "";
		boolean first = true;
		String blockBase = "/" + data[1] + "/" +  data[3];
		while(rs.next()) {
			String blockID =  rs.getString("block_id");
			if( !prev.equals(blockID) && ! first) {
				out.write(((String) "</block>" ));
			}
			
			if( !prev.equals(blockID) || first) {
				out.write(((String) "<block id ='" + blockID + 
						"' name='" + blockBase + "#" + blockID +
						"' status='" +  rs.getString("block_status") + "'>"));
				first = false;
				prev = blockID;
			}
			out.write(((String) "<event-collection id='" +  rs.getString("evc_id") + 
						"' name='" + rs.getString("evc_name") + 
						"' events='" + rs.getString("events") + 
						"' status='" + rs.getString("evc_status") + "'/>"));
		}
		if(!first) {
			out.write(((String) "</block>" ));
		}
		out.write(((String) "</processed-dataset>" ));
		out.write(XML_FOOTER);
	}


	public void getDatasetFiles(Connection conn, Writer out, String dsPath) throws Exception {
		checkPath(dsPath);

		String[] data = dsPath.split("/");
		String procID = getProcessedDSID(conn, data[1], data[2], data[3]);
		ResultSet rs =  DBManagement.executeQuery(conn, DBSSql.getFiles(procID));
		out.write(XML_HEADER);
		out.write(((String) "<processed-dataset id='" + procID + 
					"' path='" + dsPath + "'>"));
		String prev = "";
		boolean first = true;
		String blockBase = "/" + data[1] + "/" +  data[3];
		while(rs.next()) {
			String blockID =  rs.getString("block_id");
			if( !prev.equals(blockID) && ! first) {
				out.write(((String) "</block>" ));
			}
			
			if( !prev.equals(blockID) || first) {
				out.write(((String) "<block id ='" + blockID + 
						"' name='" + blockBase + "#" + blockID +
						"' status='" +  rs.getString("block_status") + 
						"' files='" +  rs.getString("files") + 
						"' bytes='" +  rs.getString("bytes") +"'>"));
				first = false;
				prev = blockID;
			}
			out.write(((String) "<file id='" +  rs.getString("file_id") + 
						"' inblock='" + blockID + 
						"' guid='" + rs.getString("guid") + 
						"' lfn='" + rs.getString("lfn") + 
						"' checksum='" + rs.getString("checksum") + 
						"' size='" + rs.getString("size") + 
						"' status='" + rs.getString("file_status") + 
						"' type='" + rs.getString("type") + "'/>"));
		}
		if(!first) {
			out.write(((String) "</block>" ));
		}
		out.write(((String) "</processed-dataset>" ));
		out.write(XML_FOOTER);
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
	
	private void checkPath(String path) throws Exception {
		if(path == null) {
			throw new DBSException("Bad Data", "300", "Expected /DATASET/TIER/OWNER");
		}
		if (! Pattern.matches(VALID_PATH, path) ) {
			throw new DBSException("Bad Data", "300", "Expected /DATASET/TIER/OWNER");
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
