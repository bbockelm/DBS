package fnal.gov.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "Primarydataset.findAll", 
    query = "select o from Primarydataset o")
@Table(name = "PrimaryDataset")
public class Primarydataset implements Serializable {
    @Column(nullable = false)
    private String annotation;
    private Long createdby;
    private Long creationdate;
    private Long description;
    private String enddate;
    @Id
    @Column(nullable = false)
    private Long id;
    private Long lastmodificationdate;
    private Long lastmodifiedby;
    @Column(nullable = false)
    private String name;
    private String startdate;
    @Column(nullable = false)
    private Long type;

    public Primarydataset() {
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
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

    public Long getDescription() {
        return description;
    }

    public void setDescription(Long description) {
        this.description = description;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
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

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
