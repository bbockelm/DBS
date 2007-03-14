/**
 $Revision: 1.6 $"
 $Id: DBSApiParser.java,v 1.6 2007/02/06 20:54:54 sekhri Exp $"
 *
*/


package dbs.api.parser;
import java.sql.Connection;
import java.io.Writer;
import java.util.Vector;
import java.util.Hashtable;
import xml.DBSXMLParser;
import xml.Element;
import xml.XMLException;
import dbs.util.DBSUtil;
import dbs.api.DBSApiFileLogic;
import dbs.api.DBSApiData;

/**
 * This class handles XML parsing for the input provided by the clients.
 * @author sekhri
 */
public class DBSApiParser {
	

	/**
	* Constructs a DBSApiPasrer object . All the methods in thhis class is static so there is no need to make the instance of this class. The constuctor does nothing.
	*/
	public DBSApiParser() {}

	
	public static Hashtable parse(String inputXml, String key) throws Exception {
		//	checkXML(inputXml);
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
 		Vector allElement = dbsParser.getElements();
		Hashtable table = null;
		for (int i=0; i<allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			if (e.name.equals(key) ) {
				table = e.attributes;
			} 
		}
		return table;
	}

	public static Hashtable parseBlock(String inputXml) throws Exception {
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
		Vector allElement = dbsParser.getElements();
		Hashtable table = null;
		for (int i=0; i<allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			String name = e.name;
			if (name.equals("block") ) {
				table = e.attributes;
				table.put("storage_element", new Vector());
			} 
			if (name.equals("storage_element") ) 
				((Vector)(table.get("storage_element"))).add(e.attributes);
		}
		return table;
	}

	public static Hashtable parsePD(String inputXml) throws Exception {
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
		Vector allElement = dbsParser.getElements();
		Hashtable psDS = null;
		for (int i=0; i<allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			String name = e.name;
			if (name.equals("processed_dataset") ) {
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
		}
		return psDS;
	}
	
	public static Hashtable parseDatasetContents(String inputXml) throws Exception {
		int index = -1;
		int blockIndex = -1;
		System.out.println("inputXml "+inputXml);
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
		Vector allElement = dbsParser.getElements();
		Hashtable table = null;
		Hashtable psDS = null;
		Vector topLevel = new Vector();
		for (int i=0; i<allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			String name = e.name;
			if (name.equals("dataset") ) {
				table = e.attributes;
			}
			if (name.equals("primary_dataset") ) {
				table.put("primary_dataset", e.attributes);
			}
			if (name.equals("processed_dataset") ) {
				psDS = e.attributes;
				psDS.put("data_tier", new Vector());
				psDS.put("parent", new Vector());
				psDS.put("algorithm", new Vector());
				psDS.put("run", new Vector());
				psDS.put("block", new Vector());
			} 
			if (name.equals("data_tier") ) 
				((Vector)(psDS.get("data_tier"))).add(e.attributes);
			if (name.equals("processed_dataset_parent") ) 
				((Vector)(psDS.get("parent"))).add(e.attributes);
			if (name.equals("algorithm") ) 
				((Vector)(psDS.get("algorithm"))).add(e.attributes);
			if (name.equals("run") ) 
				((Vector)(psDS.get("run"))).add(e.attributes);
			if (name.equals("block") ) 
				((Vector)(psDS.get("block"))).add(e.attributes);
			
			if (name.equals("block") ) {
				Hashtable block = e.attributes;
				block.put("storage_element", new Vector());
				((Vector)(psDS.get("block"))).add(block);
				++blockIndex;
			} 
			if (name.equals("storage_element") ) 
				((Vector)((Hashtable)(((Vector)(psDS.get("block"))).get(blockIndex))).get("storage_element")).add(e.attributes);

			if (name.equals("file") ) {
				Hashtable file = e.attributes;
				file.put("file_lumi_section", new Vector());
				file.put("file_data_tier", new Vector());
				file.put("file_parent", new Vector());
				file.put("file_algorithm", new Vector());
				file.put("file_branch", new Vector());
				topLevel.add(file);
				++index;
			} 
			if (name.equals("file_lumi_section") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("file_lumi_section"))).add(e.attributes);
			if (name.equals("file_data_tier") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("file_data_tier"))).add(e.attributes);
			if (name.equals("file_parent") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("file_parent"))).add(e.attributes);
			if (name.equals("file_algorithm") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("file_algorithm"))).add(e.attributes);
			if (name.equals("file_branch") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("file_branch"))).add(e.attributes);

		}
		table.put("processed_dataset", psDS);
		table.put("file", topLevel);
		return table;
	}

	public static void insertFiles(Connection conn, Writer out, String inputXml, Hashtable dbsUser) throws Exception {
		Vector topLevel = new Vector();
		int index = -1;
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
    		Vector allElement = dbsParser.getElements();
		Hashtable psDS = null;
                Hashtable block = null;

		for (int i=0; i<allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			String name = e.name;
			if (name.equals("file") ) {
				Hashtable file = e.attributes;
				file.put("file_lumi_section", new Vector());
				file.put("file_data_tier", new Vector());
				file.put("file_parent", new Vector());
				file.put("file_child", new Vector());
				file.put("file_algorithm", new Vector());
				file.put("file_branch", new Vector());
				topLevel.add(file);
				++index;
			} 
                        //ONLY One Block for this Procssed Dataset, as ALL files belong to same block
                        if (name.equals("block") ) {
                                block = e.attributes;
                                //Keep the storage_elements
                                block.put("storage_element", new Vector());
                        }
                        if (name.equals("storage_element") )
                                ((Vector)block.get("storage_element")).add(e.attributes);

			if (name.equals("file_lumi_section") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("file_lumi_section"))).add(e.attributes);
			if (name.equals("file_data_tier") )
				((Vector)(((Hashtable)topLevel.get(index)).get("file_data_tier"))).add(e.attributes);
			if (name.equals("file_parent") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("file_parent"))).add(e.attributes);
			if (name.equals("file_child") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("file_child"))).add(e.attributes);
			if (name.equals("file_algorithm") ) 
				((Vector)(((Hashtable)topLevel.get(index)).get("file_algorithm"))).add(e.attributes);
                        if (name.equals("file_branch") ) 
                                ((Vector)(((Hashtable)topLevel.get(index)).get("file_branch"))).add(e.attributes);
			if (name.equals("processed_datatset") ) {
				psDS = e.attributes;
			}
		}
                (new DBSApiFileLogic(new DBSApiData())).insertFiles(conn, out, DBSUtil.get(psDS, "path"), block, topLevel, dbsUser);
	}

	public static Hashtable parseADD(String inputXml) throws Exception {
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
		Vector allElement = dbsParser.getElements();
		Hashtable table = null;
		for (int i=0; i<allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			String name = e.name;
			if (name.equals("analysis_dataset_definition") ) {
				table = e.attributes;
				table.put("lumi_section", new Vector());
				table.put("run", new Vector());
				table.put("data_tier", new Vector());
				table.put("algorithm", new Vector());
				table.put("file", new Vector());
				table.put("analysis_dataset", new Vector());
			} 
			if (name.equals("lumi_section") ) 
				((Vector)(table.get("lumi_section"))).add(e.attributes);
			if (name.equals("run") ) 
				((Vector)(table.get("run"))).add(e.attributes);
			if (name.equals("data_tier") ) 
				((Vector)(table.get("data_tier"))).add(e.attributes);
			if (name.equals("algorithm") ) 
				((Vector)(table.get("algorithm"))).add(e.attributes);
			if (name.equals("file") ) 
				((Vector)(table.get("file"))).add(e.attributes);
			if (name.equals("analysis_dataset") ) 
				((Vector)(table.get("analysis_dataset"))).add(e.attributes);

		}
		return table;
	}

	public static void remapFiles(Connection conn, Writer out, String inputXml, Hashtable dbsUser) throws Exception {
		Vector topLevel = new Vector();
		DBSXMLParser dbsParser = new DBSXMLParser();
		dbsParser.parseString(inputXml); 
		Vector allElement = dbsParser.getElements();
		Hashtable table = null;
		for (int i=0; i<allElement.size(); ++i) {
			Element e = (Element)allElement.elementAt(i);
			String name = e.name;
			if (name.equals("in_file") ) {
				topLevel.add(DBSUtil.get(e.attributes, "lfn"));
			} 
			if (name.equals("out_file") ) {
				table = e.attributes;
			}
		}
                (new DBSApiFileLogic(new DBSApiData())).remapFiles(conn, out, topLevel, table,  dbsUser);
	}

	
}
