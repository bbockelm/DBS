/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : BLOCKS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class Block extends JSONObject  {

	public Block ( ) {

	}
		
        public Block ( int blockID, String blockName, int datasetID, int openForWriting, int originSite, int blockSize, int fileCount, int creationDate, String createBy, int lastModificationDate, String lastModifiedBy ) throws Exception  {
		
                this.putOnce("BLOCK_ID", (Integer) blockID );
                this.putOnce("BLOCK_NAME", (String) blockName );
                this.putOnce("DATASET_ID", (Integer) datasetID );
                this.putOnce("OPEN_FOR_WRITING", (Integer) openForWriting );
                this.putOnce("ORIGIN_SITE", (Integer) originSite );
                this.putOnce("BLOCK_SIZE", (Integer) blockSize );
                this.putOnce("FILE_COUNT", (Integer) fileCount );
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("CREATE_BY", (String) createBy );
                this.putOnce("LAST_MODIFICATION_DATE", (Integer) lastModificationDate );
                this.putOnce("LAST_MODIFIED_BY", (String) lastModifiedBy );
        }

	int getBlockID ( )  throws Exception {
		int blockID = 0;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_ID"))) {
                       	blockID = (Integer) this.get("BLOCK_ID");
               	}
                return blockID;
        }
	
	String getBlockName ( )  throws Exception {
		String blockName = null;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_NAME"))) {
                       	blockName = (String) this.get("BLOCK_NAME");
               	}
                return blockName;
        }
	
	int getDatasetID ( )  throws Exception {
		int datasetID = 0;
               	if (!JSONObject.NULL.equals(this.get("DATASET_ID"))) {
                       	datasetID = (Integer) this.get("DATASET_ID");
               	}
                return datasetID;
        }
	
	int getOpenForWriting ( )  throws Exception {
		int openForWriting = 0;
               	if (!JSONObject.NULL.equals(this.get("OPEN_FOR_WRITING"))) {
                       	openForWriting = (Integer) this.get("OPEN_FOR_WRITING");
               	}
                return openForWriting;
        }
	
	int getOriginSite ( )  throws Exception {
		int originSite = 0;
               	if (!JSONObject.NULL.equals(this.get("ORIGIN_SITE"))) {
                       	originSite = (Integer) this.get("ORIGIN_SITE");
               	}
                return originSite;
        }
	
	int getBlockSize ( )  throws Exception {
		int blockSize = 0;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_SIZE"))) {
                       	blockSize = (Integer) this.get("BLOCK_SIZE");
               	}
                return blockSize;
        }
	
	int getFileCount ( )  throws Exception {
		int fileCount = 0;
               	if (!JSONObject.NULL.equals(this.get("FILE_COUNT"))) {
                       	fileCount = (Integer) this.get("FILE_COUNT");
               	}
                return fileCount;
        }
	
	int getCreationDate ( )  throws Exception {
		int creationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("CREATION_DATE"))) {
                       	creationDate = (Integer) this.get("CREATION_DATE");
               	}
                return creationDate;
        }
	
	String getCreateBy ( )  throws Exception {
		String createBy = null;
               	if (!JSONObject.NULL.equals(this.get("CREATE_BY"))) {
                       	createBy = (String) this.get("CREATE_BY");
               	}
                return createBy;
        }
	
	int getLastModificationDate ( )  throws Exception {
		int lastModificationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFICATION_DATE"))) {
                       	lastModificationDate = (Integer) this.get("LAST_MODIFICATION_DATE");
               	}
                return lastModificationDate;
        }
	
	String getLastModifiedBy ( )  throws Exception {
		String lastModifiedBy = null;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFIED_BY"))) {
                       	lastModifiedBy = (String) this.get("LAST_MODIFIED_BY");
               	}
                return lastModifiedBy;
        }
	
}