package gov.fnal.ms.test;
import gov.fnal.ms.dm.session.MSTimerSessionRemote;

public class TestTimer {
	public static void main(String args[]) {
		MSTimerSessionRemote ejbTObj = (MSTimerSessionRemote)(new Util()).getEJB("ms/MSTimerSession/remote");
		ejbTObj.setTimer(30000);
	}
}
