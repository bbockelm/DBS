/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getPrimaryDSTypeID ( ) {
		int primaryDSTypeID = null;
               	if (!JSONObject.NULL.equals(this.get("PRIMARY_DS_TYPE_ID"))) {
                       	primaryDSTypeID = (Integer) this.get("PRIMARY_DS_TYPE_ID");
               	}
                return primaryDSTypeID;
        }
	
	String getPrimaryDSType ( ) {
		String primaryDSType = null;
               	if (!JSONObject.NULL.equals(this.get("PRIMARY_DS_TYPE"))) {
                       	primaryDSType = (String) this.get("PRIMARY_DS_TYPE");
               	}
                return primaryDSType;
        }
	
}