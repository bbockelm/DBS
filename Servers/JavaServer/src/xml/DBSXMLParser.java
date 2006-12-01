/*
* @author anzar
 $Revision: 1.6 $"
 $Id: DBSXMLParser.java,v 1.6 2006/11/30 16:29:37 sekhri Exp $"
*
*/

package xml;

import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.StringReader;
import java.util.Vector;
import java.util.Hashtable;
import dbs.util.DBSUtil;

public class DBSXMLParser extends DefaultHandler {

	public Vector elements;

	public DBSXMLParser() {
	        super();
		elements = new Vector();
	}

	public Vector getElements() {
		return(elements);
	}

	public void startDocument () throws SAXException {
		// Write your application specific logic
	}

	public void endDocument() throws SAXException {
		// Write your application specific logic
	}

	public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// Write your application specific logic
		//System.out.println("StartElement"); 
		//System.out.println("URI :"+uri);
		//System.out.println("localName:"+localName);
		//System.out.println("qName: >>>>>>>>>>>>>>>>>>>>>"+qName);
		Element newElement = new Element(qName); 
		//newElement.value = "Test";
		for (int i=0; i< attributes.getLength();++i){
			//System.out.println("attribute:"+attributes.getQName(i)+" value: "+attributes.getValue(i));
			 newElement.value = "dbsTest";
			newElement.attributes.put(attributes.getQName(i), attributes.getValue(i)); 
		}
		elements.add(newElement);
	 }

	public void endElement (String uri, String localName, String qName) throws SAXException  {
		//System.out.println("qName:<<<<<<<<<<<<<<<<<<<"+qName);
	}

	public void characters (char ch[], int start, int length) throws SAXException {
		//System.out.println("characters");
		// your application specific logic

		String value="";
		//System.out.print("Characters: ");
		for (int i = start; i < start + length; i++) {
			//System.out.print(ch[i]);
			switch (ch[i]) {
				case '\\':
					//System.out.print("\\\\");
					break;
				case '"':
					//System.out.print("\\\"");
					break;
				case '\n':
					//System.out.print("\\n");
					break;
				case '\r':
					//System.out.print("\\r");
					break;
				case '\t':
					//System.out.print("\\t");
					break;
				default:
				//System.out.print(ch[i]);
					value=value+ch[i];	
					break;
			}
		}
		//System.out.println("Value:"+value);       
	}

	//Parse a String
	public void parseString(String xmlcontent) throws Exception {
		if (DBSUtil.isNull(xmlcontent)) 
			throw new XMLException("Missing data", "3000", "Null Fields. Expected a valid inputxml in XML format.");
		StringReader reader = null;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			reader = new StringReader(xmlcontent);
			InputSource source = new InputSource(reader);
			saxParser.parse(source, this);
		} catch (Exception e) {
			throw new XMLException("XML parse exception", "3001", "Cannot parse xmlInput. Expected a valid inputXml in XML format. Exception from parser : " + e.getMessage() + " The given xml is " + xmlcontent);
		} finally {
			if (reader != null) reader.close();
		}

		//saxParser.parse(newFile,new DefaultHandlerImpl());
	}

	//Parse a file
	public void parseFile(String fileName) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		File newFile = new File(fileName);
		saxParser.parse(newFile, this);
		//saxParser.parse(newFile,new DefaultHandlerImpl());
	}

	public static void main(String args[]){
		String fileName = "";
		if(args.length != 1){
			System.out.println("Usage is: java DBSXMLParser [xml file Name]");
			System.exit(0);
		}else{
			fileName = args[0];
		}
		DBSXMLParser dbsParser = new DBSXMLParser();
		//Parse a file
		//dbsParser.pareFile(fileName);

		//Parse a String
		String xml_string = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>"+
				"<primary-dataset id='1' annotation='First Primary in new era' primary_name='PrimaryDS_ANZAR_01' trigger_path_description='null' mc_channel_description='null' mc_production='null' mc_decay_chain='null' other_description='null' start_date='2006-10-05' end_date='2007-10-05' file_type='null' created_by='null' creation_date='null' last_modification_by='null' last_modified_by='null'/>"+
				"<processed-dataset id='1' path='/PrimaryDS_ANZAR_01/SIM/anzar-procds-01' open_for_writing='y' creation_date='null' last_modification_date='null' run_number='null' first_event_number='null' last_event_number='null' start_of_run='null' end_of_run='null' physics_group_name='null' physics_group_convener='null'/>"+
				"<processed-dataset id='1' path='/PrimaryDS_ANZAR_01/RECO/anzar-procds-02' open_for_writing='y' creation_date='null' last_modification_date='null' run_number='null' first_event_number='null' last_event_number='null' start_of_run='null' end_of_run='null' physics_group_name='null' physics_group_convener='null'/>"+
				"<test-parent-element attrb='1'>"+
				"<test-child-element attrb='11'/>"+
				"<test-child-element attrb='12'/>"+
				"</test-parent-element>"+
				"</dbs>";
		try {
			dbsParser.parseString(xml_string); 
    			Vector allElement = dbsParser.getElements();
			for (int i=0; i<allElement.size(); ++i) {
				Element e = (Element)allElement.elementAt(i);
				String name = e.name;
				if (name == "primary-dataset" ) {
					System.out.println("Found a primary dataset: "+name);  
					Hashtable atribs = e.attributes;
					String primary_name = (String)atribs.get("primary_name");
					System.out.println("Name of primarydataset: "+primary_name);
				} 
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}

