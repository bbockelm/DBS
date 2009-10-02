/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : BLOCK_PARENTS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class BlockParent extends JSONObject  {

	public BlockParent ( ) {

	}
		
        public BlockParent ( int blockParentID, int thisBlockID, int parentBlockID ) throws Exception  {
		
                this.putOnce("BLOCK_PARENT_ID", (Integer) blockParentID );
                this.putOnce("THIS_BLOCK_ID", (Integer) thisBlockID );
                this.putOnce("PARENT_BLOCK_ID", (Integer) parentBlockID );
        }

	int getBlockParentID ( )  throws Exception {
		int blockParentID = 0;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_PARENT_ID"))) {
                       	blockParentID = (Integer) this.get("BLOCK_PARENT_ID");
               	}
                return blockParentID;
        }
	
	int getThisBlockID ( )  throws Exception {
		int thisBlockID = 0;
               	if (!JSONObject.NULL.equals(this.get("THIS_BLOCK_ID"))) {
                       	thisBlockID = (Integer) this.get("THIS_BLOCK_ID");
               	}
                return thisBlockID;
        }
	
	int getParentBlockID ( )  throws Exception {
		int parentBlockID = 0;
               	if (!JSONObject.NULL.equals(this.get("PARENT_BLOCK_ID"))) {
                       	parentBlockID = (Integer) this.get("PARENT_BLOCK_ID");
               	}
                return parentBlockID;
        }
	
}