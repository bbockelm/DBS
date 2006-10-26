/*
* @author anzar
 $Revision: 1.2 $"
 $Id: DBSApi.java,v 1.2 2006/10/26 17:11:35 afaq Exp $"
*
*/

package xml;

import java.util.Hashtable;

public class Element{

	public String name;
	public String value;
        public Hashtable attributes;
        

	public Element(String name,String value){
	
		this.name=name;
		this.value=value;
                attributes = new Hashtable(); 
	}

       public Element(String name) {
                this.name=name;
                attributes = new Hashtable(); 
       } 

}

