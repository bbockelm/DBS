/**
 * 
 $Revision: $"
 $Id: $"
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

	String getProcessingEraName ( ) {
		String processingEraName = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESSING_ERA_NAME"))) {
                       	processingEraName = (String) this.get("PROCESSING_ERA_NAME");
               	}
                return processingEraName;
        }
	
}