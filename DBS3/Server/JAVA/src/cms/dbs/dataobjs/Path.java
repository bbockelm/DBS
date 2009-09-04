/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : PATHS
*/

package cms.dbs.dataobjs;

public class Path extends JSONObject {

	public Path ( ) {

	}

        public Path ( int pathID, String pathName, int isPathValid, int primaryDSID, int processedDSID, int dataTierID, int pathTypeID, int acquisitionEraID, int processingEraID, int physicsGroupID, float xtcrosssection, String globalTag, int creationdate, String createby, int lastmodificationdate, String lastmodifiedby )  {
		
                this.putOnce("PATH_ID", (Integer) pathID );
                this.putOnce("PATH_NAME", (String) pathName );
                this.putOnce("IS_PATH_VALID", (Integer) isPathValid );
                this.putOnce("PRIMARY_DS_ID", (Integer) primaryDSID );
                this.putOnce("PROCESSED_DS_ID", (Integer) processedDSID );
                this.putOnce("DATA_TIER_ID", (Integer) dataTierID );
                this.putOnce("PATH_TYPE_ID", (Integer) pathTypeID );
                this.putOnce("ACQUISITION_ERA_ID", (Integer) acquisitionEraID );
                this.putOnce("PROCESSING_ERA_ID", (Integer) processingEraID );
                this.putOnce("PHYSICS_GROUP_ID", (Integer) physicsGroupID );
                this.putOnce("XTCROSSSECTION", (Float) xtcrosssection );
                this.putOnce("GLOBAL_TAG", (String) globalTag );
                this.putOnce("CREATIONDATE", (Integer) creationdate );
                this.putOnce("CREATEBY", (String) createby );
                this.putOnce("LASTMODIFICATIONDATE", (Integer) lastmodificationdate );
                this.putOnce("LASTMODIFIEDBY", (String) lastmodifiedby );
        }

	String getLastmodifiedby ( ) {
		String lastmodifiedby = null;
               	if (!JSONObject.NULL.equals(this.get("LASTMODIFIEDBY"))) {
                       	lastmodifiedby = (String) this.get("LASTMODIFIEDBY");
               	}
                return lastmodifiedby;
        }
	
}