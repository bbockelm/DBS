package dbs.search.qb;
import java.util.Hashtable;

public class Util {
	public static boolean isSame(String key1, String key2) {
		if((key1 == null) || (key2 == null) ) return false;
		if(key1.toLowerCase().equals(key2.toLowerCase())) return true;
		return false;
	}
}

