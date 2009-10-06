/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : DATA_TIERS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class DataTier extends JSONObject  {

	public DataTier ( ) {

	}
		
        public DataTier ( int dataTierID, String dataTierName, int creationDate, String createBy ) throws Exception  {
		
                this.putOnce("DATA_TIER_ID", dataTierID );
                this.putOnce("DATA_TIER_NAME", dataTierName );
                this.putOnce("CREATION_DATE", creationDate );
                this.putOnce("CREATE_BY", createBy );
        }

	int getDataTierID ( )  throws Exception {
		int dataTierID = 0;
               	if (!JSONObject.NULL.equals(this.get("DATA_TIER_ID"))) {
                       	dataTierID = this.getInt("DATA_TIER_ID");
               	}
                return dataTierID;
        }
	
	String getDataTierName ( )  throws Exception {
		String dataTierName = null;
               	if (!JSONObject.NULL.equals(this.get("DATA_TIER_NAME"))) {
                       	dataTierName = this.getString("DATA_TIER_NAME");
               	}
                return dataTierName;
        }
	
	int getCreationDate ( )  throws Exception {
		int creationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("CREATION_DATE"))) {
                       	creationDate = this.getInt("CREATION_DATE");
               	}
                return creationDate;
        }
	
	String getCreateBy ( )  throws Exception {
		String createBy = null;
               	if (!JSONObject.NULL.equals(this.get("CREATE_BY"))) {
                       	createBy = this.getString("CREATE_BY");
               	}
                return createBy;
        }
	
}