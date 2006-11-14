/**
 * 
 */
package dbs.util;
import java.util.Hashtable;
import java.util.Vector;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sekhri
 *
 */
public class DBSUtil {
	/**
	 * 
	 */
	public DBSUtil() {
	}
	
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

	public static Vector getVector(Vector v, int index, String key) {
		if (index >= v.size()) {
			return null;
		}
		Object o = v.get(index);
		if(o == null) {
			return null;
		}
		Hashtable table = (Hashtable)o;
		if(!table.containsKey(key)) {
			return null;
		}
		o = table.get(key);
		if(o == null) {
			return null;
		}
		return (Vector)o;

	}


	public static String getDate() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) ;
	}
	
	public static boolean isNull(String pattern) {
		if(pattern == null) {
			return true;
		}
		if(pattern.length() < 1 ) {
			return true;
		}
		return false;
	}

}
