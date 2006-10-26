/**
 * @author sekhri
 
 $Revision: 1.2 $"
 $Id: DBSSql.java,v 1.2 2006/10/26 17:09:32 afaq Exp $"
 
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
                        //get the primay dataset from the xml
                        String primaryDatasetName="";

                        DBSXMLParser dbsParser = new DBSXMLParser();
                        dbsParser.parseString(inputXml);
                        Vector allElement = dbsParser.getElements();
                        for (int i=0; i<allElement.size(); ++i) {
                            Element e = (Element)allElement.elementAt(i);
                            String name = e.name;
                            if (name == "primary-dataset" ) {
                               System.out.println("Found a primary dataset: "+name);
                               Hashtable atribs = e.attributes;
                               primaryDatasetName = (String)atribs.get("primary_name");
                               System.out.println("Name of primarydataset: "+primaryDatasetName);
                            }
                        }

                        conn = getConnection();
                        api.insertPrimaryDataset(conn, primaryDatasetName, dbsUser);
                } finally {
                        if(conn != null) conn.close();
                }
        }
	
        public void insertBlock(String inputXml) throws Exception {
                Connection conn = null;
                try {
                        DBSXMLParser dbsParser = new DBSXMLParser();
                        dbsParser.parseString(inputXml);
                        Vector allElement = dbsParser.getElements();
                        for (int i=0; i<allElement.size(); ++i) {
                            Element e = (Element)allElement.elementAt(i);
                            String name = e.name;
                            if (name == "primary-dataset" ) {
                                System.out.println("Found a block");
                                block_atribs = e.attributes;
                                conn = getConnection();
                                api.insertBlock(conn, block_atribs);       
                                break;
                            }
                        }                        
                } finally {
                        if(conn != null) conn.close();
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
