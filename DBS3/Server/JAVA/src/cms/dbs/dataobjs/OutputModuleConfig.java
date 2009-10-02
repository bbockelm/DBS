/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : OUTPUT_MODULE_CONFIGS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class OutputModuleConfig extends JSONObject  {

	public OutputModuleConfig ( ) {

	}
		
        public OutputModuleConfig ( int outputModConfigID, int appExecID, int releaseVersionID, int parameterSetHashID, String outputModuleLabel, int creationDate, String createBy ) throws Exception  {
		
                this.putOnce("OUTPUT_MOD_CONFIG_ID", (Integer) outputModConfigID );
                this.putOnce("APP_EXEC_ID", (Integer) appExecID );
                this.putOnce("RELEASE_VERSION_ID", (Integer) releaseVersionID );
                this.putOnce("PARAMETER_SET_HASH_ID", (Integer) parameterSetHashID );
                this.putOnce("OUTPUT_MODULE_LABEL", (String) outputModuleLabel );
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("CREATE_BY", (String) createBy );
        }

	int getOutputModConfigID ( )  throws Exception {
		int outputModConfigID = 0;
               	if (!JSONObject.NULL.equals(this.get("OUTPUT_MOD_CONFIG_ID"))) {
                       	outputModConfigID = (Integer) this.get("OUTPUT_MOD_CONFIG_ID");
               	}
                return outputModConfigID;
        }
	
	int getAppExecID ( )  throws Exception {
		int appExecID = 0;
               	if (!JSONObject.NULL.equals(this.get("APP_EXEC_ID"))) {
                       	appExecID = (Integer) this.get("APP_EXEC_ID");
               	}
                return appExecID;
        }
	
	int getReleaseVersionID ( )  throws Exception {
		int releaseVersionID = 0;
               	if (!JSONObject.NULL.equals(this.get("RELEASE_VERSION_ID"))) {
                       	releaseVersionID = (Integer) this.get("RELEASE_VERSION_ID");
               	}
                return releaseVersionID;
        }
	
	int getParameterSetHashID ( )  throws Exception {
		int parameterSetHashID = 0;
               	if (!JSONObject.NULL.equals(this.get("PARAMETER_SET_HASH_ID"))) {
                       	parameterSetHashID = (Integer) this.get("PARAMETER_SET_HASH_ID");
               	}
                return parameterSetHashID;
        }
	
	String getOutputModuleLabel ( )  throws Exception {
		String outputModuleLabel = null;
               	if (!JSONObject.NULL.equals(this.get("OUTPUT_MODULE_LABEL"))) {
                       	outputModuleLabel = (String) this.get("OUTPUT_MODULE_LABEL");
               	}
                return outputModuleLabel;
        }
	
	int getCreationDate ( )  throws Exception {
		int creationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("CREATION_DATE"))) {
                       	creationDate = (Integer) this.get("CREATION_DATE");
               	}
                return creationDate;
        }
	
	String getCreateBy ( )  throws Exception {
		String createBy = null;
               	if (!JSONObject.NULL.equals(this.get("CREATE_BY"))) {
                       	createBy = (String) this.get("CREATE_BY");
               	}
                return createBy;
        }
	
}