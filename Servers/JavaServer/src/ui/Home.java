package ui;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import org.richfaces.component.html.HtmlDataTable; 
import org.richfaces.component.html.HtmlDatascroller;
import org.richfaces.event.DropEvent;
import org.richfaces.component.html.HtmlDndParam;




public class Home {
    	private HtmlForm form1;
    	private HtmlDataTable dataTable1;
    	private HtmlDatascroller dataScroller1;
    	private HtmlOutputText kindOfPartIdText;
    	private HtmlDndParam instanceNameParam;
	
    	public void setForm1(HtmlForm form1) {
		this.form1 = form1;
    	}
     	public HtmlForm getForm1() {
		return form1;
    	}
    	public void setDataScroller1(HtmlDatascroller dataScroller1) {
		this.dataScroller1 = dataScroller1;
    	}
     	public HtmlDatascroller getDataScroller1() {
		return dataScroller1;
    	}
     	public void setDataTable1(HtmlDataTable dataTable1) {
		this.dataTable1 = dataTable1;
    	}
     	public HtmlDataTable getDataTable1() {
		return dataTable1;
    	}
    	public void setKindOfPartIdText(HtmlOutputText kindOfPartIdText) {
		this.kindOfPartIdText = kindOfPartIdText;
    	}
     	public HtmlOutputText getKindOfPartIdText() {
		return kindOfPartIdText;
    	}
     	public void setInstanceNameParam(HtmlDndParam instanceNameParam) {
		this.instanceNameParam = instanceNameParam;
    	}
    	public HtmlDndParam getInstanceNameParam() {
		return instanceNameParam;
    	}


	public void processSrcDrop(DropEvent event) {
		System.out.println("Inside drop event");
		FacesContext context = FacesContext.getCurrentInstance();
		System.out.println("The param passed is ");
		System.out.println("ext cont " + context.getExternalContext());
		System.out.println("param map " + context.getExternalContext().getRequestParameterMap());
		System.out.println("name " + context.getExternalContext().getRequestParameterMap().get("name"));
		System.out.println("label " + context.getExternalContext().getRequestParameterMap().get("label"));
		System.out.println(getInstanceNameParam().getValue());
		System.out.println("Done");
     	}	
}

