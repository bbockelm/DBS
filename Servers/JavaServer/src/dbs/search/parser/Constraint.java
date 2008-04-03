

package dbs.search.parser;
public class Constraint {
    	private String key;
    	private String op;
    	private String value;
    	public void setKey(String key){
    		this.key=key;
    	}
    	public void setOp(String op){
    		this.op=op;
    	}
    	public void setValue(String value){
    		this.value=value;
    	}
    	public String getKey() {
    		return this.key;
    	}
    	public String getOp() {
    		return this.op;
    	}
    	public String getValue() {
    		return this.value;
    	}
    	
    }

