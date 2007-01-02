/**
 $Revision: 1.1 $"
 $Id: DBSApiTransferLogic.java,v 1.1 2006/12/15 20:54:03 sekhri Exp $"
 *
 */

package dbs.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.Writer;
import java.util.Hashtable;
import java.util.Vector;
import dbs.sql.DBSSql;
import dbs.util.DBSUtil;

/**
* A class that has the core business logic of the DBS API for transfer. 
* @author sekhri
*/
public class DBSApiTransferLogic extends  DBSApiLogic {
		
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs. The constructor does notthing.
	*/
	public DBSApiTransferLogic() {}



	/**
	 * Lists all the contents of a processed dataset in a xml format. This method calls lsitPrimaryDataset, listProcessedDataset, listRuns, listBlocks and listFiles one after another with the given path. This methods is used for propogation dataset from one DBS server to another. It writes a complete snapshot of a processed dataset on the output stream.  A sample XML that is written to the output stream is like <br>
	 * <code>  </code>
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param path a dataset path in the format of /primary/tier/processed. This path is used to find the existing processed dataset id.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied path is invalid, the database connection is unavailable or processed dataset is not found.
	 */
	public void listDatasetContents(Connection conn, Writer out, String path, String blockName) throws Exception {
		String data[] = parseDSPath(path);
		(new DBSApiBlockLogic()).checkBlock(blockName);
		out.write(((String) "<dataset path='" + path + 
					"' block_name='" + blockName +
					"' />\n"));
		(new DBSApiPrimDSLogic()).listPrimaryDatasets(conn, out, data[1]);
		DBSApiProcDSLogic pdApi = new DBSApiProcDSLogic();
		pdApi.listProcessedDatasets(conn, out, data[1], data[2], data[3], null, null, null, null);
		pdApi.listDatasetParents(conn, out, path);
		pdApi.listRuns(conn, out, path);
		pdApi.listBlocks(conn, out, path, blockName, null);
		(new DBSApiFileLogic()).listFiles(conn, out, path, blockName, null, "true");
	}
	
	


	/**
	 * Insert a complete dataset with all of its contents passedas a  <code>java.util.Hashtable</code>. This hashtable is generated externally and filled in with the all the processed dataset information awith all the files, algo, run and lumi information. It calls all the other insert API to insert the processed dataset contents
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param table a <code>java.util.Hastable</code>  that contain all the necessary key value pairs required for inserting a new all of the processed dataset contents. The keys along with its values that it may or may not contain are <br>
	 * <code>lumi_section_number, run_number, start_event_number, end_event_number, lumi_start_time, lumi_end_time </code> <br>
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable.
	 */
	public void insertDatasetContents(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {
		//FIXME dont pass dbsUser instaed get it from the table
		String path = getPath(table, "path", true);
		String blockName = (new DBSApiBlockLogic()).getBlock(table, "block_name", true);

		(new DBSApiPrimDSLogic()).insertPrimaryDataset(conn, out, DBSUtil.getTable(table, "primary-dataset"), dbsUser);
		Hashtable pdTable = DBSUtil.getTable(table, "processed-dataset");
		Vector algoVector = DBSUtil.getVector(pdTable, "algorithm");
		for (int j = 0; j < algoVector.size(); ++j) 
			(new DBSApiAlgoLogic()).insertAlgorithm(conn, out, (Hashtable)algoVector.get(j), dbsUser);
		
		Vector runVector = DBSUtil.getVector(pdTable, "run");
		for (int j = 0; j < runVector.size(); ++j) 
			insertRun(conn, out, (Hashtable)runVector.get(j), dbsUser);
		
		(new DBSApiProcDSLogic()).insertProcessedDataset(conn, out, pdTable, dbsUser);
		Vector blockVector = DBSUtil.getVector(pdTable, "block");
		for (int j = 0; j < blockVector.size(); ++j) 
			(new DBSApiBlockLogic()).insertBlock(conn, out, (Hashtable)blockVector.get(j), dbsUser);
		
		(new DBSApiFileLogic()).insertFiles(conn, out, path, blockName, DBSUtil.getVector(table, "file"), dbsUser);

		
	}

}
