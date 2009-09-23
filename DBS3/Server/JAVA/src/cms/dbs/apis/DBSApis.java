/***
 * $Id: DBSApis.java,v 1.4 2009/09/23 13:55:48 yuyi Exp $
 * DBS Server side APIs .
 * @author Y. Guo
 ***/
package cms.dbs.apis;

import java.sql.Connection;
import org.json.JSONObject;
import org.json.JSONArray;
import cms.dbs.dataobjs.PrimaryDataset;
import cms.dbs.dataobjs.PrimaryDSType;
import cms.dbs.bizobjs.PrimaryDatasetBO;
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

    public JSONObject DBSApiFindPrimaryDatasets( PrimaryDataset cd) throws Exception{
	JSONArray result = new JSONArray();
	PrimaryDatasetBO pBO = new PrimaryDatasetBO();
	result = pBO.getPrimaryDatasets(conn, cd);
	JSONObject retn = new JSONObject();
	retn.putOnce("input", cd);
	retn.putOnce("result", result);
	return retn;
    }

    public void DBSApiInsertPrimaryDataset(PrimaryDataset cd) throws Exception{
	PrimaryDatasetBO pBO = new PrimaryDatasetBO();
	pBO.insertPrimaryDataset(conn, cd);
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
	    System.out.println("***Insert new primary dataset TEST6 ***");
	    PrimaryDSType PT = new PrimaryDSType(0, "test");
	    PrimaryDataset PD = new PrimaryDataset(0, "TEST6", PT, 0, "");
	    api.DBSApiInsertPrimaryDataset(PD);
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
