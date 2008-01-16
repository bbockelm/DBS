package ms.cron;

import java.util.Hashtable;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import ms.util.MSUtil;


public class Util {
	public void Util(){};


	private void addVersion(Hashtable table) {
		table.put("apiversion", "DBS_1_0_7");
	}

	public String makeUrl(Hashtable table) throws Exception {
		addVersion(table);
		String url = "";
		for (Enumeration e = table.keys() ; e.hasMoreElements() ;) {
			String key = (String)e.nextElement();
			url += key + "=" + URLEncoder.encode(MSUtil.get(table, key)) + "&";
		}
		return url;
	}

	public boolean isException(String message) {
		if(message.indexOf("exception") == -1) return false;
		else return true;
	}

	public String readUrl(String url) throws Exception {
		HttpURLConnection con = null;
		BufferedReader in = null;
		String toReturn = "";
		try {
	      		con =  ( HttpURLConnection ) (new URL(url)).openConnection() ; 
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			toReturn  = readUrl(in);
		} finally {
			if(in != null) in.close();
			if(con != null)  con.disconnect();
		}
		return toReturn;
	}

	public String postUrl(String url, String data) throws Exception {
		HttpURLConnection con = null;
		BufferedReader in = null;
		DataOutputStream out = null;
		String toReturn = "";
		try {
	      		con =  (HttpURLConnection) (new URL(url)).openConnection() ; 
			con.setDoInput(true);
			con.setDoOutput(true);
			out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(data);
			out.flush();
			
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			toReturn  = readUrl(in);
			/*String inputLine;
			while ((inputLine = in.readLine()) != null){
				toReturn += inputLine;	
				System.out.println(inputLine);
			}*/
		} finally {
			if(in != null) in.close();
			if(out != null) out.close();
			if(con != null)  con.disconnect();
		}
		return toReturn;
	}

	private String readUrl(BufferedReader in) throws Exception {
		String toReturn = "";
		String inputLine;
		while ((inputLine = in.readLine()) != null){
			toReturn += inputLine;	
			System.out.println(inputLine);
		}
		return toReturn;
	}
}
