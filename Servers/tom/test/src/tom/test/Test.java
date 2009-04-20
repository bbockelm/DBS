package tom.dm.test;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


import tom.dm.entity.*;
import tom.dm.service.RunService;


public class Test {
	
	public static void main(String args[]) throws Exception{
		
		Resource resource = new FileSystemResource("../../etc/applicationContext.xml");
	  	BeanFactory factory = new XmlBeanFactory(resource);
		RunService service = (RunService)factory.getBean("runService");
		{
			List<Run> runs = service.listRuns();
			for(Run aRun: runs) {
				System.out.println("ID is " + aRun.getId());
			}
		}


	}
									 
									                                         
}
