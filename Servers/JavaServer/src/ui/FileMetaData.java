package ui;
import javax.faces.component.html.HtmlInputText;
import org.richfaces.component.html.HtmlRichMessage;
import java.util.Hashtable;
import ui.util.Util;

public class FileMetaData {
	private HtmlInputText lfnInputText;
	private HtmlInputText qmdInputText;
	private HtmlRichMessage generalInputMessage;
	
	public void FileMetaData(){}
	
	public void setLfnInputText(HtmlInputText lfnInputText) {
		this.lfnInputText = lfnInputText;
	}
	public HtmlInputText getLfnInputText() {
		return lfnInputText;
	}
	
	public void setQmdInputText(HtmlInputText qmdInputText) {
		this.qmdInputText = qmdInputText;
	}
	public HtmlInputText getQmdInputText() {
		return qmdInputText;
	}
	
	public void setGeneralInputMessage(HtmlRichMessage generalInputMessage) {
		this.generalInputMessage = generalInputMessage;
	}
	
    	public HtmlRichMessage getGeneralInputMessage() {
		return generalInputMessage;
    	}
     

	/*public String updateMetaDataAction() {
		System.out.println("LFN is " + (String)lfnInputText.getValue());
		System.out.println("QMD is " + (String)qmdInputText.getValue());
		return "";
	}*/
	
	public String updateMetaDataAction() {
		try {
			Hashtable table = new Hashtable();
			table.put("api", "updateFileMetaData");
			table.put("lfn", (String)lfnInputText.getValue());
			table.put("queryable_meta_data", (String)qmdInputText.getValue());
			(new Util()).call(table, generalInputMessage);
		 } catch(Exception ex) {
			generalInputMessage.setPassedLabel(ex.getMessage());
			 
		 } finally {
			 generalInputMessage.setRendered(true);
		 }
		return "";
	}

}
