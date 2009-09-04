/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
 *
 * Data Object from table : DATA_TIERS
*/

package cms.dbs.dataobjs;

public class DataTier extends JSONObject {

	public DataTier ( ) {

	}

        public DataTier ( int dataTierID, String dataTierName, int creationDate, String createBy )  {
		
                this.putOnce("DATA_TIER_ID", (Integer) dataTierID );
                this.putOnce("DATA_TIER_NAME", (String) dataTierName );
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("CREATE_BY", (String) createBy );
        }

	int getDataTierID ( ) {
		int dataTierID = null;
               	if (!JSONObject.NULL.equals(this.get("DATA_TIER_ID"))) {
                       	dataTierID = (Integer) this.get("DATA_TIER_ID");
               	}
                return dataTierID;
        }
	
	String getDataTierName ( ) {
		String dataTierName = null;
               	if (!JSONObject.NULL.equals(this.get("DATA_TIER_NAME"))) {
                       	dataTierName = (String) this.get("DATA_TIER_NAME");
               	}
                return dataTierName;
        }
	
	int getCreationDate ( ) {
		int creationDate = null;
               	if (!JSONObject.NULL.equals(this.get("CREATION_DATE"))) {
                       	creationDate = (Integer) this.get("CREATION_DATE");
               	}
                return creationDate;
        }
	
	String getCreateBy ( ) {
		String createBy = null;
               	if (!JSONObject.NULL.equals(this.get("CREATE_BY"))) {
                       	createBy = (String) this.get("CREATE_BY");
               	}
                return createBy;
        }
	
}