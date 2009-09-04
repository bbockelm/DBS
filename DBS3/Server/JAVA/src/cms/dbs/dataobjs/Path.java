/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getPathID ( ) {
		int pathID = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_ID"))) {
                       	pathID = (Integer) this.get("PATH_ID");
               	}
                return pathID;
        }
	
	String getPathName ( ) {
		String pathName = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_NAME"))) {
                       	pathName = (String) this.get("PATH_NAME");
               	}
                return pathName;
        }
	
	int getIsPathValid ( ) {
		int isPathValid = null;
               	if (!JSONObject.NULL.equals(this.get("IS_PATH_VALID"))) {
                       	isPathValid = (Integer) this.get("IS_PATH_VALID");
               	}
                return isPathValid;
        }
	
	int getPrimaryDSID ( ) {
		int primaryDSID = null;
               	if (!JSONObject.NULL.equals(this.get("PRIMARY_DS_ID"))) {
                       	primaryDSID = (Integer) this.get("PRIMARY_DS_ID");
               	}
                return primaryDSID;
        }
	
	int getProcessedDSID ( ) {
		int processedDSID = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESSED_DS_ID"))) {
                       	processedDSID = (Integer) this.get("PROCESSED_DS_ID");
               	}
                return processedDSID;
        }
	
	int getDataTierID ( ) {
		int dataTierID = null;
               	if (!JSONObject.NULL.equals(this.get("DATA_TIER_ID"))) {
                       	dataTierID = (Integer) this.get("DATA_TIER_ID");
               	}
                return dataTierID;
        }
	
	int getPathTypeID ( ) {
		int pathTypeID = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_TYPE_ID"))) {
                       	pathTypeID = (Integer) this.get("PATH_TYPE_ID");
               	}
                return pathTypeID;
        }
	
	int getAcquisitionEraID ( ) {
		int acquisitionEraID = null;
               	if (!JSONObject.NULL.equals(this.get("ACQUISITION_ERA_ID"))) {
                       	acquisitionEraID = (Integer) this.get("ACQUISITION_ERA_ID");
               	}
                return acquisitionEraID;
        }
	
	int getProcessingEraID ( ) {
		int processingEraID = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESSING_ERA_ID"))) {
                       	processingEraID = (Integer) this.get("PROCESSING_ERA_ID");
               	}
                return processingEraID;
        }
	
	int getPhysicsGroupID ( ) {
		int physicsGroupID = null;
               	if (!JSONObject.NULL.equals(this.get("PHYSICS_GROUP_ID"))) {
                       	physicsGroupID = (Integer) this.get("PHYSICS_GROUP_ID");
               	}
                return physicsGroupID;
        }
	
	float getXtcrosssection ( ) {
		float xtcrosssection = null;
               	if (!JSONObject.NULL.equals(this.get("XTCROSSSECTION"))) {
                       	xtcrosssection = (Float) this.get("XTCROSSSECTION");
               	}
                return xtcrosssection;
        }
	
	String getGlobalTag ( ) {
		String globalTag = null;
               	if (!JSONObject.NULL.equals(this.get("GLOBAL_TAG"))) {
                       	globalTag = (String) this.get("GLOBAL_TAG");
               	}
                return globalTag;
        }
	
	int getCreationdate ( ) {
		int creationdate = null;
               	if (!JSONObject.NULL.equals(this.get("CREATIONDATE"))) {
                       	creationdate = (Integer) this.get("CREATIONDATE");
               	}
                return creationdate;
        }
	
	String getCreateby ( ) {
		String createby = null;
               	if (!JSONObject.NULL.equals(this.get("CREATEBY"))) {
                       	createby = (String) this.get("CREATEBY");
               	}
                return createby;
        }
	
	int getLastmodificationdate ( ) {
		int lastmodificationdate = null;
               	if (!JSONObject.NULL.equals(this.get("LASTMODIFICATIONDATE"))) {
                       	lastmodificationdate = (Integer) this.get("LASTMODIFICATIONDATE");
               	}
                return lastmodificationdate;
        }
	
	String getLastmodifiedby ( ) {
		String lastmodifiedby = null;
               	if (!JSONObject.NULL.equals(this.get("LASTMODIFIEDBY"))) {
                       	lastmodifiedby = (String) this.get("LASTMODIFIEDBY");
               	}
                return lastmodifiedby;
        }
	
}