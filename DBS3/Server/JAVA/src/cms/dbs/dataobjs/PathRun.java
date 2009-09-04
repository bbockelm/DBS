/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : PATH_RUNS
*/

package cms.dbs.dataobjs;

public class PathRun extends JSONObject {

	public PathRun ( ) {

	}

        public PathRun ( int pathRunID, int pathID, int runNumber, int complete, int eventCount, int lumiSectionCount, int creationDate, String createBy )  {
		
                this.putOnce("PATH_RUN_ID", (Integer) pathRunID );
                this.putOnce("PATH_ID", (Integer) pathID );
                this.putOnce("RUN_NUMBER", (Integer) runNumber );
                this.putOnce("COMPLETE", (Integer) complete );
                this.putOnce("EVENT_COUNT", (Integer) eventCount );
                this.putOnce("LUMI_SECTION_COUNT", (Integer) lumiSectionCount );
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