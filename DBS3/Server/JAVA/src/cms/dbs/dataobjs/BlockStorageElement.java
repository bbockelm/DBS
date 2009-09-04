/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getBlockSeID ( ) {
		int blockSeID = null;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_SE_ID"))) {
                       	blockSeID = (Integer) this.get("BLOCK_SE_ID");
               	}
                return blockSeID;
        }
	
	int getSeID ( ) {
		int seID = null;
               	if (!JSONObject.NULL.equals(this.get("SE_ID"))) {
                       	seID = (Integer) this.get("SE_ID");
               	}
                return seID;
        }
	
	int getBlockID ( ) {
		int blockID = null;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_ID"))) {
                       	blockID = (Integer) this.get("BLOCK_ID");
               	}
                return blockID;
        }
	
}