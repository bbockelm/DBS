/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : FILE_LUMIS
*/

package cms.dbs.dataobjs;

public class FileLumi extends JSONObject {

	public FileLumi ( ) {

	}

        public FileLumi ( int fileLumiID, int runNum, int lumiSectionNum, int fileID )  {
		
                this.putOnce("FILE_LUMI_ID", (Integer) fileLumiID );
                this.putOnce("RUN_NUM", (Integer) runNum );
                this.putOnce("LUMI_SECTION_NUM", (Integer) lumiSectionNum );
                this.putOnce("FILE_ID", (Integer) fileID );
        }

	int getFileID ( ) {
		Integer fileID = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_ID"))) {
                       	fileID = (Integer) this.get("FILE_ID");
               	}
                return fileID;
        }
	
}