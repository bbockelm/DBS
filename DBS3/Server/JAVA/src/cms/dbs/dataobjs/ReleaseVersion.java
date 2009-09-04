/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : RELEASE_VERSIONS
*/

package cms.dbs.dataobjs;

public class ReleaseVersion extends JSONObject {

	public ReleaseVersion ( ) {

	}

        public ReleaseVersion ( int releaseVersionID, String version )  {
		
                this.putOnce("RELEASE_VERSION_ID", (Integer) releaseVersionID );
                this.putOnce("VERSION", (String) version );
        }

	String getVersion ( ) {
		String version = null;
               	if (!JSONObject.NULL.equals(this.get("VERSION"))) {
                       	version = (String) this.get("VERSION");
               	}
                return version;
        }
	
}