/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : DATASET_RUNS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class DatasetRun extends JSONObject  {

	public DatasetRun ( ) {

	}
		
        public DatasetRun ( int pathRunID, int datasetID, int runNumber, int complete, int lumiSectionCount, int creationDate, String createBy ) throws Exception  {
		
                this.putOnce("PATH_RUN_ID", (Integer) pathRunID );
                this.putOnce("DATASET_ID", (Integer) datasetID );
                this.putOnce("RUN_NUMBER", (Integer) runNumber );
                this.putOnce("COMPLETE", (Integer) complete );
                this.putOnce("LUMI_SECTION_COUNT", (Integer) lumiSectionCount );
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("CREATE_BY", (String) createBy );
        }

	int getPathRunID ( )  throws Exception {
		int pathRunID = 0;
               	if (!JSONObject.NULL.equals(this.get("PATH_RUN_ID"))) {
                       	pathRunID = (Integer) this.get("PATH_RUN_ID");
               	}
                return pathRunID;
        }
	
	int getDatasetID ( )  throws Exception {
		int datasetID = 0;
               	if (!JSONObject.NULL.equals(this.get("DATASET_ID"))) {
                       	datasetID = (Integer) this.get("DATASET_ID");
               	}
                return datasetID;
        }
	
	int getRunNumber ( )  throws Exception {
		int runNumber = 0;
               	if (!JSONObject.NULL.equals(this.get("RUN_NUMBER"))) {
                       	runNumber = (Integer) this.get("RUN_NUMBER");
               	}
                return runNumber;
        }
	
	int getComplete ( )  throws Exception {
		int complete = 0;
               	if (!JSONObject.NULL.equals(this.get("COMPLETE"))) {
                       	complete = (Integer) this.get("COMPLETE");
               	}
                return complete;
        }
	
	int getLumiSectionCount ( )  throws Exception {
		int lumiSectionCount = 0;
               	if (!JSONObject.NULL.equals(this.get("LUMI_SECTION_COUNT"))) {
                       	lumiSectionCount = (Integer) this.get("LUMI_SECTION_COUNT");
               	}
                return lumiSectionCount;
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
	
}