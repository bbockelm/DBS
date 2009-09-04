/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
 *
 * Data Object from table : PARAMETER_SET_HASHES
*/

package cms.dbs.dataobjs;

public class ParameterSetHashe extends JSONObject {

	public ParameterSetHashe ( ) {

	}

        public ParameterSetHashe ( int parameterSetHashID, String hash, String name )  {
		
                this.putOnce("PARAMETER_SET_HASH_ID", (Integer) parameterSetHashID );
                this.putOnce("HASH", (String) hash );
                this.putOnce("NAME", (String) name );
        }

	int getParameterSetHashID ( ) {
		int parameterSetHashID = null;
               	if (!JSONObject.NULL.equals(this.get("PARAMETER_SET_HASH_ID"))) {
                       	parameterSetHashID = (Integer) this.get("PARAMETER_SET_HASH_ID");
               	}
                return parameterSetHashID;
        }
	
	String getHash ( ) {
		String hash = null;
               	if (!JSONObject.NULL.equals(this.get("HASH"))) {
                       	hash = (String) this.get("HASH");
               	}
                return hash;
        }
	
	String getName ( ) {
		String name = null;
               	if (!JSONObject.NULL.equals(this.get("NAME"))) {
                       	name = (String) this.get("NAME");
               	}
                return name;
        }
	
}