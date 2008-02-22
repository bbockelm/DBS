package fnal.gov.ejb.session;

import fnal.gov.ejb.entity.Dbsurl;
import fnal.gov.ejb.entity.Person;
import fnal.gov.ejb.entity.Request;

import java.util.List;

//import javax.annotation.Resource;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import fnal.gov.ejb.util.Util;

//import javax.transaction.UserTransaction;

@Stateless(name="MSSessionEJB")
public class MSSessionEJBBean implements MSSessionEJB, MSSessionEJBLocal {
    @PersistenceContext(unitName="dm")
    private EntityManager em;
    private Util u;
    //@Resource
    //private UserTransaction utx;


    public MSSessionEJBBean() {
        u = new Util();
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
        return em.createNamedQuery("Request.findByDn")
            .setParameter("distinguishedName", dn)
            .getResultList();
    }

    public List<Request> getRequestByStatus(String status) throws Exception {
        return em.createNamedQuery("Request.findByStatus")
            .setParameter("status", status)
            .getResultList();
    }


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
        System.out.println("Entity updated");
    }
    
    public Request addRequest(String srcUrl, String dstUrl, String path, 
                             String dn, String withParents, String withForce, String notify) throws Exception {
        
        
        //Check if request already exists or not                     
        List<Request> reqList = getRequest(srcUrl, dstUrl, path);
        Request r;
        if(reqList.size() >  0) {
            r = reqList.get(0);
            System.out.println("Request with ID = " + r.getId() + " Already exists");
            throw new Exception("Request with ID = " + r.getId() + " Already exists");
            
        } else {
        
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
            System.out.println("Entity persisted");
            System.out.println("ID is " + r.getId());
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
