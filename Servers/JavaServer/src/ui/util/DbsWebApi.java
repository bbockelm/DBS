package ui.util;

import org.richfaces.component.html.HtmlRichMessage;
//import javax.faces.context.FacesContext;

import java.util.Hashtable;
import java.util.Vector;
import java.util.List;
import java.util.StringTokenizer;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import dbs.api.DBSApi;
import dbs.api.parser.DBSApiParser;
import dbs.util.DBSUtil;
import xml.DBSXMLParser;
import xml.Element;
import dm.DbsDataset;



public class DbsWebApi {
	private String url;
	private Util u;
	private List<DbsDataset> paths = null;
	private String str;
	private HtmlRichMessage generalInputMessage;

	public DbsWebApi(String url){
		this.url = url;
		u = new Util();
		str = "";
	}

	public void setGeneralInputMessage(HtmlRichMessage generalInputMessage) {
		this.generalInputMessage = generalInputMessage;
	}

	public List<DbsDataset> parsePaths(String inputXml) throws Exception {
		if(u.isException(inputXml)) throw new Exception(inputXml);
		Vector toReturn = new Vector();
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
 		Vector allElement = dbsParser.getElements();
		for (int i = 0; i != allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			if (e.name.equals("processed_dataset") ) {
				DbsDataset dbsDataset = new DbsDataset();
				//System.out.println("parsing .......");
				dbsDataset.setDatasetName(DBSUtil.get(e.attributes, "path"));
				toReturn.add(dbsDataset);
			} 
		}
		return toReturn;
	}

	public List<String> parse(String inputXml, String tag, String key) throws Exception {
		if(u.isException(inputXml)) throw new Exception(inputXml);
		Vector toReturn = new Vector();
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
 		Vector allElement = dbsParser.getElements();
		for (int i = 0; i != allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			if (e.name.equals(tag) ) {
				//System.out.println("parsing .......");
				String name = DBSUtil.get(e.attributes, key);
				toReturn.add(name);
			} 
		}
		return toReturn;
	}

	public List<String> listBlocks(String path) throws Exception {
		Hashtable table = new Hashtable();
		table.put("api", "listBlocks");
		table.put("path", path);
		String instanceUrl = this.url + "?" + u.makeUrl(table);
		String xml = u.readUrl(instanceUrl);
		//System.out.println("path " + path);
		//System.out.println("xml from listBlocks " + xml);
		
		return parse(xml, "block", "name");
		
	}
	
	public List<String> listProcessedDatasets(String primName, String procName) throws Exception {
		//System.out.println("primary_datatset_name_pattern " + primName + "  processed_datatset_name_pattern " + procName);
		Hashtable table = new Hashtable();
		table.put("api", "listProcessedDatasets");
		table.put("primary_datatset_name_pattern", primName);
		table.put("processed_datatset_name_pattern", procName);
		String instanceUrl = this.url + "?" + u.makeUrl(table);
		String xml = u.readUrl(instanceUrl);
		//System.out.println("xml from listProcessedDatasets " + xml);
		return parse(xml, "path", "dataset_path");
		
	}

	public List<String> listDatasetParents(String path) throws Exception {
		Vector toReturn = new Vector();
		Hashtable table = new Hashtable();
		table.put("api", "listDatasetParents");
		table.put("path", path);
		String instanceUrl = this.url + "?" + u.makeUrl(table);
		String xml = u.readUrl(instanceUrl);
		//System.out.println("xml from listDatasetParents " + xml);
		List<String> tmpPaths = parse(xml, "processed_dataset_parent", "path");
		for (int i = 0; i != tmpPaths.size() ; ++i) {
			StringTokenizer st = new StringTokenizer((String)tmpPaths.get(i), "/");
			List<String> actualPaths = listProcessedDatasets(st.nextToken(), st.nextToken());
			for (int j = 0; j != actualPaths.size() ; ++j) {
				toReturn.add((String)actualPaths.get(j));
			}
		}
		return toReturn;
		
	}

	public List<DbsDataset> listDatasetPaths() throws Exception {
		Hashtable table = new Hashtable();
		table.put("api", "listDatasetPaths");
		String instanceUrl = this.url + "?" + u.makeUrl(table);
		String xml = u.readUrl(instanceUrl);
		//System.out.println("xml from listDatasetPaths " + xml);
		return parsePaths(xml);
	}


	public String listDatasetContents(String path, String block) throws Exception {
		Hashtable table = new Hashtable();
		table.put("api", "listDatasetContents");
		table.put("path", path);
		table.put("block_name", block);
		String instanceUrl = this.url + "?" + u.makeUrl(table);
		String xml = u.readUrl(instanceUrl);
		System.out.println("xml from listDatasetContents " + xml);
		if(u.isException(xml)) throw new Exception(xml);
		return xml;
		
	}

	public String insertDatasetContents(String xmlinput) throws Exception {
		Hashtable table = new Hashtable();
		table.put("api", "insertDatasetContents");
		table.put("xmlinput", xmlinput);
		String data = u.makeUrl(table);
		String xml = u.postUrl(this.url, data);
		System.out.println("xml from insertDatasetContents " + xml);
		if(u.isException(xml)) {
			if(xml.indexOf("code ='1024'") == -1) {
				throw new Exception(xml);
			}
		}
		return xml;
		
	}



	private boolean doesPathExists(DbsWebApi dwApiDst, String path) throws Exception {
		if (paths == null) paths = dwApiDst.listDatasetPaths();
		
		for (int i = 0; i != paths.size() ; ++i) {
			String aPath = (String)(((DbsDataset)paths.get(i)).getDatasetName());
			if(aPath.equals(path)) return true;
		}
		return false;
	
	}
	
	public String migrateDataset(String srcUrl, String dstUrl, String path, boolean withParents, boolean force) throws Exception {
		String toReturn = "";
		DbsWebApi dwApiSrc = new DbsWebApi(srcUrl);
		DbsWebApi dwApiDst = new DbsWebApi(dstUrl);
		if(withParents) {
			List<String> parents = dwApiSrc.listDatasetParents(path);
			for (int i = 0; i != parents.size() ; ++i) {
				String parentPath = (String)parents.get(i);
				//System.out.println("Parent path " + parentPath);
				toReturn += migrateDataset(srcUrl, dstUrl, parentPath, withParents, force);
			}
		}
		boolean transfer = false;
		if (!force) {
			if(!doesPathExists(dwApiDst, path)) {
				transfer = true;
			}
		} else transfer = true;
		System.out.println("************************************");
		System.out.println("force " + force + "  parents " + withParents + " transfer " + transfer);
		if(transfer) {
			List<String> v = dwApiSrc.listBlocks(path);
			for (int i = 0; i != v.size() ; ++i) {
				String blockName = (String)v.get(i);
				System.out.println("Transferring path " + path +  " \nBlock name " + blockName);
				str += "  Transferring Path " + path +  " \nBlock name " + blockName;
				u.setText(str, generalInputMessage);
				//JSFUtils.setManagedBeanValue(FacesContext.getCurrentInstance(), "DatasetMigrate.logOutputText", str);
				toReturn += dwApiDst.insertDatasetContents(dwApiSrc.listDatasetContents(path, blockName));
				/*for (int k = 0 ; k != 999; ++k ){
					for (int l = 0 ; l != 999; ++l ){
						System.out.println("k " + k + " l " + l);
					}
				}*/
				str += " Path " + path +  " \nBlock name " + blockName + " transfer complete\n\n";
				u.setText(str, generalInputMessage);
			}
		}

		return toReturn;
	}

	
	public static void main(String args[]){
		try {
			String srcUrl = "http://cmssrv48.fnal.gov:8181/DBS/servlet/DBSServlet";
			String dstUrl = "http://cmssrv48.fnal.gov:8181/DBS1/servlet/DBSServlet";
			DbsWebApi dwApi = new DbsWebApi("http://cmssrv48.fnal.gov:8181/DBS/servlet/DBSServlet");
			String path = "/test_primary_001/TestProcessedDS002/GEN-SIM";
			//String path = "/This_is_a_test_primary_3873a5b8-1d08-4189-8f74-8956328babac/This_is_a_test_processed_3873a5b8-1d08-4189-8f74-8956328babac/GEN";
			//String path = "/test_primary_001/TestProcessedDS001/GEN-SIM";
				
			/*List<String> v = dwApi.listBlocks(path);
			for (int i = 0; i != v.size() ; ++i) {
				System.out.println("Block name " + (String)v.get(i));
				String xmlinput = dwApi.listDatasetContents(path, (String)v.get(i));
				System.out.println("insert data" + dwApi.insertDatasetContents(xmlinput));
			}*/
			dwApi.migrateDataset(srcUrl, dstUrl, path, true, false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
