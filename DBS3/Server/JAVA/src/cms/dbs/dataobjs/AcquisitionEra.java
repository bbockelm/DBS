/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getAcquisitionEraID ( ) {
		int acquisitionEraID = null;
               	if (!JSONObject.NULL.equals(this.get("ACQUISITION_ERA_ID"))) {
                       	acquisitionEraID = (Integer) this.get("ACQUISITION_ERA_ID");
               	}
                return acquisitionEraID;
        }
	
	String getAcquisitionEraName ( ) {
		String acquisitionEraName = null;
               	if (!JSONObject.NULL.equals(this.get("ACQUISITION_ERA_NAME"))) {
                       	acquisitionEraName = (String) this.get("ACQUISITION_ERA_NAME");
               	}
                return acquisitionEraName;
        }
	
}