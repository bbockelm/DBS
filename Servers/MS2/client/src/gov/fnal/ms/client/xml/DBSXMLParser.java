package gov.fnal.ms.client.xml;


import gov.fnal.ms.client.util.Util;
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



public class DBSXMLParser extends DefaultHandler {

        private Vector elements;
        private Util u;

        /**
        * Constructs a DBSXMLParser object that inherits from <code>org.xml.sax.helpers.DefaultHandler</code>. This constructor initializes a private <code>java.util.Vector</code> that will be used to store the <code>java.util.Vector</code> of <code>xml.Element</code> after parsing.
        */
        public DBSXMLParser() {
                super();
                elements = new Vector();
                u = new Util();
        }

        /**
        * Returns a <code>java.util.Vector</code> of <code>xml.Element</code> .
        * @return
        * a <code>java.util.Vector</code> of <code>xml.Element</code> that contains key value pairs or <code>java.util.Hashtable</code> of key value pairs.
        */
        public Vector getElements() {
                return(elements);
        }

        /**
         * Receive notification of the start of an element when parsing beguns. This method takes in th avules returned by the standard SAX parser and stores it in a <code>java.util.Vector</code> of  <code>xml.Element</code>
         * @param uri
         * @param localName The local name (without prefix), or the empty <code>java.lang.String</code> if Namespace processing is not being performed.
         * @param qName The qualified name (with prefix), or the empty <code>java.lang.String</code> if qualified names are not available.
         * @param attributes The specified or defaulted attributes.
         * @throws SAXException - Any SAX exception, possibly wrapping another exception.
         */
        public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {
                Element newElement = new Element(qName);
                for (int i=0; i< attributes.getLength();++i){
                        //System.out.println("attribute:"+attributes.getQName(i)+" value: "+attributes.getValue(i));
                        newElement.value = "dbsTest";
                        newElement.attributes.put(attributes.getQName(i), attributes.getValue(i));
                }
                elements.add(newElement);
         }

        /**
         * A public method of this class used to start the parsing of xmlcontent that is passed as a <code>java.lang.String</code> parameter. After the parsing of this <code>java.lang.String</code> is done, the results are stored in a <code>java.util.Vector</code> of <code>xml.Element</code> that contains key value pairs or <code>java.util.Hashtable</code> of key value pairs. That vector can be retrived by calling getElements method os this class.
         * @param xmlcontent The xml <code>java.lang.String</code> to be parsed by this class.
         * @throws XMLException - Any SAX exception is wrapped in XMLException with proper code and detail.
         */
        public void parseString(String xmlcontent) throws XMLException {
                if (u.isNull(xmlcontent))
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

        }

        /**
         * A public method of this class used to start the parsing of a xml <code>java.io.File</code> that is passed as a <code>java.lang.String</code> parameter. After the parsing of this <code>java.io.File</code> is done, the results are stored in a <code>java.util.Vector</code> of <code>xml.Element</code> that contains key value pairs or <code>java.util.Hashtable</code> of key value pairs. That vector can be retrived by calling getElements method os this class.
         * @param fileName The name of the file thats contents to be parsed in <code>java.lang.String</code> by this class.
         * @throws XMLException - Any SAX exception is wrapped in XMLException with proper code and detail.
         */
        public void parseFile(String fileName) throws XMLException {
                try {
                        File newFile = new File(fileName);
                        SAXParserFactory factory = SAXParserFactory.newInstance();
                        SAXParser saxParser = factory.newSAXParser();
                        saxParser.parse(newFile, this);
                } catch (Exception e) {
                        throw new XMLException("XML parse exception", "3001", "Cannot parse xml file. Expected a valid xml file in XML format. Exception from parser : " + e.getMessage() );
                }

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

