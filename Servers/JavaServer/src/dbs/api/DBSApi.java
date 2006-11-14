/**
 $Revision: 1.13 $"
 $Id: DBSApi.java,v 1.13 2006/11/14 19:14:36 sekhri Exp $"
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
import dbs.DBSException;
import dbs.util.DBSUtil;

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
	
	public void insertAlgorithm(String inputXml, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertAlgorithm(conn, parse(inputXml, "algorithm") , dbsUser);
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

	public void insertTier(String tierName, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertTier(conn, tierName, dbsUser);
			conn.commit();
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void insertTierInPD(String path, String tierName, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertTierInPD(conn, path, tierName, dbsUser);
			conn.commit();
		} finally {
			if(conn != null) conn.close();
		}
	}
	
	public void insertParentInPD(String path, String parentPath, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertParentInPD(conn, path, parentPath, dbsUser);
			conn.commit();
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void insertAlgoInPD(String path, String inputXml, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertAlgoInPD(conn, path, parse(inputXml, "algorithm"), dbsUser);
			conn.commit();
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void insertRunInPD(String path, String runNumber, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertRunInPD(conn, path, runNumber, dbsUser);
			conn.commit();
		} finally {
			if(conn != null) conn.close();
		}
	}


	public void insertTierInFile(String lfn, String tierName, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertTierInFile(conn, lfn, tierName, dbsUser);
			conn.commit();
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void insertParentInFile(String lfn, String parentLFN, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertParentInFile(conn, lfn, parentLFN, dbsUser);
			conn.commit();
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void insertAlgoInFile(String lfn, String inputXml, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertAlgoInFile(conn, lfn, parse(inputXml, "algorithm"), dbsUser);
			conn.commit();
		} finally {
			if(conn != null) conn.close();
		}
	}

	public void insertLumiInFile(String lfn, String lsNumber, Hashtable dbsUser) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			api.insertLumiInFile(conn, lfn, lsNumber, dbsUser);
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
			Hashtable psDS = null;
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
				if (name.equals("processed_datatset") ) {
					psDS = e.attributes;
				}
			}
			//try catch for rollback
			conn = getConnection();
			conn.setAutoCommit(false);
			//try {
				api.insertFiles(conn, DBSUtil.get(psDS, "path"), DBSUtil.get(psDS, "block_name"), topLevel, dbsUser);
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
					psDS.put("run", new Vector());
				} 
				if (name.equals("data_tier") ) 
					((Vector)(psDS.get("data_tier"))).add(e.attributes);
				if (name.equals("parent") ) 
					((Vector)(psDS.get("parent"))).add(e.attributes);
				if (name.equals("algorithm") ) 
					((Vector)(psDS.get("algorithm"))).add(e.attributes);
				if (name.equals("run") ) 
					((Vector)(psDS.get("run"))).add(e.attributes);

				//System.out.println("Algorithm hashtable "+ (e.attributes).toString());
			}
			//try catch for rollback
			conn = getConnection();
			conn.setAutoCommit(false);
			//try {
				api.insertProcessedDataset(conn, psDS, dbsUser);
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
		//	checkXML(inputXml);
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

	public void listAlgorithms(Writer out, String pattern) throws Exception {
		Connection conn = getConnection();
		try {
			api.listAlgorithms(conn, out, pattern);
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

	/*private void checkXML(String xml) throws Exception {
		if(isNull(xml))
			throw new DBSException("Bad Data", "300", "Null Fields. Expected a xmlInput in XML format");
	}

	private boolean isNull(String pattern) {
		return DBSUtil.isNull(pattern);
	}*/

}
