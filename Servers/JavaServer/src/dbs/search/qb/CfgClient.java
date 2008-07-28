package dbs.search.qb;

import java.util.Hashtable;
import java.util.Vector;
import java.util.List;
import java.util.StringTokenizer;

import xml.DBSXMLParser;
import xml.Element;
import dbs.util.DBSConfig;
import fnal.gov.client.util.HttpUtil;
import fnal.gov.client.DbsWebApi;

public class CfgClient {
	private HttpUtil hu;
	private String url;
	private DbsWebApi dwApi;
	
	public CfgClient() throws Exception{
		DBSConfig dbsConfig = DBSConfig.getInstance();
		hu = new HttpUtil();
		this.url = dbsConfig.getCfgServiceURL();
		dwApi = new DbsWebApi(url);
		//dbsConfig = DBSConfig.getInstance();

	}
	/*public List<String> parse(String inputXml, String tag, String key) throws Exception {
		if(u.isException(inputXml)) throw new Exception(inputXml);
		Vector toReturn = new Vector();
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml);
		Vector allElement = dbsParser.getElements();
		for (int i = 0; i != allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			if (e.name.equals(tag) ) {
				//System.out.println("parsing .......");
				 String name = u.get(e.attributes, key);
				 toReturn.add(name);
			}
		}
		return toReturn;
	}*/
	public List<String> getPsetHash(String psetStr) throws Exception {
		String pName = "pname";
		String pType = "ptype";
		String op = "op";
		String val = "val";
		StringTokenizer st = new StringTokenizer(psetStr, "&");
		int numOfTokens = st.countTokens();
		String tmpUrl = "";
		for(int i = 0 ; i!= numOfTokens ; ++i) {
			String token = st.nextToken().trim();
			//System.out.println("token " + token);
			String dop = ">";
			String[] data = token.split(dop);
			//System.out.println("len " + data.length);
			if(data.length != 2) {dop = "<"; data = token.split(dop);}
			if(data.length != 2) {dop = "="; data = token.split(dop);}
			if(data.length != 2) {dop = ">="; data = token.split(dop);}
			if(data.length != 2) {dop = "<="; data = token.split(dop);}
			if(data.length != 2) {dop = "like"; data = token.split(dop);}
			if(data.length != 2) {dop = "likeLeft"; data = token.split(dop);}
			if(data.length != 2) {dop = "likeRight"; data = token.split(dop);}
			
			if(data.length != 2) throw new Exception("Invalid Input. Example input should be like 'associatorL25PixelTauIsolated.coneSize>0");
			String iStr = String.valueOf(i);
			if(i != 0 ) tmpUrl += "&";
			tmpUrl += pName + iStr + "=" + data[0].trim();
			tmpUrl += "&" + op + iStr + "=" + dop;
			tmpUrl += "&" + val + iStr + "=" + data[1].trim();
			tmpUrl += "&" + pType + iStr + "=" + getPType(dop);
		}
		tmpUrl += "&num=" + String.valueOf(numOfTokens); 
		System.out.println("tmpUrl " + tmpUrl);
		//String param = "pname1=associatorL25SingleTau.coneSize&pname0=associatorL25PixelTauIsolated.coneSize&op2=like&pname2=associatorL25SingleTau.jets&ptype2=2&ptype0=1&ptype1=1&num=3&val2=a&op0=>&val1=0&val0=0&op1=>";
		String instanceUrl = this.url + "?" + tmpUrl;
		String xml = hu.readUrl(instanceUrl);
		//System.out.println(xml);
		//return null;
		return dwApi.parse(xml, "hash", "value");
	}
	private String getPType(String op){
		if(op.equals(">")) return "1";
		if(op.equals("<")) return "1";
		if(op.equals("=")) return "1";
		if(op.equals(">=")) return "2";
		if(op.equals("<=")) return "2";
		if(op.equals("like")) return "2";
		if(op.equals("likeLeft")) return "2";
		if(op.equals("likeRight")) return "2";
		return "0";
	}
	
	public static void main(String args[]) throws Exception{
		//DBSConfig dbsConfig = DBSConfig.getInstance();
		//CfgClient cc = new CfgClient(dbsConfig.getCfgServiceURL());
		CfgClient cc = new CfgClient();
		List<String> hashs = cc.getPsetHash("associatorL25PixelTauIsolated.coneSize>0 & associatorL25SingleTau.coneSize>0 & associatorL25SingleTau.jetslikea");
		for (String aHash: hashs) {
			System.out.println("Hash is " + aHash);
		}
	}
		

}


