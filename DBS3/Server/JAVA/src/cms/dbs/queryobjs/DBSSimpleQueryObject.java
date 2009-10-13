/***
 * $Id: DBSSimpleQueryObject.java,v 1.3 2009/09/22 19:06:13 yuyi Exp $
 *
 * This is the super class for simple query objects. All other simple query object will inherent from this class.
 * The insert, update, select, delete and bulk insert funtions will needed to be implemented in the sub classes.
 * @author Y. Guo
 ***/
package cms.dbs.queryobjs;

import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import org.json.JSONObject;
import org.json.JSONArray;
import cms.dbs.commons.db.DBManagement;
import cms.dbs.commons.db.SequenceManager;
import cms.dbs.commons.exceptions.DBSException;
import cms.dbs.commons.utils.DBSSrvcConfig;
import cms.dbs.commons.utils.DBSSrvcUtil;

public class DBSSimpleQueryObject{
    protected JSONArray result = null;
   // private String sql = null; construct inside the functions.
    protected PreparedStatement ps = null;
    protected String schemaOwner = null;
     
    DBSSimpleQueryObject() throws Exception{
	try {
            DBSSrvcConfig config = DBSSrvcConfig.getInstance();
            this.schemaOwner = config.getSchemaOwner();
        } catch (DBSException ex) {
            throw ex;
        }
    }
    public void insertTable(Connection conn, JSONObject cond, String tableName) throws Exception{
        String sql = "insert into " + schemaOwner + tableName + "(";
        ArrayList<String> list = new ArrayList<String>();
        Iterator it = cond.keys();
        while (it.hasNext()){
            String key = (String) it.next();
            if(key.endsWith("_DO")){
                JSONObject ob = cond.getJSONObject(key);
                Iterator it2 = ob.keys();
                while (it2.hasNext()){
                    String key2 = (String)it2.next();
                    if(key2.endsWith("_ID")){
                        sql += (key2 + ",");
                        list.add((String)ob.get(key));
                    }
                }
            }
            else{
                sql += (key + ",");
                list.add((String)cond.get(key));
            }
        }//end while
        sql += ") values(";
        for(int i=0; i<list.size();i++){
            if(i != 0) sql += ",?";
            else sql += "?";
        }
        sql += ")";
        PreparedStatement ps = null;
        try{
            ps = DBManagement.getStatement(conn, sql);
            for(int i=1; i<=list.size();i++){
                ps.setString(i++, list.get(i-1));
            }
            System.out.println(ps.toString());
            ps.execute();
        }catch (SQLException ex) {
            String exmsg = ex.getMessage();
            if(!exmsg.startsWith("Duplicate entry") && !exmsg.startsWith("ORA-00001: unique constraint") ) throw ex;
            else DBSSrvcUtil.writeLog( cond + " Already Exists");
        } finally {
            if (ps != null) ps.close();
        }
    }//end insertTable
    
    public String getSchemaOwner(){
        return schemaOwner;
    }

    public PreparedStatement getPreparedStatement(){
	return ps;
    }


    public JSONArray getResult(){
	return result;
    }


    public JSONArray DBSSelect(){
    //update the result using the select
	return result;
    }

    public int DBSUpdate(){
    //update the object 
	return 0;
    }

    public int DBSInsert(){
    //update the data object
	return 0;
    }

    public int DBSBulkInsert(){
    //update the object
	return 0;
    }

    public int DBSDelete(){
	return 0;
    }

}

