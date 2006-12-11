/**
 $Revision: 1.36 $"
 $Id: DBSApi.java,v 1.36 2006/12/08 20:59:24 sekhri Exp $"
 *
*/

package dbs.api;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.Writer;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import xml.DBSXMLParser;
import xml.Element;
import xml.XMLException;
import db.DBManagement;
import dbs.DBSConstants;
import dbs.sql.DBSSql;
import dbs.DBSException;
import dbs.util.DBSUtil;
import db.DBManagement;
import java.sql.ResultSet;
import dbs.util.DBSConfig;

/**
 * This class encapsulate <code>dbs.api.DBSApiLogic</code> , handles database connection management,handle XML parsing for the input provided by the clients, checks the match between schema and api version and handles exceptions. This class works as the higher level dispatcher for DBS API. All the DBS API calls are invoked vias a public method call. The interface of this class is this call method which can take a hashtable of key value pairs. It invokes the API call depending upon the value of the api key in the hashtable. The reason for having this higher level class is to separate the lovel level business logic from database connection management and xml parsing.<br>
* Most of the insert API calls requires an xml input that needs to be parsed and converted into a hastable that can be further passed down to <code>dbs.api.DBSApiLogic</code> class. Here are the sample XML input required by various insert APIs <br> <br><code>
 * <b>insertPrimaryDataset</b> <br>
	<"?xml version='1.0' standalone='yes'?"> <br>
			<"dbs"> <br>
				<"primary-dataset annotation='aaaa' primary_name='abcd' start_date='NOV' end_date='DEC' trigger_path_description='anyTD' mc_channel_description='MCDesc' mc_production='MCProd' mc_decay_chain='DC' other_description='OD' type='VALID'">
				<"/primary-dataset"> <br>
 			<"/dbs"> <br>
 * <br>		
 * <br>		
 * <b>insertAlgorithm</b> <br>
		<"?xml version='1.0' standalone='yes'?"> <br>
		<"dbs"> <br>
			<"algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps_name2' ps_hash='DUMMY_HASH' ps_version='DUMMY1' ps_type='DUMMYTYPE1' ps_annotation='ANN1' ps_content='DUMMYCON'/"> <br>
		<"/dbs"> <br>
		
 * <br>		
 * <br>		
 * <b>insertRun</b> <br>
		<"?xml version='1.0' standalone='yes'?"> <br>
		<"dbs"> <br>
			<"run run_number='111' number_of_events='54' number_of_lumi_sections='12' total_luminosity='2' store_number='32' start_of_run='nov' end_of_run='dec'/"> <br>
		<"/dbs"> <br>
	
 * <br>		
 * <br>		
 * <b>insertLumiSection</b> <br>
		<"?xml version='1.0' standalone='yes'?"> <br>
		<"dbs"> <br>
			<"lumi lumi_section_number='1111' run_number='11' start_event_number='20' end_event_number='200' lumi_start_time='nov' lumi_end_time='dec'/"> <br>
		<"/dbs"> <br>

 * <br>		
 * <br>		
 * <b>insertProcessedDataset</b>  <br>
		<"?xml version='1.0' standalone='yes'?"> <br>
		<"dbs"> <br>
			<"processed-dataset primary_datatset_name='$primary_name' processed_datatset_name='$processed_name' open_for_writing='y' physics_group_name='AnyName' physics_group_convener='ANZARDN' status='VALID'"> <br>
				<"data_tier name='$tier_name1'/"> <br>
				<"algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps_name2' ps_hash='DUMMY_HASH' ps_version='DUMMY1' ps_type='DUMMYTYPE1' ps_annotation='ANN1' ps_content='DUMMYCON'/"> <br>
				<"algorithm app_version='MyVersion2' app_family_name='MyFamily2' app_executable_name='MyExe2' ps_name='DUMMY_ps_name2' ps_hash='DUMMY_HASH' ps_version='DUMMY2' ps_type='DUMMYTYPE2' ps_annotation='ANN2' ps_content='DUMMYCON'/"> <br>
				<"run run_number='222'/"> <br>
				<"run run_number='111'/"> <br>
			<"/processed-dataset"> <br>
		<"/dbs"> <br>

 * <br>		
 * <br>		
 * <b>insertBlock</b> <br>
		<"?xml version='1.0' standalone='yes'?"> <br>
		"<"dbs"> <br>
			<"block path='$path' name='$block_name' open_for_writing='1'/"> <br>
		<"/dbs"> <br>

 * <br>		
 * <br>		
 * <b>insertFiles</b> <br>
		<"?xml version='1.0' standalone='yes'?"> <br>
		<"dbs"> <br>
		<"processed_datatset path='$path' block_name='$block_name'"> <br>
			<"file lfn='TEST_LFN_1' checksum='CHKSUM' number_of_events='200' size='200' file_status='VALID' type= 'EVD' validation_status='VALID' queryable_meta_data='any'"> <br>
				<"lumi_section lumi_section_number='9997' run_number='$run_number1' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/"> <br>
				<"lumi_section lumi_section_number='9996' run_number='$run_number1' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/"> <br>
				<"lumi_section lumi_section_number='9995' run_number='$run_number2' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/"> <br>
				<"data_tier name='$tier_name1'/"> <br>
				<"data_tier name='$tier_name2'/"> <br>
				<"algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps_name1' ps_hash='DUMMY_HASH' ps_version='DUMMY1' ps_type='DUMMYTYPE2' ps_annotation='ANN1' ps_content='DUMMYCON'/"> <br>
				<"algorithm app_version='MyVersion2' app_family_name='MyFamily2' app_executable_name='MyExe2' ps_name='DUMMY_ps_name2' ps_hash='DUMMY_HASH' ps_version='DUMMY2' ps_type='DUMMYTYPE2' ps_annotation='ANN2' ps_content='DUMMYCON'/"> <br>
			<"/file"> <br>
			<"file lfn='TEST_LFN_2' checksum='CHKSUM2' number_of_events='300' size='2002' file_status='MERGED' type= 'EVD' validation_status='VALID' queryable_meta_data='any'"> <br>
				<"lumi_section lumi_section_number='1006' run_number='$run_number1' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/"> <br>
				<"lumi_section lumi_section_number='1017' run_number='$run_number2' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/"> <br>
				<"lumi_section lumi_section_number='1028' run_number='$run_number1' start_event_number='4' end_event_number='7' lumi_start_time='nov' lumi_end_time='dec'/"> <br>
				<"data_tier name='$tier_name1'/"> <br>
				<"data_tier name='$tier_name2'/"> <br>
				<"parent lfn='TEST_LFN_1'/"> <br>
				<"algorithm app_version='MyVersion1' app_family_name='MyFamily1' app_executable_name='MyExe1' ps_name='DUMMY_ps_name1' ps_hash='DUMMY_HASH' ps_version='DUMMY1' ps_type='DUMMYTYPE2' ps_annotation='ANN1' ps_content='DUMMYCON'/"> <br>
			<"/file"> <br>
		<"/processed_datatset"> <br>
		<"/dbs"> <br>
 * </code>
 * @author sekhri
 * 
 */
public class DBSApi {
	private DBSApiLogic api;
	
	/**
	* Constructs a DBSApi object that can be used to invoke call method that invokes several APIs from <code>dbs.api.DBSApiLogic</code> class. The constructor instantiates a private <code>dbs.api.DBSApiLogic</code> object.
	*/
	public DBSApi() {
		api = new DBSApiLogic();
	}

        public Vector supportedClientApiVersions() throws Exception {
		//FIXME ASK Anzar  to use some conventions in declaing vaiables.
                DBSConfig config = DBSConfig.getInstance();
                String clientVers = config.getSupportedClientVersions(); 
                String[] result = clientVers.split(","); 
                //String[] result = clientVers.split("\\s"); 
                //FIXME Turning array into a verstor is just something not required, fix this later 
		Vector supported_version_list  = new Vector();
                for (int x=0; x<result.length; x++) {
                    supported_version_list.add(result[x].trim());
                }
		return supported_version_list;

        }

	public String supportedSchemaVersions() throws Exception {
               //Get Schema Version from DBSConfig
               DBSConfig config = DBSConfig.getInstance();
               String suppSchemaVer = config.getSupportedSchemaVersion();
	       return suppSchemaVer;
	}

        //This func must move to Utility
        private boolean isIn(String param, Enumeration e) {
                while (e.hasMoreElements()) {
                        if( param.equals((String)e.nextElement()) ) {
                                return true;
                        }
                }
                return false;
        }

	private void checkSchemaVersion() throws Exception {
		Connection conn =  getConnection();
		try {
			String sql = "select SchemaVersion from SchemaVersion";
			DBSUtil.writeLog(sql);
			ResultSet rs =  DBSSql.getSchemaVersion(conn).executeQuery();
			String dbsSchemaVersion="";
			if(rs.next()) {
				dbsSchemaVersion = rs.getString("SchemaVersion");
			} else {
				throw new DBSException("Schema Version Failure", "1001", "Unable to get Schema Version from Database, cannot continue");
			} 
			String suppSchemaVer = supportedSchemaVersions();
			if (! dbsSchemaVersion.equals(suppSchemaVer) ) {
				throw new DBSException("Unsupported Schema version", "1002", "Database Schema Mismatch, $DBS_CONFIG Version is " + suppSchemaVer + " Current schema version in DB is :" + dbsSchemaVersion); 
			}
		} finally {
			if(conn != null) conn.close();
                }
        }  

        private void checkVersion(String apiversion) throws Exception {
		//FIXME I dont think this api check is does anything. Anyways it has to move in the call method insated of the constructor
                Enumeration verEnum = supportedClientApiVersions().elements();
                String msg  = "Incorrect API version specified '"+apiversion+"'";
                       msg += " Supported versions are: ";

                for (Enumeration e = supportedClientApiVersions().elements() ; 
                                 e.hasMoreElements() ;) {
                    msg += " "+(String)e.nextElement();
                }

                if ( ! isIn(apiversion, verEnum ) ) {
                        throw new DBSException("BAD Api Version", "1003",  msg);
                        //return;
                }


                //Now lets check the Schema version too !!!!!!!!!!!!
        }

	/**
	 * This method is the main interface for invoking the various API calls. It makes a database connection, extract the key value pairs from the input hashtable and calls the low level DBSApiLogic with the extracted parameters. If the input parameters are in xml format then it parse the xml and packs those parameters in another hashtable and pass that to the low level DBSApiLogic.
	 * If any exception is occured then it takes that exception and converts it into a proper string message and writes it ont to the output stream  that gets transferred back to the client. Any hash table with any key value pair can be passed to this call method and it determines which low level DBSApiLogic api to call depending upon the value of api parameter.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into and passed it further down to the DBSApiLogic apis.
	 * @param table a <code>java.util.Hashtable</code> that contains all the necessary key value pairs required for any particular api. It determines which api to call by the value of the api field in the table. For the various API the following key value parsie may or may not be required. <br>
	 * listPrimaryDatasets - pattern <br>
	 * listProcessedDatasets - primary_datatset_name_pattern, data_tier_name_pattern, processed_datatset_name_pattern, app_version,	app_family_name, app_executable_name, parameterset_name <br>
	 * listAlgorithms - app_version, app_family_name, app_executable_name, 	parameterset_name <br>
	 * listRuns - path <br>
	 * listTiers - path <br>
	 * listBlocks - path <br>
	 * listFiles - path, block_name, pattern_lfn <br>
	 * listDatasetContents - path<br>
	 * insertPrimaryDataset - xmlinput <br>
	 * insertAlgorithm - xmlinput <br>
	 * insertRun - xmlinput <br>
	 * insertTier - tier_name <br>
	 * insertLumiSection - xmlinput <br>
	 * insertProcessedDataset - xmlinput <br>
	 * createAnalysisDatasetFromPD - xmlinput <br>
	 * insertBlock - xmlinput <br>
	 * insertFiles - xmlinput <br>
	 * insertTierInPD - path, tier_name <br>
	 * insertParentInPD - path, parent_path <br>
	 * insertAlgoInPD - path, xmlinput <br>
	 * insertRunInPD - path, run_number <br>
	 * insertTierInFile - lfn, tier_name <br>
	 * insertParentInFile - lfn, parent_lfn <br>
	 * insertAlgoInFile - lfn, xmlinput <br>
	 * insertLumiInFile - lfn, ls_number <br>
	 * 
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception All the exceptions that are thrown by the underlying layesr are caught and written down in the output stream that goes back to the client in proper XML format. Only an un expected exception may be raised in this method when trying to wirte to the output stream, which may not propogate back to the client.
	 */
	public void call(Writer out, Hashtable table, Hashtable dbsUser) throws Exception {
                
		Connection conn = null;

		try {

			out.write(DBSConstants.XML_HEADER); 
			String apiStr = get(table, "api", true);
        	        String apiVersion = get(table, "apiversion", true);
                	DBSUtil.writeLog("apiStr: "+apiStr);

	                checkVersion(get(table, "apiversion", true));
 
        	        checkSchemaVersion();


			conn = getConnection();
			conn.setAutoCommit(false);
			if (apiStr.equals("listPrimaryDatasets")) {
				api.listPrimaryDatasets(conn, out, get(table, "pattern", false));
				
			} else if (apiStr.equals("listProcessedDatasets")) {
				api.listProcessedDatasets(conn, out, 
						get(table, "primary_datatset_name_pattern", false),
						get(table, "data_tier_name_pattern", false),
						get(table, "processed_datatset_name_pattern", false),
						get(table, "app_version", false),
						get(table, "app_family_name", false),
						get(table, "app_executable_name", false),
						get(table, "parameterset_name", false)
						);
			} else if (apiStr.equals("listDatasetContents")) {
				api.listDatasetContents(conn, out, 
						get(table, "path", false),
						get(table, "block_name", false)
						);
			} else if (apiStr.equals("listDatasetParents")) {
				api.listDatasetParents(conn, out, 
						get(table, "path", true)
						);

			} else if (apiStr.equals("listAlgorithms")) {
				api.listAlgorithms(conn, out,
						get(table, "app_version", false),
						get(table, "app_family_name", false),
						get(table, "app_executable_name", false),
						get(table, "parameterset_name", false)
						);
				
			} else if (apiStr.equals("listRuns")) {
				api.listRuns(conn, out, get(table, "path", true));
				
			} else if (apiStr.equals("listTiers")) {
				api.listTiers(conn, out, get(table, "path", true));
				
			} else if (apiStr.equals("listBlocks")) {
				api.listBlocks(conn, out, 
						get(table, "path", true),
						get(table, "block_name", false)
						);
			} else if (apiStr.equals("listFiles")) {
				api.listFiles(conn, out, 
						get(table, "path", false),
						get(table, "block_name", false),
						get(table, "pattern_lfn", false)
						);
			} else if (apiStr.equals("listFileParents")) {
				api.listFileParents(conn, out, 
						get(table, "lfn", true)
						);
			} else if (apiStr.equals("listFileAlgorithms")) {
				api.listFileAlgorithms(conn, out, 
						get(table, "lfn", true)
						);
			} else if (apiStr.equals("listFileTiers")) {
				api.listFileTiers(conn, out, 
						get(table, "lfn", true)
						);
			} else if (apiStr.equals("listFileLumis")) {
				api.listFileLumis(conn, out, 
						get(table, "lfn", true)
						);
	
			} else if (apiStr.equals("insertPrimaryDataset")) {
				api.insertPrimaryDataset(conn, out,
						parse( getXml(table), "primary-dataset") , 
						dbsUser);
				
			} else if (apiStr.equals("insertAlgorithm")) {
				api.insertAlgorithm(conn, out,
						parse(getXml(table), "algorithm") , 
						dbsUser);
				
			} else if (apiStr.equals("insertRun")) {
				api.insertRun(conn, out,
						parse(getXml(table), "run") , 
						dbsUser);
				
			} else if (apiStr.equals("insertTier")) {
				api.insertTier(conn, out, get(table, "tier_name", true), dbsUser);
				
			} else if (apiStr.equals("insertLumiSection")) {
				api.insertLumiSection(conn, out,
						parse(getXml(table), "lumi") , 
						dbsUser);
				
			} else if (apiStr.equals("insertProcessedDataset")) {
				api.insertProcessedDataset(conn, out,  parsePD(getXml(table)), dbsUser);
				
			} else if (apiStr.equals("createAnalysisDatasetFromPD")) {
				api.createAnalysisDatasetFromPD(conn, out,
					parse(getXml(table), "analysis-dataset"),
					dbsUser);
                        } else if (apiStr.equals("insertBlock")) {
				api.insertBlock(conn, out,
						parse(getXml(table), "block") , 
						dbsUser);
				
			} else if (apiStr.equals("insertFiles")) {
				insertFiles(conn, out, getXml(table), dbsUser);
				
			} else if (apiStr.equals("insertTierInPD")) {
				api.insertTierInPD(conn, out,
						get(table, "path", true), 
						get(table, "tier_name", true), 
						dbsUser);
				
			} else if (apiStr.equals("insertParentInPD")) {
				api.insertParentInPD(conn, out,
						get(table, "path", true), 
						get(table, "parent_path", true), 
						dbsUser);
					
			} else if (apiStr.equals("insertAlgoInPD")) {
				api.insertAlgoInPD(conn, out,
						get(table, "path", true), 
						parse(getXml(table), "algorithm"), 
						dbsUser);
			
			} else if (apiStr.equals("insertRunInPD")) {
				api.insertRunInPD(conn, out,
						get(table, "path", true), 
						get(table, "run_number", true), 
						dbsUser);
				
			} else if (apiStr.equals("insertTierInFile")) {
				api.insertTierInFile(conn, out,
						get(table, "lfn", true), 
						get(table, "tier_name", true), 
						dbsUser);
				
			} else if (apiStr.equals("insertParentInFile")) {
				api.insertParentInFile(conn, out,
						get(table, "lfn", true), 
						get(table, "parent_lfn", true), 
						dbsUser);
				
			} else if (apiStr.equals("insertAlgoInFile")) {
				api.insertAlgoInFile(conn, out,
						get(table, "lfn", true), 
						parse(getXml(table), "algorithm"), 
						dbsUser);
				
			} else if (apiStr.equals("insertLumiInFile")) {
				api.insertLumiInFile(conn, out,
						get(table, "lfn", true), 
						get(table, "ls_number", true), 
						dbsUser);
				
			} else {
				writeException(out, "Invalid API", "1018", "The api " + apiStr + " provided by the client is not valid");
				return;
			}
			conn.commit();
		} catch (DBSException dbsEx) {
			if(conn != null) conn.rollback();
			if (dbsEx.getMessage() == null ) {
				writeException(out, "Unexpected execution exception", "1031", "NULL POINTER DBSException");
				return;
			}
			dbsEx.printStackTrace();
			writeException(out, dbsEx.getMessage(), dbsEx.getCode(), dbsEx.getDetail());
			return;
		} catch (XMLException xmlEx) {
			if(conn != null) conn.rollback();
			if (xmlEx.getMessage() == null ) {
				writeException(out, "Unexpected execution exception", "3003", "NULL POINTER SQLException");
				return;
			}
			xmlEx.printStackTrace();
			writeException(out, xmlEx.getMessage(), xmlEx.getCode(), xmlEx.getDetail());
			return;
		} catch (SQLException sqlEx) {
			if(conn != null) conn.rollback();
			if (sqlEx.getMessage() == null ) {
				writeException(out, "Unexpected execution exception", "2001", "NULL POINTER SQLException");
				return;
			}
			sqlEx.printStackTrace();
			writeException(out, "Database exception", "2000", sqlEx.getMessage());
			return;
		} catch (Exception ex) {
			if(conn != null) conn.rollback();
			ex.printStackTrace();
			if (ex.getMessage() == null ) {
				writeException(out, "Unexpected execution exception", "4001", "NULL POINTER Exception");
				return;
			}
			writeException(out, "Unexpected execution exception", "4000", ex.getMessage());
			return;
		} finally {
			if(conn != null) conn.close();
		}

		out.write(DBSConstants.XML_SUCCESS);
		out.write(DBSConstants.XML_FOOTER);
		out.flush();
	}
	
	private String get(Hashtable table, String key, boolean excep) throws Exception {
		String value = "";
		if ( isNull(value = DBSUtil.get(table, key)) ) {
			if (excep) throw new DBSException("Mandatory data missing", "1004", "Null Fields. Expected a valid " + key);
		}
		return value;
	}
	
	
	private String getXml(Hashtable table) throws Exception {
		return 	get(table, "xmlinput", true);
	}
	
	public void writeException(Writer out, String message, String code, String detail) throws Exception {
		//out.write(DBSConstants.XML_EXCEPTION_HEADER); 
                message = message.replace('\'',' ');
                detail= detail.replace('\'',' ');
                code =code.replace('\'',' ');
		out.write("<exception message='" + message + "' "); 
		out.write(" code ='" + code + "' "); 
		out.write(" detail ='" + detail + "' />\n"); 
		out.write(DBSConstants.XML_FOOTER);
		out.flush();
		//out.write(DBSConstants.XML_EXCEPTION_FOOTER); 
	}


	/*
	
	private void writeWarnning(Writer out, String message) throws Exception {
		out.write(DBSConstants.XML_WARNNING_HEADER); 
		out.write(message); 
		out.write(DBSConstants.XML_WARNNING_FOOTER); 
	}*/

	private Connection getConnection() throws Exception {
		Connection conn = DBManagement.getDBConnManInstance().getConnection();
		if (conn != null) {
       			DBSUtil.writeLog("Pooling at work");
			return conn;
		} else {
			DBSUtil.writeLog("Pooling not required for standalone client");
			DBSConfig config = DBSConfig.getInstance();
			return DBManagement.getConnection( config.getDbDriver(),
					config.getDbURL(), 
					config.getDbUserName(),
					config.getDbUserPasswd());
					
		}
		
	}
	
	private Hashtable parse(String inputXml, String key) throws Exception {
		//	checkXML(inputXml);
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
 		Vector allElement = dbsParser.getElements();
		Hashtable table = null;
		for (int i=0; i<allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			String name = e.name;
			if (name.equals(key) ) {
				table = e.attributes;
			} 
		}
		return table;
	}
	
	private Hashtable parsePD(String inputXml) throws Exception {
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
		Vector allElement = dbsParser.getElements();
		Hashtable psDS = null;
		for (int i=0; i<allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			String name = e.name;
			if (name.equals("processed-dataset") ) {
				psDS = e.attributes;
				psDS.put("data_tier", new Vector());
				psDS.put("parent", new Vector());
				psDS.put("algorithm", new Vector());
				psDS.put("run", new Vector());
			} 
			if (name.equals("data_tier") ) 
				((Vector)(psDS.get("data_tier"))).add(e.attributes);
			if (name.equals("parent") ) 
				((Vector)(psDS.get("parent"))).add(e.attributes);
			if (name.equals("algorithm") ) 
				((Vector)(psDS.get("algorithm"))).add(e.attributes);
			if (name.equals("run") ) 
				((Vector)(psDS.get("run"))).add(e.attributes);
		}
		return psDS;
	}
	
	private void insertFiles(Connection conn, Writer out, String inputXml, Hashtable dbsUser) throws Exception {
		Vector topLevel = new Vector();
		int index = -1;
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
    		Vector allElement = dbsParser.getElements();
		Hashtable psDS = null;
		for (int i=0; i<allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			String name = e.name;
			if (name.equals("file") ) {
				Hashtable file = e.attributes;
				file.put("lumi_section", new Vector());
				file.put("data_tier", new Vector());
				file.put("parent", new Vector());
				file.put("algorithm", new Vector());
				topLevel.add(file);
				++index;
			} 
			if (name.equals("lumi_section") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("lumi_section"))).add(e.attributes);
			if (name.equals("data_tier") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("data_tier"))).add(e.attributes);
			if (name.equals("parent") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("parent"))).add(e.attributes);
			if (name.equals("algorithm") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("algorithm"))).add(e.attributes);
			if (name.equals("processed_datatset") ) {
				psDS = e.attributes;
			}
		}
		api.insertFiles(conn, out, DBSUtil.get(psDS, "path"), DBSUtil.get(psDS, "block_name"), topLevel, dbsUser);
	}

	
	private void put(Hashtable table, String key, String value) {
		if(isNull(value)) table.put(key, "");
		else table.put(key, value);
	}
	

	private boolean isNull(String pattern) {
		return DBSUtil.isNull(pattern);
	}
	
}
