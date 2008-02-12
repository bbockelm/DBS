package fnal.gov.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "Datatier.findAll", query = "select o from Datatier o")
@Table(name = "DataTier")
public class Datatier implements Serializable {
    private Long createdby;
    private Long creationdate;
    @Id
    @Column(nullable = false)
    private Long id;
    private Long lastmodificationdate;
    private Long lastmodifiedby;
    @Column(nullable = false)
    private String name;

    public Datatier() {
    }

    public Long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    public Long getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Long creationdate) {
        this.creationdate = creationdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLastmodificationdate() {
        return lastmodificationdate;
    }

    public void setLastmodificationdate(Long lastmodificationdate) {
        this.lastmodificationdate = lastmodificationdate;
    }

    public Long getLastmodifiedby() {
        return lastmodifiedby;
    }

    public void setLastmodifiedby(Long lastmodifiedby) {
        this.lastmodifiedby = lastmodifiedby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
