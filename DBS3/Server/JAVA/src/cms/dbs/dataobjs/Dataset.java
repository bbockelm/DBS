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
		
                this.putOnce("DATASET_ID", datasetID );
                this.putOnce("DATA_PATH", dataPath );
                this.putOnce("IS_PATH_VALID", isPathValid );
                this.putOnce("PRIMARY_DS_ID", primaryDSID );
                this.putOnce("PROCESSED_DS_ID", processedDSID );
                this.putOnce("DATA_TIER_ID", dataTierID );
                this.putOnce("PATH_TYPE_ID", pathTypeID );
                this.putOnce("ACQUISITION_ERA_ID", acquisitionEraID );
                this.putOnce("PROCESSING_ERA_ID", processingEraID );
                this.putOnce("PHYSICS_GROUP_ID", physicsGroupID );
                this.putOnce("XTCROSSSECTION", xtcrosssection );
                this.putOnce("GLOBAL_TAG", globalTag );
                this.putOnce("CREATION_DATE", creationDate );
                this.putOnce("CREATE_BY", createBy );
                this.putOnce("LAST_MODIFICATION_DATE", lastModificationDate );
                this.putOnce("LAST_MODIFIED_BY", lastModifiedBy );
        }

	public void setDatasetID (int datasetID) throws Exception {
 		this.put( "DATASET_ID", datasetID );
	}
	
	public void setDataPath (String dataPath) throws Exception {
 		this.put( "DATA_PATH", dataPath );
	}
	
	public void setIsPathValid (int isPathValid) throws Exception {
 		this.put( "IS_PATH_VALID", isPathValid );
	}
	
	public void setPrimaryDSID (int primaryDSID) throws Exception {
 		this.put( "PRIMARY_DS_ID", primaryDSID );
	}
	
	public void setProcessedDSID (int processedDSID) throws Exception {
 		this.put( "PROCESSED_DS_ID", processedDSID );
	}
	
	public void setDataTierID (int dataTierID) throws Exception {
 		this.put( "DATA_TIER_ID", dataTierID );
	}
	
	public void setPathTypeID (int pathTypeID) throws Exception {
 		this.put( "PATH_TYPE_ID", pathTypeID );
	}
	
	public void setAcquisitionEraID (int acquisitionEraID) throws Exception {
 		this.put( "ACQUISITION_ERA_ID", acquisitionEraID );
	}
	
	public void setProcessingEraID (int processingEraID) throws Exception {
 		this.put( "PROCESSING_ERA_ID", processingEraID );
	}
	
	public void setPhysicsGroupID (int physicsGroupID) throws Exception {
 		this.put( "PHYSICS_GROUP_ID", physicsGroupID );
	}
	
	public void setXtcrosssection (float xtcrosssection) throws Exception {
 		this.put( "XTCROSSSECTION", xtcrosssection );
	}
	
	public void setGlobalTag (String globalTag) throws Exception {
 		this.put( "GLOBAL_TAG", globalTag );
	}
	
	public void setCreationDate (int creationDate) throws Exception {
 		this.put( "CREATION_DATE", creationDate );
	}
	
	public void setCreateBy (String createBy) throws Exception {
 		this.put( "CREATE_BY", createBy );
	}
	
	public void setLastModificationDate (int lastModificationDate) throws Exception {
 		this.put( "LAST_MODIFICATION_DATE", lastModificationDate );
	}
	
	public void setLastModifiedBy (String lastModifiedBy) throws Exception {
 		this.put( "LAST_MODIFIED_BY", lastModifiedBy );
	}
	
	int getDatasetID ( )  throws Exception {
		int datasetID = 0;
               	if (!JSONObject.NULL.equals(this.get("DATASET_ID"))) {
                       	datasetID = this.getInt("DATASET_ID");
               	}
                return datasetID;
        }
	
	String getDataPath ( )  throws Exception {
		String dataPath = null;
               	if (!JSONObject.NULL.equals(this.get("DATA_PATH"))) {
                       	dataPath = this.getString("DATA_PATH");
               	}
                return dataPath;
        }
	
	int getIsPathValid ( )  throws Exception {
		int isPathValid = 0;
               	if (!JSONObject.NULL.equals(this.get("IS_PATH_VALID"))) {
                       	isPathValid = this.getInt("IS_PATH_VALID");
               	}
                return isPathValid;
        }
	
	int getPrimaryDSID ( )  throws Exception {
		int primaryDSID = 0;
               	if (!JSONObject.NULL.equals(this.get("PRIMARY_DS_ID"))) {
                       	primaryDSID = this.getInt("PRIMARY_DS_ID");
               	}
                return primaryDSID;
        }
	
	int getProcessedDSID ( )  throws Exception {
		int processedDSID = 0;
               	if (!JSONObject.NULL.equals(this.get("PROCESSED_DS_ID"))) {
                       	processedDSID = this.getInt("PROCESSED_DS_ID");
               	}
                return processedDSID;
        }
	
	int getDataTierID ( )  throws Exception {
		int dataTierID = 0;
               	if (!JSONObject.NULL.equals(this.get("DATA_TIER_ID"))) {
                       	dataTierID = this.getInt("DATA_TIER_ID");
               	}
                return dataTierID;
        }
	
	int getPathTypeID ( )  throws Exception {
		int pathTypeID = 0;
               	if (!JSONObject.NULL.equals(this.get("PATH_TYPE_ID"))) {
                       	pathTypeID = this.getInt("PATH_TYPE_ID");
               	}
                return pathTypeID;
        }
	
	int getAcquisitionEraID ( )  throws Exception {
		int acquisitionEraID = 0;
               	if (!JSONObject.NULL.equals(this.get("ACQUISITION_ERA_ID"))) {
                       	acquisitionEraID = this.getInt("ACQUISITION_ERA_ID");
               	}
                return acquisitionEraID;
        }
	
	int getProcessingEraID ( )  throws Exception {
		int processingEraID = 0;
               	if (!JSONObject.NULL.equals(this.get("PROCESSING_ERA_ID"))) {
                       	processingEraID = this.getInt("PROCESSING_ERA_ID");
               	}
                return processingEraID;
        }
	
	int getPhysicsGroupID ( )  throws Exception {
		int physicsGroupID = 0;
               	if (!JSONObject.NULL.equals(this.get("PHYSICS_GROUP_ID"))) {
                       	physicsGroupID = this.getInt("PHYSICS_GROUP_ID");
               	}
                return physicsGroupID;
        }
	
	float getXtcrosssection ( )  throws Exception {
		float xtcrosssection = 0;
               	if (!JSONObject.NULL.equals(this.get("XTCROSSSECTION"))) {
                       	//xtcrosssection = this.getFloat("XTCROSSSECTION");
			System.out.println("DANG !!!!!!!!!!!!");
               	}
                return xtcrosssection;
        }
	
	String getGlobalTag ( )  throws Exception {
		String globalTag = null;
               	if (!JSONObject.NULL.equals(this.get("GLOBAL_TAG"))) {
                       	globalTag = this.getString("GLOBAL_TAG");
               	}
                return globalTag;
        }
	
	int getCreationDate ( )  throws Exception {
		int creationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("CREATION_DATE"))) {
                       	creationDate = this.getInt("CREATION_DATE");
               	}
                return creationDate;
        }
	
	String getCreateBy ( )  throws Exception {
		String createBy = null;
               	if (!JSONObject.NULL.equals(this.get("CREATE_BY"))) {
                       	createBy = this.getString("CREATE_BY");
               	}
                return createBy;
        }
	
	int getLastModificationDate ( )  throws Exception {
		int lastModificationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFICATION_DATE"))) {
                       	lastModificationDate = this.getInt("LAST_MODIFICATION_DATE");
               	}
                return lastModificationDate;
        }
	
	String getLastModifiedBy ( )  throws Exception {
		String lastModifiedBy = null;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFIED_BY"))) {
                       	lastModifiedBy = this.getString("LAST_MODIFIED_BY");
               	}
                return lastModifiedBy;
        }
	
}
