

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package gov.fnal.ms.dm.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
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
@Table(name = "DBSUrl")
@NamedQueries({@NamedQuery(name = "DBSUrl.findById", query = "SELECT d FROM DBSUrl d WHERE d.id = :id"), @NamedQuery(name = "DBSUrl.findByUrl", query = "SELECT d FROM DBSUrl d WHERE d.url = :url")})
public class Dbsurl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "Url", nullable = false)
    private String url;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dstUrl")
    private Collection<Request> requestCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "srcUrl")
    private Collection<Request> requestCollection1;

    public Dbsurl() {
    }

    public Dbsurl(Long id) {
        this.id = id;
    }

    public Dbsurl(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
    }

    public Collection<Request> getRequestCollection1() {
        return requestCollection1;
    }

    public void setRequestCollection1(Collection<Request> requestCollection1) {
        this.requestCollection1 = requestCollection1;
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
        if (!(object instanceof DBSUrl)) {
            return false;
        }
        DBSUrl other = (DBSUrl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.fnal.ms.dm.entity.DBSUrl[id=" + id + "]";
    }

}

*/
package gov.fnal.ms.dm.entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
//@NamedQuery(name = "Dbsurl.findAll", query = "select o from Dbsurl o")
@NamedQueries({@NamedQuery(name = "Dbsurl.findById", query = "SELECT d FROM Dbsurl d WHERE d.id = :id"), 
@NamedQuery(name = "Dbsurl.findByUrl", query = "SELECT d FROM Dbsurl d WHERE d.url = :url"),
@NamedQuery(name = "Dbsurl.findAll", query = "SELECT d FROM Dbsurl d")
})
@Table(name = "cms_dbs_migration.DBSUrl")
@SequenceGenerator(name="seq", sequenceName="cms_dbs_migration.SEQ_DBSURL")
public class Dbsurl implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String url;
    /*
    //@OneToMany(mappedBy = "dstUrl", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "dstUrl")
    private List<Request> requestList;
    //@OneToMany(mappedBy = "srcUrl", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "srcUrl")
    private List<Request> requestList1;*/

    public Dbsurl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        request.setDstUrl(this);
        return request;
    }

    public Request removeRequest(Request request) {
        getRequestList().remove(request);
        request.setDstUrl(null);
        return request;
    }

    public List<Request> getRequestList1() {
        return requestList1;
    }

    public void setRequestList1(List<Request> requestList1) {
        this.requestList1 = requestList1;
    }

    public Request addRequest1(Request request) {
        getRequestList1().add(request);
        request.setSrcUrl(this);
        return request;
    }

    public Request removeRequest1(Request request) {
        getRequestList1().remove(request);
        request.setSrcUrl(null);
        return request;
    }*/
}
