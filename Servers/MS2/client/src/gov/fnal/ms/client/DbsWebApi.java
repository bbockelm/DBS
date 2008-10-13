package gov.fnal.ms.client;

import gov.fnal.ms.client.util.HttpUtil;
import gov.fnal.ms.client.util.Util;
import gov.fnal.ms.client.xml.DBSXMLParser;
import gov.fnal.ms.client.xml.Element;


import java.util.Hashtable;
import java.util.Vector;
import java.util.List;
import java.util.StringTokenizer;
import java.io.PrintWriter;



public class DbsWebApi {

    private String url;
    private Util u;
    private HttpUtil hu;
    //private String str;
    private List<String> paths = null;

    PrintWriter out ;

    public DbsWebApi(String url) throws Exception{
            this.url = url;
            u = new Util();
            hu= new HttpUtil();
            //str = "";
            //out =  new PrintWriter(System.out);

    }

    
    
    public DbsWebApi() {}
    
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
                                   String name = u.get(e.attributes, key);
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
                   String xml = hu.readUrl(instanceUrl);
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
                   String xml = hu.readUrl(instanceUrl);
                   //System.out.println("xml from listProcessedDatasets " + xml);
                   return parse(xml, "path", "dataset_path");

           }

           public List<String> listDatasetParents(String path) throws Exception {
                   Vector toReturn = new Vector();
                   Hashtable table = new Hashtable();
                   table.put("api", "listDatasetParents");
                   table.put("path", path);
                   String instanceUrl = this.url + "?" + u.makeUrl(table);
                   String xml = hu.readUrl(instanceUrl);
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

           public List<String> listDatasetPaths() throws Exception {
                   Hashtable table = new Hashtable();
                   table.put("api", "listDatasetPaths");
                   String instanceUrl = this.url + "?" + u.makeUrl(table);
                   String xml = hu.readUrl(instanceUrl);
                   //System.out.println("xml from listDatasetPaths " + xml);
                   return parse(xml, "processed_dataset", "path");
           }

            public String getDBSServerVersion() throws Exception {
                Hashtable table = new Hashtable();
                table.put("api", "getDBSServerVersion");
                String instanceUrl = this.url + "?" + u.makeUrl(table);
                String xml = hu.readUrl(instanceUrl);
                List<String> toReturn = parse(xml, "dbs_version", "server_version");
                if (toReturn.size() > 0) return toReturn.get(0);
                return "";
            }

        public String getDBSSchemaVersion() throws Exception {
            Hashtable table = new Hashtable();
            table.put("api", "getDBSServerVersion");
            String instanceUrl = this.url + "?" + u.makeUrl(table);
            String xml = hu.readUrl(instanceUrl);
            List<String> toReturn = parse(xml, "dbs_version", "schema_version");
            if (toReturn.size() > 0) return toReturn.get(0);
            return "";
        }

           public void listDatasetContents(String path, String block) throws Exception {
                   Hashtable table = new Hashtable();
                   table.put("api", "listDatasetContents");
                   table.put("path", path);
                   table.put("block_name", block);
                   String instanceUrl = this.url + "?" + u.makeUrl(table);
                   hu.readUrl(instanceUrl, block.replaceAll("[/#]", "_"));
                   //System.out.println("xml from listDatasetContents " + xml);
                   //if(u.isException(xml)) throw new Exception(xml);
                   //return xml;
           }

           public void insertDatasetContents(String block) throws Exception {
                   Hashtable table = new Hashtable();
                   table.put("api", "insertDatasetContents");
                   //table.put("xmlinput", xmlinput);
                   //String instanceUrl = this.url + "?" + u.makeUrl(table);

                   //table = new Hashtable();
                   String data = u.makeUrl(table) + "xmlinput=";
		   
                   hu.postUrlFile(this.url, data,  block.replaceAll("[/#]", "_"));
                   //System.out.println("xml from insertDatasetContents " + xml);
                   /*if(u.isException(xml)) {
                           if(xml.indexOf("code ='1024'") == -1) {
                                   throw new Exception(xml);
                           }
                   }
                   return xml;*/

           }



           protected boolean doesPathExists(DbsWebApi dwApiDst, String path) throws Exception {
                   if (paths == null) paths = dwApiDst.listDatasetPaths();

                   for (int i = 0; i != paths.size() ; ++i) {
                           String aPath = ((String)paths.get(i));
                           if(aPath.equals(path)) return true;
                   }
                   return false;

           }

           /*public String migrateDataset(String srcUrl, String dstUrl, String path, boolean withParents, boolean force) throws Exception {
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
                                   //String progress = String.valueOf((int)(((float)(i + 1)/v.size()) * 100));
                                   int progress = (int)(((float)(i + 1)/v.size()) * 100);
                                   String blockName = (String)v.get(i);
                                   System.out.println("Transferring path " + path +  " \nBlock name " + blockName);
                                   str += "  Transferring Path " + path +  " \nBlock name " + blockName;
                                   toReturn += dwApiDst.insertDatasetContents(dwApiSrc.listDatasetContents(path, blockName));
                                   str += " Path " + path +  " \nBlock name " + blockName + " transfer complete\n\n PROGRESS " + progress;
                                   //apiLogic.updateRequest(conn, out, srcUrl, dstUrl, path, "", progress, "");
                                   ejbObj.updateRequest(srcUrl, dstUrl, path, "", progress, "");
                           }
                   }

                   return toReturn;
           }*/


}
