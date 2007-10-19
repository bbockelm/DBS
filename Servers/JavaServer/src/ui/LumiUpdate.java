package ui;
import javax.faces.component.html.HtmlInputText;
import org.richfaces.component.html.HtmlRichMessage;
import java.util.Hashtable;
import ui.util.Util;

public class LumiUpdate {
	private HtmlInputText lumiInputText;
	private HtmlInputText runInputText;
	private HtmlInputText startEventInputText;
	private HtmlInputText endEventInputText;
	private HtmlInputText startLumiTimeInputText;
	private HtmlInputText endLumiTimeInputText;
	private HtmlRichMessage generalInputMessage;
	
	public void LumiUpdate(){}
	
	public void setLumiInputText(HtmlInputText lumiInputText) {
		this.lumiInputText = lumiInputText;
	}
	public HtmlInputText getLumiInputText() {
		return lumiInputText;
	}
	
	public void setRunInputText(HtmlInputText runInputText) {
		this.runInputText = runInputText;
	}
	public HtmlInputText getRunInputText() {
		return runInputText;
	}

	public void setStartEventInputText(HtmlInputText startEventInputText) {
		this.startEventInputText = startEventInputText;
	}
	public HtmlInputText getStartEventInputText() {
		return startEventInputText;
	}
	
	public void setEndEventInputText(HtmlInputText endEventInputText) {
		this.endEventInputText = endEventInputText;
	}
	public HtmlInputText getEndEventInputText() {
		return endEventInputText;
	}
	
	public void setStartLumiTimeInputText(HtmlInputText startLumiTimeInputText) {
		this.startLumiTimeInputText = startLumiTimeInputText;
	}
	public HtmlInputText getStartLumiTimeInputText() {
		return startLumiTimeInputText;
	}
	
	public void setEndLumiTimeInputText(HtmlInputText endLumiTimeInputText) {
		this.endLumiTimeInputText = endLumiTimeInputText;
	}
	public HtmlInputText getEndLumiTimeInputText() {
		return endLumiTimeInputText;
	}
	
	public void setGeneralInputMessage(HtmlRichMessage generalInputMessage) {
		this.generalInputMessage = generalInputMessage;
	}
	
    	public HtmlRichMessage getGeneralInputMessage() {
		return generalInputMessage;
    	}
     

	/*public String updateLumiAction() {
		System.out.println("Lumi No is " + (String)lumiInputText.getValue());
		System.out.println("Run No is " + (String)runInputText.getValue());
		System.out.println("startEventInputText is " + (String)startEventInputText.getValue());
		System.out.println("endEventInputText is " + (String)endEventInputText.getValue());
		System.out.println("startLumiTimeInputText is " + (String)startLumiTimeInputText.getValue());
		System.out.println("endLumiTimeInputText is " + (String)endLumiTimeInputText.getValue());
		return "";
	}*/
	
	public String updateLumiAction() {
		try {
			Hashtable table = new Hashtable();
			String xml = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				"<lumi_section lumi_section_number='" + (String)lumiInputText.getValue() + 
				"' run_number='" + (String)runInputText.getValue() + 
				"' start_event_number='" + (String)startEventInputText.getValue() + 
				"' end_event_number='" + (String)endEventInputText.getValue() + 
				"' lumi_start_time='" + (String)startLumiTimeInputText.getValue() + 
				"' lumi_end_time='" + (String)endLumiTimeInputText.getValue() + "'/>" +
				"</dbs>";
			table.put("api", "updateLumiSection");
			table.put("xmlinput", xml);
			(new Util()).call(table, generalInputMessage);
		 } catch(Exception ex) {
			generalInputMessage.setPassedLabel(ex.getMessage());
			 
		 } finally {
			 generalInputMessage.setRendered(true);
		 }
		return "";
	}

}
