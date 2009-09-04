/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
 *
 * Data Object from table : FILE_PARENTS
*/

package cms.dbs.dataobjs;

public class FileParent extends JSONObject {

	public FileParent ( ) {

	}

        public FileParent ( int fileParentID, int thisFileID, int parentFileID )  {
		
                this.putOnce("FILE_PARENT_ID", (Integer) fileParentID );
                this.putOnce("THIS_FILE_ID", (Integer) thisFileID );
                this.putOnce("PARENT_FILE_ID", (Integer) parentFileID );
        }

	int getFileParentID ( ) {
		int fileParentID = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_PARENT_ID"))) {
                       	fileParentID = (Integer) this.get("FILE_PARENT_ID");
               	}
                return fileParentID;
        }
	
	int getThisFileID ( ) {
		int thisFileID = null;
               	if (!JSONObject.NULL.equals(this.get("THIS_FILE_ID"))) {
                       	thisFileID = (Integer) this.get("THIS_FILE_ID");
               	}
                return thisFileID;
        }
	
	int getParentFileID ( ) {
		int parentFileID = null;
               	if (!JSONObject.NULL.equals(this.get("PARENT_FILE_ID"))) {
                       	parentFileID = (Integer) this.get("PARENT_FILE_ID");
               	}
                return parentFileID;
        }
	
}