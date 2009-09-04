/**
 * 
 $Revision: $"
 $Id: $"
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

	String getCreateBy ( ) {
		String createBy = null;
               	if (!JSONObject.NULL.equals(this.get("CREATE_BY"))) {
                       	createBy = (String) this.get("CREATE_BY");
               	}
                return createBy;
        }
	
}