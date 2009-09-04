/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : PRIMARY_DS_TYPES
*/

package cms.dbs.dataobjs;

public class PrimaryDSType extends JSONObject {

	public PrimaryDSType ( ) {

	}

        public PrimaryDSType ( int primaryDSTypeID, String primaryDSType )  {
		
                this.putOnce("PRIMARY_DS_TYPE_ID", (Integer) primaryDSTypeID );
                this.putOnce("PRIMARY_DS_TYPE", (String) primaryDSType );
        }

	String getPrimaryDSType ( ) {
		String primaryDSType = null;
               	if (!JSONObject.NULL.equals(this.get("PRIMARY_DS_TYPE"))) {
                       	primaryDSType = (String) this.get("PRIMARY_DS_TYPE");
               	}
                return primaryDSType;
        }
	
}