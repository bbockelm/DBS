

package dbs.search.parser;
public class Constraint {
    	private String key;
    	private String op;
    	private String value;
    	private String bracket;
    	public void setKey(String key){
    		this.key=key;
    	}
    	public void setOp(String op){
    		this.op=op;
    	}
    	public void setValue(String value){
    		this.value=value;
    	}
    	public void setBracket(String bracket){
    		this.bracket=bracket;
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
    	public String getBracket(){
    		return this.bracket;
    	}
    	
    }

