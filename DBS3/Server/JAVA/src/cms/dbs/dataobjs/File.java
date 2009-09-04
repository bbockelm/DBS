/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : FILES
*/

package cms.dbs.dataobjs;

public class File extends JSONObject {

	public File ( ) {

	}

        public File ( int fileID, String logicalFileName, int isFileValid, int pathID, int blockID, int fileTypeID, String checkSum, int eventCount, int fileSize, int branchHashID, String adler32, String md5, float autoCrossSection, int creationDate, String createBy, int lastmodificationdate, String lastModifiedBy )  {
		
                this.putOnce("FILE_ID", (Integer) fileID );
                this.putOnce("LOGICAL_FILE_NAME", (String) logicalFileName );
                this.putOnce("IS_FILE_VALID", (Integer) isFileValid );
                this.putOnce("PATH_ID", (Integer) pathID );
                this.putOnce("BLOCK_ID", (Integer) blockID );
                this.putOnce("FILE_TYPE_ID", (Integer) fileTypeID );
                this.putOnce("CHECK_SUM", (String) checkSum );
                this.putOnce("EVENT_COUNT", (Integer) eventCount );
                this.putOnce("FILE_SIZE", (Integer) fileSize );
                this.putOnce("BRANCH_HASH_ID", (Integer) branchHashID );
                this.putOnce("ADLER32", (String) adler32 );
                this.putOnce("MD5", (String) md5 );
                this.putOnce("AUTO_CROSS_SECTION", (Float) autoCrossSection );
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("CREATE_BY", (String) createBy );
                this.putOnce("LASTMODIFICATIONDATE", (Integer) lastmodificationdate );
                this.putOnce("LAST_MODIFIED_BY", (String) lastModifiedBy );
        }

	String getLastModifiedBy ( ) {
		String lastModifiedBy = null;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFIED_BY"))) {
                       	lastModifiedBy = (String) this.get("LAST_MODIFIED_BY");
               	}
                return lastModifiedBy;
        }
	
}