/**
 * @author sekhri
 $Revision: 1.4 $"
 $Id: DBManagement.java,v 1.4 2006/11/01 16:59:44 afaq Exp $"

 *
 */


package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class DBManagement{

	public static Connection getConnection(String driver,String url,String userId,String password) throws Exception {
		Class.forName(driver);
		return DriverManager.getConnection(url,userId,password);
	}

	/**
	* Executes an sql query (only select) on the database server with the driver specified in the constructor
	* @param conn A database connection object.
	* @param query An sql query in the sql format to be executed on the database server.
	* @return A ResultSet containing the result of executing the sql query. Null if an exception occurs
	*/
	public static ResultSet executeQuery(Connection conn, String query) throws Exception{
		return conn.createStatement().executeQuery(query);
	}

        /** Executes insert queries */
        public static boolean execute(Connection conn, String query) throws Exception{
                return conn.createStatement().execute(query);
        }

        /** Executes insert queries */
        public static int executeUpdate(Connection conn, String query) throws Exception{
                return conn.createStatement().executeUpdate(query);
        }
}
