package gov.fnal.nvs.dm.test;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;


import gov.fnal.nvs.dm.entity.*;
import gov.fnal.nvs.dm.test.util.HibernateUtil;
//import gov.fnal.nvs.dm.dao.PrimaryDao;
//import gov.fnal.nvs.dm.dao.hibernate.PrimaryDaoImpl;
import gov.fnal.nvs.dm.service.MainService;
//import gov.fnal.nvs.dm.service.impl.*;


public class Test {
	/*
	public List<Primarydataset> listPrimaryDatasets() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Primarydataset> result = (List<Primarydataset>)session.createCriteria(Primarydataset.class).list();
		for (Primarydataset aPrimarydataset: result) {
			System.out.println(aPrimarydataset.getName());
			//List<Processeddataset>  pdList = aPrimarydataset.getProcessedDatasetList();
			Set<Processeddataset>  pdList = aPrimarydataset.getProcessedDatasetSet();
			System.out.println("SIZE is " + pdList.size());
			for(Processeddataset aProcesseddataset: pdList) {
				if(aProcesseddataset == null) System.out.println("PROCDS is NULL");
				else System.out.println("PROC DS " + aProcesseddataset.getName());
			}
		}
		session.getTransaction().commit();
		return result;
	}*/
	
	public static void main(String args[]) throws Exception{
		//Test t = new Test();
		//t.listPrimaryDatasets();
		//PrimaryDao dao = new PrimaryDaoImpl();
		//List<Primarydataset> result = dao.listPrimaryDatasets();
		//List<Primarydataset> result = dao.listPrimaryDatasets("RelVal%");	
		//PrimaryService service = new PrimaryServiceImpl();
		//service.setPrimaryDao(dao);
		
		Resource resource = new FileSystemResource("../../etc/applicationContext.xml");
	  	BeanFactory factory = new XmlBeanFactory(resource);
		MainService service = (MainService)factory.getBean("mainService");
		{
			List<NameObject> result = service.validate("relval", "Primary");
			for(NameObject aNameObject: result) System.out.println(aNameObject.getName() + "  < --- > " + aNameObject.getSimilar());
		}
		{
			List<NameObject> result = service.validate("cmssw", "Processed");
			for(NameObject aNameObject: result) System.out.println(aNameObject.getName() + "  < --- > " + aNameObject.getSimilar());
		}
		{
			List<NameObject> result = service.validate("digi", "Tier");
			for(NameObject aNameObject: result) System.out.println(aNameObject.getName() + "  < --- > " + aNameObject.getSimilar());
		}
		{
			List<NameObject> result = service.validate("tao", "Physics");
			for(NameObject aNameObject: result) System.out.println(aNameObject.getName() + "  < --- > " + aNameObject.getSimilar());
		}

		/*{
			PrimaryService service = (PrimaryService)factory.getBean("primaryService");
			List<Primarydataset> result = service.listPrimaryDatasets("RelVal%");	
			for(Primarydataset aPrimarydataset: result) System.out.println(aPrimarydataset.getName());
		}
		{
			ProcessedService service = (ProcessedService)factory.getBean("processedService");
			List<Processeddataset> result = service.listProcessedDatasets();	
			for(Processeddataset aProcesseddataset: result) System.out.println(aProcesseddataset.getName());
		}
		{
			TierService service = (TierService)factory.getBean("tierService");
			List<Datatier> result = service.listDataTiers();	
			for(Datatier aDataTier: result) System.out.println(aDataTier.getName());
		}
		{
			PhysicsService service = (PhysicsService)factory.getBean("physicsService");
			List<Physicsgroup> result = service.listPhysicsGroups();	
			for(Physicsgroup aPhysicsGroup: result) System.out.println(aPhysicsGroup.getPhysicsgroupname());
		}*/


	}
									 
									                                         
}
