/***
 * $Id: DBSSimpleQueryObject.java,v 1.2 2009/09/21 15:21:12 yuyi Exp $
 *
 * This is the super class for simple query objects. All other simple query object will inherent from this class.
 * The insert, update, select, delete and bulk insert funtions will needed to be implemented in the sub classes.
 * @author Y. Guo
 ***/
package cms.dbs.queryobjs;

import java.sql.PreparedStatement;
import org.json.JSONObject;
import org.json.JSONArray;
import cms.dbs.commons.exceptions.DBSException;
import cms.dbs.commons.utils.DBSSrvcConfig;

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

