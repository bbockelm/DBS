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
		
                this.putOnce("BLOCK_ID", blockID );
                this.putOnce("BLOCK_NAME", blockName );
                this.putOnce("DATASET_ID", datasetID );
                this.putOnce("OPEN_FOR_WRITING", openForWriting );
                this.putOnce("ORIGIN_SITE", originSite );
                this.putOnce("BLOCK_SIZE", blockSize );
                this.putOnce("FILE_COUNT", fileCount );
                this.putOnce("CREATION_DATE", creationDate );
                this.putOnce("CREATE_BY", createBy );
                this.putOnce("LAST_MODIFICATION_DATE", lastModificationDate );
                this.putOnce("LAST_MODIFIED_BY", lastModifiedBy );
        }

	int getBlockID ( )  throws Exception {
		int blockID = 0;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_ID"))) {
                       	blockID = this.getInt("BLOCK_ID");
               	}
                return blockID;
        }
	
	String getBlockName ( )  throws Exception {
		String blockName = null;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_NAME"))) {
                       	blockName = this.getString("BLOCK_NAME");
               	}
                return blockName;
        }
	
	int getDatasetID ( )  throws Exception {
		int datasetID = 0;
               	if (!JSONObject.NULL.equals(this.get("DATASET_ID"))) {
                       	datasetID = this.getInt("DATASET_ID");
               	}
                return datasetID;
        }
	
	int getOpenForWriting ( )  throws Exception {
		int openForWriting = 0;
               	if (!JSONObject.NULL.equals(this.get("OPEN_FOR_WRITING"))) {
                       	openForWriting = this.getInt("OPEN_FOR_WRITING");
               	}
                return openForWriting;
        }
	
	int getOriginSite ( )  throws Exception {
		int originSite = 0;
               	if (!JSONObject.NULL.equals(this.get("ORIGIN_SITE"))) {
                       	originSite = this.getInt("ORIGIN_SITE");
               	}
                return originSite;
        }
	
	int getBlockSize ( )  throws Exception {
		int blockSize = 0;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_SIZE"))) {
                       	blockSize = this.getInt("BLOCK_SIZE");
               	}
                return blockSize;
        }
	
	int getFileCount ( )  throws Exception {
		int fileCount = 0;
               	if (!JSONObject.NULL.equals(this.get("FILE_COUNT"))) {
                       	fileCount = this.getInt("FILE_COUNT");
               	}
                return fileCount;
        }
	
	int getCreationDate ( )  throws Exception {
		int creationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("CREATION_DATE"))) {
                       	creationDate = this.getInt("CREATION_DATE");
               	}
                return creationDate;
        }
	
	String getCreateBy ( )  throws Exception {
		String createBy = null;
               	if (!JSONObject.NULL.equals(this.get("CREATE_BY"))) {
                       	createBy = this.getString("CREATE_BY");
               	}
                return createBy;
        }
	
	int getLastModificationDate ( )  throws Exception {
		int lastModificationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFICATION_DATE"))) {
                       	lastModificationDate = this.getInt("LAST_MODIFICATION_DATE");
               	}
                return lastModificationDate;
        }
	
	String getLastModifiedBy ( )  throws Exception {
		String lastModifiedBy = null;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFIED_BY"))) {
                       	lastModifiedBy = this.getString("LAST_MODIFIED_BY");
               	}
                return lastModifiedBy;
        }
	
}