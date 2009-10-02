/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : DATASETS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class Dataset extends JSONObject  {

	public Dataset ( ) {

	}
		
        public Dataset ( int datasetID, String dataPath, int isPathValid, int primaryDSID, int processedDSID, int dataTierID, int pathTypeID, int acquisitionEraID, int processingEraID, int physicsGroupID, float xtcrosssection, String globalTag, int creationDate, String createBy, int lastModificationDate, String lastModifiedBy ) throws Exception  {
		
                this.putOnce("DATASET_ID", (Integer) datasetID );
                this.putOnce("DATA_PATH", (String) dataPath );
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
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("CREATE_BY", (String) createBy );
                this.putOnce("LAST_MODIFICATION_DATE", (Integer) lastModificationDate );
                this.putOnce("LAST_MODIFIED_BY", (String) lastModifiedBy );
        }

	int getDatasetID ( )  throws Exception {
		int datasetID = 0;
               	if (!JSONObject.NULL.equals(this.get("DATASET_ID"))) {
                       	datasetID = (Integer) this.get("DATASET_ID");
               	}
                return datasetID;
        }
	
	String getDataPath ( )  throws Exception {
		String dataPath = null;
               	if (!JSONObject.NULL.equals(this.get("DATA_PATH"))) {
                       	dataPath = (String) this.get("DATA_PATH");
               	}
                return dataPath;
        }
	
	int getIsPathValid ( )  throws Exception {
		int isPathValid = 0;
               	if (!JSONObject.NULL.equals(this.get("IS_PATH_VALID"))) {
                       	isPathValid = (Integer) this.get("IS_PATH_VALID");
               	}
                return isPathValid;
        }
	
	int getPrimaryDSID ( )  throws Exception {
		int primaryDSID = 0;
               	if (!JSONObject.NULL.equals(this.get("PRIMARY_DS_ID"))) {
                       	primaryDSID = (Integer) this.get("PRIMARY_DS_ID");
               	}
                return primaryDSID;
        }
	
	int getProcessedDSID ( )  throws Exception {
		int processedDSID = 0;
               	if (!JSONObject.NULL.equals(this.get("PROCESSED_DS_ID"))) {
                       	processedDSID = (Integer) this.get("PROCESSED_DS_ID");
               	}
                return processedDSID;
        }
	
	int getDataTierID ( )  throws Exception {
		int dataTierID = 0;
               	if (!JSONObject.NULL.equals(this.get("DATA_TIER_ID"))) {
                       	dataTierID = (Integer) this.get("DATA_TIER_ID");
               	}
                return dataTierID;
        }
	
	int getPathTypeID ( )  throws Exception {
		int pathTypeID = 0;
               	if (!JSONObject.NULL.equals(this.get("PATH_TYPE_ID"))) {
                       	pathTypeID = (Integer) this.get("PATH_TYPE_ID");
               	}
                return pathTypeID;
        }
	
	int getAcquisitionEraID ( )  throws Exception {
		int acquisitionEraID = 0;
               	if (!JSONObject.NULL.equals(this.get("ACQUISITION_ERA_ID"))) {
                       	acquisitionEraID = (Integer) this.get("ACQUISITION_ERA_ID");
               	}
                return acquisitionEraID;
        }
	
	int getProcessingEraID ( )  throws Exception {
		int processingEraID = 0;
               	if (!JSONObject.NULL.equals(this.get("PROCESSING_ERA_ID"))) {
                       	processingEraID = (Integer) this.get("PROCESSING_ERA_ID");
               	}
                return processingEraID;
        }
	
	int getPhysicsGroupID ( )  throws Exception {
		int physicsGroupID = 0;
               	if (!JSONObject.NULL.equals(this.get("PHYSICS_GROUP_ID"))) {
                       	physicsGroupID = (Integer) this.get("PHYSICS_GROUP_ID");
               	}
                return physicsGroupID;
        }
	
	float getXtcrosssection ( )  throws Exception {
		float xtcrosssection = 0;
               	if (!JSONObject.NULL.equals(this.get("XTCROSSSECTION"))) {
                       	xtcrosssection = (Float) this.get("XTCROSSSECTION");
               	}
                return xtcrosssection;
        }
	
	String getGlobalTag ( )  throws Exception {
		String globalTag = null;
               	if (!JSONObject.NULL.equals(this.get("GLOBAL_TAG"))) {
                       	globalTag = (String) this.get("GLOBAL_TAG");
               	}
                return globalTag;
        }
	
	int getCreationDate ( )  throws Exception {
		int creationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("CREATION_DATE"))) {
                       	creationDate = (Integer) this.get("CREATION_DATE");
               	}
                return creationDate;
        }
	
	String getCreateBy ( )  throws Exception {
		String createBy = null;
               	if (!JSONObject.NULL.equals(this.get("CREATE_BY"))) {
                       	createBy = (String) this.get("CREATE_BY");
               	}
                return createBy;
        }
	
	int getLastModificationDate ( )  throws Exception {
		int lastModificationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFICATION_DATE"))) {
                       	lastModificationDate = (Integer) this.get("LAST_MODIFICATION_DATE");
               	}
                return lastModificationDate;
        }
	
	String getLastModifiedBy ( )  throws Exception {
		String lastModifiedBy = null;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFIED_BY"))) {
                       	lastModifiedBy = (String) this.get("LAST_MODIFIED_BY");
               	}
                return lastModifiedBy;
        }
	
}