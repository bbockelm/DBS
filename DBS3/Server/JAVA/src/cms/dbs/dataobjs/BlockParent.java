/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
 *
 * Data Object from table : BLOCK_PARENTS
*/

package cms.dbs.dataobjs;

public class BlockParent extends JSONObject {

	public BlockParent ( ) {

	}

        public BlockParent ( int blockParentID, int thisBlockID, int parentBlockID )  {
		
                this.putOnce("BLOCK_PARENT_ID", (Integer) blockParentID );
                this.putOnce("THIS_BLOCK_ID", (Integer) thisBlockID );
                this.putOnce("PARENT_BLOCK_ID", (Integer) parentBlockID );
        }

	int getBlockParentID ( ) {
		int blockParentID = null;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_PARENT_ID"))) {
                       	blockParentID = (Integer) this.get("BLOCK_PARENT_ID");
               	}
                return blockParentID;
        }
	
	int getThisBlockID ( ) {
		int thisBlockID = null;
               	if (!JSONObject.NULL.equals(this.get("THIS_BLOCK_ID"))) {
                       	thisBlockID = (Integer) this.get("THIS_BLOCK_ID");
               	}
                return thisBlockID;
        }
	
	int getParentBlockID ( ) {
		int parentBlockID = null;
               	if (!JSONObject.NULL.equals(this.get("PARENT_BLOCK_ID"))) {
                       	parentBlockID = (Integer) this.get("PARENT_BLOCK_ID");
               	}
                return parentBlockID;
        }
	
}