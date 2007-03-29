/**
 $Revision: 1.18 $"
 $Id: DBSApiTransferLogic.java,v 1.18 2007/03/27 03:05:02 sekhri Exp $"
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
* A class that has the core business logic of all the transfer APIs.  The signature for the API is internal to DBS and is not exposed to the clients. There is another class <code>dbs.api.DBSApi</code> that has an interface for the clients. All these low level APIs are invoked from <code>dbs.api.DBSApi</code>. This class inherits from DBSApiLogic class.
* @author sekhri
*/
public class DBSApiTransferLogic extends  DBSApiLogic {
		
	/**
	* Constructs a DBSApiLogic object that can be used to invoke several APIs. The constructor does nothing.
	*/
	DBSApiData data = null;
	public DBSApiTransferLogic(DBSApiData data) {
		super(data);
		this.data = data;
	}



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
		DBSApiBlockLogic bApi = new DBSApiBlockLogic(this.data);
		bApi.checkBlock(blockName);
                
		out.write(((String) "<dataset path='" + path + 
					"' block_name='" + blockName +
					"' />\n"));
                
                //Format of insertFile xml has modified slightly, hance changed required here, AA 01/18/2007
               /* out.write(((String) "<dataset path='" + path + "'/>\n" +
                                    "<block block_name='" + blockName +"'>\n"+
                                    "</block>\n")); */

                //FIXME: We need to add storage_elemnts in above xml as well,  AA 01/18/2007 

		(new DBSApiPrimDSLogic(this.data)).listPrimaryDatasets(conn, out, data[1]);
		DBSApiProcDSLogic pdApi = new DBSApiProcDSLogic(this.data);
		//pdApi.listProcessedDatasets(conn, out, data[1], data[3], data[2], null, null, null, null);
		pdApi.listProcessedDatasets(conn, out, data[1], "", data[2], null, null, null, null);
		pdApi.listDatasetParents(conn, out, path);
		pdApi.listRuns(conn, out, path);
		bApi.listBlocks(conn, out, path, blockName, null);
		//(new DBSApiFileLogic(this.data)).listFiles(conn, out, path, "", blockName, null, "true");
		(new DBSApiFileLogic(this.data)).listFiles(conn, out, "", data[1], data[2], data[3], "", blockName, null, "true");
	}
	
	


	/**
	 * Insert a complete dataset with all of its contents passedas a  <code>java.util.Hashtable</code>. This hashtable is generated externally and filled in with the all the processed dataset information awith all the files, algo, run and lumi information. It calls all the other insert API to insert the processed dataset contents
	 * @param conn a database connection <code>java.sql.Connection</code> object created externally.
	 * @param out an output stream <code>java.io.Writer</code> object where this method writes the results into.
	 * @param table a <code>java.util.Hastable</code>  that contain all the necessary key value pairs required for inserting a new all of the processed dataset contents.
	 * @param dbsUser a <code>java.util.Hashtable</code> that contains all the necessary key value pairs for a single user. The most import key in this table is the user_dn. This hashtable is used to insert the bookkeeping information with each row in the database. This is to know which user did the insert at the first place.
	 * @throws Exception Various types of exceptions can be thrown. Commonly they are thrown if the supplied parameters in the hashtable are invalid, the database connection is unavailable.
	 */
	public void insertDatasetContents(Connection conn, Writer out, Hashtable table, Hashtable dbsUser) throws Exception {
		//FIXME dont pass dbsUser instaed get it from the table
		String path = getPath(table, "path", true);
		//System.out.println("line 1");
                //FIXME: Confirm with Vijay -- Change made by AA 01/18/2007, Block is passed as a separate object now.
		String blockName = (new DBSApiBlockLogic(this.data)).getBlock(table, "block_name", true);
                Hashtable fileblock = new Hashtable();
                fileblock.put("block_name", blockName);
                  
                //FIXME: We need to accomodate storage_elements for Block in migrate also ??

		(new DBSApiPrimDSLogic(this.data)).insertPrimaryDataset(conn, out, DBSUtil.getTable(table, "primary_dataset"), dbsUser);
		Hashtable pdTable = DBSUtil.getTable(table, "processed_dataset");
		Vector algoVector = DBSUtil.getVector(pdTable, "algorithm");
		for (int j = 0; j < algoVector.size(); ++j) 
			(new DBSApiAlgoLogic(this.data)).insertAlgorithm(conn, out, (Hashtable)algoVector.get(j), dbsUser);
		
		Vector runVector = DBSUtil.getVector(pdTable, "run");
		for (int j = 0; j < runVector.size(); ++j) 
			insertRun(conn, out, (Hashtable)runVector.get(j), dbsUser);
		
		(new DBSApiProcDSLogic(this.data)).insertProcessedDataset(conn, out, pdTable, dbsUser);
		Vector blockVector = DBSUtil.getVector(pdTable, "block");
		Vector closeBlockVector = new Vector();
		DBSApiBlockLogic blockApi = new DBSApiBlockLogic(this.data);
		for (int j = 0; j < blockVector.size(); ++j) {
			Hashtable block = (Hashtable)blockVector.get(j);
			String name = getBlock(block, "name", true);
			String openForWriting = get(block, "open_for_writing", false);
			if(openForWriting.equals("0")) {
				closeBlockVector.add(name);
				block.remove("open_for_writing");
			}
			//System.out.println("---------> Inserting block "+name);
			blockApi.insertBlock(conn, out, block, dbsUser);
			//System.out.println("---------> DONE Inserting block "+name);
		}
		
		//(new DBSApiFileLogic(this.data)).insertFiles(conn, out, path, blockName, DBSUtil.getVector(table, "file"), dbsUser);
		//System.out.println("---------> Inserting files for path " + path);
                (new DBSApiFileLogic(this.data)).insertFiles(conn, out, path, "", "", fileblock, DBSUtil.getVector(table, "file"), dbsUser);
 
		//Close all the block which were created as open block
		for (int j = 0; j < closeBlockVector.size(); ++j) {
			blockApi.closeBlock(conn, out, (String)closeBlockVector.get(j), dbsUser);
		}

		
	}

}
