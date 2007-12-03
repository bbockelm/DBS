/**
 * @author sekhri
 $Revision: 1.12 $"
 $Id: DBSUtil.java,v 1.12 2007/11/28 17:06:43 sekhri Exp $"
 *
*/

package dbs.util;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Date;
import java.util.Enumeration;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.io.Writer;
import dbs.DBSConstants;

/**
* A util class that has a few utility menthods. All of the methods are static methods and do not need instance of this class to be called.
* @author sekhri
*/
public class DBSUtil {
	
	/**
	 * a static method that takes a <code>java.util.Hashtable</code> and a <code>java.lang.String</code> as parameters and attemps to fetch the given key value from the given table. If key or the table given are null then it does not return null, rather it retuns an empty string. Also if the key is not found in the table, it returns an empty string
	 * @param table a <code>java.util.Hashtable</code> that contain the dictionary of key value pairs.
	 * @param key a <code>java.lang.String</code> that contain key name to be searched in the table.
	 * Returns the value of the key provided in the table.
	 * @return 
	 * an empty string if the key could not be found in the table<br>
	 * an string value of the key in the table.
	 *
	 */
	public static String get(Hashtable table, String key) {
		if(key == null ||  table == null) {
			return "";
		}
		if(!table.containsKey(key)) {
			return "";
		}
		Object tmp = table.get(key);
		if(tmp == null) {
			return "";
		}
		return (String)tmp;
	}

	/**
	 * a static method that takes a <code>java.util.Hashtable</code> and a <code>java.lang.String</code> as parameters and attemps to fetch the given key value in <code>java.util.Vector</code> format from the given table. If key or the table given are null then it does not return null, rather it retuns an empty vector. Also if the key is not found in the table, it returns an empty vector
	 * @param table a <code>java.util.Hashtable</code> that contain the dictionary of key value pairs.
	 * @param key a <code>java.lang.String</code> that contain key name to be searched in the table.
	 * Returns the value in <code>java.util.Vector</code> format of the key provided in the table.
	 * @return 
	 * an empty vector if the key could not be found in the table<br>
	 * an string value of the key in the table.
	 *
	 */
	public static Vector getVector(Hashtable table, String key) {
		Vector v = new Vector();
		if(key == null ||  table == null) {
			return v;
		}
		if(!table.containsKey(key)) {
			return v;
		}
		Object tmp = table.get(key);
		if(tmp == null) {
			return v;
		}
		return (Vector)tmp;
	}

	public static Hashtable getTable(Hashtable table, String key) {
		Hashtable t = new Hashtable();
		if(key == null ||  table == null) {
			return t;
		}
		if(!table.containsKey(key)) {
			return t;
		}
		Object tmp = table.get(key);
		if(tmp == null) {
			return t;
		}
		return (Hashtable)tmp;
	}

	public static boolean isIn(String param, Enumeration e) {
                while (e.hasMoreElements()) {
                        if( param.equals((String)e.nextElement()) ) {
                                return true;
                        }
                }
                return false;
        }


	/**
	 * a static method that check for if the given parameter is null or empty <code>java.lang.String</code> 
	 * @param pattern a <code>java.lang.String</code> that is check for being null or empty.
	 * Returns true or false.
	 * @return 
	 * true if the given pattern is found to be null or empty<br>
	 * false otherwise.
	 */
	public static boolean isNull(String pattern) {
		if(pattern == null) {
			return true;
		}
		if(pattern.length() < 1 ) {
			return true;
		}
		return false;
	}

	/**
	 * a static method that write logText to stdout if the <code>DBSConstants.DEBUG</code> is set to be true. If <code>DBSConstants.DEBUG</code> is false then it does not write logText to stdout. This method is needed when the server is run in debug mode. In production mode  <code>DBSConstants.DEBUG</code> will be set to true and logText will not get printed on stdout.
	 * @param logText a <code>java.lang.String</code> that is written on stdout depending upon the <code>DBSConstants.DEBUG</code> flag.
	 */
	public static void writeLog(String logText) {
		if (DBSConstants.DEBUG) System.out.println(logText);
	}

	/**
	 * a static method that write logText to stderr if the <code>DBSConstants.ERROR</code> is set to be true. If <code>DBSConstants.ERROR</code> is false then it does not write logText to stderr. This method is needed when the server is run in debug mode. In production mode  <code>DBSConstants.ERROR</code> will be set to true and logText will not get printed on stdout.
	 * @param logText a <code>java.lang.String</code> that is written on stdout depending upon the <code>DBSConstants.ERROR</code> flag.
	 */
	public static void writeErrorLog(String logText) {
		if (DBSConstants.ERROR) System.err.println(logText);
	}

	/**
	 * a static method that write logText to stdout if the <code>DBSConstants.DEBUGCACHE</code> is set to be true. If <code>DBSConstants.DEBUGCACHE</code> is false then it does not write logText to stdout. This method is needed when the server is run in debug mode. In production mode  <code>DBSConstants.DEBUGCACHE</code> will be set to true and logText will not get printed on stdout.
	 * @param logText a <code>java.lang.String</code> that is written on stdout depending upon the <code>DBSConstants.DEBUGCACHE</code> flag.
	 */
	public static void writeCacheLog(String logText) {
		if (DBSConstants.DEBUGCACHE) System.out.println(logText);
	}

	public static int getNumberOfRows(ResultSet rs) throws SQLException {
		/*int count = 0;
		while(rs.next()) {
			++count;
		}
		return count;*/
		rs.last();
		int numberOfRows = rs.getRow();
		rs.beforeFirst();
		//rs.first();
		return numberOfRows;
	}

	public static String get(ResultSet rs, String key) throws Exception {
		String value = rs.getString(key);
		if(isNull(value)) return "";
		return value;
	}

}
