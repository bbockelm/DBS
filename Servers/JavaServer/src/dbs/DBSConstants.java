/**
 * 
 $Revision: 1.33 $"
 $Id: DBSConstants.java,v 1.33 2007/06/14 17:08:47 afaq Exp $"
 *
*/
package dbs;

/**
* A class that stores static DBS constants will be used througout by the DBS API code
* @author sekhri
*/
public class DBSConstants {
        //Specify the Global debug flag, if set to True DEBUG Messages wil be displayed. 
	public static boolean DEBUG = false;
	//public static boolean DEBUG = true;
	public static String XML_HEADER =  "<?xml version='1.0' standalone='yes'?>\n<!-- DBS Version 1 -->\n<dbs>\n";
	public static String XML_FOOTER = "</dbs>\n";
	public static String XML_EXCEPTION_HEADER = "<exception>\n";
	public static String XML_EXCEPTION_FOOTER = "</exception>\n";
	public static String XML_WARNNING_HEADER = "<warnning>\n";
	public static String XML_WARNNING_FOOTER = "</warnning>\n";
	public static String XML_SUCCESS = "<SUCCESS/>\n";
        public static String DBSTag = "$Name:  $";

}
