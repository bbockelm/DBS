package gov.fnal.nvs.dm.entity;
import java.io.Serializable;
public class Processeddataset implements Serializable {
    private Long createdby;
    private Long creationdate;
    private Long id;
    private Long lastmodificationdate;
    private Long lastmodifiedby;
    private String name;
    private Long physicsgroup;
    private Primarydataset primarydataset;
    private Long status;

    public Processeddataset() {
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

    public Long getPhysicsgroup() {
        return physicsgroup;
    }

    public void setPhysicsgroup(Long physicsgroup) {
        this.physicsgroup = physicsgroup;
    }

    public Primarydataset getPrimarydataset() {
        return primarydataset;
    }

    public void setPrimarydataset(Primarydataset primarydataset) {
        this.primarydataset = primarydataset;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
