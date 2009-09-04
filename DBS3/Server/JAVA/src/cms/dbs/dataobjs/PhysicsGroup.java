/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : PHYSICS_GROUPS
*/

package cms.dbs.dataobjs;

public class PhysicsGroup extends JSONObject {

	public PhysicsGroup ( ) {

	}

        public PhysicsGroup ( int physicsGroupID, String physicsGroupName, String physicsGroupConvener )  {
		
                this.putOnce("PHYSICS_GROUP_ID", (Integer) physicsGroupID );
                this.putOnce("PHYSICS_GROUP_NAME", (String) physicsGroupName );
                this.putOnce("PHYSICS_GROUP_CONVENER", (String) physicsGroupConvener );
        }

	String getPhysicsGroupConvener ( ) {
		String physicsGroupConvener = null;
               	if (!JSONObject.NULL.equals(this.get("PHYSICS_GROUP_CONVENER"))) {
                       	physicsGroupConvener = (String) this.get("PHYSICS_GROUP_CONVENER");
               	}
                return physicsGroupConvener;
        }
	
}