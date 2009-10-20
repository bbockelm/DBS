/***
 * $Id: DBSApis.java,v 1.7 2009/10/14 20:09:37 yuyi Exp $
 * DBS Server side APIs .
 * @author Y. Guo
 ***/
package cms.dbs.apis;

import java.sql.Connection;
import org.json.JSONObject;
import org.json.JSONArray;
import cms.dbs.dataobjs.PrimaryDataset;
import cms.dbs.dataobjs.PrimaryDSType;
import cms.dbs.dataobjs.Dataset;
import cms.dbs.dataobjs.ProcessedDataset;
import cms.dbs.dataobjs.DataTier;
import cms.dbs.dataobjs.DatasetType;
import cms.dbs.dataobjs.File;
import cms.dbs.dataobjs.PhysicsGroup;
import cms.dbs.bizobjs.PrimaryDatasetBO;
import cms.dbs.bizobjs.DatasetBO;
import cms.dbs.bizobjs.FileBO;
import cms.dbs.bizobjs.BlockBO;
import cms.dbs.commons.exceptions.DBSException;
import cms.dbs.commons.utils.DBSSrvcConfig;
import cms.dbs.commons.db.DBManagement;




public class DBSApis {
    private Connection conn = null;
    
    public DBSApis() throws Exception{
	conn = getConnection();
    }

    private Connection getConnection() throws Exception{
        DBSSrvcConfig config = DBSSrvcConfig.getInstance();
        return DBManagement.getConnection(config.getDbDriver(),
            config.getDbURL(), config.getDbUserName(), config.getDbUserPasswd());
    }

    public void finalize() throws Exception {
        if (conn != null) conn.close();        // close open connection
    }

    private void closeConnection() throws Exception{
        if(conn != null) conn.close();
    }
    
    public JSONObject DBSApiFindFiles(File file) throws Exception{
        JSONArray result = new JSONArray();
        FileBO fBO = new FileBO();
        result = fBO.getFiles(conn, file);
        JSONObject retn = new JSONObject();
        retn.putOnce("input", file);
        retn.putOnce("result", result);
        return retn;
    }

    public JSONObject DBSApiFindFiles(JSONArray files) throws Exception{
        JSONArray result = new JSONArray();
        FileBO fBO = new FileBO();
        result = fBO.getFiles(conn, files);
        JSONObject retn = new JSONObject();
        retn.putOnce("input", files);
        retn.putOnce("result", result);
        return retn;
    }

    public void DBSApiInsertFile(File f, JSONArray fps, JSONArray fls) throws Exception{
	FileBO fBO = new FileBO();	
	fBO.insertFile(conn, f, fps,fls);
    }

    public JSONObject DBSApiFindLumi4File(File file) throws Exception{
        JSONArray result = new JSONArray();
        FileBO fBO = new FileBO();
        result = fBO.getFileLumis(conn, file);
        JSONObject retn = new JSONObject();
        retn.putOnce("input", file);
        retn.putOnce("result", result);
        return retn;
    }	

    public JSONObject DBSApiFindParents4File(File file) throws Exception{
        JSONArray result = new JSONArray();
        FileBO fBO = new FileBO();
        result = fBO.getFileParents(conn, file);
        JSONObject retn = new JSONObject();
        retn.putOnce("input", file);
        retn.putOnce("result", result);
        return retn;
    }

    public JSONObject DBSApiFindDatasets(Dataset cd) throws Exception{
	JSONArray result = new JSONArray();
	DatasetBO dBO = new DatasetBO();
	result = dBO.getDatasets(conn, cd);
	JSONObject retn = new JSONObject();
        retn.putOnce("input", cd);
        retn.putOnce("result", result);
        return retn;
    }

    public int DBSApiInsertDataset(Dataset cd) throws Exception{
        DatasetBO dBO = new DatasetBO();
        return dBO.insertDataset(conn, cd);
    }

    public JSONObject DBSApiFindPrimaryDatasets( PrimaryDataset cd) throws Exception{
	JSONArray result = new JSONArray();
	PrimaryDatasetBO pBO = new PrimaryDatasetBO();
	result = pBO.getPrimaryDatasets(conn, cd);
	JSONObject retn = new JSONObject();
	retn.putOnce("input", cd);
	retn.putOnce("result", result);
	return retn;
    }

    public PrimaryDataset DBSApiInsertPrimaryDataset(PrimaryDataset cd) throws Exception{
	PrimaryDatasetBO pBO = new PrimaryDatasetBO();
	return pBO.insertPrimaryDataset(conn, cd);
    }//end DBSApiInsertPrimaryDataset
    
    public static void main (String args[]){
	try{
            DBSApis api = new DBSApis();
	    PrimaryDataset cd = new PrimaryDataset(0, "%", null, 0, "");
	    JSONArray result = (api.DBSApiFindPrimaryDatasets(cd)).getJSONArray("result");
	    for(int i=0; i<result.length();i++){
		 System.out.println(result.optJSONObject(i));
	    }
            PrimaryDataset cd2 = new PrimaryDataset(0, "Cosm%", null, 0, "");
            JSONArray result2 = (api.DBSApiFindPrimaryDatasets(cd2)).getJSONArray("result");
            for(int i=0; i<result2.length();i++){
                 System.out.println(result2.optJSONObject(i));
            }
	    //now serarch for dataset
	    Dataset dataset = new Dataset(0, "/TTbar/Summer09-MC_31X_V3-v1/GEN-SIM-RAW");
	    JSONArray datasets = (api.DBSApiFindDatasets(dataset)).getJSONArray("result");
	    for(int i=0; i<datasets.length();i++){
		System.out.println(datasets.optJSONObject(i));
	    }
	    //now insert primary dataset, processed dataset anda dataset				    
	    /*
	    System.out.println("***Insert new primary dataset TEST3 ***");
	    PrimaryDSType PT = new PrimaryDSType(0, "test");
	    PrimaryDataset PD = new PrimaryDataset(0, "TEST10-Primary", PT, 0, "");
	    System.out.println((api.DBSApiInsertPrimaryDataset(PD)).toString());
	    System.out.println("***Insert new dataset ***");
	    ProcessedDataset processedDS = new  ProcessedDataset(0, "TEST6-ProcessedDS");
	    Dataset ds = new Dataset(0, "/TEST10-Primary/TEST6-ProcessedDS/GEN-SIM-RAW", 1, PD, processedDS, new DataTier(0, "GEN-SIM-RAW"), 
				new DatasetType(0, "PRODUCTION"), null,
				null, new PhysicsGroup(6, "QCD"), 0.01, "Yuyi's Tag", 0, "");
	    System.out.println(api.DBSApiInsertDataset(ds));
	    */
	    //test list File APIs
            String LFN = "/store/mc/Summer09/TTbar/GEN-SIM-RAW/%.root";
	    File file = new File(0, LFN);
            System.out.println("*****List Files ******\n");
	    System.out.println(api.DBSApiFindFiles(file));
	    //close the connection before you leave.
            api.closeConnection();
	}
	catch (DBSException ex){
	    System.out.println("DBSException raised :" + ex.getMessage() + ". " + ex.getDetail());
	}
	catch(Exception ex){
	    System.out.println("Exception raised :" + ex.getMessage() + ". " );
	}
    }
}
