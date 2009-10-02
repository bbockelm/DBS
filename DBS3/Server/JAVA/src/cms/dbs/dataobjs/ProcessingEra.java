/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : PROCESSING_ERAS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class ProcessingEra extends JSONObject  {

	public ProcessingEra ( ) {

	}
		
        public ProcessingEra ( int processingEraID, String processingVersion, int creationDate, String createBy, String description ) throws Exception  {
		
                this.putOnce("PROCESSING_ERA_ID", (Integer) processingEraID );
                this.putOnce("PROCESSING_VERSION", (String) processingVersion );
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("CREATE_BY", (String) createBy );
                this.putOnce("DESCRIPTION", (String) description );
        }

	int getProcessingEraID ( )  throws Exception {
		int processingEraID = 0;
               	if (!JSONObject.NULL.equals(this.get("PROCESSING_ERA_ID"))) {
                       	processingEraID = (Integer) this.get("PROCESSING_ERA_ID");
               	}
                return processingEraID;
        }
	
	String getProcessingVersion ( )  throws Exception {
		String processingVersion = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESSING_VERSION"))) {
                       	processingVersion = (String) this.get("PROCESSING_VERSION");
               	}
                return processingVersion;
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
	
	String getDescription ( )  throws Exception {
		String description = null;
               	if (!JSONObject.NULL.equals(this.get("DESCRIPTION"))) {
                       	description = (String) this.get("DESCRIPTION");
               	}
                return description;
        }
	
}