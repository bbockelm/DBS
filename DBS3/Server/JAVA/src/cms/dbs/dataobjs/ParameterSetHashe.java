/**
 * 
 $Revision: $"
 $Id: $"
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

	String getName ( ) {
		String name = null;
               	if (!JSONObject.NULL.equals(this.get("NAME"))) {
                       	name = (String) this.get("NAME");
               	}
                return name;
        }
	
}