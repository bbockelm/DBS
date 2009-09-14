/***
 * $Id$
 *
 * This is the class for primary dataset business objects.
 * @author Y. Guo
 ***/
package cms.dbs.bizobjs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.json.JSONObject;
import org.json.JSONArray;
import cms.dbs.commons.db;
import cms.dbs.dataobjs.PrimaryDataset;
import cms.dbs.dataobjs.PrimaryDSType;
import cms.dbs.queryobjs.PrimaryDatasetQO;

public class PrimaryDatasetBO extends DBSBusinessObject throws Exception{
    private Connection conn = null;
    public PrimaryDatasetBO(){
	super();
	conn = getConnection();
    }

    protected void finalize() throws Exception {
	try {
	    if (conn != null) conn.close();        // close open connection
	} finally {
	    super.finalize();
	}
    }
    
    public void closeConnection() throws Exception{
	if(conn != null) conn.close();
    }

    public JSONArray getPrimaryDatasets(Primarydataset cond){
	String dsName = cond.getPrimaryDSName;
	PrimaryDatasetQO primaryDS = null;
	if(dsName.equlas("*") || dsName.equals("%")){
	    //No condition needed. 
	    primaryDS = new PrimaryDatasetQO();
	    return primaryDS.listiAllPrimaryDatasets(conn);
	}
	else{
	    primaryDS = new  PrimaryDatasetQO((new JSONArray()).put(cond));
	    return primaryDS.listiAllPrimaryDatasets(cond);
	}
	return null;
    }

}



