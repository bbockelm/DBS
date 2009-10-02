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
		
                this.putOnce("BRANCH_HASH_ID", (Integer) branchHashID );
                this.putOnce("HASH", (String) hash );
                this.putOnce("CONTENT", (String) content );
        }

	int getBranchHashID ( )  throws Exception {
		int branchHashID = 0;
               	if (!JSONObject.NULL.equals(this.get("BRANCH_HASH_ID"))) {
                       	branchHashID = (Integer) this.get("BRANCH_HASH_ID");
               	}
                return branchHashID;
        }
	
	String getHash ( )  throws Exception {
		String hash = null;
               	if (!JSONObject.NULL.equals(this.get("HASH"))) {
                       	hash = (String) this.get("HASH");
               	}
                return hash;
        }
	
	String getContent ( )  throws Exception {
		String content = null;
               	if (!JSONObject.NULL.equals(this.get("CONTENT"))) {
                       	content = (String) this.get("CONTENT");
               	}
                return content;
        }
	
}