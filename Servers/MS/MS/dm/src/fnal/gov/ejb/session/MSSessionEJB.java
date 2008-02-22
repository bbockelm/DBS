package fnal.gov.ejb.session;


import fnal.gov.ejb.entity.Dbsurl;
import fnal.gov.ejb.entity.Person;
import fnal.gov.ejb.entity.Request;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface MSSessionEJB {
    Object mergeEntity(Object entity);

    Object persistEntity(Object entity);

    List<Dbsurl> queryDbsurlFindAll();

    void removeDbsurl(Dbsurl dbsurl);

    List<Person> queryPersonFindAll();

    void removePerson(Person person);

    List<Request> queryRequestFindAll();

    void removeRequest(Request request);

    Request addRequest(String srcUrl, String dstUrl, String path, String dn, 
                      String withParents, String withForce, String notify) throws Exception;
    List<Request> getRequestByUser(String dn) throws Exception;
    List<Request> getRequestById(Long id) throws Exception;
    List<Request> getRequestByStatus(String status) throws Exception;
    void deleteRequest(String srcUrl, String dstUrl, String path ) throws Exception;
    void updateRequest(String srcUrl, String dstUrl, String path, String status, Integer progress, String detail ) throws Exception;
}
