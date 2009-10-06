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
		
                this.putOnce("FILE_PARENT_ID", fileParentID );
                this.putOnce("THIS_FILE_ID", thisFileID );
                this.putOnce("PARENT_FILE_ID", parentFileID );
        }

	int getFileParentID ( )  throws Exception {
		int fileParentID = 0;
               	if (!JSONObject.NULL.equals(this.get("FILE_PARENT_ID"))) {
                       	fileParentID = this.getInt("FILE_PARENT_ID");
               	}
                return fileParentID;
        }
	
	int getThisFileID ( )  throws Exception {
		int thisFileID = 0;
               	if (!JSONObject.NULL.equals(this.get("THIS_FILE_ID"))) {
                       	thisFileID = this.getInt("THIS_FILE_ID");
               	}
                return thisFileID;
        }
	
	int getParentFileID ( )  throws Exception {
		int parentFileID = 0;
               	if (!JSONObject.NULL.equals(this.get("PARENT_FILE_ID"))) {
                       	parentFileID = this.getInt("PARENT_FILE_ID");
               	}
                return parentFileID;
        }
	
}