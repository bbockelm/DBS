/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getFileTypeID ( ) {
		int fileTypeID = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_TYPE_ID"))) {
                       	fileTypeID = (Integer) this.get("FILE_TYPE_ID");
               	}
                return fileTypeID;
        }
	
	String getFileType ( ) {
		String fileType = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_TYPE"))) {
                       	fileType = (String) this.get("FILE_TYPE");
               	}
                return fileType;
        }
	
}