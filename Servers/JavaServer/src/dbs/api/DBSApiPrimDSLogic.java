/**
 $Revision: 1.25 $"
 $Id: DBSApiPrimDSLogic.java,v 1.25 2008/07/10 16:09:36 sekhri Exp $"
 *
 */

package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.util.Hashtable;
import dbs.sql.DBSSql;
import dbs.util.DBSUtil;
import dbs.DBSException;
import java.sql.SQLException;

/**
* A class that has the core business logic of all the Primary dataset APIs.  The signature for the API is internal to DBS and is not exposed to the clients. There is another class <code>dbs.api.DBSApi</code> that has an interface for the clients. All these low level APIs are invoked from <code>dbs.api.DBSApi</code>. This class inherits from DBSApiLogic class.
* @author sekhri
*/
public class DBSApiPrimDSLogic extends DBSApiLogic {
		
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs.
	*/

	DBSApiPersonLogic personApi = null;
	DBSApiData data = null;
	public DBSApiPrimDSLogic(DBSApiData data) {
		super(data);
		this.data = data;
		personApi = new DBSApiPersonLogic(data);
	}

	
	/**
	 * Lists all the primary datasets from the database in a xml format. This method makes one sql query, execute it, fetch the results and packs and write it in xml format to the output stream. The query that it executes get generated by <code>dbs.DBSSql.listPrimaryDatasets</code> method. A sample XML that is written to the output stream is like <br>
	 * <code><"primary_dataset id='278' annotation='aaaaf9' primary_name='This_is_a_test_primary' start_date='NOV' end_date='DEC' creation_date='2006-12-06 10:35:22.0' last_modification_date='2006-12-06 10:35:22.0' trigger_path_description='' mc_channel_description='' mc_production='' mc_decay_chain='' other_description='' type='VALID' created_by='ANZARDN' last_modified_by='ANZARDN'"/></code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param pattern a parameter passed in from the client that can contain wild card characters. This pattern is used to restrict the SQL query results by sustitution it in the WHERE clause.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied pattern is invalid or the database connection is unavailable.
	 */
	public void listPrimaryDatasets(Connection conn, Writer out, String pattern) throws Exception {
                 
		PreparedStatement ps = null;
		ResultSet rs =  null;
		try {
			ps = DBSSql.listPrimaryDatasets(conn, getPattern(pattern, "primary_dataset_name_pattern"));
			pushQuery(ps);
			rs =  ps.executeQuery();
			while(rs.next()) {
				out.write(((String) "<primary_dataset id='" + get(rs, "ID") +
						"' annotation='" + get(rs, "ANNOTATION") +
						"' primary_name='" + get(rs, "PRIMARY_NAME") +
						"' start_date='" + get(rs, "START_DATE") +
						"' end_date='" + get(rs, "END_DATE") +
						"' creation_date='" + getTime(rs, "CREATION_DATE") +
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
		} finally { 
			if (rs != null) rs.close();
			if (ps != null) ps.close(); 
		}
	}

	
	/**
	 * Insert a primary dataset whose parameters are provided in the passed dataset <code>java.util.Hashtable</code>. This hashtable dataset is generated externally and filled in with the primary dataset parameters by parsing the xml input provided by the client. This method inserts entriy into more than one table associated with PrimaryDataset table. The the main query that it executes to insert in PrimaryDataset table, get generated by <code>dbs.DBSSql.insertPrimaryDataset</code> method.<br> 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. Then it inserts the primary dataset type in PrimaryDSType table if does not exist already. It works on Description tables (needed to be done in the code) and finally inserts a new primary dataset in PrimaryDataset table.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param dataset a <code>java.util.Hashtable</code> that contains all the necessary key value pairs required for inserting a new primary dataset. The keys along with its values that it may or may not contain are <br>
	 * <code>type, annotation, primary_name, start_date, end_date, created_by, creation_date</code>
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable or a duplicate entry is being added.
	 */
	public void insertPrimaryDataset(Connection conn, Writer out, Hashtable dataset, Hashtable dbsUser) throws Exception {
		String warMsg ;
		//Get the User ID from USERDN
		
		String lmbUserID = personApi.getUserID(conn, dbsUser); 
		String cbUserID = personApi.getUserID(conn, get(dataset, "created_by"), dbsUser );
		String creationDate = getTime(dataset, "creation_date", false);

		String name = get(dataset, "primary_name", true);
		String type = get(dataset, "type", true);
                String annotation = getStr(dataset, "annotation", false);
                String start_date = getStr(dataset, "start_date", false);
                String end_date = getStr(dataset, "end_date", false);

                if (isNull(annotation)) annotation = "NO ANNOTATION PROVIDED";
                if (isNull(start_date)) start_date = "NO START_DATE PROVIDED";
                if (isNull(end_date)) start_date = "NO_END_DATE PROVIDED";
                //if (isNull(type)) type = "test";
		//System.out.println("creation_date " + creationDate);		
		//Insert a Dataset Type if it does not exists
		//insertName(conn, out, "PrimaryDSType", "Type", type , cbUserID, lmbUserID, creationDate);
		
		//Insert a Dataset Trigger Desc if it does not exists
		//FIXME some problem with this table while insertng rows
		//insertName(conn, out, "TriggerPathDescription", "TriggerPathDescription", tpDesc , lmbUserID);
		
		//Insert a Dataset Other Desc if it does not exists
		//insertName(conn, out, "OtherDescription", "Description", oDesc , lmbUserID);

		//TODO Insert MCDesc . Change in the schema is required.
		//FIXME The schemna should be changed so that PrimaryDatasetDescription should have PrimarYdataset ID as forign key. 
		//Not the other way around
		//String mcDesc = get(dataset, "mc_channel_description", false);
		//String mcPro = get(dataset, "mc_production", false);
		//String mcDecay = get(dataset, "mc_decay_chain", false);
		//String oDesc = get(dataset, "other_description", false);
		//String tpDesc = get(dataset, "trigger_path_description", false);

		//TODO Insert PrimaryDatasetDescription table also
		String primDSID;
		//if( (primDSID = getID(conn, "PrimaryDataset", "Name", name, false)) == null ) {
			PreparedStatement ps = null;
			try {
				ps = DBSSql.insertPrimaryDataset(conn, 
					annotation,
					name,
					"",//FIXME Should not be in the schema
					start_date,
					end_date,
					getID(conn, "PrimaryDSType", "Type", type, true), 
					cbUserID, lmbUserID, creationDate);
				pushQuery(ps);
				ps.execute();
			} catch (SQLException ex) {
				//System.out.println("Exception: "+ex.getMessage());
                                String exmsg = ex.getMessage();
                                        if ( exmsg.startsWith("Duplicate entry") ||
                                                exmsg.startsWith("ORA-00001: unique constraint") ) {
                                                writeWarning(out, "Already Exists", "1020", "PrimaryDS " + name + " Already Exists");
                                                if (ps != null)ps.close();
                                        } else {
                                                throw new SQLException("'"+ex.getMessage()+"' insertPrimaryDataset for : "+name+
                                                        " SQL failed is"+ps);
                                        }

			} finally { 
				if (ps != null) ps.close();
			}
		//} else {
		//	writeWarning(out, "Already Exists", "1020", "Primary Dataset " + name + " Already Exists");
		//}

	}


	/*TODO more information needed and change in the schema required,
	 * private void insertMCDesc(Connection conn, Hashtable table, String lmbUserID) throws Exception {
		String mcDesc = get(table, "mc_channel_description", true);
		String mcProd = get(table, "mc_production", false);
		String mcChain = get(table, "mc_decay_chain", false);

		//Insert a new Lumi Section by feting the run ID 
		if( getMCDescID(conn, mcDesc, mcProd, mcChain) == null ) {
			DBManagement.execute(conn, DBSSql.insertMCDesc(mcDesc, mcProd, mcChain,	lmbUserID));
		}
	}*/

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

	/**
	 * Updates the type of a primary dataset. 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it fetches the primary dataset ID and call a generic methods updateName that fetches the type id and updates it in PrimaryDataset table.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param primName the name ofthe primarydataset.
	 * @param value a value of the type field to be set in this primary dataset
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid, the database connection is unavailable or a procsssed dataset is not found.
	 */
	public void updatePrimDSType(Connection conn, Writer out, String primName, String value, Hashtable dbsUser) throws Exception {
		updateName(conn, out, "PrimaryDataset",  getID(conn, "PrimaryDataset", "Name", primName, true),
				                        "Type", "PrimaryDSType", "Type", value, personApi.getUserID(conn, dbsUser));
	}

	
}
