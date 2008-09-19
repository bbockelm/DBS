package gov.fnal.rs.dm.session;

import fnal.gov.client.DbsWebApi;
import gov.fnal.rs.dm.entity.Registration;

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
                    String serverVersion = dbsApi.getDBSServerVersion();
                    String schemaVersion = dbsApi.getDBSSchemaVersion();
                    System.out.println("Server Version is " + serverVersion);
                    System.out.println("Schema Version is " + schemaVersion);
                    setRegServerVersion(r, "active", serverVersion, schemaVersion);
                    //setRegStatus(r, "active");
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
    
    private void setRegServerVersion(Registration r, String status, String serverVersion, String schemaVersion) {
        try {
            r.setStatus(status);
            r.setServerVersion(serverVersion);
            r.setSchemaVersion(schemaVersion);
            System.out.println("trying to call the rsBean");
            if(rsBean == null) System.out.println("rsBean is null");
            else System.out.println("rsBean is NOT null");
            if(r == null) System.out.println("r is null");
            else System.out.println("r is NOT null");
            rsBean.addRegistration(r);
            System.out.println("r is persisted");
        }catch(Exception e) {
            System.out.println("ERROR --->" + e.getMessage());
        }
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
