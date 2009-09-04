/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getBlockID ( ) {
		int blockID = null;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_ID"))) {
                       	blockID = (Integer) this.get("BLOCK_ID");
               	}
                return blockID;
        }
	
	String getBlockName ( ) {
		String blockName = null;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_NAME"))) {
                       	blockName = (String) this.get("BLOCK_NAME");
               	}
                return blockName;
        }
	
	int getPathID ( ) {
		int pathID = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_ID"))) {
                       	pathID = (Integer) this.get("PATH_ID");
               	}
                return pathID;
        }
	
	int getOpenForWriting ( ) {
		int openForWriting = null;
               	if (!JSONObject.NULL.equals(this.get("OPEN_FOR_WRITING"))) {
                       	openForWriting = (Integer) this.get("OPEN_FOR_WRITING");
               	}
                return openForWriting;
        }
	
	int getOriginSite ( ) {
		int originSite = null;
               	if (!JSONObject.NULL.equals(this.get("ORIGIN_SITE"))) {
                       	originSite = (Integer) this.get("ORIGIN_SITE");
               	}
                return originSite;
        }
	
	int getBlockSize ( ) {
		int blockSize = null;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_SIZE"))) {
                       	blockSize = (Integer) this.get("BLOCK_SIZE");
               	}
                return blockSize;
        }
	
	int getFileCount ( ) {
		int fileCount = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_COUNT"))) {
                       	fileCount = (Integer) this.get("FILE_COUNT");
               	}
                return fileCount;
        }
	
	int getCreationDate ( ) {
		int creationDate = null;
               	if (!JSONObject.NULL.equals(this.get("CREATION_DATE"))) {
                       	creationDate = (Integer) this.get("CREATION_DATE");
               	}
                return creationDate;
        }
	
	String getCreateBy ( ) {
		String createBy = null;
               	if (!JSONObject.NULL.equals(this.get("CREATE_BY"))) {
                       	createBy = (String) this.get("CREATE_BY");
               	}
                return createBy;
        }
	
	int getLastModificationDate ( ) {
		int lastModificationDate = null;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFICATION_DATE"))) {
                       	lastModificationDate = (Integer) this.get("LAST_MODIFICATION_DATE");
               	}
                return lastModificationDate;
        }
	
	String getLastModifiedBy ( ) {
		String lastModifiedBy = null;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFIED_BY"))) {
                       	lastModifiedBy = (String) this.get("LAST_MODIFIED_BY");
               	}
                return lastModifiedBy;
        }
	
}