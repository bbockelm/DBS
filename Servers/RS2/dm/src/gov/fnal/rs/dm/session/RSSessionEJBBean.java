package gov.fnal.rs.dm.session;

import gov.fnal.rs.dm.entity.Person;
import gov.fnal.rs.dm.entity.Registration;

import gov.fnal.rs.dm.util.Util;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.jws.WebService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name="RSSessionEJB")
@WebService(endpointInterface = "gov.fnal.rs.dm.session.RSSessionEJBWebService")
public class RSSessionEJBBean implements RSSessionEJB, RSSessionEJBLocal, 
                                         RSSessionEJBWebService {
    @PersistenceContext(unitName="rsunit")
    private EntityManager em;
    @Resource
    private SessionContext ctx;
    //@EJB
    //TimerSessionEJBLocal timerBean;
    
    private Util u;

    public RSSessionEJBBean() {
        u = new Util();
    }

    public Object mergeEntity(Object entity) {
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    /** <code>SELECT p FROM Person p WHERE p.distinguishedName = :distinguishedName</code> */
    public List<Person> queryPersonFindByDistinguishedName(Object distinguishedName) {
        return em.createNamedQuery("Person.findByDistinguishedName").setParameter("distinguishedName", distinguishedName).getResultList();
    }

    /** <code>SELECT o FROM Registration o WHERE o.alias = :alias</code> */
    public List<Registration> queryRegistrationFindByAlias(Object alias) {
        return em.createNamedQuery("Registration.findByAlias").setParameter("alias", alias).getResultList();
    }

    /** <code>SELECT o FROM Registration o WHERE o.url = :url</code> */
    public List<Registration> queryRegistrationFindByURL(Object url) {
        System.out.println("queryRegistrationFindByURL");
        return em.createNamedQuery("Registration.findByURL").setParameter("url", url).getResultList();
        
    }

    /** <code>select o from Registration o</code> */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Registration> queryRegistrationFindAll() {
        return em.createNamedQuery("Registration.findAll").getResultList();
    }

    public void removeRegistration(Registration registration) {
        registration = em.find(Registration.class, registration.getId());
        em.remove(registration);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Registration addRegistration(Registration rIn) throws Exception {
        //System.out.println("inside addRegi");
        //timerBean.setTimer(20);
        String url = rIn.getUrl();
        //ctx.setRollbackOnly();
        if(u.isNull(url)) throw new Exception ("URL of a registration request cannot be null");
        if(u.isNull(rIn.getAlias())) rIn.setAlias(url);
        List<Registration> rList = queryRegistrationFindByURL(url);
        Registration r;
        if(rList.size() !=  0) r = updateRegistration(rList.get(0), rIn);
        else r = insertRegistration(rIn);
        return r;
    }
    
    
    
    private Registration updateRegistration(Registration r, Registration rIn) throws Exception {
        //System.out.println("in updateRegis");
        
        String alias = rIn.getAlias();
        String accName = rIn.getAccountName();
        String critical = rIn.getCritical();
        String dbName = rIn.getDbName();
        
        Integer dbPort = rIn.getDbPort();
        String nodeName = rIn.getNodeName();
        String physicalLocation = rIn.getPhysicalLocation();
        String schemaVersion = rIn.getSchemaVersion();
        String serverVersion = rIn.getServerVersion();
        
        String status = rIn.getStatus();
        
        r.setPerson(addPerson(rIn.getPerson()));
        
        
        if(!r.getAlias().equals(alias)) r.setAlias(alias);
        if(!u.isNull(accName))
            r.setAccountName(accName);
        if(!u.isNull(critical))
            r.setCritical(critical);
        if(!u.isNull(dbName))
            r.setDbName(dbName);
        if(!u.isNull(dbPort))
            r.setDbPort(dbPort);
        if(!u.isNull(nodeName))
            r.setNodeName(nodeName);
        if(!u.isNull(physicalLocation))
            r.setPhysicalLocation(physicalLocation);
        if(!u.isNull(schemaVersion))
            r.setSchemaVersion(schemaVersion);
        if(!u.isNull(serverVersion))
            r.setServerVersion(serverVersion);
        if(!u.isNull(status))
            r.setStatus(status);
        
        /*if(!u.isNull(accName))
            if(!r.getAccountName().equals(accName)) r.setAccountName(accName);
        if(!u.isNull(critical))
            if(!r.getCritical().equals(critical)) r.setCritical(critical);
        if(!u.isNull(dbName))
            if(!r.getDbName().equals(dbName)) r.setDbName(dbName);
        System.out.println("line 9");
        if(!u.isNull(dbPort))
            if(r.getDbPort() != dbPort) r.setDbPort(dbPort);
        System.out.println("line 9.1");
        if(!u.isNull(nodeName))
            if(!r.getNodeName().equals(nodeName)) r.setNodeName(nodeName);
        System.out.println("line 9.2");
        if(!u.isNull(physicalLocation))
            if(!r.getPhysicalLocation().equals(physicalLocation)) r.setPhysicalLocation(physicalLocation);
        System.out.println("line 9.3");
        if(!u.isNull(schemaVersion))
            if(!r.getSchemaVersion().equals(schemaVersion)) r.setSchemaVersion(schemaVersion);
        System.out.println("line 9.4");
        if(!u.isNull(serverVersion))
            r.setServerVersion(serverVersion);
            //if(!r.getServerVersion().equals(serverVersion)) r.setServerVersion(serverVersion);
        System.out.println("line 10");
        if(!u.isNull(status))
            if(!r.getStatus().equals(status)) r.setStatus(status);
        System.out.println("line 11");*/
        return r;
    }
    
    private Registration insertRegistration(Registration rIn) throws Exception {
        //Add Person if he/she does not exists
        rIn.setPerson(addPerson(rIn.getPerson()));

        if(!u.isNull(rIn.getCritical())) rIn.setCritical("n");
        if(!u.isNull(rIn.getStatus())) rIn.setStatus("active");

        em.persist(rIn);
        System.out.println("Registration persisted " + rIn.getId());
        return rIn;
    }
    
     private Person addPerson(Person pIn) throws Exception {
        String dn = pIn.getDistinguishedName();
        String name = pIn.getName();
        String cInfo = pIn.getContactInfo();
        if(u.isNull(dn)) throw new Exception ("Distinguished Name of a new user cannot be null");
        if(u.isNull(name)) name = dn;
        if(u.isNull(cInfo)) name = "";
        //Check if Person already exist or not
        List<Person> pList = queryPersonFindByDistinguishedName(dn);
        Person p;
        if(pList.size() !=  0) p = updatePerson(pList.get(0), pIn);
        else p = insertPerson(pIn);
        //System.out.println("Person in addPerson " + p.getId());
        return p;
    }
    
    private Person updatePerson(Person p, Person pIn) throws Exception {
        String name = pIn.getName();
        String cInfo = pIn.getContactInfo();
        if(!p.getName().equals(name)) p.setName(name);
        if(!p.getContactInfo().equals(cInfo)) p.setContactInfo(cInfo);
            //System.out.println("Just set the cInfo to " + cInfo);
        //}
        
        //em.persist(p);
        //System.out.println("Person persisted " + p.getId());
        return p;
    }
    
    private Person insertPerson(Person pIn) throws Exception {
        pIn.setCreationDate(((new Date()).getTime() / 1000));
        em.persist(pIn);
        System.out.println("Person persisted " + pIn.getId());
        return pIn;
    }
}
