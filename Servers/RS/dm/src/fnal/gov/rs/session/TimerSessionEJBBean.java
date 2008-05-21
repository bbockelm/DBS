package fnal.gov.rs.session;

import fnal.gov.client.DbsWebApi;
import fnal.gov.rs.entity.Registration;

import java.util.Date;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

@Stateless(name="TimerSessionEJB")
public class TimerSessionEJBBean implements TimerSessionEJBLocal, 
                                            TimerSessionEJBRemote {
    @Resource
    TimerService timerService;
    @EJB
    RSSessionEJBLocal rsBean;
    
    public TimerSessionEJBBean() {
    }
    public void setTimer(long intervalDuration) {
        //Timer timer = timerService.createTimer(            
        timerService.createTimer( new Date(), intervalDuration, "Created new timer");
        /*try{
            List<Registration> rList = rsBean.queryRegistrationFindAll();
            for(Registration r: rList) {
                System.out.println("URL" + r.getUrl());
            }
        }catch(Exception e) {
            System.out.println("ERRR MESSAGE " + e.getMessage());
        }*/
    }
    
    @Timeout
    public void checkDbsUrls(Timer timer) {
        System.out.println("Timeout occurred");
        try{
            List<Registration> rList = rsBean.queryRegistrationFindAll();
            for(Registration r: rList) {
                System.out.println("URL " + r.getUrl());
                try{
                    DbsWebApi dbsApi = new DbsWebApi(r.getUrl());
                    System.out.println("Server Version is " + dbsApi.getDBSServerVersion());
                    setRegStatus(r, "active");
                }catch(Exception e) {
                    System.out.println("ERROR ---> " + e.getMessage());
                    setRegStatus(r, "inactive");
                }
            }
        }catch(Exception e) {
            System.out.println("ERROR ---> " + e.getMessage());
        }
         //timer.cancel();
    }
    
    private void setRegStatus(Registration r, String status) {
        try {
            r.setStatus(status);
            rsBean.addRegistration(r);
        }catch(Exception e) {
            System.out.println("ERROR --->" + e.getMessage());
        }
    }
}
