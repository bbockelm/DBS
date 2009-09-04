/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
 *
 * Data Object from table : PROCESSING_ERAS
*/

package cms.dbs.dataobjs;

public class ProcessingEra extends JSONObject {

	public ProcessingEra ( ) {

	}

        public ProcessingEra ( int processingEraID, String processingEraName )  {
		
                this.putOnce("PROCESSING_ERA_ID", (Integer) processingEraID );
                this.putOnce("PROCESSING_ERA_NAME", (String) processingEraName );
        }

	int getProcessingEraID ( ) {
		int processingEraID = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESSING_ERA_ID"))) {
                       	processingEraID = (Integer) this.get("PROCESSING_ERA_ID");
               	}
                return processingEraID;
        }
	
	String getProcessingEraName ( ) {
		String processingEraName = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESSING_ERA_NAME"))) {
                       	processingEraName = (String) this.get("PROCESSING_ERA_NAME");
               	}
                return processingEraName;
        }
	
}