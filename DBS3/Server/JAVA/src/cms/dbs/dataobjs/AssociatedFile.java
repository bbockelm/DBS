/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
 *
 * Data Object from table : ASSOCIATED_FILES
*/

package cms.dbs.dataobjs;

public class AssociatedFile extends JSONObject {

	public AssociatedFile ( ) {

	}

        public AssociatedFile ( int assocatedFileID, int thisFileID, int assocatedFile )  {
		
                this.putOnce("ASSOCATED_FILE_ID", (Integer) assocatedFileID );
                this.putOnce("THIS_FILE_ID", (Integer) thisFileID );
                this.putOnce("ASSOCATED_FILE", (Integer) assocatedFile );
        }

	int getAssocatedFileID ( ) {
		int assocatedFileID = null;
               	if (!JSONObject.NULL.equals(this.get("ASSOCATED_FILE_ID"))) {
                       	assocatedFileID = (Integer) this.get("ASSOCATED_FILE_ID");
               	}
                return assocatedFileID;
        }
	
	int getThisFileID ( ) {
		int thisFileID = null;
               	if (!JSONObject.NULL.equals(this.get("THIS_FILE_ID"))) {
                       	thisFileID = (Integer) this.get("THIS_FILE_ID");
               	}
                return thisFileID;
        }
	
	int getAssocatedFile ( ) {
		int assocatedFile = null;
               	if (!JSONObject.NULL.equals(this.get("ASSOCATED_FILE"))) {
                       	assocatedFile = (Integer) this.get("ASSOCATED_FILE");
               	}
                return assocatedFile;
        }
	
}