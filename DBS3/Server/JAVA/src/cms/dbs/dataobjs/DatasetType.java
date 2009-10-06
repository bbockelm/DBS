/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : DATASET_TYPES
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class DatasetType extends JSONObject  {

	public DatasetType ( ) {

	}
		
        public DatasetType ( int datasetTypeID, String datasetType ) throws Exception  {
		
                this.putOnce("DATASET_TYPE_ID", datasetTypeID );
                this.putOnce("DATASET_TYPE", datasetType );
        }

	public void setDatasetTypeID (int datasetTypeID) throws Exception {
 		this.put( "DATASET_TYPE_ID", datasetTypeID );
	}
	
	public void setDatasetType (String datasetType) throws Exception {
 		this.put( "DATASET_TYPE", datasetType );
	}
	
	int getDatasetTypeID ( )  throws Exception {
		int datasetTypeID = 0;
               	if (!JSONObject.NULL.equals(this.get("DATASET_TYPE_ID"))) {
                       	datasetTypeID = this.getInt("DATASET_TYPE_ID");
               	}
                return datasetTypeID;
        }
	
	String getDatasetType ( )  throws Exception {
		String datasetType = null;
               	if (!JSONObject.NULL.equals(this.get("DATASET_TYPE"))) {
                       	datasetType = this.getString("DATASET_TYPE");
               	}
                return datasetType;
        }
	
}