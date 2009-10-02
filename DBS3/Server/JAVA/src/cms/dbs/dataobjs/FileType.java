/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : FILE_TYPES
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class FileType extends JSONObject  {

	public FileType ( ) {

	}
		
        public FileType ( int fileTypeID, String fileType ) throws Exception  {
		
                this.putOnce("FILE_TYPE_ID", (Integer) fileTypeID );
                this.putOnce("FILE_TYPE", (String) fileType );
        }

	int getFileTypeID ( )  throws Exception {
		int fileTypeID = 0;
               	if (!JSONObject.NULL.equals(this.get("FILE_TYPE_ID"))) {
                       	fileTypeID = (Integer) this.get("FILE_TYPE_ID");
               	}
                return fileTypeID;
        }
	
	String getFileType ( )  throws Exception {
		String fileType = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_TYPE"))) {
                       	fileType = (String) this.get("FILE_TYPE");
               	}
                return fileType;
        }
	
}