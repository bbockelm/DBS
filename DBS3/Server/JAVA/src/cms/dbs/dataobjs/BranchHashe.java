/**
 * 
 $Revision: $"
 $Id: $"
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

	String getContent ( ) {
		CLOB content = null;
               	if (!JSONObject.NULL.equals(this.get("CONTENT"))) {
                       	content = (CLOB) this.get("CONTENT");
               	}
                return content;
        }
	
}