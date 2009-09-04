/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
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

	int getFileLumiID ( ) {
		int fileLumiID = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_LUMI_ID"))) {
                       	fileLumiID = (Integer) this.get("FILE_LUMI_ID");
               	}
                return fileLumiID;
        }
	
	int getRunNum ( ) {
		int runNum = null;
               	if (!JSONObject.NULL.equals(this.get("RUN_NUM"))) {
                       	runNum = (Integer) this.get("RUN_NUM");
               	}
                return runNum;
        }
	
	int getLumiSectionNum ( ) {
		int lumiSectionNum = null;
               	if (!JSONObject.NULL.equals(this.get("LUMI_SECTION_NUM"))) {
                       	lumiSectionNum = (Integer) this.get("LUMI_SECTION_NUM");
               	}
                return lumiSectionNum;
        }
	
	int getFileID ( ) {
		int fileID = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_ID"))) {
                       	fileID = (Integer) this.get("FILE_ID");
               	}
                return fileID;
        }
	
}