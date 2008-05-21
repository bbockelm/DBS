package fnal.gov.rs.session;

import fnal.gov.rs.entity.Person;
import fnal.gov.rs.entity.Registration;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface RSSessionEJB {
    Object mergeEntity(Object entity);

    Object persistEntity(Object entity);

    List<Person> queryPersonFindByDistinguishedName(Object distinguishedName);

    List<Registration> queryRegistrationFindByAlias(Object alias);

    List<Registration> queryRegistrationFindByURL(Object url);

    List<Registration> queryRegistrationFindAll();

    void removeRegistration(Registration registration);
    
    Registration addRegistration(Registration rIn) throws Exception;
    
    
}
