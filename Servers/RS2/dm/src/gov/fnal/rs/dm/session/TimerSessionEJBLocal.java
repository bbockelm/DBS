package gov.fnal.rs.dm.session;

import javax.ejb.Local;

@Local
public interface TimerSessionEJBLocal {
    public void setTimer(long intervalDuration);
}
