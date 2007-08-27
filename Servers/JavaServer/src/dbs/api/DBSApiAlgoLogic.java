/**
 $Revision: 1.14 $"
 $Id: DBSApiAlgoLogic.java,v 1.14 2007/08/20 16:39:59 sekhri Exp $"
 *
 */

package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.util.Hashtable;
import dbs.sql.DBSSql;
import codec.Base64;
import dbs.DBSException;

/**
* A class that has the core business logic of all the algorithm APIs.  The signature for the API is internal to DBS and is not exposed to the clients. There is another class <code>dbs.api.DBSApi</code> that has an interface for the clients. All these low level APIs are invoked from <code>dbs.api.DBSApi</code>. This class inherits from DBSApiLogic class.
* @author sekhri
*/
public class DBSApiAlgoLogic extends DBSApiLogic {
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs. 
	*/
	DBSApiPersonLogic personApi = null;
	DBSApiData data = null;
	public DBSApiAlgoLogic(DBSApiData data) {
		super(data);
		this.data = data;
		personApi = new DBSApiPersonLogic(data);
	}


	/**
	 * Lists all the algorithms/applications from the database in a xml format. This method makes one sql query, execute it, fetch the results and packs and write it in xml format to the output stream. The query that it executes get generated by <code>dbs.DBSSql.listAlgorithms</code> method. A sample XML that is written to the output stream is like <br>
	 * <code> <"algorithm id='3' app_version='MyVersion12' app_family_name='MyFamily' app_executable_name='MyExe' ps_name='DUMMY_ps_nam' ps_hash='DUMMY_HASH' creation_date='2006-12-06 16:12:10.0' last_modification_date='2006-12-06 16:12:10.0' created_by='ANZARDN' last_modified_by='ANZARDN'"/> </code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param patternVer a parameter passed in from the client that can contain wild card characters for application version. This pattern is used to restrict the SQL query results by sustitution it in the WHERE clause.
	 * @param patternFam a parameter passed in from the client that can contain wild card characters for application family. This pattern is used to restrict the SQL query results by sustitution it in the WHERE clause.
	 * @param patternExe a parameter passed in from the client that can contain wild card characters for application executable name. This pattern is used to restrict the SQL query results by sustitution it in the WHERE clause.
	 * @param patternPS a parameter passed in from the client that can contain wild card characters for parameter set name. This pattern is used to restrict the SQL query results by sustitution it in the WHERE clause.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied pattern parameters are invalid or the database connection is unavailable.
	 */
	public void listAlgorithms(Connection conn, Writer out, String patternVer, String patternFam, String patternExe, String patternPS) throws Exception {
		//FIXME name should be changed to hash
		patternVer	= getPattern(patternVer, "app_version");
		patternFam	= getPattern(patternFam, "app_family_name");
		patternExe	= getPattern(patternExe, "app_executable_name");
		patternPS 	= getPattern(patternPS, "ps_hash");

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
						"' ps_version='" + get(rs, "PS_VERSION") +
						"' ps_type='" + get(rs, "PS_TYPE") +
						//"' ps_annotation='" + get(rs, "PS_ANNOTATION") +
						"' ps_annotation='" + Base64.encodeBytes(get(rs, "PS_ANNOTATION").getBytes()) +
						"' ps_content='" + Base64.encodeBytes(get(rs, "PS_CONTENT").getBytes()) +
						"' creation_date='" + getTime(rs, "CREATION_DATE") +
						"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
						"' created_by='" + get(rs, "CREATED_BY") +
						"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
						"'/>\n"));
			}
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}

	}

	/**
	 * Lists all the algorithms/applications within a dataset in a xml format. This method makes one sql query, execute it, fetch the results and packs and write it in xml format to the output stream. The query that it executes get generated by <code>dbs.DBSSql.listAlgorithms</code> method. A sample XML that is written to the output stream is like <br>
	 * <code> <"algorithm id='3' app_version='MyVersion12' app_family_name='MyFamily' app_executable_name='MyExe' ps_name='DUMMY_ps_nam' ps_hash='DUMMY_HASH' creation_date='2006-12-06 16:12:10.0' last_modification_date='2006-12-06 16:12:10.0' created_by='ANZARDN' last_modified_by='ANZARDN'"/> </code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param path a dataset path in the format of /primary/tier/processed. This path is used to find the existing processed dataset id.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied pattern parameters are invalid or the database connection is unavailable.
	 */
	public void listAlgorithms(Connection conn, Writer out, String path) throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBSSql.listAlgorithms(conn, (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, path, true));
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<processed_dataset_algorithm id='" + get(rs, "ID") + 
						"' app_version='" + get(rs, "APP_VERSION") +
						"' app_family_name='" + get(rs, "APP_FAMILY_NAME") +
						"' app_executable_name='" + get(rs, "APP_EXECUTABLE_NAME") +
						"' ps_name='" + get(rs, "PS_NAME") +
						"' ps_hash='" + get(rs, "PS_HASH") +
						"' ps_version='" + get(rs, "PS_VERSION") +
						"' ps_type='" + get(rs, "PS_TYPE") +
						//"' ps_annotation='" + get(rs, "PS_ANNOTATION") +
						"' ps_annotation='" + Base64.encodeBytes(get(rs, "PS_ANNOTATION").getBytes()) +
						"' ps_content='" + Base64.encodeBytes(get(rs, "PS_CONTENT").getBytes()) +
						"' creation_date='" + getTime(rs, "CREATION_DATE") +
						"' last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") +
						"' created_by='" + get(rs, "CREATED_BY") +
						"' last_modified_by='" + get(rs, "LAST_MODIFIED_BY") +
						"'/>\n"));
			}
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}

	}



	/**
	 * insert a algorithm/application whose parameters are provided in the passed algo <code>java.util.Hashtable</code>. This hashtable block is generated externally and filled in with the algorithm parameters by parsing the xml input provided by the client. This method inserts entry into more than one table associated with AlgorithmConfig table. The the main query that it executes to insert in AlgorithmConfig table, get generated by <code>dbs.DBSSql.insertApplication</code> method.<br> 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it inserts a new version in the AppVersion table by calling a generic private insertName method. <br>
	 * Then it inserts a new family in the AppFamily table by calling a generic private insertName method. <br>
	 * Then it inserts a new executable in the AppExecutable table by calling a generic private insertName method. <br>
	 * Then it inserts a new parameter set in the QueryableParameterSet table by calling a insertParameterSet method. <br>
	 * Finally it inserts a new algorithm in the AlgorithmConfig table.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param algo a <code>java.util.Hashtable</code> that contains all the necessary key value pairs required for inserting a new algorithm. The keys along with its values that it may or may not contain are <br>
	 * <code>app_version, app_family_name, app_executable_name, ps_name, ps_hash, ps_version, ps_type, ps_annotation, ps_content, created_by, creation_date</code>
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable or a duplicate entry is being added.
	 */
	public void insertAlgorithm(Connection conn, Writer out, Hashtable algo, Hashtable dbsUser) throws Exception {
		String version = get(algo, "app_version", true);
		String family = get(algo, "app_family_name", true);
		String exe = get(algo, "app_executable_name", true);
		//String psName = get(algo, "ps_name", true);
		String psHash = get(algo, "ps_hash", false);
		if ( isNull(psHash) ) {
			psHash = "NO_PSET_HASH";
		}
		
		//Get the User ID from USERDN
		String userID = personApi.getUserID(conn, dbsUser);
		String cbUserID = personApi.getUserID(conn, get(algo, "created_by"), dbsUser );
		String creationDate = getTime(algo, "creation_date", false);

		//Insert the application version if it does not exists
		insertName(conn, out, "AppVersion", "Version", version, cbUserID, userID, creationDate);
		
		//Insert the Application Family if it does not exists
		insertName(conn, out, "AppFamily", "FamilyName", family, cbUserID, userID, creationDate);
		
		//Insert the Application Executable if it does not exists
		insertName(conn, out, "AppExecutable", "ExecutableName", exe, cbUserID, userID, creationDate);

		//Insert the ParameterSet if it does not exists
		insertParameterSet(conn, out, algo, cbUserID, userID, creationDate);
			    
		//Insert the Algorithm by fetching the ID of exe, version, family and parameterset
		if(getAlgorithmID(conn, version, family, exe, psHash, false) == null) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertApplication(conn, 
					getID(conn, "AppExecutable", "ExecutableName", exe, true), 
					getID(conn, "AppVersion", "Version", version, true), 
					getID(conn, "AppFamily", "FamilyName", family, true), 
					getID(conn, "QueryableParameterSet", "Hash", psHash, true), 
					cbUserID, userID, creationDate);
				ps.execute();
			} finally { 
				if (ps != null) ps.close();
	        	}
		} else {
			writeWarning(out, "Already Exists", "1020", "Algorithm Configuration  " + version + " " + family +  " " + exe + " " + psHash + " Already Exists");
		}

       }


	/**
	 * Insert a parameter set whose parameters are provided in the passed algo <code>java.util.Hashtable</code>. This hashtable is generated externally and filled in with the lumi section parameters by parsing the xml input provided by the client. This method inserts entry into just one  QueryableParameterSet table. The the main query that it executes to insert in QueryableParameterSet table, get generated by <code>dbs.DBSSql.insertParameterSet</code> method.<br> 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it insert a new parameter set whose sql query is generated by calling <code>dbs.sql.insertParameterSet<code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param algo a <code>java.util.Hastable</code>  that contain all the necessary key value pairs required for inserting a new parameter set. The keys along with its values that it may or may not contain are <br>
	 * <code>ps_name, ps_hash, ps_version, ps_type, ps_annotation, ps_content</code> <br>
	 * @param cbUserID a user id of the person who is inserting this new row into this given database table.
	 * @param userID a user id of the person who is inserting this new row into this given database table. The user id correspond to the Person table id in database. This is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @param creationDate a user provided date that will be inserted along with the row. If this date is not provided, then the system date is used instead.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable.
	 */
	private void insertParameterSet(Connection conn, Writer out,  Hashtable algo, String cbUserID, String userID, String creationDate) throws Exception {
		String psHash = get(algo, "ps_hash", false);

                if ( isNull(psHash) ) {
                        psHash = "NO_PSET_HASH";
                }

		if( getID(conn, "QueryableParameterSet", "Hash", psHash, false) == null ) {
			PreparedStatement ps = null;
			try {
				String content = get(algo, "ps_content");
				String contentBase64 =  "";
				if(!isNull(content)) {
					contentBase64 = new String(Base64.decode(content));
				}

				String annotation = get(algo, "ps_annotation");
				//System.out.println("ps_annotation " + annotation);
				String annotationBase64 =  "";

                                if(!isNull(annotation)) {
                                        byte[] check_encode = Base64.decode(annotation);
                                        if(check_encode == null) {
                                                annotationBase64 = new String(check_encode);
                                        }
                                        else {
                                                annotationBase64 = annotation;
                                        }
                                }
				//System.out.println("annotationBase64 " + annotationBase64);

				ps = DBSSql.insertParameterSet(conn,
						psHash,
						get(algo, "ps_name"), 
						get(algo, "ps_version"), 
						get(algo, "ps_type"), 
						//get(algo, "ps_annotation"), 
						annotationBase64, 
                                                //FIXME We are allowing every thing in content, need to fix it
						contentBase64, 
						cbUserID, userID, creationDate);
				ps.execute();
			} finally {
				if (ps != null) ps.close();
			}
		} else {
			writeWarning(out, "Already Exists", "1020", "Parameter Set " + psHash +  " Already Exists");
		}	

	}

	/**
	 * Gets a algorithm id from the database by using the application version, application family, application executable and parameter set name. This actually generates the sql by calling the <code>dbs.sql.DBSSql.getAlgorithmID</code> method. 
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param ver the name of the application version whose algorithm configuration id needs to be fetched.
	 * @param fam the name of the application family whose algorithm configuration id needs to be fetched.
	 * @param exe the name of the application executable whose algorithm configuration id needs to be fetched.
	 * @param psHash the hash of the parameter set whose algorithm configuration id needs to be fetched.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid or  the database connection is unavailable, or the algorithm is not found.
	 */
	//protected String getAlgorithmID(Connection conn, String ver, String fam, String exe, String psName, boolean excep) throws Exception {
	protected String getAlgorithmID(Connection conn, String ver, String fam, String exe, String psHash, boolean excep) throws Exception {
		checkWord(ver, "app_version");
		checkWord(fam, "app_family_name");
		checkWord(exe, "app_executable_name");
		checkWord(psHash, "ps_hash");
		String id = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps =  DBSSql.getAlgorithmID(conn, ver, fam, exe, psHash);
			rs =  ps.executeQuery();
			if(!rs.next()) {
				if (excep) throw new DBSException("Unavailable data", "1009", "No such algorithm version: " + ver + " family: " + fam + " executable: " + exe + " parameter set: " + psHash);
				else return null;
			}
			id = get(rs, "ID");
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		}

		return  id;
	}

}
