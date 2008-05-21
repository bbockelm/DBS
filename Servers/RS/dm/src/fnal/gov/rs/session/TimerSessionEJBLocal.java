package fnal.gov.rs.session;

import javax.ejb.Local;

@Local
public interface TimerSessionEJBLocal {
    public void setTimer(long intervalDuration);
}
