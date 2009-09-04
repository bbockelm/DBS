/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : PRIMARY_DATASETS
*/

package cms.dbs.dataobjs;

public class PrimaryDataset extends JSONObject {

	public PrimaryDataset ( ) {

	}

        public PrimaryDataset ( int primaryDSID, String primaryDSName, int primaryDSTypeID, int creationDate, String createBy )  {
		
                this.putOnce("PRIMARY_DS_ID", (Integer) primaryDSID );
                this.putOnce("PRIMARY_DS_NAME", (String) primaryDSName );
                this.putOnce("PRIMARY_DS_TYPE_ID", (Integer) primaryDSTypeID );
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