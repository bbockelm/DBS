package ui;
import javax.faces.component.html.HtmlInputText;
import org.richfaces.component.html.HtmlRichMessage;
import java.util.Hashtable;
import ui.util.Util;

public class TierAdd {
	private HtmlInputText tierInputText;
	private HtmlRichMessage valueInputMessage;
	private HtmlRichMessage generalInputMessage;
	
	public void TierAdd(){}
	
	public void setTierInputText(HtmlInputText tierInputText) {
		this.tierInputText = tierInputText;
	}
	public HtmlInputText getTierInputText() {
		return tierInputText;
	}
		
	public void setGeneralInputMessage(HtmlRichMessage generalInputMessage) {
		this.generalInputMessage = generalInputMessage;
	}
	
    	public HtmlRichMessage getGeneralInputMessage() {
		return generalInputMessage;
    	}
     
    
	/*public String addTierAction() {
		System.out.println("Tier is " + (String)tierInputText.getValue());
		return "";
	}*/
	
	public String addTierAction() {
		try {
			Hashtable table = new Hashtable();
			String xml = "<?xml version='1.0' standalone='yes'?>" +
				"<dbs>" +
				"<tier tier_name='" + (String)tierInputText.getValue() + "'/>" +
				"</dbs>";
			table.put("api", "insertTier");
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
