/**
 * 
 $Revision: 1.4 $"
 $Id: Dataset.java,v 1.4 2009/10/07 14:58:32 yuyi Exp $"
 *
 * Data Object from table : DATASETS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class Dataset extends JSONObject  {

	public Dataset ( ) {

	}
		
        public Dataset ( int datasetID, String dataset, int isDatasetValid, PrimaryDataset primaryDS, ProcessedDataset processedDS, 
	DataTier dataTire, DatasetType datasetType, AcquisitionEra acquisitionEra, ProcessingEra processingEra, 
	PhysicsGroup physicsGroup, double xtcrosssection, String globalTag, int creationDate, String createBy, 
	int lastModificationDate, String lastModifiedBy ) throws Exception  {
		
                this.putOnce("DATASET_ID", datasetID );
                this.putOnce("DATASET", dataset );
                this.putOnce("IS_DATASET_VALID", isDatasetValid );
                this.putOnce("PRIMARY_DS_DO", primaryDS );
                this.putOnce("PROCESSED_DS_DO", processedDS );
                this.putOnce("DATA_TIER_DO", dataTire );
                this.putOnce("DATASET_TYPE_DO", datasetType );
                this.putOnce("ACQUISITION_ERA_DO", acquisitionEra );
                this.putOnce("PROCESSING_ERA_DO", processingEra );
                this.putOnce("PHYSICS_GROUP_DO", physicsGroup );
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
	
	public void setDataset (String dataPath) throws Exception {
 		this.put( "DATASET", dataPath );
	}
	
	public void setIsDatasetValid (int isPathValid) throws Exception {
 		this.put( "IS_DATASET_VALID", isPathValid );
	}
	
	public void setPrimaryDSDO (PrimaryDataset primaryDS) throws Exception {
 		this.put( "PRIMARY_DS_DO", primaryDS );
	}
	
	public void setProcessedDSDO (ProcessedDataset processedDS) throws Exception {
 		this.put( "PROCESSED_DS_DO", processedDS );
	}
	
	public void setDataTierDO (DataTier dataTier) throws Exception {
 		this.put( "DATA_TIER_DO", dataTier );
	}
	
	public void setDatasetTypeDO(DatasetType  pathType) throws Exception {
 		this.put( "DATASET_TYPE_DO", pathType);
	}
	
	public void setAcquisitionEraDO (AcquisitionEra acquisitionEra) throws Exception {
 		this.put( "ACQUISITION_ERA_DO", acquisitionEra );
	}
	
	public void setProcessingEraDO (ProcessingEra processingEra) throws Exception {
 		this.put( "PROCESSING_ERA_DO", processingEra);
	}
	
	public void setPhysicsGroupDO (PhysicsGroup physicsGroup) throws Exception {
 		this.put( "PHYSICS_GROUP_DO", physicsGroup );
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
	
	String getDataset ( )  throws Exception {
		String dataPath = null;
               	if (!JSONObject.NULL.equals(this.get("DATASET"))) {
                       	dataPath = this.getString("DATASET");
               	}
                return dataPath;
        }
	
	int getIsDatasetValid ( )  throws Exception {
		int isPathValid = 0;
               	if (!JSONObject.NULL.equals(this.get("IS_DATASET_VALID"))) {
                       	isPathValid = this.getInt("IS_DATASET_VALID");
               	}
                return isPathValid;
        }
	
	PrimaryDataset getPrimaryDSDO ( )  throws Exception {
		PrimaryDataset primaryDS = null;
               	if (!JSONObject.NULL.equals(this.get("PRIMARY_DS_DO"))) {
                       	primaryDS =(PrimaryDataset)this.getJSONObject("PRIMARY_DS_DO");
               	}
                return primaryDS;
        }
	
	ProcessedDataset getProcessedDSDO ( )  throws Exception {
		ProcessedDataset processedDS = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESSED_DS_DO"))) {
                       	processedDS = (ProcessedDataset)this.getJSONObject("PROCESSED_DS_DO");
               	}
                return processedDS;
        }
	
	DataTier getDataTierID ( )  throws Exception {
		DataTier dataTier = null;
               	if (!JSONObject.NULL.equals(this.get("DATA_TIER_DO"))) {
                       	dataTier = (DataTier)this.getJSONObject("DATA_TIER_DO");
               	}
                return dataTier;
        }
	
	DatasetType getDatasetTypeDO ( )  throws Exception {
		DatasetType pathType = null;
               	if (!JSONObject.NULL.equals(this.get("DATASET_TYPE_DO"))) {
                       	pathType = (DatasetType)this.getJSONObject("DATASET_TYPE_DO");
               	}
                return pathType;
        }
	
	AcquisitionEra getAcquisitionEraDO ( )  throws Exception {
		AcquisitionEra acquisitionEra = null;
               	if (!JSONObject.NULL.equals(this.get("ACQUISITION_ERA_DO"))) {
                       	acquisitionEra = (AcquisitionEra)this.getJSONObject("ACQUISITION_ERA_DO");
               	}
                return acquisitionEra;
        }
	
	ProcessingEra getProcessingEraDO ( )  throws Exception {
		ProcessingEra processingEra = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESSING_ERA_DO"))) {
                       	processingEra = (ProcessingEra)this.getJSONObject("PROCESSING_ERA_DO");
               	}
                return processingEra;
        }
	
	PhysicsGroup getPhysicsGroupDO ( )  throws Exception {
		PhysicsGroup physicsGroup = null;
               	if (!JSONObject.NULL.equals(this.get("PHYSICS_GROUP_DO"))) {
                       	physicsGroup = (PhysicsGroup)this.getJSONObject("PHYSICS_GROUP_DO");
               	}
                return physicsGroup;
        }
	
	double getXtcrosssection ( )  throws Exception {
		double xtcrosssection = 0;
               	if (!JSONObject.NULL.equals(this.get("XTCROSSSECTION"))) {
                       	xtcrosssection = this.getDouble("XTCROSSSECTION");
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
