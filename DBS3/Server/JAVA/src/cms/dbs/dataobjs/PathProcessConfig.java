/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getPathProcessConfID ( ) {
		int pathProcessConfID = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_PROCESS_CONF_ID"))) {
                       	pathProcessConfID = (Integer) this.get("PATH_PROCESS_CONF_ID");
               	}
                return pathProcessConfID;
        }
	
	int getPathID ( ) {
		int pathID = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_ID"))) {
                       	pathID = (Integer) this.get("PATH_ID");
               	}
                return pathID;
        }
	
	int getProcessConfigID ( ) {
		int processConfigID = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESS_CONFIG_ID"))) {
                       	processConfigID = (Integer) this.get("PROCESS_CONFIG_ID");
               	}
                return processConfigID;
        }
	
}