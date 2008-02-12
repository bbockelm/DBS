package fnal.gov.web.backing;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;

import org.richfaces.component.html.HtmlRichMessage;
import fnal.gov.web.util.Util;
import fnal.gov.session.NVSessionEJBLocal;
import fnal.gov.session.NameObject;

import java.util.ArrayList;
import java.util.List;

public class Validate {
    private HtmlForm form1;
    private HtmlInputText nameInputText;
    private HtmlSelectOneRadio type;
    private HtmlRichMessage generalInputMessage;
    //private static String SAFE_WORD = "[-\\w_\\.%]+";
    private Boolean loadTable;
    private NVSessionEJBLocal myBean;
    private Util u;
    private ArrayList results = new ArrayList();

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
        u = new Util();
        Object obj = u.getEJB("nvs/NVSessionEJB/local");
        if(obj != null)   {
            myBean = (NVSessionEJBLocal) obj;
            try {
                this.results = (ArrayList<NameObject>)myBean.validate(nameStr, typeStr);
            }catch(Exception e) {
                generalInputMessage.setPassedLabel(e.getMessage());
                generalInputMessage.setRendered(true);
                loadTable = new Boolean(false);
            }
        }
        return "";
         
    }



}
