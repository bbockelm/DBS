package dbs.search.qb;

import java.util.Hashtable;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

import dbs.util.DBSConfig;
import org.json.JSONObject;
import org.json.JSONException;
import gov.fnal.ms.client.util.HttpUtil;

public class SiteClient {
	private HttpUtil hu;
	private String url;
	
	public SiteClient() throws Exception{
		DBSConfig dbsConfig = DBSConfig.getInstance();
		hu = new HttpUtil(dbsConfig.getHostcert(), dbsConfig.getCertpass());
		this.url = dbsConfig.getSiteServiceURL();

	}

	public List<String> parse_DEPRICATED(String input, String tag) throws Exception {
		String matchBegin = tag + "': '";
		String matchEnd = "'}";

		Vector toReturn = new Vector();
		StringTokenizer st = new StringTokenizer(input, ",");
		int numTokens = st.countTokens();
		//System.out.println("numTokens " + numTokens);
		if(input.equals("{}")) return toReturn;
		for(int i = 0 ; i != numTokens ; ++i) {
			String token = st.nextToken();
			if(token.length() > 0) {
				int beginIndex = token.indexOf(matchBegin) + matchBegin.length();
				int endIndex = token.indexOf(matchEnd);
				if(beginIndex < 0 || endIndex < 0) throw new Exception("SiteDB could not return SE mapping");
				String toAdd = token.substring(beginIndex, endIndex);
				//System.out.println(toAdd);
				toReturn.add(toAdd);
			}
		}
		
		return toReturn;
	}

	public List<String> parse(String input, String tag) throws Exception {
		List<String> toReturn = new ArrayList<String>();
		JSONObject jobj = new JSONObject(input);
		if(jobj == null) return toReturn;
		String objs[] = JSONObject.getNames(jobj);
		if (objs == null) return toReturn;
		for(String a: objs) {
			//System.out.println("Names  " + a);
			//System.out.println (innerJobj);
			JSONObject innerJobj = jobj.getJSONObject(a);
			if (innerJobj == null) continue;
			String keys[] = JSONObject.getNames(innerJobj);
			if (keys == null) continue;
			for (String aKey: keys) {
				if(aKey.equals(tag)) {
					StringTokenizer st = new StringTokenizer(innerJobj.getString(aKey), ",");
					for(int i=0; i <= st.countTokens() ; ++i) {
						String token = st.nextToken();
						//System.out.println(token);
						toReturn.add(token);
						//System.out.println(innerJobj.getString(aKey));
					}
				}
			}
		}
		return toReturn;
	}

	public List<String> getSE(String siteName) throws Exception {
		String instanceUrl = this.url + "?name=" + siteName;
		//System.out.println("URL is " + instanceUrl);
		String response = hu.readUrl(instanceUrl);
		//System.out.println(response);
		List<String> toReturn = parse(response, "name");
		toReturn.add(siteName);
		return toReturn;
	}
	
	public static void main(String args[]) throws Exception{
		//DBSConfig dbsConfig = DBSConfig.getInstance();
		//SiteClient cc = new SiteClient(dbsConfig.getSiteServiceURL());
		SiteClient cc = new SiteClient();
		//List<String> hashs = cc.getSE("T1_UK_RALaa");
		List<String> sites = cc.getSE(args[0]);
		for (String aSite: sites) {
			System.out.println("Site is " + aSite);
		}
	}
		

}


