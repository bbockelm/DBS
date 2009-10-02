/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : ACQUISITION_ERAS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class AcquisitionEra extends JSONObject  {

	public AcquisitionEra ( ) {

	}
		
        public AcquisitionEra ( int acquisitionEraID, String acquisitionEraName, int creationDate, String createBy, String description ) throws Exception  {
		
                this.putOnce("ACQUISITION_ERA_ID", (Integer) acquisitionEraID );
                this.putOnce("ACQUISITION_ERA_NAME", (String) acquisitionEraName );
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("CREATE_BY", (String) createBy );
                this.putOnce("DESCRIPTION", (String) description );
        }

	int getAcquisitionEraID ( )  throws Exception {
		int acquisitionEraID = 0;
               	if (!JSONObject.NULL.equals(this.get("ACQUISITION_ERA_ID"))) {
                       	acquisitionEraID = (Integer) this.get("ACQUISITION_ERA_ID");
               	}
                return acquisitionEraID;
        }
	
	String getAcquisitionEraName ( )  throws Exception {
		String acquisitionEraName = null;
               	if (!JSONObject.NULL.equals(this.get("ACQUISITION_ERA_NAME"))) {
                       	acquisitionEraName = (String) this.get("ACQUISITION_ERA_NAME");
               	}
                return acquisitionEraName;
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