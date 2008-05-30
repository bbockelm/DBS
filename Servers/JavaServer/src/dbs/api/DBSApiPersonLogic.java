/**
 $Revision: 1.11 $"
 $Id: DBSApiPersonLogic.java,v 1.11 2007/11/16 21:29:36 sekhri Exp $"
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

/**
* A class that has the core business logic of all the Person APIs.  The signature for the API is internal to DBS and is not exposed to the clients. There is another class <code>dbs.api.DBSApi</code> that has an interface for the clients. All these low level APIs are invoked from <code>dbs.api.DBSApi</code>. This class inherits from DBSApiLogic class.
* @author sekhri
*/
public class DBSApiPersonLogic extends DBSApiLogic {
		
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs. The constructor does nothing.
	*/
	
	//DBSApiData data = null;
	public DBSApiPersonLogic(DBSApiData data) {
		super(data);
		//this.data = data;
	}


	/**
	 * Insert a new person in the Person table. This method inserts entry into just one Person table and its query is generated by <code>dbs.DBSSql.insertPerson</code> method.<br> 
	 * First it fetches the userID by using the parameters specified in the dbsUser <code>java.util.Hashtable</code> and if the user does not exists then it insert the new user in the Person table. All this user operation is done by a private method getUserID. <br>
	 * Then it insert a new user if it does not already exist.
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param table  a <code>java.util.Hastable</code> that contain all the necessary key value pairs required for inserting a new person. The keys along with its values that it may or may not contain are <br>.
	 * <code>user_name, user_dn, contact_info, created_by, creation_date </code> <br>
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid or  the database connection is unavailable.
	 */
	public void insertPerson(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {
		insertPerson(conn, out, 
				get(table, "user_name", false),
				get(table, "user_dn", true),
				get(table, "contact_info", false),
				getUserID(conn, get(table, "created_by"), dbsUser ),
				getUserID(conn, dbsUser),
				getTime(table, "creation_date", false)
				);
	}
	
	protected void insertPerson(Connection conn, Writer out, String userName, String userDN, String contactInfo, String cbUserID, String lmbUserID, String creationDate) throws Exception {
		//if (isNull(lmbUserID)) lmbUserID = "0";//0 is user not created by anyone
		//if( getID(conn, "Person", "DistinguishedName", userDN , false) == null ) {
		if( getIDNoCheck(conn, "Person", "DistinguishedName", userDN , false) == null ) {
			PreparedStatement ps = null;
			try {
				//FIXME it is not important to store whoi created this person 
				ps = DBSSql.insertPerson(conn, userName, userDN, contactInfo, cbUserID, lmbUserID, creationDate);
				pushQuery(ps);
				ps.execute();
			} finally {
				if (ps != null) ps.close();
			}
		} else {
			writeWarning(out, "Already Exists", "1020", "Person " + userDN + " Already Exists");
		}	

	}


	/**
	 * Gets a id of a user from the Person table in the database. 
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.  The keys along with its values that it may or may not contain are  <code>user_dn</code>
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters are invalid or  the database connection is unavailable, or the table id is not found.
	 */
	public String getUserID(Connection conn, Hashtable dbsUser) throws Exception {
		String id = "";
		String userDN = get(dbsUser, "user_dn");

		if(!isNull( id = get(this.data.localUser, userDN) )) {
			return id;
		}
		//Looking in Global Cache 
		if(!isNull( id = this.data.getGlobalCache().getUserID(conn, userDN)))  {
			this.data.localUser.put(userDN, id);
			return id;
		}

		if ( (id = getIDNoCheck(conn, "Person", "DistinguishedName", userDN , false)) == null) {
			//FIXME instead of passing null for out stream writer , pass the actual stream
			insertPerson(conn, null,  
					get(dbsUser, "user_name", false), 
					userDN, 
					get(dbsUser, "contact_info", false),
					"",
					"",
					""); //FIXME Get userName and contactInfo also and the lmbUserID shoudl be decicde?
			id = getIDNoCheck(conn, "Person", "DistinguishedName", userDN , true);
		}
		this.data.localUser.put(userDN, id);
		return id;
	}


	public String getUserID(Connection conn, String userDN, Hashtable dbsUser) throws Exception {
		if(isNull(userDN)) userDN = get(dbsUser, "user_dn");
		Hashtable tmpDBSUser = new Hashtable();
		tmpDBSUser.put("user_dn", userDN);//FIXME conatct infor and other things needs to go in this table too
		return getUserID(conn, tmpDBSUser);
		
	}

	
}
