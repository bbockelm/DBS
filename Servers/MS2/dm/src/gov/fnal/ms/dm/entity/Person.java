
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package gov.fnal.ms.dm.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
@NamedQueries({@NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"), @NamedQuery(name = "Person.findByDistinguishedName", query = "SELECT p FROM Person p WHERE p.distinguishedName = :distinguishedName")})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "DistinguishedName", nullable = false)
    private String distinguishedName;
    @OneToMany(mappedBy = "createdBy")
    private Collection<Request> requestCollection;

    public Person() {
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(Long id, String distinguishedName) {
        this.id = id;
        this.distinguishedName = distinguishedName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistinguishedName() {
        return distinguishedName;
    }

    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }

    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.fnal.ms.dm.entity.Person[id=" + id + "]";
    }

}

*/
package gov.fnal.ms.dm.entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
//@NamedQuery(name = "Person.findAll", query = "select o from Person o")
@NamedQueries({@NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"), 
@NamedQuery(name = "Person.findByDistinguishedName", query = "SELECT p FROM Person p WHERE p.distinguishedName = :distinguishedName"),
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
})
@Table(name = "cms_dbs_migration.Person")
@SequenceGenerator(name="seq", sequenceName="cms_dbs_migration.SEQ_PERSON")
public class Person implements Serializable {
    @Column(nullable = false)
    private String distinguishedName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @Column(nullable = false)
    private Long id;
    
    /*@OneToMany(mappedBy = "person")
    private List<Request> requestList;*/

    public Person() {
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
/*
    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public Request addRequest(Request request) {
        getRequestList().add(request);
        request.setPerson(this);
        return request;
    }

    public Request removeRequest(Request request) {
        getRequestList().remove(request);
        request.setPerson(null);
        return request;
    }*/
}
