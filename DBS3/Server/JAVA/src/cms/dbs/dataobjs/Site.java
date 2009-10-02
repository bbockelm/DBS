/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : SITES
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class Site extends JSONObject  {

	public Site ( ) {

	}
		
        public Site ( int siteID, String siteName ) throws Exception  {
		
                this.putOnce("SITE_ID", (Integer) siteID );
                this.putOnce("SITE_NAME", (String) siteName );
        }

	int getSiteID ( )  throws Exception {
		int siteID = 0;
               	if (!JSONObject.NULL.equals(this.get("SITE_ID"))) {
                       	siteID = (Integer) this.get("SITE_ID");
               	}
                return siteID;
        }
	
	String getSiteName ( )  throws Exception {
		String siteName = null;
               	if (!JSONObject.NULL.equals(this.get("SITE_NAME"))) {
                       	siteName = (String) this.get("SITE_NAME");
               	}
                return siteName;
        }
	
}