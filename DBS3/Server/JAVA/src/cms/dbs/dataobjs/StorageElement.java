/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
 *
 * Data Object from table : STORAGE_ELEMENTS
*/

package cms.dbs.dataobjs;

public class StorageElement extends JSONObject {

	public StorageElement ( ) {

	}

        public StorageElement ( int seID, String seName )  {
		
                this.putOnce("SE_ID", (Integer) seID );
                this.putOnce("SE_NAME", (String) seName );
        }

	int getSeID ( ) {
		int seID = null;
               	if (!JSONObject.NULL.equals(this.get("SE_ID"))) {
                       	seID = (Integer) this.get("SE_ID");
               	}
                return seID;
        }
	
	String getSeName ( ) {
		String seName = null;
               	if (!JSONObject.NULL.equals(this.get("SE_NAME"))) {
                       	seName = (String) this.get("SE_NAME");
               	}
                return seName;
        }
	
}