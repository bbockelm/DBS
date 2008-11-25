package gov.fnal.rss.dm.service;

import java.lang.reflect.Method;
import java.util.List;
//import gov.fnal.rss.dm.service.MainServiceWS;
import gov.fnal.rss.dm.service.impl.RunSeqWSImplService;
//import gov.fnal.rss.dm.service.impl.*;
public class TestWS {
	static RunSeqWSImplService service = new RunSeqWSImplService ();
	public static void main(String args[]) throws Exception{
		
		/*Method[] methods = service.getClass().getMethods();
		for(Method aMethod: methods) {
			System.out.println("name is " + aMethod.getName());
		}*/

		gov.fnal.rss.dm.service.impl.RunSeqWS port = service.getRunSeqWSImplPort();
		port.createRunSequence("Testseq3", 0, 0);
		System.out.println("CURRNT NUMBER is " + port.getCurrRunNumber("Testseq3"));
		System.out.println("Next NUMBER is " + port.getNextRunNumber("Testseq3"));
	}
}

