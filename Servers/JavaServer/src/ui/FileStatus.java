package ui;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import org.richfaces.component.html.HtmlRichMessage;
import java.io.StringWriter;
import java.util.Hashtable;
import ui.util.Util;


public class FileStatus {
	private HtmlInputText lfnInputText;
	private HtmlSelectOneMenu status;
	private HtmlRichMessage generalInputMessage;
	
	
	public void FileStatus(){}
	
	public void setLfnInputText(HtmlInputText lfnInputText) {
		this.lfnInputText = lfnInputText;
	}
	public HtmlInputText getLfnInputText() {
		return lfnInputText;
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
			table.put("api", "updateFileStatus");
			table.put("lfn", (String)lfnInputText.getValue());
			table.put("status", (String)status.getValue());
			(new Util()).call(table, generalInputMessage);
		 } catch(Exception ex) {
			generalInputMessage.setPassedLabel(ex.getMessage());
			 
		 } finally {
			 generalInputMessage.setRendered(true);
		 }
		return "";
	}
	
}
