/**
 * @author sekhri
 *
 */
package dbs;
public class DBSConstants {
	/**
	 * 
	 */
		
	public static String XML_HEADER =  "<?xml version='1.0' standalone='yes'?>\n<!-- DBS Version 1 -->\n<dbs>\n";
	public static String XML_FOOTER = "</dbs>\n";
	public static String XML_EXCEPTION_HEADER = "<exception>\n";
	public static String XML_EXCEPTION_FOOTER = "</exception>\n";
	public static String XML_WARNNING_HEADER = "<warnning>\n";
	public static String XML_WARNNING_FOOTER = "</warnning>\n";
	public static String XML_SUCCESS = "<SUCCESS/>\n";


	public static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static String URL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(PORT=10520)(HOST=oradev10.cern.ch))(CONNECT_DATA=(SID=D10)))";
	public static String USERID = "cms_dbs_afaq";
	public static String PASSWORD = "MeraMaslaHaina?";

	/*
	public static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static String URL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(PORT=1521)(HOST=uscmsdb03.fnal.gov))(CONNECT_DATA=(SID=cmscald)))";
	public static String USERID = "cms_reader";
	public static String PASSWORD = "reader";

	public static String DRIVER = "org.gjt.mm.mysql.Driver";
	public static String URL = "jdbc:mysql://localhost/dbs_new_era_v04";
	public static String USERID = "anzar";
	public static String PASSWORD = "prodagentpass";

	
	public static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static String URL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP) (HOST=cmsr1-v.cern.ch) (PORT=10121))(ADDRESS=(PROTOCOL=TCP)(HOST=cmsr2-v.cern.ch)(PORT=10121))(ADDRESS=(PROTOCOL=TCP)(HOST=cmsr3-v.cern.ch) (PORT=10121))(ADDRESS=(PROTOCOL=TCP)(HOST=cmsr4-v.cern.ch)(PORT=10121))(LOAD_BALANCE=yes)(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=cms_dbs_mcprod_global)(FAILOVER_MODE=(TYPE=SELECT)(METHOD=BASIC)(RETRIES=200)(DELAY=15))))";
	public static String USERID = "cms_dbs_mcprod_local_writer";
	public static String PASSWORD = "ic_2onograph";
	*/

}
