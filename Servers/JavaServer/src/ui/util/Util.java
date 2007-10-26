package ui.util;

import org.richfaces.component.html.HtmlRichMessage;
import java.util.Hashtable;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import dbs.api.DBSApi;
import dbs.util.DBSUtil;


public class Util {
	public void Util(){};
	public static boolean isNull(String pattern) {
		if(pattern == null) {
			return true;
		}
		if(pattern.length() < 1 ) {
			return true;
		}
		return false;
	}

	public void call(Hashtable table, HtmlRichMessage generalInputMessage) throws Exception {
		//generalInputMessage.setPassedLabel("");
		//generalInputMessage.setRendered(false);
		String message = getCall(table);
		setMessage(message, generalInputMessage);
		/*int startIndex = message.indexOf("<dbs>") + 5;
		int endIndex = message.indexOf("</dbs>");
		message = message.substring(startIndex, endIndex);
		if (isException(message)) generalInputMessage.setStyle("color:rgb(255,0,0);");
		else generalInputMessage.setStyle("color:rgb(128,128,128);");
		generalInputMessage.setPassedLabel(message);*/
		
	}
	
	public void setExceptionMessage(String message, HtmlRichMessage generalInputMessage) {
		try {
			int startIndex = message.indexOf("<exception>") + 11;
			if (startIndex < 0) startIndex = 0;
			int endIndex = message.length();
			message = message.substring(startIndex, endIndex);
			generalInputMessage.setStyle("color:rgb(255,0,0);");
			generalInputMessage.setPassedLabel(message);
		} finally {
			 generalInputMessage.setRendered(true);
		}
	}

	public void setMessage(String message, HtmlRichMessage generalInputMessage) throws Exception {
		int startIndex = message.indexOf("<dbs>") + 5;
		int endIndex = message.indexOf("</dbs>");
		message = message.substring(startIndex, endIndex);
		if (isException(message)) generalInputMessage.setStyle("color:rgb(255,0,0);");
		else generalInputMessage.setStyle("color:rgb(128,128,128);");
		generalInputMessage.setPassedLabel(message);
	}

	public void setText(String message, HtmlRichMessage generalInputMessage) {
		try {
			generalInputMessage.setStyle("color:rgb(128,128,128);");
			generalInputMessage.setPassedLabel(message);
		} finally {
			 generalInputMessage.setRendered(true);
		}

	}

	public String getCall(Hashtable table) throws Exception {
		DBSApi api = new DBSApi();
		//FIXME Fetch the user from Servlet or http header
		Hashtable user = new Hashtable();
		user.put("user_dn", "VIJAY_SEKHRI_DN");
		user.put("user_name", "VIJAY_SEKHRI");
		user.put("contact_info", "VIJAY_SEKHRI_HOME");
		StringWriter sout = new StringWriter();
		
		//table.put("apiversion", "DBS_1_0_7");
		addVersion(table);
		api.call(sout, table, user);
		return sout.toString();
	}
	private void addVersion(Hashtable table) {
		table.put("apiversion", "DBS_1_0_7");
	}

	public String makeUrl(Hashtable table) throws Exception {
		addVersion(table);
		String url = "";
		for (Enumeration e = table.keys() ; e.hasMoreElements() ;) {
			String key = (String)e.nextElement();
			url += key + "=" + URLEncoder.encode(DBSUtil.get(table, key)) + "&";
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
			//URL urlObj = new URL("http://cmsdbsprod.cern.ch/cms_dbs_prod_global/servlet/DBSServlet?api=listPrimaryDatasets&apiversion=DBS_1_0_7&pattern=tt*");
	      		con =  ( HttpURLConnection ) (new URL(url)).openConnection() ; 
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			toReturn  = readUrl(in);
			/*String inputLine;
			while ((inputLine = in.readLine()) != null){
				toReturn += inputLine;	
				System.out.println(inputLine);
			}*/
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
