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
import java.io.RandomAccessFile;
import java.util.Hashtable;
import java.util.Enumeration;


public class DBSCLIFile extends DBSCLI{

	private static String usage = "USAGE : java dbs.test.DBSCLIFile fileName";
							
	public DBSCLIFile() {}
	
	public void call(String fileName) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(fileName, "rw");
			long len = raf.length();
			byte[] b = new byte[(int)len];
			raf.read(b, 0 , (int)len);
			String fileStr = new String(b);
			//System.out.println("fileStr -- >" + fileStr);
			String[] tmpArgs = fileStr.split("\"");
			int lenOfTmpArgs = tmpArgs.length;
			int count = 0 ;
			for (int i = 0 ; i != lenOfTmpArgs; ++i) {
				if (!tmpArgs[i].trim().equals("")) ++count;
			}
			String[] args = new String[count];
			count = 0;
			for (int i = 0 ; i != lenOfTmpArgs; ++i) {
				if (!tmpArgs[i].trim().equals("")) {
					args[count] = tmpArgs[i];
					++count;
				}
			}
			call(args);

		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if(raf != null) raf.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		DBSCLIFile cli = new DBSCLIFile();
		int len = args.length;
		if(len != 1) {
			try {
				DBSApi api = new DBSApi();
				api.writeException(new PrintWriter(System.out), "Null parameters", "401", "No command line parameters specified\n" + usage);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		cli.call(args[0]);
	}
}

