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
		
                this.putOnce("DATASET_TYPE_ID", (Integer) datasetTypeID );
                this.putOnce("DATASET_TYPE", (String) datasetType );
        }

	int getDatasetTypeID ( )  throws Exception {
		int datasetTypeID = 0;
               	if (!JSONObject.NULL.equals(this.get("DATASET_TYPE_ID"))) {
                       	datasetTypeID = (Integer) this.get("DATASET_TYPE_ID");
               	}
                return datasetTypeID;
        }
	
	String getDatasetType ( )  throws Exception {
		String datasetType = null;
               	if (!JSONObject.NULL.equals(this.get("DATASET_TYPE"))) {
                       	datasetType = (String) this.get("DATASET_TYPE");
               	}
                return datasetType;
        }
	
}