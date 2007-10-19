package ui;
import javax.faces.component.html.HtmlInputText;
import org.richfaces.component.html.HtmlRichMessage;
import java.util.Hashtable;
import ui.util.Util;

public class StorageElementRename {
	private HtmlInputText seOldInputText;
	private HtmlInputText seNewInputText;
	private HtmlRichMessage generalInputMessage;
	
	public void StorageElementRename(){}
	
	public void setSeOldInputText(HtmlInputText seOldInputText) {
		this.seOldInputText = seOldInputText;
	}
	public HtmlInputText getSeOldInputText() {
		return seOldInputText;
	}
	
	public void setSeNewInputText(HtmlInputText seNewInputText) {
		this.seNewInputText = seNewInputText;
	}
	public HtmlInputText getSeNewInputText() {
		return seNewInputText;
	}
	
	public void setGeneralInputMessage(HtmlRichMessage generalInputMessage) {
		this.generalInputMessage = generalInputMessage;
	}
	
    	public HtmlRichMessage getGeneralInputMessage() {
		return generalInputMessage;
    	}
     

	/*
	public String renameAction() {
		System.out.println("SE Old is " + (String)seOldInputText.getValue());
		System.out.println("SE New is " + (String)seNewInputText.getValue());
		return "";
	}*/
	
	public String renameAction() {
		try {
			Hashtable table = new Hashtable();
			table.put("api", "updateSEName");
			table.put("storage_element_name_from", (String)seOldInputText.getValue());
			table.put("storage_element_name_to", (String)seNewInputText.getValue());
			(new Util()).call(table, generalInputMessage);
		 } catch(Exception ex) {
			generalInputMessage.setPassedLabel(ex.getMessage());
			 
		 } finally {
			 generalInputMessage.setRendered(true);
		 }
		return "";
	}

}
