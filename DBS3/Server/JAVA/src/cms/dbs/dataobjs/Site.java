/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : SITES
*/

package cms.dbs.dataobjs;

public class Site extends JSONObject {

	public Site ( ) {

	}

        public Site ( int siteID, String siteName )  {
		
                this.putOnce("SITE_ID", (Integer) siteID );
                this.putOnce("SITE_NAME", (String) siteName );
        }

	String getSiteName ( ) {
		String siteName = null;
               	if (!JSONObject.NULL.equals(this.get("SITE_NAME"))) {
                       	siteName = (String) this.get("SITE_NAME");
               	}
                return siteName;
        }
	
}