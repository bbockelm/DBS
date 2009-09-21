/***
 * $Id: PrimaryDatasetQO.java,v 1.3 2009/09/14 15:03:17 yuyi Exp $
 *
 * This is the class for primary dataset query objects.
 * @author Y. Guo
 ***/
package cms.dbs.queryobjs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.json.JSONObject;
import org.json.JSONArray;
import cms.dbs.commons.db.DBManagement;
import cms.dbs.dataobjs.PrimaryDataset;
import cms.dbs.dataobjs.PrimaryDSType;

public class PrimaryDatasetQO extends  DBSSimpleQueryObject{

    public PrimaryDatasetQO(JSONArray cond) throws Exception{
        super(cond);
    }

    public PrimaryDatasetQO() throws Exception{
            super();
    }
    //list entire primary_dataset table
    public JSONArray listAllPrimaryDatasets(Connection conn) throws Exception{
        String sql = "SELECT P.PRIMARY_DS_ID, P.PRIMARY_DS_NAME, P.PRIMARY_DS_TYPE_ID, PT.PRIMARY_DS_TYPE, " +
            "P.CREATION_DATE, P.CREATE_BY FROM " +
             schemaOwner + "PRIMARY_DATASETS P " +
            "JOIN " + schemaOwner + "PRIMARY_DS_TYPES PT ON " +
            " PT.PRIMARY_DS_TYPE_ID=P.PRIMARY_DS_TYPE_ID";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = DBManagement.getStatement(conn, sql);
	    //System.out.println(ps.toString());
            rs =  ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("PRIMARY_DS_NAME");
                int pID = rs.getInt("PRIMARY_DS_ID");
                int typeID = rs.getInt("PRIMARY_DS_TYPE_ID");
                String typeName = rs.getString("PRIMARY_DS_TYPE");
                int dt = rs.getInt("CREATION_DATE");
                String by = rs.getString("CREATE_BY");
                this.result=new JSONArray();
		this.result.put(new PrimaryDataset(pID, name, new PrimaryDSType(typeID, typeName), dt, by));
            }
	}finally {
	       if (rs != null) rs.close();
               if (ps != null) ps.close();
             }
            return this.result;
    }
    //list only the primary dataset that satisfied the condition.
    public JSONArray listPrimaryDatasets(Connection conn) throws Exception{
	this.result = new JSONArray();
	int condSize = 0;
        condSize = cond.length();
	PreparedStatement ps = null; 
        ResultSet rs = null;
	String sql = "SELECT P.PRIMARY_DS_ID, P.PRIMARY_DS_NAME, P.PRIMARY_DS_TYPE_ID, PT.PRIMARY_DS_TYPE, " +
	    "P.CREATION_DATE, P.CREATE_BY FROM " +
	    schemaOwner + "PRIMARY_DATASETS P " +
	    "JOIN " + schemaOwner + "PRIMARY_DS_TYPES PT ON " +
	    " PT.PRIMARY_DS_TYPE_ID=P.PRIMARY_DS_TYPE_ID WHERE ";
	for (int i=0; i<condSize; i++){
	    PrimaryDataset c = (PrimaryDataset)cond.getJSONObject(i);
	    if (i==0){
	        if ((c.getPrimaryDSName()).indexOf('_') != -1 || (c.getPrimaryDSName()).indexOf('%') != -1)
		sql += " P.PRIMARY_DS_NAME like ?";
		else  sql += " P.PRIMARY_DS_NAME = ?";
	    }
	    else{
		if ((c.getPrimaryDSName()).indexOf('_') != -1 || (c.getPrimaryDSName()).indexOf('%') != -1)
		sql +=  " or P.PRIMARY_DS_NAME like ?";
		else sql +=  " or P.PRIMARY_DS_NAME = ?";
	    }
	}
        ps = null;
	rs = null;
	try{
	    ps = DBManagement.getStatement(conn, sql);
	    //prepare statement index starting with 1, but JSONArray index starting with 0.
	    for (int i=1; i<=condSize; i++){
		PrimaryDataset c = (PrimaryDataset)cond.getJSONObject(i-1);
		ps.setString(i, c.getPrimaryDSName());
	    }
	    //System.out.println(ps.toString());
	    rs =  ps.executeQuery();
	    while(rs.next()){
		String name = rs.getString("PRIMARY_DS_NAME");
		int pID = rs.getInt("PRIMARY_DS_ID");
		int typeID = rs.getInt("PRIMARY_DS_TYPE_ID");
		String typeName = rs.getString("PRIMARY_DS_TYPE");
		int dt = rs.getInt("CREATION_DATE");
		String by = rs.getString("CREATE_BY");
		this.result.put(new PrimaryDataset(pID, name, new PrimaryDSType(typeID, typeName), dt,by));
	    } 
	}finally {
		if (rs != null) rs.close();
		if (ps != null) ps.close();
	    }
	    return this.result;
    }
}
