/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getSiteID ( ) {
		int siteID = null;
               	if (!JSONObject.NULL.equals(this.get("SITE_ID"))) {
                       	siteID = (Integer) this.get("SITE_ID");
               	}
                return siteID;
        }
	
	String getSiteName ( ) {
		String siteName = null;
               	if (!JSONObject.NULL.equals(this.get("SITE_NAME"))) {
                       	siteName = (String) this.get("SITE_NAME");
               	}
                return siteName;
        }
	
}