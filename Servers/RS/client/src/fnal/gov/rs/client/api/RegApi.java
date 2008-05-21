package fnal.gov.rs.client.api;

import fnal.gov.rs.client.util.Util;
import fnal.gov.rs.entity.Registration;
import fnal.gov.rs.session.RSSessionEJB;
import fnal.gov.rs.session.TimerSessionEJBRemote;

public class RegApi {
    private RSSessionEJB ejbObj;
    private TimerSessionEJBRemote ejbTObj;
    private Util u ;
    public RegApi(String url) throws Exception {
        u = new Util(url);
        Object obj = u.getInitialContext().lookup("rs/RSSessionEJB/remote");
        if(obj != null) ejbObj = (RSSessionEJB)obj;
        obj = u.getInitialContext().lookup("rs/TimerSessionEJB/remote");
        if(obj != null) ejbTObj = (TimerSessionEJBRemote)obj;
    }
    
    public void addRegistration(Registration r) throws Exception {
        ejbObj.addRegistration(r);
    }
    public void setTimer(long interval) throws Exception{
        ejbTObj.setTimer(interval);
    }

}
