package gov.fnal.rs.client.api;

import gov.fnal.rs.client.util.Util;
import gov.fnal.rs.dm.entity.Registration;
import gov.fnal.rs.dm.session.RSSessionEJB;
import gov.fnal.rs.dm.session.TimerSessionEJBRemote;

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
