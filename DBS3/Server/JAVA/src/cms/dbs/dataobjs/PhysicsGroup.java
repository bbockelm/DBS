/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getPhysicsGroupID ( ) {
		int physicsGroupID = null;
               	if (!JSONObject.NULL.equals(this.get("PHYSICS_GROUP_ID"))) {
                       	physicsGroupID = (Integer) this.get("PHYSICS_GROUP_ID");
               	}
                return physicsGroupID;
        }
	
	String getPhysicsGroupName ( ) {
		String physicsGroupName = null;
               	if (!JSONObject.NULL.equals(this.get("PHYSICS_GROUP_NAME"))) {
                       	physicsGroupName = (String) this.get("PHYSICS_GROUP_NAME");
               	}
                return physicsGroupName;
        }
	
	String getPhysicsGroupConvener ( ) {
		String physicsGroupConvener = null;
               	if (!JSONObject.NULL.equals(this.get("PHYSICS_GROUP_CONVENER"))) {
                       	physicsGroupConvener = (String) this.get("PHYSICS_GROUP_CONVENER");
               	}
                return physicsGroupConvener;
        }
	
}