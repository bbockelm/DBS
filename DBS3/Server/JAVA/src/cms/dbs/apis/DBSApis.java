/***
 * $Id$
 * DBS Server side APIs .
 * @author Y. Guo
 ***/
package cms.dbs.apis;

import org.json.JSONObject;
import org.json.JSONArray;
import cms.dbs.dataobjs.PrimaryDataset;
import cms.dbs.dataobjs.PrimaryDSType;
import cms.dbs.bizobjs;

public class DBSApis {

    public DBSApis(){
    }
    public static JSONArray DBSApiFindPrimaryDatasets( PrimaryDataset cd) throws Exception{
	JSONArray result = null;
	PrimaryDatasetBO pBO = new PrimaryDatasetBO();
	result = pBO.getPrimaryDatasets(cd);
	return result;
    }
}
