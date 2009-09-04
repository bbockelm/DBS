/**
 * 
 $Revision: $"
 $Id: $"
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

	int getParentPathID ( ) {
		Integer parentPathID = null;
               	if (!JSONObject.NULL.equals(this.get("PARENT_PATH_ID"))) {
                       	parentPathID = (Integer) this.get("PARENT_PATH_ID");
               	}
                return parentPathID;
        }
	
}