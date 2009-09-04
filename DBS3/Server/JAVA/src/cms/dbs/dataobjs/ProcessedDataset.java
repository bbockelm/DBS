/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getProcessedDSID ( ) {
		int processedDSID = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESSED_DS_ID"))) {
                       	processedDSID = (Integer) this.get("PROCESSED_DS_ID");
               	}
                return processedDSID;
        }
	
	String getProcessedDSName ( ) {
		String processedDSName = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESSED_DS_NAME"))) {
                       	processedDSName = (String) this.get("PROCESSED_DS_NAME");
               	}
                return processedDSName;
        }
	
}