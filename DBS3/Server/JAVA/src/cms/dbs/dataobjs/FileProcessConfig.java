/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getFileProcessConfigID ( ) {
		int fileProcessConfigID = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_PROCESS_CONFIG_ID"))) {
                       	fileProcessConfigID = (Integer) this.get("FILE_PROCESS_CONFIG_ID");
               	}
                return fileProcessConfigID;
        }
	
	int getFileID ( ) {
		int fileID = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_ID"))) {
                       	fileID = (Integer) this.get("FILE_ID");
               	}
                return fileID;
        }
	
	int getProcessConfigID ( ) {
		int processConfigID = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESS_CONFIG_ID"))) {
                       	processConfigID = (Integer) this.get("PROCESS_CONFIG_ID");
               	}
                return processConfigID;
        }
	
}