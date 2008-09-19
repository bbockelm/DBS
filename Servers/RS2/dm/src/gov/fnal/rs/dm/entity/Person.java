package gov.fnal.rs.dm.entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({@NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"), 
@NamedQuery(name = "Person.findByDistinguishedName", query = "SELECT p FROM Person p WHERE p.distinguishedName = :distinguishedName")
})
@Table(name = "Person")
@SequenceGenerator(name="seq", sequenceName="seq_person")
public class Person implements Serializable {
    private String contactInfo;
    private Long createdBy;
    private Long creationDate;
    @Column(nullable = false)
    private String distinguishedName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @Column(nullable = false)
    private Long id;
    private Long lastModificationDate;
    private Long lastModifiedBy;
    private String name;
    
    /*@OneToMany(mappedBy = "person")
    private List<Registration> registrationList;*/

    public Person() {
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public String getDistinguishedName() {
        return distinguishedName;
    }

    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Long lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registrationList) {
        this.registrationList = registrationList;
    }

    public Registration addRegistration(Registration registration) {
        getRegistrationList().add(registration);
        registration.setPerson(this);
        return registration;
    }

    public Registration removeRegistration(Registration registration) {
        getRegistrationList().remove(registration);
        registration.setPerson(null);
        return registration;
    }*/
}
