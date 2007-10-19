package ui;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import org.richfaces.component.html.HtmlRichMessage;
import java.util.Hashtable;
import ui.util.Util;

public class DatasetStatus {
	private HtmlInputText datasetInputText;
	private HtmlSelectOneMenu status;
	private HtmlRichMessage generalInputMessage;
	
	public void DatasetStatus(){}
	
	public void setDatasetInputText(HtmlInputText datasetInputText) {
		this.datasetInputText = datasetInputText;
	}
	public HtmlInputText getDatasetInputText() {
		return datasetInputText;
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
			table.put("api", "updateProcDSStatus");
			table.put("path", (String)datasetInputText.getValue());
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
