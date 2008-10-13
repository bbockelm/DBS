package gov.fnal.ms.dm.session;

import gov.fnal.ms.dm.entity.Dbsurl;
import gov.fnal.ms.dm.entity.Person;
import gov.fnal.ms.dm.entity.Request;

//import gov.fnal.ms.dm.mail.MailHelper;

import java.util.List;

//import javax.annotation.Resource;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import gov.fnal.ms.dm.util.Util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

//import javax.ejb.ApplicationException;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

//import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

import javax.jms.QueueSession;

import javax.jws.WebService;

//import javax.transaction.UserTransaction;

@Stateless(name="MSSessionEJB")
@WebService(endpointInterface = "gov.fnal.ms.dm.session.MSSessionEJBWS")
public class MSSessionEJBBean implements MSSessionEJB, MSSessionEJBLocal, MSSessionEJBWS {
//public class MSSessionEJBBean implements MSSessionEJB, MSSessionEJBLocal {
    @PersistenceContext(unitName="dm")
    private EntityManager em;
    private Util u;
    
    @Resource
    private SessionContext ctx;
    
    
    @Resource(mappedName = "ConnectionFactory")
    private QueueConnectionFactory factory;
    @Resource(mappedName = "queue/mailmdb")
    private Queue queueMail;
    @Resource(mappedName = "queue/requestmdb")
    private Queue queueRequest;

    private QueueConnection conn;
    //@Resource
    //private UserTransaction utx;


    public MSSessionEJBBean() {
        u = new Util();
        //mh = new MailHelper();
        /*Object obj = u.getJNDIObj("queue/mdb");
        if(obj != null) queue = (Queue) obj;
        obj = u.getJNDIObj("ConnectionFactory");
        if(obj != null) factory = (QueueConnectionFactory)obj;*/
    }


    @PostConstruct
    public void makeConnection() {
        try {
            conn = factory.createQueueConnection();
        } catch (Exception e) {
            //throw new JMSException(t.toString());
             e.printStackTrace();
        }
    }

    @PreDestroy
    public void endConnection() throws RuntimeException {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public Object mergeEntity(Object entity) {
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    
    public List<Dbsurl> queryDbsurlFindAll() {
        return em.createNamedQuery("Dbsurl.findAll").getResultList();
    }

    public void removeDbsurl(Dbsurl dbsurl) {
        dbsurl = em.find(Dbsurl.class, dbsurl.getId());
        em.remove(dbsurl);
    }

    
    public List<Person> queryPersonFindAll() {
        return em.createNamedQuery("Person.findAll").getResultList();
    }

    public void removePerson(Person person) {
        person = em.find(Person.class, person.getId());
        em.remove(person);
    }

    
    public List<Request> queryRequestFindAll() {
        return em.createNamedQuery("Request.findAll").getResultList();
    }

    public void removeRequest(Request request) {
        request = em.find(Request.class, request.getId());
        em.remove(request);
    }

    
    public List<Request> getRequestById(Long id) throws Exception {
        return em.createNamedQuery("Request.findById")
            .setParameter("id", id)
            .getResultList();
    }
    
    public List<Request> getRequestByUser(String dn) throws Exception {
        //System.out.println("DN is -----------------------> " + dn);
        //List<Request> rList = em.createNamedQuery("Request.findByDn")
        return  em.createNamedQuery("Request.findByDn")
            .setParameter("distinguishedName", dn)
            .getResultList();
        //System.out.println("Rlist returned hhahah");
        //return(rList);
    }

    public List<Request> getRequestByStatus(String status) throws Exception {
        return em.createNamedQuery("Request.findByStatus")
            .setParameter("status", status)
            .getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteRequest(String srcUrl, String dstUrl, String path ) throws Exception {
        List<Request> reqList = getRequest(srcUrl, dstUrl, path);
        Request r;
        if(reqList.size() ==  0) throw new Exception("Request does not exists");
        r = reqList.get(0);
        em.remove(r);
    }
    
    public void updateRequest(String srcUrl, String dstUrl, String path, String status, Integer progress, String detail ) throws Exception {
        List<Request> reqList = getRequest(srcUrl, dstUrl, path);
        Request r;
        if(reqList.size() ==  0) throw new Exception("Request does not exists");
        r = reqList.get(0);
        if(!u.isNull(status)) r.setStatus(status);
        if(!u.isNull(progress)) r.setProgress(progress);
        if(!u.isNull(detail)) r.setDetail(detail);
	em.persist(r);
        System.out.println("Inside updateRequest.  Entity updated");
        
        //Send Email
	System.out.println("Before sening email cheing status " + status);
        String notify = r.getNotify();
        if(status.equals("Finished") || status.equals("Halted")) {
            String subject = "Your Migration request is " + status;
            String body = "The request to migrate the dataset " + path + "\n" +
            "from DBS instance " + srcUrl + "\n" +
            "to DBS instance " + dstUrl + "\n" +
            "with request id " + r.getId() + "\n" +
            "is " + status + ".\n\n" +
            "Information returned from the system is " + detail;
            if(!u.isNull(notify)) {
		    QueueSession session = null;
		    try {
	                session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
        	        MapMessage mapMsg = session.createMapMessage();
                	mapMsg.setString("recipient", notify);
	                mapMsg.setString("subject", subject);
        	        mapMsg.setString("body", body);
                	session.createSender(queueMail).send(mapMsg);
		    } finally {
			    if(session != null)  session.close();
		    }
                //mh.sendMessage(notify, subject, body);
            } else System.out.println("Notify is NULL");
        }
    }
    
    
    //@ApplicationException(rollback=true)
    public Request addRequest(String srcUrl, String dstUrl, String path, 
                             String dn, String withParents, String withForce, String notify) throws Exception {
        
        
        //Check if request already exists or not                     
        List<Request> reqList = getRequest(srcUrl, dstUrl, path);
        Request r;
        if(reqList.size() >  0) {
            r = reqList.get(0);
            System.out.println("Request with ID = " + r.getId() + " Already exists");
            ctx.setRollbackOnly();
            throw new Exception("Request with ID = " + r.getId() + " Already exists");
            
        } else {
            if (u.isNull(withParents)) withParents = "y";
            if (u.isNull(withForce)) withForce = "y";
            Dbsurl dbsUrlDst = addDbsUrl(dstUrl);
            Dbsurl dbsUrlSrc = addDbsUrl(srcUrl);
            Person p = addPerson(dn);
            r = new Request();
            r.setNotify(notify);
            r.setPath(path);
            r.setPerson(p);
            r.setSrcUrl(dbsUrlSrc);
            r.setDstUrl(dbsUrlDst);
            r.setWithForce(withForce);
            r.setWithParents(withParents);
            r.setProgress(new Integer(0));
            r.setStatus("Queued");
            r.setDetail("Waiting to be Picked up");
            em.persist(r);
            System.out.println("Request Entity persisted");
            System.out.println("ID is " + r.getId());
	    System.out.println("Request is ADDED...........................");
	    //System.out.println("r.getSrcUrl() " + r.getSrcUrl());
	    //System.out.println("r.getDstUrl() " + r.getDstUrl());
	    //System.out.println("r.getPath() " + r.getPath());
            //u.sendMsg(factory, queue, "newRequest");
            //u.sendMsg(conn, queue, "newRequest");
	    QueueSession session = null;
	    try {
	    	session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
	            MapMessage mapMsg = session.createMapMessage();
        	    mapMsg.setLong("request", r.getId());
	            session.createSender(queueRequest).send(mapMsg);
	    } finally {
		    if (session != null) session.close();
	    }

            System.out.println("Message sent to queue");
        }
        return r;
    }
    
    private List<Request> getRequest(String srcUrl, String dstUrl, String path) {
        return em.createNamedQuery("Request.findByUnique")
            .setParameter("path", path)
            .setParameter("dstUrl", dstUrl)
            .setParameter("srcUrl", srcUrl).
            getResultList();
    }
    
    private Dbsurl addDbsUrl(String url){
        
        //Check if srcUrl already exist or not
        List<Dbsurl> uList = em.createNamedQuery("Dbsurl.findByUrl")
                .setParameter("url", url)
                .getResultList();
        Dbsurl dbsUrl; 
        if(uList.size() !=  0) dbsUrl =  uList.get(0);      
        else {
            dbsUrl = new Dbsurl();
            dbsUrl.setUrl(url);
            em.persist(dbsUrl);
            System.out.println("dbsUrl persisted " + dbsUrl.getId());
        }
        return dbsUrl;
    }
    
    private Person addPerson(String dn) {
        //Check if Person already exist or not
        List<Person> pList = em.createNamedQuery("Person.findByDistinguishedName")
            .setParameter("distinguishedName", dn)
            .getResultList();
            
        Person p;
        if(pList.size() !=  0) p =  pList.get(0);      
        else {
            p = new Person();
            p.setDistinguishedName(dn);
            em.persist(p);
            System.out.println("Person persisted " + p.getId());
        }
        return p;
    }
}
