/**
 * @author sekhri
 $Revision: 1.7 $"
 $Id: DBManagement.java,v 1.7 2006/12/01 18:47:42 sekhri Exp $"

 *
 */


package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

import db.PreparedStatementWrapper;

public class DBManagement{
	private static DBManagement instance = null;
	private static Boolean mutex = new Boolean(true);
	private DataSource ds = null;
	
	public static DBManagement getDBConnManInstance() throws Exception {
		if( instance != null) return instance;
		synchronized(mutex) {
			if( instance != null ) return instance;
			DBManagement dbm = new DBManagement();
			instance = dbm;
		}
		return instance;
	}
	
	private DBManagement() throws Exception {
		//get DNS name from configuration file that ANZAR is working on
		try {
			if ( (ds = (DataSource)(((Context)((new InitialContext()).lookup("java:/comp/env"))).lookup("jdbc/dbs"))) == null ) {
				throw new SQLException("Datasource cound not be initialized. Connection pooling failed.");
			}
		} catch(javax.naming.NoInitialContextException e) {
			System.out.println("This must be a standalone client");
		}
	}

	public Connection getConnection() throws Exception {
		if(ds == null) return null;
		return ds.getConnection();
	}

	public static Connection getConnection(String driver,String url,String userId,String password) throws Exception {
		Class.forName(driver);
		return DriverManager.getConnection(url,userId,password);
	}
	public static PreparedStatement getStatement(Connection conn, String query) throws SQLException {
		return (new PreparedStatementWrapper(conn.prepareStatement(query), conn, query));
	}
	public static void close(PreparedStatement ps, ResultSet rs) throws SQLException {
		if (rs != null) rs.close();
		close(ps);
	}
	public static void close(PreparedStatement ps) throws SQLException {
		if (ps != null) ps.close();
	}
	
	/**
	* Executes an sql query (only select) on the database server with the driver specified in the constructor
	* @param conn A database connection object.
	* @param query An sql query in the sql format to be executed on the database server.
	* @return A ResultSet containing the result of executing the sql query. Null if an exception occurs
	*/
	/*public static ResultSet executeQuery(Connection conn, String query) throws Exception {
		return conn.createStatement().executeQuery(query);
	}*/


        /** Executes insert queries */
	/*public static boolean execute(Connection conn, String query) throws Exception {
		return conn.createStatement().execute(query);
	}*/

        /** Executes insert queries */
	/*public static int executeUpdate(Connection conn, String query) throws Exception {
		return conn.createStatement().executeUpdate(query);
	}*/
	
}
