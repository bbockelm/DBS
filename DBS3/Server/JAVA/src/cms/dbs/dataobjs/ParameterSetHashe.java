/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : PARAMETER_SET_HASHES
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class ParameterSetHashe extends JSONObject  {

	public ParameterSetHashe ( ) {

	}
		
        public ParameterSetHashe ( int parameterSetHashID, String hash, String name ) throws Exception  {
		
                this.putOnce("PARAMETER_SET_HASH_ID", parameterSetHashID );
                this.putOnce("HASH", hash );
                this.putOnce("NAME", name );
        }

	int getParameterSetHashID ( )  throws Exception {
		int parameterSetHashID = 0;
               	if (!JSONObject.NULL.equals(this.get("PARAMETER_SET_HASH_ID"))) {
                       	parameterSetHashID = this.getInt("PARAMETER_SET_HASH_ID");
               	}
                return parameterSetHashID;
        }
	
	String getHash ( )  throws Exception {
		String hash = null;
               	if (!JSONObject.NULL.equals(this.get("HASH"))) {
                       	hash = this.getString("HASH");
               	}
                return hash;
        }
	
	String getName ( )  throws Exception {
		String name = null;
               	if (!JSONObject.NULL.equals(this.get("NAME"))) {
                       	name = this.getString("NAME");
               	}
                return name;
        }
	
}