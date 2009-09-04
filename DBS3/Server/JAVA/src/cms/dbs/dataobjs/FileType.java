/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : FILE_TYPES
*/

package cms.dbs.dataobjs;

public class FileType extends JSONObject {

	public FileType ( ) {

	}

        public FileType ( int fileTypeID, String fileType )  {
		
                this.putOnce("FILE_TYPE_ID", (Integer) fileTypeID );
                this.putOnce("FILE_TYPE", (String) fileType );
        }

	String getFileType ( ) {
		String fileType = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_TYPE"))) {
                       	fileType = (String) this.get("FILE_TYPE");
               	}
                return fileType;
        }
	
}