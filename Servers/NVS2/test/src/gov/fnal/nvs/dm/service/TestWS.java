package gov.fnal.nvs.dm.service;

import java.lang.reflect.Method;
import java.util.List;
//import gov.fnal.nvs.dm.service.NameObject;
import gov.fnal.nvs.dm.entity.NameObject;
import gov.fnal.nvs.dm.service.MainServiceWS;
import gov.fnal.nvs.dm.service.impl.MainServiceWSImplService;
//import gov.fnal.nvs.dm.service.impl.*;
public class TestWS {
	static MainServiceWSImplService service = new MainServiceWSImplService ();
	public static void main(String args[]) throws Exception{
		
		/*Method[] methods = service.getClass().getMethods();
		for(Method aMethod: methods) {
			System.out.println("name is " + aMethod.getName());
		}*/	

		gov.fnal.nvs.dm.service.impl.MainServiceWS port = service.getMainServiceWSImplPort();
		List<gov.fnal.nvs.dm.service.NameObject> noList =  port.validate("RelVal", "Primary");
		for(gov.fnal.nvs.dm.service.NameObject aNo: noList) {
			System.out.println(aNo.getName());
			System.out.println(aNo.getSimilar());
		}
	}
}

