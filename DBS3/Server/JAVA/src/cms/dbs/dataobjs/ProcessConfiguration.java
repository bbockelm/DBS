/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : PROCESS_CONFIGURATIONS
*/

package cms.dbs.dataobjs;

public class ProcessConfiguration extends JSONObject {

	public ProcessConfiguration ( ) {

	}

        public ProcessConfiguration ( int processConfigID, int appExecID, int releaseVersionID, int parameterSetHashID, String outputModuleLabel, int creationDate, String createBy )  {
		
                this.putOnce("PROCESS_CONFIG_ID", (Integer) processConfigID );
                this.putOnce("APP_EXEC_ID", (Integer) appExecID );
                this.putOnce("RELEASE_VERSION_ID", (Integer) releaseVersionID );
                this.putOnce("PARAMETER_SET_HASH_ID", (Integer) parameterSetHashID );
                this.putOnce("OUTPUT_MODULE_LABEL", (String) outputModuleLabel );
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