/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : PATH_PROCESS_CONFIGS
*/

package cms.dbs.dataobjs;

public class PathProcessConfig extends JSONObject {

	public PathProcessConfig ( ) {

	}

        public PathProcessConfig ( int pathProcessConfID, int pathID, int processConfigID )  {
		
                this.putOnce("PATH_PROCESS_CONF_ID", (Integer) pathProcessConfID );
                this.putOnce("PATH_ID", (Integer) pathID );
                this.putOnce("PROCESS_CONFIG_ID", (Integer) processConfigID );
        }

	int getProcessConfigID ( ) {
		Integer processConfigID = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESS_CONFIG_ID"))) {
                       	processConfigID = (Integer) this.get("PROCESS_CONFIG_ID");
               	}
                return processConfigID;
        }
	
}