/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : DATASET_PARENTS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class DatasetParent extends JSONObject  {

	public DatasetParent ( ) {

	}
		
        public DatasetParent ( int datasetParentID, int thisDatasetID, int parentDatasetID ) throws Exception  {
		
                this.putOnce("DATASET_PARENT_ID", (Integer) datasetParentID );
                this.putOnce("THIS_DATASET_ID", (Integer) thisDatasetID );
                this.putOnce("PARENT_DATASET_ID", (Integer) parentDatasetID );
        }

	int getDatasetParentID ( )  throws Exception {
		int datasetParentID = 0;
               	if (!JSONObject.NULL.equals(this.get("DATASET_PARENT_ID"))) {
                       	datasetParentID = (Integer) this.get("DATASET_PARENT_ID");
               	}
                return datasetParentID;
        }
	
	int getThisDatasetID ( )  throws Exception {
		int thisDatasetID = 0;
               	if (!JSONObject.NULL.equals(this.get("THIS_DATASET_ID"))) {
                       	thisDatasetID = (Integer) this.get("THIS_DATASET_ID");
               	}
                return thisDatasetID;
        }
	
	int getParentDatasetID ( )  throws Exception {
		int parentDatasetID = 0;
               	if (!JSONObject.NULL.equals(this.get("PARENT_DATASET_ID"))) {
                       	parentDatasetID = (Integer) this.get("PARENT_DATASET_ID");
               	}
                return parentDatasetID;
        }
	
}