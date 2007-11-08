package dm;

import java.util.Vector;
import java.util.List;
import java.util.Hashtable;
import dbs.api.parser.DBSApiParser;
import dbs.util.DBSUtil;
import xml.DBSXMLParser;
import xml.Element;
import ui.util.Util;
import ui.util.DbsWebApi;
import ui.util.JSFUtils;

public class JavaServiceFacade {
	public JavaServiceFacade(){}


	public List<DbsInstance> getAllDbsInstances() {
		Vector toReturn = new Vector();
		DbsInstance dbsInstance = new DbsInstance();
		dbsInstance.setInstanceName("http://cmssrv48.fnal.gov:8181/DBS/servlet/DBSServlet");
		toReturn.add(dbsInstance);

		dbsInstance = new DbsInstance();
		dbsInstance.setInstanceName("http://cmssrv48.fnal.gov:8181/DBS1/servlet/DBSServlet");
		toReturn.add(dbsInstance);

		/*dbsInstance = new DbsInstance();
		dbsInstance.setInstanceName("http://cmssrv48.fnal.gov:8080/PSeudoGLOBAL_107pre2/servlet/DBSServlet");
		toReturn.add(dbsInstance);
		
		dbsInstance = new DbsInstance();
		dbsInstance.setInstanceName("http://cmsdbsprod.cern.ch/cms_dbs_prod_local_02/servlet/DBSServlet");
		toReturn.add(dbsInstance);
		
		dbsInstance = new DbsInstance();
		dbsInstance.setInstanceName("http://cmsdbsprod.cern.ch/cms_dbs_prod_local_03/servlet/DBSServlet");
		toReturn.add(dbsInstance);
		
		dbsInstance = new DbsInstance();
		dbsInstance.setInstanceName("http://cmsdbsprod.cern.ch/cms_dbs_prod_local_04/servlet/DBSServlet");
		toReturn.add(dbsInstance);
		
		dbsInstance = new DbsInstance();
		dbsInstance.setInstanceName("http://cmsdbsprod.cern.ch/cms_dbs_prod_local_05/servlet/DBSServlet");
		toReturn.add(dbsInstance);
		
		dbsInstance = new DbsInstance();
		dbsInstance.setInstanceName("http://cmsdbsprod.cern.ch/cms_dbs_prod_local_06/servlet/DBSServlet");
		toReturn.add(dbsInstance);
		
		dbsInstance = new DbsInstance();
		dbsInstance.setInstanceName("http://cmsdbsprod.cern.ch/cms_dbs_prod_local_07/servlet/DBSServlet");
		toReturn.add(dbsInstance);
		
		dbsInstance = new DbsInstance();
		dbsInstance.setInstanceName("http://cmsdbsprod.cern.ch/cms_dbs_prod_local_08/servlet/DBSServlet");
		toReturn.add(dbsInstance);
		
		dbsInstance = new DbsInstance();
		dbsInstance.setInstanceName("http://cmsdbsprod.cern.ch/cms_dbs_prod_local_09/servlet/DBSServlet");
		toReturn.add(dbsInstance);
		
		dbsInstance = new DbsInstance();
		dbsInstance.setInstanceName("http://cmssrv17.fnal.gov:8989/DBS_1_0_5/servlet/DBSServlet");
		toReturn.add(dbsInstance);*/
		
		return toReturn;

	}

	/*public List<DbsDataset> parse(String inputXml) throws Exception {
		Vector toReturn = new Vector();
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
 		Vector allElement = dbsParser.getElements();
		Hashtable table = null;
		for (int i=0; i<allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			DbsDataset dbsDataset = new DbsDataset();
			if (e.name.equals("processed_dataset") ) {
				System.out.println("parsing .......");
				dbsDataset.setDatasetName(DBSUtil.get(e.attributes, "path"));
				toReturn.add(dbsDataset);
			} 
		}
		return toReturn;
	}*/

	
	public List<DbsDataset> getAllDatasets(String instanceUrl) {

		try {
			DbsWebApi dwApi = new DbsWebApi(instanceUrl);
			return dwApi.listDatasetPaths();
			/*if(DBSUtil.isNull(instanceUrl)) return (new Vector());
			instanceUrl += "?api=listDatasetPaths&apiversion=DBS_1_0_7";
			String xml = (new Util()).readUrl(instanceUrl);
			//JSFUtils.storeBeanValue("DatasetMigrate.xml", xml);
			System.out.println("XML is " + xml);
			return parse(xml);*/
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			//JSFUtils.storeBeanValue("DatasetMigrate.xml", ex.getMessage());
       		} 
		return (new Vector());


	}
	
	public List<DbsDataset> getAllDstDatasets() {
			String instanceUrl = (String)JSFUtils.retriveBeanValue("DatasetMigrate.dstInstanceNameOutputText.value");
			System.out.println("Bean VALUE is ------------> " + instanceUrl);
			return (getAllDatasets(instanceUrl));
	}
	
	public List<DbsDataset> getAllSrcDatasets() {
		String instanceUrl = (String)JSFUtils.retriveBeanValue("DatasetMigrate.srcInstanceNameOutputText.value");
		System.out.println("Bean VALUE is ------------> " + instanceUrl);
		return (getAllDatasets(instanceUrl));
	}

}
