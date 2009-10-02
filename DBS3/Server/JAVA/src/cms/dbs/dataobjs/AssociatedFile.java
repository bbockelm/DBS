/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : ASSOCIATED_FILES
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class AssociatedFile extends JSONObject  {

	public AssociatedFile ( ) {

	}
		
        public AssociatedFile ( int assocatedFileID, int thisFileID, int assocatedFile ) throws Exception  {
		
                this.putOnce("ASSOCATED_FILE_ID", (Integer) assocatedFileID );
                this.putOnce("THIS_FILE_ID", (Integer) thisFileID );
                this.putOnce("ASSOCATED_FILE", (Integer) assocatedFile );
        }

	int getAssocatedFileID ( )  throws Exception {
		int assocatedFileID = 0;
               	if (!JSONObject.NULL.equals(this.get("ASSOCATED_FILE_ID"))) {
                       	assocatedFileID = (Integer) this.get("ASSOCATED_FILE_ID");
               	}
                return assocatedFileID;
        }
	
	int getThisFileID ( )  throws Exception {
		int thisFileID = 0;
               	if (!JSONObject.NULL.equals(this.get("THIS_FILE_ID"))) {
                       	thisFileID = (Integer) this.get("THIS_FILE_ID");
               	}
                return thisFileID;
        }
	
	int getAssocatedFile ( )  throws Exception {
		int assocatedFile = 0;
               	if (!JSONObject.NULL.equals(this.get("ASSOCATED_FILE"))) {
                       	assocatedFile = (Integer) this.get("ASSOCATED_FILE");
               	}
                return assocatedFile;
        }
	
}