package dbs.search.qb;

import java.util.Hashtable;
import java.util.Vector;
import java.util.List;

import xml.DBSXMLParser;
import xml.Element;
import dbs.util.DBSConfig;
import fnal.gov.client.util.HttpUtil;
import fnal.gov.client.DbsWebApi;

public class CfgClient {
	private HttpUtil hu;
	private String url;
	private DbsWebApi dwApi;
	
	public CfgClient(String url) throws Exception{
		hu = new HttpUtil();
		this.url = url;
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
	public List<String> getPsetHash(String path) throws Exception {
		String param = "pname1=associatorL25SingleTau.coneSize&pname0=associatorL25PixelTauIsolated.coneSize&op2=like&pname2=associatorL25SingleTau.jets&ptype2=2&ptype0=1&ptype1=1&num=3&val2=a&op0=>&val1=0&val0=0&op1=>";
		String instanceUrl = this.url + "?" + param;
		String xml = hu.readUrl(instanceUrl);
		//System.out.println(xml);
		//return null;
		return dwApi.parse(xml, "hash", "value");
	}
	
	public static void main(String args[]) throws Exception{
		DBSConfig dbsConfig = DBSConfig.getInstance();
		CfgClient cc = new CfgClient(dbsConfig.getCfgServiceURL());
		List<String> hashs = cc.getPsetHash("abc");
		for (String aHash: hashs) {
			System.out.println("Hash is " + aHash);
		}
	}
		

}


