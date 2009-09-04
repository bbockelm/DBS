/**
 * 
 $Revision: $"
 $Id: $"
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

	int getParentBlockID ( ) {
		Integer parentBlockID = null;
               	if (!JSONObject.NULL.equals(this.get("PARENT_BLOCK_ID"))) {
                       	parentBlockID = (Integer) this.get("PARENT_BLOCK_ID");
               	}
                return parentBlockID;
        }
	
}