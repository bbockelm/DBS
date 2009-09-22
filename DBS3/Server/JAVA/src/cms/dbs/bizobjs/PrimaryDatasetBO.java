/***
 * $Id: PrimaryDatasetBO.java,v 1.2 2009/09/21 15:07:15 yuyi Exp $
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

    public void commitConnection() throws Exception{
	if(conn != null) conn.commit();
    }

    public JSONArray getPrimaryDatasets(PrimaryDataset cond) throws Exception{
	PrimaryDatasetQO primaryDS = new  PrimaryDatasetQO();
	return primaryDS.listPrimaryDatasets(conn, cond);
	
    }

   public void insertPrimaryDataset(PrimaryDataset cond) throws Exception{
	PrimaryDatasetQO primaryDS = new  PrimaryDatasetQO();
	primaryDS.putPrimaryDataset(conn, cond);
	//conn.commit(); 
   }

}



