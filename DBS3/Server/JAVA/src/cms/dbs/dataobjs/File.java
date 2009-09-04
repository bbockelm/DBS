/**
 * 
 $Revision: 1.2 $"
 $Id: generate_dataobjs.py,v 1.2 2009/09/04 20:21:16 afaq Exp $"
 *
 * Data Object from table : FILES
*/

package cms.dbs.dataobjs;

public class File extends JSONObject {

	public File ( ) {

	}

        public File ( int fileID, String logicalFileName, int isFileValid, int pathID, int blockID, int fileTypeID, String checkSum, int eventCount, int fileSize, int branchHashID, String adler32, String md5, float autoCrossSection, int creationDate, String createBy, int lastmodificationdate, String lastModifiedBy )  {
		
                this.putOnce("FILE_ID", (Integer) fileID );
                this.putOnce("LOGICAL_FILE_NAME", (String) logicalFileName );
                this.putOnce("IS_FILE_VALID", (Integer) isFileValid );
                this.putOnce("PATH_ID", (Integer) pathID );
                this.putOnce("BLOCK_ID", (Integer) blockID );
                this.putOnce("FILE_TYPE_ID", (Integer) fileTypeID );
                this.putOnce("CHECK_SUM", (String) checkSum );
                this.putOnce("EVENT_COUNT", (Integer) eventCount );
                this.putOnce("FILE_SIZE", (Integer) fileSize );
                this.putOnce("BRANCH_HASH_ID", (Integer) branchHashID );
                this.putOnce("ADLER32", (String) adler32 );
                this.putOnce("MD5", (String) md5 );
                this.putOnce("AUTO_CROSS_SECTION", (Float) autoCrossSection );
                this.putOnce("CREATION_DATE", (Integer) creationDate );
                this.putOnce("CREATE_BY", (String) createBy );
                this.putOnce("LASTMODIFICATIONDATE", (Integer) lastmodificationdate );
                this.putOnce("LAST_MODIFIED_BY", (String) lastModifiedBy );
        }

	int getFileID ( ) {
		int fileID = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_ID"))) {
                       	fileID = (Integer) this.get("FILE_ID");
               	}
                return fileID;
        }
	
	String getLogicalFileName ( ) {
		String logicalFileName = null;
               	if (!JSONObject.NULL.equals(this.get("LOGICAL_FILE_NAME"))) {
                       	logicalFileName = (String) this.get("LOGICAL_FILE_NAME");
               	}
                return logicalFileName;
        }
	
	int getIsFileValid ( ) {
		int isFileValid = null;
               	if (!JSONObject.NULL.equals(this.get("IS_FILE_VALID"))) {
                       	isFileValid = (Integer) this.get("IS_FILE_VALID");
               	}
                return isFileValid;
        }
	
	int getPathID ( ) {
		int pathID = null;
               	if (!JSONObject.NULL.equals(this.get("PATH_ID"))) {
                       	pathID = (Integer) this.get("PATH_ID");
               	}
                return pathID;
        }
	
	int getBlockID ( ) {
		int blockID = null;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_ID"))) {
                       	blockID = (Integer) this.get("BLOCK_ID");
               	}
                return blockID;
        }
	
	int getFileTypeID ( ) {
		int fileTypeID = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_TYPE_ID"))) {
                       	fileTypeID = (Integer) this.get("FILE_TYPE_ID");
               	}
                return fileTypeID;
        }
	
	String getCheckSum ( ) {
		String checkSum = null;
               	if (!JSONObject.NULL.equals(this.get("CHECK_SUM"))) {
                       	checkSum = (String) this.get("CHECK_SUM");
               	}
                return checkSum;
        }
	
	int getEventCount ( ) {
		int eventCount = null;
               	if (!JSONObject.NULL.equals(this.get("EVENT_COUNT"))) {
                       	eventCount = (Integer) this.get("EVENT_COUNT");
               	}
                return eventCount;
        }
	
	int getFileSize ( ) {
		int fileSize = null;
               	if (!JSONObject.NULL.equals(this.get("FILE_SIZE"))) {
                       	fileSize = (Integer) this.get("FILE_SIZE");
               	}
                return fileSize;
        }
	
	int getBranchHashID ( ) {
		int branchHashID = null;
               	if (!JSONObject.NULL.equals(this.get("BRANCH_HASH_ID"))) {
                       	branchHashID = (Integer) this.get("BRANCH_HASH_ID");
               	}
                return branchHashID;
        }
	
	String getAdler32 ( ) {
		String adler32 = null;
               	if (!JSONObject.NULL.equals(this.get("ADLER32"))) {
                       	adler32 = (String) this.get("ADLER32");
               	}
                return adler32;
        }
	
	String getMd5 ( ) {
		String md5 = null;
               	if (!JSONObject.NULL.equals(this.get("MD5"))) {
                       	md5 = (String) this.get("MD5");
               	}
                return md5;
        }
	
	float getAutoCrossSection ( ) {
		float autoCrossSection = null;
               	if (!JSONObject.NULL.equals(this.get("AUTO_CROSS_SECTION"))) {
                       	autoCrossSection = (Float) this.get("AUTO_CROSS_SECTION");
               	}
                return autoCrossSection;
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
	
	int getLastmodificationdate ( ) {
		int lastmodificationdate = null;
               	if (!JSONObject.NULL.equals(this.get("LASTMODIFICATIONDATE"))) {
                       	lastmodificationdate = (Integer) this.get("LASTMODIFICATIONDATE");
               	}
                return lastmodificationdate;
        }
	
	String getLastModifiedBy ( ) {
		String lastModifiedBy = null;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFIED_BY"))) {
                       	lastModifiedBy = (String) this.get("LAST_MODIFIED_BY");
               	}
                return lastModifiedBy;
        }
	
}