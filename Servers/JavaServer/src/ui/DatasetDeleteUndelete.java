package ui;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import org.richfaces.component.html.HtmlRichMessage;
import java.util.Hashtable;
import ui.util.Util;

public class DatasetDeleteUndelete {
	private HtmlInputText datasetInputText;
	private HtmlSelectOneMenu op;
	private HtmlRichMessage generalInputMessage;
	
	public void DatasetDeleteUndelete(){}
	
	public void setDatasetInputText(HtmlInputText datasetInputText) {
		this.datasetInputText = datasetInputText;
	}
	public HtmlInputText getDatasetInputText() {
		return datasetInputText;
	}
	
	public void setOp(HtmlSelectOneMenu op) {
		this.op = op;
	}
	public HtmlSelectOneMenu getOp() {
		return op;
	}
		
	public void setGeneralInputMessage(HtmlRichMessage generalInputMessage) {
		this.generalInputMessage = generalInputMessage;
	}
	
    	public HtmlRichMessage getGeneralInputMessage() {
		return generalInputMessage;
    	}
     
    

	public String deleteUndeleteAction() {
		try {
			Hashtable table = new Hashtable();
			String datasetOp = (String)op.getValue();
			if(datasetOp.equals("DELETE")) table.put("api", "deleteProcDS");
			else table.put("api", "undeleteProcDS");
			table.put("path", (String)datasetInputText.getValue());
			(new Util()).call(table, generalInputMessage);
		 } catch(Exception ex) {
			generalInputMessage.setPassedLabel(ex.getMessage());
			 
		 } finally {
			 generalInputMessage.setRendered(true);
		 }
		return "";
	}

	
}
