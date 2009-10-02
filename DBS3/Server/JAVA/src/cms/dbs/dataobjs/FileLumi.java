/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : FILE_LUMIS
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class FileLumi extends JSONObject  {

	public FileLumi ( ) {

	}
		
        public FileLumi ( int fileLumiID, int runNum, int lumiSectionNum, int fileID ) throws Exception  {
		
                this.putOnce("FILE_LUMI_ID", (Integer) fileLumiID );
                this.putOnce("RUN_NUM", (Integer) runNum );
                this.putOnce("LUMI_SECTION_NUM", (Integer) lumiSectionNum );
                this.putOnce("FILE_ID", (Integer) fileID );
        }

	int getFileLumiID ( )  throws Exception {
		int fileLumiID = 0;
               	if (!JSONObject.NULL.equals(this.get("FILE_LUMI_ID"))) {
                       	fileLumiID = (Integer) this.get("FILE_LUMI_ID");
               	}
                return fileLumiID;
        }
	
	int getRunNum ( )  throws Exception {
		int runNum = 0;
               	if (!JSONObject.NULL.equals(this.get("RUN_NUM"))) {
                       	runNum = (Integer) this.get("RUN_NUM");
               	}
                return runNum;
        }
	
	int getLumiSectionNum ( )  throws Exception {
		int lumiSectionNum = 0;
               	if (!JSONObject.NULL.equals(this.get("LUMI_SECTION_NUM"))) {
                       	lumiSectionNum = (Integer) this.get("LUMI_SECTION_NUM");
               	}
                return lumiSectionNum;
        }
	
	int getFileID ( )  throws Exception {
		int fileID = 0;
               	if (!JSONObject.NULL.equals(this.get("FILE_ID"))) {
                       	fileID = (Integer) this.get("FILE_ID");
               	}
                return fileID;
        }
	
}