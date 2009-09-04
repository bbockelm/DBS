/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
 *
 * Data Object from table : BRANCH_HASHES
*/

package cms.dbs.dataobjs;

public class BranchHashe extends JSONObject {

	public BranchHashe ( ) {

	}

        public BranchHashe ( int branchHashID, String hash, String content )  {
		
                this.putOnce("BRANCH_HASH_ID", (Integer) branchHashID );
                this.putOnce("HASH", (String) hash );
                this.putOnce("CONTENT", (CLOB) content );
        }

	int getBranchHashID ( ) {
		int branchHashID = null;
               	if (!JSONObject.NULL.equals(this.get("BRANCH_HASH_ID"))) {
                       	branchHashID = (Integer) this.get("BRANCH_HASH_ID");
               	}
                return branchHashID;
        }
	
	String getHash ( ) {
		String hash = null;
               	if (!JSONObject.NULL.equals(this.get("HASH"))) {
                       	hash = (String) this.get("HASH");
               	}
                return hash;
        }
	
	String getContent ( ) {
		String content = null;
               	if (!JSONObject.NULL.equals(this.get("CONTENT"))) {
                       	content = (CLOB) this.get("CONTENT");
               	}
                return content;
        }
	
}