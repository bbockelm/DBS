/**
 * 
 $Revision: $"
 $Id: $"
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

	String getSeName ( ) {
		String seName = null;
               	if (!JSONObject.NULL.equals(this.get("SE_NAME"))) {
                       	seName = (String) this.get("SE_NAME");
               	}
                return seName;
        }
	
}