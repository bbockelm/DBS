/**
 * 
 $Revision: $"
 $Id: $"
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

	int getAssocatedFile ( ) {
		Integer assocatedFile = null;
               	if (!JSONObject.NULL.equals(this.get("ASSOCATED_FILE"))) {
                       	assocatedFile = (Integer) this.get("ASSOCATED_FILE");
               	}
                return assocatedFile;
        }
	
}