/**
 * 
 $Revision: 1.2 $"
 $Id: PrimaryDataset.java,v 1.2 2009/09/04 20:24:48 afaq Exp $"
 *
 * Data Object from table : PRIMARY_DATASETS
*/

package cms.dbs.dataobjs;

import cms.dbs.dataobjs.PrimaryDSType;
import org.json.JSONObject;
import org.json.JSONArray;

public class PrimaryDataset extends JSONObject {

	public PrimaryDataset ( ) {

	}

        public PrimaryDataset ( int primaryDSID, String primaryDSName, PrimaryDSType pType, int creationDate, String createBy )  {
		
                this.putOnce("PRIMARY_DS_ID", (Integer) primaryDSID );
                this.putOnce("PRIMARY_DS_NAME", (String) primaryDSName );
                this.putOnce("PRIMARY_DS_TYPE_DO", pType );
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("CREATE_BY", (String) createBy );
        }

	int getPrimaryDSID ( ) throws Exception{
		int primaryDSID = null;
               	if (!JSONObject.NULL.equals(this.get("PRIMARY_DS_ID"))) {
                       	primaryDSID = (Integer) this.get("PRIMARY_DS_ID");
               	}
                return primaryDSID;
        }
	
	String getPrimaryDSName ( ) throws Exception{
		String primaryDSName = null;
               	if (!JSONObject.NULL.equals(this.get("PRIMARY_DS_NAME"))) {
                       	primaryDSName = (String) this.get("PRIMARY_DS_NAME");
               	}
                return primaryDSName;
        }
	
	int getPrimaryDSTypeDO ( ) throws Exception{
		JSONObject primaryDSType = null;
               	if (! this.isNull("PRIMARY_DS_TYPE_DO")) {
                       	primaryDSType =  this.getJSONObject("PRIMARY_DS_TYPE_DO");
               	}
                return primaryDSType;
        }
	
	int getCreationDate ( ) throws Exception{
		int creationDate = null;
               	if (!JSONObject.NULL.equals(this.get("CREATION_DATE"))) {
                       	creationDate = (Integer) this.get("CREATION_DATE");
               	}
                return creationDate;
        }
	
	String getCreateBy ( ) throws Exception{
		String createBy = null;
               	if (!JSONObject.NULL.equals(this.get("CREATE_BY"))) {
                       	createBy = (String) this.get("CREATE_BY");
               	}
                return createBy;
        }
	
}
