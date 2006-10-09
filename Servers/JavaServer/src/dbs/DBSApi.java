/**
 * @author sekhri
 *
 */

package dbs;
import java.sql.Connection;
import java.io.Writer;
import db.DBManagement;
public class DBSApi {
	/**
	 * 
	 */
	private DBSApiLogic api;
	

	public DBSApi() {
		api = new DBSApiLogic();
		//System.out.println("Constructor DBSApi");
	}

	private Connection getConnection() throws Exception {
		return DBManagement.getConnection(DBSConstants.DRIVER ,DBSConstants.URL ,DBSConstants.USERID ,DBSConstants.PASSWORD);
	}
	
	
	public void getDatasetInfo(Writer out, String dsPath) throws Exception {
		Connection conn = getConnection();
		try {
			api.getDatasetInfo(conn, out, dsPath);
		} finally {
			conn.close();
		}
	}

	public void listPrimaryDatasets(Writer out, String pattern) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			api.listPrimaryDatasets(conn, out, pattern);
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void listProcessedDatasets(Writer out, String patternDs, String patternApp) throws Exception {
		Connection conn = getConnection();
		try {
			api.listProcessedDatasets(conn, out, patternDs, patternApp);
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void listParameterSets(Writer out, String pattern) throws Exception {
		Connection conn = getConnection();
		try {
			api.listParameterSets(conn, out, pattern);
		} finally {
			if(conn != null) conn.close();
		}
	}
	
	public void listApplications(Writer out, String pattern) throws Exception {
		Connection conn = getConnection();
		try {
			api.listApplications(conn, out, pattern);
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void listApplicationConfigs(Writer out, String pattern) throws Exception {
		Connection conn = getConnection();
		try {
			api.listApplicationConfigs(conn, out, pattern);
		} finally {
			if(conn != null) conn.close();
		}
	}
	
	public void getDatasetContents(Writer out, String dsPath) throws Exception {
		Connection conn = getConnection();
		try {
			api.getDatasetContents(conn, out, dsPath);
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void getDatasetFiles(Writer out, String dsPath) throws Exception {
		Connection conn = getConnection();
		try {
			api.getDatasetFiles(conn, out, dsPath);
		} finally {
			if(conn != null) conn.close();
		}
	}

}
