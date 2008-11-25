package gov.fnal.rss.dm.test;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;


import gov.fnal.rss.dm.entity.*;
import gov.fnal.rss.dm.test.util.HibernateUtil;
import gov.fnal.rss.dm.service.RunSeqService;


public class Test {
	
	public static void main(String args[]) throws Exception{
		
		Resource resource = new FileSystemResource("../../etc/applicationContext.xml");
	  	BeanFactory factory = new XmlBeanFactory(resource);
		RunSeqService service = (RunSeqService)factory.getBean("runSeqService");
		{
			//service.createRunSequence("Testseq1", 1, 5);
			System.out.println("currnt number is " + service.getCurrRunNumber("Testseq1"));
			System.out.println("Next number is " + service.getNextRunNumber("Testseq1"));
			System.out.println("currnt number is " + service.getCurrRunNumber("Testseq1"));
		}


	}
									 
									                                         
}
