/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : BLOCK_STORAGE_ELEMENTS
*/

package cms.dbs.dataobjs;

public class BlockStorageElement extends JSONObject {

	public BlockStorageElement ( ) {

	}

        public BlockStorageElement ( int blockSeID, int seID, int blockID )  {
		
                this.putOnce("BLOCK_SE_ID", (Integer) blockSeID );
                this.putOnce("SE_ID", (Integer) seID );
                this.putOnce("BLOCK_ID", (Integer) blockID );
        }

	int getBlockID ( ) {
		Integer blockID = null;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_ID"))) {
                       	blockID = (Integer) this.get("BLOCK_ID");
               	}
                return blockID;
        }
	
}