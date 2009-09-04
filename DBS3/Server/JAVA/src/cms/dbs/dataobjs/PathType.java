/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
 *
 * Data Object from table : PATH_TYPES
*/

package cms.dbs.dataobjs;

public class PathType extends JSONObject {

	public PathType ( ) {

	}

        public PathType ( int pathTypeID, String pathType )  {
		
                this.putOnce("PATH_TYPE_ID", (Integer) pathTypeID );
                this.putOnce("PATH_TYPE", (String) pathType );
        }

	int getPathTypeID ( ) {
		int pathTypeID = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_TYPE_ID"))) {
                       	pathTypeID = (Integer) this.get("PATH_TYPE_ID");
               	}
                return pathTypeID;
        }
	
	String getPathType ( ) {
		String pathType = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_TYPE"))) {
                       	pathType = (String) this.get("PATH_TYPE");
               	}
                return pathType;
        }
	
}