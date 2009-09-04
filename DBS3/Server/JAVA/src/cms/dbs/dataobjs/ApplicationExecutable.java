/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
 *
 * Data Object from table : APPLICATION_EXECUTABLES
*/

package cms.dbs.dataobjs;

public class ApplicationExecutable extends JSONObject {

	public ApplicationExecutable ( ) {

	}

        public ApplicationExecutable ( int appExecID, String appName )  {
		
                this.putOnce("APP_EXEC_ID", (Integer) appExecID );
                this.putOnce("APP_NAME", (String) appName );
        }

	int getAppExecID ( ) {
		int appExecID = null;
               	if (!JSONObject.NULL.equals(this.get("APP_EXEC_ID"))) {
                       	appExecID = (Integer) this.get("APP_EXEC_ID");
               	}
                return appExecID;
        }
	
	String getAppName ( ) {
		String appName = null;
               	if (!JSONObject.NULL.equals(this.get("APP_NAME"))) {
                       	appName = (String) this.get("APP_NAME");
               	}
                return appName;
        }
	
}