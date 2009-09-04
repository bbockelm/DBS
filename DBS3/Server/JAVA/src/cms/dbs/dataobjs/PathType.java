/**
 * 
 $Revision: $"
 $Id: $"
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

	String getPathType ( ) {
		String pathType = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_TYPE"))) {
                       	pathType = (String) this.get("PATH_TYPE");
               	}
                return pathType;
        }
	
}