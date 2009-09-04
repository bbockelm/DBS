/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : ACQUISITION_ERAS
*/

package cms.dbs.dataobjs;

public class AcquisitionEra extends JSONObject {

	public AcquisitionEra ( ) {

	}

        public AcquisitionEra ( int acquisitionEraID, String acquisitionEraName )  {
		
                this.putOnce("ACQUISITION_ERA_ID", (Integer) acquisitionEraID );
                this.putOnce("ACQUISITION_ERA_NAME", (String) acquisitionEraName );
        }

	String getAcquisitionEraName ( ) {
		String acquisitionEraName = null;
               	if (!JSONObject.NULL.equals(this.get("ACQUISITION_ERA_NAME"))) {
                       	acquisitionEraName = (String) this.get("ACQUISITION_ERA_NAME");
               	}
                return acquisitionEraName;
        }
	
}