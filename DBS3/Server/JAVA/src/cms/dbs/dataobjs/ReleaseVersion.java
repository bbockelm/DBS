/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getReleaseVersionID ( ) {
		int releaseVersionID = null;
               	if (!JSONObject.NULL.equals(this.get("RELEASE_VERSION_ID"))) {
                       	releaseVersionID = (Integer) this.get("RELEASE_VERSION_ID");
               	}
                return releaseVersionID;
        }
	
	String getVersion ( ) {
		String version = null;
               	if (!JSONObject.NULL.equals(this.get("VERSION"))) {
                       	version = (String) this.get("VERSION");
               	}
                return version;
        }
	
}