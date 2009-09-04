/**
 * 
 $Revision: $"
 $Id: $"
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

	String getAppName ( ) {
		String appName = null;
               	if (!JSONObject.NULL.equals(this.get("APP_NAME"))) {
                       	appName = (String) this.get("APP_NAME");
               	}
                return appName;
        }
	
}