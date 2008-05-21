package fnal.gov.rs.session;

import javax.ejb.Remote;

@Remote
public interface TimerSessionEJBRemote {
    public void setTimer(long intervalDuration);
}
