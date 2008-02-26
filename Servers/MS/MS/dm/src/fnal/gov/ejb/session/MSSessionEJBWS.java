package fnal.gov.ejb.session;

import fnal.gov.ejb.entity.Request;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public interface MSSessionEJBWS {

    @WebMethod(operationName = "getRequestById")
    public List<Request> getRequestById(@WebParam(name = "id") Long id) throws Exception;

    @WebMethod(operationName = "getRequestByUser")
    public List<Request> getRequestByUser(@WebParam(name = "dn") String dn) throws Exception;

    @WebMethod(operationName = "getRequestByStatus")
    public List<Request> getRequestByStatus(@WebParam(name = "status") String status) throws Exception;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @WebMethod(operationName = "deleteRequest")
    public void deleteRequest(@WebParam(name = "srcUrl") String srcUrl, 
                                @WebParam(name = "dstUrl") String dstUrl, 
                                @WebParam(name = "path") String path) throws Exception;
                              
    @WebMethod(operationName = "addRequest")
    public Request addRequest(@WebParam(name = "srcUrl") String srcUrl, 
                                @WebParam(name = "dstUrl") String dstUrl, 
                                @WebParam(name = "path") String path, 
                                @WebParam(name = "dn") String dn, 
                                @WebParam(name = "withParents") String withParents, 
                                @WebParam(name = "withForce") String withForce, 
                                @WebParam(name = "notify") String notify) throws Exception;
}
