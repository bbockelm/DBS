/***
 * $Id: PrimaryDatasetQO.java,v 1.1 2009/09/09 15:52:41 yuyi Exp $
 *
 * This is the class for primary dataset query objects.
 * @author Y. Guo
 ***/
package cms.dbs.queryobjs.impl.objects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import cms.dbs.commons.db;
import cms.dbs.dataobjs.PrimaryDataset;
import cms.dbs.dataobjs.PrimaryDSType;

public class PrimaryDatasetQO extends  DBSSimpleQueryObject{

    public PrimaryDatasetQO(JSONArray cond){
        super(cond);
    }

    public PrimaryDatasetQO(){
            super();
    }

    public JSONArray listiAllPrimaryDatasets(Connection conn) throws Exception{
        String sql = "SELECT P.PRIMARY_DS_ID, P.PRIMARY_DS_NAME, P.PRIMARY_DS_TYPE_ID, PT.PRIMARY_DS_TYPE," +
            "P.CREATION_DATE, P.CREATE_BY FROM " +
             schemaOwner + "PRIMARY_DATASETS P" +
            "JOIN " + schemaOwner + "PRIMARY_DS_TYPES PT ON " +
            " PT.PRIMARY_DS_TYPE_ID=P.PRIMARY_DS_TYPE_ID";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = DBManagement.getStatement(conn, sql);
            rs =  ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("P.PRIMARY_DS_NAME");
                int pID = rs.getInt("P.PRIMARY_DS_ID");
                int typeID = rs.getInt("P.PRIMARY_DS_TYPE_ID");
                String typeName = rs.getString("PT.PRIMARY_DS_TYPE");
                int dt = rs.getInt("P.CREATION_DATE");
                String by = rs.getString("P.CREATE_BY");
                restlt.put(PrimaryDataset(pID, name, PrimaryDSType(typeID, typeName), dt,by));
            } finally {
               if (rs != null) rs.close();
               if (ps != null) ps.close();
             }
           return result;

    }

    public JSONArray listPrimaryDataset(Connection conn) throws Exception{
	int condSize = 0;
        condSize = cond.length();
	PreparedStatement ps = null; 
        ResultSet rs = null;
	String sql = "SELECT P.PRIMARY_DS_ID, P.PRIMARY_DS_NAME, P.PRIMARY_DS_TYPE_ID, PT.PRIMARY_DS_TYPE," +
	    "P.CREATION_DATE, P.CREATE_BY FROM " +
	    schemaOwner + "PRIMARY_DATASETS P" +
	    "JOIN " + schemaOwner + "PRIMARY_DS_TYPES PT ON " +
	    " PT.PRIMARY_DS_TYPE_ID=P.PRIMARY_DS_TYPE_ID WHERE ";
	for (int i=0; i<condSize; i++){
	    JSONObject c = cond.getJSONObject(i);
	    if (i=0) sql += " P.PRIMARY_DS_NAME = ?";
	    else sql +=  " or P.PRIMARY_DS_NAME = ?";
	}
        PreparedStatement ps = null;
	ResultSet rs = null;
	try{
	    ps = DBManagement.getStatement(conn, sql);
	    for (int i=1; i<=condSize; i++){
		JSONObject c = cond.getJSONObject(i);
		ps.setString(i, c.get("PRIMARY_DS_NAME"));
	    }
	    rs =  ps.executeQuery();
	    while(rs.next()){
		String name = rs.getString("P.PRIMARY_DS_NAME");
		int pID = rs.getInt("P.PRIMARY_DS_ID");
		int typeID = rs.getInt("P.PRIMARY_DS_TYPE_ID");
		String typeName = rs.getString("PT.PRIMARY_DS_TYPE");
		int dt = rs.getInt("P.CREATION_DATE");
		String by = rs.getString("P.CREATE_BY");
		restlt.put(PrimaryDataset(pID, name, PrimaryDSType(typeID, typeName), dt,by));
	    } finally {
		if (rs != null) rs.close();
		if (ps != null) ps.close();
	    }
	    return result;

    }
}
