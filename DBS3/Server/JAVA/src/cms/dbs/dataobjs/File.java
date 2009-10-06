/**
 * 
 $Revision: $"
 $Id: $"
 *
 * Data Object from table : FILES
*/

package cms.dbs.dataobjs;

import org.json.JSONObject;

public class File extends JSONObject  {

	public File ( ) {

	}
		
        public File ( int fileID, String logicalFileName, int isFileValid, int pathID, int blockID, int fileTypeID, String checkSum, int eventCount, int fileSize, int branchHashID, String adler32, String md5, float autoCrossSection, int creationDate, String createBy, int lastModificationDate, String lastModifiedBy ) throws Exception  {
		
                this.putOnce("FILE_ID", fileID );
                this.putOnce("LOGICAL_FILE_NAME", logicalFileName );
                this.putOnce("IS_FILE_VALID", isFileValid );
                this.putOnce("PATH_ID", pathID );
                this.putOnce("BLOCK_ID", blockID );
                this.putOnce("FILE_TYPE_ID", fileTypeID );
                this.putOnce("CHECK_SUM", checkSum );
                this.putOnce("EVENT_COUNT", eventCount );
                this.putOnce("FILE_SIZE", fileSize );
                this.putOnce("BRANCH_HASH_ID", branchHashID );
                this.putOnce("ADLER32", adler32 );
                this.putOnce("MD5", md5 );
                this.putOnce("AUTO_CROSS_SECTION", autoCrossSection );
                this.putOnce("CREATION_DATE", creationDate );
                this.putOnce("CREATE_BY", createBy );
                this.putOnce("LAST_MODIFICATION_DATE", lastModificationDate );
                this.putOnce("LAST_MODIFIED_BY", lastModifiedBy );
        }

	int getFileID ( )  throws Exception {
		int fileID = 0;
               	if (!JSONObject.NULL.equals(this.get("FILE_ID"))) {
                       	fileID = this.getInt("FILE_ID");
               	}
                return fileID;
        }
	
	String getLogicalFileName ( )  throws Exception {
		String logicalFileName = null;
               	if (!JSONObject.NULL.equals(this.get("LOGICAL_FILE_NAME"))) {
                       	logicalFileName = this.getString("LOGICAL_FILE_NAME");
               	}
                return logicalFileName;
        }
	
	int getIsFileValid ( )  throws Exception {
		int isFileValid = 0;
               	if (!JSONObject.NULL.equals(this.get("IS_FILE_VALID"))) {
                       	isFileValid = this.getInt("IS_FILE_VALID");
               	}
                return isFileValid;
        }
	
	int getPathID ( )  throws Exception {
		int pathID = 0;
               	if (!JSONObject.NULL.equals(this.get("PATH_ID"))) {
                       	pathID = this.getInt("PATH_ID");
               	}
                return pathID;
        }
	
	int getBlockID ( )  throws Exception {
		int blockID = 0;
               	if (!JSONObject.NULL.equals(this.get("BLOCK_ID"))) {
                       	blockID = this.getInt("BLOCK_ID");
               	}
                return blockID;
        }
	
	int getFileTypeID ( )  throws Exception {
		int fileTypeID = 0;
               	if (!JSONObject.NULL.equals(this.get("FILE_TYPE_ID"))) {
                       	fileTypeID = this.getInt("FILE_TYPE_ID");
               	}
                return fileTypeID;
        }
	
	String getCheckSum ( )  throws Exception {
		String checkSum = null;
               	if (!JSONObject.NULL.equals(this.get("CHECK_SUM"))) {
                       	checkSum = this.getString("CHECK_SUM");
               	}
                return checkSum;
        }
	
	int getEventCount ( )  throws Exception {
		int eventCount = 0;
               	if (!JSONObject.NULL.equals(this.get("EVENT_COUNT"))) {
                       	eventCount = this.getInt("EVENT_COUNT");
               	}
                return eventCount;
        }
	
	int getFileSize ( )  throws Exception {
		int fileSize = 0;
               	if (!JSONObject.NULL.equals(this.get("FILE_SIZE"))) {
                       	fileSize = this.getInt("FILE_SIZE");
               	}
                return fileSize;
        }
	
	int getBranchHashID ( )  throws Exception {
		int branchHashID = 0;
               	if (!JSONObject.NULL.equals(this.get("BRANCH_HASH_ID"))) {
                       	branchHashID = this.getInt("BRANCH_HASH_ID");
               	}
                return branchHashID;
        }
	
	String getAdler32 ( )  throws Exception {
		String adler32 = null;
               	if (!JSONObject.NULL.equals(this.get("ADLER32"))) {
                       	adler32 = this.getString("ADLER32");
               	}
                return adler32;
        }
	
	String getMd5 ( )  throws Exception {
		String md5 = null;
               	if (!JSONObject.NULL.equals(this.get("MD5"))) {
                       	md5 = this.getString("MD5");
               	}
                return md5;
        }
	
	float getAutoCrossSection ( )  throws Exception {
		float autoCrossSection = 0;
               	if (!JSONObject.NULL.equals(this.get("AUTO_CROSS_SECTION"))) {
                       	autoCrossSection = this.getFloat("AUTO_CROSS_SECTION");
               	}
                return autoCrossSection;
        }
	
	int getCreationDate ( )  throws Exception {
		int creationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("CREATION_DATE"))) {
                       	creationDate = this.getInt("CREATION_DATE");
               	}
                return creationDate;
        }
	
	String getCreateBy ( )  throws Exception {
		String createBy = null;
               	if (!JSONObject.NULL.equals(this.get("CREATE_BY"))) {
                       	createBy = this.getString("CREATE_BY");
               	}
                return createBy;
        }
	
	int getLastModificationDate ( )  throws Exception {
		int lastModificationDate = 0;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFICATION_DATE"))) {
                       	lastModificationDate = this.getInt("LAST_MODIFICATION_DATE");
               	}
                return lastModificationDate;
        }
	
	String getLastModifiedBy ( )  throws Exception {
		String lastModifiedBy = null;
               	if (!JSONObject.NULL.equals(this.get("LAST_MODIFIED_BY"))) {
                       	lastModifiedBy = this.getString("LAST_MODIFIED_BY");
               	}
                return lastModifiedBy;
        }
	
}