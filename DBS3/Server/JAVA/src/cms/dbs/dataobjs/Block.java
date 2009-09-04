/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : BLOCKS
*/

package cms.dbs.dataobjs;

public class Block extends JSONObject {

	public Block ( ) {

	}

        public Block ( int blockID, String blockName, int pathID, int openForWriting, int originSite, int blockSize, int fileCount, int creationDate, String createBy, int lastModificationDate, String lastModifiedBy )  {
		
                this.putOnce("BLOCK_ID", (Integer) blockID );
                this.putOnce("BLOCK_NAME", (String) blockName );
                this.putOnce("PATH_ID", (Integer) pathID );
                this.putOnce("OPEN_FOR_WRITING", (Integer) openForWriting );
                this.putOnce("ORIGIN_SITE", (Integer) originSite );
                this.putOnce("BLOCK_SIZE", (Integer) blockSize );
                this.putOnce("FILE_COUNT", (Integer) fileCount );
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("CREATE_BY", (String) createBy );
                this.putOnce("LAST_MODIFICATION_DATE", (Integer) lastModificationDate );
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