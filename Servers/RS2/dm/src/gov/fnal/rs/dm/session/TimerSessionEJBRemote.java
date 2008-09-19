package gov.fnal.rs.dm.session;

import javax.ejb.Remote;

@Remote
public interface TimerSessionEJBRemote {
    public void setTimer(long intervalDuration);
}
