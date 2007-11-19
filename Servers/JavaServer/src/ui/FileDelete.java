package ui;
import javax.faces.component.html.HtmlInputText;
import org.richfaces.component.html.HtmlRichMessage;
import java.util.Hashtable;
import ui.util.Util;

public class FileDelete {
	private HtmlInputText lfnInputText;
	private HtmlRichMessage generalInputMessage;
	
	public void FileDelete(){}
	
	public void setLfnInputText(HtmlInputText lfnInputText) {
		this.lfnInputText = lfnInputText;
	}
	public HtmlInputText getLfnInputText() {
		return lfnInputText;
	}
		
	public void setGeneralInputMessage(HtmlRichMessage generalInputMessage) {
		this.generalInputMessage = generalInputMessage;
	}
	
    	public HtmlRichMessage getGeneralInputMessage() {
		return generalInputMessage;
    	}
     

	public String deleteAction() {
		System.out.println("LFN is " + (String)lfnInputText.getValue());
		generalInputMessage.setPassedLabel("Under Construction. Not implemented yet");
		generalInputMessage.setRendered(true);
		return "";
	}
	

}
