/**
 * 
 $Revision: $"
 $Id: $"
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

	int getParentFileID ( ) {
		Integer parentFileID = null;
               	if (!JSONObject.NULL.equals(this.get("PARENT_FILE_ID"))) {
                       	parentFileID = (Integer) this.get("PARENT_FILE_ID");
               	}
                return parentFileID;
        }
	
}