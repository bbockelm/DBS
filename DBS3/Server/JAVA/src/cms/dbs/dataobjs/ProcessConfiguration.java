/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getProcessConfigID ( ) {
		int processConfigID = null;
               	if (!JSONObject.NULL.equals(this.get("PROCESS_CONFIG_ID"))) {
                       	processConfigID = (Integer) this.get("PROCESS_CONFIG_ID");
               	}
                return processConfigID;
        }
	
	int getAppExecID ( ) {
		int appExecID = null;
               	if (!JSONObject.NULL.equals(this.get("APP_EXEC_ID"))) {
                       	appExecID = (Integer) this.get("APP_EXEC_ID");
               	}
                return appExecID;
        }
	
	int getReleaseVersionID ( ) {
		int releaseVersionID = null;
               	if (!JSONObject.NULL.equals(this.get("RELEASE_VERSION_ID"))) {
                       	releaseVersionID = (Integer) this.get("RELEASE_VERSION_ID");
               	}
                return releaseVersionID;
        }
	
	int getParameterSetHashID ( ) {
		int parameterSetHashID = null;
               	if (!JSONObject.NULL.equals(this.get("PARAMETER_SET_HASH_ID"))) {
                       	parameterSetHashID = (Integer) this.get("PARAMETER_SET_HASH_ID");
               	}
                return parameterSetHashID;
        }
	
	String getOutputModuleLabel ( ) {
		String outputModuleLabel = null;
               	if (!JSONObject.NULL.equals(this.get("OUTPUT_MODULE_LABEL"))) {
                       	outputModuleLabel = (String) this.get("OUTPUT_MODULE_LABEL");
               	}
                return outputModuleLabel;
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