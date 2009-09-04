/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : FILE_PROCESS_CONFIGS
*/

package cms.dbs.dataobjs;

public class FileProcessConfig extends JSONObject {

	public FileProcessConfig ( ) {

	}

        public FileProcessConfig ( int fileProcessConfigID, int fileID, int processConfigID )  {
		
                this.putOnce("FILE_PROCESS_CONFIG_ID", (Integer) fileProcessConfigID );
                this.putOnce("FILE_ID", (Integer) fileID );
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