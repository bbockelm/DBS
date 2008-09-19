package gov.fnal.rs.client.test;

import gov.fnal.rs.client.api.RegApi;
import gov.fnal.rs.client.util.Util;
import gov.fnal.rs.dm.entity.Person;
import gov.fnal.rs.dm.entity.Registration;
import gov.fnal.rs.dm.session.RSSessionEJB;
import gov.fnal.rs.dm.session.TimerSessionEJBRemote;


public class RegTest {
    //private Util u;
    public RegTest() {
        //u = new Util();
    }
    
    public static void main(String args[]) throws Exception{
        //Util u = new Util("jnp://plasma.dhcp.fnal.gov:1099");
        //Util u = new Util("jnp://vocms25.cern.ch:1099");
        //Util u = new Util("jnp://vocmsvm01.cern.ch:1099");
        //Util u = new Util("jnp://venom.dhcp.fnal.gov:1099");
        Util u = new Util("jnp://" + args[0] + ":1099");
        /*Object obj = u.getInitialContext().lookup("rs/RSSessionEJB/remote");
        RSSessionEJB ejbObj = (RSSessionEJB)obj;*/
        
        TimerSessionEJBRemote ejbTObj = (TimerSessionEJBRemote)u.getInitialContext().lookup("rs/TimerSessionEJB/remote");
        
        Person p = new Person();
        p.setContactInfo("sekhri@fnal.gov");
        p.setDistinguishedName("VIJAY_DN");
        p.setName("Vijay Sekhri");
        Registration r = new Registration();
        //r.setUrl("test_URL");
        r.setUrl("https://cmsdbsprod.cern.ch:8443/cms_dbs_prod_global_writer/servlet/DBSServlet");
        r.setAlias("Prod_Global");
        r.setAccountName("test_AccNambbbbbb");
        r.setCritical("y");
        r.setDbName("test_dbName");
        r.setDbPort(2222);
        r.setNodeName("test_nodeName");
        r.setPhysicalLocation("test_physicalLocation");
        r.setSchemaVersion("test_schemaVersion");
        r.setServerVersion("test_serverVersion");
        r.setStatus("active");
        r.setPerson(p);
        System.out.println("Adding a new Registration ");
        try{
            //RegApi rApi = new RegApi("jnp://plasma.dhcp.fnal.gov:1099");
            //RegApi rApi = new RegApi("jnp://vocms25.cern.ch:1099");
            //RegApi rApi = new RegApi("jnp://vocmsvm01.cern.ch:1099");
            RegApi rApi = new RegApi("jnp://" + args[0] + ":1099");
            //RegApi rApi = new RegApi("jnp://venom.dhcp.fnal.gov:1099");
            rApi.addRegistration(r);
            //ejbObj.addRegistration(r);
            //ejbTObj.setTimer(300000);
            System.out.println("Done Adding a new Registration ");
        }catch(Exception e){
            System.out.println("ERROR " + e.getMessage());
        }
        
        
    }
}
