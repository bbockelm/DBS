package gov.fnal.ms.dm.util;

import gov.fnal.ms.dm.session.MSSessionEJB;
import gov.fnal.ms.dm.session.MSSessionEJBLocal;
import gov.fnal.ms.client.DbsWebApi;

import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

public class DbsWebMigrateApi extends DbsWebApi {
    
    private String str = "";
    private MSSessionEJBLocal ejbObj;
    
    private List<String> migratedDataset ;
    public DbsWebMigrateApi() {}
    public DbsWebMigrateApi(String url, MSSessionEJBLocal ejbObj) throws Exception{
            super(url);
            
            /*Object obj = u.getInitialContext().lookup("ms/MSSessionEJB/remote");
            System.out.println("class of obj is : "+obj);
            ejbObj = (MSSessionEJB)obj;*/
            this.ejbObj = ejbObj;
    		migratedDataset = new ArrayList<String>() ;

    }    
    public String migrateDataset(String srcUrl, String dstUrl, String path, boolean withParents, boolean force) throws Exception {
	    migratedDataset.add(path);
            String toReturn = "";
            DbsWebApi dwApiSrc = new DbsWebApi(srcUrl);
            DbsWebApi dwApiDst = new DbsWebApi(dstUrl);
            if(withParents) {
                    List<String> parents = dwApiSrc.listDatasetParents(path);
		    for(String parentPath: parents) {
                            System.out.println("Parent path " + parentPath);
			    if(!migratedDataset.contains(parentPath))
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
                            System.out.println("Transferring path " + path +  " \nBlock name " + blockName + "\n\n");
                            str += "  Transferring Path " + path +  " \nBlock name " + blockName;
			    dwApiSrc.listDatasetContents(path, blockName);
                            dwApiDst.insertDatasetContents(blockName);
                            str += " Path " + path +  " \nBlock name " + blockName + " transfer complete\n\n PROGRESS " + progress;
                            //apiLogic.updateRequest(conn, out, srcUrl, dstUrl, path, "", progress, "");
			    System.out.println("CAlling updateRequest on ");
			    System.out.println("srcurl " + srcUrl);
			    System.out.println("dstUrl " + dstUrl);
			    System.out.println("path " + path);
			    System.out.println("progress   " + progress);
                            if(ejbObj != null) {
				    try {
					    ejbObj.updateRequest(srcUrl, dstUrl, path, "", progress, "");
				    }catch(Exception e) {
					    if(!e.getMessage().equals("Request does not exists")) throw e;
				    }
			    }
                    }
            }

            return toReturn;
    }

    public static void main(String args[]) throws Exception {
       	    DbsWebMigrateApi dwApi = new DbsWebMigrateApi("http://cmssrv48.fnal.gov:8282/DBS/servlet/DBSServlet", null);
	    boolean withParents = true;
	    boolean withForce = true;
	    String srcUrl = "https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_local_10_writer/servlet/DBSServlet";
	    String dstUrl = "http://cmssrv48.fnal.gov:8383/DBS/servlet/DBSServlet";
	    String path = "/DiPion_E1to10_Eta25/Summer08_IDEAL_V9_v1/GEN-SIM-RECO";
		    
		
	    dwApi.migrateDataset(srcUrl, dstUrl, path, withParents, withForce);
																			 
    }

}
