/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getPathRunID ( ) {
		int pathRunID = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_RUN_ID"))) {
                       	pathRunID = (Integer) this.get("PATH_RUN_ID");
               	}
                return pathRunID;
        }
	
	int getPathID ( ) {
		int pathID = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_ID"))) {
                       	pathID = (Integer) this.get("PATH_ID");
               	}
                return pathID;
        }
	
	int getRunNumber ( ) {
		int runNumber = null;
               	if (!JSONObject.NULL.equals(this.get("RUN_NUMBER"))) {
                       	runNumber = (Integer) this.get("RUN_NUMBER");
               	}
                return runNumber;
        }
	
	int getComplete ( ) {
		int complete = null;
               	if (!JSONObject.NULL.equals(this.get("COMPLETE"))) {
                       	complete = (Integer) this.get("COMPLETE");
               	}
                return complete;
        }
	
	int getEventCount ( ) {
		int eventCount = null;
               	if (!JSONObject.NULL.equals(this.get("EVENT_COUNT"))) {
                       	eventCount = (Integer) this.get("EVENT_COUNT");
               	}
                return eventCount;
        }
	
	int getLumiSectionCount ( ) {
		int lumiSectionCount = null;
               	if (!JSONObject.NULL.equals(this.get("LUMI_SECTION_COUNT"))) {
                       	lumiSectionCount = (Integer) this.get("LUMI_SECTION_COUNT");
               	}
                return lumiSectionCount;
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