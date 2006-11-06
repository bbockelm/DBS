/**
 $Revision: 1.6 $"
 $Id: DBSApi.java,v 1.6 2006/11/01 16:59:44 afaq Exp $"
 *
*/

package dbs.api;
import java.sql.Connection;
import java.io.Writer;
import java.util.Vector;
import java.util.Hashtable;
import xml.DBSXMLParser;
import xml.Element;
import db.DBManagement;
import dbs.DBSConstants;

public class DBSApi {
	/**
	 * 
	 */
	private DBSApiLogic api;
	
        public Vector getApiVersions() {
              Vector supported_version_list  = new Vector();
              supported_version_list.add("v00_00_01");
              supported_version_list.add("v00_00_02");

              return supported_version_list;

        }

	public DBSApi() {
		api = new DBSApiLogic();
		//System.out.println("Constructor DBSApi");
	}

	private Connection getConnection() throws Exception {
		return DBManagement.getConnection(DBSConstants.DRIVER ,DBSConstants.URL ,DBSConstants.USERID ,DBSConstants.PASSWORD);
	}
	
        public void insertPrimaryDataset(String inputXml, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertPrimaryDataset(conn, parse(inputXml, "primary-dataset") , dbsUser);
			conn.commit();
		} finally {
			if(conn != null) conn.close();
		}
        }
	
	public void insertApplication(String inputXml, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertApplication(conn, parse(inputXml, "algorithm") , dbsUser);
			conn.commit();
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void insertRun(String inputXml, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertRun(conn, parse(inputXml, "run") , dbsUser);
			conn.commit();
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void insertBlock(String inputXml, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertBlock(conn, parse(inputXml, "block") , dbsUser);
			conn.commit();
		} finally {
			if(conn != null) conn.close();
		}
	}


	public void insertFiles(String inputXml, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			Vector topLevel = new Vector();
			int index = -1;
			DBSXMLParser dbsParser = new DBSXMLParser();
			dbsParser.parseString(inputXml); 
    			Vector allElement = dbsParser.getElements();
			for (int i=0; i<allElement.size(); ++i) {
				Element e = (Element)allElement.elementAt(i);
				String name = e.name;
				if (name.equals("file") ) {
					System.out.println("Found a file: " + name);  
					Hashtable file = e.attributes;
					file.put("lumi_section", new Vector());
					file.put("data_tier", new Vector());
					file.put("parent", new Vector());
					file.put("algorithm", new Vector());
					topLevel.add(file);
					++index;
				} 
				if (name.equals("lumi_section") ) 
					((Vector)(((Hashtable)topLevel.get(index)).get("lumi_section"))).add(e.attributes);
				if (name.equals("data_tier") ) 
					((Vector)(((Hashtable)topLevel.get(index)).get("data_tier"))).add(e.attributes);
				if (name.equals("parent") ) 
					((Vector)(((Hashtable)topLevel.get(index)).get("parent"))).add(e.attributes);
				if (name.equals("algorithm") ) 
					((Vector)(((Hashtable)topLevel.get(index)).get("algorithm"))).add(e.attributes);
			}
			//try catch for rollback
			conn = getConnection();
			conn.setAutoCommit(false);
			//try {
				api.insertFiles(conn, topLevel, dbsUser);
			/*} catch (Exception e) {
				conn.rollback();
				throw e;
			}*/
			conn.commit();

		} finally {
			if(conn != null) conn.close();
		}
	}

	public void insertProcessedDataset(String inputXml, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			DBSXMLParser dbsParser = new DBSXMLParser();
			dbsParser.parseString(inputXml); 
    			Vector allElement = dbsParser.getElements();
			Hashtable psDS = null;
			for (int i=0; i<allElement.size(); ++i) {
				Element e = (Element)allElement.elementAt(i);
				String name = e.name;
				if (name.equals("processed-dataset") ) {
					System.out.println("Found a processed-dataset: " + name);  
					psDS = e.attributes;
					psDS.put("data_tier", new Vector());
					psDS.put("parent", new Vector());
					psDS.put("algorithm", new Vector());
				} 
				if (name.equals("data_tier") ) 
					((Vector)(psDS.get("data_tier"))).add(e.attributes);
				if (name.equals("parent") ) 
					((Vector)(psDS.get("parent"))).add(e.attributes);
				if (name.equals("algorithm") ) 
					((Vector)(psDS.get("algorithm"))).add(e.attributes);
				//System.out.println("Algorithm hashtable "+ (e.attributes).toString());
			}
			//try catch for rollback
			conn = getConnection();
			conn.setAutoCommit(false);
			//try {
				api.insertProcessedDatatset(conn, psDS, dbsUser);
			/*} catch (Exception e) {
				conn.rollback();
				throw e;
			}*/
			conn.commit();

		} finally {
			if(conn != null) conn.close();
		}
	}
	
	public Hashtable parse(String inputXml, String key) throws Exception {
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
 		Vector allElement = dbsParser.getElements();
		Hashtable table = null;
		for (int i=0; i<allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			String name = e.name;
			if (name.equals(key) ) {
				System.out.println("Found a " + key + " : " + name);  
				table = e.attributes;
			} 
		}
		return table;
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

	public void listProcessedDatasets(Writer out, String pattern) throws Exception {
		Connection conn = getConnection();
		try {
			api.listProcessedDatasets(conn, out, pattern);
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

	public void listRuns(Writer out, String path) throws Exception {
		Connection conn = getConnection();
		try {
			api.listRuns(conn, out, path);
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void listTiers(Writer out, String path) throws Exception {
		Connection conn = getConnection();
		try {
			api.listTiers(conn, out, path);
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void listBlocks(Writer out, String path) throws Exception {
		Connection conn = getConnection();
		try {
			api.listBlocks(conn, out, path);
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void listFiles(Writer out, String path, String blockName, String patternLFN) throws Exception {
		Connection conn = getConnection();
		try {
			api.listFiles(conn, out, path, blockName, patternLFN);
		} finally {
			if(conn != null) conn.close();
		}
	}



}
