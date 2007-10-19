package ui;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import org.richfaces.component.html.HtmlRichMessage;
import java.util.Hashtable;
import ui.util.Util;


public class BlockStatus {
	private HtmlInputText blockInputText;
	private HtmlSelectOneMenu status;
	private HtmlRichMessage generalInputMessage;
	
	public void BlockStatus(){}
	
	public void setBlockInputText(HtmlInputText blockInputText) {
		this.blockInputText = blockInputText;
	}
	public HtmlInputText getBlockInputText() {
		return blockInputText;
	}
	
	public void setStatus(HtmlSelectOneMenu status) {
		this.status = status;
	}
	public HtmlSelectOneMenu getStatus() {
		return status;
	}
	
	public void setGeneralInputMessage(HtmlRichMessage generalInputMessage) {
		this.generalInputMessage = generalInputMessage;
	}
	
    	public HtmlRichMessage getGeneralInputMessage() {
		return generalInputMessage;
    	}
     
	
	public String changeStatusAction() {
		try {
			Hashtable table = new Hashtable();
			String blockStatus = (String)status.getValue();
			if(blockStatus.equals("OPEN")) table.put("api", "openBlock");
			else table.put("api", "closeBlock");
			table.put("block_name", (String)blockInputText.getValue());
			(new Util()).call(table, generalInputMessage);
		 } catch(Exception ex) {
			generalInputMessage.setPassedLabel(ex.getMessage());
			 
		 } finally {
			 generalInputMessage.setRendered(true);
		 }
		return "";
	}

	
}
