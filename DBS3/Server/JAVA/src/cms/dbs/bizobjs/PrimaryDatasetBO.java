/***
 * $Id: PrimaryDatasetBO.java,v 1.1 2009/09/14 14:57:16 yuyi Exp $
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
import cms.dbs.dataobjs.PrimaryDataset;
import cms.dbs.dataobjs.PrimaryDSType;
import cms.dbs.queryobjs.PrimaryDatasetQO;

public class PrimaryDatasetBO extends DBSBusinessObject{
    private Connection conn = null;
    
    public PrimaryDatasetBO() throws Exception {
	super();
	conn = getConnection();
    }

    protected void finalize() throws Exception {
	if (conn != null) conn.close();        // close open connection
    }
    
    public void closeConnection() throws Exception{
	if(conn != null) conn.close();
    }

    public JSONArray getPrimaryDatasets(PrimaryDataset cond) throws Exception{
	String dsName = cond.getPrimaryDSName();
	PrimaryDatasetQO primaryDS = null;
	if(dsName.equals("%")){
	    //No condition needed. 
	    primaryDS = new PrimaryDatasetQO();
	    return primaryDS.listAllPrimaryDatasets(conn);
	}
	else{
	    primaryDS = new  PrimaryDatasetQO((new JSONArray()).put(cond));
	    return primaryDS.listPrimaryDatasets(conn);
	}
    }

}



