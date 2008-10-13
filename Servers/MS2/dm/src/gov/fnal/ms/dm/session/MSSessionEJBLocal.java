package gov.fnal.ms.dm.session;

import gov.fnal.ms.dm.entity.Dbsurl;
import gov.fnal.ms.dm.entity.Person;
import gov.fnal.ms.dm.entity.Request;

import java.util.List;

import javax.ejb.Local;

@Local
public interface MSSessionEJBLocal {
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
