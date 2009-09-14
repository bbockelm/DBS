/***
 * $Id$
 *
 * This is the top level class for business objects.
 * @author Y. Guo
 ***/
package cms.dbs.bizobjs;

import java.sql.Connection;
import cms.dbs.commons.db;
import cms.dbs.commons.utils;

public class DBSBusinessObject{

    public DBSBusinessObject() {
    }
    
    protected Connection getConnection() throws Exception{
	DBSSrvcConfig config = DBSSrvcConfig.getInstance();
	return DBManagement.getConnection(config.getDbDriver(),
	    config.getDbURL(), config.getDbUserName(), config.getDbUserPasswd());
    }

} 


