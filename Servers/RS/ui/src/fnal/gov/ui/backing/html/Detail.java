package fnal.gov.ui.backing.html;

import fnal.gov.ui.util.FacesUtil;
import fnal.gov.rs.entity.Registration;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

public class Detail {
    private HtmlForm form1;

    public void setForm1(HtmlForm form1) {
        this.form1 = form1;
    }

    public HtmlForm getForm1() {
        return form1;
    }
    
    public String homeAction() {
        
        /*System.out.println("in home action");
        Object o = FacesUtil.getSessionMapValue("#{home.register}");
        //Object o = FacesUtil.getApplicationMapValue("#{home.url}");
        //Object o = FacesUtil.getManagedBeanValue(FacesContext.getCurrentInstance(), "home.urla");
        if(o == null) System.out.println(" url is NULL");
        else {
            Registration register = (Registration)o;
            //System.out.println("url is " + url.getValue());
            System.out.println("url is " + register.getUrl());
        }*/
        return "home";
    }
}
