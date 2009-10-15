/***
 * $Id: DBSSimpleQueryObject.java,v 1.4 2009/10/13 16:05:30 yuyi Exp $
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
	//System.out.println("insert Table \n");
	//System.out.println(cond);
        String sql = "insert into " + schemaOwner + tableName + "(";
        ArrayList<String> list = new ArrayList<String>();
        Iterator it = cond.keys();
	boolean first = true;
        while (it.hasNext()){
            String key = (String) it.next();
            if(key.endsWith("_DO")){
		//System.out.println("***key:  " + key);
                JSONObject ob = cond.getJSONObject(key);
		//System.out.println(ob);
                Iterator it2 = ob.keys();
                while (it2.hasNext()){
                    String key2 = (String)it2.next();
		    //System.out.println("***key2:  " + key2);
                    if(key2.endsWith("_ID")){
			if(first){
			     sql += key2 ;
			     first = false;
			}
			else sql += (", " + key2 );
                        list.add((ob.get(key2)).toString());
			//System.out.println(list);
                    }
                }
            }
            else{
	        //System.out.println(key);
                if(first){
		    sql += key;
		    first = false;
		}
		else sql += ("," + key);
                list.add((cond.get(key)).toString());
		//System.out.println(list);
            }
        }//end while
        sql += ") values(";
        for(int i=0; i<list.size();i++){
            if(i != 0) sql += ",?";
            else sql += "?";
        }
        sql += ")";
	//System.out.println("*****sql: " + sql); 
        PreparedStatement ps = null;
        try{
            ps = DBManagement.getStatement(conn, sql);
            for(int i=1; i<list.size()+1;i++){
                ps.setString(i, list.get(i-1));
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

