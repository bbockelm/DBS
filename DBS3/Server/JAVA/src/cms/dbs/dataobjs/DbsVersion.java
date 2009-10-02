/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : DBS_VERSIONS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class DbsVersion extends JSONObject  {

	public DbsVersion ( ) {

	}
		
        public DbsVersion ( int dbsVersionID, String schemaVersion, String dbsReleaseVersion, String instanceName, String instanceType, int creationDate, int lastModificationDate ) throws Exception  {
		
                this.putOnce("DBS_VERSION_ID", (Integer) dbsVersionID );
                this.putOnce("SCHEMA_VERSION", (String) schemaVersion );
                this.putOnce("DBS_RELEASE_VERSION", (String) dbsReleaseVersion );
                this.putOnce("INSTANCE_NAME", (String) instanceName );
                this.putOnce("INSTANCE_TYPE", (String) instanceType );
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("LAST_MODIFICATION_DATE", (Integer) lastModificationDate );
        }

	int getDbsVersionID ( )  throws Exception {
		int dbsVersionID = 0;
               	if (!JSONObject.NULL.equals(this.get("DBS_VERSION_ID"))) {
                       	dbsVersionID = (Integer) this.get("DBS_VERSION_ID");
               	}
                return dbsVersionID;
        }
	
	String getSchemaVersion ( )  throws Exception {
		String schemaVersion = null;
               	if (!JSONObject.NULL.equals(this.get("SCHEMA_VERSION"))) {
                       	schemaVersion = (String) this.get("SCHEMA_VERSION");
               	}
                return schemaVersion;
        }
	
	String getDbsReleaseVersion ( )  throws Exception {
		String dbsReleaseVersion = null;
               	if (!JSONObject.NULL.equals(this.get("DBS_RELEASE_VERSION"))) {
                       	dbsReleaseVersion = (String) this.get("DBS_RELEASE_VERSION");
               	}
                return dbsReleaseVersion;
        }
	
	String getInstanceName ( )  throws Exception {
		String instanceName = null;
               	if (!JSONObject.NULL.equals(this.get("INSTANCE_NAME"))) {
                       	instanceName = (String) this.get("INSTANCE_NAME");
               	}
                return instanceName;
        }
	
	String getInstanceType ( )  throws Exception {
		String instanceType = null;
               	if (!JSONObject.NULL.equals(this.get("INSTANCE_TYPE"))) {
                       	instanceType = (String) this.get("INSTANCE_TYPE");
               	}
                return instanceType;
        }
	
	int getCreationDate ( )  throws Exception {
		int creationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("CREATION_DATE"))) {
                       	creationDate = (Integer) this.get("CREATION_DATE");
               	}
                return creationDate;
        }
	
	int getLastModificationDate ( )  throws Exception {
		int lastModificationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFICATION_DATE"))) {
                       	lastModificationDate = (Integer) this.get("LAST_MODIFICATION_DATE");
               	}
                return lastModificationDate;
        }
	
}