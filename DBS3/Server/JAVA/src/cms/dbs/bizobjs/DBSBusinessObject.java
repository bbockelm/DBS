/***
 * $Id: DBSBusinessObject.java,v 1.1 2009/09/14 14:57:15 yuyi Exp $
 *
 * This is the top level class for business objects.
 * @author Y. Guo
 ***/
package cms.dbs.bizobjs;

import java.sql.Connection;
import cms.dbs.commons.db.DBManagement;
import cms.dbs.commons.utils.DBSSrvcConfig;

public class DBSBusinessObject{

    public DBSBusinessObject() {
    }
    
    protected Connection getConnection() throws Exception{
	DBSSrvcConfig config = DBSSrvcConfig.getInstance();
	return DBManagement.getConnection(config.getDbDriver(),
	    config.getDbURL(), config.getDbUserName(), config.getDbUserPasswd());
    }

} 


