/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : DBS_VERSIONS
*/

package cms.dbs.dataobjs;

public class DbsVersion extends JSONObject {

	public DbsVersion ( ) {

	}

        public DbsVersion ( int dbsVersionID, String schemaVersion, String dbsReleaseVersion, String instanceName, String instanceType, int creationDate, int lastModificationDate )  {
		
                this.putOnce("DBS_VERSION_ID", (Integer) dbsVersionID );
                this.putOnce("SCHEMA_VERSION", (String) schemaVersion );
                this.putOnce("DBS_RELEASE_VERSION", (String) dbsReleaseVersion );
                this.putOnce("INSTANCE_NAME", (String) instanceName );
                this.putOnce("INSTANCE_TYPE", (String) instanceType );
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("LAST_MODIFICATION_DATE", (Integer) lastModificationDate );
        }

	int getLastModificationDate ( ) {
		Integer lastModificationDate = null;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFICATION_DATE"))) {
                       	lastModificationDate = (Integer) this.get("LAST_MODIFICATION_DATE");
               	}
                return lastModificationDate;
        }
	
}