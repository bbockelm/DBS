/**
 $Revision: 1.3 $"
 $Id: DBSApiProcQuality.java,v 1.3 2008/12/03 20:44:31 afaq Exp $"
 *
 */

package dbs.api;
import java.sql.Connection;
import db.DBManagement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.util.Hashtable;
import dbs.sql.DBSSql;
import dbs.util.DBSUtil;
import dbs.DBSException;
import java.util.Vector;
import java.util.Date;
import dbs.data.DBSDataCache;
import java.util.ArrayList;
import dbs.DBSConstants;

/**
* A class that has the core business logic of all the Primary dataset APIs.  The signature for the API is internal to DBS and is not exposed to the clients. There is another class <code>dbs.api.DBSApi</code> that has an interface for the clients. All these low level APIs are invoked from <code>dbs.api.DBSApi</code>. This class inherits from DBSApiLogic class.
* @author anzar
*/
public class DBSApiProcQuality extends DBSApiLogic {
		
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs.
	*/

	DBSApiPersonLogic personApi = null;
	DBSApiData data = null;
	public DBSApiProcQuality(DBSApiData data) {
		super(data);
		this.data = data;
		personApi = new DBSApiPersonLogic(data);
	}

        //Inserts Processing Quality information for a File
	//Why it failed to produce a childFile for a childDataset
        public void insertFileProcQuality(Connection conn, Writer out, Hashtable fileProcInfo, Hashtable dbsUser) throws Exception
        {

		String lfn = get(fileProcInfo, "lfn", true);
		String fileID = getID(conn, "Files", "LogicalFileName", lfn, true);

		String childDatasetID = (new DBSApiProcDSLogic(this.data)).getProcessedDSID(conn, 
									get(fileProcInfo, "child_dataset", true), true);
		String processingStatusID = getID(conn, "ProcessingStatus", "ProcessingStatus", 
							get(fileProcInfo, "processing_status", true) , true);
		String failedEventCount = get(fileProcInfo, "failed_event_count", false);
		String failedEventList = getStr(fileProcInfo, "failed_event_list", false);
		String desc = getStr(fileProcInfo, "description", true);

                String lmbUserID = personApi.getUserID(conn, dbsUser);
                String cbUserID = personApi.getUserID(conn, get(fileProcInfo, "created_by"), dbsUser );
                String creationDate = getTime(fileProcInfo, "creation_date", false);

		PreparedStatement ps = null;
		try {
			ps = DBSSql.insertFileProcQuality(conn, fileID, childDatasetID, 
								processingStatusID, failedEventCount, failedEventList, desc,
								cbUserID, lmbUserID, creationDate);
                        if (DBSConstants.DEBUG) pushQuery(ps);
                        ps.execute();

                } finally {
                        if (ps != null) ps.close();
                }
        }

        public void listFileProcQuality(Connection conn, Writer out, String lfn, String path) throws Exception
        {


		if ( DBSUtil.isNull(lfn) && DBSUtil.isNull(path) )
			throw new DBSException("Missing data", "1006", "Null field. Expected a valid LFN or DATASETPath" );

                PreparedStatement ps = null;
		ResultSet rs = null;
                try {
                        ps = DBSSql.listFileProcQuality(conn, lfn, path);
                        if (DBSConstants.DEBUG) pushQuery(ps);
                        rs = ps.executeQuery();
			ArrayList alreadyThere = new ArrayList();
                        while(rs.next()) {
				String id = get(rs, "ID");
				if (!alreadyThere.contains(id)) {
					out.write(((String) "<file_proc_quality "+
						" id='" + id + "'" +
						" lfn='" + get(rs, "LFN") + "'" +
						" child_dataset='" + get(rs, "CHILDDATASET") + "'" +
						" failed_event_count='" + get(rs, "FAILEDEVENTCOUNT") + "'" +
						" failed_event_list='" + get(rs, "FAILEDEVENTLIST") + "'" +
						" description='" + get(rs, "DESCRIPTION") + "'" +
						" processing_status='" + get(rs, "PROCESSINGSTATUS") + "'" +
                                                " creation_date='" + getTime(rs, "CREATION_DATE") + "'" +
                                                " last_modification_date='" + get(rs, "LAST_MODIFICATION_DATE") + "'" +
                                                " created_by='" + get(rs, "CREATED_BY") + "'" +
                                                " last_modified_by='" + get(rs, "LAST_MODIFIED_BY") + "'" +
						" />"
						));	
					alreadyThere.add(id);
				}
			}
                } finally {
			if (rs != null) rs.close();
                        if (ps != null) ps.close();
                }
	}

}
