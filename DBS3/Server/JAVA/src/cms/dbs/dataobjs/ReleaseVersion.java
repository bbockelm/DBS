/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : RELEASE_VERSIONS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class ReleaseVersion extends JSONObject  {

	public ReleaseVersion ( ) {

	}
		
        public ReleaseVersion ( int releaseVersionID, String version ) throws Exception  {
		
                this.putOnce("RELEASE_VERSION_ID", (Integer) releaseVersionID );
                this.putOnce("VERSION", (String) version );
        }

	int getReleaseVersionID ( )  throws Exception {
		int releaseVersionID = 0;
               	if (!JSONObject.NULL.equals(this.get("RELEASE_VERSION_ID"))) {
                       	releaseVersionID = (Integer) this.get("RELEASE_VERSION_ID");
               	}
                return releaseVersionID;
        }
	
	String getVersion ( )  throws Exception {
		String version = null;
               	if (!JSONObject.NULL.equals(this.get("VERSION"))) {
                       	version = (String) this.get("VERSION");
               	}
                return version;
        }
	
}