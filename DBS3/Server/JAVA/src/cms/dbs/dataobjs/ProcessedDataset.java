/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : PROCESSED_DATASETS
*/

package cms.dbs.dataobjs;

public class ProcessedDataset extends JSONObject {

	public ProcessedDataset ( ) {

	}

        public ProcessedDataset ( int processedDSID, String processedDSName )  {
		
                this.putOnce("PROCESSED_DS_ID", (Integer) processedDSID );
                this.putOnce("PROCESSED_DS_NAME", (String) processedDSName );
        }

	String getProcessedDSName ( ) {
		String processedDSName = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESSED_DS_NAME"))) {
                       	processedDSName = (String) this.get("PROCESSED_DS_NAME");
               	}
                return processedDSName;
        }
	
}