/***
 * $Id: DBSApis.java,v 1.1 2009/09/14 15:02:02 yuyi Exp $
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
    public static JSONArray DBSApiFindPrimaryDatasets( PrimaryDataset cd) throws Exception{
	JSONArray result = new JSONArray();
	PrimaryDatasetBO pBO = new PrimaryDatasetBO();
	result = pBO.getPrimaryDatasets(cd);
	return result;
    }
    public static void main (String args[]){
	try{
	    PrimaryDataset cd = new PrimaryDataset(0, "%", null, 0, "");
	    JSONArray result = new DBSApis().DBSApiFindPrimaryDatasets(cd);
	    for(int i=0; i<result.length();i++){
		 System.out.println(result.optJSONObject(i));
	    }
            PrimaryDataset cd2 = new PrimaryDataset(0, "Cosm%", null, 0, "");
            JSONArray result2 = new DBSApis().DBSApiFindPrimaryDatasets(cd2);
            for(int i=0; i<result2.length();i++){
                 System.out.println(result2.optJSONObject(i));
            }

	}
	catch (DBSException ex){
	    System.out.println("DBSException raised :" + ex.getMessage() + ". " + ex.getDetail());
	}
	catch(Exception ex){
	    System.out.println("Exception raised :" + ex.getMessage() + ". " );
	}
    }
}
