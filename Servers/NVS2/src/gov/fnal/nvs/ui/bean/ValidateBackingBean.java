package gov.fnal.nvs.ui.bean;

import java.util.List;
import java.util.ArrayList;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;

import org.richfaces.component.html.HtmlRichMessage;
import gov.fnal.nvs.dm.entity.NameObject;


public class ValidateBackingBean extends BaseBean {
	private HtmlForm form1;
	private HtmlInputText nameInputText;
	private HtmlSelectOneRadio type;
	private HtmlRichMessage generalInputMessage;
	private Boolean loadTable;
	private List results = new ArrayList();
	public void setForm1(HtmlForm form1) {
		this.form1 = form1;
	}
	public HtmlForm getForm1() {
		return form1;
	}
	public void setNameInputText(HtmlInputText nameInputText) {
		this.nameInputText = nameInputText;
	}
	public HtmlInputText getNameInputText() {
		return nameInputText;
	}
       	public void setType(HtmlSelectOneRadio type) {
		this.type = type;
	}
	public HtmlSelectOneRadio getType() {
		return type;
	}
	public void setGeneralInputMessage(HtmlRichMessage generalInputMessage) {
		this.generalInputMessage = generalInputMessage;
	}
	public HtmlRichMessage getGeneralInputMessage() {
		return generalInputMessage;
	}
	public void setLoadTable(Boolean loadTable) {
		this.loadTable = loadTable;
	}
	public Boolean getLoadTable() {
		return loadTable;
	}
	public  List<NameObject> getResult() {
		return this.results;
	}
	public String validateAction() {
		loadTable = new Boolean(true);
		generalInputMessage.setPassedLabel("");
		String typeStr = (String)type.getValue();
		String nameStr = (String)nameInputText.getValue();
		System.out.println("name " + nameStr);
		System.out.println("type is " + typeStr);
		try {
			this.results = (List<NameObject>)getServiceLocator().getMainService().validate(nameStr, typeStr);
		}catch(Exception e) {
			generalInputMessage.setPassedLabel(e.getMessage());
			generalInputMessage.setRendered(true);
			loadTable = new Boolean(false);
		}
		return "";
	}
}
