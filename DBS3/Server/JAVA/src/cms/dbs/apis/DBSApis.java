/***
 * $Id: DBSApis.java,v 1.3 2009/09/22 19:06:13 yuyi Exp $
 * DBS Server side APIs .
 * @author Y. Guo
 ***/
package cms.dbs.apis;

import org.json.JSONObject;
import org.json.JSONArray;
import cms.dbs.dataobjs.PrimaryDataset;
import cms.dbs.dataobjs.PrimaryDSType;
import cms.dbs.bizobjs.PrimaryDatasetBO;
import cms.dbs.commons.exceptions.DBSException;

public class DBSApis {

    public DBSApis(){
    }
    public static JSONObject DBSApiFindPrimaryDatasets( PrimaryDataset cd) throws Exception{
	JSONArray result = new JSONArray();
	PrimaryDatasetBO pBO = new PrimaryDatasetBO();
	result = pBO.getPrimaryDatasets(cd);
	pBO.closeConnection();
	JSONObject retn = new JSONObject();
	retn.putOnce("input", cd);
	retn.putOnce("result", result);
	return retn;
    }

    public static void DBSApiInsertPrimaryDataset(PrimaryDataset cd) throws Exception{
	PrimaryDatasetBO pBO = new PrimaryDatasetBO();
	pBO.insertPrimaryDataset(cd);
	pBO.commitConnection();
	pBO.closeConnection();
    }//end DBSApiInsertPrimaryDataset
    
    public static void main (String args[]){
	try{
	    PrimaryDataset cd = new PrimaryDataset(0, "%", null, 0, "");
	    JSONArray result = (new DBSApis().DBSApiFindPrimaryDatasets(cd)).getJSONArray("result");
	    for(int i=0; i<result.length();i++){
		 System.out.println(result.optJSONObject(i));
	    }
            PrimaryDataset cd2 = new PrimaryDataset(0, "Cosm%", null, 0, "");
            JSONArray result2 = (new DBSApis().DBSApiFindPrimaryDatasets(cd2)).getJSONArray("result");
            for(int i=0; i<result2.length();i++){
                 System.out.println(result2.optJSONObject(i));
            }
	    System.out.println("***Insert new primary dataset TEST4 ***");
	    PrimaryDSType PT = new PrimaryDSType(0, "test");
	    PrimaryDataset PD = new PrimaryDataset(0, "TEST4", PT, 0, "");
	    new DBSApis().DBSApiInsertPrimaryDataset(PD);
	}
	catch (DBSException ex){
	    System.out.println("DBSException raised :" + ex.getMessage() + ". " + ex.getDetail());
	}
	catch(Exception ex){
	    System.out.println("Exception raised :" + ex.getMessage() + ". " );
	}
    }
}
