package fnal.gov.ui.backing.html;

import fnal.gov.rs.entity.Registration;
import fnal.gov.rs.session.RSSessionEJBLocal;
import fnal.gov.ui.util.FacesUtil;
import fnal.gov.ui.util.Util;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;


public class Home {
    private HtmlForm form1;

    private HtmlOutputText url;
    private String urlStr;
    private RSSessionEJBLocal myBean;
    private Util u;
    
    private boolean loadedOnce = false;
    
    private List<Registration> result = new ArrayList();
    private Registration register;
    
    public Home(){
        //u = new Util("jnp://plasma.dhcp.fnal.gov:1099");
        u = new Util("jnp://vocms25.cern.ch:1099");
        Object obj = u.getEJB("rs/RSSessionEJB/local");
        if(obj != null) myBean = (RSSessionEJBLocal) obj;
    }
    
    
    public void setForm1(HtmlForm form1) {
        this.form1 = form1;
    }

    public HtmlForm getForm1() {
        return form1;
    }
    
    public void setUrl(HtmlOutputText url) {
        this.url = url;
    }

    public HtmlOutputText getUrl() {
        return url;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }

    public String getUrlStr() {
        return urlStr;
    }
    
    public Registration getRegister() {
        return register;
    }
    public void setRegister(Registration register) {
        this.register = register;
    }

    
    public String detailAction() {
        //UserSystemState.storeKindsOfPartID((Double)kindOfPartIdText.getValue());
        System.out.println((String)getUrl().getValue());
        setUrlStr((String)getUrl().getValue());
        //FacesUtil.setSessionMapValue("#{home.url}",getUrl());
        //FacesUtil.setApplicationMapValue("#{home.url}",getUrl());
        /*FacesUtil.setManagedBeanValue(FacesContext.getCurrentInstance(),"home.urla",(String)getUrl().getValue());        
        //Object o = FacesUtil.getSessionMapValue("#{home.url}");
        //Object o = FacesUtil.getApplicationMapValue("#{home.url}");
        Object o = FacesUtil.getManagedBeanValue(FacesContext.getCurrentInstance(), "home.urla");
        if(o == null) System.out.println("IN HOME url is NULL");
        else {
            //HtmlOutputText urla = (HtmlOutputText)o;
            //System.out.println("in HOME url is " + urla.getValue());
            System.out.println("in HOME url is " + (String)o);
        }*/
         //myBean = getEJB();
         if(myBean != null)   {
             List<Registration> result = myBean.queryRegistrationFindByURL(urlStr);
             if(result.size()> 0) {
                setRegister(result.get(0));
                System.out.println("Foiund one .... ");
                //FacesUtil.setSessionMapValue("#{home.register}", getRegister());
             }
             
         } 
        
        
        return "detail";
    }
    public List<Registration> getResult() {
        if(this.loadedOnce) return this.result;
        //myBean = getEJB();
        if(myBean != null)   {
            result = myBean.queryRegistrationFindAll();
            loadedOnce = true;
            return result;
        } 
        return new ArrayList();
        
    }
    
}
