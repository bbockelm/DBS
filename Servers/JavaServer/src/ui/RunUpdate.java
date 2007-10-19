package ui;
import javax.faces.component.html.HtmlInputText;
import org.richfaces.component.html.HtmlRichMessage;
import java.util.Hashtable;
import ui.util.Util;

public class RunUpdate {
	private HtmlInputText runInputText;
	private HtmlInputText noEventsInputText;
	private HtmlInputText noLumiInputText;
	private HtmlInputText luminosityInputText;
	private HtmlInputText startRunInputText;
	private HtmlInputText endRunTimeInputText;
	private HtmlRichMessage generalInputMessage;
	
	public void RunUpdate(){}
	
	public void setNoEventsInputText(HtmlInputText noEventsInputText) {
		this.noEventsInputText = noEventsInputText;
	}
	public HtmlInputText getNoEventsInputText() {
		return noEventsInputText;
	}
	
	public void setRunInputText(HtmlInputText runInputText) {
		this.runInputText = runInputText;
	}
	public HtmlInputText getRunInputText() {
		return runInputText;
	}

	public void setNoLumiInputText(HtmlInputText noLumiInputText) {
		this.noLumiInputText = noLumiInputText;
	}
	public HtmlInputText getNoLumiInputText() {
		return noLumiInputText;
	}
	
	public void setLuminosityInputText(HtmlInputText luminosityInputText) {
		this.luminosityInputText = luminosityInputText;
	}
	public HtmlInputText getLuminosityInputText() {
		return luminosityInputText;
	}
	
	public void setStartRunInputText(HtmlInputText startRunInputText) {
		this.startRunInputText = startRunInputText;
	}
	public HtmlInputText getStartRunInputText() {
		return startRunInputText;
	}
	
	public void setEndRunTimeInputText(HtmlInputText endRunTimeInputText) {
		this.endRunTimeInputText = endRunTimeInputText;
	}
	public HtmlInputText getEndRunTimeInputText() {
		return endRunTimeInputText;
	}
	
	public void setGeneralInputMessage(HtmlRichMessage generalInputMessage) {
		this.generalInputMessage = generalInputMessage;
	}
	
    	public HtmlRichMessage getGeneralInputMessage() {
		return generalInputMessage;
    	}
     
/*
	public String updateRunAction() {
		System.out.println("Run No is " + (String)runInputText.getValue());
		System.out.println("noEventsInputText is " + (String)noEventsInputText.getValue());
		System.out.println("noLumiInputText is " + (String)noLumiInputText.getValue());
		System.out.println("luminosityInputText is " + (String)luminosityInputText.getValue());
		System.out.println("startRunInputText is " + (String)startRunInputText.getValue());
		System.out.println("endRunTimeInputText is " + (String)endRunTimeInputText.getValue());
		return "";
	}*/
	
	public String updateRunAction() {
		try {
			generalInputMessage.setPassedLabel("");
			Hashtable table = new Hashtable();
			String xml = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				"<run run_number='" + (String)runInputText.getValue() + 
				"' number_of_events='" + (String)noEventsInputText.getValue() + 
				"' number_of_lumi_sections='" + (String)noLumiInputText.getValue() + 
				"' total_luminosity='" + (String)luminosityInputText.getValue() + 
				"' start_of_run='" + (String)startRunInputText.getValue() + 
				"' end_of_run='" + (String)endRunTimeInputText.getValue() + "'/>" +
				"</dbs>";
			table.put("api", "updateRun");
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
