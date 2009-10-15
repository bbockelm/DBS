/**
 $Revision: 1.2 $
 $Id: DBSSrvcUtil.java,v 1.2 2009/09/21 15:04:39 yuyi Exp $
 * Contains general utility static methods
*/

package cms.dbs.commons.utils;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Date;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.io.Writer;
import java.io.File;
import java.net.URL;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;

import cms.dbs.commons.utils.DBSSrvcConstants;
import cms.dbs.commons.exceptions.DBSException;

import java.security.*;
import java.math.*;


public class DBSSrvcUtil {

	public DBSSrvcUtil(){}


        public static long  getEpoch() throws Exception{

                return  (new Date()).getTime() / 1000 ;
        }

	public static boolean isNull(String pattern) {

		if(pattern == "null") {
                        return true;
                }

		if(pattern == null) {
			return true;
		}
		if(pattern.length() < 1 ) {
			return true;
		}
		return false;
	}

	public static void writeLog(String logText) {
		if (DBSSrvcConstants.DEBUG) System.out.println(logText);
	}

	public static void writeErrorLog(String logText) {
		if (DBSSrvcConstants.ERROR) System.err.println(logText);
	}


	public String[] parseDSPath(String path) throws Exception {
		String[] data = path.split("/");
		if(data.length != 4) {
			throw new DBSException("Invalid format", " Expected a path in format /PRIMARY/TIER/PROCESSED given " + path);
		}
		return data;
	}

   	public static String md5(String s) throws Exception{
      		MessageDigest m=MessageDigest.getInstance("MD5");
      		m.update(s.getBytes(),0,s.length());
		return String.valueOf(new BigInteger(1,m.digest()).toString(16));
      		//System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
   	}
}
