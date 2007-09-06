/**
 * @author sekhri
 $Revision: 1.3 $"
 $Id: DBSCLI.java,v 1.3 2006/12/13 16:58:01 sekhri Exp $"
 *
 */

package dbs.test;
import dbs.api.DBSApi;
import dbs.util.DBSUtil;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Enumeration;


public class DBSCLI {

	private DBSApi api;
	private PrintWriter out;
	Hashtable user;
	private static String usage = "USAGE : java dbs.test.DBSCLI <key1=value1> <key2=value2> ... <keyN=valueN>";
							
	public DBSCLI() {
		api = new DBSApi();
		//FIXME get the user information from the proxy
		user = new Hashtable();
		user.put("user_dn", "VIJAY_SEKHRI_DN");
		user.put("user_name", "VIJAY_SEKHRI");
		user.put("contact_info", "VIJAY_SEKHRI_HOME");
		out = new PrintWriter(System.out);
	}
	
	public void call(String[] args) {
		try {
			try { 
				Hashtable table = new Hashtable();
				int len = args.length;
				if(len < 1) {
					api.writeException(out, "Null parameters", "401", "No command line parameters specified\n" + usage);
					return;
				}
				
				for (int i = 0 ; i != len; ++i) {
					//out.write("arg "+ i + " => " + args[i] + "\n");
					String[] pairs = args[i].split("=");
					if (pairs.length < 2) {
						api.writeException(out, "Invalid command line parameter", "401", "The format provided in the command line is invalid\n" + usage);
						return;
					}
					table.put(pairs[0].trim(), 
							args[i].substring(args[i].indexOf("=") + 1, 
									args[i].length()).trim());
					
				}
				//printTable(table);
				api.call(out, table, user);
			} catch(Exception ex) {
				api.writeException(out, "Execution error", "401", ex.getMessage());
			} finally {
				out.flush();
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void printTable(Hashtable table) {
		Enumeration e = table.keys();
		while (e.hasMoreElements()) {
			String key = (String)e.nextElement();
			System.out.println("key " + key + " value " + DBSUtil.get(table, key));
		}

	}
	public static void main(String[] args) {
		DBSCLI cli = new DBSCLI();
		cli.call(args);
	}
}

