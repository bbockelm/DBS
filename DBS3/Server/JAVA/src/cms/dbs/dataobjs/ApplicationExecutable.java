/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : APPLICATION_EXECUTABLES
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class ApplicationExecutable extends JSONObject  {

	public ApplicationExecutable ( ) {

	}
		
        public ApplicationExecutable ( int appExecID, String appName ) throws Exception  {
		
                this.putOnce("APP_EXEC_ID", appExecID );
                this.putOnce("APP_NAME", appName );
        }

	int getAppExecID ( )  throws Exception {
		int appExecID = 0;
               	if (!JSONObject.NULL.equals(this.get("APP_EXEC_ID"))) {
                       	appExecID = this.getInt("APP_EXEC_ID");
               	}
                return appExecID;
        }
	
	String getAppName ( )  throws Exception {
		String appName = null;
               	if (!JSONObject.NULL.equals(this.get("APP_NAME"))) {
                       	appName = this.getString("APP_NAME");
               	}
                return appName;
        }
	
}