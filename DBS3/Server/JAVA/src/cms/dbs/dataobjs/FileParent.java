/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : FILE_PARENTS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class FileParent extends JSONObject  {

	public FileParent ( ) {

	}
		
        public FileParent ( int fileParentID, int thisFileID, int parentFileID ) throws Exception  {
		
                this.putOnce("FILE_PARENT_ID", (Integer) fileParentID );
                this.putOnce("THIS_FILE_ID", (Integer) thisFileID );
                this.putOnce("PARENT_FILE_ID", (Integer) parentFileID );
        }

	int getFileParentID ( )  throws Exception {
		int fileParentID = 0;
               	if (!JSONObject.NULL.equals(this.get("FILE_PARENT_ID"))) {
                       	fileParentID = (Integer) this.get("FILE_PARENT_ID");
               	}
                return fileParentID;
        }
	
	int getThisFileID ( )  throws Exception {
		int thisFileID = 0;
               	if (!JSONObject.NULL.equals(this.get("THIS_FILE_ID"))) {
                       	thisFileID = (Integer) this.get("THIS_FILE_ID");
               	}
                return thisFileID;
        }
	
	int getParentFileID ( )  throws Exception {
		int parentFileID = 0;
               	if (!JSONObject.NULL.equals(this.get("PARENT_FILE_ID"))) {
                       	parentFileID = (Integer) this.get("PARENT_FILE_ID");
               	}
                return parentFileID;
        }
	
}