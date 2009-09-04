/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getPrimaryDSID ( ) {
		int primaryDSID = null;
               	if (!JSONObject.NULL.equals(this.get("PRIMARY_DS_ID"))) {
                       	primaryDSID = (Integer) this.get("PRIMARY_DS_ID");
               	}
                return primaryDSID;
        }
	
	String getPrimaryDSName ( ) {
		String primaryDSName = null;
               	if (!JSONObject.NULL.equals(this.get("PRIMARY_DS_NAME"))) {
                       	primaryDSName = (String) this.get("PRIMARY_DS_NAME");
               	}
                return primaryDSName;
        }
	
	int getPrimaryDSTypeID ( ) {
		int primaryDSTypeID = null;
               	if (!JSONObject.NULL.equals(this.get("PRIMARY_DS_TYPE_ID"))) {
                       	primaryDSTypeID = (Integer) this.get("PRIMARY_DS_TYPE_ID");
               	}
                return primaryDSTypeID;
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