/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : BLOCK_STORAGE_ELEMENTS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class BlockStorageElement extends JSONObject  {

	public BlockStorageElement ( ) {

	}
		
        public BlockStorageElement ( int blockSeID, int seID, int blockID ) throws Exception  {
		
                this.putOnce("BLOCK_SE_ID", (Integer) blockSeID );
                this.putOnce("SE_ID", (Integer) seID );
                this.putOnce("BLOCK_ID", (Integer) blockID );
        }

	int getBlockSeID ( )  throws Exception {
		int blockSeID = 0;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_SE_ID"))) {
                       	blockSeID = (Integer) this.get("BLOCK_SE_ID");
               	}
                return blockSeID;
        }
	
	int getSeID ( )  throws Exception {
		int seID = 0;
               	if (!JSONObject.NULL.equals(this.get("SE_ID"))) {
                       	seID = (Integer) this.get("SE_ID");
               	}
                return seID;
        }
	
	int getBlockID ( )  throws Exception {
		int blockID = 0;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_ID"))) {
                       	blockID = (Integer) this.get("BLOCK_ID");
               	}
                return blockID;
        }
	
}