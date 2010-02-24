/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
 *
 * Data Object from table : PATH_PARENTS
*/

package cms.dbs.dataobjs;

public class PathParent extends JSONObject {

	public PathParent ( ) {

	}

        public PathParent ( int pathParentID, int thisPathID, int parentPathID )  {
		
                this.putOnce("PATH_PARENT_ID", (Integer) pathParentID );
                this.putOnce("THIS_PATH_ID", (Integer) thisPathID );
                this.putOnce("PARENT_PATH_ID", (Integer) parentPathID );
        }

	int getPathParentID ( ) {
		int pathParentID = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_PARENT_ID"))) {
                       	pathParentID = (Integer) this.get("PATH_PARENT_ID");
               	}
                return pathParentID;
        }
	
	int getThisPathID ( ) {
		int thisPathID = null;
               	if (!JSONObject.NULL.equals(this.get("THIS_PATH_ID"))) {
                       	thisPathID = (Integer) this.get("THIS_PATH_ID");
               	}
                return thisPathID;
        }
	
	int getParentPathID ( ) {
		int parentPathID = null;
               	if (!JSONObject.NULL.equals(this.get("PARENT_PATH_ID"))) {
                       	parentPathID = (Integer) this.get("PARENT_PATH_ID");
               	}
                return parentPathID;
        }
	
}