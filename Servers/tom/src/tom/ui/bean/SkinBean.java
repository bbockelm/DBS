package tom.ui.bean;

public class SkinBean extends BaseBean {
	private String skin = "wine";
	public SkinBean() {
	}
	
	protected void init() {
		skin = "wine";
	}
	public void setSkin(String skin){
		this.skin = skin;
	}
	public String getSkin(){
		return this.skin;					    
	}
}
