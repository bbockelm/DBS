/**
 * 
 $Revision: 1.5 $"
 $Id: Block.java,v 1.5 2009/10/06 20:22:18 afaq Exp $"
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

	public Block ( int blockID, String blockName) throws Exception  {

                this.putOnce("BLOCK_ID", blockID );
                this.putOnce("BLOCK_NAME", blockName );
        }

	public void setBlockID (int blockID) throws Exception {
 		this.put( "BLOCK_ID", blockID );
	}
	
	public void setBlockName (String blockName) throws Exception {
 		this.put( "BLOCK_NAME", blockName );
	}
	
	public void setDatasetID (int datasetID) throws Exception {
 		this.put( "DATASET_ID", datasetID );
	}
	
	public void setOpenForWriting (int openForWriting) throws Exception {
 		this.put( "OPEN_FOR_WRITING", openForWriting );
	}
	
	public void setOriginSite (int originSite) throws Exception {
 		this.put( "ORIGIN_SITE", originSite );
	}
	
	public void setBlockSize (int blockSize) throws Exception {
 		this.put( "BLOCK_SIZE", blockSize );
	}
	
	public void setFileCount (int fileCount) throws Exception {
 		this.put( "FILE_COUNT", fileCount );
	}
	
	public void setCreationDate (int creationDate) throws Exception {
 		this.put( "CREATION_DATE", creationDate );
	}
	
	public void setCreateBy (String createBy) throws Exception {
 		this.put( "CREATE_BY", createBy );
	}
	
	public void setLastModificationDate (int lastModificationDate) throws Exception {
 		this.put( "LAST_MODIFICATION_DATE", lastModificationDate );
	}
	
	public void setLastModifiedBy (String lastModifiedBy) throws Exception {
 		this.put( "LAST_MODIFIED_BY", lastModifiedBy );
	}
	
	public int getBlockID ( )  throws Exception {
		int blockID = 0;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_ID"))) {
                       	blockID = this.getInt("BLOCK_ID");
               	}
                return blockID;
        }
	
	public String getBlockName ( )  throws Exception {
		String blockName = null;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_NAME"))) {
                       	blockName = this.getString("BLOCK_NAME");
               	}
                return blockName;
        }
	
	public int getDatasetID ( )  throws Exception {
		int datasetID = 0;
               	if (!JSONObject.NULL.equals(this.get("DATASET_ID"))) {
                       	datasetID = this.getInt("DATASET_ID");
               	}
                return datasetID;
        }
	
	public int getOpenForWriting ( )  throws Exception {
		int openForWriting = 0;
               	if (!JSONObject.NULL.equals(this.get("OPEN_FOR_WRITING"))) {
                       	openForWriting = this.getInt("OPEN_FOR_WRITING");
               	}
                return openForWriting;
        }
	
	public int getOriginSite ( )  throws Exception {
		int originSite = 0;
               	if (!JSONObject.NULL.equals(this.get("ORIGIN_SITE"))) {
                       	originSite = this.getInt("ORIGIN_SITE");
               	}
                return originSite;
        }
	
	public int getBlockSize ( )  throws Exception {
		int blockSize = 0;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_SIZE"))) {
                       	blockSize = this.getInt("BLOCK_SIZE");
               	}
                return blockSize;
        }
	
	public int getFileCount ( )  throws Exception {
		int fileCount = 0;
               	if (!JSONObject.NULL.equals(this.get("FILE_COUNT"))) {
                       	fileCount = this.getInt("FILE_COUNT");
               	}
                return fileCount;
        }
	
	public int getCreationDate ( )  throws Exception {
		int creationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("CREATION_DATE"))) {
                       	creationDate = this.getInt("CREATION_DATE");
               	}
                return creationDate;
        }
	
	public String getCreateBy ( )  throws Exception {
		String createBy = null;
               	if (!JSONObject.NULL.equals(this.get("CREATE_BY"))) {
                       	createBy = this.getString("CREATE_BY");
               	}
                return createBy;
        }
	
	public int getLastModificationDate ( )  throws Exception {
		int lastModificationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFICATION_DATE"))) {
                       	lastModificationDate = this.getInt("LAST_MODIFICATION_DATE");
               	}
                return lastModificationDate;
        }
	
	public String getLastModifiedBy ( )  throws Exception {
		String lastModifiedBy = null;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFIED_BY"))) {
                       	lastModifiedBy = this.getString("LAST_MODIFIED_BY");
               	}
                return lastModifiedBy;
        }
	
}
