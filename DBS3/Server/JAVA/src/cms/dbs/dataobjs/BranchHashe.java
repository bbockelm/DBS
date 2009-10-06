/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : BRANCH_HASHES
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class BranchHashe extends JSONObject  {

	public BranchHashe ( ) {

	}
		
        public BranchHashe ( int branchHashID, String hash, String content ) throws Exception  {
		
                this.putOnce("BRANCH_HASH_ID", branchHashID );
                this.putOnce("HASH", hash );
                this.putOnce("CONTENT", content );
        }

	int getBranchHashID ( )  throws Exception {
		int branchHashID = 0;
               	if (!JSONObject.NULL.equals(this.get("BRANCH_HASH_ID"))) {
                       	branchHashID = this.getInt("BRANCH_HASH_ID");
               	}
                return branchHashID;
        }
	
	String getHash ( )  throws Exception {
		String hash = null;
               	if (!JSONObject.NULL.equals(this.get("HASH"))) {
                       	hash = this.getString("HASH");
               	}
                return hash;
        }
	
	String getContent ( )  throws Exception {
		String content = null;
               	if (!JSONObject.NULL.equals(this.get("CONTENT"))) {
                       	content = this.getString("CONTENT");
               	}
                return content;
        }
	
}