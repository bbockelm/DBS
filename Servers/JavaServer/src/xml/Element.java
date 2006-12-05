/*
* @author anzar
 $Revision: 1.3 $"
 $Id: Element.java,v 1.3 2006/10/26 18:26:04 afaq Exp $"
*
*/

package xml;

import java.util.Hashtable;

/**
* A data structure for containning key value pairs and a <code>java.util.Hashtable</code> of key value pairs. This class is used by <code>xml.DBSXMLParser</code> to store the key value pairs or <code>java.util.Hashtable</code> of key value pairs. After the parsing is done by the <code>xml.DBSXMLParser<code> a vector of this class is returned for easy retrival of attributes and values fetched from the xml. This class has three public variables for storing data.<br> 
* 1) name contains the name of the key. <br>
* 2) value contains the value of key. <br>
* 3) attributes contains the hastable of key value pairs. <br>
* @author sekhri
*
*/
public class Element{

	public String name;
	public String value;
        public Hashtable attributes;
        
	/**
	* Constructs a Element object that can contain key value pairs or a hastable of key value pairs.
	* @param name The name of the key to be stored in this class.
	*/
	public Element(String name) {
		this.name=name;
		attributes = new Hashtable(); 
	} 

}

