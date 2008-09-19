package gov.fnal.rs.dm.session;

import gov.fnal.rs.dm.entity.Person;
import gov.fnal.rs.dm.entity.Registration;

import java.rmi.Remote;

import java.rmi.RemoteException;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface RSSessionEJBWebService extends Remote {
    
    List<Registration> queryRegistrationFindByAlias(Object alias) throws RemoteException;

    List<Registration> queryRegistrationFindByURL(Object url) throws RemoteException;

    List<Registration> queryRegistrationFindAll() throws RemoteException;

    void removeRegistration(Registration registration) throws RemoteException;
    
    //@WebMethod(operationName = "addRegistration")
    Registration addRegistration(Registration rIn) throws Exception;
}
