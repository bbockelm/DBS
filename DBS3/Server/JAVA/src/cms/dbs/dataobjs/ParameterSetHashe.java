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
		
                this.putOnce("PARAMETER_SET_HASH_ID", (Integer) parameterSetHashID );
                this.putOnce("HASH", (String) hash );
                this.putOnce("NAME", (String) name );
        }

	int getParameterSetHashID ( )  throws Exception {
		int parameterSetHashID = 0;
               	if (!JSONObject.NULL.equals(this.get("PARAMETER_SET_HASH_ID"))) {
                       	parameterSetHashID = (Integer) this.get("PARAMETER_SET_HASH_ID");
               	}
                return parameterSetHashID;
        }
	
	String getHash ( )  throws Exception {
		String hash = null;
               	if (!JSONObject.NULL.equals(this.get("HASH"))) {
                       	hash = (String) this.get("HASH");
               	}
                return hash;
        }
	
	String getName ( )  throws Exception {
		String name = null;
               	if (!JSONObject.NULL.equals(this.get("NAME"))) {
                       	name = (String) this.get("NAME");
               	}
                return name;
        }
	
}