/*
package gov.fnal.ms.dm.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Request")
@NamedQueries({@NamedQuery(name = "Request.findById", query = "SELECT r FROM Request r WHERE r.id = :id"), @NamedQuery(name = "Request.findByPath", query = "SELECT r FROM Request r WHERE r.path = :path"), @NamedQuery(name = "Request.findByWithParents", query = "SELECT r FROM Request r WHERE r.withParents = :withParents"), @NamedQuery(name = "Request.findByWithForce", query = "SELECT r FROM Request r WHERE r.withForce = :withForce"), @NamedQuery(name = "Request.findByStatus", query = "SELECT r FROM Request r WHERE r.status = :status"), @NamedQuery(name = "Request.findByProgress", query = "SELECT r FROM Request r WHERE r.progress = :progress"), @NamedQuery(name = "Request.findByDetail", query = "SELECT r FROM Request r WHERE r.detail = :detail"), @NamedQuery(name = "Request.findByNotify", query = "SELECT r FROM Request r WHERE r.notify = :notify")})
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "Path", nullable = false)
    private String path;
    @Column(name = "WithParents")
    private String withParents;
    @Column(name = "WithForce")
    private String withForce;
    @Column(name = "Status", nullable = false)
    private String status;
    @Column(name = "Progress", nullable = false)
    private int progress;
    @Column(name = "Detail")
    private String detail;
    @Column(name = "Notify")
    private String notify;
    @JoinColumn(name = "CreatedBy", referencedColumnName = "ID")
    @ManyToOne
    private Person createdBy;
    @JoinColumn(name = "DstUrl", referencedColumnName = "ID")
    @ManyToOne
    private DBSUrl dstUrl;
    @JoinColumn(name = "SrcUrl", referencedColumnName = "ID")
    @ManyToOne
    private DBSUrl srcUrl;

    public Request() {
    }

    public Request(Long id) {
        this.id = id;
    }

    public Request(Long id, String path, String status, int progress) {
        this.id = id;
        this.path = path;
        this.status = status;
        this.progress = progress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getWithParents() {
        return withParents;
    }

    public void setWithParents(String withParents) {
        this.withParents = withParents;
    }

    public String getWithForce() {
        return withForce;
    }

    public void setWithForce(String withForce) {
        this.withForce = withForce;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public Person getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Person createdBy) {
        this.createdBy = createdBy;
    }

    public DBSUrl getDstUrl() {
        return dstUrl;
    }

    public void setDstUrl(DBSUrl dstUrl) {
        this.dstUrl = dstUrl;
    }

    public DBSUrl getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(DBSUrl srcUrl) {
        this.srcUrl = srcUrl;
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
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.fnal.ms.dm.entity.Request[id=" + id + "]";
    }

}


*/
package gov.fnal.ms.dm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
//@NamedQuery(name = "Request.findAll", query = "select o from Request o")
@NamedQueries({@NamedQuery(name = "Request.findById", query = "SELECT r FROM Request r WHERE r.id = :id"), 
@NamedQuery(name = "Request.findByPath", query = "SELECT r FROM Request r WHERE r.path = :path"), 
@NamedQuery(name = "Request.findByWithParents", query = "SELECT r FROM Request r WHERE r.withParents = :withParents"), 
@NamedQuery(name = "Request.findByWithForce", query = "SELECT r FROM Request r WHERE r.withForce = :withForce"), 
@NamedQuery(name = "Request.findByStatus", query = "SELECT r FROM Request r WHERE r.status = :status"), 
@NamedQuery(name = "Request.findByProgress", query = "SELECT r FROM Request r WHERE r.progress = :progress"), 
@NamedQuery(name = "Request.findByDetail", query = "SELECT r FROM Request r WHERE r.detail = :detail"), 
@NamedQuery(name = "Request.findByNotify", query = "SELECT r FROM Request r WHERE r.notify = :notify"),
@NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r"),
@NamedQuery(name = "Request.findByDn", query = "SELECT r FROM Request r ," +
                    "Person p " +
                    "WHERE p.id = r.person " +
                    "AND p.distinguishedName = :distinguishedName"),
@NamedQuery(name = "Request.findByUnique", query = "SELECT r FROM Request r ," +
                    "Dbsurl dstUrl ," +
                    "Dbsurl srcUrl " +
                    "WHERE r.path = :path " +
                    "AND dstUrl.url = :dstUrl " +
                    "AND srcUrl.url = :srcUrl " +
                    "AND r.dstUrl = dstUrl.id " +
                    "AND r.srcUrl = srcUrl.id")
})
@Table(name = "cms_dbs_migration.Request")
@SequenceGenerator(name="seq", sequenceName="cms_dbs_migration.SEQ_REQUEST")
public class Request implements Serializable {
    private String detail;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @Column(nullable = false)
    private Long id;
    private String notify;
    @Column(nullable = false)
    private String path;
    @Column(nullable = false)
    private Integer progress;
    @Column(nullable = false)
    private String status;
    private String withForce;
    private String withParents;
    @ManyToOne
    @JoinColumn(name = "CreatedBy", referencedColumnName = "ID")
    private Person person;
    @ManyToOne
    @JoinColumn(name = "DstUrl", referencedColumnName = "ID")
    private Dbsurl dstUrl;
    @ManyToOne
    @JoinColumn(name = "SrcUrl", referencedColumnName = "ID")
    private Dbsurl srcUrl;

    public Request() {
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWithForce() {
        return withForce;
    }

    public void setWithForce(String withForce) {
        this.withForce = withForce;
    }

    public String getWithParents() {
        return withParents;
    }

    public void setWithParents(String withParents) {
        this.withParents = withParents;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Dbsurl getDstUrl() {
        return dstUrl;
    }

    public void setDstUrl(Dbsurl dstUrl) {
        this.dstUrl = dstUrl;
    }

    public Dbsurl getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(Dbsurl srcUrl) {
        this.srcUrl = srcUrl;
    }
}
