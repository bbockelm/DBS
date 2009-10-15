/***
 * $Id$
 *
 * This is the class for dataset business objects.
 * @author Y. Guo  Oct-13-09
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
import cms.dbs.dataobjs.Dataset;
import cms.dbs.queryobjs.DatasetQO;

public class DatasetBO extends DBSBusinessObject{
    
    public DatasetBO() throws Exception {
	super();
    }

    public JSONArray getDatasets(Connection conn, Dataset cond) throws Exception{
	DatasetQO dataset = new  DatasetQO();
	return dataset.listDatasets(conn, cond);
	
    }

   public int insertDataset(Connection conn, Dataset cond) throws Exception{
	DatasetQO dataset = new  DatasetQO();
	int id = dataset.putDataset(conn, cond);
	conn.commit(); 
	return id;
   }

}
